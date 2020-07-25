var BackgroundLib = {
    // Web Server
    webServer: 'http://39.108.55.167:8080/yunsearch/',
    // API Server
    apiServer: 'http://39.108.55.167:8080/yunsearch/api',
    // Manifest Resource
    manifest: chrome.runtime.getManifest()
};

/**
 * 获取 Selector
 *
 * @return array|null
 */
BackgroundLib.fetchSelector = function () {
    var query = 'client_version=' + encodeURIComponent(BackgroundLib.manifest.version);
    var url = BackgroundLib.apiServer + '/selector' + '?' + query;

    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, false);
    xhr.send(null);
    var response = JSON.parse(xhr.responseText);
    response=response.bdy;
    console.log(xhr.responseText);
    if (xhr.status === 200) {
        return response;
    } else {
        return null;
    }

    // var query = 'access_key=4fxNbkKKJX2pAm3b8AEu2zT5d2MbqGbD' + '&client_version=' + encodeURIComponent(BackgroundLib.manifest.version);
    // var url ='https://ypsuperkey.meek.com.cn/api/selector' + '?' + query;
    //
    // var xhr = new XMLHttpRequest();
    // xhr.open("GET", url, false);
    // xhr.send(null);
    // var response = JSON.parse(xhr.responseText);
    // if (xhr.status === 200) {
    //     return response;
    // } else {
    //     return null;
    // }
};

/**
 * 从 API Server 获取提取密码
 *
 * @param uuid string
 * @return string|null 当提取码为一些特殊字符串时，为服务器错误代码
 */
BackgroundLib.fetchAccessCode = function (uuid) {
    var query = 'uuid='+uuid+'&client_version=' + encodeURIComponent(BackgroundLib.manifest.version);
    var url = BackgroundLib.apiServer + '/items' + '?' + query;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, false);
    xhr.send(null);
    var response = JSON.parse(xhr.responseText);
    console.log("返回提取码对象"+response);
    if (xhr.status === 200) {
//         if (response.access_code==null){
//             let n = "client_version=2019.2",
//                 o = "https://ypsuperkey.meek.com.cn/api" + "/v1/items/" + uuid + "?" + n,
//                 r = new XMLHttpRequest;
//             r.open("GET", o, !1),
//                 r.send(null);
//             let t = JSON.parse(r.responseText);
//             return 200 === r.status ? t.access_code : 404 === r.status ? null : 415 === r.status ? "*_*HTTP-415*_*" : 500 === r.status ? "*_*HTTP-500*_*" : 503 === r.status ? "*_*HTTP-503*_*" : "*_*ERROR*_*"
//         }
        return response.access_code;
    } else if (xhr.status === 404) {
        return null;
    } else if (xhr.status === 415) { // 当前插件的版本已过期
        return '*_*HTTP-415*_*';
    } else if (xhr.status === 500) { // 服务器错误
        return '*_*HTTP-500*_*';
    } else if (xhr.status === 503) { // 服务暂时不可用
        return '*_*HTTP-503*_*';
    } else {                         // 未知错误
        return '*_*ERROR*_*';
    }
};

/**
 * 发送提取码到服务器
 */
BackgroundLib.sendAccessCode = function(uuid, accessCode) {
    var post = 'method=sendAccessCode'+'&uuid='+uuid+'&access_code=' +
        encodeURIComponent(accessCode) + '&client_version=' +
        encodeURIComponent(BackgroundLib.manifest.version);
    var url = BackgroundLib.apiServer + '/items';

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(post);
    var response = JSON.parse(xhr.responseText);
    console.log('数据已发送至服务器。' + xhr.status + ' // ' + JSON.stringify(response));
};

/**
 * 发送链接状态到服务器
 */
BackgroundLib.sendState = function(uuid, state) {
    var post = 'method=sendState'+'&uuid='+uuid + '&state=' + encodeURIComponent(state) + '&client_version=' + encodeURIComponent(BackgroundLib.manifest.version);
    var url = BackgroundLib.apiServer + '/items';

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(post);
    var response = JSON.parse(xhr.responseText);
    console.log('数据已发送至服务器。' + xhr.status + ' // ' + JSON.stringify(response));
};

/**
 * 发送来路数据到服务器
 */
