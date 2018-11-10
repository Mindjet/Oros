import json

from app.crawler import overwatch
from app.models import Hero
from django.http import HttpResponse

def test(request):
    Hero.objects.create(
        name='asdsad'

    )
    return None


def crawler_overwatch_hero(request):
    crawler = overwatch.OverwatchCrawler()
    result = crawler.get_hero_details()
    crawler.finish()
    result = json.dumps(result, ensure_ascii=False)
    return HttpResponse(result, content_type='application/json')
