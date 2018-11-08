from selenium import webdriver

_base_url = ''


class OverwatchCrawler(object):
    def __init__(self):
        self.driver = webdriver.Chrome(executable_path='./driver/chromedriver.exe')

    def get_heroes(self):
        self.driver.get('https://www.aliyun.com/')