BackgroundLib.sendReferrer = function(uuid, referrer) {
    var post = 'method=sendReferrer'+'&uuid='+uuid+ '&referrer[url]=' + encodeURIComponent(referrer.url) + '&referrer[title]=' + encodeURIComponent(referrer.title) + '&client_version=' + encodeURIComponent(BackgroundLib.manifest.version);
    var url = BackgroundLib.apiServer + '/items';

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(post);
    var response = JSON.parse(xhr.responseText);
    console.log('数据已发送至服务器。' + xhr.status + ' // ' + JSON.stringify(response));
};

/**
 * 发送资源数据到服务器
 */
BackgroundLib.sendResource = function(uuid, resource) {
    var post = 'uuid=' + uuid + '&resource[mode]=' + encodeURIComponent(resource.mode) + '&resource[share_time]=' + encodeURIComponent(resource.share_time) +
        '&resource[name]=' + encodeURIComponent(resource.name) + '&resource[size]=' + encodeURIComponent(resource.size) + '&resource[user_id]=' + encodeURIComponent(resource.user_id) + '&resource[user_name]=' + encodeURIComponent(resource.user_name) +
        '&client_version=' + encodeURIComponent(BackgroundLib.manifest.version);
    var url = BackgroundLib.apiServer + "/resources" ;

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(post);
    var response = JSON.parse(xhr.responseText);
    console.log('数据已发送至服务器。' + xhr.status + ' // ' + JSON.stringify(response));
};

/**
 * 发送文件夹子节点数据到服务器
 */
BackgroundLib.sendSubnodes = function(uuid, subnodes) {
    var post = 'uuid=' + uuid + '&subnodes=' + encodeURIComponent(subnodes) + '&client_version=' + encodeURIComponent(BackgroundLib.manifest.version);
    var url = BackgroundLib.apiServer + '/resources/subnodes';

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(post);
    var response = JSON.parse(xhr.responseText);
    console.log('数据已发送至服务器。' + xhr.status + ' // ' + JSON.stringify(response));
};

/**
 * 检查待发送的数据是否完整
 */
BackgroundLib.checkPreSendCompleteness = function (data) {
    return (typeof(data.uuid) !== 'undefined' &&
        typeof(data.accessCode) !== 'undefined' &&
        typeof(data.url) !== 'undefined' &&
        typeof(data.referrer) !== 'undefined');
};

/**
 * 检查最新版本
 *
 * @return array|null
 */
BackgroundLib.checkUpdate = function () {
    var url = BackgroundLib.apiServer + '/check-update';
    var post =  'client_version=' + encodeURIComponent(BackgroundLib.manifest.version);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(post);

    return JSON.parse(xhr.responseText);
};

/**
 * 检查资源的实时数据
 *
 * @param uuids array
 * @return array|null
 */
BackgroundLib.checkData = function (uuids) {
    var url = "https://ypsuperkey.meek.com.cn/api/v1/item/check-data";
    var post = 'uuids=' + encodeURIComponent(uuids.toString()) +
        '&client_version=' +
        encodeURIComponent(BackgroundLib.manifest.version);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(post);
    return JSON.parse(xhr.responseText);
};

/**
 * 处理 Result 结果，如果符合要求，则发送数据并删除原始记录
 *
 * @param uuid string
 * @param result object
 * @param sendResponse callback
 */
var allowshare;
BackgroundLib.handleResult = function(uuid, result, sendResponse) {
    new Promise(function(resolve, reject) {
        chrome.storage.local.get({allowShare: true}, function (items) {
            resolve(items.allowShare);
        });
    }).then(function (allowShare) {
        allowshare=allowShare;
    })
        if (BackgroundLib.checkPreSendCompleteness(result[uuid])) {
            if (allowshare) {
                BackgroundLib.sendAccessCode(result[uuid].uuid, result[uuid].accessCode);
                sendResponse({'message': '已将提取码发送至服务器。','result': result});
            } else {
                sendResponse({'message': '暂时停止将提取码发送至服务器。','result': result});
            }
            delete result[uuid];
        } else {
            sendResponse({'message': '数据还不完整。', 'result': result});
        }
    sendResponse({'message': '已将提取码发送至服务器。','result': result});

};

/**
 * 处理 Collector 结果，如果符合要求，则发送数据并删除原始记录
 *
 * @param uuid string
 * @param collector object
 * @param sendResponse callback
 */
