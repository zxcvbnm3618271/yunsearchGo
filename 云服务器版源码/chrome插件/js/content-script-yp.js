!
    function(e) {
        function t(r) {
            if (n[r]) return n[r].exports;
            var o = n[r] = {
                i: r,
                l: !1,
                exports: {}
            };
            return e[r].call(o.exports, o, o.exports, t),
                o.l = !0,
                o.exports
        }
        var n = {};
        t.m = e,
            t.c = n,
            t.d = function(e, n, r) {
                t.o(e, n) || Object.defineProperty(e, n, {
                    configurable: !1,
                    enumerable: !0,
                    get: r
                })
            },
            t.n = function(e) {
                var n = e && e.__esModule ?
                    function() {
                        return e.
                            default
                    }:
                    function() {
                        return e
                    };
                return t.d(n, "a", n),
                    n
            },
            t.o = function(e, t) {
                return Object.prototype.hasOwnProperty.call(e, t)
            },
            t.p = "",
            t(t.s = 2)
    } ([, ,
        function(e, t, n) {
            var r = n(3),
                o = n(7),
                i = {};
            $(document).ready(function() {
                var e, t = BaseLib.fetchUuid(location.href); !
                    function() {
                        BaseLib.isInitUrl(location.href) && new Promise(function(e, t) {
                            chrome.storage.local.get({
                                    selector: null
                                },
                                function(t) {
                                    i = t.selector,
                                        e()
                                })
                        }).then(function() {
                            new Promise(function(e, t) {
                                chrome.storage.local.get({
                                        allowShare: !0
                                    },
                                    function(n) {
                                        if (n.allowShare) e();
                                        else {
                                            $(i.notice).html('<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAADsAAAA7ABJ8QPrQAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTNui8sowAAAQGSURBVGiB5ZrPixxFFMc/r2ays9F0EmMOu44uLrIehIAkcUEIRoZgDtmDCf4BAe9BvIRNIx6S9ijiXcgfsCQ5bA4RXTxEhJgsQsBDlrASXTeHsP7oSGZmZ7o8VG3v/Oie6e6dX0s+UJeaV1XfN9XV3e+9Fq01vcD35DWgBLwNvAu8Ahy2Pz8B/gR+An4BlhxX/96LdWUnDvieTAFzwAcY4RNAocuwCvAY48i3wKLj6kdZNWRywPfkBHABI35v1sUtz4BF4GvH1bfTDk7lgO/JDPAF8FHahRKyAFxyXL2SdIBKauh78gmwTP/EY+detmslousO+J7sB76hv8KjWAA+dlz9byejjg74nkxgrs9jvdWWmHvAnOPqx3EGsQ74nkwCPwBv9kVach4A7zuuXo/6MfIM2MtmkeGLB6Nh0WpqI+4QXwWO9ktRBo5iNLXR5oDvyQXgbJ8FZeGs1dZE0xnwPZkGfgXGBygsDWXgLcfVq1sdrTvwJWnEa6AGerO5UY+wrQObLa1m50jOuNUYEu6A78k7wJ1U0wWgDk5BfgzCnRT003V0+T/INYjf66Ccou3QgELXy+iN1RSP05BZx9U/A+QbOufTzKArUCjNUzjxOagc23+lEPyzyrOr7xH45vYt+yd54fyPqANTDTMIBDUqt69Q+f4y0u0VsJl54BxY331PXgXOJFdvRuZePwX5Aqg8qD225VEvzSCH3kDXQddBvTyDOjgNkmtoCnJj5KZPGRXpLqUzVnO4eR8CY6mmAKiVOzjZcBB0kG2OeMYwmkMHTmeZZcicBlC+J0XgyJDFZOGI70lRYcLAieHpyBwRTgAlBRynexjYP+rVrD4UgOMKmO2poMEyq4BiV7PRpajYTn3sRg6nf4iPGAqTdNqtPFHAWv/X6U32L4I1Rdo30CwEUe/XPeGOAu4C1X6t0EeqwF0FLAGREf+Isw4sKcfVa8D9YavJwH3H1Wtbt9FbQ5WSjVuw/Tp9gyznIN8hfJaGR4zk4u06zRFPFaPZOOC4+g/gZuLhAgRQ/+07qFUgqEGwaVuNYGMFvfEwDL70xkOCv1ba7KhVqK8umbuspHLgptU8uKBexl9E9k0SPhNEoFYl+PvRjoL61rzQdWyolghtxLWmV0WxLX6LentkKWLt0v37NxxXh4m3Vt8/xSSPkiFAHmRPc2sTj+lrs8unFl+2GkOaHLAZr4upphwsFxuzchCTXvc9ucbo5UevO64+19oZd3zOY8pJo8IyRlMbkQ7Yss4cprgwbB5gqjSRpabYG5itiJzElHmGxT3gZFx1BrrcgW1tqoQpuA2aBaDUqT4GKerEtvR5Gdi3c20deQp85rj6qyTGu77Q/Xx9atDKrv3YI4phfW7zP95yiUc6CZGWAAAAAElFTkSuQmCC" style="width:14px;margin-right:5px;margin-bottom:2px;vertical-align:middle;">暂停分享，系统不会记录任何数据，请输入密码：'),
                                                t()
                                        }
                                    })
                            }).then(function() {
                                e = {
                                    cmd: "FETCH_ACCESS_CODE",
                                    uuid: t
                                },
                                    chrome.runtime.sendMessage(e,
                                        function(e) {
                                            var t = e,
                                                n = '<img src="data:image/png;base64,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" style="width:14px;margin-right:5px;margin-bottom:2px;vertical-align:middle;">';
                                            null=== t ? $(i.notice).html(n + "未发现能解开该资源的钥匙，请输入提取密码：") : void 0 === t ? $(i.notice).html(n + "获取远程提取密码失败，请刷新页面重新获取！") : "*_*HTTP-415*_*" === t ? $(i.notice).html(n + '聚合搜索版本过低，赶紧 <a href="http://localhost:8080/yunsearch/update" target="_blank">点此</a> 升级！') : "*_*HTTP-500*_*" === t ? $(i.notice).html(n + "服务器发生错误，请稍后再试！") : "*_*HTTP-503*_*" === t ? $(i.notice).html(n + "服务器正在维护，暂时无法提供服务！") : "*_*ERROR*_*" === t ? $(i.notice).html(n + '服务器发生未知错误，请 <a href="http://localhost:8080/yunsearch/feedback" target="_blank">点此</a> 提交报告！') : ($(i.input).val(t), $(i.submit).trigger("click"))
                                        })
                            }).
                            catch(function() {})
                        })
                    } (),
                    function() {
                    //如果是由初始页面跳转至分享页面，则表示已输入提取码
                        BaseLib.isAccessUrl(location.href) && BaseLib.isInitUrl(document.referrer) && (e = {
                            cmd: "SEND_URL",
                            uuid: t,
                            url: location.href,
                            referrer: document.referrer
                        },//触发回调函数，返回message：已将提取码发送至服务器
                            chrome.runtime.sendMessage(e,
                                function(e) {
                                    console.log("收到：" + JSON.stringify(e))
                                }))
                    } (),
                    function() {
                        new Promise(function(e, t) {
                            chrome.storage.local.get({
                                    selector: null
                                },
                                function(t) {
                                    i = t.selector,
                                        e()
                                })
                        }).then(function() {
                            let e = "";
                            setInterval(function() {
                                    e = $(i.input).val()
                                },
                                200);
                            let n = function() {
                                let n = $(i.input).val();
                                "****" !== e && 4 === e.length && (n = e);
                                let r = {
                                    cmd: "SEND_ACCESS_CODE",
                                    uuid: t,
                                    accessCode: n
                                };
                                chrome.runtime.sendMessage(r,
                                    function(e) {
                                        console.log("收到：" + JSON.stringify(e))
                                    })
                            };
                            $(document).on("click", i.submit,
                                function() {
                                    n()
                                }),
                                $(document).on("keydown", i.input,
                                    function(e) {
                                        13 === e.which && n()
                                    })
                        })
                    } (),
                    function() {
                        $(".share-valid-check").length > 0 && (e = {
                            cmd: "SEND_STATE",
                            uuid: t,
                            state: "VALID"
                        },
                            chrome.runtime.sendMessage(e,
                                function(e) {
                                    console.log("收到：" + JSON.stringify(e))
                                })),
                        $("#share_nofound_des").length > 0 && (e = {
                            cmd: "SEND_STATE",
                            uuid: t,
                            state: "INVALID"
                        },
                            chrome.runtime.sendMessage(e,
                                function(e) {
                                    console.log("收到：" + JSON.stringify(e))
                                }))
                    } (),
                    function() {
                        var n = function() {
                            var n = $(".slide-show-left h2.file-name em.global-icon-16-dir").length > 0 ? "DIR": "FILE",
                                i = $(".slide-show-other-infos .share-file-info span").text(),
                                c = $(".slide-show-left .file-name").attr("title"),
                                a = 0,
                                u = $(".share-person-data .share-person-data-top > a:nth-child(1)"),
                                s = u.attr("href");
                            if (s!=null){
                                s=s.match(/uk=(\d+)&?/);
                                if (s!=null)
                                s=s[1];
                            }
                            var l = u.html();
                            if ("FILE" === n) {
                                a = $(".slide-show-right .button-box > a:nth-child(2)").attr("title");
                                if (a!=null){
                                    if (a.match(/下载\((.+?)\)/).length==2) {
                                        a=a.match(/下载\((.+?)\)/)[1];
                                    }
                                } else{
                                    a=0;
                                }

                            }
                            var f = {};
                            f.mode = n,
                                f.share_time = i,
                                f.name = c,
                                f.size = a,
                                f.user_id = s,
                                f.user_name = l,
                                e = {
                                    cmd: "SEND_RESOURCE",
                                    uuid: t,
                                    resource: f
                                },
                                chrome.runtime.sendMessage(e,
                                    function(e) {
                                        console.log("收到：" + JSON.stringify(e))
                                    });
                            var h = r.parse(location.hash);
                            if (void 0 !== h["list/path"]) {
                                var g = o(h["list/path"]),
                                    A = g.name;
                                if (void 0 !== g.ext && null !== g.ext && (A += g.ext), A === c) {
                                    var m = Array();
                                    if ($(".share-list > div:nth-child(2) > div:nth-child(3) > div > div > dd").each(function() {
                                        var e = $(this).find(".file-name .text a").attr("title"),
                                            t = $(this).find(".JS-fileicon.dir-small").length > 0 ? "DIR": "FILE",
                                            n = $(this).find(".file-size").text();
                                        "-" === n && (n = 0),
                                            m.push({
                                                name: e,
                                                mode: t,
                                                size: n
                                            })
                                    }), m.length > 0) {
                                        var d = JSON.stringify(m);
                                        e = {
                                            cmd: "SEND_RESOURCE_SUBNODES",
                                            uuid: t,
                                            subnodes: d
                                        },
                                            chrome.runtime.sendMessage(e,
                                                function(e) {
                                                    console.log("收到：" + JSON.stringify(e))
                                                })
                                    }
                                }
                            }
                        };
                        window.onhashchange = function() {
                            setTimeout(function() {
                                    n()
                                },
                                3e3)
                        },
                            setTimeout(function() {
                                    n()
                                },
                                3e3)
                    }  ()
            })
        },
        function(e, t, n) {
            "use strict";
            function r(e) {
                switch (e.arrayFormat) {
                    case "index":
                        return function(t, n, r) {
                            return null === n ? [i(t, e), "[", r, "]"].join("") : [i(t, e), "[", i(r, e), "]=", i(n, e)].join("")
                        };
                    case "bracket":
                        return function(t, n) {
                            return null === n ? i(t, e) : [i(t, e), "[]=", i(n, e)].join("")
                        };
                    default:
                        return function(t, n) {
                            return null === n ? i(t, e) : [i(t, e), "=", i(n, e)].join("")
                        }
                }
            }
            function o(e) {
                var t;
                switch (e.arrayFormat) {
                    case "index":
                        return function(e, n, r) {
                            if (t = /\[(\d*)\]$/.exec(e), e = e.replace(/\[\d*\]$/, ""), !t) return void(r[e] = n);
                            void 0 === r[e] && (r[e] = {}),
                                r[e][t[1]] = n
                        };
                    case "bracket":
                        return function(e, n, r) {
                            return t = /(\[\])$/.exec(e),
                                e = e.replace(/\[\]$/, ""),
                                t ? void 0 === r[e] ? void(r[e] = [n]) : void(r[e] = [].concat(r[e], n)) : void(r[e] = n)
                        };
                    default:
                        return function(e, t, n) {
                            if (void 0 === n[e]) return void(n[e] = t);
                            n[e] = [].concat(n[e], t)
                        }
                }
            }
            function i(e, t) {
                return t.encode ? t.strict ? a(e) : encodeURIComponent(e) : e
            }
            function c(e) {
                return Array.isArray(e) ? e.sort() : "object" == typeof e ? c(Object.keys(e)).sort(function(e, t) {
                    return Number(e) - Number(t)
                }).map(function(t) {
                    return e[t]
                }) : e
            }
            var a = n(4),
                u = n(5),
                s = n(6);
            t.extract = function(e) {
                var t = e.indexOf("?");
                return - 1 === t ? "": e.slice(t + 1)
            },
                t.parse = function(e, t) {
                    t = u({
                            arrayFormat: "none"
                        },
                        t);
                    var n = o(t),
                        r = Object.create(null);
                    return "string" != typeof e ? r: (e = e.trim().replace(/^[?#&]/, "")) ? (e.split("&").forEach(function(e) {
                        var t = e.replace(/\+/g, " ").split("="),
                            o = t.shift(),
                            i = t.length > 0 ? t.join("=") : void 0;
                        i = void 0 === i ? null: s(i),
                            n(s(o), i, r)
                    }), Object.keys(r).sort().reduce(function(e, t) {
                            var n = r[t];
                            return Boolean(n) && "object" == typeof n && !Array.isArray(n) ? e[t] = c(n) : e[t] = n,
                                e
                        },
                        Object.create(null))) : r
                },
                t.stringify = function(e, t) {
                    t = u({
                            encode: !0,
                            strict: !0,
                            arrayFormat: "none"
                        },
                        t);
                    var n = r(t);
                    return e ? Object.keys(e).sort().map(function(r) {
                        var o = e[r];
                        if (void 0 === o) return "";
                        if (null === o) return i(r, t);
                        if (Array.isArray(o)) {
                            var c = [];
                            return o.slice().forEach(function(e) {
                                void 0 !== e && c.push(n(r, e, c.length))
                            }),
                                c.join("&")
                        }
                        return i(r, t) + "=" + i(o, t)
                    }).filter(function(e) {
                        return e.length > 0
                    }).join("&") : ""
                }
        },
        function(e, t, n) {
            "use strict";
            e.exports = function(e) {
                return encodeURIComponent(e).replace(/[!'()*]/g,
                    function(e) {
                        return "%" + e.charCodeAt(0).toString(16).toUpperCase()
                    })
            }
        },
        function(e, t, n) {
            "use strict";
            function r(e) {
                if (null === e || void 0 === e) throw new TypeError("Object.assign cannot be called with null or undefined");
                return Object(e)
            }
/*
object-assign
(c) Sindre Sorhus
@license MIT
*/
            var o = Object.getOwnPropertySymbols,
                i = Object.prototype.hasOwnProperty,
                c = Object.prototype.propertyIsEnumerable;
            e.exports = function() {
                try {
                    if (!Object.assign) return ! 1;
                    var e = new String("abc");
                    if (e[5] = "de", "5" === Object.getOwnPropertyNames(e)[0]) return ! 1;
                    for (var t = {},
                             n = 0; n < 10; n++) t["_" + String.fromCharCode(n)] = n;
                    if ("0123456789" !== Object.getOwnPropertyNames(t).map(function(e) {
                        return t[e]
                    }).join("")) return ! 1;
                    var r = {};
                    return "abcdefghijklmnopqrst".split("").forEach(function(e) {
                        r[e] = e
                    }),
                    "abcdefghijklmnopqrst" === Object.keys(Object.assign({},
                        r)).join("")
                } catch(e) {
                    return ! 1
                }
            } () ? Object.assign: function(e, t) {
                for (var n, a, u = r(e), s = 1; s < arguments.length; s++) {
                    n = Object(arguments[s]);
                    for (var l in n) i.call(n, l) && (u[l] = n[l]);
                    if (o) {
                        a = o(n);
                        for (var f = 0; f < a.length; f++) c.call(n, a[f]) && (u[a[f]] = n[a[f]])
                    }
                }
                return u
            }
        },
        function(e, t, n) {
            "use strict";
            function r(e, t) {
                try {
                    return decodeURIComponent(e.join(""))
                } catch(e) {}
                if (1 === e.length) return e;
                t = t || 1;
                var n = e.slice(0, t),
                    o = e.slice(t);
                return Array.prototype.concat.call([], r(n), r(o))
            }
            function o(e) {
                try {
                    return decodeURIComponent(e)
                } catch(o) {
                    for (var t = e.match(c), n = 1; n < t.length; n++) e = r(t, n).join(""),
                        t = e.match(c);
                    return e
                }
            }
            function i(e) {
                for (var t = {
                        "%FE%FF": "��",
                        "%FF%FE": "��"
                    },
                         n = a.exec(e); n;) {
                    try {
                        t[n[0]] = decodeURIComponent(n[0])
                    } catch(e) {
                        var r = o(n[0]);
                        r !== n[0] && (t[n[0]] = r)
                    }
                    n = a.exec(e)
                }
                t["%C2"] = "�";
                for (var i = Object.keys(t), c = 0; c < i.length; c++) {
                    var u = i[c];
                    e = e.replace(new RegExp(u, "g"), t[u])
                }
                return e
            }
            var c = new RegExp("%[a-f0-9]{2}", "gi"),
                a = new RegExp("(%[a-f0-9]{2})+", "gi");
            e.exports = function(e) {
                if ("string" != typeof e) throw new TypeError("Expected `encodedURI` to be of type `string`, got `" + typeof e + "`");
                try {
                    return e = e.replace(/\+/g, " "),
                        decodeURIComponent(e)
                } catch(t) {
                    return i(e)
                }
            }
        },
        function(e, t, n) {
            "use strict"; (function(t) {
                function n(e) {
                    var t = i.exec(e),
                        n = (t[1] || "") + (t[2] || ""),
                        r = t[3] || "",
                        o = c.exec(r);
                    return [n, o[1], o[2], o[3]]
                }
                function r(e) {
                    return u.exec(e).slice(1)
                }
                var o = "win32" === t.platform,
                    i = /^([a-zA-Z]:|[\\\/]{2}[^\\\/]+[\\\/]+[^\\\/]+)?([\\\/])?([\s\S]*?)$/,
                    c = /^([\s\S]*?)((?:\.{1,2}|[^\\\/]+?|)(\.[^.\/\\]*|))(?:[\\\/]*)$/,
                    a = {};
                a.parse = function(e) {
                    if ("string" != typeof e) throw new TypeError("Parameter 'pathString' must be a string, not " + typeof e);
                    var t = n(e);
                    if (!t || 4 !== t.length) throw new TypeError("Invalid path '" + e + "'");
                    return {
                        root: t[0],
                        dir: t[0] + t[1].slice(0, -1),
                        base: t[2],
                        ext: t[3],
                        name: t[2].slice(0, t[2].length - t[3].length)
                    }
                };
                var u = /^(\/?|)([\s\S]*?)((?:\.{1,2}|[^\/]+?|)(\.[^.\/]*|))(?:[\/]*)$/,
                    s = {};
                s.parse = function(e) {
                    if ("string" != typeof e) throw new TypeError("Parameter 'pathString' must be a string, not " + typeof e);
                    var t = r(e);
                    if (!t || 4 !== t.length) throw new TypeError("Invalid path '" + e + "'");
                    return t[1] = t[1] || "",
                        t[2] = t[2] || "",
                        t[3] = t[3] || "",
                        {
                            root: t[0],
                            dir: t[0] + t[1].slice(0, -1),
                            base: t[2],
                            ext: t[3],
                            name: t[2].slice(0, t[2].length - t[3].length)
                        }
                },
                    e.exports = o ? a.parse: s.parse,
                    e.exports.posix = s.parse,
                    e.exports.win32 = a.parse
            }).call(t, n(8))
        },
        function(e, t) {
            function n() {
                throw new Error("setTimeout has not been defined")
            }
            function r() {
                throw new Error("clearTimeout has not been defined")
            }
            function o(e) {
                if (l === setTimeout) return setTimeout(e, 0);
                if ((l === n || !l) && setTimeout) return l = setTimeout,
                    setTimeout(e, 0);
                try {
                    return l(e, 0)
                } catch(t) {
                    try {
                        return l.call(null, e, 0)
                    } catch(t) {
                        return l.call(this, e, 0)
                    }
                }
            }
            function i(e) {
                if (f === clearTimeout) return clearTimeout(e);
                if ((f === r || !f) && clearTimeout) return f = clearTimeout,
                    clearTimeout(e);
                try {
                    return f(e)
                } catch(t) {
                    try {
                        return f.call(null, e)
                    } catch(t) {
                        return f.call(this, e)
                    }
                }
            }
            function c() {
                m && g && (m = !1, g.length ? A = g.concat(A) : d = -1, A.length && a())
            }
            function a() {
                if (!m) {
                    var e = o(c);
                    m = !0;
                    for (var t = A.length; t;) {
                        for (g = A, A = []; ++d < t;) g && g[d].run();
                        d = -1,
                            t = A.length
                    }
                    g = null,
                        m = !1,
                        i(e)
                }
            }
            function u(e, t) {
                this.fun = e,
                    this.array = t
            }
            function s() {}
            var l, f, h = e.exports = {}; !
                function() {
                    try {
                        l = "function" == typeof setTimeout ? setTimeout: n
                    } catch(e) {
                        l = n
                    }
                    try {
                        f = "function" == typeof clearTimeout ? clearTimeout: r
                    } catch(e) {
                        f = r
                    }
                } ();
            var g, A = [],
                m = !1,
                d = -1;
            h.nextTick = function(e) {
                var t = new Array(arguments.length - 1);
                if (arguments.length > 1) for (var n = 1; n < arguments.length; n++) t[n - 1] = arguments[n];
                A.push(new u(e, t)),
                1 !== A.length || m || o(a)
            },
                u.prototype.run = function() {
                    this.fun.apply(null, this.array)
                },
                h.title = "browser",
                h.browser = !0,
                h.env = {},
                h.argv = [],
                h.version = "",
                h.versions = {},
                h.on = s,
                h.addListener = s,
                h.once = s,
                h.off = s,
                h.removeListener = s,
                h.removeAllListeners = s,
                h.emit = s,
                h.prependListener = s,
                h.prependOnceListener = s,
                h.listeners = function(e) {
                    return []
                },
                h.binding = function(e) {
                    throw new Error("process.binding is not supported")
                },
                h.cwd = function() {
                    return "/"
                },
                h.chdir = function(e) {
                    throw new Error("process.chdir is not supported")
                },
                h.umask = function() {
                    return 0
                }
        }]);