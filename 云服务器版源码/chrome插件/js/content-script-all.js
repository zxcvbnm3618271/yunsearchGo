!
    function(e) {
        function t(r) {
            if (n[r]) return n[r].exports;
            var i = n[r] = {
                i: r,
                l: !1,
                exports: {}
            };
            return e[r].call(i.exports, i, i.exports, t),
                i.l = !0,
                i.exports
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
            t(t.s = 0)
    } ([function(e, t, n) {
        var r = n(1);
        $(document).ready(function() {
            var e = {},
                t = function(e) {
                    switch (e) {
                        case "14px":
                        case "16px":
                            return 14;
                        case "18px":
                            return 16;
                        case "20px":
                            return 18;
                        case "22px":
                            return 20;
                        case "24px":
                            return 22
                    }
                    return 14
                },
                n = function(e) {
                    switch (e) {
                        case "14px":
                        case "16px":
                            return 2;
                        case "18px":
                        case "20px":
                        case "22px":
                        case "24px":
                            return 4
                    }
                    return 2
                },
                i = function(e) {
                    var r = t($(e).css("font-size")),
                        i = n($(e).css("font-size"));
                    $('<img src="data:image/png;base64,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" style="display:inline-block;width:' + r + "px;margin:0 5px " + i + 'px 0;vertical-align:middle;" title="YPSuperKey Unlocked">').prependTo($(e))
                },
                a = function(e) {
                    var r = t($(e).css("font-size")),
                        i = n($(e).css("font-size"));
                    $('<img src="data:image/png;base64,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" style="display:inline-block;width:' + r + "px;margin:0 5px " + i + 'px 0;vertical-align:middle;" title="YPSuperKey Broken">').prependTo($(e)),
                        $(e).css("text-decoration", "line-through").css("color", "#ccc"),
                        $(e).attr("title", "该资源已失效")
                }; !
                function() {
                    $("body > div:contains('pan.baidu.com')").each(function() {
                        $(this).find("a").each(function() {
                            var e = $(this).html(); - 1 === e.indexOf("<script") && $(this).html(e.replace(/pan\.baidu\.com/gi, "####BDY####"))
                        }),
                            $(this).find("textarea").each(function() {
                                var e = $(this).html();
                                $(this).html(e.replace(/pan\.baidu\.com/gi, "####BDY####"))
                            }),
                            r($(this)[0], {
                                find: /https?:\/\/pan\.baidu\.com\/s\/1[a-zA-Z0-9_\-]{5,22}/gi,
                                replace: function(e, t) {
                                    var n = document.createElement("a"),
                                        r = document.createTextNode(t.toString().replace(/pan\.baidu\.com/gi, "####BDY####"));
                                    return n.href = t,
                                        n.target = "_blank",
                                        n.appendChild(r),
                                        n
                                }
                            }),
                            r($(this)[0], {
                                find: /pan\.baidu\.com\/s\/1[a-zA-Z0-9_\-]{5,22}/gi,
                                replace: function(e, t) {
                                    var n = document.createElement("a"),
                                        r = document.createTextNode("https://" + t);
                                    return n.href = "https://" + t,
                                        n.target = "_blank",
                                        n.appendChild(r),
                                        n
                                }
                            }),
                            $(this).find("textarea").each(function() {
                                var e = $(this).html();
                                $(this).html(e.replace(/####BDY####/g, "pan.baidu.com"))
                            }),
                            $(this).find("a").each(function() {
                                var e = $(this).html(); - 1 === e.indexOf("<script") && $(this).html(e.replace(/####BDY####/g, "pan.baidu.com"))
                            })
                    })
                } (),
                function() {
                    if ($("a").each(function() {
                        var t = $(this).attr("href"),
                            n = $(this).text(),
                            r = BaseLib.fetchUuid(t);
                        null !== r ? e[r] = null: null !== (r = BaseLib.fetchUuid(n)) && (e[r] = null)
                    }), Object.keys(e).length > 0) {
                        var t = {
                            cmd: "CHECK_DATA",
                            uncheckedUuids: e
                        };
                        chrome.runtime.sendMessage(t,
                            function(e) {
                                console.log("收到：" + JSON.stringify(e)),
                                    $("a").each(function() {
                                        var t = $(this).attr("href"),
                                            n = $(this).text(),
                                            r = BaseLib.fetchUuid(t);
                                        null === r && (r = BaseLib.fetchUuid(n)),
                                        null !== r && void 0 !== e[r] && null !== e[r] && (void 0 === e[r].state || "VALID" === e[r].state ? void 0 !== e[r].access_code && i(this) : "INVALID" === e[r].state && a(this))
                                    })
                            })
                    }
                } ()
        })
    },
        function(e, t, n) {
            var r, i;
/**
 * findAndReplaceDOMText v 0.4.6
 * @author James Padolsey http://james.padolsey.com
 * @license http://unlicense.org/UNLICENSE
 *
 * Matches the text of a DOM node against a regular expression
 * and replaces each match (or node-separated portions of the match)
 * in the specified element.
 */
!
    function(a, o) {
        "object" == typeof e && e.exports ? e.exports = o() : (r = o, void 0 !== (i = "function" == typeof r ? r.call(t, n, t, e) : r) && (e.exports = i))
    } (0,
        function() {
            function e(e) {
                return String(e).replace(/([.*+?^=!:${}()|[\]\/\\])/g, "\\$1")
            }
            function t() {
                return n.apply(null, arguments) || r.apply(null, arguments)
            }
            function n(e, n, i, a, o) {
                if (n && !n.nodeType && arguments.length <= 2) return ! 1;
                var d = "function" == typeof i;
                d && (i = function(e) {
                    return function(t, n) {
                        return e(t.text, n.startIndex)
                    }
                } (i));
                var s = r(n, {
                    find: e,
                    wrap: d ? null: i,
                    replace: d ? i: "$" + (a || "&"),
                    prepMatch: function(e, t) {
                        if (!e[0]) throw "findAndReplaceDOMText cannot handle zero-length matches";
                        if (a > 0) {
                            var n = e[a];
                            e.index += e[0].indexOf(n),
                                e[0] = n
                        }
                        return e.endIndex = e.index + e[0].length,
                            e.startIndex = e.index,
                            e.index = t,
                            e
                    },
                    filterElements: o
                });
                return t.revert = function() {
                    return s.revert()
                },
                    !0
            }
            function r(e, t) {
                return new i(e, t)
            }
            function i(e, n) {
                var r = n.preset && t.PRESETS[n.preset];
                if (n.portionMode = n.portionMode || a, r) for (var i in r) d.call(r, i) && !d.call(n, i) && (n[i] = r[i]);
                this.node = e,
                    this.options = n,
                    this.prepMatch = n.prepMatch || this.prepMatch,
                    this.reverts = [],
                    this.matches = this.search(),
                this.matches.length && this.processMatches()
            }
            var a = "retain",
                o = document,
                d = {}.hasOwnProperty;
            return t.NON_PROSE_ELEMENTS = {
                br: 1,
                hr: 1,
                script: 1,
                style: 1,
                img: 1,
                video: 1,
                audio: 1,
                canvas: 1,
                svg: 1,
                map: 1,
                object: 1,
                input: 1,
                textarea: 1,
                select: 1,
                option: 1,
                optgroup: 1,
                button: 1
            },
                t.NON_CONTIGUOUS_PROSE_ELEMENTS = {
                    address: 1,
                    article: 1,
                    aside: 1,
                    blockquote: 1,
                    dd: 1,
                    div: 1,
                    dl: 1,
                    fieldset: 1,
                    figcaption: 1,
                    figure: 1,
                    footer: 1,
                    form: 1,
                    h1: 1,
                    h2: 1,
                    h3: 1,
                    h4: 1,
                    h5: 1,
                    h6: 1,
                    header: 1,
                    hgroup: 1,
                    hr: 1,
                    main: 1,
                    nav: 1,
                    noscript: 1,
                    ol: 1,
                    output: 1,
                    p: 1,
                    pre: 1,
                    section: 1,
                    ul: 1,
                    br: 1,
                    li: 1,
                    summary: 1,
                    dt: 1,
                    details: 1,
                    rp: 1,
                    rt: 1,
                    rtc: 1,
                    script: 1,
                    style: 1,
                    img: 1,
                    video: 1,
                    audio: 1,
                    canvas: 1,
                    svg: 1,
                    map: 1,
                    object: 1,
                    input: 1,
                    textarea: 1,
                    select: 1,
                    option: 1,
                    optgroup: 1,
                    button: 1,
                    table: 1,
                    tbody: 1,
                    thead: 1,
                    th: 1,
                    tr: 1,
                    td: 1,
                    caption: 1,
                    col: 1,
                    tfoot: 1,
                    colgroup: 1
                },
                t.NON_INLINE_PROSE = function(e) {
                    return d.call(t.NON_CONTIGUOUS_PROSE_ELEMENTS, e.nodeName.toLowerCase())
                },
                t.PRESETS = {
                    prose: {
                        forceContext: t.NON_INLINE_PROSE,
                        filterElements: function(e) {
                            return ! d.call(t.NON_PROSE_ELEMENTS, e.nodeName.toLowerCase())
                        }
                    }
                },
                t.Finder = i,
                i.prototype = {
                    search: function() {
                        function t(e) {
                            for (var o = 0,
                                     c = e.length; o < c; ++o) {
                                var p = e[o];
                                if ("string" == typeof p) {
                                    if (a.global) for (; n = a.exec(p);) d.push(s.prepMatch(n, r++, i));
                                    else(n = p.match(a)) && d.push(s.prepMatch(n, 0, i));
                                    i += p.length
                                } else t(p)
                            }
                        }
                        var n, r = 0,
                            i = 0,
                            a = this.options.find,
                            o = this.getAggregateText(),
                            d = [],
                            s = this;
                        return a = "string" == typeof a ? RegExp(e(a), "g") : a,
                            t(o),
                            d
                    },
                    prepMatch: function(e, t, n) {
                        if (!e[0]) throw new Error("findAndReplaceDOMText cannot handle zero-length matches");
                        return e.endIndex = n + e.index + e[0].length,
                            e.startIndex = n + e.index,
                            e.index = t,
                            e
                    },
                    getAggregateText: function() {
                        function e(r) {
                            if (r.nodeType === Node.TEXT_NODE) return [r.data];
                            if (t && !t(r)) return [];
                            var i = [""],
                                a = 0;
                            if (r = r.firstChild) do {
                                if (r.nodeType !== Node.TEXT_NODE) {
                                    var o = e(r);
                                    n && r.nodeType === Node.ELEMENT_NODE && (!0 === n || n(r)) ? (i[++a] = o, i[++a] = "") : ("string" == typeof o[0] && (i[a] += o.shift()), o.length && (i[++a] = o, i[++a] = ""))
                                } else i[a] += r.data
                            } while ( r = r . nextSibling );
                            return i
                        }
                        var t = this.options.filterElements,
                            n = this.options.forceContext;
                        return e(this.node)
                    },
                    processMatches: function() {
                        var e, t, n, r = this.matches,
                            i = this.node,
                            a = this.options.filterElements,
                            o = [],
                            d = i,
                            s = r.shift(),
                            c = 0,
                            p = 0,
                            l = 0,
                            u = [i];
                        e: for (;;) {
                            if (d.nodeType === Node.TEXT_NODE && (!t && d.length + c >= s.endIndex ? t = {
                                node: d,
                                index: l++,
                                text: d.data.substring(s.startIndex - c, s.endIndex - c),
                                indexInMatch: 0 === c ? 0 : c - s.startIndex,
                                indexInNode: s.startIndex - c,
                                endIndexInNode: s.endIndex - c,
                                isEnd: !0
                            }: e && o.push({
                                node: d,
                                index: l++,
                                text: d.data,
                                indexInMatch: c - s.startIndex,
                                indexInNode: 0
                            }), !e && d.length + c > s.startIndex && (e = {
                                node: d,
                                index: l++,
                                indexInMatch: 0,
                                indexInNode: s.startIndex - c,
                                endIndexInNode: s.endIndex - c,
                                text: d.data.substring(s.startIndex - c, s.endIndex - c)
                            }), c += d.data.length), n = d.nodeType === Node.ELEMENT_NODE && a && !a(d), e && t) {
                                if (d = this.replaceMatch(s, e, o, t), c -= t.node.data.length - t.endIndexInNode, e = null, t = null, o = [], s = r.shift(), l = 0, p++, !s) break
                            } else if (!n && (d.firstChild || d.nextSibling)) {
                                d.firstChild ? (u.push(d), d = d.firstChild) : d = d.nextSibling;
                                continue
                            }
                            for (;;) {
                                if (d.nextSibling) {
                                    d = d.nextSibling;
                                    break
                                }
                                if ((d = u.pop()) === i) break e
                            }
                        }
                    },
                    revert: function() {
                        for (var e = this.reverts.length; e--;) this.reverts[e]();
                        this.reverts = []
                    },
                    prepareReplacementString: function(e, t, n) {
                        var r = this.options.portionMode;
                        return "first" === r && t.indexInMatch > 0 ? "": (e = e.replace(/\$(\d+|&|`|')/g,
                            function(e, t) {
                                var r;
                                switch (t) {
                                    case "&":
                                        r = n[0];
                                        break;
                                    case "`":
                                        r = n.input.substring(0, n.startIndex);
                                        break;
                                    case "'":
                                        r = n.input.substring(n.endIndex);
                                        break;
                                    default:
                                        r = n[ + t] || ""
                                }
                                return r
                            }), "first" === r ? e: t.isEnd ? e.substring(t.indexInMatch) : e.substring(t.indexInMatch, t.indexInMatch + t.text.length))
                    },
                    getPortionReplacementNode: function(e, t) {
                        var n = this.options.replace || "$&",
                            r = this.options.wrap,
                            i = this.options.wrapClass;
                        if (r && r.nodeType) {
                            var a = o.createElement("div");
                            a.innerHTML = r.outerHTML || (new XMLSerializer).serializeToString(r),
                                r = a.firstChild
                        }
                        if ("function" == typeof n) return n = n(e, t),
                            n && n.nodeType ? n: o.createTextNode(String(n));
                        var d = "string" == typeof r ? o.createElement(r) : r;
                        return d && i && (d.className = i),
                            n = o.createTextNode(this.prepareReplacementString(n, e, t)),
                            n.data && d ? (d.appendChild(n), d) : n
                    },
                    replaceMatch: function(e, t, n, r) {
                        var i, a, d = t.node,
                            s = r.node;
                        if (d === s) {
                            var c = d;
                            t.indexInNode > 0 && (i = o.createTextNode(c.data.substring(0, t.indexInNode)), c.parentNode.insertBefore(i, c));
                            var p = this.getPortionReplacementNode(r, e);
                            return c.parentNode.insertBefore(p, c),
                            r.endIndexInNode < c.length && (a = o.createTextNode(c.data.substring(r.endIndexInNode)), c.parentNode.insertBefore(a, c)),
                                c.parentNode.removeChild(c),
                                this.reverts.push(function() {
                                    i === p.previousSibling && i.parentNode.removeChild(i),
                                    a === p.nextSibling && a.parentNode.removeChild(a),
                                        p.parentNode.replaceChild(c, p)
                                }),
                                p
                        }
                        i = o.createTextNode(d.data.substring(0, t.indexInNode)),
                            a = o.createTextNode(s.data.substring(r.endIndexInNode));
                        for (var l = this.getPortionReplacementNode(t, e), u = [], h = 0, g = n.length; h < g; ++h) {
                            var f = n[h],
                                A = this.getPortionReplacementNode(f, e);
                            f.node.parentNode.replaceChild(A, f.node),
                                this.reverts.push(function(e, t) {
                                    return function() {
                                        t.parentNode.replaceChild(e.node, t)
                                    }
                                } (f, A)),
                                u.push(A)
                        }
                        var x = this.getPortionReplacementNode(r, e);
                        return d.parentNode.insertBefore(i, d),
                            d.parentNode.insertBefore(l, d),
                            d.parentNode.removeChild(d),
                            s.parentNode.insertBefore(x, s),
                            s.parentNode.insertBefore(a, s),
                            s.parentNode.removeChild(s),
                            this.reverts.push(function() {
                                i.parentNode.removeChild(i),
                                    l.parentNode.replaceChild(d, l),
                                    a.parentNode.removeChild(a),
                                    x.parentNode.replaceChild(s, x)
                            }),
                            x
                    }
                },
                t
        })
        }]);