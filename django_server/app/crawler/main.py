from app.crawler.overwatch import OverwatchCrawler

if __name__ == '__main__':
    crawler = OverwatchCrawler()
    heroes = crawler.get_hero_details()
    print(heroes)
    crawler.finish()
