from django.db import models


# Create your models here.
class Ability(models.Model):
    name = models.CharField(max_length=20)
    description = models.CharField(max_length=200)
    icon = models.URLField()
    video = models.URLField()
    hero = models.ForeignKey("Hero", null=True, on_delete=models.SET_NULL)


class Media(models.Model):
    thumbnail = models.URLField()
    name = models.CharField(max_length=20)
    type = models.CharField(max_length=10)
    source = models.URLField()
    hero = models.ForeignKey("Hero", null=True, on_delete=models.SET_NULL)


class Hero(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=30)
    description = models.CharField(max_length=200)
    story = models.CharField(max_length=5000)
    bio = models.CharField(max_length=200)
    position = models.CharField(max_length=30)
    # health = models.IntegerField()
    # armour = models.IntegerField()
    # shield = models.IntegerField()
    # real_name = models.CharField(max_length=20)
    # age = models.IntegerField()
    # height = models.IntegerField()
    affiliation = models.CharField(max_length=50)
    base_of_operation = models.CharField(max_length=50)
    avatar = models.URLField()
    large_avatar = models.URLField()
    # abilities = models.ManyToManyField(to=Ability)
    # media = models.ManyToManyField(to=Media)
    pass
