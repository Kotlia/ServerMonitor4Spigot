import React, {useEffect, useState} from 'react';
import TableBuilder from 'table-builder'
import Toggle from 'react-toggle'
import "react-toggle/style.css" // for ES6 modules

export default function PluginList() {
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    useEffect(() => {
        fetch("https://api.baakun.com:3000/server/plugins")
            .then(res => res.json())
            .then(result => {
                setIsLoaded(true)
                const m = result.map(it => {
                    return it
                })
                console.log(m)
                setItems(m)
            })
    }, [])
    if (!isLoaded) {
        return (
            <h1>Loading...</h1>
        )
    } else {
        return (
            <div dangerouslySetInnerHTML={{__html:
                    new TableBuilder()
                        .setHeaders({"name": "Name", "version": "Version", "isEnabled": "On/Off"})
                        .setData(items)
                        .render()
            }}/>
        );
    }
}