BackgroundLib.handleCollector = function(uuid, collector, sendResponse) {

    if (typeof(collector[uuid].state) !== 'undefined') {
        BackgroundLib.sendState(collector[uuid].uuid, collector[uuid].state);
        sendResponse({'message': '已将 State 发送至服务器。'});
    }
    if (typeof(collector[uuid].referrer) !== 'undefined') {
        BackgroundLib.sendReferrer(collector[uuid].uuid, collector[uuid].referrer);
        sendResponse({'message': '已将 Referrer 发送至服务器。'});
    }
    delete collector[uuid];
};

/**
 * 处理 Resource 结果，如果符合要求，则发送数据并删除原始记录
 *
 * @param uuid string
 * @param resource object
 * @param sendResponse callback
 */
BackgroundLib.handleResource = function(uuid, resource, sendResponse) {
    BackgroundLib.sendResource(uuid, resource);
    sendResponse({'message': '已将 Resource 发送至服务器。'});
};

/**
 * 处理 Subnodes 结果，如果符合要求，则发送数据并删除原始记录
 *
 * @param uuid string
 * @param subnodes object
 * @param sendResponse callback
 */
BackgroundLib.handleSubnodes = function(uuid, subnodes, sendResponse) {
    BackgroundLib.sendSubnodes(uuid, subnodes);
    sendResponse({'message': '已将 Subnodes 发送至服务器。'});
};

/*--------------------------------------------------------------------------------------------------------------------*/
/* 初始化 */
(function() {
    chrome.storage.local.get({allowShare: true}, function (items) {
        if (chrome.runtime.lastError) {
            console.log('发生错误：' + chrome.runtime.lastError);
            return;
        }
        if (items.allowShare) {
            chrome.browserAction.setIcon({
                path: {
                    '19': 'images/icon_19.png',
                    '38': 'images/icon_38.png'
                }
            });
        } else {
            chrome.browserAction.setIcon({
                path: {
                    '19': 'images/icon-inactive_19.png',
                    '38': 'images/icon-inactive_38.png'
                }
            });
        }
    });
})();

/* 获取远程 Selector */
(function() {
    var selector = BackgroundLib.fetchSelector();
    console.log("获取selector"+selector);
    chrome.storage.local.set({'selector': selector}, function () {
    });
})();

/* 检查最新版本 */
(function() {
    var update = BackgroundLib.checkUpdate();
    if (!update.is_new) {
        // 由于版本更新太过频繁，导致手动安装的用户疲于升级，暂时关闭该提示，后期版本迭代稳定后再开启
        chrome.browserAction.setBadgeText({text: 'new'});
        chrome.browserAction.setBadgeBackgroundColor({color: [255, 0, 0, 255]});
    }
    chrome.storage.local.set({'update': update}, function () {
    });
})();

/* Google Analytics */
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-109184535-1']);
_gaq.push(['_trackEvent', 'EXTENSION', 'LOADED', navigator.userAgent]);

