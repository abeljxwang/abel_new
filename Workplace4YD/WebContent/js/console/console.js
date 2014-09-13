/**
 * é¦–é¡µçš„ç»Ÿè®¡æŠ¥è¡¨æ‰€ç”¨çš„JS
 */

// ç”Ÿæˆ�æœˆåº¦è�¥æ”¶å›¾æ ‡ ï¼ˆHOMEé¡µIDä¸ºrevenue-timeçš„divï¼‰
FusionCharts.ready(function() {
	var revenueChart = new FusionCharts({
		type : 'column2d',
		renderAt : 'revenue-time',
		width : "450",
		height : "300",
		dataFormat : 'json',
		dataSource : {
			"chart" : {
				"caption" : "æœˆåº¦è�¥æ”¶ç»Ÿè®¡",
				"subCaption" : "--2014å¹´",
				"xAxisName" : "æœˆåº¦",
				"yAxisName" : "é‡‘é¢� (äººæ°‘å¸�)",
				"numberPrefix" : "ï¿¥",
				"palette" : "3",
				"usePlotGradientColor" : "0",
				"showPlotBorder" : "0",
				"showCanvasBorder" : "0",
				"canvasBgAlpha" : "0",
				"showAlternateHGridColor" : "0",
				"placevaluesInside" : "1",
				"rotateValues" : "1",
				"valueFontColor" : "#ffffff",
				"labelDisplay" : "rotate",
				"slantLabels" : "1",
				"theme" : "fint"
			},

			"data" : [ {
				"label" : "Jan",
				"value" : "420000",
				"color" : "#008ee4"
			}, {
				"label" : "Feb",
				"value" : "810000",
				"color" : "#008ee4"
			}, {
				"label" : "Mar",
				"value" : "720000",
				"color" : "#008ee4"
			}, {
				"label" : "Apr",
				"value" : "550000",
				"color" : "#9b59b6"
			}, {
				"label" : "May",
				"value" : "910000",
				"color" : "#9b59b6"
			}, {
				"label" : "Jun",
				"value" : "510000",
				"color" : "#9b59b6"
			}, {
				"label" : "Jul",
				"value" : "680000",
				"color" : "#6baa01"
			}, {
				"label" : "Aug",
				"value" : "620000",
				"color" : "#6baa01"
			}, {
				"label" : "Sep",
				"value" : "0",
				"color" : "#6baa01"
			}, {
				"label" : "Oct",
				"value" : "0",
				"color" : "#e44a00"
			}, {
				"label" : "Nov",
				"value" : "0",
				"color" : "#e44a00"
			}, {
				"label" : "Dec",
				"value" : "0",
				"color" : "#e44a00"
			} ]
		}
	});
	revenueChart.render();
});

// ç”Ÿæˆ�æ¡ˆä»¶çŠ¶æ€�ç®¡é�“å›¾ ï¼ˆHOMEé¡µIDä¸ºcase-funnelçš„divï¼‰
FusionCharts
		.ready(function() {
			var conversionChart = new FusionCharts(
					{
						type : 'funnel',
						renderAt : 'case-funnel',
						width : '270',
						height : '300',
						dataFormat : 'json',
						dataSource : {
							"chart" : {
								"caption" : "æ¡ˆä»¶çŠ¶æ€�ç®¡é�“å›¾",
								"subcaption" : "",
								"decimals" : "2",
								"labelDistance" : "25",
								"plotTooltext" : "$label : $percentOfPrevValue",
								// To show the values in percentage
								"showPercentValues" : "0",
								"palette" : "3",
								"paletteColors" : "#0099CC, #FF6633,#FFFF33, #66FF33, #330066,#CC0000",
								"theme" : "fint"
							},
							"data" : [ {
								"label" : "æŽ¥è§¦",
								"value" : "146"
							}, {
								"label" : "æ”¶æ¡ˆ",
								"value" : "73"
							}, {
								"label" : "è°ƒæŸ¥",
								"value" : "54"
							}, {
								"label" : "ä»£ç�†è¯‰è®¼",
								"value" : "32"
							}, {
								"label" : "åº­å‰�å·¥ä½œ",
								"value" : "19"
							}, {
								"label" : "å‡ºåº­",
								"value" : "12"
							}, {
								"label" : "ç»“æ¡ˆå�Ž",
								"value" : "11"
							} ]
						}
					});

			conversionChart.render();
		});

//ç”Ÿæˆ�è�¥æ”¶åœ°åŸŸåˆ†å¸ƒå›¾ï¼ˆHOMEé¡µIDä¸ºrevenue-areaçš„divï¼‰
FusionCharts.ready(function() {
    var salesByStateChart = new FusionCharts({
        type: 'hubei',
        renderAt: 'revenue-area',
        width: '270',
        height: '300',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": "æ¹–åŒ—çœ�ä¸šåŠ¡åˆ†å¸ƒå›¾",
                "subcaption": "2014å¹´",
//                "entityFillHoverColor": "#cccccc",                
                "numberPrefix": "èµ·",
                "showLabels": "1",
                "borderColor" : "#415d6f",
                "theme": "fint"
            },
            "colorrange": {
                "minvalue": "10",
                "startlabel": "Low",
                "endlabel": "High",
                "code": "#FF4411",
                "gradient": "1",
                "color": [
                    {
                        "maxvalue": "100",
                        "displayvalue": "Average",
                        "code": "#FFDD44"
                    },
                    {
                        "maxvalue": "500",
                        "code": "#6baa01"
                    }
                ]
            },
            "data": [
                {
                    "id": "WH",
                    "value": "433"
                },
                {
                    "id": "EZ",
                    "value": "15"
                },
                {
                    "id": "HS",
                    "value": "33"
                },
                {
                    "id": "XN",
                    "value": "11"
                },
                {
                    "id": "JZ",
                    "value": "78"
                },
                {
                    "id": "YC",
                    "value": "280"
                },
                {
                    "id": "XY",
                    "value": "327"
                },
                {
                    "id": "ES",
                    "value": "9"
                },
                {
                    "id": "XG",
                    "value": "79"
                },
                {
                    "id": "SY",
                    "value": "122"
                },
                {
                    "id": "SZ",
                    "value": "298"
                },
                {
                    "id": "HG",
                    "value": "103"
                }
            ]
        }
    }).render();

});

$('#example').tooltip(options);