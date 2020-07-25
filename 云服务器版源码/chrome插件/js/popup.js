var manifest = chrome.runtime.getManifest();

$(document).ready(function () {

    /* 检查新版本 */
    (function () {
        chrome.storage.local.get({update: null}, function (items) {
            if (chrome.runtime.lastError) {
                console.log('发生错误：' + chrome.runtime.lastError);
                return;
            }
            var $update = $('.i-update');
            var $link = $update.parent();
            if (items.update.latest_version==manifest.version) {
                //点击进入官网
                $link.data('href', 'http://39.108.55.167:8080/yunsearch/');
                $update.html('当前版本：' + manifest.version + ' 已是最新版');
            } else {
                $update.html('发现新版本 <strong>' + items.update.latest_version + '</strong>，点此升级').addClass('c-highlight');
            }
        });
    })();

    /* 初始化 */
    (function () {
        chrome.storage.local.get({allowShare: true}, function (items) {
            if (chrome.runtime.lastError) {
                console.log('发生错误：' + chrome.runtime.lastError);
                return;
            }
            if (items.allowShare) {
                honeySwitch.showOn("#i_share");
            } else {
                honeySwitch.showOff("#i_share");
            }
        });
        honeySwitch.switchEvent('#i_share', function () {
            chrome.storage.local.set({allowShare: true}, function () {
                chrome.browserAction.setIcon({
                    path: {
                        '19': 'images/icon_19.png',
                        '38': 'images/icon_38.png'
                    }
                });
            });
        }, function () {
            chrome.storage.local.set({allowShare: false}, function () {
                chrome.browserAction.setIcon({
                    path: {
                        '19': 'images/icon-inactive_19.png',
                        '38': 'images/icon-inactive_38.png'
                    }
                });
            });
        });
    })();

});

/* 响应菜单链接事件 */
$(document).on('click', 'li.i-link', function () {
    var href = $(this).data('href');
    window.open(href);
});
