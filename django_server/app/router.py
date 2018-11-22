from rest_framework import routers
from app.viewsets import HeroViewSet, BriefHeroViewSet

router = routers.DefaultRouter()

router.register(r'hero-brief', BriefHeroViewSet, base_name='hero-brief')
router.register(r'heroes', HeroViewSet, base_name='heroes')
