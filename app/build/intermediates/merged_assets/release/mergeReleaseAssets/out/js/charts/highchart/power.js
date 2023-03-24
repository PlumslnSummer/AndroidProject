function power(val) {

    $('#container').highcharts({

            chart: {
                type: 'gauge',
                plotBackgroundColor: null,
                plotBackgroundImage: null,
                plotBorderWidth: 0,
                plotShadow: false
            },

            title: {
                text: '用电量(kWh)'
            },

            pane: {
                startAngle: -120,
                endAngle: 120,
                background: [{
                    backgroundColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, '#FFF'],
                            [1, '#333']
                        ]
                    },
                    borderWidth: 0,
                    outerRadius: '109%'
                }, {
                    backgroundColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, '#333'],
                            [1, '#FFF']
                        ]
                    },
                    borderWidth: 1,
                    outerRadius: '107%'
                }, {
                    // default background
                }, {
                    backgroundColor: '#DDD',
                    borderWidth: 0,
                    outerRadius: '105%',
                    innerRadius: '103%'
                }]
            },

            // the value axis
            yAxis: {
                min: 0,
                max: 2000,

                minorTickInterval: 'auto',
                minorTickWidth: 1,
                minorTickLength: 10,
                minorTickPosition: 'inside',
                minorTickColor: '#666',

                tickPixelInterval: 30,
                tickWidth: 2,
                tickPosition: 'inside',
                tickLength: 10,
                tickColor: '#666',
                labels: {
                    step: 2,
                    rotation: 'auto'
                },
                title: {
                    text: ''
                },
                plotBands: [{
                    from: 0,
                    to: 800,
                    color: '#55BF3B' // green
                }, {
                    from: 800,
                    to: 1600,
                    color: '#DDDF0D' // yellow
                }, {
                    from: 1600,
                    to: 2000,
                    color: '#DF5353' // red
                }]
            },

            series: [{
                name: 'Power',
                data: [val],
                tooltip: {
                    valueSuffix: ' W'
                }
            }]

        },
        // Add some life
        function(chart) {
            if (!chart.renderer.forExport) {
                setInterval(function() {
//                    var point = chart.series[0].points[0],
//                        newVal,
//                        inc = 20;
//
//
//                    point.update(newVal);

                }, 3000);
            }
        });
}