import React, { Component }  from 'react';

export default class TableBuilder {
    constructor() {
        this.base = <table />
    }
    addRow(...args) {
        const row = this.base.insertRow(-1)
        args.forEach((it, i) => {
            row.insertCell(i).innerHTML = args[i]
        })
    }
    toJSXElement() {
        return this.base
    }
}