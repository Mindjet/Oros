const fetch = require("node-fetch");
const fs = require('fs');
const cnData = require('./cn.json');

const HERO_BRIEF_URL = 'https://overwatch-api.net/api/v1/hero';

function getHeroBrief(callback) {
    fetch(HERO_BRIEF_URL)
        .then(res => res.json())
        .then(callback);
}

function receiveHeroBrief(response) {
    const data = response.data;
    const output = {data: []};
    data.map(hero => {
        hero = mystify(hero);
        const id = hero.id - 1;
        hero.name = [hero.name, cnData.name[id]];
        hero.description = [hero.description, cnData.description[id]];
        hero.real_name = [hero.real_name, cnData.real_name[id]];
        hero.affiliation = [hero.affiliation, cnData.affiliation[id]];
        hero.base_of_operations = [hero.base_of_operations, cnData.base_of_operations[id]];
        hero.role = cnData.role[id];
        return hero;
    }).forEach(hero => output.data.push(hero));
    writeFile(JSON.stringify(output) + '', './hero-brief.json');
}

function writeFile(source, path) {
    fs.writeFile(path, source, 'utf-8', err => {
        console.log('write file failed', err);
    });
}

function mystify(o) {
    for (let key in o) {
        if (o[key] == null || o[key] === '') {
            o[key] = '■■■';
        }
    }
    return o;
}

getHeroBrief(receiveHeroBrief);
