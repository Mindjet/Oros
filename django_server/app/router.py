from rest_framework import routers
from app.viewsets import HeroViewSet, BriefHeroViewSet, SpecificHeroViewSet

router = routers.DefaultRouter()

router.register(r'hero-brief', BriefHeroViewSet, base_name='hero-brief')
router.register(r'heroes', HeroViewSet, base_name='heroes')
router.register(r'hero', viewset=SpecificHeroViewSet, base_name='hero')
