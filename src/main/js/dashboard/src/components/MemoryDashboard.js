import React, { Component } from 'react';
import { Line } from 'react-chartjs-2';
import Chart from 'chart.js'
import 'chartjs-plugin-streaming';

export default class App extends Component {
    static color = Chart.helpers.color;
    static memQueue = []
    static swapQueue = []
    static fetchedSysData;
    static chartColors = {
        red: 'rgb(255, 99, 132)',
        orange: 'rgb(255, 159, 64)',
        yellow: 'rgb(255, 205, 86)',
        green: 'rgb(75, 192, 192)',
        blue: 'rgb(54, 162, 235)',
        purple: 'rgb(153, 102, 255)',
        grey: 'rgb(201, 203, 207)'
    };
    static getQueueElement() {
        fetch("https://api.baakun.com:3000/sysinfo")
            .then(r => r.json())
            .then(d => {
                App.fetchedSysData = d
                App.memQueue.push(d.usedMemP)
                App.swapQueue.push(d.usedSwapP)
            })
        return [
            (App.memQueue.shift()) + 3,
            (App.swapQueue.shift()),
        ]
    }
    render() {
        return (
            <Line
                data={{
                    datasets: [{
                        label: 'RAM (16GB)',
                        backgroundColor: App.color(App.chartColors.red).alpha(0.5).rgbString(),
                        borderColor: App.chartColors.red,
                        cubicInterpolationMode: 'monotone',
                        fill: false,
                        data: []
                    }, {
                        label: 'SWAP (2GB)',
                        backgroundColor: App.color(App.chartColors.green).alpha(0.5).rgbString(),
                        borderColor: App.chartColors.green,
                        fill: false,
                        cubicInterpolationMode: 'monotone',
                        data: []
                    }]
                }}
                options={{
                    scales: {
                        xAxes: [{
                            type: 'realtime',
                            realtime: {
                                onRefresh: (chart) => {
                                    chart.data.datasets.forEach((dataset) => {
                                        dataset.data.push({
                                            x: Date.now(),
                                            y: App.getQueueElement()[dataset.label === 'RAM (16GB)'? 0 : 1]
                                        });
                                    });
                                },
                                delay: 2000
                            }
                        }],
                        yAxes: [{
                            scaleLabel: {
                                display: true,
                                labelString: 'value',
                            },
                            ticks: {
                                max: 100,
                                min: 0
                            }
                        }]
                    }
                }}
            />
        );
    }
}