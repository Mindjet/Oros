import json

from app.crawler.overwatch import OverwatchCrawler

if __name__ == '__main__':
    crawler = OverwatchCrawler()
    heroes = crawler.get_hero_details()
    heroes = json.dumps(heroes, ensure_ascii=False)
    with open('./heroes.json', 'w', encoding='utf-8') as f:
        f.write(heroes)
    crawler.finish()