(function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = 'https://ssl.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();

/*--------------------------------------------------------------------------------------------------------------------*/
// 临时保存客户端获取到的资源编号，提取密码，网址等数据
var result = {};
var cop={};
// 临时保存客户端收集的链接状态，来路，标题等数据
var collector = {};

chrome.runtime.onMessage.addListener(function(request, sender, sendResponse)
{
    var uuid = request.uuid;
    if (typeof(uuid) !== 'undefined' && typeof(result[uuid]) === 'undefined') {
        result[uuid] = {};
    }
    if (typeof(uuid) !== 'undefined' && typeof(cop[uuid]) === 'undefined') {
        cop[uuid] = {};
    }
    if (typeof(uuid) !== 'undefined' && typeof(collector[uuid]) === 'undefined') {
        collector[uuid] = {};
    }

    switch (request.cmd) {
        case 'SEND_URL':
            result[uuid].uuid = request.uuid;
            result[uuid].url = request.url;
            result[uuid].referrer = request.referrer;
            cop[uuid].uuid = request.uuid;
            cop[uuid].url = request.url;
            cop[uuid].referrer = request.referrer;
            BackgroundLib.handleResult(uuid, result, sendResponse);
            BackgroundLib.handleCollector(uuid, cop, sendResponse);

            break;
        case 'SEND_ACCESS_CODE':
            result[uuid].uuid = request.uuid;
            result[uuid].accessCode = request.accessCode;
            BackgroundLib.handleResult(uuid, result, sendResponse);
            break;
        case 'SEND_STATE':
            collector[uuid].uuid = request.uuid;
            collector[uuid].state = request.state;
            BackgroundLib.handleCollector(uuid, collector, sendResponse);
            break;
        case 'SEND_REFERRER':
            collector[uuid].uuid = request.uuid;
            collector[uuid].referrer = request.referrer;
            BackgroundLib.handleCollector(uuid, collector, sendResponse);
            break;
        case 'FETCH_ACCESS_CODE':
            var accessCode = BackgroundLib.fetchAccessCode(request.uuid);
            sendResponse(accessCode);
            if (accessCode === null) {
                window._gaq.push(["_trackEvent", "ACCESS_CODE", "FETCH_FAILED", BackgroundLib.manifest.version])
            } else {
                window._gaq.push(["_trackEvent", "ACCESS_CODE", "FETCH_SUCCEED", BackgroundLib.manifest.version])
            }
            break;
        case 'CHECK_DATA':
            var uuids = [];
            for (var i in request.uncheckedUuids) {
                if (!request.uncheckedUuids.hasOwnProperty(i)) {
                    continue;
                }
                uuids.push(i);
            }
            var checkedUuids = BackgroundLib.checkData(uuids);
            sendResponse(checkedUuids);

            _gaq.push(['_trackEvent', 'STATE', 'CHECK_DATA', BackgroundLib.manifest.version]);
            break;
        case 'SEND_RESOURCE':
            BackgroundLib.handleResource(uuid, request.resource, sendResponse);
            break;
        case 'SEND_RESOURCE_SUBNODES':
            BackgroundLib.handleSubnodes(uuid, request.subnodes, sendResponse);
            break;
    }

    return true;
});

/**
 * 通过监听 WebRequest 收集来路数据
 */
chrome.webRequest.onBeforeSendHeaders.addListener(
    function(details) {
        var uuid = BaseLib.fetchUuid(details.url);
        if (typeof(uuid) !== 'undefined' && typeof(collector[uuid]) === 'undefined') {
            collector[uuid] = {};
        }

        for (var i = 0; i < details.requestHeaders.length; i++) {
            if (details.requestHeaders[i].name === 'Referer') {
                var referrer = {'url': details.requestHeaders[i].value};
                // 需要输入提取码的链接来路是来自百度的 init 页面，这里需要过滤掉，不能算正常的来路
                if (BaseLib.isInitUrl(referrer.url) || BaseLib.isAccessUrl(referrer.url)) {
                    continue;
                }
                collector[uuid].uuid = uuid;
                collector[uuid].referrer = referrer;
                (function(uuid, referrer){
                    chrome.tabs.query({'url': referrer.url}, function(result) {
                        if (result.length > 0) {
                            collector[uuid].referrer.title = result[0].title;
                            BackgroundLib.handleCollector(uuid, collector, function(response) {
                                console.log('收到：' + JSON.stringify(response));
                            });
                        }
                    });
                })(uuid, referrer);
            }
        }

        return {requestHeaders: details.requestHeaders};
    },
    {urls: ["https://pan.baidu.com/s/*"]},
    ["blocking", "requestHeaders"]
);

/**
 * 通过监听 WebRequest 收集 404 状态的链接
 */
chrome.webRequest.onHeadersReceived.addListener(
    function(details) {
        var uuid = BaseLib.fetchUuid(details.url);
        if (typeof(uuid) !== 'undefined' && typeof(collector[uuid]) === 'undefined') {
            collector[uuid] = {};
        }

        for (var i = 0; i < details.responseHeaders.length; i++) {
            if (details.responseHeaders[i].name === 'Location' && details.responseHeaders[i].value === '/error/404.html') {
                collector[uuid].uuid = uuid;
                collector[uuid].state = 'INVALID';
                BackgroundLib.handleCollector(uuid, collector, function(response) {
                    console.log('收到：' + JSON.stringify(response));
                });
            }
        }

        return {responseHeaders: details.responseHeaders};
    },
    {urls: ["https://pan.baidu.com/s/*"]},
    ["blocking", "responseHeaders"]
);

chrome.runtime.onInstalled.addListener(function () {
    // var url = BackgroundLib.webServer + '/installed?version=' + encodeURIComponent(BackgroundLib.manifest.version);
    // window.open(url);

    _gaq.push(['_trackEvent', 'EXTENSION', 'INSTALLED', navigator.userAgent]);
});

chrome.runtime.setUninstallURL(BackgroundLib.webServer + '/uninstalled');