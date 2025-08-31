from celery import Celery
from app.scraper import get_price


celery = Celery(
    "tasks",
    broker="redis://redis:6379/0",  # 👈 Redis service name in Docker
    backend="redis://redis:6379/0"
)

@celery.task
def scrape_price_task(url):
    return get_price(url)
