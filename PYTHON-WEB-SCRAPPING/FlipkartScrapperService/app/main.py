from fastapi import FastAPI
from pydantic import BaseModel
from celery.result import AsyncResult
from app.tasks import scrape_price_task
from app.worker import celery

app = FastAPI()

class Product(BaseModel):
    url: str

@app.post("/scrape")
async def scrape(product: Product):
    task = scrape_price_task.delay(product.url)
    return {"message": "Task submitted", "task_id": task.id}

@app.get("/result/{task_id}")
async def get_result(task_id: str):
    result = AsyncResult(task_id, app=celery)
    if result.state == "PENDING":
        return {"status": "Processing"}
    elif result.state == "SUCCESS":
        return {"status": "Success", "price": result.result}
    elif result.state == "FAILURE":
        return {"status": "Failed", "reason": str(result.result)}
    else:
        return {"status": result.state}