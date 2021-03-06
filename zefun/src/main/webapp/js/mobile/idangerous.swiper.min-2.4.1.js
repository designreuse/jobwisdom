var Swiper = function(a, b) {
    function d(a, b) {
        return document.querySelectorAll ? (b || document).querySelectorAll(a) : jQuery(a, b)
    }
    function v(a) {
        "use strict";
        return "[object Array]" === Object.prototype.toString.apply(a) ? !0 : !1
    }
    function w() {
        var a = h - k;
        return b.freeMode && (a = h - k),
        b.slidesPerView > e.slides.length && (a = 0),
        0 > a && (a = 0),
        a
    }
    function y() {
        function g(a) {
            var c = new Image;
            c.onload = function() {
                e.imagesLoaded++,
                e.imagesLoaded == e.imagesToLoad.length && (e.reInit(), b.onImagesReady && e.fireCallback(b.onImagesReady, e))
            },
            c.src = a
        }
        var f, a = e.h.addEventListener;
        if (e.browser.ie10 ? (a(e.wrapper, e.touchEvents.touchStart, N), a(document, e.touchEvents.touchMove, Q), a(document, e.touchEvents.touchEnd, R)) : (e.support.touch && (a(e.wrapper, "touchstart", N), a(e.wrapper, "touchmove", Q), a(e.wrapper, "touchend", R)), b.simulateTouch && (a(e.wrapper, "mousedown", N), a(document, "mousemove", Q), a(document, "mouseup", R))), b.autoResize && a(window, "resize", e.resizeFix), z(), e._wheelEvent = !1, b.mousewheelControl) {
            void 0 !== document.onmousewheel && (e._wheelEvent = "mousewheel");
            try {
                WheelEvent("wheel"),
                e._wheelEvent = "wheel"
            } catch(c) {}
            e._wheelEvent || (e._wheelEvent = "DOMMouseScroll"),
            e._wheelEvent && a(e.container, e._wheelEvent, D)
        }
        if (b.keyboardControl && a(document, "keydown", B), b.updateOnImagesReady) for (e.imagesToLoad = d("img", e.container), f = 0; f < e.imagesToLoad.length; f++) g(e.imagesToLoad[f].getAttribute("src"))
    }
    function z() {
        var c, f, g, a = e.h.addEventListener;
        if (b.preventLinks) for (f = d("a", e.container), c = 0; c < f.length; c++) a(f[c], "click", I);
        if (b.releaseFormElements) for (g = d("input, textarea, select", e.container), c = 0; c < g.length; c++) a(g[c], e.touchEvents.touchStart, J, !0);
        if (b.onSlideClick) for (c = 0; c < e.slides.length; c++) a(e.slides[c], "click", F);
        if (b.onSlideTouch) for (c = 0; c < e.slides.length; c++) a(e.slides[c], e.touchEvents.touchStart, G)
    }
    function A() {
        var c, f, g, a = e.h.removeEventListener;
        if (b.onSlideClick) for (c = 0; c < e.slides.length; c++) a(e.slides[c], "click", F);
        if (b.onSlideTouch) for (c = 0; c < e.slides.length; c++) a(e.slides[c], e.touchEvents.touchStart, G);
        if (b.releaseFormElements) for (f = d("input, textarea, select", e.container), c = 0; c < f.length; c++) a(f[c], e.touchEvents.touchStart, J, !0);
        if (b.preventLinks) for (g = d("a", e.container), c = 0; c < g.length; c++) a(g[c], "click", I)
    }
    function B(a) {
        var c, d, f, g, h, i, j, k, l, b = a.keyCode || a.charCode;
        if (37 == b || 39 == b || 38 == b || 40 == b) {
            for (c = !1, d = e.h.getOffset(e.container), f = e.h.windowScroll().left, g = e.h.windowScroll().top, h = e.h.windowWidth(), i = e.h.windowHeight(), j = [[d.left, d.top], [d.left + e.width, d.top], [d.left, d.top + e.height], [d.left + e.width, d.top + e.height]], k = 0; k < j.length; k++) l = j[k],
            l[0] >= f && l[0] <= f + h && l[1] >= g && l[1] <= g + i && (c = !0);
            if (!c) return
        }
        o ? ((37 == b || 39 == b) && (a.preventDefault ? a.preventDefault() : a.returnValue = !1), 39 == b && e.swipeNext(), 37 == b && e.swipePrev()) : ((38 == b || 40 == b) && (a.preventDefault ? a.preventDefault() : a.returnValue = !1), 40 == b && e.swipeNext(), 38 == b && e.swipePrev())
    }
    function D(a) {
        var f, c = e._wheelEvent,
        d = 0;
        if (a.detail) d = -a.detail;
        else if ("mousewheel" == c) if (b.mousewheelControlForceToAxis) if (o) {
            if (! (Math.abs(a.wheelDeltaX) > Math.abs(a.wheelDeltaY))) return;
            d = a.wheelDeltaX
        } else {
            if (! (Math.abs(a.wheelDeltaY) > Math.abs(a.wheelDeltaX))) return;
            d = a.wheelDeltaY
        } else d = a.wheelDelta;
        else if ("DOMMouseScroll" == c) d = -a.detail;
        else if ("wheel" == c) if (b.mousewheelControlForceToAxis) if (o) {
            if (! (Math.abs(a.deltaX) > Math.abs(a.deltaY))) return;
            d = -a.deltaX
        } else {
            if (! (Math.abs(a.deltaY) > Math.abs(a.deltaX))) return;
            d = -a.deltaY
        } else d = Math.abs(a.deltaX) > Math.abs(a.deltaY) ? -a.deltaX: -a.deltaY;
        if (b.freeMode) {
            if (f = e.getWrapperTranslate() + d, f > 0 && (f = 0), f < -w() && (f = -w()), e.setWrapperTransition(0), e.setWrapperTranslate(f), e.updateActiveSlide(f), 0 == f || f == -w()) return
        } else(new Date).getTime() - C > 60 && (0 > d ? e.swipeNext() : e.swipePrev()),
        C = (new Date).getTime();
        return b.autoplay && e.stopAutoplay(!0),
        a.preventDefault ? a.preventDefault() : a.returnValue = !1,
        !1
    }
    function F(a) {
        e.allowSlideClick && (H(a), e.fireCallback(b.onSlideClick, e, a))
    }
    function G(a) {
        H(a),
        e.fireCallback(b.onSlideTouch, e, a)
    }
    function H(a) {
        if (a.currentTarget) e.clickedSlide = a.currentTarget;
        else {
            var c = a.srcElement;
            do
            if (c.className.indexOf(b.slideClass) > -1) break;
            while (c = c.parentNode);
            e.clickedSlide = c
        }
        e.clickedSlideIndex = e.slides.indexOf(e.clickedSlide),
        e.clickedSlideLoopIndex = e.clickedSlideIndex - (e.loopedSlides || 0)
    }
    function I(a) {
        return e.allowLinks ? void 0 : (a.preventDefault ? a.preventDefault() : a.returnValue = !1, !1)
    }
    function J(a) {
        return a.stopPropagation ? a.stopPropagation() : a.returnValue = !1,
        !1
    }
    function N(a) {
        var c, d;
        return b.preventLinks && (e.allowLinks = !0),
        e.isTouched || b.onlyExternal ? !1 : b.noSwiping && (a.target || a.srcElement) && S(a.target || a.srcElement) ? !1 : (M = !1, e.isTouched = !0, K = "touchstart" == a.type, K && 1 != a.targetTouches.length || (e.callPlugins("onTouchStartBegin"), K || (a.preventDefault ? a.preventDefault() : a.returnValue = !1), c = K ? a.targetTouches[0].pageX: a.pageX || a.clientX, d = K ? a.targetTouches[0].pageY: a.pageY || a.clientY, e.touches.startX = e.touches.currentX = c, e.touches.startY = e.touches.currentY = d, e.touches.start = e.touches.current = o ? c: d, e.setWrapperTransition(0), e.positions.start = e.positions.current = e.getWrapperTranslate(), e.setWrapperTranslate(e.positions.start), e.times.start = (new Date).getTime(), j = void 0, b.moveStartThreshold > 0 && (L = !1), b.onTouchStart && e.fireCallback(b.onTouchStart, e), e.callPlugins("onTouchStartEnd")), void 0)
    }
    function Q(a) {
        var c, d, f, g, h, i;
        if (e.isTouched && !b.onlyExternal && (!K || "mousemove" != a.type)) {
            if (c = K ? a.targetTouches[0].pageX: a.pageX || a.clientX, d = K ? a.targetTouches[0].pageY: a.pageY || a.clientY, "undefined" == typeof j && o && (j = !!(j || Math.abs(d - e.touches.startY) > Math.abs(c - e.touches.startX))), "undefined" != typeof j || o || (j = !!(j || Math.abs(d - e.touches.startY) < Math.abs(c - e.touches.startX))), j) return e.isTouched = !1,
            void 0;
            if (a.assignedToSwiper) return e.isTouched = !1,
            void 0;
            if (a.assignedToSwiper = !0, b.preventLinks && (e.allowLinks = !1), b.onSlideClick && (e.allowSlideClick = !1), b.autoplay && e.stopAutoplay(!0), !K || 1 == a.touches.length) {
                if (e.isMoved || (e.callPlugins("onTouchMoveStart"), b.loop && (e.fixLoop(), e.positions.start = e.getWrapperTranslate()), b.onTouchMoveStart && e.fireCallback(b.onTouchMoveStart, e)), e.isMoved = !0, a.preventDefault ? a.preventDefault() : a.returnValue = !1, e.touches.current = o ? c: d, e.positions.current = (e.touches.current - e.touches.start) * b.touchRatio + e.positions.start, e.positions.current > 0 && b.onResistanceBefore && e.fireCallback(b.onResistanceBefore, e, e.positions.current), e.positions.current < -w() && b.onResistanceAfter && e.fireCallback(b.onResistanceAfter, e, Math.abs(e.positions.current + w())), b.resistance && "100%" != b.resistance && (e.positions.current > 0 && (f = 1 - e.positions.current / k / 2, e.positions.current = .5 > f ? k / 2 : e.positions.current * f), e.positions.current < -w() && (g = (e.touches.current - e.touches.start) * b.touchRatio + (w() + e.positions.start), f = (k + g) / k, h = e.positions.current - g * (1 - f) / 2, i = -w() - k / 2, e.positions.current = i > h || 0 >= f ? i: h)), b.resistance && "100%" == b.resistance && (e.positions.current > 0 && (!b.freeMode || b.freeModeFluid) && (e.positions.current = 0), e.positions.current < -w() && (!b.freeMode || b.freeModeFluid) && (e.positions.current = -w())), !b.followFinger) return;
                return b.moveStartThreshold ? Math.abs(e.touches.current - e.touches.start) > b.moveStartThreshold || L ? (L = !0, e.setWrapperTranslate(e.positions.current)) : e.positions.current = e.positions.start: e.setWrapperTranslate(e.positions.current),
                (b.freeMode || b.watchActiveIndex) && e.updateActiveSlide(e.positions.current),
                b.grabCursor && (e.container.style.cursor = "move", e.container.style.cursor = "grabbing", e.container.style.cursor = "-moz-grabbin", e.container.style.cursor = "-webkit-grabbing"),
                O || (O = e.touches.current),
                P || (P = (new Date).getTime()),
                e.velocity = (e.touches.current - O) / ((new Date).getTime() - P) / 2,
                Math.abs(e.touches.current - O) < 2 && (e.velocity = 0),
                O = e.touches.current,
                P = (new Date).getTime(),
                e.callPlugins("onTouchMoveEnd"),
                b.onTouchMove && e.fireCallback(b.onTouchMove, e),
                !1
            }
        }
    }
    function R() {
        var c, d, f, h, l, m, n, p, q, r, s, t, u, v, x;
        if (j && e.swipeReset(), !b.onlyExternal && e.isTouched) {
            if (e.isTouched = !1, b.grabCursor && (e.container.style.cursor = "move", e.container.style.cursor = "grab", e.container.style.cursor = "-moz-grab", e.container.style.cursor = "-webkit-grab"), e.positions.current || 0 === e.positions.current || (e.positions.current = e.positions.start), b.followFinger && e.setWrapperTranslate(e.positions.current), e.times.end = (new Date).getTime(), e.touches.diff = e.touches.current - e.touches.start, e.touches.abs = Math.abs(e.touches.diff), e.positions.diff = e.positions.current - e.positions.start, e.positions.abs = Math.abs(e.positions.diff), c = e.positions.diff, d = e.positions.abs, f = e.times.end - e.times.start, 5 > d && 300 > f && 0 == e.allowLinks && (b.freeMode || 0 == d || e.swipeReset(), b.preventLinks && (e.allowLinks = !0), b.onSlideClick && (e.allowSlideClick = !0)), setTimeout(function() {
                b.preventLinks && (e.allowLinks = !0),
                b.onSlideClick && (e.allowSlideClick = !0)
            },
            100), h = w(), !e.isMoved && b.freeMode) return e.isMoved = !1,
            b.onTouchEnd && e.fireCallback(b.onTouchEnd, e),
            e.callPlugins("onTouchEnd"),
            void 0;
            if (!e.isMoved || e.positions.current > 0 || e.positions.current < -h) return e.swipeReset(),
            b.onTouchEnd && e.fireCallback(b.onTouchEnd, e),
            e.callPlugins("onTouchEnd"),
            void 0;
            if (e.isMoved = !1, b.freeMode) return b.freeModeFluid && (l = 1e3 * b.momentumRatio, m = e.velocity * l, n = e.positions.current + m, p = !1, r = 20 * Math.abs(e.velocity) * b.momentumBounceRatio, -h > n && (b.momentumBounce && e.support.transitions ? ( - r > n + h && (n = -h - r), q = -h, p = !0, M = !0) : n = -h), n > 0 && (b.momentumBounce && e.support.transitions ? (n > r && (n = r), q = 0, p = !0, M = !0) : n = 0), 0 != e.velocity && (l = Math.abs((n - e.positions.current) / e.velocity)), e.setWrapperTranslate(n), e.setWrapperTransition(l), b.momentumBounce && p && e.wrapperTransitionEnd(function() {
                M && (b.onMomentumBounce && e.fireCallback(b.onMomentumBounce, e), e.callPlugins("onMomentumBounce"), e.setWrapperTranslate(q), e.setWrapperTransition(300))
            }), e.updateActiveSlide(n)),
            (!b.freeModeFluid || f >= 300) && e.updateActiveSlide(e.positions.current),
            b.onTouchEnd && e.fireCallback(b.onTouchEnd, e),
            e.callPlugins("onTouchEnd"),
            void 0;
            if (i = 0 > c ? "toNext": "toPrev", "toNext" == i && 300 >= f && (30 > d || !b.shortSwipes ? e.swipeReset() : e.swipeNext(!0)), "toPrev" == i && 300 >= f && (30 > d || !b.shortSwipes ? e.swipeReset() : e.swipePrev(!0)), s = 0, "auto" == b.slidesPerView) {
                for (t = Math.abs(e.getWrapperTranslate()), u = 0, x = 0; x < e.slides.length; x++) if (v = o ? e.slides[x].getWidth(!0) : e.slides[x].getHeight(!0), u += v, u > t) {
                    s = v;
                    break
                }
                s > k && (s = k)
            } else s = g * b.slidesPerView;
            "toNext" == i && f > 300 && (d >= .5 * s ? e.swipeNext(!0) : e.swipeReset()),
            "toPrev" == i && f > 300 && (d >= .5 * s ? e.swipePrev(!0) : e.swipeReset()),
            b.onTouchEnd && b.onTouchEnd(e),
            e.callPlugins("onTouchEnd")
        }
    }
    function S(a) {
        var c = !1;
        do a.className.indexOf(b.noSwipingClass) > -1 && (c = !0),
        a = a.parentElement;
        while (!c && a.parentElement && -1 == a.className.indexOf(b.wrapperClass));
        return ! c && a.className.indexOf(b.wrapperClass) > -1 && a.className.indexOf(b.noSwipingClass) > -1 && (c = !0),
        c
    }
    function T(a, c, d) {
        function l() {
            var c = +new Date,
            d = c - g;
            h += i * d / (1e3 / 60),
            k = "toNext" == j ? h > a: a > h,
            k ? (e.setWrapperTranslate(Math.round(h)), e._DOMAnimating = !0, window.setTimeout(function() {
                l()
            },
            1e3 / 60)) : (b.onSlideChangeEnd && e.fireCallback(b.onSlideChangeEnd, e), e.setWrapperTranslate(a), e._DOMAnimating = !1)
        }
        var h, i, j, k, f = "to" == c && d.speed >= 0 ? d.speed: b.speed,
        g = +new Date;
        if (e.support.transitions || !b.DOMAnimation) e.setWrapperTranslate(a),
        e.setWrapperTransition(f);
        else {
            if (h = e.getWrapperTranslate(), i = Math.ceil((a - h) / f * (1e3 / 60)), j = h > a ? "toNext": "toPrev", k = "toNext" == j ? h > a: a > h, e._DOMAnimating) return;
            l()
        }
        e.updateActiveSlide(a),
        b.onSlideNext && "next" == c && e.fireCallback(b.onSlideNext, e, a),
        b.onSlidePrev && "prev" == c && e.fireCallback(b.onSlidePrev, e, a),
        b.onSlideReset && "reset" == c && e.fireCallback(b.onSlideReset, e, a),
        ("next" == c || "prev" == c || "to" == c && 1 == d.runCallbacks) && U(c)
    }
    function U(a) {
        if (e.callPlugins("onSlideChangeStart"), b.onSlideChangeStart) if (b.queueStartCallbacks && e.support.transitions) {
            if (e._queueStartCallbacks) return;
            e._queueStartCallbacks = !0,
            e.fireCallback(b.onSlideChangeStart, e, a),
            e.wrapperTransitionEnd(function() {
                e._queueStartCallbacks = !1
            })
        } else e.fireCallback(b.onSlideChangeStart, e, a);
        if (b.onSlideChangeEnd) if (e.support.transitions) if (b.queueEndCallbacks) {
            if (e._queueEndCallbacks) return;
            e._queueEndCallbacks = !0,
            e.wrapperTransitionEnd(function(c) {
                e.fireCallback(b.onSlideChangeEnd, c, a)
            })
        } else e.wrapperTransitionEnd(function(c) {
            e.fireCallback(b.onSlideChangeEnd, c, a)
        });
        else b.DOMAnimation || setTimeout(function() {
            e.fireCallback(b.onSlideChangeStart, e, a)
        },
        10)
    }
    function V() {
        var b, a = e.paginationButtons;
        for (b = 0; b < a.length; b++) e.h.removeEventListener(a[b], "click", X)
    }
    function W() {
        var b, a = e.paginationButtons;
        for (b = 0; b < a.length; b++) e.h.addEventListener(a[b], "click", X)
    }
    function X(a) {
        var b, f, c = a.target || a.srcElement,
        d = e.paginationButtons;
        for (f = 0; f < d.length; f++) c === d[f] && (b = f);
        e.swipeTo(b)
    }
    function Z() {
        Y = setTimeout(function() {
            b.loop ? (e.fixLoop(), e.swipeNext(!0)) : e.swipeNext(!0) || e.swipeTo(0),
            e.wrapperTransitionEnd(function() {
                "undefined" != typeof Y && Z()
            })
        },
        b.autoplay)
    }
    function $() {
        e.calcSlides(),
        b.loader.slides.length > 0 && 0 == e.slides.length && e.loadSlides(),
        b.loop && e.createLoop(),
        e.init(),
        y(),
        b.pagination && e.createPagination(!0),
        b.loop || b.initialSlide > 0 ? e.swipeTo(b.initialSlide, 0, !1) : e.updateActiveSlide(0),
        b.autoplay && e.startAutoplay(),
        e.centerIndex = e.activeIndex,
        b.onSwiperCreated && e.fireCallback(b.onSwiperCreated, e),
        e.callPlugins("onSwiperCreated")
    }
    var c, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, C, E, K, L, M, O, P, Y;
    if (document.body.__defineGetter__ && HTMLElement && (c = HTMLElement.prototype, c.__defineGetter__ && c.__defineGetter__("outerHTML",
    function() {
        return (new XMLSerializer).serializeToString(this)
    })), window.getComputedStyle || (window.getComputedStyle = function(a) {
        return this.el = a,
        this.getPropertyValue = function(b) {
            var c = /(\-([a-z]){1})/g;
            return "float" === b && (b = "styleFloat"),
            c.test(b) && (b = b.replace(c,
            function() {
                return arguments[2].toUpperCase()
            })),
            a.currentStyle[b] ? a.currentStyle[b] : null
        },
        this
    }), Array.prototype.indexOf || (Array.prototype.indexOf = function(a, b) {
        for (var c = b || 0,
        d = this.length; d > c; c++) if (this[c] === a) return c;
        return - 1
    }), (document.querySelectorAll || window.jQuery) && "undefined" != typeof a && (a.nodeType || 0 !== d(a).length)) {
        e = this,
        e.touches = {
            start: 0,
            startX: 0,
            startY: 0,
            current: 0,
            currentX: 0,
            currentY: 0,
            diff: 0,
            abs: 0
        },
        e.positions = {
            start: 0,
            abs: 0,
            diff: 0,
            current: 0
        },
        e.times = {
            start: 0,
            end: 0
        },
        e.id = (new Date).getTime(),
        e.container = a.nodeType ? a: d(a)[0],
        e.isTouched = !1,
        e.isMoved = !1,
        e.activeIndex = 0,
        e.centerIndex = 0,
        e.activeLoaderIndex = 0,
        e.activeLoopIndex = 0,
        e.previousIndex = null,
        e.velocity = 0,
        e.snapGrid = [],
        e.slidesGrid = [],
        e.imagesToLoad = [],
        e.imagesLoaded = 0,
        e.wrapperLeft = 0,
        e.wrapperRight = 0,
        e.wrapperTop = 0,
        e.wrapperBottom = 0,
        l = {
            mode: "horizontal",
            touchRatio: 1,
            speed: 300,
            freeMode: !1,
            freeModeFluid: !1,
            momentumRatio: 1,
            momentumBounce: !0,
            momentumBounceRatio: 1,
            slidesPerView: 1,
            slidesPerGroup: 1,
            simulateTouch: !0,
            followFinger: !0,
            shortSwipes: !0,
            moveStartThreshold: !1,
            onlyExternal: !1,
            createPagination: !0,
            pagination: !1,
            paginationElement: "span",
            paginationClickable: !1,
            paginationAsRange: !0,
            resistance: !0,
            scrollContainer: !1,
            preventLinks: !0,
            noSwiping: !1,
            noSwipingClass: "swiper-no-swiping",
            initialSlide: 0,
            keyboardControl: !1,
            mousewheelControl: !1,
            mousewheelControlForceToAxis: !1,
            useCSS3Transforms: !0,
            autoplay: !1,
            autoplayDisableOnInteraction: !0,
            loop: !1,
            loopAdditionalSlides: 0,
            calculateHeight: !1,
            cssWidthAndHeight: !1,
            updateOnImagesReady: !0,
            releaseFormElements: !0,
            watchActiveIndex: !1,
            visibilityFullFit: !1,
            offsetPxBefore: 0,
            offsetPxAfter: 0,
            offsetSlidesBefore: 0,
            offsetSlidesAfter: 0,
            centeredSlides: !1,
            queueStartCallbacks: !1,
            queueEndCallbacks: !1,
            autoResize: !0,
            resizeReInit: !1,
            DOMAnimation: !0,
            loader: {
                slides: [],
                slidesHTMLType: "inner",
                surroundGroups: 1,
                logic: "reload",
                loadAllSlides: !1
            },
            slideElement: "div",
            slideClass: "swiper-slide",
            slideActiveClass: "swiper-slide-active",
            slideVisibleClass: "swiper-slide-visible",
            wrapperClass: "swiper-wrapper",
            paginationElementClass: "swiper-pagination-switch",
            paginationActiveClass: "swiper-active-switch",
            paginationVisibleClass: "swiper-visible-switch"
        },
        b = b || {};
        for (m in l) if (m in b && "object" == typeof b[m]) for (n in l[m]) n in b[m] || (b[m][n] = l[m][n]);
        else m in b || (b[m] = l[m]);
        for (e.params = b, b.scrollContainer && (b.freeMode = !0, b.freeModeFluid = !0), b.loop && (b.resistance = "100%"), o = "horizontal" === b.mode, e.touchEvents = {
            touchStart: e.support.touch || !b.simulateTouch ? "touchstart": e.browser.ie10 ? "MSPointerDown": "mousedown",
            touchMove: e.support.touch || !b.simulateTouch ? "touchmove": e.browser.ie10 ? "MSPointerMove": "mousemove",
            touchEnd: e.support.touch || !b.simulateTouch ? "touchend": e.browser.ie10 ? "MSPointerUp": "mouseup"
        },
        p = e.container.childNodes.length - 1; p >= 0; p--) if (e.container.childNodes[p].className) for (q = e.container.childNodes[p].className.split(" "), r = 0; r < q.length; r++) q[r] === b.wrapperClass && (f = e.container.childNodes[p]);
        e.wrapper = f,
        e._extendSwiperSlide = function(a) {
            return a.append = function() {
                return b.loop ? (a.insertAfter(e.slides.length - e.loopedSlides), e.removeLoopedSlides(), e.calcSlides(), e.createLoop()) : e.wrapper.appendChild(a),
                e.reInit(),
                a
            },
            a.prepend = function() {
                return b.loop ? (e.wrapper.insertBefore(a, e.slides[e.loopedSlides]), e.removeLoopedSlides(), e.calcSlides(), e.createLoop()) : e.wrapper.insertBefore(a, e.wrapper.firstChild),
                e.reInit(),
                a
            },
            a.insertAfter = function(c) {
                if ("undefined" == typeof c) return ! 1;
                var d;
                return b.loop ? (d = e.slides[c + 1 + e.loopedSlides], e.wrapper.insertBefore(a, d), e.removeLoopedSlides(), e.calcSlides(), e.createLoop()) : (d = e.slides[c + 1], e.wrapper.insertBefore(a, d)),
                e.reInit(),
                a
            },
            a.clone = function() {
                return e._extendSwiperSlide(a.cloneNode(!0))
            },
            a.remove = function() {
                e.wrapper.removeChild(a),
                e.reInit()
            },
            a.html = function(b) {
                return "undefined" == typeof b ? a.innerHTML: (a.innerHTML = b, a)
            },
            a.index = function() {
                var b, c;
                for (c = e.slides.length - 1; c >= 0; c--) a === e.slides[c] && (b = c);
                return b
            },
            a.isActive = function() {
                return a.index() === e.activeIndex ? !0 : !1
            },
            a.swiperSlideDataStorage || (a.swiperSlideDataStorage = {}),
            a.getData = function(b) {
                return a.swiperSlideDataStorage[b]
            },
            a.setData = function(b, c) {
                return a.swiperSlideDataStorage[b] = c,
                a
            },
            a.data = function(b, c) {
                return c ? (a.setAttribute("data-" + b, c), a) : a.getAttribute("data-" + b)
            },
            a.getWidth = function(b) {
                return e.h.getWidth(a, b)
            },
            a.getHeight = function(b) {
                return e.h.getHeight(a, b)
            },
            a.getOffset = function() {
                return e.h.getOffset(a)
            },
            a
        },
        e.calcSlides = function(a) {
            var d, f, g, h, c = e.slides ? e.slides.length: !1;
            for (e.slides = [], e.displaySlides = [], d = 0; d < e.wrapper.childNodes.length; d++) if (e.wrapper.childNodes[d].className) for (f = e.wrapper.childNodes[d].className, g = f.split(" "), h = 0; h < g.length; h++) g[h] === b.slideClass && e.slides.push(e.wrapper.childNodes[d]);
            for (d = e.slides.length - 1; d >= 0; d--) e._extendSwiperSlide(e.slides[d]);
            c !== !1 && (c !== e.slides.length || a) && (A(), z(), e.updateActiveSlide(), e.params.pagination && e.createPagination(), e.callPlugins("numberOfSlidesChanged"))
        },
        e.createSlide = function(a, c, d) {
            var f;
            return c = c || e.params.slideClass,
            d = d || b.slideElement,
            f = document.createElement(d),
            f.innerHTML = a || "",
            f.className = c,
            e._extendSwiperSlide(f)
        },
        e.appendSlide = function(a, b, c) {
            return a ? a.nodeType ? e._extendSwiperSlide(a).append() : e.createSlide(a, b, c).append() : void 0
        },
        e.prependSlide = function(a, b, c) {
            return a ? a.nodeType ? e._extendSwiperSlide(a).prepend() : e.createSlide(a, b, c).prepend() : void 0
        },
        e.insertSlideAfter = function(a, b, c, d) {
            return "undefined" == typeof a ? !1 : b.nodeType ? e._extendSwiperSlide(b).insertAfter(a) : e.createSlide(b, c, d).insertAfter(a)
        },
        e.removeSlide = function(a) {
            if (e.slides[a]) {
                if (b.loop) {
                    if (!e.slides[a + e.loopedSlides]) return ! 1;
                    e.slides[a + e.loopedSlides].remove(),
                    e.removeLoopedSlides(),
                    e.calcSlides(),
                    e.createLoop()
                } else e.slides[a].remove();
                return ! 0
            }
            return ! 1
        },
        e.removeLastSlide = function() {
            return e.slides.length > 0 ? (b.loop ? (e.slides[e.slides.length - 1 - e.loopedSlides].remove(), e.removeLoopedSlides(), e.calcSlides(), e.createLoop()) : e.slides[e.slides.length - 1].remove(), !0) : !1
        },
        e.removeAllSlides = function() {
            for (var a = e.slides.length - 1; a >= 0; a--) e.slides[a].remove()
        },
        e.getSlide = function(a) {
            return e.slides[a]
        },
        e.getLastSlide = function() {
            return e.slides[e.slides.length - 1]
        },
        e.getFirstSlide = function() {
            return e.slides[0]
        },
        e.activeSlide = function() {
            return e.slides[e.activeIndex]
        },
        s = [];
        for (t in e.plugins) b[t] && (u = e.plugins[t](e, b[t]), u && s.push(u));
        e.callPlugins = function(a, b) {
            b || (b = {});
            for (var c = 0; c < s.length; c++) a in s[c] && s[c][a](b)
        },
        e.fireCallback = function() {
            var b, a = arguments[0];
            if ("[object Array]" === Object.prototype.toString.call(a)) for (b = 0; b < a.length; b++)"function" == typeof a[b] && a[b](arguments[1], arguments[2], arguments[3], arguments[4], arguments[5]);
            else a(arguments[1], arguments[2], arguments[3], arguments[4], arguments[5])
        },
        e.addCallback = function(a, b) {
            "use strict";
            var d, c = this;
            return c.params["on" + a] ? v(this.params["on" + a]) ? this.params["on" + a].push(b) : "function" == typeof this.params["on" + a] ? (d = this.params["on" + a], this.params["on" + a] = [], this.params["on" + a].push(d), this.params["on" + a].push(b)) : void 0 : (this.params["on" + a] = [], this.params["on" + a].push(b))
        },
        e.removeCallbacks = function(a) {
            return e.params["on" + a] ? e.params["on" + a] = null: void 0
        },
        e.browser.ie10 && !b.onlyExternal && e.wrapper.classList.add("swiper-wp8-" + (o ? "horizontal": "vertical")),
        b.freeMode && (e.container.className += " swiper-free-mode"),
        e.initialized = !1,
        e.init = function(a, c) {
            var i, j, l, m, n, p, q, r, s, t, u, v, w, x, y, z, d = e.h.getWidth(e.container),
            f = e.h.getHeight(e.container);
            if (d !== e.width || f !== e.height || a) {
                if (e.width = d, e.height = f, k = o ? d: f, i = e.wrapper, a && e.calcSlides(c), "auto" === b.slidesPerView) {
                    for (j = 0, l = 0, b.slidesOffset > 0 && (i.style.paddingLeft = "", i.style.paddingRight = "", i.style.paddingTop = "", i.style.paddingBottom = ""), i.style.width = "", i.style.height = "", b.offsetPxBefore > 0 && (o ? e.wrapperLeft = b.offsetPxBefore: e.wrapperTop = b.offsetPxBefore), b.offsetPxAfter > 0 && (o ? e.wrapperRight = b.offsetPxAfter: e.wrapperBottom = b.offsetPxAfter), b.centeredSlides && (o ? (e.wrapperLeft = (k - this.slides[0].getWidth(!0)) / 2, e.wrapperRight = (k - e.slides[e.slides.length - 1].getWidth(!0)) / 2) : (e.wrapperTop = (k - e.slides[0].getHeight(!0)) / 2, e.wrapperBottom = (k - e.slides[e.slides.length - 1].getHeight(!0)) / 2)), o ? (e.wrapperLeft >= 0 && (i.style.paddingLeft = e.wrapperLeft + "px"), e.wrapperRight >= 0 && (i.style.paddingRight = e.wrapperRight + "px")) : (e.wrapperTop >= 0 && (i.style.paddingTop = e.wrapperTop + "px"), e.wrapperBottom >= 0 && (i.style.paddingBottom = e.wrapperBottom + "px")), m = 0, n = 0, e.snapGrid = [], e.slidesGrid = [], p = 0, q = 0; q < e.slides.length; q++) {
                        if (r = e.slides[q].getWidth(!0), s = e.slides[q].getHeight(!0), b.calculateHeight && (p = Math.max(p, s)), t = o ? r: s, b.centeredSlides) {
                            if (u = q === e.slides.length - 1 ? 0 : e.slides[q + 1].getWidth(!0), v = q === e.slides.length - 1 ? 0 : e.slides[q + 1].getHeight(!0), w = o ? u: v, t > k) {
                                for (x = 0; x <= Math.floor(t / (k + e.wrapperLeft)); x++) 0 === x ? e.snapGrid.push(m + e.wrapperLeft) : e.snapGrid.push(m + e.wrapperLeft + k * x);
                                e.slidesGrid.push(m + e.wrapperLeft)
                            } else e.snapGrid.push(n),
                            e.slidesGrid.push(n);
                            n += t / 2 + w / 2
                        } else {
                            if (t > k) for (x = 0; x <= Math.floor(t / k); x++) e.snapGrid.push(m + k * x);
                            else e.snapGrid.push(m);
                            e.slidesGrid.push(m)
                        }
                        m += t,
                        j += r,
                        l += s
                    }
                    b.calculateHeight && (e.height = p),
                    o ? (h = j + e.wrapperRight + e.wrapperLeft, i.style.width = j + "px", i.style.height = e.height + "px") : (h = l + e.wrapperTop + e.wrapperBottom, i.style.width = e.width + "px", i.style.height = l + "px")
                } else if (b.scrollContainer) i.style.width = "",
                i.style.height = "",
                y = e.slides[0].getWidth(!0),
                z = e.slides[0].getHeight(!0),
                h = o ? y: z,
                i.style.width = y + "px",
                i.style.height = z + "px",
                g = o ? y: z;
                else {
                    if (b.calculateHeight) {
                        for (p = 0, z = 0, o || (e.container.style.height = ""), i.style.height = "", q = 0; q < e.slides.length; q++) e.slides[q].style.height = "",
                        p = Math.max(e.slides[q].getHeight(!0), p),
                        o || (z += e.slides[q].getHeight(!0));
                        s = p,
                        e.height = s,
                        o ? z = s: (k = s, e.container.style.height = k + "px")
                    } else s = o ? e.height: e.height / b.slidesPerView,
                    z = o ? e.height: e.slides.length * s;
                    for (r = o ? e.width / b.slidesPerView: e.width, y = o ? e.slides.length * r: e.width, g = o ? r: s, b.offsetSlidesBefore > 0 && (o ? e.wrapperLeft = g * b.offsetSlidesBefore: e.wrapperTop = g * b.offsetSlidesBefore), b.offsetSlidesAfter > 0 && (o ? e.wrapperRight = g * b.offsetSlidesAfter: e.wrapperBottom = g * b.offsetSlidesAfter), b.offsetPxBefore > 0 && (o ? e.wrapperLeft = b.offsetPxBefore: e.wrapperTop = b.offsetPxBefore), b.offsetPxAfter > 0 && (o ? e.wrapperRight = b.offsetPxAfter: e.wrapperBottom = b.offsetPxAfter), b.centeredSlides && (o ? (e.wrapperLeft = (k - g) / 2, e.wrapperRight = (k - g) / 2) : (e.wrapperTop = (k - g) / 2, e.wrapperBottom = (k - g) / 2)), o ? (e.wrapperLeft > 0 && (i.style.paddingLeft = e.wrapperLeft + "px"), e.wrapperRight > 0 && (i.style.paddingRight = e.wrapperRight + "px")) : (e.wrapperTop > 0 && (i.style.paddingTop = e.wrapperTop + "px"), e.wrapperBottom > 0 && (i.style.paddingBottom = e.wrapperBottom + "px")), h = o ? y + e.wrapperRight + e.wrapperLeft: z + e.wrapperTop + e.wrapperBottom, b.cssWidthAndHeight || (parseFloat(y) > 0 && (i.style.width = y + "px"), parseFloat(z) > 0 && (i.style.height = z + "px")), m = 0, e.snapGrid = [], e.slidesGrid = [], q = 0; q < e.slides.length; q++) e.snapGrid.push(m),
                    e.slidesGrid.push(m),
                    m += g,
                    b.cssWidthAndHeight || (parseFloat(r) > 0 && (e.slides[q].style.width = r + "px"), parseFloat(s) > 0 && (e.slides[q].style.height = s + "px"))
                }
                e.initialized ? (e.callPlugins("onInit"), b.onInit && e.fireCallback(b.onInit, e)) : (e.callPlugins("onFirstInit"), b.onFirstInit && e.fireCallback(b.onFirstInit, e)),
                e.initialized = !0
            }
        },
        e.reInit = function(a) {
            e.init(!0, a)
        },
        e.resizeFix = function(a) {
            e.callPlugins("beforeResizeFix"),
            e.init(b.resizeReInit || a),
            b.freeMode ? e.getWrapperTranslate() < -w() && (e.setWrapperTransition(0), e.setWrapperTranslate( - w())) : e.swipeTo(b.loop ? e.activeLoopIndex: e.activeIndex, 0, !1),
            e.callPlugins("afterResizeFix")
        },
        e.destroy = function() {
            var c = e.h.removeEventListener;
            e.browser.ie10 ? (c(e.wrapper, e.touchEvents.touchStart, N), c(document, e.touchEvents.touchMove, Q), c(document, e.touchEvents.touchEnd, R)) : (e.support.touch && (c(e.wrapper, "touchstart", N), c(e.wrapper, "touchmove", Q), c(e.wrapper, "touchend", R)), b.simulateTouch && (c(e.wrapper, "mousedown", N), c(document, "mousemove", Q), c(document, "mouseup", R))),
            b.autoResize && c(window, "resize", e.resizeFix),
            A(),
            b.paginationClickable && V(),
            b.mousewheelControl && e._wheelEvent && c(e.container, e._wheelEvent, D),
            b.keyboardControl && c(document, "keydown", B),
            b.autoplay && e.stopAutoplay(),
            e.callPlugins("onDestroy"),
            e = null
        },
        C = (new Date).getTime(),
        b.grabCursor && (E = e.container.style, E.cursor = "move", E.cursor = "grab", E.cursor = "-moz-grab", E.cursor = "-webkit-grab"),
        e.allowSlideClick = !0,
        e.allowLinks = !0,
        K = !1,
        M = !0,
        e.swipeNext = function(a) {
            var c, d, f, h;
            if (!a && b.loop && e.fixLoop(), !a && b.autoplay && e.stopAutoplay(!0), e.callPlugins("onSwipeNext"), c = e.getWrapperTranslate(), d = c, "auto" == b.slidesPerView) {
                for (f = 0; f < e.snapGrid.length; f++) if ( - c >= e.snapGrid[f] && -c < e.snapGrid[f + 1]) {
                    d = -e.snapGrid[f + 1];
                    break
                }
            } else h = g * b.slidesPerGroup,
            d = -(Math.floor(Math.abs(c) / Math.floor(h)) * h + h);
            return d < -w() && (d = -w()),
            d == c ? !1 : (T(d, "next"), !0)
        },
        e.swipePrev = function(a) {
            var c, d, f, h;
            if (!a && b.loop && e.fixLoop(), !a && b.autoplay && e.stopAutoplay(!0), e.callPlugins("onSwipePrev"), c = Math.ceil(e.getWrapperTranslate()), "auto" == b.slidesPerView) for (d = 0, f = 1; f < e.snapGrid.length; f++) {
                if ( - c == e.snapGrid[f]) {
                    d = -e.snapGrid[f - 1];
                    break
                }
                if ( - c > e.snapGrid[f] && -c < e.snapGrid[f + 1]) {
                    d = -e.snapGrid[f];
                    break
                }
            } else h = g * b.slidesPerGroup,
            d = -(Math.ceil( - c / h) - 1) * h;
            return d > 0 && (d = 0),
            d == c ? !1 : (T(d, "prev"), !0)
        },
        e.swipeReset = function() {
            var a, c, d, h;
            if (e.callPlugins("onSwipeReset"), a = e.getWrapperTranslate(), c = g * b.slidesPerGroup, -w(), "auto" == b.slidesPerView) {
                for (d = 0, h = 0; h < e.snapGrid.length; h++) {
                    if ( - a === e.snapGrid[h]) return;
                    if ( - a >= e.snapGrid[h] && -a < e.snapGrid[h + 1]) {
                        d = e.positions.diff > 0 ? -e.snapGrid[h + 1] : -e.snapGrid[h];
                        break
                    }
                } - a >= e.snapGrid[e.snapGrid.length - 1] && (d = -e.snapGrid[e.snapGrid.length - 1]),
                a <= -w() && (d = -w())
            } else d = 0 > a ? Math.round(a / c) * c: 0;
            return b.scrollContainer && (d = 0 > a ? a: 0),
            d < -w() && (d = -w()),
            b.scrollContainer && k > g && (d = 0),
            d == a ? !1 : (T(d, "reset"), !0)
        },
        e.swipeTo = function(a, c, d) {
            var f, h;
            return a = parseInt(a, 10),
            e.callPlugins("onSwipeTo", {
                index: a,
                speed: c
            }),
            b.loop && (a += e.loopedSlides),
            f = e.getWrapperTranslate(),
            a > e.slides.length - 1 || 0 > a ? void 0 : (h = "auto" == b.slidesPerView ? -e.slidesGrid[a] : -a * g, h < -w() && (h = -w()), h == f ? !1 : (d = d === !1 ? !1 : !0, T(h, "to", {
                index: a,
                speed: c,
                runCallbacks: d
            }), !0))
        },
        e._queueStartCallbacks = !1,
        e._queueEndCallbacks = !1,
        e.updateActiveSlide = function(a) {
            var d, f, h, i, j, k;
            if (e.initialized && 0 != e.slides.length) {
                if (e.previousIndex = e.activeIndex, "undefined" == typeof a && (a = e.getWrapperTranslate()), a > 0 && (a = 0), "auto" == b.slidesPerView) {
                    if (e.activeIndex = e.slidesGrid.indexOf( - a), e.activeIndex < 0) {
                        for (d = 0; d < e.slidesGrid.length - 1 && !( - a > e.slidesGrid[d] && -a < e.slidesGrid[d + 1]); d++);
                        f = Math.abs(e.slidesGrid[d] + a),
                        h = Math.abs(e.slidesGrid[d + 1] + a),
                        e.activeIndex = h >= f ? d: d + 1
                    }
                } else e.activeIndex = Math[b.visibilityFullFit ? "ceil": "round"]( - a / g);
                if (e.activeIndex == e.slides.length && (e.activeIndex = e.slides.length - 1), e.activeIndex < 0 && (e.activeIndex = 0), e.slides[e.activeIndex]) {
                    for (e.calcVisibleSlides(a), i = new RegExp("\\s*" + b.slideActiveClass), j = new RegExp("\\s*" + b.slideVisibleClass), d = 0; d < e.slides.length; d++) e.slides[d].className = e.slides[d].className.replace(i, "").replace(j, ""),
                    e.visibleSlides.indexOf(e.slides[d]) >= 0 && (e.slides[d].className += " " + b.slideVisibleClass);
                    e.slides[e.activeIndex].className += " " + b.slideActiveClass,
                    b.loop ? (k = e.loopedSlides, e.activeLoopIndex = e.activeIndex - k, e.activeLoopIndex >= e.slides.length - 2 * k && (e.activeLoopIndex = e.slides.length - 2 * k - e.activeLoopIndex), e.activeLoopIndex < 0 && (e.activeLoopIndex = e.slides.length - 2 * k + e.activeLoopIndex)) : e.activeLoopIndex = e.activeIndex,
                    b.pagination && e.updatePagination(a)
                }
            }
        },
        e.createPagination = function(a) {
            var c, f, g, h;
            if (b.paginationClickable && e.paginationButtons && V(), e.paginationContainer = b.pagination.nodeType ? b.pagination: d(b.pagination)[0], b.createPagination) {
                for (c = "", f = e.slides.length, g = f, b.loop && (g -= 2 * e.loopedSlides), h = 0; g > h; h++) c += "<" + b.paginationElement + ' class="' + b.paginationElementClass + '"></' + b.paginationElement + ">";
                e.paginationContainer.innerHTML = c
            }
            e.paginationButtons = d("." + b.paginationElementClass, e.paginationContainer),
            a || e.updatePagination(),
            e.callPlugins("onCreatePagination"),
            b.paginationClickable && W()
        },
        e.updatePagination = function(a) {
            var c, f, g, h, i, j;
            if (b.pagination && !(e.slides.length < 1) && (c = d("." + b.paginationActiveClass, e.paginationContainer), c && (f = e.paginationButtons, 0 != f.length))) {
                for (g = 0; g < f.length; g++) f[g].className = b.paginationElementClass;
                if (h = b.loop ? e.loopedSlides: 0, b.paginationAsRange) {
                    for (e.visibleSlides || e.calcVisibleSlides(a), i = [], g = 0; g < e.visibleSlides.length; g++) j = e.slides.indexOf(e.visibleSlides[g]) - h,
                    b.loop && 0 > j && (j = e.slides.length - 2 * e.loopedSlides + j),
                    b.loop && j >= e.slides.length - 2 * e.loopedSlides && (j = e.slides.length - 2 * e.loopedSlides - j, j = Math.abs(j)),
                    i.push(j);
                    for (g = 0; g < i.length; g++) f[i[g]] && (f[i[g]].className += " " + b.paginationVisibleClass);
                    b.loop ? f[e.activeLoopIndex].className += " " + b.paginationActiveClass: f[e.activeIndex].className += " " + b.paginationActiveClass
                } else b.loop ? f[e.activeLoopIndex].className += " " + b.paginationActiveClass + " " + b.paginationVisibleClass: f[e.activeIndex].className += " " + b.paginationActiveClass + " " + b.paginationVisibleClass
            }
        },
        e.calcVisibleSlides = function(a) {
            var i, j, c = [],
            d = 0,
            f = 0,
            h = 0;
            for (o && e.wrapperLeft > 0 && (a += e.wrapperLeft), !o && e.wrapperTop > 0 && (a += e.wrapperTop), i = 0; i < e.slides.length; i++) d += f,
            f = "auto" == b.slidesPerView ? o ? e.h.getWidth(e.slides[i], !0) : e.h.getHeight(e.slides[i], !0) : g,
            h = d + f,
            j = !1,
            b.visibilityFullFit ? (d >= -a && -a + k >= h && (j = !0), -a >= d && h >= -a + k && (j = !0)) : (h > -a && -a + k >= h && (j = !0), d >= -a && -a + k > d && (j = !0), -a > d && h > -a + k && (j = !0)),
            j && c.push(e.slides[i]);
            0 == c.length && (c = [e.slides[e.activeIndex]]),
            e.visibleSlides = c
        },
        Y = void 0,
        e.startAutoplay = function() {
            return "undefined" != typeof Y ? !1 : (b.autoplay && (e.callPlugins("onAutoplayStart"), Z()), void 0)
        },
        e.stopAutoplay = function(a) {
            Y && clearTimeout(Y),
            Y = void 0,
            a && !b.autoplayDisableOnInteraction && e.wrapperTransitionEnd(function() {
                Z()
            }),
            e.callPlugins("onAutoplayStop")
        },
        e.loopCreated = !1,
        e.removeLoopedSlides = function() {
            if (e.loopCreated) for (var a = 0; a < e.slides.length; a++) e.slides[a].getData("looped") === !0 && e.wrapper.removeChild(e.slides[a])
        },
        e.createLoop = function() {
            var d, a, c, g, h, i, j, k, l, m;
            if (0 != e.slides.length) {
                for (e.loopedSlides = "auto" == b.slidesPerView ? b.loopedSlides || 1 : b.slidesPerView + b.loopAdditionalSlides, e.loopedSlides > e.slides.length && (e.loopedSlides = e.slides.length), a = "", c = "", g = "", h = e.slides.length, i = Math.floor(e.loopedSlides / h), j = e.loopedSlides % h, d = 0; i * h > d; d++) k = d,
                d >= h && (l = Math.floor(d / h), k = d - h * l),
                g += e.slides[k].outerHTML;
                for (d = 0; j > d; d++) c += e.slides[d].outerHTML;
                for (d = h - j; h > d; d++) a += e.slides[d].outerHTML;
                for (m = a + g + f.innerHTML + g + c, f.innerHTML = m, e.loopCreated = !0, e.calcSlides(), d = 0; d < e.slides.length; d++)(d < e.loopedSlides || d >= e.slides.length - e.loopedSlides) && e.slides[d].setData("looped", !0);
                e.callPlugins("onCreateLoop")
            }
        },
        e.fixLoop = function() {
            var a;
            e.activeIndex < e.loopedSlides ? (a = e.slides.length - 3 * e.loopedSlides + e.activeIndex, e.swipeTo(a, 0, !1)) : e.activeIndex > e.slides.length - 2 * b.slidesPerView && (a = -e.slides.length + e.activeIndex + e.loopedSlides, e.swipeTo(a, 0, !1))
        },
        e.loadSlides = function() {
            var c, d, f, a = "";
            for (e.activeLoaderIndex = 0, c = b.loader.slides, d = b.loader.loadAllSlides ? c.length: b.slidesPerView * (1 + b.loader.surroundGroups), f = 0; d > f; f++) a += "outer" == b.loader.slidesHTMLType ? c[f] : "<" + b.slideElement + ' class="' + b.slideClass + '" data-swiperindex="' + f + '">' + c[f] + "</" + b.slideElement + ">";
            e.wrapper.innerHTML = a,
            e.calcSlides(!0),
            b.loader.loadAllSlides || e.wrapperTransitionEnd(e.reloadSlides, !0)
        },
        e.reloadSlides = function() {
            var d, f, h, i, j, k, l, m, n, a = b.loader.slides,
            c = parseInt(e.activeSlide().data("swiperindex"), 10);
            if (! (0 > c || c > a.length - 1)) {
                if (e.activeLoaderIndex = c, d = Math.max(0, c - b.slidesPerView * b.loader.surroundGroups), f = Math.min(c + b.slidesPerView * (1 + b.loader.surroundGroups) - 1, a.length - 1), c > 0 && (h = -g * (c - d), e.setWrapperTranslate(h), e.setWrapperTransition(0)), "reload" === b.loader.logic) {
                    for (e.wrapper.innerHTML = "", i = "", j = d; f >= j; j++) i += "outer" == b.loader.slidesHTMLType ? a[j] : "<" + b.slideElement + ' class="' + b.slideClass + '" data-swiperindex="' + j + '">' + a[j] + "</" + b.slideElement + ">";
                    e.wrapper.innerHTML = i
                } else {
                    for (k = 1e3, l = 0, j = 0; j < e.slides.length; j++) m = e.slides[j].data("swiperindex"),
                    d > m || m > f ? e.wrapper.removeChild(e.slides[j]) : (k = Math.min(m, k), l = Math.max(m, l));
                    for (j = d; f >= j; j++) k > j && (n = document.createElement(b.slideElement), n.className = b.slideClass, n.setAttribute("data-swiperindex", j), n.innerHTML = a[j], e.wrapper.insertBefore(n, e.wrapper.firstChild)),
                    j > l && (n = document.createElement(b.slideElement), n.className = b.slideClass, n.setAttribute("data-swiperindex", j), n.innerHTML = a[j], e.wrapper.appendChild(n))
                }
                e.reInit(!0)
            }
        },
        $()
    }
};
Swiper.prototype = {
    plugins: {},
    wrapperTransitionEnd: function(a, b) {
        function g() {
            if (a(c), c.params.queueEndCallbacks && (c._queueEndCallbacks = !1), !b) for (f = 0; f < e.length; f++) c.h.removeEventListener(d, e[f], g)
        }
        var f, c = this,
        d = c.wrapper,
        e = ["webkitTransitionEnd", "transitionend", "oTransitionEnd", "MSTransitionEnd", "msTransitionEnd"];
        if (a) for (f = 0; f < e.length; f++) c.h.addEventListener(d, e[f], g)
    },
    getWrapperTranslate: function(a) {
        var c, d, e, f, b = this.wrapper;
        return "undefined" == typeof a && (a = "horizontal" == this.params.mode ? "x": "y"),
        this.support.transforms && this.params.useCSS3Transforms ? (e = window.getComputedStyle(b, null), window.WebKitCSSMatrix ? f = new WebKitCSSMatrix(e.webkitTransform) : (f = e.MozTransform || e.OTransform || e.MsTransform || e.msTransform || e.transform || e.getPropertyValue("transform").replace("translate(", "matrix(1, 0, 0, 1,"), c = f.toString().split(",")), "x" == a && (d = window.WebKitCSSMatrix ? f.m41: 16 == c.length ? parseFloat(c[12]) : parseFloat(c[4])), "y" == a && (d = window.WebKitCSSMatrix ? f.m42: 16 == c.length ? parseFloat(c[13]) : parseFloat(c[5]))) : ("x" == a && (d = parseFloat(b.style.left, 10) || 0), "y" == a && (d = parseFloat(b.style.top, 10) || 0)),
        d || 0
    },
    setWrapperTranslate: function(a, b, c) {
        var f, d = this.wrapper.style,
        e = {
            x: 0,
            y: 0,
            z: 0
        };
        3 == arguments.length ? (e.x = a, e.y = b, e.z = c) : ("undefined" == typeof b && (b = "horizontal" == this.params.mode ? "x": "y"), e[b] = a),
        this.support.transforms && this.params.useCSS3Transforms ? (f = this.support.transforms3d ? "translate3d(" + e.x + "px, " + e.y + "px, " + e.z + "px)": "translate(" + e.x + "px, " + e.y + "px)", d.webkitTransform = d.MsTransform = d.msTransform = d.MozTransform = d.OTransform = d.transform = f) : (d.left = e.x + "px", d.top = e.y + "px"),
        this.callPlugins("onSetWrapperTransform", e),
        this.params.onSetWrapperTransform && this.fireCallback(this.params.onSetWrapperTransform, this, e)
    },
    setWrapperTransition: function(a) {
        var b = this.wrapper.style;
        b.webkitTransitionDuration = b.MsTransitionDuration = b.msTransitionDuration = b.MozTransitionDuration = b.OTransitionDuration = b.transitionDuration = a / 1e3 + "s",
        this.callPlugins("onSetWrapperTransition", {
            duration: a
        }),
        this.params.onSetWrapperTransition && this.fireCallback(this.params.onSetWrapperTransition, this, a)
    },
    h: {
        getWidth: function(a, b) {
            var c = window.getComputedStyle(a, null).getPropertyValue("width"),
            d = parseFloat(c);
            return (isNaN(d) || c.indexOf("%") > 0) && (d = a.offsetWidth - parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-left")) - parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-right"))),
            b && (d += parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-left")) + parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-right"))),
            d
        },
        getHeight: function(a, b) {
            var c, d;
            return b ? a.offsetHeight: (c = window.getComputedStyle(a, null).getPropertyValue("height"), d = parseFloat(c), (isNaN(d) || c.indexOf("%") > 0) && (d = a.offsetHeight - parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-top")) - parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-bottom"))), b && (d += parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-top")) + parseFloat(window.getComputedStyle(a, null).getPropertyValue("padding-bottom"))), d)
        },
        getOffset: function(a) {
            var b = a.getBoundingClientRect(),
            c = document.body,
            d = a.clientTop || c.clientTop || 0,
            e = a.clientLeft || c.clientLeft || 0,
            f = window.pageYOffset || a.scrollTop,
            g = window.pageXOffset || a.scrollLeft;
            return document.documentElement && !window.pageYOffset && (f = document.documentElement.scrollTop, g = document.documentElement.scrollLeft),
            {
                top: b.top + f - d,
                left: b.left + g - e
            }
        },
        windowWidth: function() {
            return window.innerWidth ? window.innerWidth: document.documentElement && document.documentElement.clientWidth ? document.documentElement.clientWidth: void 0
        },
        windowHeight: function() {
            return window.innerHeight ? window.innerHeight: document.documentElement && document.documentElement.clientHeight ? document.documentElement.clientHeight: void 0
        },
        windowScroll: function() {
            return "undefined" != typeof pageYOffset ? {
                left: window.pageXOffset,
                top: window.pageYOffset
            }: document.documentElement ? {
                left: document.documentElement.scrollLeft,
                top: document.documentElement.scrollTop
            }: void 0
        },
        addEventListener: function(a, b, c, d) {
            "undefined" == typeof d && (d = !1),
            a.addEventListener ? a.addEventListener(b, c, d) : a.attachEvent && a.attachEvent("on" + b, c)
        },
        removeEventListener: function(a, b, c, d) {
            "undefined" == typeof d && (d = !1),
            a.removeEventListener ? a.removeEventListener(b, c, d) : a.detachEvent && a.detachEvent("on" + b, c)
        }
    },
    setTransform: function(a, b) {
        var c = a.style;
        c.webkitTransform = c.MsTransform = c.msTransform = c.MozTransform = c.OTransform = c.transform = b
    },
    setTranslate: function(a, b) {
        var c = a.style,
        d = {
            x: b.x || 0,
            y: b.y || 0,
            z: b.z || 0
        },
        e = this.support.transforms3d ? "translate3d(" + d.x + "px," + d.y + "px," + d.z + "px)": "translate(" + d.x + "px," + d.y + "px)";
        c.webkitTransform = c.MsTransform = c.msTransform = c.MozTransform = c.OTransform = c.transform = e,
        this.support.transforms || (c.left = d.x + "px", c.top = d.y + "px")
    },
    setTransition: function(a, b) {
        var c = a.style;
        c.webkitTransitionDuration = c.MsTransitionDuration = c.msTransitionDuration = c.MozTransitionDuration = c.OTransitionDuration = c.transitionDuration = b + "ms"
    },
    support: {
        touch: window.Modernizr && Modernizr.touch === !0 ||
        function() {
            return !! ("ontouchstart" in window || window.DocumentTouch && document instanceof DocumentTouch)
        } (),
        transforms3d: window.Modernizr && Modernizr.csstransforms3d === !0 ||
        function() {
            var a = document.createElement("div").style;
            return "webkitPerspective" in a || "MozPerspective" in a || "OPerspective" in a || "MsPerspective" in a || "perspective" in a
        } (),
        transforms: window.Modernizr && Modernizr.csstransforms === !0 ||
        function() {
            var a = document.createElement("div").style;
            return "transform" in a || "WebkitTransform" in a || "MozTransform" in a || "msTransform" in a || "MsTransform" in a || "OTransform" in a
        } (),
        transitions: window.Modernizr && Modernizr.csstransitions === !0 ||
        function() {
            var a = document.createElement("div").style;
            return "transition" in a || "WebkitTransition" in a || "MozTransition" in a || "msTransition" in a || "MsTransition" in a || "OTransition" in a
        } ()
    },
    browser: {
        ie8: function() {
            var b, c, a = -1;
            return "Microsoft Internet Explorer" == navigator.appName && (b = navigator.userAgent, c = new RegExp("MSIE ([0-9]{1,}[.0-9]{0,})"), null != c.exec(b) && (a = parseFloat(RegExp.$1))),
            -1 != a && 9 > a
        } (),
        ie10: window.navigator.msPointerEnabled
    }
},
(window.jQuery || window.Zepto) &&
function(a) {
    a.fn.swiper = function(b) {
        var c = new Swiper(a(this)[0], b);
        return a(this).data("swiper", c),
        c
    }
} (window.jQuery || window.Zepto),
"undefined" != typeof module && (module.exports = Swiper);