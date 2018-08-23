const fetch = require("node-fetch");
const fs = require('fs');
const cnData = require('./cn.json');
const commonData = require('./common.json');

const HERO_BRIEF_URL = 'https://overwatch-api.net/api/v1/hero';

function getHeroBrief(callback) {
    fetch(HERO_BRIEF_URL)
        .then(res => res.json())
        .then(res => {
            writeFile(JSON.stringify(res), './raw-hero-brief.json');
            return res;
        })
        .then(callback);
}

function receiveHeroBrief(response) {
    const data = response.data;
    const output = {data: []};
    data.map(hero => {
        const id = hero.id - 1;
        hero.name = [hero.name, cnData.name[id]];
        hero.description = [hero.description, cnData.description[id]];
        hero.real_name = [hero.real_name, cnData.real_name[id]];
        hero.affiliation = [hero.affiliation, cnData.affiliation[id]];
        hero.base_of_operations = [hero.base_of_operations, cnData.base_of_operations[id]];
        hero.role = commonData.role[id];
        hero.avatar = commonData.avatar[id];
        return hero;
    })
        .map(hero => mystifyObject(hero))
        .forEach(hero => output.data.push(hero));
    writeFile(JSON.stringify(output), './hero-brief.json');
}

function writeFile(source, path) {
    fs.writeFile(path, source, 'utf-8', err => {
        if (err != null) {
            console.log("write file failed, " + err);
            return;
        }
        console.log("write file succeed.");
    });
}

function mystifyObject(o) {
    if (typeof o === 'number') {
        return o;
    }
    if (o == null || o === '') {
        return '■■■';
    }
    if (typeof o === 'string') {
        return o;
    }
    if (o instanceof Array) {
        return o.map(item => mystifyObject(item));
    }
    for (let key in o) {
        o[key] = mystifyObject(o[key]);
    }
    return o;
}

getHeroBrief(receiveHeroBrief);
