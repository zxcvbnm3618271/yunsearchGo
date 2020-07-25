var BaseLib = {};

/**
 * 测试网址是否为已输入提取码后的网址
 *
 * @param url string
 * @return boolean
 */
BaseLib.isAccessUrl = function (url) {
    return /https?:\/\/pan\.baidu\.com\/s\/1[a-zA-Z0-9_\-]{5,22}/i.test(url);
};

/**
 * 测试 Referrer 是否为未输入提取码的初始化页面
 *
 * @param url string
 * @return boolean
 */
BaseLib.isInitUrl = function (url) {
    return /https?:\/\/pan\.baidu\.com\/share\/init\?surl=[a-zA-Z0-9_\-]{5,22}/i.test(url);
};

/**
 * 根据网址获取资源 UUID
 *
 * @param url string
 * @return string|null
 */
BaseLib.fetchUuid = function (url) {
    var type = BaseLib.fetchType(url);
    var pid = BaseLib.fetchPid(url);

    if (type !== null && pid !== null) {
        return type + '-' + pid;
    }
    return null;
};

/**
 * 根据网址获取资源类型
 *
 * @param url string
 * @return string|null
 */
BaseLib.fetchType = function (url) {
    if (/https?:\/\/pan\.baidu\.com\/s\/1[a-zA-Z0-9_\-]{5,22}/ig.test(url) ||
        /https?:\/\/pan\.baidu\.com\/share\/init\?surl=[a-zA-Z0-9_\-]{5,22}/ig.test(url)) {
        return 'BDY';
    }

    return null;
};

/**
 * 根据网址获取资源编号
 *
 * @param url string
 * @return string|null
 */
BaseLib.fetchPid = function (url) {
    var matches;
    matches = /https?:\/\/pan\.baidu\.com\/s\/1([a-zA-Z0-9_\-]{5,22})/ig.exec(url);
    if (matches && matches.length === 2) {
        return matches[1];
    }
    matches = /https?:\/\/pan\.baidu\.com\/share\/init\?surl=([a-zA-Z0-9_\-]{5,22})/ig.exec(url);
    if (matches && matches.length === 2) {
        return matches[1];
    }
    return null;
};