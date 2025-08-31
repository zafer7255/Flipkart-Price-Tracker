from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import time

def get_price(url: str) -> str:
    options = Options()
    options.add_argument("--headless")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")

    driver = webdriver.Chrome(options=options)

    try:
        driver.get(url)
        time.sleep(5)  # wait for page to load

        # Find the element where class starts with 'Nx9bqj CxhGGd'
        price_element = driver.find_element(
            By.XPATH, "//div[starts-with(@class, 'Nx9bqj CxhGGd')]"
        )

        return price_element.text

    finally:
        driver.quit()
