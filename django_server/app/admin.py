from django.contrib import admin

from app.models import Hero, Ability, Media


class AbilityInline(admin.TabularInline):
    model = Ability


class MediaInline(admin.TabularInline):
    model = Media


class HeroUI(admin.ModelAdmin):
    list_display = ('name', 'affiliation', 'base_of_operation', 'position', 'bio')
    inlines = [AbilityInline, MediaInline]


# Register your models here.
admin.site.register(Hero, HeroUI)
