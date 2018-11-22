from rest_framework import routers
from app.viewsets import HeroViewSet, BriefHeroViewSet

router = routers.DefaultRouter()

router.register(r'heroes', HeroViewSet)
router.register(r'hero-brief', BriefHeroViewSet)
