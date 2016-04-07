function particleUniverse() {
    function start() {
        container = document.createElement("div"),
        document.getElementById("particles").appendChild(container),
        camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 1e4),
        camera.position.z = 300,
        scene = new THREE.Scene;
        var lineMaterial = new THREE.LineBasicMaterial({color: 0xffffff}), distance = 299;
        for (geometry = new THREE.Geometry, geometryLine = new THREE.Geometry, i = 0; 75 > i; i++) {
            var vertex = new THREE.Vector3, theta = THREE.Math.randFloatSpread(360), phi = THREE.Math.randFloatSpread(360);
            do vertex.x = 2e3 * Math.random() - 1e3, vertex.y = 2e3 * Math.random() - 1e3, vertex.z = 2e3 * Math.random() - 1e3;
            while (vertex.length() > 500);
            geometry.vertices.push(vertex), geometryLine.vertices.push(vertex)
        }
        new THREE.Line(geometryLine, lineMaterial);
        attributes = {size: {type: "f", value: []}, customColor: {type: "c", value: []}},
        uniforms = {
            color: {
                type: "c",
                value: new THREE.Color(0xffffff)
            },
         texture: {type: "t", value: THREE.ImageUtils.loadTexture(baseUrl + "img/home/disc.png")}
    };

    var shaderMaterial = new THREE.ShaderMaterial({
        uniforms: uniforms,
        attributes: attributes,
        color: {type: "c", value: new THREE.Color(0xffffff)},
        vertexShader: document.getElementById("vertexshader").textContent,
        fragmentShader: document.getElementById("fragmentshader").textContent,
        alphaTest:.9
    });
    materials[0] = new THREE.PointCloudMaterial({
        size: 40,
        color: 0xffffff
    }),
    particles = new THREE.PointCloud(geometry, shaderMaterial);
    for (var values_size = attributes.size.value, values_color = attributes.customColor.value, vertices = particles.geometry.vertices, v = 0, vl = vertices.length; vl > v; v++)
      values_size[v] = PARTICLE_SIZE,
        values_color[v] = (new THREE.Color(0xffffff));
        scene.add(particles);
        var material = new THREE.MeshBasicMaterial({color: 0xffffff, wireframe: !0, wireframeLinewidth: 1});
        Ico = new THREE.Mesh(new THREE.IcosahedronGeometry(150, 0), material),
        Ico.position.x = -160, scene.add(Ico),
        THREE.ImageUtils.crossOrigin = "",
        dotLime = THREE.ImageUtils.loadTexture(baseUrl + "img/home/dot-lime.png"),
        dotLBlue = THREE.ImageUtils.loadTexture(baseUrl + "img/home/dot-lblue.png"),
        dotSunset = THREE.ImageUtils.loadTexture(baseUrl + "img/home/dot-sunset.png"),
        dotOrange = THREE.ImageUtils.loadTexture(baseUrl + "img/home/dot-orange.png"),
        dotVelvet = THREE.ImageUtils.loadTexture(baseUrl + "img/home/dot-velvet.png");
        var dots = [dotLime, dotLBlue, dotSunset, dotOrange, dotVelvet];
        distance = 150;
        var shadeMaterial = new THREE.ShaderMaterial({
            uniforms: {
                texture: {
                    type: "t",
                    value: THREE.ImageUtils.loadTexture(baseUrl + "img/home/disc.png")
                }, color: {type: "c", value: new THREE.Color(0xffffff)}
            },
            attributes: {customColor: {type: "c", value: [new THREE.Color(0xffffff)]}, size: {type: "f", value: [40]}},
            vertexShader: document.getElementById("vertexshader").textContent,
            fragmentShader: document.getElementById("fragmentshader").textContent,
            alphaTest: .9
        });
        /*立体图形的所有的点*/
        for (i = 0; i < Ico.geometry.vertices.length; i++) {
            geometry2 = new THREE.Geometry;
            var vertex = new THREE.Vector3;
            vertex.x = Ico.geometry.vertices[i].x, vertex.y = Ico.geometry.vertices[i].y, vertex.z = Ico.geometry.vertices[i].z, geometry2.vertices.push(vertex);
            new THREE.PointCloudMaterial({opacity: 1, size: 64, segments: 32, transparent: !0, color: 0xffffff});
            particles2 = new THREE.PointCloud(geometry2, shadeMaterial), particles2.position.x = -160, particles2.name = "bubbles", scene.add(particles2)
        }
       //particles2.position.x = -160是所有立体上点的距中心点距离
        for (i = 0; 5 > i; i++) {
            geometry2 = new THREE.Geometry;
            var vertex = new THREE.Vector3, theta = THREE.Math.randFloatSpread(360), phi = THREE.Math.randFloatSpread(360);
            distance = 80,
            vertex.x = distance * Math.sin(theta) * Math.cos(phi),
            vertex.y = distance * Math.sin(theta) * Math.sin(phi), vertex.z = distance *Math.cos(theta), geometry2.vertices.push(vertex),
            materials2[i] = new THREE.PointCloudMaterial({
                opacity: 1,
                size: 64,
                segments: 32,
                map: dots[i],
                alphaTest: .5,
                transparent: !0
            }),
            particles2 = new THREE.PointCloud(geometry2, materials2[i]),
              particles2.name = "colors", particles2.rotation.x += .005 * Math.random(),
              particles2.rotation.y += .007 * Math.random(),
              particles2.rotation.z += .003 * Math.random(),
              particles2.position.x = -200,//所有小颜色图片的weizhi,以中间点为原点
              scene.add(particles2)
        }
        renderer = new THREE.WebGLRenderer({alpha: !0}),
          renderer.setPixelRatio(window.devicePixelRatio),
          renderer.setSize(window.innerWidth, window.innerHeight),
          container.appendChild(renderer.domElement),
          mouse = new THREE.Vector2,
          document.addEventListener("mousemove", onDocumentMouseMove, !1),
          window.addEventListener("scroll", onWindowScroll, !1),
          window.addEventListener("resize", onWindowResize, !1)
    }

    function onWindowResize() {
        windowHalfX = window.innerWidth / 2,
          windowHalfY = window.innerHeight / 2,
          camera.aspect = window.innerWidth / window.innerHeight,
          camera.updateProjectionMatrix(),
          renderer.setSize(window.innerWidth, window.innerHeight)
    }

    function onWindowScroll(event) {
        var doc = document.documentElement, top = (window.pageYOffset || doc.scrollTop) - (doc.clientTop || 0);
        topScroll = top
    }

    function onDocumentMouseMove(event) {
        mouseX = event.clientX - windowHalfX,
          mouseY = event.clientY - windowHalfY,
          mouse.x = event.clientX / window.innerWidth * 2 - 1,
          mouse.y = 2 * -(event.clientY / window.innerHeight) + 1
    }

    function animate() {
        requestAnimationFrame(animate), render()
    }

    function render() {
        for (camera.position.x += .05 * (.2 * mouseX - camera.position.x), camera.position.y += .05 * (-(.2 * mouseY) - camera.position.y), camera.lookAt(scene.position), particles.rotation.x += .001, particles.rotation.y += .001, Ico.rotation.x += .005, Ico.rotation.y += .005, Ico.position.y = topScroll, i = 0; i < scene.children.length; i++) {
            var object = scene.children[i];
            object instanceof THREE.PointCloud && "bubbles" === object.name && (object.rotation.x += .005, object.rotation.y += .005, object.position.y = topScroll), "colors" === object.name && (object.rotation.x -= .005, object.rotation.y -= .005, object.rotation.z -= .005, object.position.y = topScroll)
        }
        renderer.setClearColor(0, 0), renderer.render(scene, camera)
    }

    Detector.webgl || Detector.addGetWebGLMessage();
    var container, camera, scene, renderer, particles, geometry, geometry2, i, mouse, uniforms, attributes, Ico, materials = [], materials2 = [], topScroll = 0,            PARTICLE_SIZE = 20, mouseX = 0, mouseY = 0, windowHalfX = window.innerWidth / 2, windowHalfY = window.innerHeight / 2;
    start(), animate()
}


/*jQuery*/
if (function (global, factory) {
        "object" == typeof module && "object" == typeof module.exports ? module.exports = global.document ? factory(global, !0) : function (w) {
            if (!w.document)throw new Error("jQuery requires a window with a document");
            return factory(w)
        } : factory(global)
    }("undefined" != typeof window ? window : this, function (window, noGlobal) {
        function isArraylike(obj) {
            var length = "length" in obj && obj.length, type = jQuery.type(obj);
            return "function" === type || jQuery.isWindow(obj) ? !1 : 1 === obj.nodeType && length ? !0 : "array" === type || 0 === length || "number" == typeof length && length > 0 && length - 1 in obj
        }

        function winnow(elements, qualifier, not) {
            if (jQuery.isFunction(qualifier))return jQuery.grep(elements, function (elem, i) {
                return !!qualifier.call(elem, i, elem) !== not
            });
            if (qualifier.nodeType)return jQuery.grep(elements, function (elem) {
                return elem === qualifier !== not
            });
            if ("string" == typeof qualifier) {
                if (risSimple.test(qualifier))return jQuery.filter(qualifier, elements, not);
                qualifier = jQuery.filter(qualifier, elements)
            }
            return jQuery.grep(elements, function (elem) {
                return indexOf.call(qualifier, elem) >= 0 !== not
            })
        }

        function sibling(cur, dir) {
            for (; (cur = cur[dir]) && 1 !== cur.nodeType;);
            return cur
        }

        function createOptions(options) {
            var object = optionsCache[options] = {};
            return jQuery.each(options.match(rnotwhite) || [], function (_, flag) {
                object[flag] = !0
            }), object
        }

        function completed() {
            document.removeEventListener("DOMContentLoaded", completed, !1), window.removeEventListener("load", completed, !1), jQuery.ready()
        }

        function Data() {
            Object.defineProperty(this.cache = {}, 0, {
                get: function () {
                    return {}
                }
            }), this.expando = jQuery.expando + Data.uid++
        }

        function dataAttr(elem, key, data) {
            var name;
            if (void 0 === data && 1 === elem.nodeType)if (name = "data-" + key.replace(rmultiDash, "-$1").toLowerCase(), data = elem.getAttribute(name), "string" == typeof data) {
                try {
                    data = "true" === data ? !0 : "false" === data ? !1 : "null" === data ? null : +data + "" === data ? +data : rbrace.test(data) ? jQuery.parseJSON(data) : data
                } catch (e) {
                }
                data_user.set(elem, key, data)
            } else data = void 0;
            return data
        }

        function returnTrue() {
            return !0
        }

        function returnFalse() {
            return !1
        }

        function safeActiveElement() {
            try {
                return document.activeElement
            } catch (err) {
            }
        }

        function manipulationTarget(elem, content) {
            return jQuery.nodeName(elem, "table") && jQuery.nodeName(11 !== content.nodeType ? content : content.firstChild, "tr") ? elem.getElementsByTagName("tbody")[0] || elem.appendChild(elem.ownerDocument.createElement("tbody")) : elem
        }

        function disableScript(elem) {
            return elem.type = (null !== elem.getAttribute("type")) + "/" + elem.type, elem
        }

        function restoreScript(elem) {
            var match = rscriptTypeMasked.exec(elem.type);
            return match ? elem.type = match[1] : elem.removeAttribute("type"), elem
        }

        function setGlobalEval(elems, refElements) {
            for (var i = 0, l = elems.length; l > i; i++)data_priv.set(elems[i], "globalEval", !refElements || data_priv.get(refElements[i], "globalEval"))
        }

        function cloneCopyEvent(src, dest) {
            var i, l, type, pdataOld, pdataCur, udataOld, udataCur, events;
            if (1 === dest.nodeType) {
                if (data_priv.hasData(src) && (pdataOld = data_priv.access(src), pdataCur = data_priv.set(dest, pdataOld), events = pdataOld.events)) {
                    delete pdataCur.handle, pdataCur.events = {};
                    for (type in events)for (i = 0, l = events[type].length; l > i; i++)jQuery.event.add(dest, type, events[type][i])
                }
                data_user.hasData(src) && (udataOld = data_user.access(src), udataCur = jQuery.extend({}, udataOld), data_user.set(dest, udataCur))
            }
        }

        function getAll(context, tag) {
            var ret = context.getElementsByTagName ? context.getElementsByTagName(tag || "*") : context.querySelectorAll ? context.querySelectorAll(tag || "*") : [];
            return void 0 === tag || tag && jQuery.nodeName(context, tag) ? jQuery.merge([context], ret) : ret
        }

        function fixInput(src, dest) {
            var nodeName = dest.nodeName.toLowerCase();
            "input" === nodeName && rcheckableType.test(src.type) ? dest.checked = src.checked : ("input" === nodeName || "textarea" === nodeName) && (dest.defaultValue = src.defaultValue)
        }

        function actualDisplay(name, doc) {
            var style, elem = jQuery(doc.createElement(name)).appendTo(doc.body), display = window.getDefaultComputedStyle && (style = window.getDefaultComputedStyle(elem[0])) ? style.display : jQuery.css(elem[0], "display");
            return elem.detach(), display
        }

        function defaultDisplay(nodeName) {
            var doc = document, display = elemdisplay[nodeName];
            return display || (display = actualDisplay(nodeName, doc), "none" !== display && display || (iframe = (iframe || jQuery("<iframe frameborder='0' width='0' height='0'/>")).appendTo(doc.documentElement), doc = iframe[0].contentDocument, doc.write(), doc.close(), display = actualDisplay(nodeName, doc), iframe.detach()), elemdisplay[nodeName] = display), display
        }

        function curCSS(elem, name, computed) {
            var width, minWidth, maxWidth, ret, style = elem.style;
            return computed = computed || getStyles(elem), computed && (ret = computed.getPropertyValue(name) || computed[name]), computed && ("" !== ret || jQuery.contains(elem.ownerDocument, elem) || (ret = jQuery.style(elem, name)), rnumnonpx.test(ret) && rmargin.test(name) && (width = style.width, minWidth = style.minWidth, maxWidth = style.maxWidth, style.minWidth = style.maxWidth = style.width = ret, ret = computed.width, style.width = width, style.minWidth = minWidth, style.maxWidth = maxWidth)), void 0 !== ret ? ret + "" : ret
        }

        function addGetHookIf(conditionFn, hookFn) {
            return {
                get: function () {
                    return conditionFn() ? void delete this.get : (this.get = hookFn).apply(this, arguments)
                }
            }
        }

        function vendorPropName(style, name) {
            if (name in style)return name;
            for (var capName = name[0].toUpperCase() + name.slice(1), origName = name, i = cssPrefixes.length; i--;)if (name = cssPrefixes[i] + capName, name in style)return name;
            return origName
        }

        function setPositiveNumber(elem, value, subtract) {
            var matches = rnumsplit.exec(value);
            return matches ? Math.max(0, matches[1] - (subtract || 0)) + (matches[2] || "px") : value
        }

        function augmentWidthOrHeight(elem, name, extra, isBorderBox, styles) {
            for (var i = extra === (isBorderBox ? "border" : "content") ? 4 : "width" === name ? 1 : 0, val = 0; 4 > i; i += 2)"margin" === extra && (val += jQuery.css(elem, extra + cssExpand[i], !0, styles)), isBorderBox ? ("content" === extra && (val -= jQuery.css(elem, "padding" + cssExpand[i], !0, styles)), "margin" !== extra && (val -= jQuery.css(elem, "border" + cssExpand[i] + "Width", !0, styles))) : (val += jQuery.css(elem, "padding" + cssExpand[i], !0, styles), "padding" !== extra && (val += jQuery.css(elem, "border" + cssExpand[i] + "Width", !0, styles)));
            return val
        }

        function getWidthOrHeight(elem, name, extra) {
            var valueIsBorderBox = !0, val = "width" === name ? elem.offsetWidth : elem.offsetHeight, styles = getStyles(elem), isBorderBox = "border-box" === jQuery.css(elem, "boxSizing", !1, styles);
            if (0 >= val || null == val) {
                if (val = curCSS(elem, name, styles), (0 > val || null == val) && (val = elem.style[name]), rnumnonpx.test(val))return val;
                valueIsBorderBox = isBorderBox && (support.boxSizingReliable() || val === elem.style[name]), val = parseFloat(val) || 0
            }
            return val + augmentWidthOrHeight(elem, name, extra || (isBorderBox ? "border" : "content"), valueIsBorderBox, styles) + "px"
        }

        function showHide(elements, show) {
            for (var display, elem, hidden, values = [], index = 0, length = elements.length; length > index; index++)elem = elements[index], elem.style && (values[index] = data_priv.get(elem, "olddisplay"), display = elem.style.display, show ? (values[index] || "none" !== display || (elem.style.display = ""), "" === elem.style.display && isHidden(elem) && (values[index] = data_priv.access(elem, "olddisplay", defaultDisplay(elem.nodeName)))) : (hidden = isHidden(elem), "none" === display && hidden || data_priv.set(elem, "olddisplay", hidden ? display : jQuery.css(elem, "display"))));
            for (index = 0; length > index; index++)elem = elements[index], elem.style && (show && "none" !== elem.style.display && "" !== elem.style.display || (elem.style.display = show ? values[index] || "" : "none"));
            return elements
        }

        function Tween(elem, options, prop, end, easing) {
            return new Tween.prototype.init(elem, options, prop, end, easing)
        }

        function createFxNow() {
            return setTimeout(function () {
                fxNow = void 0
            }), fxNow = jQuery.now()
        }

        function genFx(type, includeWidth) {
            var which, i = 0, attrs = {height: type};
            for (includeWidth = includeWidth ? 1 : 0; 4 > i; i += 2 - includeWidth)which = cssExpand[i], attrs["margin" + which] = attrs["padding" + which] = type;
            return includeWidth && (attrs.opacity = attrs.width = type), attrs
        }

        function createTween(value, prop, animation) {
            for (var tween, collection = (tweeners[prop] || []).concat(tweeners["*"]), index = 0, length = collection.length; length > index; index++)if (tween = collection[index].call(animation, prop, value))return tween
        }

        function defaultPrefilter(elem, props, opts) {
            var prop, value, toggle, tween, hooks, oldfire, display, checkDisplay, anim = this, orig = {}, style = elem.style, hidden = elem.nodeType && isHidden(elem), dataShow = data_priv.get(elem, "fxshow");
            opts.queue || (hooks = jQuery._queueHooks(elem, "fx"), null == hooks.unqueued && (hooks.unqueued = 0, oldfire = hooks.empty.fire, hooks.empty.fire = function () {
                hooks.unqueued || oldfire()
            }), hooks.unqueued++, anim.always(function () {
                anim.always(function () {
                    hooks.unqueued--, jQuery.queue(elem, "fx").length || hooks.empty.fire()
                })
            })), 1 === elem.nodeType && ("height" in props || "width" in props) && (opts.overflow = [style.overflow, style.overflowX, style.overflowY], display = jQuery.css(elem, "display"), checkDisplay = "none" === display ? data_priv.get(elem, "olddisplay") || defaultDisplay(elem.nodeName) : display, "inline" === checkDisplay && "none" === jQuery.css(elem, "float") && (style.display = "inline-block")), opts.overflow && (style.overflow = "hidden", anim.always(function () {
                style.overflow = opts.overflow[0], style.overflowX = opts.overflow[1], style.overflowY = opts.overflow[2]
            }));
            for (prop in props)if (value = props[prop], rfxtypes.exec(value)) {
                if (delete props[prop], toggle = toggle || "toggle" === value, value === (hidden ? "hide" : "show")) {
                    if ("show" !== value || !dataShow || void 0 === dataShow[prop])continue;
                    hidden = !0
                }
                orig[prop] = dataShow && dataShow[prop] || jQuery.style(elem, prop)
            } else display = void 0;
            if (jQuery.isEmptyObject(orig))"inline" === ("none" === display ? defaultDisplay(elem.nodeName) : display) && (style.display = display); else {
                dataShow ? "hidden" in dataShow && (hidden = dataShow.hidden) : dataShow = data_priv.access(elem, "fxshow", {}), toggle && (dataShow.hidden = !hidden), hidden ? jQuery(elem).show() : anim.done(function () {
                    jQuery(elem).hide()
                }), anim.done(function () {
                    var prop;
                    data_priv.remove(elem, "fxshow");
                    for (prop in orig)jQuery.style(elem, prop, orig[prop])
                });
                for (prop in orig)tween = createTween(hidden ? dataShow[prop] : 0, prop, anim), prop in dataShow || (dataShow[prop] = tween.start, hidden && (tween.end = tween.start, tween.start = "width" === prop || "height" === prop ? 1 : 0))
            }
        }

        function propFilter(props, specialEasing) {
            var index, name, easing, value, hooks;
            for (index in props)if (name = jQuery.camelCase(index), easing = specialEasing[name], value = props[index], jQuery.isArray(value) && (easing = value[1], value = props[index] = value[0]), index !== name && (props[name] = value, delete props[index]), hooks = jQuery.cssHooks[name], hooks && "expand" in hooks) {
                value = hooks.expand(value), delete props[name];
                for (index in value)index in props || (props[index] = value[index], specialEasing[index] = easing)
            } else specialEasing[name] = easing
        }

        function Animation(elem, properties, options) {
            var result, stopped, index = 0, length = animationPrefilters.length, deferred = jQuery.Deferred().always(function () {
                delete tick.elem
            }), tick = function () {
                if (stopped)return !1;
                for (var currentTime = fxNow || createFxNow(), remaining = Math.max(0, animation.startTime + animation.duration - currentTime), temp = remaining / animation.duration || 0, percent = 1 - temp, index = 0, length = animation.tweens.length; length > index; index++)animation.tweens[index].run(percent);
                return deferred.notifyWith(elem, [animation, percent, remaining]), 1 > percent && length ? remaining : (deferred.resolveWith(elem, [animation]), !1)
            }, animation = deferred.promise({
                elem: elem,
                props: jQuery.extend({}, properties),
                opts: jQuery.extend(!0, {specialEasing: {}}, options),
                originalProperties: properties,
                originalOptions: options,
                startTime: fxNow || createFxNow(),
                duration: options.duration,
                tweens: [],
                createTween: function (prop, end) {
                    var tween = jQuery.Tween(elem, animation.opts, prop, end, animation.opts.specialEasing[prop] || animation.opts.easing);
                    return animation.tweens.push(tween), tween
                },
                stop: function (gotoEnd) {
                    var index = 0, length = gotoEnd ? animation.tweens.length : 0;
                    if (stopped)return this;
                    for (stopped = !0; length > index; index++)animation.tweens[index].run(1);
                    return gotoEnd ? deferred.resolveWith(elem, [animation, gotoEnd]) : deferred.rejectWith(elem, [animation, gotoEnd]), this
                }
            }), props = animation.props;
            for (propFilter(props, animation.opts.specialEasing); length > index; index++)if (result = animationPrefilters[index].call(animation, elem, props, animation.opts))return result;
            return jQuery.map(props, createTween, animation), jQuery.isFunction(animation.opts.start) && animation.opts.start.call(elem, animation), jQuery.fx.timer(jQuery.extend(tick, {
                elem: elem,
                anim: animation,
                queue: animation.opts.queue
            })), animation.progress(animation.opts.progress).done(animation.opts.done, animation.opts.complete).fail(animation.opts.fail).always(animation.opts.always)
        }

        function addToPrefiltersOrTransports(structure) {
            return function (dataTypeExpression, func) {
                "string" != typeof dataTypeExpression && (func = dataTypeExpression, dataTypeExpression = "*");
                var dataType, i = 0, dataTypes = dataTypeExpression.toLowerCase().match(rnotwhite) || [];
                if (jQuery.isFunction(func))for (; dataType = dataTypes[i++];)"+" === dataType[0] ? (dataType = dataType.slice(1) || "*", (structure[dataType] = structure[dataType] || []).unshift(func)) : (structure[dataType] = structure[dataType] || []).push(func)
            }
        }

        function inspectPrefiltersOrTransports(structure, options, originalOptions, jqXHR) {
            function inspect(dataType) {
                var selected;
                return inspected[dataType] = !0, jQuery.each(structure[dataType] || [], function (_, prefilterOrFactory) {
                    var dataTypeOrTransport = prefilterOrFactory(options, originalOptions, jqXHR);
                    return "string" != typeof dataTypeOrTransport || seekingTransport || inspected[dataTypeOrTransport] ? seekingTransport ? !(selected = dataTypeOrTransport) : void 0 : (options.dataTypes.unshift(dataTypeOrTransport), inspect(dataTypeOrTransport), !1)
                }), selected
            }

            var inspected = {}, seekingTransport = structure === transports;
            return inspect(options.dataTypes[0]) || !inspected["*"] && inspect("*")
        }

        function ajaxExtend(target, src) {
            var key, deep, flatOptions = jQuery.ajaxSettings.flatOptions || {};
            for (key in src)void 0 !== src[key] && ((flatOptions[key] ? target : deep || (deep = {}))[key] = src[key]);
            return deep && jQuery.extend(!0, target, deep), target
        }

        function ajaxHandleResponses(s, jqXHR, responses) {
            for (var ct, type, finalDataType, firstDataType, contents = s.contents, dataTypes = s.dataTypes; "*" === dataTypes[0];)dataTypes.shift(), void 0 === ct && (ct = s.mimeType || jqXHR.getResponseHeader("Content-Type"));
            if (ct)for (type in contents)if (contents[type] && contents[type].test(ct)) {
                dataTypes.unshift(type);
                break
            }
            if (dataTypes[0] in responses)finalDataType = dataTypes[0]; else {
                for (type in responses) {
                    if (!dataTypes[0] || s.converters[type + " " + dataTypes[0]]) {
                        finalDataType = type;
                        break
                    }
                    firstDataType || (firstDataType = type)
                }
                finalDataType = finalDataType || firstDataType
            }
            return finalDataType ? (finalDataType !== dataTypes[0] && dataTypes.unshift(finalDataType), responses[finalDataType]) : void 0
        }

        function ajaxConvert(s, response, jqXHR, isSuccess) {
            var conv2, current, conv, tmp, prev, converters = {}, dataTypes = s.dataTypes.slice();
            if (dataTypes[1])for (conv in s.converters)converters[conv.toLowerCase()] = s.converters[conv];
            for (current = dataTypes.shift(); current;)if (s.responseFields[current] && (jqXHR[s.responseFields[current]] = response), !prev && isSuccess && s.dataFilter && (response = s.dataFilter(response, s.dataType)), prev = current, current = dataTypes.shift())if ("*" === current)current = prev; else if ("*" !== prev && prev !== current) {
                if (conv = converters[prev + " " + current] || converters["* " + current], !conv)for (conv2 in converters)if (tmp = conv2.split(" "), tmp[1] === current && (conv = converters[prev + " " + tmp[0]] || converters["* " + tmp[0]])) {
                    conv === !0 ? conv = converters[conv2] : converters[conv2] !== !0 && (current = tmp[0], dataTypes.unshift(tmp[1]));
                    break
                }
                if (conv !== !0)if (conv && s["throws"])response = conv(response); else try {
                    response = conv(response)
                } catch (e) {
                    return {state: "parsererror", error: conv ? e : "No conversion from " + prev + " to " + current}
                }
            }
            return {state: "success", data: response}
        }

        function buildParams(prefix, obj, traditional, add) {
            var name;
            if (jQuery.isArray(obj))jQuery.each(obj, function (i, v) {
                traditional || rbracket.test(prefix) ? add(prefix, v) : buildParams(prefix + "[" + ("object" == typeof v ? i : "") + "]", v, traditional, add)
            }); else if (traditional || "object" !== jQuery.type(obj))add(prefix, obj); else for (name in obj)buildParams(prefix + "[" + name + "]", obj[name], traditional, add)
        }

        function getWindow(elem) {
            return jQuery.isWindow(elem) ? elem : 9 === elem.nodeType && elem.defaultView
        }

        var arr = [], slice = arr.slice, concat = arr.concat, push = arr.push, indexOf = arr.indexOf, class2type = {}, toString = class2type.toString, hasOwn = class2type.hasOwnProperty, support = {}, document = window.document, version = "2.1.4", jQuery = function (selector, context) {
            return new jQuery.fn.init(selector, context)
        }, rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, rmsPrefix = /^-ms-/, rdashAlpha = /-([\da-z])/gi, fcamelCase = function (all, letter) {
            return letter.toUpperCase()
        };
        jQuery.fn = jQuery.prototype = {
            jquery: version, constructor: jQuery, selector: "", length: 0, toArray: function () {
                return slice.call(this)
            }, get: function (num) {
                return null != num ? 0 > num ? this[num + this.length] : this[num] : slice.call(this)
            }, pushStack: function (elems) {
                var ret = jQuery.merge(this.constructor(), elems);
                return ret.prevObject = this, ret.context = this.context, ret
            }, each: function (callback, args) {
                return jQuery.each(this, callback, args)
            }, map: function (callback) {
                return this.pushStack(jQuery.map(this, function (elem, i) {
                    return callback.call(elem, i, elem)
                }))
            }, slice: function () {
                return this.pushStack(slice.apply(this, arguments))
            }, first: function () {
                return this.eq(0)
            },
            last: function () {
                return this.eq(-1)
            }, eq: function (i) {
                var len = this.length, j = +i + (0 > i ? len : 0);
                return this.pushStack(j >= 0 && len > j ? [this[j]] : [])
            }, end: function () {
                return this.prevObject || this.constructor(null)
            }, push: push, sort: arr.sort, splice: arr.splice
        }, jQuery.extend = jQuery.fn.extend = function () {
            var options, name, src, copy, copyIsArray, clone, target = arguments[0] || {}, i = 1, length = arguments.length, deep = !1;
            for ("boolean" == typeof target && (deep = target, target = arguments[i] || {}, i++), "object" == typeof target || jQuery.isFunction(target) || (target = {}), i === length && (target = this, i--); length > i; i++)if (null != (options = arguments[i]))for (name in options)src = target[name], copy = options[name], target !== copy && (deep && copy && (jQuery.isPlainObject(copy) || (copyIsArray = jQuery.isArray(copy))) ? (copyIsArray ? (copyIsArray = !1, clone = src && jQuery.isArray(src) ? src : []) : clone = src && jQuery.isPlainObject(src) ? src : {}, target[name] = jQuery.extend(deep, clone, copy)) : void 0 !== copy && (target[name] = copy));
            return target
        }, jQuery.extend({
            expando: "jQuery" + (version + Math.random()).replace(/\D/g, ""),
            isReady: !0,
            error: function (msg) {
                throw new Error(msg)
            },
            noop: function () {
            },
            isFunction: function (obj) {
                return "function" === jQuery.type(obj)
            },
            isArray: Array.isArray,
            isWindow: function (obj) {
                return null != obj && obj === obj.window
            },
            isNumeric: function (obj) {
                return !jQuery.isArray(obj) && obj - parseFloat(obj) + 1 >= 0
            },
            isPlainObject: function (obj) {
                return "object" !== jQuery.type(obj) || obj.nodeType || jQuery.isWindow(obj) ? !1 : obj.constructor && !hasOwn.call(obj.constructor.prototype, "isPrototypeOf") ? !1 : !0
            },
            isEmptyObject: function (obj) {
                var name;
                for (name in obj)return !1;
                return !0
            },
            type: function (obj) {
                return null == obj ? obj + "" : "object" == typeof obj || "function" == typeof obj ? class2type[toString.call(obj)] || "object" : typeof obj
            },
            globalEval: function (code) {
                var script, indirect = eval;
                code = jQuery.trim(code), code && (1 === code.indexOf("use strict") ? (script = document.createElement("script"), script.text = code, document.head.appendChild(script).parentNode.removeChild(script)) : indirect(code))
            },
            camelCase: function (string) {
                return string.replace(rmsPrefix, "ms-").replace(rdashAlpha, fcamelCase)
            },
            nodeName: function (elem, name) {
                return elem.nodeName && elem.nodeName.toLowerCase() === name.toLowerCase()
            },
            each: function (obj, callback, args) {
                var value, i = 0, length = obj.length, isArray = isArraylike(obj);
                if (args) {
                    if (isArray)for (; length > i && (value = callback.apply(obj[i], args), value !== !1); i++); else for (i in obj)if (value = callback.apply(obj[i], args), value === !1)break
                } else if (isArray)for (; length > i && (value = callback.call(obj[i], i, obj[i]), value !== !1); i++); else for (i in obj)if (value = callback.call(obj[i], i, obj[i]), value === !1)break;
                return obj
            },
            trim: function (text) {
                return null == text ? "" : (text + "").replace(rtrim, "")
            },
            makeArray: function (arr, results) {
                var ret = results || [];
                return null != arr && (isArraylike(Object(arr)) ? jQuery.merge(ret, "string" == typeof arr ? [arr] : arr) : push.call(ret, arr)), ret
            },
            inArray: function (elem, arr, i) {
                return null == arr ? -1 : indexOf.call(arr, elem, i)
            },
            merge: function (first, second) {
                for (var len = +second.length, j = 0, i = first.length; len > j; j++)first[i++] = second[j];
                return first.length = i, first
            },
            grep: function (elems, callback, invert) {
                for (var callbackInverse, matches = [], i = 0, length = elems.length, callbackExpect = !invert; length > i; i++)callbackInverse = !callback(elems[i], i), callbackInverse !== callbackExpect && matches.push(elems[i]);
                return matches
            },
            map: function (elems, callback, arg) {
                var value, i = 0, length = elems.length, isArray = isArraylike(elems), ret = [];
                if (isArray)for (; length > i; i++)value = callback(elems[i], i, arg), null != value && ret.push(value); else for (i in elems)value = callback(elems[i], i, arg), null != value && ret.push(value);
                return concat.apply([], ret)
            },
            guid: 1,
            proxy: function (fn, context) {
                var tmp, args, proxy;
                return "string" == typeof context && (tmp = fn[context], context = fn, fn = tmp), jQuery.isFunction(fn) ? (args = slice.call(arguments, 2), proxy = function () {
                    return fn.apply(context || this, args.concat(slice.call(arguments)))
                }, proxy.guid = fn.guid = fn.guid || jQuery.guid++, proxy) : void 0
            },
            now: Date.now,
            support: support
        }), jQuery.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function (i, name) {
            class2type["[object " + name + "]"] = name.toLowerCase()
        });
        var Sizzle = function (window) {
            function Sizzle(selector, context, results, seed) {
                var match, elem, m, nodeType, i, groups, old, nid, newContext, newSelector;
                if ((context ? context.ownerDocument || context : preferredDoc) !== document && setDocument(context), context = context || document, results = results || [], nodeType = context.nodeType, "string" != typeof selector || !selector || 1 !== nodeType && 9 !== nodeType && 11 !== nodeType)return results;
                if (!seed && documentIsHTML) {
                    if (11 !== nodeType && (match = rquickExpr.exec(selector)))if (m = match[1]) {
                        if (9 === nodeType) {
                            if (elem = context.getElementById(m), !elem || !elem.parentNode)return results;
                            if (elem.id === m)return results.push(elem), results
                        } else if (context.ownerDocument && (elem = context.ownerDocument.getElementById(m)) && contains(context, elem) && elem.id === m)return results.push(elem), results
                    } else {
                        if (match[2])return push.apply(results, context.getElementsByTagName(selector)), results;
                        if ((m = match[3]) && support.getElementsByClassName)return push.apply(results, context.getElementsByClassName(m)), results
                    }
                    if (support.qsa && (!rbuggyQSA || !rbuggyQSA.test(selector))) {
                        if (nid = old = expando, newContext = context, newSelector = 1 !== nodeType && selector, 1 === nodeType && "object" !== context.nodeName.toLowerCase()) {
                            for (groups = tokenize(selector), (old = context.getAttribute("id")) ? nid = old.replace(rescape, "\\$&") : context.setAttribute("id", nid), nid = "[id='" + nid + "'] ", i = groups.length; i--;)groups[i] = nid + toSelector(groups[i]);
                            newContext = rsibling.test(selector) && testContext(context.parentNode) || context, newSelector = groups.join(",")
                        }
                        if (newSelector)try {
                            return push.apply(results, newContext.querySelectorAll(newSelector)), results
                        } catch (qsaError) {
                        } finally {
                            old || context.removeAttribute("id")
                        }
                    }
                }
                return select(selector.replace(rtrim, "$1"), context, results, seed)
            }

            function createCache() {
                function cache(key, value) {
                    return keys.push(key + " ") > Expr.cacheLength && delete cache[keys.shift()], cache[key + " "] = value
                }

                var keys = [];
                return cache
            }

            function markFunction(fn) {
                return fn[expando] = !0, fn
            }

            function assert(fn) {
                var div = document.createElement("div");
                try {
                    return !!fn(div)
                } catch (e) {
                    return !1
                } finally {
                    div.parentNode && div.parentNode.removeChild(div), div = null
                }
            }

            function addHandle(attrs, handler) {
                for (var arr = attrs.split("|"), i = attrs.length; i--;)Expr.attrHandle[arr[i]] = handler
            }

            function siblingCheck(a, b) {
                var cur = b && a, diff = cur && 1 === a.nodeType && 1 === b.nodeType && (~b.sourceIndex || MAX_NEGATIVE) - (~a.sourceIndex || MAX_NEGATIVE);
                if (diff)return diff;
                if (cur)for (; cur = cur.nextSibling;)if (cur === b)return -1;
                return a ? 1 : -1
            }

            function createInputPseudo(type) {
                return function (elem) {
                    var name = elem.nodeName.toLowerCase();
                    return "input" === name && elem.type === type
                }
            }

            function createButtonPseudo(type) {
                return function (elem) {
                    var name = elem.nodeName.toLowerCase();
                    return ("input" === name || "button" === name) && elem.type === type
                }
            }

            function createPositionalPseudo(fn) {
                return markFunction(function (argument) {
                    return argument = +argument, markFunction(function (seed, matches) {
                        for (var j, matchIndexes = fn([], seed.length, argument), i = matchIndexes.length; i--;)seed[j = matchIndexes[i]] && (seed[j] = !(matches[j] = seed[j]))
                    })
                })
            }

            function testContext(context) {
                return context && "undefined" != typeof context.getElementsByTagName && context
            }

            function setFilters() {
            }

            function toSelector(tokens) {
                for (var i = 0, len = tokens.length, selector = ""; len > i; i++)selector += tokens[i].value;
                return selector
            }

            function addCombinator(matcher, combinator, base) {
                var dir = combinator.dir, checkNonElements = base && "parentNode" === dir, doneName = done++;
                return combinator.first ? function (elem, context, xml) {
                    for (; elem = elem[dir];)if (1 === elem.nodeType || checkNonElements)return matcher(elem, context, xml)
                } : function (elem, context, xml) {
                    var oldCache, outerCache, newCache = [dirruns, doneName];
                    if (xml) {
                        for (; elem = elem[dir];)if ((1 === elem.nodeType || checkNonElements) && matcher(elem, context, xml))return !0
                    } else for (; elem = elem[dir];)if (1 === elem.nodeType || checkNonElements) {
                        if (outerCache = elem[expando] || (elem[expando] = {}), (oldCache = outerCache[dir]) && oldCache[0] === dirruns && oldCache[1] === doneName)return newCache[2] = oldCache[2];
                        if (outerCache[dir] = newCache, newCache[2] = matcher(elem, context, xml))return !0
                    }
                }
            }

            function elementMatcher(matchers) {
                return matchers.length > 1 ? function (elem, context, xml) {
                    for (var i = matchers.length; i--;)if (!matchers[i](elem, context, xml))return !1;
                    return !0
                } : matchers[0]
            }

            function multipleContexts(selector, contexts, results) {
                for (var i = 0, len = contexts.length; len > i; i++)Sizzle(selector, contexts[i], results);
                return results
            }

            function condense(unmatched, map, filter, context, xml) {
                for (var elem, newUnmatched = [], i = 0, len = unmatched.length, mapped = null != map; len > i; i++)(elem = unmatched[i]) && (!filter || filter(elem, context, xml)) && (newUnmatched.push(elem), mapped && map.push(i));
                return newUnmatched
            }

            function setMatcher(preFilter, selector, matcher, postFilter, postFinder, postSelector) {
                return postFilter && !postFilter[expando] && (postFilter = setMatcher(postFilter)), postFinder && !postFinder[expando] && (postFinder = setMatcher(postFinder, postSelector)), markFunction(function (seed, results, context, xml) {
                    var temp, i, elem, preMap = [], postMap = [], preexisting = results.length, elems = seed || multipleContexts(selector || "*", context.nodeType ? [context] : context, []), matcherIn = !preFilter || !seed && selector ? elems : condense(elems, preMap, preFilter, context, xml), matcherOut = matcher ? postFinder || (seed ? preFilter : preexisting || postFilter) ? [] : results : matcherIn;
                    if (matcher && matcher(matcherIn, matcherOut, context, xml), postFilter)for (temp = condense(matcherOut, postMap), postFilter(temp, [], context, xml), i = temp.length; i--;)(elem = temp[i]) && (matcherOut[postMap[i]] = !(matcherIn[postMap[i]] = elem));
                    if (seed) {
                        if (postFinder || preFilter) {
                            if (postFinder) {
                                for (temp = [], i = matcherOut.length; i--;)(elem = matcherOut[i]) && temp.push(matcherIn[i] = elem);
                                postFinder(null, matcherOut = [], temp, xml)
                            }
                            for (i = matcherOut.length; i--;)(elem = matcherOut[i]) && (temp = postFinder ? indexOf(seed, elem) : preMap[i]) > -1 && (seed[temp] = !(results[temp] = elem))
                        }
                    } else matcherOut = condense(matcherOut === results ? matcherOut.splice(preexisting, matcherOut.length) : matcherOut), postFinder ? postFinder(null, results, matcherOut, xml) : push.apply(results, matcherOut)
                })
            }

            function matcherFromTokens(tokens) {
                for (var checkContext, matcher, j, len = tokens.length, leadingRelative = Expr.relative[tokens[0].type], implicitRelative = leadingRelative || Expr.relative[" "], i = leadingRelative ? 1 : 0, matchContext = addCombinator(function (elem) {
                    return elem === checkContext
                }, implicitRelative, !0), matchAnyContext = addCombinator(function (elem) {
                    return indexOf(checkContext, elem) > -1
                }, implicitRelative, !0), matchers = [function (elem, context, xml) {
                    var ret = !leadingRelative && (xml || context !== outermostContext) || ((checkContext = context).nodeType ? matchContext(elem, context, xml) : matchAnyContext(elem, context, xml));
                    return checkContext = null, ret
                }]; len > i; i++)if (matcher = Expr.relative[tokens[i].type])matchers = [addCombinator(elementMatcher(matchers), matcher)]; else {
                    if (matcher = Expr.filter[tokens[i].type].apply(null, tokens[i].matches), matcher[expando]) {
                        for (j = ++i; len > j && !Expr.relative[tokens[j].type]; j++);
                        return setMatcher(i > 1 && elementMatcher(matchers), i > 1 && toSelector(tokens.slice(0, i - 1).concat({value: " " === tokens[i - 2].type ? "*" : ""})).replace(rtrim, "$1"), matcher, j > i && matcherFromTokens(tokens.slice(i, j)), len > j && matcherFromTokens(tokens = tokens.slice(j)), len > j && toSelector(tokens))
                    }
                    matchers.push(matcher)
                }
                return elementMatcher(matchers)
            }

            function matcherFromGroupMatchers(elementMatchers, setMatchers) {
                var bySet = setMatchers.length > 0, byElement = elementMatchers.length > 0, superMatcher = function (seed, context, xml, results, outermost) {
                    var elem, j, matcher, matchedCount = 0, i = "0", unmatched = seed && [], setMatched = [], contextBackup = outermostContext, elems = seed || byElement && Expr.find.TAG("*", outermost), dirrunsUnique = dirruns += null == contextBackup ? 1 : Math.random() || .1, len = elems.length;
                    for (outermost && (outermostContext = context !== document && context); i !== len && null != (elem = elems[i]); i++) {
                        if (byElement && elem) {
                            for (j = 0; matcher = elementMatchers[j++];)if (matcher(elem, context, xml)) {
                                results.push(elem);
                                break
                            }
                            outermost && (dirruns = dirrunsUnique)
                        }
                        bySet && ((elem = !matcher && elem) && matchedCount--, seed && unmatched.push(elem))
                    }
                    if (matchedCount += i, bySet && i !== matchedCount) {
                        for (j = 0; matcher = setMatchers[j++];)matcher(unmatched, setMatched, context, xml);
                        if (seed) {
                            if (matchedCount > 0)for (; i--;)unmatched[i] || setMatched[i] || (setMatched[i] = pop.call(results));
                            setMatched = condense(setMatched)
                        }
                        push.apply(results, setMatched), outermost && !seed && setMatched.length > 0 && matchedCount + setMatchers.length > 1 && Sizzle.uniqueSort(results)
                    }
                    return outermost && (dirruns = dirrunsUnique, outermostContext = contextBackup), unmatched
                };
                return bySet ? markFunction(superMatcher) : superMatcher
            }

            var i, support, Expr, getText, isXML, tokenize, compile, select, outermostContext, sortInput, hasDuplicate, setDocument, document, docElem, documentIsHTML, rbuggyQSA, rbuggyMatches, matches, contains, expando = "sizzle" + 1 * new Date, preferredDoc = window.document, dirruns = 0, done = 0, classCache = createCache(), tokenCache = createCache(), compilerCache = createCache(), sortOrder = function (a, b) {
                return a === b && (hasDuplicate = !0), 0
            }, MAX_NEGATIVE = 1 << 31, hasOwn = {}.hasOwnProperty, arr = [], pop = arr.pop, push_native = arr.push, push = arr.push, slice = arr.slice, indexOf = function (list, elem) {
                for (var i = 0, len = list.length; len > i; i++)if (list[i] === elem)return i;
                return -1
            }, booleans = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped", whitespace = "[\\x20\\t\\r\\n\\f]", characterEncoding = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+", identifier = characterEncoding.replace("w", "w#"), attributes = "\\[" + whitespace + "*(" + characterEncoding + ")(?:" + whitespace + "*([*^$|!~]?=)" + whitespace + "*(?:'((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\"|(" + identifier + "))|)" + whitespace + "*\\]", pseudos = ":(" + characterEncoding + ")(?:\\((('((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\")|((?:\\\\.|[^\\\\()[\\]]|" + attributes + ")*)|.*)\\)|)", rwhitespace = new RegExp(whitespace + "+", "g"), rtrim = new RegExp("^" + whitespace + "+|((?:^|[^\\\\])(?:\\\\.)*)" + whitespace + "+$", "g"), rcomma = new RegExp("^" + whitespace + "*," + whitespace + "*"), rcombinators = new RegExp("^" + whitespace + "*([>+~]|" + whitespace + ")" + whitespace + "*"), rattributeQuotes = new RegExp("=" + whitespace + "*([^\\]'\"]*?)" + whitespace + "*\\]", "g"), rpseudo = new RegExp(pseudos), ridentifier = new RegExp("^" + identifier + "$"), matchExpr = {
                ID: new RegExp("^#(" + characterEncoding + ")"),
                CLASS: new RegExp("^\\.(" + characterEncoding + ")"),
                TAG: new RegExp("^(" + characterEncoding.replace("w", "w*") + ")"),
                ATTR: new RegExp("^" + attributes),
                PSEUDO: new RegExp("^" + pseudos),
                CHILD: new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + whitespace + "*(even|odd|(([+-]|)(\\d*)n|)" + whitespace + "*(?:([+-]|)" + whitespace + "*(\\d+)|))" + whitespace + "*\\)|)", "i"),
                bool: new RegExp("^(?:" + booleans + ")$", "i"),
                needsContext: new RegExp("^" + whitespace + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + whitespace + "*((?:-\\d)?\\d*)" + whitespace + "*\\)|)(?=[^-]|$)", "i")
            }, rinputs = /^(?:input|select|textarea|button)$/i, rheader = /^h\d$/i, rnative = /^[^{]+\{\s*\[native \w/, rquickExpr = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, rsibling = /[+~]/, rescape = /'|\\/g, runescape = new RegExp("\\\\([\\da-f]{1,6}" + whitespace + "?|(" + whitespace + ")|.)", "ig"), funescape = function (_, escaped, escapedWhitespace) {
                var high = "0x" + escaped - 65536;
                return high !== high || escapedWhitespace ? escaped : 0 > high ? String.fromCharCode(high + 65536) : String.fromCharCode(high >> 10 | 55296, 1023 & high | 56320)
            }, unloadHandler = function () {
                setDocument()
            };
            try {
                push.apply(arr = slice.call(preferredDoc.childNodes), preferredDoc.childNodes), arr[preferredDoc.childNodes.length].nodeType
            } catch (e) {
                push = {
                    apply: arr.length ? function (target, els) {
                        push_native.apply(target, slice.call(els))
                    } : function (target, els) {
                        for (var j = target.length, i = 0; target[j++] = els[i++];);
                        target.length = j - 1
                    }
                }
            }
            support = Sizzle.support = {}, isXML = Sizzle.isXML = function (elem) {
                var documentElement = elem && (elem.ownerDocument || elem).documentElement;
                return documentElement ? "HTML" !== documentElement.nodeName : !1
            }, setDocument = Sizzle.setDocument = function (node) {
                var hasCompare, parent, doc = node ? node.ownerDocument || node : preferredDoc;
                return doc !== document && 9 === doc.nodeType && doc.documentElement ? (document = doc, docElem = doc.documentElement, parent = doc.defaultView, parent && parent !== parent.top && (parent.addEventListener ? parent.addEventListener("unload", unloadHandler, !1) : parent.attachEvent && parent.attachEvent("onunload", unloadHandler)), documentIsHTML = !isXML(doc), support.attributes = assert(function (div) {
                    return div.className = "i", !div.getAttribute("className")
                }), support.getElementsByTagName = assert(function (div) {
                    return div.appendChild(doc.createComment("")), !div.getElementsByTagName("*").length
                }), support.getElementsByClassName = rnative.test(doc.getElementsByClassName), support.getById = assert(function (div) {
                    return docElem.appendChild(div).id = expando, !doc.getElementsByName || !doc.getElementsByName(expando).length
                }), support.getById ? (Expr.find.ID = function (id, context) {
                    if ("undefined" != typeof context.getElementById && documentIsHTML) {
                        var m = context.getElementById(id);
                        return m && m.parentNode ? [m] : []
                    }
                }, Expr.filter.ID = function (id) {
                    var attrId = id.replace(runescape, funescape);
                    return function (elem) {
                        return elem.getAttribute("id") === attrId
                    }
                }) : (delete Expr.find.ID, Expr.filter.ID = function (id) {
                    var attrId = id.replace(runescape, funescape);
                    return function (elem) {
                        var node = "undefined" != typeof elem.getAttributeNode && elem.getAttributeNode("id");
                        return node && node.value === attrId
                    }
                }), Expr.find.TAG = support.getElementsByTagName ? function (tag, context) {
                    return "undefined" != typeof context.getElementsByTagName ? context.getElementsByTagName(tag) : support.qsa ? context.querySelectorAll(tag) : void 0
                } : function (tag, context) {
                    var elem, tmp = [], i = 0, results = context.getElementsByTagName(tag);
                    if ("*" === tag) {
                        for (; elem = results[i++];)1 === elem.nodeType && tmp.push(elem);
                        return tmp
                    }
                    return results
                }, Expr.find.CLASS = support.getElementsByClassName && function (className, context) {
                        return documentIsHTML ? context.getElementsByClassName(className) : void 0
                    }, rbuggyMatches = [], rbuggyQSA = [], (support.qsa = rnative.test(doc.querySelectorAll)) && (assert(function (div) {
                    docElem.appendChild(div).innerHTML = "<a id='" + expando + "'></a><select id='" + expando + "-\f]' msallowcapture=''><option selected=''></option></select>", div.querySelectorAll("[msallowcapture^='']").length && rbuggyQSA.push("[*^$]=" + whitespace + "*(?:''|\"\")"), div.querySelectorAll("[selected]").length || rbuggyQSA.push("\\[" + whitespace + "*(?:value|" + booleans + ")"), div.querySelectorAll("[id~=" + expando + "-]").length || rbuggyQSA.push("~="), div.querySelectorAll(":checked").length || rbuggyQSA.push(":checked"), div.querySelectorAll("a#" + expando + "+*").length || rbuggyQSA.push(".#.+[+~]")
                }), assert(function (div) {
                    var input = doc.createElement("input");
                    input.setAttribute("type", "hidden"), div.appendChild(input).setAttribute("name", "D"), div.querySelectorAll("[name=d]").length && rbuggyQSA.push("name" + whitespace + "*[*^$|!~]?="), div.querySelectorAll(":enabled").length || rbuggyQSA.push(":enabled", ":disabled"), div.querySelectorAll("*,:x"), rbuggyQSA.push(",.*:")
                })), (support.matchesSelector = rnative.test(matches = docElem.matches || docElem.webkitMatchesSelector || docElem.mozMatchesSelector || docElem.oMatchesSelector || docElem.msMatchesSelector)) && assert(function (div) {
                    support.disconnectedMatch = matches.call(div, "div"), matches.call(div, "[s!='']:x"), rbuggyMatches.push("!=", pseudos)
                }), rbuggyQSA = rbuggyQSA.length && new RegExp(rbuggyQSA.join("|")), rbuggyMatches = rbuggyMatches.length && new RegExp(rbuggyMatches.join("|")), hasCompare = rnative.test(docElem.compareDocumentPosition), contains = hasCompare || rnative.test(docElem.contains) ? function (a, b) {
                    var adown = 9 === a.nodeType ? a.documentElement : a, bup = b && b.parentNode;
                    return a === bup || !(!bup || 1 !== bup.nodeType || !(adown.contains ? adown.contains(bup) : a.compareDocumentPosition && 16 & a.compareDocumentPosition(bup)))
                } : function (a, b) {
                    if (b)for (; b = b.parentNode;)if (b === a)return !0;
                    return !1
                }, sortOrder = hasCompare ? function (a, b) {
                    if (a === b)return hasDuplicate = !0, 0;
                    var compare = !a.compareDocumentPosition - !b.compareDocumentPosition;
                    return compare ? compare : (compare = (a.ownerDocument || a) === (b.ownerDocument || b) ? a.compareDocumentPosition(b) : 1, 1 & compare || !support.sortDetached && b.compareDocumentPosition(a) === compare ? a === doc || a.ownerDocument === preferredDoc && contains(preferredDoc, a) ? -1 : b === doc || b.ownerDocument === preferredDoc && contains(preferredDoc, b) ? 1 : sortInput ? indexOf(sortInput, a) - indexOf(sortInput, b) : 0 : 4 & compare ? -1 : 1)
                } : function (a, b) {
                    if (a === b)return hasDuplicate = !0, 0;
                    var cur, i = 0, aup = a.parentNode, bup = b.parentNode, ap = [a], bp = [b];
                    if (!aup || !bup)return a === doc ? -1 : b === doc ? 1 : aup ? -1 : bup ? 1 : sortInput ? indexOf(sortInput, a) - indexOf(sortInput, b) : 0;
                    if (aup === bup)return siblingCheck(a, b);
                    for (cur = a; cur = cur.parentNode;)ap.unshift(cur);
                    for (cur = b; cur = cur.parentNode;)bp.unshift(cur);
                    for (; ap[i] === bp[i];)i++;
                    return i ? siblingCheck(ap[i], bp[i]) : ap[i] === preferredDoc ? -1 : bp[i] === preferredDoc ? 1 : 0
                }, doc) : document
            }, Sizzle.matches = function (expr, elements) {
                return Sizzle(expr, null, null, elements)
            }, Sizzle.matchesSelector = function (elem, expr) {
                if ((elem.ownerDocument || elem) !== document && setDocument(elem), expr = expr.replace(rattributeQuotes, "='$1']"), support.matchesSelector && documentIsHTML && (!rbuggyMatches || !rbuggyMatches.test(expr)) && (!rbuggyQSA || !rbuggyQSA.test(expr)))try {
                    var ret = matches.call(elem, expr);
                    if (ret || support.disconnectedMatch || elem.document && 11 !== elem.document.nodeType)return ret
                } catch (e) {
                }
                return Sizzle(expr, document, null, [elem]).length > 0
            }, Sizzle.contains = function (context, elem) {
                return (context.ownerDocument || context) !== document && setDocument(context), contains(context, elem)
            }, Sizzle.attr = function (elem, name) {
                (elem.ownerDocument || elem) !== document && setDocument(elem);
                var fn = Expr.attrHandle[name.toLowerCase()], val = fn && hasOwn.call(Expr.attrHandle, name.toLowerCase()) ? fn(elem, name, !documentIsHTML) : void 0;
                return void 0 !== val ? val : support.attributes || !documentIsHTML ? elem.getAttribute(name) : (val = elem.getAttributeNode(name)) && val.specified ? val.value : null
            }, Sizzle.error = function (msg) {
                throw new Error("Syntax error, unrecognized expression: " + msg)
            }, Sizzle.uniqueSort = function (results) {
                var elem, duplicates = [], j = 0, i = 0;
                if (hasDuplicate = !support.detectDuplicates, sortInput = !support.sortStable && results.slice(0), results.sort(sortOrder), hasDuplicate) {
                    for (; elem = results[i++];)elem === results[i] && (j = duplicates.push(i));
                    for (; j--;)results.splice(duplicates[j], 1)
                }
                return sortInput = null, results
            }, getText = Sizzle.getText = function (elem) {
                var node, ret = "", i = 0, nodeType = elem.nodeType;
                if (nodeType) {
                    if (1 === nodeType || 9 === nodeType || 11 === nodeType) {
                        if ("string" == typeof elem.textContent)return elem.textContent;
                        for (elem = elem.firstChild; elem; elem = elem.nextSibling)ret += getText(elem)
                    } else if (3 === nodeType || 4 === nodeType)return elem.nodeValue
                } else for (; node = elem[i++];)ret += getText(node);
                return ret
            }, Expr = Sizzle.selectors = {
                cacheLength: 50,
                createPseudo: markFunction,
                match: matchExpr,
                attrHandle: {},
                find: {},
                relative: {
                    ">": {dir: "parentNode", first: !0},
                    " ": {dir: "parentNode"},
                    "+": {dir: "previousSibling", first: !0},
                    "~": {dir: "previousSibling"}
                },
                preFilter: {
                    ATTR: function (match) {
                        return match[1] = match[1].replace(runescape, funescape), match[3] = (match[3] || match[4] || match[5] || "").replace(runescape, funescape), "~=" === match[2] && (match[3] = " " + match[3] + " "), match.slice(0, 4)
                    }, CHILD: function (match) {
                        return match[1] = match[1].toLowerCase(), "nth" === match[1].slice(0, 3) ? (match[3] || Sizzle.error(match[0]), match[4] = +(match[4] ? match[5] + (match[6] || 1) : 2 * ("even" === match[3] || "odd" === match[3])), match[5] = +(match[7] + match[8] || "odd" === match[3])) : match[3] && Sizzle.error(match[0]), match
                    }, PSEUDO: function (match) {
                        var excess, unquoted = !match[6] && match[2];
                        return matchExpr.CHILD.test(match[0]) ? null : (match[3] ? match[2] = match[4] || match[5] || "" : unquoted && rpseudo.test(unquoted) && (excess = tokenize(unquoted, !0)) && (excess = unquoted.indexOf(")", unquoted.length - excess) - unquoted.length) && (match[0] = match[0].slice(0, excess), match[2] = unquoted.slice(0, excess)), match.slice(0, 3))
                    }
                },
                filter: {
                    TAG: function (nodeNameSelector) {
                        var nodeName = nodeNameSelector.replace(runescape, funescape).toLowerCase();
                        return "*" === nodeNameSelector ? function () {
                            return !0
                        } : function (elem) {
                            return elem.nodeName && elem.nodeName.toLowerCase() === nodeName
                        }
                    }, CLASS: function (className) {
                        var pattern = classCache[className + " "];
                        return pattern || (pattern = new RegExp("(^|" + whitespace + ")" + className + "(" + whitespace + "|$)")) && classCache(className, function (elem) {
                                return pattern.test("string" == typeof elem.className && elem.className || "undefined" != typeof elem.getAttribute && elem.getAttribute("class") || "")
                            })
                    }, ATTR: function (name, operator, check) {
                        return function (elem) {
                            var result = Sizzle.attr(elem, name);
                            return null == result ? "!=" === operator : operator ? (result += "", "=" === operator ? result === check : "!=" === operator ? result !== check : "^=" === operator ? check && 0 === result.indexOf(check) : "*=" === operator ? check && result.indexOf(check) > -1 : "$=" === operator ? check && result.slice(-check.length) === check : "~=" === operator ? (" " + result.replace(rwhitespace, " ") + " ").indexOf(check) > -1 : "|=" === operator ? result === check || result.slice(0, check.length + 1) === check + "-" : !1) : !0
                        }
                    }, CHILD: function (type, what, argument, first, last) {
                        var simple = "nth" !== type.slice(0, 3), forward = "last" !== type.slice(-4), ofType = "of-type" === what;
                        return 1 === first && 0 === last ? function (elem) {
                            return !!elem.parentNode
                        } : function (elem, context, xml) {
                            var cache, outerCache, node, diff, nodeIndex, start, dir = simple !== forward ? "nextSibling" : "previousSibling", parent = elem.parentNode, name = ofType && elem.nodeName.toLowerCase(), useCache = !xml && !ofType;
                            if (parent) {
                                if (simple) {
                                    for (; dir;) {
                                        for (node = elem; node = node[dir];)if (ofType ? node.nodeName.toLowerCase() === name : 1 === node.nodeType)return !1;
                                        start = dir = "only" === type && !start && "nextSibling"
                                    }
                                    return !0
                                }
                                if (start = [forward ? parent.firstChild : parent.lastChild], forward && useCache) {
                                    for (outerCache = parent[expando] || (parent[expando] = {}), cache = outerCache[type] || [], nodeIndex = cache[0] === dirruns && cache[1], diff = cache[0] === dirruns && cache[2], node = nodeIndex && parent.childNodes[nodeIndex]; node = ++nodeIndex && node && node[dir] || (diff = nodeIndex = 0) || start.pop();)if (1 === node.nodeType && ++diff && node === elem) {
                                        outerCache[type] = [dirruns, nodeIndex, diff];
                                        break
                                    }
                                } else if (useCache && (cache = (elem[expando] || (elem[expando] = {}))[type]) && cache[0] === dirruns)diff = cache[1]; else for (; (node = ++nodeIndex && node && node[dir] || (diff = nodeIndex = 0) || start.pop()) && ((ofType ? node.nodeName.toLowerCase() !== name : 1 !== node.nodeType) || !++diff || (useCache && ((node[expando] || (node[expando] = {}))[type] = [dirruns, diff]), node !== elem)););
                                return diff -= last, diff === first || diff % first === 0 && diff / first >= 0
                            }
                        }
                    }, PSEUDO: function (pseudo, argument) {
                        var args, fn = Expr.pseudos[pseudo] || Expr.setFilters[pseudo.toLowerCase()] || Sizzle.error("unsupported pseudo: " + pseudo);
                        return fn[expando] ? fn(argument) : fn.length > 1 ? (args = [pseudo, pseudo, "", argument], Expr.setFilters.hasOwnProperty(pseudo.toLowerCase()) ? markFunction(function (seed, matches) {
                            for (var idx, matched = fn(seed, argument), i = matched.length; i--;)idx = indexOf(seed, matched[i]), seed[idx] = !(matches[idx] = matched[i])
                        }) : function (elem) {
                            return fn(elem, 0, args)
                        }) : fn
                    }
                },
                pseudos: {
                    not: markFunction(function (selector) {
                        var input = [], results = [], matcher = compile(selector.replace(rtrim, "$1"));
                        return matcher[expando] ? markFunction(function (seed, matches, context, xml) {
                            for (var elem, unmatched = matcher(seed, null, xml, []), i = seed.length; i--;)(elem = unmatched[i]) && (seed[i] = !(matches[i] = elem))
                        }) : function (elem, context, xml) {
                            return input[0] = elem, matcher(input, null, xml, results), input[0] = null, !results.pop()
                        }
                    }), has: markFunction(function (selector) {
                        return function (elem) {
                            return Sizzle(selector, elem).length > 0
                        }
                    }), contains: markFunction(function (text) {
                        return text = text.replace(runescape, funescape), function (elem) {
                            return (elem.textContent || elem.innerText || getText(elem)).indexOf(text) > -1
                        }
                    }), lang: markFunction(function (lang) {
                        return ridentifier.test(lang || "") || Sizzle.error("unsupported lang: " + lang), lang = lang.replace(runescape, funescape).toLowerCase(), function (elem) {
                            var elemLang;
                            do if (elemLang = documentIsHTML ? elem.lang : elem.getAttribute("xml:lang") || elem.getAttribute("lang"))return elemLang = elemLang.toLowerCase(), elemLang === lang || 0 === elemLang.indexOf(lang + "-"); while ((elem = elem.parentNode) && 1 === elem.nodeType);
                            return !1
                        }
                    }), target: function (elem) {
                        var hash = window.location && window.location.hash;
                        return hash && hash.slice(1) === elem.id
                    }, root: function (elem) {
                        return elem === docElem
                    }, focus: function (elem) {
                        return elem === document.activeElement && (!document.hasFocus || document.hasFocus()) && !!(elem.type || elem.href || ~elem.tabIndex)
                    }, enabled: function (elem) {
                        return elem.disabled === !1
                    }, disabled: function (elem) {
                        return elem.disabled === !0
                    }, checked: function (elem) {
                        var nodeName = elem.nodeName.toLowerCase();
                        return "input" === nodeName && !!elem.checked || "option" === nodeName && !!elem.selected
                    }, selected: function (elem) {
                        return elem.parentNode && elem.parentNode.selectedIndex, elem.selected === !0
                    }, empty: function (elem) {
                        for (elem = elem.firstChild; elem; elem = elem.nextSibling)if (elem.nodeType < 6)return !1;
                        return !0
                    }, parent: function (elem) {
                        return !Expr.pseudos.empty(elem)
                    }, header: function (elem) {
                        return rheader.test(elem.nodeName)
                    }, input: function (elem) {
                        return rinputs.test(elem.nodeName)
                    }, button: function (elem) {
                        var name = elem.nodeName.toLowerCase();
                        return "input" === name && "button" === elem.type || "button" === name
                    }, text: function (elem) {
                        var attr;
                        return "input" === elem.nodeName.toLowerCase() && "text" === elem.type && (null == (attr = elem.getAttribute("type")) || "text" === attr.toLowerCase())
                    }, first: createPositionalPseudo(function () {
                        return [0]
                    }), last: createPositionalPseudo(function (matchIndexes, length) {
                        return [length - 1]
                    }), eq: createPositionalPseudo(function (matchIndexes, length, argument) {
                        return [0 > argument ? argument + length : argument]
                    }), even: createPositionalPseudo(function (matchIndexes, length) {
                        for (var i = 0; length > i; i += 2)matchIndexes.push(i);
                        return matchIndexes
                    }), odd: createPositionalPseudo(function (matchIndexes, length) {
                        for (var i = 1; length > i; i += 2)matchIndexes.push(i);
                        return matchIndexes
                    }), lt: createPositionalPseudo(function (matchIndexes, length, argument) {
                        for (var i = 0 > argument ? argument + length : argument; --i >= 0;)matchIndexes.push(i);
                        return matchIndexes
                    }), gt: createPositionalPseudo(function (matchIndexes, length, argument) {
                        for (var i = 0 > argument ? argument + length : argument; ++i < length;)matchIndexes.push(i);
                        return matchIndexes
                    })
                }
            }, Expr.pseudos.nth = Expr.pseudos.eq;
            for (i in{
                radio: !0,
                checkbox: !0,
                file: !0,
                password: !0,
                image: !0
            })Expr.pseudos[i] = createInputPseudo(i);
            for (i in{submit: !0, reset: !0})Expr.pseudos[i] = createButtonPseudo(i);
            return setFilters.prototype = Expr.filters = Expr.pseudos, Expr.setFilters = new setFilters, tokenize = Sizzle.tokenize = function (selector, parseOnly) {
                var matched, match, tokens, type, soFar, groups, preFilters, cached = tokenCache[selector + " "];
                if (cached)return parseOnly ? 0 : cached.slice(0);
                for (soFar = selector, groups = [], preFilters = Expr.preFilter; soFar;) {
                    (!matched || (match = rcomma.exec(soFar))) && (match && (soFar = soFar.slice(match[0].length) || soFar), groups.push(tokens = [])), matched = !1, (match = rcombinators.exec(soFar)) && (matched = match.shift(), tokens.push({
                        value: matched,
                        type: match[0].replace(rtrim, " ")
                    }), soFar = soFar.slice(matched.length));
                    for (type in Expr.filter)!(match = matchExpr[type].exec(soFar)) || preFilters[type] && !(match = preFilters[type](match)) || (matched = match.shift(), tokens.push({
                        value: matched,
                        type: type,
                        matches: match
                    }), soFar = soFar.slice(matched.length));
                    if (!matched)break
                }
                return parseOnly ? soFar.length : soFar ? Sizzle.error(selector) : tokenCache(selector, groups).slice(0)
            }, compile = Sizzle.compile = function (selector, match) {
                var i, setMatchers = [], elementMatchers = [], cached = compilerCache[selector + " "];
                if (!cached) {
                    for (match || (match = tokenize(selector)), i = match.length; i--;)cached = matcherFromTokens(match[i]), cached[expando] ? setMatchers.push(cached) : elementMatchers.push(cached);
                    cached = compilerCache(selector, matcherFromGroupMatchers(elementMatchers, setMatchers)), cached.selector = selector
                }
                return cached
            }, select = Sizzle.select = function (selector, context, results, seed) {
                var i, tokens, token, type, find, compiled = "function" == typeof selector && selector, match = !seed && tokenize(selector = compiled.selector || selector);
                if (results = results || [], 1 === match.length) {
                    if (tokens = match[0] = match[0].slice(0), tokens.length > 2 && "ID" === (token = tokens[0]).type && support.getById && 9 === context.nodeType && documentIsHTML && Expr.relative[tokens[1].type]) {
                        if (context = (Expr.find.ID(token.matches[0].replace(runescape, funescape), context) || [])[0], !context)return results;
                        compiled && (context = context.parentNode), selector = selector.slice(tokens.shift().value.length)
                    }
                    for (i = matchExpr.needsContext.test(selector) ? 0 : tokens.length; i-- && (token = tokens[i],
                        !Expr.relative[type = token.type]);)if ((find = Expr.find[type]) && (seed = find(token.matches[0].replace(runescape, funescape), rsibling.test(tokens[0].type) && testContext(context.parentNode) || context))) {
                        if (tokens.splice(i, 1), selector = seed.length && toSelector(tokens), !selector)return push.apply(results, seed), results;
                        break
                    }
                }
                return (compiled || compile(selector, match))(seed, context, !documentIsHTML, results, rsibling.test(selector) && testContext(context.parentNode) || context), results
            }, support.sortStable = expando.split("").sort(sortOrder).join("") === expando, support.detectDuplicates = !!hasDuplicate, setDocument(), support.sortDetached = assert(function (div1) {
                return 1 & div1.compareDocumentPosition(document.createElement("div"))
            }), assert(function (div) {
                return div.innerHTML = "<a href='#'></a>", "#" === div.firstChild.getAttribute("href")
            }) || addHandle("type|href|height|width", function (elem, name, isXML) {
                return isXML ? void 0 : elem.getAttribute(name, "type" === name.toLowerCase() ? 1 : 2)
            }), support.attributes && assert(function (div) {
                return div.innerHTML = "<input/>", div.firstChild.setAttribute("value", ""), "" === div.firstChild.getAttribute("value")
            }) || addHandle("value", function (elem, name, isXML) {
                return isXML || "input" !== elem.nodeName.toLowerCase() ? void 0 : elem.defaultValue
            }), assert(function (div) {
                return null == div.getAttribute("disabled")
            }) || addHandle(booleans, function (elem, name, isXML) {
                var val;
                return isXML ? void 0 : elem[name] === !0 ? name.toLowerCase() : (val = elem.getAttributeNode(name)) && val.specified ? val.value : null
            }), Sizzle
        }(window);
        jQuery.find = Sizzle, jQuery.expr = Sizzle.selectors, jQuery.expr[":"] = jQuery.expr.pseudos, jQuery.unique = Sizzle.uniqueSort, jQuery.text = Sizzle.getText, jQuery.isXMLDoc = Sizzle.isXML, jQuery.contains = Sizzle.contains;
        var rneedsContext = jQuery.expr.match.needsContext, rsingleTag = /^<(\w+)\s*\/?>(?:<\/\1>|)$/, risSimple = /^.[^:#\[\.,]*$/;
        jQuery.filter = function (expr, elems, not) {
            var elem = elems[0];
            return not && (expr = ":not(" + expr + ")"), 1 === elems.length && 1 === elem.nodeType ? jQuery.find.matchesSelector(elem, expr) ? [elem] : [] : jQuery.find.matches(expr, jQuery.grep(elems, function (elem) {
                return 1 === elem.nodeType
            }))
        }, jQuery.fn.extend({
            find: function (selector) {
                var i, len = this.length, ret = [], self = this;
                if ("string" != typeof selector)return this.pushStack(jQuery(selector).filter(function () {
                    for (i = 0; len > i; i++)if (jQuery.contains(self[i], this))return !0
                }));
                for (i = 0; len > i; i++)jQuery.find(selector, self[i], ret);
                return ret = this.pushStack(len > 1 ? jQuery.unique(ret) : ret), ret.selector = this.selector ? this.selector + " " + selector : selector, ret
            }, filter: function (selector) {
                return this.pushStack(winnow(this, selector || [], !1))
            }, not: function (selector) {
                return this.pushStack(winnow(this, selector || [], !0))
            }, is: function (selector) {
                return !!winnow(this, "string" == typeof selector && rneedsContext.test(selector) ? jQuery(selector) : selector || [], !1).length
            }
        });
        var rootjQuery, rquickExpr = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/, init = jQuery.fn.init = function (selector, context) {
            var match, elem;
            if (!selector)return this;
            if ("string" == typeof selector) {
                if (match = "<" === selector[0] && ">" === selector[selector.length - 1] && selector.length >= 3 ? [null, selector, null] : rquickExpr.exec(selector), !match || !match[1] && context)return !context || context.jquery ? (context || rootjQuery).find(selector) : this.constructor(context).find(selector);
                if (match[1]) {
                    if (context = context instanceof jQuery ? context[0] : context, jQuery.merge(this, jQuery.parseHTML(match[1], context && context.nodeType ? context.ownerDocument || context : document, !0)), rsingleTag.test(match[1]) && jQuery.isPlainObject(context))for (match in context)jQuery.isFunction(this[match]) ? this[match](context[match]) : this.attr(match, context[match]);
                    return this
                }
                return elem = document.getElementById(match[2]), elem && elem.parentNode && (this.length = 1, this[0] = elem), this.context = document, this.selector = selector, this
            }
            return selector.nodeType ? (this.context = this[0] = selector, this.length = 1, this) : jQuery.isFunction(selector) ? "undefined" != typeof rootjQuery.ready ? rootjQuery.ready(selector) : selector(jQuery) : (void 0 !== selector.selector && (this.selector = selector.selector, this.context = selector.context), jQuery.makeArray(selector, this))
        };
        init.prototype = jQuery.fn, rootjQuery = jQuery(document);
        var rparentsprev = /^(?:parents|prev(?:Until|All))/, guaranteedUnique = {
            children: !0,
            contents: !0,
            next: !0,
            prev: !0
        };
        jQuery.extend({
            dir: function (elem, dir, until) {
                for (var matched = [], truncate = void 0 !== until; (elem = elem[dir]) && 9 !== elem.nodeType;)if (1 === elem.nodeType) {
                    if (truncate && jQuery(elem).is(until))break;
                    matched.push(elem)
                }
                return matched
            }, sibling: function (n, elem) {
                for (var matched = []; n; n = n.nextSibling)1 === n.nodeType && n !== elem && matched.push(n);
                return matched
            }
        }), jQuery.fn.extend({
            has: function (target) {
                var targets = jQuery(target, this), l = targets.length;
                return this.filter(function () {
                    for (var i = 0; l > i; i++)if (jQuery.contains(this, targets[i]))return !0
                })
            }, closest: function (selectors, context) {
                for (var cur, i = 0, l = this.length, matched = [], pos = rneedsContext.test(selectors) || "string" != typeof selectors ? jQuery(selectors, context || this.context) : 0; l > i; i++)for (cur = this[i]; cur && cur !== context; cur = cur.parentNode)if (cur.nodeType < 11 && (pos ? pos.index(cur) > -1 : 1 === cur.nodeType && jQuery.find.matchesSelector(cur, selectors))) {
                    matched.push(cur);
                    break
                }
                return this.pushStack(matched.length > 1 ? jQuery.unique(matched) : matched)
            }, index: function (elem) {
                return elem ? "string" == typeof elem ? indexOf.call(jQuery(elem), this[0]) : indexOf.call(this, elem.jquery ? elem[0] : elem) : this[0] && this[0].parentNode ? this.first().prevAll().length : -1
            }, add: function (selector, context) {
                return this.pushStack(jQuery.unique(jQuery.merge(this.get(), jQuery(selector, context))))
            }, addBack: function (selector) {
                return this.add(null == selector ? this.prevObject : this.prevObject.filter(selector))
            }
        }), jQuery.each({
            parent: function (elem) {
                var parent = elem.parentNode;
                return parent && 11 !== parent.nodeType ? parent : null
            }, parents: function (elem) {
                return jQuery.dir(elem, "parentNode")
            }, parentsUntil: function (elem, i, until) {
                return jQuery.dir(elem, "parentNode", until)
            }, next: function (elem) {
                return sibling(elem, "nextSibling")
            }, prev: function (elem) {
                return sibling(elem, "previousSibling")
            }, nextAll: function (elem) {
                return jQuery.dir(elem, "nextSibling")
            }, prevAll: function (elem) {
                return jQuery.dir(elem, "previousSibling")
            }, nextUntil: function (elem, i, until) {
                return jQuery.dir(elem, "nextSibling", until)
            }, prevUntil: function (elem, i, until) {
                return jQuery.dir(elem, "previousSibling", until)
            }, siblings: function (elem) {
                return jQuery.sibling((elem.parentNode || {}).firstChild, elem)
            }, children: function (elem) {
                return jQuery.sibling(elem.firstChild)
            }, contents: function (elem) {
                return elem.contentDocument || jQuery.merge([], elem.childNodes)
            }
        }, function (name, fn) {
            jQuery.fn[name] = function (until, selector) {
                var matched = jQuery.map(this, fn, until);
                return "Until" !== name.slice(-5) && (selector = until), selector && "string" == typeof selector && (matched = jQuery.filter(selector, matched)), this.length > 1 && (guaranteedUnique[name] || jQuery.unique(matched), rparentsprev.test(name) && matched.reverse()), this.pushStack(matched)
            }
        });
        var rnotwhite = /\S+/g, optionsCache = {};
        jQuery.Callbacks = function (options) {
            options = "string" == typeof options ? optionsCache[options] || createOptions(options) : jQuery.extend({}, options);
            var memory, fired, firing, firingStart, firingLength, firingIndex, list = [], stack = !options.once && [], fire = function (data) {
                for (memory = options.memory && data, fired = !0, firingIndex = firingStart || 0, firingStart = 0, firingLength = list.length, firing = !0; list && firingLength > firingIndex; firingIndex++)if (list[firingIndex].apply(data[0], data[1]) === !1 && options.stopOnFalse) {
                    memory = !1;
                    break
                }
                firing = !1, list && (stack ? stack.length && fire(stack.shift()) : memory ? list = [] : self.disable())
            }, self = {
                add: function () {
                    if (list) {
                        var start = list.length;
                        !function add(args) {
                            jQuery.each(args, function (_, arg) {
                                var type = jQuery.type(arg);
                                "function" === type ? options.unique && self.has(arg) || list.push(arg) : arg && arg.length && "string" !== type && add(arg)
                            })
                        }(arguments), firing ? firingLength = list.length : memory && (firingStart = start, fire(memory))
                    }
                    return this
                }, remove: function () {
                    return list && jQuery.each(arguments, function (_, arg) {
                        for (var index; (index = jQuery.inArray(arg, list, index)) > -1;)list.splice(index, 1), firing && (firingLength >= index && firingLength--, firingIndex >= index && firingIndex--)
                    }), this
                }, has: function (fn) {
                    return fn ? jQuery.inArray(fn, list) > -1 : !(!list || !list.length)
                }, empty: function () {
                    return list = [], firingLength = 0, this
                }, disable: function () {
                    return list = stack = memory = void 0, this
                }, disabled: function () {
                    return !list
                }, lock: function () {
                    return stack = void 0, memory || self.disable(), this
                }, locked: function () {
                    return !stack
                }, fireWith: function (context, args) {
                    return !list || fired && !stack || (args = args || [], args = [context, args.slice ? args.slice() : args], firing ? stack.push(args) : fire(args)), this
                }, fire: function () {
                    return self.fireWith(this, arguments), this
                }, fired: function () {
                    return !!fired
                }
            };
            return self
        }, jQuery.extend({
            Deferred: function (func) {
                var tuples = [["resolve", "done", jQuery.Callbacks("once memory"), "resolved"], ["reject", "fail", jQuery.Callbacks("once memory"), "rejected"], ["notify", "progress", jQuery.Callbacks("memory")]], state = "pending", promise = {
                    state: function () {
                        return state
                    }, always: function () {
                        return deferred.done(arguments).fail(arguments), this
                    }, then: function () {
                        var fns = arguments;
                        return jQuery.Deferred(function (newDefer) {
                            jQuery.each(tuples, function (i, tuple) {
                                var fn = jQuery.isFunction(fns[i]) && fns[i];
                                deferred[tuple[1]](function () {
                                    var returned = fn && fn.apply(this, arguments);
                                    returned && jQuery.isFunction(returned.promise) ? returned.promise().done(newDefer.resolve).fail(newDefer.reject).progress(newDefer.notify) : newDefer[tuple[0] + "With"](this === promise ? newDefer.promise() : this, fn ? [returned] : arguments)
                                })
                            }), fns = null
                        }).promise()
                    }, promise: function (obj) {
                        return null != obj ? jQuery.extend(obj, promise) : promise
                    }
                }, deferred = {};
                return promise.pipe = promise.then, jQuery.each(tuples, function (i, tuple) {
                    var list = tuple[2], stateString = tuple[3];
                    promise[tuple[1]] = list.add, stateString && list.add(function () {
                        state = stateString
                    }, tuples[1 ^ i][2].disable, tuples[2][2].lock), deferred[tuple[0]] = function () {
                        return deferred[tuple[0] + "With"](this === deferred ? promise : this, arguments), this
                    }, deferred[tuple[0] + "With"] = list.fireWith
                }), promise.promise(deferred), func && func.call(deferred, deferred), deferred
            }, when: function (subordinate) {
                var progressValues, progressContexts, resolveContexts, i = 0, resolveValues = slice.call(arguments), length = resolveValues.length, remaining = 1 !== length || subordinate && jQuery.isFunction(subordinate.promise) ? length : 0, deferred = 1 === remaining ? subordinate : jQuery.Deferred(), updateFunc = function (i, contexts, values) {
                    return function (value) {
                        contexts[i] = this, values[i] = arguments.length > 1 ? slice.call(arguments) : value, values === progressValues ? deferred.notifyWith(contexts, values) : --remaining || deferred.resolveWith(contexts, values)
                    }
                };
                if (length > 1)for (progressValues = new Array(length), progressContexts = new Array(length), resolveContexts = new Array(length); length > i; i++)resolveValues[i] && jQuery.isFunction(resolveValues[i].promise) ? resolveValues[i].promise().done(updateFunc(i, resolveContexts, resolveValues)).fail(deferred.reject).progress(updateFunc(i, progressContexts, progressValues)) : --remaining;
                return remaining || deferred.resolveWith(resolveContexts, resolveValues), deferred.promise()
            }
        });
        var readyList;
        jQuery.fn.ready = function (fn) {
            return jQuery.ready.promise().done(fn), this
        }, jQuery.extend({
            isReady: !1, readyWait: 1, holdReady: function (hold) {
                hold ? jQuery.readyWait++ : jQuery.ready(!0)
            }, ready: function (wait) {
                (wait === !0 ? --jQuery.readyWait : jQuery.isReady) || (jQuery.isReady = !0, wait !== !0 && --jQuery.readyWait > 0 || (readyList.resolveWith(document, [jQuery]), jQuery.fn.triggerHandler && (jQuery(document).triggerHandler("ready"), jQuery(document).off("ready"))))
            }
        }), jQuery.ready.promise = function (obj) {
            return readyList || (readyList = jQuery.Deferred(), "complete" === document.readyState ? setTimeout(jQuery.ready) : (document.addEventListener("DOMContentLoaded", completed, !1), window.addEventListener("load", completed, !1))), readyList.promise(obj)
        }, jQuery.ready.promise();
        var access = jQuery.access = function (elems, fn, key, value, chainable, emptyGet, raw) {
            var i = 0, len = elems.length, bulk = null == key;
            if ("object" === jQuery.type(key)) {
                chainable = !0;
                for (i in key)jQuery.access(elems, fn, i, key[i], !0, emptyGet, raw)
            } else if (void 0 !== value && (chainable = !0, jQuery.isFunction(value) || (raw = !0), bulk && (raw ? (fn.call(elems, value), fn = null) : (bulk = fn, fn = function (elem, key, value) {
                    return bulk.call(jQuery(elem), value)
                })), fn))for (; len > i; i++)fn(elems[i], key, raw ? value : value.call(elems[i], i, fn(elems[i], key)));
            return chainable ? elems : bulk ? fn.call(elems) : len ? fn(elems[0], key) : emptyGet
        };
        jQuery.acceptData = function (owner) {
            return 1 === owner.nodeType || 9 === owner.nodeType || !+owner.nodeType
        }, Data.uid = 1, Data.accepts = jQuery.acceptData, Data.prototype = {
            key: function (owner) {
                if (!Data.accepts(owner))return 0;
                var descriptor = {}, unlock = owner[this.expando];
                if (!unlock) {
                    unlock = Data.uid++;
                    try {
                        descriptor[this.expando] = {value: unlock}, Object.defineProperties(owner, descriptor)
                    } catch (e) {
                        descriptor[this.expando] = unlock, jQuery.extend(owner, descriptor)
                    }
                }
                return this.cache[unlock] || (this.cache[unlock] = {}), unlock
            }, set: function (owner, data, value) {
                var prop, unlock = this.key(owner), cache = this.cache[unlock];
                if ("string" == typeof data)cache[data] = value; else if (jQuery.isEmptyObject(cache))jQuery.extend(this.cache[unlock], data); else for (prop in data)cache[prop] = data[prop];
                return cache
            }, get: function (owner, key) {
                var cache = this.cache[this.key(owner)];
                return void 0 === key ? cache : cache[key]
            }, access: function (owner, key, value) {
                var stored;
                return void 0 === key || key && "string" == typeof key && void 0 === value ? (stored = this.get(owner, key), void 0 !== stored ? stored : this.get(owner, jQuery.camelCase(key))) : (this.set(owner, key, value), void 0 !== value ? value : key)
            }, remove: function (owner, key) {
                var i, name, camel, unlock = this.key(owner), cache = this.cache[unlock];
                if (void 0 === key)this.cache[unlock] = {}; else {
                    jQuery.isArray(key) ? name = key.concat(key.map(jQuery.camelCase)) : (camel = jQuery.camelCase(key), key in cache ? name = [key, camel] : (name = camel, name = name in cache ? [name] : name.match(rnotwhite) || [])), i = name.length;
                    for (; i--;)delete cache[name[i]]
                }
            }, hasData: function (owner) {
                return !jQuery.isEmptyObject(this.cache[owner[this.expando]] || {})
            }, discard: function (owner) {
                owner[this.expando] && delete this.cache[owner[this.expando]]
            }
        };
        var data_priv = new Data, data_user = new Data, rbrace = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/, rmultiDash = /([A-Z])/g;
        jQuery.extend({
            hasData: function (elem) {
                return data_user.hasData(elem) || data_priv.hasData(elem)
            }, data: function (elem, name, data) {
                return data_user.access(elem, name, data)
            }, removeData: function (elem, name) {
                data_user.remove(elem, name)
            }, _data: function (elem, name, data) {
                return data_priv.access(elem, name, data)
            }, _removeData: function (elem, name) {
                data_priv.remove(elem, name)
            }
        }), jQuery.fn.extend({
            data: function (key, value) {
                var i, name, data, elem = this[0], attrs = elem && elem.attributes;
                if (void 0 === key) {
                    if (this.length && (data = data_user.get(elem), 1 === elem.nodeType && !data_priv.get(elem, "hasDataAttrs"))) {
                        for (i = attrs.length; i--;)attrs[i] && (name = attrs[i].name, 0 === name.indexOf("data-") && (name = jQuery.camelCase(name.slice(5)), dataAttr(elem, name, data[name])));
                        data_priv.set(elem, "hasDataAttrs", !0)
                    }
                    return data
                }
                return "object" == typeof key ? this.each(function () {
                    data_user.set(this, key)
                }) : access(this, function (value) {
                    var data, camelKey = jQuery.camelCase(key);
                    if (elem && void 0 === value) {
                        if (data = data_user.get(elem, key), void 0 !== data)return data;
                        if (data = data_user.get(elem, camelKey), void 0 !== data)return data;
                        if (data = dataAttr(elem, camelKey, void 0), void 0 !== data)return data
                    } else this.each(function () {
                        var data = data_user.get(this, camelKey);
                        data_user.set(this, camelKey, value), -1 !== key.indexOf("-") && void 0 !== data && data_user.set(this, key, value)
                    })
                }, null, value, arguments.length > 1, null, !0)
            }, removeData: function (key) {
                return this.each(function () {
                    data_user.remove(this, key)
                })
            }
        }), jQuery.extend({
            queue: function (elem, type, data) {
                var queue;
                return elem ? (type = (type || "fx") + "queue", queue = data_priv.get(elem, type), data && (!queue || jQuery.isArray(data) ? queue = data_priv.access(elem, type, jQuery.makeArray(data)) : queue.push(data)), queue || []) : void 0
            }, dequeue: function (elem, type) {
                type = type || "fx";
                var queue = jQuery.queue(elem, type), startLength = queue.length, fn = queue.shift(), hooks = jQuery._queueHooks(elem, type), next = function () {
                    jQuery.dequeue(elem, type)
                };
                "inprogress" === fn && (fn = queue.shift(), startLength--), fn && ("fx" === type && queue.unshift("inprogress"), delete hooks.stop, fn.call(elem, next, hooks)), !startLength && hooks && hooks.empty.fire()
            }, _queueHooks: function (elem, type) {
                var key = type + "queueHooks";
                return data_priv.get(elem, key) || data_priv.access(elem, key, {
                        empty: jQuery.Callbacks("once memory").add(function () {
                            data_priv.remove(elem, [type + "queue", key])
                        })
                    })
            }
        }), jQuery.fn.extend({
            queue: function (type, data) {
                var setter = 2;
                return "string" != typeof type && (data = type, type = "fx", setter--), arguments.length < setter ? jQuery.queue(this[0], type) : void 0 === data ? this : this.each(function () {
                    var queue = jQuery.queue(this, type, data);
                    jQuery._queueHooks(this, type), "fx" === type && "inprogress" !== queue[0] && jQuery.dequeue(this, type)
                })
            }, dequeue: function (type) {
                return this.each(function () {
                    jQuery.dequeue(this, type)
                })
            }, clearQueue: function (type) {
                return this.queue(type || "fx", [])
            }, promise: function (type, obj) {
                var tmp, count = 1, defer = jQuery.Deferred(), elements = this, i = this.length, resolve = function () {
                    --count || defer.resolveWith(elements, [elements])
                };
                for ("string" != typeof type && (obj = type, type = void 0), type = type || "fx"; i--;)tmp = data_priv.get(elements[i], type + "queueHooks"), tmp && tmp.empty && (count++, tmp.empty.add(resolve));
                return resolve(), defer.promise(obj)
            }
        });
        var pnum = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source, cssExpand = ["Top", "Right", "Bottom", "Left"], isHidden = function (elem, el) {
            return elem = el || elem, "none" === jQuery.css(elem, "display") || !jQuery.contains(elem.ownerDocument, elem)
        }, rcheckableType = /^(?:checkbox|radio)$/i;
        !function () {
            var fragment = document.createDocumentFragment(), div = fragment.appendChild(document.createElement("div")), input = document.createElement("input");
            input.setAttribute("type", "radio"), input.setAttribute("checked", "checked"), input.setAttribute("name", "t"), div.appendChild(input), support.checkClone = div.cloneNode(!0).cloneNode(!0).lastChild.checked, div.innerHTML = "<textarea>x</textarea>", support.noCloneChecked = !!div.cloneNode(!0).lastChild.defaultValue
        }();
        var strundefined = "undefined";
        support.focusinBubbles = "onfocusin" in window;
        var rkeyEvent = /^key/, rmouseEvent = /^(?:mouse|pointer|contextmenu)|click/, rfocusMorph = /^(?:focusinfocus|focusoutblur)$/, rtypenamespace = /^([^.]*)(?:\.(.+)|)$/;
        jQuery.event = {
            global: {},
            add: function (elem, types, handler, data, selector) {
                var handleObjIn, eventHandle, tmp, events, t, handleObj, special, handlers, type, namespaces, origType, elemData = data_priv.get(elem);
                if (elemData)for (handler.handler && (handleObjIn = handler, handler = handleObjIn.handler, selector = handleObjIn.selector), handler.guid || (handler.guid = jQuery.guid++), (events = elemData.events) || (events = elemData.events = {}), (eventHandle = elemData.handle) || (eventHandle = elemData.handle = function (e) {
                    return typeof jQuery !== strundefined && jQuery.event.triggered !== e.type ? jQuery.event.dispatch.apply(elem, arguments) : void 0
                }), types = (types || "").match(rnotwhite) || [""], t = types.length; t--;)tmp = rtypenamespace.exec(types[t]) || [], type = origType = tmp[1], namespaces = (tmp[2] || "").split(".").sort(), type && (special = jQuery.event.special[type] || {}, type = (selector ? special.delegateType : special.bindType) || type, special = jQuery.event.special[type] || {}, handleObj = jQuery.extend({
                    type: type,
                    origType: origType,
                    data: data,
                    handler: handler,
                    guid: handler.guid,
                    selector: selector,
                    needsContext: selector && jQuery.expr.match.needsContext.test(selector),
                    namespace: namespaces.join(".")
                }, handleObjIn), (handlers = events[type]) || (handlers = events[type] = [], handlers.delegateCount = 0, special.setup && special.setup.call(elem, data, namespaces, eventHandle) !== !1 || elem.addEventListener && elem.addEventListener(type, eventHandle, !1)), special.add && (special.add.call(elem, handleObj), handleObj.handler.guid || (handleObj.handler.guid = handler.guid)), selector ? handlers.splice(handlers.delegateCount++, 0, handleObj) : handlers.push(handleObj), jQuery.event.global[type] = !0)
            },
            remove: function (elem, types, handler, selector, mappedTypes) {
                var j, origCount, tmp, events, t, handleObj, special, handlers, type, namespaces, origType, elemData = data_priv.hasData(elem) && data_priv.get(elem);
                if (elemData && (events = elemData.events)) {
                    for (types = (types || "").match(rnotwhite) || [""], t = types.length; t--;)if (tmp = rtypenamespace.exec(types[t]) || [], type = origType = tmp[1], namespaces = (tmp[2] || "").split(".").sort(), type) {
                        for (special = jQuery.event.special[type] || {}, type = (selector ? special.delegateType : special.bindType) || type, handlers = events[type] || [], tmp = tmp[2] && new RegExp("(^|\\.)" + namespaces.join("\\.(?:.*\\.|)") + "(\\.|$)"), origCount = j = handlers.length; j--;)handleObj = handlers[j], !mappedTypes && origType !== handleObj.origType || handler && handler.guid !== handleObj.guid || tmp && !tmp.test(handleObj.namespace) || selector && selector !== handleObj.selector && ("**" !== selector || !handleObj.selector) || (handlers.splice(j, 1), handleObj.selector && handlers.delegateCount--, special.remove && special.remove.call(elem, handleObj));
                        origCount && !handlers.length && (special.teardown && special.teardown.call(elem, namespaces, elemData.handle) !== !1 || jQuery.removeEvent(elem, type, elemData.handle), delete events[type])
                    } else for (type in events)jQuery.event.remove(elem, type + types[t], handler, selector, !0);
                    jQuery.isEmptyObject(events) && (delete elemData.handle, data_priv.remove(elem, "events"))
                }
            },
            trigger: function (event, data, elem, onlyHandlers) {
                var i, cur, tmp, bubbleType, ontype, handle, special, eventPath = [elem || document], type = hasOwn.call(event, "type") ? event.type : event, namespaces = hasOwn.call(event, "namespace") ? event.namespace.split(".") : [];
                if (cur = tmp = elem = elem || document, 3 !== elem.nodeType && 8 !== elem.nodeType && !rfocusMorph.test(type + jQuery.event.triggered) && (type.indexOf(".") >= 0 && (namespaces = type.split("."), type = namespaces.shift(), namespaces.sort()), ontype = type.indexOf(":") < 0 && "on" + type, event = event[jQuery.expando] ? event : new jQuery.Event(type, "object" == typeof event && event), event.isTrigger = onlyHandlers ? 2 : 3, event.namespace = namespaces.join("."), event.namespace_re = event.namespace ? new RegExp("(^|\\.)" + namespaces.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, event.result = void 0, event.target || (event.target = elem), data = null == data ? [event] : jQuery.makeArray(data, [event]), special = jQuery.event.special[type] || {}, onlyHandlers || !special.trigger || special.trigger.apply(elem, data) !== !1)) {
                    if (!onlyHandlers && !special.noBubble && !jQuery.isWindow(elem)) {
                        for (bubbleType = special.delegateType || type, rfocusMorph.test(bubbleType + type) || (cur = cur.parentNode); cur; cur = cur.parentNode)eventPath.push(cur), tmp = cur;
                        tmp === (elem.ownerDocument || document) && eventPath.push(tmp.defaultView || tmp.parentWindow || window)
                    }
                    for (i = 0; (cur = eventPath[i++]) && !event.isPropagationStopped();)event.type = i > 1 ? bubbleType : special.bindType || type, handle = (data_priv.get(cur, "events") || {})[event.type] && data_priv.get(cur, "handle"), handle && handle.apply(cur, data), handle = ontype && cur[ontype], handle && handle.apply && jQuery.acceptData(cur) && (event.result = handle.apply(cur, data), event.result === !1 && event.preventDefault());
                    return event.type = type, onlyHandlers || event.isDefaultPrevented() || special._default && special._default.apply(eventPath.pop(), data) !== !1 || !jQuery.acceptData(elem) || ontype && jQuery.isFunction(elem[type]) && !jQuery.isWindow(elem) && (tmp = elem[ontype], tmp && (elem[ontype] = null), jQuery.event.triggered = type, elem[type](), jQuery.event.triggered = void 0, tmp && (elem[ontype] = tmp)), event.result
                }
            },
            dispatch: function (event) {
                event = jQuery.event.fix(event);
                var i, j, ret, matched, handleObj, handlerQueue = [], args = slice.call(arguments), handlers = (data_priv.get(this, "events") || {})[event.type] || [], special = jQuery.event.special[event.type] || {};
                if (args[0] = event, event.delegateTarget = this, !special.preDispatch || special.preDispatch.call(this, event) !== !1) {
                    for (handlerQueue = jQuery.event.handlers.call(this, event, handlers), i = 0; (matched = handlerQueue[i++]) && !event.isPropagationStopped();)for (event.currentTarget = matched.elem, j = 0; (handleObj = matched.handlers[j++]) && !event.isImmediatePropagationStopped();)(!event.namespace_re || event.namespace_re.test(handleObj.namespace)) && (event.handleObj = handleObj, event.data = handleObj.data, ret = ((jQuery.event.special[handleObj.origType] || {}).handle || handleObj.handler).apply(matched.elem, args), void 0 !== ret && (event.result = ret) === !1 && (event.preventDefault(), event.stopPropagation()));
                    return special.postDispatch && special.postDispatch.call(this, event), event.result
                }
            },
            handlers: function (event, handlers) {
                var i, matches, sel, handleObj, handlerQueue = [], delegateCount = handlers.delegateCount, cur = event.target;
                if (delegateCount && cur.nodeType && (!event.button || "click" !== event.type))for (; cur !== this; cur = cur.parentNode || this)if (cur.disabled !== !0 || "click" !== event.type) {
                    for (matches = [], i = 0; delegateCount > i; i++)handleObj = handlers[i], sel = handleObj.selector + " ", void 0 === matches[sel] && (matches[sel] = handleObj.needsContext ? jQuery(sel, this).index(cur) >= 0 : jQuery.find(sel, this, null, [cur]).length), matches[sel] && matches.push(handleObj);
                    matches.length && handlerQueue.push({elem: cur, handlers: matches})
                }
                return delegateCount < handlers.length && handlerQueue.push({
                    elem: this,
                    handlers: handlers.slice(delegateCount)
                }), handlerQueue
            },
            props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
            fixHooks: {},
            keyHooks: {
                props: "char charCode key keyCode".split(" "), filter: function (event, original) {
                    return null == event.which && (event.which = null != original.charCode ? original.charCode : original.keyCode), event
                }
            },
            mouseHooks: {
                props: "button buttons clientX clientY offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
                filter: function (event, original) {
                    var eventDoc, doc, body, button = original.button;
                    return null == event.pageX && null != original.clientX && (eventDoc = event.target.ownerDocument || document, doc = eventDoc.documentElement, body = eventDoc.body, event.pageX = original.clientX + (doc && doc.scrollLeft || body && body.scrollLeft || 0) - (doc && doc.clientLeft || body && body.clientLeft || 0), event.pageY = original.clientY + (doc && doc.scrollTop || body && body.scrollTop || 0) - (doc && doc.clientTop || body && body.clientTop || 0)), event.which || void 0 === button || (event.which = 1 & button ? 1 : 2 & button ? 3 : 4 & button ? 2 : 0), event
                }
            },
            fix: function (event) {
                if (event[jQuery.expando])return event;
                var i, prop, copy, type = event.type, originalEvent = event, fixHook = this.fixHooks[type];
                for (fixHook || (this.fixHooks[type] = fixHook = rmouseEvent.test(type) ? this.mouseHooks : rkeyEvent.test(type) ? this.keyHooks : {}), copy = fixHook.props ? this.props.concat(fixHook.props) : this.props, event = new jQuery.Event(originalEvent), i = copy.length; i--;)prop = copy[i], event[prop] = originalEvent[prop];
                return event.target || (event.target = document), 3 === event.target.nodeType && (event.target = event.target.parentNode), fixHook.filter ? fixHook.filter(event, originalEvent) : event
            },
            special: {
                load: {noBubble: !0}, focus: {
                    trigger: function () {
                        return this !== safeActiveElement() && this.focus ? (this.focus(), !1) : void 0
                    }, delegateType: "focusin"
                }, blur: {
                    trigger: function () {
                        return this === safeActiveElement() && this.blur ? (this.blur(), !1) : void 0
                    }, delegateType: "focusout"
                }, click: {
                    trigger: function () {
                        return "checkbox" === this.type && this.click && jQuery.nodeName(this, "input") ? (this.click(), !1) : void 0
                    }, _default: function (event) {
                        return jQuery.nodeName(event.target, "a")
                    }
                }, beforeunload: {
                    postDispatch: function (event) {
                        void 0 !== event.result && event.originalEvent && (event.originalEvent.returnValue = event.result)
                    }
                }
            },
            simulate: function (type, elem, event, bubble) {
                var e = jQuery.extend(new jQuery.Event, event, {type: type, isSimulated: !0, originalEvent: {}});
                bubble ? jQuery.event.trigger(e, null, elem) : jQuery.event.dispatch.call(elem, e), e.isDefaultPrevented() && event.preventDefault()
            }
        }, jQuery.removeEvent = function (elem, type, handle) {
            elem.removeEventListener && elem.removeEventListener(type, handle, !1)
        }, jQuery.Event = function (src, props) {
            return this instanceof jQuery.Event ? (src && src.type ? (this.originalEvent = src, this.type = src.type, this.isDefaultPrevented = src.defaultPrevented || void 0 === src.defaultPrevented && src.returnValue === !1 ? returnTrue : returnFalse) : this.type = src, props && jQuery.extend(this, props), this.timeStamp = src && src.timeStamp || jQuery.now(), void(this[jQuery.expando] = !0)) : new jQuery.Event(src, props)
        }, jQuery.Event.prototype = {
            isDefaultPrevented: returnFalse,
            isPropagationStopped: returnFalse,
            isImmediatePropagationStopped: returnFalse,
            preventDefault: function () {
                var e = this.originalEvent;
                this.isDefaultPrevented = returnTrue, e && e.preventDefault && e.preventDefault()
            },
            stopPropagation: function () {
                var e = this.originalEvent;
                this.isPropagationStopped = returnTrue, e && e.stopPropagation && e.stopPropagation()
            },
            stopImmediatePropagation: function () {
                var e = this.originalEvent;
                this.isImmediatePropagationStopped = returnTrue, e && e.stopImmediatePropagation && e.stopImmediatePropagation(), this.stopPropagation()
            }
        }, jQuery.each({
            mouseenter: "mouseover",
            mouseleave: "mouseout",
            pointerenter: "pointerover",
            pointerleave: "pointerout"
        }, function (orig, fix) {
            jQuery.event.special[orig] = {
                delegateType: fix, bindType: fix, handle: function (event) {
                    var ret, target = this, related = event.relatedTarget, handleObj = event.handleObj;
                    return (!related || related !== target && !jQuery.contains(target, related)) && (event.type = handleObj.origType, ret = handleObj.handler.apply(this, arguments), event.type = fix), ret
                }
            }
        }), support.focusinBubbles || jQuery.each({focus: "focusin", blur: "focusout"}, function (orig, fix) {
            var handler = function (event) {
                jQuery.event.simulate(fix, event.target, jQuery.event.fix(event), !0)
            };
            jQuery.event.special[fix] = {
                setup: function () {
                    var doc = this.ownerDocument || this, attaches = data_priv.access(doc, fix);
                    attaches || doc.addEventListener(orig, handler, !0), data_priv.access(doc, fix, (attaches || 0) + 1)
                }, teardown: function () {
                    var doc = this.ownerDocument || this, attaches = data_priv.access(doc, fix) - 1;
                    attaches ? data_priv.access(doc, fix, attaches) : (doc.removeEventListener(orig, handler, !0), data_priv.remove(doc, fix))
                }
            }
        }), jQuery.fn.extend({
            on: function (types, selector, data, fn, one) {
                var origFn, type;
                if ("object" == typeof types) {
                    "string" != typeof selector && (data = data || selector, selector = void 0);
                    for (type in types)this.on(type, selector, data, types[type], one);
                    return this
                }
                if (null == data && null == fn ? (fn = selector, data = selector = void 0) : null == fn && ("string" == typeof selector ? (fn = data, data = void 0) : (fn = data, data = selector, selector = void 0)), fn === !1)fn = returnFalse; else if (!fn)return this;
                return 1 === one && (origFn = fn, fn = function (event) {
                    return jQuery().off(event), origFn.apply(this, arguments)
                }, fn.guid = origFn.guid || (origFn.guid = jQuery.guid++)), this.each(function () {
                    jQuery.event.add(this, types, fn, data, selector)
                })
            }, one: function (types, selector, data, fn) {
                return this.on(types, selector, data, fn, 1)
            }, off: function (types, selector, fn) {
                var handleObj, type;
                if (types && types.preventDefault && types.handleObj)return handleObj = types.handleObj, jQuery(types.delegateTarget).off(handleObj.namespace ? handleObj.origType + "." + handleObj.namespace : handleObj.origType, handleObj.selector, handleObj.handler), this;
                if ("object" == typeof types) {
                    for (type in types)this.off(type, selector, types[type]);
                    return this
                }
                return (selector === !1 || "function" == typeof selector) && (fn = selector, selector = void 0), fn === !1 && (fn = returnFalse), this.each(function () {
                    jQuery.event.remove(this, types, fn, selector)
                })
            }, trigger: function (type, data) {
                return this.each(function () {
                    jQuery.event.trigger(type, data, this)
                })
            }, triggerHandler: function (type, data) {
                var elem = this[0];
                return elem ? jQuery.event.trigger(type, data, elem, !0) : void 0
            }
        });
        var rxhtmlTag = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi, rtagName = /<([\w:]+)/, rhtml = /<|&#?\w+;/, rnoInnerhtml = /<(?:script|style|link)/i, rchecked = /checked\s*(?:[^=]|=\s*.checked.)/i, rscriptType = /^$|\/(?:java|ecma)script/i, rscriptTypeMasked = /^true\/(.*)/, rcleanScript = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g, wrapMap = {
            option: [1, "<select multiple='multiple'>", "</select>"],
            thead: [1, "<table>", "</table>"],
            col: [2, "<table><colgroup>", "</colgroup></table>"],
            tr: [2, "<table><tbody>", "</tbody></table>"],
            td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
            _default: [0, "", ""]
        };
        wrapMap.optgroup = wrapMap.option, wrapMap.tbody = wrapMap.tfoot = wrapMap.colgroup = wrapMap.caption = wrapMap.thead, wrapMap.th = wrapMap.td, jQuery.extend({
            clone: function (elem, dataAndEvents, deepDataAndEvents) {
                var i, l, srcElements, destElements, clone = elem.cloneNode(!0), inPage = jQuery.contains(elem.ownerDocument, elem);
                if (!(support.noCloneChecked || 1 !== elem.nodeType && 11 !== elem.nodeType || jQuery.isXMLDoc(elem)))for (destElements = getAll(clone),
                                                                                                                               srcElements = getAll(elem), i = 0, l = srcElements.length; l > i; i++)fixInput(srcElements[i], destElements[i]);
                if (dataAndEvents)if (deepDataAndEvents)for (srcElements = srcElements || getAll(elem), destElements = destElements || getAll(clone), i = 0, l = srcElements.length; l > i; i++)cloneCopyEvent(srcElements[i], destElements[i]); else cloneCopyEvent(elem, clone);
                return destElements = getAll(clone, "script"), destElements.length > 0 && setGlobalEval(destElements, !inPage && getAll(elem, "script")), clone
            }, buildFragment: function (elems, context, scripts, selection) {
                for (var elem, tmp, tag, wrap, contains, j, fragment = context.createDocumentFragment(), nodes = [], i = 0, l = elems.length; l > i; i++)if (elem = elems[i], elem || 0 === elem)if ("object" === jQuery.type(elem))jQuery.merge(nodes, elem.nodeType ? [elem] : elem); else if (rhtml.test(elem)) {
                    for (tmp = tmp || fragment.appendChild(context.createElement("div")), tag = (rtagName.exec(elem) || ["", ""])[1].toLowerCase(), wrap = wrapMap[tag] || wrapMap._default, tmp.innerHTML = wrap[1] + elem.replace(rxhtmlTag, "<$1></$2>") + wrap[2], j = wrap[0]; j--;)tmp = tmp.lastChild;
                    jQuery.merge(nodes, tmp.childNodes), tmp = fragment.firstChild, tmp.textContent = ""
                } else nodes.push(context.createTextNode(elem));
                for (fragment.textContent = "", i = 0; elem = nodes[i++];)if ((!selection || -1 === jQuery.inArray(elem, selection)) && (contains = jQuery.contains(elem.ownerDocument, elem), tmp = getAll(fragment.appendChild(elem), "script"), contains && setGlobalEval(tmp), scripts))for (j = 0; elem = tmp[j++];)rscriptType.test(elem.type || "") && scripts.push(elem);
                return fragment
            }, cleanData: function (elems) {
                for (var data, elem, type, key, special = jQuery.event.special, i = 0; void 0 !== (elem = elems[i]); i++) {
                    if (jQuery.acceptData(elem) && (key = elem[data_priv.expando], key && (data = data_priv.cache[key]))) {
                        if (data.events)for (type in data.events)special[type] ? jQuery.event.remove(elem, type) : jQuery.removeEvent(elem, type, data.handle);
                        data_priv.cache[key] && delete data_priv.cache[key]
                    }
                    delete data_user.cache[elem[data_user.expando]]
                }
            }
        }), jQuery.fn.extend({
            text: function (value) {
                return access(this, function (value) {
                    return void 0 === value ? jQuery.text(this) : this.empty().each(function () {
                        (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) && (this.textContent = value)
                    })
                }, null, value, arguments.length)
            }, append: function () {
                return this.domManip(arguments, function (elem) {
                    if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                        var target = manipulationTarget(this, elem);
                        target.appendChild(elem)
                    }
                })
            }, prepend: function () {
                return this.domManip(arguments, function (elem) {
                    if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                        var target = manipulationTarget(this, elem);
                        target.insertBefore(elem, target.firstChild)
                    }
                })
            }, before: function () {
                return this.domManip(arguments, function (elem) {
                    this.parentNode && this.parentNode.insertBefore(elem, this)
                })
            }, after: function () {
                return this.domManip(arguments, function (elem) {
                    this.parentNode && this.parentNode.insertBefore(elem, this.nextSibling)
                })
            }, remove: function (selector, keepData) {
                for (var elem, elems = selector ? jQuery.filter(selector, this) : this, i = 0; null != (elem = elems[i]); i++)keepData || 1 !== elem.nodeType || jQuery.cleanData(getAll(elem)), elem.parentNode && (keepData && jQuery.contains(elem.ownerDocument, elem) && setGlobalEval(getAll(elem, "script")), elem.parentNode.removeChild(elem));
                return this
            }, empty: function () {
                for (var elem, i = 0; null != (elem = this[i]); i++)1 === elem.nodeType && (jQuery.cleanData(getAll(elem, !1)), elem.textContent = "");
                return this
            }, clone: function (dataAndEvents, deepDataAndEvents) {
                return dataAndEvents = null == dataAndEvents ? !1 : dataAndEvents, deepDataAndEvents = null == deepDataAndEvents ? dataAndEvents : deepDataAndEvents, this.map(function () {
                    return jQuery.clone(this, dataAndEvents, deepDataAndEvents)
                })
            }, html: function (value) {
                return access(this, function (value) {
                    var elem = this[0] || {}, i = 0, l = this.length;
                    if (void 0 === value && 1 === elem.nodeType)return elem.innerHTML;
                    if ("string" == typeof value && !rnoInnerhtml.test(value) && !wrapMap[(rtagName.exec(value) || ["", ""])[1].toLowerCase()]) {
                        value = value.replace(rxhtmlTag, "<$1></$2>");
                        try {
                            for (; l > i; i++)elem = this[i] || {}, 1 === elem.nodeType && (jQuery.cleanData(getAll(elem, !1)), elem.innerHTML = value);
                            elem = 0
                        } catch (e) {
                        }
                    }
                    elem && this.empty().append(value)
                }, null, value, arguments.length)
            }, replaceWith: function () {
                var arg = arguments[0];
                return this.domManip(arguments, function (elem) {
                    arg = this.parentNode, jQuery.cleanData(getAll(this)), arg && arg.replaceChild(elem, this)
                }), arg && (arg.length || arg.nodeType) ? this : this.remove()
            }, detach: function (selector) {
                return this.remove(selector, !0)
            }, domManip: function (args, callback) {
                args = concat.apply([], args);
                var fragment, first, scripts, hasScripts, node, doc, i = 0, l = this.length, set = this, iNoClone = l - 1, value = args[0], isFunction = jQuery.isFunction(value);
                if (isFunction || l > 1 && "string" == typeof value && !support.checkClone && rchecked.test(value))return this.each(function (index) {
                    var self = set.eq(index);
                    isFunction && (args[0] = value.call(this, index, self.html())), self.domManip(args, callback)
                });
                if (l && (fragment = jQuery.buildFragment(args, this[0].ownerDocument, !1, this), first = fragment.firstChild, 1 === fragment.childNodes.length && (fragment = first), first)) {
                    for (scripts = jQuery.map(getAll(fragment, "script"), disableScript), hasScripts = scripts.length; l > i; i++)node = fragment, i !== iNoClone && (node = jQuery.clone(node, !0, !0), hasScripts && jQuery.merge(scripts, getAll(node, "script"))), callback.call(this[i], node, i);
                    if (hasScripts)for (doc = scripts[scripts.length - 1].ownerDocument, jQuery.map(scripts, restoreScript), i = 0; hasScripts > i; i++)node = scripts[i], rscriptType.test(node.type || "") && !data_priv.access(node, "globalEval") && jQuery.contains(doc, node) && (node.src ? jQuery._evalUrl && jQuery._evalUrl(node.src) : jQuery.globalEval(node.textContent.replace(rcleanScript, "")))
                }
                return this
            }
        }), jQuery.each({
            appendTo: "append",
            prependTo: "prepend",
            insertBefore: "before",
            insertAfter: "after",
            replaceAll: "replaceWith"
        }, function (name, original) {
            jQuery.fn[name] = function (selector) {
                for (var elems, ret = [], insert = jQuery(selector), last = insert.length - 1, i = 0; last >= i; i++)elems = i === last ? this : this.clone(!0), jQuery(insert[i])[original](elems), push.apply(ret, elems.get());
                return this.pushStack(ret)
            }
        });
        var iframe, elemdisplay = {}, rmargin = /^margin/, rnumnonpx = new RegExp("^(" + pnum + ")(?!px)[a-z%]+$", "i"), getStyles = function (elem) {
            return elem.ownerDocument.defaultView.opener ? elem.ownerDocument.defaultView.getComputedStyle(elem, null) : window.getComputedStyle(elem, null)
        };
        !function () {
            function computePixelPositionAndBoxSizingReliable() {
                div.style.cssText = "-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;display:block;margin-top:1%;top:1%;border:1px;padding:1px;width:4px;position:absolute", div.innerHTML = "", docElem.appendChild(container);
                var divStyle = window.getComputedStyle(div, null);
                pixelPositionVal = "1%" !== divStyle.top, boxSizingReliableVal = "4px" === divStyle.width, docElem.removeChild(container)
            }

            var pixelPositionVal, boxSizingReliableVal, docElem = document.documentElement, container = document.createElement("div"), div = document.createElement("div");
            div.style && (div.style.backgroundClip = "content-box", div.cloneNode(!0).style.backgroundClip = "", support.clearCloneStyle = "content-box" === div.style.backgroundClip, container.style.cssText = "border:0;width:0;height:0;top:0;left:-9999px;margin-top:1px;position:absolute", container.appendChild(div), window.getComputedStyle && jQuery.extend(support, {
                pixelPosition: function () {
                    return computePixelPositionAndBoxSizingReliable(), pixelPositionVal
                }, boxSizingReliable: function () {
                    return null == boxSizingReliableVal && computePixelPositionAndBoxSizingReliable(), boxSizingReliableVal
                }, reliableMarginRight: function () {
                    var ret, marginDiv = div.appendChild(document.createElement("div"));
                    return marginDiv.style.cssText = div.style.cssText = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:0", marginDiv.style.marginRight = marginDiv.style.width = "0", div.style.width = "1px", docElem.appendChild(container), ret = !parseFloat(window.getComputedStyle(marginDiv, null).marginRight), docElem.removeChild(container), div.removeChild(marginDiv), ret
                }
            }))
        }(), jQuery.swap = function (elem, options, callback, args) {
            var ret, name, old = {};
            for (name in options)old[name] = elem.style[name], elem.style[name] = options[name];
            ret = callback.apply(elem, args || []);
            for (name in options)elem.style[name] = old[name];
            return ret
        };
        var rdisplayswap = /^(none|table(?!-c[ea]).+)/, rnumsplit = new RegExp("^(" + pnum + ")(.*)$", "i"), rrelNum = new RegExp("^([+-])=(" + pnum + ")", "i"), cssShow = {
            position: "absolute",
            visibility: "hidden",
            display: "block"
        }, cssNormalTransform = {letterSpacing: "0", fontWeight: "400"}, cssPrefixes = ["Webkit", "O", "Moz", "ms"];
        jQuery.extend({
            cssHooks: {
                opacity: {
                    get: function (elem, computed) {
                        if (computed) {
                            var ret = curCSS(elem, "opacity");
                            return "" === ret ? "1" : ret
                        }
                    }
                }
            },
            cssNumber: {
                columnCount: !0,
                fillOpacity: !0,
                flexGrow: !0,
                flexShrink: !0,
                fontWeight: !0,
                lineHeight: !0,
                opacity: !0,
                order: !0,
                orphans: !0,
                widows: !0,
                zIndex: !0,
                zoom: !0
            },
            cssProps: {"float": "cssFloat"},
            style: function (elem, name, value, extra) {
                if (elem && 3 !== elem.nodeType && 8 !== elem.nodeType && elem.style) {
                    var ret, type, hooks, origName = jQuery.camelCase(name), style = elem.style;
                    return name = jQuery.cssProps[origName] || (jQuery.cssProps[origName] = vendorPropName(style, origName)), hooks = jQuery.cssHooks[name] || jQuery.cssHooks[origName], void 0 === value ? hooks && "get" in hooks && void 0 !== (ret = hooks.get(elem, !1, extra)) ? ret : style[name] : (type = typeof value, "string" === type && (ret = rrelNum.exec(value)) && (value = (ret[1] + 1) * ret[2] + parseFloat(jQuery.css(elem, name)), type = "number"), null != value && value === value && ("number" !== type || jQuery.cssNumber[origName] || (value += "px"), support.clearCloneStyle || "" !== value || 0 !== name.indexOf("background") || (style[name] = "inherit"), hooks && "set" in hooks && void 0 === (value = hooks.set(elem, value, extra)) || (style[name] = value)), void 0)
                }
            },
            css: function (elem, name, extra, styles) {
                var val, num, hooks, origName = jQuery.camelCase(name);
                return name = jQuery.cssProps[origName] || (jQuery.cssProps[origName] = vendorPropName(elem.style, origName)), hooks = jQuery.cssHooks[name] || jQuery.cssHooks[origName], hooks && "get" in hooks && (val = hooks.get(elem, !0, extra)), void 0 === val && (val = curCSS(elem, name, styles)), "normal" === val && name in cssNormalTransform && (val = cssNormalTransform[name]), "" === extra || extra ? (num = parseFloat(val), extra === !0 || jQuery.isNumeric(num) ? num || 0 : val) : val
            }
        }), jQuery.each(["height", "width"], function (i, name) {
            jQuery.cssHooks[name] = {
                get: function (elem, computed, extra) {
                    return computed ? rdisplayswap.test(jQuery.css(elem, "display")) && 0 === elem.offsetWidth ? jQuery.swap(elem, cssShow, function () {
                        return getWidthOrHeight(elem, name, extra)
                    }) : getWidthOrHeight(elem, name, extra) : void 0
                }, set: function (elem, value, extra) {
                    var styles = extra && getStyles(elem);
                    return setPositiveNumber(elem, value, extra ? augmentWidthOrHeight(elem, name, extra, "border-box" === jQuery.css(elem, "boxSizing", !1, styles), styles) : 0)
                }
            }
        }), jQuery.cssHooks.marginRight = addGetHookIf(support.reliableMarginRight, function (elem, computed) {
            return computed ? jQuery.swap(elem, {display: "inline-block"}, curCSS, [elem, "marginRight"]) : void 0
        }), jQuery.each({margin: "", padding: "", border: "Width"}, function (prefix, suffix) {
            jQuery.cssHooks[prefix + suffix] = {
                expand: function (value) {
                    for (var i = 0, expanded = {}, parts = "string" == typeof value ? value.split(" ") : [value]; 4 > i; i++)expanded[prefix + cssExpand[i] + suffix] = parts[i] || parts[i - 2] || parts[0];
                    return expanded
                }
            }, rmargin.test(prefix) || (jQuery.cssHooks[prefix + suffix].set = setPositiveNumber)
        }), jQuery.fn.extend({
            css: function (name, value) {
                return access(this, function (elem, name, value) {
                    var styles, len, map = {}, i = 0;
                    if (jQuery.isArray(name)) {
                        for (styles = getStyles(elem), len = name.length; len > i; i++)map[name[i]] = jQuery.css(elem, name[i], !1, styles);
                        return map
                    }
                    return void 0 !== value ? jQuery.style(elem, name, value) : jQuery.css(elem, name)
                }, name, value, arguments.length > 1)
            }, show: function () {
                return showHide(this, !0)
            }, hide: function () {
                return showHide(this)
            }, toggle: function (state) {
                return "boolean" == typeof state ? state ? this.show() : this.hide() : this.each(function () {
                    isHidden(this) ? jQuery(this).show() : jQuery(this).hide()
                })
            }
        }), jQuery.Tween = Tween, Tween.prototype = {
            constructor: Tween,
            init: function (elem, options, prop, end, easing, unit) {
                this.elem = elem, this.prop = prop, this.easing = easing || "swing", this.options = options, this.start = this.now = this.cur(), this.end = end, this.unit = unit || (jQuery.cssNumber[prop] ? "" : "px")
            },
            cur: function () {
                var hooks = Tween.propHooks[this.prop];
                return hooks && hooks.get ? hooks.get(this) : Tween.propHooks._default.get(this)
            },
            run: function (percent) {
                var eased, hooks = Tween.propHooks[this.prop];
                return this.options.duration ? this.pos = eased = jQuery.easing[this.easing](percent, this.options.duration * percent, 0, 1, this.options.duration) : this.pos = eased = percent, this.now = (this.end - this.start) * eased + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), hooks && hooks.set ? hooks.set(this) : Tween.propHooks._default.set(this), this
            }
        }, Tween.prototype.init.prototype = Tween.prototype, Tween.propHooks = {
            _default: {
                get: function (tween) {
                    var result;
                    return null == tween.elem[tween.prop] || tween.elem.style && null != tween.elem.style[tween.prop] ? (result = jQuery.css(tween.elem, tween.prop, ""), result && "auto" !== result ? result : 0) : tween.elem[tween.prop]
                }, set: function (tween) {
                    jQuery.fx.step[tween.prop] ? jQuery.fx.step[tween.prop](tween) : tween.elem.style && (null != tween.elem.style[jQuery.cssProps[tween.prop]] || jQuery.cssHooks[tween.prop]) ? jQuery.style(tween.elem, tween.prop, tween.now + tween.unit) : tween.elem[tween.prop] = tween.now
                }
            }
        }, Tween.propHooks.scrollTop = Tween.propHooks.scrollLeft = {
            set: function (tween) {
                tween.elem.nodeType && tween.elem.parentNode && (tween.elem[tween.prop] = tween.now)
            }
        }, jQuery.easing = {
            linear: function (p) {
                return p
            }, swing: function (p) {
                return .5 - Math.cos(p * Math.PI) / 2
            }
        }, jQuery.fx = Tween.prototype.init, jQuery.fx.step = {};
        var fxNow, timerId, rfxtypes = /^(?:toggle|show|hide)$/, rfxnum = new RegExp("^(?:([+-])=|)(" + pnum + ")([a-z%]*)$", "i"), rrun = /queueHooks$/, animationPrefilters = [defaultPrefilter], tweeners = {
            "*": [function (prop, value) {
                var tween = this.createTween(prop, value), target = tween.cur(), parts = rfxnum.exec(value), unit = parts && parts[3] || (jQuery.cssNumber[prop] ? "" : "px"), start = (jQuery.cssNumber[prop] || "px" !== unit && +target) && rfxnum.exec(jQuery.css(tween.elem, prop)), scale = 1, maxIterations = 20;
                if (start && start[3] !== unit) {
                    unit = unit || start[3], parts = parts || [], start = +target || 1;
                    do scale = scale || ".5", start /= scale, jQuery.style(tween.elem, prop, start + unit); while (scale !== (scale = tween.cur() / target) && 1 !== scale && --maxIterations)
                }
                return parts && (start = tween.start = +start || +target || 0, tween.unit = unit, tween.end = parts[1] ? start + (parts[1] + 1) * parts[2] : +parts[2]), tween
            }]
        };
        jQuery.Animation = jQuery.extend(Animation, {
            tweener: function (props, callback) {
                jQuery.isFunction(props) ? (callback = props, props = ["*"]) : props = props.split(" ");
                for (var prop, index = 0, length = props.length; length > index; index++)prop = props[index], tweeners[prop] = tweeners[prop] || [], tweeners[prop].unshift(callback)
            }, prefilter: function (callback, prepend) {
                prepend ? animationPrefilters.unshift(callback) : animationPrefilters.push(callback)
            }
        }), jQuery.speed = function (speed, easing, fn) {
            var opt = speed && "object" == typeof speed ? jQuery.extend({}, speed) : {
                complete: fn || !fn && easing || jQuery.isFunction(speed) && speed,
                duration: speed,
                easing: fn && easing || easing && !jQuery.isFunction(easing) && easing
            };
            return opt.duration = jQuery.fx.off ? 0 : "number" == typeof opt.duration ? opt.duration : opt.duration in jQuery.fx.speeds ? jQuery.fx.speeds[opt.duration] : jQuery.fx.speeds._default, (null == opt.queue || opt.queue === !0) && (opt.queue = "fx"), opt.old = opt.complete, opt.complete = function () {
                jQuery.isFunction(opt.old) && opt.old.call(this), opt.queue && jQuery.dequeue(this, opt.queue)
            }, opt
        }, jQuery.fn.extend({
            fadeTo: function (speed, to, easing, callback) {
                return this.filter(isHidden).css("opacity", 0).show().end().animate({opacity: to}, speed, easing, callback)
            }, animate: function (prop, speed, easing, callback) {
                var empty = jQuery.isEmptyObject(prop), optall = jQuery.speed(speed, easing, callback), doAnimation = function () {
                    var anim = Animation(this, jQuery.extend({}, prop), optall);
                    (empty || data_priv.get(this, "finish")) && anim.stop(!0)
                };
                return doAnimation.finish = doAnimation, empty || optall.queue === !1 ? this.each(doAnimation) : this.queue(optall.queue, doAnimation)
            }, stop: function (type, clearQueue, gotoEnd) {
                var stopQueue = function (hooks) {
                    var stop = hooks.stop;
                    delete hooks.stop, stop(gotoEnd)
                };
                return "string" != typeof type && (gotoEnd = clearQueue, clearQueue = type, type = void 0), clearQueue && type !== !1 && this.queue(type || "fx", []), this.each(function () {
                    var dequeue = !0, index = null != type && type + "queueHooks", timers = jQuery.timers, data = data_priv.get(this);
                    if (index)data[index] && data[index].stop && stopQueue(data[index]); else for (index in data)data[index] && data[index].stop && rrun.test(index) && stopQueue(data[index]);
                    for (index = timers.length; index--;)timers[index].elem !== this || null != type && timers[index].queue !== type || (timers[index].anim.stop(gotoEnd), dequeue = !1, timers.splice(index, 1));
                    (dequeue || !gotoEnd) && jQuery.dequeue(this, type)
                })
            }, finish: function (type) {
                return type !== !1 && (type = type || "fx"), this.each(function () {
                    var index, data = data_priv.get(this), queue = data[type + "queue"], hooks = data[type + "queueHooks"], timers = jQuery.timers, length = queue ? queue.length : 0;
                    for (data.finish = !0, jQuery.queue(this, type, []), hooks && hooks.stop && hooks.stop.call(this, !0), index = timers.length; index--;)timers[index].elem === this && timers[index].queue === type && (timers[index].anim.stop(!0), timers.splice(index, 1));
                    for (index = 0; length > index; index++)queue[index] && queue[index].finish && queue[index].finish.call(this);
                    delete data.finish
                })
            }
        }), jQuery.each(["toggle", "show", "hide"], function (i, name) {
            var cssFn = jQuery.fn[name];
            jQuery.fn[name] = function (speed, easing, callback) {
                return null == speed || "boolean" == typeof speed ? cssFn.apply(this, arguments) : this.animate(genFx(name, !0), speed, easing, callback)
            }
        }), jQuery.each({
            slideDown: genFx("show"),
            slideUp: genFx("hide"),
            slideToggle: genFx("toggle"),
            fadeIn: {opacity: "show"},
            fadeOut: {opacity: "hide"},
            fadeToggle: {opacity: "toggle"}
        }, function (name, props) {
            jQuery.fn[name] = function (speed, easing, callback) {
                return this.animate(props, speed, easing, callback)
            }
        }), jQuery.timers = [], jQuery.fx.tick = function () {
            var timer, i = 0, timers = jQuery.timers;
            for (fxNow = jQuery.now(); i < timers.length; i++)timer = timers[i], timer() || timers[i] !== timer || timers.splice(i--, 1);
            timers.length || jQuery.fx.stop(), fxNow = void 0
        }, jQuery.fx.timer = function (timer) {
            jQuery.timers.push(timer), timer() ? jQuery.fx.start() : jQuery.timers.pop()
        }, jQuery.fx.interval = 13, jQuery.fx.start = function () {
            timerId || (timerId = setInterval(jQuery.fx.tick, jQuery.fx.interval))
        }, jQuery.fx.stop = function () {
            clearInterval(timerId), timerId = null
        }, jQuery.fx.speeds = {slow: 600, fast: 200, _default: 400}, jQuery.fn.delay = function (time, type) {
            return time = jQuery.fx ? jQuery.fx.speeds[time] || time : time, type = type || "fx", this.queue(type, function (next, hooks) {
                var timeout = setTimeout(next, time);
                hooks.stop = function () {
                    clearTimeout(timeout)
                }
            })
        }, function () {
            var input = document.createElement("input"), select = document.createElement("select"), opt = select.appendChild(document.createElement("option"));
            input.type = "checkbox", support.checkOn = "" !== input.value, support.optSelected = opt.selected, select.disabled = !0, support.optDisabled = !opt.disabled, input = document.createElement("input"), input.value = "t", input.type = "radio", support.radioValue = "t" === input.value
        }();
        var nodeHook, boolHook, attrHandle = jQuery.expr.attrHandle;
        jQuery.fn.extend({
            attr: function (name, value) {
                return access(this, jQuery.attr, name, value, arguments.length > 1)
            }, removeAttr: function (name) {
                return this.each(function () {
                    jQuery.removeAttr(this, name)
                })
            }
        }), jQuery.extend({
            attr: function (elem, name, value) {
                var hooks, ret, nType = elem.nodeType;
                if (elem && 3 !== nType && 8 !== nType && 2 !== nType)return typeof elem.getAttribute === strundefined ? jQuery.prop(elem, name, value) : (1 === nType && jQuery.isXMLDoc(elem) || (name = name.toLowerCase(), hooks = jQuery.attrHooks[name] || (jQuery.expr.match.bool.test(name) ? boolHook : nodeHook)), void 0 === value ? hooks && "get" in hooks && null !== (ret = hooks.get(elem, name)) ? ret : (ret = jQuery.find.attr(elem, name), null == ret ? void 0 : ret) : null !== value ? hooks && "set" in hooks && void 0 !== (ret = hooks.set(elem, value, name)) ? ret : (elem.setAttribute(name, value + ""), value) : void jQuery.removeAttr(elem, name))
            }, removeAttr: function (elem, value) {
                var name, propName, i = 0, attrNames = value && value.match(rnotwhite);
                if (attrNames && 1 === elem.nodeType)for (; name = attrNames[i++];)propName = jQuery.propFix[name] || name, jQuery.expr.match.bool.test(name) && (elem[propName] = !1), elem.removeAttribute(name)
            }, attrHooks: {
                type: {
                    set: function (elem, value) {
                        if (!support.radioValue && "radio" === value && jQuery.nodeName(elem, "input")) {
                            var val = elem.value;
                            return elem.setAttribute("type", value), val && (elem.value = val), value
                        }
                    }
                }
            }
        }), boolHook = {
            set: function (elem, value, name) {
                return value === !1 ? jQuery.removeAttr(elem, name) : elem.setAttribute(name, name), name
            }
        }, jQuery.each(jQuery.expr.match.bool.source.match(/\w+/g), function (i, name) {
            var getter = attrHandle[name] || jQuery.find.attr;
            attrHandle[name] = function (elem, name, isXML) {
                var ret, handle;
                return isXML || (handle = attrHandle[name], attrHandle[name] = ret, ret = null != getter(elem, name, isXML) ? name.toLowerCase() : null, attrHandle[name] = handle), ret
            }
        });
        var rfocusable = /^(?:input|select|textarea|button)$/i;
        jQuery.fn.extend({
            prop: function (name, value) {
                return access(this, jQuery.prop, name, value, arguments.length > 1)
            }, removeProp: function (name) {
                return this.each(function () {
                    delete this[jQuery.propFix[name] || name]
                })
            }
        }), jQuery.extend({
            propFix: {"for": "htmlFor", "class": "className"}, prop: function (elem, name, value) {
                var ret, hooks, notxml, nType = elem.nodeType;
                if (elem && 3 !== nType && 8 !== nType && 2 !== nType)return notxml = 1 !== nType || !jQuery.isXMLDoc(elem), notxml && (name = jQuery.propFix[name] || name, hooks = jQuery.propHooks[name]), void 0 !== value ? hooks && "set" in hooks && void 0 !== (ret = hooks.set(elem, value, name)) ? ret : elem[name] = value : hooks && "get" in hooks && null !== (ret = hooks.get(elem, name)) ? ret : elem[name]
            }, propHooks: {
                tabIndex: {
                    get: function (elem) {
                        return elem.hasAttribute("tabindex") || rfocusable.test(elem.nodeName) || elem.href ? elem.tabIndex : -1
                    }
                }
            }
        }), support.optSelected || (jQuery.propHooks.selected = {
            get: function (elem) {
                var parent = elem.parentNode;
                return parent && parent.parentNode && parent.parentNode.selectedIndex, null
            }
        }), jQuery.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"], function () {
            jQuery.propFix[this.toLowerCase()] = this
        });
        var rclass = /[\t\r\n\f]/g;
        jQuery.fn.extend({
            addClass: function (value) {
                var classes, elem, cur, clazz, j, finalValue, proceed = "string" == typeof value && value, i = 0, len = this.length;
                if (jQuery.isFunction(value))return this.each(function (j) {
                    jQuery(this).addClass(value.call(this, j, this.className))
                });
                if (proceed)for (classes = (value || "").match(rnotwhite) || []; len > i; i++)if (elem = this[i], cur = 1 === elem.nodeType && (elem.className ? (" " + elem.className + " ").replace(rclass, " ") : " ")) {
                    for (j = 0; clazz = classes[j++];)cur.indexOf(" " + clazz + " ") < 0 && (cur += clazz + " ");
                    finalValue = jQuery.trim(cur), elem.className !== finalValue && (elem.className = finalValue)
                }
                return this
            }, removeClass: function (value) {
                var classes, elem, cur, clazz, j, finalValue, proceed = 0 === arguments.length || "string" == typeof value && value, i = 0, len = this.length;
                if (jQuery.isFunction(value))return this.each(function (j) {
                    jQuery(this).removeClass(value.call(this, j, this.className))
                });
                if (proceed)for (classes = (value || "").match(rnotwhite) || []; len > i; i++)if (elem = this[i], cur = 1 === elem.nodeType && (elem.className ? (" " + elem.className + " ").replace(rclass, " ") : "")) {
                    for (j = 0; clazz = classes[j++];)for (; cur.indexOf(" " + clazz + " ") >= 0;)cur = cur.replace(" " + clazz + " ", " ");
                    finalValue = value ? jQuery.trim(cur) : "", elem.className !== finalValue && (elem.className = finalValue)
                }
                return this
            }, toggleClass: function (value, stateVal) {
                var type = typeof value;
                return "boolean" == typeof stateVal && "string" === type ? stateVal ? this.addClass(value) : this.removeClass(value) : jQuery.isFunction(value) ? this.each(function (i) {
                    jQuery(this).toggleClass(value.call(this, i, this.className, stateVal), stateVal)
                }) : this.each(function () {
                    if ("string" === type)for (var className, i = 0, self = jQuery(this), classNames = value.match(rnotwhite) || []; className = classNames[i++];)self.hasClass(className) ? self.removeClass(className) : self.addClass(className); else(type === strundefined || "boolean" === type) && (this.className && data_priv.set(this, "__className__", this.className), this.className = this.className || value === !1 ? "" : data_priv.get(this, "__className__") || "")
                })
            }, hasClass: function (selector) {
                for (var className = " " + selector + " ", i = 0, l = this.length; l > i; i++)if (1 === this[i].nodeType && (" " + this[i].className + " ").replace(rclass, " ").indexOf(className) >= 0)return !0;
                return !1
            }
        });
        var rreturn = /\r/g;
        jQuery.fn.extend({
            val: function (value) {
                var hooks, ret, isFunction, elem = this[0];
                {
                    if (arguments.length)return isFunction = jQuery.isFunction(value), this.each(function (i) {
                        var val;
                        1 === this.nodeType && (val = isFunction ? value.call(this, i, jQuery(this).val()) : value, null == val ? val = "" : "number" == typeof val ? val += "" : jQuery.isArray(val) && (val = jQuery.map(val, function (value) {
                            return null == value ? "" : value + ""
                        })), hooks = jQuery.valHooks[this.type] || jQuery.valHooks[this.nodeName.toLowerCase()], hooks && "set" in hooks && void 0 !== hooks.set(this, val, "value") || (this.value = val))
                    });
                    if (elem)return hooks = jQuery.valHooks[elem.type] || jQuery.valHooks[elem.nodeName.toLowerCase()], hooks && "get" in hooks && void 0 !== (ret = hooks.get(elem, "value")) ? ret : (ret = elem.value, "string" == typeof ret ? ret.replace(rreturn, "") : null == ret ? "" : ret)
                }
            }
        }), jQuery.extend({
            valHooks: {
                option: {
                    get: function (elem) {
                        var val = jQuery.find.attr(elem, "value");
                        return null != val ? val : jQuery.trim(jQuery.text(elem))
                    }
                }, select: {
                    get: function (elem) {
                        for (var value, option, options = elem.options, index = elem.selectedIndex, one = "select-one" === elem.type || 0 > index, values = one ? null : [], max = one ? index + 1 : options.length, i = 0 > index ? max : one ? index : 0; max > i; i++)if (option = options[i], (option.selected || i === index) && (support.optDisabled ? !option.disabled : null === option.getAttribute("disabled")) && (!option.parentNode.disabled || !jQuery.nodeName(option.parentNode, "optgroup"))) {
                            if (value = jQuery(option).val(), one)return value;
                            values.push(value)
                        }
                        return values
                    }, set: function (elem, value) {
                        for (var optionSet, option, options = elem.options, values = jQuery.makeArray(value), i = options.length; i--;)option = options[i], (option.selected = jQuery.inArray(option.value, values) >= 0) && (optionSet = !0);
                        return optionSet || (elem.selectedIndex = -1), values
                    }
                }
            }
        }), jQuery.each(["radio", "checkbox"], function () {
            jQuery.valHooks[this] = {
                set: function (elem, value) {
                    return jQuery.isArray(value) ? elem.checked = jQuery.inArray(jQuery(elem).val(), value) >= 0 : void 0
                }
            }, support.checkOn || (jQuery.valHooks[this].get = function (elem) {
                return null === elem.getAttribute("value") ? "on" : elem.value
            })
        }), jQuery.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function (i, name) {
            jQuery.fn[name] = function (data, fn) {
                return arguments.length > 0 ? this.on(name, null, data, fn) : this.trigger(name)
            }
        }), jQuery.fn.extend({
            hover: function (fnOver, fnOut) {
                return this.mouseenter(fnOver).mouseleave(fnOut || fnOver)
            }, bind: function (types, data, fn) {
                return this.on(types, null, data, fn)
            }, unbind: function (types, fn) {
                return this.off(types, null, fn)
            }, delegate: function (selector, types, data, fn) {
                return this.on(types, selector, data, fn)
            }, undelegate: function (selector, types, fn) {
                return 1 === arguments.length ? this.off(selector, "**") : this.off(types, selector || "**", fn)
            }
        });
        var nonce = jQuery.now(), rquery = /\?/;
        jQuery.parseJSON = function (data) {
            return JSON.parse(data + "")
        }, jQuery.parseXML = function (data) {
            var xml, tmp;
            if (!data || "string" != typeof data)return null;
            try {
                tmp = new DOMParser, xml = tmp.parseFromString(data, "text/xml")
            } catch (e) {
                xml = void 0
            }
            return (!xml || xml.getElementsByTagName("parsererror").length) && jQuery.error("Invalid XML: " + data), xml
        };
        var rhash = /#.*$/, rts = /([?&])_=[^&]*/, rheaders = /^(.*?):[ \t]*([^\r\n]*)$/gm, rlocalProtocol = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/, rnoContent = /^(?:GET|HEAD)$/, rprotocol = /^\/\//, rurl = /^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/, prefilters = {}, transports = {}, allTypes = "*/".concat("*"), ajaxLocation = window.location.href, ajaxLocParts = rurl.exec(ajaxLocation.toLowerCase()) || [];
        jQuery.extend({
            active: 0,
            lastModified: {},
            etag: {},
            ajaxSettings: {
                url: ajaxLocation,
                type: "GET",
                isLocal: rlocalProtocol.test(ajaxLocParts[1]),
                global: !0,
                processData: !0,
                async: !0,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                accepts: {
                    "*": allTypes,
                    text: "text/plain",
                    html: "text/html",
                    xml: "application/xml, text/xml",
                    json: "application/json, text/javascript"
                },
                contents: {xml: /xml/, html: /html/, json: /json/},
                responseFields: {xml: "responseXML", text: "responseText", json: "responseJSON"},
                converters: {
                    "* text": String,
                    "text html": !0,
                    "text json": jQuery.parseJSON,
                    "text xml": jQuery.parseXML
                },
                flatOptions: {url: !0, context: !0}
            },
            ajaxSetup: function (target, settings) {
                return settings ? ajaxExtend(ajaxExtend(target, jQuery.ajaxSettings), settings) : ajaxExtend(jQuery.ajaxSettings, target)
            },
            ajaxPrefilter: addToPrefiltersOrTransports(prefilters),
            ajaxTransport: addToPrefiltersOrTransports(transports),
            ajax: function (url, options) {
                function done(status, nativeStatusText, responses, headers) {
                    var isSuccess, success, error, response, modified, statusText = nativeStatusText;
                    2 !== state && (state = 2, timeoutTimer && clearTimeout(timeoutTimer), transport = void 0, responseHeadersString = headers || "", jqXHR.readyState = status > 0 ? 4 : 0, isSuccess = status >= 200 && 300 > status || 304 === status, responses && (response = ajaxHandleResponses(s, jqXHR, responses)), response = ajaxConvert(s, response, jqXHR, isSuccess), isSuccess ? (s.ifModified && (modified = jqXHR.getResponseHeader("Last-Modified"), modified && (jQuery.lastModified[cacheURL] = modified), modified = jqXHR.getResponseHeader("etag"), modified && (jQuery.etag[cacheURL] = modified)), 204 === status || "HEAD" === s.type ? statusText = "nocontent" : 304 === status ? statusText = "notmodified" : (statusText = response.state, success = response.data, error = response.error, isSuccess = !error)) : (error = statusText, (status || !statusText) && (statusText = "error", 0 > status && (status = 0))), jqXHR.status = status, jqXHR.statusText = (nativeStatusText || statusText) + "", isSuccess ? deferred.resolveWith(callbackContext, [success, statusText, jqXHR]) : deferred.rejectWith(callbackContext, [jqXHR, statusText, error]), jqXHR.statusCode(statusCode), statusCode = void 0, fireGlobals && globalEventContext.trigger(isSuccess ? "ajaxSuccess" : "ajaxError", [jqXHR, s, isSuccess ? success : error]), completeDeferred.fireWith(callbackContext, [jqXHR, statusText]), fireGlobals && (globalEventContext.trigger("ajaxComplete", [jqXHR, s]), --jQuery.active || jQuery.event.trigger("ajaxStop")))
                }

                "object" == typeof url && (options = url, url = void 0), options = options || {};
                var transport, cacheURL, responseHeadersString, responseHeaders, timeoutTimer, parts, fireGlobals, i, s = jQuery.ajaxSetup({}, options), callbackContext = s.context || s, globalEventContext = s.context && (callbackContext.nodeType || callbackContext.jquery) ? jQuery(callbackContext) : jQuery.event, deferred = jQuery.Deferred(), completeDeferred = jQuery.Callbacks("once memory"), statusCode = s.statusCode || {}, requestHeaders = {}, requestHeadersNames = {}, state = 0, strAbort = "canceled", jqXHR = {
                    readyState: 0,
                    getResponseHeader: function (key) {
                        var match;
                        if (2 === state) {
                            if (!responseHeaders)for (responseHeaders = {}; match = rheaders.exec(responseHeadersString);)responseHeaders[match[1].toLowerCase()] = match[2];
                            match = responseHeaders[key.toLowerCase()]
                        }
                        return null == match ? null : match
                    },
                    getAllResponseHeaders: function () {
                        return 2 === state ? responseHeadersString : null
                    },
                    setRequestHeader: function (name, value) {
                        var lname = name.toLowerCase();
                        return state || (name = requestHeadersNames[lname] = requestHeadersNames[lname] || name, requestHeaders[name] = value), this
                    },
                    overrideMimeType: function (type) {
                        return state || (s.mimeType = type), this
                    },
                    statusCode: function (map) {
                        var code;
                        if (map)if (2 > state)for (code in map)statusCode[code] = [statusCode[code], map[code]]; else jqXHR.always(map[jqXHR.status]);
                        return this
                    },
                    abort: function (statusText) {
                        var finalText = statusText || strAbort;
                        return transport && transport.abort(finalText), done(0, finalText), this
                    }
                };
                if (deferred.promise(jqXHR).complete = completeDeferred.add, jqXHR.success = jqXHR.done, jqXHR.error = jqXHR.fail, s.url = ((url || s.url || ajaxLocation) + "").replace(rhash, "").replace(rprotocol, ajaxLocParts[1] + "//"), s.type = options.method || options.type || s.method || s.type, s.dataTypes = jQuery.trim(s.dataType || "*").toLowerCase().match(rnotwhite) || [""], null == s.crossDomain && (parts = rurl.exec(s.url.toLowerCase()), s.crossDomain = !(!parts || parts[1] === ajaxLocParts[1] && parts[2] === ajaxLocParts[2] && (parts[3] || ("http:" === parts[1] ? "80" : "443")) === (ajaxLocParts[3] || ("http:" === ajaxLocParts[1] ? "80" : "443")))),
                    s.data && s.processData && "string" != typeof s.data && (s.data = jQuery.param(s.data, s.traditional)), inspectPrefiltersOrTransports(prefilters, s, options, jqXHR), 2 === state)return jqXHR;
                fireGlobals = jQuery.event && s.global, fireGlobals && 0 === jQuery.active++ && jQuery.event.trigger("ajaxStart"), s.type = s.type.toUpperCase(), s.hasContent = !rnoContent.test(s.type), cacheURL = s.url, s.hasContent || (s.data && (cacheURL = s.url += (rquery.test(cacheURL) ? "&" : "?") + s.data, delete s.data), s.cache === !1 && (s.url = rts.test(cacheURL) ? cacheURL.replace(rts, "$1_=" + nonce++) : cacheURL + (rquery.test(cacheURL) ? "&" : "?") + "_=" + nonce++)), s.ifModified && (jQuery.lastModified[cacheURL] && jqXHR.setRequestHeader("If-Modified-Since", jQuery.lastModified[cacheURL]), jQuery.etag[cacheURL] && jqXHR.setRequestHeader("If-None-Match", jQuery.etag[cacheURL])), (s.data && s.hasContent && s.contentType !== !1 || options.contentType) && jqXHR.setRequestHeader("Content-Type", s.contentType), jqXHR.setRequestHeader("Accept", s.dataTypes[0] && s.accepts[s.dataTypes[0]] ? s.accepts[s.dataTypes[0]] + ("*" !== s.dataTypes[0] ? ", " + allTypes + "; q=0.01" : "") : s.accepts["*"]);
                for (i in s.headers)jqXHR.setRequestHeader(i, s.headers[i]);
                if (s.beforeSend && (s.beforeSend.call(callbackContext, jqXHR, s) === !1 || 2 === state))return jqXHR.abort();
                strAbort = "abort";
                for (i in{success: 1, error: 1, complete: 1})jqXHR[i](s[i]);
                if (transport = inspectPrefiltersOrTransports(transports, s, options, jqXHR)) {
                    jqXHR.readyState = 1, fireGlobals && globalEventContext.trigger("ajaxSend", [jqXHR, s]), s.async && s.timeout > 0 && (timeoutTimer = setTimeout(function () {
                        jqXHR.abort("timeout")
                    }, s.timeout));
                    try {
                        state = 1, transport.send(requestHeaders, done)
                    } catch (e) {
                        if (!(2 > state))throw e;
                        done(-1, e)
                    }
                } else done(-1, "No Transport");
                return jqXHR
            },
            getJSON: function (url, data, callback) {
                return jQuery.get(url, data, callback, "json")
            },
            getScript: function (url, callback) {
                return jQuery.get(url, void 0, callback, "script")
            }
        }), jQuery.each(["get", "post"], function (i, method) {
            jQuery[method] = function (url, data, callback, type) {
                return jQuery.isFunction(data) && (type = type || callback, callback = data, data = void 0), jQuery.ajax({
                    url: url,
                    type: method,
                    dataType: type,
                    data: data,
                    success: callback
                })
            }
        }), jQuery._evalUrl = function (url) {
            return jQuery.ajax({url: url, type: "GET", dataType: "script", async: !1, global: !1, "throws": !0})
        }, jQuery.fn.extend({
            wrapAll: function (html) {
                var wrap;
                return jQuery.isFunction(html) ? this.each(function (i) {
                    jQuery(this).wrapAll(html.call(this, i))
                }) : (this[0] && (wrap = jQuery(html, this[0].ownerDocument).eq(0).clone(!0), this[0].parentNode && wrap.insertBefore(this[0]), wrap.map(function () {
                    for (var elem = this; elem.firstElementChild;)elem = elem.firstElementChild;
                    return elem
                }).append(this)), this)
            }, wrapInner: function (html) {
                return jQuery.isFunction(html) ? this.each(function (i) {
                    jQuery(this).wrapInner(html.call(this, i))
                }) : this.each(function () {
                    var self = jQuery(this), contents = self.contents();
                    contents.length ? contents.wrapAll(html) : self.append(html)
                })
            }, wrap: function (html) {
                var isFunction = jQuery.isFunction(html);
                return this.each(function (i) {
                    jQuery(this).wrapAll(isFunction ? html.call(this, i) : html)
                })
            }, unwrap: function () {
                return this.parent().each(function () {
                    jQuery.nodeName(this, "body") || jQuery(this).replaceWith(this.childNodes)
                }).end()
            }
        }), jQuery.expr.filters.hidden = function (elem) {
            return elem.offsetWidth <= 0 && elem.offsetHeight <= 0
        }, jQuery.expr.filters.visible = function (elem) {
            return !jQuery.expr.filters.hidden(elem)
        };
        var r20 = /%20/g, rbracket = /\[\]$/, rCRLF = /\r?\n/g, rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i, rsubmittable = /^(?:input|select|textarea|keygen)/i;
        jQuery.param = function (a, traditional) {
            var prefix, s = [], add = function (key, value) {
                value = jQuery.isFunction(value) ? value() : null == value ? "" : value, s[s.length] = encodeURIComponent(key) + "=" + encodeURIComponent(value)
            };
            if (void 0 === traditional && (traditional = jQuery.ajaxSettings && jQuery.ajaxSettings.traditional), jQuery.isArray(a) || a.jquery && !jQuery.isPlainObject(a))jQuery.each(a, function () {
                add(this.name, this.value)
            }); else for (prefix in a)buildParams(prefix, a[prefix], traditional, add);
            return s.join("&").replace(r20, "+")
        }, jQuery.fn.extend({
            serialize: function () {
                return jQuery.param(this.serializeArray())
            }, serializeArray: function () {
                return this.map(function () {
                    var elements = jQuery.prop(this, "elements");
                    return elements ? jQuery.makeArray(elements) : this
                }).filter(function () {
                    var type = this.type;
                    return this.name && !jQuery(this).is(":disabled") && rsubmittable.test(this.nodeName) && !rsubmitterTypes.test(type) && (this.checked || !rcheckableType.test(type))
                }).map(function (i, elem) {
                    var val = jQuery(this).val();
                    return null == val ? null : jQuery.isArray(val) ? jQuery.map(val, function (val) {
                        return {name: elem.name, value: val.replace(rCRLF, "\r\n")}
                    }) : {name: elem.name, value: val.replace(rCRLF, "\r\n")}
                }).get()
            }
        }), jQuery.ajaxSettings.xhr = function () {
            try {
                return new XMLHttpRequest
            } catch (e) {
            }
        };
        var xhrId = 0, xhrCallbacks = {}, xhrSuccessStatus = {
            0: 200,
            1223: 204
        }, xhrSupported = jQuery.ajaxSettings.xhr();
        window.attachEvent && window.attachEvent("onunload", function () {
            for (var key in xhrCallbacks)xhrCallbacks[key]()
        }), support.cors = !!xhrSupported && "withCredentials" in xhrSupported, support.ajax = xhrSupported = !!xhrSupported, jQuery.ajaxTransport(function (options) {
            var callback;
            return support.cors || xhrSupported && !options.crossDomain ? {
                send: function (headers, complete) {
                    var i, xhr = options.xhr(), id = ++xhrId;
                    if (xhr.open(options.type, options.url, options.async, options.username, options.password), options.xhrFields)for (i in options.xhrFields)xhr[i] = options.xhrFields[i];
                    options.mimeType && xhr.overrideMimeType && xhr.overrideMimeType(options.mimeType), options.crossDomain || headers["X-Requested-With"] || (headers["X-Requested-With"] = "XMLHttpRequest");
                    for (i in headers)xhr.setRequestHeader(i, headers[i]);
                    callback = function (type) {
                        return function () {
                            callback && (delete xhrCallbacks[id], callback = xhr.onload = xhr.onerror = null, "abort" === type ? xhr.abort() : "error" === type ? complete(xhr.status, xhr.statusText) : complete(xhrSuccessStatus[xhr.status] || xhr.status, xhr.statusText, "string" == typeof xhr.responseText ? {text: xhr.responseText} : void 0, xhr.getAllResponseHeaders()))
                        }
                    }, xhr.onload = callback(), xhr.onerror = callback("error"), callback = xhrCallbacks[id] = callback("abort");
                    try {
                        xhr.send(options.hasContent && options.data || null)
                    } catch (e) {
                        if (callback)throw e
                    }
                }, abort: function () {
                    callback && callback()
                }
            } : void 0
        }), jQuery.ajaxSetup({
            accepts: {script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"},
            contents: {script: /(?:java|ecma)script/},
            converters: {
                "text script": function (text) {
                    return jQuery.globalEval(text), text
                }
            }
        }), jQuery.ajaxPrefilter("script", function (s) {
            void 0 === s.cache && (s.cache = !1), s.crossDomain && (s.type = "GET")
        }), jQuery.ajaxTransport("script", function (s) {
            if (s.crossDomain) {
                var script, callback;
                return {
                    send: function (_, complete) {
                        script = jQuery("<script>").prop({
                            async: !0,
                            charset: s.scriptCharset,
                            src: s.url
                        }).on("load error", callback = function (evt) {
                            script.remove(), callback = null, evt && complete("error" === evt.type ? 404 : 200, evt.type)
                        }), document.head.appendChild(script[0])
                    }, abort: function () {
                        callback && callback()
                    }
                }
            }
        });
        var oldCallbacks = [], rjsonp = /(=)\?(?=&|$)|\?\?/;
        jQuery.ajaxSetup({
            jsonp: "callback", jsonpCallback: function () {
                var callback = oldCallbacks.pop() || jQuery.expando + "_" + nonce++;
                return this[callback] = !0, callback
            }
        }), jQuery.ajaxPrefilter("json jsonp", function (s, originalSettings, jqXHR) {
            var callbackName, overwritten, responseContainer, jsonProp = s.jsonp !== !1 && (rjsonp.test(s.url) ? "url" : "string" == typeof s.data && !(s.contentType || "").indexOf("application/x-www-form-urlencoded") && rjsonp.test(s.data) && "data");
            return jsonProp || "jsonp" === s.dataTypes[0] ? (callbackName = s.jsonpCallback = jQuery.isFunction(s.jsonpCallback) ? s.jsonpCallback() : s.jsonpCallback, jsonProp ? s[jsonProp] = s[jsonProp].replace(rjsonp, "$1" + callbackName) : s.jsonp !== !1 && (s.url += (rquery.test(s.url) ? "&" : "?") + s.jsonp + "=" + callbackName), s.converters["script json"] = function () {
                return responseContainer || jQuery.error(callbackName + " was not called"), responseContainer[0]
            }, s.dataTypes[0] = "json", overwritten = window[callbackName], window[callbackName] = function () {
                responseContainer = arguments
            }, jqXHR.always(function () {
                window[callbackName] = overwritten, s[callbackName] && (s.jsonpCallback = originalSettings.jsonpCallback, oldCallbacks.push(callbackName)), responseContainer && jQuery.isFunction(overwritten) && overwritten(responseContainer[0]), responseContainer = overwritten = void 0
            }), "script") : void 0
        }), jQuery.parseHTML = function (data, context, keepScripts) {
            if (!data || "string" != typeof data)return null;
            "boolean" == typeof context && (keepScripts = context, context = !1), context = context || document;
            var parsed = rsingleTag.exec(data), scripts = !keepScripts && [];
            return parsed ? [context.createElement(parsed[1])] : (parsed = jQuery.buildFragment([data], context, scripts), scripts && scripts.length && jQuery(scripts).remove(), jQuery.merge([], parsed.childNodes))
        };
        var _load = jQuery.fn.load;
        jQuery.fn.load = function (url, params, callback) {
            if ("string" != typeof url && _load)return _load.apply(this, arguments);
            var selector, type, response, self = this, off = url.indexOf(" ");
            return off >= 0 && (selector = jQuery.trim(url.slice(off)), url = url.slice(0, off)), jQuery.isFunction(params) ? (callback = params, params = void 0) : params && "object" == typeof params && (type = "POST"), self.length > 0 && jQuery.ajax({
                url: url,
                type: type,
                dataType: "html",
                data: params
            }).done(function (responseText) {
                response = arguments, self.html(selector ? jQuery("<div>").append(jQuery.parseHTML(responseText)).find(selector) : responseText)
            }).complete(callback && function (jqXHR, status) {
                    self.each(callback, response || [jqXHR.responseText, status, jqXHR])
                }), this
        }, jQuery.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"], function (i, type) {
            jQuery.fn[type] = function (fn) {
                return this.on(type, fn)
            }
        }), jQuery.expr.filters.animated = function (elem) {
            return jQuery.grep(jQuery.timers, function (fn) {
                return elem === fn.elem
            }).length
        };
        var docElem = window.document.documentElement;
        jQuery.offset = {
            setOffset: function (elem, options, i) {
                var curPosition, curLeft, curCSSTop, curTop, curOffset, curCSSLeft, calculatePosition, position = jQuery.css(elem, "position"), curElem = jQuery(elem), props = {};
                "static" === position && (elem.style.position = "relative"), curOffset = curElem.offset(), curCSSTop = jQuery.css(elem, "top"), curCSSLeft = jQuery.css(elem, "left"), calculatePosition = ("absolute" === position || "fixed" === position) && (curCSSTop + curCSSLeft).indexOf("auto") > -1, calculatePosition ? (curPosition = curElem.position(), curTop = curPosition.top, curLeft = curPosition.left) : (curTop = parseFloat(curCSSTop) || 0, curLeft = parseFloat(curCSSLeft) || 0), jQuery.isFunction(options) && (options = options.call(elem, i, curOffset)), null != options.top && (props.top = options.top - curOffset.top + curTop), null != options.left && (props.left = options.left - curOffset.left + curLeft), "using" in options ? options.using.call(elem, props) : curElem.css(props)
            }
        }, jQuery.fn.extend({
            offset: function (options) {
                if (arguments.length)return void 0 === options ? this : this.each(function (i) {
                    jQuery.offset.setOffset(this, options, i)
                });
                var docElem, win, elem = this[0], box = {top: 0, left: 0}, doc = elem && elem.ownerDocument;
                if (doc)return docElem = doc.documentElement, jQuery.contains(docElem, elem) ? (typeof elem.getBoundingClientRect !== strundefined && (box = elem.getBoundingClientRect()), win = getWindow(doc), {
                    top: box.top + win.pageYOffset - docElem.clientTop,
                    left: box.left + win.pageXOffset - docElem.clientLeft
                }) : box
            }, position: function () {
                if (this[0]) {
                    var offsetParent, offset, elem = this[0], parentOffset = {top: 0, left: 0};
                    return "fixed" === jQuery.css(elem, "position") ? offset = elem.getBoundingClientRect() : (offsetParent = this.offsetParent(), offset = this.offset(), jQuery.nodeName(offsetParent[0], "html") || (parentOffset = offsetParent.offset()), parentOffset.top += jQuery.css(offsetParent[0], "borderTopWidth", !0), parentOffset.left += jQuery.css(offsetParent[0], "borderLeftWidth", !0)), {
                        top: offset.top - parentOffset.top - jQuery.css(elem, "marginTop", !0),
                        left: offset.left - parentOffset.left - jQuery.css(elem, "marginLeft", !0)
                    }
                }
            }, offsetParent: function () {
                return this.map(function () {
                    for (var offsetParent = this.offsetParent || docElem; offsetParent && !jQuery.nodeName(offsetParent, "html") && "static" === jQuery.css(offsetParent, "position");)offsetParent = offsetParent.offsetParent;
                    return offsetParent || docElem
                })
            }
        }), jQuery.each({scrollLeft: "pageXOffset", scrollTop: "pageYOffset"}, function (method, prop) {
            var top = "pageYOffset" === prop;
            jQuery.fn[method] = function (val) {
                return access(this, function (elem, method, val) {
                    var win = getWindow(elem);
                    return void 0 === val ? win ? win[prop] : elem[method] : void(win ? win.scrollTo(top ? window.pageXOffset : val, top ? val : window.pageYOffset) : elem[method] = val)
                }, method, val, arguments.length, null)
            }
        }), jQuery.each(["top", "left"], function (i, prop) {
            jQuery.cssHooks[prop] = addGetHookIf(support.pixelPosition, function (elem, computed) {
                return computed ? (computed = curCSS(elem, prop), rnumnonpx.test(computed) ? jQuery(elem).position()[prop] + "px" : computed) : void 0
            })
        }), jQuery.each({Height: "height", Width: "width"}, function (name, type) {
            jQuery.each({
                padding: "inner" + name,
                content: type,
                "": "outer" + name
            }, function (defaultExtra, funcName) {
                jQuery.fn[funcName] = function (margin, value) {
                    var chainable = arguments.length && (defaultExtra || "boolean" != typeof margin), extra = defaultExtra || (margin === !0 || value === !0 ? "margin" : "border");
                    return access(this, function (elem, type, value) {
                        var doc;
                        return jQuery.isWindow(elem) ? elem.document.documentElement["client" + name] : 9 === elem.nodeType ? (doc = elem.documentElement, Math.max(elem.body["scroll" + name], doc["scroll" + name], elem.body["offset" + name], doc["offset" + name], doc["client" + name])) : void 0 === value ? jQuery.css(elem, type, extra) : jQuery.style(elem, type, value, extra)
                    }, type, chainable ? margin : void 0, chainable, null)
                }
            })
        }), jQuery.fn.size = function () {
            return this.length
        }, jQuery.fn.andSelf = jQuery.fn.addBack, "function" == typeof define && define.amd && define("jquery", [], function () {
            return jQuery
        });
        var _jQuery = window.jQuery, _$ = window.$;
        return jQuery.noConflict = function (deep) {
            return window.$ === jQuery && (window.$ = _$), deep && window.jQuery === jQuery && (window.jQuery = _jQuery), jQuery
        }, typeof noGlobal === strundefined && (window.jQuery = window.$ = jQuery), jQuery
    }), "function" != typeof Object.create && (Object.create = function (obj) {
        function F() {
        }

        return F.prototype = obj, new F
    }), function ($, window, document) {
        var Carousel = {
            init: function (options, el) {
                var base = this;
                base.$elem = $(el), base.options = $.extend({}, $.fn.owlCarousel.options, base.$elem.data(), options), base.userOptions = options, base.loadContent()
            }, loadContent: function () {
                function getData(data) {
                    var i, content = "";
                    if ("function" == typeof base.options.jsonSuccess)base.options.jsonSuccess.apply(this, [data]); else {
                        for (i in data.owl)data.owl.hasOwnProperty(i) && (content += data.owl[i].item);
                        base.$elem.html(content)
                    }
                    base.logIn()
                }

                var url, base = this;
                "function" == typeof base.options.beforeInit && base.options.beforeInit.apply(this, [base.$elem]), "string" == typeof base.options.jsonPath ? (url = base.options.jsonPath, $.getJSON(url, getData)) : base.logIn()
            }, logIn: function () {
                var base = this;
                base.$elem.data("owl-originalStyles", base.$elem.attr("style")), base.$elem.data("owl-originalClasses", base.$elem.attr("class")), base.$elem.css({opacity: 0}), base.orignalItems = base.options.items, base.checkBrowser(), base.wrapperWidth = 0, base.checkVisible = null, base.setVars()
            }, setVars: function () {
                var base = this;
                return 0 === base.$elem.children().length ? !1 : (base.baseClass(), base.eventTypes(), base.$userItems = base.$elem.children(), base.itemsAmount = base.$userItems.length, base.wrapItems(), base.$owlItems = base.$elem.find(".owl-item"), base.$owlWrapper = base.$elem.find(".owl-wrapper"), base.playDirection = "next", base.prevItem = 0, base.prevArr = [0], base.currentItem = 0, base.customEvents(), void base.onStartup())
            }, onStartup: function () {
                var base = this;
                base.updateItems(), base.calculateAll(), base.buildControls(), base.updateControls(), base.response(), base.moveEvents(), base.stopOnHover(), base.owlStatus(), base.options.transitionStyle !== !1 && base.transitionTypes(base.options.transitionStyle), base.options.autoPlay === !0 && (base.options.autoPlay = 5e3), base.play(), base.$elem.find(".owl-wrapper").css("display", "block"), base.$elem.is(":visible") ? base.$elem.css("opacity", 1) : base.watchVisibility(), base.onstartup = !1, base.eachMoveUpdate(), "function" == typeof base.options.afterInit && base.options.afterInit.apply(this, [base.$elem])
            }, eachMoveUpdate: function () {
                var base = this;
                base.options.lazyLoad === !0 && base.lazyLoad(), base.options.autoHeight === !0 && base.autoHeight(), base.onVisibleItems(), "function" == typeof base.options.afterAction && base.options.afterAction.apply(this, [base.$elem])
            }, updateVars: function () {
                var base = this;
                "function" == typeof base.options.beforeUpdate && base.options.beforeUpdate.apply(this, [base.$elem]), base.watchVisibility(), base.updateItems(), base.calculateAll(), base.updatePosition(), base.updateControls(), base.eachMoveUpdate(), "function" == typeof base.options.afterUpdate && base.options.afterUpdate.apply(this, [base.$elem])
            }, reload: function () {
                var base = this;
                window.setTimeout(function () {
                    base.updateVars()
                }, 0)
            }, watchVisibility: function () {
                var base = this;
                return base.$elem.is(":visible") !== !1 ? !1 : (base.$elem.css({opacity: 0}), window.clearInterval(base.autoPlayInterval), window.clearInterval(base.checkVisible), void(base.checkVisible = window.setInterval(function () {
                    base.$elem.is(":visible") && (base.reload(), base.$elem.animate({opacity: 1}, 200), window.clearInterval(base.checkVisible))
                }, 500)))
            }, wrapItems: function () {
                var base = this;
                base.$userItems.wrapAll('<div class="owl-wrapper">').wrap('<div class="owl-item"></div>'), base.$elem.find(".owl-wrapper").wrap('<div class="owl-wrapper-outer">'), base.wrapperOuter = base.$elem.find(".owl-wrapper-outer"), base.$elem.css("display", "block")
            }, baseClass: function () {
                var base = this, hasBaseClass = base.$elem.hasClass(base.options.baseClass), hasThemeClass = base.$elem.hasClass(base.options.theme);
                hasBaseClass || base.$elem.addClass(base.options.baseClass), hasThemeClass || base.$elem.addClass(base.options.theme)
            }, updateItems: function () {
                var width, i, base = this;
                if (base.options.responsive === !1)return !1;
                if (base.options.singleItem === !0)return base.options.items = base.orignalItems = 1, base.options.itemsCustom = !1, base.options.itemsDesktop = !1, base.options.itemsDesktopSmall = !1, base.options.itemsTablet = !1, base.options.itemsTabletSmall = !1, base.options.itemsMobile = !1, !1;
                if (width = $(base.options.responsiveBaseWidth).width(), width > (base.options.itemsDesktop[0] || base.orignalItems) && (base.options.items = base.orignalItems), base.options.itemsCustom !== !1)for (base.options.itemsCustom.sort(function (a, b) {
                    return a[0] - b[0]
                }), i = 0; i < base.options.itemsCustom.length; i += 1)base.options.itemsCustom[i][0] <= width && (base.options.items = base.options.itemsCustom[i][1]); else width <= base.options.itemsDesktop[0] && base.options.itemsDesktop !== !1 && (base.options.items = base.options.itemsDesktop[1]), width <= base.options.itemsDesktopSmall[0] && base.options.itemsDesktopSmall !== !1 && (base.options.items = base.options.itemsDesktopSmall[1]), width <= base.options.itemsTablet[0] && base.options.itemsTablet !== !1 && (base.options.items = base.options.itemsTablet[1]), width <= base.options.itemsTabletSmall[0] && base.options.itemsTabletSmall !== !1 && (base.options.items = base.options.itemsTabletSmall[1]), width <= base.options.itemsMobile[0] && base.options.itemsMobile !== !1 && (base.options.items = base.options.itemsMobile[1]);
                base.options.items > base.itemsAmount && base.options.itemsScaleUp === !0 && (base.options.items = base.itemsAmount)
            }, response: function () {
                var smallDelay, lastWindowWidth, base = this;
                return base.options.responsive !== !0 ? !1 : (lastWindowWidth = $(window).width(), base.resizer = function () {
                    $(window).width() !== lastWindowWidth && (base.options.autoPlay !== !1 && window.clearInterval(base.autoPlayInterval), window.clearTimeout(smallDelay), smallDelay = window.setTimeout(function () {
                        lastWindowWidth = $(window).width(), base.updateVars()
                    }, base.options.responsiveRefreshRate))
                }, void $(window).resize(base.resizer))
            }, updatePosition: function () {
                var base = this;
                base.jumpTo(base.currentItem), base.options.autoPlay !== !1 && base.checkAp()
            }, appendItemsSizes: function () {
                var base = this, roundPages = 0, lastItem = base.itemsAmount - base.options.items;
                base.$owlItems.each(function (index) {
                    var $this = $(this);
                    $this.css({width: base.itemWidth}).data("owl-item", Number(index)), (index % base.options.items === 0 || index === lastItem) && (index > lastItem || (roundPages += 1)), $this.data("owl-roundPages", roundPages)
                })
            }, appendWrapperSizes: function () {
                var base = this, width = base.$owlItems.length * base.itemWidth;
                base.$owlWrapper.css({width: 2 * width, left: 0}), base.appendItemsSizes()
            }, calculateAll: function () {
                var base = this;
                base.calculateWidth(), base.appendWrapperSizes(), base.loops(), base.max()
            }, calculateWidth: function () {
                var base = this;
                base.itemWidth = Math.round(base.$elem.width() / base.options.items)
            }, max: function () {
                var base = this, maximum = -1 * (base.itemsAmount * base.itemWidth - base.options.items * base.itemWidth);
                return base.options.items > base.itemsAmount ? (base.maximumItem = 0, maximum = 0, base.maximumPixels = 0) : (base.maximumItem = base.itemsAmount - base.options.items, base.maximumPixels = maximum), maximum
            }, min: function () {
                return 0
            }, loops: function () {
                var i, item, roundPageNum, base = this, prev = 0, elWidth = 0;
                for (base.positionsInArray = [0], base.pagesInArray = [], i = 0; i < base.itemsAmount; i += 1)elWidth += base.itemWidth, base.positionsInArray.push(-elWidth), base.options.scrollPerPage === !0 && (item = $(base.$owlItems[i]), roundPageNum = item.data("owl-roundPages"), roundPageNum !== prev && (base.pagesInArray[prev] = base.positionsInArray[i], prev = roundPageNum))
            }, buildControls: function () {
                var base = this;
                (base.options.navigation === !0 || base.options.pagination === !0) && (base.owlControls = $('<div class="owl-controls"/>').toggleClass("clickable", !base.browser.isTouch).appendTo(base.$elem)), base.options.pagination === !0 && base.buildPagination(), base.options.navigation === !0 && base.buildButtons()
            }, buildButtons: function () {
                var base = this, buttonsWrapper = $('<div class="owl-buttons"/>');
                base.owlControls.append(buttonsWrapper), base.buttonPrev = $("<div/>", {
                    "class": "owl-prev",
                    html: base.options.navigationText[0] || ""
                }), base.buttonNext = $("<div/>", {
                    "class": "owl-next",
                    html: base.options.navigationText[1] || ""
                }), buttonsWrapper.append(base.buttonPrev).append(base.buttonNext), buttonsWrapper.on("touchstart.owlControls mousedown.owlControls", 'div[class^="owl"]', function (event) {
                    event.preventDefault()
                }), buttonsWrapper.on("touchend.owlControls mouseup.owlControls", 'div[class^="owl"]', function (event) {
                    event.preventDefault(), $(this).hasClass("owl-next") ? base.next() : base.prev()
                })
            }, buildPagination: function () {
                var base = this;
                base.paginationWrapper = $('<div class="owl-pagination"/>'), base.owlControls.append(base.paginationWrapper), base.paginationWrapper.on("touchend.owlControls mouseup.owlControls", ".owl-page", function (event) {
                    event.preventDefault(), Number($(this).data("owl-page")) !== base.currentItem && base.goTo(Number($(this).data("owl-page")), !0)
                })
            }, updatePagination: function () {
                var counter, lastPage, lastItem, i, paginationButton, paginationButtonInner, base = this;
                if (base.options.pagination === !1)return !1;
                for (base.paginationWrapper.html(""), counter = 0, lastPage = base.itemsAmount - base.itemsAmount % base.options.items, i = 0; i < base.itemsAmount; i += 1)i % base.options.items === 0 && (counter += 1, lastPage === i && (lastItem = base.itemsAmount - base.options.items), paginationButton = $("<div/>", {"class": "owl-page"}), paginationButtonInner = $("<span></span>", {
                    text: base.options.paginationNumbers === !0 ? counter : "",
                    "class": base.options.paginationNumbers === !0 ? "owl-numbers" : ""
                }), paginationButton.append(paginationButtonInner), paginationButton.data("owl-page", lastPage === i ? lastItem : i), paginationButton.data("owl-roundPages", counter), base.paginationWrapper.append(paginationButton));
                base.checkPagination()
            }, checkPagination: function () {
                var base = this;
                return base.options.pagination === !1 ? !1 : void base.paginationWrapper.find(".owl-page").each(function () {
                    $(this).data("owl-roundPages") === $(base.$owlItems[base.currentItem]).data("owl-roundPages") && (base.paginationWrapper.find(".owl-page").removeClass("active"), $(this).addClass("active"))
                })
            }, checkNavigation: function () {
                var base = this;
                return base.options.navigation === !1 ? !1 : void(base.options.rewindNav === !1 && (0 === base.currentItem && 0 === base.maximumItem ? (base.buttonPrev.addClass("disabled"), base.buttonNext.addClass("disabled")) : 0 === base.currentItem && 0 !== base.maximumItem ? (base.buttonPrev.addClass("disabled"), base.buttonNext.removeClass("disabled")) : base.currentItem === base.maximumItem ? (base.buttonPrev.removeClass("disabled"), base.buttonNext.addClass("disabled")) : 0 !== base.currentItem && base.currentItem !== base.maximumItem && (base.buttonPrev.removeClass("disabled"), base.buttonNext.removeClass("disabled"))))
            }, updateControls: function () {
                var base = this;
                base.updatePagination(), base.checkNavigation(), base.owlControls && (base.options.items >= base.itemsAmount ? base.owlControls.hide() : base.owlControls.show())
            }, destroyControls: function () {
                var base = this;
                base.owlControls && base.owlControls.remove()
            }, next: function (speed) {
                var base = this;
                if (base.isTransition)return !1;
                if (base.currentItem += base.options.scrollPerPage === !0 ? base.options.items : 1, base.currentItem > base.maximumItem + (base.options.scrollPerPage === !0 ? base.options.items - 1 : 0)) {
                    if (base.options.rewindNav !== !0)return base.currentItem = base.maximumItem, !1;
                    base.currentItem = 0, speed = "rewind"
                }
                base.goTo(base.currentItem, speed)
            }, prev: function (speed) {
                var base = this;
                if (base.isTransition)return !1;
                if (base.options.scrollPerPage === !0 && base.currentItem > 0 && base.currentItem < base.options.items ? base.currentItem = 0 : base.currentItem -= base.options.scrollPerPage === !0 ? base.options.items : 1, base.currentItem < 0) {
                    if (base.options.rewindNav !== !0)return base.currentItem = 0, !1;
                    base.currentItem = base.maximumItem, speed = "rewind"
                }
                base.goTo(base.currentItem, speed)
            }, goTo: function (position, speed, drag) {
                var goToPixel, base = this;
                return base.isTransition ? !1 : ("function" == typeof base.options.beforeMove && base.options.beforeMove.apply(this, [base.$elem]), position >= base.maximumItem ? position = base.maximumItem : 0 >= position && (position = 0), base.currentItem = base.owl.currentItem = position, base.options.transitionStyle !== !1 && "drag" !== drag && 1 === base.options.items && base.browser.support3d === !0 ? (base.swapSpeed(0), base.browser.support3d === !0 ? base.transition3d(base.positionsInArray[position]) : base.css2slide(base.positionsInArray[position], 1), base.afterGo(), base.singleItemTransition(), !1) : (goToPixel = base.positionsInArray[position], base.browser.support3d === !0 ? (base.isCss3Finish = !1, speed === !0 ? (base.swapSpeed("paginationSpeed"), window.setTimeout(function () {
                    base.isCss3Finish = !0
                }, base.options.paginationSpeed)) : "rewind" === speed ? (base.swapSpeed(base.options.rewindSpeed), window.setTimeout(function () {
                    base.isCss3Finish = !0
                }, base.options.rewindSpeed)) : (base.swapSpeed("slideSpeed"), window.setTimeout(function () {
                    base.isCss3Finish = !0
                }, base.options.slideSpeed)), base.transition3d(goToPixel)) : speed === !0 ? base.css2slide(goToPixel, base.options.paginationSpeed) : "rewind" === speed ? base.css2slide(goToPixel, base.options.rewindSpeed) : base.css2slide(goToPixel, base.options.slideSpeed), void base.afterGo()))
            }, jumpTo: function (position) {
                var base = this;
                "function" == typeof base.options.beforeMove && base.options.beforeMove.apply(this, [base.$elem]), position >= base.maximumItem || -1 === position ? position = base.maximumItem : 0 >= position && (position = 0), base.swapSpeed(0), base.browser.support3d === !0 ? base.transition3d(base.positionsInArray[position]) : base.css2slide(base.positionsInArray[position], 1), base.currentItem = base.owl.currentItem = position, base.afterGo()
            }, afterGo: function () {
                var base = this;
                base.prevArr.push(base.currentItem), base.prevItem = base.owl.prevItem = base.prevArr[base.prevArr.length - 2], base.prevArr.shift(0), base.prevItem !== base.currentItem && (base.checkPagination(), base.checkNavigation(), base.eachMoveUpdate(), base.options.autoPlay !== !1 && base.checkAp()), "function" == typeof base.options.afterMove && base.prevItem !== base.currentItem && base.options.afterMove.apply(this, [base.$elem])
            }, stop: function () {
                var base = this;
                base.apStatus = "stop", window.clearInterval(base.autoPlayInterval)
            }, checkAp: function () {
                var base = this;
                "stop" !== base.apStatus && base.play()
            }, play: function () {
                var base = this;
                return base.apStatus = "play", base.options.autoPlay === !1 ? !1 : (window.clearInterval(base.autoPlayInterval), void(base.autoPlayInterval = window.setInterval(function () {
                    base.next(!0)
                }, base.options.autoPlay)))
            }, swapSpeed: function (action) {
                var base = this;
                "slideSpeed" === action ? base.$owlWrapper.css(base.addCssSpeed(base.options.slideSpeed)) : "paginationSpeed" === action ? base.$owlWrapper.css(base.addCssSpeed(base.options.paginationSpeed)) : "string" != typeof action && base.$owlWrapper.css(base.addCssSpeed(action))
            }, addCssSpeed: function (speed) {
                return {
                    "-webkit-transition": "all " + speed + "ms ease",
                    "-moz-transition": "all " + speed + "ms ease",
                    "-o-transition": "all " + speed + "ms ease",
                    transition: "all " + speed + "ms ease"
                }
            }, removeTransition: function () {
                return {"-webkit-transition": "", "-moz-transition": "", "-o-transition": "", transition: ""}
            }, doTranslate: function (pixels) {
                return {
                    "-webkit-transform": "translate3d(" + pixels + "px, 0px, 0px)",
                    "-moz-transform": "translate3d(" + pixels + "px, 0px, 0px)",
                    "-o-transform": "translate3d(" + pixels + "px, 0px, 0px)",
                    "-ms-transform": "translate3d(" + pixels + "px, 0px, 0px)",
                    transform: "translate3d(" + pixels + "px, 0px,0px)"
                }
            }, transition3d: function (value) {
                var base = this;
                base.$owlWrapper.css(base.doTranslate(value))
            }, css2move: function (value) {
                var base = this;
                base.$owlWrapper.css({left: value})
            }, css2slide: function (value, speed) {
                var base = this;
                base.isCssFinish = !1, base.$owlWrapper.stop(!0, !0).animate({left: value}, {
                    duration: speed || base.options.slideSpeed,
                    complete: function () {
                        base.isCssFinish = !0
                    }
                })
            }, checkBrowser: function () {
                var regex, asSupport, support3d, isTouch, base = this, translate3D = "translate3d(0px, 0px, 0px)", tempElem = document.createElement("div");
                tempElem.style.cssText = "  -moz-transform:" + translate3D + "; -ms-transform:" + translate3D + "; -o-transform:" + translate3D + "; -webkit-transform:" + translate3D + "; transform:" + translate3D, regex = /translate3d\(0px, 0px, 0px\)/g, asSupport = tempElem.style.cssText.match(regex), support3d = null !== asSupport && 1 === asSupport.length, isTouch = "ontouchstart" in window || window.navigator.msMaxTouchPoints, base.browser = {
                    support3d: support3d,
                    isTouch: isTouch
                }
            }, moveEvents: function () {
                var base = this;
                (base.options.mouseDrag !== !1 || base.options.touchDrag !== !1) && (base.gestures(), base.disabledEvents())
            }, eventTypes: function () {
                var base = this, types = ["s", "e", "x"];
                base.ev_types = {}, base.options.mouseDrag === !0 && base.options.touchDrag === !0 ? types = ["touchstart.owl mousedown.owl", "touchmove.owl mousemove.owl", "touchend.owl touchcancel.owl mouseup.owl"] : base.options.mouseDrag === !1 && base.options.touchDrag === !0 ? types = ["touchstart.owl", "touchmove.owl", "touchend.owl touchcancel.owl"] : base.options.mouseDrag === !0 && base.options.touchDrag === !1 && (types = ["mousedown.owl", "mousemove.owl", "mouseup.owl"]), base.ev_types.start = types[0], base.ev_types.move = types[1], base.ev_types.end = types[2]
            }, disabledEvents: function () {
                var base = this;
                base.$elem.on("dragstart.owl", function (event) {
                    event.preventDefault()
                }), base.$elem.on("mousedown.disableTextSelect", function (e) {
                    return $(e.target).is("input, textarea, select, option")
                })
            }, gestures: function () {
                function getTouches(event) {
                    if (void 0 !== event.touches)return {x: event.touches[0].pageX, y: event.touches[0].pageY};
                    if (void 0 === event.touches) {
                        if (void 0 !== event.pageX)return {x: event.pageX, y: event.pageY};
                        if (void 0 === event.pageX)return {x: event.clientX, y: event.clientY}
                    }
                }

                function swapEvents(type) {
                    "on" === type ? ($(document).on(base.ev_types.move, dragMove), $(document).on(base.ev_types.end, dragEnd)) : "off" === type && ($(document).off(base.ev_types.move), $(document).off(base.ev_types.end))
                }

                function dragStart(event) {
                    var position, ev = event.originalEvent || event || window.event;
                    if (3 === ev.which)return !1;
                    if (!(base.itemsAmount <= base.options.items)) {
                        if (base.isCssFinish === !1 && !base.options.dragBeforeAnimFinish)return !1;
                        if (base.isCss3Finish === !1 && !base.options.dragBeforeAnimFinish)return !1;
                        base.options.autoPlay !== !1 && window.clearInterval(base.autoPlayInterval), base.browser.isTouch === !0 || base.$owlWrapper.hasClass("grabbing") || base.$owlWrapper.addClass("grabbing"), base.newPosX = 0, base.newRelativeX = 0, $(this).css(base.removeTransition()), position = $(this).position(), locals.relativePos = position.left, locals.offsetX = getTouches(ev).x - position.left, locals.offsetY = getTouches(ev).y - position.top, swapEvents("on"), locals.sliding = !1, locals.targetElement = ev.target || ev.srcElement
                    }
                }

                function dragMove(event) {
                    var minSwipe, maxSwipe, ev = event.originalEvent || event || window.event;
                    base.newPosX = getTouches(ev).x - locals.offsetX, base.newPosY = getTouches(ev).y - locals.offsetY,
                        base.newRelativeX = base.newPosX - locals.relativePos, "function" == typeof base.options.startDragging && locals.dragging !== !0 && 0 !== base.newRelativeX && (locals.dragging = !0, base.options.startDragging.apply(base, [base.$elem])), (base.newRelativeX > 8 || base.newRelativeX < -8) && base.browser.isTouch === !0 && (void 0 !== ev.preventDefault ? ev.preventDefault() : ev.returnValue = !1, locals.sliding = !0), (base.newPosY > 10 || base.newPosY < -10) && locals.sliding === !1 && $(document).off("touchmove.owl"), minSwipe = function () {
                        return base.newRelativeX / 5
                    }, maxSwipe = function () {
                        return base.maximumPixels + base.newRelativeX / 5
                    }, base.newPosX = Math.max(Math.min(base.newPosX, minSwipe()), maxSwipe()), base.browser.support3d === !0 ? base.transition3d(base.newPosX) : base.css2move(base.newPosX)
                }

                function dragEnd(event) {
                    var newPosition, handlers, owlStopEvent, ev = event.originalEvent || event || window.event;
                    ev.target = ev.target || ev.srcElement, locals.dragging = !1, base.browser.isTouch !== !0 && base.$owlWrapper.removeClass("grabbing"), base.newRelativeX < 0 ? base.dragDirection = base.owl.dragDirection = "left" : base.dragDirection = base.owl.dragDirection = "right", 0 !== base.newRelativeX && (newPosition = base.getNewPosition(), base.goTo(newPosition, !1, "drag"), locals.targetElement === ev.target && base.browser.isTouch !== !0 && ($(ev.target).on("click.disable", function (ev) {
                        ev.stopImmediatePropagation(), ev.stopPropagation(), ev.preventDefault(), $(ev.target).off("click.disable")
                    }), handlers = $._data(ev.target, "events").click, owlStopEvent = handlers.pop(), handlers.splice(0, 0, owlStopEvent))), swapEvents("off")
                }

                var base = this, locals = {
                    offsetX: 0,
                    offsetY: 0,
                    baseElWidth: 0,
                    relativePos: 0,
                    position: null,
                    minSwipe: null,
                    maxSwipe: null,
                    sliding: null,
                    dargging: null,
                    targetElement: null
                };
                base.isCssFinish = !0, base.$elem.on(base.ev_types.start, ".owl-wrapper", dragStart)
            }, getNewPosition: function () {
                var base = this, newPosition = base.closestItem();
                return newPosition > base.maximumItem ? (base.currentItem = base.maximumItem, newPosition = base.maximumItem) : base.newPosX >= 0 && (newPosition = 0, base.currentItem = 0), newPosition
            }, closestItem: function () {
                var base = this, array = base.options.scrollPerPage === !0 ? base.pagesInArray : base.positionsInArray, goal = base.newPosX, closest = null;
                return $.each(array, function (i, v) {
                    goal - base.itemWidth / 20 > array[i + 1] && goal - base.itemWidth / 20 < v && "left" === base.moveDirection() ? (closest = v, base.options.scrollPerPage === !0 ? base.currentItem = $.inArray(closest, base.positionsInArray) : base.currentItem = i) : goal + base.itemWidth / 20 < v && goal + base.itemWidth / 20 > (array[i + 1] || array[i] - base.itemWidth) && "right" === base.moveDirection() && (base.options.scrollPerPage === !0 ? (closest = array[i + 1] || array[array.length - 1], base.currentItem = $.inArray(closest, base.positionsInArray)) : (closest = array[i + 1], base.currentItem = i + 1))
                }), base.currentItem
            }, moveDirection: function () {
                var direction, base = this;
                return base.newRelativeX < 0 ? (direction = "right", base.playDirection = "next") : (direction = "left", base.playDirection = "prev"), direction
            }, customEvents: function () {
                var base = this;
                base.$elem.on("owl.next", function () {
                    base.next()
                }), base.$elem.on("owl.prev", function () {
                    base.prev()
                }), base.$elem.on("owl.play", function (event, speed) {
                    base.options.autoPlay = speed, base.play(), base.hoverStatus = "play"
                }), base.$elem.on("owl.stop", function () {
                    base.stop(), base.hoverStatus = "stop"
                }), base.$elem.on("owl.goTo", function (event, item) {
                    base.goTo(item)
                }), base.$elem.on("owl.jumpTo", function (event, item) {
                    base.jumpTo(item)
                })
            }, stopOnHover: function () {
                var base = this;
                base.options.stopOnHover === !0 && base.browser.isTouch !== !0 && base.options.autoPlay !== !1 && (base.$elem.on("mouseover", function () {
                    base.stop()
                }), base.$elem.on("mouseout", function () {
                    "stop" !== base.hoverStatus && base.play()
                }))
            }, lazyLoad: function () {
                var i, $item, itemNumber, $lazyImg, follow, base = this;
                if (base.options.lazyLoad === !1)return !1;
                for (i = 0; i < base.itemsAmount; i += 1)$item = $(base.$owlItems[i]), "loaded" !== $item.data("owl-loaded") && (itemNumber = $item.data("owl-item"), $lazyImg = $item.find(".lazyOwl"), "string" == typeof $lazyImg.data("src") ? (void 0 === $item.data("owl-loaded") && ($lazyImg.hide(), $item.addClass("loading").data("owl-loaded", "checked")), follow = base.options.lazyFollow === !0 ? itemNumber >= base.currentItem : !0, follow && itemNumber < base.currentItem + base.options.items && $lazyImg.length && base.lazyPreload($item, $lazyImg)) : $item.data("owl-loaded", "loaded"))
            }, lazyPreload: function ($item, $lazyImg) {
                function showImage() {
                    $item.data("owl-loaded", "loaded").removeClass("loading"), $lazyImg.removeAttr("data-src"), "fade" === base.options.lazyEffect ? $lazyImg.fadeIn(400) : $lazyImg.show(), "function" == typeof base.options.afterLazyLoad && base.options.afterLazyLoad.apply(this, [base.$elem])
                }

                function checkLazyImage() {
                    iterations += 1, base.completeImg($lazyImg.get(0)) || isBackgroundImg === !0 ? showImage() : 100 >= iterations ? window.setTimeout(checkLazyImage, 100) : showImage()
                }

                var isBackgroundImg, base = this, iterations = 0;
                "DIV" === $lazyImg.prop("tagName") ? ($lazyImg.css("background-image", "url(" + $lazyImg.data("src") + ")"), isBackgroundImg = !0) : $lazyImg[0].src = $lazyImg.data("src"), checkLazyImage()
            }, autoHeight: function () {
                function addHeight() {
                    var $currentItem = $(base.$owlItems[base.currentItem]).height();
                    base.wrapperOuter.css("height", $currentItem + "px"), base.wrapperOuter.hasClass("autoHeight") || window.setTimeout(function () {
                        base.wrapperOuter.addClass("autoHeight")
                    }, 0)
                }

                function checkImage() {
                    iterations += 1, base.completeImg($currentimg.get(0)) ? addHeight() : 100 >= iterations ? window.setTimeout(checkImage, 100) : base.wrapperOuter.css("height", "")
                }

                var iterations, base = this, $currentimg = $(base.$owlItems[base.currentItem]).find("img");
                void 0 !== $currentimg.get(0) ? (iterations = 0, checkImage()) : addHeight()
            }, completeImg: function (img) {
                var naturalWidthType;
                return img.complete ? (naturalWidthType = typeof img.naturalWidth, "undefined" !== naturalWidthType && 0 === img.naturalWidth ? !1 : !0) : !1
            }, onVisibleItems: function () {
                var i, base = this;
                for (base.options.addClassActive === !0 && base.$owlItems.removeClass("active"), base.visibleItems = [], i = base.currentItem; i < base.currentItem + base.options.items; i += 1)base.visibleItems.push(i), base.options.addClassActive === !0 && $(base.$owlItems[i]).addClass("active");
                base.owl.visibleItems = base.visibleItems
            }, transitionTypes: function (className) {
                var base = this;
                base.outClass = "owl-" + className + "-out", base.inClass = "owl-" + className + "-in"
            }, singleItemTransition: function () {
                function transStyles(prevPos) {
                    return {position: "relative", left: prevPos + "px"}
                }

                var base = this, outClass = base.outClass, inClass = base.inClass, $currentItem = base.$owlItems.eq(base.currentItem), $prevItem = base.$owlItems.eq(base.prevItem), prevPos = Math.abs(base.positionsInArray[base.currentItem]) + base.positionsInArray[base.prevItem], origin = Math.abs(base.positionsInArray[base.currentItem]) + base.itemWidth / 2, animEnd = "webkitAnimationEnd oAnimationEnd MSAnimationEnd animationend";
                base.isTransition = !0, base.$owlWrapper.addClass("owl-origin").css({
                    "-webkit-transform-origin": origin + "px",
                    "-moz-perspective-origin": origin + "px",
                    "perspective-origin": origin + "px"
                }), $prevItem.css(transStyles(prevPos, 10)).addClass(outClass).on(animEnd, function () {
                    base.endPrev = !0, $prevItem.off(animEnd), base.clearTransStyle($prevItem, outClass)
                }), $currentItem.addClass(inClass).on(animEnd, function () {
                    base.endCurrent = !0, $currentItem.off(animEnd), base.clearTransStyle($currentItem, inClass)
                })
            }, clearTransStyle: function (item, classToRemove) {
                var base = this;
                item.css({
                    position: "",
                    left: ""
                }).removeClass(classToRemove), base.endPrev && base.endCurrent && (base.$owlWrapper.removeClass("owl-origin"), base.endPrev = !1, base.endCurrent = !1, base.isTransition = !1)
            }, owlStatus: function () {
                var base = this;
                base.owl = {
                    userOptions: base.userOptions,
                    baseElement: base.$elem,
                    userItems: base.$userItems,
                    owlItems: base.$owlItems,
                    currentItem: base.currentItem,
                    prevItem: base.prevItem,
                    visibleItems: base.visibleItems,
                    isTouch: base.browser.isTouch,
                    browser: base.browser,
                    dragDirection: base.dragDirection
                }
            }, clearEvents: function () {
                var base = this;
                base.$elem.off(".owl owl mousedown.disableTextSelect"), $(document).off(".owl owl"), $(window).off("resize", base.resizer)
            }, unWrap: function () {
                var base = this;
                0 !== base.$elem.children().length && (base.$owlWrapper.unwrap(), base.$userItems.unwrap().unwrap(), base.owlControls && base.owlControls.remove()), base.clearEvents(), base.$elem.attr("style", base.$elem.data("owl-originalStyles") || "").attr("class", base.$elem.data("owl-originalClasses"))
            }, destroy: function () {
                var base = this;
                base.stop(), window.clearInterval(base.checkVisible), base.unWrap(), base.$elem.removeData()
            }, reinit: function (newOptions) {
                var base = this, options = $.extend({}, base.userOptions, newOptions);
                base.unWrap(), base.init(options, base.$elem)
            }, addItem: function (htmlString, targetPosition) {
                var position, base = this;
                return htmlString ? 0 === base.$elem.children().length ? (base.$elem.append(htmlString), base.setVars(), !1) : (base.unWrap(), position = void 0 === targetPosition || -1 === targetPosition ? -1 : targetPosition, position >= base.$userItems.length || -1 === position ? base.$userItems.eq(-1).after(htmlString) : base.$userItems.eq(position).before(htmlString), void base.setVars()) : !1
            }, removeItem: function (targetPosition) {
                var position, base = this;
                return 0 === base.$elem.children().length ? !1 : (position = void 0 === targetPosition || -1 === targetPosition ? -1 : targetPosition, base.unWrap(), base.$userItems.eq(position).remove(), void base.setVars())
            }
        };
        $.fn.owlCarousel = function (options) {
            return this.each(function () {
                if ($(this).data("owl-init") === !0)return !1;
                $(this).data("owl-init", !0);
                var carousel = Object.create(Carousel);
                carousel.init(options, this), $.data(this, "owlCarousel", carousel)
            })
        }, $.fn.owlCarousel.options = {
            items: 5,
            itemsCustom: !1,
            itemsDesktop: [1199, 4],
            itemsDesktopSmall: [979, 3],
            itemsTablet: [768, 2],
            itemsTabletSmall: !1,
            itemsMobile: [479, 1],
            singleItem: !1,
            itemsScaleUp: !1,
            slideSpeed: 200,
            paginationSpeed: 800,
            rewindSpeed: 1e3,
            autoPlay: !1,
            stopOnHover: !1,
            navigation: !1,
            navigationText: ["prev", "next"],
            rewindNav: !0,
            scrollPerPage: !1,
            pagination: !0,
            paginationNumbers: !1,
            responsive: !0,
            responsiveRefreshRate: 200,
            responsiveBaseWidth: window,
            baseClass: "owl-carousel",
            theme: "owl-theme",
            lazyLoad: !1,
            lazyFollow: !0,
            lazyEffect: "fade",
            autoHeight: !1,
            jsonPath: !1,
            jsonSuccess: !1,
            dragBeforeAnimFinish: !0,
            mouseDrag: !0,
            touchDrag: !0,
            addClassActive: !1,
            transitionStyle: !1,
            beforeUpdate: !1,
            afterUpdate: !1,
            beforeInit: !1,
            afterInit: !1,
            beforeMove: !1,
            afterMove: !1,
            afterAction: !1,
            startDragging: !1,
            afterLazyLoad: !1
        }
    }(jQuery, window, document), "undefined" == typeof jQuery)throw new Error("Bootstrap's JavaScript requires jQuery");
+function ($) {
    "use strict";
    var version = $.fn.jquery.split(" ")[0].split(".");
    if (version[0] < 2 && version[1] < 9 || 1 == version[0] && 9 == version[1] && version[2] < 1)throw new Error("Bootstrap's JavaScript requires jQuery version 1.9.1 or higher")
}(jQuery), +function ($) {
    "use strict";
    function transitionEnd() {
        var el = document.createElement("bootstrap"), transEndEventNames = {
            WebkitTransition: "webkitTransitionEnd",
            MozTransition: "transitionend",
            OTransition: "oTransitionEnd otransitionend",
            transition: "transitionend"
        };
        for (var name in transEndEventNames)if (void 0 !== el.style[name])return {end: transEndEventNames[name]};
        return !1
    }

    $.fn.emulateTransitionEnd = function (duration) {
        var called = !1, $el = this;
        $(this).one("bsTransitionEnd", function () {
            called = !0
        });
        var callback = function () {
            called || $($el).trigger($.support.transition.end)
        };
        return setTimeout(callback, duration), this
    }, $(function () {
        $.support.transition = transitionEnd(), $.support.transition && ($.event.special.bsTransitionEnd = {
            bindType: $.support.transition.end,
            delegateType: $.support.transition.end,
            handle: function (e) {
                return $(e.target).is(this) ? e.handleObj.handler.apply(this, arguments) : void 0
            }
        })
    })
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.alert");
            data || $this.data("bs.alert", data = new Alert(this)), "string" == typeof option && data[option].call($this)
        })
    }

    var dismiss = '[data-dismiss="alert"]', Alert = function (el) {
        $(el).on("click", dismiss, this.close)
    };
    Alert.VERSION = "3.3.5", Alert.TRANSITION_DURATION = 150, Alert.prototype.close = function (e) {
        function removeElement() {
            $parent.detach().trigger("closed.bs.alert").remove()
        }

        var $this = $(this), selector = $this.attr("data-target");
        selector || (selector = $this.attr("href"), selector = selector && selector.replace(/.*(?=#[^\s]*$)/, ""));
        var $parent = $(selector);
        e && e.preventDefault(), $parent.length || ($parent = $this.closest(".alert")), $parent.trigger(e = $.Event("close.bs.alert")), e.isDefaultPrevented() || ($parent.removeClass("in"), $.support.transition && $parent.hasClass("fade") ? $parent.one("bsTransitionEnd", removeElement).emulateTransitionEnd(Alert.TRANSITION_DURATION) : removeElement())
    };
    var old = $.fn.alert;
    $.fn.alert = Plugin, $.fn.alert.Constructor = Alert, $.fn.alert.noConflict = function () {
        return $.fn.alert = old, this
    }, $(document).on("click.bs.alert.data-api", dismiss, Alert.prototype.close)
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.button"), options = "object" == typeof option && option;
            data || $this.data("bs.button", data = new Button(this, options)), "toggle" == option ? data.toggle() : option && data.setState(option)
        })
    }

    var Button = function (element, options) {
        this.$element = $(element), this.options = $.extend({}, Button.DEFAULTS, options), this.isLoading = !1
    };
    Button.VERSION = "3.3.5", Button.DEFAULTS = {loadingText: "loading..."}, Button.prototype.setState = function (state) {
        var d = "disabled", $el = this.$element, val = $el.is("input") ? "val" : "html", data = $el.data();
        state += "Text", null == data.resetText && $el.data("resetText", $el[val]()), setTimeout($.proxy(function () {
            $el[val](null == data[state] ? this.options[state] : data[state]), "loadingText" == state ? (this.isLoading = !0, $el.addClass(d).attr(d, d)) : this.isLoading && (this.isLoading = !1, $el.removeClass(d).removeAttr(d))
        }, this), 0)
    }, Button.prototype.toggle = function () {
        var changed = !0, $parent = this.$element.closest('[data-toggle="buttons"]');
        if ($parent.length) {
            var $input = this.$element.find("input");
            "radio" == $input.prop("type") ? ($input.prop("checked") && (changed = !1), $parent.find(".active").removeClass("active"), this.$element.addClass("active")) : "checkbox" == $input.prop("type") && ($input.prop("checked") !== this.$element.hasClass("active") && (changed = !1), this.$element.toggleClass("active")), $input.prop("checked", this.$element.hasClass("active")), changed && $input.trigger("change")
        } else this.$element.attr("aria-pressed", !this.$element.hasClass("active")), this.$element.toggleClass("active")
    };
    var old = $.fn.button;
    $.fn.button = Plugin, $.fn.button.Constructor = Button, $.fn.button.noConflict = function () {
        return $.fn.button = old, this
    }, $(document).on("click.bs.button.data-api", '[data-toggle^="button"]', function (e) {
        var $btn = $(e.target);
        $btn.hasClass("btn") || ($btn = $btn.closest(".btn")), Plugin.call($btn, "toggle"), $(e.target).is('input[type="radio"]') || $(e.target).is('input[type="checkbox"]') || e.preventDefault()
    }).on("focus.bs.button.data-api blur.bs.button.data-api", '[data-toggle^="button"]', function (e) {
        $(e.target).closest(".btn").toggleClass("focus", /^focus(in)?$/.test(e.type))
    })
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.carousel"), options = $.extend({}, Carousel.DEFAULTS, $this.data(), "object" == typeof option && option), action = "string" == typeof option ? option : options.slide;
            data || $this.data("bs.carousel", data = new Carousel(this, options)), "number" == typeof option ? data.to(option) : action ? data[action]() : options.interval && data.pause().cycle()
        })
    }

    var Carousel = function (element, options) {
        this.$element = $(element), this.$indicators = this.$element.find(".carousel-indicators"), this.options = options, this.paused = null, this.sliding = null, this.interval = null, this.$active = null, this.$items = null, this.options.keyboard && this.$element.on("keydown.bs.carousel", $.proxy(this.keydown, this)), "hover" == this.options.pause && !("ontouchstart" in document.documentElement) && this.$element.on("mouseenter.bs.carousel", $.proxy(this.pause, this)).on("mouseleave.bs.carousel", $.proxy(this.cycle, this))
    };
    Carousel.VERSION = "3.3.5", Carousel.TRANSITION_DURATION = 600, Carousel.DEFAULTS = {
        interval: 5e3,
        pause: "hover",
        wrap: !0,
        keyboard: !0
    }, Carousel.prototype.keydown = function (e) {
        if (!/input|textarea/i.test(e.target.tagName)) {
            switch (e.which) {
                case 37:
                    this.prev();
                    break;
                case 39:
                    this.next();
                    break;
                default:
                    return
            }
            e.preventDefault()
        }
    }, Carousel.prototype.cycle = function (e) {
        return e || (this.paused = !1), this.interval && clearInterval(this.interval), this.options.interval && !this.paused && (this.interval = setInterval($.proxy(this.next, this), this.options.interval)), this
    }, Carousel.prototype.getItemIndex = function (item) {
        return this.$items = item.parent().children(".item"), this.$items.index(item || this.$active)
    }, Carousel.prototype.getItemForDirection = function (direction, active) {
        var activeIndex = this.getItemIndex(active), willWrap = "prev" == direction && 0 === activeIndex || "next" == direction && activeIndex == this.$items.length - 1;
        if (willWrap && !this.options.wrap)return active;
        var delta = "prev" == direction ? -1 : 1, itemIndex = (activeIndex + delta) % this.$items.length;
        return this.$items.eq(itemIndex)
    }, Carousel.prototype.to = function (pos) {
        var that = this, activeIndex = this.getItemIndex(this.$active = this.$element.find(".item.active"));
        return pos > this.$items.length - 1 || 0 > pos ? void 0 : this.sliding ? this.$element.one("slid.bs.carousel", function () {
            that.to(pos)
        }) : activeIndex == pos ? this.pause().cycle() : this.slide(pos > activeIndex ? "next" : "prev", this.$items.eq(pos))
    }, Carousel.prototype.pause = function (e) {
        return e || (this.paused = !0), this.$element.find(".next, .prev").length && $.support.transition && (this.$element.trigger($.support.transition.end), this.cycle(!0)), this.interval = clearInterval(this.interval), this
    }, Carousel.prototype.next = function () {
        return this.sliding ? void 0 : this.slide("next")
    }, Carousel.prototype.prev = function () {
        return this.sliding ? void 0 : this.slide("prev")
    }, Carousel.prototype.slide = function (type, next) {
        var $active = this.$element.find(".item.active"), $next = next || this.getItemForDirection(type, $active), isCycling = this.interval, direction = "next" == type ? "left" : "right", that = this;
        if ($next.hasClass("active"))return this.sliding = !1;
        var relatedTarget = $next[0], slideEvent = $.Event("slide.bs.carousel", {
            relatedTarget: relatedTarget,
            direction: direction
        });
        if (this.$element.trigger(slideEvent), !slideEvent.isDefaultPrevented()) {
            if (this.sliding = !0, isCycling && this.pause(), this.$indicators.length) {
                this.$indicators.find(".active").removeClass("active");
                var $nextIndicator = $(this.$indicators.children()[this.getItemIndex($next)]);
                $nextIndicator && $nextIndicator.addClass("active")
            }
            var slidEvent = $.Event("slid.bs.carousel", {relatedTarget: relatedTarget, direction: direction});
            return $.support.transition && this.$element.hasClass("slide") ? ($next.addClass(type), $next[0].offsetWidth, $active.addClass(direction), $next.addClass(direction), $active.one("bsTransitionEnd", function () {
                $next.removeClass([type, direction].join(" ")).addClass("active"), $active.removeClass(["active", direction].join(" ")), that.sliding = !1, setTimeout(function () {
                    that.$element.trigger(slidEvent)
                }, 0)
            }).emulateTransitionEnd(Carousel.TRANSITION_DURATION)) : ($active.removeClass("active"), $next.addClass("active"), this.sliding = !1, this.$element.trigger(slidEvent)), isCycling && this.cycle(), this
        }
    };
    var old = $.fn.carousel;
    $.fn.carousel = Plugin, $.fn.carousel.Constructor = Carousel, $.fn.carousel.noConflict = function () {
        return $.fn.carousel = old, this
    };
    var clickHandler = function (e) {
        var href, $this = $(this), $target = $($this.attr("data-target") || (href = $this.attr("href")) && href.replace(/.*(?=#[^\s]+$)/, ""));
        if ($target.hasClass("carousel")) {
            var options = $.extend({}, $target.data(), $this.data()), slideIndex = $this.attr("data-slide-to");
            slideIndex && (options.interval = !1), Plugin.call($target, options), slideIndex && $target.data("bs.carousel").to(slideIndex), e.preventDefault()
        }
    };
    $(document).on("click.bs.carousel.data-api", "[data-slide]", clickHandler).on("click.bs.carousel.data-api", "[data-slide-to]", clickHandler), $(window).on("load", function () {
        $('[data-ride="carousel"]').each(function () {
            var $carousel = $(this);
            Plugin.call($carousel, $carousel.data())
        })
    })
}(jQuery), +function ($) {
    "use strict";
    function getTargetFromTrigger($trigger) {
        var href, target = $trigger.attr("data-target") || (href = $trigger.attr("href")) && href.replace(/.*(?=#[^\s]+$)/, "");
        return $(target)
    }

    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.collapse"), options = $.extend({}, Collapse.DEFAULTS, $this.data(), "object" == typeof option && option);
            !data && options.toggle && /show|hide/.test(option) && (options.toggle = !1), data || $this.data("bs.collapse", data = new Collapse(this, options)), "string" == typeof option && data[option]()
        })
    }

    var Collapse = function (element, options) {
        this.$element = $(element), this.options = $.extend({}, Collapse.DEFAULTS, options), this.$trigger = $('[data-toggle="collapse"][href="#' + element.id + '"],[data-toggle="collapse"][data-target="#' + element.id + '"]'), this.transitioning = null, this.options.parent ? this.$parent = this.getParent() : this.addAriaAndCollapsedClass(this.$element, this.$trigger), this.options.toggle && this.toggle()
    };
    Collapse.VERSION = "3.3.5", Collapse.TRANSITION_DURATION = 350, Collapse.DEFAULTS = {toggle: !0}, Collapse.prototype.dimension = function () {
        var hasWidth = this.$element.hasClass("width");
        return hasWidth ? "width" : "height"
    }, Collapse.prototype.show = function () {
        if (!this.transitioning && !this.$element.hasClass("in")) {
            var activesData, actives = this.$parent && this.$parent.children(".panel").children(".in, .collapsing");
            if (!(actives && actives.length && (activesData = actives.data("bs.collapse"), activesData && activesData.transitioning))) {
                var startEvent = $.Event("show.bs.collapse");
                if (this.$element.trigger(startEvent), !startEvent.isDefaultPrevented()) {
                    actives && actives.length && (Plugin.call(actives, "hide"), activesData || actives.data("bs.collapse", null));
                    var dimension = this.dimension();
                    this.$element.removeClass("collapse").addClass("collapsing")[dimension](0).attr("aria-expanded", !0), this.$trigger.removeClass("collapsed").attr("aria-expanded", !0), this.transitioning = 1;
                    var complete = function () {
                        this.$element.removeClass("collapsing").addClass("collapse in")[dimension](""), this.transitioning = 0, this.$element.trigger("shown.bs.collapse")
                    };
                    if (!$.support.transition)return complete.call(this);
                    var scrollSize = $.camelCase(["scroll", dimension].join("-"));
                    this.$element.one("bsTransitionEnd", $.proxy(complete, this)).emulateTransitionEnd(Collapse.TRANSITION_DURATION)[dimension](this.$element[0][scrollSize])
                }
            }
        }
    }, Collapse.prototype.hide = function () {
        if (!this.transitioning && this.$element.hasClass("in")) {
            var startEvent = $.Event("hide.bs.collapse");
            if (this.$element.trigger(startEvent), !startEvent.isDefaultPrevented()) {
                var dimension = this.dimension();
                this.$element[dimension](this.$element[dimension]())[0].offsetHeight, this.$element.addClass("collapsing").removeClass("collapse in").attr("aria-expanded", !1), this.$trigger.addClass("collapsed").attr("aria-expanded", !1), this.transitioning = 1;
                var complete = function () {
                    this.transitioning = 0, this.$element.removeClass("collapsing").addClass("collapse").trigger("hidden.bs.collapse")
                };
                return $.support.transition ? void this.$element[dimension](0).one("bsTransitionEnd", $.proxy(complete, this)).emulateTransitionEnd(Collapse.TRANSITION_DURATION) : complete.call(this)
            }
        }
    }, Collapse.prototype.toggle = function () {
        this[this.$element.hasClass("in") ? "hide" : "show"]()
    }, Collapse.prototype.getParent = function () {
        return $(this.options.parent).find('[data-toggle="collapse"][data-parent="' + this.options.parent + '"]').each($.proxy(function (i, element) {
            var $element = $(element);
            this.addAriaAndCollapsedClass(getTargetFromTrigger($element), $element)
        }, this)).end()
    }, Collapse.prototype.addAriaAndCollapsedClass = function ($element, $trigger) {
        var isOpen = $element.hasClass("in");
        $element.attr("aria-expanded", isOpen), $trigger.toggleClass("collapsed", !isOpen).attr("aria-expanded", isOpen)
    };
    var old = $.fn.collapse;
    $.fn.collapse = Plugin, $.fn.collapse.Constructor = Collapse, $.fn.collapse.noConflict = function () {
        return $.fn.collapse = old, this
    }, $(document).on("click.bs.collapse.data-api", '[data-toggle="collapse"]', function (e) {
        var $this = $(this);
        $this.attr("data-target") || e.preventDefault();
        var $target = getTargetFromTrigger($this), data = $target.data("bs.collapse"), option = data ? "toggle" : $this.data();
        Plugin.call($target, option)
    })
}(jQuery), +function ($) {
    "use strict";
    function getParent($this) {
        var selector = $this.attr("data-target");
        selector || (selector = $this.attr("href"), selector = selector && /#[A-Za-z]/.test(selector) && selector.replace(/.*(?=#[^\s]*$)/, ""));
        var $parent = selector && $(selector);
        return $parent && $parent.length ? $parent : $this.parent()
    }

    function clearMenus(e) {
        e && 3 === e.which || ($(backdrop).remove(), $(toggle).each(function () {
            var $this = $(this), $parent = getParent($this), relatedTarget = {relatedTarget: this};
            $parent.hasClass("open") && (e && "click" == e.type && /input|textarea/i.test(e.target.tagName) && $.contains($parent[0], e.target) || ($parent.trigger(e = $.Event("hide.bs.dropdown", relatedTarget)), e.isDefaultPrevented() || ($this.attr("aria-expanded", "false"), $parent.removeClass("open").trigger("hidden.bs.dropdown", relatedTarget))))
        }))
    }

    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.dropdown");
            data || $this.data("bs.dropdown", data = new Dropdown(this)), "string" == typeof option && data[option].call($this)
        })
    }

    var backdrop = ".dropdown-backdrop", toggle = '[data-toggle="dropdown"]', Dropdown = function (element) {
        $(element).on("click.bs.dropdown", this.toggle)
    };
    Dropdown.VERSION = "3.3.5", Dropdown.prototype.toggle = function (e) {
        var $this = $(this);
        if (!$this.is(".disabled, :disabled")) {
            var $parent = getParent($this), isActive = $parent.hasClass("open");
            if (clearMenus(), !isActive) {
                "ontouchstart" in document.documentElement && !$parent.closest(".navbar-nav").length && $(document.createElement("div")).addClass("dropdown-backdrop").insertAfter($(this)).on("click", clearMenus);
                var relatedTarget = {relatedTarget: this};
                if ($parent.trigger(e = $.Event("show.bs.dropdown", relatedTarget)), e.isDefaultPrevented())return;
                $this.trigger("focus").attr("aria-expanded", "true"), $parent.toggleClass("open").trigger("shown.bs.dropdown", relatedTarget)
            }
            return !1
        }
    }, Dropdown.prototype.keydown = function (e) {
        if (/(38|40|27|32)/.test(e.which) && !/input|textarea/i.test(e.target.tagName)) {
            var $this = $(this);
            if (e.preventDefault(), e.stopPropagation(), !$this.is(".disabled, :disabled")) {
                var $parent = getParent($this), isActive = $parent.hasClass("open");
                if (!isActive && 27 != e.which || isActive && 27 == e.which)return 27 == e.which && $parent.find(toggle).trigger("focus"), $this.trigger("click");
                var desc = " li:not(.disabled):visible a", $items = $parent.find(".dropdown-menu" + desc);
                if ($items.length) {
                    var index = $items.index(e.target);
                    38 == e.which && index > 0 && index--, 40 == e.which && index < $items.length - 1 && index++, ~index || (index = 0), $items.eq(index).trigger("focus")
                }
            }
        }
    };
    var old = $.fn.dropdown;
    $.fn.dropdown = Plugin, $.fn.dropdown.Constructor = Dropdown, $.fn.dropdown.noConflict = function () {
        return $.fn.dropdown = old, this
    }, $(document).on("click.bs.dropdown.data-api", clearMenus).on("click.bs.dropdown.data-api", ".dropdown form", function (e) {
        e.stopPropagation()
    }).on("click.bs.dropdown.data-api", toggle, Dropdown.prototype.toggle).on("keydown.bs.dropdown.data-api", toggle, Dropdown.prototype.keydown).on("keydown.bs.dropdown.data-api", ".dropdown-menu", Dropdown.prototype.keydown)
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option, _relatedTarget) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.modal"), options = $.extend({}, Modal.DEFAULTS, $this.data(), "object" == typeof option && option);
            data || $this.data("bs.modal", data = new Modal(this, options)), "string" == typeof option ? data[option](_relatedTarget) : options.show && data.show(_relatedTarget)
        })
    }

    var Modal = function (element, options) {
        this.options = options, this.$body = $(document.body), this.$element = $(element), this.$dialog = this.$element.find(".modal-dialog"), this.$backdrop = null, this.isShown = null, this.originalBodyPad = null, this.scrollbarWidth = 0, this.ignoreBackdropClick = !1, this.options.remote && this.$element.find(".modal-content").load(this.options.remote, $.proxy(function () {
            this.$element.trigger("loaded.bs.modal")
        }, this))
    };
    Modal.VERSION = "3.3.5", Modal.TRANSITION_DURATION = 300, Modal.BACKDROP_TRANSITION_DURATION = 150, Modal.DEFAULTS = {
        backdrop: !0,
        keyboard: !0,
        show: !0
    }, Modal.prototype.toggle = function (_relatedTarget) {
        return this.isShown ? this.hide() : this.show(_relatedTarget)
    }, Modal.prototype.show = function (_relatedTarget) {
        var that = this, e = $.Event("show.bs.modal", {relatedTarget: _relatedTarget});
        this.$element.trigger(e), this.isShown || e.isDefaultPrevented() || (this.isShown = !0, this.checkScrollbar(), this.setScrollbar(), this.$body.addClass("modal-open"), this.escape(), this.resize(), this.$element.on("click.dismiss.bs.modal", '[data-dismiss="modal"]', $.proxy(this.hide, this)), this.$dialog.on("mousedown.dismiss.bs.modal", function () {
            that.$element.one("mouseup.dismiss.bs.modal", function (e) {
                $(e.target).is(that.$element) && (that.ignoreBackdropClick = !0)
            })
        }), this.backdrop(function () {
            var transition = $.support.transition && that.$element.hasClass("fade");
            that.$element.parent().length || that.$element.appendTo(that.$body), that.$element.show().scrollTop(0), that.adjustDialog(), transition && that.$element[0].offsetWidth, that.$element.addClass("in"), that.enforceFocus();
            var e = $.Event("shown.bs.modal", {relatedTarget: _relatedTarget});
            transition ? that.$dialog.one("bsTransitionEnd", function () {
                that.$element.trigger("focus").trigger(e)
            }).emulateTransitionEnd(Modal.TRANSITION_DURATION) : that.$element.trigger("focus").trigger(e)
        }))
    }, Modal.prototype.hide = function (e) {
        e && e.preventDefault(), e = $.Event("hide.bs.modal"), this.$element.trigger(e), this.isShown && !e.isDefaultPrevented() && (this.isShown = !1, this.escape(), this.resize(), $(document).off("focusin.bs.modal"), this.$element.removeClass("in").off("click.dismiss.bs.modal").off("mouseup.dismiss.bs.modal"), this.$dialog.off("mousedown.dismiss.bs.modal"), $.support.transition && this.$element.hasClass("fade") ? this.$element.one("bsTransitionEnd", $.proxy(this.hideModal, this)).emulateTransitionEnd(Modal.TRANSITION_DURATION) : this.hideModal())
    }, Modal.prototype.enforceFocus = function () {
        $(document).off("focusin.bs.modal").on("focusin.bs.modal", $.proxy(function (e) {
            this.$element[0] === e.target || this.$element.has(e.target).length || this.$element.trigger("focus")
        }, this))
    }, Modal.prototype.escape = function () {
        this.isShown && this.options.keyboard ? this.$element.on("keydown.dismiss.bs.modal", $.proxy(function (e) {
            27 == e.which && this.hide()
        }, this)) : this.isShown || this.$element.off("keydown.dismiss.bs.modal")
    }, Modal.prototype.resize = function () {
        this.isShown ? $(window).on("resize.bs.modal", $.proxy(this.handleUpdate, this)) : $(window).off("resize.bs.modal")
    }, Modal.prototype.hideModal = function () {
        var that = this;
        this.$element.hide(), this.backdrop(function () {
            that.$body.removeClass("modal-open"), that.resetAdjustments(), that.resetScrollbar(), that.$element.trigger("hidden.bs.modal")
        })
    }, Modal.prototype.removeBackdrop = function () {
        this.$backdrop && this.$backdrop.remove(), this.$backdrop = null
    }, Modal.prototype.backdrop = function (callback) {
        var that = this, animate = this.$element.hasClass("fade") ? "fade" : "";
        if (this.isShown && this.options.backdrop) {
            var doAnimate = $.support.transition && animate;
            if (this.$backdrop = $(document.createElement("div")).addClass("modal-backdrop " + animate).appendTo(this.$body), this.$element.on("click.dismiss.bs.modal", $.proxy(function (e) {
                    return this.ignoreBackdropClick ? void(this.ignoreBackdropClick = !1) : void(e.target === e.currentTarget && ("static" == this.options.backdrop ? this.$element[0].focus() : this.hide()))
                }, this)), doAnimate && this.$backdrop[0].offsetWidth, this.$backdrop.addClass("in"), !callback)return;
            doAnimate ? this.$backdrop.one("bsTransitionEnd", callback).emulateTransitionEnd(Modal.BACKDROP_TRANSITION_DURATION) : callback()
        } else if (!this.isShown && this.$backdrop) {
            this.$backdrop.removeClass("in");
            var callbackRemove = function () {
                that.removeBackdrop(), callback && callback()
            };
            $.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one("bsTransitionEnd", callbackRemove).emulateTransitionEnd(Modal.BACKDROP_TRANSITION_DURATION) : callbackRemove()
        } else callback && callback()
    }, Modal.prototype.handleUpdate = function () {
        this.adjustDialog()
    }, Modal.prototype.adjustDialog = function () {
        var modalIsOverflowing = this.$element[0].scrollHeight > document.documentElement.clientHeight;
        this.$element.css({
            paddingLeft: !this.bodyIsOverflowing && modalIsOverflowing ? this.scrollbarWidth : "",
            paddingRight: this.bodyIsOverflowing && !modalIsOverflowing ? this.scrollbarWidth : ""
        })
    }, Modal.prototype.resetAdjustments = function () {
        this.$element.css({paddingLeft: "", paddingRight: ""})
    }, Modal.prototype.checkScrollbar = function () {
        var fullWindowWidth = window.innerWidth;
        if (!fullWindowWidth) {
            var documentElementRect = document.documentElement.getBoundingClientRect();
            fullWindowWidth = documentElementRect.right - Math.abs(documentElementRect.left)
        }
        this.bodyIsOverflowing = document.body.clientWidth < fullWindowWidth, this.scrollbarWidth = this.measureScrollbar()
    }, Modal.prototype.setScrollbar = function () {
        var bodyPad = parseInt(this.$body.css("padding-right") || 0, 10);
        this.originalBodyPad = document.body.style.paddingRight || "", this.bodyIsOverflowing && this.$body.css("padding-right", bodyPad + this.scrollbarWidth)
    }, Modal.prototype.resetScrollbar = function () {
        this.$body.css("padding-right", this.originalBodyPad)
    }, Modal.prototype.measureScrollbar = function () {
        var scrollDiv = document.createElement("div");
        scrollDiv.className = "modal-scrollbar-measure", this.$body.append(scrollDiv);
        var scrollbarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth;
        return this.$body[0].removeChild(scrollDiv), scrollbarWidth
    };
    var old = $.fn.modal;
    $.fn.modal = Plugin, $.fn.modal.Constructor = Modal, $.fn.modal.noConflict = function () {
        return $.fn.modal = old, this
    }, $(document).on("click.bs.modal.data-api", '[data-toggle="modal"]', function (e) {
        var $this = $(this), href = $this.attr("href"), $target = $($this.attr("data-target") || href && href.replace(/.*(?=#[^\s]+$)/, "")), option = $target.data("bs.modal") ? "toggle" : $.extend({remote: !/#/.test(href) && href}, $target.data(), $this.data());
        $this.is("a") && e.preventDefault(), $target.one("show.bs.modal", function (showEvent) {
            showEvent.isDefaultPrevented() || $target.one("hidden.bs.modal", function () {
                $this.is(":visible") && $this.trigger("focus")
            })
        }), Plugin.call($target, option, this)
    })
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.tooltip"), options = "object" == typeof option && option;
            (data || !/destroy|hide/.test(option)) && (data || $this.data("bs.tooltip", data = new Tooltip(this, options)), "string" == typeof option && data[option]())
        })
    }

    var Tooltip = function (element, options) {
        this.type = null, this.options = null, this.enabled = null, this.timeout = null, this.hoverState = null, this.$element = null, this.inState = null, this.init("tooltip", element, options)
    };
    Tooltip.VERSION = "3.3.5", Tooltip.TRANSITION_DURATION = 150, Tooltip.DEFAULTS = {
        animation: !0,
        placement: "top",
        selector: !1,
        template: '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
        trigger: "hover focus",
        title: "",
        delay: 0,
        html: !1,
        container: !1,
        viewport: {selector: "body", padding: 0}
    }, Tooltip.prototype.init = function (type, element, options) {
        if (this.enabled = !0, this.type = type, this.$element = $(element), this.options = this.getOptions(options), this.$viewport = this.options.viewport && $($.isFunction(this.options.viewport) ? this.options.viewport.call(this, this.$element) : this.options.viewport.selector || this.options.viewport), this.inState = {
                click: !1,
                hover: !1,
                focus: !1
            }, this.$element[0] instanceof document.constructor && !this.options.selector)throw new Error("`selector` option must be specified when initializing " + this.type + " on the window.document object!");
        for (var triggers = this.options.trigger.split(" "), i = triggers.length; i--;) {
            var trigger = triggers[i];
            if ("click" == trigger)this.$element.on("click." + this.type, this.options.selector, $.proxy(this.toggle, this)); else if ("manual" != trigger) {
                var eventIn = "hover" == trigger ? "mouseenter" : "focusin", eventOut = "hover" == trigger ? "mouseleave" : "focusout";
                this.$element.on(eventIn + "." + this.type, this.options.selector, $.proxy(this.enter, this)), this.$element.on(eventOut + "." + this.type, this.options.selector, $.proxy(this.leave, this))
            }
        }
        this.options.selector ? this._options = $.extend({}, this.options, {
            trigger: "manual",
            selector: ""
        }) : this.fixTitle()
    }, Tooltip.prototype.getDefaults = function () {
        return Tooltip.DEFAULTS
    }, Tooltip.prototype.getOptions = function (options) {
        return options = $.extend({}, this.getDefaults(), this.$element.data(), options), options.delay && "number" == typeof options.delay && (options.delay = {
            show: options.delay,
            hide: options.delay
        }), options
    }, Tooltip.prototype.getDelegateOptions = function () {
        var options = {}, defaults = this.getDefaults();
        return this._options && $.each(this._options, function (key, value) {
            defaults[key] != value && (options[key] = value)
        }), options
    }, Tooltip.prototype.enter = function (obj) {
        var self = obj instanceof this.constructor ? obj : $(obj.currentTarget).data("bs." + this.type);
        return self || (self = new this.constructor(obj.currentTarget, this.getDelegateOptions()), $(obj.currentTarget).data("bs." + this.type, self)), obj instanceof $.Event && (self.inState["focusin" == obj.type ? "focus" : "hover"] = !0), self.tip().hasClass("in") || "in" == self.hoverState ? void(self.hoverState = "in") : (clearTimeout(self.timeout), self.hoverState = "in", self.options.delay && self.options.delay.show ? void(self.timeout = setTimeout(function () {
            "in" == self.hoverState && self.show()
        }, self.options.delay.show)) : self.show())
    }, Tooltip.prototype.isInStateTrue = function () {
        for (var key in this.inState)if (this.inState[key])return !0;
        return !1
    }, Tooltip.prototype.leave = function (obj) {
        var self = obj instanceof this.constructor ? obj : $(obj.currentTarget).data("bs." + this.type);
        return self || (self = new this.constructor(obj.currentTarget, this.getDelegateOptions()), $(obj.currentTarget).data("bs." + this.type, self)), obj instanceof $.Event && (self.inState["focusout" == obj.type ? "focus" : "hover"] = !1), self.isInStateTrue() ? void 0 : (clearTimeout(self.timeout), self.hoverState = "out", self.options.delay && self.options.delay.hide ? void(self.timeout = setTimeout(function () {
            "out" == self.hoverState && self.hide()
        }, self.options.delay.hide)) : self.hide())
    }, Tooltip.prototype.show = function () {
        var e = $.Event("show.bs." + this.type);
        if (this.hasContent() && this.enabled) {
            this.$element.trigger(e);
            var inDom = $.contains(this.$element[0].ownerDocument.documentElement, this.$element[0]);
            if (e.isDefaultPrevented() || !inDom)return;
            var that = this, $tip = this.tip(), tipId = this.getUID(this.type);
            this.setContent(), $tip.attr("id", tipId), this.$element.attr("aria-describedby", tipId), this.options.animation && $tip.addClass("fade");
            var placement = "function" == typeof this.options.placement ? this.options.placement.call(this, $tip[0], this.$element[0]) : this.options.placement, autoToken = /\s?auto?\s?/i, autoPlace = autoToken.test(placement);
            autoPlace && (placement = placement.replace(autoToken, "") || "top"), $tip.detach().css({
                top: 0,
                left: 0,
                display: "block"
            }).addClass(placement).data("bs." + this.type, this), this.options.container ? $tip.appendTo(this.options.container) : $tip.insertAfter(this.$element), this.$element.trigger("inserted.bs." + this.type);
            var pos = this.getPosition(), actualWidth = $tip[0].offsetWidth, actualHeight = $tip[0].offsetHeight;
            if (autoPlace) {
                var orgPlacement = placement, viewportDim = this.getPosition(this.$viewport);
                placement = "bottom" == placement && pos.bottom + actualHeight > viewportDim.bottom ? "top" : "top" == placement && pos.top - actualHeight < viewportDim.top ? "bottom" : "right" == placement && pos.right + actualWidth > viewportDim.width ? "left" : "left" == placement && pos.left - actualWidth < viewportDim.left ? "right" : placement, $tip.removeClass(orgPlacement).addClass(placement)
            }
            var calculatedOffset = this.getCalculatedOffset(placement, pos, actualWidth, actualHeight);
            this.applyPlacement(calculatedOffset, placement);
            var complete = function () {
                var prevHoverState = that.hoverState;
                that.$element.trigger("shown.bs." + that.type), that.hoverState = null, "out" == prevHoverState && that.leave(that)
            };
            $.support.transition && this.$tip.hasClass("fade") ? $tip.one("bsTransitionEnd", complete).emulateTransitionEnd(Tooltip.TRANSITION_DURATION) : complete()
        }
    }, Tooltip.prototype.applyPlacement = function (offset, placement) {
        var $tip = this.tip(), width = $tip[0].offsetWidth, height = $tip[0].offsetHeight, marginTop = parseInt($tip.css("margin-top"), 10), marginLeft = parseInt($tip.css("margin-left"), 10);
        isNaN(marginTop) && (marginTop = 0), isNaN(marginLeft) && (marginLeft = 0), offset.top += marginTop, offset.left += marginLeft, $.offset.setOffset($tip[0], $.extend({
            using: function (props) {
                $tip.css({top: Math.round(props.top), left: Math.round(props.left)})
            }
        }, offset), 0), $tip.addClass("in");
        var actualWidth = $tip[0].offsetWidth, actualHeight = $tip[0].offsetHeight;
        "top" == placement && actualHeight != height && (offset.top = offset.top + height - actualHeight);
        var delta = this.getViewportAdjustedDelta(placement, offset, actualWidth, actualHeight);
        delta.left ? offset.left += delta.left : offset.top += delta.top;
        var isVertical = /top|bottom/.test(placement), arrowDelta = isVertical ? 2 * delta.left - width + actualWidth : 2 * delta.top - height + actualHeight, arrowOffsetPosition = isVertical ? "offsetWidth" : "offsetHeight";
        $tip.offset(offset), this.replaceArrow(arrowDelta, $tip[0][arrowOffsetPosition], isVertical)
    }, Tooltip.prototype.replaceArrow = function (delta, dimension, isVertical) {
        this.arrow().css(isVertical ? "left" : "top", 50 * (1 - delta / dimension) + "%").css(isVertical ? "top" : "left", "")
    }, Tooltip.prototype.setContent = function () {
        var $tip = this.tip(), title = this.getTitle();
        $tip.find(".tooltip-inner")[this.options.html ? "html" : "text"](title), $tip.removeClass("fade in top bottom left right")
    }, Tooltip.prototype.hide = function (callback) {
        function complete() {
            "in" != that.hoverState && $tip.detach(), that.$element.removeAttr("aria-describedby").trigger("hidden.bs." + that.type), callback && callback()
        }

        var that = this, $tip = $(this.$tip), e = $.Event("hide.bs." + this.type);
        return this.$element.trigger(e), e.isDefaultPrevented() ? void 0 : ($tip.removeClass("in"), $.support.transition && $tip.hasClass("fade") ? $tip.one("bsTransitionEnd", complete).emulateTransitionEnd(Tooltip.TRANSITION_DURATION) : complete(), this.hoverState = null, this)
    }, Tooltip.prototype.fixTitle = function () {
        var $e = this.$element;
        ($e.attr("title") || "string" != typeof $e.attr("data-original-title")) && $e.attr("data-original-title", $e.attr("title") || "").attr("title", "")
    }, Tooltip.prototype.hasContent = function () {
        return this.getTitle()
    }, Tooltip.prototype.getPosition = function ($element) {
        $element = $element || this.$element;
        var el = $element[0], isBody = "BODY" == el.tagName, elRect = el.getBoundingClientRect();
        null == elRect.width && (elRect = $.extend({}, elRect, {
            width: elRect.right - elRect.left,
            height: elRect.bottom - elRect.top
        }));
        var elOffset = isBody ? {
            top: 0,
            left: 0
        } : $element.offset(), scroll = {scroll: isBody ? document.documentElement.scrollTop || document.body.scrollTop : $element.scrollTop()}, outerDims = isBody ? {
            width: $(window).width(),
            height: $(window).height()
        } : null;
        return $.extend({}, elRect, scroll, outerDims, elOffset)
    }, Tooltip.prototype.getCalculatedOffset = function (placement, pos, actualWidth, actualHeight) {
        return "bottom" == placement ? {
            top: pos.top + pos.height,
            left: pos.left + pos.width / 2 - actualWidth / 2
        } : "top" == placement ? {
            top: pos.top - actualHeight,
            left: pos.left + pos.width / 2 - actualWidth / 2
        } : "left" == placement ? {
            top: pos.top + pos.height / 2 - actualHeight / 2,
            left: pos.left - actualWidth
        } : {top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left + pos.width}
    }, Tooltip.prototype.getViewportAdjustedDelta = function (placement, pos, actualWidth, actualHeight) {
        var delta = {top: 0, left: 0};
        if (!this.$viewport)return delta;
        var viewportPadding = this.options.viewport && this.options.viewport.padding || 0, viewportDimensions = this.getPosition(this.$viewport);
        if (/right|left/.test(placement)) {
            var topEdgeOffset = pos.top - viewportPadding - viewportDimensions.scroll, bottomEdgeOffset = pos.top + viewportPadding - viewportDimensions.scroll + actualHeight;
            topEdgeOffset < viewportDimensions.top ? delta.top = viewportDimensions.top - topEdgeOffset : bottomEdgeOffset > viewportDimensions.top + viewportDimensions.height && (delta.top = viewportDimensions.top + viewportDimensions.height - bottomEdgeOffset)
        } else {
            var leftEdgeOffset = pos.left - viewportPadding, rightEdgeOffset = pos.left + viewportPadding + actualWidth;
            leftEdgeOffset < viewportDimensions.left ? delta.left = viewportDimensions.left - leftEdgeOffset : rightEdgeOffset > viewportDimensions.right && (delta.left = viewportDimensions.left + viewportDimensions.width - rightEdgeOffset)
        }
        return delta
    }, Tooltip.prototype.getTitle = function () {
        var title, $e = this.$element, o = this.options;
        return title = $e.attr("data-original-title") || ("function" == typeof o.title ? o.title.call($e[0]) : o.title)
    }, Tooltip.prototype.getUID = function (prefix) {
        do prefix += ~~(1e6 * Math.random()); while (document.getElementById(prefix));
        return prefix
    }, Tooltip.prototype.tip = function () {
        if (!this.$tip && (this.$tip = $(this.options.template), 1 != this.$tip.length))throw new Error(this.type + " `template` option must consist of exactly 1 top-level element!");
        return this.$tip
    }, Tooltip.prototype.arrow = function () {
        return this.$arrow = this.$arrow || this.tip().find(".tooltip-arrow")
    }, Tooltip.prototype.enable = function () {
        this.enabled = !0
    }, Tooltip.prototype.disable = function () {
        this.enabled = !1
    }, Tooltip.prototype.toggleEnabled = function () {
        this.enabled = !this.enabled
    }, Tooltip.prototype.toggle = function (e) {
        var self = this;
        e && (self = $(e.currentTarget).data("bs." + this.type), self || (self = new this.constructor(e.currentTarget, this.getDelegateOptions()), $(e.currentTarget).data("bs." + this.type, self))), e ? (self.inState.click = !self.inState.click, self.isInStateTrue() ? self.enter(self) : self.leave(self)) : self.tip().hasClass("in") ? self.leave(self) : self.enter(self)
    }, Tooltip.prototype.destroy = function () {
        var that = this;
        clearTimeout(this.timeout), this.hide(function () {
            that.$element.off("." + that.type).removeData("bs." + that.type), that.$tip && that.$tip.detach(), that.$tip = null, that.$arrow = null, that.$viewport = null
        })
    };
    var old = $.fn.tooltip;
    $.fn.tooltip = Plugin, $.fn.tooltip.Constructor = Tooltip, $.fn.tooltip.noConflict = function () {
        return $.fn.tooltip = old, this
    }
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.popover"), options = "object" == typeof option && option;
            (data || !/destroy|hide/.test(option)) && (data || $this.data("bs.popover", data = new Popover(this, options)), "string" == typeof option && data[option]())
        })
    }

    var Popover = function (element, options) {
        this.init("popover", element, options)
    };
    if (!$.fn.tooltip)throw new Error("Popover requires tooltip.js");
    Popover.VERSION = "3.3.5", Popover.DEFAULTS = $.extend({}, $.fn.tooltip.Constructor.DEFAULTS, {
        placement: "right",
        trigger: "click",
        content: "",
        template: '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
    }), Popover.prototype = $.extend({}, $.fn.tooltip.Constructor.prototype), Popover.prototype.constructor = Popover, Popover.prototype.getDefaults = function () {
        return Popover.DEFAULTS
    }, Popover.prototype.setContent = function () {
        var $tip = this.tip(), title = this.getTitle(), content = this.getContent();
        $tip.find(".popover-title")[this.options.html ? "html" : "text"](title), $tip.find(".popover-content").children().detach().end()[this.options.html ? "string" == typeof content ? "html" : "append" : "text"](content), $tip.removeClass("fade top bottom left right in"), $tip.find(".popover-title").html() || $tip.find(".popover-title").hide()
    }, Popover.prototype.hasContent = function () {
        return this.getTitle() || this.getContent()
    }, Popover.prototype.getContent = function () {
        var $e = this.$element, o = this.options;
        return $e.attr("data-content") || ("function" == typeof o.content ? o.content.call($e[0]) : o.content)
    }, Popover.prototype.arrow = function () {
        return this.$arrow = this.$arrow || this.tip().find(".arrow")
    };
    var old = $.fn.popover;
    $.fn.popover = Plugin, $.fn.popover.Constructor = Popover, $.fn.popover.noConflict = function () {
        return $.fn.popover = old, this
    }
}(jQuery), +function ($) {
    "use strict";
    function ScrollSpy(element, options) {
        this.$body = $(document.body), this.$scrollElement = $($(element).is(document.body) ? window : element), this.options = $.extend({}, ScrollSpy.DEFAULTS, options), this.selector = (this.options.target || "") + " .nav li > a", this.offsets = [], this.targets = [], this.activeTarget = null, this.scrollHeight = 0, this.$scrollElement.on("scroll.bs.scrollspy", $.proxy(this.process, this)), this.refresh(), this.process()
    }

    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.scrollspy"), options = "object" == typeof option && option;
            data || $this.data("bs.scrollspy", data = new ScrollSpy(this, options)), "string" == typeof option && data[option]()
        })
    }

    ScrollSpy.VERSION = "3.3.5", ScrollSpy.DEFAULTS = {offset: 10}, ScrollSpy.prototype.getScrollHeight = function () {
        return this.$scrollElement[0].scrollHeight || Math.max(this.$body[0].scrollHeight, document.documentElement.scrollHeight)
    }, ScrollSpy.prototype.refresh = function () {
        var that = this, offsetMethod = "offset", offsetBase = 0;
        this.offsets = [], this.targets = [], this.scrollHeight = this.getScrollHeight(), $.isWindow(this.$scrollElement[0]) || (offsetMethod = "position", offsetBase = this.$scrollElement.scrollTop()), this.$body.find(this.selector).map(function () {
            var $el = $(this), href = $el.data("target") || $el.attr("href"), $href = /^#./.test(href) && $(href);
            return $href && $href.length && $href.is(":visible") && [[$href[offsetMethod]().top + offsetBase, href]] || null
        }).sort(function (a, b) {
            return a[0] - b[0]
        }).each(function () {
            that.offsets.push(this[0]), that.targets.push(this[1])
        })
    }, ScrollSpy.prototype.process = function () {
        var i, scrollTop = this.$scrollElement.scrollTop() + this.options.offset, scrollHeight = this.getScrollHeight(), maxScroll = this.options.offset + scrollHeight - this.$scrollElement.height(), offsets = this.offsets, targets = this.targets, activeTarget = this.activeTarget;
        if (this.scrollHeight != scrollHeight && this.refresh(), scrollTop >= maxScroll)return activeTarget != (i = targets[targets.length - 1]) && this.activate(i);
        if (activeTarget && scrollTop < offsets[0])return this.activeTarget = null, this.clear();
        for (i = offsets.length; i--;)activeTarget != targets[i] && scrollTop >= offsets[i] && (void 0 === offsets[i + 1] || scrollTop < offsets[i + 1]) && this.activate(targets[i])
    }, ScrollSpy.prototype.activate = function (target) {
        this.activeTarget = target, this.clear();
        var selector = this.selector + '[data-target="' + target + '"],' + this.selector + '[href="' + target + '"]', active = $(selector).parents("li").addClass("active");
        active.parent(".dropdown-menu").length && (active = active.closest("li.dropdown").addClass("active")), active.trigger("activate.bs.scrollspy")
    }, ScrollSpy.prototype.clear = function () {
        $(this.selector).parentsUntil(this.options.target, ".active").removeClass("active")
    };
    var old = $.fn.scrollspy;
    $.fn.scrollspy = Plugin, $.fn.scrollspy.Constructor = ScrollSpy, $.fn.scrollspy.noConflict = function () {
        return $.fn.scrollspy = old, this
    }, $(window).on("load.bs.scrollspy.data-api", function () {
        $('[data-spy="scroll"]').each(function () {
            var $spy = $(this);
            Plugin.call($spy, $spy.data())
        })
    })
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.tab");
            data || $this.data("bs.tab", data = new Tab(this)), "string" == typeof option && data[option]()
        })
    }

    var Tab = function (element) {
        this.element = $(element)
    };
    Tab.VERSION = "3.3.5", Tab.TRANSITION_DURATION = 150, Tab.prototype.show = function () {
        var $this = this.element, $ul = $this.closest("ul:not(.dropdown-menu)"), selector = $this.data("target");
        if (selector || (selector = $this.attr("href"), selector = selector && selector.replace(/.*(?=#[^\s]*$)/, "")), !$this.parent("li").hasClass("active")) {
            var $previous = $ul.find(".active:last a"), hideEvent = $.Event("hide.bs.tab", {relatedTarget: $this[0]}), showEvent = $.Event("show.bs.tab", {relatedTarget: $previous[0]});
            if ($previous.trigger(hideEvent), $this.trigger(showEvent), !showEvent.isDefaultPrevented() && !hideEvent.isDefaultPrevented()) {
                var $target = $(selector);
                this.activate($this.closest("li"), $ul), this.activate($target, $target.parent(), function () {
                    $previous.trigger({
                        type: "hidden.bs.tab",
                        relatedTarget: $this[0]
                    }), $this.trigger({type: "shown.bs.tab", relatedTarget: $previous[0]})
                })
            }
        }
    }, Tab.prototype.activate = function (element, container, callback) {
        function next() {
            $active.removeClass("active").find("> .dropdown-menu > .active").removeClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded", !1), element.addClass("active").find('[data-toggle="tab"]').attr("aria-expanded", !0), transition ? (element[0].offsetWidth, element.addClass("in")) : element.removeClass("fade"), element.parent(".dropdown-menu").length && element.closest("li.dropdown").addClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded", !0), callback && callback()
        }

        var $active = container.find("> .active"), transition = callback && $.support.transition && ($active.length && $active.hasClass("fade") || !!container.find("> .fade").length);
        $active.length && transition ? $active.one("bsTransitionEnd", next).emulateTransitionEnd(Tab.TRANSITION_DURATION) : next(), $active.removeClass("in")
    };
    var old = $.fn.tab;
    $.fn.tab = Plugin, $.fn.tab.Constructor = Tab, $.fn.tab.noConflict = function () {
        return $.fn.tab = old, this
    };
    var clickHandler = function (e) {
        e.preventDefault(), Plugin.call($(this), "show")
    };
    $(document).on("click.bs.tab.data-api", '[data-toggle="tab"]', clickHandler).on("click.bs.tab.data-api", '[data-toggle="pill"]', clickHandler)
}(jQuery), +function ($) {
    "use strict";
    function Plugin(option) {
        return this.each(function () {
            var $this = $(this), data = $this.data("bs.affix"), options = "object" == typeof option && option;
            data || $this.data("bs.affix", data = new Affix(this, options)), "string" == typeof option && data[option]()
        })
    }

    var Affix = function (element, options) {
        this.options = $.extend({}, Affix.DEFAULTS, options), this.$target = $(this.options.target).on("scroll.bs.affix.data-api", $.proxy(this.checkPosition, this)).on("click.bs.affix.data-api", $.proxy(this.checkPositionWithEventLoop, this)), this.$element = $(element), this.affixed = null, this.unpin = null, this.pinnedOffset = null, this.checkPosition()
    };
    Affix.VERSION = "3.3.5", Affix.RESET = "affix affix-top affix-bottom", Affix.DEFAULTS = {
        offset: 0,
        target: window
    }, Affix.prototype.getState = function (scrollHeight, height, offsetTop, offsetBottom) {
        var scrollTop = this.$target.scrollTop(), position = this.$element.offset(), targetHeight = this.$target.height();
        if (null != offsetTop && "top" == this.affixed)return offsetTop > scrollTop ? "top" : !1;
        if ("bottom" == this.affixed)return null != offsetTop ? scrollTop + this.unpin <= position.top ? !1 : "bottom" : scrollHeight - offsetBottom >= scrollTop + targetHeight ? !1 : "bottom";
        var initializing = null == this.affixed, colliderTop = initializing ? scrollTop : position.top, colliderHeight = initializing ? targetHeight : height;
        return null != offsetTop && offsetTop >= scrollTop ? "top" : null != offsetBottom && colliderTop + colliderHeight >= scrollHeight - offsetBottom ? "bottom" : !1
    }, Affix.prototype.getPinnedOffset = function () {
        if (this.pinnedOffset)return this.pinnedOffset;
        this.$element.removeClass(Affix.RESET).addClass("affix");
        var scrollTop = this.$target.scrollTop(), position = this.$element.offset();
        return this.pinnedOffset = position.top - scrollTop
    }, Affix.prototype.checkPositionWithEventLoop = function () {
        setTimeout($.proxy(this.checkPosition, this), 1)
    }, Affix.prototype.checkPosition = function () {
        if (this.$element.is(":visible")) {
            var height = this.$element.height(), offset = this.options.offset, offsetTop = offset.top, offsetBottom = offset.bottom, scrollHeight = Math.max($(document).height(), $(document.body).height());
            "object" != typeof offset && (offsetBottom = offsetTop = offset), "function" == typeof offsetTop && (offsetTop = offset.top(this.$element)), "function" == typeof offsetBottom && (offsetBottom = offset.bottom(this.$element));
            var affix = this.getState(scrollHeight, height, offsetTop, offsetBottom);
            if (this.affixed != affix) {
                null != this.unpin && this.$element.css("top", "");
                var affixType = "affix" + (affix ? "-" + affix : ""), e = $.Event(affixType + ".bs.affix");
                if (this.$element.trigger(e), e.isDefaultPrevented())return;
                this.affixed = affix, this.unpin = "bottom" == affix ? this.getPinnedOffset() : null, this.$element.removeClass(Affix.RESET).addClass(affixType).trigger(affixType.replace("affix", "affixed") + ".bs.affix")
            }
            "bottom" == affix && this.$element.offset({top: scrollHeight - height - offsetBottom})
        }
    };
    var old = $.fn.affix;
    $.fn.affix = Plugin, $.fn.affix.Constructor = Affix, $.fn.affix.noConflict = function () {
        return $.fn.affix = old, this
    }, $(window).on("load", function () {
        $('[data-spy="affix"]').each(function () {
            var $spy = $(this), data = $spy.data();
            data.offset = data.offset || {}, null != data.offsetBottom && (data.offset.bottom = data.offsetBottom), null != data.offsetTop && (data.offset.top = data.offsetTop), Plugin.call($spy, data)
        })
    })
}(jQuery), !function (a) {
    function b() {
    }

    function c(a) {
        function c(b) {
            b.prototype.option || (b.prototype.option = function (b) {
                a.isPlainObject(b) && (this.options = a.extend(!0, this.options, b))
            })
        }

        function e(b, c) {
            a.fn[b] = function (e) {
                if ("string" == typeof e) {
                    for (var g = d.call(arguments, 1), h = 0, i = this.length; i > h; h++) {
                        var j = this[h], k = a.data(j, b);
                        if (k)if (a.isFunction(k[e]) && "_" !== e.charAt(0)) {
                            var l = k[e].apply(k, g);
                            if (void 0 !== l)return l
                        } else f("no such method '" + e + "' for " + b + " instance"); else f("cannot call methods on " + b + " prior to initialization; attempted to call '" + e + "'")
                    }
                    return this
                }
                return this.each(function () {
                    var d = a.data(this, b);
                    d ? (d.option(e), d._init()) : (d = new c(this, e), a.data(this, b, d))
                })
            }
        }

        if (a) {
            var f = "undefined" == typeof console ? b : function (a) {
                console.error(a)
            };
            return a.bridget = function (a, b) {
                c(b), e(a, b)
            }, a.bridget
        }
    }

    var d = Array.prototype.slice;
    "function" == typeof define && define.amd ? define("jquery-bridget/jquery.bridget", ["jquery"], c) : c("object" == typeof exports ? require("jquery") : a.jQuery)
}(window), function (a) {
    function b(b) {
        var c = a.event;
        return c.target = c.target || c.srcElement || b, c
    }

    var c = document.documentElement, d = function () {
    };
    c.addEventListener ? d = function (a, b, c) {
        a.addEventListener(b, c, !1)
    } : c.attachEvent && (d = function (a, c, d) {
        a[c + d] = d.handleEvent ? function () {
            var c = b(a);
            d.handleEvent.call(d, c)
        } : function () {
            var c = b(a);
            d.call(a, c)
        }, a.attachEvent("on" + c, a[c + d])
    });
    var e = function () {
    };
    c.removeEventListener ? e = function (a, b, c) {
        a.removeEventListener(b, c, !1)
    } : c.detachEvent && (e = function (a, b, c) {
        a.detachEvent("on" + b, a[b + c]);
        try {
            delete a[b + c]
        } catch (d) {
            a[b + c] = void 0
        }
    });
    var f = {bind: d, unbind: e};
    "function" == typeof define && define.amd ? define("eventie/eventie", f) : "object" == typeof exports ? module.exports = f : a.eventie = f
}(window), function () {
    function a() {
    }

    function b(a, b) {
        for (var c = a.length; c--;)if (a[c].listener === b)return c;
        return -1
    }

    function c(a) {
        return function () {
            return this[a].apply(this, arguments)
        }
    }

    var d = a.prototype, e = this, f = e.EventEmitter;
    d.getListeners = function (a) {
        var b, c, d = this._getEvents();
        if (a instanceof RegExp) {
            b = {};
            for (c in d)d.hasOwnProperty(c) && a.test(c) && (b[c] = d[c])
        } else b = d[a] || (d[a] = []);
        return b
    }, d.flattenListeners = function (a) {
        var b, c = [];
        for (b = 0; b < a.length; b += 1)c.push(a[b].listener);
        return c
    }, d.getListenersAsObject = function (a) {
        var b, c = this.getListeners(a);
        return c instanceof Array && (b = {}, b[a] = c), b || c
    }, d.addListener = function (a, c) {
        var d, e = this.getListenersAsObject(a), f = "object" == typeof c;
        for (d in e)e.hasOwnProperty(d) && -1 === b(e[d], c) && e[d].push(f ? c : {listener: c, once: !1});
        return this
    }, d.on = c("addListener"), d.addOnceListener = function (a, b) {
        return this.addListener(a, {listener: b, once: !0})
    }, d.once = c("addOnceListener"), d.defineEvent = function (a) {
        return this.getListeners(a), this
    }, d.defineEvents = function (a) {
        for (var b = 0; b < a.length; b += 1)this.defineEvent(a[b]);
        return this
    }, d.removeListener = function (a, c) {
        var d, e, f = this.getListenersAsObject(a);
        for (e in f)f.hasOwnProperty(e) && (d = b(f[e], c), -1 !== d && f[e].splice(d, 1));
        return this
    }, d.off = c("removeListener"), d.addListeners = function (a, b) {
        return this.manipulateListeners(!1, a, b)
    }, d.removeListeners = function (a, b) {
        return this.manipulateListeners(!0, a, b)
    }, d.manipulateListeners = function (a, b, c) {
        var d, e, f = a ? this.removeListener : this.addListener, g = a ? this.removeListeners : this.addListeners;
        if ("object" != typeof b || b instanceof RegExp)for (d = c.length; d--;)f.call(this, b, c[d]); else for (d in b)b.hasOwnProperty(d) && (e = b[d]) && ("function" == typeof e ? f.call(this, d, e) : g.call(this, d, e));
        return this
    }, d.removeEvent = function (a) {
        var b, c = typeof a, d = this._getEvents();
        if ("string" === c)delete d[a]; else if (a instanceof RegExp)for (b in d)d.hasOwnProperty(b) && a.test(b) && delete d[b]; else delete this._events;
        return this
    }, d.removeAllListeners = c("removeEvent"), d.emitEvent = function (a, b) {
        var c, d, e, f, g = this.getListenersAsObject(a);
        for (e in g)if (g.hasOwnProperty(e))for (d = g[e].length; d--;)c = g[e][d], c.once === !0 && this.removeListener(a, c.listener), f = c.listener.apply(this, b || []), f === this._getOnceReturnValue() && this.removeListener(a, c.listener);
        return this
    }, d.trigger = c("emitEvent"), d.emit = function (a) {
        var b = Array.prototype.slice.call(arguments, 1);
        return this.emitEvent(a, b)
    }, d.setOnceReturnValue = function (a) {
        return this._onceReturnValue = a, this
    }, d._getOnceReturnValue = function () {
        return this.hasOwnProperty("_onceReturnValue") ? this._onceReturnValue : !0
    }, d._getEvents = function () {
        return this._events || (this._events = {})
    }, a.noConflict = function () {
        return e.EventEmitter = f, a
    }, "function" == typeof define && define.amd ? define("eventEmitter/EventEmitter", [], function () {
        return a
    }) : "object" == typeof module && module.exports ? module.exports = a : e.EventEmitter = a
}.call(this), function (a) {
    function b(a) {
        if (a) {
            if ("string" == typeof d[a])return a;
            a = a.charAt(0).toUpperCase() + a.slice(1);
            for (var b, e = 0, f = c.length; f > e; e++)if (b = c[e] + a, "string" == typeof d[b])return b
        }
    }

    var c = "Webkit Moz ms Ms O".split(" "), d = document.documentElement.style;
    "function" == typeof define && define.amd ? define("get-style-property/get-style-property", [], function () {
        return b
    }) : "object" == typeof exports ? module.exports = b : a.getStyleProperty = b
}(window), function (a) {
    function b(a) {
        var b = parseFloat(a), c = -1 === a.indexOf("%") && !isNaN(b);
        return c && b
    }

    function c() {
    }

    function d() {
        for (var a = {
            width: 0,
            height: 0,
            innerWidth: 0,
            innerHeight: 0,
            outerWidth: 0,
            outerHeight: 0
        }, b = 0, c = g.length; c > b; b++) {
            var d = g[b];
            a[d] = 0
        }
        return a
    }

    function e(c) {
        function e() {
            if (!m) {
                m = !0;
                var d = a.getComputedStyle;
                if (j = function () {
                        var a = d ? function (a) {
                            return d(a, null)
                        } : function (a) {
                            return a.currentStyle
                        };
                        return function (b) {
                            var c = a(b);
                            return c || f("Style returned " + c + ". Are you running this code in a hidden iframe on Firefox? See http://bit.ly/getsizebug1"), c
                        }
                    }(), k = c("boxSizing")) {
                    var e = document.createElement("div");
                    e.style.width = "200px", e.style.padding = "1px 2px 3px 4px", e.style.borderStyle = "solid", e.style.borderWidth = "1px 2px 3px 4px", e.style[k] = "border-box";
                    var g = document.body || document.documentElement;
                    g.appendChild(e);
                    var h = j(e);
                    l = 200 === b(h.width), g.removeChild(e)
                }
            }
        }

        function h(a) {
            if (e(), "string" == typeof a && (a = document.querySelector(a)), a && "object" == typeof a && a.nodeType) {
                var c = j(a);
                if ("none" === c.display)return d();
                var f = {};
                f.width = a.offsetWidth, f.height = a.offsetHeight;
                for (var h = f.isBorderBox = !(!k || !c[k] || "border-box" !== c[k]), m = 0, n = g.length; n > m; m++) {
                    var o = g[m], p = c[o];
                    p = i(a, p);
                    var q = parseFloat(p);
                    f[o] = isNaN(q) ? 0 : q
                }
                var r = f.paddingLeft + f.paddingRight, s = f.paddingTop + f.paddingBottom, t = f.marginLeft + f.marginRight, u = f.marginTop + f.marginBottom, v = f.borderLeftWidth + f.borderRightWidth, w = f.borderTopWidth + f.borderBottomWidth, x = h && l, y = b(c.width);
                y !== !1 && (f.width = y + (x ? 0 : r + v));
                var z = b(c.height);
                return z !== !1 && (f.height = z + (x ? 0 : s + w)), f.innerWidth = f.width - (r + v), f.innerHeight = f.height - (s + w), f.outerWidth = f.width + t, f.outerHeight = f.height + u, f
            }
        }

        function i(b, c) {
            if (a.getComputedStyle || -1 === c.indexOf("%"))return c;
            var d = b.style, e = d.left, f = b.runtimeStyle, g = f && f.left;
            return g && (f.left = b.currentStyle.left), d.left = c, c = d.pixelLeft, d.left = e, g && (f.left = g), c
        }

        var j, k, l, m = !1;
        return h
    }

    var f = "undefined" == typeof console ? c : function (a) {
        console.error(a)
    }, g = ["paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "marginLeft", "marginRight", "marginTop", "marginBottom", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"];
    "function" == typeof define && define.amd ? define("get-size/get-size", ["get-style-property/get-style-property"], e) : "object" == typeof exports ? module.exports = e(require("desandro-get-style-property")) : a.getSize = e(a.getStyleProperty)
}(window), function (a) {
    function b(a) {
        "function" == typeof a && (b.isReady ? a() : g.push(a))
    }

    function c(a) {
        var c = "readystatechange" === a.type && "complete" !== f.readyState;
        b.isReady || c || d()
    }

    function d() {
        b.isReady = !0;
        for (var a = 0, c = g.length; c > a; a++) {
            var d = g[a];
            d()
        }
    }

    function e(e) {
        return "complete" === f.readyState ? d() : (e.bind(f, "DOMContentLoaded", c), e.bind(f, "readystatechange", c), e.bind(a, "load", c)), b
    }

    var f = a.document, g = [];
    b.isReady = !1, "function" == typeof define && define.amd ? define("doc-ready/doc-ready", ["eventie/eventie"], e) : "object" == typeof exports ? module.exports = e(require("eventie")) : a.docReady = e(a.eventie)
}(window), function (a) {
    function b(a, b) {
        return a[g](b)
    }

    function c(a) {
        if (!a.parentNode) {
            var b = document.createDocumentFragment();
            b.appendChild(a)
        }
    }

    function d(a, b) {
        c(a);
        for (var d = a.parentNode.querySelectorAll(b), e = 0, f = d.length; f > e; e++)if (d[e] === a)return !0;
        return !1
    }

    function e(a, d) {
        return c(a), b(a, d)
    }

    var f, g = function () {
        if (a.matches)return "matches";
        if (a.matchesSelector)return "matchesSelector";
        for (var b = ["webkit", "moz", "ms", "o"], c = 0, d = b.length; d > c; c++) {
            var e = b[c], f = e + "MatchesSelector";
            if (a[f])return f
        }
    }();
    if (g) {
        var h = document.createElement("div"), i = b(h, "div");
        f = i ? b : e
    } else f = d;
    "function" == typeof define && define.amd ? define("matches-selector/matches-selector", [], function () {
        return f
    }) : "object" == typeof exports ? module.exports = f : window.matchesSelector = f
}(Element.prototype), function (a, b) {
    "function" == typeof define && define.amd ? define("fizzy-ui-utils/utils", ["doc-ready/doc-ready", "matches-selector/matches-selector"], function (c, d) {
        return b(a, c, d)
    }) : "object" == typeof exports ? module.exports = b(a, require("doc-ready"), require("desandro-matches-selector")) : a.fizzyUIUtils = b(a, a.docReady, a.matchesSelector)
}(window, function (a, b, c) {
    var d = {};
    d.extend = function (a, b) {
        for (var c in b)a[c] = b[c];
        return a
    }, d.modulo = function (a, b) {
        return (a % b + b) % b
    };
    var e = Object.prototype.toString;
    d.isArray = function (a) {
        return "[object Array]" == e.call(a)
    }, d.makeArray = function (a) {
        var b = [];
        if (d.isArray(a))b = a; else if (a && "number" == typeof a.length)for (var c = 0, e = a.length; e > c; c++)b.push(a[c]); else b.push(a);
        return b
    }, d.indexOf = Array.prototype.indexOf ? function (a, b) {
        return a.indexOf(b)
    } : function (a, b) {
        for (var c = 0, d = a.length; d > c; c++)if (a[c] === b)return c;
        return -1
    }, d.removeFrom = function (a, b) {
        var c = d.indexOf(a, b);
        -1 != c && a.splice(c, 1)
    }, d.isElement = "function" == typeof HTMLElement || "object" == typeof HTMLElement ? function (a) {
        return a instanceof HTMLElement
    } : function (a) {
        return a && "object" == typeof a && 1 == a.nodeType && "string" == typeof a.nodeName
    }, d.setText = function () {
        function a(a, c) {
            b = b || (void 0 !== document.documentElement.textContent ? "textContent" : "innerText"), a[b] = c
        }

        var b;
        return a
    }(), d.getParent = function (a, b) {
        for (; a != document.body;)if (a = a.parentNode, c(a, b))return a
    }, d.getQueryElement = function (a) {
        return "string" == typeof a ? document.querySelector(a) : a
    }, d.handleEvent = function (a) {
        var b = "on" + a.type;
        this[b] && this[b](a)
    }, d.filterFindElements = function (a, b) {
        a = d.makeArray(a);
        for (var e = [], f = 0, g = a.length; g > f; f++) {
            var h = a[f];
            if (d.isElement(h))if (b) {
                c(h, b) && e.push(h);
                for (var i = h.querySelectorAll(b), j = 0, k = i.length; k > j; j++)e.push(i[j])
            } else e.push(h)
        }
        return e
    }, d.debounceMethod = function (a, b, c) {
        var d = a.prototype[b], e = b + "Timeout";
        a.prototype[b] = function () {
            var a = this[e];
            a && clearTimeout(a);
            var b = arguments, f = this;
            this[e] = setTimeout(function () {
                d.apply(f, b), delete f[e]
            }, c || 100)
        }
    }, d.toDashed = function (a) {
        return a.replace(/(.)([A-Z])/g, function (a, b, c) {
            return b + "-" + c
        }).toLowerCase()
    };
    var f = a.console;
    return d.htmlInit = function (c, e) {
        b(function () {
            for (var b = d.toDashed(e), g = document.querySelectorAll(".js-" + b), h = "data-" + b + "-options", i = 0, j = g.length; j > i; i++) {
                var k, l = g[i], m = l.getAttribute(h);
                try {
                    k = m && JSON.parse(m)
                } catch (n) {
                    f && f.error("Error parsing " + h + " on " + l.nodeName.toLowerCase() + (l.id ? "#" + l.id : "") + ": " + n);
                    continue
                }
                var o = new c(l, k), p = a.jQuery;
                p && p.data(l, e, o)
            }
        })
    }, d
}), function (a, b) {
    "function" == typeof define && define.amd ? define("outlayer/item", ["eventEmitter/EventEmitter", "get-size/get-size", "get-style-property/get-style-property", "fizzy-ui-utils/utils"], function (c, d, e, f) {
        return b(a, c, d, e, f)
    }) : "object" == typeof exports ? module.exports = b(a, require("wolfy87-eventemitter"), require("get-size"), require("desandro-get-style-property"), require("fizzy-ui-utils")) : (a.Outlayer = {}, a.Outlayer.Item = b(a, a.EventEmitter, a.getSize, a.getStyleProperty, a.fizzyUIUtils))
}(window, function (a, b, c, d, e) {
    function f(a) {
        for (var b in a)return !1;
        return b = null, !0
    }

    function g(a, b) {
        a && (this.element = a, this.layout = b, this.position = {x: 0, y: 0}, this._create())
    }

    function h(a) {
        return a.replace(/([A-Z])/g, function (a) {
            return "-" + a.toLowerCase()
        })
    }

    var i = a.getComputedStyle, j = i ? function (a) {
        return i(a, null)
    } : function (a) {
        return a.currentStyle
    }, k = d("transition"), l = d("transform"), m = k && l, n = !!d("perspective"), o = {
        WebkitTransition: "webkitTransitionEnd",
        MozTransition: "transitionend",
        OTransition: "otransitionend",
        transition: "transitionend"
    }[k], p = ["transform", "transition", "transitionDuration", "transitionProperty"], q = function () {
        for (var a = {}, b = 0, c = p.length; c > b; b++) {
            var e = p[b], f = d(e);
            f && f !== e && (a[e] = f)
        }
        return a
    }();
    e.extend(g.prototype, b.prototype), g.prototype._create = function () {
        this._transn = {ingProperties: {}, clean: {}, onEnd: {}}, this.css({position: "absolute"})
    }, g.prototype.handleEvent = function (a) {
        var b = "on" + a.type;
        this[b] && this[b](a)
    }, g.prototype.getSize = function () {
        this.size = c(this.element)
    }, g.prototype.css = function (a) {
        var b = this.element.style;
        for (var c in a) {
            var d = q[c] || c;
            b[d] = a[c]
        }
    }, g.prototype.getPosition = function () {
        var a = j(this.element), b = this.layout.options, c = b.isOriginLeft, d = b.isOriginTop, e = a[c ? "left" : "right"], f = a[d ? "top" : "bottom"], g = this.layout.size, h = -1 != e.indexOf("%") ? parseFloat(e) / 100 * g.width : parseInt(e, 10), i = -1 != f.indexOf("%") ? parseFloat(f) / 100 * g.height : parseInt(f, 10);
        h = isNaN(h) ? 0 : h, i = isNaN(i) ? 0 : i, h -= c ? g.paddingLeft : g.paddingRight, i -= d ? g.paddingTop : g.paddingBottom, this.position.x = h, this.position.y = i
    }, g.prototype.layoutPosition = function () {
        var a = this.layout.size, b = this.layout.options, c = {}, d = b.isOriginLeft ? "paddingLeft" : "paddingRight", e = b.isOriginLeft ? "left" : "right", f = b.isOriginLeft ? "right" : "left", g = this.position.x + a[d];
        c[e] = this.getXValue(g), c[f] = "";
        var h = b.isOriginTop ? "paddingTop" : "paddingBottom", i = b.isOriginTop ? "top" : "bottom", j = b.isOriginTop ? "bottom" : "top", k = this.position.y + a[h];
        c[i] = this.getYValue(k), c[j] = "", this.css(c), this.emitEvent("layout", [this])
    }, g.prototype.getXValue = function (a) {
        var b = this.layout.options;
        return b.percentPosition && !b.isHorizontal ? a / this.layout.size.width * 100 + "%" : a + "px"
    }, g.prototype.getYValue = function (a) {
        var b = this.layout.options;
        return b.percentPosition && b.isHorizontal ? a / this.layout.size.height * 100 + "%" : a + "px"
    }, g.prototype._transitionTo = function (a, b) {
        this.getPosition();
        var c = this.position.x, d = this.position.y, e = parseInt(a, 10), f = parseInt(b, 10), g = e === this.position.x && f === this.position.y;
        if (this.setPosition(a, b), g && !this.isTransitioning)return void this.layoutPosition();
        var h = a - c, i = b - d, j = {};
        j.transform = this.getTranslate(h, i), this.transition({
            to: j,
            onTransitionEnd: {transform: this.layoutPosition},
            isCleaning: !0
        })
    }, g.prototype.getTranslate = function (a, b) {
        var c = this.layout.options;
        return a = c.isOriginLeft ? a : -a, b = c.isOriginTop ? b : -b, n ? "translate3d(" + a + "px, " + b + "px, 0)" : "translate(" + a + "px, " + b + "px)"
    }, g.prototype.goTo = function (a, b) {
        this.setPosition(a, b), this.layoutPosition()
    }, g.prototype.moveTo = m ? g.prototype._transitionTo : g.prototype.goTo, g.prototype.setPosition = function (a, b) {
        this.position.x = parseInt(a, 10), this.position.y = parseInt(b, 10)
    }, g.prototype._nonTransition = function (a) {
        this.css(a.to), a.isCleaning && this._removeStyles(a.to);
        for (var b in a.onTransitionEnd)a.onTransitionEnd[b].call(this)
    }, g.prototype._transition = function (a) {
        if (!parseFloat(this.layout.options.transitionDuration))return void this._nonTransition(a);
        var b = this._transn;
        for (var c in a.onTransitionEnd)b.onEnd[c] = a.onTransitionEnd[c];
        for (c in a.to)b.ingProperties[c] = !0, a.isCleaning && (b.clean[c] = !0);
        if (a.from) {
            this.css(a.from);
            var d = this.element.offsetHeight;
            d = null
        }
        this.enableTransition(a.to), this.css(a.to), this.isTransitioning = !0
    };
    var r = "opacity," + h(q.transform || "transform");
    g.prototype.enableTransition = function () {
        this.isTransitioning || (this.css({
            transitionProperty: r,
            transitionDuration: this.layout.options.transitionDuration
        }), this.element.addEventListener(o, this, !1))
    }, g.prototype.transition = g.prototype[k ? "_transition" : "_nonTransition"], g.prototype.onwebkitTransitionEnd = function (a) {
        this.ontransitionend(a)
    }, g.prototype.onotransitionend = function (a) {
        this.ontransitionend(a)
    };
    var s = {"-webkit-transform": "transform", "-moz-transform": "transform", "-o-transform": "transform"};
    g.prototype.ontransitionend = function (a) {
        if (a.target === this.element) {
            var b = this._transn, c = s[a.propertyName] || a.propertyName;
            if (delete b.ingProperties[c], f(b.ingProperties) && this.disableTransition(), c in b.clean && (this.element.style[a.propertyName] = "", delete b.clean[c]), c in b.onEnd) {
                var d = b.onEnd[c];
                d.call(this), delete b.onEnd[c]
            }
            this.emitEvent("transitionEnd", [this])
        }
    }, g.prototype.disableTransition = function () {
        this.removeTransitionStyles(), this.element.removeEventListener(o, this, !1), this.isTransitioning = !1
    }, g.prototype._removeStyles = function (a) {
        var b = {};
        for (var c in a)b[c] = "";
        this.css(b)
    };
    var t = {transitionProperty: "", transitionDuration: ""};
    return g.prototype.removeTransitionStyles = function () {
        this.css(t)
    }, g.prototype.removeElem = function () {
        this.element.parentNode.removeChild(this.element), this.css({display: ""}), this.emitEvent("remove", [this])
    }, g.prototype.remove = function () {
        if (!k || !parseFloat(this.layout.options.transitionDuration))return void this.removeElem();
        var a = this;
        this.once("transitionEnd", function () {
            a.removeElem()
        }), this.hide()
    }, g.prototype.reveal = function () {
        delete this.isHidden, this.css({display: ""});
        var a = this.layout.options, b = {}, c = this.getHideRevealTransitionEndProperty("visibleStyle");
        b[c] = this.onRevealTransitionEnd, this.transition({
            from: a.hiddenStyle,
            to: a.visibleStyle,
            isCleaning: !0,
            onTransitionEnd: b
        })
    }, g.prototype.onRevealTransitionEnd = function () {
        this.isHidden || this.emitEvent("reveal")
    }, g.prototype.getHideRevealTransitionEndProperty = function (a) {
        var b = this.layout.options[a];
        if (b.opacity)return "opacity";
        for (var c in b)return c
    }, g.prototype.hide = function () {
        this.isHidden = !0, this.css({display: ""});
        var a = this.layout.options, b = {}, c = this.getHideRevealTransitionEndProperty("hiddenStyle");
        b[c] = this.onHideTransitionEnd, this.transition({
            from: a.visibleStyle,
            to: a.hiddenStyle,
            isCleaning: !0,
            onTransitionEnd: b
        })
    }, g.prototype.onHideTransitionEnd = function () {
        this.isHidden && (this.css({display: "none"}), this.emitEvent("hide"))
    }, g.prototype.destroy = function () {
        this.css({position: "", left: "", right: "", top: "", bottom: "", transition: "", transform: ""})
    }, g
}), function (a, b) {
    "function" == typeof define && define.amd ? define("outlayer/outlayer", ["eventie/eventie", "eventEmitter/EventEmitter", "get-size/get-size", "fizzy-ui-utils/utils", "./item"], function (c, d, e, f, g) {
        return b(a, c, d, e, f, g)
    }) : "object" == typeof exports ? module.exports = b(a, require("eventie"), require("wolfy87-eventemitter"), require("get-size"), require("fizzy-ui-utils"), require("./item")) : a.Outlayer = b(a, a.eventie, a.EventEmitter, a.getSize, a.fizzyUIUtils, a.Outlayer.Item)
}(window, function (a, b, c, d, e, f) {
    function g(a, b) {
        var c = e.getQueryElement(a);
        if (!c)return void(h && h.error("Bad element for " + this.constructor.namespace + ": " + (c || a)));
        this.element = c, i && (this.$element = i(this.element)), this.options = e.extend({}, this.constructor.defaults), this.option(b);
        var d = ++k;
        this.element.outlayerGUID = d, l[d] = this, this._create(), this.options.isInitLayout && this.layout()
    }

    var h = a.console, i = a.jQuery, j = function () {
    }, k = 0, l = {};
    return g.namespace = "outlayer", g.Item = f, g.defaults = {
        containerStyle: {position: "relative"},
        isInitLayout: !0,
        isOriginLeft: !0,
        isOriginTop: !0,
        isResizeBound: !0,
        isResizingContainer: !0,
        transitionDuration: "0.4s",
        hiddenStyle: {opacity: 0, transform: "scale(0.001)"},
        visibleStyle: {opacity: 1, transform: "scale(1)"}
    }, e.extend(g.prototype, c.prototype), g.prototype.option = function (a) {
        e.extend(this.options, a)
    }, g.prototype._create = function () {
        this.reloadItems(), this.stamps = [], this.stamp(this.options.stamp), e.extend(this.element.style, this.options.containerStyle), this.options.isResizeBound && this.bindResize()
    }, g.prototype.reloadItems = function () {
        this.items = this._itemize(this.element.children)
    }, g.prototype._itemize = function (a) {
        for (var b = this._filterFindItemElements(a), c = this.constructor.Item, d = [], e = 0, f = b.length; f > e; e++) {
            var g = b[e], h = new c(g, this);
            d.push(h)
        }
        return d
    }, g.prototype._filterFindItemElements = function (a) {
        return e.filterFindElements(a, this.options.itemSelector)
    }, g.prototype.getItemElements = function () {
        for (var a = [], b = 0, c = this.items.length; c > b; b++)a.push(this.items[b].element);
        return a
    }, g.prototype.layout = function () {
        this._resetLayout(), this._manageStamps();
        var a = void 0 !== this.options.isLayoutInstant ? this.options.isLayoutInstant : !this._isLayoutInited;
        this.layoutItems(this.items, a), this._isLayoutInited = !0
    }, g.prototype._init = g.prototype.layout, g.prototype._resetLayout = function () {
        this.getSize()
    }, g.prototype.getSize = function () {
        this.size = d(this.element)
    }, g.prototype._getMeasurement = function (a, b) {
        var c, f = this.options[a];
        f ? ("string" == typeof f ? c = this.element.querySelector(f) : e.isElement(f) && (c = f), this[a] = c ? d(c)[b] : f) : this[a] = 0
    }, g.prototype.layoutItems = function (a, b) {
        a = this._getItemsForLayout(a), this._layoutItems(a, b), this._postLayout()
    }, g.prototype._getItemsForLayout = function (a) {
        for (var b = [], c = 0, d = a.length; d > c; c++) {
            var e = a[c];
            e.isIgnored || b.push(e)
        }
        return b
    }, g.prototype._layoutItems = function (a, b) {
        if (this._emitCompleteOnItems("layout", a), a && a.length) {
            for (var c = [], d = 0, e = a.length; e > d; d++) {
                var f = a[d], g = this._getItemLayoutPosition(f);
                g.item = f, g.isInstant = b || f.isLayoutInstant, c.push(g)
            }
            this._processLayoutQueue(c)
        }
    }, g.prototype._getItemLayoutPosition = function () {
        return {x: 0, y: 0}
    }, g.prototype._processLayoutQueue = function (a) {
        for (var b = 0, c = a.length; c > b; b++) {
            var d = a[b];
            this._positionItem(d.item, d.x, d.y, d.isInstant)
        }
    }, g.prototype._positionItem = function (a, b, c, d) {
        d ? a.goTo(b, c) : a.moveTo(b, c)
    }, g.prototype._postLayout = function () {
        this.resizeContainer()
    }, g.prototype.resizeContainer = function () {
        if (this.options.isResizingContainer) {
            var a = this._getContainerSize();
            a && (this._setContainerMeasure(a.width, !0), this._setContainerMeasure(a.height, !1))
        }
    }, g.prototype._getContainerSize = j, g.prototype._setContainerMeasure = function (a, b) {
        if (void 0 !== a) {
            var c = this.size;
            c.isBorderBox && (a += b ? c.paddingLeft + c.paddingRight + c.borderLeftWidth + c.borderRightWidth : c.paddingBottom + c.paddingTop + c.borderTopWidth + c.borderBottomWidth), a = Math.max(a, 0), this.element.style[b ? "width" : "height"] = a + "px"
        }
    }, g.prototype._emitCompleteOnItems = function (a, b) {
        function c() {
            e.dispatchEvent(a + "Complete", null, [b])
        }

        function d() {
            g++, g === f && c()
        }

        var e = this, f = b.length;
        if (!b || !f)return void c();
        for (var g = 0, h = 0, i = b.length; i > h; h++) {
            var j = b[h];
            j.once(a, d)
        }
    }, g.prototype.dispatchEvent = function (a, b, c) {
        var d = b ? [b].concat(c) : c;
        if (this.emitEvent(a, d), i)if (this.$element = this.$element || i(this.element), b) {
            var e = i.Event(b);
            e.type = a, this.$element.trigger(e, c)
        } else this.$element.trigger(a, c)
    }, g.prototype.ignore = function (a) {
        var b = this.getItem(a);
        b && (b.isIgnored = !0)
    }, g.prototype.unignore = function (a) {
        var b = this.getItem(a);
        b && delete b.isIgnored
    }, g.prototype.stamp = function (a) {
        if (a = this._find(a)) {
            this.stamps = this.stamps.concat(a);
            for (var b = 0, c = a.length; c > b; b++) {
                var d = a[b];
                this.ignore(d)
            }
        }
    }, g.prototype.unstamp = function (a) {
        if (a = this._find(a))for (var b = 0, c = a.length; c > b; b++) {
            var d = a[b];
            e.removeFrom(this.stamps, d), this.unignore(d)
        }
    }, g.prototype._find = function (a) {
        return a ? ("string" == typeof a && (a = this.element.querySelectorAll(a)), a = e.makeArray(a)) : void 0
    }, g.prototype._manageStamps = function () {
        if (this.stamps && this.stamps.length) {
            this._getBoundingRect();
            for (var a = 0, b = this.stamps.length; b > a; a++) {
                var c = this.stamps[a];
                this._manageStamp(c)
            }
        }
    }, g.prototype._getBoundingRect = function () {
        var a = this.element.getBoundingClientRect(), b = this.size;
        this._boundingRect = {
            left: a.left + b.paddingLeft + b.borderLeftWidth,
            top: a.top + b.paddingTop + b.borderTopWidth,
            right: a.right - (b.paddingRight + b.borderRightWidth),
            bottom: a.bottom - (b.paddingBottom + b.borderBottomWidth)
        }
    }, g.prototype._manageStamp = j, g.prototype._getElementOffset = function (a) {
        var b = a.getBoundingClientRect(), c = this._boundingRect, e = d(a), f = {
            left: b.left - c.left - e.marginLeft,
            top: b.top - c.top - e.marginTop,
            right: c.right - b.right - e.marginRight,
            bottom: c.bottom - b.bottom - e.marginBottom
        };
        return f
    }, g.prototype.handleEvent = function (a) {
        var b = "on" + a.type;
        this[b] && this[b](a)
    }, g.prototype.bindResize = function () {
        this.isResizeBound || (b.bind(a, "resize", this), this.isResizeBound = !0)
    }, g.prototype.unbindResize = function () {
        this.isResizeBound && b.unbind(a, "resize", this), this.isResizeBound = !1
    }, g.prototype.onresize = function () {
        function a() {
            b.resize(), delete b.resizeTimeout
        }

        this.resizeTimeout && clearTimeout(this.resizeTimeout);
        var b = this;
        this.resizeTimeout = setTimeout(a, 100)
    }, g.prototype.resize = function () {
        this.isResizeBound && this.needsResizeLayout() && this.layout()
    }, g.prototype.needsResizeLayout = function () {
        var a = d(this.element), b = this.size && a;
        return b && a.innerWidth !== this.size.innerWidth
    }, g.prototype.addItems = function (a) {
        var b = this._itemize(a);
        return b.length && (this.items = this.items.concat(b)), b
    }, g.prototype.appended = function (a) {
        var b = this.addItems(a);
        b.length && (this.layoutItems(b, !0), this.reveal(b))
    }, g.prototype.prepended = function (a) {
        var b = this._itemize(a);
        if (b.length) {
            var c = this.items.slice(0);
            this.items = b.concat(c), this._resetLayout(), this._manageStamps(), this.layoutItems(b, !0), this.reveal(b), this.layoutItems(c)
        }
    }, g.prototype.reveal = function (a) {
        this._emitCompleteOnItems("reveal", a);
        for (var b = a && a.length, c = 0; b && b > c; c++) {
            var d = a[c];
            d.reveal()
        }
    }, g.prototype.hide = function (a) {
        this._emitCompleteOnItems("hide", a);
        for (var b = a && a.length, c = 0; b && b > c; c++) {
            var d = a[c];
            d.hide()
        }
    }, g.prototype.revealItemElements = function (a) {
        var b = this.getItems(a);
        this.reveal(b)
    }, g.prototype.hideItemElements = function (a) {
        var b = this.getItems(a);
        this.hide(b)
    }, g.prototype.getItem = function (a) {
        for (var b = 0, c = this.items.length; c > b; b++) {
            var d = this.items[b];
            if (d.element === a)return d
        }
    }, g.prototype.getItems = function (a) {
        a = e.makeArray(a);
        for (var b = [], c = 0, d = a.length; d > c; c++) {
            var f = a[c], g = this.getItem(f);
            g && b.push(g)
        }
        return b
    }, g.prototype.remove = function (a) {
        var b = this.getItems(a);
        if (this._emitCompleteOnItems("remove", b), b && b.length)for (var c = 0, d = b.length; d > c; c++) {
            var f = b[c];
            f.remove(), e.removeFrom(this.items, f)
        }
    }, g.prototype.destroy = function () {
        var a = this.element.style;
        a.height = "", a.position = "", a.width = "";
        for (var b = 0, c = this.items.length; c > b; b++) {
            var d = this.items[b];
            d.destroy()
        }
        this.unbindResize();
        var e = this.element.outlayerGUID;
        delete l[e], delete this.element.outlayerGUID, i && i.removeData(this.element, this.constructor.namespace)
    }, g.data = function (a) {
        a = e.getQueryElement(a);
        var b = a && a.outlayerGUID;
        return b && l[b]
    }, g.create = function (a, b) {
        function c() {
            g.apply(this, arguments)
        }

        return Object.create ? c.prototype = Object.create(g.prototype) : e.extend(c.prototype, g.prototype), c.prototype.constructor = c, c.defaults = e.extend({}, g.defaults), e.extend(c.defaults, b), c.prototype.settings = {}, c.namespace = a, c.data = g.data, c.Item = function () {
            f.apply(this, arguments)
        }, c.Item.prototype = new f, e.htmlInit(c, a), i && i.bridget && i.bridget(a, c), c
    }, g.Item = f, g
}), function (a, b) {
    "function" == typeof define && define.amd ? define(["outlayer/outlayer", "get-size/get-size", "fizzy-ui-utils/utils"], b) : "object" == typeof exports ? module.exports = b(require("outlayer"), require("get-size"), require("fizzy-ui-utils")) : a.Masonry = b(a.Outlayer, a.getSize, a.fizzyUIUtils)
}(window, function (a, b, c) {
    var d = a.create("masonry");
    return d.prototype._resetLayout = function () {
        this.getSize(), this._getMeasurement("columnWidth", "outerWidth"), this._getMeasurement("gutter", "outerWidth"), this.measureColumns();
        var a = this.cols;
        for (this.colYs = []; a--;)this.colYs.push(0);
        this.maxY = 0
    }, d.prototype.measureColumns = function () {
        if (this.getContainerWidth(), !this.columnWidth) {
            var a = this.items[0], c = a && a.element;
            this.columnWidth = c && b(c).outerWidth || this.containerWidth
        }
        var d = this.columnWidth += this.gutter, e = this.containerWidth + this.gutter, f = e / d, g = d - e % d, h = g && 1 > g ? "round" : "floor";
        f = Math[h](f), this.cols = Math.max(f, 1)
    }, d.prototype.getContainerWidth = function () {
        var a = this.options.isFitWidth ? this.element.parentNode : this.element, c = b(a);
        this.containerWidth = c && c.innerWidth
    }, d.prototype._getItemLayoutPosition = function (a) {
        a.getSize();
        var b = a.size.outerWidth % this.columnWidth, d = b && 1 > b ? "round" : "ceil", e = Math[d](a.size.outerWidth / this.columnWidth);
        e = Math.min(e, this.cols);
        for (var f = this._getColGroup(e), g = Math.min.apply(Math, f), h = c.indexOf(f, g), i = {
            x: this.columnWidth * h,
            y: g
        }, j = g + a.size.outerHeight, k = this.cols + 1 - f.length, l = 0; k > l; l++)this.colYs[h + l] = j;
        return i
    }, d.prototype._getColGroup = function (a) {
        if (2 > a)return this.colYs;
        for (var b = [], c = this.cols + 1 - a, d = 0; c > d; d++) {
            var e = this.colYs.slice(d, d + a);
            b[d] = Math.max.apply(Math, e)
        }
        return b
    }, d.prototype._manageStamp = function (a) {
        var c = b(a), d = this._getElementOffset(a), e = this.options.isOriginLeft ? d.left : d.right, f = e + c.outerWidth, g = Math.floor(e / this.columnWidth);
        g = Math.max(0, g);
        var h = Math.floor(f / this.columnWidth);
        h -= f % this.columnWidth ? 0 : 1, h = Math.min(this.cols - 1, h);
        for (var i = (this.options.isOriginTop ? d.top : d.bottom) + c.outerHeight, j = g; h >= j; j++)this.colYs[j] = Math.max(i, this.colYs[j])
    }, d.prototype._getContainerSize = function () {
        this.maxY = Math.max.apply(Math, this.colYs);
        var a = {height: this.maxY};
        return this.options.isFitWidth && (a.width = this._getContainerFitWidth()), a
    }, d.prototype._getContainerFitWidth = function () {
        for (var a = 0, b = this.cols; --b && 0 === this.colYs[b];)a++;
        return (this.cols - a) * this.columnWidth - this.gutter
    }, d.prototype.needsResizeLayout = function () {
        var a = this.containerWidth;
        return this.getContainerWidth(), a !== this.containerWidth
    }, d
}), function () {
    var e, t;
    e = function () {
        function e(e, t) {
            var n, r;
            if (this.options = {
                    target: "instafeed",
                    get: "popular",
                    resolution: "thumbnail",
                    sortBy: "none",
                    links: !0,
                    mock: !1,
                    useHttp: !1
                }, "object" == typeof e)for (n in e)r = e[n], this.options[n] = r;
            this.context = null != t ? t : this, this.unique = this._genKey()
        }

        return e.prototype.hasNext = function () {
            return "string" == typeof this.context.nextUrl && this.context.nextUrl.length > 0
        }, e.prototype.next = function () {
            return this.hasNext() ? this.run(this.context.nextUrl) : !1
        }, e.prototype.run = function (t) {
            var n, r, i;
            if ("string" != typeof this.options.clientId && "string" != typeof this.options.accessToken)throw new Error("Missing clientId or accessToken.");
            if ("string" != typeof this.options.accessToken && "string" != typeof this.options.clientId)throw new Error("Missing clientId or accessToken.");
            return null != this.options.before && "function" == typeof this.options.before && this.options.before.call(this), "undefined" != typeof document && null !== document && (i = document.createElement("script"), i.id = "instafeed-fetcher", i.src = t || this._buildUrl(), n = document.getElementsByTagName("head"), n[0].appendChild(i), r = "instafeedCache" + this.unique, window[r] = new e(this.options, this), window[r].unique = this.unique), !0
        }, e.prototype.parse = function (e) {
            var t, n, r, i, s, o, u, a, f, l, c, h, p, d, v, m, g, y, b, w, E, S;
            if ("object" != typeof e) {
                if (null != this.options.error && "function" == typeof this.options.error)return this.options.error.call(this, "Invalid JSON data"), !1;
                throw new Error("Invalid JSON response")
            }
            if (200 !== e.meta.code) {
                if (null != this.options.error && "function" == typeof this.options.error)return this.options.error.call(this, e.meta.error_message), !1;
                throw new Error("Error from Instagram: " + e.meta.error_message)
            }
            if (0 === e.data.length) {
                if (null != this.options.error && "function" == typeof this.options.error)return this.options.error.call(this, "No images were returned from Instagram"), !1;
                throw new Error("No images were returned from Instagram")
            }
            if (null != this.options.success && "function" == typeof this.options.success && this.options.success.call(this, e), this.context.nextUrl = "", null != e.pagination && (this.context.nextUrl = e.pagination.next_url), "none" !== this.options.sortBy)switch (d = "random" === this.options.sortBy ? ["", "random"] : this.options.sortBy.split("-"), p = "least" === d[0] ? !0 : !1, d[1]) {
                case"random":
                    e.data.sort(function () {
                        return .5 - Math.random()
                    });
                    break;
                case"recent":
                    e.data = this._sortBy(e.data, "created_time", p);
                    break;
                case"liked":
                    e.data = this._sortBy(e.data, "likes.count", p);
                    break;
                case"commented":
                    e.data = this._sortBy(e.data, "comments.count", p);
                    break;
                default:
                    throw new Error("Invalid option for sortBy: '" + this.options.sortBy + "'.")
            }
            if ("undefined" != typeof document && null !== document && this.options.mock === !1) {
                if (a = e.data, null != this.options.limit && a.length > this.options.limit && (a = a.slice(0, this.options.limit + 1 || 9e9)), n = document.createDocumentFragment(), null != this.options.filter && "function" == typeof this.options.filter && (a = this._filter(a, this.options.filter)), null != this.options.template && "string" == typeof this.options.template) {
                    for (i = "", o = "", l = "", v = document.createElement("div"), m = 0, b = a.length; b > m; m++)s = a[m], u = s.images[this.options.resolution].url, this.options.useHttp || (u = u.replace("http://", "//")), o = this._makeTemplate(this.options.template, {
                        model: s,
                        id: s.id,
                        link: s.link,
                        image: u,
                        caption: this._getObjectProperty(s, "caption.text"),
                        likes: s.likes.count,
                        comments: s.comments.count,
                        location: this._getObjectProperty(s, "location.name")
                    }), i += o;
                    for (v.innerHTML = i, S = [].slice.call(v.childNodes), g = 0, w = S.length; w > g; g++)h = S[g], n.appendChild(h)
                } else for (y = 0, E = a.length; E > y; y++)s = a[y], f = document.createElement("img"), u = s.images[this.options.resolution].url, this.options.useHttp || (u = u.replace("http://", "//")), f.src = u, this.options.links === !0 ? (t = document.createElement("a"), t.href = s.link, t.appendChild(f), n.appendChild(t)) : n.appendChild(f);
                document.getElementById(this.options.target).appendChild(n), r = document.getElementsByTagName("head")[0], r.removeChild(document.getElementById("instafeed-fetcher")), c = "instafeedCache" + this.unique, window[c] = void 0;
                try {
                    delete window[c]
                } catch (x) {
                }
            }
            return null != this.options.after && "function" == typeof this.options.after && this.options.after.call(this), !0
        }, e.prototype._buildUrl = function () {
            var e, t, n;
            switch (e = "https://api.instagram.com/v1", this.options.get) {
                case"popular":
                    t = "media/popular";
                    break;
                case"tagged":
                    if ("string" != typeof this.options.tagName)throw new Error("No tag name specified. Use the 'tagName' option.");
                    t = "tags/" + this.options.tagName + "/media/recent";
                    break;
                case"location":
                    if ("number" != typeof this.options.locationId)throw new Error("No location specified. Use the 'locationId' option.");
                    t = "locations/" + this.options.locationId + "/media/recent";
                    break;
                case"user":
                    if ("number" != typeof this.options.userId)throw new Error("No user specified. Use the 'userId' option.");
                    if ("string" != typeof this.options.accessToken)throw new Error("No access token. Use the 'accessToken' option.");
                    t = "users/" + this.options.userId + "/media/recent";
                    break;
                default:
                    throw new Error("Invalid option for get: '" + this.options.get + "'.")
            }
            return n = "" + e + "/" + t, n += null != this.options.accessToken ? "?access_token=" + this.options.accessToken : "?client_id=" + this.options.clientId, null != this.options.limit && (n += "&count=" + this.options.limit), n += "&callback=instafeedCache" + this.unique + ".parse"
        }, e.prototype._genKey = function () {
            var e;
            return e = function () {
                return (65536 * (1 + Math.random()) | 0).toString(16).substring(1)
            }, "" + e() + e() + e() + e()
        }, e.prototype._makeTemplate = function (e, t) {
            var n, r, i, s, o;
            for (r = /(?:\{{2})([\w\[\]\.]+)(?:\}{2})/, n = e; r.test(n);)i = n.match(r)[1], s = null != (o = this._getObjectProperty(t, i)) ? o : "", n = n.replace(r, "" + s);
            return n
        }, e.prototype._getObjectProperty = function (e, t) {
            var n, r;
            for (t = t.replace(/\[(\w+)\]/g, ".$1"), r = t.split("."); r.length;) {
                if (n = r.shift(), !(null != e && n in e))return null;
                e = e[n]
            }
            return e
        }, e.prototype._sortBy = function (e, t, n) {
            var r;
            return r = function (e, r) {
                var i, s;
                return i = this._getObjectProperty(e, t), s = this._getObjectProperty(r, t), n ? i > s ? 1 : -1 : s > i ? 1 : -1
            }, e.sort(r.bind(this)), e
        }, e.prototype._filter = function (e, t) {
            var n, r, i, s, o;
            for (n = [], i = function (e) {
                return t(e) ? n.push(e) : void 0
            }, s = 0, o = e.length; o > s; s++)r = e[s], i(r);
            return n
        }, e
    }(), t = "undefined" != typeof exports && null !== exports ? exports : window, t.Instafeed = e
}.call(this), !function (a) {
    "function" == typeof define && define.amd ? define(["jquery"], a) : "object" == typeof exports ? module.exports = a : a(jQuery)
}(function (a) {
    function b(b) {
        var g = b || window.event, h = i.call(arguments, 1), j = 0, l = 0, m = 0, n = 0, o = 0, p = 0;
        if (b = a.event.fix(g), b.type = "mousewheel", "detail" in g && (m = -1 * g.detail), "wheelDelta" in g && (m = g.wheelDelta), "wheelDeltaY" in g && (m = g.wheelDeltaY), "wheelDeltaX" in g && (l = -1 * g.wheelDeltaX), "axis" in g && g.axis === g.HORIZONTAL_AXIS && (l = -1 * m, m = 0), j = 0 === m ? l : m, "deltaY" in g && (m = -1 * g.deltaY, j = m), "deltaX" in g && (l = g.deltaX, 0 === m && (j = -1 * l)), 0 !== m || 0 !== l) {
            if (1 === g.deltaMode) {
                var q = a.data(this, "mousewheel-line-height");
                j *= q, m *= q, l *= q
            } else if (2 === g.deltaMode) {
                var r = a.data(this, "mousewheel-page-height");
                j *= r, m *= r, l *= r
            }
            if (n = Math.max(Math.abs(m), Math.abs(l)), (!f || f > n) && (f = n, d(g, n) && (f /= 40)), d(g, n) && (j /= 40, l /= 40, m /= 40), j = Math[j >= 1 ? "floor" : "ceil"](j / f), l = Math[l >= 1 ? "floor" : "ceil"](l / f), m = Math[m >= 1 ? "floor" : "ceil"](m / f), k.settings.normalizeOffset && this.getBoundingClientRect) {
                var s = this.getBoundingClientRect();
                o = b.clientX - s.left, p = b.clientY - s.top
            }
            return b.deltaX = l, b.deltaY = m, b.deltaFactor = f, b.offsetX = o, b.offsetY = p, b.deltaMode = 0, h.unshift(b, j, l, m), e && clearTimeout(e), e = setTimeout(c, 200), (a.event.dispatch || a.event.handle).apply(this, h)
        }
    }

    function c() {
        f = null
    }

    function d(a, b) {
        return k.settings.adjustOldDeltas && "mousewheel" === a.type && b % 120 === 0
    }

    var e, f, g = ["wheel", "mousewheel", "DOMMouseScroll", "MozMousePixelScroll"], h = "onwheel" in document || document.documentMode >= 9 ? ["wheel"] : ["mousewheel", "DomMouseScroll", "MozMousePixelScroll"], i = Array.prototype.slice;
    if (a.event.fixHooks)for (var j = g.length; j;)a.event.fixHooks[g[--j]] = a.event.mouseHooks;
    var k = a.event.special.mousewheel = {
        version: "3.1.12", setup: function () {
            if (this.addEventListener)for (var c = h.length; c;)this.addEventListener(h[--c], b, !1); else this.onmousewheel = b;
            a.data(this, "mousewheel-line-height", k.getLineHeight(this)), a.data(this, "mousewheel-page-height", k.getPageHeight(this))
        }, teardown: function () {
            if (this.removeEventListener)for (var c = h.length; c;)this.removeEventListener(h[--c], b, !1); else this.onmousewheel = null;
            a.removeData(this, "mousewheel-line-height"), a.removeData(this, "mousewheel-page-height")
        }, getLineHeight: function (b) {
            var c = a(b), d = c["offsetParent" in a.fn ? "offsetParent" : "parent"]();
            return d.length || (d = a("body")), parseInt(d.css("fontSize"), 10) || parseInt(c.css("fontSize"), 10) || 16
        }, getPageHeight: function (b) {
            return a(b).height()
        }, settings: {adjustOldDeltas: !0, normalizeOffset: !0}
    };
    a.fn.extend({
        mousewheel: function (a) {
            return a ? this.bind("mousewheel", a) : this.trigger("mousewheel")
        }, unmousewheel: function (a) {
            return this.unbind("mousewheel", a)
        }
    })
}), !function (e) {
    "use strict";
    e.srSmoothscroll = function (t) {
        var n = e.extend({
            step: 55,
            speed: 400,
            ease: "swing",
            target: e("body"),
            container: e(window)
        }, t || {}), o = n.container, r = 0, i = n.step, s = o.height(), a = !1, c = "body" == n.target.selector ? -1 !== navigator.userAgent.indexOf("AppleWebKit") ? n.target : e("html") : o;
        n.target.mousewheel(function (e, t) {
            return a = !0, r = 0 > t ? r + s >= n.target.outerHeight(!0) ? r : r += i : 0 >= r ? 0 : r -= i, c.stop().animate({scrollTop: r}, n.speed, n.ease, function () {
                a = !1
            }), !1
        }), o.on("resize", function () {
            s = o.height()
        }).on("scroll", function () {
            a || (r = o.scrollTop())
        })
    }
}(jQuery);

var THREE = {REVISION: "71"};
"object" == typeof module && (module.exports = THREE), void 0 === Math.sign && (Math.sign = function (a) {
    return 0 > a ? -1 : a > 0 ? 1 : +a
}), THREE.log = function () {
    console.log.apply(console, arguments)
}, THREE.warn = function () {
    console.warn.apply(console, arguments)
}, THREE.error = function () {
    console.error.apply(console, arguments)
}, THREE.MOUSE = {
    LEFT: 0,
    MIDDLE: 1,
    RIGHT: 2
}, THREE.CullFaceNone = 0, THREE.CullFaceBack = 1, THREE.CullFaceFront = 2, THREE.CullFaceFrontBack = 3, THREE.FrontFaceDirectionCW = 0, THREE.FrontFaceDirectionCCW = 1, THREE.BasicShadowMap = 0, THREE.PCFShadowMap = 1, THREE.PCFSoftShadowMap = 2, THREE.FrontSide = 0, THREE.BackSide = 1, THREE.DoubleSide = 2, THREE.NoShading = 0, THREE.FlatShading = 1, THREE.SmoothShading = 2, THREE.NoColors = 0, THREE.FaceColors = 1, THREE.VertexColors = 2, THREE.NoBlending = 0, THREE.NormalBlending = 1, THREE.AdditiveBlending = 2, THREE.SubtractiveBlending = 3, THREE.MultiplyBlending = 4, THREE.CustomBlending = 5, THREE.AddEquation = 100, THREE.SubtractEquation = 101, THREE.ReverseSubtractEquation = 102, THREE.MinEquation = 103, THREE.MaxEquation = 104, THREE.ZeroFactor = 200, THREE.OneFactor = 201, THREE.SrcColorFactor = 202, THREE.OneMinusSrcColorFactor = 203, THREE.SrcAlphaFactor = 204, THREE.OneMinusSrcAlphaFactor = 205, THREE.DstAlphaFactor = 206, THREE.OneMinusDstAlphaFactor = 207, THREE.DstColorFactor = 208, THREE.OneMinusDstColorFactor = 209, THREE.SrcAlphaSaturateFactor = 210, THREE.MultiplyOperation = 0, THREE.MixOperation = 1, THREE.AddOperation = 2, THREE.UVMapping = 300, THREE.CubeReflectionMapping = 301, THREE.CubeRefractionMapping = 302, THREE.EquirectangularReflectionMapping = 303, THREE.EquirectangularRefractionMapping = 304, THREE.SphericalReflectionMapping = 305, THREE.RepeatWrapping = 1e3, THREE.ClampToEdgeWrapping = 1001, THREE.MirroredRepeatWrapping = 1002, THREE.NearestFilter = 1003, THREE.NearestMipMapNearestFilter = 1004, THREE.NearestMipMapLinearFilter = 1005, THREE.LinearFilter = 1006, THREE.LinearMipMapNearestFilter = 1007, THREE.LinearMipMapLinearFilter = 1008, THREE.UnsignedByteType = 1009, THREE.ByteType = 1010, THREE.ShortType = 1011, THREE.UnsignedShortType = 1012, THREE.IntType = 1013, THREE.UnsignedIntType = 1014, THREE.FloatType = 1015, THREE.HalfFloatType = 1025, THREE.UnsignedShort4444Type = 1016, THREE.UnsignedShort5551Type = 1017, THREE.UnsignedShort565Type = 1018, THREE.AlphaFormat = 1019, THREE.RGBFormat = 1020, THREE.RGBAFormat = 1021, THREE.LuminanceFormat = 1022,
    THREE.LuminanceAlphaFormat = 1023, THREE.RGBEFormat = THREE.RGBAFormat, THREE.RGB_S3TC_DXT1_Format = 2001, THREE.RGBA_S3TC_DXT1_Format = 2002, THREE.RGBA_S3TC_DXT3_Format = 2003, THREE.RGBA_S3TC_DXT5_Format = 2004, THREE.RGB_PVRTC_4BPPV1_Format = 2100, THREE.RGB_PVRTC_2BPPV1_Format = 2101, THREE.RGBA_PVRTC_4BPPV1_Format = 2102, THREE.RGBA_PVRTC_2BPPV1_Format = 2103, THREE.Projector = function () {
    THREE.error("THREE.Projector has been moved to /examples/js/renderers/Projector.js."), this.projectVector = function (a, b) {
        THREE.warn("THREE.Projector: .projectVector() is now vector.project()."), a.project(b)
    }, this.unprojectVector = function (a, b) {
        THREE.warn("THREE.Projector: .unprojectVector() is now vector.unproject()."), a.unproject(b)
    }, this.pickingRay = function (a, b) {
        THREE.error("THREE.Projector: .pickingRay() is now raycaster.setFromCamera().")
    }
}, THREE.CanvasRenderer = function () {
    THREE.error("THREE.CanvasRenderer has been moved to /examples/js/renderers/CanvasRenderer.js"), this.domElement = document.createElement("canvas"), this.clear = function () {
    }, this.render = function () {
    }, this.setClearColor = function () {
    }, this.setSize = function () {
    }
}, THREE.Color = function (a) {
    return 3 === arguments.length ? this.setRGB(arguments[0], arguments[1], arguments[2]) : this.set(a)
}, THREE.Color.prototype = {
    constructor: THREE.Color, r: 1, g: 1, b: 1, set: function (a) {
        return a instanceof THREE.Color ? this.copy(a) : "number" == typeof a ? this.setHex(a) : "string" == typeof a && this.setStyle(a), this
    }, setHex: function (a) {
        return a = Math.floor(a), this.r = (a >> 16 & 255) / 255, this.g = (a >> 8 & 255) / 255, this.b = (255 & a) / 255, this
    }, setRGB: function (a, b, c) {
        return this.r = a, this.g = b, this.b = c, this
    }, setHSL: function (a, b, c) {
        if (0 === b)this.r = this.g = this.b = c; else {
            var d = function (a, b, c) {
                return 0 > c && (c += 1), c > 1 && (c -= 1), 1 / 6 > c ? a + 6 * (b - a) * c : .5 > c ? b : 2 / 3 > c ? a + 6 * (b - a) * (2 / 3 - c) : a
            };
            b = .5 >= c ? c * (1 + b) : c + b - c * b, c = 2 * c - b, this.r = d(c, b, a + 1 / 3), this.g = d(c, b, a), this.b = d(c, b, a - 1 / 3)
        }
        return this
    }, setStyle: function (a) {
        return /^rgb\((\d+), ?(\d+), ?(\d+)\)$/i.test(a) ? (a = /^rgb\((\d+), ?(\d+), ?(\d+)\)$/i.exec(a), this.r = Math.min(255, parseInt(a[1], 10)) / 255, this.g = Math.min(255, parseInt(a[2], 10)) / 255, this.b = Math.min(255, parseInt(a[3], 10)) / 255, this) : /^rgb\((\d+)\%, ?(\d+)\%, ?(\d+)\%\)$/i.test(a) ? (a = /^rgb\((\d+)\%, ?(\d+)\%, ?(\d+)\%\)$/i.exec(a), this.r = Math.min(100, parseInt(a[1], 10)) / 100, this.g = Math.min(100, parseInt(a[2], 10)) / 100, this.b = Math.min(100, parseInt(a[3], 10)) / 100, this) : /^\#([0-9a-f]{6})$/i.test(a) ? (a = /^\#([0-9a-f]{6})$/i.exec(a), this.setHex(parseInt(a[1], 16)), this) : /^\#([0-9a-f])([0-9a-f])([0-9a-f])$/i.test(a) ? (a = /^\#([0-9a-f])([0-9a-f])([0-9a-f])$/i.exec(a), this.setHex(parseInt(a[1] + a[1] + a[2] + a[2] + a[3] + a[3], 16)), this) : /^(\w+)$/i.test(a) ? (this.setHex(THREE.ColorKeywords[a]), this) : void 0
    }, copy: function (a) {
        return this.r = a.r, this.g = a.g, this.b = a.b, this
    }, copyGammaToLinear: function (a, b) {
        return void 0 === b && (b = 2), this.r = Math.pow(a.r, b), this.g = Math.pow(a.g, b), this.b = Math.pow(a.b, b), this
    }, copyLinearToGamma: function (a, b) {
        void 0 === b && (b = 2);
        var c = b > 0 ? 1 / b : 1;
        return this.r = Math.pow(a.r, c), this.g = Math.pow(a.g, c), this.b = Math.pow(a.b, c), this
    }, convertGammaToLinear: function () {
        var a = this.r, b = this.g, c = this.b;
        return this.r = a * a, this.g = b * b, this.b = c * c, this
    }, convertLinearToGamma: function () {
        return this.r = Math.sqrt(this.r), this.g = Math.sqrt(this.g), this.b = Math.sqrt(this.b), this
    }, getHex: function () {
        return 255 * this.r << 16 ^ 255 * this.g << 8 ^ 255 * this.b << 0
    }, getHexString: function () {
        return ("000000" + this.getHex().toString(16)).slice(-6)
    }, getHSL: function (a) {
        a = a || {h: 0, s: 0, l: 0};
        var g, b = this.r, c = this.g, d = this.b, e = Math.max(b, c, d), f = Math.min(b, c, d), h = (f + e) / 2;
        if (f === e)f = g = 0; else {
            var k = e - f, f = .5 >= h ? k / (e + f) : k / (2 - e - f);
            switch (e) {
                case b:
                    g = (c - d) / k + (d > c ? 6 : 0);
                    break;
                case c:
                    g = (d - b) / k + 2;
                    break;
                case d:
                    g = (b - c) / k + 4
            }
            g /= 6
        }
        return a.h = g, a.s = f, a.l = h, a
    }, getStyle: function () {
        return "rgb(" + (255 * this.r | 0) + "," + (255 * this.g | 0) + "," + (255 * this.b | 0) + ")"
    }, offsetHSL: function (a, b, c) {
        var d = this.getHSL();
        return d.h += a, d.s += b, d.l += c, this.setHSL(d.h, d.s, d.l), this
    }, add: function (a) {
        return this.r += a.r, this.g += a.g, this.b += a.b, this
    }, addColors: function (a, b) {
        return this.r = a.r + b.r, this.g = a.g + b.g, this.b = a.b + b.b, this
    }, addScalar: function (a) {
        return this.r += a, this.g += a, this.b += a, this
    }, multiply: function (a) {
        return this.r *= a.r, this.g *= a.g, this.b *= a.b, this
    }, multiplyScalar: function (a) {
        return this.r *= a, this.g *= a, this.b *= a, this
    }, lerp: function (a, b) {
        return this.r += (a.r - this.r) * b, this.g += (a.g - this.g) * b, this.b += (a.b - this.b) * b, this
    }, equals: function (a) {
        return a.r === this.r && a.g === this.g && a.b === this.b
    }, fromArray: function (a) {
        return this.r = a[0], this.g = a[1], this.b = a[2], this
    }, toArray: function (a, b) {
        return void 0 === a && (a = []), void 0 === b && (b = 0), a[b] = this.r, a[b + 1] = this.g, a[b + 2] = this.b, a
    }, clone: function () {
        return (new THREE.Color).setRGB(this.r, this.g, this.b)
    }
}, THREE.ColorKeywords = {
    aliceblue: 15792383,
    antiquewhite: 16444375,
    aqua: 65535,
    aquamarine: 8388564,
    azure: 15794175,
    beige: 16119260,
    bisque: 16770244,
    black: 0,
    blanchedalmond: 16772045,
    blue: 255,
    blueviolet: 9055202,
    brown: 10824234,
    burlywood: 14596231,
    cadetblue: 6266528,
    chartreuse: 8388352,
    chocolate: 13789470,
    coral: 16744272,
    cornflowerblue: 6591981,
    cornsilk: 16775388,
    crimson: 14423100,
    cyan: 65535,
    darkblue: 139,
    darkcyan: 35723,
    darkgoldenrod: 12092939,
    darkgray: 11119017,
    darkgreen: 25600,
    darkgrey: 11119017,
    darkkhaki: 12433259,
    darkmagenta: 9109643,
    darkolivegreen: 5597999,
    darkorange: 16747520,
    darkorchid: 10040012,
    darkred: 9109504,
    darksalmon: 15308410,
    darkseagreen: 9419919,
    darkslateblue: 4734347,
    darkslategray: 3100495,
    darkslategrey: 3100495,
    darkturquoise: 52945,
    darkviolet: 9699539,
    deeppink: 16716947,
    deepskyblue: 49151,
    dimgray: 6908265,
    dimgrey: 6908265,
    dodgerblue: 2003199,
    firebrick: 11674146,
    floralwhite: 16775920,
    forestgreen: 2263842,
    fuchsia: 16711935,
    gainsboro: 14474460,
    ghostwhite: 16316671,
    gold: 16766720,
    goldenrod: 14329120,
    gray: 8421504,
    green: 32768,
    greenyellow: 11403055,
    grey: 8421504,
    honeydew: 15794160,
    hotpink: 16738740,
    indianred: 13458524,
    indigo: 4915330,
    ivory: 16777200,
    khaki: 15787660,
    lavender: 15132410,
    lavenderblush: 16773365,
    lawngreen: 8190976,
    lemonchiffon: 16775885,
    lightblue: 11393254,
    lightcoral: 15761536,
    lightcyan: 14745599,
    lightgoldenrodyellow: 16448210,
    lightgray: 13882323,
    lightgreen: 9498256,
    lightgrey: 13882323,
    lightpink: 16758465,
    lightsalmon: 16752762,
    lightseagreen: 2142890,
    lightskyblue: 8900346,
    lightslategray: 7833753,
    lightslategrey: 7833753,
    lightsteelblue: 11584734,
    lightyellow: 16777184,
    lime: 65280,
    limegreen: 3329330,
    linen: 16445670,
    magenta: 16711935,
    maroon: 8388608,
    mediumaquamarine: 6737322,
    mediumblue: 205,
    mediumorchid: 12211667,
    mediumpurple: 9662683,
    mediumseagreen: 3978097,
    mediumslateblue: 8087790,
    mediumspringgreen: 64154,
    mediumturquoise: 4772300,
    mediumvioletred: 13047173,
    midnightblue: 1644912,
    mintcream: 16121850,
    mistyrose: 16770273,
    moccasin: 16770229,
    navajowhite: 16768685,
    navy: 128,
    oldlace: 16643558,
    olive: 8421376,
    olivedrab: 7048739,
    orange: 16753920,
    orangered: 16729344,
    orchid: 14315734,
    palegoldenrod: 15657130,
    palegreen: 10025880,
    paleturquoise: 11529966,
    palevioletred: 14381203,
    papayawhip: 16773077,
    peachpuff: 16767673,
    peru: 13468991,
    pink: 16761035,
    plum: 14524637,
    powderblue: 11591910,
    purple: 8388736,
    red: 16711680,
    rosybrown: 12357519,
    royalblue: 4286945,
    saddlebrown: 9127187,
    salmon: 16416882,
    sandybrown: 16032864,
    seagreen: 3050327,
    seashell: 16774638,
    sienna: 10506797,
    silver: 12632256,
    skyblue: 8900331,
    slateblue: 6970061,
    slategray: 7372944,
    slategrey: 7372944,
    snow: 16775930,
    springgreen: 65407,
    steelblue: 4620980,
    tan: 13808780,
    teal: 32896,
    thistle: 14204888,
    tomato: 16737095,
    turquoise: 4251856,
    violet: 15631086,
    wheat: 16113331,
    white: 16777215,
    whitesmoke: 16119285,
    yellow: 16776960,
    yellowgreen: 10145074
}, THREE.Quaternion = function (a, b, c, d) {
    this._x = a || 0, this._y = b || 0, this._z = c || 0, this._w = void 0 !== d ? d : 1
}, THREE.Quaternion.prototype = {
    constructor: THREE.Quaternion, _x: 0, _y: 0, _z: 0, _w: 0, get x() {
        return this._x
    }, set x(a) {
        this._x = a, this.onChangeCallback()
    }, get y() {
        return this._y
    }, set y(a) {
        this._y = a, this.onChangeCallback()
    }, get z() {
        return this._z
    }, set z(a) {
        this._z = a, this.onChangeCallback()
    }, get w() {
        return this._w
    }, set w(a) {
        this._w = a, this.onChangeCallback()
    }, set: function (a, b, c, d) {
        return this._x = a, this._y = b, this._z = c, this._w = d, this.onChangeCallback(), this
    }, copy: function (a) {
        return this._x = a.x, this._y = a.y, this._z = a.z, this._w = a.w, this.onChangeCallback(), this
    }, setFromEuler: function (a, b) {
        if (!1 == a instanceof THREE.Euler)throw Error("THREE.Quaternion: .setFromEuler() now expects a Euler rotation rather than a Vector3 and order.");
        var c = Math.cos(a._x / 2), d = Math.cos(a._y / 2), e = Math.cos(a._z / 2), f = Math.sin(a._x / 2), g = Math.sin(a._y / 2), h = Math.sin(a._z / 2);
        return "XYZ" === a.order ? (this._x = f * d * e + c * g * h, this._y = c * g * e - f * d * h, this._z = c * d * h + f * g * e, this._w = c * d * e - f * g * h) : "YXZ" === a.order ? (this._x = f * d * e + c * g * h, this._y = c * g * e - f * d * h, this._z = c * d * h - f * g * e, this._w = c * d * e + f * g * h) : "ZXY" === a.order ? (this._x = f * d * e - c * g * h, this._y = c * g * e + f * d * h, this._z = c * d * h + f * g * e, this._w = c * d * e - f * g * h) : "ZYX" === a.order ? (this._x = f * d * e - c * g * h, this._y = c * g * e + f * d * h, this._z = c * d * h - f * g * e, this._w = c * d * e + f * g * h) : "YZX" === a.order ? (this._x = f * d * e + c * g * h, this._y = c * g * e + f * d * h, this._z = c * d * h - f * g * e, this._w = c * d * e - f * g * h) : "XZY" === a.order && (this._x = f * d * e - c * g * h, this._y = c * g * e - f * d * h, this._z = c * d * h + f * g * e, this._w = c * d * e + f * g * h), !1 !== b && this.onChangeCallback(), this
    }, setFromAxisAngle: function (a, b) {
        var c = b / 2, d = Math.sin(c);
        return this._x = a.x * d, this._y = a.y * d, this._z = a.z * d, this._w = Math.cos(c), this.onChangeCallback(), this
    }, setFromRotationMatrix: function (a) {
        var b = a.elements, c = b[0];
        a = b[4];
        var d = b[8], e = b[1], f = b[5], g = b[9], h = b[2], k = b[6], b = b[10], l = c + f + b;
        return l > 0 ? (c = .5 / Math.sqrt(l + 1), this._w = .25 / c, this._x = (k - g) * c, this._y = (d - h) * c, this._z = (e - a) * c) : c > f && c > b ? (c = 2 * Math.sqrt(1 + c - f - b), this._w = (k - g) / c, this._x = .25 * c, this._y = (a + e) / c, this._z = (d + h) / c) : f > b ? (c = 2 * Math.sqrt(1 + f - c - b), this._w = (d - h) / c, this._x = (a + e) / c, this._y = .25 * c, this._z = (g + k) / c) : (c = 2 * Math.sqrt(1 + b - c - f), this._w = (e - a) / c, this._x = (d + h) / c, this._y = (g + k) / c, this._z = .25 * c), this.onChangeCallback(), this
    }, setFromUnitVectors: function () {
        var a, b;
        return function (c, d) {
            return void 0 === a && (a = new THREE.Vector3), b = c.dot(d) + 1, 1e-6 > b ? (b = 0, Math.abs(c.x) > Math.abs(c.z) ? a.set(-c.y, c.x, 0) : a.set(0, -c.z, c.y)) : a.crossVectors(c, d), this._x = a.x, this._y = a.y, this._z = a.z, this._w = b, this.normalize(), this
        }
    }(), inverse: function () {
        return this.conjugate().normalize(), this
    }, conjugate: function () {
        return this._x *= -1, this._y *= -1, this._z *= -1, this.onChangeCallback(), this
    }, dot: function (a) {
        return this._x * a._x + this._y * a._y + this._z * a._z + this._w * a._w
    }, lengthSq: function () {
        return this._x * this._x + this._y * this._y + this._z * this._z + this._w * this._w
    }, length: function () {
        return Math.sqrt(this._x * this._x + this._y * this._y + this._z * this._z + this._w * this._w)
    }, normalize: function () {
        var a = this.length();
        return 0 === a ? (this._z = this._y = this._x = 0, this._w = 1) : (a = 1 / a, this._x *= a, this._y *= a, this._z *= a, this._w *= a), this.onChangeCallback(), this
    }, multiply: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Quaternion: .multiply() now only accepts one argument. Use .multiplyQuaternions( a, b ) instead."), this.multiplyQuaternions(a, b)) : this.multiplyQuaternions(this, a)
    }, multiplyQuaternions: function (a, b) {
        var c = a._x, d = a._y, e = a._z, f = a._w, g = b._x, h = b._y, k = b._z, l = b._w;
        return this._x = c * l + f * g + d * k - e * h, this._y = d * l + f * h + e * g - c * k, this._z = e * l + f * k + c * h - d * g, this._w = f * l - c * g - d * h - e * k, this.onChangeCallback(), this
    }, multiplyVector3: function (a) {
        return THREE.warn("THREE.Quaternion: .multiplyVector3() has been removed. Use is now vector.applyQuaternion( quaternion ) instead."), a.applyQuaternion(this)
    }, slerp: function (a, b) {
        if (0 === b)return this;
        if (1 === b)return this.copy(a);
        var c = this._x, d = this._y, e = this._z, f = this._w, g = f * a._w + c * a._x + d * a._y + e * a._z;
        if (0 > g ? (this._w = -a._w, this._x = -a._x, this._y = -a._y, this._z = -a._z, g = -g) : this.copy(a), g >= 1)return this._w = f, this._x = c, this._y = d, this._z = e, this;
        var h = Math.acos(g), k = Math.sqrt(1 - g * g);
        return .001 > Math.abs(k) ? (this._w = .5 * (f + this._w), this._x = .5 * (c + this._x), this._y = .5 * (d + this._y), this._z = .5 * (e + this._z), this) : (g = Math.sin((1 - b) * h) / k, h = Math.sin(b * h) / k, this._w = f * g + this._w * h, this._x = c * g + this._x * h, this._y = d * g + this._y * h, this._z = e * g + this._z * h, this.onChangeCallback(), this)
    }, equals: function (a) {
        return a._x === this._x && a._y === this._y && a._z === this._z && a._w === this._w
    }, fromArray: function (a, b) {
        return void 0 === b && (b = 0), this._x = a[b], this._y = a[b + 1], this._z = a[b + 2], this._w = a[b + 3], this.onChangeCallback(), this
    }, toArray: function (a, b) {
        return void 0 === a && (a = []), void 0 === b && (b = 0), a[b] = this._x, a[b + 1] = this._y, a[b + 2] = this._z, a[b + 3] = this._w, a
    }, onChange: function (a) {
        return this.onChangeCallback = a, this
    }, onChangeCallback: function () {
    }, clone: function () {
        return new THREE.Quaternion(this._x, this._y, this._z, this._w)
    }
}, THREE.Quaternion.slerp = function (a, b, c, d) {
    return c.copy(a).slerp(b, d)
}, THREE.Vector2 = function (a, b) {
    this.x = a || 0, this.y = b || 0
}, THREE.Vector2.prototype = {
    constructor: THREE.Vector2, set: function (a, b) {
        return this.x = a, this.y = b, this
    }, setX: function (a) {
        return this.x = a, this
    }, setY: function (a) {
        return this.y = a, this
    }, setComponent: function (a, b) {
        switch (a) {
            case 0:
                this.x = b;
                break;
            case 1:
                this.y = b;
                break;
            default:
                throw Error("index is out of range: " + a)
        }
    }, getComponent: function (a) {
        switch (a) {
            case 0:
                return this.x;
            case 1:
                return this.y;
            default:
                throw Error("index is out of range: " + a)
        }
    }, copy: function (a) {
        return this.x = a.x, this.y = a.y, this
    }, add: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Vector2: .add() now only accepts one argument. Use .addVectors( a, b ) instead."), this.addVectors(a, b)) : (this.x += a.x, this.y += a.y, this)
    }, addScalar: function (a) {
        return this.x += a, this.y += a, this
    }, addVectors: function (a, b) {
        return this.x = a.x + b.x, this.y = a.y + b.y, this
    }, sub: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Vector2: .sub() now only accepts one argument. Use .subVectors( a, b ) instead."), this.subVectors(a, b)) : (this.x -= a.x, this.y -= a.y, this)
    }, subScalar: function (a) {
        return this.x -= a, this.y -= a, this
    }, subVectors: function (a, b) {
        return this.x = a.x - b.x, this.y = a.y - b.y, this
    }, multiply: function (a) {
        return this.x *= a.x, this.y *= a.y, this
    }, multiplyScalar: function (a) {
        return this.x *= a, this.y *= a, this
    }, divide: function (a) {
        return this.x /= a.x, this.y /= a.y, this
    }, divideScalar: function (a) {
        return 0 !== a ? (a = 1 / a, this.x *= a, this.y *= a) : this.y = this.x = 0, this
    }, min: function (a) {
        return this.x > a.x && (this.x = a.x), this.y > a.y && (this.y = a.y), this
    }, max: function (a) {
        return this.x < a.x && (this.x = a.x), this.y < a.y && (this.y = a.y), this
    }, clamp: function (a, b) {
        return this.x < a.x ? this.x = a.x : this.x > b.x && (this.x = b.x), this.y < a.y ? this.y = a.y : this.y > b.y && (this.y = b.y), this
    }, clampScalar: function () {
        var a, b;
        return function (c, d) {
            return void 0 === a && (a = new THREE.Vector2, b = new THREE.Vector2), a.set(c, c), b.set(d, d), this.clamp(a, b)
        }
    }(), floor: function () {
        return this.x = Math.floor(this.x), this.y = Math.floor(this.y), this
    }, ceil: function () {
        return this.x = Math.ceil(this.x), this.y = Math.ceil(this.y), this
    }, round: function () {
        return this.x = Math.round(this.x), this.y = Math.round(this.y), this
    }, roundToZero: function () {
        return this.x = 0 > this.x ? Math.ceil(this.x) : Math.floor(this.x), this.y = 0 > this.y ? Math.ceil(this.y) : Math.floor(this.y), this
    }, negate: function () {
        return this.x = -this.x, this.y = -this.y, this
    }, dot: function (a) {
        return this.x * a.x + this.y * a.y
    }, lengthSq: function () {
        return this.x * this.x + this.y * this.y
    }, length: function () {
        return Math.sqrt(this.x * this.x + this.y * this.y)
    }, normalize: function () {
        return this.divideScalar(this.length())
    }, distanceTo: function (a) {
        return Math.sqrt(this.distanceToSquared(a))
    }, distanceToSquared: function (a) {
        var b = this.x - a.x;
        return a = this.y - a.y, b * b + a * a
    }, setLength: function (a) {
        var b = this.length();
        return 0 !== b && a !== b && this.multiplyScalar(a / b), this
    }, lerp: function (a, b) {
        return this.x += (a.x - this.x) * b, this.y += (a.y - this.y) * b, this
    }, lerpVectors: function (a, b, c) {
        return this.subVectors(b, a).multiplyScalar(c).add(a), this
    }, equals: function (a) {
        return a.x === this.x && a.y === this.y
    }, fromArray: function (a, b) {
        return void 0 === b && (b = 0), this.x = a[b], this.y = a[b + 1], this
    }, toArray: function (a, b) {
        return void 0 === a && (a = []), void 0 === b && (b = 0), a[b] = this.x, a[b + 1] = this.y, a
    }, fromAttribute: function (a, b, c) {
        return void 0 === c && (c = 0), b = b * a.itemSize + c, this.x = a.array[b], this.y = a.array[b + 1], this
    }, clone: function () {
        return new THREE.Vector2(this.x, this.y)
    }
}, THREE.Vector3 = function (a, b, c) {
    this.x = a || 0, this.y = b || 0, this.z = c || 0
}, THREE.Vector3.prototype = {
    constructor: THREE.Vector3, set: function (a, b, c) {
        return this.x = a, this.y = b, this.z = c, this
    }, setX: function (a) {
        return this.x = a, this
    }, setY: function (a) {
        return this.y = a, this
    }, setZ: function (a) {
        return this.z = a, this
    }, setComponent: function (a, b) {
        switch (a) {
            case 0:
                this.x = b;
                break;
            case 1:
                this.y = b;
                break;
            case 2:
                this.z = b;
                break;
            default:
                throw Error("index is out of range: " + a)
        }
    }, getComponent: function (a) {
        switch (a) {
            case 0:
                return this.x;
            case 1:
                return this.y;
            case 2:
                return this.z;
            default:
                throw Error("index is out of range: " + a)
        }
    }, copy: function (a) {
        return this.x = a.x, this.y = a.y, this.z = a.z, this
    }, add: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Vector3: .add() now only accepts one argument. Use .addVectors( a, b ) instead."), this.addVectors(a, b)) : (this.x += a.x, this.y += a.y, this.z += a.z, this)
    }, addScalar: function (a) {
        return this.x += a, this.y += a, this.z += a, this
    }, addVectors: function (a, b) {
        return this.x = a.x + b.x, this.y = a.y + b.y, this.z = a.z + b.z, this
    }, sub: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Vector3: .sub() now only accepts one argument. Use .subVectors( a, b ) instead."), this.subVectors(a, b)) : (this.x -= a.x, this.y -= a.y, this.z -= a.z, this)
    }, subScalar: function (a) {
        return this.x -= a, this.y -= a, this.z -= a, this
    }, subVectors: function (a, b) {
        return this.x = a.x - b.x, this.y = a.y - b.y, this.z = a.z - b.z, this
    }, multiply: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Vector3: .multiply() now only accepts one argument. Use .multiplyVectors( a, b ) instead."), this.multiplyVectors(a, b)) : (this.x *= a.x, this.y *= a.y, this.z *= a.z, this)
    }, multiplyScalar: function (a) {
        return this.x *= a, this.y *= a, this.z *= a, this
    }, multiplyVectors: function (a, b) {
        return this.x = a.x * b.x, this.y = a.y * b.y, this.z = a.z * b.z, this
    }, applyEuler: function () {
        var a;
        return function (b) {
            return !1 == b instanceof THREE.Euler && THREE.error("THREE.Vector3: .applyEuler() now expects a Euler rotation rather than a Vector3 and order."), void 0 === a && (a = new THREE.Quaternion), this.applyQuaternion(a.setFromEuler(b)), this
        }
    }(), applyAxisAngle: function () {
        var a;
        return function (b, c) {
            return void 0 === a && (a = new THREE.Quaternion), this.applyQuaternion(a.setFromAxisAngle(b, c)), this
        }
    }(), applyMatrix3: function (a) {
        var b = this.x, c = this.y, d = this.z;
        return a = a.elements, this.x = a[0] * b + a[3] * c + a[6] * d, this.y = a[1] * b + a[4] * c + a[7] * d, this.z = a[2] * b + a[5] * c + a[8] * d, this
    }, applyMatrix4: function (a) {
        var b = this.x, c = this.y, d = this.z;
        return a = a.elements, this.x = a[0] * b + a[4] * c + a[8] * d + a[12], this.y = a[1] * b + a[5] * c + a[9] * d + a[13], this.z = a[2] * b + a[6] * c + a[10] * d + a[14], this
    }, applyProjection: function (a) {
        var b = this.x, c = this.y, d = this.z;
        a = a.elements;
        var e = 1 / (a[3] * b + a[7] * c + a[11] * d + a[15]);
        return this.x = (a[0] * b + a[4] * c + a[8] * d + a[12]) * e, this.y = (a[1] * b + a[5] * c + a[9] * d + a[13]) * e, this.z = (a[2] * b + a[6] * c + a[10] * d + a[14]) * e, this
    }, applyQuaternion: function (a) {
        var b = this.x, c = this.y, d = this.z, e = a.x, f = a.y, g = a.z;
        a = a.w;
        var h = a * b + f * d - g * c, k = a * c + g * b - e * d, l = a * d + e * c - f * b, b = -e * b - f * c - g * d;
        return this.x = h * a + b * -e + k * -g - l * -f, this.y = k * a + b * -f + l * -e - h * -g, this.z = l * a + b * -g + h * -f - k * -e, this
    }, project: function () {
        var a;
        return function (b) {
            return void 0 === a && (a = new THREE.Matrix4), a.multiplyMatrices(b.projectionMatrix, a.getInverse(b.matrixWorld)), this.applyProjection(a)
        }
    }(), unproject: function () {
        var a;
        return function (b) {
            return void 0 === a && (a = new THREE.Matrix4), a.multiplyMatrices(b.matrixWorld, a.getInverse(b.projectionMatrix)), this.applyProjection(a)
        }
    }(), transformDirection: function (a) {
        var b = this.x, c = this.y, d = this.z;
        return a = a.elements, this.x = a[0] * b + a[4] * c + a[8] * d, this.y = a[1] * b + a[5] * c + a[9] * d, this.z = a[2] * b + a[6] * c + a[10] * d, this.normalize(), this
    }, divide: function (a) {
        return this.x /= a.x, this.y /= a.y, this.z /= a.z, this
    }, divideScalar: function (a) {
        return 0 !== a ? (a = 1 / a, this.x *= a, this.y *= a, this.z *= a) : this.z = this.y = this.x = 0, this
    }, min: function (a) {
        return this.x > a.x && (this.x = a.x), this.y > a.y && (this.y = a.y), this.z > a.z && (this.z = a.z), this
    }, max: function (a) {
        return this.x < a.x && (this.x = a.x), this.y < a.y && (this.y = a.y), this.z < a.z && (this.z = a.z), this
    }, clamp: function (a, b) {
        return this.x < a.x ? this.x = a.x : this.x > b.x && (this.x = b.x), this.y < a.y ? this.y = a.y : this.y > b.y && (this.y = b.y), this.z < a.z ? this.z = a.z : this.z > b.z && (this.z = b.z), this
    }, clampScalar: function () {
        var a, b;
        return function (c, d) {
            return void 0 === a && (a = new THREE.Vector3, b = new THREE.Vector3), a.set(c, c, c), b.set(d, d, d), this.clamp(a, b)
        }
    }(), floor: function () {
        return this.x = Math.floor(this.x), this.y = Math.floor(this.y), this.z = Math.floor(this.z), this
    }, ceil: function () {
        return this.x = Math.ceil(this.x), this.y = Math.ceil(this.y), this.z = Math.ceil(this.z), this
    }, round: function () {
        return this.x = Math.round(this.x), this.y = Math.round(this.y), this.z = Math.round(this.z), this
    }, roundToZero: function () {
        return this.x = 0 > this.x ? Math.ceil(this.x) : Math.floor(this.x), this.y = 0 > this.y ? Math.ceil(this.y) : Math.floor(this.y), this.z = 0 > this.z ? Math.ceil(this.z) : Math.floor(this.z), this
    }, negate: function () {
        return this.x = -this.x, this.y = -this.y, this.z = -this.z, this
    }, dot: function (a) {
        return this.x * a.x + this.y * a.y + this.z * a.z
    }, lengthSq: function () {
        return this.x * this.x + this.y * this.y + this.z * this.z
    }, length: function () {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z)
    }, lengthManhattan: function () {
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z)
    }, normalize: function () {
        return this.divideScalar(this.length())
    }, setLength: function (a) {
        var b = this.length();
        return 0 !== b && a !== b && this.multiplyScalar(a / b), this
    }, lerp: function (a, b) {
        return this.x += (a.x - this.x) * b, this.y += (a.y - this.y) * b, this.z += (a.z - this.z) * b, this
    }, lerpVectors: function (a, b, c) {
        return this.subVectors(b, a).multiplyScalar(c).add(a), this
    }, cross: function (a, b) {
        if (void 0 !== b)return THREE.warn("THREE.Vector3: .cross() now only accepts one argument. Use .crossVectors( a, b ) instead."), this.crossVectors(a, b);
        var c = this.x, d = this.y, e = this.z;
        return this.x = d * a.z - e * a.y, this.y = e * a.x - c * a.z, this.z = c * a.y - d * a.x, this
    }, crossVectors: function (a, b) {
        var c = a.x, d = a.y, e = a.z, f = b.x, g = b.y, h = b.z;
        return this.x = d * h - e * g, this.y = e * f - c * h, this.z = c * g - d * f, this
    }, projectOnVector: function () {
        var a, b;
        return function (c) {
            return void 0 === a && (a = new THREE.Vector3), a.copy(c).normalize(), b = this.dot(a), this.copy(a).multiplyScalar(b)
        }
    }(), projectOnPlane: function () {
        var a;
        return function (b) {
            return void 0 === a && (a = new THREE.Vector3), a.copy(this).projectOnVector(b), this.sub(a)
        }
    }(), reflect: function () {
        var a;
        return function (b) {
            return void 0 === a && (a = new THREE.Vector3), this.sub(a.copy(b).multiplyScalar(2 * this.dot(b)))
        }
    }(), angleTo: function (a) {
        return a = this.dot(a) / (this.length() * a.length()), Math.acos(THREE.Math.clamp(a, -1, 1))
    }, distanceTo: function (a) {
        return Math.sqrt(this.distanceToSquared(a))
    }, distanceToSquared: function (a) {
        var b = this.x - a.x, c = this.y - a.y;
        return a = this.z - a.z, b * b + c * c + a * a
    }, setEulerFromRotationMatrix: function (a, b) {
        THREE.error("THREE.Vector3: .setEulerFromRotationMatrix() has been removed. Use Euler.setFromRotationMatrix() instead.")
    }, setEulerFromQuaternion: function (a, b) {
        THREE.error("THREE.Vector3: .setEulerFromQuaternion() has been removed. Use Euler.setFromQuaternion() instead.")
    }, getPositionFromMatrix: function (a) {
        return THREE.warn("THREE.Vector3: .getPositionFromMatrix() has been renamed to .setFromMatrixPosition()."), this.setFromMatrixPosition(a)
    }, getScaleFromMatrix: function (a) {
        return THREE.warn("THREE.Vector3: .getScaleFromMatrix() has been renamed to .setFromMatrixScale()."), this.setFromMatrixScale(a)
    }, getColumnFromMatrix: function (a, b) {
        return THREE.warn("THREE.Vector3: .getColumnFromMatrix() has been renamed to .setFromMatrixColumn()."), this.setFromMatrixColumn(a, b)
    }, setFromMatrixPosition: function (a) {
        return this.x = a.elements[12], this.y = a.elements[13], this.z = a.elements[14], this
    }, setFromMatrixScale: function (a) {
        var b = this.set(a.elements[0], a.elements[1], a.elements[2]).length(), c = this.set(a.elements[4], a.elements[5], a.elements[6]).length();
        return a = this.set(a.elements[8], a.elements[9], a.elements[10]).length(), this.x = b, this.y = c, this.z = a, this
    }, setFromMatrixColumn: function (a, b) {
        var c = 4 * a, d = b.elements;
        return this.x = d[c], this.y = d[c + 1], this.z = d[c + 2], this
    }, equals: function (a) {
        return a.x === this.x && a.y === this.y && a.z === this.z
    }, fromArray: function (a, b) {
        return void 0 === b && (b = 0), this.x = a[b], this.y = a[b + 1], this.z = a[b + 2], this
    }, toArray: function (a, b) {
        return void 0 === a && (a = []), void 0 === b && (b = 0), a[b] = this.x, a[b + 1] = this.y, a[b + 2] = this.z, a
    }, fromAttribute: function (a, b, c) {
        return void 0 === c && (c = 0), b = b * a.itemSize + c, this.x = a.array[b], this.y = a.array[b + 1], this.z = a.array[b + 2], this
    }, clone: function () {
        return new THREE.Vector3(this.x, this.y, this.z)
    }
},THREE.Vector4 = function (a, b, c, d) {
    this.x = a || 0, this.y = b || 0, this.z = c || 0, this.w = void 0 !== d ? d : 1
},THREE.Vector4.prototype = {
    constructor: THREE.Vector4, set: function (a, b, c, d) {
        return this.x = a, this.y = b, this.z = c, this.w = d, this
    }, setX: function (a) {
        return this.x = a, this
    }, setY: function (a) {
        return this.y = a, this
    }, setZ: function (a) {
        return this.z = a, this
    }, setW: function (a) {
        return this.w = a, this
    }, setComponent: function (a, b) {
        switch (a) {
            case 0:
                this.x = b;
                break;
            case 1:
                this.y = b;
                break;
            case 2:
                this.z = b;
                break;
            case 3:
                this.w = b;
                break;
            default:
                throw Error("index is out of range: " + a)
        }
    }, getComponent: function (a) {
        switch (a) {
            case 0:
                return this.x;
            case 1:
                return this.y;
            case 2:
                return this.z;
            case 3:
                return this.w;
            default:
                throw Error("index is out of range: " + a)
        }
    }, copy: function (a) {
        return this.x = a.x, this.y = a.y, this.z = a.z, this.w = void 0 !== a.w ? a.w : 1, this
    }, add: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Vector4: .add() now only accepts one argument. Use .addVectors( a, b ) instead."), this.addVectors(a, b)) : (this.x += a.x, this.y += a.y, this.z += a.z, this.w += a.w, this)
    }, addScalar: function (a) {
        return this.x += a, this.y += a, this.z += a, this.w += a, this
    }, addVectors: function (a, b) {
        return this.x = a.x + b.x, this.y = a.y + b.y, this.z = a.z + b.z, this.w = a.w + b.w, this
    }, sub: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Vector4: .sub() now only accepts one argument. Use .subVectors( a, b ) instead."), this.subVectors(a, b)) : (this.x -= a.x, this.y -= a.y, this.z -= a.z, this.w -= a.w, this)
    }, subScalar: function (a) {
        return this.x -= a, this.y -= a, this.z -= a, this.w -= a, this
    }, subVectors: function (a, b) {
        return this.x = a.x - b.x, this.y = a.y - b.y, this.z = a.z - b.z, this.w = a.w - b.w, this
    }, multiplyScalar: function (a) {
        return this.x *= a, this.y *= a, this.z *= a, this.w *= a, this
    }, applyMatrix4: function (a) {
        var b = this.x, c = this.y, d = this.z, e = this.w;
        return a = a.elements, this.x = a[0] * b + a[4] * c + a[8] * d + a[12] * e, this.y = a[1] * b + a[5] * c + a[9] * d + a[13] * e, this.z = a[2] * b + a[6] * c + a[10] * d + a[14] * e, this.w = a[3] * b + a[7] * c + a[11] * d + a[15] * e, this
    }, divideScalar: function (a) {
        return 0 !== a ? (a = 1 / a, this.x *= a, this.y *= a, this.z *= a, this.w *= a) : (this.z = this.y = this.x = 0, this.w = 1), this
    }, setAxisAngleFromQuaternion: function (a) {
        this.w = 2 * Math.acos(a.w);
        var b = Math.sqrt(1 - a.w * a.w);
        return 1e-4 > b ? (this.x = 1, this.z = this.y = 0) : (this.x = a.x / b, this.y = a.y / b, this.z = a.z / b), this
    }, setAxisAngleFromRotationMatrix: function (a) {
        var b, c, d;
        a = a.elements;
        var e = a[0];
        d = a[4];
        var f = a[8], g = a[1], h = a[5], k = a[9];
        c = a[2], b = a[6];
        var l = a[10];
        return .01 > Math.abs(d - g) && .01 > Math.abs(f - c) && .01 > Math.abs(k - b) ? .1 > Math.abs(d + g) && .1 > Math.abs(f + c) && .1 > Math.abs(k + b) && .1 > Math.abs(e + h + l - 3) ? (this.set(1, 0, 0, 0), this) : (a = Math.PI, e = (e + 1) / 2, h = (h + 1) / 2, l = (l + 1) / 2, d = (d + g) / 4, f = (f + c) / 4, k = (k + b) / 4, e > h && e > l ? .01 > e ? (b = 0, d = c = .707106781) : (b = Math.sqrt(e), c = d / b, d = f / b) : h > l ? .01 > h ? (b = .707106781, c = 0, d = .707106781) : (c = Math.sqrt(h), b = d / c, d = k / c) : .01 > l ? (c = b = .707106781, d = 0) : (d = Math.sqrt(l), b = f / d, c = k / d), this.set(b, c, d, a), this) : (a = Math.sqrt((b - k) * (b - k) + (f - c) * (f - c) + (g - d) * (g - d)), .001 > Math.abs(a) && (a = 1), this.x = (b - k) / a, this.y = (f - c) / a, this.z = (g - d) / a, this.w = Math.acos((e + h + l - 1) / 2), this)
    }, min: function (a) {
        return this.x > a.x && (this.x = a.x), this.y > a.y && (this.y = a.y), this.z > a.z && (this.z = a.z), this.w > a.w && (this.w = a.w), this
    }, max: function (a) {
        return this.x < a.x && (this.x = a.x), this.y < a.y && (this.y = a.y), this.z < a.z && (this.z = a.z), this.w < a.w && (this.w = a.w), this
    }, clamp: function (a, b) {
        return this.x < a.x ? this.x = a.x : this.x > b.x && (this.x = b.x), this.y < a.y ? this.y = a.y : this.y > b.y && (this.y = b.y), this.z < a.z ? this.z = a.z : this.z > b.z && (this.z = b.z), this.w < a.w ? this.w = a.w : this.w > b.w && (this.w = b.w), this
    }, clampScalar: function () {
        var a, b;
        return function (c, d) {
            return void 0 === a && (a = new THREE.Vector4, b = new THREE.Vector4), a.set(c, c, c, c), b.set(d, d, d, d), this.clamp(a, b)
        }
    }(), floor: function () {
        return this.x = Math.floor(this.x), this.y = Math.floor(this.y), this.z = Math.floor(this.z), this.w = Math.floor(this.w), this
    }, ceil: function () {
        return this.x = Math.ceil(this.x), this.y = Math.ceil(this.y), this.z = Math.ceil(this.z), this.w = Math.ceil(this.w), this
    }, round: function () {
        return this.x = Math.round(this.x), this.y = Math.round(this.y), this.z = Math.round(this.z), this.w = Math.round(this.w), this
    }, roundToZero: function () {
        return this.x = 0 > this.x ? Math.ceil(this.x) : Math.floor(this.x), this.y = 0 > this.y ? Math.ceil(this.y) : Math.floor(this.y), this.z = 0 > this.z ? Math.ceil(this.z) : Math.floor(this.z), this.w = 0 > this.w ? Math.ceil(this.w) : Math.floor(this.w), this
    }, negate: function () {
        return this.x = -this.x, this.y = -this.y, this.z = -this.z, this.w = -this.w, this
    }, dot: function (a) {
        return this.x * a.x + this.y * a.y + this.z * a.z + this.w * a.w
    }, lengthSq: function () {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w
    }, length: function () {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w)
    }, lengthManhattan: function () {
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z) + Math.abs(this.w)
    }, normalize: function () {
        return this.divideScalar(this.length())
    }, setLength: function (a) {
        var b = this.length();
        return 0 !== b && a !== b && this.multiplyScalar(a / b), this
    }, lerp: function (a, b) {
        return this.x += (a.x - this.x) * b, this.y += (a.y - this.y) * b, this.z += (a.z - this.z) * b, this.w += (a.w - this.w) * b, this
    }, lerpVectors: function (a, b, c) {
        return this.subVectors(b, a).multiplyScalar(c).add(a), this
    }, equals: function (a) {
        return a.x === this.x && a.y === this.y && a.z === this.z && a.w === this.w
    }, fromArray: function (a, b) {
        return void 0 === b && (b = 0), this.x = a[b], this.y = a[b + 1], this.z = a[b + 2], this.w = a[b + 3], this
    }, toArray: function (a, b) {
        return void 0 === a && (a = []), void 0 === b && (b = 0), a[b] = this.x, a[b + 1] = this.y, a[b + 2] = this.z, a[b + 3] = this.w, a
    }, fromAttribute: function (a, b, c) {
        return void 0 === c && (c = 0), b = b * a.itemSize + c, this.x = a.array[b], this.y = a.array[b + 1], this.z = a.array[b + 2], this.w = a.array[b + 3], this
    }, clone: function () {
        return new THREE.Vector4(this.x, this.y, this.z, this.w)
    }
},THREE.Euler = function (a, b, c, d) {
    this._x = a || 0, this._y = b || 0, this._z = c || 0, this._order = d || THREE.Euler.DefaultOrder
},THREE.Euler.RotationOrders = "XYZ YZX ZXY XZY YXZ ZYX".split(" "),THREE.Euler.DefaultOrder = "XYZ",THREE.Euler.prototype = {
    constructor: THREE.Euler, _x: 0, _y: 0, _z: 0, _order: THREE.Euler.DefaultOrder, get x() {
        return this._x
    }, set x(a) {
        this._x = a, this.onChangeCallback()
    }, get y() {
        return this._y
    }, set y(a) {
        this._y = a, this.onChangeCallback()
    }, get z() {
        return this._z
    }, set z(a) {
        this._z = a, this.onChangeCallback()
    }, get order() {
        return this._order
    }, set order(a) {
        this._order = a, this.onChangeCallback()
    }, set: function (a, b, c, d) {
        return this._x = a, this._y = b, this._z = c, this._order = d || this._order, this.onChangeCallback(), this
    }, copy: function (a) {
        return this._x = a._x, this._y = a._y, this._z = a._z, this._order = a._order, this.onChangeCallback(), this
    }, setFromRotationMatrix: function (a, b, c) {
        var d = THREE.Math.clamp, e = a.elements;
        a = e[0];
        var f = e[4], g = e[8], h = e[1], k = e[5], l = e[9], p = e[2], q = e[6], e = e[10];
        return b = b || this._order, "XYZ" === b ? (this._y = Math.asin(d(g, -1, 1)), .99999 > Math.abs(g) ? (this._x = Math.atan2(-l, e), this._z = Math.atan2(-f, a)) : (this._x = Math.atan2(q, k), this._z = 0)) : "YXZ" === b ? (this._x = Math.asin(-d(l, -1, 1)), .99999 > Math.abs(l) ? (this._y = Math.atan2(g, e), this._z = Math.atan2(h, k)) : (this._y = Math.atan2(-p, a), this._z = 0)) : "ZXY" === b ? (this._x = Math.asin(d(q, -1, 1)), .99999 > Math.abs(q) ? (this._y = Math.atan2(-p, e), this._z = Math.atan2(-f, k)) : (this._y = 0, this._z = Math.atan2(h, a))) : "ZYX" === b ? (this._y = Math.asin(-d(p, -1, 1)), .99999 > Math.abs(p) ? (this._x = Math.atan2(q, e), this._z = Math.atan2(h, a)) : (this._x = 0, this._z = Math.atan2(-f, k))) : "YZX" === b ? (this._z = Math.asin(d(h, -1, 1)), .99999 > Math.abs(h) ? (this._x = Math.atan2(-l, k), this._y = Math.atan2(-p, a)) : (this._x = 0, this._y = Math.atan2(g, e))) : "XZY" === b ? (this._z = Math.asin(-d(f, -1, 1)), .99999 > Math.abs(f) ? (this._x = Math.atan2(q, k), this._y = Math.atan2(g, a)) : (this._x = Math.atan2(-l, e), this._y = 0)) : THREE.warn("THREE.Euler: .setFromRotationMatrix() given unsupported order: " + b), this._order = b, !1 !== c && this.onChangeCallback(), this
    }, setFromQuaternion: function () {
        var a;
        return function (b, c, d) {
            return void 0 === a && (a = new THREE.Matrix4), a.makeRotationFromQuaternion(b), this.setFromRotationMatrix(a, c, d), this
        }
    }(), setFromVector3: function (a, b) {
        return this.set(a.x, a.y, a.z, b || this._order)
    }, reorder: function () {
        var a = new THREE.Quaternion;
        return function (b) {
            a.setFromEuler(this), this.setFromQuaternion(a, b)
        }
    }(), equals: function (a) {
        return a._x === this._x && a._y === this._y && a._z === this._z && a._order === this._order;
    }, fromArray: function (a) {
        return this._x = a[0], this._y = a[1], this._z = a[2], void 0 !== a[3] && (this._order = a[3]), this.onChangeCallback(), this
    }, toArray: function (a, b) {
        return void 0 === a && (a = []), void 0 === b && (b = 0), a[b] = this._x, a[b + 1] = this._y, a[b + 2] = this._z, a[b + 3] = this._order, a
    }, toVector3: function (a) {
        return a ? a.set(this._x, this._y, this._z) : new THREE.Vector3(this._x, this._y, this._z)
    }, onChange: function (a) {
        return this.onChangeCallback = a, this
    }, onChangeCallback: function () {
    }, clone: function () {
        return new THREE.Euler(this._x, this._y, this._z, this._order)
    }
},THREE.Line3 = function (a, b) {
    this.start = void 0 !== a ? a : new THREE.Vector3, this.end = void 0 !== b ? b : new THREE.Vector3
},THREE.Line3.prototype = {
    constructor: THREE.Line3, set: function (a, b) {
        return this.start.copy(a), this.end.copy(b), this
    }, copy: function (a) {
        return this.start.copy(a.start), this.end.copy(a.end), this
    }, center: function (a) {
        return (a || new THREE.Vector3).addVectors(this.start, this.end).multiplyScalar(.5)
    }, delta: function (a) {
        return (a || new THREE.Vector3).subVectors(this.end, this.start)
    }, distanceSq: function () {
        return this.start.distanceToSquared(this.end)
    }, distance: function () {
        return this.start.distanceTo(this.end)
    }, at: function (a, b) {
        var c = b || new THREE.Vector3;
        return this.delta(c).multiplyScalar(a).add(this.start)
    }, closestPointToPointParameter: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3;
        return function (c, d) {
            a.subVectors(c, this.start), b.subVectors(this.end, this.start);
            var e = b.dot(b), e = b.dot(a) / e;
            return d && (e = THREE.Math.clamp(e, 0, 1)), e
        }
    }(), closestPointToPoint: function (a, b, c) {
        return a = this.closestPointToPointParameter(a, b), c = c || new THREE.Vector3, this.delta(c).multiplyScalar(a).add(this.start)
    }, applyMatrix4: function (a) {
        return this.start.applyMatrix4(a), this.end.applyMatrix4(a), this
    }, equals: function (a) {
        return a.start.equals(this.start) && a.end.equals(this.end)
    }, clone: function () {
        return (new THREE.Line3).copy(this)
    }
},THREE.Box2 = function (a, b) {
    this.min = void 0 !== a ? a : new THREE.Vector2(1 / 0, 1 / 0), this.max = void 0 !== b ? b : new THREE.Vector2(-(1 / 0), -(1 / 0))
},THREE.Box2.prototype = {
    constructor: THREE.Box2, set: function (a, b) {
        return this.min.copy(a), this.max.copy(b), this
    }, setFromPoints: function (a) {
        this.makeEmpty();
        for (var b = 0, c = a.length; c > b; b++)this.expandByPoint(a[b]);
        return this
    }, setFromCenterAndSize: function () {
        var a = new THREE.Vector2;
        return function (b, c) {
            var d = a.copy(c).multiplyScalar(.5);
            return this.min.copy(b).sub(d), this.max.copy(b).add(d), this
        }
    }(), copy: function (a) {
        return this.min.copy(a.min), this.max.copy(a.max), this
    }, makeEmpty: function () {
        return this.min.x = this.min.y = 1 / 0, this.max.x = this.max.y = -(1 / 0), this
    }, empty: function () {
        return this.max.x < this.min.x || this.max.y < this.min.y
    }, center: function (a) {
        return (a || new THREE.Vector2).addVectors(this.min, this.max).multiplyScalar(.5)
    }, size: function (a) {
        return (a || new THREE.Vector2).subVectors(this.max, this.min)
    }, expandByPoint: function (a) {
        return this.min.min(a), this.max.max(a), this
    }, expandByVector: function (a) {
        return this.min.sub(a), this.max.add(a), this
    }, expandByScalar: function (a) {
        return this.min.addScalar(-a), this.max.addScalar(a), this
    }, containsPoint: function (a) {
        return a.x < this.min.x || a.x > this.max.x || a.y < this.min.y || a.y > this.max.y ? !1 : !0
    }, containsBox: function (a) {
        return this.min.x <= a.min.x && a.max.x <= this.max.x && this.min.y <= a.min.y && a.max.y <= this.max.y ? !0 : !1
    }, getParameter: function (a, b) {
        return (b || new THREE.Vector2).set((a.x - this.min.x) / (this.max.x - this.min.x), (a.y - this.min.y) / (this.max.y - this.min.y))
    }, isIntersectionBox: function (a) {
        return a.max.x < this.min.x || a.min.x > this.max.x || a.max.y < this.min.y || a.min.y > this.max.y ? !1 : !0
    }, clampPoint: function (a, b) {
        return (b || new THREE.Vector2).copy(a).clamp(this.min, this.max)
    }, distanceToPoint: function () {
        var a = new THREE.Vector2;
        return function (b) {
            return a.copy(b).clamp(this.min, this.max).sub(b).length()
        }
    }(), intersect: function (a) {
        return this.min.max(a.min), this.max.min(a.max), this
    }, union: function (a) {
        return this.min.min(a.min), this.max.max(a.max), this
    }, translate: function (a) {
        return this.min.add(a), this.max.add(a), this
    }, equals: function (a) {
        return a.min.equals(this.min) && a.max.equals(this.max)
    }, clone: function () {
        return (new THREE.Box2).copy(this)
    }
},THREE.Box3 = function (a, b) {
    this.min = void 0 !== a ? a : new THREE.Vector3(1 / 0, 1 / 0, 1 / 0), this.max = void 0 !== b ? b : new THREE.Vector3(-(1 / 0), -(1 / 0), -(1 / 0))
},THREE.Box3.prototype = {
    constructor: THREE.Box3, set: function (a, b) {
        return this.min.copy(a), this.max.copy(b), this
    }, setFromPoints: function (a) {
        this.makeEmpty();
        for (var b = 0, c = a.length; c > b; b++)this.expandByPoint(a[b]);
        return this
    }, setFromCenterAndSize: function () {
        var a = new THREE.Vector3;
        return function (b, c) {
            var d = a.copy(c).multiplyScalar(.5);
            return this.min.copy(b).sub(d), this.max.copy(b).add(d), this
        }
    }(), setFromObject: function () {
        var a = new THREE.Vector3;
        return function (b) {
            var c = this;
            return b.updateMatrixWorld(!0), this.makeEmpty(), b.traverse(function (b) {
                var e = b.geometry;
                if (void 0 !== e)if (e instanceof THREE.Geometry)for (var f = e.vertices, e = 0, g = f.length; g > e; e++)a.copy(f[e]), a.applyMatrix4(b.matrixWorld), c.expandByPoint(a); else if (e instanceof THREE.BufferGeometry && void 0 !== e.attributes.position)for (f = e.attributes.position.array, e = 0, g = f.length; g > e; e += 3)a.set(f[e], f[e + 1], f[e + 2]), a.applyMatrix4(b.matrixWorld), c.expandByPoint(a)
            }), this
        }
    }(), copy: function (a) {
        return this.min.copy(a.min), this.max.copy(a.max), this
    }, makeEmpty: function () {
        return this.min.x = this.min.y = this.min.z = 1 / 0, this.max.x = this.max.y = this.max.z = -(1 / 0), this
    }, empty: function () {
        return this.max.x < this.min.x || this.max.y < this.min.y || this.max.z < this.min.z
    }, center: function (a) {
        return (a || new THREE.Vector3).addVectors(this.min, this.max).multiplyScalar(.5)
    }, size: function (a) {
        return (a || new THREE.Vector3).subVectors(this.max, this.min)
    }, expandByPoint: function (a) {
        return this.min.min(a), this.max.max(a), this
    }, expandByVector: function (a) {
        return this.min.sub(a), this.max.add(a), this
    }, expandByScalar: function (a) {
        return this.min.addScalar(-a), this.max.addScalar(a), this
    }, containsPoint: function (a) {
        return a.x < this.min.x || a.x > this.max.x || a.y < this.min.y || a.y > this.max.y || a.z < this.min.z || a.z > this.max.z ? !1 : !0
    }, containsBox: function (a) {
        return this.min.x <= a.min.x && a.max.x <= this.max.x && this.min.y <= a.min.y && a.max.y <= this.max.y && this.min.z <= a.min.z && a.max.z <= this.max.z ? !0 : !1
    }, getParameter: function (a, b) {
        return (b || new THREE.Vector3).set((a.x - this.min.x) / (this.max.x - this.min.x), (a.y - this.min.y) / (this.max.y - this.min.y), (a.z - this.min.z) / (this.max.z - this.min.z))
    }, isIntersectionBox: function (a) {
        return a.max.x < this.min.x || a.min.x > this.max.x || a.max.y < this.min.y || a.min.y > this.max.y || a.max.z < this.min.z || a.min.z > this.max.z ? !1 : !0
    }, clampPoint: function (a, b) {
        return (b || new THREE.Vector3).copy(a).clamp(this.min, this.max)
    }, distanceToPoint: function () {
        var a = new THREE.Vector3;
        return function (b) {
            return a.copy(b).clamp(this.min, this.max).sub(b).length()
        }
    }(), getBoundingSphere: function () {
        var a = new THREE.Vector3;
        return function (b) {
            return b = b || new THREE.Sphere, b.center = this.center(), b.radius = .5 * this.size(a).length(), b
        }
    }(), intersect: function (a) {
        return this.min.max(a.min), this.max.min(a.max), this
    }, union: function (a) {
        return this.min.min(a.min), this.max.max(a.max), this
    }, applyMatrix4: function () {
        var a = [new THREE.Vector3, new THREE.Vector3, new THREE.Vector3, new THREE.Vector3, new THREE.Vector3, new THREE.Vector3, new THREE.Vector3, new THREE.Vector3];
        return function (b) {
            return a[0].set(this.min.x, this.min.y, this.min.z).applyMatrix4(b), a[1].set(this.min.x, this.min.y, this.max.z).applyMatrix4(b), a[2].set(this.min.x, this.max.y, this.min.z).applyMatrix4(b), a[3].set(this.min.x, this.max.y, this.max.z).applyMatrix4(b), a[4].set(this.max.x, this.min.y, this.min.z).applyMatrix4(b), a[5].set(this.max.x, this.min.y, this.max.z).applyMatrix4(b), a[6].set(this.max.x, this.max.y, this.min.z).applyMatrix4(b), a[7].set(this.max.x, this.max.y, this.max.z).applyMatrix4(b), this.makeEmpty(), this.setFromPoints(a), this
        }
    }(), translate: function (a) {
        return this.min.add(a), this.max.add(a), this
    }, equals: function (a) {
        return a.min.equals(this.min) && a.max.equals(this.max)
    }, clone: function () {
        return (new THREE.Box3).copy(this)
    }
},THREE.Matrix3 = function () {
    this.elements = new Float32Array([1, 0, 0, 0, 1, 0, 0, 0, 1]), 0 < arguments.length && THREE.error("THREE.Matrix3: the constructor no longer reads arguments. use .set() instead.")
},THREE.Matrix3.prototype = {
    constructor: THREE.Matrix3, set: function (a, b, c, d, e, f, g, h, k) {
        var l = this.elements;
        return l[0] = a, l[3] = b, l[6] = c, l[1] = d, l[4] = e, l[7] = f, l[2] = g, l[5] = h, l[8] = k, this
    }, identity: function () {
        return this.set(1, 0, 0, 0, 1, 0, 0, 0, 1), this
    }, copy: function (a) {
        return a = a.elements, this.set(a[0], a[3], a[6], a[1], a[4], a[7], a[2], a[5], a[8]), this
    }, multiplyVector3: function (a) {
        return THREE.warn("THREE.Matrix3: .multiplyVector3() has been removed. Use vector.applyMatrix3( matrix ) instead."), a.applyMatrix3(this)
    }, multiplyVector3Array: function (a) {
        return THREE.warn("THREE.Matrix3: .multiplyVector3Array() has been renamed. Use matrix.applyToVector3Array( array ) instead."), this.applyToVector3Array(a)
    }, applyToVector3Array: function () {
        var a = new THREE.Vector3;
        return function (b, c, d) {
            void 0 === c && (c = 0), void 0 === d && (d = b.length);
            for (var e = 0; d > e; e += 3, c += 3)a.x = b[c], a.y = b[c + 1], a.z = b[c + 2], a.applyMatrix3(this), b[c] = a.x, b[c + 1] = a.y, b[c + 2] = a.z;
            return b
        }
    }(), multiplyScalar: function (a) {
        var b = this.elements;
        return b[0] *= a, b[3] *= a, b[6] *= a, b[1] *= a, b[4] *= a, b[7] *= a, b[2] *= a, b[5] *= a, b[8] *= a, this
    }, determinant: function () {
        var a = this.elements, b = a[0], c = a[1], d = a[2], e = a[3], f = a[4], g = a[5], h = a[6], k = a[7], a = a[8];
        return b * f * a - b * g * k - c * e * a + c * g * h + d * e * k - d * f * h
    }, getInverse: function (a, b) {
        var c = a.elements, d = this.elements;
        if (d[0] = c[10] * c[5] - c[6] * c[9], d[1] = -c[10] * c[1] + c[2] * c[9], d[2] = c[6] * c[1] - c[2] * c[5], d[3] = -c[10] * c[4] + c[6] * c[8], d[4] = c[10] * c[0] - c[2] * c[8], d[5] = -c[6] * c[0] + c[2] * c[4], d[6] = c[9] * c[4] - c[5] * c[8], d[7] = -c[9] * c[0] + c[1] * c[8], d[8] = c[5] * c[0] - c[1] * c[4], c = c[0] * d[0] + c[1] * d[3] + c[2] * d[6], 0 === c) {
            if (b)throw Error("Matrix3.getInverse(): can't invert matrix, determinant is 0");
            return THREE.warn("Matrix3.getInverse(): can't invert matrix, determinant is 0"), this.identity(), this
        }
        return this.multiplyScalar(1 / c), this
    }, transpose: function () {
        var a, b = this.elements;
        return a = b[1], b[1] = b[3], b[3] = a, a = b[2], b[2] = b[6], b[6] = a, a = b[5], b[5] = b[7], b[7] = a, this
    }, flattenToArrayOffset: function (a, b) {
        var c = this.elements;
        return a[b] = c[0], a[b + 1] = c[1], a[b + 2] = c[2], a[b + 3] = c[3], a[b + 4] = c[4], a[b + 5] = c[5], a[b + 6] = c[6], a[b + 7] = c[7], a[b + 8] = c[8], a
    }, getNormalMatrix: function (a) {
        return this.getInverse(a).transpose(), this
    }, transposeIntoArray: function (a) {
        var b = this.elements;
        return a[0] = b[0], a[1] = b[3], a[2] = b[6], a[3] = b[1], a[4] = b[4], a[5] = b[7], a[6] = b[2], a[7] = b[5], a[8] = b[8], this
    }, fromArray: function (a) {
        return this.elements.set(a), this
    }, toArray: function () {
        var a = this.elements;
        return [a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8]]
    }, clone: function () {
        return (new THREE.Matrix3).fromArray(this.elements)
    }
},THREE.Matrix4 = function () {
    this.elements = new Float32Array([1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1]), 0 < arguments.length && THREE.error("THREE.Matrix4: the constructor no longer reads arguments. use .set() instead.")
},THREE.Matrix4.prototype = {
    constructor: THREE.Matrix4,
    set: function (a, b, c, d, e, f, g, h, k, l, p, q, n, t, r, s) {
        var u = this.elements;
        return u[0] = a, u[4] = b, u[8] = c, u[12] = d, u[1] = e, u[5] = f, u[9] = g, u[13] = h, u[2] = k, u[6] = l, u[10] = p, u[14] = q, u[3] = n, u[7] = t, u[11] = r, u[15] = s, this
    },
    identity: function () {
        return this.set(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1), this
    },
    copy: function (a) {
        return this.elements.set(a.elements), this
    },
    extractPosition: function (a) {
        return THREE.warn("THREE.Matrix4: .extractPosition() has been renamed to .copyPosition()."), this.copyPosition(a)
    },
    copyPosition: function (a) {
        var b = this.elements;
        return a = a.elements, b[12] = a[12], b[13] = a[13], b[14] = a[14], this
    },
    extractBasis: function (a, b, c) {
        var d = this.elements;
        return a.set(d[0], d[1], d[2]), b.set(d[4], d[5], d[6]), c.set(d[8], d[9], d[10]), this
    },
    makeBasis: function (a, b, c) {
        return this.set(a.x, b.x, c.x, 0, a.y, b.y, c.y, 0, a.z, b.z, c.z, 0, 0, 0, 0, 1), this
    },
    extractRotation: function () {
        var a = new THREE.Vector3;
        return function (b) {
            var c = this.elements;
            b = b.elements;
            var d = 1 / a.set(b[0], b[1], b[2]).length(), e = 1 / a.set(b[4], b[5], b[6]).length(), f = 1 / a.set(b[8], b[9], b[10]).length();
            return c[0] = b[0] * d, c[1] = b[1] * d, c[2] = b[2] * d, c[4] = b[4] * e, c[5] = b[5] * e, c[6] = b[6] * e, c[8] = b[8] * f, c[9] = b[9] * f, c[10] = b[10] * f, this
        }
    }(),
    makeRotationFromEuler: function (a) {
        !1 == a instanceof THREE.Euler && THREE.error("THREE.Matrix: .makeRotationFromEuler() now expects a Euler rotation rather than a Vector3 and order.");
        var b = this.elements, c = a.x, d = a.y, e = a.z, f = Math.cos(c), c = Math.sin(c), g = Math.cos(d), d = Math.sin(d), h = Math.cos(e), e = Math.sin(e);
        if ("XYZ" === a.order) {
            a = f * h;
            var k = f * e, l = c * h, p = c * e;
            b[0] = g * h, b[4] = -g * e, b[8] = d, b[1] = k + l * d, b[5] = a - p * d, b[9] = -c * g, b[2] = p - a * d, b[6] = l + k * d, b[10] = f * g
        } else"YXZ" === a.order ? (a = g * h, k = g * e, l = d * h, p = d * e, b[0] = a + p * c, b[4] = l * c - k, b[8] = f * d, b[1] = f * e, b[5] = f * h, b[9] = -c, b[2] = k * c - l, b[6] = p + a * c, b[10] = f * g) : "ZXY" === a.order ? (a = g * h, k = g * e, l = d * h, p = d * e, b[0] = a - p * c, b[4] = -f * e, b[8] = l + k * c, b[1] = k + l * c, b[5] = f * h, b[9] = p - a * c, b[2] = -f * d, b[6] = c, b[10] = f * g) : "ZYX" === a.order ? (a = f * h, k = f * e, l = c * h, p = c * e, b[0] = g * h, b[4] = l * d - k, b[8] = a * d + p, b[1] = g * e, b[5] = p * d + a, b[9] = k * d - l, b[2] = -d, b[6] = c * g, b[10] = f * g) : "YZX" === a.order ? (a = f * g, k = f * d, l = c * g, p = c * d, b[0] = g * h, b[4] = p - a * e, b[8] = l * e + k, b[1] = e, b[5] = f * h, b[9] = -c * h, b[2] = -d * h, b[6] = k * e + l, b[10] = a - p * e) : "XZY" === a.order && (a = f * g, k = f * d, l = c * g, p = c * d, b[0] = g * h, b[4] = -e, b[8] = d * h, b[1] = a * e + p, b[5] = f * h, b[9] = k * e - l, b[2] = l * e - k, b[6] = c * h, b[10] = p * e + a);
        return b[3] = 0, b[7] = 0, b[11] = 0, b[12] = 0, b[13] = 0, b[14] = 0, b[15] = 1, this
    },
    setRotationFromQuaternion: function (a) {
        return THREE.warn("THREE.Matrix4: .setRotationFromQuaternion() has been renamed to .makeRotationFromQuaternion()."), this.makeRotationFromQuaternion(a)
    },
    makeRotationFromQuaternion: function (a) {
        var b = this.elements, c = a.x, d = a.y, e = a.z, f = a.w, g = c + c, h = d + d, k = e + e;
        a = c * g;
        var l = c * h, c = c * k, p = d * h, d = d * k, e = e * k, g = f * g, h = f * h, f = f * k;
        return b[0] = 1 - (p + e), b[4] = l - f, b[8] = c + h, b[1] = l + f, b[5] = 1 - (a + e), b[9] = d - g, b[2] = c - h, b[6] = d + g, b[10] = 1 - (a + p), b[3] = 0, b[7] = 0, b[11] = 0, b[12] = 0, b[13] = 0, b[14] = 0, b[15] = 1, this
    },
    lookAt: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3, c = new THREE.Vector3;
        return function (d, e, f) {
            var g = this.elements;
            return c.subVectors(d, e).normalize(), 0 === c.length() && (c.z = 1), a.crossVectors(f, c).normalize(), 0 === a.length() && (c.x += 1e-4, a.crossVectors(f, c).normalize()), b.crossVectors(c, a), g[0] = a.x, g[4] = b.x, g[8] = c.x, g[1] = a.y, g[5] = b.y, g[9] = c.y, g[2] = a.z, g[6] = b.z, g[10] = c.z, this
        }
    }(),
    multiply: function (a, b) {
        return void 0 !== b ? (THREE.warn("THREE.Matrix4: .multiply() now only accepts one argument. Use .multiplyMatrices( a, b ) instead."), this.multiplyMatrices(a, b)) : this.multiplyMatrices(this, a)
    },
    multiplyMatrices: function (a, b) {
        var c = a.elements, d = b.elements, e = this.elements, f = c[0], g = c[4], h = c[8], k = c[12], l = c[1], p = c[5], q = c[9], n = c[13], t = c[2], r = c[6], s = c[10], u = c[14], v = c[3], x = c[7], D = c[11], c = c[15], w = d[0], y = d[4], A = d[8], E = d[12], G = d[1], F = d[5], z = d[9], I = d[13], U = d[2], M = d[6], H = d[10], L = d[14], P = d[3], N = d[7], R = d[11], d = d[15];
        return e[0] = f * w + g * G + h * U + k * P, e[4] = f * y + g * F + h * M + k * N, e[8] = f * A + g * z + h * H + k * R, e[12] = f * E + g * I + h * L + k * d, e[1] = l * w + p * G + q * U + n * P, e[5] = l * y + p * F + q * M + n * N, e[9] = l * A + p * z + q * H + n * R, e[13] = l * E + p * I + q * L + n * d, e[2] = t * w + r * G + s * U + u * P, e[6] = t * y + r * F + s * M + u * N, e[10] = t * A + r * z + s * H + u * R, e[14] = t * E + r * I + s * L + u * d, e[3] = v * w + x * G + D * U + c * P, e[7] = v * y + x * F + D * M + c * N, e[11] = v * A + x * z + D * H + c * R, e[15] = v * E + x * I + D * L + c * d, this
    },
    multiplyToArray: function (a, b, c) {
        var d = this.elements;
        return this.multiplyMatrices(a, b), c[0] = d[0], c[1] = d[1], c[2] = d[2], c[3] = d[3], c[4] = d[4], c[5] = d[5], c[6] = d[6], c[7] = d[7], c[8] = d[8], c[9] = d[9], c[10] = d[10], c[11] = d[11], c[12] = d[12], c[13] = d[13], c[14] = d[14], c[15] = d[15], this
    },
    multiplyScalar: function (a) {
        var b = this.elements;
        return b[0] *= a, b[4] *= a, b[8] *= a, b[12] *= a, b[1] *= a, b[5] *= a, b[9] *= a, b[13] *= a, b[2] *= a, b[6] *= a, b[10] *= a, b[14] *= a, b[3] *= a, b[7] *= a, b[11] *= a, b[15] *= a, this
    },
    multiplyVector3: function (a) {
        return THREE.warn("THREE.Matrix4: .multiplyVector3() has been removed. Use vector.applyMatrix4( matrix ) or vector.applyProjection( matrix ) instead."), a.applyProjection(this)
    },
    multiplyVector4: function (a) {
        return THREE.warn("THREE.Matrix4: .multiplyVector4() has been removed. Use vector.applyMatrix4( matrix ) instead."), a.applyMatrix4(this)
    },
    multiplyVector3Array: function (a) {
        return THREE.warn("THREE.Matrix4: .multiplyVector3Array() has been renamed. Use matrix.applyToVector3Array( array ) instead."), this.applyToVector3Array(a)
    },
    applyToVector3Array: function () {
        var a = new THREE.Vector3;
        return function (b, c, d) {
            void 0 === c && (c = 0), void 0 === d && (d = b.length);
            for (var e = 0; d > e; e += 3, c += 3)a.x = b[c], a.y = b[c + 1], a.z = b[c + 2], a.applyMatrix4(this), b[c] = a.x, b[c + 1] = a.y, b[c + 2] = a.z;
            return b
        }
    }(),
    rotateAxis: function (a) {
        THREE.warn("THREE.Matrix4: .rotateAxis() has been removed. Use Vector3.transformDirection( matrix ) instead."), a.transformDirection(this)
    },
    crossVector: function (a) {
        return THREE.warn("THREE.Matrix4: .crossVector() has been removed. Use vector.applyMatrix4( matrix ) instead."), a.applyMatrix4(this)
    },
    determinant: function () {
        var a = this.elements, b = a[0], c = a[4], d = a[8], e = a[12], f = a[1], g = a[5], h = a[9], k = a[13], l = a[2], p = a[6], q = a[10], n = a[14];
        return a[3] * (+e * h * p - d * k * p - e * g * q + c * k * q + d * g * n - c * h * n) + a[7] * (+b * h * n - b * k * q + e * f * q - d * f * n + d * k * l - e * h * l) + a[11] * (+b * k * p - b * g * n - e * f * p + c * f * n + e * g * l - c * k * l) + a[15] * (-d * g * l - b * h * p + b * g * q + d * f * p - c * f * q + c * h * l)
    },
    transpose: function () {
        var b, a = this.elements;
        return b = a[1], a[1] = a[4], a[4] = b, b = a[2], a[2] = a[8], a[8] = b, b = a[6], a[6] = a[9], a[9] = b, b = a[3], a[3] = a[12], a[12] = b, b = a[7], a[7] = a[13], a[13] = b, b = a[11], a[11] = a[14], a[14] = b, this
    },
    flattenToArrayOffset: function (a, b) {
        var c = this.elements;
        return a[b] = c[0], a[b + 1] = c[1], a[b + 2] = c[2], a[b + 3] = c[3], a[b + 4] = c[4], a[b + 5] = c[5], a[b + 6] = c[6], a[b + 7] = c[7], a[b + 8] = c[8], a[b + 9] = c[9], a[b + 10] = c[10], a[b + 11] = c[11], a[b + 12] = c[12], a[b + 13] = c[13], a[b + 14] = c[14], a[b + 15] = c[15], a
    },
    getPosition: function () {
        var a = new THREE.Vector3;
        return function () {
            THREE.warn("THREE.Matrix4: .getPosition() has been removed. Use Vector3.setFromMatrixPosition( matrix ) instead.");
            var b = this.elements;
            return a.set(b[12], b[13], b[14])
        }
    }(),
    setPosition: function (a) {
        var b = this.elements;
        return b[12] = a.x, b[13] = a.y, b[14] = a.z, this
    },
    getInverse: function (a, b) {
        var c = this.elements, d = a.elements, e = d[0], f = d[4], g = d[8], h = d[12], k = d[1], l = d[5], p = d[9], q = d[13], n = d[2], t = d[6], r = d[10], s = d[14], u = d[3], v = d[7], x = d[11], d = d[15];
        if (c[0] = p * s * v - q * r * v + q * t * x - l * s * x - p * t * d + l * r * d, c[4] = h * r * v - g * s * v - h * t * x + f * s * x + g * t * d - f * r * d, c[8] = g * q * v - h * p * v + h * l * x - f * q * x - g * l * d + f * p * d, c[12] = h * p * t - g * q * t - h * l * r + f * q * r + g * l * s - f * p * s, c[1] = q * r * u - p * s * u - q * n * x + k * s * x + p * n * d - k * r * d, c[5] = g * s * u - h * r * u + h * n * x - e * s * x - g * n * d + e * r * d, c[9] = h * p * u - g * q * u - h * k * x + e * q * x + g * k * d - e * p * d, c[13] = g * q * n - h * p * n + h * k * r - e * q * r - g * k * s + e * p * s, c[2] = l * s * u - q * t * u + q * n * v - k * s * v - l * n * d + k * t * d, c[6] = h * t * u - f * s * u - h * n * v + e * s * v + f * n * d - e * t * d, c[10] = f * q * u - h * l * u + h * k * v - e * q * v - f * k * d + e * l * d, c[14] = h * l * n - f * q * n - h * k * t + e * q * t + f * k * s - e * l * s, c[3] = p * t * u - l * r * u - p * n * v + k * r * v + l * n * x - k * t * x, c[7] = f * r * u - g * t * u + g * n * v - e * r * v - f * n * x + e * t * x, c[11] = g * l * u - f * p * u - g * k * v + e * p * v + f * k * x - e * l * x, c[15] = f * p * n - g * l * n + g * k * t - e * p * t - f * k * r + e * l * r, c = e * c[0] + k * c[4] + n * c[8] + u * c[12], 0 == c) {
            if (b)throw Error("THREE.Matrix4.getInverse(): can't invert matrix, determinant is 0");
            return THREE.warn("THREE.Matrix4.getInverse(): can't invert matrix, determinant is 0"), this.identity(), this
        }
        return this.multiplyScalar(1 / c), this
    },
    translate: function (a) {
        THREE.error("THREE.Matrix4: .translate() has been removed.")
    },
    rotateX: function (a) {
        THREE.error("THREE.Matrix4: .rotateX() has been removed.")
    },
    rotateY: function (a) {
        THREE.error("THREE.Matrix4: .rotateY() has been removed.")
    },
    rotateZ: function (a) {
        THREE.error("THREE.Matrix4: .rotateZ() has been removed.")
    },
    rotateByAxis: function (a, b) {
        THREE.error("THREE.Matrix4: .rotateByAxis() has been removed.")
    },
    scale: function (a) {
        var b = this.elements, c = a.x, d = a.y;
        return a = a.z, b[0] *= c, b[4] *= d, b[8] *= a, b[1] *= c, b[5] *= d, b[9] *= a, b[2] *= c, b[6] *= d, b[10] *= a, b[3] *= c, b[7] *= d, b[11] *= a, this
    },
    getMaxScaleOnAxis: function () {
        var a = this.elements;
        return Math.sqrt(Math.max(a[0] * a[0] + a[1] * a[1] + a[2] * a[2], Math.max(a[4] * a[4] + a[5] * a[5] + a[6] * a[6], a[8] * a[8] + a[9] * a[9] + a[10] * a[10])))
    },
    makeTranslation: function (a, b, c) {
        return this.set(1, 0, 0, a, 0, 1, 0, b, 0, 0, 1, c, 0, 0, 0, 1), this
    },
    makeRotationX: function (a) {
        var b = Math.cos(a);
        return a = Math.sin(a), this.set(1, 0, 0, 0, 0, b, -a, 0, 0, a, b, 0, 0, 0, 0, 1), this
    },
    makeRotationY: function (a) {
        var b = Math.cos(a);
        return a = Math.sin(a), this.set(b, 0, a, 0, 0, 1, 0, 0, -a, 0, b, 0, 0, 0, 0, 1), this
    },
    makeRotationZ: function (a) {
        var b = Math.cos(a);
        return a = Math.sin(a), this.set(b, -a, 0, 0, a, b, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1), this
    },
    makeRotationAxis: function (a, b) {
        var c = Math.cos(b), d = Math.sin(b), e = 1 - c, f = a.x, g = a.y, h = a.z, k = e * f, l = e * g;
        return this.set(k * f + c, k * g - d * h, k * h + d * g, 0, k * g + d * h, l * g + c, l * h - d * f, 0, k * h - d * g, l * h + d * f, e * h * h + c, 0, 0, 0, 0, 1), this
    },
    makeScale: function (a, b, c) {
        return this.set(a, 0, 0, 0, 0, b, 0, 0, 0, 0, c, 0, 0, 0, 0, 1), this
    },
    compose: function (a, b, c) {
        return this.makeRotationFromQuaternion(b), this.scale(c), this.setPosition(a), this
    },
    decompose: function () {
        var a = new THREE.Vector3, b = new THREE.Matrix4;
        return function (c, d, e) {
            var f = this.elements, g = a.set(f[0], f[1], f[2]).length(), h = a.set(f[4], f[5], f[6]).length(), k = a.set(f[8], f[9], f[10]).length();
            0 > this.determinant() && (g = -g), c.x = f[12], c.y = f[13], c.z = f[14], b.elements.set(this.elements), c = 1 / g;
            var f = 1 / h, l = 1 / k;
            return b.elements[0] *= c, b.elements[1] *= c, b.elements[2] *= c, b.elements[4] *= f, b.elements[5] *= f, b.elements[6] *= f, b.elements[8] *= l, b.elements[9] *= l, b.elements[10] *= l, d.setFromRotationMatrix(b), e.x = g, e.y = h, e.z = k, this
        }
    }(),
    makeFrustum: function (a, b, c, d, e, f) {
        var g = this.elements;
        return g[0] = 2 * e / (b - a), g[4] = 0, g[8] = (b + a) / (b - a), g[12] = 0, g[1] = 0, g[5] = 2 * e / (d - c), g[9] = (d + c) / (d - c), g[13] = 0, g[2] = 0, g[6] = 0, g[10] = -(f + e) / (f - e), g[14] = -2 * f * e / (f - e), g[3] = 0, g[7] = 0, g[11] = -1, g[15] = 0, this
    },
    makePerspective: function (a, b, c, d) {
        a = c * Math.tan(THREE.Math.degToRad(.5 * a));
        var e = -a;
        return this.makeFrustum(e * b, a * b, e, a, c, d)
    },
    makeOrthographic: function (a, b, c, d, e, f) {
        var g = this.elements, h = b - a, k = c - d, l = f - e;
        return g[0] = 2 / h, g[4] = 0, g[8] = 0, g[12] = -((b + a) / h), g[1] = 0, g[5] = 2 / k, g[9] = 0, g[13] = -((c + d) / k), g[2] = 0, g[6] = 0, g[10] = -2 / l, g[14] = -((f + e) / l), g[3] = 0, g[7] = 0, g[11] = 0, g[15] = 1, this
    },
    fromArray: function (a) {
        return this.elements.set(a), this
    },
    toArray: function () {
        var a = this.elements;
        return [a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], a[11], a[12], a[13], a[14], a[15]]
    },
    clone: function () {
        return (new THREE.Matrix4).fromArray(this.elements)
    }
},THREE.Ray = function (a, b) {
    this.origin = void 0 !== a ? a : new THREE.Vector3, this.direction = void 0 !== b ? b : new THREE.Vector3
},THREE.Ray.prototype = {
    constructor: THREE.Ray, set: function (a, b) {
        return this.origin.copy(a), this.direction.copy(b), this
    }, copy: function (a) {
        return this.origin.copy(a.origin), this.direction.copy(a.direction), this
    }, at: function (a, b) {
        return (b || new THREE.Vector3).copy(this.direction).multiplyScalar(a).add(this.origin)
    }, recast: function () {
        var a = new THREE.Vector3;
        return function (b) {
            return this.origin.copy(this.at(b, a)), this
        }
    }(), closestPointToPoint: function (a, b) {
        var c = b || new THREE.Vector3;
        c.subVectors(a, this.origin);
        var d = c.dot(this.direction);
        return 0 > d ? c.copy(this.origin) : c.copy(this.direction).multiplyScalar(d).add(this.origin)
    }, distanceToPoint: function () {
        var a = new THREE.Vector3;
        return function (b) {
            var c = a.subVectors(b, this.origin).dot(this.direction);
            return 0 > c ? this.origin.distanceTo(b) : (a.copy(this.direction).multiplyScalar(c).add(this.origin), a.distanceTo(b))
        }
    }(), distanceSqToSegment: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3, c = new THREE.Vector3;
        return function (d, e, f, g) {
            a.copy(d).add(e).multiplyScalar(.5), b.copy(e).sub(d).normalize(), c.copy(this.origin).sub(a);
            var t, h = .5 * d.distanceTo(e), k = -this.direction.dot(b), l = c.dot(this.direction), p = -c.dot(b), q = c.lengthSq(), n = Math.abs(1 - k * k);
            return n > 0 ? (d = k * p - l, e = k * l - p, t = h * n, d >= 0 ? e >= -t ? t >= e ? (h = 1 / n, d *= h, e *= h, k = d * (d + k * e + 2 * l) + e * (k * d + e + 2 * p) + q) : (e = h, d = Math.max(0, -(k * e + l)), k = -d * d + e * (e + 2 * p) + q) : (e = -h, d = Math.max(0, -(k * e + l)), k = -d * d + e * (e + 2 * p) + q) : -t >= e ? (d = Math.max(0, -(-k * h + l)), e = d > 0 ? -h : Math.min(Math.max(-h, -p), h), k = -d * d + e * (e + 2 * p) + q) : t >= e ? (d = 0, e = Math.min(Math.max(-h, -p), h), k = e * (e + 2 * p) + q) : (d = Math.max(0, -(k * h + l)), e = d > 0 ? h : Math.min(Math.max(-h, -p), h), k = -d * d + e * (e + 2 * p) + q)) : (e = k > 0 ? -h : h, d = Math.max(0, -(k * e + l)), k = -d * d + e * (e + 2 * p) + q), f && f.copy(this.direction).multiplyScalar(d).add(this.origin), g && g.copy(b).multiplyScalar(e).add(a), k
        }
    }(), isIntersectionSphere: function (a) {
        return this.distanceToPoint(a.center) <= a.radius
    }, intersectSphere: function () {
        var a = new THREE.Vector3;
        return function (b, c) {
            a.subVectors(b.center, this.origin);
            var d = a.dot(this.direction), e = a.dot(a) - d * d, f = b.radius * b.radius;
            return e > f ? null : (f = Math.sqrt(f - e), e = d - f, d += f, 0 > e && 0 > d ? null : 0 > e ? this.at(d, c) : this.at(e, c))
        }
    }(), isIntersectionPlane: function (a) {
        var b = a.distanceToPoint(this.origin);
        return 0 === b || 0 > a.normal.dot(this.direction) * b ? !0 : !1
    }, distanceToPlane: function (a) {
        var b = a.normal.dot(this.direction);
        return 0 == b ? 0 == a.distanceToPoint(this.origin) ? 0 : null : (a = -(this.origin.dot(a.normal) + a.constant) / b, a >= 0 ? a : null)
    }, intersectPlane: function (a, b) {
        var c = this.distanceToPlane(a);
        return null === c ? null : this.at(c, b)
    }, isIntersectionBox: function () {
        var a = new THREE.Vector3;
        return function (b) {
            return null !== this.intersectBox(b, a)
        }
    }(), intersectBox: function (a, b) {
        var c, d, e, f, g;
        d = 1 / this.direction.x, f = 1 / this.direction.y, g = 1 / this.direction.z;
        var h = this.origin;
        return d >= 0 ? (c = (a.min.x - h.x) * d, d *= a.max.x - h.x) : (c = (a.max.x - h.x) * d, d *= a.min.x - h.x), f >= 0 ? (e = (a.min.y - h.y) * f, f *= a.max.y - h.y) : (e = (a.max.y - h.y) * f, f *= a.min.y - h.y), c > f || e > d ? null : ((e > c || c !== c) && (c = e), (d > f || d !== d) && (d = f), g >= 0 ? (e = (a.min.z - h.z) * g, g *= a.max.z - h.z) : (e = (a.max.z - h.z) * g, g *= a.min.z - h.z), c > g || e > d ? null : ((e > c || c !== c) && (c = e), (d > g || d !== d) && (d = g), 0 > d ? null : this.at(c >= 0 ? c : d, b)))
    }, intersectTriangle: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3, c = new THREE.Vector3, d = new THREE.Vector3;
        return function (e, f, g, h, k) {
            if (b.subVectors(f, e), c.subVectors(g, e), d.crossVectors(b, c), f = this.direction.dot(d), f > 0) {
                if (h)return null;
                h = 1
            } else {
                if (!(0 > f))return null;
                h = -1, f = -f
            }
            return a.subVectors(this.origin, e), e = h * this.direction.dot(c.crossVectors(a, c)), 0 > e ? null : (g = h * this.direction.dot(b.cross(a)), 0 > g || e + g > f ? null : (e = -h * a.dot(d), 0 > e ? null : this.at(e / f, k)))
        }
    }(), applyMatrix4: function (a) {
        return this.direction.add(this.origin).applyMatrix4(a), this.origin.applyMatrix4(a), this.direction.sub(this.origin), this.direction.normalize(), this
    }, equals: function (a) {
        return a.origin.equals(this.origin) && a.direction.equals(this.direction)
    }, clone: function () {
        return (new THREE.Ray).copy(this)
    }
},THREE.Sphere = function (a, b) {
    this.center = void 0 !== a ? a : new THREE.Vector3, this.radius = void 0 !== b ? b : 0
},THREE.Sphere.prototype = {
    constructor: THREE.Sphere, set: function (a, b) {
        return this.center.copy(a), this.radius = b, this
    }, setFromPoints: function () {
        var a = new THREE.Box3;
        return function (b, c) {
            var d = this.center;
            void 0 !== c ? d.copy(c) : a.setFromPoints(b).center(d);
            for (var e = 0, f = 0, g = b.length; g > f; f++)e = Math.max(e, d.distanceToSquared(b[f]));
            return this.radius = Math.sqrt(e), this
        }
    }(), copy: function (a) {
        return this.center.copy(a.center), this.radius = a.radius, this
    }, empty: function () {
        return 0 >= this.radius
    }, containsPoint: function (a) {
        return a.distanceToSquared(this.center) <= this.radius * this.radius
    }, distanceToPoint: function (a) {
        return a.distanceTo(this.center) - this.radius
    }, intersectsSphere: function (a) {
        var b = this.radius + a.radius;
        return a.center.distanceToSquared(this.center) <= b * b
    }, clampPoint: function (a, b) {
        var c = this.center.distanceToSquared(a), d = b || new THREE.Vector3;
        return d.copy(a), c > this.radius * this.radius && (d.sub(this.center).normalize(), d.multiplyScalar(this.radius).add(this.center)), d
    }, getBoundingBox: function (a) {
        return a = a || new THREE.Box3, a.set(this.center, this.center), a.expandByScalar(this.radius), a
    }, applyMatrix4: function (a) {
        return this.center.applyMatrix4(a), this.radius *= a.getMaxScaleOnAxis(), this
    }, translate: function (a) {
        return this.center.add(a), this
    }, equals: function (a) {
        return a.center.equals(this.center) && a.radius === this.radius
    }, clone: function () {
        return (new THREE.Sphere).copy(this)
    }
},THREE.Frustum = function (a, b, c, d, e, f) {
    this.planes = [void 0 !== a ? a : new THREE.Plane, void 0 !== b ? b : new THREE.Plane, void 0 !== c ? c : new THREE.Plane, void 0 !== d ? d : new THREE.Plane, void 0 !== e ? e : new THREE.Plane, void 0 !== f ? f : new THREE.Plane]
},THREE.Frustum.prototype = {
    constructor: THREE.Frustum, set: function (a, b, c, d, e, f) {
        var g = this.planes;
        return g[0].copy(a), g[1].copy(b), g[2].copy(c), g[3].copy(d), g[4].copy(e), g[5].copy(f), this
    }, copy: function (a) {
        for (var b = this.planes, c = 0; 6 > c; c++)b[c].copy(a.planes[c]);
        return this
    }, setFromMatrix: function (a) {
        var b = this.planes, c = a.elements;
        a = c[0];
        var d = c[1], e = c[2], f = c[3], g = c[4], h = c[5], k = c[6], l = c[7], p = c[8], q = c[9], n = c[10], t = c[11], r = c[12], s = c[13], u = c[14], c = c[15];
        return b[0].setComponents(f - a, l - g, t - p, c - r).normalize(), b[1].setComponents(f + a, l + g, t + p, c + r).normalize(), b[2].setComponents(f + d, l + h, t + q, c + s).normalize(), b[3].setComponents(f - d, l - h, t - q, c - s).normalize(), b[4].setComponents(f - e, l - k, t - n, c - u).normalize(), b[5].setComponents(f + e, l + k, t + n, c + u).normalize(), this
    }, intersectsObject: function () {
        var a = new THREE.Sphere;
        return function (b) {
            var c = b.geometry;
            return null === c.boundingSphere && c.computeBoundingSphere(), a.copy(c.boundingSphere), a.applyMatrix4(b.matrixWorld), this.intersectsSphere(a)
        }
    }(), intersectsSphere: function (a) {
        var b = this.planes, c = a.center;
        a = -a.radius;
        for (var d = 0; 6 > d; d++)if (b[d].distanceToPoint(c) < a)return !1;
        return !0
    }, intersectsBox: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3;
        return function (c) {
            for (var d = this.planes, e = 0; 6 > e; e++) {
                var f = d[e];
                a.x = 0 < f.normal.x ? c.min.x : c.max.x, b.x = 0 < f.normal.x ? c.max.x : c.min.x, a.y = 0 < f.normal.y ? c.min.y : c.max.y, b.y = 0 < f.normal.y ? c.max.y : c.min.y, a.z = 0 < f.normal.z ? c.min.z : c.max.z, b.z = 0 < f.normal.z ? c.max.z : c.min.z;
                var g = f.distanceToPoint(a), f = f.distanceToPoint(b);
                if (0 > g && 0 > f)return !1
            }
            return !0
        }
    }(), containsPoint: function (a) {
        for (var b = this.planes, c = 0; 6 > c; c++)if (0 > b[c].distanceToPoint(a))return !1;
        return !0
    }, clone: function () {
        return (new THREE.Frustum).copy(this)
    }
},THREE.Plane = function (a, b) {
    this.normal = void 0 !== a ? a : new THREE.Vector3(1, 0, 0), this.constant = void 0 !== b ? b : 0
},THREE.Plane.prototype = {
    constructor: THREE.Plane, set: function (a, b) {
        return this.normal.copy(a), this.constant = b, this
    }, setComponents: function (a, b, c, d) {
        return this.normal.set(a, b, c), this.constant = d, this
    }, setFromNormalAndCoplanarPoint: function (a, b) {
        return this.normal.copy(a), this.constant = -b.dot(this.normal), this
    }, setFromCoplanarPoints: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3;
        return function (c, d, e) {
            return d = a.subVectors(e, d).cross(b.subVectors(c, d)).normalize(), this.setFromNormalAndCoplanarPoint(d, c), this
        }
    }(), copy: function (a) {
        return this.normal.copy(a.normal), this.constant = a.constant, this
    }, normalize: function () {
        var a = 1 / this.normal.length();
        return this.normal.multiplyScalar(a), this.constant *= a, this
    }, negate: function () {
        return this.constant *= -1, this.normal.negate(), this
    }, distanceToPoint: function (a) {
        return this.normal.dot(a) + this.constant
    }, distanceToSphere: function (a) {
        return this.distanceToPoint(a.center) - a.radius
    }, projectPoint: function (a, b) {
        return this.orthoPoint(a, b).sub(a).negate()
    }, orthoPoint: function (a, b) {
        var c = this.distanceToPoint(a);
        return (b || new THREE.Vector3).copy(this.normal).multiplyScalar(c)
    }, isIntersectionLine: function (a) {
        var b = this.distanceToPoint(a.start);
        return a = this.distanceToPoint(a.end), 0 > b && a > 0 || 0 > a && b > 0
    }, intersectLine: function () {
        var a = new THREE.Vector3;
        return function (b, c) {
            var d = c || new THREE.Vector3, e = b.delta(a), f = this.normal.dot(e);
            return 0 != f ? (f = -(b.start.dot(this.normal) + this.constant) / f, 0 > f || f > 1 ? void 0 : d.copy(e).multiplyScalar(f).add(b.start)) : 0 == this.distanceToPoint(b.start) ? d.copy(b.start) : void 0
        }
    }(), coplanarPoint: function (a) {
        return (a || new THREE.Vector3).copy(this.normal).multiplyScalar(-this.constant)
    }, applyMatrix4: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3, c = new THREE.Matrix3;
        return function (d, e) {
            var f = e || c.getNormalMatrix(d), f = a.copy(this.normal).applyMatrix3(f), g = this.coplanarPoint(b);
            return g.applyMatrix4(d), this.setFromNormalAndCoplanarPoint(f, g), this
        }
    }(), translate: function (a) {
        return this.constant -= a.dot(this.normal), this
    }, equals: function (a) {
        return a.normal.equals(this.normal) && a.constant == this.constant
    }, clone: function () {
        return (new THREE.Plane).copy(this)
    }
},THREE.Math = {
    generateUUID: function () {
        var d, a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split(""), b = Array(36), c = 0;
        return function () {
            for (var e = 0; 36 > e; e++)8 == e || 13 == e || 18 == e || 23 == e ? b[e] = "-" : 14 == e ? b[e] = "4" : (2 >= c && (c = 33554432 + 16777216 * Math.random() | 0), d = 15 & c, c >>= 4, b[e] = a[19 == e ? 3 & d | 8 : d]);
            return b.join("")
        }
    }(), clamp: function (a, b, c) {
        return b > a ? b : a > c ? c : a
    }, clampBottom: function (a, b) {
        return b > a ? b : a
    }, mapLinear: function (a, b, c, d, e) {
        return d + (a - b) * (e - d) / (c - b)
    }, smoothstep: function (a, b, c) {
        return b >= a ? 0 : a >= c ? 1 : (a = (a - b) / (c - b), a * a * (3 - 2 * a))
    }, smootherstep: function (a, b, c) {
        return b >= a ? 0 : a >= c ? 1 : (a = (a - b) / (c - b), a * a * a * (a * (6 * a - 15) + 10))
    }, random16: function () {
        return (65280 * Math.random() + 255 * Math.random()) / 65535
    }, randInt: function (a, b) {
        return Math.floor(this.randFloat(a, b))
    }, randFloat: function (a, b) {
        return a + Math.random() * (b - a)
    }, randFloatSpread: function (a) {
        return a * (.5 - Math.random())
    }, degToRad: function () {
        var a = Math.PI / 180;
        return function (b) {
            return b * a
        }
    }(), radToDeg: function () {
        var a = 180 / Math.PI;
        return function (b) {
            return b * a
        }
    }(), isPowerOfTwo: function (a) {
        return 0 === (a & a - 1) && 0 !== a
    }, nextPowerOfTwo: function (a) {
        return a--, a |= a >> 1, a |= a >> 2, a |= a >> 4, a |= a >> 8, a |= a >> 16, a++, a
    }
},THREE.Spline = function (a) {
    function b(a, b, c, d, e, f, g) {
        return a = .5 * (c - a), d = .5 * (d - b), (2 * (b - c) + a + d) * g + (-3 * (b - c) - 2 * a - d) * f + a * e + b
    }

    this.points = a;
    var e, f, g, h, k, l, p, q, n, c = [], d = {x: 0, y: 0, z: 0};
    this.initFromArray = function (a) {
        this.points = [];
        for (var b = 0; b < a.length; b++)this.points[b] = {x: a[b][0], y: a[b][1], z: a[b][2]}
    }, this.getPoint = function (a) {
        return e = (this.points.length - 1) * a, f = Math.floor(e), g = e - f, c[0] = 0 === f ? f : f - 1, c[1] = f, c[2] = f > this.points.length - 2 ? this.points.length - 1 : f + 1, c[3] = f > this.points.length - 3 ? this.points.length - 1 : f + 2, l = this.points[c[0]], p = this.points[c[1]], q = this.points[c[2]], n = this.points[c[3]], h = g * g, k = g * h, d.x = b(l.x, p.x, q.x, n.x, g, h, k), d.y = b(l.y, p.y, q.y, n.y, g, h, k), d.z = b(l.z, p.z, q.z, n.z, g, h, k), d
    }, this.getControlPointsArray = function () {
        var a, b, c = this.points.length, d = [];
        for (a = 0; c > a; a++)b = this.points[a], d[a] = [b.x, b.y, b.z];
        return d
    }, this.getLength = function (a) {
        var b, c, d, e = b = b = 0, f = new THREE.Vector3, g = new THREE.Vector3, h = [], k = 0;
        for (h[0] = 0, a || (a = 100), c = this.points.length * a, f.copy(this.points[0]), a = 1; c > a; a++)b = a / c, d = this.getPoint(b), g.copy(d), k += g.distanceTo(f), f.copy(d), b *= this.points.length - 1, b = Math.floor(b), b != e && (h[b] = k, e = b);
        return h[h.length] = k, {chunks: h, total: k}
    }, this.reparametrizeByArcLength = function (a) {
        var b, c, d, e, f, g, h = [], k = new THREE.Vector3, n = this.getLength();
        for (h.push(k.copy(this.points[0]).clone()), b = 1; b < this.points.length; b++) {
            for (c = n.chunks[b] - n.chunks[b - 1], g = Math.ceil(a * c / n.total), e = (b - 1) / (this.points.length - 1), f = b / (this.points.length - 1), c = 1; g - 1 > c; c++)d = e + 1 / g * c * (f - e), d = this.getPoint(d), h.push(k.copy(d).clone());
            h.push(k.copy(this.points[b]).clone())
        }
        this.points = h
    }
},THREE.Triangle = function (a, b, c) {
    this.a = void 0 !== a ? a : new THREE.Vector3, this.b = void 0 !== b ? b : new THREE.Vector3, this.c = void 0 !== c ? c : new THREE.Vector3
},THREE.Triangle.normal = function () {
    var a = new THREE.Vector3;
    return function (b, c, d, e) {
        return e = e || new THREE.Vector3, e.subVectors(d, c), a.subVectors(b, c), e.cross(a), b = e.lengthSq(), b > 0 ? e.multiplyScalar(1 / Math.sqrt(b)) : e.set(0, 0, 0)
    }
}(),THREE.Triangle.barycoordFromPoint = function () {
    var a = new THREE.Vector3, b = new THREE.Vector3, c = new THREE.Vector3;
    return function (d, e, f, g, h) {
        a.subVectors(g, e), b.subVectors(f, e), c.subVectors(d, e), d = a.dot(a), e = a.dot(b), f = a.dot(c);
        var k = b.dot(b);
        g = b.dot(c);
        var l = d * k - e * e;
        return h = h || new THREE.Vector3, 0 == l ? h.set(-2, -1, -1) : (l = 1 / l, k = (k * f - e * g) * l, d = (d * g - e * f) * l, h.set(1 - k - d, d, k))
    }
}(),THREE.Triangle.containsPoint = function () {
    var a = new THREE.Vector3;
    return function (b, c, d, e) {
        return b = THREE.Triangle.barycoordFromPoint(b, c, d, e, a), 0 <= b.x && 0 <= b.y && 1 >= b.x + b.y
    }
}(),THREE.Triangle.prototype = {
    constructor: THREE.Triangle, set: function (a, b, c) {
        return this.a.copy(a), this.b.copy(b), this.c.copy(c), this
    }, setFromPointsAndIndices: function (a, b, c, d) {
        return this.a.copy(a[b]), this.b.copy(a[c]), this.c.copy(a[d]), this
    }, copy: function (a) {
        return this.a.copy(a.a), this.b.copy(a.b), this.c.copy(a.c), this
    }, area: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3;
        return function () {
            return a.subVectors(this.c, this.b), b.subVectors(this.a, this.b), .5 * a.cross(b).length()
        }
    }(), midpoint: function (a) {
        return (a || new THREE.Vector3).addVectors(this.a, this.b).add(this.c).multiplyScalar(1 / 3)
    }, normal: function (a) {
        return THREE.Triangle.normal(this.a, this.b, this.c, a)
    }, plane: function (a) {
        return (a || new THREE.Plane).setFromCoplanarPoints(this.a, this.b, this.c)
    }, barycoordFromPoint: function (a, b) {
        return THREE.Triangle.barycoordFromPoint(a, this.a, this.b, this.c, b)
    }, containsPoint: function (a) {
        return THREE.Triangle.containsPoint(a, this.a, this.b, this.c)
    }, equals: function (a) {
        return a.a.equals(this.a) && a.b.equals(this.b) && a.c.equals(this.c)
    }, clone: function () {
        return (new THREE.Triangle).copy(this)
    }
},THREE.Clock = function (a) {
    this.autoStart = void 0 !== a ? a : !0, this.elapsedTime = this.oldTime = this.startTime = 0, this.running = !1
},THREE.Clock.prototype = {
    constructor: THREE.Clock, start: function () {
        this.oldTime = this.startTime = void 0 !== self.performance && void 0 !== self.performance.now ? self.performance.now() : Date.now(), this.running = !0
    }, stop: function () {
        this.getElapsedTime(), this.running = !1
    }, getElapsedTime: function () {
        return this.getDelta(), this.elapsedTime
    }, getDelta: function () {
        var a = 0;
        if (this.autoStart && !this.running && this.start(), this.running) {
            var b = void 0 !== self.performance && void 0 !== self.performance.now ? self.performance.now() : Date.now(), a = .001 * (b - this.oldTime);
            this.oldTime = b, this.elapsedTime += a
        }
        return a
    }
},THREE.EventDispatcher = function () {
},THREE.EventDispatcher.prototype = {
    constructor: THREE.EventDispatcher, apply: function (a) {
        a.addEventListener = THREE.EventDispatcher.prototype.addEventListener, a.hasEventListener = THREE.EventDispatcher.prototype.hasEventListener, a.removeEventListener = THREE.EventDispatcher.prototype.removeEventListener, a.dispatchEvent = THREE.EventDispatcher.prototype.dispatchEvent
    }, addEventListener: function (a, b) {
        void 0 === this._listeners && (this._listeners = {});
        var c = this._listeners;
        void 0 === c[a] && (c[a] = []), -1 === c[a].indexOf(b) && c[a].push(b)
    }, hasEventListener: function (a, b) {
        if (void 0 === this._listeners)return !1;
        var c = this._listeners;
        return void 0 !== c[a] && -1 !== c[a].indexOf(b) ? !0 : !1
    }, removeEventListener: function (a, b) {
        if (void 0 !== this._listeners) {
            var c = this._listeners[a];
            if (void 0 !== c) {
                var d = c.indexOf(b);
                -1 !== d && c.splice(d, 1)
            }
        }
    }, dispatchEvent: function (a) {
        if (void 0 !== this._listeners) {
            var b = this._listeners[a.type];
            if (void 0 !== b) {
                a.target = this;
                for (var c = [], d = b.length, e = 0; d > e; e++)c[e] = b[e];
                for (e = 0; d > e; e++)c[e].call(this, a)
            }
        }
    }
},function (a) {
    a.Raycaster = function (b, c, f, g) {
        this.ray = new a.Ray(b, c), this.near = f || 0, this.far = g || 1 / 0, this.params = {
            Sprite: {},
            Mesh: {},
            PointCloud: {threshold: 1},
            LOD: {},
            Line: {}
        }
    };
    var b = function (a, b) {
        return a.distance - b.distance
    }, c = function (a, b, f, g) {
        if (a.raycast(b, f), !0 === g) {
            a = a.children, g = 0;
            for (var h = a.length; h > g; g++)c(a[g], b, f, !0)
        }
    };
    a.Raycaster.prototype = {
        constructor: a.Raycaster, precision: 1e-4, linePrecision: 1, set: function (a, b) {
            this.ray.set(a, b)
        }, setFromCamera: function (b, c) {
            c instanceof a.PerspectiveCamera ? (this.ray.origin.copy(c.position), this.ray.direction.set(b.x, b.y, .5).unproject(c).sub(c.position).normalize()) : c instanceof a.OrthographicCamera ? (this.ray.origin.set(b.x, b.y, -1).unproject(c), this.ray.direction.set(0, 0, -1).transformDirection(c.matrixWorld)) : a.error("THREE.Raycaster: Unsupported camera type.")
        }, intersectObject: function (a, e) {
            var f = [];
            return c(a, this, f, e), f.sort(b), f
        }, intersectObjects: function (d, e) {
            var f = [];
            if (!1 == d instanceof Array)return a.warn("THREE.Raycaster.intersectObjects: objects is not an Array."), f;
            for (var g = 0, h = d.length; h > g; g++)c(d[g], this, f, e);
            return f.sort(b), f
        }
    }
}(THREE),THREE.Object3D = function () {
    Object.defineProperty(this, "id", {value: THREE.Object3DIdCount++}), this.uuid = THREE.Math.generateUUID(), this.name = "", this.type = "Object3D", this.parent = void 0, this.children = [], this.up = THREE.Object3D.DefaultUp.clone();
    var a = new THREE.Vector3, b = new THREE.Euler, c = new THREE.Quaternion, d = new THREE.Vector3(1, 1, 1);
    b.onChange(function () {
        c.setFromEuler(b, !1)
    }), c.onChange(function () {
        b.setFromQuaternion(c, void 0, !1)
    }), Object.defineProperties(this, {
        position: {enumerable: !0, value: a},
        rotation: {enumerable: !0, value: b},
        quaternion: {enumerable: !0, value: c},
        scale: {enumerable: !0, value: d}
    }), this.rotationAutoUpdate = !0, this.matrix = new THREE.Matrix4, this.matrixWorld = new THREE.Matrix4, this.matrixAutoUpdate = !0, this.matrixWorldNeedsUpdate = !1, this.visible = !0, this.receiveShadow = this.castShadow = !1, this.frustumCulled = !0, this.renderOrder = 0, this.userData = {}
},THREE.Object3D.DefaultUp = new THREE.Vector3(0, 1, 0),THREE.Object3D.prototype = {
    constructor: THREE.Object3D,
    get eulerOrder() {
        return THREE.warn("THREE.Object3D: .eulerOrder has been moved to .rotation.order."), this.rotation.order
    },
    set eulerOrder(a) {
        THREE.warn("THREE.Object3D: .eulerOrder has been moved to .rotation.order."), this.rotation.order = a
    },
    get useQuaternion() {
        THREE.warn("THREE.Object3D: .useQuaternion has been removed. The library now uses quaternions by default.")
    },
    set useQuaternion(a) {
        THREE.warn("THREE.Object3D: .useQuaternion has been removed. The library now uses quaternions by default.")
    },
    applyMatrix: function (a) {
        this.matrix.multiplyMatrices(a, this.matrix), this.matrix.decompose(this.position, this.quaternion, this.scale)
    },
    setRotationFromAxisAngle: function (a, b) {
        this.quaternion.setFromAxisAngle(a, b)
    },
    setRotationFromEuler: function (a) {
        this.quaternion.setFromEuler(a, !0)
    },
    setRotationFromMatrix: function (a) {
        this.quaternion.setFromRotationMatrix(a)
    },
    setRotationFromQuaternion: function (a) {
        this.quaternion.copy(a)
    },
    rotateOnAxis: function () {
        var a = new THREE.Quaternion;
        return function (b, c) {
            return a.setFromAxisAngle(b, c), this.quaternion.multiply(a), this
        }
    }(),
    rotateX: function () {
        var a = new THREE.Vector3(1, 0, 0);
        return function (b) {
            return this.rotateOnAxis(a, b)
        }
    }(),
    rotateY: function () {
        var a = new THREE.Vector3(0, 1, 0);
        return function (b) {
            return this.rotateOnAxis(a, b)
        }
    }(),
    rotateZ: function () {
        var a = new THREE.Vector3(0, 0, 1);
        return function (b) {
            return this.rotateOnAxis(a, b)
        }
    }(),
    translateOnAxis: function () {
        var a = new THREE.Vector3;
        return function (b, c) {
            return a.copy(b).applyQuaternion(this.quaternion), this.position.add(a.multiplyScalar(c)), this
        }
    }(),
    translate: function (a, b) {
        return THREE.warn("THREE.Object3D: .translate() has been removed. Use .translateOnAxis( axis, distance ) instead."), this.translateOnAxis(b, a)
    },
    translateX: function () {
        var a = new THREE.Vector3(1, 0, 0);
        return function (b) {
            return this.translateOnAxis(a, b)
        }
    }(),
    translateY: function () {
        var a = new THREE.Vector3(0, 1, 0);
        return function (b) {
            return this.translateOnAxis(a, b)
        }
    }(),
    translateZ: function () {
        var a = new THREE.Vector3(0, 0, 1);
        return function (b) {
            return this.translateOnAxis(a, b)
        }
    }(),
    localToWorld: function (a) {
        return a.applyMatrix4(this.matrixWorld)
    },
    worldToLocal: function () {
        var a = new THREE.Matrix4;
        return function (b) {
            return b.applyMatrix4(a.getInverse(this.matrixWorld))
        }
    }(),
    lookAt: function () {
        var a = new THREE.Matrix4;
        return function (b) {
            a.lookAt(b, this.position, this.up), this.quaternion.setFromRotationMatrix(a)
        }
    }(),
    add: function (a) {
        if (1 < arguments.length) {
            for (var b = 0; b < arguments.length; b++)this.add(arguments[b]);
            return this
        }
        return a === this ? (THREE.error("THREE.Object3D.add: object can't be added as a child of itself.", a), this) : (a instanceof THREE.Object3D ? (void 0 !== a.parent && a.parent.remove(a), a.parent = this, a.dispatchEvent({type: "added"}), this.children.push(a)) : THREE.error("THREE.Object3D.add: object not an instance of THREE.Object3D.", a), this)
    },
    remove: function (a) {
        if (1 < arguments.length)for (var b = 0; b < arguments.length; b++)this.remove(arguments[b]);
        b = this.children.indexOf(a), -1 !== b && (a.parent = void 0, a.dispatchEvent({type: "removed"}), this.children.splice(b, 1))
    },
    getChildByName: function (a) {
        return THREE.warn("THREE.Object3D: .getChildByName() has been renamed to .getObjectByName()."), this.getObjectByName(a)
    },
    getObjectById: function (a) {
        return this.getObjectByProperty("id", a)
    },
    getObjectByName: function (a) {
        return this.getObjectByProperty("name", a)
    },
    getObjectByProperty: function (a, b) {
        if (this[a] === b)return this;
        for (var c = 0, d = this.children.length; d > c; c++) {
            var e = this.children[c].getObjectByProperty(a, b);
            if (void 0 !== e)return e
        }
    },
    getWorldPosition: function (a) {
        return a = a || new THREE.Vector3, this.updateMatrixWorld(!0), a.setFromMatrixPosition(this.matrixWorld)
    },
    getWorldQuaternion: function () {
        var a = new THREE.Vector3, b = new THREE.Vector3;
        return function (c) {
            return c = c || new THREE.Quaternion, this.updateMatrixWorld(!0), this.matrixWorld.decompose(a, c, b), c
        }
    }(),
    getWorldRotation: function () {
        var a = new THREE.Quaternion;
        return function (b) {
            return b = b || new THREE.Euler, this.getWorldQuaternion(a), b.setFromQuaternion(a, this.rotation.order, !1)
        }
    }(),
    getWorldScale: function () {
        var a = new THREE.Vector3, b = new THREE.Quaternion;
        return function (c) {
            return c = c || new THREE.Vector3, this.updateMatrixWorld(!0), this.matrixWorld.decompose(a, b, c), c
        }
    }(),
    getWorldDirection: function () {
        var a = new THREE.Quaternion;
        return function (b) {
            return b = b || new THREE.Vector3, this.getWorldQuaternion(a), b.set(0, 0, 1).applyQuaternion(a)
        }
    }(),
    raycast: function () {
    },
    traverse: function (a) {
        a(this);
        for (var b = 0, c = this.children.length; c > b; b++)this.children[b].traverse(a)
    },
    traverseVisible: function (a) {
        if (!1 !== this.visible) {
            a(this);
            for (var b = 0, c = this.children.length; c > b; b++)this.children[b].traverseVisible(a)
        }
    },
    traverseAncestors: function (a) {
        this.parent && (a(this.parent), this.parent.traverseAncestors(a))
    },
    updateMatrix: function () {
        this.matrix.compose(this.position, this.quaternion, this.scale), this.matrixWorldNeedsUpdate = !0
    },
    updateMatrixWorld: function (a) {
        !0 === this.matrixAutoUpdate && this.updateMatrix(), (!0 === this.matrixWorldNeedsUpdate || !0 === a) && (void 0 === this.parent ? this.matrixWorld.copy(this.matrix) : this.matrixWorld.multiplyMatrices(this.parent.matrixWorld, this.matrix), this.matrixWorldNeedsUpdate = !1, a = !0);
        for (var b = 0, c = this.children.length; c > b; b++)this.children[b].updateMatrixWorld(a)
    },
    toJSON: function () {
        var a = {
            metadata: {
                version: 4.3,
                type: "Object",
                generator: "ObjectExporter"
            }
        }, b = {}, c = {}, d = function (b) {
            if (void 0 === a.materials && (a.materials = []), void 0 === c[b.uuid]) {
                var d = b.toJSON();
                delete d.metadata, c[b.uuid] = d, a.materials.push(d)
            }
            return b.uuid
        }, e = function (c) {
            var g = {};
            if (g.uuid = c.uuid, g.type = c.type, "" !== c.name && (g.name = c.name), "{}" !== JSON.stringify(c.userData) && (g.userData = c.userData), !0 !== c.visible && (g.visible = c.visible), c instanceof THREE.PerspectiveCamera)g.fov = c.fov, g.aspect = c.aspect, g.near = c.near, g.far = c.far; else if (c instanceof THREE.OrthographicCamera)g.left = c.left, g.right = c.right, g.top = c.top, g.bottom = c.bottom, g.near = c.near, g.far = c.far; else if (c instanceof THREE.AmbientLight)g.color = c.color.getHex(); else if (c instanceof THREE.DirectionalLight)g.color = c.color.getHex(), g.intensity = c.intensity; else if (c instanceof THREE.PointLight)g.color = c.color.getHex(), g.intensity = c.intensity, g.distance = c.distance, g.decay = c.decay; else if (c instanceof THREE.SpotLight)g.color = c.color.getHex(), g.intensity = c.intensity, g.distance = c.distance, g.angle = c.angle, g.exponent = c.exponent, g.decay = c.decay; else if (c instanceof THREE.HemisphereLight)g.color = c.color.getHex(), g.groundColor = c.groundColor.getHex(); else if (c instanceof THREE.Mesh || c instanceof THREE.Line || c instanceof THREE.PointCloud) {
                var h = c.geometry;
                if (void 0 === a.geometries && (a.geometries = []), void 0 === b[h.uuid]) {
                    var k = h.toJSON();
                    delete k.metadata, b[h.uuid] = k, a.geometries.push(k)
                }
                g.geometry = h.uuid, g.material = d(c.material), c instanceof THREE.Line && (g.mode = c.mode)
            } else c instanceof THREE.Sprite && (g.material = d(c.material));
            if (g.matrix = c.matrix.toArray(), 0 < c.children.length)for (g.children = [], h = 0; h < c.children.length; h++)g.children.push(e(c.children[h]));
            return g
        };
        return a.object = e(this), a
    },
    clone: function (a, b) {
        if (void 0 === a && (a = new THREE.Object3D), void 0 === b && (b = !0), a.name = this.name, a.up.copy(this.up), a.position.copy(this.position), a.quaternion.copy(this.quaternion), a.scale.copy(this.scale), a.rotationAutoUpdate = this.rotationAutoUpdate, a.matrix.copy(this.matrix), a.matrixWorld.copy(this.matrixWorld), a.matrixAutoUpdate = this.matrixAutoUpdate, a.matrixWorldNeedsUpdate = this.matrixWorldNeedsUpdate, a.visible = this.visible, a.castShadow = this.castShadow, a.receiveShadow = this.receiveShadow, a.frustumCulled = this.frustumCulled, a.userData = JSON.parse(JSON.stringify(this.userData)), !0 === b)for (var c = 0; c < this.children.length; c++)a.add(this.children[c].clone());
        return a
    }
},THREE.EventDispatcher.prototype.apply(THREE.Object3D.prototype),THREE.Object3DIdCount = 0,THREE.Face3 = function (a, b, c, d, e, f) {
    this.a = a, this.b = b, this.c = c, this.normal = d instanceof THREE.Vector3 ? d : new THREE.Vector3, this.vertexNormals = d instanceof Array ? d : [], this.color = e instanceof THREE.Color ? e : new THREE.Color, this.vertexColors = e instanceof Array ? e : [], this.vertexTangents = [], this.materialIndex = void 0 !== f ? f : 0
},THREE.Face3.prototype = {
    constructor: THREE.Face3, clone: function () {
        var a = new THREE.Face3(this.a, this.b, this.c);
        a.normal.copy(this.normal), a.color.copy(this.color), a.materialIndex = this.materialIndex;
        for (var b = 0, c = this.vertexNormals.length; c > b; b++)a.vertexNormals[b] = this.vertexNormals[b].clone();
        for (b = 0, c = this.vertexColors.length; c > b; b++)a.vertexColors[b] = this.vertexColors[b].clone();
        for (b = 0, c = this.vertexTangents.length; c > b; b++)a.vertexTangents[b] = this.vertexTangents[b].clone();
        return a
    }
},THREE.Face4 = function (a, b, c, d, e, f, g) {
    return THREE.warn("THREE.Face4 has been removed. A THREE.Face3 will be created instead."), new THREE.Face3(a, b, c, e, f, g)
},THREE.BufferAttribute = function (a, b) {
    this.array = a, this.itemSize = b, this.needsUpdate = !1
},THREE.BufferAttribute.prototype = {
    constructor: THREE.BufferAttribute, get length() {
        return this.array.length
    }, copyAt: function (a, b, c) {
        a *= this.itemSize, c *= b.itemSize;
        for (var d = 0, e = this.itemSize; e > d; d++)this.array[a + d] = b.array[c + d];
        return this
    }, set: function (a, b) {
        return void 0 === b && (b = 0), this.array.set(a, b), this
    }, setX: function (a, b) {
        return this.array[a * this.itemSize] = b, this
    }, setY: function (a, b) {
        return this.array[a * this.itemSize + 1] = b, this
    }, setZ: function (a, b) {
        return this.array[a * this.itemSize + 2] = b, this
    }, setXY: function (a, b, c) {
        return a *= this.itemSize, this.array[a] = b, this.array[a + 1] = c, this
    }, setXYZ: function (a, b, c, d) {
        return a *= this.itemSize, this.array[a] = b, this.array[a + 1] = c, this.array[a + 2] = d, this
    }, setXYZW: function (a, b, c, d, e) {
        return a *= this.itemSize, this.array[a] = b, this.array[a + 1] = c, this.array[a + 2] = d, this.array[a + 3] = e, this
    }, clone: function () {
        return new THREE.BufferAttribute(new this.array.constructor(this.array), this.itemSize)
    }
},THREE.Int8Attribute = function (a, b) {
    return THREE.warn("THREE.Int8Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Uint8Attribute = function (a, b) {
    return THREE.warn("THREE.Uint8Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Uint8ClampedAttribute = function (a, b) {
    return THREE.warn("THREE.Uint8ClampedAttribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Int16Attribute = function (a, b) {
    return THREE.warn("THREE.Int16Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Uint16Attribute = function (a, b) {
    return THREE.warn("THREE.Uint16Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Int32Attribute = function (a, b) {
    return THREE.warn("THREE.Int32Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Uint32Attribute = function (a, b) {
    return THREE.warn("THREE.Uint32Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Float32Attribute = function (a, b) {
    return THREE.warn("THREE.Float32Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.Float64Attribute = function (a, b) {
    return THREE.warn("THREE.Float64Attribute has been removed. Use THREE.BufferAttribute( array, itemSize ) instead."), new THREE.BufferAttribute(a, b)
},THREE.DynamicBufferAttribute = function (a, b) {
    THREE.BufferAttribute.call(this, a, b), this.updateRange = {offset: 0, count: -1}
},THREE.DynamicBufferAttribute.prototype = Object.create(THREE.BufferAttribute.prototype),THREE.DynamicBufferAttribute.prototype.constructor = THREE.DynamicBufferAttribute,THREE.DynamicBufferAttribute.prototype.clone = function () {
    return new THREE.DynamicBufferAttribute(new this.array.constructor(this.array), this.itemSize)
},THREE.BufferGeometry = function () {
    Object.defineProperty(this, "id", {value: THREE.GeometryIdCount++}), this.uuid = THREE.Math.generateUUID(), this.name = "", this.type = "BufferGeometry", this.attributes = {}, this.attributesKeys = [], this.offsets = this.drawcalls = [], this.boundingSphere = this.boundingBox = null
},THREE.BufferGeometry.prototype = {
    constructor: THREE.BufferGeometry, addAttribute: function (a, b, c) {
        !1 == b instanceof THREE.BufferAttribute ? (THREE.warn("THREE.BufferGeometry: .addAttribute() now expects ( name, attribute )."), this.attributes[a] = {
            array: b,
            itemSize: c
        }) : (this.attributes[a] = b, this.attributesKeys = Object.keys(this.attributes))
    }, getAttribute: function (a) {
        return this.attributes[a]
    }, addDrawCall: function (a, b, c) {
        this.drawcalls.push({start: a, count: b, index: void 0 !== c ? c : 0})
    }, applyMatrix: function (a) {
        var b = this.attributes.position;
        void 0 !== b && (a.applyToVector3Array(b.array), b.needsUpdate = !0), b = this.attributes.normal, void 0 !== b && ((new THREE.Matrix3).getNormalMatrix(a).applyToVector3Array(b.array), b.needsUpdate = !0), null !== this.boundingBox && this.computeBoundingBox(), null !== this.boundingSphere && this.computeBoundingSphere()
    }, center: function () {
        this.computeBoundingBox();
        var a = this.boundingBox.center().negate();
        return this.applyMatrix((new THREE.Matrix4).setPosition(a)), a
    }, fromGeometry: function (a, b) {
        b = b || {vertexColors: THREE.NoColors};
        var c = a.vertices, d = a.faces, e = a.faceVertexUvs, f = b.vertexColors, g = 0 < e[0].length, h = 3 == d[0].vertexNormals.length, k = new Float32Array(9 * d.length);
        this.addAttribute("position", new THREE.BufferAttribute(k, 3));
        var l = new Float32Array(9 * d.length);
        if (this.addAttribute("normal", new THREE.BufferAttribute(l, 3)), f !== THREE.NoColors) {
            var p = new Float32Array(9 * d.length);
            this.addAttribute("color", new THREE.BufferAttribute(p, 3))
        }
        if (!0 === g) {
            var q = new Float32Array(6 * d.length);
            this.addAttribute("uv", new THREE.BufferAttribute(q, 2))
        }
        for (var n = 0, t = 0, r = 0; n < d.length; n++, t += 6, r += 9) {
            var s = d[n], u = c[s.a], v = c[s.b], x = c[s.c];
            k[r] = u.x, k[r + 1] = u.y, k[r + 2] = u.z, k[r + 3] = v.x, k[r + 4] = v.y, k[r + 5] = v.z, k[r + 6] = x.x, k[r + 7] = x.y, k[r + 8] = x.z, !0 === h ? (u = s.vertexNormals[0], v = s.vertexNormals[1], x = s.vertexNormals[2], l[r] = u.x, l[r + 1] = u.y, l[r + 2] = u.z, l[r + 3] = v.x, l[r + 4] = v.y, l[r + 5] = v.z, l[r + 6] = x.x, l[r + 7] = x.y, l[r + 8] = x.z) : (u = s.normal, l[r] = u.x, l[r + 1] = u.y, l[r + 2] = u.z, l[r + 3] = u.x, l[r + 4] = u.y, l[r + 5] = u.z, l[r + 6] = u.x, l[r + 7] = u.y, l[r + 8] = u.z), f === THREE.FaceColors ? (s = s.color, p[r] = s.r, p[r + 1] = s.g, p[r + 2] = s.b, p[r + 3] = s.r, p[r + 4] = s.g, p[r + 5] = s.b, p[r + 6] = s.r, p[r + 7] = s.g, p[r + 8] = s.b) : f === THREE.VertexColors && (u = s.vertexColors[0], v = s.vertexColors[1], s = s.vertexColors[2], p[r] = u.r, p[r + 1] = u.g, p[r + 2] = u.b, p[r + 3] = v.r, p[r + 4] = v.g, p[r + 5] = v.b, p[r + 6] = s.r, p[r + 7] = s.g, p[r + 8] = s.b), !0 === g && (s = e[0][n][0], u = e[0][n][1], v = e[0][n][2], q[t] = s.x, q[t + 1] = s.y, q[t + 2] = u.x, q[t + 3] = u.y, q[t + 4] = v.x, q[t + 5] = v.y)
        }
        return this.computeBoundingSphere(), this
    }, computeBoundingBox: function () {
        var a = new THREE.Vector3;
        return function () {
            null === this.boundingBox && (this.boundingBox = new THREE.Box3);
            var b = this.attributes.position.array;
            if (b) {
                var c = this.boundingBox;
                c.makeEmpty();
                for (var d = 0, e = b.length; e > d; d += 3)a.set(b[d], b[d + 1], b[d + 2]), c.expandByPoint(a)
            }
            (void 0 === b || 0 === b.length) && (this.boundingBox.min.set(0, 0, 0), this.boundingBox.max.set(0, 0, 0)), (isNaN(this.boundingBox.min.x) || isNaN(this.boundingBox.min.y) || isNaN(this.boundingBox.min.z)) && THREE.error('THREE.BufferGeometry.computeBoundingBox: Computed min/max have NaN values. The "position" attribute is likely to have NaN values.')
        }
    }(), computeBoundingSphere: function () {
        var a = new THREE.Box3, b = new THREE.Vector3;
        return function () {
            null === this.boundingSphere && (this.boundingSphere = new THREE.Sphere);
            var c = this.attributes.position.array;
            if (c) {
                a.makeEmpty();
                for (var d = this.boundingSphere.center, e = 0, f = c.length; f > e; e += 3)b.set(c[e], c[e + 1], c[e + 2]), a.expandByPoint(b);
                a.center(d);
                for (var g = 0, e = 0, f = c.length; f > e; e += 3)b.set(c[e], c[e + 1], c[e + 2]), g = Math.max(g, d.distanceToSquared(b));
                this.boundingSphere.radius = Math.sqrt(g), isNaN(this.boundingSphere.radius) && THREE.error('THREE.BufferGeometry.computeBoundingSphere(): Computed radius is NaN. The "position" attribute is likely to have NaN values.')
            }
        }
    }(), computeFaceNormals: function () {
    }, computeVertexNormals: function () {
        var a = this.attributes;
        if (a.position) {
            var b = a.position.array;
            if (void 0 === a.normal)this.addAttribute("normal", new THREE.BufferAttribute(new Float32Array(b.length), 3)); else for (var c = a.normal.array, d = 0, e = c.length; e > d; d++)c[d] = 0;
            var f, g, h, c = a.normal.array, k = new THREE.Vector3, l = new THREE.Vector3, p = new THREE.Vector3, q = new THREE.Vector3, n = new THREE.Vector3;
            if (a.index)for (var t = a.index.array, r = 0 < this.offsets.length ? this.offsets : [{
                start: 0,
                count: t.length,
                index: 0
            }], s = 0, u = r.length; u > s; ++s) {
                e = r[s].start, f = r[s].count;
                for (var v = r[s].index, d = e, e = e + f; e > d; d += 3)f = 3 * (v + t[d]), g = 3 * (v + t[d + 1]), h = 3 * (v + t[d + 2]), k.fromArray(b, f), l.fromArray(b, g), p.fromArray(b, h), q.subVectors(p, l), n.subVectors(k, l), q.cross(n), c[f] += q.x, c[f + 1] += q.y, c[f + 2] += q.z, c[g] += q.x, c[g + 1] += q.y, c[g + 2] += q.z, c[h] += q.x, c[h + 1] += q.y, c[h + 2] += q.z
            } else for (d = 0, e = b.length; e > d; d += 9)k.fromArray(b, d), l.fromArray(b, d + 3), p.fromArray(b, d + 6), q.subVectors(p, l), n.subVectors(k, l), q.cross(n), c[d] = q.x, c[d + 1] = q.y, c[d + 2] = q.z, c[d + 3] = q.x, c[d + 4] = q.y, c[d + 5] = q.z, c[d + 6] = q.x, c[d + 7] = q.y, c[d + 8] = q.z;
            this.normalizeNormals(), a.normal.needsUpdate = !0
        }
    }, computeTangents: function () {
        function a(a, b, c) {
            q.fromArray(d, 3 * a), n.fromArray(d, 3 * b), t.fromArray(d, 3 * c), r.fromArray(f, 2 * a), s.fromArray(f, 2 * b), u.fromArray(f, 2 * c), v = n.x - q.x, x = t.x - q.x, D = n.y - q.y, w = t.y - q.y, y = n.z - q.z, A = t.z - q.z, E = s.x - r.x, G = u.x - r.x, F = s.y - r.y, z = u.y - r.y, I = 1 / (E * z - G * F), U.set((z * v - F * x) * I, (z * D - F * w) * I, (z * y - F * A) * I), M.set((E * x - G * v) * I, (E * w - G * D) * I, (E * A - G * y) * I), k[a].add(U), k[b].add(U), k[c].add(U), l[a].add(M), l[b].add(M), l[c].add(M)
        }

        function b(a) {
            ha.fromArray(e, 3 * a), O.copy(ha), ba = k[a], oa.copy(ba), oa.sub(ha.multiplyScalar(ha.dot(ba))).normalize(), ja.crossVectors(O, ba), qa = ja.dot(l[a]), ca = 0 > qa ? -1 : 1, h[4 * a] = oa.x, h[4 * a + 1] = oa.y, h[4 * a + 2] = oa.z, h[4 * a + 3] = ca
        }

        if (void 0 === this.attributes.index || void 0 === this.attributes.position || void 0 === this.attributes.normal || void 0 === this.attributes.uv)THREE.warn("THREE.BufferGeometry: Missing required attributes (index, position, normal or uv) in BufferGeometry.computeTangents()"); else {
            var c = this.attributes.index.array, d = this.attributes.position.array, e = this.attributes.normal.array, f = this.attributes.uv.array, g = d.length / 3;
            void 0 === this.attributes.tangent && this.addAttribute("tangent", new THREE.BufferAttribute(new Float32Array(4 * g), 4));
            for (var h = this.attributes.tangent.array, k = [], l = [], p = 0; g > p; p++)k[p] = new THREE.Vector3, l[p] = new THREE.Vector3;
            var v, x, D, w, y, A, E, G, F, z, I, H, L, P, N, R, q = new THREE.Vector3, n = new THREE.Vector3, t = new THREE.Vector3, r = new THREE.Vector2, s = new THREE.Vector2, u = new THREE.Vector2, U = new THREE.Vector3, M = new THREE.Vector3;
            0 === this.drawcalls.length && this.addDrawCall(0, c.length, 0);
            var V = this.drawcalls, p = 0;
            for (L = V.length; L > p; ++p) {
                H = V[p].start, P = V[p].count;
                var J = V[p].index, g = H;
                for (H += P; H > g; g += 3)P = J + c[g], N = J + c[g + 1], R = J + c[g + 2], a(P, N, R)
            }
            var ca, ba, qa, oa = new THREE.Vector3, ja = new THREE.Vector3, ha = new THREE.Vector3, O = new THREE.Vector3, p = 0;
            for (L = V.length; L > p; ++p)for (H = V[p].start, P = V[p].count, J = V[p].index, g = H, H += P; H > g; g += 3)P = J + c[g], N = J + c[g + 1], R = J + c[g + 2], b(P), b(N), b(R)
        }
    }, computeOffsets: function (a) {
        void 0 === a && (a = 65535);
        for (var b = this.attributes.index.array, c = this.attributes.position.array, d = b.length / 3, e = new Uint16Array(b.length), f = 0, g = 0, h = [{
            start: 0,
            count: 0,
            index: 0
        }], k = h[0], l = 0, p = 0, q = new Int32Array(6), n = new Int32Array(c.length), t = new Int32Array(c.length), r = 0; r < c.length; r++)n[r] = -1, t[r] = -1;
        for (c = 0; d > c; c++) {
            for (var s = p = 0; 3 > s; s++)r = b[3 * c + s], -1 == n[r] ? (q[2 * s] = r, q[2 * s + 1] = -1, p++) : n[r] < k.index ? (q[2 * s] = r, q[2 * s + 1] = -1, l++) : (q[2 * s] = r, q[2 * s + 1] = n[r]);
            if (g + p > k.index + a)for (k = {
                start: f,
                count: 0,
                index: g
            }, h.push(k), p = 0; 6 > p; p += 2)s = q[p + 1], s > -1 && s < k.index && (q[p + 1] = -1);
            for (p = 0; 6 > p; p += 2)r = q[p], s = q[p + 1], -1 === s && (s = g++), n[r] = s, t[s] = r, e[f++] = s - k.index, k.count++
        }
        return this.reorderBuffers(e, t, g), this.drawcalls = this.offsets = h
    }, merge: function (a, b) {
        if (!1 != a instanceof THREE.BufferGeometry) {
            void 0 === b && (b = 0);
            var d, c = this.attributes;
            for (d in c)if (void 0 !== a.attributes[d])for (var e = c[d].array, f = a.attributes[d], g = f.array, h = 0, f = f.itemSize * b; h < g.length; h++, f++)e[f] = g[h];
            return this
        }
        THREE.error("THREE.BufferGeometry.merge(): geometry not an instance of THREE.BufferGeometry.", a)
    }, normalizeNormals: function () {
        for (var b, c, d, a = this.attributes.normal.array, e = 0, f = a.length; f > e; e += 3)b = a[e], c = a[e + 1], d = a[e + 2], b = 1 / Math.sqrt(b * b + c * c + d * d), a[e] *= b, a[e + 1] *= b, a[e + 2] *= b
    }, reorderBuffers: function (a, b, c) {
        var e, d = {};
        for (e in this.attributes)"index" != e && (d[e] = new this.attributes[e].array.constructor(this.attributes[e].itemSize * c));
        for (var f = 0; c > f; f++) {
            var g = b[f];
            for (e in this.attributes)if ("index" != e)for (var h = this.attributes[e].array, k = this.attributes[e].itemSize, l = d[e], p = 0; k > p; p++)l[f * k + p] = h[g * k + p]
        }
        this.attributes.index.array = a;
        for (e in this.attributes)"index" != e && (this.attributes[e].array = d[e], this.attributes[e].numItems = this.attributes[e].itemSize * c)
    }, toJSON: function () {
        var e, a = {
            metadata: {version: 4, type: "BufferGeometry", generator: "BufferGeometryExporter"},
            uuid: this.uuid,
            type: this.type,
            data: {attributes: {}}
        }, b = this.attributes, c = this.offsets, d = this.boundingSphere;
        for (e in b) {
            var f = b[e], g = Array.prototype.slice.call(f.array);
            a.data.attributes[e] = {itemSize: f.itemSize, type: f.array.constructor.name, array: g}
        }
        return 0 < c.length && (a.data.offsets = JSON.parse(JSON.stringify(c))), null !== d && (a.data.boundingSphere = {
            center: d.center.toArray(),
            radius: d.radius
        }), a
    }, clone: function () {
        var b, a = new THREE.BufferGeometry;
        for (b in this.attributes)a.addAttribute(b, this.attributes[b].clone());
        b = 0;
        for (var c = this.offsets.length; c > b; b++) {
            var d = this.offsets[b];
            a.offsets.push({start: d.start, index: d.index, count: d.count})
        }
        return a
    }, dispose: function () {
        this.dispatchEvent({type: "dispose"})
    }
},THREE.EventDispatcher.prototype.apply(THREE.BufferGeometry.prototype),THREE.Geometry = function () {
    Object.defineProperty(this, "id", {value: THREE.GeometryIdCount++}), this.uuid = THREE.Math.generateUUID(), this.name = "", this.type = "Geometry", this.vertices = [], this.colors = [], this.faces = [], this.faceVertexUvs = [[]], this.morphTargets = [], this.morphColors = [], this.morphNormals = [], this.skinWeights = [], this.skinIndices = [], this.lineDistances = [], this.boundingSphere = this.boundingBox = null, this.hasTangents = !1, this.dynamic = !0, this.groupsNeedUpdate = this.lineDistancesNeedUpdate = this.colorsNeedUpdate = this.tangentsNeedUpdate = this.normalsNeedUpdate = this.uvsNeedUpdate = this.elementsNeedUpdate = this.verticesNeedUpdate = !1
},THREE.Geometry.prototype = {
    constructor: THREE.Geometry, applyMatrix: function (a) {
        for (var b = (new THREE.Matrix3).getNormalMatrix(a), c = 0, d = this.vertices.length; d > c; c++)this.vertices[c].applyMatrix4(a);
        for (c = 0, d = this.faces.length; d > c; c++) {
            a = this.faces[c], a.normal.applyMatrix3(b).normalize();
            for (var e = 0, f = a.vertexNormals.length; f > e; e++)a.vertexNormals[e].applyMatrix3(b).normalize()
        }
        null !== this.boundingBox && this.computeBoundingBox(), null !== this.boundingSphere && this.computeBoundingSphere(), this.normalsNeedUpdate = this.verticesNeedUpdate = !0
    }, fromBufferGeometry: function (a) {
        for (var b = this, c = a.attributes, d = c.position.array, e = void 0 !== c.index ? c.index.array : void 0, f = void 0 !== c.normal ? c.normal.array : void 0, g = void 0 !== c.color ? c.color.array : void 0, h = void 0 !== c.uv ? c.uv.array : void 0, k = [], l = [], p = c = 0; c < d.length; c += 3, p += 2)b.vertices.push(new THREE.Vector3(d[c], d[c + 1], d[c + 2])), void 0 !== f && k.push(new THREE.Vector3(f[c], f[c + 1], f[c + 2])), void 0 !== g && b.colors.push(new THREE.Color(g[c], g[c + 1], g[c + 2])), void 0 !== h && l.push(new THREE.Vector2(h[p], h[p + 1]));
        var q = function (a, c, d) {
            var e = void 0 !== f ? [k[a].clone(), k[c].clone(), k[d].clone()] : [], n = void 0 !== g ? [b.colors[a].clone(), b.colors[c].clone(), b.colors[d].clone()] : [];
            b.faces.push(new THREE.Face3(a, c, d, e, n)), void 0 !== h && b.faceVertexUvs[0].push([l[a].clone(), l[c].clone(), l[d].clone()])
        };
        if (void 0 !== e)if (d = a.drawcalls, 0 < d.length)for (c = 0; c < d.length; c++)for (var p = d[c], n = p.start, t = p.count, r = p.index, p = n, n = n + t; n > p; p += 3)q(r + e[p], r + e[p + 1], r + e[p + 2]); else for (c = 0; c < e.length; c += 3)q(e[c], e[c + 1], e[c + 2]); else for (c = 0; c < d.length / 3; c += 3)q(c, c + 1, c + 2);
        return this.computeFaceNormals(), null !== a.boundingBox && (this.boundingBox = a.boundingBox.clone()), null !== a.boundingSphere && (this.boundingSphere = a.boundingSphere.clone()), this
    }, center: function () {
        this.computeBoundingBox();
        var a = this.boundingBox.center().negate();
        return this.applyMatrix((new THREE.Matrix4).setPosition(a)), a
    }, computeFaceNormals: function () {
        for (var a = new THREE.Vector3, b = new THREE.Vector3, c = 0, d = this.faces.length; d > c; c++) {
            var e = this.faces[c], f = this.vertices[e.a], g = this.vertices[e.b];
            a.subVectors(this.vertices[e.c], g), b.subVectors(f, g), a.cross(b), a.normalize(), e.normal.copy(a)
        }
    }, computeVertexNormals: function (a) {
        var b, c, d;
        for (d = Array(this.vertices.length), b = 0, c = this.vertices.length; c > b; b++)d[b] = new THREE.Vector3;
        if (a) {
            var e, f, g, h = new THREE.Vector3, k = new THREE.Vector3;
            for (a = 0, b = this.faces.length; b > a; a++)c = this.faces[a], e = this.vertices[c.a], f = this.vertices[c.b], g = this.vertices[c.c], h.subVectors(g, f), k.subVectors(e, f), h.cross(k), d[c.a].add(h), d[c.b].add(h), d[c.c].add(h)
        } else for (a = 0, b = this.faces.length; b > a; a++)c = this.faces[a], d[c.a].add(c.normal), d[c.b].add(c.normal), d[c.c].add(c.normal);
        for (b = 0, c = this.vertices.length; c > b; b++)d[b].normalize();
        for (a = 0, b = this.faces.length; b > a; a++)c = this.faces[a], c.vertexNormals[0] = d[c.a].clone(), c.vertexNormals[1] = d[c.b].clone(), c.vertexNormals[2] = d[c.c].clone()
    }, computeMorphNormals: function () {
        var a, b, c, d, e;
        for (c = 0, d = this.faces.length; d > c; c++)for (e = this.faces[c], e.__originalFaceNormal ? e.__originalFaceNormal.copy(e.normal) : e.__originalFaceNormal = e.normal.clone(), e.__originalVertexNormals || (e.__originalVertexNormals = []), a = 0, b = e.vertexNormals.length; b > a; a++)e.__originalVertexNormals[a] ? e.__originalVertexNormals[a].copy(e.vertexNormals[a]) : e.__originalVertexNormals[a] = e.vertexNormals[a].clone();
        var f = new THREE.Geometry;
        for (f.faces = this.faces, a = 0, b = this.morphTargets.length; b > a; a++) {
            if (!this.morphNormals[a]) {
                this.morphNormals[a] = {}, this.morphNormals[a].faceNormals = [], this.morphNormals[a].vertexNormals = [], e = this.morphNormals[a].faceNormals;
                var h, k, g = this.morphNormals[a].vertexNormals;
                for (c = 0, d = this.faces.length; d > c; c++)h = new THREE.Vector3, k = {
                    a: new THREE.Vector3,
                    b: new THREE.Vector3,
                    c: new THREE.Vector3
                }, e.push(h), g.push(k)
            }
            for (g = this.morphNormals[a], f.vertices = this.morphTargets[a].vertices, f.computeFaceNormals(), f.computeVertexNormals(), c = 0, d = this.faces.length; d > c; c++)e = this.faces[c], h = g.faceNormals[c], k = g.vertexNormals[c], h.copy(e.normal), k.a.copy(e.vertexNormals[0]), k.b.copy(e.vertexNormals[1]), k.c.copy(e.vertexNormals[2])
        }
        for (c = 0, d = this.faces.length; d > c; c++)e = this.faces[c], e.normal = e.__originalFaceNormal, e.vertexNormals = e.__originalVertexNormals
    }, computeTangents: function () {
        var a, b, c, d, e, f, g, h, k, l, p, q, n, t, r, s, u, v = [], x = [];
        c = new THREE.Vector3;
        var D = new THREE.Vector3, w = new THREE.Vector3, y = new THREE.Vector3, A = new THREE.Vector3;
        for (a = 0, b = this.vertices.length; b > a; a++)v[a] = new THREE.Vector3, x[a] = new THREE.Vector3;
        for (a = 0, b = this.faces.length; b > a; a++)e = this.faces[a], f = this.faceVertexUvs[0][a], d = e.a, u = e.b, e = e.c, g = this.vertices[d], h = this.vertices[u], k = this.vertices[e], l = f[0], p = f[1], q = f[2], f = h.x - g.x, n = k.x - g.x, t = h.y - g.y, r = k.y - g.y, h = h.z - g.z, g = k.z - g.z, k = p.x - l.x, s = q.x - l.x, p = p.y - l.y, l = q.y - l.y, q = 1 / (k * l - s * p), c.set((l * f - p * n) * q, (l * t - p * r) * q, (l * h - p * g) * q), D.set((k * n - s * f) * q, (k * r - s * t) * q, (k * g - s * h) * q), v[d].add(c), v[u].add(c), v[e].add(c), x[d].add(D), x[u].add(D), x[e].add(D);
        for (D = ["a", "b", "c", "d"], a = 0, b = this.faces.length; b > a; a++)for (e = this.faces[a], c = 0; c < Math.min(e.vertexNormals.length, 3); c++)A.copy(e.vertexNormals[c]), d = e[D[c]], u = v[d], w.copy(u), w.sub(A.multiplyScalar(A.dot(u))).normalize(), y.crossVectors(e.vertexNormals[c], u), d = y.dot(x[d]), d = 0 > d ? -1 : 1, e.vertexTangents[c] = new THREE.Vector4(w.x, w.y, w.z, d);
        this.hasTangents = !0
    }, computeLineDistances: function () {
        for (var a = 0, b = this.vertices, c = 0, d = b.length; d > c; c++)c > 0 && (a += b[c].distanceTo(b[c - 1])), this.lineDistances[c] = a
    }, computeBoundingBox: function () {
        null === this.boundingBox && (this.boundingBox = new THREE.Box3), this.boundingBox.setFromPoints(this.vertices)
    }, computeBoundingSphere: function () {
        null === this.boundingSphere && (this.boundingSphere = new THREE.Sphere), this.boundingSphere.setFromPoints(this.vertices)
    }, merge: function (a, b, c) {
        if (!1 == a instanceof THREE.Geometry)THREE.error("THREE.Geometry.merge(): geometry not an instance of THREE.Geometry.", a); else {
            var d, e = this.vertices.length, f = this.vertices, g = a.vertices, h = this.faces, k = a.faces, l = this.faceVertexUvs[0];
            a = a.faceVertexUvs[0], void 0 === c && (c = 0), void 0 !== b && (d = (new THREE.Matrix3).getNormalMatrix(b));
            for (var p = 0, q = g.length; q > p; p++) {
                var n = g[p].clone();
                void 0 !== b && n.applyMatrix4(b), f.push(n)
            }
            for (p = 0, q = k.length; q > p; p++) {
                var t, g = k[p], r = g.vertexNormals, s = g.vertexColors, n = new THREE.Face3(g.a + e, g.b + e, g.c + e);
                for (n.normal.copy(g.normal), void 0 !== d && n.normal.applyMatrix3(d).normalize(), b = 0, f = r.length; f > b; b++)t = r[b].clone(), void 0 !== d && t.applyMatrix3(d).normalize(), n.vertexNormals.push(t);
                for (n.color.copy(g.color), b = 0, f = s.length; f > b; b++)t = s[b], n.vertexColors.push(t.clone());
                n.materialIndex = g.materialIndex + c, h.push(n)
            }
            for (p = 0, q = a.length; q > p; p++)if (c = a[p], d = [], void 0 !== c) {
                for (b = 0, f = c.length; f > b; b++)d.push(c[b].clone());
                l.push(d)
            }
        }
    }, mergeMesh: function (a) {
        !1 == a instanceof THREE.Mesh ? THREE.error("THREE.Geometry.mergeMesh(): mesh not an instance of THREE.Mesh.", a) : (a.matrixAutoUpdate && a.updateMatrix(), this.merge(a.geometry, a.matrix))
    }, mergeVertices: function () {
        var d, f, g, a = {}, b = [], c = [], e = Math.pow(10, 4);
        for (f = 0, g = this.vertices.length; g > f; f++)d = this.vertices[f], d = Math.round(d.x * e) + "_" + Math.round(d.y * e) + "_" + Math.round(d.z * e), void 0 === a[d] ? (a[d] = f, b.push(this.vertices[f]), c[f] = b.length - 1) : c[f] = c[a[d]];
        for (a = [], f = 0, g = this.faces.length; g > f; f++)for (e = this.faces[f], e.a = c[e.a], e.b = c[e.b], e.c = c[e.c], e = [e.a, e.b, e.c], d = 0; 3 > d; d++)if (e[d] == e[(d + 1) % 3]) {
            a.push(f);
            break
        }
        for (f = a.length - 1; f >= 0; f--)for (e = a[f], this.faces.splice(e, 1), c = 0, g = this.faceVertexUvs.length; g > c; c++)this.faceVertexUvs[c].splice(e, 1);
        return f = this.vertices.length - b.length, this.vertices = b, f
    }, toJSON: function () {
        function a(a, b, c) {
            return c ? a | 1 << b : a & ~(1 << b)
        }

        function b(a) {
            var b = a.x.toString() + a.y.toString() + a.z.toString();
            return void 0 !== l[b] ? l[b] : (l[b] = k.length / 3, k.push(a.x, a.y, a.z), l[b])
        }

        function c(a) {
            var b = a.r.toString() + a.g.toString() + a.b.toString();
            return void 0 !== q[b] ? q[b] : (q[b] = p.length, p.push(a.getHex()), q[b])
        }

        function d(a) {
            var b = a.x.toString() + a.y.toString();
            return void 0 !== t[b] ? t[b] : (t[b] = n.length / 2, n.push(a.x, a.y), t[b])
        }

        var e = {
            metadata: {version: 4, type: "BufferGeometry", generator: "BufferGeometryExporter"},
            uuid: this.uuid,
            type: this.type
        };
        if ("" !== this.name && (e.name = this.name), void 0 !== this.parameters) {
            var g, f = this.parameters;
            for (g in f)void 0 !== f[g] && (e[g] = f[g]);
            return e
        }
        for (f = [], g = 0; g < this.vertices.length; g++) {
            var h = this.vertices[g];
            f.push(h.x, h.y, h.z)
        }
        var h = [], k = [], l = {}, p = [], q = {}, n = [], t = {};
        for (g = 0; g < this.faces.length; g++) {
            var r = this.faces[g], s = void 0 !== this.faceVertexUvs[0][g], u = 0 < r.normal.length(), v = 0 < r.vertexNormals.length, x = 1 !== r.color.r || 1 !== r.color.g || 1 !== r.color.b, D = 0 < r.vertexColors.length, w = 0, w = a(w, 0, 0), w = a(w, 1, !1), w = a(w, 2, !1), w = a(w, 3, s), w = a(w, 4, u), w = a(w, 5, v), w = a(w, 6, x), w = a(w, 7, D);
            h.push(w), h.push(r.a, r.b, r.c), s && (s = this.faceVertexUvs[0][g], h.push(d(s[0]), d(s[1]), d(s[2]))), u && h.push(b(r.normal)), v && (u = r.vertexNormals, h.push(b(u[0]), b(u[1]), b(u[2]))), x && h.push(c(r.color)), D && (r = r.vertexColors, h.push(c(r[0]), c(r[1]), c(r[2])))
        }
        return e.data = {}, e.data.vertices = f, e.data.normals = k, 0 < p.length && (e.data.colors = p), 0 < n.length && (e.data.uvs = [n]), e.data.faces = h, e
    }, clone: function () {
        for (var a = new THREE.Geometry, b = this.vertices, c = 0, d = b.length; d > c; c++)a.vertices.push(b[c].clone());
        for (b = this.faces, c = 0, d = b.length; d > c; c++)a.faces.push(b[c].clone());
        for (c = 0, d = this.faceVertexUvs.length; d > c; c++) {
            b = this.faceVertexUvs[c], void 0 === a.faceVertexUvs[c] && (a.faceVertexUvs[c] = []);
            for (var e = 0, f = b.length; f > e; e++) {
                for (var g = b[e], h = [], k = 0, l = g.length; l > k; k++)h.push(g[k].clone());
                a.faceVertexUvs[c].push(h)
            }
        }
        return a
    }, dispose: function () {
        this.dispatchEvent({type: "dispose"})
    }
},THREE.EventDispatcher.prototype.apply(THREE.Geometry.prototype),THREE.GeometryIdCount = 0,THREE.Camera = function () {
    THREE.Object3D.call(this), this.type = "Camera", this.matrixWorldInverse = new THREE.Matrix4, this.projectionMatrix = new THREE.Matrix4
},THREE.Camera.prototype = Object.create(THREE.Object3D.prototype),THREE.Camera.prototype.constructor = THREE.Camera,THREE.Camera.prototype.getWorldDirection = function () {
    var a = new THREE.Quaternion;
    return function (b) {
        return b = b || new THREE.Vector3, this.getWorldQuaternion(a), b.set(0, 0, -1).applyQuaternion(a)
    }
}(),THREE.Camera.prototype.lookAt = function () {
    var a = new THREE.Matrix4;
    return function (b) {
        a.lookAt(this.position, b, this.up), this.quaternion.setFromRotationMatrix(a)
    }
}(),THREE.Camera.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.Camera), THREE.Object3D.prototype.clone.call(this, a), a.matrixWorldInverse.copy(this.matrixWorldInverse), a.projectionMatrix.copy(this.projectionMatrix), a
},THREE.CubeCamera = function (a, b, c) {
    THREE.Object3D.call(this), this.type = "CubeCamera";
    var d = new THREE.PerspectiveCamera(90, 1, a, b);
    d.up.set(0, -1, 0), d.lookAt(new THREE.Vector3(1, 0, 0)), this.add(d);
    var e = new THREE.PerspectiveCamera(90, 1, a, b);
    e.up.set(0, -1, 0), e.lookAt(new THREE.Vector3(-1, 0, 0)), this.add(e);
    var f = new THREE.PerspectiveCamera(90, 1, a, b);
    f.up.set(0, 0, 1), f.lookAt(new THREE.Vector3(0, 1, 0)), this.add(f);
    var g = new THREE.PerspectiveCamera(90, 1, a, b);
    g.up.set(0, 0, -1), g.lookAt(new THREE.Vector3(0, -1, 0)), this.add(g);
    var h = new THREE.PerspectiveCamera(90, 1, a, b);
    h.up.set(0, -1, 0), h.lookAt(new THREE.Vector3(0, 0, 1)), this.add(h);
    var k = new THREE.PerspectiveCamera(90, 1, a, b);
    k.up.set(0, -1, 0), k.lookAt(new THREE.Vector3(0, 0, -1)), this.add(k), this.renderTarget = new THREE.WebGLRenderTargetCube(c, c, {
        format: THREE.RGBFormat,
        magFilter: THREE.LinearFilter,
        minFilter: THREE.LinearFilter
    }), this.updateCubeMap = function (a, b) {
        var c = this.renderTarget, n = c.generateMipmaps;
        c.generateMipmaps = !1, c.activeCubeFace = 0, a.render(b, d, c), c.activeCubeFace = 1, a.render(b, e, c), c.activeCubeFace = 2, a.render(b, f, c), c.activeCubeFace = 3, a.render(b, g, c), c.activeCubeFace = 4, a.render(b, h, c), c.generateMipmaps = n, c.activeCubeFace = 5, a.render(b, k, c)
    }
},THREE.CubeCamera.prototype = Object.create(THREE.Object3D.prototype),THREE.CubeCamera.prototype.constructor = THREE.CubeCamera,THREE.OrthographicCamera = function (a, b, c, d, e, f) {
    THREE.Camera.call(this), this.type = "OrthographicCamera", this.zoom = 1, this.left = a, this.right = b, this.top = c, this.bottom = d, this.near = void 0 !== e ? e : .1, this.far = void 0 !== f ? f : 2e3, this.updateProjectionMatrix()
},THREE.OrthographicCamera.prototype = Object.create(THREE.Camera.prototype),THREE.OrthographicCamera.prototype.constructor = THREE.OrthographicCamera,THREE.OrthographicCamera.prototype.updateProjectionMatrix = function () {
    var a = (this.right - this.left) / (2 * this.zoom), b = (this.top - this.bottom) / (2 * this.zoom), c = (this.right + this.left) / 2, d = (this.top + this.bottom) / 2;
    this.projectionMatrix.makeOrthographic(c - a, c + a, d + b, d - b, this.near, this.far)
},THREE.OrthographicCamera.prototype.clone = function () {
    var a = new THREE.OrthographicCamera;
    return THREE.Camera.prototype.clone.call(this, a), a.zoom = this.zoom, a.left = this.left, a.right = this.right, a.top = this.top, a.bottom = this.bottom, a.near = this.near, a.far = this.far, a.projectionMatrix.copy(this.projectionMatrix), a
},THREE.PerspectiveCamera = function (a, b, c, d) {
    THREE.Camera.call(this), this.type = "PerspectiveCamera", this.zoom = 1, this.fov = void 0 !== a ? a : 50, this.aspect = void 0 !== b ? b : 1, this.near = void 0 !== c ? c : .1, this.far = void 0 !== d ? d : 2e3, this.updateProjectionMatrix()
},THREE.PerspectiveCamera.prototype = Object.create(THREE.Camera.prototype),THREE.PerspectiveCamera.prototype.constructor = THREE.PerspectiveCamera,THREE.PerspectiveCamera.prototype.setLens = function (a, b) {
    void 0 === b && (b = 24), this.fov = 2 * THREE.Math.radToDeg(Math.atan(b / (2 * a))), this.updateProjectionMatrix()
},THREE.PerspectiveCamera.prototype.setViewOffset = function (a, b, c, d, e, f) {
    this.fullWidth = a, this.fullHeight = b, this.x = c, this.y = d, this.width = e, this.height = f, this.updateProjectionMatrix()
},THREE.PerspectiveCamera.prototype.updateProjectionMatrix = function () {
    var a = THREE.Math.radToDeg(2 * Math.atan(Math.tan(.5 * THREE.Math.degToRad(this.fov)) / this.zoom));
    if (this.fullWidth) {
        var b = this.fullWidth / this.fullHeight, a = Math.tan(THREE.Math.degToRad(.5 * a)) * this.near, c = -a, d = b * c, b = Math.abs(b * a - d), c = Math.abs(a - c);
        this.projectionMatrix.makeFrustum(d + this.x * b / this.fullWidth, d + (this.x + this.width) * b / this.fullWidth, a - (this.y + this.height) * c / this.fullHeight, a - this.y * c / this.fullHeight, this.near, this.far)
    } else this.projectionMatrix.makePerspective(a, this.aspect, this.near, this.far)
},THREE.PerspectiveCamera.prototype.clone = function () {
    var a = new THREE.PerspectiveCamera;
    return THREE.Camera.prototype.clone.call(this, a), a.zoom = this.zoom, a.fov = this.fov, a.aspect = this.aspect, a.near = this.near, a.far = this.far, a.projectionMatrix.copy(this.projectionMatrix), a
},THREE.Light = function (a) {
    THREE.Object3D.call(this), this.type = "Light", this.color = new THREE.Color(a)
},THREE.Light.prototype = Object.create(THREE.Object3D.prototype),THREE.Light.prototype.constructor = THREE.Light,THREE.Light.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.Light), THREE.Object3D.prototype.clone.call(this, a), a.color.copy(this.color), a
},THREE.AmbientLight = function (a) {
    THREE.Light.call(this, a), this.type = "AmbientLight"
},THREE.AmbientLight.prototype = Object.create(THREE.Light.prototype),THREE.AmbientLight.prototype.constructor = THREE.AmbientLight,THREE.AmbientLight.prototype.clone = function () {
    var a = new THREE.AmbientLight;
    return THREE.Light.prototype.clone.call(this, a), a
},THREE.AreaLight = function (a, b) {
    THREE.Light.call(this, a), this.type = "AreaLight", this.normal = new THREE.Vector3(0, -1, 0), this.right = new THREE.Vector3(1, 0, 0), this.intensity = void 0 !== b ? b : 1, this.height = this.width = 1, this.constantAttenuation = 1.5, this.linearAttenuation = .5, this.quadraticAttenuation = .1
},THREE.AreaLight.prototype = Object.create(THREE.Light.prototype),THREE.AreaLight.prototype.constructor = THREE.AreaLight,THREE.DirectionalLight = function (a, b) {
    THREE.Light.call(this, a), this.type = "DirectionalLight", this.position.set(0, 1, 0), this.target = new THREE.Object3D, this.intensity = void 0 !== b ? b : 1, this.onlyShadow = this.castShadow = !1, this.shadowCameraNear = 50, this.shadowCameraFar = 5e3, this.shadowCameraLeft = -500, this.shadowCameraTop = this.shadowCameraRight = 500, this.shadowCameraBottom = -500, this.shadowCameraVisible = !1, this.shadowBias = 0, this.shadowDarkness = .5, this.shadowMapHeight = this.shadowMapWidth = 512, this.shadowCascade = !1, this.shadowCascadeOffset = new THREE.Vector3(0, 0, -1e3), this.shadowCascadeCount = 2, this.shadowCascadeBias = [0, 0, 0], this.shadowCascadeWidth = [512, 512, 512], this.shadowCascadeHeight = [512, 512, 512], this.shadowCascadeNearZ = [-1, .99, .998], this.shadowCascadeFarZ = [.99, .998, 1], this.shadowCascadeArray = [], this.shadowMatrix = this.shadowCamera = this.shadowMapSize = this.shadowMap = null
},THREE.DirectionalLight.prototype = Object.create(THREE.Light.prototype),THREE.DirectionalLight.prototype.constructor = THREE.DirectionalLight,THREE.DirectionalLight.prototype.clone = function () {
    var a = new THREE.DirectionalLight;
    return THREE.Light.prototype.clone.call(this, a), a.target = this.target.clone(), a.intensity = this.intensity, a.castShadow = this.castShadow, a.onlyShadow = this.onlyShadow, a.shadowCameraNear = this.shadowCameraNear, a.shadowCameraFar = this.shadowCameraFar, a.shadowCameraLeft = this.shadowCameraLeft, a.shadowCameraRight = this.shadowCameraRight, a.shadowCameraTop = this.shadowCameraTop, a.shadowCameraBottom = this.shadowCameraBottom, a.shadowCameraVisible = this.shadowCameraVisible, a.shadowBias = this.shadowBias, a.shadowDarkness = this.shadowDarkness, a.shadowMapWidth = this.shadowMapWidth, a.shadowMapHeight = this.shadowMapHeight, a.shadowCascade = this.shadowCascade, a.shadowCascadeOffset.copy(this.shadowCascadeOffset), a.shadowCascadeCount = this.shadowCascadeCount, a.shadowCascadeBias = this.shadowCascadeBias.slice(0), a.shadowCascadeWidth = this.shadowCascadeWidth.slice(0), a.shadowCascadeHeight = this.shadowCascadeHeight.slice(0), a.shadowCascadeNearZ = this.shadowCascadeNearZ.slice(0), a.shadowCascadeFarZ = this.shadowCascadeFarZ.slice(0), a
},THREE.HemisphereLight = function (a, b, c) {
    THREE.Light.call(this, a), this.type = "HemisphereLight", this.position.set(0, 100, 0), this.groundColor = new THREE.Color(b), this.intensity = void 0 !== c ? c : 1
},THREE.HemisphereLight.prototype = Object.create(THREE.Light.prototype),THREE.HemisphereLight.prototype.constructor = THREE.HemisphereLight,THREE.HemisphereLight.prototype.clone = function () {
    var a = new THREE.HemisphereLight;
    return THREE.Light.prototype.clone.call(this, a), a.groundColor.copy(this.groundColor), a.intensity = this.intensity, a
},THREE.PointLight = function (a, b, c, d) {
    THREE.Light.call(this, a), this.type = "PointLight", this.intensity = void 0 !== b ? b : 1, this.distance = void 0 !== c ? c : 0, this.decay = void 0 !== d ? d : 1
},THREE.PointLight.prototype = Object.create(THREE.Light.prototype),THREE.PointLight.prototype.constructor = THREE.PointLight,THREE.PointLight.prototype.clone = function () {
    var a = new THREE.PointLight;
    return THREE.Light.prototype.clone.call(this, a), a.intensity = this.intensity, a.distance = this.distance, a.decay = this.decay, a
},THREE.SpotLight = function (a, b, c, d, e, f) {
    THREE.Light.call(this, a), this.type = "SpotLight", this.position.set(0, 1, 0), this.target = new THREE.Object3D, this.intensity = void 0 !== b ? b : 1, this.distance = void 0 !== c ? c : 0, this.angle = void 0 !== d ? d : Math.PI / 3, this.exponent = void 0 !== e ? e : 10, this.decay = void 0 !== f ? f : 1, this.onlyShadow = this.castShadow = !1, this.shadowCameraNear = 50, this.shadowCameraFar = 5e3, this.shadowCameraFov = 50, this.shadowCameraVisible = !1, this.shadowBias = 0, this.shadowDarkness = .5, this.shadowMapHeight = this.shadowMapWidth = 512, this.shadowMatrix = this.shadowCamera = this.shadowMapSize = this.shadowMap = null
},THREE.SpotLight.prototype = Object.create(THREE.Light.prototype),THREE.SpotLight.prototype.constructor = THREE.SpotLight,THREE.SpotLight.prototype.clone = function () {
    var a = new THREE.SpotLight;
    return THREE.Light.prototype.clone.call(this, a), a.target = this.target.clone(), a.intensity = this.intensity, a.distance = this.distance, a.angle = this.angle, a.exponent = this.exponent, a.decay = this.decay, a.castShadow = this.castShadow, a.onlyShadow = this.onlyShadow, a.shadowCameraNear = this.shadowCameraNear, a.shadowCameraFar = this.shadowCameraFar, a.shadowCameraFov = this.shadowCameraFov, a.shadowCameraVisible = this.shadowCameraVisible, a.shadowBias = this.shadowBias, a.shadowDarkness = this.shadowDarkness, a.shadowMapWidth = this.shadowMapWidth, a.shadowMapHeight = this.shadowMapHeight, a
},THREE.Cache = {
    files: {}, add: function (a, b) {
        this.files[a] = b
    }, get: function (a) {
        return this.files[a]
    }, remove: function (a) {
        delete this.files[a]
    }, clear: function () {
        this.files = {}
    }
},THREE.Loader = function (a) {
    this.statusDomElement = (this.showStatus = a) ? THREE.Loader.prototype.addStatusElement() : null, this.imageLoader = new THREE.ImageLoader, this.onLoadStart = function () {
    }, this.onLoadProgress = function () {
    }, this.onLoadComplete = function () {
    }
},THREE.Loader.prototype = {
    constructor: THREE.Loader, crossOrigin: void 0, addStatusElement: function () {
        var a = document.createElement("div");
        return a.style.position = "absolute", a.style.right = "0px", a.style.top = "0px", a.style.fontSize = "0.8em", a.style.textAlign = "left", a.style.background = "rgba(0,0,0,0.25)", a.style.color = "#fff", a.style.width = "120px", a.style.padding = "0.5em 0.5em 0.5em 0.5em", a.style.zIndex = 1e3, a.innerHTML = "Loading ...", a
    }, updateProgress: function (a) {
        var b = "Loaded ", b = a.total ? b + ((100 * a.loaded / a.total).toFixed(0) + "%") : b + ((a.loaded / 1024).toFixed(2) + " KB");
        this.statusDomElement.innerHTML = b
    }, extractUrlBase: function (a) {
        return a = a.split("/"), 1 === a.length ? "./" : (a.pop(), a.join("/") + "/")
    }, initMaterials: function (a, b) {
        for (var c = [], d = 0; d < a.length; ++d)c[d] = this.createMaterial(a[d], b);
        return c
    }, needsTangents: function (a) {
        for (var b = 0, c = a.length; c > b; b++)if (a[b] instanceof THREE.ShaderMaterial)return !0;
        return !1
    }, createMaterial: function (a, b) {
        function c(a) {
            return a = Math.log(a) / Math.LN2, Math.pow(2, Math.round(a))
        }

        function d(a, d, e, g, h, k, s) {
            var v, u = b + e, x = THREE.Loader.Handlers.get(u);
            null !== x ? v = x.load(u) : (v = new THREE.Texture, x = f.imageLoader, x.crossOrigin = f.crossOrigin, x.load(u, function (a) {
                if (!1 === THREE.Math.isPowerOfTwo(a.width) || !1 === THREE.Math.isPowerOfTwo(a.height)) {
                    var b = c(a.width), d = c(a.height), e = document.createElement("canvas");
                    e.width = b, e.height = d, e.getContext("2d").drawImage(a, 0, 0, b, d), v.image = e
                } else v.image = a;
                v.needsUpdate = !0
            })), v.sourceFile = e, g && (v.repeat.set(g[0], g[1]), 1 !== g[0] && (v.wrapS = THREE.RepeatWrapping), 1 !== g[1] && (v.wrapT = THREE.RepeatWrapping)), h && v.offset.set(h[0], h[1]), k && (e = {
                repeat: THREE.RepeatWrapping,
                mirror: THREE.MirroredRepeatWrapping
            }, void 0 !== e[k[0]] && (v.wrapS = e[k[0]]), void 0 !== e[k[1]] && (v.wrapT = e[k[1]])), s && (v.anisotropy = s), a[d] = v
        }

        function e(a) {
            return (255 * a[0] << 16) + (255 * a[1] << 8) + 255 * a[2]
        }

        var f = this, g = "MeshLambertMaterial", h = {
            color: 15658734,
            opacity: 1,
            map: null,
            lightMap: null,
            normalMap: null,
            bumpMap: null,
            wireframe: !1
        };
        if (a.shading) {
            var k = a.shading.toLowerCase();
            "phong" === k ? g = "MeshPhongMaterial" : "basic" === k && (g = "MeshBasicMaterial")
        }
        return void 0 !== a.blending && void 0 !== THREE[a.blending] && (h.blending = THREE[a.blending]), void 0 !== a.transparent && (h.transparent = a.transparent), void 0 !== a.opacity && 1 > a.opacity && (h.transparent = !0), void 0 !== a.depthTest && (h.depthTest = a.depthTest), void 0 !== a.depthWrite && (h.depthWrite = a.depthWrite), void 0 !== a.visible && (h.visible = a.visible), void 0 !== a.flipSided && (h.side = THREE.BackSide), void 0 !== a.doubleSided && (h.side = THREE.DoubleSide), void 0 !== a.wireframe && (h.wireframe = a.wireframe), void 0 !== a.vertexColors && ("face" === a.vertexColors ? h.vertexColors = THREE.FaceColors : a.vertexColors && (h.vertexColors = THREE.VertexColors)), a.colorDiffuse ? h.color = e(a.colorDiffuse) : a.DbgColor && (h.color = a.DbgColor), a.colorSpecular && (h.specular = e(a.colorSpecular)), a.colorEmissive && (h.emissive = e(a.colorEmissive)), void 0 !== a.transparency && (console.warn("THREE.Loader: transparency has been renamed to opacity"), a.opacity = a.transparency), void 0 !== a.opacity && (h.opacity = a.opacity), a.specularCoef && (h.shininess = a.specularCoef), a.mapDiffuse && b && d(h, "map", a.mapDiffuse, a.mapDiffuseRepeat, a.mapDiffuseOffset, a.mapDiffuseWrap, a.mapDiffuseAnisotropy), a.mapLight && b && d(h, "lightMap", a.mapLight, a.mapLightRepeat, a.mapLightOffset, a.mapLightWrap, a.mapLightAnisotropy), a.mapBump && b && d(h, "bumpMap", a.mapBump, a.mapBumpRepeat, a.mapBumpOffset, a.mapBumpWrap, a.mapBumpAnisotropy), a.mapNormal && b && d(h, "normalMap", a.mapNormal, a.mapNormalRepeat, a.mapNormalOffset, a.mapNormalWrap, a.mapNormalAnisotropy), a.mapSpecular && b && d(h, "specularMap", a.mapSpecular, a.mapSpecularRepeat, a.mapSpecularOffset, a.mapSpecularWrap, a.mapSpecularAnisotropy), a.mapAlpha && b && d(h, "alphaMap", a.mapAlpha, a.mapAlphaRepeat, a.mapAlphaOffset, a.mapAlphaWrap, a.mapAlphaAnisotropy), a.mapBumpScale && (h.bumpScale = a.mapBumpScale), a.mapNormalFactor && (h.normalScale = new THREE.Vector2(a.mapNormalFactor, a.mapNormalFactor)), g = new THREE[g](h), void 0 !== a.DbgName && (g.name = a.DbgName), g
    }
},THREE.Loader.Handlers = {
    handlers: [], add: function (a, b) {
        this.handlers.push(a, b)
    }, get: function (a) {
        for (var b = 0, c = this.handlers.length; c > b; b += 2) {
            var d = this.handlers[b + 1];
            if (this.handlers[b].test(a))return d
        }
        return null
    }
},THREE.XHRLoader = function (a) {
    this.manager = void 0 !== a ? a : THREE.DefaultLoadingManager
},THREE.XHRLoader.prototype = {
    constructor: THREE.XHRLoader, load: function (a, b, c, d) {
        var e = this, f = THREE.Cache.get(a);
        void 0 !== f ? b && b(f) : (f = new XMLHttpRequest, f.open("GET", a, !0), f.addEventListener("load", function (c) {
            THREE.Cache.add(a, this.response), b && b(this.response), e.manager.itemEnd(a)
        }, !1), void 0 !== c && f.addEventListener("progress", function (a) {
            c(a)
        }, !1), void 0 !== d && f.addEventListener("error", function (a) {
            d(a)
        }, !1), void 0 !== this.crossOrigin && (f.crossOrigin = this.crossOrigin), void 0 !== this.responseType && (f.responseType = this.responseType), f.send(null), e.manager.itemStart(a))
    }, setResponseType: function (a) {
        this.responseType = a
    }, setCrossOrigin: function (a) {
        this.crossOrigin = a
    }
},THREE.ImageLoader = function (a) {
    this.manager = void 0 !== a ? a : THREE.DefaultLoadingManager
},THREE.ImageLoader.prototype = {
    constructor: THREE.ImageLoader, load: function (a, b, c, d) {
        var e = this, f = THREE.Cache.get(a);
        return void 0 === f ? (f = document.createElement("img"), f.addEventListener("load", function (c) {
            THREE.Cache.add(a, this), b && b(this), e.manager.itemEnd(a)
        }, !1), void 0 !== c && f.addEventListener("progress", function (a) {
            c(a)
        }, !1), void 0 !== d && f.addEventListener("error", function (a) {
            d(a)
        }, !1), void 0 !== this.crossOrigin && (f.crossOrigin = this.crossOrigin), f.src = a, e.manager.itemStart(a), f) : void b(f)
    }, setCrossOrigin: function (a) {
        this.crossOrigin = a
    }
},THREE.JSONLoader = function (a) {
    THREE.Loader.call(this, a), this.withCredentials = !1
},THREE.JSONLoader.prototype = Object.create(THREE.Loader.prototype),THREE.JSONLoader.prototype.constructor = THREE.JSONLoader,THREE.JSONLoader.prototype.load = function (a, b, c) {
    c = c && "string" == typeof c ? c : this.extractUrlBase(a), this.onLoadStart(), this.loadAjaxJSON(this, a, b, c)
},THREE.JSONLoader.prototype.loadAjaxJSON = function (a, b, c, d, e) {
    var f = new XMLHttpRequest, g = 0;
    f.onreadystatechange = function () {
        if (f.readyState === f.DONE)if (200 === f.status || 0 === f.status) {
            if (f.responseText) {
                var h = JSON.parse(f.responseText), k = h.metadata;
                if (void 0 !== k) {
                    if ("object" === k.type)return void THREE.error("THREE.JSONLoader: " + b + " should be loaded with THREE.ObjectLoader instead.");
                    if ("scene" === k.type)return void THREE.error("THREE.JSONLoader: " + b + " seems to be a Scene. Use THREE.SceneLoader instead.")
                }
                h = a.parse(h, d), c(h.geometry, h.materials)
            } else THREE.error("THREE.JSONLoader: " + b + " seems to be unreachable or the file is empty.");
            a.onLoadComplete()
        } else THREE.error("THREE.JSONLoader: Couldn't load " + b + " (" + f.status + ")"); else f.readyState === f.LOADING ? e && (0 === g && (g = f.getResponseHeader("Content-Length")), e({
            total: g,
            loaded: f.responseText.length
        })) : f.readyState === f.HEADERS_RECEIVED && void 0 !== e && (g = f.getResponseHeader("Content-Length"))
    }, f.open("GET", b, !0), f.withCredentials = this.withCredentials, f.send(null)
},THREE.JSONLoader.prototype.parse = function (a, b) {
    var c = new THREE.Geometry, d = void 0 !== a.scale ? 1 / a.scale : 1;
    return function (b) {
        var d, g, h, k, l, p, q, n, t, r, s, u, v, x = a.faces;
        p = a.vertices;
        var D = a.normals, w = a.colors, y = 0;
        if (void 0 !== a.uvs) {
            for (d = 0; d < a.uvs.length; d++)a.uvs[d].length && y++;
            for (d = 0; y > d; d++)c.faceVertexUvs[d] = []
        }
        for (k = 0, l = p.length; l > k;)d = new THREE.Vector3, d.x = p[k++] * b, d.y = p[k++] * b, d.z = p[k++] * b, c.vertices.push(d);
        for (k = 0, l = x.length; l > k;)if (b = x[k++], t = 1 & b, h = 2 & b, d = 8 & b, q = 16 & b, r = 32 & b, p = 64 & b, b &= 128, t) {
            if (t = new THREE.Face3, t.a = x[k], t.b = x[k + 1], t.c = x[k + 3], s = new THREE.Face3, s.a = x[k + 1], s.b = x[k + 2], s.c = x[k + 3], k += 4, h && (h = x[k++], t.materialIndex = h, s.materialIndex = h), h = c.faces.length, d)for (d = 0; y > d; d++)for (u = a.uvs[d], c.faceVertexUvs[d][h] = [], c.faceVertexUvs[d][h + 1] = [], g = 0; 4 > g; g++)n = x[k++], v = u[2 * n], n = u[2 * n + 1], v = new THREE.Vector2(v, n), 2 !== g && c.faceVertexUvs[d][h].push(v), 0 !== g && c.faceVertexUvs[d][h + 1].push(v);
            if (q && (q = 3 * x[k++], t.normal.set(D[q++], D[q++], D[q]), s.normal.copy(t.normal)), r)for (d = 0; 4 > d; d++)q = 3 * x[k++], r = new THREE.Vector3(D[q++], D[q++], D[q]), 2 !== d && t.vertexNormals.push(r), 0 !== d && s.vertexNormals.push(r);
            if (p && (p = x[k++], p = w[p], t.color.setHex(p), s.color.setHex(p)), b)for (d = 0; 4 > d; d++)p = x[k++], p = w[p], 2 !== d && t.vertexColors.push(new THREE.Color(p)), 0 !== d && s.vertexColors.push(new THREE.Color(p));
            c.faces.push(t), c.faces.push(s)
        } else {
            if (t = new THREE.Face3, t.a = x[k++], t.b = x[k++], t.c = x[k++], h && (h = x[k++], t.materialIndex = h), h = c.faces.length, d)for (d = 0; y > d; d++)for (u = a.uvs[d], c.faceVertexUvs[d][h] = [], g = 0; 3 > g; g++)n = x[k++], v = u[2 * n], n = u[2 * n + 1], v = new THREE.Vector2(v, n), c.faceVertexUvs[d][h].push(v);
            if (q && (q = 3 * x[k++], t.normal.set(D[q++], D[q++], D[q])), r)for (d = 0; 3 > d; d++)q = 3 * x[k++], r = new THREE.Vector3(D[q++], D[q++], D[q]), t.vertexNormals.push(r);
            if (p && (p = x[k++], t.color.setHex(w[p])), b)for (d = 0; 3 > d; d++)p = x[k++], t.vertexColors.push(new THREE.Color(w[p]));
            c.faces.push(t)
        }
    }(d), function () {
        var b = void 0 !== a.influencesPerVertex ? a.influencesPerVertex : 2;
        if (a.skinWeights)for (var d = 0, g = a.skinWeights.length; g > d; d += b)c.skinWeights.push(new THREE.Vector4(a.skinWeights[d], b > 1 ? a.skinWeights[d + 1] : 0, b > 2 ? a.skinWeights[d + 2] : 0, b > 3 ? a.skinWeights[d + 3] : 0));
        if (a.skinIndices)for (d = 0, g = a.skinIndices.length; g > d; d += b)c.skinIndices.push(new THREE.Vector4(a.skinIndices[d], b > 1 ? a.skinIndices[d + 1] : 0, b > 2 ? a.skinIndices[d + 2] : 0, b > 3 ? a.skinIndices[d + 3] : 0));
        c.bones = a.bones, c.bones && 0 < c.bones.length && (c.skinWeights.length !== c.skinIndices.length || c.skinIndices.length !== c.vertices.length) && THREE.warn("THREE.JSONLoader: When skinning, number of vertices (" + c.vertices.length + "), skinIndices (" + c.skinIndices.length + "), and skinWeights (" + c.skinWeights.length + ") should match."), c.animation = a.animation, c.animations = a.animations
    }(), function (b) {
        if (void 0 !== a.morphTargets) {
            var d, g, h, k, l, p;
            for (d = 0, g = a.morphTargets.length; g > d; d++)for (c.morphTargets[d] = {}, c.morphTargets[d].name = a.morphTargets[d].name, c.morphTargets[d].vertices = [], l = c.morphTargets[d].vertices, p = a.morphTargets[d].vertices, h = 0, k = p.length; k > h; h += 3) {
                var q = new THREE.Vector3;
                q.x = p[h] * b, q.y = p[h + 1] * b, q.z = p[h + 2] * b, l.push(q)
            }
        }
        if (void 0 !== a.morphColors)for (d = 0, g = a.morphColors.length; g > d; d++)for (c.morphColors[d] = {}, c.morphColors[d].name = a.morphColors[d].name, c.morphColors[d].colors = [], k = c.morphColors[d].colors, l = a.morphColors[d].colors, b = 0, h = l.length; h > b; b += 3)p = new THREE.Color(16755200), p.setRGB(l[b], l[b + 1], l[b + 2]), k.push(p)
    }(d), c.computeFaceNormals(), c.computeBoundingSphere(), void 0 === a.materials || 0 === a.materials.length ? {geometry: c} : (d = this.initMaterials(a.materials, b), this.needsTangents(d) && c.computeTangents(), {
        geometry: c,
        materials: d
    })
},THREE.LoadingManager = function (a, b, c) {
    var d = this, e = 0, f = 0;
    this.onLoad = a, this.onProgress = b, this.onError = c, this.itemStart = function (a) {
        f++
    }, this.itemEnd = function (a) {
        e++, void 0 !== d.onProgress && d.onProgress(a, e, f), e === f && void 0 !== d.onLoad && d.onLoad()
    }
},THREE.DefaultLoadingManager = new THREE.LoadingManager,THREE.BufferGeometryLoader = function (a) {
    this.manager = void 0 !== a ? a : THREE.DefaultLoadingManager
},THREE.BufferGeometryLoader.prototype = {
    constructor: THREE.BufferGeometryLoader, load: function (a, b, c, d) {
        var e = this, f = new THREE.XHRLoader(e.manager);
        f.setCrossOrigin(this.crossOrigin), f.load(a, function (a) {
            b(e.parse(JSON.parse(a)))
        }, c, d)
    }, setCrossOrigin: function (a) {
        this.crossOrigin = a
    }, parse: function (a) {
        var d, b = new THREE.BufferGeometry, c = a.data.attributes;
        for (d in c) {
            var e = c[d], f = new self[e.type](e.array);
            b.addAttribute(d, new THREE.BufferAttribute(f, e.itemSize))
        }
        return c = a.data.offsets, void 0 !== c && (b.offsets = JSON.parse(JSON.stringify(c))), a = a.data.boundingSphere, void 0 !== a && (c = new THREE.Vector3, void 0 !== a.center && c.fromArray(a.center), b.boundingSphere = new THREE.Sphere(c, a.radius)), b
    }
},THREE.MaterialLoader = function (a) {
    this.manager = void 0 !== a ? a : THREE.DefaultLoadingManager
},THREE.MaterialLoader.prototype = {
    constructor: THREE.MaterialLoader, load: function (a, b, c, d) {
        var e = this, f = new THREE.XHRLoader(e.manager);
        f.setCrossOrigin(this.crossOrigin), f.load(a, function (a) {
            b(e.parse(JSON.parse(a)))
        }, c, d)
    }, setCrossOrigin: function (a) {
        this.crossOrigin = a
    }, parse: function (a) {
        var b = new THREE[a.type];
        if (void 0 !== a.color && b.color.setHex(a.color), void 0 !== a.emissive && b.emissive.setHex(a.emissive), void 0 !== a.specular && b.specular.setHex(a.specular), void 0 !== a.shininess && (b.shininess = a.shininess), void 0 !== a.uniforms && (b.uniforms = a.uniforms), void 0 !== a.vertexShader && (b.vertexShader = a.vertexShader), void 0 !== a.fragmentShader && (b.fragmentShader = a.fragmentShader), void 0 !== a.vertexColors && (b.vertexColors = a.vertexColors), void 0 !== a.shading && (b.shading = a.shading), void 0 !== a.blending && (b.blending = a.blending), void 0 !== a.side && (b.side = a.side), void 0 !== a.opacity && (b.opacity = a.opacity), void 0 !== a.transparent && (b.transparent = a.transparent), void 0 !== a.wireframe && (b.wireframe = a.wireframe), void 0 !== a.size && (b.size = a.size), void 0 !== a.sizeAttenuation && (b.sizeAttenuation = a.sizeAttenuation), void 0 !== a.materials)for (var c = 0, d = a.materials.length; d > c; c++)b.materials.push(this.parse(a.materials[c]));
        return b
    }
},THREE.ObjectLoader = function (a) {
    this.manager = void 0 !== a ? a : THREE.DefaultLoadingManager, this.texturePath = ""
},THREE.ObjectLoader.prototype = {
    constructor: THREE.ObjectLoader, load: function (a, b, c, d) {
        "" === this.texturePath && (this.texturePath = a.substring(0, a.lastIndexOf("/") + 1));
        var e = this, f = new THREE.XHRLoader(e.manager);
        f.setCrossOrigin(this.crossOrigin), f.load(a, function (a) {
            e.parse(JSON.parse(a), b)
        }, c, d)
    }, setTexturePath: function (a) {
        this.texturePath = a
    }, setCrossOrigin: function (a) {
        this.crossOrigin = a
    }, parse: function (a, b) {
        var c = this.parseGeometries(a.geometries), d = this.parseImages(a.images, function () {
            void 0 !== b && b(e)
        }), d = this.parseTextures(a.textures, d), d = this.parseMaterials(a.materials, d), e = this.parseObject(a.object, c, d);
        return void 0 !== a.images && 0 !== a.images.length || void 0 === b || b(e), e
    }, parseGeometries: function (a) {
        var b = {};
        if (void 0 !== a)for (var c = new THREE.JSONLoader, d = new THREE.BufferGeometryLoader, e = 0, f = a.length; f > e; e++) {
            var g, h = a[e];
            switch (h.type) {
                case"PlaneGeometry":
                case"PlaneBufferGeometry":
                    g = new THREE[h.type](h.width, h.height, h.widthSegments, h.heightSegments);
                    break;
                case"BoxGeometry":
                case"CubeGeometry":
                    g = new THREE.BoxGeometry(h.width, h.height, h.depth, h.widthSegments, h.heightSegments, h.depthSegments);
                    break;
                case"CircleGeometry":
                    g = new THREE.CircleGeometry(h.radius, h.segments);
                    break;
                case"CylinderGeometry":
                    g = new THREE.CylinderGeometry(h.radiusTop, h.radiusBottom, h.height, h.radialSegments, h.heightSegments, h.openEnded);
                    break;
                case"SphereGeometry":
                    g = new THREE.SphereGeometry(h.radius, h.widthSegments, h.heightSegments, h.phiStart, h.phiLength, h.thetaStart, h.thetaLength);
                    break;
                case"IcosahedronGeometry":
                    g = new THREE.IcosahedronGeometry(h.radius, h.detail);
                    break;
                case"TorusGeometry":
                    g = new THREE.TorusGeometry(h.radius, h.tube, h.radialSegments, h.tubularSegments, h.arc);
                    break;
                case"TorusKnotGeometry":
                    g = new THREE.TorusKnotGeometry(h.radius, h.tube, h.radialSegments, h.tubularSegments, h.p, h.q, h.heightScale);
                    break;
                case"BufferGeometry":
                    g = d.parse(h);
                    break;
                case"Geometry":
                    g = c.parse(h.data).geometry
            }
            g.uuid = h.uuid, void 0 !== h.name && (g.name = h.name), b[h.uuid] = g
        }
        return b
    }, parseMaterials: function (a, b) {
        var c = {};
        if (void 0 !== a)for (var d = function (a) {
            return void 0 === b[a] && THREE.warn("THREE.ObjectLoader: Undefined texture", a), b[a]
        }, e = new THREE.MaterialLoader, f = 0, g = a.length; g > f; f++) {
            var h = a[f], k = e.parse(h);
            k.uuid = h.uuid, void 0 !== h.name && (k.name = h.name), void 0 !== h.map && (k.map = d(h.map)), void 0 !== h.bumpMap && (k.bumpMap = d(h.bumpMap), h.bumpScale && (k.bumpScale = new THREE.Vector2(h.bumpScale, h.bumpScale))), void 0 !== h.alphaMap && (k.alphaMap = d(h.alphaMap)), void 0 !== h.envMap && (k.envMap = d(h.envMap)), void 0 !== h.normalMap && (k.normalMap = d(h.normalMap), h.normalScale && (k.normalScale = new THREE.Vector2(h.normalScale, h.normalScale))), void 0 !== h.lightMap && (k.lightMap = d(h.lightMap)), void 0 !== h.specularMap && (k.specularMap = d(h.specularMap)), c[h.uuid] = k
        }
        return c
    }, parseImages: function (a, b) {
        var c = this, d = {};
        if (void 0 !== a && 0 < a.length) {
            var e = new THREE.LoadingManager(b), f = new THREE.ImageLoader(e);
            f.setCrossOrigin(this.crossOrigin);
            for (var e = function (a) {
                return c.manager.itemStart(a), f.load(a, function () {
                    c.manager.itemEnd(a)
                })
            }, g = 0, h = a.length; h > g; g++) {
                var k = a[g], l = /^(\/\/)|([a-z]+:(\/\/)?)/i.test(k.url) ? k.url : c.texturePath + k.url;
                d[k.uuid] = e(l)
            }
        }
        return d
    }, parseTextures: function (a, b) {
        var c = {};
        if (void 0 !== a)for (var d = 0, e = a.length; e > d; d++) {
            var f = a[d];
            void 0 === f.image && THREE.warn('THREE.ObjectLoader: No "image" speficied for', f.uuid), void 0 === b[f.image] && THREE.warn("THREE.ObjectLoader: Undefined image", f.image);
            var g = new THREE.Texture(b[f.image]);
            g.needsUpdate = !0, g.uuid = f.uuid, void 0 !== f.name && (g.name = f.name), void 0 !== f.repeat && (g.repeat = new THREE.Vector2(f.repeat[0], f.repeat[1])), void 0 !== f.minFilter && (g.minFilter = THREE[f.minFilter]), void 0 !== f.magFilter && (g.magFilter = THREE[f.magFilter]), void 0 !== f.anisotropy && (g.anisotropy = f.anisotropy), f.wrap instanceof Array && (g.wrapS = THREE[f.wrap[0]], g.wrapT = THREE[f.wrap[1]]), c[f.uuid] = g
        }
        return c
    }, parseObject: function () {
        var a = new THREE.Matrix4;
        return function (b, c, d) {
            var e;
            e = function (a) {
                return void 0 === c[a] && THREE.warn("THREE.ObjectLoader: Undefined geometry", a), c[a]
            };
            var f = function (a) {
                return void 0 === d[a] && THREE.warn("THREE.ObjectLoader: Undefined material", a), d[a]
            };
            switch (b.type) {
                case"Scene":
                    e = new THREE.Scene;
                    break;
                case"PerspectiveCamera":
                    e = new THREE.PerspectiveCamera(b.fov, b.aspect, b.near, b.far);
                    break;
                case"OrthographicCamera":
                    e = new THREE.OrthographicCamera(b.left, b.right, b.top, b.bottom, b.near, b.far);
                    break;
                case"AmbientLight":
                    e = new THREE.AmbientLight(b.color);
                    break;
                case"DirectionalLight":
                    e = new THREE.DirectionalLight(b.color, b.intensity);
                    break;
                case"PointLight":
                    e = new THREE.PointLight(b.color, b.intensity, b.distance, b.decay);
                    break;
                case"SpotLight":
                    e = new THREE.SpotLight(b.color, b.intensity, b.distance, b.angle, b.exponent, b.decay);
                    break;
                case"HemisphereLight":
                    e = new THREE.HemisphereLight(b.color, b.groundColor, b.intensity);
                    break;
                case"Mesh":
                    e = new THREE.Mesh(e(b.geometry), f(b.material));
                    break;
                case"Line":
                    e = new THREE.Line(e(b.geometry), f(b.material), b.mode);
                    break;
                case"PointCloud":
                    e = new THREE.PointCloud(e(b.geometry), f(b.material));
                    break;
                case"Sprite":
                    e = new THREE.Sprite(f(b.material));
                    break;
                case"Group":
                    e = new THREE.Group;
                    break;
                default:
                    e = new THREE.Object3D
            }
            if (e.uuid = b.uuid, void 0 !== b.name && (e.name = b.name), void 0 !== b.matrix ? (a.fromArray(b.matrix), a.decompose(e.position, e.quaternion, e.scale)) : (void 0 !== b.position && e.position.fromArray(b.position), void 0 !== b.rotation && e.rotation.fromArray(b.rotation), void 0 !== b.scale && e.scale.fromArray(b.scale)), void 0 !== b.visible && (e.visible = b.visible), void 0 !== b.userData && (e.userData = b.userData), void 0 !== b.children)for (var g in b.children)e.add(this.parseObject(b.children[g], c, d));
            return e
        }
    }()
},THREE.TextureLoader = function (a) {
    this.manager = void 0 !== a ? a : THREE.DefaultLoadingManager
},THREE.TextureLoader.prototype = {
    constructor: THREE.TextureLoader, load: function (a, b, c, d) {
        var e = new THREE.ImageLoader(this.manager);
        e.setCrossOrigin(this.crossOrigin), e.load(a, function (a) {
            a = new THREE.Texture(a), a.needsUpdate = !0, void 0 !== b && b(a)
        }, c, d)
    }, setCrossOrigin: function (a) {
        this.crossOrigin = a
    }
},THREE.DataTextureLoader = THREE.BinaryTextureLoader = function () {
    this._parser = null
},THREE.BinaryTextureLoader.prototype = {
    constructor: THREE.BinaryTextureLoader, load: function (a, b, c, d) {
        var e = this, f = new THREE.DataTexture, g = new THREE.XHRLoader;
        return g.setResponseType("arraybuffer"), g.load(a, function (a) {
            (a = e._parser(a)) && (void 0 !== a.image ? f.image = a.image : void 0 !== a.data && (f.image.width = a.width, f.image.height = a.height, f.image.data = a.data), f.wrapS = void 0 !== a.wrapS ? a.wrapS : THREE.ClampToEdgeWrapping, f.wrapT = void 0 !== a.wrapT ? a.wrapT : THREE.ClampToEdgeWrapping, f.magFilter = void 0 !== a.magFilter ? a.magFilter : THREE.LinearFilter, f.minFilter = void 0 !== a.minFilter ? a.minFilter : THREE.LinearMipMapLinearFilter, f.anisotropy = void 0 !== a.anisotropy ? a.anisotropy : 1, void 0 !== a.format && (f.format = a.format), void 0 !== a.type && (f.type = a.type), void 0 !== a.mipmaps && (f.mipmaps = a.mipmaps), 1 === a.mipmapCount && (f.minFilter = THREE.LinearFilter), f.needsUpdate = !0, b && b(f, a))
        }, c, d), f
    }
},THREE.CompressedTextureLoader = function () {
    this._parser = null
},THREE.CompressedTextureLoader.prototype = {
    constructor: THREE.CompressedTextureLoader, load: function (a, b, c) {
        var d = this, e = [], f = new THREE.CompressedTexture;
        f.image = e;
        var g = new THREE.XHRLoader;
        if (g.setResponseType("arraybuffer"), a instanceof Array) {
            var h = 0;
            c = function (c) {
                g.load(a[c], function (a) {
                    a = d._parser(a, !0), e[c] = {
                        width: a.width,
                        height: a.height,
                        format: a.format,
                        mipmaps: a.mipmaps
                    }, h += 1, 6 === h && (1 == a.mipmapCount && (f.minFilter = THREE.LinearFilter), f.format = a.format, f.needsUpdate = !0, b && b(f))
                })
            };
            for (var k = 0, l = a.length; l > k; ++k)c(k)
        } else g.load(a, function (a) {
            if (a = d._parser(a, !0), a.isCubemap)for (var c = a.mipmaps.length / a.mipmapCount, g = 0; c > g; g++) {
                e[g] = {mipmaps: []};
                for (var h = 0; h < a.mipmapCount; h++)e[g].mipmaps.push(a.mipmaps[g * a.mipmapCount + h]), e[g].format = a.format, e[g].width = a.width, e[g].height = a.height
            } else f.image.width = a.width, f.image.height = a.height, f.mipmaps = a.mipmaps;
            1 === a.mipmapCount && (f.minFilter = THREE.LinearFilter), f.format = a.format, f.needsUpdate = !0, b && b(f)
        });
        return f
    }
},THREE.Material = function () {
    Object.defineProperty(this, "id", {value: THREE.MaterialIdCount++}), this.uuid = THREE.Math.generateUUID(), this.name = "", this.type = "Material", this.side = THREE.FrontSide, this.opacity = 1, this.transparent = !1, this.blending = THREE.NormalBlending, this.blendSrc = THREE.SrcAlphaFactor, this.blendDst = THREE.OneMinusSrcAlphaFactor, this.blendEquation = THREE.AddEquation, this.blendEquationAlpha = this.blendDstAlpha = this.blendSrcAlpha = null, this.colorWrite = this.depthWrite = this.depthTest = !0, this.polygonOffset = !1, this.overdraw = this.alphaTest = this.polygonOffsetUnits = this.polygonOffsetFactor = 0, this._needsUpdate = this.visible = !0
},THREE.Material.prototype = {
    constructor: THREE.Material, get needsUpdate() {
        return this._needsUpdate
    }, set needsUpdate(a) {
        !0 === a && this.update(), this._needsUpdate = a
    }, setValues: function (a) {
        if (void 0 !== a)for (var b in a) {
            var c = a[b];
            if (void 0 === c)THREE.warn("THREE.Material: '" + b + "' parameter is undefined."); else if (b in this) {
                var d = this[b];
                d instanceof THREE.Color ? d.set(c) : d instanceof THREE.Vector3 && c instanceof THREE.Vector3 ? d.copy(c) : this[b] = "overdraw" == b ? Number(c) : c
            }
        }
    }, toJSON: function () {
        var a = {
            metadata: {version: 4.2, type: "material", generator: "MaterialExporter"},
            uuid: this.uuid,
            type: this.type
        };
        return "" !== this.name && (a.name = this.name), this instanceof THREE.MeshBasicMaterial ? (a.color = this.color.getHex(), this.vertexColors !== THREE.NoColors && (a.vertexColors = this.vertexColors), this.blending !== THREE.NormalBlending && (a.blending = this.blending), this.side !== THREE.FrontSide && (a.side = this.side)) : this instanceof THREE.MeshLambertMaterial ? (a.color = this.color.getHex(), a.emissive = this.emissive.getHex(), this.vertexColors !== THREE.NoColors && (a.vertexColors = this.vertexColors), this.shading !== THREE.SmoothShading && (a.shading = this.shading), this.blending !== THREE.NormalBlending && (a.blending = this.blending), this.side !== THREE.FrontSide && (a.side = this.side)) : this instanceof THREE.MeshPhongMaterial ? (a.color = this.color.getHex(), a.emissive = this.emissive.getHex(), a.specular = this.specular.getHex(), a.shininess = this.shininess, this.vertexColors !== THREE.NoColors && (a.vertexColors = this.vertexColors), this.shading !== THREE.SmoothShading && (a.shading = this.shading), this.blending !== THREE.NormalBlending && (a.blending = this.blending), this.side !== THREE.FrontSide && (a.side = this.side)) : this instanceof THREE.MeshNormalMaterial ? (this.blending !== THREE.NormalBlending && (a.blending = this.blending), this.side !== THREE.FrontSide && (a.side = this.side)) : this instanceof THREE.MeshDepthMaterial ? (this.blending !== THREE.NormalBlending && (a.blending = this.blending), this.side !== THREE.FrontSide && (a.side = this.side)) : this instanceof THREE.PointCloudMaterial ? (a.size = this.size, a.sizeAttenuation = this.sizeAttenuation, a.color = this.color.getHex(), this.vertexColors !== THREE.NoColors && (a.vertexColors = this.vertexColors), this.blending !== THREE.NormalBlending && (a.blending = this.blending)) : this instanceof THREE.ShaderMaterial ? (a.uniforms = this.uniforms, a.vertexShader = this.vertexShader, a.fragmentShader = this.fragmentShader) : this instanceof THREE.SpriteMaterial && (a.color = this.color.getHex()), 1 > this.opacity && (a.opacity = this.opacity), !1 !== this.transparent && (a.transparent = this.transparent), !1 !== this.wireframe && (a.wireframe = this.wireframe), a
    }, clone: function (a) {
        return void 0 === a && (a = new THREE.Material), a.name = this.name, a.side = this.side, a.opacity = this.opacity, a.transparent = this.transparent, a.blending = this.blending, a.blendSrc = this.blendSrc, a.blendDst = this.blendDst, a.blendEquation = this.blendEquation, a.blendSrcAlpha = this.blendSrcAlpha, a.blendDstAlpha = this.blendDstAlpha, a.blendEquationAlpha = this.blendEquationAlpha, a.depthTest = this.depthTest, a.depthWrite = this.depthWrite, a.polygonOffset = this.polygonOffset, a.polygonOffsetFactor = this.polygonOffsetFactor, a.polygonOffsetUnits = this.polygonOffsetUnits, a.alphaTest = this.alphaTest, a.overdraw = this.overdraw, a.visible = this.visible, a
    }, update: function () {
        this.dispatchEvent({type: "update"})
    }, dispose: function () {
        this.dispatchEvent({type: "dispose"})
    }
},THREE.EventDispatcher.prototype.apply(THREE.Material.prototype),THREE.MaterialIdCount = 0,THREE.LineBasicMaterial = function (a) {
    THREE.Material.call(this), this.type = "LineBasicMaterial", this.color = new THREE.Color(16777215), this.linewidth = 1, this.linejoin = this.linecap = "round", this.vertexColors = THREE.NoColors, this.fog = !0, this.setValues(a)
},THREE.LineBasicMaterial.prototype = Object.create(THREE.Material.prototype),THREE.LineBasicMaterial.prototype.constructor = THREE.LineBasicMaterial,THREE.LineBasicMaterial.prototype.clone = function () {
    var a = new THREE.LineBasicMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.color.copy(this.color), a.linewidth = this.linewidth, a.linecap = this.linecap, a.linejoin = this.linejoin, a.vertexColors = this.vertexColors, a.fog = this.fog, a
},THREE.LineDashedMaterial = function (a) {
    THREE.Material.call(this), this.type = "LineDashedMaterial", this.color = new THREE.Color(16777215), this.scale = this.linewidth = 1, this.dashSize = 3, this.gapSize = 1, this.vertexColors = !1, this.fog = !0, this.setValues(a)
},THREE.LineDashedMaterial.prototype = Object.create(THREE.Material.prototype),THREE.LineDashedMaterial.prototype.constructor = THREE.LineDashedMaterial,THREE.LineDashedMaterial.prototype.clone = function () {
    var a = new THREE.LineDashedMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.color.copy(this.color), a.linewidth = this.linewidth, a.scale = this.scale, a.dashSize = this.dashSize, a.gapSize = this.gapSize, a.vertexColors = this.vertexColors, a.fog = this.fog, a
},THREE.MeshBasicMaterial = function (a) {
    THREE.Material.call(this), this.type = "MeshBasicMaterial", this.color = new THREE.Color(16777215), this.envMap = this.alphaMap = this.specularMap = this.lightMap = this.map = null, this.combine = THREE.MultiplyOperation, this.reflectivity = 1, this.refractionRatio = .98, this.fog = !0, this.shading = THREE.SmoothShading, this.wireframe = !1, this.wireframeLinewidth = 1, this.wireframeLinejoin = this.wireframeLinecap = "round", this.vertexColors = THREE.NoColors, this.morphTargets = this.skinning = !1, this.setValues(a)
},THREE.MeshBasicMaterial.prototype = Object.create(THREE.Material.prototype),THREE.MeshBasicMaterial.prototype.constructor = THREE.MeshBasicMaterial,THREE.MeshBasicMaterial.prototype.clone = function () {
    var a = new THREE.MeshBasicMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.color.copy(this.color), a.map = this.map, a.lightMap = this.lightMap, a.specularMap = this.specularMap, a.alphaMap = this.alphaMap, a.envMap = this.envMap, a.combine = this.combine, a.reflectivity = this.reflectivity, a.refractionRatio = this.refractionRatio, a.fog = this.fog, a.shading = this.shading, a.wireframe = this.wireframe, a.wireframeLinewidth = this.wireframeLinewidth, a.wireframeLinecap = this.wireframeLinecap, a.wireframeLinejoin = this.wireframeLinejoin, a.vertexColors = this.vertexColors, a.skinning = this.skinning, a.morphTargets = this.morphTargets, a
},THREE.MeshLambertMaterial = function (a) {
    THREE.Material.call(this), this.type = "MeshLambertMaterial", this.color = new THREE.Color(16777215), this.emissive = new THREE.Color(0), this.wrapAround = !1, this.wrapRGB = new THREE.Vector3(1, 1, 1), this.envMap = this.alphaMap = this.specularMap = this.lightMap = this.map = null, this.combine = THREE.MultiplyOperation, this.reflectivity = 1, this.refractionRatio = .98, this.fog = !0, this.shading = THREE.SmoothShading, this.wireframe = !1, this.wireframeLinewidth = 1, this.wireframeLinejoin = this.wireframeLinecap = "round", this.vertexColors = THREE.NoColors, this.morphNormals = this.morphTargets = this.skinning = !1, this.setValues(a)
},THREE.MeshLambertMaterial.prototype = Object.create(THREE.Material.prototype),THREE.MeshLambertMaterial.prototype.constructor = THREE.MeshLambertMaterial,THREE.MeshLambertMaterial.prototype.clone = function () {
    var a = new THREE.MeshLambertMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.color.copy(this.color), a.emissive.copy(this.emissive), a.wrapAround = this.wrapAround, a.wrapRGB.copy(this.wrapRGB), a.map = this.map, a.lightMap = this.lightMap, a.specularMap = this.specularMap, a.alphaMap = this.alphaMap, a.envMap = this.envMap, a.combine = this.combine, a.reflectivity = this.reflectivity, a.refractionRatio = this.refractionRatio, a.fog = this.fog, a.shading = this.shading, a.wireframe = this.wireframe, a.wireframeLinewidth = this.wireframeLinewidth, a.wireframeLinecap = this.wireframeLinecap, a.wireframeLinejoin = this.wireframeLinejoin, a.vertexColors = this.vertexColors, a.skinning = this.skinning, a.morphTargets = this.morphTargets, a.morphNormals = this.morphNormals, a
},THREE.MeshPhongMaterial = function (a) {
    THREE.Material.call(this), this.type = "MeshPhongMaterial", this.color = new THREE.Color(16777215), this.emissive = new THREE.Color(0), this.specular = new THREE.Color(1118481), this.shininess = 30, this.wrapAround = this.metal = !1, this.wrapRGB = new THREE.Vector3(1, 1, 1), this.bumpMap = this.lightMap = this.map = null, this.bumpScale = 1, this.normalMap = null, this.normalScale = new THREE.Vector2(1, 1), this.envMap = this.alphaMap = this.specularMap = null, this.combine = THREE.MultiplyOperation, this.reflectivity = 1, this.refractionRatio = .98, this.fog = !0, this.shading = THREE.SmoothShading, this.wireframe = !1, this.wireframeLinewidth = 1, this.wireframeLinejoin = this.wireframeLinecap = "round", this.vertexColors = THREE.NoColors, this.morphNormals = this.morphTargets = this.skinning = !1, this.setValues(a)
},THREE.MeshPhongMaterial.prototype = Object.create(THREE.Material.prototype),THREE.MeshPhongMaterial.prototype.constructor = THREE.MeshPhongMaterial,THREE.MeshPhongMaterial.prototype.clone = function () {
    var a = new THREE.MeshPhongMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.color.copy(this.color), a.emissive.copy(this.emissive), a.specular.copy(this.specular), a.shininess = this.shininess, a.metal = this.metal, a.wrapAround = this.wrapAround, a.wrapRGB.copy(this.wrapRGB), a.map = this.map, a.lightMap = this.lightMap, a.bumpMap = this.bumpMap, a.bumpScale = this.bumpScale, a.normalMap = this.normalMap, a.normalScale.copy(this.normalScale), a.specularMap = this.specularMap, a.alphaMap = this.alphaMap, a.envMap = this.envMap, a.combine = this.combine, a.reflectivity = this.reflectivity, a.refractionRatio = this.refractionRatio, a.fog = this.fog, a.shading = this.shading, a.wireframe = this.wireframe, a.wireframeLinewidth = this.wireframeLinewidth, a.wireframeLinecap = this.wireframeLinecap, a.wireframeLinejoin = this.wireframeLinejoin, a.vertexColors = this.vertexColors, a.skinning = this.skinning, a.morphTargets = this.morphTargets, a.morphNormals = this.morphNormals, a
},THREE.MeshDepthMaterial = function (a) {
    THREE.Material.call(this), this.type = "MeshDepthMaterial", this.wireframe = this.morphTargets = !1, this.wireframeLinewidth = 1, this.setValues(a)
},THREE.MeshDepthMaterial.prototype = Object.create(THREE.Material.prototype),THREE.MeshDepthMaterial.prototype.constructor = THREE.MeshDepthMaterial,THREE.MeshDepthMaterial.prototype.clone = function () {
    var a = new THREE.MeshDepthMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.wireframe = this.wireframe, a.wireframeLinewidth = this.wireframeLinewidth, a
},THREE.MeshNormalMaterial = function (a) {
    THREE.Material.call(this, a), this.type = "MeshNormalMaterial", this.wireframe = !1, this.wireframeLinewidth = 1, this.morphTargets = !1, this.setValues(a)
},THREE.MeshNormalMaterial.prototype = Object.create(THREE.Material.prototype),THREE.MeshNormalMaterial.prototype.constructor = THREE.MeshNormalMaterial,THREE.MeshNormalMaterial.prototype.clone = function () {
    var a = new THREE.MeshNormalMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.wireframe = this.wireframe, a.wireframeLinewidth = this.wireframeLinewidth, a
},THREE.MeshFaceMaterial = function (a) {
    this.uuid = THREE.Math.generateUUID(), this.type = "MeshFaceMaterial", this.materials = a instanceof Array ? a : []
},THREE.MeshFaceMaterial.prototype = {
    constructor: THREE.MeshFaceMaterial, toJSON: function () {
        for (var a = {
            metadata: {version: 4.2, type: "material", generator: "MaterialExporter"},
            uuid: this.uuid,
            type: this.type,
            materials: []
        }, b = 0, c = this.materials.length; c > b; b++)a.materials.push(this.materials[b].toJSON());
        return a
    }, clone: function () {
        for (var a = new THREE.MeshFaceMaterial, b = 0; b < this.materials.length; b++)a.materials.push(this.materials[b].clone());
        return a
    }
},THREE.PointCloudMaterial = function (a) {
    THREE.Material.call(this), this.type = "PointCloudMaterial", this.color = new THREE.Color(16777215), this.map = null, this.size = 1, this.sizeAttenuation = !0, this.vertexColors = THREE.NoColors, this.fog = !0, this.setValues(a)
},THREE.PointCloudMaterial.prototype = Object.create(THREE.Material.prototype),THREE.PointCloudMaterial.prototype.constructor = THREE.PointCloudMaterial,THREE.PointCloudMaterial.prototype.clone = function () {
    var a = new THREE.PointCloudMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.color.copy(this.color), a.map = this.map, a.size = this.size, a.sizeAttenuation = this.sizeAttenuation, a.vertexColors = this.vertexColors, a.fog = this.fog, a
},THREE.ParticleBasicMaterial = function (a) {
    return THREE.warn("THREE.ParticleBasicMaterial has been renamed to THREE.PointCloudMaterial."), new THREE.PointCloudMaterial(a)
},THREE.ParticleSystemMaterial = function (a) {
    return THREE.warn("THREE.ParticleSystemMaterial has been renamed to THREE.PointCloudMaterial."), new THREE.PointCloudMaterial(a)
},THREE.ShaderMaterial = function (a) {
    THREE.Material.call(this), this.type = "ShaderMaterial", this.defines = {}, this.uniforms = {}, this.attributes = null, this.vertexShader = "void main() {\n	gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );\n}", this.fragmentShader = "void main() {\n	gl_FragColor = vec4( 1.0, 0.0, 0.0, 1.0 );\n}", this.shading = THREE.SmoothShading, this.linewidth = 1, this.wireframe = !1, this.wireframeLinewidth = 1, this.lights = this.fog = !1, this.vertexColors = THREE.NoColors, this.morphNormals = this.morphTargets = this.skinning = !1, this.defaultAttributeValues = {
        color: [1, 1, 1],
        uv: [0, 0],
        uv2: [0, 0]
    }, this.index0AttributeName = void 0, this.setValues(a)
},THREE.ShaderMaterial.prototype = Object.create(THREE.Material.prototype),THREE.ShaderMaterial.prototype.constructor = THREE.ShaderMaterial,THREE.ShaderMaterial.prototype.clone = function () {
    var a = new THREE.ShaderMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.fragmentShader = this.fragmentShader, a.vertexShader = this.vertexShader, a.uniforms = THREE.UniformsUtils.clone(this.uniforms), a.attributes = this.attributes, a.defines = this.defines, a.shading = this.shading, a.wireframe = this.wireframe, a.wireframeLinewidth = this.wireframeLinewidth, a.fog = this.fog, a.lights = this.lights, a.vertexColors = this.vertexColors, a.skinning = this.skinning, a.morphTargets = this.morphTargets, a.morphNormals = this.morphNormals, a
},THREE.RawShaderMaterial = function (a) {
    THREE.ShaderMaterial.call(this, a), this.type = "RawShaderMaterial"
},THREE.RawShaderMaterial.prototype = Object.create(THREE.ShaderMaterial.prototype),THREE.RawShaderMaterial.prototype.constructor = THREE.RawShaderMaterial,THREE.RawShaderMaterial.prototype.clone = function () {
    var a = new THREE.RawShaderMaterial;
    return THREE.ShaderMaterial.prototype.clone.call(this, a), a
},THREE.SpriteMaterial = function (a) {
    THREE.Material.call(this), this.type = "SpriteMaterial", this.color = new THREE.Color(16777215), this.map = null, this.rotation = 0, this.fog = !1, this.setValues(a)
},THREE.SpriteMaterial.prototype = Object.create(THREE.Material.prototype),THREE.SpriteMaterial.prototype.constructor = THREE.SpriteMaterial,THREE.SpriteMaterial.prototype.clone = function () {
    var a = new THREE.SpriteMaterial;
    return THREE.Material.prototype.clone.call(this, a), a.color.copy(this.color), a.map = this.map, a.rotation = this.rotation, a.fog = this.fog, a
},THREE.Texture = function (a, b, c, d, e, f, g, h, k) {
    Object.defineProperty(this, "id", {value: THREE.TextureIdCount++}), this.uuid = THREE.Math.generateUUID(), this.sourceFile = this.name = "", this.image = void 0 !== a ? a : THREE.Texture.DEFAULT_IMAGE, this.mipmaps = [], this.mapping = void 0 !== b ? b : THREE.Texture.DEFAULT_MAPPING, this.wrapS = void 0 !== c ? c : THREE.ClampToEdgeWrapping, this.wrapT = void 0 !== d ? d : THREE.ClampToEdgeWrapping, this.magFilter = void 0 !== e ? e : THREE.LinearFilter, this.minFilter = void 0 !== f ? f : THREE.LinearMipMapLinearFilter, this.anisotropy = void 0 !== k ? k : 1, this.format = void 0 !== g ? g : THREE.RGBAFormat, this.type = void 0 !== h ? h : THREE.UnsignedByteType, this.offset = new THREE.Vector2(0, 0), this.repeat = new THREE.Vector2(1, 1), this.generateMipmaps = !0, this.premultiplyAlpha = !1, this.flipY = !0, this.unpackAlignment = 4, this._needsUpdate = !1, this.onUpdate = null
},THREE.Texture.DEFAULT_IMAGE = void 0,THREE.Texture.DEFAULT_MAPPING = THREE.UVMapping,THREE.Texture.prototype = {
    constructor: THREE.Texture,
    get needsUpdate() {
        return this._needsUpdate
    },
    set needsUpdate(a) {
        !0 === a && this.update(), this._needsUpdate = a
    },
    clone: function (a) {
        return void 0 === a && (a = new THREE.Texture), a.image = this.image, a.mipmaps = this.mipmaps.slice(0), a.mapping = this.mapping, a.wrapS = this.wrapS, a.wrapT = this.wrapT, a.magFilter = this.magFilter, a.minFilter = this.minFilter, a.anisotropy = this.anisotropy, a.format = this.format, a.type = this.type, a.offset.copy(this.offset), a.repeat.copy(this.repeat), a.generateMipmaps = this.generateMipmaps, a.premultiplyAlpha = this.premultiplyAlpha, a.flipY = this.flipY, a.unpackAlignment = this.unpackAlignment, a
    },
    update: function () {
        this.dispatchEvent({type: "update"})
    },
    dispose: function () {
        this.dispatchEvent({type: "dispose"})
    }
},THREE.EventDispatcher.prototype.apply(THREE.Texture.prototype),THREE.TextureIdCount = 0,THREE.CubeTexture = function (a, b, c, d, e, f, g, h, k) {
    b = void 0 !== b ? b : THREE.CubeReflectionMapping, THREE.Texture.call(this, a, b, c, d, e, f, g, h, k), this.images = a
},THREE.CubeTexture.prototype = Object.create(THREE.Texture.prototype),THREE.CubeTexture.prototype.constructor = THREE.CubeTexture,THREE.CubeTexture.clone = function (a) {
    return void 0 === a && (a = new THREE.CubeTexture), THREE.Texture.prototype.clone.call(this, a), a.images = this.images, a
},THREE.CompressedTexture = function (a, b, c, d, e, f, g, h, k, l, p) {
    THREE.Texture.call(this, null, f, g, h, k, l, d, e, p), this.image = {
        width: b,
        height: c
    }, this.mipmaps = a, this.generateMipmaps = this.flipY = !1
},THREE.CompressedTexture.prototype = Object.create(THREE.Texture.prototype),THREE.CompressedTexture.prototype.constructor = THREE.CompressedTexture,THREE.CompressedTexture.prototype.clone = function () {
    var a = new THREE.CompressedTexture;
    return THREE.Texture.prototype.clone.call(this, a), a
},THREE.DataTexture = function (a, b, c, d, e, f, g, h, k, l, p) {
    THREE.Texture.call(this, null, f, g, h, k, l, d, e, p), this.image = {data: a, width: b, height: c}
},THREE.DataTexture.prototype = Object.create(THREE.Texture.prototype),THREE.DataTexture.prototype.constructor = THREE.DataTexture,THREE.DataTexture.prototype.clone = function () {
    var a = new THREE.DataTexture;
    return THREE.Texture.prototype.clone.call(this, a), a
},THREE.VideoTexture = function (a, b, c, d, e, f, g, h, k) {
    THREE.Texture.call(this, a, b, c, d, e, f, g, h, k), this.generateMipmaps = !1;
    var l = this, p = function () {
        requestAnimationFrame(p), a.readyState === a.HAVE_ENOUGH_DATA && (l.needsUpdate = !0)
    };
    p()
},THREE.VideoTexture.prototype = Object.create(THREE.Texture.prototype),THREE.VideoTexture.prototype.constructor = THREE.VideoTexture,THREE.Group = function () {
    THREE.Object3D.call(this), this.type = "Group"
},THREE.Group.prototype = Object.create(THREE.Object3D.prototype),THREE.Group.prototype.constructor = THREE.Group,THREE.PointCloud = function (a, b) {
    THREE.Object3D.call(this), this.type = "PointCloud", this.geometry = void 0 !== a ? a : new THREE.Geometry, this.material = void 0 !== b ? b : new THREE.PointCloudMaterial({color: 16777215 * Math.random()})
},THREE.PointCloud.prototype = Object.create(THREE.Object3D.prototype),THREE.PointCloud.prototype.constructor = THREE.PointCloud,THREE.PointCloud.prototype.raycast = function () {
    var a = new THREE.Matrix4, b = new THREE.Ray;
    return function (c, d) {
        var e = this, f = e.geometry, g = c.params.PointCloud.threshold;
        if (a.getInverse(this.matrixWorld), b.copy(c.ray).applyMatrix4(a), null === f.boundingBox || !1 !== b.isIntersectionBox(f.boundingBox)) {
            var h = g / ((this.scale.x + this.scale.y + this.scale.z) / 3), k = new THREE.Vector3, g = function (a, f) {
                var g = b.distanceToPoint(a);
                if (h > g) {
                    var k = b.closestPointToPoint(a);
                    k.applyMatrix4(e.matrixWorld);
                    var n = c.ray.origin.distanceTo(k);
                    d.push({distance: n, distanceToRay: g, point: k.clone(), index: f, face: null, object: e})
                }
            };
            if (f instanceof THREE.BufferGeometry) {
                var l = f.attributes, p = l.position.array;
                if (void 0 !== l.index) {
                    var l = l.index.array, q = f.offsets;
                    0 === q.length && (q = [{start: 0, count: l.length, index: 0}]);
                    for (var n = 0, t = q.length; t > n; ++n)for (var r = q[n].start, s = q[n].index, f = r, r = r + q[n].count; r > f; f++) {
                        var u = s + l[f];
                        k.fromArray(p, 3 * u), g(k, u)
                    }
                } else for (l = p.length / 3, f = 0; l > f; f++)k.set(p[3 * f], p[3 * f + 1], p[3 * f + 2]), g(k, f)
            } else for (k = this.geometry.vertices, f = 0; f < k.length; f++)g(k[f], f)
        }
    }
}(),THREE.PointCloud.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.PointCloud(this.geometry, this.material)), THREE.Object3D.prototype.clone.call(this, a), a
},THREE.ParticleSystem = function (a, b) {
    return THREE.warn("THREE.ParticleSystem has been renamed to THREE.PointCloud."), new THREE.PointCloud(a, b)
},THREE.Line = function (a, b, c) {
    THREE.Object3D.call(this), this.type = "Line", this.geometry = void 0 !== a ? a : new THREE.Geometry, this.material = void 0 !== b ? b : new THREE.LineBasicMaterial({color: 16777215 * Math.random()}), this.mode = void 0 !== c ? c : THREE.LineStrip
},THREE.LineStrip = 0,THREE.LinePieces = 1,THREE.Line.prototype = Object.create(THREE.Object3D.prototype),THREE.Line.prototype.constructor = THREE.Line,THREE.Line.prototype.raycast = function () {
    var a = new THREE.Matrix4, b = new THREE.Ray, c = new THREE.Sphere;
    return function (d, e) {
        var f = d.linePrecision, f = f * f, g = this.geometry;
        if (null === g.boundingSphere && g.computeBoundingSphere(), c.copy(g.boundingSphere), c.applyMatrix4(this.matrixWorld), !1 !== d.ray.isIntersectionSphere(c)) {
            a.getInverse(this.matrixWorld), b.copy(d.ray).applyMatrix4(a);
            var h = new THREE.Vector3, k = new THREE.Vector3, l = new THREE.Vector3, p = new THREE.Vector3, q = this.mode === THREE.LineStrip ? 1 : 2;
            if (g instanceof THREE.BufferGeometry) {
                var n = g.attributes;
                if (void 0 !== n.index) {
                    var t = n.index.array, n = n.position.array, r = g.offsets;
                    0 === r.length && (r = [{start: 0, count: t.length, index: 0}]);
                    for (var s = 0; s < r.length; s++)for (var u = r[s].start, v = r[s].count, x = r[s].index, g = u; u + v - 1 > g; g += q) {
                        var D = x + t[g + 1];
                        h.fromArray(n, 3 * (x + t[g])), k.fromArray(n, 3 * D), D = b.distanceSqToSegment(h, k, p, l), D > f || (D = b.origin.distanceTo(p), D < d.near || D > d.far || e.push({
                            distance: D,
                            point: l.clone().applyMatrix4(this.matrixWorld),
                            index: g,
                            offsetIndex: s,
                            face: null,
                            faceIndex: null,
                            object: this
                        }))
                    }
                } else for (n = n.position.array, g = 0; g < n.length / 3 - 1; g += q)h.fromArray(n, 3 * g), k.fromArray(n, 3 * g + 3), D = b.distanceSqToSegment(h, k, p, l), D > f || (D = b.origin.distanceTo(p), D < d.near || D > d.far || e.push({
                    distance: D,
                    point: l.clone().applyMatrix4(this.matrixWorld),
                    index: g,
                    face: null,
                    faceIndex: null,
                    object: this
                }))
            } else if (g instanceof THREE.Geometry)for (h = g.vertices, k = h.length, g = 0; k - 1 > g; g += q)D = b.distanceSqToSegment(h[g], h[g + 1], p, l), D > f || (D = b.origin.distanceTo(p), D < d.near || D > d.far || e.push({
                distance: D,
                point: l.clone().applyMatrix4(this.matrixWorld),
                index: g,
                face: null,
                faceIndex: null,
                object: this
            }))
        }
    }
}(),THREE.Line.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.Line(this.geometry, this.material, this.mode)), THREE.Object3D.prototype.clone.call(this, a), a
},THREE.Mesh = function (a, b) {
    THREE.Object3D.call(this), this.type = "Mesh", this.geometry = void 0 !== a ? a : new THREE.Geometry, this.material = void 0 !== b ? b : new THREE.MeshBasicMaterial({color: 16777215 * Math.random()}), this.updateMorphTargets()
},THREE.Mesh.prototype = Object.create(THREE.Object3D.prototype),THREE.Mesh.prototype.constructor = THREE.Mesh,THREE.Mesh.prototype.updateMorphTargets = function () {
    if (void 0 !== this.geometry.morphTargets && 0 < this.geometry.morphTargets.length) {
        this.morphTargetBase = -1, this.morphTargetForcedOrder = [], this.morphTargetInfluences = [], this.morphTargetDictionary = {};
        for (var a = 0, b = this.geometry.morphTargets.length; b > a; a++)this.morphTargetInfluences.push(0), this.morphTargetDictionary[this.geometry.morphTargets[a].name] = a
    }
},THREE.Mesh.prototype.getMorphTargetIndexByName = function (a) {
    return void 0 !== this.morphTargetDictionary[a] ? this.morphTargetDictionary[a] : (THREE.warn("THREE.Mesh.getMorphTargetIndexByName: morph target " + a + " does not exist. Returning 0."), 0)
},THREE.Mesh.prototype.raycast = function () {
    var a = new THREE.Matrix4, b = new THREE.Ray, c = new THREE.Sphere, d = new THREE.Vector3, e = new THREE.Vector3, f = new THREE.Vector3;
    return function (g, h) {
        var k = this.geometry;
        if (null === k.boundingSphere && k.computeBoundingSphere(), c.copy(k.boundingSphere), c.applyMatrix4(this.matrixWorld), !1 !== g.ray.isIntersectionSphere(c) && (a.getInverse(this.matrixWorld), b.copy(g.ray).applyMatrix4(a), null === k.boundingBox || !1 !== b.isIntersectionBox(k.boundingBox)))if (k instanceof THREE.BufferGeometry) {
            var l = this.material;
            if (void 0 !== l) {
                var q, n, p = k.attributes, t = g.precision;
                if (void 0 !== p.index) {
                    var r = p.index.array, s = p.position.array, u = k.offsets;
                    0 === u.length && (u = [{start: 0, count: r.length, index: 0}]);
                    for (var v = 0, x = u.length; x > v; ++v)for (var p = u[v].start, D = u[v].index, k = p, w = p + u[v].count; w > k; k += 3) {
                        p = D + r[k], q = D + r[k + 1], n = D + r[k + 2], d.fromArray(s, 3 * p), e.fromArray(s, 3 * q), f.fromArray(s, 3 * n);
                        var y = l.side === THREE.BackSide ? b.intersectTriangle(f, e, d, !0) : b.intersectTriangle(d, e, f, l.side !== THREE.DoubleSide);
                        if (null !== y) {
                            y.applyMatrix4(this.matrixWorld);
                            var A = g.ray.origin.distanceTo(y);
                            t > A || A < g.near || A > g.far || h.push({
                                distance: A,
                                point: y,
                                face: new THREE.Face3(p, q, n, THREE.Triangle.normal(d, e, f)),
                                faceIndex: null,
                                object: this
                            })
                        }
                    }
                } else for (s = p.position.array, r = k = 0, w = s.length; w > k; k += 3, r += 9)p = k, q = k + 1, n = k + 2, d.fromArray(s, r), e.fromArray(s, r + 3), f.fromArray(s, r + 6), y = l.side === THREE.BackSide ? b.intersectTriangle(f, e, d, !0) : b.intersectTriangle(d, e, f, l.side !== THREE.DoubleSide),
                null !== y && (y.applyMatrix4(this.matrixWorld), A = g.ray.origin.distanceTo(y), t > A || A < g.near || A > g.far || h.push({
                    distance: A,
                    point: y,
                    face: new THREE.Face3(p, q, n, THREE.Triangle.normal(d, e, f)),
                    faceIndex: null,
                    object: this
                }))
            }
        } else if (k instanceof THREE.Geometry)for (r = this.material instanceof THREE.MeshFaceMaterial, s = !0 === r ? this.material.materials : null, t = g.precision, u = k.vertices, v = 0, x = k.faces.length; x > v; v++)if (D = k.faces[v], l = !0 === r ? s[D.materialIndex] : this.material, void 0 !== l) {
            if (p = u[D.a], q = u[D.b], n = u[D.c], !0 === l.morphTargets) {
                y = k.morphTargets, A = this.morphTargetInfluences, d.set(0, 0, 0), e.set(0, 0, 0), f.set(0, 0, 0);
                for (var w = 0, E = y.length; E > w; w++) {
                    var G = A[w];
                    if (0 !== G) {
                        var F = y[w].vertices;
                        d.x += (F[D.a].x - p.x) * G, d.y += (F[D.a].y - p.y) * G, d.z += (F[D.a].z - p.z) * G, e.x += (F[D.b].x - q.x) * G, e.y += (F[D.b].y - q.y) * G, e.z += (F[D.b].z - q.z) * G, f.x += (F[D.c].x - n.x) * G, f.y += (F[D.c].y - n.y) * G, f.z += (F[D.c].z - n.z) * G
                    }
                }
                d.add(p), e.add(q), f.add(n), p = d, q = e, n = f
            }
            y = l.side === THREE.BackSide ? b.intersectTriangle(n, q, p, !0) : b.intersectTriangle(p, q, n, l.side !== THREE.DoubleSide), null !== y && (y.applyMatrix4(this.matrixWorld), A = g.ray.origin.distanceTo(y), t > A || A < g.near || A > g.far || h.push({
                distance: A,
                point: y,
                face: D,
                faceIndex: v,
                object: this
            }))
        }
    }
}(),THREE.Mesh.prototype.clone = function (a, b) {
    return void 0 === a && (a = new THREE.Mesh(this.geometry, this.material)), THREE.Object3D.prototype.clone.call(this, a, b), a
},THREE.Bone = function (a) {
    THREE.Object3D.call(this), this.type = "Bone", this.skin = a
},THREE.Bone.prototype = Object.create(THREE.Object3D.prototype),THREE.Bone.prototype.constructor = THREE.Bone,THREE.Skeleton = function (a, b, c) {
    if (this.useVertexTexture = void 0 !== c ? c : !0, this.identityMatrix = new THREE.Matrix4, a = a || [], this.bones = a.slice(0), this.useVertexTexture ? (this.boneTextureHeight = this.boneTextureWidth = a = 256 < this.bones.length ? 64 : 64 < this.bones.length ? 32 : 16 < this.bones.length ? 16 : 8, this.boneMatrices = new Float32Array(this.boneTextureWidth * this.boneTextureHeight * 4), this.boneTexture = new THREE.DataTexture(this.boneMatrices, this.boneTextureWidth, this.boneTextureHeight, THREE.RGBAFormat, THREE.FloatType), this.boneTexture.minFilter = THREE.NearestFilter, this.boneTexture.magFilter = THREE.NearestFilter, this.boneTexture.generateMipmaps = !1, this.boneTexture.flipY = !1) : this.boneMatrices = new Float32Array(16 * this.bones.length), void 0 === b)this.calculateInverses(); else if (this.bones.length === b.length)this.boneInverses = b.slice(0); else for (THREE.warn("THREE.Skeleton bonInverses is the wrong length."), this.boneInverses = [], b = 0, a = this.bones.length; a > b; b++)this.boneInverses.push(new THREE.Matrix4)
},THREE.Skeleton.prototype.calculateInverses = function () {
    this.boneInverses = [];
    for (var a = 0, b = this.bones.length; b > a; a++) {
        var c = new THREE.Matrix4;
        this.bones[a] && c.getInverse(this.bones[a].matrixWorld), this.boneInverses.push(c)
    }
},THREE.Skeleton.prototype.pose = function () {
    for (var a, b = 0, c = this.bones.length; c > b; b++)(a = this.bones[b]) && a.matrixWorld.getInverse(this.boneInverses[b]);
    for (b = 0, c = this.bones.length; c > b; b++)(a = this.bones[b]) && (a.parent ? (a.matrix.getInverse(a.parent.matrixWorld), a.matrix.multiply(a.matrixWorld)) : a.matrix.copy(a.matrixWorld), a.matrix.decompose(a.position, a.quaternion, a.scale))
},THREE.Skeleton.prototype.update = function () {
    var a = new THREE.Matrix4;
    return function () {
        for (var b = 0, c = this.bones.length; c > b; b++)a.multiplyMatrices(this.bones[b] ? this.bones[b].matrixWorld : this.identityMatrix, this.boneInverses[b]), a.flattenToArrayOffset(this.boneMatrices, 16 * b);
        this.useVertexTexture && (this.boneTexture.needsUpdate = !0)
    }
}(),THREE.SkinnedMesh = function (a, b, c) {
    if (THREE.Mesh.call(this, a, b), this.type = "SkinnedMesh", this.bindMode = "attached", this.bindMatrix = new THREE.Matrix4, this.bindMatrixInverse = new THREE.Matrix4, a = [], this.geometry && void 0 !== this.geometry.bones) {
        for (var d, e, f, g, h = 0, k = this.geometry.bones.length; k > h; ++h)d = this.geometry.bones[h], e = d.pos, f = d.rotq, g = d.scl, b = new THREE.Bone(this), a.push(b), b.name = d.name, b.position.set(e[0], e[1], e[2]), b.quaternion.set(f[0], f[1], f[2], f[3]), void 0 !== g ? b.scale.set(g[0], g[1], g[2]) : b.scale.set(1, 1, 1);
        for (h = 0, k = this.geometry.bones.length; k > h; ++h)d = this.geometry.bones[h], -1 !== d.parent ? a[d.parent].add(a[h]) : this.add(a[h])
    }
    this.normalizeSkinWeights(), this.updateMatrixWorld(!0), this.bind(new THREE.Skeleton(a, void 0, c))
},THREE.SkinnedMesh.prototype = Object.create(THREE.Mesh.prototype),THREE.SkinnedMesh.prototype.constructor = THREE.SkinnedMesh,THREE.SkinnedMesh.prototype.bind = function (a, b) {
    this.skeleton = a, void 0 === b && (this.updateMatrixWorld(!0), b = this.matrixWorld), this.bindMatrix.copy(b), this.bindMatrixInverse.getInverse(b)
},THREE.SkinnedMesh.prototype.pose = function () {
    this.skeleton.pose()
},THREE.SkinnedMesh.prototype.normalizeSkinWeights = function () {
    if (this.geometry instanceof THREE.Geometry)for (var a = 0; a < this.geometry.skinIndices.length; a++) {
        var b = this.geometry.skinWeights[a], c = 1 / b.lengthManhattan();
        1 / 0 !== c ? b.multiplyScalar(c) : b.set(1)
    }
},THREE.SkinnedMesh.prototype.updateMatrixWorld = function (a) {
    THREE.Mesh.prototype.updateMatrixWorld.call(this, !0), "attached" === this.bindMode ? this.bindMatrixInverse.getInverse(this.matrixWorld) : "detached" === this.bindMode ? this.bindMatrixInverse.getInverse(this.bindMatrix) : THREE.warn("THREE.SkinnedMesh unreckognized bindMode: " + this.bindMode)
},THREE.SkinnedMesh.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.SkinnedMesh(this.geometry, this.material, this.useVertexTexture)), THREE.Mesh.prototype.clone.call(this, a), a
},THREE.MorphAnimMesh = function (a, b) {
    THREE.Mesh.call(this, a, b), this.type = "MorphAnimMesh", this.duration = 1e3, this.mirroredLoop = !1, this.currentKeyframe = this.lastKeyframe = this.time = 0, this.direction = 1, this.directionBackwards = !1, this.setFrameRange(0, this.geometry.morphTargets.length - 1)
},THREE.MorphAnimMesh.prototype = Object.create(THREE.Mesh.prototype),THREE.MorphAnimMesh.prototype.constructor = THREE.MorphAnimMesh,THREE.MorphAnimMesh.prototype.setFrameRange = function (a, b) {
    this.startKeyframe = a, this.endKeyframe = b, this.length = this.endKeyframe - this.startKeyframe + 1
},THREE.MorphAnimMesh.prototype.setDirectionForward = function () {
    this.direction = 1, this.directionBackwards = !1
},THREE.MorphAnimMesh.prototype.setDirectionBackward = function () {
    this.direction = -1, this.directionBackwards = !0
},THREE.MorphAnimMesh.prototype.parseAnimations = function () {
    var a = this.geometry;
    a.animations || (a.animations = {});
    for (var b, c = a.animations, d = /([a-z]+)_?(\d+)/, e = 0, f = a.morphTargets.length; f > e; e++) {
        var g = a.morphTargets[e].name.match(d);
        if (g && 1 < g.length) {
            g = g[1], c[g] || (c[g] = {start: 1 / 0, end: -(1 / 0)});
            var h = c[g];
            e < h.start && (h.start = e), e > h.end && (h.end = e), b || (b = g)
        }
    }
    a.firstAnimation = b
},THREE.MorphAnimMesh.prototype.setAnimationLabel = function (a, b, c) {
    this.geometry.animations || (this.geometry.animations = {}), this.geometry.animations[a] = {start: b, end: c}
},THREE.MorphAnimMesh.prototype.playAnimation = function (a, b) {
    var c = this.geometry.animations[a];
    c ? (this.setFrameRange(c.start, c.end), this.duration = (c.end - c.start) / b * 1e3, this.time = 0) : THREE.warn("THREE.MorphAnimMesh: animation[" + a + "] undefined in .playAnimation()")
},THREE.MorphAnimMesh.prototype.updateAnimation = function (a) {
    var b = this.duration / this.length;
    this.time += this.direction * a, this.mirroredLoop ? (this.time > this.duration || 0 > this.time) && (this.direction *= -1, this.time > this.duration && (this.time = this.duration, this.directionBackwards = !0), 0 > this.time && (this.time = 0, this.directionBackwards = !1)) : (this.time %= this.duration, 0 > this.time && (this.time += this.duration)), a = this.startKeyframe + THREE.Math.clamp(Math.floor(this.time / b), 0, this.length - 1), a !== this.currentKeyframe && (this.morphTargetInfluences[this.lastKeyframe] = 0, this.morphTargetInfluences[this.currentKeyframe] = 1, this.morphTargetInfluences[a] = 0, this.lastKeyframe = this.currentKeyframe, this.currentKeyframe = a), b = this.time % b / b, this.directionBackwards && (b = 1 - b), this.morphTargetInfluences[this.currentKeyframe] = b, this.morphTargetInfluences[this.lastKeyframe] = 1 - b
},THREE.MorphAnimMesh.prototype.interpolateTargets = function (a, b, c) {
    for (var d = this.morphTargetInfluences, e = 0, f = d.length; f > e; e++)d[e] = 0;
    a > -1 && (d[a] = 1 - c), b > -1 && (d[b] = c)
},THREE.MorphAnimMesh.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.MorphAnimMesh(this.geometry, this.material)), a.duration = this.duration, a.mirroredLoop = this.mirroredLoop, a.time = this.time, a.lastKeyframe = this.lastKeyframe, a.currentKeyframe = this.currentKeyframe, a.direction = this.direction, a.directionBackwards = this.directionBackwards, THREE.Mesh.prototype.clone.call(this, a), a
},THREE.LOD = function () {
    THREE.Object3D.call(this), this.objects = []
},THREE.LOD.prototype = Object.create(THREE.Object3D.prototype),THREE.LOD.prototype.constructor = THREE.LOD,THREE.LOD.prototype.addLevel = function (a, b) {
    void 0 === b && (b = 0), b = Math.abs(b);
    for (var c = 0; c < this.objects.length && !(b < this.objects[c].distance); c++);
    this.objects.splice(c, 0, {distance: b, object: a}), this.add(a)
},THREE.LOD.prototype.getObjectForDistance = function (a) {
    for (var b = 1, c = this.objects.length; c > b && !(a < this.objects[b].distance); b++);
    return this.objects[b - 1].object
},THREE.LOD.prototype.raycast = function () {
    var a = new THREE.Vector3;
    return function (b, c) {
        a.setFromMatrixPosition(this.matrixWorld);
        var d = b.ray.origin.distanceTo(a);
        this.getObjectForDistance(d).raycast(b, c)
    }
}(),THREE.LOD.prototype.update = function () {
    var a = new THREE.Vector3, b = new THREE.Vector3;
    return function (c) {
        if (1 < this.objects.length) {
            a.setFromMatrixPosition(c.matrixWorld), b.setFromMatrixPosition(this.matrixWorld), c = a.distanceTo(b), this.objects[0].object.visible = !0;
            for (var d = 1, e = this.objects.length; e > d && c >= this.objects[d].distance; d++)this.objects[d - 1].object.visible = !1, this.objects[d].object.visible = !0;
            for (; e > d; d++)this.objects[d].object.visible = !1
        }
    }
}(),THREE.LOD.prototype.clone = function (a) {
    void 0 === a && (a = new THREE.LOD), THREE.Object3D.prototype.clone.call(this, a);
    for (var b = 0, c = this.objects.length; c > b; b++) {
        var d = this.objects[b].object.clone();
        d.visible = 0 === b, a.addLevel(d, this.objects[b].distance)
    }
    return a
},THREE.Sprite = function () {
    var a = new Uint16Array([0, 1, 2, 0, 2, 3]), b = new Float32Array([-.5, -.5, 0, .5, -.5, 0, .5, .5, 0, -.5, .5, 0]), c = new Float32Array([0, 0, 1, 0, 1, 1, 0, 1]), d = new THREE.BufferGeometry;
    return d.addAttribute("index", new THREE.BufferAttribute(a, 1)), d.addAttribute("position", new THREE.BufferAttribute(b, 3)), d.addAttribute("uv", new THREE.BufferAttribute(c, 2)), function (a) {
        THREE.Object3D.call(this), this.type = "Sprite", this.geometry = d, this.material = void 0 !== a ? a : new THREE.SpriteMaterial
    }
}(),THREE.Sprite.prototype = Object.create(THREE.Object3D.prototype),THREE.Sprite.prototype.constructor = THREE.Sprite,THREE.Sprite.prototype.raycast = function () {
    var a = new THREE.Vector3;
    return function (b, c) {
        a.setFromMatrixPosition(this.matrixWorld);
        var d = b.ray.distanceToPoint(a);
        d > this.scale.x || c.push({distance: d, point: this.position, face: null, object: this})
    }
}(),THREE.Sprite.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.Sprite(this.material)), THREE.Object3D.prototype.clone.call(this, a), a
},THREE.Particle = THREE.Sprite,THREE.LensFlare = function (a, b, c, d, e) {
    THREE.Object3D.call(this), this.lensFlares = [], this.positionScreen = new THREE.Vector3, this.customUpdateCallback = void 0, void 0 !== a && this.add(a, b, c, d, e)
},THREE.LensFlare.prototype = Object.create(THREE.Object3D.prototype),THREE.LensFlare.prototype.constructor = THREE.LensFlare,THREE.LensFlare.prototype.add = function (a, b, c, d, e, f) {
    void 0 === b && (b = -1), void 0 === c && (c = 0), void 0 === f && (f = 1), void 0 === e && (e = new THREE.Color(16777215)), void 0 === d && (d = THREE.NormalBlending), c = Math.min(c, Math.max(0, c)), this.lensFlares.push({
        texture: a,
        size: b,
        distance: c,
        x: 0,
        y: 0,
        z: 0,
        scale: 1,
        rotation: 1,
        opacity: f,
        color: e,
        blending: d
    })
},THREE.LensFlare.prototype.updateLensFlares = function () {
    var a, c, b = this.lensFlares.length, d = 2 * -this.positionScreen.x, e = 2 * -this.positionScreen.y;
    for (a = 0; b > a; a++)c = this.lensFlares[a], c.x = this.positionScreen.x + d * c.distance, c.y = this.positionScreen.y + e * c.distance, c.wantedRotation = c.x * Math.PI * .25, c.rotation += .25 * (c.wantedRotation - c.rotation)
},THREE.Scene = function () {
    THREE.Object3D.call(this), this.type = "Scene", this.overrideMaterial = this.fog = null, this.autoUpdate = !0
},THREE.Scene.prototype = Object.create(THREE.Object3D.prototype),THREE.Scene.prototype.constructor = THREE.Scene,THREE.Scene.prototype.clone = function (a) {
    return void 0 === a && (a = new THREE.Scene), THREE.Object3D.prototype.clone.call(this, a), null !== this.fog && (a.fog = this.fog.clone()), null !== this.overrideMaterial && (a.overrideMaterial = this.overrideMaterial.clone()), a.autoUpdate = this.autoUpdate, a.matrixAutoUpdate = this.matrixAutoUpdate, a
},THREE.Fog = function (a, b, c) {
    this.name = "", this.color = new THREE.Color(a), this.near = void 0 !== b ? b : 1, this.far = void 0 !== c ? c : 1e3
},THREE.Fog.prototype.clone = function () {
    return new THREE.Fog(this.color.getHex(), this.near, this.far)
},THREE.FogExp2 = function (a, b) {
    this.name = "", this.color = new THREE.Color(a), this.density = void 0 !== b ? b : 25e-5
},THREE.FogExp2.prototype.clone = function () {
    return new THREE.FogExp2(this.color.getHex(), this.density)
},THREE.ShaderChunk = {},THREE.ShaderChunk.common = "#define PI 3.14159\n#define PI2 6.28318\n#define RECIPROCAL_PI2 0.15915494\n#define LOG2 1.442695\n#define EPSILON 1e-6\n\nfloat square( in float a ) { return a*a; }\nvec2  square( in vec2 a )  { return vec2( a.x*a.x, a.y*a.y ); }\nvec3  square( in vec3 a )  { return vec3( a.x*a.x, a.y*a.y, a.z*a.z ); }\nvec4  square( in vec4 a )  { return vec4( a.x*a.x, a.y*a.y, a.z*a.z, a.w*a.w ); }\nfloat saturate( in float a ) { return clamp( a, 0.0, 1.0 ); }\nvec2  saturate( in vec2 a )  { return clamp( a, 0.0, 1.0 ); }\nvec3  saturate( in vec3 a )  { return clamp( a, 0.0, 1.0 ); }\nvec4  saturate( in vec4 a )  { return clamp( a, 0.0, 1.0 ); }\nfloat average( in float a ) { return a; }\nfloat average( in vec2 a )  { return ( a.x + a.y) * 0.5; }\nfloat average( in vec3 a )  { return ( a.x + a.y + a.z) / 3.0; }\nfloat average( in vec4 a )  { return ( a.x + a.y + a.z + a.w) * 0.25; }\nfloat whiteCompliment( in float a ) { return saturate( 1.0 - a ); }\nvec2  whiteCompliment( in vec2 a )  { return saturate( vec2(1.0) - a ); }\nvec3  whiteCompliment( in vec3 a )  { return saturate( vec3(1.0) - a ); }\nvec4  whiteCompliment( in vec4 a )  { return saturate( vec4(1.0) - a ); }\nvec3 transformDirection( in vec3 normal, in mat4 matrix ) {\n	return normalize( ( matrix * vec4( normal, 0.0 ) ).xyz );\n}\n// http://en.wikibooks.org/wiki/GLSL_Programming/Applying_Matrix_Transformations\nvec3 inverseTransformDirection( in vec3 normal, in mat4 matrix ) {\n	return normalize( ( vec4( normal, 0.0 ) * matrix ).xyz );\n}\nvec3 projectOnPlane(in vec3 point, in vec3 pointOnPlane, in vec3 planeNormal) {\n	float distance = dot( planeNormal, point-pointOnPlane );\n	return point - distance * planeNormal;\n}\nfloat sideOfPlane( in vec3 point, in vec3 pointOnPlane, in vec3 planeNormal ) {\n	return sign( dot( point - pointOnPlane, planeNormal ) );\n}\nvec3 linePlaneIntersect( in vec3 pointOnLine, in vec3 lineDirection, in vec3 pointOnPlane, in vec3 planeNormal ) {\n	return pointOnLine + lineDirection * ( dot( planeNormal, pointOnPlane - pointOnLine ) / dot( planeNormal, lineDirection ) );\n}\nfloat calcLightAttenuation( float lightDistance, float cutoffDistance, float decayExponent ) {\n	if ( decayExponent > 0.0 ) {\n	  return pow( saturate( 1.0 - lightDistance / cutoffDistance ), decayExponent );\n	}\n	return 1.0;\n}\n\nvec3 inputToLinear( in vec3 a ) {\n#ifdef GAMMA_INPUT\n	return pow( a, vec3( float( GAMMA_FACTOR ) ) );\n#else\n	return a;\n#endif\n}\nvec3 linearToOutput( in vec3 a ) {\n#ifdef GAMMA_OUTPUT\n	return pow( a, vec3( 1.0 / float( GAMMA_FACTOR ) ) );\n#else\n	return a;\n#endif\n}\n",THREE.ShaderChunk.alphatest_fragment = "#ifdef ALPHATEST\n\n	if ( diffuseColor.a < ALPHATEST ) discard;\n\n#endif\n",THREE.ShaderChunk.lights_lambert_vertex = "vLightFront = vec3( 0.0 );\n\n#ifdef DOUBLE_SIDED\n\n	vLightBack = vec3( 0.0 );\n\n#endif\n\ntransformedNormal = normalize( transformedNormal );\n\n#if MAX_DIR_LIGHTS > 0\n\nfor( int i = 0; i < MAX_DIR_LIGHTS; i ++ ) {\n\n	vec3 dirVector = transformDirection( directionalLightDirection[ i ], viewMatrix );\n\n	float dotProduct = dot( transformedNormal, dirVector );\n	vec3 directionalLightWeighting = vec3( max( dotProduct, 0.0 ) );\n\n	#ifdef DOUBLE_SIDED\n\n		vec3 directionalLightWeightingBack = vec3( max( -dotProduct, 0.0 ) );\n\n		#ifdef WRAP_AROUND\n\n			vec3 directionalLightWeightingHalfBack = vec3( max( -0.5 * dotProduct + 0.5, 0.0 ) );\n\n		#endif\n\n	#endif\n\n	#ifdef WRAP_AROUND\n\n		vec3 directionalLightWeightingHalf = vec3( max( 0.5 * dotProduct + 0.5, 0.0 ) );\n		directionalLightWeighting = mix( directionalLightWeighting, directionalLightWeightingHalf, wrapRGB );\n\n		#ifdef DOUBLE_SIDED\n\n			directionalLightWeightingBack = mix( directionalLightWeightingBack, directionalLightWeightingHalfBack, wrapRGB );\n\n		#endif\n\n	#endif\n\n	vLightFront += directionalLightColor[ i ] * directionalLightWeighting;\n\n	#ifdef DOUBLE_SIDED\n\n		vLightBack += directionalLightColor[ i ] * directionalLightWeightingBack;\n\n	#endif\n\n}\n\n#endif\n\n#if MAX_POINT_LIGHTS > 0\n\n	for( int i = 0; i < MAX_POINT_LIGHTS; i ++ ) {\n\n		vec4 lPosition = viewMatrix * vec4( pointLightPosition[ i ], 1.0 );\n		vec3 lVector = lPosition.xyz - mvPosition.xyz;\n\n		float attenuation = calcLightAttenuation( length( lVector ), pointLightDistance[ i ], pointLightDecay[ i ] );\n\n		lVector = normalize( lVector );\n		float dotProduct = dot( transformedNormal, lVector );\n\n		vec3 pointLightWeighting = vec3( max( dotProduct, 0.0 ) );\n\n		#ifdef DOUBLE_SIDED\n\n			vec3 pointLightWeightingBack = vec3( max( -dotProduct, 0.0 ) );\n\n			#ifdef WRAP_AROUND\n\n				vec3 pointLightWeightingHalfBack = vec3( max( -0.5 * dotProduct + 0.5, 0.0 ) );\n\n			#endif\n\n		#endif\n\n		#ifdef WRAP_AROUND\n\n			vec3 pointLightWeightingHalf = vec3( max( 0.5 * dotProduct + 0.5, 0.0 ) );\n			pointLightWeighting = mix( pointLightWeighting, pointLightWeightingHalf, wrapRGB );\n\n			#ifdef DOUBLE_SIDED\n\n				pointLightWeightingBack = mix( pointLightWeightingBack, pointLightWeightingHalfBack, wrapRGB );\n\n			#endif\n\n		#endif\n\n		vLightFront += pointLightColor[ i ] * pointLightWeighting * attenuation;\n\n		#ifdef DOUBLE_SIDED\n\n			vLightBack += pointLightColor[ i ] * pointLightWeightingBack * attenuation;\n\n		#endif\n\n	}\n\n#endif\n\n#if MAX_SPOT_LIGHTS > 0\n\n	for( int i = 0; i < MAX_SPOT_LIGHTS; i ++ ) {\n\n		vec4 lPosition = viewMatrix * vec4( spotLightPosition[ i ], 1.0 );\n		vec3 lVector = lPosition.xyz - mvPosition.xyz;\n\n		float spotEffect = dot( spotLightDirection[ i ], normalize( spotLightPosition[ i ] - worldPosition.xyz ) );\n\n		if ( spotEffect > spotLightAngleCos[ i ] ) {\n\n			spotEffect = max( pow( max( spotEffect, 0.0 ), spotLightExponent[ i ] ), 0.0 );\n\n			float attenuation = calcLightAttenuation( length( lVector ), spotLightDistance[ i ], spotLightDecay[ i ] );\n\n			lVector = normalize( lVector );\n\n			float dotProduct = dot( transformedNormal, lVector );\n			vec3 spotLightWeighting = vec3( max( dotProduct, 0.0 ) );\n\n			#ifdef DOUBLE_SIDED\n\n				vec3 spotLightWeightingBack = vec3( max( -dotProduct, 0.0 ) );\n\n				#ifdef WRAP_AROUND\n\n					vec3 spotLightWeightingHalfBack = vec3( max( -0.5 * dotProduct + 0.5, 0.0 ) );\n\n				#endif\n\n			#endif\n\n			#ifdef WRAP_AROUND\n\n				vec3 spotLightWeightingHalf = vec3( max( 0.5 * dotProduct + 0.5, 0.0 ) );\n				spotLightWeighting = mix( spotLightWeighting, spotLightWeightingHalf, wrapRGB );\n\n				#ifdef DOUBLE_SIDED\n\n					spotLightWeightingBack = mix( spotLightWeightingBack, spotLightWeightingHalfBack, wrapRGB );\n\n				#endif\n\n			#endif\n\n			vLightFront += spotLightColor[ i ] * spotLightWeighting * attenuation * spotEffect;\n\n			#ifdef DOUBLE_SIDED\n\n				vLightBack += spotLightColor[ i ] * spotLightWeightingBack * attenuation * spotEffect;\n\n			#endif\n\n		}\n\n	}\n\n#endif\n\n#if MAX_HEMI_LIGHTS > 0\n\n	for( int i = 0; i < MAX_HEMI_LIGHTS; i ++ ) {\n\n		vec3 lVector = transformDirection( hemisphereLightDirection[ i ], viewMatrix );\n\n		float dotProduct = dot( transformedNormal, lVector );\n\n		float hemiDiffuseWeight = 0.5 * dotProduct + 0.5;\n		float hemiDiffuseWeightBack = -0.5 * dotProduct + 0.5;\n\n		vLightFront += mix( hemisphereLightGroundColor[ i ], hemisphereLightSkyColor[ i ], hemiDiffuseWeight );\n\n		#ifdef DOUBLE_SIDED\n\n			vLightBack += mix( hemisphereLightGroundColor[ i ], hemisphereLightSkyColor[ i ], hemiDiffuseWeightBack );\n\n		#endif\n\n	}\n\n#endif\n\nvLightFront += ambientLightColor;\n\n#ifdef DOUBLE_SIDED\n\n	vLightBack += ambientLightColor;\n\n#endif\n",THREE.ShaderChunk.map_particle_pars_fragment = "#ifdef USE_MAP\n\n	uniform vec4 offsetRepeat;\n	uniform sampler2D map;\n\n#endif\n",THREE.ShaderChunk.default_vertex = "#ifdef USE_SKINNING\n\n	vec4 mvPosition = modelViewMatrix * skinned;\n\n#elif defined( USE_MORPHTARGETS )\n\n	vec4 mvPosition = modelViewMatrix * vec4( morphed, 1.0 );\n\n#else\n\n	vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );\n\n#endif\n\ngl_Position = projectionMatrix * mvPosition;\n",THREE.ShaderChunk.map_pars_fragment = "#if defined( USE_MAP ) || defined( USE_BUMPMAP ) || defined( USE_NORMALMAP ) || defined( USE_SPECULARMAP ) || defined( USE_ALPHAMAP )\n\n	varying vec2 vUv;\n\n#endif\n\n#ifdef USE_MAP\n\n	uniform sampler2D map;\n\n#endif",THREE.ShaderChunk.skinnormal_vertex = "#ifdef USE_SKINNING\n\n	mat4 skinMatrix = mat4( 0.0 );\n	skinMatrix += skinWeight.x * boneMatX;\n	skinMatrix += skinWeight.y * boneMatY;\n	skinMatrix += skinWeight.z * boneMatZ;\n	skinMatrix += skinWeight.w * boneMatW;\n	skinMatrix  = bindMatrixInverse * skinMatrix * bindMatrix;\n\n	#ifdef USE_MORPHNORMALS\n\n	vec4 skinnedNormal = skinMatrix * vec4( morphedNormal, 0.0 );\n\n	#else\n\n	vec4 skinnedNormal = skinMatrix * vec4( normal, 0.0 );\n\n	#endif\n\n#endif\n",THREE.ShaderChunk.logdepthbuf_pars_vertex = "#ifdef USE_LOGDEPTHBUF\n\n	#ifdef USE_LOGDEPTHBUF_EXT\n\n		varying float vFragDepth;\n\n	#endif\n\n	uniform float logDepthBufFC;\n\n#endif",THREE.ShaderChunk.lightmap_pars_vertex = "#ifdef USE_LIGHTMAP\n\n	varying vec2 vUv2;\n\n#endif",THREE.ShaderChunk.lights_phong_fragment = "#ifndef FLAT_SHADED\n\n	vec3 normal = normalize( vNormal );\n\n	#ifdef DOUBLE_SIDED\n\n		normal = normal * ( -1.0 + 2.0 * float( gl_FrontFacing ) );\n\n	#endif\n\n#else\n\n	vec3 fdx = dFdx( vViewPosition );\n	vec3 fdy = dFdy( vViewPosition );\n	vec3 normal = normalize( cross( fdx, fdy ) );\n\n#endif\n\nvec3 viewPosition = normalize( vViewPosition );\n\n#ifdef USE_NORMALMAP\n\n	normal = perturbNormal2Arb( -vViewPosition, normal );\n\n#elif defined( USE_BUMPMAP )\n\n	normal = perturbNormalArb( -vViewPosition, normal, dHdxy_fwd() );\n\n#endif\n\nvec3 totalDiffuseLight = vec3( 0.0 );\nvec3 totalSpecularLight = vec3( 0.0 );\n\n#if MAX_POINT_LIGHTS > 0\n\n	for ( int i = 0; i < MAX_POINT_LIGHTS; i ++ ) {\n\n		vec4 lPosition = viewMatrix * vec4( pointLightPosition[ i ], 1.0 );\n		vec3 lVector = lPosition.xyz + vViewPosition.xyz;\n\n		float attenuation = calcLightAttenuation( length( lVector ), pointLightDistance[ i ], pointLightDecay[ i ] );\n\n		lVector = normalize( lVector );\n\n		// diffuse\n\n		float dotProduct = dot( normal, lVector );\n\n		#ifdef WRAP_AROUND\n\n			float pointDiffuseWeightFull = max( dotProduct, 0.0 );\n			float pointDiffuseWeightHalf = max( 0.5 * dotProduct + 0.5, 0.0 );\n\n			vec3 pointDiffuseWeight = mix( vec3( pointDiffuseWeightFull ), vec3( pointDiffuseWeightHalf ), wrapRGB );\n\n		#else\n\n			float pointDiffuseWeight = max( dotProduct, 0.0 );\n\n		#endif\n\n		totalDiffuseLight += pointLightColor[ i ] * pointDiffuseWeight * attenuation;\n\n				// specular\n\n		vec3 pointHalfVector = normalize( lVector + viewPosition );\n		float pointDotNormalHalf = max( dot( normal, pointHalfVector ), 0.0 );\n		float pointSpecularWeight = specularStrength * max( pow( pointDotNormalHalf, shininess ), 0.0 );\n\n		float specularNormalization = ( shininess + 2.0 ) / 8.0;\n\n		vec3 schlick = specular + vec3( 1.0 - specular ) * pow( max( 1.0 - dot( lVector, pointHalfVector ), 0.0 ), 5.0 );\n		totalSpecularLight += schlick * pointLightColor[ i ] * pointSpecularWeight * pointDiffuseWeight * attenuation * specularNormalization;\n\n	}\n\n#endif\n\n#if MAX_SPOT_LIGHTS > 0\n\n	for ( int i = 0; i < MAX_SPOT_LIGHTS; i ++ ) {\n\n		vec4 lPosition = viewMatrix * vec4( spotLightPosition[ i ], 1.0 );\n		vec3 lVector = lPosition.xyz + vViewPosition.xyz;\n\n		float attenuation = calcLightAttenuation( length( lVector ), spotLightDistance[ i ], spotLightDecay[ i ] );\n\n		lVector = normalize( lVector );\n\n		float spotEffect = dot( spotLightDirection[ i ], normalize( spotLightPosition[ i ] - vWorldPosition ) );\n\n		if ( spotEffect > spotLightAngleCos[ i ] ) {\n\n			spotEffect = max( pow( max( spotEffect, 0.0 ), spotLightExponent[ i ] ), 0.0 );\n\n			// diffuse\n\n			float dotProduct = dot( normal, lVector );\n\n			#ifdef WRAP_AROUND\n\n				float spotDiffuseWeightFull = max( dotProduct, 0.0 );\n				float spotDiffuseWeightHalf = max( 0.5 * dotProduct + 0.5, 0.0 );\n\n				vec3 spotDiffuseWeight = mix( vec3( spotDiffuseWeightFull ), vec3( spotDiffuseWeightHalf ), wrapRGB );\n\n			#else\n\n				float spotDiffuseWeight = max( dotProduct, 0.0 );\n\n			#endif\n\n			totalDiffuseLight += spotLightColor[ i ] * spotDiffuseWeight * attenuation * spotEffect;\n\n			// specular\n\n			vec3 spotHalfVector = normalize( lVector + viewPosition );\n			float spotDotNormalHalf = max( dot( normal, spotHalfVector ), 0.0 );\n			float spotSpecularWeight = specularStrength * max( pow( spotDotNormalHalf, shininess ), 0.0 );\n\n			float specularNormalization = ( shininess + 2.0 ) / 8.0;\n\n			vec3 schlick = specular + vec3( 1.0 - specular ) * pow( max( 1.0 - dot( lVector, spotHalfVector ), 0.0 ), 5.0 );\n			totalSpecularLight += schlick * spotLightColor[ i ] * spotSpecularWeight * spotDiffuseWeight * attenuation * specularNormalization * spotEffect;\n\n		}\n\n	}\n\n#endif\n\n#if MAX_DIR_LIGHTS > 0\n\n	for( int i = 0; i < MAX_DIR_LIGHTS; i ++ ) {\n\n		vec3 dirVector = transformDirection( directionalLightDirection[ i ], viewMatrix );\n\n		// diffuse\n\n		float dotProduct = dot( normal, dirVector );\n\n		#ifdef WRAP_AROUND\n\n			float dirDiffuseWeightFull = max( dotProduct, 0.0 );\n			float dirDiffuseWeightHalf = max( 0.5 * dotProduct + 0.5, 0.0 );\n\n			vec3 dirDiffuseWeight = mix( vec3( dirDiffuseWeightFull ), vec3( dirDiffuseWeightHalf ), wrapRGB );\n\n		#else\n\n			float dirDiffuseWeight = max( dotProduct, 0.0 );\n\n		#endif\n\n		totalDiffuseLight += directionalLightColor[ i ] * dirDiffuseWeight;\n\n		// specular\n\n		vec3 dirHalfVector = normalize( dirVector + viewPosition );\n		float dirDotNormalHalf = max( dot( normal, dirHalfVector ), 0.0 );\n		float dirSpecularWeight = specularStrength * max( pow( dirDotNormalHalf, shininess ), 0.0 );\n\n		/*\n		// fresnel term from skin shader\n		const float F0 = 0.128;\n\n		float base = 1.0 - dot( viewPosition, dirHalfVector );\n		float exponential = pow( base, 5.0 );\n\n		float fresnel = exponential + F0 * ( 1.0 - exponential );\n		*/\n\n		/*\n		// fresnel term from fresnel shader\n		const float mFresnelBias = 0.08;\n		const float mFresnelScale = 0.3;\n		const float mFresnelPower = 5.0;\n\n		float fresnel = mFresnelBias + mFresnelScale * pow( 1.0 + dot( normalize( -viewPosition ), normal ), mFresnelPower );\n		*/\n\n		float specularNormalization = ( shininess + 2.0 ) / 8.0;\n\n		// 		dirSpecular += specular * directionalLightColor[ i ] * dirSpecularWeight * dirDiffuseWeight * specularNormalization * fresnel;\n\n		vec3 schlick = specular + vec3( 1.0 - specular ) * pow( max( 1.0 - dot( dirVector, dirHalfVector ), 0.0 ), 5.0 );\n		totalSpecularLight += schlick * directionalLightColor[ i ] * dirSpecularWeight * dirDiffuseWeight * specularNormalization;\n\n\n	}\n\n#endif\n\n#if MAX_HEMI_LIGHTS > 0\n\n	for( int i = 0; i < MAX_HEMI_LIGHTS; i ++ ) {\n\n		vec3 lVector = transformDirection( hemisphereLightDirection[ i ], viewMatrix );\n\n		// diffuse\n\n		float dotProduct = dot( normal, lVector );\n		float hemiDiffuseWeight = 0.5 * dotProduct + 0.5;\n\n		vec3 hemiColor = mix( hemisphereLightGroundColor[ i ], hemisphereLightSkyColor[ i ], hemiDiffuseWeight );\n\n		totalDiffuseLight += hemiColor;\n\n		// specular (sky light)\n\n		vec3 hemiHalfVectorSky = normalize( lVector + viewPosition );\n		float hemiDotNormalHalfSky = 0.5 * dot( normal, hemiHalfVectorSky ) + 0.5;\n		float hemiSpecularWeightSky = specularStrength * max( pow( max( hemiDotNormalHalfSky, 0.0 ), shininess ), 0.0 );\n\n		// specular (ground light)\n\n		vec3 lVectorGround = -lVector;\n\n		vec3 hemiHalfVectorGround = normalize( lVectorGround + viewPosition );\n		float hemiDotNormalHalfGround = 0.5 * dot( normal, hemiHalfVectorGround ) + 0.5;\n		float hemiSpecularWeightGround = specularStrength * max( pow( max( hemiDotNormalHalfGround, 0.0 ), shininess ), 0.0 );\n\n		float dotProductGround = dot( normal, lVectorGround );\n\n		float specularNormalization = ( shininess + 2.0 ) / 8.0;\n\n		vec3 schlickSky = specular + vec3( 1.0 - specular ) * pow( max( 1.0 - dot( lVector, hemiHalfVectorSky ), 0.0 ), 5.0 );\n		vec3 schlickGround = specular + vec3( 1.0 - specular ) * pow( max( 1.0 - dot( lVectorGround, hemiHalfVectorGround ), 0.0 ), 5.0 );\n		totalSpecularLight += hemiColor * specularNormalization * ( schlickSky * hemiSpecularWeightSky * max( dotProduct, 0.0 ) + schlickGround * hemiSpecularWeightGround * max( dotProductGround, 0.0 ) );\n\n	}\n\n#endif\n\n#ifdef METAL\n\n	outgoingLight += diffuseColor.rgb * ( totalDiffuseLight + ambientLightColor ) * specular + totalSpecularLight + emissive;\n\n#else\n\n	outgoingLight += diffuseColor.rgb * ( totalDiffuseLight + ambientLightColor ) + totalSpecularLight + emissive;\n\n#endif\n",THREE.ShaderChunk.fog_pars_fragment = "#ifdef USE_FOG\n\n	uniform vec3 fogColor;\n\n	#ifdef FOG_EXP2\n\n		uniform float fogDensity;\n\n	#else\n\n		uniform float fogNear;\n		uniform float fogFar;\n	#endif\n\n#endif",THREE.ShaderChunk.morphnormal_vertex = "#ifdef USE_MORPHNORMALS\n\n	vec3 morphedNormal = vec3( 0.0 );\n\n	morphedNormal += ( morphNormal0 - normal ) * morphTargetInfluences[ 0 ];\n	morphedNormal += ( morphNormal1 - normal ) * morphTargetInfluences[ 1 ];\n	morphedNormal += ( morphNormal2 - normal ) * morphTargetInfluences[ 2 ];\n	morphedNormal += ( morphNormal3 - normal ) * morphTargetInfluences[ 3 ];\n\n	morphedNormal += normal;\n\n#endif",THREE.ShaderChunk.envmap_pars_fragment = "#ifdef USE_ENVMAP\n\n	uniform float reflectivity;\n	#ifdef ENVMAP_TYPE_CUBE\n		uniform samplerCube envMap;\n	#else\n		uniform sampler2D envMap;\n	#endif\n	uniform float flipEnvMap;\n\n	#if defined( USE_BUMPMAP ) || defined( USE_NORMALMAP ) || defined( PHONG )\n\n		uniform float refractionRatio;\n\n	#else\n\n		varying vec3 vReflect;\n\n	#endif\n\n#endif\n",THREE.ShaderChunk.logdepthbuf_fragment = "#if defined(USE_LOGDEPTHBUF) && defined(USE_LOGDEPTHBUF_EXT)\n\n	gl_FragDepthEXT = log2(vFragDepth) * logDepthBufFC * 0.5;\n\n#endif",THREE.ShaderChunk.normalmap_pars_fragment = "#ifdef USE_NORMALMAP\n\n	uniform sampler2D normalMap;\n	uniform vec2 normalScale;\n\n	// Per-Pixel Tangent Space Normal Mapping\n	// http://hacksoflife.blogspot.ch/2009/11/per-pixel-tangent-space-normal-mapping.html\n\n	vec3 perturbNormal2Arb( vec3 eye_pos, vec3 surf_norm ) {\n\n		vec3 q0 = dFdx( eye_pos.xyz );\n		vec3 q1 = dFdy( eye_pos.xyz );\n		vec2 st0 = dFdx( vUv.st );\n		vec2 st1 = dFdy( vUv.st );\n\n		vec3 S = normalize( q0 * st1.t - q1 * st0.t );\n		vec3 T = normalize( -q0 * st1.s + q1 * st0.s );\n		vec3 N = normalize( surf_norm );\n\n		vec3 mapN = texture2D( normalMap, vUv ).xyz * 2.0 - 1.0;\n		mapN.xy = normalScale * mapN.xy;\n		mat3 tsn = mat3( S, T, N );\n		return normalize( tsn * mapN );\n\n	}\n\n#endif\n",THREE.ShaderChunk.lights_phong_pars_vertex = "#if MAX_SPOT_LIGHTS > 0 || defined( USE_BUMPMAP ) || defined( USE_ENVMAP )\n\n	varying vec3 vWorldPosition;\n\n#endif\n",THREE.ShaderChunk.lightmap_pars_fragment = "#ifdef USE_LIGHTMAP\n\n	varying vec2 vUv2;\n	uniform sampler2D lightMap;\n\n#endif",THREE.ShaderChunk.shadowmap_vertex = "#ifdef USE_SHADOWMAP\n\n	for( int i = 0; i < MAX_SHADOWS; i ++ ) {\n\n		vShadowCoord[ i ] = shadowMatrix[ i ] * worldPosition;\n\n	}\n\n#endif",THREE.ShaderChunk.lights_phong_vertex = "#if MAX_SPOT_LIGHTS > 0 || defined( USE_BUMPMAP ) || defined( USE_ENVMAP )\n\n	vWorldPosition = worldPosition.xyz;\n\n#endif",
THREE.ShaderChunk.map_fragment = "#ifdef USE_MAP\n\n	vec4 texelColor = texture2D( map, vUv );\n\n	texelColor.xyz = inputToLinear( texelColor.xyz );\n\n	diffuseColor *= texelColor;\n\n#endif",THREE.ShaderChunk.lightmap_vertex = "#ifdef USE_LIGHTMAP\n\n	vUv2 = uv2;\n\n#endif",THREE.ShaderChunk.map_particle_fragment = "#ifdef USE_MAP\n\n	diffuseColor *= texture2D( map, vec2( gl_PointCoord.x, 1.0 - gl_PointCoord.y ) * offsetRepeat.zw + offsetRepeat.xy );\n\n#endif\n",THREE.ShaderChunk.color_pars_fragment = "#ifdef USE_COLOR\n\n	varying vec3 vColor;\n\n#endif\n",THREE.ShaderChunk.color_vertex = "#ifdef USE_COLOR\n\n	vColor.xyz = inputToLinear( color.xyz );\n\n#endif",THREE.ShaderChunk.skinning_vertex = "#ifdef USE_SKINNING\n\n	#ifdef USE_MORPHTARGETS\n\n	vec4 skinVertex = bindMatrix * vec4( morphed, 1.0 );\n\n	#else\n\n	vec4 skinVertex = bindMatrix * vec4( position, 1.0 );\n\n	#endif\n\n	vec4 skinned = vec4( 0.0 );\n	skinned += boneMatX * skinVertex * skinWeight.x;\n	skinned += boneMatY * skinVertex * skinWeight.y;\n	skinned += boneMatZ * skinVertex * skinWeight.z;\n	skinned += boneMatW * skinVertex * skinWeight.w;\n	skinned  = bindMatrixInverse * skinned;\n\n#endif\n",THREE.ShaderChunk.envmap_pars_vertex = "#if defined( USE_ENVMAP ) && ! defined( USE_BUMPMAP ) && ! defined( USE_NORMALMAP ) && ! defined( PHONG )\n\n	varying vec3 vReflect;\n\n	uniform float refractionRatio;\n\n#endif\n",THREE.ShaderChunk.linear_to_gamma_fragment = "\n	outgoingLight = linearToOutput( outgoingLight );\n",THREE.ShaderChunk.color_pars_vertex = "#ifdef USE_COLOR\n\n	varying vec3 vColor;\n\n#endif",THREE.ShaderChunk.lights_lambert_pars_vertex = "uniform vec3 ambientLightColor;\n\n#if MAX_DIR_LIGHTS > 0\n\n	uniform vec3 directionalLightColor[ MAX_DIR_LIGHTS ];\n	uniform vec3 directionalLightDirection[ MAX_DIR_LIGHTS ];\n\n#endif\n\n#if MAX_HEMI_LIGHTS > 0\n\n	uniform vec3 hemisphereLightSkyColor[ MAX_HEMI_LIGHTS ];\n	uniform vec3 hemisphereLightGroundColor[ MAX_HEMI_LIGHTS ];\n	uniform vec3 hemisphereLightDirection[ MAX_HEMI_LIGHTS ];\n\n#endif\n\n#if MAX_POINT_LIGHTS > 0\n\n	uniform vec3 pointLightColor[ MAX_POINT_LIGHTS ];\n	uniform vec3 pointLightPosition[ MAX_POINT_LIGHTS ];\n	uniform float pointLightDistance[ MAX_POINT_LIGHTS ];\n	uniform float pointLightDecay[ MAX_POINT_LIGHTS ];\n\n#endif\n\n#if MAX_SPOT_LIGHTS > 0\n\n	uniform vec3 spotLightColor[ MAX_SPOT_LIGHTS ];\n	uniform vec3 spotLightPosition[ MAX_SPOT_LIGHTS ];\n	uniform vec3 spotLightDirection[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightDistance[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightAngleCos[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightExponent[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightDecay[ MAX_SPOT_LIGHTS ];\n\n#endif\n\n#ifdef WRAP_AROUND\n\n	uniform vec3 wrapRGB;\n\n#endif\n",THREE.ShaderChunk.map_pars_vertex = "#if defined( USE_MAP ) || defined( USE_BUMPMAP ) || defined( USE_NORMALMAP ) || defined( USE_SPECULARMAP ) || defined( USE_ALPHAMAP )\n\n	varying vec2 vUv;\n	uniform vec4 offsetRepeat;\n\n#endif\n",THREE.ShaderChunk.envmap_fragment = "#ifdef USE_ENVMAP\n\n	#if defined( USE_BUMPMAP ) || defined( USE_NORMALMAP ) || defined( PHONG )\n\n		vec3 cameraToVertex = normalize( vWorldPosition - cameraPosition );\n\n		// Transforming Normal Vectors with the Inverse Transformation\n		vec3 worldNormal = inverseTransformDirection( normal, viewMatrix );\n\n		#ifdef ENVMAP_MODE_REFLECTION\n\n			vec3 reflectVec = reflect( cameraToVertex, worldNormal );\n\n		#else\n\n			vec3 reflectVec = refract( cameraToVertex, worldNormal, refractionRatio );\n\n		#endif\n\n	#else\n\n		vec3 reflectVec = vReflect;\n\n	#endif\n\n	#ifdef DOUBLE_SIDED\n		float flipNormal = ( -1.0 + 2.0 * float( gl_FrontFacing ) );\n	#else\n		float flipNormal = 1.0;\n	#endif\n\n	#ifdef ENVMAP_TYPE_CUBE\n		vec4 envColor = textureCube( envMap, flipNormal * vec3( flipEnvMap * reflectVec.x, reflectVec.yz ) );\n\n	#elif defined( ENVMAP_TYPE_EQUIREC )\n		vec2 sampleUV;\n		sampleUV.y = saturate( flipNormal * reflectVec.y * 0.5 + 0.5 );\n		sampleUV.x = atan( flipNormal * reflectVec.z, flipNormal * reflectVec.x ) * RECIPROCAL_PI2 + 0.5;\n		vec4 envColor = texture2D( envMap, sampleUV );\n\n	#elif defined( ENVMAP_TYPE_SPHERE )\n		vec3 reflectView = flipNormal * normalize((viewMatrix * vec4( reflectVec, 0.0 )).xyz + vec3(0.0,0.0,1.0));\n		vec4 envColor = texture2D( envMap, reflectView.xy * 0.5 + 0.5 );\n	#endif\n\n	envColor.xyz = inputToLinear( envColor.xyz );\n\n	#ifdef ENVMAP_BLENDING_MULTIPLY\n\n		outgoingLight = mix( outgoingLight, outgoingLight * envColor.xyz, specularStrength * reflectivity );\n\n	#elif defined( ENVMAP_BLENDING_MIX )\n\n		outgoingLight = mix( outgoingLight, envColor.xyz, specularStrength * reflectivity );\n\n	#elif defined( ENVMAP_BLENDING_ADD )\n\n		outgoingLight += envColor.xyz * specularStrength * reflectivity;\n\n	#endif\n\n#endif\n",THREE.ShaderChunk.specularmap_pars_fragment = "#ifdef USE_SPECULARMAP\n\n	uniform sampler2D specularMap;\n\n#endif",THREE.ShaderChunk.logdepthbuf_vertex = "#ifdef USE_LOGDEPTHBUF\n\n	gl_Position.z = log2(max( EPSILON, gl_Position.w + 1.0 )) * logDepthBufFC;\n\n	#ifdef USE_LOGDEPTHBUF_EXT\n\n		vFragDepth = 1.0 + gl_Position.w;\n\n#else\n\n		gl_Position.z = (gl_Position.z - 1.0) * gl_Position.w;\n\n	#endif\n\n#endif",THREE.ShaderChunk.morphtarget_pars_vertex = "#ifdef USE_MORPHTARGETS\n\n	#ifndef USE_MORPHNORMALS\n\n	uniform float morphTargetInfluences[ 8 ];\n\n	#else\n\n	uniform float morphTargetInfluences[ 4 ];\n\n	#endif\n\n#endif",THREE.ShaderChunk.specularmap_fragment = "float specularStrength;\n\n#ifdef USE_SPECULARMAP\n\n	vec4 texelSpecular = texture2D( specularMap, vUv );\n	specularStrength = texelSpecular.r;\n\n#else\n\n	specularStrength = 1.0;\n\n#endif",THREE.ShaderChunk.fog_fragment = "#ifdef USE_FOG\n\n	#ifdef USE_LOGDEPTHBUF_EXT\n\n		float depth = gl_FragDepthEXT / gl_FragCoord.w;\n\n	#else\n\n		float depth = gl_FragCoord.z / gl_FragCoord.w;\n\n	#endif\n\n	#ifdef FOG_EXP2\n\n		float fogFactor = exp2( - square( fogDensity ) * square( depth ) * LOG2 );\n		fogFactor = whiteCompliment( fogFactor );\n\n	#else\n\n		float fogFactor = smoothstep( fogNear, fogFar, depth );\n\n	#endif\n	\n	outgoingLight = mix( outgoingLight, fogColor, fogFactor );\n\n#endif",THREE.ShaderChunk.bumpmap_pars_fragment = "#ifdef USE_BUMPMAP\n\n	uniform sampler2D bumpMap;\n	uniform float bumpScale;\n\n	// Derivative maps - bump mapping unparametrized surfaces by Morten Mikkelsen\n	// http://mmikkelsen3d.blogspot.sk/2011/07/derivative-maps.html\n\n	// Evaluate the derivative of the height w.r.t. screen-space using forward differencing (listing 2)\n\n	vec2 dHdxy_fwd() {\n\n		vec2 dSTdx = dFdx( vUv );\n		vec2 dSTdy = dFdy( vUv );\n\n		float Hll = bumpScale * texture2D( bumpMap, vUv ).x;\n		float dBx = bumpScale * texture2D( bumpMap, vUv + dSTdx ).x - Hll;\n		float dBy = bumpScale * texture2D( bumpMap, vUv + dSTdy ).x - Hll;\n\n		return vec2( dBx, dBy );\n\n	}\n\n	vec3 perturbNormalArb( vec3 surf_pos, vec3 surf_norm, vec2 dHdxy ) {\n\n		vec3 vSigmaX = dFdx( surf_pos );\n		vec3 vSigmaY = dFdy( surf_pos );\n		vec3 vN = surf_norm;		// normalized\n\n		vec3 R1 = cross( vSigmaY, vN );\n		vec3 R2 = cross( vN, vSigmaX );\n\n		float fDet = dot( vSigmaX, R1 );\n\n		vec3 vGrad = sign( fDet ) * ( dHdxy.x * R1 + dHdxy.y * R2 );\n		return normalize( abs( fDet ) * surf_norm - vGrad );\n\n	}\n\n#endif\n",THREE.ShaderChunk.defaultnormal_vertex = "#ifdef USE_SKINNING\n\n	vec3 objectNormal = skinnedNormal.xyz;\n\n#elif defined( USE_MORPHNORMALS )\n\n	vec3 objectNormal = morphedNormal;\n\n#else\n\n	vec3 objectNormal = normal;\n\n#endif\n\n#ifdef FLIP_SIDED\n\n	objectNormal = -objectNormal;\n\n#endif\n\nvec3 transformedNormal = normalMatrix * objectNormal;\n",THREE.ShaderChunk.lights_phong_pars_fragment = "uniform vec3 ambientLightColor;\n\n#if MAX_DIR_LIGHTS > 0\n\n	uniform vec3 directionalLightColor[ MAX_DIR_LIGHTS ];\n	uniform vec3 directionalLightDirection[ MAX_DIR_LIGHTS ];\n\n#endif\n\n#if MAX_HEMI_LIGHTS > 0\n\n	uniform vec3 hemisphereLightSkyColor[ MAX_HEMI_LIGHTS ];\n	uniform vec3 hemisphereLightGroundColor[ MAX_HEMI_LIGHTS ];\n	uniform vec3 hemisphereLightDirection[ MAX_HEMI_LIGHTS ];\n\n#endif\n\n#if MAX_POINT_LIGHTS > 0\n\n	uniform vec3 pointLightColor[ MAX_POINT_LIGHTS ];\n\n	uniform vec3 pointLightPosition[ MAX_POINT_LIGHTS ];\n	uniform float pointLightDistance[ MAX_POINT_LIGHTS ];\n	uniform float pointLightDecay[ MAX_POINT_LIGHTS ];\n\n#endif\n\n#if MAX_SPOT_LIGHTS > 0\n\n	uniform vec3 spotLightColor[ MAX_SPOT_LIGHTS ];\n	uniform vec3 spotLightPosition[ MAX_SPOT_LIGHTS ];\n	uniform vec3 spotLightDirection[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightAngleCos[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightExponent[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightDistance[ MAX_SPOT_LIGHTS ];\n	uniform float spotLightDecay[ MAX_SPOT_LIGHTS ];\n\n#endif\n\n#if MAX_SPOT_LIGHTS > 0 || defined( USE_BUMPMAP ) || defined( USE_ENVMAP )\n\n	varying vec3 vWorldPosition;\n\n#endif\n\n#ifdef WRAP_AROUND\n\n	uniform vec3 wrapRGB;\n\n#endif\n\nvarying vec3 vViewPosition;\n\n#ifndef FLAT_SHADED\n\n	varying vec3 vNormal;\n\n#endif\n",THREE.ShaderChunk.skinbase_vertex = "#ifdef USE_SKINNING\n\n	mat4 boneMatX = getBoneMatrix( skinIndex.x );\n	mat4 boneMatY = getBoneMatrix( skinIndex.y );\n	mat4 boneMatZ = getBoneMatrix( skinIndex.z );\n	mat4 boneMatW = getBoneMatrix( skinIndex.w );\n\n#endif",THREE.ShaderChunk.map_vertex = "#if defined( USE_MAP ) || defined( USE_BUMPMAP ) || defined( USE_NORMALMAP ) || defined( USE_SPECULARMAP ) || defined( USE_ALPHAMAP )\n\n	vUv = uv * offsetRepeat.zw + offsetRepeat.xy;\n\n#endif",THREE.ShaderChunk.lightmap_fragment = "#ifdef USE_LIGHTMAP\n\n	outgoingLight *= diffuseColor.xyz * texture2D( lightMap, vUv2 ).xyz;\n\n#endif",THREE.ShaderChunk.shadowmap_pars_vertex = "#ifdef USE_SHADOWMAP\n\n	varying vec4 vShadowCoord[ MAX_SHADOWS ];\n	uniform mat4 shadowMatrix[ MAX_SHADOWS ];\n\n#endif",THREE.ShaderChunk.color_fragment = "#ifdef USE_COLOR\n\n	diffuseColor.rgb *= vColor;\n\n#endif",THREE.ShaderChunk.morphtarget_vertex = "#ifdef USE_MORPHTARGETS\n\n	vec3 morphed = vec3( 0.0 );\n	morphed += ( morphTarget0 - position ) * morphTargetInfluences[ 0 ];\n	morphed += ( morphTarget1 - position ) * morphTargetInfluences[ 1 ];\n	morphed += ( morphTarget2 - position ) * morphTargetInfluences[ 2 ];\n	morphed += ( morphTarget3 - position ) * morphTargetInfluences[ 3 ];\n\n	#ifndef USE_MORPHNORMALS\n\n	morphed += ( morphTarget4 - position ) * morphTargetInfluences[ 4 ];\n	morphed += ( morphTarget5 - position ) * morphTargetInfluences[ 5 ];\n	morphed += ( morphTarget6 - position ) * morphTargetInfluences[ 6 ];\n	morphed += ( morphTarget7 - position ) * morphTargetInfluences[ 7 ];\n\n	#endif\n\n	morphed += position;\n\n#endif",THREE.ShaderChunk.envmap_vertex = "#if defined( USE_ENVMAP ) && ! defined( USE_BUMPMAP ) && ! defined( USE_NORMALMAP ) && ! defined( PHONG )\n\n	vec3 worldNormal = transformDirection( objectNormal, modelMatrix );\n\n	vec3 cameraToVertex = normalize( worldPosition.xyz - cameraPosition );\n\n	#ifdef ENVMAP_MODE_REFLECTION\n\n		vReflect = reflect( cameraToVertex, worldNormal );\n\n	#else\n\n		vReflect = refract( cameraToVertex, worldNormal, refractionRatio );\n\n	#endif\n\n#endif\n",THREE.ShaderChunk.shadowmap_fragment = "#ifdef USE_SHADOWMAP\n\n	#ifdef SHADOWMAP_DEBUG\n\n		vec3 frustumColors[3];\n		frustumColors[0] = vec3( 1.0, 0.5, 0.0 );\n		frustumColors[1] = vec3( 0.0, 1.0, 0.8 );\n		frustumColors[2] = vec3( 0.0, 0.5, 1.0 );\n\n	#endif\n\n	#ifdef SHADOWMAP_CASCADE\n\n		int inFrustumCount = 0;\n\n	#endif\n\n	float fDepth;\n	vec3 shadowColor = vec3( 1.0 );\n\n	for( int i = 0; i < MAX_SHADOWS; i ++ ) {\n\n		vec3 shadowCoord = vShadowCoord[ i ].xyz / vShadowCoord[ i ].w;\n\n				// if ( something && something ) breaks ATI OpenGL shader compiler\n				// if ( all( something, something ) ) using this instead\n\n		bvec4 inFrustumVec = bvec4 ( shadowCoord.x >= 0.0, shadowCoord.x <= 1.0, shadowCoord.y >= 0.0, shadowCoord.y <= 1.0 );\n		bool inFrustum = all( inFrustumVec );\n\n				// don't shadow pixels outside of light frustum\n				// use just first frustum (for cascades)\n				// don't shadow pixels behind far plane of light frustum\n\n		#ifdef SHADOWMAP_CASCADE\n\n			inFrustumCount += int( inFrustum );\n			bvec3 frustumTestVec = bvec3( inFrustum, inFrustumCount == 1, shadowCoord.z <= 1.0 );\n\n		#else\n\n			bvec2 frustumTestVec = bvec2( inFrustum, shadowCoord.z <= 1.0 );\n\n		#endif\n\n		bool frustumTest = all( frustumTestVec );\n\n		if ( frustumTest ) {\n\n			shadowCoord.z += shadowBias[ i ];\n\n			#if defined( SHADOWMAP_TYPE_PCF )\n\n						// Percentage-close filtering\n						// (9 pixel kernel)\n						// http://fabiensanglard.net/shadowmappingPCF/\n\n				float shadow = 0.0;\n\n		/*\n						// nested loops breaks shader compiler / validator on some ATI cards when using OpenGL\n						// must enroll loop manually\n\n				for ( float y = -1.25; y <= 1.25; y += 1.25 )\n					for ( float x = -1.25; x <= 1.25; x += 1.25 ) {\n\n						vec4 rgbaDepth = texture2D( shadowMap[ i ], vec2( x * xPixelOffset, y * yPixelOffset ) + shadowCoord.xy );\n\n								// doesn't seem to produce any noticeable visual difference compared to simple texture2D lookup\n								//vec4 rgbaDepth = texture2DProj( shadowMap[ i ], vec4( vShadowCoord[ i ].w * ( vec2( x * xPixelOffset, y * yPixelOffset ) + shadowCoord.xy ), 0.05, vShadowCoord[ i ].w ) );\n\n						float fDepth = unpackDepth( rgbaDepth );\n\n						if ( fDepth < shadowCoord.z )\n							shadow += 1.0;\n\n				}\n\n				shadow /= 9.0;\n\n		*/\n\n				const float shadowDelta = 1.0 / 9.0;\n\n				float xPixelOffset = 1.0 / shadowMapSize[ i ].x;\n				float yPixelOffset = 1.0 / shadowMapSize[ i ].y;\n\n				float dx0 = -1.25 * xPixelOffset;\n				float dy0 = -1.25 * yPixelOffset;\n				float dx1 = 1.25 * xPixelOffset;\n				float dy1 = 1.25 * yPixelOffset;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx0, dy0 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( 0.0, dy0 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx1, dy0 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx0, 0.0 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx1, 0.0 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx0, dy1 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( 0.0, dy1 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				fDepth = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx1, dy1 ) ) );\n				if ( fDepth < shadowCoord.z ) shadow += shadowDelta;\n\n				shadowColor = shadowColor * vec3( ( 1.0 - shadowDarkness[ i ] * shadow ) );\n\n			#elif defined( SHADOWMAP_TYPE_PCF_SOFT )\n\n						// Percentage-close filtering\n						// (9 pixel kernel)\n						// http://fabiensanglard.net/shadowmappingPCF/\n\n				float shadow = 0.0;\n\n				float xPixelOffset = 1.0 / shadowMapSize[ i ].x;\n				float yPixelOffset = 1.0 / shadowMapSize[ i ].y;\n\n				float dx0 = -1.0 * xPixelOffset;\n				float dy0 = -1.0 * yPixelOffset;\n				float dx1 = 1.0 * xPixelOffset;\n				float dy1 = 1.0 * yPixelOffset;\n\n				mat3 shadowKernel;\n				mat3 depthKernel;\n\n				depthKernel[0][0] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx0, dy0 ) ) );\n				depthKernel[0][1] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx0, 0.0 ) ) );\n				depthKernel[0][2] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx0, dy1 ) ) );\n				depthKernel[1][0] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( 0.0, dy0 ) ) );\n				depthKernel[1][1] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy ) );\n				depthKernel[1][2] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( 0.0, dy1 ) ) );\n				depthKernel[2][0] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx1, dy0 ) ) );\n				depthKernel[2][1] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx1, 0.0 ) ) );\n				depthKernel[2][2] = unpackDepth( texture2D( shadowMap[ i ], shadowCoord.xy + vec2( dx1, dy1 ) ) );\n\n				vec3 shadowZ = vec3( shadowCoord.z );\n				shadowKernel[0] = vec3(lessThan(depthKernel[0], shadowZ ));\n				shadowKernel[0] *= vec3(0.25);\n\n				shadowKernel[1] = vec3(lessThan(depthKernel[1], shadowZ ));\n				shadowKernel[1] *= vec3(0.25);\n\n				shadowKernel[2] = vec3(lessThan(depthKernel[2], shadowZ ));\n				shadowKernel[2] *= vec3(0.25);\n\n				vec2 fractionalCoord = 1.0 - fract( shadowCoord.xy * shadowMapSize[i].xy );\n\n				shadowKernel[0] = mix( shadowKernel[1], shadowKernel[0], fractionalCoord.x );\n				shadowKernel[1] = mix( shadowKernel[2], shadowKernel[1], fractionalCoord.x );\n\n				vec4 shadowValues;\n				shadowValues.x = mix( shadowKernel[0][1], shadowKernel[0][0], fractionalCoord.y );\n				shadowValues.y = mix( shadowKernel[0][2], shadowKernel[0][1], fractionalCoord.y );\n				shadowValues.z = mix( shadowKernel[1][1], shadowKernel[1][0], fractionalCoord.y );\n				shadowValues.w = mix( shadowKernel[1][2], shadowKernel[1][1], fractionalCoord.y );\n\n				shadow = dot( shadowValues, vec4( 1.0 ) );\n\n				shadowColor = shadowColor * vec3( ( 1.0 - shadowDarkness[ i ] * shadow ) );\n\n			#else\n\n				vec4 rgbaDepth = texture2D( shadowMap[ i ], shadowCoord.xy );\n				float fDepth = unpackDepth( rgbaDepth );\n\n				if ( fDepth < shadowCoord.z )\n\n		// spot with multiple shadows is darker\n\n					shadowColor = shadowColor * vec3( 1.0 - shadowDarkness[ i ] );\n\n		// spot with multiple shadows has the same color as single shadow spot\n\n		// 					shadowColor = min( shadowColor, vec3( shadowDarkness[ i ] ) );\n\n			#endif\n\n		}\n\n\n		#ifdef SHADOWMAP_DEBUG\n\n			#ifdef SHADOWMAP_CASCADE\n\n				if ( inFrustum && inFrustumCount == 1 ) outgoingLight *= frustumColors[ i ];\n\n			#else\n\n				if ( inFrustum ) outgoingLight *= frustumColors[ i ];\n\n			#endif\n\n		#endif\n\n	}\n\n	// NOTE: I am unsure if this is correct in linear space.  -bhouston, Dec 29, 2014\n	shadowColor = inputToLinear( shadowColor );\n\n	outgoingLight = outgoingLight * shadowColor;\n\n#endif\n",THREE.ShaderChunk.worldpos_vertex = "#if defined( USE_ENVMAP ) || defined( PHONG ) || defined( LAMBERT ) || defined ( USE_SHADOWMAP )\n\n	#ifdef USE_SKINNING\n\n		vec4 worldPosition = modelMatrix * skinned;\n\n	#elif defined( USE_MORPHTARGETS )\n\n		vec4 worldPosition = modelMatrix * vec4( morphed, 1.0 );\n\n	#else\n\n		vec4 worldPosition = modelMatrix * vec4( position, 1.0 );\n\n	#endif\n\n#endif\n",THREE.ShaderChunk.shadowmap_pars_fragment = "#ifdef USE_SHADOWMAP\n\n	uniform sampler2D shadowMap[ MAX_SHADOWS ];\n	uniform vec2 shadowMapSize[ MAX_SHADOWS ];\n\n	uniform float shadowDarkness[ MAX_SHADOWS ];\n	uniform float shadowBias[ MAX_SHADOWS ];\n\n	varying vec4 vShadowCoord[ MAX_SHADOWS ];\n\n	float unpackDepth( const in vec4 rgba_depth ) {\n\n		const vec4 bit_shift = vec4( 1.0 / ( 256.0 * 256.0 * 256.0 ), 1.0 / ( 256.0 * 256.0 ), 1.0 / 256.0, 1.0 );\n		float depth = dot( rgba_depth, bit_shift );\n		return depth;\n\n	}\n\n#endif",THREE.ShaderChunk.skinning_pars_vertex = "#ifdef USE_SKINNING\n\n	uniform mat4 bindMatrix;\n	uniform mat4 bindMatrixInverse;\n\n	#ifdef BONE_TEXTURE\n\n		uniform sampler2D boneTexture;\n		uniform int boneTextureWidth;\n		uniform int boneTextureHeight;\n\n		mat4 getBoneMatrix( const in float i ) {\n\n			float j = i * 4.0;\n			float x = mod( j, float( boneTextureWidth ) );\n			float y = floor( j / float( boneTextureWidth ) );\n\n			float dx = 1.0 / float( boneTextureWidth );\n			float dy = 1.0 / float( boneTextureHeight );\n\n			y = dy * ( y + 0.5 );\n\n			vec4 v1 = texture2D( boneTexture, vec2( dx * ( x + 0.5 ), y ) );\n			vec4 v2 = texture2D( boneTexture, vec2( dx * ( x + 1.5 ), y ) );\n			vec4 v3 = texture2D( boneTexture, vec2( dx * ( x + 2.5 ), y ) );\n			vec4 v4 = texture2D( boneTexture, vec2( dx * ( x + 3.5 ), y ) );\n\n			mat4 bone = mat4( v1, v2, v3, v4 );\n\n			return bone;\n\n		}\n\n	#else\n\n		uniform mat4 boneGlobalMatrices[ MAX_BONES ];\n\n		mat4 getBoneMatrix( const in float i ) {\n\n			mat4 bone = boneGlobalMatrices[ int(i) ];\n			return bone;\n\n		}\n\n	#endif\n\n#endif\n",THREE.ShaderChunk.logdepthbuf_pars_fragment = "#ifdef USE_LOGDEPTHBUF\n\n	uniform float logDepthBufFC;\n\n	#ifdef USE_LOGDEPTHBUF_EXT\n\n		#extension GL_EXT_frag_depth : enable\n		varying float vFragDepth;\n\n	#endif\n\n#endif",THREE.ShaderChunk.alphamap_fragment = "#ifdef USE_ALPHAMAP\n\n	diffuseColor.a *= texture2D( alphaMap, vUv ).g;\n\n#endif\n",THREE.ShaderChunk.alphamap_pars_fragment = "#ifdef USE_ALPHAMAP\n\n	uniform sampler2D alphaMap;\n\n#endif\n",THREE.UniformsUtils = {
    merge: function (a) {
        for (var b = {}, c = 0; c < a.length; c++) {
            var e, d = this.clone(a[c]);
            for (e in d)b[e] = d[e]
        }
        return b
    }, clone: function (a) {
        var c, b = {};
        for (c in a) {
            b[c] = {};
            for (var d in a[c]) {
                var e = a[c][d];
                b[c][d] = e instanceof THREE.Color || e instanceof THREE.Vector2 || e instanceof THREE.Vector3 || e instanceof THREE.Vector4 || e instanceof THREE.Matrix4 || e instanceof THREE.Texture ? e.clone() : e instanceof Array ? e.slice() : e
            }
        }
        return b
    }
},THREE.UniformsLib = {
    common: {
        diffuse: {type: "c", value: new THREE.Color(15658734)},
        opacity: {type: "f", value: 1},
        map: {type: "t", value: null},
        offsetRepeat: {type: "v4", value: new THREE.Vector4(0, 0, 1, 1)},
        lightMap: {type: "t", value: null},
        specularMap: {type: "t", value: null},
        alphaMap: {type: "t", value: null},
        envMap: {type: "t", value: null},
        flipEnvMap: {type: "f", value: -1},
        reflectivity: {type: "f", value: 1},
        refractionRatio: {type: "f", value: .98},
        morphTargetInfluences: {type: "f", value: 0}
    },
    bump: {bumpMap: {type: "t", value: null}, bumpScale: {type: "f", value: 1}},
    normalmap: {normalMap: {type: "t", value: null}, normalScale: {type: "v2", value: new THREE.Vector2(1, 1)}},
    fog: {
        fogDensity: {type: "f", value: 25e-5},
        fogNear: {type: "f", value: 1},
        fogFar: {type: "f", value: 2e3},
        fogColor: {type: "c", value: new THREE.Color(16777215)}
    },
    lights: {
        ambientLightColor: {type: "fv", value: []},
        directionalLightDirection: {type: "fv", value: []},
        directionalLightColor: {type: "fv", value: []},
        hemisphereLightDirection: {type: "fv", value: []},
        hemisphereLightSkyColor: {type: "fv", value: []},
        hemisphereLightGroundColor: {type: "fv", value: []},
        pointLightColor: {type: "fv", value: []},
        pointLightPosition: {type: "fv", value: []},
        pointLightDistance: {type: "fv1", value: []},
        pointLightDecay: {type: "fv1", value: []},
        spotLightColor: {type: "fv", value: []},
        spotLightPosition: {type: "fv", value: []},
        spotLightDirection: {type: "fv", value: []},
        spotLightDistance: {type: "fv1", value: []},
        spotLightAngleCos: {type: "fv1", value: []},
        spotLightExponent: {type: "fv1", value: []},
        spotLightDecay: {type: "fv1", value: []}
    },
    particle: {
        psColor: {type: "c", value: new THREE.Color(15658734)},
        opacity: {type: "f", value: 1},
        size: {type: "f", value: 1},
        scale: {type: "f", value: 1},
        map: {type: "t", value: null},
        offsetRepeat: {type: "v4", value: new THREE.Vector4(0, 0, 1, 1)},
        fogDensity: {type: "f", value: 25e-5},
        fogNear: {type: "f", value: 1},
        fogFar: {type: "f", value: 2e3},
        fogColor: {type: "c", value: new THREE.Color(16777215)}
    },
    shadowmap: {
        shadowMap: {type: "tv", value: []},
        shadowMapSize: {type: "v2v", value: []},
        shadowBias: {type: "fv1", value: []},
        shadowDarkness: {type: "fv1", value: []},
        shadowMatrix: {type: "m4v", value: []}
    }
},THREE.ShaderLib = {
    basic: {
        uniforms: THREE.UniformsUtils.merge([THREE.UniformsLib.common, THREE.UniformsLib.fog, THREE.UniformsLib.shadowmap]),
        vertexShader: [THREE.ShaderChunk.common, THREE.ShaderChunk.map_pars_vertex, THREE.ShaderChunk.lightmap_pars_vertex, THREE.ShaderChunk.envmap_pars_vertex, THREE.ShaderChunk.color_pars_vertex, THREE.ShaderChunk.morphtarget_pars_vertex, THREE.ShaderChunk.skinning_pars_vertex, THREE.ShaderChunk.shadowmap_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {", THREE.ShaderChunk.map_vertex, THREE.ShaderChunk.lightmap_vertex, THREE.ShaderChunk.color_vertex, THREE.ShaderChunk.skinbase_vertex, "	#ifdef USE_ENVMAP", THREE.ShaderChunk.morphnormal_vertex, THREE.ShaderChunk.skinnormal_vertex, THREE.ShaderChunk.defaultnormal_vertex, "	#endif", THREE.ShaderChunk.morphtarget_vertex, THREE.ShaderChunk.skinning_vertex, THREE.ShaderChunk.default_vertex, THREE.ShaderChunk.logdepthbuf_vertex, THREE.ShaderChunk.worldpos_vertex, THREE.ShaderChunk.envmap_vertex, THREE.ShaderChunk.shadowmap_vertex, "}"].join("\n"),
        fragmentShader: ["uniform vec3 diffuse;\nuniform float opacity;", THREE.ShaderChunk.common, THREE.ShaderChunk.color_pars_fragment, THREE.ShaderChunk.map_pars_fragment, THREE.ShaderChunk.alphamap_pars_fragment, THREE.ShaderChunk.lightmap_pars_fragment, THREE.ShaderChunk.envmap_pars_fragment, THREE.ShaderChunk.fog_pars_fragment, THREE.ShaderChunk.shadowmap_pars_fragment, THREE.ShaderChunk.specularmap_pars_fragment, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\n	vec3 outgoingLight = vec3( 0.0 );\n	vec4 diffuseColor = vec4( diffuse, opacity );", THREE.ShaderChunk.logdepthbuf_fragment, THREE.ShaderChunk.map_fragment, THREE.ShaderChunk.color_fragment, THREE.ShaderChunk.alphamap_fragment, THREE.ShaderChunk.alphatest_fragment, THREE.ShaderChunk.specularmap_fragment, "	outgoingLight = diffuseColor.rgb;", THREE.ShaderChunk.lightmap_fragment, THREE.ShaderChunk.envmap_fragment, THREE.ShaderChunk.shadowmap_fragment, THREE.ShaderChunk.linear_to_gamma_fragment, THREE.ShaderChunk.fog_fragment, "	gl_FragColor = vec4( outgoingLight, diffuseColor.a );\n}"].join("\n")
    },
    lambert: {
        uniforms: THREE.UniformsUtils.merge([THREE.UniformsLib.common, THREE.UniformsLib.fog, THREE.UniformsLib.lights, THREE.UniformsLib.shadowmap, {
            emissive: {
                type: "c",
                value: new THREE.Color(0)
            }, wrapRGB: {type: "v3", value: new THREE.Vector3(1, 1, 1)}
        }]),
        vertexShader: ["#define LAMBERT\nvarying vec3 vLightFront;\n#ifdef DOUBLE_SIDED\n	varying vec3 vLightBack;\n#endif", THREE.ShaderChunk.common, THREE.ShaderChunk.map_pars_vertex, THREE.ShaderChunk.lightmap_pars_vertex, THREE.ShaderChunk.envmap_pars_vertex, THREE.ShaderChunk.lights_lambert_pars_vertex, THREE.ShaderChunk.color_pars_vertex, THREE.ShaderChunk.morphtarget_pars_vertex, THREE.ShaderChunk.skinning_pars_vertex, THREE.ShaderChunk.shadowmap_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {", THREE.ShaderChunk.map_vertex, THREE.ShaderChunk.lightmap_vertex, THREE.ShaderChunk.color_vertex, THREE.ShaderChunk.morphnormal_vertex, THREE.ShaderChunk.skinbase_vertex, THREE.ShaderChunk.skinnormal_vertex, THREE.ShaderChunk.defaultnormal_vertex, THREE.ShaderChunk.morphtarget_vertex, THREE.ShaderChunk.skinning_vertex, THREE.ShaderChunk.default_vertex, THREE.ShaderChunk.logdepthbuf_vertex, THREE.ShaderChunk.worldpos_vertex, THREE.ShaderChunk.envmap_vertex, THREE.ShaderChunk.lights_lambert_vertex, THREE.ShaderChunk.shadowmap_vertex, "}"].join("\n"),
        fragmentShader: ["uniform vec3 diffuse;\nuniform vec3 emissive;\nuniform float opacity;\nvarying vec3 vLightFront;\n#ifdef DOUBLE_SIDED\n	varying vec3 vLightBack;\n#endif", THREE.ShaderChunk.common, THREE.ShaderChunk.color_pars_fragment, THREE.ShaderChunk.map_pars_fragment, THREE.ShaderChunk.alphamap_pars_fragment, THREE.ShaderChunk.lightmap_pars_fragment, THREE.ShaderChunk.envmap_pars_fragment, THREE.ShaderChunk.fog_pars_fragment, THREE.ShaderChunk.shadowmap_pars_fragment, THREE.ShaderChunk.specularmap_pars_fragment, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\n	vec3 outgoingLight = vec3( 0.0 );\n	vec4 diffuseColor = vec4( diffuse, opacity );", THREE.ShaderChunk.logdepthbuf_fragment, THREE.ShaderChunk.map_fragment, THREE.ShaderChunk.color_fragment, THREE.ShaderChunk.alphamap_fragment, THREE.ShaderChunk.alphatest_fragment, THREE.ShaderChunk.specularmap_fragment, "	#ifdef DOUBLE_SIDED\n		if ( gl_FrontFacing )\n			outgoingLight += diffuseColor.rgb * vLightFront + emissive;\n		else\n			outgoingLight += diffuseColor.rgb * vLightBack + emissive;\n	#else\n		outgoingLight += diffuseColor.rgb * vLightFront + emissive;\n	#endif", THREE.ShaderChunk.lightmap_fragment, THREE.ShaderChunk.envmap_fragment, THREE.ShaderChunk.shadowmap_fragment, THREE.ShaderChunk.linear_to_gamma_fragment, THREE.ShaderChunk.fog_fragment, "	gl_FragColor = vec4( outgoingLight, diffuseColor.a );\n}"].join("\n")
    },
    phong: {
        uniforms: THREE.UniformsUtils.merge([THREE.UniformsLib.common, THREE.UniformsLib.bump, THREE.UniformsLib.normalmap, THREE.UniformsLib.fog, THREE.UniformsLib.lights, THREE.UniformsLib.shadowmap, {
            emissive: {
                type: "c",
                value: new THREE.Color(0)
            },
            specular: {type: "c", value: new THREE.Color(1118481)},
            shininess: {type: "f", value: 30},
            wrapRGB: {type: "v3", value: new THREE.Vector3(1, 1, 1)}
        }]),
        vertexShader: ["#define PHONG\nvarying vec3 vViewPosition;\n#ifndef FLAT_SHADED\n	varying vec3 vNormal;\n#endif", THREE.ShaderChunk.common, THREE.ShaderChunk.map_pars_vertex, THREE.ShaderChunk.lightmap_pars_vertex, THREE.ShaderChunk.envmap_pars_vertex, THREE.ShaderChunk.lights_phong_pars_vertex, THREE.ShaderChunk.color_pars_vertex, THREE.ShaderChunk.morphtarget_pars_vertex, THREE.ShaderChunk.skinning_pars_vertex, THREE.ShaderChunk.shadowmap_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {", THREE.ShaderChunk.map_vertex, THREE.ShaderChunk.lightmap_vertex, THREE.ShaderChunk.color_vertex, THREE.ShaderChunk.morphnormal_vertex, THREE.ShaderChunk.skinbase_vertex, THREE.ShaderChunk.skinnormal_vertex, THREE.ShaderChunk.defaultnormal_vertex, "#ifndef FLAT_SHADED\n	vNormal = normalize( transformedNormal );\n#endif", THREE.ShaderChunk.morphtarget_vertex, THREE.ShaderChunk.skinning_vertex, THREE.ShaderChunk.default_vertex, THREE.ShaderChunk.logdepthbuf_vertex, "	vViewPosition = -mvPosition.xyz;", THREE.ShaderChunk.worldpos_vertex, THREE.ShaderChunk.envmap_vertex, THREE.ShaderChunk.lights_phong_vertex, THREE.ShaderChunk.shadowmap_vertex, "}"].join("\n"),
        fragmentShader: ["#define PHONG\nuniform vec3 diffuse;\nuniform vec3 emissive;\nuniform vec3 specular;\nuniform float shininess;\nuniform float opacity;", THREE.ShaderChunk.common, THREE.ShaderChunk.color_pars_fragment, THREE.ShaderChunk.map_pars_fragment, THREE.ShaderChunk.alphamap_pars_fragment, THREE.ShaderChunk.lightmap_pars_fragment, THREE.ShaderChunk.envmap_pars_fragment, THREE.ShaderChunk.fog_pars_fragment, THREE.ShaderChunk.lights_phong_pars_fragment, THREE.ShaderChunk.shadowmap_pars_fragment, THREE.ShaderChunk.bumpmap_pars_fragment, THREE.ShaderChunk.normalmap_pars_fragment, THREE.ShaderChunk.specularmap_pars_fragment, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\n	vec3 outgoingLight = vec3( 0.0 );\n	vec4 diffuseColor = vec4( diffuse, opacity );", THREE.ShaderChunk.logdepthbuf_fragment, THREE.ShaderChunk.map_fragment, THREE.ShaderChunk.color_fragment, THREE.ShaderChunk.alphamap_fragment, THREE.ShaderChunk.alphatest_fragment, THREE.ShaderChunk.specularmap_fragment, THREE.ShaderChunk.lights_phong_fragment, THREE.ShaderChunk.lightmap_fragment, THREE.ShaderChunk.envmap_fragment, THREE.ShaderChunk.shadowmap_fragment, THREE.ShaderChunk.linear_to_gamma_fragment, THREE.ShaderChunk.fog_fragment, "	gl_FragColor = vec4( outgoingLight, diffuseColor.a );\n}"].join("\n")
    },
    particle_basic: {
        uniforms: THREE.UniformsUtils.merge([THREE.UniformsLib.particle, THREE.UniformsLib.shadowmap]),
        vertexShader: ["uniform float size;\nuniform float scale;", THREE.ShaderChunk.common, THREE.ShaderChunk.color_pars_vertex, THREE.ShaderChunk.shadowmap_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {", THREE.ShaderChunk.color_vertex, "	vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );\n	#ifdef USE_SIZEATTENUATION\n		gl_PointSize = size * ( scale / length( mvPosition.xyz ) );\n	#else\n		gl_PointSize = size;\n	#endif\n	gl_Position = projectionMatrix * mvPosition;", THREE.ShaderChunk.logdepthbuf_vertex, THREE.ShaderChunk.worldpos_vertex, THREE.ShaderChunk.shadowmap_vertex, "}"].join("\n"),
        fragmentShader: ["uniform vec3 psColor;\nuniform float opacity;", THREE.ShaderChunk.common, THREE.ShaderChunk.color_pars_fragment, THREE.ShaderChunk.map_particle_pars_fragment, THREE.ShaderChunk.fog_pars_fragment, THREE.ShaderChunk.shadowmap_pars_fragment, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\n	vec3 outgoingLight = vec3( 0.0 );\n	vec4 diffuseColor = vec4( psColor, opacity );", THREE.ShaderChunk.logdepthbuf_fragment, THREE.ShaderChunk.map_particle_fragment, THREE.ShaderChunk.color_fragment, THREE.ShaderChunk.alphatest_fragment, "	outgoingLight = diffuseColor.rgb;", THREE.ShaderChunk.shadowmap_fragment, THREE.ShaderChunk.fog_fragment, "	gl_FragColor = vec4( outgoingLight, diffuseColor.a );\n}"].join("\n")
    },
    dashed: {
        uniforms: THREE.UniformsUtils.merge([THREE.UniformsLib.common, THREE.UniformsLib.fog, {
            scale: {
                type: "f",
                value: 1
            }, dashSize: {type: "f", value: 1}, totalSize: {type: "f", value: 2}
        }]),
        vertexShader: ["uniform float scale;\nattribute float lineDistance;\nvarying float vLineDistance;", THREE.ShaderChunk.common, THREE.ShaderChunk.color_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {", THREE.ShaderChunk.color_vertex, "	vLineDistance = scale * lineDistance;\n	vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );\n	gl_Position = projectionMatrix * mvPosition;", THREE.ShaderChunk.logdepthbuf_vertex, "}"].join("\n"),
        fragmentShader: ["uniform vec3 diffuse;\nuniform float opacity;\nuniform float dashSize;\nuniform float totalSize;\nvarying float vLineDistance;", THREE.ShaderChunk.common, THREE.ShaderChunk.color_pars_fragment, THREE.ShaderChunk.fog_pars_fragment, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\n	if ( mod( vLineDistance, totalSize ) > dashSize ) {\n		discard;\n	}\n	vec3 outgoingLight = vec3( 0.0 );\n	vec4 diffuseColor = vec4( diffuse, opacity );", THREE.ShaderChunk.logdepthbuf_fragment, THREE.ShaderChunk.color_fragment, "	outgoingLight = diffuseColor.rgb;", THREE.ShaderChunk.fog_fragment, "	gl_FragColor = vec4( outgoingLight, diffuseColor.a );\n}"].join("\n")
    },
    depth: {
        uniforms: {mNear: {type: "f", value: 1}, mFar: {type: "f", value: 2e3}, opacity: {type: "f", value: 1}},
        vertexShader: [THREE.ShaderChunk.common, THREE.ShaderChunk.morphtarget_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {", THREE.ShaderChunk.morphtarget_vertex, THREE.ShaderChunk.default_vertex, THREE.ShaderChunk.logdepthbuf_vertex, "}"].join("\n"),
        fragmentShader: ["uniform float mNear;\nuniform float mFar;\nuniform float opacity;", THREE.ShaderChunk.common, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {", THREE.ShaderChunk.logdepthbuf_fragment, "	#ifdef USE_LOGDEPTHBUF_EXT\n		float depth = gl_FragDepthEXT / gl_FragCoord.w;\n	#else\n		float depth = gl_FragCoord.z / gl_FragCoord.w;\n	#endif\n	float color = 1.0 - smoothstep( mNear, mFar, depth );\n	gl_FragColor = vec4( vec3( color ), opacity );\n}"].join("\n")
    },
    normal: {
        uniforms: {opacity: {type: "f", value: 1}},
        vertexShader: ["varying vec3 vNormal;", THREE.ShaderChunk.common, THREE.ShaderChunk.morphtarget_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {\n	vNormal = normalize( normalMatrix * normal );", THREE.ShaderChunk.morphtarget_vertex, THREE.ShaderChunk.default_vertex, THREE.ShaderChunk.logdepthbuf_vertex, "}"].join("\n"),
        fragmentShader: ["uniform float opacity;\nvarying vec3 vNormal;", THREE.ShaderChunk.common, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\n	gl_FragColor = vec4( 0.5 * normalize( vNormal ) + 0.5, opacity );", THREE.ShaderChunk.logdepthbuf_fragment, "}"].join("\n")
    },
    cube: {
        uniforms: {tCube: {type: "t", value: null}, tFlip: {type: "f", value: -1}},
        vertexShader: ["varying vec3 vWorldPosition;", THREE.ShaderChunk.common, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {\n	vWorldPosition = transformDirection( position, modelMatrix );\n	gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );", THREE.ShaderChunk.logdepthbuf_vertex, "}"].join("\n"),
        fragmentShader: ["uniform samplerCube tCube;\nuniform float tFlip;\nvarying vec3 vWorldPosition;", THREE.ShaderChunk.common, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\n	gl_FragColor = textureCube( tCube, vec3( tFlip * vWorldPosition.x, vWorldPosition.yz ) );", THREE.ShaderChunk.logdepthbuf_fragment, "}"].join("\n")
    },
    equirect: {
        uniforms: {tEquirect: {type: "t", value: null}, tFlip: {type: "f", value: -1}},
        vertexShader: ["varying vec3 vWorldPosition;", THREE.ShaderChunk.common, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {\n	vWorldPosition = transformDirection( position, modelMatrix );\n	gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );", THREE.ShaderChunk.logdepthbuf_vertex, "}"].join("\n"),
        fragmentShader: ["uniform sampler2D tEquirect;\nuniform float tFlip;\nvarying vec3 vWorldPosition;", THREE.ShaderChunk.common, THREE.ShaderChunk.logdepthbuf_pars_fragment, "void main() {\nvec3 direction = normalize( vWorldPosition );\nvec2 sampleUV;\nsampleUV.y = saturate( tFlip * direction.y * -0.5 + 0.5 );\nsampleUV.x = atan( direction.z, direction.x ) * RECIPROCAL_PI2 + 0.5;\ngl_FragColor = texture2D( tEquirect, sampleUV );", THREE.ShaderChunk.logdepthbuf_fragment, "}"].join("\n")
    },
    depthRGBA: {
        uniforms: {},
        vertexShader: [THREE.ShaderChunk.common, THREE.ShaderChunk.morphtarget_pars_vertex, THREE.ShaderChunk.skinning_pars_vertex, THREE.ShaderChunk.logdepthbuf_pars_vertex, "void main() {", THREE.ShaderChunk.skinbase_vertex, THREE.ShaderChunk.morphtarget_vertex, THREE.ShaderChunk.skinning_vertex, THREE.ShaderChunk.default_vertex, THREE.ShaderChunk.logdepthbuf_vertex, "}"].join("\n"),
        fragmentShader: [THREE.ShaderChunk.common, THREE.ShaderChunk.logdepthbuf_pars_fragment, "vec4 pack_depth( const in float depth ) {\n	const vec4 bit_shift = vec4( 256.0 * 256.0 * 256.0, 256.0 * 256.0, 256.0, 1.0 );\n	const vec4 bit_mask = vec4( 0.0, 1.0 / 256.0, 1.0 / 256.0, 1.0 / 256.0 );\n	vec4 res = mod( depth * bit_shift * vec4( 255 ), vec4( 256 ) ) / vec4( 255 );\n	res -= res.xxyz * bit_mask;\n	return res;\n}\nvoid main() {", THREE.ShaderChunk.logdepthbuf_fragment, "	#ifdef USE_LOGDEPTHBUF_EXT\n		gl_FragData[ 0 ] = pack_depth( gl_FragDepthEXT );\n	#else\n		gl_FragData[ 0 ] = pack_depth( gl_FragCoord.z );\n	#endif\n}"].join("\n")
    }
},THREE.WebGLRenderer = function (a) {
    function b(a) {
        var b = a.geometry;
        a = a.material;
        var c = b.vertices.length;
        if (a.attributes) {
            void 0 === b.__webglCustomAttributesList && (b.__webglCustomAttributesList = []);
            for (var d in a.attributes) {
                var e = a.attributes[d];
                if (!e.__webglInitialized || e.createUniqueBuffers) {
                    e.__webglInitialized = !0;
                    var f = 1;
                    "v2" === e.type ? f = 2 : "v3" === e.type ? f = 3 : "v4" === e.type ? f = 4 : "c" === e.type && (f = 3), e.size = f, e.array = new Float32Array(c * f), e.buffer = m.createBuffer(), e.buffer.belongsToAttribute = d, e.needsUpdate = !0
                }
                b.__webglCustomAttributesList.push(e)
            }
        }
    }

    function c(a, b) {
        return a.material instanceof THREE.MeshFaceMaterial ? a.material.materials[b.materialIndex] : a.material
    }

    function d(a, b, c, d) {
        c = c.attributes;
        var e = b.attributes;
        b = b.attributesKeys;
        for (var f = 0, g = b.length; g > f; f++) {
            var h = b[f], k = e[h];
            if (k >= 0) {
                var n = c[h];
                void 0 !== n ? (h = n.itemSize, m.bindBuffer(m.ARRAY_BUFFER, n.buffer), W.enableAttribute(k), m.vertexAttribPointer(k, h, m.FLOAT, !1, 0, d * h * 4)) : void 0 !== a.defaultAttributeValues && (2 === a.defaultAttributeValues[h].length ? m.vertexAttrib2fv(k, a.defaultAttributeValues[h]) : 3 === a.defaultAttributeValues[h].length && m.vertexAttrib3fv(k, a.defaultAttributeValues[h]))
            }
        }
        W.disableUnusedAttributes()
    }

    function e(a, b) {
        return a.object.renderOrder !== b.object.renderOrder ? a.object.renderOrder - b.object.renderOrder : a.material.id !== b.material.id ? a.material.id - b.material.id : a.z !== b.z ? a.z - b.z : a.id - b.id
    }

    function f(a, b) {
        return a.object.renderOrder !== b.object.renderOrder ? a.object.renderOrder - b.object.renderOrder : a.z !== b.z ? b.z - a.z : a.id - b.id
    }

    function g(a, b) {
        return b[0] - a[0]
    }

    function h(a) {
        if (!1 !== a.visible) {
            if (!(a instanceof THREE.Scene || a instanceof THREE.Group)) {
                void 0 === a.__webglInit && (a.__webglInit = !0, a._modelViewMatrix = new THREE.Matrix4, a._normalMatrix = new THREE.Matrix3, a.addEventListener("removed", wb));
                var c = a.geometry;
                if (void 0 !== c && void 0 === c.__webglInit)if (c.__webglInit = !0, c.addEventListener("dispose", jb), c instanceof THREE.BufferGeometry)B.info.memory.geometries++; else if (a instanceof THREE.Mesh)q(a, c); else if (a instanceof THREE.Line) {
                    if (void 0 === c.__webglVertexBuffer) {
                        c.__webglVertexBuffer = m.createBuffer(), c.__webglColorBuffer = m.createBuffer(), c.__webglLineDistanceBuffer = m.createBuffer(), B.info.memory.geometries++;
                        var d = c.vertices.length;
                        c.__vertexArray = new Float32Array(3 * d), c.__colorArray = new Float32Array(3 * d), c.__lineDistanceArray = new Float32Array(1 * d), c.__webglLineCount = d, b(a), c.verticesNeedUpdate = !0, c.colorsNeedUpdate = !0, c.lineDistancesNeedUpdate = !0
                    }
                } else a instanceof THREE.PointCloud && void 0 === c.__webglVertexBuffer && (c.__webglVertexBuffer = m.createBuffer(), c.__webglColorBuffer = m.createBuffer(), B.info.memory.geometries++, d = c.vertices.length, c.__vertexArray = new Float32Array(3 * d), c.__colorArray = new Float32Array(3 * d), c.__webglParticleCount = d, b(a), c.verticesNeedUpdate = !0, c.colorsNeedUpdate = !0);
                if (void 0 === a.__webglActive)if (a.__webglActive = !0, a instanceof THREE.Mesh) {
                    if (c instanceof THREE.BufferGeometry)n(ba, c, a); else if (c instanceof THREE.Geometry)for (var c = Ua[c.id], d = 0, e = c.length; e > d; d++)n(ba, c[d], a)
                } else a instanceof THREE.Line || a instanceof THREE.PointCloud ? n(ba, c, a) : (a instanceof THREE.ImmediateRenderObject || a.immediateRenderCallback) && qa.push({
                    id: null,
                    object: a,
                    opaque: null,
                    transparent: null,
                    z: 0
                });
                if (a instanceof THREE.Light)ca.push(a); else if (a instanceof THREE.Sprite)Xa.push(a); else if (a instanceof THREE.LensFlare)Ya.push(a); else if ((c = ba[a.id]) && (!1 === a.frustumCulled || !0 === cb.intersectsObject(a)))for (d = 0, e = c.length; e > d; d++) {
                    var f = c[d], g = f, k = g.object, l = g.buffer, p = k.geometry, k = k.material;
                    k instanceof THREE.MeshFaceMaterial ? (k = k.materials[p instanceof THREE.BufferGeometry ? 0 : l.materialIndex], g.material = k, k.transparent ? Qa.push(g) : Ka.push(g)) : k && (g.material = k, k.transparent ? Qa.push(g) : Ka.push(g)), f.render = !0, !0 === B.sortObjects && (wa.setFromMatrixPosition(a.matrixWorld), wa.applyProjection(db), f.z = wa.z)
                }
            }
            for (d = 0, e = a.children.length; e > d; d++)h(a.children[d])
        }
    }

    function k(a, b, c, d, e) {
        for (var f, g = 0, h = a.length; h > g; g++) {
            f = a[g];
            var k = f.object, m = f.buffer;
            if (w(k, b), e)f = e; else {
                if (f = f.material, !f)continue;
                u(f)
            }
            B.setMaterialFaces(f), m instanceof THREE.BufferGeometry ? B.renderBufferDirect(b, c, d, f, m, k) : B.renderBuffer(b, c, d, f, m, k)
        }
    }

    function l(a, b, c, d, e, f) {
        for (var g, h = 0, k = a.length; k > h; h++) {
            g = a[h];
            var m = g.object;
            if (m.visible) {
                if (f)g = f; else {
                    if (g = g[b], !g)continue;
                    u(g)
                }
                B.renderImmediateObject(c, d, e, g, m)
            }
        }
    }

    function p(a) {
        var b = a.object.material;
        b.transparent ? (a.transparent = b, a.opaque = null) : (a.opaque = b, a.transparent = null)
    }

    function q(a, b) {
        var d = a.material, e = !1;
        if (void 0 === Ua[b.id] || !0 === b.groupsNeedUpdate) {
            delete ba[a.id];
            for (var k, q, f = Ua, g = b.id, d = d instanceof THREE.MeshFaceMaterial, h = da.get("OES_element_index_uint") ? 4294967296 : 65535, e = {}, l = b.morphTargets.length, p = b.morphNormals.length, s = {}, t = [], r = 0, w = b.faces.length; w > r; r++) {
                k = b.faces[r];
                var u = d ? k.materialIndex : 0;
                u in e || (e[u] = {hash: u, counter: 0}), k = e[u].hash + "_" + e[u].counter, k in s || (q = {
                    id: Qb++,
                    faces3: [],
                    materialIndex: u,
                    vertices: 0,
                    numMorphTargets: l,
                    numMorphNormals: p
                }, s[k] = q, t.push(q)), s[k].vertices + 3 > h && (e[u].counter += 1, k = e[u].hash + "_" + e[u].counter, k in s || (q = {
                    id: Qb++,
                    faces3: [],
                    materialIndex: u,
                    vertices: 0,
                    numMorphTargets: l,
                    numMorphNormals: p
                }, s[k] = q, t.push(q))), s[k].faces3.push(r), s[k].vertices += 3
            }
            f[g] = t, b.groupsNeedUpdate = !1
        }
        for (f = Ua[b.id], g = 0, d = f.length; d > g; g++) {
            if (h = f[g], void 0 === h.__webglVertexBuffer) {
                if (e = h, e.__webglVertexBuffer = m.createBuffer(), e.__webglNormalBuffer = m.createBuffer(), e.__webglTangentBuffer = m.createBuffer(), e.__webglColorBuffer = m.createBuffer(), e.__webglUVBuffer = m.createBuffer(), e.__webglUV2Buffer = m.createBuffer(), e.__webglSkinIndicesBuffer = m.createBuffer(), e.__webglSkinWeightsBuffer = m.createBuffer(), e.__webglFaceBuffer = m.createBuffer(), e.__webglLineBuffer = m.createBuffer(), p = e.numMorphTargets)for (e.__webglMorphTargetsBuffers = [], l = 0; p > l; l++)e.__webglMorphTargetsBuffers.push(m.createBuffer());
                if (p = e.numMorphNormals)for (e.__webglMorphNormalsBuffers = [], l = 0; p > l; l++)e.__webglMorphNormalsBuffers.push(m.createBuffer());
                if (B.info.memory.geometries++, e = h, r = a, w = r.geometry, p = e.faces3, l = 3 * p.length, s = 1 * p.length, t = 3 * p.length, p = c(r, e), e.__vertexArray = new Float32Array(3 * l), e.__normalArray = new Float32Array(3 * l), e.__colorArray = new Float32Array(3 * l), e.__uvArray = new Float32Array(2 * l), 1 < w.faceVertexUvs.length && (e.__uv2Array = new Float32Array(2 * l)), w.hasTangents && (e.__tangentArray = new Float32Array(4 * l)), r.geometry.skinWeights.length && r.geometry.skinIndices.length && (e.__skinIndexArray = new Float32Array(4 * l), e.__skinWeightArray = new Float32Array(4 * l)), r = null !== da.get("OES_element_index_uint") && s > 21845 ? Uint32Array : Uint16Array, e.__typeArray = r, e.__faceArray = new r(3 * s), e.__lineArray = new r(2 * t), w = e.numMorphTargets)for (e.__morphTargetsArrays = [], r = 0; w > r; r++)e.__morphTargetsArrays.push(new Float32Array(3 * l));
                if (w = e.numMorphNormals)for (e.__morphNormalsArrays = [], r = 0; w > r; r++)e.__morphNormalsArrays.push(new Float32Array(3 * l));
                if (e.__webglFaceCount = 3 * s, e.__webglLineCount = 2 * t, p.attributes)for (s in void 0 === e.__webglCustomAttributesList && (e.__webglCustomAttributesList = []), s = void 0, p.attributes) {
                    var v, t = p.attributes[s], r = {};
                    for (v in t)r[v] = t[v];
                    (!r.__webglInitialized || r.createUniqueBuffers) && (r.__webglInitialized = !0, w = 1, "v2" === r.type ? w = 2 : "v3" === r.type ? w = 3 : "v4" === r.type ? w = 4 : "c" === r.type && (w = 3), r.size = w, r.array = new Float32Array(l * w), r.buffer = m.createBuffer(), r.buffer.belongsToAttribute = s, t.needsUpdate = !0, r.__original = t), e.__webglCustomAttributesList.push(r)
                }
                e.__inittedArrays = !0, b.verticesNeedUpdate = !0, b.morphTargetsNeedUpdate = !0, b.elementsNeedUpdate = !0, b.uvsNeedUpdate = !0, b.normalsNeedUpdate = !0, b.tangentsNeedUpdate = !0, e = b.colorsNeedUpdate = !0
            } else e = !1;
            (e || void 0 === a.__webglActive) && n(ba, h, a)
        }
        a.__webglActive = !0
    }

    function n(a, b, c) {
        var d = c.id;
        a[d] = a[d] || [], a[d].push({id: d, buffer: b, object: c, material: null, z: 0})
    }

    function t(a) {
        var b = a.geometry;
        if (b instanceof THREE.BufferGeometry)for (var d = b.attributes, e = b.attributesKeys, f = 0, g = e.length; g > f; f++) {
            var h = e[f], k = d[h], n = "index" === h ? m.ELEMENT_ARRAY_BUFFER : m.ARRAY_BUFFER;
            void 0 === k.buffer ? (k.buffer = m.createBuffer(), m.bindBuffer(n, k.buffer), m.bufferData(n, k.array, k instanceof THREE.DynamicBufferAttribute ? m.DYNAMIC_DRAW : m.STATIC_DRAW), k.needsUpdate = !1) : !0 === k.needsUpdate && (m.bindBuffer(n, k.buffer), void 0 === k.updateRange || -1 === k.updateRange.count ? m.bufferSubData(n, 0, k.array) : 0 === k.updateRange.count ? console.error("THREE.WebGLRenderer.updateObject: using updateRange for THREE.DynamicBufferAttribute and marked as needsUpdate but count is 0, ensure you are using set methods or updating manually.") : (m.bufferSubData(n, k.updateRange.offset * k.array.BYTES_PER_ELEMENT, k.array.subarray(k.updateRange.offset, k.updateRange.offset + k.updateRange.count)), k.updateRange.count = 0), k.needsUpdate = !1)
        } else if (a instanceof THREE.Mesh) {
            !0 === b.groupsNeedUpdate && q(a, b);
            for (var l = Ua[b.id], f = 0, p = l.length; p > f; f++) {
                var t = l[f], w = c(a, t), u = w.attributes && r(w);
                if (b.verticesNeedUpdate || b.morphTargetsNeedUpdate || b.elementsNeedUpdate || b.uvsNeedUpdate || b.normalsNeedUpdate || b.colorsNeedUpdate || b.tangentsNeedUpdate || u) {
                    var v = t, x = a, D = m.DYNAMIC_DRAW, A = !b.dynamic, E = w;
                    if (v.__inittedArrays) {
                        var G = !1 == E instanceof THREE.MeshPhongMaterial && E.shading === THREE.FlatShading, y = void 0, z = void 0, F = void 0, B = void 0, I = void 0, H = void 0, M = void 0, R = void 0, P = void 0, U = void 0, O = void 0, J = void 0, L = void 0, N = void 0, Ka = void 0, V = void 0, W = void 0, Qa = void 0, Ya = void 0, Xa = void 0, da = void 0, ba = void 0, ja = void 0, Pa = void 0, ka = void 0, Q = void 0, ha = void 0, ia = void 0, ob = void 0, Y = void 0, ub = void 0, pa = void 0, ab = void 0, oa = void 0, ca = void 0, qa = void 0, Ca = void 0, ta = void 0, na = void 0, wa = void 0, La = 0, Ma = 0, kb = 0, yb = 0, zb = 0, Ra = 0, Aa = 0, eb = 0, Ha = 0, la = 0, ra = 0, K = 0, za = void 0, Sa = v.__vertexArray, Ab = v.__uvArray, lb = v.__uv2Array, Na = v.__normalArray, sa = v.__tangentArray, Da = v.__colorArray, Ea = v.__skinIndexArray, Fa = v.__skinWeightArray, Gb = v.__morphTargetsArrays, Bb = v.__morphNormalsArrays, mb = v.__webglCustomAttributesList, C = void 0, Va = v.__faceArray, Ta = v.__lineArray, ea = x.geometry, fb = ea.elementsNeedUpdate, vb = ea.uvsNeedUpdate, Mb = ea.normalsNeedUpdate, Ob = ea.tangentsNeedUpdate, ib = ea.colorsNeedUpdate, sb = ea.morphTargetsNeedUpdate, Cb = ea.vertices, $ = v.faces3, xa = ea.faces, Hb = ea.faceVertexUvs[0], Oa = ea.faceVertexUvs[1], $a = ea.skinIndices, Ga = ea.skinWeights, nb = ea.morphTargets, bb = ea.morphNormals;
                        if (ea.verticesNeedUpdate) {
                            for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], J = Cb[B.a], L = Cb[B.b], N = Cb[B.c], Sa[Ma] = J.x, Sa[Ma + 1] = J.y, Sa[Ma + 2] = J.z, Sa[Ma + 3] = L.x, Sa[Ma + 4] = L.y, Sa[Ma + 5] = L.z, Sa[Ma + 6] = N.x, Sa[Ma + 7] = N.y, Sa[Ma + 8] = N.z, Ma += 9;
                            m.bindBuffer(m.ARRAY_BUFFER, v.__webglVertexBuffer), m.bufferData(m.ARRAY_BUFFER, Sa, D)
                        }
                        if (sb)for (ca = 0, qa = nb.length; qa > ca; ca++) {
                            for (y = ra = 0, z = $.length; z > y; y++)na = $[y], B = xa[na], J = nb[ca].vertices[B.a], L = nb[ca].vertices[B.b], N = nb[ca].vertices[B.c], Ca = Gb[ca], Ca[ra] = J.x, Ca[ra + 1] = J.y, Ca[ra + 2] = J.z, Ca[ra + 3] = L.x, Ca[ra + 4] = L.y, Ca[ra + 5] = L.z, Ca[ra + 6] = N.x, Ca[ra + 7] = N.y, Ca[ra + 8] = N.z, E.morphNormals && (G ? Xa = Ya = Qa = bb[ca].faceNormals[na] : (wa = bb[ca].vertexNormals[na], Qa = wa.a, Ya = wa.b, Xa = wa.c), ta = Bb[ca], ta[ra] = Qa.x, ta[ra + 1] = Qa.y, ta[ra + 2] = Qa.z, ta[ra + 3] = Ya.x, ta[ra + 4] = Ya.y, ta[ra + 5] = Ya.z, ta[ra + 6] = Xa.x, ta[ra + 7] = Xa.y, ta[ra + 8] = Xa.z), ra += 9;
                            m.bindBuffer(m.ARRAY_BUFFER, v.__webglMorphTargetsBuffers[ca]), m.bufferData(m.ARRAY_BUFFER, Gb[ca], D), E.morphNormals && (m.bindBuffer(m.ARRAY_BUFFER, v.__webglMorphNormalsBuffers[ca]), m.bufferData(m.ARRAY_BUFFER, Bb[ca], D))
                        }
                        if (Ga.length) {
                            for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], Pa = Ga[B.a], ka = Ga[B.b], Q = Ga[B.c], Fa[la] = Pa.x, Fa[la + 1] = Pa.y, Fa[la + 2] = Pa.z, Fa[la + 3] = Pa.w, Fa[la + 4] = ka.x, Fa[la + 5] = ka.y, Fa[la + 6] = ka.z, Fa[la + 7] = ka.w, Fa[la + 8] = Q.x, Fa[la + 9] = Q.y, Fa[la + 10] = Q.z, Fa[la + 11] = Q.w, ha = $a[B.a], ia = $a[B.b], ob = $a[B.c], Ea[la] = ha.x, Ea[la + 1] = ha.y, Ea[la + 2] = ha.z, Ea[la + 3] = ha.w, Ea[la + 4] = ia.x, Ea[la + 5] = ia.y, Ea[la + 6] = ia.z, Ea[la + 7] = ia.w, Ea[la + 8] = ob.x, Ea[la + 9] = ob.y, Ea[la + 10] = ob.z, Ea[la + 11] = ob.w, la += 12;
                            la > 0 && (m.bindBuffer(m.ARRAY_BUFFER, v.__webglSkinIndicesBuffer), m.bufferData(m.ARRAY_BUFFER, Ea, D), m.bindBuffer(m.ARRAY_BUFFER, v.__webglSkinWeightsBuffer), m.bufferData(m.ARRAY_BUFFER, Fa, D))
                        }
                        if (ib) {
                            for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], M = B.vertexColors, R = B.color, 3 === M.length && E.vertexColors === THREE.VertexColors ? (da = M[0], ba = M[1], ja = M[2]) : ja = ba = da = R, Da[Ha] = da.r, Da[Ha + 1] = da.g, Da[Ha + 2] = da.b, Da[Ha + 3] = ba.r, Da[Ha + 4] = ba.g, Da[Ha + 5] = ba.b, Da[Ha + 6] = ja.r, Da[Ha + 7] = ja.g, Da[Ha + 8] = ja.b, Ha += 9;
                            Ha > 0 && (m.bindBuffer(m.ARRAY_BUFFER, v.__webglColorBuffer), m.bufferData(m.ARRAY_BUFFER, Da, D))
                        }
                        if (Ob && ea.hasTangents) {
                            for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], P = B.vertexTangents, Ka = P[0], V = P[1], W = P[2], sa[Aa] = Ka.x, sa[Aa + 1] = Ka.y, sa[Aa + 2] = Ka.z, sa[Aa + 3] = Ka.w, sa[Aa + 4] = V.x, sa[Aa + 5] = V.y, sa[Aa + 6] = V.z, sa[Aa + 7] = V.w, sa[Aa + 8] = W.x, sa[Aa + 9] = W.y, sa[Aa + 10] = W.z, sa[Aa + 11] = W.w, Aa += 12;
                            m.bindBuffer(m.ARRAY_BUFFER, v.__webglTangentBuffer), m.bufferData(m.ARRAY_BUFFER, sa, D)
                        }
                        if (Mb) {
                            for (y = 0, z = $.length; z > y; y++)if (B = xa[$[y]], I = B.vertexNormals, H = B.normal, 3 === I.length && !1 === G)for (Y = 0; 3 > Y; Y++)pa = I[Y], Na[Ra] = pa.x, Na[Ra + 1] = pa.y, Na[Ra + 2] = pa.z, Ra += 3; else for (Y = 0; 3 > Y; Y++)Na[Ra] = H.x, Na[Ra + 1] = H.y, Na[Ra + 2] = H.z, Ra += 3;
                            m.bindBuffer(m.ARRAY_BUFFER, v.__webglNormalBuffer), m.bufferData(m.ARRAY_BUFFER, Na, D)
                        }
                        if (vb && Hb) {
                            for (y = 0, z = $.length; z > y; y++)if (F = $[y], U = Hb[F], void 0 !== U)for (Y = 0; 3 > Y; Y++)ab = U[Y], Ab[kb] = ab.x, Ab[kb + 1] = ab.y, kb += 2;
                            kb > 0 && (m.bindBuffer(m.ARRAY_BUFFER, v.__webglUVBuffer), m.bufferData(m.ARRAY_BUFFER, Ab, D))
                        }
                        if (vb && Oa) {
                            for (y = 0, z = $.length; z > y; y++)if (F = $[y], O = Oa[F], void 0 !== O)for (Y = 0; 3 > Y; Y++)oa = O[Y], lb[yb] = oa.x, lb[yb + 1] = oa.y, yb += 2;
                            yb > 0 && (m.bindBuffer(m.ARRAY_BUFFER, v.__webglUV2Buffer), m.bufferData(m.ARRAY_BUFFER, lb, D))
                        }
                        if (fb) {
                            for (y = 0, z = $.length; z > y; y++)Va[zb] = La, Va[zb + 1] = La + 1, Va[zb + 2] = La + 2, zb += 3, Ta[eb] = La, Ta[eb + 1] = La + 1, Ta[eb + 2] = La, Ta[eb + 3] = La + 2, Ta[eb + 4] = La + 1, Ta[eb + 5] = La + 2, eb += 6, La += 3;
                            m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, v.__webglFaceBuffer), m.bufferData(m.ELEMENT_ARRAY_BUFFER, Va, D), m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, v.__webglLineBuffer), m.bufferData(m.ELEMENT_ARRAY_BUFFER, Ta, D)
                        }
                        if (mb)for (Y = 0, ub = mb.length; ub > Y; Y++)if (C = mb[Y], C.__original.needsUpdate) {
                            if (K = 0, 1 === C.size) {
                                if (void 0 === C.boundTo || "vertices" === C.boundTo)for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], C.array[K] = C.value[B.a], C.array[K + 1] = C.value[B.b], C.array[K + 2] = C.value[B.c], K += 3; else if ("faces" === C.boundTo)for (y = 0, z = $.length; z > y; y++)za = C.value[$[y]], C.array[K] = za, C.array[K + 1] = za, C.array[K + 2] = za, K += 3
                            } else if (2 === C.size) {
                                if (void 0 === C.boundTo || "vertices" === C.boundTo)for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], J = C.value[B.a], L = C.value[B.b], N = C.value[B.c], C.array[K] = J.x, C.array[K + 1] = J.y, C.array[K + 2] = L.x, C.array[K + 3] = L.y, C.array[K + 4] = N.x, C.array[K + 5] = N.y, K += 6; else if ("faces" === C.boundTo)for (y = 0, z = $.length; z > y; y++)N = L = J = za = C.value[$[y]], C.array[K] = J.x, C.array[K + 1] = J.y, C.array[K + 2] = L.x, C.array[K + 3] = L.y, C.array[K + 4] = N.x, C.array[K + 5] = N.y, K += 6
                            } else if (3 === C.size) {
                                var T;
                                if (T = "c" === C.type ? ["r", "g", "b"] : ["x", "y", "z"], void 0 === C.boundTo || "vertices" === C.boundTo)for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], J = C.value[B.a], L = C.value[B.b], N = C.value[B.c], C.array[K] = J[T[0]], C.array[K + 1] = J[T[1]], C.array[K + 2] = J[T[2]], C.array[K + 3] = L[T[0]], C.array[K + 4] = L[T[1]], C.array[K + 5] = L[T[2]], C.array[K + 6] = N[T[0]], C.array[K + 7] = N[T[1]], C.array[K + 8] = N[T[2]], K += 9; else if ("faces" === C.boundTo)for (y = 0, z = $.length; z > y; y++)N = L = J = za = C.value[$[y]], C.array[K] = J[T[0]], C.array[K + 1] = J[T[1]], C.array[K + 2] = J[T[2]], C.array[K + 3] = L[T[0]], C.array[K + 4] = L[T[1]], C.array[K + 5] = L[T[2]], C.array[K + 6] = N[T[0]], C.array[K + 7] = N[T[1]], C.array[K + 8] = N[T[2]], K += 9; else if ("faceVertices" === C.boundTo)for (y = 0, z = $.length; z > y; y++)za = C.value[$[y]], J = za[0], L = za[1], N = za[2], C.array[K] = J[T[0]], C.array[K + 1] = J[T[1]], C.array[K + 2] = J[T[2]], C.array[K + 3] = L[T[0]], C.array[K + 4] = L[T[1]], C.array[K + 5] = L[T[2]], C.array[K + 6] = N[T[0]], C.array[K + 7] = N[T[1]], C.array[K + 8] = N[T[2]], K += 9
                            } else if (4 === C.size)if (void 0 === C.boundTo || "vertices" === C.boundTo)for (y = 0, z = $.length; z > y; y++)B = xa[$[y]], J = C.value[B.a], L = C.value[B.b], N = C.value[B.c], C.array[K] = J.x, C.array[K + 1] = J.y, C.array[K + 2] = J.z, C.array[K + 3] = J.w, C.array[K + 4] = L.x, C.array[K + 5] = L.y, C.array[K + 6] = L.z, C.array[K + 7] = L.w, C.array[K + 8] = N.x, C.array[K + 9] = N.y, C.array[K + 10] = N.z, C.array[K + 11] = N.w, K += 12; else if ("faces" === C.boundTo)for (y = 0, z = $.length; z > y; y++)N = L = J = za = C.value[$[y]], C.array[K] = J.x, C.array[K + 1] = J.y, C.array[K + 2] = J.z, C.array[K + 3] = J.w, C.array[K + 4] = L.x, C.array[K + 5] = L.y, C.array[K + 6] = L.z, C.array[K + 7] = L.w, C.array[K + 8] = N.x, C.array[K + 9] = N.y, C.array[K + 10] = N.z, C.array[K + 11] = N.w, K += 12; else if ("faceVertices" === C.boundTo)for (y = 0, z = $.length; z > y; y++)za = C.value[$[y]], J = za[0], L = za[1], N = za[2], C.array[K] = J.x, C.array[K + 1] = J.y, C.array[K + 2] = J.z, C.array[K + 3] = J.w, C.array[K + 4] = L.x, C.array[K + 5] = L.y, C.array[K + 6] = L.z, C.array[K + 7] = L.w, C.array[K + 8] = N.x, C.array[K + 9] = N.y, C.array[K + 10] = N.z, C.array[K + 11] = N.w, K += 12;
                            m.bindBuffer(m.ARRAY_BUFFER, C.buffer), m.bufferData(m.ARRAY_BUFFER, C.array, D)
                        }
                        A && (delete v.__inittedArrays, delete v.__colorArray, delete v.__normalArray, delete v.__tangentArray, delete v.__uvArray, delete v.__uv2Array, delete v.__faceArray, delete v.__vertexArray, delete v.__lineArray, delete v.__skinIndexArray, delete v.__skinWeightArray)
                    }
                }
            }
            b.verticesNeedUpdate = !1, b.morphTargetsNeedUpdate = !1, b.elementsNeedUpdate = !1, b.uvsNeedUpdate = !1, b.normalsNeedUpdate = !1, b.colorsNeedUpdate = !1, b.tangentsNeedUpdate = !1, w.attributes && s(w)
        } else if (a instanceof THREE.Line) {
            if (w = c(a, b), u = w.attributes && r(w), b.verticesNeedUpdate || b.colorsNeedUpdate || b.lineDistancesNeedUpdate || u) {
                var S, aa, Z, Ba, X, Eb, Jb, cb, ua, Kb, Ia, fa, Db = m.DYNAMIC_DRAW, Rb = b.vertices, Ib = b.colors, gb = b.lineDistances, ya = Rb.length, pb = Ib.length, qb = gb.length, Wa = b.__vertexArray, tb = b.__colorArray, hb = b.__lineDistanceArray, $b = b.colorsNeedUpdate, Fb = b.lineDistancesNeedUpdate, Sb = b.__webglCustomAttributesList;
                if (b.verticesNeedUpdate) {
                    for (S = 0; ya > S; S++)Ba = Rb[S], X = 3 * S, Wa[X] = Ba.x, Wa[X + 1] = Ba.y, Wa[X + 2] = Ba.z;
                    m.bindBuffer(m.ARRAY_BUFFER, b.__webglVertexBuffer), m.bufferData(m.ARRAY_BUFFER, Wa, Db)
                }
                if ($b) {
                    for (aa = 0; pb > aa; aa++)Eb = Ib[aa], X = 3 * aa, tb[X] = Eb.r, tb[X + 1] = Eb.g, tb[X + 2] = Eb.b;
                    m.bindBuffer(m.ARRAY_BUFFER, b.__webglColorBuffer), m.bufferData(m.ARRAY_BUFFER, tb, Db)
                }
                if (Fb) {
                    for (Z = 0; qb > Z; Z++)hb[Z] = gb[Z];
                    m.bindBuffer(m.ARRAY_BUFFER, b.__webglLineDistanceBuffer), m.bufferData(m.ARRAY_BUFFER, hb, Db)
                }
                if (Sb)for (Jb = 0, cb = Sb.length; cb > Jb; Jb++)if (fa = Sb[Jb], fa.needsUpdate && (void 0 === fa.boundTo || "vertices" === fa.boundTo)) {
                    if (X = 0, Kb = fa.value.length, 1 === fa.size)for (ua = 0; Kb > ua; ua++)fa.array[ua] = fa.value[ua]; else if (2 === fa.size)for (ua = 0; Kb > ua; ua++)Ia = fa.value[ua], fa.array[X] = Ia.x, fa.array[X + 1] = Ia.y, X += 2; else if (3 === fa.size)if ("c" === fa.type)for (ua = 0; Kb > ua; ua++)Ia = fa.value[ua], fa.array[X] = Ia.r, fa.array[X + 1] = Ia.g, fa.array[X + 2] = Ia.b, X += 3; else for (ua = 0; Kb > ua; ua++)Ia = fa.value[ua], fa.array[X] = Ia.x, fa.array[X + 1] = Ia.y, fa.array[X + 2] = Ia.z, X += 3; else if (4 === fa.size)for (ua = 0; Kb > ua; ua++)Ia = fa.value[ua], fa.array[X] = Ia.x, fa.array[X + 1] = Ia.y, fa.array[X + 2] = Ia.z, fa.array[X + 3] = Ia.w, X += 4;
                    m.bindBuffer(m.ARRAY_BUFFER, fa.buffer), m.bufferData(m.ARRAY_BUFFER, fa.array, Db), fa.needsUpdate = !1
                }
            }
            b.verticesNeedUpdate = !1, b.colorsNeedUpdate = !1, b.lineDistancesNeedUpdate = !1, w.attributes && s(w)
        } else if (a instanceof THREE.PointCloud) {
            if (w = c(a, b), u = w.attributes && r(w), b.verticesNeedUpdate || b.colorsNeedUpdate || u) {
                var Tb, Ub, ac, ma, bc, ec, jb, va, Lb, Ja, ga, db = m.DYNAMIC_DRAW, Nb = b.vertices, Vb = Nb.length, Pb = b.colors, rb = Pb.length, cc = b.__vertexArray, dc = b.__colorArray, wb = b.colorsNeedUpdate, gc = b.__webglCustomAttributesList;
                if (b.verticesNeedUpdate) {
                    for (Tb = 0; Vb > Tb; Tb++)ac = Nb[Tb], ma = 3 * Tb, cc[ma] = ac.x, cc[ma + 1] = ac.y, cc[ma + 2] = ac.z;
                    m.bindBuffer(m.ARRAY_BUFFER, b.__webglVertexBuffer), m.bufferData(m.ARRAY_BUFFER, cc, db)
                }
                if (wb) {
                    for (Ub = 0; rb > Ub; Ub++)bc = Pb[Ub], ma = 3 * Ub, dc[ma] = bc.r, dc[ma + 1] = bc.g, dc[ma + 2] = bc.b;
                    m.bindBuffer(m.ARRAY_BUFFER, b.__webglColorBuffer), m.bufferData(m.ARRAY_BUFFER, dc, db)
                }
                if (gc)for (ec = 0, jb = gc.length; jb > ec; ec++) {
                    if (ga = gc[ec], ga.needsUpdate && (void 0 === ga.boundTo || "vertices" === ga.boundTo))if (Lb = ga.value.length, ma = 0, 1 === ga.size)for (va = 0; Lb > va; va++)ga.array[va] = ga.value[va]; else if (2 === ga.size)for (va = 0; Lb > va; va++)Ja = ga.value[va], ga.array[ma] = Ja.x, ga.array[ma + 1] = Ja.y, ma += 2; else if (3 === ga.size)if ("c" === ga.type)for (va = 0; Lb > va; va++)Ja = ga.value[va], ga.array[ma] = Ja.r, ga.array[ma + 1] = Ja.g, ga.array[ma + 2] = Ja.b, ma += 3; else for (va = 0; Lb > va; va++)Ja = ga.value[va], ga.array[ma] = Ja.x, ga.array[ma + 1] = Ja.y, ga.array[ma + 2] = Ja.z, ma += 3; else if (4 === ga.size)for (va = 0; Lb > va; va++)Ja = ga.value[va], ga.array[ma] = Ja.x, ga.array[ma + 1] = Ja.y, ga.array[ma + 2] = Ja.z, ga.array[ma + 3] = Ja.w, ma += 4;
                    m.bindBuffer(m.ARRAY_BUFFER, ga.buffer), m.bufferData(m.ARRAY_BUFFER, ga.array, db), ga.needsUpdate = !1
                }
            }
            b.verticesNeedUpdate = !1, b.colorsNeedUpdate = !1, w.attributes && s(w)
        }
    }

    function r(a) {
        for (var b in a.attributes)if (a.attributes[b].needsUpdate)return !0;
        return !1
    }

    function s(a) {
        for (var b in a.attributes)a.attributes[b].needsUpdate = !1
    }

    function u(a) {
        !0 === a.transparent ? W.setBlending(a.blending, a.blendEquation, a.blendSrc, a.blendDst, a.blendEquationAlpha, a.blendSrcAlpha, a.blendDstAlpha) : W.setBlending(THREE.NoBlending), W.setDepthTest(a.depthTest), W.setDepthWrite(a.depthWrite), W.setColorWrite(a.colorWrite), W.setPolygonOffset(a.polygonOffset, a.polygonOffsetFactor, a.polygonOffsetUnits)
    }

    function v(a, b, c, d, e) {
        var f, g, h, k;
        if (Mb = 0, d.needsUpdate) {
            d.program && hc(d), d.addEventListener("dispose", ic);
            var n = pc[d.type];
            if (n) {
                var l = THREE.ShaderLib[n];
                d.__webglShader = {
                    uniforms: THREE.UniformsUtils.clone(l.uniforms),
                    vertexShader: l.vertexShader,
                    fragmentShader: l.fragmentShader
                }
            } else d.__webglShader = {
                uniforms: d.uniforms,
                vertexShader: d.vertexShader,
                fragmentShader: d.fragmentShader
            };
            for (var p = 0, q = 0, r = 0, s = 0, t = 0, w = b.length; w > t; t++) {
                var v = b[t];
                v.onlyShadow || !1 === v.visible || (v instanceof THREE.DirectionalLight && p++, v instanceof THREE.PointLight && q++, v instanceof THREE.SpotLight && r++, v instanceof THREE.HemisphereLight && s++)
            }
            f = p, g = q, h = r, k = s;
            for (var u, z = 0, G = 0, F = b.length; F > G; G++) {
                var J = b[G];
                J.castShadow && (J instanceof THREE.SpotLight && z++, J instanceof THREE.DirectionalLight && !J.shadowCascade && z++)
            }
            u = z;
            var H;
            if (Nb && e && e.skeleton && e.skeleton.useVertexTexture)H = 1024; else {
                var N = m.getParameter(m.MAX_VERTEX_UNIFORM_VECTORS), M = Math.floor((N - 20) / 4);
                void 0 !== e && e instanceof THREE.SkinnedMesh && (M = Math.min(e.skeleton.bones.length, M), M < e.skeleton.bones.length && THREE.warn("WebGLRenderer: too many bones - " + e.skeleton.bones.length + ", this GPU supports just " + M + " (try OpenGL instead of ANGLE)")), H = M
            }
            var P = {
                precision: L,
                supportsVertexTextures: Vb,
                map: !!d.map,
                envMap: !!d.envMap,
                envMapMode: d.envMap && d.envMap.mapping,
                lightMap: !!d.lightMap,
                bumpMap: !!d.bumpMap,
                normalMap: !!d.normalMap,
                specularMap: !!d.specularMap,
                alphaMap: !!d.alphaMap,
                combine: d.combine,
                vertexColors: d.vertexColors,
                fog: c,
                useFog: d.fog,
                fogExp: c instanceof THREE.FogExp2,
                flatShading: d.shading === THREE.FlatShading,
                sizeAttenuation: d.sizeAttenuation,
                logarithmicDepthBuffer: ja,
                skinning: d.skinning,
                maxBones: H,
                useVertexTexture: Nb && e && e.skeleton && e.skeleton.useVertexTexture,
                morphTargets: d.morphTargets,
                morphNormals: d.morphNormals,
                maxMorphTargets: B.maxMorphTargets,
                maxMorphNormals: B.maxMorphNormals,
                maxDirLights: f,
                maxPointLights: g,
                maxSpotLights: h,
                maxHemiLights: k,
                maxShadows: u,
                shadowMapEnabled: B.shadowMapEnabled && e.receiveShadow && u > 0,
                shadowMapType: B.shadowMapType,
                shadowMapDebug: B.shadowMapDebug,
                shadowMapCascade: B.shadowMapCascade,
                alphaTest: d.alphaTest,
                metal: d.metal,
                wrapAround: d.wrapAround,
                doubleSided: d.side === THREE.DoubleSide,
                flipSided: d.side === THREE.BackSide
            }, R = [];
            if (n ? R.push(n) : (R.push(d.fragmentShader), R.push(d.vertexShader)), void 0 !== d.defines)for (var O in d.defines)R.push(O), R.push(d.defines[O]);
            for (O in P)R.push(O), R.push(P[O]);
            for (var V, Ka = R.join(), W = 0, Qa = Pa.length; Qa > W; W++) {
                var Ya = Pa[W];
                if (Ya.code === Ka) {
                    V = Ya, V.usedTimes++;
                    break
                }
            }
            void 0 === V && (V = new THREE.WebGLProgram(B, Ka, d, P), Pa.push(V), B.info.memory.programs = Pa.length), d.program = V;
            var Xa = V.attributes;
            if (d.morphTargets) {
                d.numSupportedMorphTargets = 0;
                for (var ca, da = "morphTarget", ba = 0; ba < B.maxMorphTargets; ba++)ca = da + ba, 0 <= Xa[ca] && d.numSupportedMorphTargets++
            }
            if (d.morphNormals)for (d.numSupportedMorphNormals = 0, da = "morphNormal", ba = 0; ba < B.maxMorphNormals; ba++)ca = da + ba, 0 <= Xa[ca] && d.numSupportedMorphNormals++;
            d.uniformsList = [];
            for (var ha in d.__webglShader.uniforms) {
                var ta = d.program.uniforms[ha];
                ta && d.uniformsList.push([d.__webglShader.uniforms[ha], ta])
            }
            d.needsUpdate = !1
        }
        d.morphTargets && !e.__webglMorphTargetInfluences && (e.__webglMorphTargetInfluences = new Float32Array(B.maxMorphTargets));
        var ab = !1, oa = !1, qa = !1, Ua = d.program, ka = Ua.uniforms, Q = d.__webglShader.uniforms;
        if (Ua.id !== ob && (m.useProgram(Ua.program), ob = Ua.id, qa = oa = ab = !0), d.id !== ub && (-1 === ub && (qa = !0), ub = d.id, oa = !0), (ab || a !== vb) && (m.uniformMatrix4fv(ka.projectionMatrix, !1, a.projectionMatrix.elements), ja && m.uniform1f(ka.logDepthBufFC, 2 / (Math.log(a.far + 1) / Math.LN2)), a !== vb && (vb = a), (d instanceof THREE.ShaderMaterial || d instanceof THREE.MeshPhongMaterial || d.envMap) && null !== ka.cameraPosition && (wa.setFromMatrixPosition(a.matrixWorld), m.uniform3f(ka.cameraPosition, wa.x, wa.y, wa.z)), (d instanceof THREE.MeshPhongMaterial || d instanceof THREE.MeshLambertMaterial || d instanceof THREE.MeshBasicMaterial || d instanceof THREE.ShaderMaterial || d.skinning) && null !== ka.viewMatrix && m.uniformMatrix4fv(ka.viewMatrix, !1, a.matrixWorldInverse.elements)), d.skinning)if (e.bindMatrix && null !== ka.bindMatrix && m.uniformMatrix4fv(ka.bindMatrix, !1, e.bindMatrix.elements), e.bindMatrixInverse && null !== ka.bindMatrixInverse && m.uniformMatrix4fv(ka.bindMatrixInverse, !1, e.bindMatrixInverse.elements), Nb && e.skeleton && e.skeleton.useVertexTexture) {
            if (null !== ka.boneTexture) {
                var db = D();
                m.uniform1i(ka.boneTexture, db), B.setTexture(e.skeleton.boneTexture, db)
            }
            null !== ka.boneTextureWidth && m.uniform1i(ka.boneTextureWidth, e.skeleton.boneTextureWidth), null !== ka.boneTextureHeight && m.uniform1i(ka.boneTextureHeight, e.skeleton.boneTextureHeight)
        } else e.skeleton && e.skeleton.boneMatrices && null !== ka.boneGlobalMatrices && m.uniformMatrix4fv(ka.boneGlobalMatrices, !1, e.skeleton.boneMatrices);
        if (oa) {
            if (c && d.fog && (Q.fogColor.value = c.color, c instanceof THREE.Fog ? (Q.fogNear.value = c.near, Q.fogFar.value = c.far) : c instanceof THREE.FogExp2 && (Q.fogDensity.value = c.density)), d instanceof THREE.MeshPhongMaterial || d instanceof THREE.MeshLambertMaterial || d.lights) {
                if (Ob) {
                    var ia, Za, Y, xb, pb, qb, Ca, jb, qa = !0, bb = 0, cb = 0, ib = 0, na = jc, rb = na.directional.colors, La = na.directional.positions, Ma = na.point.colors, kb = na.point.positions, yb = na.point.distances, zb = na.point.decays, Ra = na.spot.colors, Aa = na.spot.positions, eb = na.spot.distances, Ha = na.spot.directions, la = na.spot.anglesCos, ra = na.spot.exponents, K = na.spot.decays, za = na.hemi.skyColors, Sa = na.hemi.groundColors, Ab = na.hemi.positions, lb = 0, Na = 0, sa = 0, Da = 0, Ea = 0, Fa = 0, Gb = 0, Bb = 0, mb = 0, C = 0, Va = 0, Ta = 0;
                    for (ia = 0, Za = b.length; Za > ia; ia++)Y = b[ia], Y.onlyShadow || (xb = Y.color, Ca = Y.intensity, jb = Y.distance, Y instanceof THREE.AmbientLight ? Y.visible && (bb += xb.r, cb += xb.g, ib += xb.b) : Y instanceof THREE.DirectionalLight ? (Ea += 1, Y.visible && (pa.setFromMatrixPosition(Y.matrixWorld), wa.setFromMatrixPosition(Y.target.matrixWorld), pa.sub(wa), pa.normalize(), mb = 3 * lb, La[mb] = pa.x, La[mb + 1] = pa.y, La[mb + 2] = pa.z, y(rb, mb, xb, Ca), lb += 1)) : Y instanceof THREE.PointLight ? (Fa += 1, Y.visible && (C = 3 * Na,
                        y(Ma, C, xb, Ca), wa.setFromMatrixPosition(Y.matrixWorld), kb[C] = wa.x, kb[C + 1] = wa.y, kb[C + 2] = wa.z, yb[Na] = jb, zb[Na] = 0 === Y.distance ? 0 : Y.decay, Na += 1)) : Y instanceof THREE.SpotLight ? (Gb += 1, Y.visible && (Va = 3 * sa, y(Ra, Va, xb, Ca), pa.setFromMatrixPosition(Y.matrixWorld), Aa[Va] = pa.x, Aa[Va + 1] = pa.y, Aa[Va + 2] = pa.z, eb[sa] = jb, wa.setFromMatrixPosition(Y.target.matrixWorld), pa.sub(wa), pa.normalize(), Ha[Va] = pa.x, Ha[Va + 1] = pa.y, Ha[Va + 2] = pa.z, la[sa] = Math.cos(Y.angle), ra[sa] = Y.exponent, K[sa] = 0 === Y.distance ? 0 : Y.decay, sa += 1)) : Y instanceof THREE.HemisphereLight && (Bb += 1, Y.visible && (pa.setFromMatrixPosition(Y.matrixWorld), pa.normalize(), Ta = 3 * Da, Ab[Ta] = pa.x, Ab[Ta + 1] = pa.y, Ab[Ta + 2] = pa.z, pb = Y.color, qb = Y.groundColor, y(za, Ta, pb, Ca), y(Sa, Ta, qb, Ca), Da += 1)));
                    for (ia = 3 * lb, Za = Math.max(rb.length, 3 * Ea); Za > ia; ia++)rb[ia] = 0;
                    for (ia = 3 * Na, Za = Math.max(Ma.length, 3 * Fa); Za > ia; ia++)Ma[ia] = 0;
                    for (ia = 3 * sa, Za = Math.max(Ra.length, 3 * Gb); Za > ia; ia++)Ra[ia] = 0;
                    for (ia = 3 * Da, Za = Math.max(za.length, 3 * Bb); Za > ia; ia++)za[ia] = 0;
                    for (ia = 3 * Da, Za = Math.max(Sa.length, 3 * Bb); Za > ia; ia++)Sa[ia] = 0;
                    na.directional.length = lb, na.point.length = Na, na.spot.length = sa, na.hemi.length = Da, na.ambient[0] = bb, na.ambient[1] = cb, na.ambient[2] = ib, Ob = !1
                }
                if (qa) {
                    var ea = jc;
                    Q.ambientLightColor.value = ea.ambient, Q.directionalLightColor.value = ea.directional.colors, Q.directionalLightDirection.value = ea.directional.positions, Q.pointLightColor.value = ea.point.colors, Q.pointLightPosition.value = ea.point.positions, Q.pointLightDistance.value = ea.point.distances, Q.pointLightDecay.value = ea.point.decays, Q.spotLightColor.value = ea.spot.colors, Q.spotLightPosition.value = ea.spot.positions, Q.spotLightDistance.value = ea.spot.distances, Q.spotLightDirection.value = ea.spot.directions, Q.spotLightAngleCos.value = ea.spot.anglesCos, Q.spotLightExponent.value = ea.spot.exponents, Q.spotLightDecay.value = ea.spot.decays, Q.hemisphereLightSkyColor.value = ea.hemi.skyColors, Q.hemisphereLightGroundColor.value = ea.hemi.groundColors, Q.hemisphereLightDirection.value = ea.hemi.positions, x(Q, !0)
                } else x(Q, !1)
            }
            if (d instanceof THREE.MeshBasicMaterial || d instanceof THREE.MeshLambertMaterial || d instanceof THREE.MeshPhongMaterial) {
                Q.opacity.value = d.opacity, Q.diffuse.value = d.color, Q.map.value = d.map, Q.lightMap.value = d.lightMap, Q.specularMap.value = d.specularMap, Q.alphaMap.value = d.alphaMap, d.bumpMap && (Q.bumpMap.value = d.bumpMap, Q.bumpScale.value = d.bumpScale), d.normalMap && (Q.normalMap.value = d.normalMap, Q.normalScale.value.copy(d.normalScale));
                var fb;
                if (d.map ? fb = d.map : d.specularMap ? fb = d.specularMap : d.normalMap ? fb = d.normalMap : d.bumpMap ? fb = d.bumpMap : d.alphaMap && (fb = d.alphaMap), void 0 !== fb) {
                    var wb = fb.offset, Qb = fb.repeat;
                    Q.offsetRepeat.value.set(wb.x, wb.y, Qb.x, Qb.y)
                }
                Q.envMap.value = d.envMap, Q.flipEnvMap.value = d.envMap instanceof THREE.WebGLRenderTargetCube ? 1 : -1, Q.reflectivity.value = d.reflectivity, Q.refractionRatio.value = d.refractionRatio
            }
            if (d instanceof THREE.LineBasicMaterial)Q.diffuse.value = d.color, Q.opacity.value = d.opacity; else if (d instanceof THREE.LineDashedMaterial)Q.diffuse.value = d.color, Q.opacity.value = d.opacity, Q.dashSize.value = d.dashSize, Q.totalSize.value = d.dashSize + d.gapSize, Q.scale.value = d.scale; else if (d instanceof THREE.PointCloudMaterial) {
                if (Q.psColor.value = d.color, Q.opacity.value = d.opacity, Q.size.value = d.size, Q.scale.value = U.height / 2, Q.map.value = d.map, null !== d.map) {
                    var Wb = d.map.offset, Xb = d.map.repeat;
                    Q.offsetRepeat.value.set(Wb.x, Wb.y, Xb.x, Xb.y)
                }
            } else d instanceof THREE.MeshPhongMaterial ? (Q.shininess.value = d.shininess, Q.emissive.value = d.emissive, Q.specular.value = d.specular, d.wrapAround && Q.wrapRGB.value.copy(d.wrapRGB)) : d instanceof THREE.MeshLambertMaterial ? (Q.emissive.value = d.emissive, d.wrapAround && Q.wrapRGB.value.copy(d.wrapRGB)) : d instanceof THREE.MeshDepthMaterial ? (Q.mNear.value = a.near, Q.mFar.value = a.far, Q.opacity.value = d.opacity) : d instanceof THREE.MeshNormalMaterial && (Q.opacity.value = d.opacity);
            if (e.receiveShadow && !d._shadowPass && Q.shadowMatrix)for (var sb = 0, Cb = 0, $ = b.length; $ > Cb; Cb++) {
                var xa = b[Cb];
                xa.castShadow && (xa instanceof THREE.SpotLight || xa instanceof THREE.DirectionalLight && !xa.shadowCascade) && (Q.shadowMap.value[sb] = xa.shadowMap, Q.shadowMapSize.value[sb] = xa.shadowMapSize, Q.shadowMatrix.value[sb] = xa.shadowMatrix, Q.shadowDarkness.value[sb] = xa.shadowDarkness, Q.shadowBias.value[sb] = xa.shadowBias, sb++)
            }
            for (var Oa, $a, Ga, Hb = d.uniformsList, nb = 0, fc = Hb.length; fc > nb; nb++) {
                var T = Hb[nb][0];
                if (!1 !== T.needsUpdate) {
                    var Db = T.type, S = T.value, aa = Hb[nb][1];
                    switch (Db) {
                        case"1i":
                            m.uniform1i(aa, S);
                            break;
                        case"1f":
                            m.uniform1f(aa, S);
                            break;
                        case"2f":
                            m.uniform2f(aa, S[0], S[1]);
                            break;
                        case"3f":
                            m.uniform3f(aa, S[0], S[1], S[2]);
                            break;
                        case"4f":
                            m.uniform4f(aa, S[0], S[1], S[2], S[3]);
                            break;
                        case"1iv":
                            m.uniform1iv(aa, S);
                            break;
                        case"3iv":
                            m.uniform3iv(aa, S);
                            break;
                        case"1fv":
                            m.uniform1fv(aa, S);
                            break;
                        case"2fv":
                            m.uniform2fv(aa, S);
                            break;
                        case"3fv":
                            m.uniform3fv(aa, S);
                            break;
                        case"4fv":
                            m.uniform4fv(aa, S);
                            break;
                        case"Matrix3fv":
                            m.uniformMatrix3fv(aa, !1, S);
                            break;
                        case"Matrix4fv":
                            m.uniformMatrix4fv(aa, !1, S);
                            break;
                        case"i":
                            m.uniform1i(aa, S);
                            break;
                        case"f":
                            m.uniform1f(aa, S);
                            break;
                        case"v2":
                            m.uniform2f(aa, S.x, S.y);
                            break;
                        case"v3":
                            m.uniform3f(aa, S.x, S.y, S.z);
                            break;
                        case"v4":
                            m.uniform4f(aa, S.x, S.y, S.z, S.w);
                            break;
                        case"c":
                            m.uniform3f(aa, S.r, S.g, S.b);
                            break;
                        case"iv1":
                            m.uniform1iv(aa, S);
                            break;
                        case"iv":
                            m.uniform3iv(aa, S);
                            break;
                        case"fv1":
                            m.uniform1fv(aa, S);
                            break;
                        case"fv":
                            m.uniform3fv(aa, S);
                            break;
                        case"v2v":
                            void 0 === T._array && (T._array = new Float32Array(2 * S.length));
                            for (var Z = 0, Ba = S.length; Ba > Z; Z++)Ga = 2 * Z, T._array[Ga] = S[Z].x, T._array[Ga + 1] = S[Z].y;
                            m.uniform2fv(aa, T._array);
                            break;
                        case"v3v":
                            for (void 0 === T._array && (T._array = new Float32Array(3 * S.length)), Z = 0, Ba = S.length; Ba > Z; Z++)Ga = 3 * Z, T._array[Ga] = S[Z].x, T._array[Ga + 1] = S[Z].y, T._array[Ga + 2] = S[Z].z;
                            m.uniform3fv(aa, T._array);
                            break;
                        case"v4v":
                            for (void 0 === T._array && (T._array = new Float32Array(4 * S.length)), Z = 0, Ba = S.length; Ba > Z; Z++)Ga = 4 * Z, T._array[Ga] = S[Z].x, T._array[Ga + 1] = S[Z].y, T._array[Ga + 2] = S[Z].z, T._array[Ga + 3] = S[Z].w;
                            m.uniform4fv(aa, T._array);
                            break;
                        case"m3":
                            m.uniformMatrix3fv(aa, !1, S.elements);
                            break;
                        case"m3v":
                            for (void 0 === T._array && (T._array = new Float32Array(9 * S.length)), Z = 0, Ba = S.length; Ba > Z; Z++)S[Z].flattenToArrayOffset(T._array, 9 * Z);
                            m.uniformMatrix3fv(aa, !1, T._array);
                            break;
                        case"m4":
                            m.uniformMatrix4fv(aa, !1, S.elements);
                            break;
                        case"m4v":
                            for (void 0 === T._array && (T._array = new Float32Array(16 * S.length)), Z = 0, Ba = S.length; Ba > Z; Z++)S[Z].flattenToArrayOffset(T._array, 16 * Z);
                            m.uniformMatrix4fv(aa, !1, T._array);
                            break;
                        case"t":
                            if (Oa = S, $a = D(), m.uniform1i(aa, $a), !Oa)continue;
                            if (Oa instanceof THREE.CubeTexture || Oa.image instanceof Array && 6 === Oa.image.length) {
                                var X = Oa, Eb = $a;
                                if (6 === X.image.length)if (X.needsUpdate) {
                                    X.image.__webglTextureCube || (X.addEventListener("dispose", Pb), X.image.__webglTextureCube = m.createTexture(), B.info.memory.textures++), m.activeTexture(m.TEXTURE0 + Eb), m.bindTexture(m.TEXTURE_CUBE_MAP, X.image.__webglTextureCube), m.pixelStorei(m.UNPACK_FLIP_Y_WEBGL, X.flipY);
                                    for (var Rb = X instanceof THREE.CompressedTexture, Ib = X.image[0] instanceof THREE.DataTexture, gb = [], ya = 0; 6 > ya; ya++)gb[ya] = !B.autoScaleCubemaps || Rb || Ib ? Ib ? X.image[ya].image : X.image[ya] : E(X.image[ya], qc);
                                    var Yb = gb[0], Zb = THREE.Math.isPowerOfTwo(Yb.width) && THREE.Math.isPowerOfTwo(Yb.height), Wa = I(X.format), tb = I(X.type);
                                    for (A(m.TEXTURE_CUBE_MAP, X, Zb), ya = 0; 6 > ya; ya++)if (Rb)for (var hb, $b = gb[ya].mipmaps, Fb = 0, Sb = $b.length; Sb > Fb; Fb++)hb = $b[Fb], X.format !== THREE.RGBAFormat && X.format !== THREE.RGBFormat ? -1 < kc().indexOf(Wa) ? m.compressedTexImage2D(m.TEXTURE_CUBE_MAP_POSITIVE_X + ya, Fb, Wa, hb.width, hb.height, 0, hb.data) : THREE.warn("THREE.WebGLRenderer: Attempt to load unsupported compressed texture format in .setCubeTexture()") : m.texImage2D(m.TEXTURE_CUBE_MAP_POSITIVE_X + ya, Fb, Wa, hb.width, hb.height, 0, Wa, tb, hb.data); else Ib ? m.texImage2D(m.TEXTURE_CUBE_MAP_POSITIVE_X + ya, 0, Wa, gb[ya].width, gb[ya].height, 0, Wa, tb, gb[ya].data) : m.texImage2D(m.TEXTURE_CUBE_MAP_POSITIVE_X + ya, 0, Wa, Wa, tb, gb[ya]);
                                    X.generateMipmaps && Zb && m.generateMipmap(m.TEXTURE_CUBE_MAP), X.needsUpdate = !1, X.onUpdate && X.onUpdate()
                                } else m.activeTexture(m.TEXTURE0 + Eb), m.bindTexture(m.TEXTURE_CUBE_MAP, X.image.__webglTextureCube)
                            } else if (Oa instanceof THREE.WebGLRenderTargetCube) {
                                var Jb = Oa;
                                m.activeTexture(m.TEXTURE0 + $a), m.bindTexture(m.TEXTURE_CUBE_MAP, Jb.__webglTexture)
                            } else B.setTexture(Oa, $a);
                            break;
                        case"tv":
                            for (void 0 === T._array && (T._array = []), Z = 0, Ba = T.value.length; Ba > Z; Z++)T._array[Z] = D();
                            for (m.uniform1iv(aa, T._array), Z = 0, Ba = T.value.length; Ba > Z; Z++)Oa = T.value[Z], $a = T._array[Z], Oa && B.setTexture(Oa, $a);
                            break;
                        default:
                            THREE.warn("THREE.WebGLRenderer: Unknown uniform type: " + Db)
                    }
                }
            }
        }
        return m.uniformMatrix4fv(ka.modelViewMatrix, !1, e._modelViewMatrix.elements), ka.normalMatrix && m.uniformMatrix3fv(ka.normalMatrix, !1, e._normalMatrix.elements), null !== ka.modelMatrix && m.uniformMatrix4fv(ka.modelMatrix, !1, e.matrixWorld.elements), Ua
    }

    function x(a, b) {
        a.ambientLightColor.needsUpdate = b, a.directionalLightColor.needsUpdate = b, a.directionalLightDirection.needsUpdate = b, a.pointLightColor.needsUpdate = b, a.pointLightPosition.needsUpdate = b, a.pointLightDistance.needsUpdate = b, a.pointLightDecay.needsUpdate = b, a.spotLightColor.needsUpdate = b, a.spotLightPosition.needsUpdate = b, a.spotLightDistance.needsUpdate = b, a.spotLightDirection.needsUpdate = b, a.spotLightAngleCos.needsUpdate = b, a.spotLightExponent.needsUpdate = b, a.spotLightDecay.needsUpdate = b, a.hemisphereLightSkyColor.needsUpdate = b, a.hemisphereLightGroundColor.needsUpdate = b, a.hemisphereLightDirection.needsUpdate = b
    }

    function D() {
        var a = Mb;
        return a >= Wb && THREE.warn("WebGLRenderer: trying to use " + a + " texture units while this GPU supports only " + Wb), Mb += 1, a
    }

    function w(a, b) {
        a._modelViewMatrix.multiplyMatrices(b.matrixWorldInverse, a.matrixWorld), a._normalMatrix.getNormalMatrix(a._modelViewMatrix)
    }

    function y(a, b, c, d) {
        a[b] = c.r * d, a[b + 1] = c.g * d, a[b + 2] = c.b * d
    }

    function A(a, b, c) {
        c ? (m.texParameteri(a, m.TEXTURE_WRAP_S, I(b.wrapS)), m.texParameteri(a, m.TEXTURE_WRAP_T, I(b.wrapT)), m.texParameteri(a, m.TEXTURE_MAG_FILTER, I(b.magFilter)), m.texParameteri(a, m.TEXTURE_MIN_FILTER, I(b.minFilter))) : (m.texParameteri(a, m.TEXTURE_WRAP_S, m.CLAMP_TO_EDGE), m.texParameteri(a, m.TEXTURE_WRAP_T, m.CLAMP_TO_EDGE), b.wrapS === THREE.ClampToEdgeWrapping && b.wrapT === THREE.ClampToEdgeWrapping || THREE.warn("THREE.WebGLRenderer: Texture is not power of two. Texture.wrapS and Texture.wrapT should be set to THREE.ClampToEdgeWrapping. ( " + b.sourceFile + " )"), m.texParameteri(a, m.TEXTURE_MAG_FILTER, z(b.magFilter)), m.texParameteri(a, m.TEXTURE_MIN_FILTER, z(b.minFilter)), b.minFilter !== THREE.NearestFilter && b.minFilter !== THREE.LinearFilter && THREE.warn("THREE.WebGLRenderer: Texture is not power of two. Texture.minFilter should be set to THREE.NearestFilter or THREE.LinearFilter. ( " + b.sourceFile + " )")), (c = da.get("EXT_texture_filter_anisotropic")) && b.type !== THREE.FloatType && b.type !== THREE.HalfFloatType && (1 < b.anisotropy || b.__currentAnisotropy) && (m.texParameterf(a, c.TEXTURE_MAX_ANISOTROPY_EXT, Math.min(b.anisotropy, B.getMaxAnisotropy())), b.__currentAnisotropy = b.anisotropy)
    }

    function E(a, b) {
        if (a.width > b || a.height > b) {
            var c = b / Math.max(a.width, a.height), d = document.createElement("canvas");
            return d.width = Math.floor(a.width * c), d.height = Math.floor(a.height * c), d.getContext("2d").drawImage(a, 0, 0, a.width, a.height, 0, 0, d.width, d.height), THREE.warn("THREE.WebGLRenderer: image is too big (" + a.width + "x" + a.height + "). Resized to " + d.width + "x" + d.height, a), d
        }
        return a
    }

    function G(a, b) {
        m.bindRenderbuffer(m.RENDERBUFFER, a), b.depthBuffer && !b.stencilBuffer ? (m.renderbufferStorage(m.RENDERBUFFER, m.DEPTH_COMPONENT16, b.width, b.height), m.framebufferRenderbuffer(m.FRAMEBUFFER, m.DEPTH_ATTACHMENT, m.RENDERBUFFER, a)) : b.depthBuffer && b.stencilBuffer ? (m.renderbufferStorage(m.RENDERBUFFER, m.DEPTH_STENCIL, b.width, b.height), m.framebufferRenderbuffer(m.FRAMEBUFFER, m.DEPTH_STENCIL_ATTACHMENT, m.RENDERBUFFER, a)) : m.renderbufferStorage(m.RENDERBUFFER, m.RGBA4, b.width, b.height)
    }

    function F(a) {
        a instanceof THREE.WebGLRenderTargetCube ? (m.bindTexture(m.TEXTURE_CUBE_MAP, a.__webglTexture), m.generateMipmap(m.TEXTURE_CUBE_MAP), m.bindTexture(m.TEXTURE_CUBE_MAP, null)) : (m.bindTexture(m.TEXTURE_2D, a.__webglTexture), m.generateMipmap(m.TEXTURE_2D), m.bindTexture(m.TEXTURE_2D, null))
    }

    function z(a) {
        return a === THREE.NearestFilter || a === THREE.NearestMipMapNearestFilter || a === THREE.NearestMipMapLinearFilter ? m.NEAREST : m.LINEAR
    }

    function I(a) {
        var b;
        if (a === THREE.RepeatWrapping)return m.REPEAT;
        if (a === THREE.ClampToEdgeWrapping)return m.CLAMP_TO_EDGE;
        if (a === THREE.MirroredRepeatWrapping)return m.MIRRORED_REPEAT;
        if (a === THREE.NearestFilter)return m.NEAREST;
        if (a === THREE.NearestMipMapNearestFilter)return m.NEAREST_MIPMAP_NEAREST;
        if (a === THREE.NearestMipMapLinearFilter)return m.NEAREST_MIPMAP_LINEAR;
        if (a === THREE.LinearFilter)return m.LINEAR;
        if (a === THREE.LinearMipMapNearestFilter)return m.LINEAR_MIPMAP_NEAREST;
        if (a === THREE.LinearMipMapLinearFilter)return m.LINEAR_MIPMAP_LINEAR;
        if (a === THREE.UnsignedByteType)return m.UNSIGNED_BYTE;
        if (a === THREE.UnsignedShort4444Type)return m.UNSIGNED_SHORT_4_4_4_4;
        if (a === THREE.UnsignedShort5551Type)return m.UNSIGNED_SHORT_5_5_5_1;
        if (a === THREE.UnsignedShort565Type)return m.UNSIGNED_SHORT_5_6_5;
        if (a === THREE.ByteType)return m.BYTE;
        if (a === THREE.ShortType)return m.SHORT;
        if (a === THREE.UnsignedShortType)return m.UNSIGNED_SHORT;
        if (a === THREE.IntType)return m.INT;
        if (a === THREE.UnsignedIntType)return m.UNSIGNED_INT;
        if (a === THREE.FloatType)return m.FLOAT;
        if (b = da.get("OES_texture_half_float"), null !== b && a === THREE.HalfFloatType)return b.HALF_FLOAT_OES;
        if (a === THREE.AlphaFormat)return m.ALPHA;
        if (a === THREE.RGBFormat)return m.RGB;
        if (a === THREE.RGBAFormat)return m.RGBA;
        if (a === THREE.LuminanceFormat)return m.LUMINANCE;
        if (a === THREE.LuminanceAlphaFormat)return m.LUMINANCE_ALPHA;
        if (a === THREE.AddEquation)return m.FUNC_ADD;
        if (a === THREE.SubtractEquation)return m.FUNC_SUBTRACT;
        if (a === THREE.ReverseSubtractEquation)return m.FUNC_REVERSE_SUBTRACT;
        if (a === THREE.ZeroFactor)return m.ZERO;
        if (a === THREE.OneFactor)return m.ONE;
        if (a === THREE.SrcColorFactor)return m.SRC_COLOR;
        if (a === THREE.OneMinusSrcColorFactor)return m.ONE_MINUS_SRC_COLOR;
        if (a === THREE.SrcAlphaFactor)return m.SRC_ALPHA;
        if (a === THREE.OneMinusSrcAlphaFactor)return m.ONE_MINUS_SRC_ALPHA;
        if (a === THREE.DstAlphaFactor)return m.DST_ALPHA;
        if (a === THREE.OneMinusDstAlphaFactor)return m.ONE_MINUS_DST_ALPHA;
        if (a === THREE.DstColorFactor)return m.DST_COLOR;
        if (a === THREE.OneMinusDstColorFactor)return m.ONE_MINUS_DST_COLOR;
        if (a === THREE.SrcAlphaSaturateFactor)return m.SRC_ALPHA_SATURATE;
        if (b = da.get("WEBGL_compressed_texture_s3tc"), null !== b) {
            if (a === THREE.RGB_S3TC_DXT1_Format)return b.COMPRESSED_RGB_S3TC_DXT1_EXT;
            if (a === THREE.RGBA_S3TC_DXT1_Format)return b.COMPRESSED_RGBA_S3TC_DXT1_EXT;
            if (a === THREE.RGBA_S3TC_DXT3_Format)return b.COMPRESSED_RGBA_S3TC_DXT3_EXT;
            if (a === THREE.RGBA_S3TC_DXT5_Format)return b.COMPRESSED_RGBA_S3TC_DXT5_EXT
        }
        if (b = da.get("WEBGL_compressed_texture_pvrtc"), null !== b) {
            if (a === THREE.RGB_PVRTC_4BPPV1_Format)return b.COMPRESSED_RGB_PVRTC_4BPPV1_IMG;
            if (a === THREE.RGB_PVRTC_2BPPV1_Format)return b.COMPRESSED_RGB_PVRTC_2BPPV1_IMG;
            if (a === THREE.RGBA_PVRTC_4BPPV1_Format)return b.COMPRESSED_RGBA_PVRTC_4BPPV1_IMG;
            if (a === THREE.RGBA_PVRTC_2BPPV1_Format)return b.COMPRESSED_RGBA_PVRTC_2BPPV1_IMG
        }
        if (b = da.get("EXT_blend_minmax"), null !== b) {
            if (a === THREE.MinEquation)return b.MIN_EXT;
            if (a === THREE.MaxEquation)return b.MAX_EXT
        }
        return 0
    }

    console.log("THREE.WebGLRenderer", THREE.REVISION), a = a || {};
    var U = void 0 !== a.canvas ? a.canvas : document.createElement("canvas"), M = void 0 !== a.context ? a.context : null, H = 1, L = void 0 !== a.precision ? a.precision : "highp", P = void 0 !== a.alpha ? a.alpha : !1, N = void 0 !== a.depth ? a.depth : !0, R = void 0 !== a.stencil ? a.stencil : !0, V = void 0 !== a.antialias ? a.antialias : !1, J = void 0 !== a.premultipliedAlpha ? a.premultipliedAlpha : !0, oa = void 0 !== a.preserveDrawingBuffer ? a.preserveDrawingBuffer : !1, ja = void 0 !== a.logarithmicDepthBuffer ? a.logarithmicDepthBuffer : !1, ha = new THREE.Color(0), O = 0, ca = [], ba = {}, qa = [], Ka = [], Qa = [], Xa = [], Ya = [];
    this.domElement = U, this.context = null, this.sortObjects = this.autoClearStencil = this.autoClearDepth = this.autoClearColor = this.autoClear = !0, this.gammaFactor = 2, this.shadowMapEnabled = this.gammaOutput = this.gammaInput = !1, this.shadowMapType = THREE.PCFShadowMap, this.shadowMapCullFace = THREE.CullFaceFront, this.shadowMapCascade = this.shadowMapDebug = !1, this.maxMorphTargets = 8, this.maxMorphNormals = 4, this.autoScaleCubemaps = !0, this.info = {
        memory: {
            programs: 0,
            geometries: 0,
            textures: 0
        }, render: {calls: 0, vertices: 0, faces: 0, points: 0}
    };
    var m, B = this, Pa = [], ob = null, ab = null, ub = -1, ta = "", vb = null, Mb = 0, ib = 0, bb = 0, pb = U.width, qb = U.height, Xb = 0, fc = 0, cb = new THREE.Frustum, db = new THREE.Matrix4, wa = new THREE.Vector3, pa = new THREE.Vector3, Ob = !0, jc = {
        ambient: [0, 0, 0],
        directional: {length: 0, colors: [], positions: []},
        point: {length: 0, colors: [], positions: [], distances: [], decays: []},
        spot: {
            length: 0,
            colors: [],
            positions: [],
            distances: [],
            directions: [],
            anglesCos: [],
            exponents: [],
            decays: []
        },
        hemi: {length: 0, skyColors: [], groundColors: [], positions: []}
    };
    try {
        var Yb = {alpha: P, depth: N, stencil: R, antialias: V, premultipliedAlpha: J, preserveDrawingBuffer: oa};
        if (m = M || U.getContext("webgl", Yb) || U.getContext("experimental-webgl", Yb), null === m) {
            if (null !== U.getContext("webgl"))throw"Error creating WebGL context with your selected attributes.";
            throw"Error creating WebGL context."
        }
        U.addEventListener("webglcontextlost", function (a) {
            a.preventDefault(), Zb(), lc(), ba = {}
        }, !1)
    } catch (rc) {
        THREE.error("THREE.WebGLRenderer: " + rc)
    }
    var W = new THREE.WebGLState(m, I);
    void 0 === m.getShaderPrecisionFormat && (m.getShaderPrecisionFormat = function () {
        return {rangeMin: 1, rangeMax: 1, precision: 1}
    });
    var da = new THREE.WebGLExtensions(m);
    da.get("OES_texture_float"), da.get("OES_texture_float_linear"), da.get("OES_texture_half_float"), da.get("OES_texture_half_float_linear"), da.get("OES_standard_derivatives"), ja && da.get("EXT_frag_depth");
    var rb = function (a, b, c, d) {
        !0 === J && (a *= d, b *= d, c *= d), m.clearColor(a, b, c, d)
    }, lc = function () {
        m.clearColor(0, 0, 0, 1), m.clearDepth(1), m.clearStencil(0), m.enable(m.DEPTH_TEST), m.depthFunc(m.LEQUAL), m.frontFace(m.CCW), m.cullFace(m.BACK), m.enable(m.CULL_FACE), m.enable(m.BLEND), m.blendEquation(m.FUNC_ADD), m.blendFunc(m.SRC_ALPHA, m.ONE_MINUS_SRC_ALPHA), m.viewport(ib, bb, pb, qb), rb(ha.r, ha.g, ha.b, O)
    }, Zb = function () {
        vb = ob = null, ta = "", ub = -1, Ob = !0, W.reset()
    };
    lc(), this.context = m, this.state = W;
    var Wb = m.getParameter(m.MAX_TEXTURE_IMAGE_UNITS), sc = m.getParameter(m.MAX_VERTEX_TEXTURE_IMAGE_UNITS), tc = m.getParameter(m.MAX_TEXTURE_SIZE), qc = m.getParameter(m.MAX_CUBE_MAP_TEXTURE_SIZE), Vb = sc > 0, Nb = Vb && da.get("OES_texture_float"), uc = m.getShaderPrecisionFormat(m.VERTEX_SHADER, m.HIGH_FLOAT), vc = m.getShaderPrecisionFormat(m.VERTEX_SHADER, m.MEDIUM_FLOAT), wc = m.getShaderPrecisionFormat(m.FRAGMENT_SHADER, m.HIGH_FLOAT), xc = m.getShaderPrecisionFormat(m.FRAGMENT_SHADER, m.MEDIUM_FLOAT), kc = function () {
        var a;
        return function () {
            if (void 0 !== a)return a;
            if (a = [], da.get("WEBGL_compressed_texture_pvrtc") || da.get("WEBGL_compressed_texture_s3tc"))for (var b = m.getParameter(m.COMPRESSED_TEXTURE_FORMATS), c = 0; c < b.length; c++)a.push(b[c]);
            return a
        }
    }(), yc = 0 < uc.precision && 0 < wc.precision, mc = 0 < vc.precision && 0 < xc.precision;
    "highp" !== L || yc || (mc ? (L = "mediump", THREE.warn("THREE.WebGLRenderer: highp not supported, using mediump.")) : (L = "lowp", THREE.warn("THREE.WebGLRenderer: highp and mediump not supported, using lowp."))), "mediump" !== L || mc || (L = "lowp", THREE.warn("THREE.WebGLRenderer: mediump not supported, using lowp."));
    var zc = new THREE.ShadowMapPlugin(this, ca, ba, qa), Ac = new THREE.SpritePlugin(this, Xa), Bc = new THREE.LensFlarePlugin(this, Ya);
    this.getContext = function () {
        return m
    }, this.forceContextLoss = function () {
        da.get("WEBGL_lose_context").loseContext()
    }, this.supportsVertexTextures = function () {
        return Vb
    }, this.supportsFloatTextures = function () {
        return da.get("OES_texture_float")
    }, this.supportsHalfFloatTextures = function () {
        return da.get("OES_texture_half_float")
    }, this.supportsStandardDerivatives = function () {
        return da.get("OES_standard_derivatives")
    }, this.supportsCompressedTextureS3TC = function () {
        return da.get("WEBGL_compressed_texture_s3tc")
    }, this.supportsCompressedTexturePVRTC = function () {
        return da.get("WEBGL_compressed_texture_pvrtc")
    }, this.supportsBlendMinMax = function () {
        return da.get("EXT_blend_minmax")
    }, this.getMaxAnisotropy = function () {
        var a;
        return function () {
            if (void 0 !== a)return a;
            var b = da.get("EXT_texture_filter_anisotropic");
            return a = null !== b ? m.getParameter(b.MAX_TEXTURE_MAX_ANISOTROPY_EXT) : 0
        }
    }(), this.getPrecision = function () {
        return L
    }, this.getPixelRatio = function () {
        return H
    }, this.setPixelRatio = function (a) {
        H = a
    }, this.setSize = function (a, b, c) {
        U.width = a * H, U.height = b * H, !1 !== c && (U.style.width = a + "px", U.style.height = b + "px"), this.setViewport(0, 0, a, b)
    }, this.setViewport = function (a, b, c, d) {
        ib = a * H, bb = b * H, pb = c * H, qb = d * H, m.viewport(ib, bb, pb, qb)
    }, this.setScissor = function (a, b, c, d) {
        m.scissor(a * H, b * H, c * H, d * H)
    }, this.enableScissorTest = function (a) {
        a ? m.enable(m.SCISSOR_TEST) : m.disable(m.SCISSOR_TEST)
    }, this.getClearColor = function () {
        return ha
    }, this.setClearColor = function (a, b) {
        ha.set(a), O = void 0 !== b ? b : 1, rb(ha.r, ha.g, ha.b, O)
    }, this.getClearAlpha = function () {
        return O
    }, this.setClearAlpha = function (a) {
        O = a, rb(ha.r, ha.g, ha.b, O)
    }, this.clear = function (a, b, c) {
        var d = 0;
        (void 0 === a || a) && (d |= m.COLOR_BUFFER_BIT), (void 0 === b || b) && (d |= m.DEPTH_BUFFER_BIT), (void 0 === c || c) && (d |= m.STENCIL_BUFFER_BIT), m.clear(d)
    }, this.clearColor = function () {
        m.clear(m.COLOR_BUFFER_BIT)
    }, this.clearDepth = function () {
        m.clear(m.DEPTH_BUFFER_BIT)
    }, this.clearStencil = function () {
        m.clear(m.STENCIL_BUFFER_BIT)
    }, this.clearTarget = function (a, b, c, d) {
        this.setRenderTarget(a), this.clear(b, c, d)
    }, this.resetGLState = Zb;
    var wb = function (a) {
        a.target.traverse(function (a) {
            if (a.removeEventListener("remove", wb), a instanceof THREE.Mesh || a instanceof THREE.PointCloud || a instanceof THREE.Line)delete ba[a.id]; else if (a instanceof THREE.ImmediateRenderObject || a.immediateRenderCallback)for (var b = qa, c = b.length - 1; c >= 0; c--)b[c].object === a && b.splice(c, 1);
            delete a.__webglInit, delete a._modelViewMatrix, delete a._normalMatrix, delete a.__webglActive
        })
    }, jb = function (a) {
        if (a = a.target, a.removeEventListener("dispose", jb), delete a.__webglInit, a instanceof THREE.BufferGeometry) {
            for (var b in a.attributes) {
                var c = a.attributes[b];
                void 0 !== c.buffer && (m.deleteBuffer(c.buffer), delete c.buffer)
            }
            B.info.memory.geometries--
        } else if (b = Ua[a.id], void 0 !== b) {
            for (var c = 0, d = b.length; d > c; c++) {
                var e = b[c];
                if (void 0 !== e.numMorphTargets) {
                    for (var f = 0, g = e.numMorphTargets; g > f; f++)m.deleteBuffer(e.__webglMorphTargetsBuffers[f]);
                    delete e.__webglMorphTargetsBuffers
                }
                if (void 0 !== e.numMorphNormals) {
                    for (f = 0, g = e.numMorphNormals; g > f; f++)m.deleteBuffer(e.__webglMorphNormalsBuffers[f]);
                    delete e.__webglMorphNormalsBuffers
                }
                nc(e)
            }
            delete Ua[a.id]
        } else nc(a);
        ta = ""
    }, Pb = function (a) {
        a = a.target, a.removeEventListener("dispose", Pb), a.image && a.image.__webglTextureCube ? (m.deleteTexture(a.image.__webglTextureCube), delete a.image.__webglTextureCube) : void 0 !== a.__webglInit && (m.deleteTexture(a.__webglTexture), delete a.__webglTexture, delete a.__webglInit), B.info.memory.textures--
    }, oc = function (a) {
        if (a = a.target, a.removeEventListener("dispose", oc), a && void 0 !== a.__webglTexture) {
            if (m.deleteTexture(a.__webglTexture), delete a.__webglTexture, a instanceof THREE.WebGLRenderTargetCube)for (var b = 0; 6 > b; b++)m.deleteFramebuffer(a.__webglFramebuffer[b]), m.deleteRenderbuffer(a.__webglRenderbuffer[b]); else m.deleteFramebuffer(a.__webglFramebuffer), m.deleteRenderbuffer(a.__webglRenderbuffer);
            delete a.__webglFramebuffer, delete a.__webglRenderbuffer
        }
        B.info.memory.textures--
    }, ic = function (a) {
        a = a.target, a.removeEventListener("dispose", ic), hc(a)
    }, nc = function (a) {
        for (var b = "__webglVertexBuffer __webglNormalBuffer __webglTangentBuffer __webglColorBuffer __webglUVBuffer __webglUV2Buffer __webglSkinIndicesBuffer __webglSkinWeightsBuffer __webglFaceBuffer __webglLineBuffer __webglLineDistanceBuffer".split(" "), c = 0, d = b.length; d > c; c++) {
            var e = b[c];
            void 0 !== a[e] && (m.deleteBuffer(a[e]), delete a[e])
        }
        if (void 0 !== a.__webglCustomAttributesList) {
            for (e in a.__webglCustomAttributesList)m.deleteBuffer(a.__webglCustomAttributesList[e].buffer);
            delete a.__webglCustomAttributesList
        }
        B.info.memory.geometries--
    }, hc = function (a) {
        var b = a.program.program;
        if (void 0 !== b) {
            a.program = void 0;
            var c, d, e = !1;
            for (a = 0, c = Pa.length; c > a; a++)if (d = Pa[a], d.program === b) {
                d.usedTimes--, 0 === d.usedTimes && (e = !0);
                break
            }
            if (!0 === e) {
                for (e = [], a = 0, c = Pa.length; c > a; a++)d = Pa[a], d.program !== b && e.push(d);
                Pa = e, m.deleteProgram(b), B.info.memory.programs--
            }
        }
    };
    this.renderBufferImmediate = function (a, b, c) {
        if (W.initAttributes(), a.hasPositions && !a.__webglVertexBuffer && (a.__webglVertexBuffer = m.createBuffer()), a.hasNormals && !a.__webglNormalBuffer && (a.__webglNormalBuffer = m.createBuffer()), a.hasUvs && !a.__webglUvBuffer && (a.__webglUvBuffer = m.createBuffer()), a.hasColors && !a.__webglColorBuffer && (a.__webglColorBuffer = m.createBuffer()), a.hasPositions && (m.bindBuffer(m.ARRAY_BUFFER, a.__webglVertexBuffer), m.bufferData(m.ARRAY_BUFFER, a.positionArray, m.DYNAMIC_DRAW), W.enableAttribute(b.attributes.position), m.vertexAttribPointer(b.attributes.position, 3, m.FLOAT, !1, 0, 0)), a.hasNormals) {
            if (m.bindBuffer(m.ARRAY_BUFFER, a.__webglNormalBuffer), !1 == c instanceof THREE.MeshPhongMaterial && c.shading === THREE.FlatShading) {
                var d, e, f, g, h, k, n, l, p, q, r, s = 3 * a.count;
                for (r = 0; s > r; r += 9)q = a.normalArray, d = q[r], e = q[r + 1], f = q[r + 2], g = q[r + 3], k = q[r + 4], l = q[r + 5], h = q[r + 6], n = q[r + 7], p = q[r + 8], d = (d + g + h) / 3, e = (e + k + n) / 3, f = (f + l + p) / 3, q[r] = d, q[r + 1] = e, q[r + 2] = f, q[r + 3] = d, q[r + 4] = e, q[r + 5] = f, q[r + 6] = d, q[r + 7] = e, q[r + 8] = f
            }
            m.bufferData(m.ARRAY_BUFFER, a.normalArray, m.DYNAMIC_DRAW), W.enableAttribute(b.attributes.normal), m.vertexAttribPointer(b.attributes.normal, 3, m.FLOAT, !1, 0, 0)
        }
        a.hasUvs && c.map && (m.bindBuffer(m.ARRAY_BUFFER, a.__webglUvBuffer), m.bufferData(m.ARRAY_BUFFER, a.uvArray, m.DYNAMIC_DRAW), W.enableAttribute(b.attributes.uv), m.vertexAttribPointer(b.attributes.uv, 2, m.FLOAT, !1, 0, 0)), a.hasColors && c.vertexColors !== THREE.NoColors && (m.bindBuffer(m.ARRAY_BUFFER, a.__webglColorBuffer), m.bufferData(m.ARRAY_BUFFER, a.colorArray, m.DYNAMIC_DRAW), W.enableAttribute(b.attributes.color), m.vertexAttribPointer(b.attributes.color, 3, m.FLOAT, !1, 0, 0)), W.disableUnusedAttributes(), m.drawArrays(m.TRIANGLES, 0, a.count), a.count = 0
    }, this.renderBufferDirect = function (a, b, c, e, f, g) {
        if (!1 !== e.visible)if (t(g), a = v(a, b, c, e, g), b = !1, c = "direct_" + f.id + "_" + a.id + "_" + (e.wireframe ? 1 : 0), c !== ta && (ta = c, b = !0), b && W.initAttributes(), g instanceof THREE.Mesh) {
            g = !0 === e.wireframe ? m.LINES : m.TRIANGLES;
            var h = f.attributes.index;
            if (h) {
                var k, n;
                if (h.array instanceof Uint32Array && da.get("OES_element_index_uint") ? (k = m.UNSIGNED_INT, n = 4) : (k = m.UNSIGNED_SHORT, n = 2), c = f.offsets, 0 === c.length)b && (d(e, a, f, 0), m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, h.buffer)), m.drawElements(g, h.array.length, k, 0), B.info.render.calls++, B.info.render.vertices += h.array.length, B.info.render.faces += h.array.length / 3; else {
                    b = !0;
                    for (var l = 0, p = c.length; p > l; l++) {
                        var q = c[l].index;
                        b && (d(e, a, f, q), m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, h.buffer)), m.drawElements(g, c[l].count, k, c[l].start * n), B.info.render.calls++, B.info.render.vertices += c[l].count, B.info.render.faces += c[l].count / 3
                    }
                }
            } else b && d(e, a, f, 0), e = f.attributes.position, m.drawArrays(g, 0, e.array.length / e.itemSize), B.info.render.calls++, B.info.render.vertices += e.array.length / e.itemSize, B.info.render.faces += e.array.length / (3 * e.itemSize)
        } else if (g instanceof THREE.PointCloud)if (g = m.POINTS, h = f.attributes.index)if (h.array instanceof Uint32Array && da.get("OES_element_index_uint") ? (k = m.UNSIGNED_INT, n = 4) : (k = m.UNSIGNED_SHORT, n = 2), c = f.offsets, 0 === c.length)b && (d(e, a, f, 0), m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, h.buffer)), m.drawElements(g, h.array.length, k, 0), B.info.render.calls++, B.info.render.points += h.array.length; else for (1 < c.length && (b = !0), l = 0, p = c.length; p > l; l++)q = c[l].index, b && (d(e, a, f, q), m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, h.buffer)), m.drawElements(g, c[l].count, k, c[l].start * n), B.info.render.calls++, B.info.render.points += c[l].count; else if (b && d(e, a, f, 0), e = f.attributes.position, c = f.offsets, 0 === c.length)m.drawArrays(g, 0, e.array.length / 3), B.info.render.calls++, B.info.render.points += e.array.length / 3; else for (l = 0, p = c.length; p > l; l++)m.drawArrays(g, c[l].index, c[l].count), B.info.render.calls++, B.info.render.points += c[l].count; else if (g instanceof THREE.Line)if (g = g.mode === THREE.LineStrip ? m.LINE_STRIP : m.LINES, W.setLineWidth(e.linewidth * H), h = f.attributes.index)if (h.array instanceof Uint32Array ? (k = m.UNSIGNED_INT, n = 4) : (k = m.UNSIGNED_SHORT, n = 2), c = f.offsets, 0 === c.length)b && (d(e, a, f, 0), m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, h.buffer)), m.drawElements(g, h.array.length, k, 0), B.info.render.calls++, B.info.render.vertices += h.array.length; else for (1 < c.length && (b = !0), l = 0, p = c.length; p > l; l++)q = c[l].index, b && (d(e, a, f, q), m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, h.buffer)), m.drawElements(g, c[l].count, k, c[l].start * n), B.info.render.calls++, B.info.render.vertices += c[l].count; else if (b && d(e, a, f, 0), e = f.attributes.position, c = f.offsets, 0 === c.length)m.drawArrays(g, 0, e.array.length / 3), B.info.render.calls++, B.info.render.vertices += e.array.length / 3; else for (l = 0, p = c.length; p > l; l++)m.drawArrays(g, c[l].index, c[l].count), B.info.render.calls++, B.info.render.vertices += c[l].count
    }, this.renderBuffer = function (a, b, c, d, e, f) {
        if (!1 !== d.visible) {
            if (t(f), c = v(a, b, c, d, f), b = c.attributes, a = !1, c = e.id + "_" + c.id + "_" + (d.wireframe ? 1 : 0), c !== ta && (ta = c, a = !0), a && W.initAttributes(), !d.morphTargets && 0 <= b.position)a && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglVertexBuffer), W.enableAttribute(b.position), m.vertexAttribPointer(b.position, 3, m.FLOAT, !1, 0, 0)); else if (f.morphTargetBase) {
                if (c = d.program.attributes, -1 !== f.morphTargetBase && 0 <= c.position ? (m.bindBuffer(m.ARRAY_BUFFER, e.__webglMorphTargetsBuffers[f.morphTargetBase]), W.enableAttribute(c.position), m.vertexAttribPointer(c.position, 3, m.FLOAT, !1, 0, 0)) : 0 <= c.position && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglVertexBuffer), W.enableAttribute(c.position), m.vertexAttribPointer(c.position, 3, m.FLOAT, !1, 0, 0)), f.morphTargetForcedOrder.length)for (var l, h = 0, k = f.morphTargetForcedOrder, n = f.morphTargetInfluences; h < d.numSupportedMorphTargets && h < k.length;)l = c["morphTarget" + h], l >= 0 && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglMorphTargetsBuffers[k[h]]), W.enableAttribute(l), m.vertexAttribPointer(l, 3, m.FLOAT, !1, 0, 0)), l = c["morphNormal" + h], l >= 0 && d.morphNormals && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglMorphNormalsBuffers[k[h]]), W.enableAttribute(l), m.vertexAttribPointer(l, 3, m.FLOAT, !1, 0, 0)), f.__webglMorphTargetInfluences[h] = n[k[h]], h++; else {
                    for (k = [], n = f.morphTargetInfluences, h = f.geometry.morphTargets, n.length > h.length && (console.warn("THREE.WebGLRenderer: Influences array is bigger than morphTargets array."), n.length = h.length), h = 0, l = n.length; l > h; h++)k.push([n[h], h]);
                    k.length > d.numSupportedMorphTargets ? (k.sort(g), k.length = d.numSupportedMorphTargets) : k.length > d.numSupportedMorphNormals ? k.sort(g) : 0 === k.length && k.push([0, 0]);
                    for (var h = 0, p = d.numSupportedMorphTargets; p > h; h++)if (k[h]) {
                        var q = k[h][1];
                        l = c["morphTarget" + h], l >= 0 && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglMorphTargetsBuffers[q]), W.enableAttribute(l), m.vertexAttribPointer(l, 3, m.FLOAT, !1, 0, 0)), l = c["morphNormal" + h], l >= 0 && d.morphNormals && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglMorphNormalsBuffers[q]), W.enableAttribute(l), m.vertexAttribPointer(l, 3, m.FLOAT, !1, 0, 0)), f.__webglMorphTargetInfluences[h] = n[q]
                    } else f.__webglMorphTargetInfluences[h] = 0
                }
                null !== d.program.uniforms.morphTargetInfluences && m.uniform1fv(d.program.uniforms.morphTargetInfluences, f.__webglMorphTargetInfluences)
            }
            if (a) {
                if (e.__webglCustomAttributesList)for (c = 0, n = e.__webglCustomAttributesList.length; n > c; c++)k = e.__webglCustomAttributesList[c], 0 <= b[k.buffer.belongsToAttribute] && (m.bindBuffer(m.ARRAY_BUFFER, k.buffer), W.enableAttribute(b[k.buffer.belongsToAttribute]), m.vertexAttribPointer(b[k.buffer.belongsToAttribute], k.size, m.FLOAT, !1, 0, 0));
                0 <= b.color && (0 < f.geometry.colors.length || 0 < f.geometry.faces.length ? (m.bindBuffer(m.ARRAY_BUFFER, e.__webglColorBuffer), W.enableAttribute(b.color), m.vertexAttribPointer(b.color, 3, m.FLOAT, !1, 0, 0)) : void 0 !== d.defaultAttributeValues && m.vertexAttrib3fv(b.color, d.defaultAttributeValues.color)), 0 <= b.normal && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglNormalBuffer), W.enableAttribute(b.normal), m.vertexAttribPointer(b.normal, 3, m.FLOAT, !1, 0, 0)), 0 <= b.tangent && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglTangentBuffer), W.enableAttribute(b.tangent), m.vertexAttribPointer(b.tangent, 4, m.FLOAT, !1, 0, 0)), 0 <= b.uv && (f.geometry.faceVertexUvs[0] ? (m.bindBuffer(m.ARRAY_BUFFER, e.__webglUVBuffer), W.enableAttribute(b.uv), m.vertexAttribPointer(b.uv, 2, m.FLOAT, !1, 0, 0)) : void 0 !== d.defaultAttributeValues && m.vertexAttrib2fv(b.uv, d.defaultAttributeValues.uv)),
                0 <= b.uv2 && (f.geometry.faceVertexUvs[1] ? (m.bindBuffer(m.ARRAY_BUFFER, e.__webglUV2Buffer), W.enableAttribute(b.uv2), m.vertexAttribPointer(b.uv2, 2, m.FLOAT, !1, 0, 0)) : void 0 !== d.defaultAttributeValues && m.vertexAttrib2fv(b.uv2, d.defaultAttributeValues.uv2)), d.skinning && 0 <= b.skinIndex && 0 <= b.skinWeight && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglSkinIndicesBuffer), W.enableAttribute(b.skinIndex), m.vertexAttribPointer(b.skinIndex, 4, m.FLOAT, !1, 0, 0), m.bindBuffer(m.ARRAY_BUFFER, e.__webglSkinWeightsBuffer), W.enableAttribute(b.skinWeight), m.vertexAttribPointer(b.skinWeight, 4, m.FLOAT, !1, 0, 0)), 0 <= b.lineDistance && (m.bindBuffer(m.ARRAY_BUFFER, e.__webglLineDistanceBuffer), W.enableAttribute(b.lineDistance), m.vertexAttribPointer(b.lineDistance, 1, m.FLOAT, !1, 0, 0))
            }
            W.disableUnusedAttributes(), f instanceof THREE.Mesh ? (f = e.__typeArray === Uint32Array ? m.UNSIGNED_INT : m.UNSIGNED_SHORT, d.wireframe ? (W.setLineWidth(d.wireframeLinewidth * H), a && m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, e.__webglLineBuffer), m.drawElements(m.LINES, e.__webglLineCount, f, 0)) : (a && m.bindBuffer(m.ELEMENT_ARRAY_BUFFER, e.__webglFaceBuffer), m.drawElements(m.TRIANGLES, e.__webglFaceCount, f, 0)), B.info.render.calls++, B.info.render.vertices += e.__webglFaceCount, B.info.render.faces += e.__webglFaceCount / 3) : f instanceof THREE.Line ? (f = f.mode === THREE.LineStrip ? m.LINE_STRIP : m.LINES, W.setLineWidth(d.linewidth * H), m.drawArrays(f, 0, e.__webglLineCount), B.info.render.calls++) : f instanceof THREE.PointCloud && (m.drawArrays(m.POINTS, 0, e.__webglParticleCount), B.info.render.calls++, B.info.render.points += e.__webglParticleCount)
        }
    }, this.render = function (a, b, c, d) {
        if (!1 == b instanceof THREE.Camera)THREE.error("THREE.WebGLRenderer.render: camera is not an instance of THREE.Camera."); else {
            var g = a.fog;
            ta = "", ub = -1, vb = null, Ob = !0, !0 === a.autoUpdate && a.updateMatrixWorld(), void 0 === b.parent && b.updateMatrixWorld(), a.traverse(function (a) {
                a instanceof THREE.SkinnedMesh && a.skeleton.update()
            }), b.matrixWorldInverse.getInverse(b.matrixWorld), db.multiplyMatrices(b.projectionMatrix, b.matrixWorldInverse), cb.setFromMatrix(db), ca.length = 0, Ka.length = 0, Qa.length = 0, Xa.length = 0, Ya.length = 0, h(a), !0 === B.sortObjects && (Ka.sort(e), Qa.sort(f)), zc.render(a, b), B.info.render.calls = 0, B.info.render.vertices = 0, B.info.render.faces = 0, B.info.render.points = 0, this.setRenderTarget(c), (this.autoClear || d) && this.clear(this.autoClearColor, this.autoClearDepth, this.autoClearStencil), d = 0;
            for (var n = qa.length; n > d; d++) {
                var m = qa[d], q = m.object;
                q.visible && (w(q, b), p(m))
            }
            a.overrideMaterial ? (d = a.overrideMaterial, u(d), k(Ka, b, ca, g, d), k(Qa, b, ca, g, d), l(qa, "", b, ca, g, d)) : (W.setBlending(THREE.NoBlending), k(Ka, b, ca, g, null), l(qa, "opaque", b, ca, g, null), k(Qa, b, ca, g, null), l(qa, "transparent", b, ca, g, null)), Ac.render(a, b), Bc.render(a, b, Xb, fc), c && c.generateMipmaps && c.minFilter !== THREE.NearestFilter && c.minFilter !== THREE.LinearFilter && F(c), W.setDepthTest(!0), W.setDepthWrite(!0), W.setColorWrite(!0)
        }
    }, this.renderImmediateObject = function (a, b, c, d, e) {
        var f = v(a, b, c, d, e);
        ta = "", B.setMaterialFaces(d), e.immediateRenderCallback ? e.immediateRenderCallback(f, m, cb) : e.render(function (a) {
            B.renderBufferImmediate(a, f, d)
        })
    };
    var Ua = {}, Qb = 0, pc = {
        MeshDepthMaterial: "depth",
        MeshNormalMaterial: "normal",
        MeshBasicMaterial: "basic",
        MeshLambertMaterial: "lambert",
        MeshPhongMaterial: "phong",
        LineBasicMaterial: "basic",
        LineDashedMaterial: "dashed",
        PointCloudMaterial: "particle_basic"
    };
    this.setFaceCulling = function (a, b) {
        a === THREE.CullFaceNone ? m.disable(m.CULL_FACE) : (b === THREE.FrontFaceDirectionCW ? m.frontFace(m.CW) : m.frontFace(m.CCW), a === THREE.CullFaceBack ? m.cullFace(m.BACK) : a === THREE.CullFaceFront ? m.cullFace(m.FRONT) : m.cullFace(m.FRONT_AND_BACK), m.enable(m.CULL_FACE))
    }, this.setMaterialFaces = function (a) {
        W.setDoubleSided(a.side === THREE.DoubleSide), W.setFlipSided(a.side === THREE.BackSide)
    }, this.uploadTexture = function (a) {
        void 0 === a.__webglInit && (a.__webglInit = !0, a.addEventListener("dispose", Pb), a.__webglTexture = m.createTexture(), B.info.memory.textures++), m.bindTexture(m.TEXTURE_2D, a.__webglTexture), m.pixelStorei(m.UNPACK_FLIP_Y_WEBGL, a.flipY), m.pixelStorei(m.UNPACK_PREMULTIPLY_ALPHA_WEBGL, a.premultiplyAlpha), m.pixelStorei(m.UNPACK_ALIGNMENT, a.unpackAlignment), a.image = E(a.image, tc);
        var b = a.image, c = THREE.Math.isPowerOfTwo(b.width) && THREE.Math.isPowerOfTwo(b.height), d = I(a.format), e = I(a.type);
        A(m.TEXTURE_2D, a, c);
        var f = a.mipmaps;
        if (a instanceof THREE.DataTexture)if (0 < f.length && c) {
            for (var g = 0, h = f.length; h > g; g++)b = f[g], m.texImage2D(m.TEXTURE_2D, g, d, b.width, b.height, 0, d, e, b.data);
            a.generateMipmaps = !1
        } else m.texImage2D(m.TEXTURE_2D, 0, d, b.width, b.height, 0, d, e, b.data); else if (a instanceof THREE.CompressedTexture)for (g = 0, h = f.length; h > g; g++)b = f[g], a.format !== THREE.RGBAFormat && a.format !== THREE.RGBFormat ? -1 < kc().indexOf(d) ? m.compressedTexImage2D(m.TEXTURE_2D, g, d, b.width, b.height, 0, b.data) : THREE.warn("THREE.WebGLRenderer: Attempt to load unsupported compressed texture format in .uploadTexture()") : m.texImage2D(m.TEXTURE_2D, g, d, b.width, b.height, 0, d, e, b.data); else if (0 < f.length && c) {
            for (g = 0, h = f.length; h > g; g++)b = f[g], m.texImage2D(m.TEXTURE_2D, g, d, d, e, b);
            a.generateMipmaps = !1
        } else m.texImage2D(m.TEXTURE_2D, 0, d, d, e, a.image);
        a.generateMipmaps && c && m.generateMipmap(m.TEXTURE_2D), a.needsUpdate = !1, a.onUpdate && a.onUpdate()
    }, this.setTexture = function (a, b) {
        m.activeTexture(m.TEXTURE0 + b), a.needsUpdate ? B.uploadTexture(a) : m.bindTexture(m.TEXTURE_2D, a.__webglTexture)
    }, this.setRenderTarget = function (a) {
        var b = a instanceof THREE.WebGLRenderTargetCube;
        if (a && void 0 === a.__webglFramebuffer) {
            void 0 === a.depthBuffer && (a.depthBuffer = !0), void 0 === a.stencilBuffer && (a.stencilBuffer = !0), a.addEventListener("dispose", oc), a.__webglTexture = m.createTexture(), B.info.memory.textures++;
            var c = THREE.Math.isPowerOfTwo(a.width) && THREE.Math.isPowerOfTwo(a.height), d = I(a.format), e = I(a.type);
            if (b) {
                a.__webglFramebuffer = [], a.__webglRenderbuffer = [], m.bindTexture(m.TEXTURE_CUBE_MAP, a.__webglTexture), A(m.TEXTURE_CUBE_MAP, a, c);
                for (var f = 0; 6 > f; f++) {
                    a.__webglFramebuffer[f] = m.createFramebuffer(), a.__webglRenderbuffer[f] = m.createRenderbuffer(), m.texImage2D(m.TEXTURE_CUBE_MAP_POSITIVE_X + f, 0, d, a.width, a.height, 0, d, e, null);
                    var g = a, h = m.TEXTURE_CUBE_MAP_POSITIVE_X + f;
                    m.bindFramebuffer(m.FRAMEBUFFER, a.__webglFramebuffer[f]), m.framebufferTexture2D(m.FRAMEBUFFER, m.COLOR_ATTACHMENT0, h, g.__webglTexture, 0), G(a.__webglRenderbuffer[f], a)
                }
                c && m.generateMipmap(m.TEXTURE_CUBE_MAP)
            } else a.__webglFramebuffer = m.createFramebuffer(), a.__webglRenderbuffer = a.shareDepthFrom ? a.shareDepthFrom.__webglRenderbuffer : m.createRenderbuffer(), m.bindTexture(m.TEXTURE_2D, a.__webglTexture), A(m.TEXTURE_2D, a, c), m.texImage2D(m.TEXTURE_2D, 0, d, a.width, a.height, 0, d, e, null), d = m.TEXTURE_2D, m.bindFramebuffer(m.FRAMEBUFFER, a.__webglFramebuffer), m.framebufferTexture2D(m.FRAMEBUFFER, m.COLOR_ATTACHMENT0, d, a.__webglTexture, 0), a.shareDepthFrom ? a.depthBuffer && !a.stencilBuffer ? m.framebufferRenderbuffer(m.FRAMEBUFFER, m.DEPTH_ATTACHMENT, m.RENDERBUFFER, a.__webglRenderbuffer) : a.depthBuffer && a.stencilBuffer && m.framebufferRenderbuffer(m.FRAMEBUFFER, m.DEPTH_STENCIL_ATTACHMENT, m.RENDERBUFFER, a.__webglRenderbuffer) : G(a.__webglRenderbuffer, a), c && m.generateMipmap(m.TEXTURE_2D);
            b ? m.bindTexture(m.TEXTURE_CUBE_MAP, null) : m.bindTexture(m.TEXTURE_2D, null), m.bindRenderbuffer(m.RENDERBUFFER, null), m.bindFramebuffer(m.FRAMEBUFFER, null)
        }
        a ? (b = b ? a.__webglFramebuffer[a.activeCubeFace] : a.__webglFramebuffer, c = a.width, a = a.height, e = d = 0) : (b = null, c = pb, a = qb, d = ib, e = bb), b !== ab && (m.bindFramebuffer(m.FRAMEBUFFER, b), m.viewport(d, e, c, a), ab = b), Xb = c, fc = a
    }, this.readRenderTargetPixels = function (a, b, c, d, e, f) {
        if (a instanceof THREE.WebGLRenderTarget) {
            if (a.__webglFramebuffer)if (a.format !== THREE.RGBAFormat)console.error("THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not in RGBA format. readPixels can read only RGBA format."); else {
                var g = !1;
                a.__webglFramebuffer !== ab && (m.bindFramebuffer(m.FRAMEBUFFER, a.__webglFramebuffer), g = !0), m.checkFramebufferStatus(m.FRAMEBUFFER) === m.FRAMEBUFFER_COMPLETE ? m.readPixels(b, c, d, e, m.RGBA, m.UNSIGNED_BYTE, f) : console.error("THREE.WebGLRenderer.readRenderTargetPixels: readPixels from renderTarget failed. Framebuffer not complete."), g && m.bindFramebuffer(m.FRAMEBUFFER, ab)
            }
        } else console.error("THREE.WebGLRenderer.readRenderTargetPixels: renderTarget is not THREE.WebGLRenderTarget.")
    }, this.initMaterial = function () {
        THREE.warn("THREE.WebGLRenderer: .initMaterial() has been removed.")
    }, this.addPrePlugin = function () {
        THREE.warn("THREE.WebGLRenderer: .addPrePlugin() has been removed.")
    }, this.addPostPlugin = function () {
        THREE.warn("THREE.WebGLRenderer: .addPostPlugin() has been removed.")
    }, this.updateShadowMap = function () {
        THREE.warn("THREE.WebGLRenderer: .updateShadowMap() has been removed.")
    }
},THREE.WebGLRenderTarget = function (a, b, c) {
    this.width = a, this.height = b, c = c || {}, this.wrapS = void 0 !== c.wrapS ? c.wrapS : THREE.ClampToEdgeWrapping, this.wrapT = void 0 !== c.wrapT ? c.wrapT : THREE.ClampToEdgeWrapping, this.magFilter = void 0 !== c.magFilter ? c.magFilter : THREE.LinearFilter, this.minFilter = void 0 !== c.minFilter ? c.minFilter : THREE.LinearMipMapLinearFilter, this.anisotropy = void 0 !== c.anisotropy ? c.anisotropy : 1, this.offset = new THREE.Vector2(0, 0), this.repeat = new THREE.Vector2(1, 1), this.format = void 0 !== c.format ? c.format : THREE.RGBAFormat, this.type = void 0 !== c.type ? c.type : THREE.UnsignedByteType, this.depthBuffer = void 0 !== c.depthBuffer ? c.depthBuffer : !0, this.stencilBuffer = void 0 !== c.stencilBuffer ? c.stencilBuffer : !0, this.generateMipmaps = !0, this.shareDepthFrom = void 0 !== c.shareDepthFrom ? c.shareDepthFrom : null
},THREE.WebGLRenderTarget.prototype = {
    constructor: THREE.WebGLRenderTarget, setSize: function (a, b) {
        this.width = a, this.height = b
    }, clone: function () {
        var a = new THREE.WebGLRenderTarget(this.width, this.height);
        return a.wrapS = this.wrapS, a.wrapT = this.wrapT, a.magFilter = this.magFilter, a.minFilter = this.minFilter, a.anisotropy = this.anisotropy, a.offset.copy(this.offset), a.repeat.copy(this.repeat), a.format = this.format, a.type = this.type, a.depthBuffer = this.depthBuffer, a.stencilBuffer = this.stencilBuffer, a.generateMipmaps = this.generateMipmaps, a.shareDepthFrom = this.shareDepthFrom, a
    }, dispose: function () {
        this.dispatchEvent({type: "dispose"})
    }
},THREE.EventDispatcher.prototype.apply(THREE.WebGLRenderTarget.prototype),THREE.WebGLRenderTargetCube = function (a, b, c) {
    THREE.WebGLRenderTarget.call(this, a, b, c), this.activeCubeFace = 0
},THREE.WebGLRenderTargetCube.prototype = Object.create(THREE.WebGLRenderTarget.prototype),THREE.WebGLRenderTargetCube.prototype.constructor = THREE.WebGLRenderTargetCube,THREE.WebGLExtensions = function (a) {
    var b = {};
    this.get = function (c) {
        if (void 0 !== b[c])return b[c];
        var d;
        switch (c) {
            case"EXT_texture_filter_anisotropic":
                d = a.getExtension("EXT_texture_filter_anisotropic") || a.getExtension("MOZ_EXT_texture_filter_anisotropic") || a.getExtension("WEBKIT_EXT_texture_filter_anisotropic");
                break;
            case"WEBGL_compressed_texture_s3tc":
                d = a.getExtension("WEBGL_compressed_texture_s3tc") || a.getExtension("MOZ_WEBGL_compressed_texture_s3tc") || a.getExtension("WEBKIT_WEBGL_compressed_texture_s3tc");
                break;
            case"WEBGL_compressed_texture_pvrtc":
                d = a.getExtension("WEBGL_compressed_texture_pvrtc") || a.getExtension("WEBKIT_WEBGL_compressed_texture_pvrtc");
                break;
            default:
                d = a.getExtension(c)
        }
        return null === d && THREE.warn("THREE.WebGLRenderer: " + c + " extension not supported."), b[c] = d
    }
},THREE.WebGLProgram = function () {
    var a = 0;
    return function (b, c, d, e) {
        var f = b.context, g = d.defines, h = d.__webglShader.uniforms, k = d.attributes, l = d.__webglShader.vertexShader, p = d.__webglShader.fragmentShader, q = d.index0AttributeName;
        void 0 === q && !0 === e.morphTargets && (q = "position");
        var n = "SHADOWMAP_TYPE_BASIC";
        e.shadowMapType === THREE.PCFShadowMap ? n = "SHADOWMAP_TYPE_PCF" : e.shadowMapType === THREE.PCFSoftShadowMap && (n = "SHADOWMAP_TYPE_PCF_SOFT");
        var t = "ENVMAP_TYPE_CUBE", r = "ENVMAP_MODE_REFLECTION", s = "ENVMAP_BLENDING_MULTIPLY";
        if (e.envMap) {
            switch (d.envMap.mapping) {
                case THREE.CubeReflectionMapping:
                case THREE.CubeRefractionMapping:
                    t = "ENVMAP_TYPE_CUBE";
                    break;
                case THREE.EquirectangularReflectionMapping:
                case THREE.EquirectangularRefractionMapping:
                    t = "ENVMAP_TYPE_EQUIREC";
                    break;
                case THREE.SphericalReflectionMapping:
                    t = "ENVMAP_TYPE_SPHERE"
            }
            switch (d.envMap.mapping) {
                case THREE.CubeRefractionMapping:
                case THREE.EquirectangularRefractionMapping:
                    r = "ENVMAP_MODE_REFRACTION"
            }
            switch (d.combine) {
                case THREE.MultiplyOperation:
                    s = "ENVMAP_BLENDING_MULTIPLY";
                    break;
                case THREE.MixOperation:
                    s = "ENVMAP_BLENDING_MIX";
                    break;
                case THREE.AddOperation:
                    s = "ENVMAP_BLENDING_ADD"
            }
        }
        var v, x, u = 0 < b.gammaFactor ? b.gammaFactor : 1;
        v = [];
        for (var D in g)x = g[D], !1 !== x && (x = "#define " + D + " " + x, v.push(x));
        v = v.join("\n"), g = f.createProgram(), d instanceof THREE.RawShaderMaterial ? b = d = "" : (d = ["precision " + e.precision + " float;", "precision " + e.precision + " int;", v, e.supportsVertexTextures ? "#define VERTEX_TEXTURES" : "", b.gammaInput ? "#define GAMMA_INPUT" : "", b.gammaOutput ? "#define GAMMA_OUTPUT" : "", "#define GAMMA_FACTOR " + u, "#define MAX_DIR_LIGHTS " + e.maxDirLights, "#define MAX_POINT_LIGHTS " + e.maxPointLights, "#define MAX_SPOT_LIGHTS " + e.maxSpotLights, "#define MAX_HEMI_LIGHTS " + e.maxHemiLights, "#define MAX_SHADOWS " + e.maxShadows, "#define MAX_BONES " + e.maxBones, e.map ? "#define USE_MAP" : "", e.envMap ? "#define USE_ENVMAP" : "", e.envMap ? "#define " + r : "", e.lightMap ? "#define USE_LIGHTMAP" : "", e.bumpMap ? "#define USE_BUMPMAP" : "", e.normalMap ? "#define USE_NORMALMAP" : "", e.specularMap ? "#define USE_SPECULARMAP" : "", e.alphaMap ? "#define USE_ALPHAMAP" : "", e.vertexColors ? "#define USE_COLOR" : "", e.flatShading ? "#define FLAT_SHADED" : "", e.skinning ? "#define USE_SKINNING" : "", e.useVertexTexture ? "#define BONE_TEXTURE" : "", e.morphTargets ? "#define USE_MORPHTARGETS" : "", e.morphNormals ? "#define USE_MORPHNORMALS" : "", e.wrapAround ? "#define WRAP_AROUND" : "", e.doubleSided ? "#define DOUBLE_SIDED" : "", e.flipSided ? "#define FLIP_SIDED" : "", e.shadowMapEnabled ? "#define USE_SHADOWMAP" : "", e.shadowMapEnabled ? "#define " + n : "", e.shadowMapDebug ? "#define SHADOWMAP_DEBUG" : "", e.shadowMapCascade ? "#define SHADOWMAP_CASCADE" : "", e.sizeAttenuation ? "#define USE_SIZEATTENUATION" : "", e.logarithmicDepthBuffer ? "#define USE_LOGDEPTHBUF" : "", "uniform mat4 modelMatrix;\nuniform mat4 modelViewMatrix;\nuniform mat4 projectionMatrix;\nuniform mat4 viewMatrix;\nuniform mat3 normalMatrix;\nuniform vec3 cameraPosition;\nattribute vec3 position;\nattribute vec3 normal;\nattribute vec2 uv;\nattribute vec2 uv2;\n#ifdef USE_COLOR\n	attribute vec3 color;\n#endif\n#ifdef USE_MORPHTARGETS\n	attribute vec3 morphTarget0;\n	attribute vec3 morphTarget1;\n	attribute vec3 morphTarget2;\n	attribute vec3 morphTarget3;\n	#ifdef USE_MORPHNORMALS\n		attribute vec3 morphNormal0;\n		attribute vec3 morphNormal1;\n		attribute vec3 morphNormal2;\n		attribute vec3 morphNormal3;\n	#else\n		attribute vec3 morphTarget4;\n		attribute vec3 morphTarget5;\n		attribute vec3 morphTarget6;\n		attribute vec3 morphTarget7;\n	#endif\n#endif\n#ifdef USE_SKINNING\n	attribute vec4 skinIndex;\n	attribute vec4 skinWeight;\n#endif\n"].join("\n"), b = ["precision " + e.precision + " float;", "precision " + e.precision + " int;", e.bumpMap || e.normalMap || e.flatShading ? "#extension GL_OES_standard_derivatives : enable" : "", v, "#define MAX_DIR_LIGHTS " + e.maxDirLights, "#define MAX_POINT_LIGHTS " + e.maxPointLights, "#define MAX_SPOT_LIGHTS " + e.maxSpotLights, "#define MAX_HEMI_LIGHTS " + e.maxHemiLights, "#define MAX_SHADOWS " + e.maxShadows, e.alphaTest ? "#define ALPHATEST " + e.alphaTest : "", b.gammaInput ? "#define GAMMA_INPUT" : "", b.gammaOutput ? "#define GAMMA_OUTPUT" : "", "#define GAMMA_FACTOR " + u, e.useFog && e.fog ? "#define USE_FOG" : "", e.useFog && e.fogExp ? "#define FOG_EXP2" : "", e.map ? "#define USE_MAP" : "", e.envMap ? "#define USE_ENVMAP" : "", e.envMap ? "#define " + t : "", e.envMap ? "#define " + r : "", e.envMap ? "#define " + s : "", e.lightMap ? "#define USE_LIGHTMAP" : "", e.bumpMap ? "#define USE_BUMPMAP" : "", e.normalMap ? "#define USE_NORMALMAP" : "", e.specularMap ? "#define USE_SPECULARMAP" : "", e.alphaMap ? "#define USE_ALPHAMAP" : "", e.vertexColors ? "#define USE_COLOR" : "", e.flatShading ? "#define FLAT_SHADED" : "", e.metal ? "#define METAL" : "", e.wrapAround ? "#define WRAP_AROUND" : "", e.doubleSided ? "#define DOUBLE_SIDED" : "", e.flipSided ? "#define FLIP_SIDED" : "", e.shadowMapEnabled ? "#define USE_SHADOWMAP" : "", e.shadowMapEnabled ? "#define " + n : "", e.shadowMapDebug ? "#define SHADOWMAP_DEBUG" : "", e.shadowMapCascade ? "#define SHADOWMAP_CASCADE" : "", e.logarithmicDepthBuffer ? "#define USE_LOGDEPTHBUF" : "", "uniform mat4 viewMatrix;\nuniform vec3 cameraPosition;\n"].join("\n")), l = new THREE.WebGLShader(f, f.VERTEX_SHADER, d + l), p = new THREE.WebGLShader(f, f.FRAGMENT_SHADER, b + p), f.attachShader(g, l), f.attachShader(g, p), void 0 !== q && f.bindAttribLocation(g, 0, q), f.linkProgram(g), q = f.getProgramInfoLog(g), !1 === f.getProgramParameter(g, f.LINK_STATUS) && THREE.error("THREE.WebGLProgram: shader error: " + f.getError(), "gl.VALIDATE_STATUS", f.getProgramParameter(g, f.VALIDATE_STATUS), "gl.getPRogramInfoLog", q), "" !== q && THREE.warn("THREE.WebGLProgram: gl.getProgramInfoLog()" + q), f.deleteShader(l), f.deleteShader(p), q = "viewMatrix modelViewMatrix projectionMatrix normalMatrix modelMatrix cameraPosition morphTargetInfluences bindMatrix bindMatrixInverse".split(" "), e.useVertexTexture ? (q.push("boneTexture"), q.push("boneTextureWidth"), q.push("boneTextureHeight")) : q.push("boneGlobalMatrices"), e.logarithmicDepthBuffer && q.push("logDepthBufFC");
        for (var w in h)q.push(w);
        for (h = q, w = {}, q = 0, b = h.length; b > q; q++)n = h[q], w[n] = f.getUniformLocation(g, n);
        for (this.uniforms = w, q = "position normal uv uv2 tangent color skinIndex skinWeight lineDistance".split(" "), h = 0; h < e.maxMorphTargets; h++)q.push("morphTarget" + h);
        for (h = 0; h < e.maxMorphNormals; h++)q.push("morphNormal" + h);
        for (var y in k)q.push(y);
        for (e = q, k = {}, y = 0, h = e.length; h > y; y++)w = e[y], k[w] = f.getAttribLocation(g, w);
        return this.attributes = k, this.attributesKeys = Object.keys(this.attributes), this.id = a++, this.code = c, this.usedTimes = 1, this.program = g, this.vertexShader = l, this.fragmentShader = p, this
    }
}(),THREE.WebGLShader = function () {
    var a = function (a) {
        a = a.split("\n");
        for (var c = 0; c < a.length; c++)a[c] = c + 1 + ": " + a[c];
        return a.join("\n")
    };
    return function (b, c, d) {
        return c = b.createShader(c), b.shaderSource(c, d), b.compileShader(c), !1 === b.getShaderParameter(c, b.COMPILE_STATUS) && THREE.error("THREE.WebGLShader: Shader couldn't compile."), "" !== b.getShaderInfoLog(c) && THREE.warn("THREE.WebGLShader: gl.getShaderInfoLog()", b.getShaderInfoLog(c), a(d)), c
    }
}(),THREE.WebGLState = function (a, b) {
    var c = new Uint8Array(16), d = new Uint8Array(16), e = null, f = null, g = null, h = null, k = null, l = null, p = null, q = null, n = null, t = null, r = null, s = null, u = null, v = null, x = null, D = null;
    this.initAttributes = function () {
        for (var a = 0, b = c.length; b > a; a++)c[a] = 0
    }, this.enableAttribute = function (b) {
        c[b] = 1, 0 === d[b] && (a.enableVertexAttribArray(b), d[b] = 1)
    }, this.disableUnusedAttributes = function () {
        for (var b = 0, e = d.length; e > b; b++)d[b] !== c[b] && (a.disableVertexAttribArray(b), d[b] = 0)
    }, this.setBlending = function (c, d, n, q, r, s, t) {
        c !== e && (c === THREE.NoBlending ? a.disable(a.BLEND) : c === THREE.AdditiveBlending ? (a.enable(a.BLEND), a.blendEquation(a.FUNC_ADD), a.blendFunc(a.SRC_ALPHA, a.ONE)) : c === THREE.SubtractiveBlending ? (a.enable(a.BLEND), a.blendEquation(a.FUNC_ADD), a.blendFunc(a.ZERO, a.ONE_MINUS_SRC_COLOR)) : c === THREE.MultiplyBlending ? (a.enable(a.BLEND), a.blendEquation(a.FUNC_ADD), a.blendFunc(a.ZERO, a.SRC_COLOR)) : c === THREE.CustomBlending ? a.enable(a.BLEND) : (a.enable(a.BLEND), a.blendEquationSeparate(a.FUNC_ADD, a.FUNC_ADD), a.blendFuncSeparate(a.SRC_ALPHA, a.ONE_MINUS_SRC_ALPHA, a.ONE, a.ONE_MINUS_SRC_ALPHA)), e = c), c === THREE.CustomBlending ? (r = r || d, s = s || n, t = t || q, (d !== f || r !== k) && (a.blendEquationSeparate(b(d), b(r)), f = d, k = r), (n !== g || q !== h || s !== l || t !== p) && (a.blendFuncSeparate(b(n), b(q), b(s), b(t)), g = n, h = q, l = s, p = t)) : p = l = k = h = g = f = null
    }, this.setDepthTest = function (b) {
        q !== b && (b ? a.enable(a.DEPTH_TEST) : a.disable(a.DEPTH_TEST), q = b)
    }, this.setDepthWrite = function (b) {
        n !== b && (a.depthMask(b), n = b)
    }, this.setColorWrite = function (b) {
        t !== b && (a.colorMask(b, b, b, b), t = b)
    }, this.setDoubleSided = function (b) {
        r !== b && (b ? a.disable(a.CULL_FACE) : a.enable(a.CULL_FACE), r = b)
    }, this.setFlipSided = function (b) {
        s !== b && (b ? a.frontFace(a.CW) : a.frontFace(a.CCW), s = b)
    }, this.setLineWidth = function (b) {
        b !== u && (a.lineWidth(b), u = b)
    }, this.setPolygonOffset = function (b, c, d) {
        v !== b && (b ? a.enable(a.POLYGON_OFFSET_FILL) : a.disable(a.POLYGON_OFFSET_FILL), v = b), !b || x === c && D === d || (a.polygonOffset(c, d), x = c, D = d)
    }, this.reset = function () {
        for (var a = 0; a < d.length; a++)d[a] = 0;
        s = r = t = n = q = e = null
    }
},THREE.LensFlarePlugin = function (a, b) {
    var c, d, e, f, g, h, k, l, p, q, t, r, s, u, v, x, n = a.context;
    this.render = function (D, w, y, A) {
        if (0 !== b.length) {
            D = new THREE.Vector3;
            var E = A / y, G = .5 * y, F = .5 * A, z = 16 / A, I = new THREE.Vector2(z * E, z), U = new THREE.Vector3(1, 1, 0), M = new THREE.Vector2(1, 1);
            if (void 0 === s) {
                var z = new Float32Array([-1, -1, 0, 0, 1, -1, 1, 0, 1, 1, 1, 1, -1, 1, 0, 1]), H = new Uint16Array([0, 1, 2, 0, 2, 3]);
                t = n.createBuffer(), r = n.createBuffer(), n.bindBuffer(n.ARRAY_BUFFER, t), n.bufferData(n.ARRAY_BUFFER, z, n.STATIC_DRAW), n.bindBuffer(n.ELEMENT_ARRAY_BUFFER, r), n.bufferData(n.ELEMENT_ARRAY_BUFFER, H, n.STATIC_DRAW), v = n.createTexture(), x = n.createTexture(), n.bindTexture(n.TEXTURE_2D, v), n.texImage2D(n.TEXTURE_2D, 0, n.RGB, 16, 16, 0, n.RGB, n.UNSIGNED_BYTE, null), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_WRAP_S, n.CLAMP_TO_EDGE), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_WRAP_T, n.CLAMP_TO_EDGE), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_MAG_FILTER, n.NEAREST), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_MIN_FILTER, n.NEAREST), n.bindTexture(n.TEXTURE_2D, x), n.texImage2D(n.TEXTURE_2D, 0, n.RGBA, 16, 16, 0, n.RGBA, n.UNSIGNED_BYTE, null), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_WRAP_S, n.CLAMP_TO_EDGE), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_WRAP_T, n.CLAMP_TO_EDGE), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_MAG_FILTER, n.NEAREST), n.texParameteri(n.TEXTURE_2D, n.TEXTURE_MIN_FILTER, n.NEAREST);
                var z = (u = 0 < n.getParameter(n.MAX_VERTEX_TEXTURE_IMAGE_UNITS)) ? {
                    vertexShader: "uniform lowp int renderType;\nuniform vec3 screenPosition;\nuniform vec2 scale;\nuniform float rotation;\nuniform sampler2D occlusionMap;\nattribute vec2 position;\nattribute vec2 uv;\nvarying vec2 vUV;\nvarying float vVisibility;\nvoid main() {\nvUV = uv;\nvec2 pos = position;\nif( renderType == 2 ) {\nvec4 visibility = texture2D( occlusionMap, vec2( 0.1, 0.1 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.5, 0.1 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.9, 0.1 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.9, 0.5 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.9, 0.9 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.5, 0.9 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.1, 0.9 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.1, 0.5 ) );\nvisibility += texture2D( occlusionMap, vec2( 0.5, 0.5 ) );\nvVisibility =        visibility.r / 9.0;\nvVisibility *= 1.0 - visibility.g / 9.0;\nvVisibility *=       visibility.b / 9.0;\nvVisibility *= 1.0 - visibility.a / 9.0;\npos.x = cos( rotation ) * position.x - sin( rotation ) * position.y;\npos.y = sin( rotation ) * position.x + cos( rotation ) * position.y;\n}\ngl_Position = vec4( ( pos * scale + screenPosition.xy ).xy, screenPosition.z, 1.0 );\n}",
                    fragmentShader: "uniform lowp int renderType;\nuniform sampler2D map;\nuniform float opacity;\nuniform vec3 color;\nvarying vec2 vUV;\nvarying float vVisibility;\nvoid main() {\nif( renderType == 0 ) {\ngl_FragColor = vec4( 1.0, 0.0, 1.0, 0.0 );\n} else if( renderType == 1 ) {\ngl_FragColor = texture2D( map, vUV );\n} else {\nvec4 texture = texture2D( map, vUV );\ntexture.a *= opacity * vVisibility;\ngl_FragColor = texture;\ngl_FragColor.rgb *= color;\n}\n}"
                } : {
                    vertexShader: "uniform lowp int renderType;\nuniform vec3 screenPosition;\nuniform vec2 scale;\nuniform float rotation;\nattribute vec2 position;\nattribute vec2 uv;\nvarying vec2 vUV;\nvoid main() {\nvUV = uv;\nvec2 pos = position;\nif( renderType == 2 ) {\npos.x = cos( rotation ) * position.x - sin( rotation ) * position.y;\npos.y = sin( rotation ) * position.x + cos( rotation ) * position.y;\n}\ngl_Position = vec4( ( pos * scale + screenPosition.xy ).xy, screenPosition.z, 1.0 );\n}",
                    fragmentShader: "precision mediump float;\nuniform lowp int renderType;\nuniform sampler2D map;\nuniform sampler2D occlusionMap;\nuniform float opacity;\nuniform vec3 color;\nvarying vec2 vUV;\nvoid main() {\nif( renderType == 0 ) {\ngl_FragColor = vec4( texture2D( map, vUV ).rgb, 0.0 );\n} else if( renderType == 1 ) {\ngl_FragColor = texture2D( map, vUV );\n} else {\nfloat visibility = texture2D( occlusionMap, vec2( 0.5, 0.1 ) ).a;\nvisibility += texture2D( occlusionMap, vec2( 0.9, 0.5 ) ).a;\nvisibility += texture2D( occlusionMap, vec2( 0.5, 0.9 ) ).a;\nvisibility += texture2D( occlusionMap, vec2( 0.1, 0.5 ) ).a;\nvisibility = ( 1.0 - visibility / 4.0 );\nvec4 texture = texture2D( map, vUV );\ntexture.a *= opacity * visibility;\ngl_FragColor = texture;\ngl_FragColor.rgb *= color;\n}\n}"
                }, H = n.createProgram(), L = n.createShader(n.FRAGMENT_SHADER), P = n.createShader(n.VERTEX_SHADER), N = "precision " + a.getPrecision() + " float;\n";
                n.shaderSource(L, N + z.fragmentShader), n.shaderSource(P, N + z.vertexShader), n.compileShader(L), n.compileShader(P), n.attachShader(H, L), n.attachShader(H, P), n.linkProgram(H), s = H, p = n.getAttribLocation(s, "position"), q = n.getAttribLocation(s, "uv"), c = n.getUniformLocation(s, "renderType"), d = n.getUniformLocation(s, "map"), e = n.getUniformLocation(s, "occlusionMap"), f = n.getUniformLocation(s, "opacity"), g = n.getUniformLocation(s, "color"), h = n.getUniformLocation(s, "scale"), k = n.getUniformLocation(s, "rotation"), l = n.getUniformLocation(s, "screenPosition")
            }
            for (n.useProgram(s), n.enableVertexAttribArray(p), n.enableVertexAttribArray(q), n.uniform1i(e, 0), n.uniform1i(d, 1), n.bindBuffer(n.ARRAY_BUFFER, t), n.vertexAttribPointer(p, 2, n.FLOAT, !1, 16, 0), n.vertexAttribPointer(q, 2, n.FLOAT, !1, 16, 8), n.bindBuffer(n.ELEMENT_ARRAY_BUFFER, r), n.disable(n.CULL_FACE), n.depthMask(!1), H = 0, L = b.length; L > H; H++)if (z = 16 / A, I.set(z * E, z), P = b[H], D.set(P.matrixWorld.elements[12], P.matrixWorld.elements[13], P.matrixWorld.elements[14]), D.applyMatrix4(w.matrixWorldInverse), D.applyProjection(w.projectionMatrix), U.copy(D), M.x = U.x * G + G, M.y = U.y * F + F, u || 0 < M.x && M.x < y && 0 < M.y && M.y < A) {
                n.activeTexture(n.TEXTURE1), n.bindTexture(n.TEXTURE_2D, v), n.copyTexImage2D(n.TEXTURE_2D, 0, n.RGB, M.x - 8, M.y - 8, 16, 16, 0), n.uniform1i(c, 0), n.uniform2f(h, I.x, I.y), n.uniform3f(l, U.x, U.y, U.z), n.disable(n.BLEND), n.enable(n.DEPTH_TEST), n.drawElements(n.TRIANGLES, 6, n.UNSIGNED_SHORT, 0), n.activeTexture(n.TEXTURE0), n.bindTexture(n.TEXTURE_2D, x), n.copyTexImage2D(n.TEXTURE_2D, 0, n.RGBA, M.x - 8, M.y - 8, 16, 16, 0), n.uniform1i(c, 1), n.disable(n.DEPTH_TEST), n.activeTexture(n.TEXTURE1), n.bindTexture(n.TEXTURE_2D, v), n.drawElements(n.TRIANGLES, 6, n.UNSIGNED_SHORT, 0), P.positionScreen.copy(U), P.customUpdateCallback ? P.customUpdateCallback(P) : P.updateLensFlares(), n.uniform1i(c, 2), n.enable(n.BLEND);
                for (var N = 0, R = P.lensFlares.length; R > N; N++) {
                    var V = P.lensFlares[N];
                    .001 < V.opacity && .001 < V.scale && (U.x = V.x, U.y = V.y, U.z = V.z, z = V.size * V.scale / A, I.x = z * E, I.y = z, n.uniform3f(l, U.x, U.y, U.z), n.uniform2f(h, I.x, I.y), n.uniform1f(k, V.rotation), n.uniform1f(f, V.opacity), n.uniform3f(g, V.color.r, V.color.g, V.color.b), a.state.setBlending(V.blending, V.blendEquation, V.blendSrc, V.blendDst), a.setTexture(V.texture, 1), n.drawElements(n.TRIANGLES, 6, n.UNSIGNED_SHORT, 0))
                }
            }
            n.enable(n.CULL_FACE), n.enable(n.DEPTH_TEST), n.depthMask(!0), a.resetGLState()
        }
    }
},THREE.ShadowMapPlugin = function (a, b, c, d) {
    function e(a, b, d) {
        if (b.visible) {
            var f = c[b.id];
            if (f && b.castShadow && (!1 === b.frustumCulled || !0 === p.intersectsObject(b)))for (var g = 0, h = f.length; h > g; g++) {
                var k = f[g];
                b._modelViewMatrix.multiplyMatrices(d.matrixWorldInverse, b.matrixWorld), s.push(k)
            }
            for (g = 0, h = b.children.length; h > g; g++)e(a, b.children[g], d)
        }
    }

    var g, h, k, l, f = a.context, p = new THREE.Frustum, q = new THREE.Matrix4, n = new THREE.Vector3, t = new THREE.Vector3, r = new THREE.Vector3, s = [], u = THREE.ShaderLib.depthRGBA, v = THREE.UniformsUtils.clone(u.uniforms);
    g = new THREE.ShaderMaterial({
        uniforms: v,
        vertexShader: u.vertexShader,
        fragmentShader: u.fragmentShader
    }), h = new THREE.ShaderMaterial({
        uniforms: v,
        vertexShader: u.vertexShader,
        fragmentShader: u.fragmentShader,
        morphTargets: !0
    }), k = new THREE.ShaderMaterial({
        uniforms: v,
        vertexShader: u.vertexShader,
        fragmentShader: u.fragmentShader,
        skinning: !0
    }), l = new THREE.ShaderMaterial({
        uniforms: v,
        vertexShader: u.vertexShader,
        fragmentShader: u.fragmentShader,
        morphTargets: !0,
        skinning: !0
    }), g._shadowPass = !0, h._shadowPass = !0, k._shadowPass = !0, l._shadowPass = !0, this.render = function (c, v) {
        if (!1 !== a.shadowMapEnabled) {
            var u, y, A, E, G, F, z, I, U = [];
            for (E = 0, f.clearColor(1, 1, 1, 1), f.disable(f.BLEND), f.enable(f.CULL_FACE), f.frontFace(f.CCW), a.shadowMapCullFace === THREE.CullFaceFront ? f.cullFace(f.FRONT) : f.cullFace(f.BACK), a.state.setDepthTest(!0), u = 0, y = b.length; y > u; u++)if (A = b[u], A.castShadow)if (A instanceof THREE.DirectionalLight && A.shadowCascade)for (G = 0; G < A.shadowCascadeCount; G++) {
                var M;
                if (A.shadowCascadeArray[G])M = A.shadowCascadeArray[G]; else {
                    z = A;
                    var H = G;
                    M = new THREE.DirectionalLight, M.isVirtual = !0, M.onlyShadow = !0, M.castShadow = !0, M.shadowCameraNear = z.shadowCameraNear, M.shadowCameraFar = z.shadowCameraFar, M.shadowCameraLeft = z.shadowCameraLeft, M.shadowCameraRight = z.shadowCameraRight, M.shadowCameraBottom = z.shadowCameraBottom, M.shadowCameraTop = z.shadowCameraTop, M.shadowCameraVisible = z.shadowCameraVisible, M.shadowDarkness = z.shadowDarkness, M.shadowBias = z.shadowCascadeBias[H], M.shadowMapWidth = z.shadowCascadeWidth[H], M.shadowMapHeight = z.shadowCascadeHeight[H], M.pointsWorld = [], M.pointsFrustum = [], I = M.pointsWorld, F = M.pointsFrustum;
                    for (var L = 0; 8 > L; L++)I[L] = new THREE.Vector3, F[L] = new THREE.Vector3;
                    I = z.shadowCascadeNearZ[H], z = z.shadowCascadeFarZ[H], F[0].set(-1, -1, I), F[1].set(1, -1, I), F[2].set(-1, 1, I), F[3].set(1, 1, I), F[4].set(-1, -1, z), F[5].set(1, -1, z), F[6].set(-1, 1, z), F[7].set(1, 1, z), M.originalCamera = v, F = new THREE.Gyroscope, F.position.copy(A.shadowCascadeOffset), F.add(M), F.add(M.target), v.add(F), A.shadowCascadeArray[G] = M
                }
                H = A, I = G, z = H.shadowCascadeArray[I], z.position.copy(H.position), z.target.position.copy(H.target.position), z.lookAt(z.target), z.shadowCameraVisible = H.shadowCameraVisible, z.shadowDarkness = H.shadowDarkness, z.shadowBias = H.shadowCascadeBias[I], F = H.shadowCascadeNearZ[I], H = H.shadowCascadeFarZ[I], z = z.pointsFrustum, z[0].z = F, z[1].z = F, z[2].z = F, z[3].z = F, z[4].z = H, z[5].z = H, z[6].z = H, z[7].z = H, U[E] = M, E++
            } else U[E] = A, E++;
            for (u = 0, y = U.length; y > u; u++) {
                if (A = U[u], A.shadowMap || (G = THREE.LinearFilter, a.shadowMapType === THREE.PCFSoftShadowMap && (G = THREE.NearestFilter), A.shadowMap = new THREE.WebGLRenderTarget(A.shadowMapWidth, A.shadowMapHeight, {
                        minFilter: G,
                        magFilter: G,
                        format: THREE.RGBAFormat
                    }), A.shadowMapSize = new THREE.Vector2(A.shadowMapWidth, A.shadowMapHeight), A.shadowMatrix = new THREE.Matrix4), !A.shadowCamera) {
                    if (A instanceof THREE.SpotLight)A.shadowCamera = new THREE.PerspectiveCamera(A.shadowCameraFov, A.shadowMapWidth / A.shadowMapHeight, A.shadowCameraNear, A.shadowCameraFar); else {
                        if (!(A instanceof THREE.DirectionalLight)) {
                            THREE.error("THREE.ShadowMapPlugin: Unsupported light type for shadow", A);
                            continue
                        }
                        A.shadowCamera = new THREE.OrthographicCamera(A.shadowCameraLeft, A.shadowCameraRight, A.shadowCameraTop, A.shadowCameraBottom, A.shadowCameraNear, A.shadowCameraFar)
                    }
                    c.add(A.shadowCamera), !0 === c.autoUpdate && c.updateMatrixWorld()
                }
                if (A.shadowCameraVisible && !A.cameraHelper && (A.cameraHelper = new THREE.CameraHelper(A.shadowCamera), c.add(A.cameraHelper)), A.isVirtual && M.originalCamera == v) {
                    for (G = v, E = A.shadowCamera, F = A.pointsFrustum, z = A.pointsWorld, n.set(1 / 0, 1 / 0, 1 / 0), t.set(-(1 / 0), -(1 / 0), -(1 / 0)), H = 0; 8 > H; H++)I = z[H], I.copy(F[H]), I.unproject(G), I.applyMatrix4(E.matrixWorldInverse), I.x < n.x && (n.x = I.x), I.x > t.x && (t.x = I.x), I.y < n.y && (n.y = I.y), I.y > t.y && (t.y = I.y), I.z < n.z && (n.z = I.z), I.z > t.z && (t.z = I.z);
                    E.left = n.x, E.right = t.x, E.top = t.y, E.bottom = n.y, E.updateProjectionMatrix()
                }
                for (E = A.shadowMap, F = A.shadowMatrix, G = A.shadowCamera, G.position.setFromMatrixPosition(A.matrixWorld), r.setFromMatrixPosition(A.target.matrixWorld), G.lookAt(r), G.updateMatrixWorld(), G.matrixWorldInverse.getInverse(G.matrixWorld), A.cameraHelper && (A.cameraHelper.visible = A.shadowCameraVisible), A.shadowCameraVisible && A.cameraHelper.update(), F.set(.5, 0, 0, .5, 0, .5, 0, .5, 0, 0, .5, .5, 0, 0, 0, 1), F.multiply(G.projectionMatrix), F.multiply(G.matrixWorldInverse), q.multiplyMatrices(G.projectionMatrix, G.matrixWorldInverse), p.setFromMatrix(q), a.setRenderTarget(E), a.clear(), s.length = 0, e(c, c, G), A = 0, E = s.length; E > A; A++)z = s[A], F = z.object, z = z.buffer, H = F.material instanceof THREE.MeshFaceMaterial ? F.material.materials[0] : F.material, I = void 0 !== F.geometry.morphTargets && 0 < F.geometry.morphTargets.length && H.morphTargets,
                    L = F instanceof THREE.SkinnedMesh && H.skinning, I = F.customDepthMaterial ? F.customDepthMaterial : L ? I ? l : k : I ? h : g, a.setMaterialFaces(H), z instanceof THREE.BufferGeometry ? a.renderBufferDirect(G, b, null, I, z, F) : a.renderBuffer(G, b, null, I, z, F);
                for (A = 0, E = d.length; E > A; A++)z = d[A], F = z.object, F.visible && F.castShadow && (F._modelViewMatrix.multiplyMatrices(G.matrixWorldInverse, F.matrixWorld), a.renderImmediateObject(G, b, null, g, F))
            }
            u = a.getClearColor(), y = a.getClearAlpha(), f.clearColor(u.r, u.g, u.b, y), f.enable(f.BLEND), a.shadowMapCullFace === THREE.CullFaceFront && f.cullFace(f.BACK), a.resetGLState()
        }
    }
},THREE.SpritePlugin = function (a, b) {
    function D(a, b) {
        return a.z !== b.z ? b.z - a.z : b.id - a.id
    }

    var c, d, e, f, g, h, k, l, p, q, n, t, r, s, u, v, x, y, A, E, G, w = a.context, F = new THREE.Vector3, z = new THREE.Quaternion, I = new THREE.Vector3;
    this.render = function (U, M) {
        if (0 !== b.length) {
            if (void 0 === E) {
                var H = new Float32Array([-.5, -.5, 0, 0, .5, -.5, 1, 0, .5, .5, 1, 1, -.5, .5, 0, 1]), L = new Uint16Array([0, 1, 2, 0, 2, 3]);
                y = w.createBuffer(), A = w.createBuffer(), w.bindBuffer(w.ARRAY_BUFFER, y), w.bufferData(w.ARRAY_BUFFER, H, w.STATIC_DRAW), w.bindBuffer(w.ELEMENT_ARRAY_BUFFER, A), w.bufferData(w.ELEMENT_ARRAY_BUFFER, L, w.STATIC_DRAW);
                var H = w.createProgram(), L = w.createShader(w.VERTEX_SHADER), P = w.createShader(w.FRAGMENT_SHADER);
                w.shaderSource(L, ["precision " + a.getPrecision() + " float;", "uniform mat4 modelViewMatrix;\nuniform mat4 projectionMatrix;\nuniform float rotation;\nuniform vec2 scale;\nuniform vec2 uvOffset;\nuniform vec2 uvScale;\nattribute vec2 position;\nattribute vec2 uv;\nvarying vec2 vUV;\nvoid main() {\nvUV = uvOffset + uv * uvScale;\nvec2 alignedPosition = position * scale;\nvec2 rotatedPosition;\nrotatedPosition.x = cos( rotation ) * alignedPosition.x - sin( rotation ) * alignedPosition.y;\nrotatedPosition.y = sin( rotation ) * alignedPosition.x + cos( rotation ) * alignedPosition.y;\nvec4 finalPosition;\nfinalPosition = modelViewMatrix * vec4( 0.0, 0.0, 0.0, 1.0 );\nfinalPosition.xy += rotatedPosition;\nfinalPosition = projectionMatrix * finalPosition;\ngl_Position = finalPosition;\n}"].join("\n")), w.shaderSource(P, ["precision " + a.getPrecision() + " float;", "uniform vec3 color;\nuniform sampler2D map;\nuniform float opacity;\nuniform int fogType;\nuniform vec3 fogColor;\nuniform float fogDensity;\nuniform float fogNear;\nuniform float fogFar;\nuniform float alphaTest;\nvarying vec2 vUV;\nvoid main() {\nvec4 texture = texture2D( map, vUV );\nif ( texture.a < alphaTest ) discard;\ngl_FragColor = vec4( color * texture.xyz, texture.a * opacity );\nif ( fogType > 0 ) {\nfloat depth = gl_FragCoord.z / gl_FragCoord.w;\nfloat fogFactor = 0.0;\nif ( fogType == 1 ) {\nfogFactor = smoothstep( fogNear, fogFar, depth );\n} else {\nconst float LOG2 = 1.442695;\nfloat fogFactor = exp2( - fogDensity * fogDensity * depth * depth * LOG2 );\nfogFactor = 1.0 - clamp( fogFactor, 0.0, 1.0 );\n}\ngl_FragColor = mix( gl_FragColor, vec4( fogColor, gl_FragColor.w ), fogFactor );\n}\n}"].join("\n")), w.compileShader(L), w.compileShader(P), w.attachShader(H, L), w.attachShader(H, P), w.linkProgram(H), E = H, v = w.getAttribLocation(E, "position"), x = w.getAttribLocation(E, "uv"), c = w.getUniformLocation(E, "uvOffset"), d = w.getUniformLocation(E, "uvScale"), e = w.getUniformLocation(E, "rotation"), f = w.getUniformLocation(E, "scale"), g = w.getUniformLocation(E, "color"), h = w.getUniformLocation(E, "map"), k = w.getUniformLocation(E, "opacity"), l = w.getUniformLocation(E, "modelViewMatrix"), p = w.getUniformLocation(E, "projectionMatrix"), q = w.getUniformLocation(E, "fogType"), n = w.getUniformLocation(E, "fogDensity"), t = w.getUniformLocation(E, "fogNear"), r = w.getUniformLocation(E, "fogFar"), s = w.getUniformLocation(E, "fogColor"), u = w.getUniformLocation(E, "alphaTest"), H = document.createElement("canvas"), H.width = 8, H.height = 8, L = H.getContext("2d"), L.fillStyle = "white", L.fillRect(0, 0, 8, 8), G = new THREE.Texture(H), G.needsUpdate = !0
            }
            w.useProgram(E), w.enableVertexAttribArray(v), w.enableVertexAttribArray(x), w.disable(w.CULL_FACE), w.enable(w.BLEND), w.bindBuffer(w.ARRAY_BUFFER, y), w.vertexAttribPointer(v, 2, w.FLOAT, !1, 16, 0), w.vertexAttribPointer(x, 2, w.FLOAT, !1, 16, 8), w.bindBuffer(w.ELEMENT_ARRAY_BUFFER, A), w.uniformMatrix4fv(p, !1, M.projectionMatrix.elements), w.activeTexture(w.TEXTURE0), w.uniform1i(h, 0), L = H = 0, (P = U.fog) ? (w.uniform3f(s, P.color.r, P.color.g, P.color.b), P instanceof THREE.Fog ? (w.uniform1f(t, P.near), w.uniform1f(r, P.far), w.uniform1i(q, 1), L = H = 1) : P instanceof THREE.FogExp2 && (w.uniform1f(n, P.density), w.uniform1i(q, 2), L = H = 2)) : (w.uniform1i(q, 0), L = H = 0);
            for (var P = 0, N = b.length; N > P; P++) {
                var R = b[P];
                R._modelViewMatrix.multiplyMatrices(M.matrixWorldInverse, R.matrixWorld), R.z = -R._modelViewMatrix.elements[14]
            }
            b.sort(D);
            for (var V = [], P = 0, N = b.length; N > P; P++) {
                var R = b[P], J = R.material;
                w.uniform1f(u, J.alphaTest), w.uniformMatrix4fv(l, !1, R._modelViewMatrix.elements), R.matrixWorld.decompose(F, z, I), V[0] = I.x, V[1] = I.y, R = 0, U.fog && J.fog && (R = L), H !== R && (w.uniform1i(q, R), H = R), null !== J.map ? (w.uniform2f(c, J.map.offset.x, J.map.offset.y), w.uniform2f(d, J.map.repeat.x, J.map.repeat.y)) : (w.uniform2f(c, 0, 0), w.uniform2f(d, 1, 1)), w.uniform1f(k, J.opacity), w.uniform3f(g, J.color.r, J.color.g, J.color.b), w.uniform1f(e, J.rotation), w.uniform2fv(f, V), a.state.setBlending(J.blending, J.blendEquation, J.blendSrc, J.blendDst), a.state.setDepthTest(J.depthTest), a.state.setDepthWrite(J.depthWrite), J.map && J.map.image && J.map.image.width ? a.setTexture(J.map, 0) : a.setTexture(G, 0), w.drawElements(w.TRIANGLES, 6, w.UNSIGNED_SHORT, 0)
            }
            w.enable(w.CULL_FACE), a.resetGLState()
        }
    }
},THREE.GeometryUtils = {
    merge: function (a, b, c) {
        THREE.warn("THREE.GeometryUtils: .merge() has been moved to Geometry. Use geometry.merge( geometry2, matrix, materialIndexOffset ) instead.");
        var d;
        b instanceof THREE.Mesh && (b.matrixAutoUpdate && b.updateMatrix(), d = b.matrix, b = b.geometry), a.merge(b, d, c)
    }, center: function (a) {
        return THREE.warn("THREE.GeometryUtils: .center() has been moved to Geometry. Use geometry.center() instead."), a.center()
    }
},THREE.ImageUtils = {
    crossOrigin: void 0, loadTexture: function (a, b, c, d) {
        var e = new THREE.ImageLoader;
        e.crossOrigin = this.crossOrigin;
        var f = new THREE.Texture(void 0, b);
        return e.load(a, function (a) {
            f.image = a, f.needsUpdate = !0, c && c(f)
        }, void 0, function (a) {
            d && d(a)
        }), f.sourceFile = a, f
    }, loadTextureCube: function (a, b, c, d) {
        var e = new THREE.ImageLoader;
        e.crossOrigin = this.crossOrigin;
        var f = new THREE.CubeTexture([], b);
        f.flipY = !1;
        var g = 0;
        b = function (b) {
            e.load(a[b], function (a) {
                f.images[b] = a, g += 1, 6 === g && (f.needsUpdate = !0, c && c(f))
            }, void 0, d)
        };
        for (var h = 0, k = a.length; k > h; ++h)b(h);
        return f
    }, loadCompressedTexture: function () {
        THREE.error("THREE.ImageUtils.loadCompressedTexture has been removed. Use THREE.DDSLoader instead.")
    }, loadCompressedTextureCube: function () {
        THREE.error("THREE.ImageUtils.loadCompressedTextureCube has been removed. Use THREE.DDSLoader instead.")
    }, getNormalMap: function (a, b) {
        var c = function (a) {
            var b = Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2]);
            return [a[0] / b, a[1] / b, a[2] / b]
        };
        b |= 1;
        var d = a.width, e = a.height, f = document.createElement("canvas");
        f.width = d, f.height = e;
        var g = f.getContext("2d");
        g.drawImage(a, 0, 0);
        for (var h = g.getImageData(0, 0, d, e).data, k = g.createImageData(d, e), l = k.data, p = 0; d > p; p++)for (var q = 0; e > q; q++) {
            var n = 0 > q - 1 ? 0 : q - 1, t = q + 1 > e - 1 ? e - 1 : q + 1, r = 0 > p - 1 ? 0 : p - 1, s = p + 1 > d - 1 ? d - 1 : p + 1, u = [], v = [0, 0, h[4 * (q * d + p)] / 255 * b];
            for (u.push([-1, 0, h[4 * (q * d + r)] / 255 * b]), u.push([-1, -1, h[4 * (n * d + r)] / 255 * b]), u.push([0, -1, h[4 * (n * d + p)] / 255 * b]), u.push([1, -1, h[4 * (n * d + s)] / 255 * b]), u.push([1, 0, h[4 * (q * d + s)] / 255 * b]), u.push([1, 1, h[4 * (t * d + s)] / 255 * b]), u.push([0, 1, h[4 * (t * d + p)] / 255 * b]), u.push([-1, 1, h[4 * (t * d + r)] / 255 * b]), n = [], r = u.length, t = 0; r > t; t++) {
                var s = u[t], x = u[(t + 1) % r], s = [s[0] - v[0], s[1] - v[1], s[2] - v[2]], x = [x[0] - v[0], x[1] - v[1], x[2] - v[2]];
                n.push(c([s[1] * x[2] - s[2] * x[1], s[2] * x[0] - s[0] * x[2], s[0] * x[1] - s[1] * x[0]]))
            }
            for (u = [0, 0, 0], t = 0; t < n.length; t++)u[0] += n[t][0], u[1] += n[t][1], u[2] += n[t][2];
            u[0] /= n.length, u[1] /= n.length, u[2] /= n.length, v = 4 * (q * d + p), l[v] = (u[0] + 1) / 2 * 255 | 0, l[v + 1] = (u[1] + 1) / 2 * 255 | 0, l[v + 2] = 255 * u[2] | 0, l[v + 3] = 255
        }
        return g.putImageData(k, 0, 0), f
    }, generateDataTexture: function (a, b, c) {
        var d = a * b, e = new Uint8Array(3 * d), f = Math.floor(255 * c.r), g = Math.floor(255 * c.g);
        c = Math.floor(255 * c.b);
        for (var h = 0; d > h; h++)e[3 * h] = f, e[3 * h + 1] = g, e[3 * h + 2] = c;
        return a = new THREE.DataTexture(e, a, b, THREE.RGBFormat), a.needsUpdate = !0, a
    }
},THREE.SceneUtils = {
    createMultiMaterialObject: function (a, b) {
        for (var c = new THREE.Object3D, d = 0, e = b.length; e > d; d++)c.add(new THREE.Mesh(a, b[d]));
        return c
    }, detach: function (a, b, c) {
        a.applyMatrix(b.matrixWorld), b.remove(a), c.add(a)
    }, attach: function (a, b, c) {
        var d = new THREE.Matrix4;
        d.getInverse(c.matrixWorld), a.applyMatrix(d), b.remove(a), c.add(a)
    }
},THREE.FontUtils = {
    faces: {},
    face: "helvetiker",
    weight: "normal",
    style: "normal",
    size: 150,
    divisions: 10,
    getFace: function () {
        try {
            return this.faces[this.face][this.weight][this.style]
        } catch (a) {
            throw"The font " + this.face + " with " + this.weight + " weight and " + this.style + " style is missing."
        }
    },
    loadFace: function (a) {
        var b = a.familyName.toLowerCase();
        return this.faces[b] = this.faces[b] || {}, this.faces[b][a.cssFontWeight] = this.faces[b][a.cssFontWeight] || {}, this.faces[b][a.cssFontWeight][a.cssFontStyle] = a, this.faces[b][a.cssFontWeight][a.cssFontStyle] = a
    },
    drawText: function (a) {
        var b = this.getFace(), c = this.size / b.resolution, d = 0, e = String(a).split(""), f = e.length, g = [];
        for (a = 0; f > a; a++) {
            var h = new THREE.Path, h = this.extractGlyphPoints(e[a], b, c, d, h), d = d + h.offset;
            g.push(h.path)
        }
        return {paths: g, offset: d / 2}
    },
    extractGlyphPoints: function (a, b, c, d, e) {
        var g, h, k, l, p, q, n, t, r, s, u, f = [], v = b.glyphs[a] || b.glyphs["?"];
        if (v) {
            if (v.o)for (b = v._cachedOutline || (v._cachedOutline = v.o.split(" ")), l = b.length, a = 0; l > a;)switch (k = b[a++]) {
                case"m":
                    k = b[a++] * c + d, p = b[a++] * c, e.moveTo(k, p);
                    break;
                case"l":
                    k = b[a++] * c + d, p = b[a++] * c, e.lineTo(k, p);
                    break;
                case"q":
                    if (k = b[a++] * c + d, p = b[a++] * c, t = b[a++] * c + d, r = b[a++] * c, e.quadraticCurveTo(t, r, k, p), g = f[f.length - 1])for (q = g.x, n = g.y, g = 1, h = this.divisions; h >= g; g++) {
                        var x = g / h;
                        THREE.Shape.Utils.b2(x, q, t, k), THREE.Shape.Utils.b2(x, n, r, p)
                    }
                    break;
                case"b":
                    if (k = b[a++] * c + d, p = b[a++] * c, t = b[a++] * c + d, r = b[a++] * c, s = b[a++] * c + d, u = b[a++] * c, e.bezierCurveTo(t, r, s, u, k, p), g = f[f.length - 1])for (q = g.x, n = g.y, g = 1, h = this.divisions; h >= g; g++)x = g / h, THREE.Shape.Utils.b3(x, q, t, s, k), THREE.Shape.Utils.b3(x, n, r, u, p)
            }
            return {offset: v.ha * c, path: e}
        }
    }
},THREE.FontUtils.generateShapes = function (a, b) {
    b = b || {};
    var c = void 0 !== b.curveSegments ? b.curveSegments : 4, d = void 0 !== b.font ? b.font : "helvetiker", e = void 0 !== b.weight ? b.weight : "normal", f = void 0 !== b.style ? b.style : "normal";
    for (THREE.FontUtils.size = void 0 !== b.size ? b.size : 100, THREE.FontUtils.divisions = c, THREE.FontUtils.face = d, THREE.FontUtils.weight = e, THREE.FontUtils.style = f, c = THREE.FontUtils.drawText(a).paths, d = [], e = 0, f = c.length; f > e; e++)Array.prototype.push.apply(d, c[e].toShapes());
    return d
},function (a) {
    var b = function (a) {
        for (var b = a.length, e = 0, f = b - 1, g = 0; b > g; f = g++)e += a[f].x * a[g].y - a[g].x * a[f].y;
        return .5 * e
    };
    return a.Triangulate = function (a, d) {
        var e = a.length;
        if (3 > e)return null;
        var k, l, p, f = [], g = [], h = [];
        if (0 < b(a))for (l = 0; e > l; l++)g[l] = l; else for (l = 0; e > l; l++)g[l] = e - 1 - l;
        var q = 2 * e;
        for (l = e - 1; e > 2;) {
            if (0 >= q--) {
                THREE.warn("THREE.FontUtils: Warning, unable to triangulate polygon! in Triangulate.process()");
                break
            }
            k = l, k >= e && (k = 0), l = k + 1, l >= e && (l = 0), p = l + 1, p >= e && (p = 0);
            var n;
            a:{
                var t = n = void 0, r = void 0, s = void 0, u = void 0, v = void 0, x = void 0, D = void 0, w = void 0, t = a[g[k]].x, r = a[g[k]].y, s = a[g[l]].x, u = a[g[l]].y, v = a[g[p]].x, x = a[g[p]].y;
                if (1e-10 > (s - t) * (x - r) - (u - r) * (v - t))n = !1; else {
                    var y = void 0, A = void 0, E = void 0, G = void 0, F = void 0, z = void 0, I = void 0, U = void 0, M = void 0, H = void 0, M = U = I = w = D = void 0, y = v - s, A = x - u, E = t - v, G = r - x, F = s - t, z = u - r;
                    for (n = 0; e > n; n++)if (D = a[g[n]].x, w = a[g[n]].y, !(D === t && w === r || D === s && w === u || D === v && w === x) && (I = D - t, U = w - r, M = D - s, H = w - u, D -= v, w -= x, M = y * H - A * M, I = F * U - z * I, U = E * w - G * D, M >= -1e-10 && U >= -1e-10 && I >= -1e-10)) {
                        n = !1;
                        break a
                    }
                    n = !0
                }
            }
            if (n) {
                for (f.push([a[g[k]], a[g[l]], a[g[p]]]), h.push([g[k], g[l], g[p]]), k = l, p = l + 1; e > p; k++, p++)g[k] = g[p];
                e--, q = 2 * e
            }
        }
        return d ? h : f
    }, a.Triangulate.area = b, a
}(THREE.FontUtils),self._typeface_js = {
    faces: THREE.FontUtils.faces,
    loadFace: THREE.FontUtils.loadFace
},THREE.typeface_js = self._typeface_js,THREE.Audio = function (a) {
    THREE.Object3D.call(this), this.type = "Audio", this.context = a.context, this.source = this.context.createBufferSource(), this.source.onended = this.onEnded.bind(this), this.gain = this.context.createGain(), this.gain.connect(this.context.destination), this.panner = this.context.createPanner(), this.panner.connect(this.gain), this.autoplay = !1, this.startTime = 0, this.isPlaying = !1
},THREE.Audio.prototype = Object.create(THREE.Object3D.prototype),THREE.Audio.prototype.constructor = THREE.Audio,THREE.Audio.prototype.load = function (a) {
    var b = this, c = new XMLHttpRequest;
    return c.open("GET", a, !0), c.responseType = "arraybuffer", c.onload = function (a) {
        b.context.decodeAudioData(this.response, function (a) {
            b.source.buffer = a, b.autoplay && b.play()
        })
    }, c.send(), this
},THREE.Audio.prototype.play = function () {
    if (!0 === this.isPlaying)THREE.warn("THREE.Audio: Audio is already playing."); else {
        var a = this.context.createBufferSource();
        a.buffer = this.source.buffer, a.loop = this.source.loop, a.onended = this.source.onended, a.connect(this.panner), a.start(0, this.startTime), this.isPlaying = !0, this.source = a
    }
},THREE.Audio.prototype.pause = function () {
    this.source.stop(), this.startTime = this.context.currentTime
},THREE.Audio.prototype.stop = function () {
    this.source.stop(), this.startTime = 0
},THREE.Audio.prototype.onEnded = function () {
    this.isPlaying = !1
},THREE.Audio.prototype.setLoop = function (a) {
    this.source.loop = a
},THREE.Audio.prototype.setRefDistance = function (a) {
    this.panner.refDistance = a
},THREE.Audio.prototype.setRolloffFactor = function (a) {
    this.panner.rolloffFactor = a
},THREE.Audio.prototype.setVolume = function (a) {
    this.gain.gain.value = a
},THREE.Audio.prototype.updateMatrixWorld = function () {
    var a = new THREE.Vector3;
    return function (b) {
        THREE.Object3D.prototype.updateMatrixWorld.call(this, b), a.setFromMatrixPosition(this.matrixWorld), this.panner.setPosition(a.x, a.y, a.z)
    }
}(),THREE.AudioListener = function () {
    THREE.Object3D.call(this), this.type = "AudioListener", this.context = new (window.AudioContext || window.webkitAudioContext)
},THREE.AudioListener.prototype = Object.create(THREE.Object3D.prototype),THREE.AudioListener.prototype.constructor = THREE.AudioListener,THREE.AudioListener.prototype.updateMatrixWorld = function () {
    var a = new THREE.Vector3, b = new THREE.Quaternion, c = new THREE.Vector3, d = new THREE.Vector3, e = new THREE.Vector3, f = new THREE.Vector3;
    return function (g) {
        THREE.Object3D.prototype.updateMatrixWorld.call(this, g), g = this.context.listener;
        var h = this.up;
        this.matrixWorld.decompose(a, b, c), d.set(0, 0, -1).applyQuaternion(b), e.subVectors(a, f), g.setPosition(a.x, a.y, a.z), g.setOrientation(d.x, d.y, d.z, h.x, h.y, h.z), g.setVelocity(e.x, e.y, e.z), f.copy(a)
    }
}(),THREE.Curve = function () {
},THREE.Curve.prototype.getPoint = function (a) {
    return THREE.warn("THREE.Curve: Warning, getPoint() not implemented!"), null
},THREE.Curve.prototype.getPointAt = function (a) {
    return a = this.getUtoTmapping(a), this.getPoint(a)
},THREE.Curve.prototype.getPoints = function (a) {
    a || (a = 5);
    var b, c = [];
    for (b = 0; a >= b; b++)c.push(this.getPoint(b / a));
    return c
},THREE.Curve.prototype.getSpacedPoints = function (a) {
    a || (a = 5);
    var b, c = [];
    for (b = 0; a >= b; b++)c.push(this.getPointAt(b / a));
    return c
},THREE.Curve.prototype.getLength = function () {
    var a = this.getLengths();
    return a[a.length - 1]
},THREE.Curve.prototype.getLengths = function (a) {
    if (a || (a = this.__arcLengthDivisions ? this.__arcLengthDivisions : 200), this.cacheArcLengths && this.cacheArcLengths.length == a + 1 && !this.needsUpdate)return this.cacheArcLengths;
    this.needsUpdate = !1;
    var c, e, b = [], d = this.getPoint(0), f = 0;
    for (b.push(0), e = 1; a >= e; e++)c = this.getPoint(e / a), f += c.distanceTo(d), b.push(f), d = c;
    return this.cacheArcLengths = b
},THREE.Curve.prototype.updateArcLengths = function () {
    this.needsUpdate = !0, this.getLengths()
},THREE.Curve.prototype.getUtoTmapping = function (a, b) {
    var f, c = this.getLengths(), d = 0, e = c.length;
    f = b ? b : a * c[e - 1];
    for (var k, g = 0, h = e - 1; h >= g;)if (d = Math.floor(g + (h - g) / 2), k = c[d] - f, 0 > k)g = d + 1; else {
        if (!(k > 0)) {
            h = d;
            break
        }
        h = d - 1
    }
    return d = h, c[d] == f ? d / (e - 1) : (g = c[d], c = (d + (f - g) / (c[d + 1] - g)) / (e - 1))
},THREE.Curve.prototype.getTangent = function (a) {
    var b = a - 1e-4;
    return a += 1e-4, 0 > b && (b = 0), a > 1 && (a = 1), b = this.getPoint(b), this.getPoint(a).clone().sub(b).normalize()
},THREE.Curve.prototype.getTangentAt = function (a) {
    return a = this.getUtoTmapping(a), this.getTangent(a)
},THREE.Curve.Utils = {
    tangentQuadraticBezier: function (a, b, c, d) {
        return 2 * (1 - a) * (c - b) + 2 * a * (d - c)
    }, tangentCubicBezier: function (a, b, c, d, e) {
        return -3 * b * (1 - a) * (1 - a) + 3 * c * (1 - a) * (1 - a) - 6 * a * c * (1 - a) + 6 * a * d * (1 - a) - 3 * a * a * d + 3 * a * a * e
    }, tangentSpline: function (a, b, c, d, e) {
        return 6 * a * a - 6 * a + (3 * a * a - 4 * a + 1) + (-6 * a * a + 6 * a) + (3 * a * a - 2 * a)
    }, interpolate: function (a, b, c, d, e) {
        a = .5 * (c - a), d = .5 * (d - b);
        var f = e * e;
        return (2 * b - 2 * c + a + d) * e * f + (-3 * b + 3 * c - 2 * a - d) * f + a * e + b
    }
},THREE.Curve.create = function (a, b) {
    return a.prototype = Object.create(THREE.Curve.prototype), a.prototype.constructor = a, a.prototype.getPoint = b, a
},THREE.CurvePath = function () {
    this.curves = [], this.bends = [], this.autoClose = !1
},THREE.CurvePath.prototype = Object.create(THREE.Curve.prototype),THREE.CurvePath.prototype.constructor = THREE.CurvePath,THREE.CurvePath.prototype.add = function (a) {
    this.curves.push(a)
},THREE.CurvePath.prototype.checkConnection = function () {
},THREE.CurvePath.prototype.closePath = function () {
    var a = this.curves[0].getPoint(0), b = this.curves[this.curves.length - 1].getPoint(1);
    a.equals(b) || this.curves.push(new THREE.LineCurve(b, a))
},THREE.CurvePath.prototype.getPoint = function (a) {
    var b = a * this.getLength(), c = this.getCurveLengths();
    for (a = 0; a < c.length;) {
        if (c[a] >= b)return b = c[a] - b, a = this.curves[a], b = 1 - b / a.getLength(), a.getPointAt(b);
        a++
    }
    return null
},THREE.CurvePath.prototype.getLength = function () {
    var a = this.getCurveLengths();
    return a[a.length - 1]
},THREE.CurvePath.prototype.getCurveLengths = function () {
    if (this.cacheLengths && this.cacheLengths.length == this.curves.length)return this.cacheLengths;
    var c, a = [], b = 0, d = this.curves.length;
    for (c = 0; d > c; c++)b += this.curves[c].getLength(), a.push(b);
    return this.cacheLengths = a
},THREE.CurvePath.prototype.getBoundingBox = function () {
    var b, c, d, e, f, g, a = this.getPoints();
    b = c = Number.NEGATIVE_INFINITY, e = f = Number.POSITIVE_INFINITY;
    var h, k, l, p, q = a[0] instanceof THREE.Vector3;
    for (p = q ? new THREE.Vector3 : new THREE.Vector2, k = 0, l = a.length; l > k; k++)h = a[k], h.x > b ? b = h.x : h.x < e && (e = h.x), h.y > c ? c = h.y : h.y < f && (f = h.y), q && (h.z > d ? d = h.z : h.z < g && (g = h.z)), p.add(h);
    return a = {minX: e, minY: f, maxX: b, maxY: c}, q && (a.maxZ = d, a.minZ = g), a
},THREE.CurvePath.prototype.createPointsGeometry = function (a) {
    return a = this.getPoints(a, !0), this.createGeometry(a)
},THREE.CurvePath.prototype.createSpacedPointsGeometry = function (a) {
    return a = this.getSpacedPoints(a, !0), this.createGeometry(a)
},THREE.CurvePath.prototype.createGeometry = function (a) {
    for (var b = new THREE.Geometry, c = 0; c < a.length; c++)b.vertices.push(new THREE.Vector3(a[c].x, a[c].y, a[c].z || 0));
    return b
},THREE.CurvePath.prototype.addWrapPath = function (a) {
    this.bends.push(a)
},THREE.CurvePath.prototype.getTransformedPoints = function (a, b) {
    var d, e, c = this.getPoints(a);
    for (b || (b = this.bends), d = 0, e = b.length; e > d; d++)c = this.getWrapPoints(c, b[d]);
    return c
},THREE.CurvePath.prototype.getTransformedSpacedPoints = function (a, b) {
    var d, e, c = this.getSpacedPoints(a);
    for (b || (b = this.bends), d = 0, e = b.length; e > d; d++)c = this.getWrapPoints(c, b[d]);
    return c
},THREE.CurvePath.prototype.getWrapPoints = function (a, b) {
    var d, e, f, g, h, k, c = this.getBoundingBox();
    for (d = 0, e = a.length; e > d; d++)f = a[d], g = f.x, h = f.y, k = g / c.maxX, k = b.getUtoTmapping(k, g), g = b.getPoint(k), k = b.getTangent(k), k.set(-k.y, k.x).multiplyScalar(h), f.x = g.x + k.x, f.y = g.y + k.y;
    return a
},THREE.Gyroscope = function () {
    THREE.Object3D.call(this)
},THREE.Gyroscope.prototype = Object.create(THREE.Object3D.prototype),THREE.Gyroscope.prototype.constructor = THREE.Gyroscope,THREE.Gyroscope.prototype.updateMatrixWorld = function () {
    var a = new THREE.Vector3, b = new THREE.Quaternion, c = new THREE.Vector3, d = new THREE.Vector3, e = new THREE.Quaternion, f = new THREE.Vector3;
    return function (g) {
        this.matrixAutoUpdate && this.updateMatrix(), (this.matrixWorldNeedsUpdate || g) && (this.parent ? (this.matrixWorld.multiplyMatrices(this.parent.matrixWorld, this.matrix), this.matrixWorld.decompose(d, e, f), this.matrix.decompose(a, b, c), this.matrixWorld.compose(d, b, f)) : this.matrixWorld.copy(this.matrix), this.matrixWorldNeedsUpdate = !1, g = !0);
        for (var h = 0, k = this.children.length; k > h; h++)this.children[h].updateMatrixWorld(g)
    }
}(),THREE.Path = function (a) {
    THREE.CurvePath.call(this), this.actions = [], a && this.fromPoints(a)
},THREE.Path.prototype = Object.create(THREE.CurvePath.prototype),THREE.Path.prototype.constructor = THREE.Path,THREE.PathActions = {
    MOVE_TO: "moveTo",
    LINE_TO: "lineTo",
    QUADRATIC_CURVE_TO: "quadraticCurveTo",
    BEZIER_CURVE_TO: "bezierCurveTo",
    CSPLINE_THRU: "splineThru",
    ARC: "arc",
    ELLIPSE: "ellipse"
},THREE.Path.prototype.fromPoints = function (a) {
    this.moveTo(a[0].x, a[0].y);
    for (var b = 1, c = a.length; c > b; b++)this.lineTo(a[b].x, a[b].y)
},THREE.Path.prototype.moveTo = function (a, b) {
    var c = Array.prototype.slice.call(arguments);
    this.actions.push({action: THREE.PathActions.MOVE_TO, args: c})
},THREE.Path.prototype.lineTo = function (a, b) {
    var c = Array.prototype.slice.call(arguments), d = this.actions[this.actions.length - 1].args, d = new THREE.LineCurve(new THREE.Vector2(d[d.length - 2], d[d.length - 1]), new THREE.Vector2(a, b));
    this.curves.push(d), this.actions.push({action: THREE.PathActions.LINE_TO, args: c})
},THREE.Path.prototype.quadraticCurveTo = function (a, b, c, d) {
    var e = Array.prototype.slice.call(arguments), f = this.actions[this.actions.length - 1].args, f = new THREE.QuadraticBezierCurve(new THREE.Vector2(f[f.length - 2], f[f.length - 1]), new THREE.Vector2(a, b), new THREE.Vector2(c, d));
    this.curves.push(f), this.actions.push({action: THREE.PathActions.QUADRATIC_CURVE_TO, args: e})
},THREE.Path.prototype.bezierCurveTo = function (a, b, c, d, e, f) {
    var g = Array.prototype.slice.call(arguments), h = this.actions[this.actions.length - 1].args, h = new THREE.CubicBezierCurve(new THREE.Vector2(h[h.length - 2], h[h.length - 1]), new THREE.Vector2(a, b), new THREE.Vector2(c, d), new THREE.Vector2(e, f));
    this.curves.push(h), this.actions.push({action: THREE.PathActions.BEZIER_CURVE_TO, args: g})
},THREE.Path.prototype.splineThru = function (a) {
    var b = Array.prototype.slice.call(arguments), c = this.actions[this.actions.length - 1].args, c = [new THREE.Vector2(c[c.length - 2], c[c.length - 1])];
    Array.prototype.push.apply(c, a), c = new THREE.SplineCurve(c), this.curves.push(c), this.actions.push({
        action: THREE.PathActions.CSPLINE_THRU,
        args: b
    })
},THREE.Path.prototype.arc = function (a, b, c, d, e, f) {
    var g = this.actions[this.actions.length - 1].args;
    this.absarc(a + g[g.length - 2], b + g[g.length - 1], c, d, e, f)
},THREE.Path.prototype.absarc = function (a, b, c, d, e, f) {
    this.absellipse(a, b, c, c, d, e, f)
},THREE.Path.prototype.ellipse = function (a, b, c, d, e, f, g) {
    var h = this.actions[this.actions.length - 1].args;
    this.absellipse(a + h[h.length - 2], b + h[h.length - 1], c, d, e, f, g)
},THREE.Path.prototype.absellipse = function (a, b, c, d, e, f, g) {
    var h = Array.prototype.slice.call(arguments), k = new THREE.EllipseCurve(a, b, c, d, e, f, g);
    this.curves.push(k), k = k.getPoint(1), h.push(k.x), h.push(k.y), this.actions.push({
        action: THREE.PathActions.ELLIPSE,
        args: h
    })
},THREE.Path.prototype.getSpacedPoints = function (a, b) {
    a || (a = 40);
    for (var c = [], d = 0; a > d; d++)c.push(this.getPoint(d / a));
    return c
},THREE.Path.prototype.getPoints = function (a, b) {
    if (this.useSpacedPoints)return console.log("tata"), this.getSpacedPoints(a, b);
    a = a || 12;
    var d, e, f, g, h, k, l, p, q, n, t, r, s, c = [];
    for (d = 0, e = this.actions.length; e > d; d++)switch (f = this.actions[d], g = f.action, f = f.args, g) {
        case THREE.PathActions.MOVE_TO:
            c.push(new THREE.Vector2(f[0], f[1]));
            break;
        case THREE.PathActions.LINE_TO:
            c.push(new THREE.Vector2(f[0], f[1]));
            break;
        case THREE.PathActions.QUADRATIC_CURVE_TO:
            for (h = f[2], k = f[3], q = f[0], n = f[1], 0 < c.length ? (g = c[c.length - 1], t = g.x, r = g.y) : (g = this.actions[d - 1].args, t = g[g.length - 2], r = g[g.length - 1]), f = 1; a >= f; f++)s = f / a, g = THREE.Shape.Utils.b2(s, t, q, h), s = THREE.Shape.Utils.b2(s, r, n, k), c.push(new THREE.Vector2(g, s));
            break;
        case THREE.PathActions.BEZIER_CURVE_TO:
            for (h = f[4], k = f[5], q = f[0], n = f[1], l = f[2], p = f[3], 0 < c.length ? (g = c[c.length - 1], t = g.x, r = g.y) : (g = this.actions[d - 1].args, t = g[g.length - 2], r = g[g.length - 1]), f = 1; a >= f; f++)s = f / a, g = THREE.Shape.Utils.b3(s, t, q, l, h), s = THREE.Shape.Utils.b3(s, r, n, p, k), c.push(new THREE.Vector2(g, s));
            break;
        case THREE.PathActions.CSPLINE_THRU:
            for (g = this.actions[d - 1].args, s = [new THREE.Vector2(g[g.length - 2], g[g.length - 1])], g = a * f[0].length, s = s.concat(f[0]), s = new THREE.SplineCurve(s), f = 1; g >= f; f++)c.push(s.getPointAt(f / g));
            break;
        case THREE.PathActions.ARC:
            for (h = f[0], k = f[1], n = f[2], l = f[3], g = f[4], q = !!f[5], t = g - l, r = 2 * a, f = 1; r >= f; f++)s = f / r, q || (s = 1 - s), s = l + s * t, g = h + n * Math.cos(s), s = k + n * Math.sin(s), c.push(new THREE.Vector2(g, s));
            break;
        case THREE.PathActions.ELLIPSE:
            for (h = f[0], k = f[1], n = f[2], p = f[3], l = f[4], g = f[5], q = !!f[6], t = g - l, r = 2 * a, f = 1; r >= f; f++)s = f / r, q || (s = 1 - s), s = l + s * t, g = h + n * Math.cos(s), s = k + p * Math.sin(s), c.push(new THREE.Vector2(g, s))
    }
    return d = c[c.length - 1], 1e-10 > Math.abs(d.x - c[0].x) && 1e-10 > Math.abs(d.y - c[0].y) && c.splice(c.length - 1, 1), b && c.push(c[0]), c
},THREE.Path.prototype.toShapes = function (a, b) {
    function c(a) {
        for (var b = [], c = 0, d = a.length; d > c; c++) {
            var e = a[c], f = new THREE.Shape;
            f.actions = e.actions, f.curves = e.curves, b.push(f)
        }
        return b
    }

    function d(a, b) {
        for (var c = b.length, d = !1, e = c - 1, f = 0; c > f; e = f++) {
            var g = b[e], h = b[f], k = h.x - g.x, n = h.y - g.y;
            if (1e-10 < Math.abs(n)) {
                if (0 > n && (g = b[f], k = -k, h = b[e], n = -n), !(a.y < g.y || a.y > h.y))if (a.y == g.y) {
                    if (a.x == g.x)return !0
                } else {
                    if (e = n * (a.x - g.x) - k * (a.y - g.y), 0 == e)return !0;
                    0 > e || (d = !d)
                }
            } else if (a.y == g.y && (h.x <= a.x && a.x <= g.x || g.x <= a.x && a.x <= h.x))return !0
        }
        return d
    }

    var e = function (a) {
        var b, c, d, e, f = [], g = new THREE.Path;
        for (b = 0, c = a.length; c > b; b++)d = a[b], e = d.args, d = d.action, d == THREE.PathActions.MOVE_TO && 0 != g.actions.length && (f.push(g), g = new THREE.Path), g[d].apply(g, e);
        return 0 != g.actions.length && f.push(g), f
    }(this.actions);
    if (0 == e.length)return [];
    if (!0 === b)return c(e);
    var f, g, h, k = [];
    if (1 == e.length)return g = e[0], h = new THREE.Shape, h.actions = g.actions, h.curves = g.curves, k.push(h), k;
    var l = !THREE.Shape.Utils.isClockWise(e[0].getPoints()), l = a ? !l : l;
    h = [];
    var t, p = [], q = [], n = 0;
    p[n] = void 0, q[n] = [];
    var r, s;
    for (r = 0, s = e.length; s > r; r++)g = e[r], t = g.getPoints(), f = THREE.Shape.Utils.isClockWise(t), (f = a ? !f : f) ? (!l && p[n] && n++, p[n] = {
        s: new THREE.Shape,
        p: t
    }, p[n].s.actions = g.actions, p[n].s.curves = g.curves, l && n++, q[n] = []) : q[n].push({h: g, p: t[0]});
    if (!p[0])return c(e);
    if (1 < p.length) {
        for (r = !1, s = [], g = 0, e = p.length; e > g; g++)h[g] = [];
        for (g = 0, e = p.length; e > g; g++)for (f = q[g], l = 0; l < f.length; l++) {
            n = f[l], t = !0;
            for (var u = 0; u < p.length; u++)d(n.p, p[u].p) && (g != u && s.push({
                froms: g,
                tos: u,
                hole: l
            }), t ? (t = !1, h[u].push(n)) : r = !0);
            t && h[g].push(n)
        }
        0 < s.length && (r || (q = h))
    }
    for (r = 0, s = p.length; s > r; r++)for (h = p[r].s, k.push(h), g = q[r], e = 0, f = g.length; f > e; e++)h.holes.push(g[e].h);
    return k
},THREE.Shape = function () {
    THREE.Path.apply(this, arguments), this.holes = []
},THREE.Shape.prototype = Object.create(THREE.Path.prototype),THREE.Shape.prototype.constructor = THREE.Shape,THREE.Shape.prototype.extrude = function (a) {
    return new THREE.ExtrudeGeometry(this, a)
},THREE.Shape.prototype.makeGeometry = function (a) {
    return new THREE.ShapeGeometry(this, a)
},THREE.Shape.prototype.getPointsHoles = function (a) {
    var b, c = this.holes.length, d = [];
    for (b = 0; c > b; b++)d[b] = this.holes[b].getTransformedPoints(a, this.bends);
    return d
},THREE.Shape.prototype.getSpacedPointsHoles = function (a) {
    var b, c = this.holes.length, d = [];
    for (b = 0; c > b; b++)d[b] = this.holes[b].getTransformedSpacedPoints(a, this.bends);
    return d
},THREE.Shape.prototype.extractAllPoints = function (a) {
    return {shape: this.getTransformedPoints(a), holes: this.getPointsHoles(a)}
},THREE.Shape.prototype.extractPoints = function (a) {
    return this.useSpacedPoints ? this.extractAllSpacedPoints(a) : this.extractAllPoints(a)
},THREE.Shape.prototype.extractAllSpacedPoints = function (a) {
    return {shape: this.getTransformedSpacedPoints(a), holes: this.getSpacedPointsHoles(a)}
},THREE.Shape.Utils = {
    triangulateShape: function (a, b) {
        function c(a, b, c) {
            return a.x != b.x ? a.x < b.x ? a.x <= c.x && c.x <= b.x : b.x <= c.x && c.x <= a.x : a.y < b.y ? a.y <= c.y && c.y <= b.y : b.y <= c.y && c.y <= a.y
        }

        function d(a, b, d, e, f) {
            var g = b.x - a.x, h = b.y - a.y, k = e.x - d.x, l = e.y - d.y, p = a.x - d.x, q = a.y - d.y, E = h * k - g * l, G = h * p - g * q;
            if (1e-10 < Math.abs(E)) {
                if (E > 0) {
                    if (0 > G || G > E)return [];
                    if (k = l * p - k * q, 0 > k || k > E)return []
                } else {
                    if (G > 0 || E > G)return [];
                    if (k = l * p - k * q, k > 0 || E > k)return []
                }
                return 0 == k ? !f || 0 != G && G != E ? [a] : [] : k == E ? !f || 0 != G && G != E ? [b] : [] : 0 == G ? [d] : G == E ? [e] : (f = k / E, [{
                    x: a.x + f * g,
                    y: a.y + f * h
                }])
            }
            return 0 != G || l * p != k * q ? [] : (h = 0 == g && 0 == h, k = 0 == k && 0 == l, h && k ? a.x != d.x || a.y != d.y ? [] : [a] : h ? c(d, e, a) ? [a] : [] : k ? c(a, b, d) ? [d] : [] : (0 != g ? (a.x < b.x ? (g = a, k = a.x, h = b, a = b.x) : (g = b, k = b.x, h = a, a = a.x), d.x < e.x ? (b = d, E = d.x, l = e, d = e.x) : (b = e, E = e.x, l = d, d = d.x)) : (a.y < b.y ? (g = a, k = a.y, h = b, a = b.y) : (g = b, k = b.y, h = a, a = a.y), d.y < e.y ? (b = d, E = d.y, l = e, d = e.y) : (b = e, E = e.y, l = d, d = d.y)), E >= k ? E > a ? [] : a == E ? f ? [] : [b] : d >= a ? [b, h] : [b, l] : k > d ? [] : k == d ? f ? [] : [g] : d >= a ? [g, h] : [g, l]))
        }

        function e(a, b, c, d) {
            var e = b.x - a.x, f = b.y - a.y;
            b = c.x - a.x, c = c.y - a.y;
            var g = d.x - a.x;
            return d = d.y - a.y, a = e * c - f * b, e = e * d - f * g, 1e-10 < Math.abs(a) ? (b = g * c - d * b, a > 0 ? e >= 0 && b >= 0 : e >= 0 || b >= 0) : e > 0
        }

        var f, g, h, k, l, p = {};
        for (h = a.concat(), f = 0, g = b.length; g > f; f++)Array.prototype.push.apply(h, b[f]);
        for (f = 0, g = h.length; g > f; f++)l = h[f].x + ":" + h[f].y, void 0 !== p[l] && THREE.warn("THREE.Shape: Duplicate point", l), p[l] = f;
        f = function (a, b) {
            function c(a, b) {
                var d = h.length - 1, f = a - 1;
                0 > f && (f = d);
                var g = a + 1;
                return g > d && (g = 0), (d = e(h[a], h[f], h[g], k[b])) ? (d = k.length - 1, f = b - 1, 0 > f && (f = d), g = b + 1, g > d && (g = 0), (d = e(k[b], k[f], k[g], h[a])) ? !0 : !1) : !1
            }

            function f(a, b) {
                var c, e;
                for (c = 0; c < h.length; c++)if (e = c + 1, e %= h.length, e = d(a, b, h[c], h[e], !0), 0 < e.length)return !0;
                return !1
            }

            function g(a, c) {
                var e, f, h, k;
                for (e = 0; e < l.length; e++)for (f = b[l[e]], h = 0; h < f.length; h++)if (k = h + 1, k %= f.length, k = d(a, c, f[h], f[k], !0), 0 < k.length)return !0;
                return !1
            }

            var k, p, q, A, E, G, z, I, U, h = a.concat(), l = [], F = [], M = 0;
            for (p = b.length; p > M; M++)l.push(M);
            z = 0;
            for (var H = 2 * l.length; 0 < l.length;) {
                if (H--, 0 > H) {
                    console.log("Infinite Loop! Holes left:" + l.length + ", Probably Hole outside Shape!");
                    break
                }
                for (q = z; q < h.length; q++) {
                    for (A = h[q], p = -1, M = 0; M < l.length; M++)if (E = l[M], G = A.x + ":" + A.y + ":" + E, void 0 === F[G]) {
                        for (k = b[E], I = 0; I < k.length; I++)if (E = k[I], c(q, I) && !f(A, E) && !g(A, E)) {
                            p = I, l.splice(M, 1), z = h.slice(0, q + 1), E = h.slice(q), I = k.slice(p), U = k.slice(0, p + 1), h = z.concat(I).concat(U).concat(E), z = q;
                            break
                        }
                        if (p >= 0)break;
                        F[G] = !0
                    }
                    if (p >= 0)break
                }
            }
            return h
        }(a, b);
        var q = THREE.FontUtils.Triangulate(f, !1);
        for (f = 0, g = q.length; g > f; f++)for (k = q[f], h = 0; 3 > h; h++)l = k[h].x + ":" + k[h].y, l = p[l], void 0 !== l && (k[h] = l);
        return q.concat()
    }, isClockWise: function (a) {
        return 0 > THREE.FontUtils.Triangulate.area(a)
    }, b2p0: function (a, b) {
        var c = 1 - a;
        return c * c * b
    }, b2p1: function (a, b) {
        return 2 * (1 - a) * a * b
    }, b2p2: function (a, b) {
        return a * a * b
    }, b2: function (a, b, c, d) {
        return this.b2p0(a, b) + this.b2p1(a, c) + this.b2p2(a, d)
    }, b3p0: function (a, b) {
        var c = 1 - a;
        return c * c * c * b
    }, b3p1: function (a, b) {
        var c = 1 - a;
        return 3 * c * c * a * b
    }, b3p2: function (a, b) {
        return 3 * (1 - a) * a * a * b
    }, b3p3: function (a, b) {
        return a * a * a * b
    }, b3: function (a, b, c, d, e) {
        return this.b3p0(a, b) + this.b3p1(a, c) + this.b3p2(a, d) + this.b3p3(a, e)
    }
},THREE.LineCurve = function (a, b) {
    this.v1 = a, this.v2 = b
},THREE.LineCurve.prototype = Object.create(THREE.Curve.prototype),THREE.LineCurve.prototype.constructor = THREE.LineCurve,THREE.LineCurve.prototype.getPoint = function (a) {
    var b = this.v2.clone().sub(this.v1);
    return b.multiplyScalar(a).add(this.v1), b
},THREE.LineCurve.prototype.getPointAt = function (a) {
    return this.getPoint(a)
},THREE.LineCurve.prototype.getTangent = function (a) {
    return this.v2.clone().sub(this.v1).normalize()
},THREE.QuadraticBezierCurve = function (a, b, c) {
    this.v0 = a, this.v1 = b, this.v2 = c
},THREE.QuadraticBezierCurve.prototype = Object.create(THREE.Curve.prototype),THREE.QuadraticBezierCurve.prototype.constructor = THREE.QuadraticBezierCurve,THREE.QuadraticBezierCurve.prototype.getPoint = function (a) {
    var b = new THREE.Vector2;
    return b.x = THREE.Shape.Utils.b2(a, this.v0.x, this.v1.x, this.v2.x), b.y = THREE.Shape.Utils.b2(a, this.v0.y, this.v1.y, this.v2.y), b
},THREE.QuadraticBezierCurve.prototype.getTangent = function (a) {
    var b = new THREE.Vector2;
    return b.x = THREE.Curve.Utils.tangentQuadraticBezier(a, this.v0.x, this.v1.x, this.v2.x), b.y = THREE.Curve.Utils.tangentQuadraticBezier(a, this.v0.y, this.v1.y, this.v2.y), b.normalize()
},THREE.CubicBezierCurve = function (a, b, c, d) {
    this.v0 = a, this.v1 = b, this.v2 = c, this.v3 = d
},THREE.CubicBezierCurve.prototype = Object.create(THREE.Curve.prototype),THREE.CubicBezierCurve.prototype.constructor = THREE.CubicBezierCurve,THREE.CubicBezierCurve.prototype.getPoint = function (a) {
    var b;
    return b = THREE.Shape.Utils.b3(a, this.v0.x, this.v1.x, this.v2.x, this.v3.x), a = THREE.Shape.Utils.b3(a, this.v0.y, this.v1.y, this.v2.y, this.v3.y), new THREE.Vector2(b, a)
},THREE.CubicBezierCurve.prototype.getTangent = function (a) {
    var b;
    return b = THREE.Curve.Utils.tangentCubicBezier(a, this.v0.x, this.v1.x, this.v2.x, this.v3.x), a = THREE.Curve.Utils.tangentCubicBezier(a, this.v0.y, this.v1.y, this.v2.y, this.v3.y), b = new THREE.Vector2(b, a), b.normalize(), b
},THREE.SplineCurve = function (a) {
    this.points = void 0 == a ? [] : a
},THREE.SplineCurve.prototype = Object.create(THREE.Curve.prototype),THREE.SplineCurve.prototype.constructor = THREE.SplineCurve,THREE.SplineCurve.prototype.getPoint = function (a) {
    var b = this.points;
    a *= b.length - 1;
    var c = Math.floor(a);
    a -= c;
    var d = b[0 == c ? c : c - 1], e = b[c], f = b[c > b.length - 2 ? b.length - 1 : c + 1], b = b[c > b.length - 3 ? b.length - 1 : c + 2], c = new THREE.Vector2;
    return c.x = THREE.Curve.Utils.interpolate(d.x, e.x, f.x, b.x, a), c.y = THREE.Curve.Utils.interpolate(d.y, e.y, f.y, b.y, a), c
},THREE.EllipseCurve = function (a, b, c, d, e, f, g) {
    this.aX = a, this.aY = b, this.xRadius = c, this.yRadius = d, this.aStartAngle = e, this.aEndAngle = f, this.aClockwise = g
},THREE.EllipseCurve.prototype = Object.create(THREE.Curve.prototype),THREE.EllipseCurve.prototype.constructor = THREE.EllipseCurve,THREE.EllipseCurve.prototype.getPoint = function (a) {
    var b = this.aEndAngle - this.aStartAngle;
    return 0 > b && (b += 2 * Math.PI), b > 2 * Math.PI && (b -= 2 * Math.PI), a = !0 === this.aClockwise ? this.aEndAngle + (1 - a) * (2 * Math.PI - b) : this.aStartAngle + a * b, b = new THREE.Vector2, b.x = this.aX + this.xRadius * Math.cos(a), b.y = this.aY + this.yRadius * Math.sin(a), b
},THREE.ArcCurve = function (a, b, c, d, e, f) {
    THREE.EllipseCurve.call(this, a, b, c, c, d, e, f)
},THREE.ArcCurve.prototype = Object.create(THREE.EllipseCurve.prototype),THREE.ArcCurve.prototype.constructor = THREE.ArcCurve,THREE.LineCurve3 = THREE.Curve.create(function (a, b) {
    this.v1 = a, this.v2 = b
}, function (a) {
    var b = new THREE.Vector3;
    return b.subVectors(this.v2, this.v1), b.multiplyScalar(a), b.add(this.v1), b
}),THREE.QuadraticBezierCurve3 = THREE.Curve.create(function (a, b, c) {
    this.v0 = a, this.v1 = b, this.v2 = c
}, function (a) {
    var b = new THREE.Vector3;
    return b.x = THREE.Shape.Utils.b2(a, this.v0.x, this.v1.x, this.v2.x), b.y = THREE.Shape.Utils.b2(a, this.v0.y, this.v1.y, this.v2.y), b.z = THREE.Shape.Utils.b2(a, this.v0.z, this.v1.z, this.v2.z), b
}),THREE.CubicBezierCurve3 = THREE.Curve.create(function (a, b, c, d) {
    this.v0 = a, this.v1 = b, this.v2 = c, this.v3 = d
}, function (a) {
    var b = new THREE.Vector3;
    return b.x = THREE.Shape.Utils.b3(a, this.v0.x, this.v1.x, this.v2.x, this.v3.x), b.y = THREE.Shape.Utils.b3(a, this.v0.y, this.v1.y, this.v2.y, this.v3.y), b.z = THREE.Shape.Utils.b3(a, this.v0.z, this.v1.z, this.v2.z, this.v3.z), b
}),THREE.SplineCurve3 = THREE.Curve.create(function (a) {
    this.points = void 0 == a ? [] : a
}, function (a) {
    var b = this.points;
    a *= b.length - 1;
    var c = Math.floor(a);
    a -= c;
    var d = b[0 == c ? c : c - 1], e = b[c], f = b[c > b.length - 2 ? b.length - 1 : c + 1], b = b[c > b.length - 3 ? b.length - 1 : c + 2], c = new THREE.Vector3;
    return c.x = THREE.Curve.Utils.interpolate(d.x, e.x, f.x, b.x, a), c.y = THREE.Curve.Utils.interpolate(d.y, e.y, f.y, b.y, a), c.z = THREE.Curve.Utils.interpolate(d.z, e.z, f.z, b.z, a), c
}),THREE.ClosedSplineCurve3 = THREE.Curve.create(function (a) {
    this.points = void 0 == a ? [] : a
}, function (a) {
    var b = this.points;
    a *= b.length - 0;
    var c = Math.floor(a);
    a -= c;
    var c = c + (c > 0 ? 0 : (Math.floor(Math.abs(c) / b.length) + 1) * b.length), d = b[(c - 1) % b.length], e = b[c % b.length], f = b[(c + 1) % b.length], b = b[(c + 2) % b.length], c = new THREE.Vector3;
    return c.x = THREE.Curve.Utils.interpolate(d.x, e.x, f.x, b.x, a), c.y = THREE.Curve.Utils.interpolate(d.y, e.y, f.y, b.y, a), c.z = THREE.Curve.Utils.interpolate(d.z, e.z, f.z, b.z, a), c
}),THREE.AnimationHandler = {
    LINEAR: 0, CATMULLROM: 1, CATMULLROM_FORWARD: 2, add: function () {
        THREE.warn("THREE.AnimationHandler.add() has been deprecated.")
    }, get: function () {
        THREE.warn("THREE.AnimationHandler.get() has been deprecated.")
    }, remove: function () {
        THREE.warn("THREE.AnimationHandler.remove() has been deprecated.")
    }, animations: [], init: function (a) {
        if (!0 === a.initialized)return a;
        for (var b = 0; b < a.hierarchy.length; b++) {
            for (var c = 0; c < a.hierarchy[b].keys.length; c++)if (0 > a.hierarchy[b].keys[c].time && (a.hierarchy[b].keys[c].time = 0), void 0 !== a.hierarchy[b].keys[c].rot && !(a.hierarchy[b].keys[c].rot instanceof THREE.Quaternion)) {
                var d = a.hierarchy[b].keys[c].rot;
                a.hierarchy[b].keys[c].rot = (new THREE.Quaternion).fromArray(d)
            }
            if (a.hierarchy[b].keys.length && void 0 !== a.hierarchy[b].keys[0].morphTargets) {
                for (d = {}, c = 0; c < a.hierarchy[b].keys.length; c++)for (var e = 0; e < a.hierarchy[b].keys[c].morphTargets.length; e++) {
                    var f = a.hierarchy[b].keys[c].morphTargets[e];
                    d[f] = -1
                }
                for (a.hierarchy[b].usedMorphTargets = d, c = 0; c < a.hierarchy[b].keys.length; c++) {
                    var g = {};
                    for (f in d) {
                        for (e = 0; e < a.hierarchy[b].keys[c].morphTargets.length; e++)if (a.hierarchy[b].keys[c].morphTargets[e] === f) {
                            g[f] = a.hierarchy[b].keys[c].morphTargetsInfluences[e];
                            break
                        }
                        e === a.hierarchy[b].keys[c].morphTargets.length && (g[f] = 0)
                    }
                    a.hierarchy[b].keys[c].morphTargetsInfluences = g
                }
            }
            for (c = 1; c < a.hierarchy[b].keys.length; c++)a.hierarchy[b].keys[c].time === a.hierarchy[b].keys[c - 1].time && (a.hierarchy[b].keys.splice(c, 1), c--);
            for (c = 0; c < a.hierarchy[b].keys.length; c++)a.hierarchy[b].keys[c].index = c
        }
        return a.initialized = !0, a
    }, parse: function (a) {
        var b = function (a, c) {
            c.push(a);
            for (var d = 0; d < a.children.length; d++)b(a.children[d], c)
        }, c = [];
        if (a instanceof THREE.SkinnedMesh)for (var d = 0; d < a.skeleton.bones.length; d++)c.push(a.skeleton.bones[d]); else b(a, c);
        return c
    }, play: function (a) {
        -1 === this.animations.indexOf(a) && this.animations.push(a)
    }, stop: function (a) {
        a = this.animations.indexOf(a), -1 !== a && this.animations.splice(a, 1)
    }, update: function (a) {
        for (var b = 0; b < this.animations.length; b++)this.animations[b].resetBlendWeights();
        for (b = 0; b < this.animations.length; b++)this.animations[b].update(a)
    }
},THREE.Animation = function (a, b) {
    this.root = a, this.data = THREE.AnimationHandler.init(b), this.hierarchy = THREE.AnimationHandler.parse(a), this.currentTime = 0, this.timeScale = 1, this.isPlaying = !1, this.loop = !0, this.weight = 0, this.interpolationType = THREE.AnimationHandler.LINEAR
},THREE.Animation.prototype = {
    constructor: THREE.Animation, keyTypes: ["pos", "rot", "scl"], play: function (a, b) {
        this.currentTime = void 0 !== a ? a : 0, this.weight = void 0 !== b ? b : 1, this.isPlaying = !0, this.reset(), THREE.AnimationHandler.play(this)
    }, stop: function () {
        this.isPlaying = !1, THREE.AnimationHandler.stop(this)
    }, reset: function () {
        for (var a = 0, b = this.hierarchy.length; b > a; a++) {
            var c = this.hierarchy[a];
            void 0 === c.animationCache && (c.animationCache = {
                animations: {},
                blending: {positionWeight: 0, quaternionWeight: 0, scaleWeight: 0}
            });
            var d = this.data.name, e = c.animationCache.animations, f = e[d];
            for (void 0 === f && (f = {
                prevKey: {pos: 0, rot: 0, scl: 0},
                nextKey: {pos: 0, rot: 0, scl: 0},
                originalMatrix: c.matrix
            }, e[d] = f), c = 0; 3 > c; c++) {
                for (var d = this.keyTypes[c], e = this.data.hierarchy[a].keys[0], g = this.getNextKeyWith(d, a, 1); g.time < this.currentTime && g.index > e.index;)e = g, g = this.getNextKeyWith(d, a, g.index + 1);
                f.prevKey[d] = e, f.nextKey[d] = g
            }
        }
    }, resetBlendWeights: function () {
        for (var a = 0, b = this.hierarchy.length; b > a; a++) {
            var c = this.hierarchy[a].animationCache;
            void 0 !== c && (c = c.blending, c.positionWeight = 0, c.quaternionWeight = 0, c.scaleWeight = 0)
        }
    }, update: function () {
        var a = [], b = new THREE.Vector3, c = new THREE.Vector3, d = new THREE.Quaternion, e = function (a, b) {
            var e, q, n, t, r, s, c = [], d = [];
            return e = (a.length - 1) * b, q = Math.floor(e), e -= q, c[0] = 0 === q ? q : q - 1, c[1] = q, c[2] = q > a.length - 2 ? q : q + 1, c[3] = q > a.length - 3 ? q : q + 2, q = a[c[0]], t = a[c[1]], r = a[c[2]], s = a[c[3]], c = e * e, n = e * c, d[0] = f(q[0], t[0], r[0], s[0], e, c, n), d[1] = f(q[1], t[1], r[1], s[1], e, c, n), d[2] = f(q[2], t[2], r[2], s[2], e, c, n), d
        }, f = function (a, b, c, d, e, f, n) {
            return a = .5 * (c - a), d = .5 * (d - b), (2 * (b - c) + a + d) * n + (-3 * (b - c) - 2 * a - d) * f + a * e + b
        };
        return function (f) {
            if (!1 !== this.isPlaying && (this.currentTime += f * this.timeScale, 0 !== this.weight)) {
                f = this.data.length, (this.currentTime > f || 0 > this.currentTime) && (this.loop ? (this.currentTime %= f, 0 > this.currentTime && (this.currentTime += f), this.reset()) : this.stop()), f = 0;
                for (var h = this.hierarchy.length; h > f; f++)for (var k = this.hierarchy[f], l = k.animationCache.animations[this.data.name], p = k.animationCache.blending, q = 0; 3 > q; q++) {
                    var n = this.keyTypes[q], t = l.prevKey[n], r = l.nextKey[n];
                    if (0 < this.timeScale && r.time <= this.currentTime || 0 > this.timeScale && t.time >= this.currentTime) {
                        for (t = this.data.hierarchy[f].keys[0], r = this.getNextKeyWith(n, f, 1); r.time < this.currentTime && r.index > t.index;)t = r, r = this.getNextKeyWith(n, f, r.index + 1);
                        l.prevKey[n] = t, l.nextKey[n] = r
                    }
                    var s = (this.currentTime - t.time) / (r.time - t.time), u = t[n], v = r[n];
                    0 > s && (s = 0), s > 1 && (s = 1), "pos" === n ? this.interpolationType === THREE.AnimationHandler.LINEAR ? (c.x = u[0] + (v[0] - u[0]) * s, c.y = u[1] + (v[1] - u[1]) * s, c.z = u[2] + (v[2] - u[2]) * s, t = this.weight / (this.weight + p.positionWeight), k.position.lerp(c, t), p.positionWeight += this.weight) : (this.interpolationType === THREE.AnimationHandler.CATMULLROM || this.interpolationType === THREE.AnimationHandler.CATMULLROM_FORWARD) && (a[0] = this.getPrevKeyWith("pos", f, t.index - 1).pos, a[1] = u, a[2] = v, a[3] = this.getNextKeyWith("pos", f, r.index + 1).pos, s = .33 * s + .33, r = e(a, s), t = this.weight / (this.weight + p.positionWeight), p.positionWeight += this.weight, n = k.position, n.x += (r[0] - n.x) * t, n.y += (r[1] - n.y) * t, n.z += (r[2] - n.z) * t, this.interpolationType === THREE.AnimationHandler.CATMULLROM_FORWARD && (s = e(a, 1.01 * s), b.set(s[0], s[1], s[2]), b.sub(n), b.y = 0, b.normalize(), s = Math.atan2(b.x, b.z), k.rotation.set(0, s, 0))) : "rot" === n ? (THREE.Quaternion.slerp(u, v, d, s), 0 === p.quaternionWeight ? (k.quaternion.copy(d), p.quaternionWeight = this.weight) : (t = this.weight / (this.weight + p.quaternionWeight), THREE.Quaternion.slerp(k.quaternion, d, k.quaternion, t), p.quaternionWeight += this.weight)) : "scl" === n && (c.x = u[0] + (v[0] - u[0]) * s, c.y = u[1] + (v[1] - u[1]) * s, c.z = u[2] + (v[2] - u[2]) * s, t = this.weight / (this.weight + p.scaleWeight), k.scale.lerp(c, t), p.scaleWeight += this.weight)
                }
                return !0
            }
        }
    }(), getNextKeyWith: function (a, b, c) {
        var d = this.data.hierarchy[b].keys;
        for (c = this.interpolationType === THREE.AnimationHandler.CATMULLROM || this.interpolationType === THREE.AnimationHandler.CATMULLROM_FORWARD ? c < d.length - 1 ? c : d.length - 1 : c % d.length; c < d.length; c++)if (void 0 !== d[c][a])return d[c];
        return this.data.hierarchy[b].keys[0]
    }, getPrevKeyWith: function (a, b, c) {
        var d = this.data.hierarchy[b].keys;
        for (c = this.interpolationType === THREE.AnimationHandler.CATMULLROM || this.interpolationType === THREE.AnimationHandler.CATMULLROM_FORWARD ? c > 0 ? c : 0 : c >= 0 ? c : c + d.length; c >= 0; c--)if (void 0 !== d[c][a])return d[c];
        return this.data.hierarchy[b].keys[d.length - 1]
    }
},THREE.KeyFrameAnimation = function (a) {
    this.root = a.node, this.data = THREE.AnimationHandler.init(a), this.hierarchy = THREE.AnimationHandler.parse(this.root), this.currentTime = 0, this.timeScale = .001, this.isPlaying = !1, this.loop = this.isPaused = !0, a = 0;
    for (var b = this.hierarchy.length; b > a; a++) {
        var c = this.data.hierarchy[a].sids, d = this.hierarchy[a];
        if (this.data.hierarchy[a].keys.length && c) {
            for (var e = 0; e < c.length; e++) {
                var f = c[e], g = this.getNextKeyWith(f, a, 0);
                g && g.apply(f)
            }
            d.matrixAutoUpdate = !1, this.data.hierarchy[a].node.updateMatrix(), d.matrixWorldNeedsUpdate = !0
        }
    }
},THREE.KeyFrameAnimation.prototype = {
    constructor: THREE.KeyFrameAnimation, play: function (a) {
        if (this.currentTime = void 0 !== a ? a : 0, !1 === this.isPlaying) {
            this.isPlaying = !0;
            var c, d, b = this.hierarchy.length;
            for (a = 0; b > a; a++)c = this.hierarchy[a], d = this.data.hierarchy[a], void 0 === d.animationCache && (d.animationCache = {}, d.animationCache.prevKey = null, d.animationCache.nextKey = null, d.animationCache.originalMatrix = c.matrix), c = this.data.hierarchy[a].keys, c.length && (d.animationCache.prevKey = c[0], d.animationCache.nextKey = c[1], this.startTime = Math.min(c[0].time, this.startTime), this.endTime = Math.max(c[c.length - 1].time, this.endTime));
            this.update(0)
        }
        this.isPaused = !1, THREE.AnimationHandler.play(this)
    }, stop: function () {
        this.isPaused = this.isPlaying = !1, THREE.AnimationHandler.stop(this);
        for (var a = 0; a < this.data.hierarchy.length; a++) {
            var b = this.hierarchy[a], c = this.data.hierarchy[a];
            if (void 0 !== c.animationCache) {
                var d = c.animationCache.originalMatrix;
                d.copy(b.matrix), b.matrix = d, delete c.animationCache
            }
        }
    }, update: function (a) {
        if (!1 !== this.isPlaying) {
            this.currentTime += a * this.timeScale, a = this.data.length, !0 === this.loop && this.currentTime > a && (this.currentTime %= a), this.currentTime = Math.min(this.currentTime, a), a = 0;
            for (var b = this.hierarchy.length; b > a; a++) {
                var c = this.hierarchy[a], d = this.data.hierarchy[a], e = d.keys, d = d.animationCache;
                if (e.length) {
                    var f = d.prevKey, g = d.nextKey;
                    if (g.time <= this.currentTime) {
                        for (; g.time < this.currentTime && g.index > f.index;)f = g, g = e[f.index + 1];
                        d.prevKey = f, d.nextKey = g
                    }
                    g.time >= this.currentTime ? f.interpolate(g, this.currentTime) : f.interpolate(g, g.time), this.data.hierarchy[a].node.updateMatrix(), c.matrixWorldNeedsUpdate = !0
                }
            }
        }
    }, getNextKeyWith: function (a, b, c) {
        for (b = this.data.hierarchy[b].keys, c %= b.length; c < b.length; c++)if (b[c].hasTarget(a))return b[c];
        return b[0]
    }, getPrevKeyWith: function (a, b, c) {
        for (b = this.data.hierarchy[b].keys, c = c >= 0 ? c : c + b.length; c >= 0; c--)if (b[c].hasTarget(a))return b[c];
        return b[b.length - 1]
    }
},THREE.MorphAnimation = function (a) {
    this.mesh = a, this.frames = a.morphTargetInfluences.length, this.currentTime = 0, this.duration = 1e3, this.loop = !0, this.currentFrame = this.lastFrame = 0, this.isPlaying = !1
},THREE.MorphAnimation.prototype = {
    constructor: THREE.MorphAnimation, play: function () {
        this.isPlaying = !0
    }, pause: function () {
        this.isPlaying = !1
    }, update: function (a) {
        if (!1 !== this.isPlaying) {
            this.currentTime += a, !0 === this.loop && this.currentTime > this.duration && (this.currentTime %= this.duration), this.currentTime = Math.min(this.currentTime, this.duration), a = this.duration / this.frames;
            var b = Math.floor(this.currentTime / a), c = this.mesh.morphTargetInfluences;
            b != this.currentFrame && (c[this.lastFrame] = 0, c[this.currentFrame] = 1, c[b] = 0, this.lastFrame = this.currentFrame, this.currentFrame = b), c[b] = this.currentTime % a / a, c[this.lastFrame] = 1 - c[b]
        }
    }
},THREE.BoxGeometry = function (a, b, c, d, e, f) {
    function g(a, b, c, d, e, f, g, s) {
        var u, v = h.widthSegments, x = h.heightSegments, D = e / 2, w = f / 2, y = h.vertices.length;
        "x" === a && "y" === b || "y" === a && "x" === b ? u = "z" : "x" === a && "z" === b || "z" === a && "x" === b ? (u = "y", x = h.depthSegments) : ("z" === a && "y" === b || "y" === a && "z" === b) && (u = "x", v = h.depthSegments);
        var A = v + 1, E = x + 1, G = e / v, F = f / x, z = new THREE.Vector3;
        for (z[u] = g > 0 ? 1 : -1, e = 0; E > e; e++)for (f = 0; A > f; f++) {
            var I = new THREE.Vector3;
            I[a] = (f * G - D) * c, I[b] = (e * F - w) * d, I[u] = g, h.vertices.push(I)
        }
        for (e = 0; x > e; e++)for (f = 0; v > f; f++)w = f + A * e, a = f + A * (e + 1), b = f + 1 + A * (e + 1), c = f + 1 + A * e, d = new THREE.Vector2(f / v, 1 - e / x), g = new THREE.Vector2(f / v, 1 - (e + 1) / x), u = new THREE.Vector2((f + 1) / v, 1 - (e + 1) / x), D = new THREE.Vector2((f + 1) / v, 1 - e / x), w = new THREE.Face3(w + y, a + y, c + y), w.normal.copy(z), w.vertexNormals.push(z.clone(), z.clone(), z.clone()), w.materialIndex = s, h.faces.push(w), h.faceVertexUvs[0].push([d, g, D]), w = new THREE.Face3(a + y, b + y, c + y), w.normal.copy(z), w.vertexNormals.push(z.clone(), z.clone(), z.clone()), w.materialIndex = s, h.faces.push(w), h.faceVertexUvs[0].push([g.clone(), u, D.clone()])
    }

    THREE.Geometry.call(this), this.type = "BoxGeometry", this.parameters = {
        width: a,
        height: b,
        depth: c,
        widthSegments: d,
        heightSegments: e,
        depthSegments: f
    }, this.widthSegments = d || 1, this.heightSegments = e || 1, this.depthSegments = f || 1;
    var h = this;
    d = a / 2, e = b / 2, f = c / 2, g("z", "y", -1, -1, c, b, d, 0), g("z", "y", 1, -1, c, b, -d, 1), g("x", "z", 1, 1, a, c, e, 2), g("x", "z", 1, -1, a, c, -e, 3), g("x", "y", 1, -1, a, b, f, 4), g("x", "y", -1, -1, a, b, -f, 5), this.mergeVertices()
},THREE.BoxGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.BoxGeometry.prototype.constructor = THREE.BoxGeometry,THREE.CircleGeometry = function (a, b, c, d) {
    THREE.Geometry.call(this), this.type = "CircleGeometry", this.parameters = {
        radius: a,
        segments: b,
        thetaStart: c,
        thetaLength: d
    }, a = a || 50, b = void 0 !== b ? Math.max(3, b) : 8, c = void 0 !== c ? c : 0, d = void 0 !== d ? d : 2 * Math.PI;
    var e, f = [];
    e = new THREE.Vector3;
    var g = new THREE.Vector2(.5, .5);
    for (this.vertices.push(e), f.push(g), e = 0; b >= e; e++) {
        var h = new THREE.Vector3, k = c + e / b * d;
        h.x = a * Math.cos(k), h.y = a * Math.sin(k), this.vertices.push(h), f.push(new THREE.Vector2((h.x / a + 1) / 2, (h.y / a + 1) / 2))
    }
    for (c = new THREE.Vector3(0, 0, 1), e = 1; b >= e; e++)this.faces.push(new THREE.Face3(e, e + 1, 0, [c.clone(), c.clone(), c.clone()])), this.faceVertexUvs[0].push([f[e].clone(), f[e + 1].clone(), g.clone()]);
    this.computeFaceNormals(), this.boundingSphere = new THREE.Sphere(new THREE.Vector3, a)
},THREE.CircleGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.CircleGeometry.prototype.constructor = THREE.CircleGeometry,THREE.CubeGeometry = function (a, b, c, d, e, f) {
    return THREE.warn("THREE.CubeGeometry has been renamed to THREE.BoxGeometry."), new THREE.BoxGeometry(a, b, c, d, e, f)
},THREE.CylinderGeometry = function (a, b, c, d, e, f, g, h) {
    THREE.Geometry.call(this), this.type = "CylinderGeometry", this.parameters = {
        radiusTop: a,
        radiusBottom: b,
        height: c,
        radialSegments: d,
        heightSegments: e,
        openEnded: f,
        thetaStart: g,
        thetaLength: h
    }, a = void 0 !== a ? a : 20, b = void 0 !== b ? b : 20, c = void 0 !== c ? c : 100, d = d || 8, e = e || 1, f = void 0 !== f ? f : !1, g = void 0 !== g ? g : 0, h = void 0 !== h ? h : 2 * Math.PI;
    var l, p, k = c / 2, q = [], n = [];
    for (p = 0; e >= p; p++) {
        var t = [], r = [], s = p / e, u = s * (b - a) + a;
        for (l = 0; d >= l; l++) {
            var v = l / d, x = new THREE.Vector3;
            x.x = u * Math.sin(v * h + g), x.y = -s * c + k, x.z = u * Math.cos(v * h + g), this.vertices.push(x), t.push(this.vertices.length - 1), r.push(new THREE.Vector2(v, 1 - s))
        }
        q.push(t), n.push(r)
    }
    for (c = (b - a) / c, l = 0; d > l; l++)for (0 !== a ? (g = this.vertices[q[0][l]].clone(), h = this.vertices[q[0][l + 1]].clone()) : (g = this.vertices[q[1][l]].clone(), h = this.vertices[q[1][l + 1]].clone()), g.setY(Math.sqrt(g.x * g.x + g.z * g.z) * c).normalize(), h.setY(Math.sqrt(h.x * h.x + h.z * h.z) * c).normalize(), p = 0; e > p; p++) {
        var t = q[p][l], r = q[p + 1][l], s = q[p + 1][l + 1], u = q[p][l + 1], v = g.clone(), x = g.clone(), D = h.clone(), w = h.clone(), y = n[p][l].clone(), A = n[p + 1][l].clone(), E = n[p + 1][l + 1].clone(), G = n[p][l + 1].clone();
        this.faces.push(new THREE.Face3(t, r, u, [v, x, w])), this.faceVertexUvs[0].push([y, A, G]), this.faces.push(new THREE.Face3(r, s, u, [x.clone(), D, w.clone()])), this.faceVertexUvs[0].push([A.clone(), E, G.clone()])
    }
    if (!1 === f && a > 0)for (this.vertices.push(new THREE.Vector3(0, k, 0)), l = 0; d > l; l++)t = q[0][l], r = q[0][l + 1], s = this.vertices.length - 1, v = new THREE.Vector3(0, 1, 0), x = new THREE.Vector3(0, 1, 0), D = new THREE.Vector3(0, 1, 0), y = n[0][l].clone(), A = n[0][l + 1].clone(), E = new THREE.Vector2(A.x, 0), this.faces.push(new THREE.Face3(t, r, s, [v, x, D])), this.faceVertexUvs[0].push([y, A, E]);
    if (!1 === f && b > 0)for (this.vertices.push(new THREE.Vector3(0, -k, 0)), l = 0; d > l; l++)t = q[e][l + 1], r = q[e][l], s = this.vertices.length - 1, v = new THREE.Vector3(0, -1, 0), x = new THREE.Vector3(0, -1, 0), D = new THREE.Vector3(0, -1, 0), y = n[e][l + 1].clone(), A = n[e][l].clone(), E = new THREE.Vector2(A.x, 1), this.faces.push(new THREE.Face3(t, r, s, [v, x, D])), this.faceVertexUvs[0].push([y, A, E]);
    this.computeFaceNormals()
},THREE.CylinderGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.CylinderGeometry.prototype.constructor = THREE.CylinderGeometry,THREE.ExtrudeGeometry = function (a, b) {
    "undefined" != typeof a && (THREE.Geometry.call(this), this.type = "ExtrudeGeometry", a = a instanceof Array ? a : [a], this.addShapeList(a, b), this.computeFaceNormals())
},THREE.ExtrudeGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.ExtrudeGeometry.prototype.constructor = THREE.ExtrudeGeometry,THREE.ExtrudeGeometry.prototype.addShapeList = function (a, b) {
    for (var c = a.length, d = 0; c > d; d++)this.addShape(a[d], b)
},THREE.ExtrudeGeometry.prototype.addShape = function (a, b) {
    function c(a, b, c) {
        return b || THREE.error("THREE.ExtrudeGeometry: vec does not exist"), b.clone().multiplyScalar(c).add(a)
    }

    function d(a, b, c) {
        var d = 1, d = a.x - b.x, e = a.y - b.y, f = c.x - a.x, g = c.y - a.y, h = d * d + e * e;
        if (1e-10 < Math.abs(d * g - e * f)) {
            var k = Math.sqrt(h), l = Math.sqrt(f * f + g * g), h = b.x - e / k;
            if (b = b.y + d / k, f = ((c.x - g / l - h) * g - (c.y + f / l - b) * f) / (d * g - e * f), c = h + d * f - a.x, a = b + e * f - a.y, d = c * c + a * a, 2 >= d)return new THREE.Vector2(c, a);
            d = Math.sqrt(d / 2)
        } else a = !1, d > 1e-10 ? f > 1e-10 && (a = !0) : -1e-10 > d ? -1e-10 > f && (a = !0) : Math.sign(e) == Math.sign(g) && (a = !0), a ? (c = -e, a = d, d = Math.sqrt(h)) : (c = d, a = e, d = Math.sqrt(h / 2));
        return new THREE.Vector2(c / d, a / d)
    }

    function e(a, b) {
        var c, d;
        for (O = a.length; 0 <= --O;) {
            c = O, d = O - 1, 0 > d && (d = a.length - 1);
            for (var e = 0, f = t + 2 * p, e = 0; f > e; e++) {
                var g = oa * e, h = oa * (e + 1), k = b + c + g, g = b + d + g, l = b + d + h, h = b + c + h, k = k + U, g = g + U, l = l + U, h = h + U;
                I.faces.push(new THREE.Face3(k, g, h, null, null, x)), I.faces.push(new THREE.Face3(g, l, h, null, null, x)), k = D.generateSideWallUV(I, k, g, l, h), I.faceVertexUvs[0].push([k[0], k[1], k[3]]), I.faceVertexUvs[0].push([k[1], k[2], k[3]])
            }
        }
    }

    function f(a, b, c) {
        I.vertices.push(new THREE.Vector3(a, b, c))
    }

    function g(a, b, c) {
        a += U, b += U, c += U, I.faces.push(new THREE.Face3(a, b, c, null, null, v)), a = D.generateTopUV(I, a, b, c), I.faceVertexUvs[0].push(a)
    }

    var s, w, y, A, E, h = void 0 !== b.amount ? b.amount : 100, k = void 0 !== b.bevelThickness ? b.bevelThickness : 6, l = void 0 !== b.bevelSize ? b.bevelSize : k - 2, p = void 0 !== b.bevelSegments ? b.bevelSegments : 3, q = void 0 !== b.bevelEnabled ? b.bevelEnabled : !0, n = void 0 !== b.curveSegments ? b.curveSegments : 12, t = void 0 !== b.steps ? b.steps : 1, r = b.extrudePath, u = !1, v = b.material, x = b.extrudeMaterial, D = void 0 !== b.UVGenerator ? b.UVGenerator : THREE.ExtrudeGeometry.WorldUVGenerator;
    r && (s = r.getSpacedPoints(t), u = !0, q = !1, w = void 0 !== b.frames ? b.frames : new THREE.TubeGeometry.FrenetFrames(r, t, !1), y = new THREE.Vector3, A = new THREE.Vector3, E = new THREE.Vector3), q || (l = k = p = 0);
    var G, F, z, I = this, U = this.vertices.length, r = a.extractPoints(n), n = r.shape, M = r.holes;
    if (r = !THREE.Shape.Utils.isClockWise(n)) {
        for (n = n.reverse(), F = 0, z = M.length; z > F; F++)G = M[F], THREE.Shape.Utils.isClockWise(G) && (M[F] = G.reverse());
        r = !1
    }
    var H = THREE.Shape.Utils.triangulateShape(n, M), L = n;
    for (F = 0, z = M.length; z > F; F++)G = M[F], n = n.concat(G);
    var P, N, R, V, J, ja, oa = n.length, ha = H.length, r = [], O = 0;
    for (R = L.length, P = R - 1, N = O + 1; R > O; O++, P++, N++)P === R && (P = 0), N === R && (N = 0), r[O] = d(L[O], L[P], L[N]);
    var ba, ca = [], qa = r.concat();
    for (F = 0, z = M.length; z > F; F++) {
        for (G = M[F], ba = [], O = 0, R = G.length, P = R - 1, N = O + 1; R > O; O++, P++, N++)P === R && (P = 0), N === R && (N = 0), ba[O] = d(G[O], G[P], G[N]);
        ca.push(ba), qa = qa.concat(ba)
    }
    for (P = 0; p > P; P++) {
        for (R = P / p, V = k * (1 - R), N = l * Math.sin(R * Math.PI / 2), O = 0, R = L.length; R > O; O++)J = c(L[O], r[O], N), f(J.x, J.y, -V);
        for (F = 0, z = M.length; z > F; F++)for (G = M[F], ba = ca[F], O = 0, R = G.length; R > O; O++)J = c(G[O], ba[O], N), f(J.x, J.y, -V)
    }
    for (N = l, O = 0; oa > O; O++)J = q ? c(n[O], qa[O], N) : n[O], u ? (A.copy(w.normals[0]).multiplyScalar(J.x), y.copy(w.binormals[0]).multiplyScalar(J.y), E.copy(s[0]).add(A).add(y), f(E.x, E.y, E.z)) : f(J.x, J.y, 0);
    for (R = 1; t >= R; R++)for (O = 0; oa > O; O++)J = q ? c(n[O], qa[O], N) : n[O], u ? (A.copy(w.normals[R]).multiplyScalar(J.x), y.copy(w.binormals[R]).multiplyScalar(J.y), E.copy(s[R]).add(A).add(y), f(E.x, E.y, E.z)) : f(J.x, J.y, h / t * R);
    for (P = p - 1; P >= 0; P--) {
        for (R = P / p, V = k * (1 - R), N = l * Math.sin(R * Math.PI / 2), O = 0, R = L.length; R > O; O++)J = c(L[O], r[O], N), f(J.x, J.y, h + V);
        for (F = 0, z = M.length; z > F; F++)for (G = M[F], ba = ca[F], O = 0, R = G.length; R > O; O++)J = c(G[O], ba[O], N), u ? f(J.x, J.y + s[t - 1].y, s[t - 1].x + V) : f(J.x, J.y, h + V)
    }
    !function () {
        if (q) {
            var a;
            for (a = 0 * oa, O = 0; ha > O; O++)ja = H[O], g(ja[2] + a, ja[1] + a, ja[0] + a);
            for (a = t + 2 * p, a *= oa, O = 0; ha > O; O++)ja = H[O], g(ja[0] + a, ja[1] + a, ja[2] + a)
        } else {
            for (O = 0; ha > O; O++)ja = H[O], g(ja[2], ja[1], ja[0]);
            for (O = 0; ha > O; O++)ja = H[O], g(ja[0] + oa * t, ja[1] + oa * t, ja[2] + oa * t)
        }
    }(), function () {
        var a = 0;
        for (e(L, a), a += L.length, F = 0, z = M.length; z > F; F++)G = M[F], e(G, a), a += G.length
    }()
},THREE.ExtrudeGeometry.WorldUVGenerator = {
    generateTopUV: function (a, b, c, d) {
        return a = a.vertices, b = a[b], c = a[c], d = a[d], [new THREE.Vector2(b.x, b.y), new THREE.Vector2(c.x, c.y), new THREE.Vector2(d.x, d.y)]
    }, generateSideWallUV: function (a, b, c, d, e) {
        return a = a.vertices, b = a[b], c = a[c], d = a[d], e = a[e], .01 > Math.abs(b.y - c.y) ? [new THREE.Vector2(b.x, 1 - b.z), new THREE.Vector2(c.x, 1 - c.z), new THREE.Vector2(d.x, 1 - d.z), new THREE.Vector2(e.x, 1 - e.z)] : [new THREE.Vector2(b.y, 1 - b.z), new THREE.Vector2(c.y, 1 - c.z), new THREE.Vector2(d.y, 1 - d.z), new THREE.Vector2(e.y, 1 - e.z)]
    }
},THREE.ShapeGeometry = function (a, b) {
    THREE.Geometry.call(this), this.type = "ShapeGeometry", !1 == a instanceof Array && (a = [a]), this.addShapeList(a, b), this.computeFaceNormals()
},THREE.ShapeGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.ShapeGeometry.prototype.constructor = THREE.ShapeGeometry,THREE.ShapeGeometry.prototype.addShapeList = function (a, b) {
    for (var c = 0, d = a.length; d > c; c++)this.addShape(a[c], b);
    return this
},THREE.ShapeGeometry.prototype.addShape = function (a, b) {
    void 0 === b && (b = {});
    var e, f, g, c = b.material, d = void 0 === b.UVGenerator ? THREE.ExtrudeGeometry.WorldUVGenerator : b.UVGenerator, h = this.vertices.length;
    e = a.extractPoints(void 0 !== b.curveSegments ? b.curveSegments : 12);
    var k = e.shape, l = e.holes;
    if (!THREE.Shape.Utils.isClockWise(k))for (k = k.reverse(), e = 0, f = l.length; f > e; e++)g = l[e], THREE.Shape.Utils.isClockWise(g) && (l[e] = g.reverse());
    var p = THREE.Shape.Utils.triangulateShape(k, l);
    for (e = 0, f = l.length; f > e; e++)g = l[e], k = k.concat(g);
    for (l = k.length, f = p.length, e = 0; l > e; e++)g = k[e], this.vertices.push(new THREE.Vector3(g.x, g.y, 0));
    for (e = 0; f > e; e++)l = p[e], k = l[0] + h, g = l[1] + h, l = l[2] + h, this.faces.push(new THREE.Face3(k, g, l, null, null, c)), this.faceVertexUvs[0].push(d.generateTopUV(this, k, g, l))
},THREE.LatheGeometry = function (a, b, c, d) {
    THREE.Geometry.call(this), this.type = "LatheGeometry", this.parameters = {
        points: a,
        segments: b,
        phiStart: c,
        phiLength: d
    }, b = b || 12, c = c || 0, d = d || 2 * Math.PI;
    for (var e = 1 / (a.length - 1), f = 1 / b, g = 0, h = b; h >= g; g++)for (var k = c + g * f * d, l = Math.cos(k), p = Math.sin(k), k = 0, q = a.length; q > k; k++) {
        var n = a[k], t = new THREE.Vector3;
        t.x = l * n.x - p * n.y, t.y = p * n.x + l * n.y, t.z = n.z, this.vertices.push(t)
    }
    for (c = a.length, g = 0, h = b; h > g; g++)for (k = 0, q = a.length - 1; q > k; k++) {
        b = p = k + c * g, d = p + c;
        var l = p + 1 + c, p = p + 1, n = g * f, t = k * e, r = n + f, s = t + e;
        this.faces.push(new THREE.Face3(b, d, p)), this.faceVertexUvs[0].push([new THREE.Vector2(n, t), new THREE.Vector2(r, t), new THREE.Vector2(n, s)]), this.faces.push(new THREE.Face3(d, l, p)), this.faceVertexUvs[0].push([new THREE.Vector2(r, t), new THREE.Vector2(r, s), new THREE.Vector2(n, s)])
    }
    this.mergeVertices(), this.computeFaceNormals(), this.computeVertexNormals()
},THREE.LatheGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.LatheGeometry.prototype.constructor = THREE.LatheGeometry,THREE.PlaneGeometry = function (a, b, c, d) {
    console.info("THREE.PlaneGeometry: Consider using THREE.PlaneBufferGeometry for lower memory footprint."), THREE.Geometry.call(this), this.type = "PlaneGeometry", this.parameters = {
        width: a,
        height: b,
        widthSegments: c,
        heightSegments: d
    }, this.fromBufferGeometry(new THREE.PlaneBufferGeometry(a, b, c, d))
},THREE.PlaneGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.PlaneGeometry.prototype.constructor = THREE.PlaneGeometry,THREE.PlaneBufferGeometry = function (a, b, c, d) {
    THREE.BufferGeometry.call(this), this.type = "PlaneBufferGeometry", this.parameters = {
        width: a,
        height: b,
        widthSegments: c,
        heightSegments: d
    };
    var e = a / 2, f = b / 2;
    c = c || 1, d = d || 1;
    var g = c + 1, h = d + 1, k = a / c, l = b / d;
    b = new Float32Array(g * h * 3), a = new Float32Array(g * h * 3);
    for (var p = new Float32Array(g * h * 2), q = 0, n = 0, t = 0; h > t; t++)for (var r = t * l - f, s = 0; g > s; s++)b[q] = s * k - e, b[q + 1] = -r, a[q + 2] = 1, p[n] = s / c, p[n + 1] = 1 - t / d, q += 3, n += 2;
    for (q = 0, e = new (65535 < b.length / 3 ? Uint32Array : Uint16Array)(c * d * 6), t = 0; d > t; t++)for (s = 0; c > s; s++)f = s + g * (t + 1), h = s + 1 + g * (t + 1), k = s + 1 + g * t, e[q] = s + g * t, e[q + 1] = f, e[q + 2] = k, e[q + 3] = f, e[q + 4] = h, e[q + 5] = k, q += 6;
    this.addAttribute("index", new THREE.BufferAttribute(e, 1)), this.addAttribute("position", new THREE.BufferAttribute(b, 3)), this.addAttribute("normal", new THREE.BufferAttribute(a, 3)), this.addAttribute("uv", new THREE.BufferAttribute(p, 2))
},THREE.PlaneBufferGeometry.prototype = Object.create(THREE.BufferGeometry.prototype),THREE.PlaneBufferGeometry.prototype.constructor = THREE.PlaneBufferGeometry,THREE.RingGeometry = function (a, b, c, d, e, f) {
    THREE.Geometry.call(this), this.type = "RingGeometry", this.parameters = {
        innerRadius: a,
        outerRadius: b,
        thetaSegments: c,
        phiSegments: d,
        thetaStart: e,
        thetaLength: f
    }, a = a || 0, b = b || 50, e = void 0 !== e ? e : 0, f = void 0 !== f ? f : 2 * Math.PI, c = void 0 !== c ? Math.max(3, c) : 8, d = void 0 !== d ? Math.max(1, d) : 8;
    var g, h = [], k = a, l = (b - a) / d;
    for (a = 0; d + 1 > a; a++) {
        for (g = 0; c + 1 > g; g++) {
            var p = new THREE.Vector3, q = e + g / c * f;
            p.x = k * Math.cos(q), p.y = k * Math.sin(q), this.vertices.push(p), h.push(new THREE.Vector2((p.x / b + 1) / 2, (p.y / b + 1) / 2))
        }
        k += l
    }
    for (b = new THREE.Vector3(0, 0, 1), a = 0; d > a; a++)for (e = a * (c + 1), g = 0; c > g; g++)f = q = g + e, l = q + c + 1, p = q + c + 2, this.faces.push(new THREE.Face3(f, l, p, [b.clone(), b.clone(), b.clone()])), this.faceVertexUvs[0].push([h[f].clone(), h[l].clone(), h[p].clone()]), f = q, l = q + c + 2, p = q + 1, this.faces.push(new THREE.Face3(f, l, p, [b.clone(), b.clone(), b.clone()])), this.faceVertexUvs[0].push([h[f].clone(), h[l].clone(), h[p].clone()]);
    this.computeFaceNormals(), this.boundingSphere = new THREE.Sphere(new THREE.Vector3, k)
},THREE.RingGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.RingGeometry.prototype.constructor = THREE.RingGeometry,THREE.SphereGeometry = function (a, b, c, d, e, f, g) {
    THREE.Geometry.call(this), this.type = "SphereGeometry", this.parameters = {
        radius: a,
        widthSegments: b,
        heightSegments: c,
        phiStart: d,
        phiLength: e,
        thetaStart: f,
        thetaLength: g
    }, a = a || 50, b = Math.max(3, Math.floor(b) || 8), c = Math.max(2, Math.floor(c) || 6), d = void 0 !== d ? d : 0, e = void 0 !== e ? e : 2 * Math.PI, f = void 0 !== f ? f : 0, g = void 0 !== g ? g : Math.PI;
    var h, k, l = [], p = [];
    for (k = 0; c >= k; k++) {
        var q = [], n = [];
        for (h = 0; b >= h; h++) {
            var t = h / b, r = k / c, s = new THREE.Vector3;
            s.x = -a * Math.cos(d + t * e) * Math.sin(f + r * g), s.y = a * Math.cos(f + r * g), s.z = a * Math.sin(d + t * e) * Math.sin(f + r * g), this.vertices.push(s), q.push(this.vertices.length - 1), n.push(new THREE.Vector2(t, 1 - r))
        }
        l.push(q), p.push(n)
    }
    for (k = 0; c > k; k++)for (h = 0; b > h; h++) {
        d = l[k][h + 1], e = l[k][h], f = l[k + 1][h], g = l[k + 1][h + 1];
        var q = this.vertices[d].clone().normalize(), n = this.vertices[e].clone().normalize(), t = this.vertices[f].clone().normalize(), r = this.vertices[g].clone().normalize(), s = p[k][h + 1].clone(), u = p[k][h].clone(), v = p[k + 1][h].clone(), x = p[k + 1][h + 1].clone();
        Math.abs(this.vertices[d].y) === a ? (s.x = (s.x + u.x) / 2, this.faces.push(new THREE.Face3(d, f, g, [q, t, r])), this.faceVertexUvs[0].push([s, v, x])) : Math.abs(this.vertices[f].y) === a ? (v.x = (v.x + x.x) / 2, this.faces.push(new THREE.Face3(d, e, f, [q, n, t])), this.faceVertexUvs[0].push([s, u, v])) : (this.faces.push(new THREE.Face3(d, e, g, [q, n, r])), this.faceVertexUvs[0].push([s, u, x]), this.faces.push(new THREE.Face3(e, f, g, [n.clone(), t, r.clone()])), this.faceVertexUvs[0].push([u.clone(), v, x.clone()]))
    }
    this.computeFaceNormals(), this.boundingSphere = new THREE.Sphere(new THREE.Vector3, a)
},THREE.SphereGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.SphereGeometry.prototype.constructor = THREE.SphereGeometry,THREE.TextGeometry = function (a, b) {
    b = b || {};
    var c = THREE.FontUtils.generateShapes(a, b);
    b.amount = void 0 !== b.height ? b.height : 50, void 0 === b.bevelThickness && (b.bevelThickness = 10), void 0 === b.bevelSize && (b.bevelSize = 8), void 0 === b.bevelEnabled && (b.bevelEnabled = !1), THREE.ExtrudeGeometry.call(this, c, b), this.type = "TextGeometry"
},THREE.TextGeometry.prototype = Object.create(THREE.ExtrudeGeometry.prototype),THREE.TextGeometry.prototype.constructor = THREE.TextGeometry,THREE.TorusGeometry = function (a, b, c, d, e) {
    THREE.Geometry.call(this), this.type = "TorusGeometry", this.parameters = {
        radius: a,
        tube: b,
        radialSegments: c,
        tubularSegments: d,
        arc: e
    }, a = a || 100, b = b || 40, c = c || 8, d = d || 6, e = e || 2 * Math.PI;
    for (var f = new THREE.Vector3, g = [], h = [], k = 0; c >= k; k++)for (var l = 0; d >= l; l++) {
        var p = l / d * e, q = k / c * Math.PI * 2;
        f.x = a * Math.cos(p), f.y = a * Math.sin(p);
        var n = new THREE.Vector3;
        n.x = (a + b * Math.cos(q)) * Math.cos(p), n.y = (a + b * Math.cos(q)) * Math.sin(p), n.z = b * Math.sin(q), this.vertices.push(n), g.push(new THREE.Vector2(l / d, k / c)), h.push(n.clone().sub(f).normalize())
    }
    for (k = 1; c >= k; k++)for (l = 1; d >= l; l++)a = (d + 1) * k + l - 1, b = (d + 1) * (k - 1) + l - 1, e = (d + 1) * (k - 1) + l, f = (d + 1) * k + l, p = new THREE.Face3(a, b, f, [h[a].clone(), h[b].clone(), h[f].clone()]), this.faces.push(p), this.faceVertexUvs[0].push([g[a].clone(), g[b].clone(), g[f].clone()]), p = new THREE.Face3(b, e, f, [h[b].clone(), h[e].clone(), h[f].clone()]), this.faces.push(p), this.faceVertexUvs[0].push([g[b].clone(), g[e].clone(), g[f].clone()]);
    this.computeFaceNormals()
},THREE.TorusGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.TorusGeometry.prototype.constructor = THREE.TorusGeometry,THREE.TorusKnotGeometry = function (a, b, c, d, e, f, g) {
    function h(a, b, c, d, e) {
        var f = Math.cos(a), g = Math.sin(a);
        return a *= b / c, b = Math.cos(a), f *= d * (2 + b) * .5, g = d * (2 + b) * g * .5, d = e * d * Math.sin(a) * .5, new THREE.Vector3(f, g, d)
    }

    THREE.Geometry.call(this), this.type = "TorusKnotGeometry", this.parameters = {
        radius: a,
        tube: b,
        radialSegments: c,
        tubularSegments: d,
        p: e,
        q: f,
        heightScale: g
    }, a = a || 100, b = b || 40, c = c || 64, d = d || 8, e = e || 2, f = f || 3, g = g || 1;
    for (var k = Array(c), l = new THREE.Vector3, p = new THREE.Vector3, q = new THREE.Vector3, n = 0; c > n; ++n) {
        k[n] = Array(d);
        var t = n / c * 2 * e * Math.PI, r = h(t, f, e, a, g), t = h(t + .01, f, e, a, g);
        for (l.subVectors(t, r), p.addVectors(t, r), q.crossVectors(l, p), p.crossVectors(q, l), q.normalize(), p.normalize(), t = 0; d > t; ++t) {
            var s = t / d * 2 * Math.PI, u = -b * Math.cos(s), s = b * Math.sin(s), v = new THREE.Vector3;
            v.x = r.x + u * p.x + s * q.x, v.y = r.y + u * p.y + s * q.y, v.z = r.z + u * p.z + s * q.z, k[n][t] = this.vertices.push(v) - 1
        }
    }
    for (n = 0; c > n; ++n)for (t = 0; d > t; ++t)e = (n + 1) % c, f = (t + 1) % d, a = k[n][t], b = k[e][t], e = k[e][f], f = k[n][f], g = new THREE.Vector2(n / c, t / d), l = new THREE.Vector2((n + 1) / c, t / d), p = new THREE.Vector2((n + 1) / c, (t + 1) / d), q = new THREE.Vector2(n / c, (t + 1) / d), this.faces.push(new THREE.Face3(a, b, f)), this.faceVertexUvs[0].push([g, l, q]),
        this.faces.push(new THREE.Face3(b, e, f)), this.faceVertexUvs[0].push([l.clone(), p, q.clone()]);
    this.computeFaceNormals(), this.computeVertexNormals()
},THREE.TorusKnotGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.TorusKnotGeometry.prototype.constructor = THREE.TorusKnotGeometry,THREE.TubeGeometry = function (a, b, c, d, e, f) {
    THREE.Geometry.call(this), this.type = "TubeGeometry", this.parameters = {
        path: a,
        segments: b,
        radius: c,
        radialSegments: d,
        closed: e
    }, b = b || 64, c = c || 1, d = d || 8, e = e || !1, f = f || THREE.TubeGeometry.NoTaper;
    var h, k, p, q, n, t, r, u, v, x, g = [], l = b + 1, s = new THREE.Vector3;
    for (u = new THREE.TubeGeometry.FrenetFrames(a, b, e), v = u.normals, x = u.binormals, this.tangents = u.tangents, this.normals = v, this.binormals = x, u = 0; l > u; u++)for (g[u] = [], p = u / (l - 1), r = a.getPointAt(p), h = v[u], k = x[u], n = c * f(p), p = 0; d > p; p++)q = p / d * 2 * Math.PI, t = -n * Math.cos(q), q = n * Math.sin(q), s.copy(r), s.x += t * h.x + q * k.x, s.y += t * h.y + q * k.y, s.z += t * h.z + q * k.z, g[u][p] = this.vertices.push(new THREE.Vector3(s.x, s.y, s.z)) - 1;
    for (u = 0; b > u; u++)for (p = 0; d > p; p++)f = e ? (u + 1) % b : u + 1, l = (p + 1) % d, a = g[u][p], c = g[f][p], f = g[f][l], l = g[u][l], s = new THREE.Vector2(u / b, p / d), v = new THREE.Vector2((u + 1) / b, p / d), x = new THREE.Vector2((u + 1) / b, (p + 1) / d), h = new THREE.Vector2(u / b, (p + 1) / d), this.faces.push(new THREE.Face3(a, c, l)), this.faceVertexUvs[0].push([s, v, h]), this.faces.push(new THREE.Face3(c, f, l)), this.faceVertexUvs[0].push([v.clone(), x, h.clone()]);
    this.computeFaceNormals(), this.computeVertexNormals()
},THREE.TubeGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.TubeGeometry.prototype.constructor = THREE.TubeGeometry,THREE.TubeGeometry.NoTaper = function (a) {
    return 1
},THREE.TubeGeometry.SinusoidalTaper = function (a) {
    return Math.sin(Math.PI * a)
},THREE.TubeGeometry.FrenetFrames = function (a, b, c) {
    var d = new THREE.Vector3, e = [], f = [], g = [], h = new THREE.Vector3, k = new THREE.Matrix4;
    b += 1;
    var l, p, q;
    for (this.tangents = e, this.normals = f, this.binormals = g, l = 0; b > l; l++)p = l / (b - 1), e[l] = a.getTangentAt(p), e[l].normalize();
    for (f[0] = new THREE.Vector3, g[0] = new THREE.Vector3, a = Number.MAX_VALUE, l = Math.abs(e[0].x), p = Math.abs(e[0].y), q = Math.abs(e[0].z), a >= l && (a = l, d.set(1, 0, 0)), a >= p && (a = p, d.set(0, 1, 0)), a >= q && d.set(0, 0, 1), h.crossVectors(e[0], d).normalize(), f[0].crossVectors(e[0], h), g[0].crossVectors(e[0], f[0]), l = 1; b > l; l++)f[l] = f[l - 1].clone(), g[l] = g[l - 1].clone(), h.crossVectors(e[l - 1], e[l]), 1e-4 < h.length() && (h.normalize(), d = Math.acos(THREE.Math.clamp(e[l - 1].dot(e[l]), -1, 1)), f[l].applyMatrix4(k.makeRotationAxis(h, d))), g[l].crossVectors(e[l], f[l]);
    if (c)for (d = Math.acos(THREE.Math.clamp(f[0].dot(f[b - 1]), -1, 1)), d /= b - 1, 0 < e[0].dot(h.crossVectors(f[0], f[b - 1])) && (d = -d), l = 1; b > l; l++)f[l].applyMatrix4(k.makeRotationAxis(e[l], d * l)), g[l].crossVectors(e[l], f[l])
},THREE.PolyhedronGeometry = function (a, b, c, d) {
    function e(a) {
        var b = a.normalize().clone();
        b.index = k.vertices.push(b) - 1;
        var c = Math.atan2(a.z, -a.x) / 2 / Math.PI + .5;
        return a = Math.atan2(-a.y, Math.sqrt(a.x * a.x + a.z * a.z)) / Math.PI + .5, b.uv = new THREE.Vector2(c, 1 - a), b
    }

    function f(a, b, c) {
        var d = new THREE.Face3(a.index, b.index, c.index, [a.clone(), b.clone(), c.clone()]);
        k.faces.push(d), u.copy(a).add(b).add(c).divideScalar(3), d = Math.atan2(u.z, -u.x), k.faceVertexUvs[0].push([h(a.uv, a, d), h(b.uv, b, d), h(c.uv, c, d)])
    }

    function g(a, b) {
        for (var c = Math.pow(2, b), d = e(k.vertices[a.a]), g = e(k.vertices[a.b]), h = e(k.vertices[a.c]), l = [], n = 0; c >= n; n++) {
            l[n] = [];
            for (var p = e(d.clone().lerp(h, n / c)), q = e(g.clone().lerp(h, n / c)), s = c - n, r = 0; s >= r; r++)l[n][r] = 0 == r && n == c ? p : e(p.clone().lerp(q, r / s))
        }
        for (n = 0; c > n; n++)for (r = 0; 2 * (c - n) - 1 > r; r++)d = Math.floor(r / 2), 0 == r % 2 ? f(l[n][d + 1], l[n + 1][d], l[n][d]) : f(l[n][d + 1], l[n + 1][d + 1], l[n + 1][d])
    }

    function h(a, b, c) {
        return 0 > c && 1 === a.x && (a = new THREE.Vector2(a.x - 1, a.y)), 0 === b.x && 0 === b.z && (a = new THREE.Vector2(c / 2 / Math.PI + .5, a.y)), a.clone()
    }

    THREE.Geometry.call(this), this.type = "PolyhedronGeometry", this.parameters = {
        vertices: a,
        indices: b,
        radius: c,
        detail: d
    }, c = c || 1, d = d || 0;
    for (var k = this, l = 0, p = a.length; p > l; l += 3)e(new THREE.Vector3(a[l], a[l + 1], a[l + 2]));
    a = this.vertices;
    for (var q = [], n = l = 0, p = b.length; p > l; l += 3, n++) {
        var t = a[b[l]], r = a[b[l + 1]], s = a[b[l + 2]];
        q[n] = new THREE.Face3(t.index, r.index, s.index, [t.clone(), r.clone(), s.clone()])
    }
    for (var u = new THREE.Vector3, l = 0, p = q.length; p > l; l++)g(q[l], d);
    for (l = 0, p = this.faceVertexUvs[0].length; p > l; l++)b = this.faceVertexUvs[0][l], d = b[0].x, a = b[1].x, q = b[2].x, n = Math.max(d, Math.max(a, q)), t = Math.min(d, Math.min(a, q)), n > .9 && .1 > t && (.2 > d && (b[0].x += 1), .2 > a && (b[1].x += 1), .2 > q && (b[2].x += 1));
    for (l = 0, p = this.vertices.length; p > l; l++)this.vertices[l].multiplyScalar(c);
    this.mergeVertices(), this.computeFaceNormals(), this.boundingSphere = new THREE.Sphere(new THREE.Vector3, c)
},THREE.PolyhedronGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.PolyhedronGeometry.prototype.constructor = THREE.PolyhedronGeometry,THREE.DodecahedronGeometry = function (a, b) {
    this.parameters = {radius: a, detail: b};
    var c = (1 + Math.sqrt(5)) / 2, d = 1 / c;
    THREE.PolyhedronGeometry.call(this, [-1, -1, -1, -1, -1, 1, -1, 1, -1, -1, 1, 1, 1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 0, -d, -c, 0, -d, c, 0, d, -c, 0, d, c, -d, -c, 0, -d, c, 0, d, -c, 0, d, c, 0, -c, 0, -d, c, 0, -d, -c, 0, d, c, 0, d], [3, 11, 7, 3, 7, 15, 3, 15, 13, 7, 19, 17, 7, 17, 6, 7, 6, 15, 17, 4, 8, 17, 8, 10, 17, 10, 6, 8, 0, 16, 8, 16, 2, 8, 2, 10, 0, 12, 1, 0, 1, 18, 0, 18, 16, 6, 10, 2, 6, 2, 13, 6, 13, 15, 2, 16, 18, 2, 18, 3, 2, 3, 13, 18, 1, 9, 18, 9, 11, 18, 11, 3, 4, 14, 12, 4, 12, 0, 4, 0, 8, 11, 9, 5, 11, 5, 19, 11, 19, 7, 19, 5, 14, 19, 14, 4, 19, 4, 17, 1, 12, 14, 1, 14, 5, 1, 5, 9], a, b)
},THREE.DodecahedronGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.DodecahedronGeometry.prototype.constructor = THREE.DodecahedronGeometry,THREE.IcosahedronGeometry = function (a, b) {
    var c = (1 + Math.sqrt(5)) / 2;
    THREE.PolyhedronGeometry.call(this, [-1, c, 0, 1, c, 0, -1, -c, 0, 1, -c, 0, 0, -1, c, 0, 1, c, 0, -1, -c, 0, 1, -c, c, 0, -1, c, 0, 1, -c, 0, -1, -c, 0, 1], [0, 11, 5, 0, 5, 1, 0, 1, 7, 0, 7, 10, 0, 10, 11, 1, 5, 9, 5, 11, 4, 11, 10, 2, 10, 7, 6, 7, 1, 8, 3, 9, 4, 3, 4, 2, 3, 2, 6, 3, 6, 8, 3, 8, 9, 4, 9, 5, 2, 4, 11, 6, 2, 10, 8, 6, 7, 9, 8, 1], a, b), this.type = "IcosahedronGeometry", this.parameters = {
        radius: a,
        detail: b
    }
},THREE.IcosahedronGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.IcosahedronGeometry.prototype.constructor = THREE.IcosahedronGeometry,THREE.OctahedronGeometry = function (a, b) {
    this.parameters = {
        radius: a,
        detail: b
    }, THREE.PolyhedronGeometry.call(this, [1, 0, 0, -1, 0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 1, 0, 0, -1], [0, 2, 4, 0, 4, 3, 0, 3, 5, 0, 5, 2, 1, 2, 5, 1, 5, 3, 1, 3, 4, 1, 4, 2], a, b), this.type = "OctahedronGeometry", this.parameters = {
        radius: a,
        detail: b
    }
},THREE.OctahedronGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.OctahedronGeometry.prototype.constructor = THREE.OctahedronGeometry,THREE.TetrahedronGeometry = function (a, b) {
    THREE.PolyhedronGeometry.call(this, [1, 1, 1, -1, -1, 1, -1, 1, -1, 1, -1, -1], [2, 1, 0, 0, 3, 2, 1, 3, 0, 2, 3, 1], a, b), this.type = "TetrahedronGeometry", this.parameters = {
        radius: a,
        detail: b
    }
},THREE.TetrahedronGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.TetrahedronGeometry.prototype.constructor = THREE.TetrahedronGeometry,THREE.ParametricGeometry = function (a, b, c) {
    THREE.Geometry.call(this), this.type = "ParametricGeometry", this.parameters = {func: a, slices: b, stacks: c};
    var g, h, k, l, d = this.vertices, e = this.faces, f = this.faceVertexUvs[0], p = b + 1;
    for (g = 0; c >= g; g++)for (l = g / c, h = 0; b >= h; h++)k = h / b, k = a(k, l), d.push(k);
    var q, n, t, r;
    for (g = 0; c > g; g++)for (h = 0; b > h; h++)a = g * p + h, d = g * p + h + 1, l = (g + 1) * p + h + 1, k = (g + 1) * p + h, q = new THREE.Vector2(h / b, g / c), n = new THREE.Vector2((h + 1) / b, g / c), t = new THREE.Vector2((h + 1) / b, (g + 1) / c), r = new THREE.Vector2(h / b, (g + 1) / c), e.push(new THREE.Face3(a, d, k)), f.push([q, n, r]), e.push(new THREE.Face3(d, l, k)), f.push([n.clone(), t, r.clone()]);
    this.computeFaceNormals(), this.computeVertexNormals()
},THREE.ParametricGeometry.prototype = Object.create(THREE.Geometry.prototype),THREE.ParametricGeometry.prototype.constructor = THREE.ParametricGeometry,THREE.AxisHelper = function (a) {
    a = a || 1;
    var b = new Float32Array([0, 0, 0, a, 0, 0, 0, 0, 0, 0, a, 0, 0, 0, 0, 0, 0, a]), c = new Float32Array([1, 0, 0, 1, .6, 0, 0, 1, 0, .6, 1, 0, 0, 0, 1, 0, .6, 1]);
    a = new THREE.BufferGeometry, a.addAttribute("position", new THREE.BufferAttribute(b, 3)), a.addAttribute("color", new THREE.BufferAttribute(c, 3)), b = new THREE.LineBasicMaterial({vertexColors: THREE.VertexColors}), THREE.Line.call(this, a, b, THREE.LinePieces)
},THREE.AxisHelper.prototype = Object.create(THREE.Line.prototype),THREE.AxisHelper.prototype.constructor = THREE.AxisHelper,THREE.ArrowHelper = function () {
    var a = new THREE.Geometry;
    a.vertices.push(new THREE.Vector3(0, 0, 0), new THREE.Vector3(0, 1, 0));
    var b = new THREE.CylinderGeometry(0, .5, 1, 5, 1);
    return b.applyMatrix((new THREE.Matrix4).makeTranslation(0, -.5, 0)), function (c, d, e, f, g, h) {
        THREE.Object3D.call(this), void 0 === f && (f = 16776960), void 0 === e && (e = 1), void 0 === g && (g = .2 * e), void 0 === h && (h = .2 * g), this.position.copy(d), this.line = new THREE.Line(a, new THREE.LineBasicMaterial({color: f})), this.line.matrixAutoUpdate = !1, this.add(this.line), this.cone = new THREE.Mesh(b, new THREE.MeshBasicMaterial({color: f})), this.cone.matrixAutoUpdate = !1, this.add(this.cone), this.setDirection(c), this.setLength(e, g, h)
    }
}(),THREE.ArrowHelper.prototype = Object.create(THREE.Object3D.prototype),THREE.ArrowHelper.prototype.constructor = THREE.ArrowHelper,THREE.ArrowHelper.prototype.setDirection = function () {
    var b, a = new THREE.Vector3;
    return function (c) {
        .99999 < c.y ? this.quaternion.set(0, 0, 0, 1) : -.99999 > c.y ? this.quaternion.set(1, 0, 0, 0) : (a.set(c.z, 0, -c.x).normalize(), b = Math.acos(c.y), this.quaternion.setFromAxisAngle(a, b))
    }
}(),THREE.ArrowHelper.prototype.setLength = function (a, b, c) {
    void 0 === b && (b = .2 * a), void 0 === c && (c = .2 * b), this.line.scale.set(1, a - b, 1), this.line.updateMatrix(), this.cone.scale.set(c, b, c), this.cone.position.y = a, this.cone.updateMatrix()
},THREE.ArrowHelper.prototype.setColor = function (a) {
    this.line.material.color.set(a), this.cone.material.color.set(a)
},THREE.BoxHelper = function (a) {
    var b = new THREE.BufferGeometry;
    b.addAttribute("position", new THREE.BufferAttribute(new Float32Array(72), 3)), THREE.Line.call(this, b, new THREE.LineBasicMaterial({color: 16776960}), THREE.LinePieces), void 0 !== a && this.update(a)
},THREE.BoxHelper.prototype = Object.create(THREE.Line.prototype),THREE.BoxHelper.prototype.constructor = THREE.BoxHelper,THREE.BoxHelper.prototype.update = function (a) {
    var b = a.geometry;
    null === b.boundingBox && b.computeBoundingBox();
    var c = b.boundingBox.min, b = b.boundingBox.max, d = this.geometry.attributes.position.array;
    d[0] = b.x, d[1] = b.y, d[2] = b.z, d[3] = c.x, d[4] = b.y, d[5] = b.z, d[6] = c.x, d[7] = b.y, d[8] = b.z, d[9] = c.x, d[10] = c.y, d[11] = b.z, d[12] = c.x, d[13] = c.y, d[14] = b.z, d[15] = b.x, d[16] = c.y, d[17] = b.z, d[18] = b.x, d[19] = c.y, d[20] = b.z, d[21] = b.x, d[22] = b.y, d[23] = b.z, d[24] = b.x, d[25] = b.y, d[26] = c.z, d[27] = c.x, d[28] = b.y, d[29] = c.z, d[30] = c.x, d[31] = b.y, d[32] = c.z, d[33] = c.x, d[34] = c.y, d[35] = c.z, d[36] = c.x, d[37] = c.y, d[38] = c.z, d[39] = b.x, d[40] = c.y, d[41] = c.z, d[42] = b.x, d[43] = c.y, d[44] = c.z, d[45] = b.x, d[46] = b.y, d[47] = c.z, d[48] = b.x, d[49] = b.y, d[50] = b.z, d[51] = b.x, d[52] = b.y, d[53] = c.z, d[54] = c.x, d[55] = b.y, d[56] = b.z, d[57] = c.x, d[58] = b.y, d[59] = c.z, d[60] = c.x, d[61] = c.y, d[62] = b.z, d[63] = c.x, d[64] = c.y, d[65] = c.z, d[66] = b.x, d[67] = c.y, d[68] = b.z, d[69] = b.x, d[70] = c.y, d[71] = c.z, this.geometry.attributes.position.needsUpdate = !0, this.geometry.computeBoundingSphere(), this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1
},THREE.BoundingBoxHelper = function (a, b) {
    var c = void 0 !== b ? b : 8947848;
    this.object = a, this.box = new THREE.Box3, THREE.Mesh.call(this, new THREE.BoxGeometry(1, 1, 1), new THREE.MeshBasicMaterial({
        color: c,
        wireframe: !0
    }))
},THREE.BoundingBoxHelper.prototype = Object.create(THREE.Mesh.prototype),THREE.BoundingBoxHelper.prototype.constructor = THREE.BoundingBoxHelper,THREE.BoundingBoxHelper.prototype.update = function () {
    this.box.setFromObject(this.object), this.box.size(this.scale), this.box.center(this.position)
},THREE.CameraHelper = function (a) {
    function b(a, b, d) {
        c(a, d), c(b, d)
    }

    function c(a, b) {
        d.vertices.push(new THREE.Vector3), d.colors.push(new THREE.Color(b)), void 0 === f[a] && (f[a] = []), f[a].push(d.vertices.length - 1)
    }

    var d = new THREE.Geometry, e = new THREE.LineBasicMaterial({
        color: 16777215,
        vertexColors: THREE.FaceColors
    }), f = {};
    b("n1", "n2", 16755200), b("n2", "n4", 16755200), b("n4", "n3", 16755200), b("n3", "n1", 16755200), b("f1", "f2", 16755200), b("f2", "f4", 16755200), b("f4", "f3", 16755200), b("f3", "f1", 16755200), b("n1", "f1", 16755200), b("n2", "f2", 16755200), b("n3", "f3", 16755200), b("n4", "f4", 16755200), b("p", "n1", 16711680), b("p", "n2", 16711680), b("p", "n3", 16711680), b("p", "n4", 16711680), b("u1", "u2", 43775), b("u2", "u3", 43775), b("u3", "u1", 43775), b("c", "t", 16777215), b("p", "c", 3355443), b("cn1", "cn2", 3355443), b("cn3", "cn4", 3355443), b("cf1", "cf2", 3355443), b("cf3", "cf4", 3355443), THREE.Line.call(this, d, e, THREE.LinePieces), this.camera = a, this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1, this.pointMap = f, this.update()
},THREE.CameraHelper.prototype = Object.create(THREE.Line.prototype),THREE.CameraHelper.prototype.constructor = THREE.CameraHelper,THREE.CameraHelper.prototype.update = function () {
    var a, b, c = new THREE.Vector3, d = new THREE.Camera, e = function (e, g, h, k) {
        if (c.set(g, h, k).unproject(d), e = b[e], void 0 !== e)for (g = 0, h = e.length; h > g; g++)a.vertices[e[g]].copy(c)
    };
    return function () {
        a = this.geometry, b = this.pointMap, d.projectionMatrix.copy(this.camera.projectionMatrix), e("c", 0, 0, -1), e("t", 0, 0, 1), e("n1", -1, -1, -1), e("n2", 1, -1, -1), e("n3", -1, 1, -1), e("n4", 1, 1, -1), e("f1", -1, -1, 1), e("f2", 1, -1, 1), e("f3", -1, 1, 1), e("f4", 1, 1, 1), e("u1", .7, 1.1, -1), e("u2", -.7, 1.1, -1), e("u3", 0, 2, -1), e("cf1", -1, 0, 1), e("cf2", 1, 0, 1), e("cf3", 0, -1, 1), e("cf4", 0, 1, 1), e("cn1", -1, 0, -1), e("cn2", 1, 0, -1), e("cn3", 0, -1, -1), e("cn4", 0, 1, -1), a.verticesNeedUpdate = !0
    }
}(),THREE.DirectionalLightHelper = function (a, b) {
    THREE.Object3D.call(this), this.light = a, this.light.updateMatrixWorld(), this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1, b = b || 1;
    var c = new THREE.Geometry;
    c.vertices.push(new THREE.Vector3(-b, b, 0), new THREE.Vector3(b, b, 0), new THREE.Vector3(b, -b, 0), new THREE.Vector3(-b, -b, 0), new THREE.Vector3(-b, b, 0));
    var d = new THREE.LineBasicMaterial({fog: !1});
    d.color.copy(this.light.color).multiplyScalar(this.light.intensity), this.lightPlane = new THREE.Line(c, d), this.add(this.lightPlane), c = new THREE.Geometry, c.vertices.push(new THREE.Vector3, new THREE.Vector3), d = new THREE.LineBasicMaterial({fog: !1}), d.color.copy(this.light.color).multiplyScalar(this.light.intensity), this.targetLine = new THREE.Line(c, d), this.add(this.targetLine), this.update()
},THREE.DirectionalLightHelper.prototype = Object.create(THREE.Object3D.prototype),THREE.DirectionalLightHelper.prototype.constructor = THREE.DirectionalLightHelper,THREE.DirectionalLightHelper.prototype.dispose = function () {
    this.lightPlane.geometry.dispose(), this.lightPlane.material.dispose(), this.targetLine.geometry.dispose(), this.targetLine.material.dispose()
},THREE.DirectionalLightHelper.prototype.update = function () {
    var a = new THREE.Vector3, b = new THREE.Vector3, c = new THREE.Vector3;
    return function () {
        a.setFromMatrixPosition(this.light.matrixWorld), b.setFromMatrixPosition(this.light.target.matrixWorld), c.subVectors(b, a), this.lightPlane.lookAt(c), this.lightPlane.material.color.copy(this.light.color).multiplyScalar(this.light.intensity), this.targetLine.geometry.vertices[1].copy(c), this.targetLine.geometry.verticesNeedUpdate = !0, this.targetLine.material.color.copy(this.lightPlane.material.color)
    }
}(),THREE.EdgesHelper = function (a, b, c) {
    b = void 0 !== b ? b : 16777215, c = Math.cos(THREE.Math.degToRad(void 0 !== c ? c : 1));
    var k, d = [0, 0], e = {}, f = function (a, b) {
        return a - b
    }, g = ["a", "b", "c"], h = new THREE.BufferGeometry;
    a.geometry instanceof THREE.BufferGeometry ? (k = new THREE.Geometry, k.fromBufferGeometry(a.geometry)) : k = a.geometry.clone(), k.mergeVertices(), k.computeFaceNormals();
    var l = k.vertices;
    k = k.faces;
    for (var p = 0, q = 0, n = k.length; n > q; q++)for (var t = k[q], r = 0; 3 > r; r++) {
        d[0] = t[g[r]], d[1] = t[g[(r + 1) % 3]], d.sort(f);
        var s = d.toString();
        void 0 === e[s] ? (e[s] = {vert1: d[0], vert2: d[1], face1: q, face2: void 0}, p++) : e[s].face2 = q
    }
    d = new Float32Array(6 * p), f = 0;
    for (s in e)g = e[s], (void 0 === g.face2 || k[g.face1].normal.dot(k[g.face2].normal) <= c) && (p = l[g.vert1], d[f++] = p.x, d[f++] = p.y, d[f++] = p.z, p = l[g.vert2], d[f++] = p.x, d[f++] = p.y, d[f++] = p.z);
    h.addAttribute("position", new THREE.BufferAttribute(d, 3)), THREE.Line.call(this, h, new THREE.LineBasicMaterial({color: b}), THREE.LinePieces), this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1
},THREE.EdgesHelper.prototype = Object.create(THREE.Line.prototype),THREE.EdgesHelper.prototype.constructor = THREE.EdgesHelper,THREE.FaceNormalsHelper = function (a, b, c, d) {
    this.object = a, this.size = void 0 !== b ? b : 1, a = void 0 !== c ? c : 16776960, d = void 0 !== d ? d : 1, b = new THREE.Geometry, c = 0;
    for (var e = this.object.geometry.faces.length; e > c; c++)b.vertices.push(new THREE.Vector3, new THREE.Vector3);
    THREE.Line.call(this, b, new THREE.LineBasicMaterial({
        color: a,
        linewidth: d
    }), THREE.LinePieces), this.matrixAutoUpdate = !1, this.normalMatrix = new THREE.Matrix3, this.update()
},THREE.FaceNormalsHelper.prototype = Object.create(THREE.Line.prototype),THREE.FaceNormalsHelper.prototype.constructor = THREE.FaceNormalsHelper,THREE.FaceNormalsHelper.prototype.update = function () {
    var a = this.geometry.vertices, b = this.object, c = b.geometry.vertices, d = b.geometry.faces, e = b.matrixWorld;
    b.updateMatrixWorld(!0), this.normalMatrix.getNormalMatrix(e);
    for (var f = b = 0, g = d.length; g > b; b++, f += 2) {
        var h = d[b];
        a[f].copy(c[h.a]).add(c[h.b]).add(c[h.c]).divideScalar(3).applyMatrix4(e), a[f + 1].copy(h.normal).applyMatrix3(this.normalMatrix).normalize().multiplyScalar(this.size).add(a[f])
    }
    return this.geometry.verticesNeedUpdate = !0, this
},THREE.GridHelper = function (a, b) {
    var c = new THREE.Geometry, d = new THREE.LineBasicMaterial({vertexColors: THREE.VertexColors});
    this.color1 = new THREE.Color(4473924), this.color2 = new THREE.Color(8947848);
    for (var e = -a; a >= e; e += b) {
        c.vertices.push(new THREE.Vector3(-a, 0, e), new THREE.Vector3(a, 0, e), new THREE.Vector3(e, 0, -a), new THREE.Vector3(e, 0, a));
        var f = 0 === e ? this.color1 : this.color2;
        c.colors.push(f, f, f, f)
    }
    THREE.Line.call(this, c, d, THREE.LinePieces)
},THREE.GridHelper.prototype = Object.create(THREE.Line.prototype),THREE.GridHelper.prototype.constructor = THREE.GridHelper,THREE.GridHelper.prototype.setColors = function (a, b) {
    this.color1.set(a), this.color2.set(b), this.geometry.colorsNeedUpdate = !0
},THREE.HemisphereLightHelper = function (a, b) {
    THREE.Object3D.call(this), this.light = a, this.light.updateMatrixWorld(), this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1, this.colors = [new THREE.Color, new THREE.Color];
    var c = new THREE.SphereGeometry(b, 4, 2);
    c.applyMatrix((new THREE.Matrix4).makeRotationX(-Math.PI / 2));
    for (var d = 0; 8 > d; d++)c.faces[d].color = this.colors[4 > d ? 0 : 1];
    d = new THREE.MeshBasicMaterial({
        vertexColors: THREE.FaceColors,
        wireframe: !0
    }), this.lightSphere = new THREE.Mesh(c, d), this.add(this.lightSphere), this.update()
},THREE.HemisphereLightHelper.prototype = Object.create(THREE.Object3D.prototype),THREE.HemisphereLightHelper.prototype.constructor = THREE.HemisphereLightHelper,THREE.HemisphereLightHelper.prototype.dispose = function () {
    this.lightSphere.geometry.dispose(), this.lightSphere.material.dispose()
},THREE.HemisphereLightHelper.prototype.update = function () {
    var a = new THREE.Vector3;
    return function () {
        this.colors[0].copy(this.light.color).multiplyScalar(this.light.intensity), this.colors[1].copy(this.light.groundColor).multiplyScalar(this.light.intensity), this.lightSphere.lookAt(a.setFromMatrixPosition(this.light.matrixWorld).negate()), this.lightSphere.geometry.colorsNeedUpdate = !0
    }
}(),THREE.PointLightHelper = function (a, b) {
    this.light = a, this.light.updateMatrixWorld();
    var c = new THREE.SphereGeometry(b, 4, 2), d = new THREE.MeshBasicMaterial({wireframe: !0, fog: !1});
    d.color.copy(this.light.color).multiplyScalar(this.light.intensity), THREE.Mesh.call(this, c, d), this.matrix = this.light.matrixWorld, this.matrixAutoUpdate = !1
},THREE.PointLightHelper.prototype = Object.create(THREE.Mesh.prototype),THREE.PointLightHelper.prototype.constructor = THREE.PointLightHelper,THREE.PointLightHelper.prototype.dispose = function () {
    this.geometry.dispose(), this.material.dispose()
},THREE.PointLightHelper.prototype.update = function () {
    this.material.color.copy(this.light.color).multiplyScalar(this.light.intensity)
},THREE.SkeletonHelper = function (a) {
    this.bones = this.getBoneList(a);
    for (var b = new THREE.Geometry, c = 0; c < this.bones.length; c++)this.bones[c].parent instanceof THREE.Bone && (b.vertices.push(new THREE.Vector3), b.vertices.push(new THREE.Vector3), b.colors.push(new THREE.Color(0, 0, 1)), b.colors.push(new THREE.Color(0, 1, 0)));
    c = new THREE.LineBasicMaterial({
        vertexColors: THREE.VertexColors,
        depthTest: !1,
        depthWrite: !1,
        transparent: !0
    }), THREE.Line.call(this, b, c, THREE.LinePieces), this.root = a, this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1, this.update()
},THREE.SkeletonHelper.prototype = Object.create(THREE.Line.prototype),THREE.SkeletonHelper.prototype.constructor = THREE.SkeletonHelper,THREE.SkeletonHelper.prototype.getBoneList = function (a) {
    var b = [];
    a instanceof THREE.Bone && b.push(a);
    for (var c = 0; c < a.children.length; c++)b.push.apply(b, this.getBoneList(a.children[c]));
    return b
},THREE.SkeletonHelper.prototype.update = function () {
    for (var a = this.geometry, b = (new THREE.Matrix4).getInverse(this.root.matrixWorld), c = new THREE.Matrix4, d = 0, e = 0; e < this.bones.length; e++) {
        var f = this.bones[e];
        f.parent instanceof THREE.Bone && (c.multiplyMatrices(b, f.matrixWorld), a.vertices[d].setFromMatrixPosition(c), c.multiplyMatrices(b, f.parent.matrixWorld), a.vertices[d + 1].setFromMatrixPosition(c), d += 2)
    }
    a.verticesNeedUpdate = !0, a.computeBoundingSphere()
},THREE.SpotLightHelper = function (a) {
    THREE.Object3D.call(this), this.light = a, this.light.updateMatrixWorld(), this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1, a = new THREE.CylinderGeometry(0, 1, 1, 8, 1, !0), a.applyMatrix((new THREE.Matrix4).makeTranslation(0, -.5, 0)), a.applyMatrix((new THREE.Matrix4).makeRotationX(-Math.PI / 2));
    var b = new THREE.MeshBasicMaterial({wireframe: !0, fog: !1});
    this.cone = new THREE.Mesh(a, b), this.add(this.cone), this.update()
},THREE.SpotLightHelper.prototype = Object.create(THREE.Object3D.prototype),THREE.SpotLightHelper.prototype.constructor = THREE.SpotLightHelper,THREE.SpotLightHelper.prototype.dispose = function () {
    this.cone.geometry.dispose(), this.cone.material.dispose()
},THREE.SpotLightHelper.prototype.update = function () {
    var a = new THREE.Vector3, b = new THREE.Vector3;
    return function () {
        var c = this.light.distance ? this.light.distance : 1e4, d = c * Math.tan(this.light.angle);
        this.cone.scale.set(d, d, c), a.setFromMatrixPosition(this.light.matrixWorld), b.setFromMatrixPosition(this.light.target.matrixWorld), this.cone.lookAt(b.sub(a)), this.cone.material.color.copy(this.light.color).multiplyScalar(this.light.intensity)
    }
}(),THREE.VertexNormalsHelper = function (a, b, c, d) {
    this.object = a, this.size = void 0 !== b ? b : 1, b = void 0 !== c ? c : 16711680, d = void 0 !== d ? d : 1, c = new THREE.Geometry, a = a.geometry.faces;
    for (var e = 0, f = a.length; f > e; e++)for (var g = 0, h = a[e].vertexNormals.length; h > g; g++)c.vertices.push(new THREE.Vector3, new THREE.Vector3);
    THREE.Line.call(this, c, new THREE.LineBasicMaterial({
        color: b,
        linewidth: d
    }), THREE.LinePieces), this.matrixAutoUpdate = !1, this.normalMatrix = new THREE.Matrix3, this.update()
},THREE.VertexNormalsHelper.prototype = Object.create(THREE.Line.prototype),THREE.VertexNormalsHelper.prototype.constructor = THREE.VertexNormalsHelper,THREE.VertexNormalsHelper.prototype.update = function (a) {
    var b = new THREE.Vector3;
    return function (a) {
        a = ["a", "b", "c", "d"], this.object.updateMatrixWorld(!0), this.normalMatrix.getNormalMatrix(this.object.matrixWorld);
        for (var d = this.geometry.vertices, e = this.object.geometry.vertices, f = this.object.geometry.faces, g = this.object.matrixWorld, h = 0, k = 0, l = f.length; l > k; k++)for (var p = f[k], q = 0, n = p.vertexNormals.length; n > q; q++) {
            var t = p.vertexNormals[q];
            d[h].copy(e[p[a[q]]]).applyMatrix4(g), b.copy(t).applyMatrix3(this.normalMatrix).normalize().multiplyScalar(this.size), b.add(d[h]), h += 1, d[h].copy(b), h += 1
        }
        return this.geometry.verticesNeedUpdate = !0, this
    }
}(),THREE.VertexTangentsHelper = function (a, b, c, d) {
    this.object = a, this.size = void 0 !== b ? b : 1, b = void 0 !== c ? c : 255, d = void 0 !== d ? d : 1, c = new THREE.Geometry, a = a.geometry.faces;
    for (var e = 0, f = a.length; f > e; e++)for (var g = 0, h = a[e].vertexTangents.length; h > g; g++)c.vertices.push(new THREE.Vector3), c.vertices.push(new THREE.Vector3);
    THREE.Line.call(this, c, new THREE.LineBasicMaterial({
        color: b,
        linewidth: d
    }), THREE.LinePieces), this.matrixAutoUpdate = !1, this.update()
},THREE.VertexTangentsHelper.prototype = Object.create(THREE.Line.prototype),THREE.VertexTangentsHelper.prototype.constructor = THREE.VertexTangentsHelper,THREE.VertexTangentsHelper.prototype.update = function (a) {
    var b = new THREE.Vector3;
    return function (a) {
        a = ["a", "b", "c", "d"], this.object.updateMatrixWorld(!0);
        for (var d = this.geometry.vertices, e = this.object.geometry.vertices, f = this.object.geometry.faces, g = this.object.matrixWorld, h = 0, k = 0, l = f.length; l > k; k++)for (var p = f[k], q = 0, n = p.vertexTangents.length; n > q; q++) {
            var t = p.vertexTangents[q];
            d[h].copy(e[p[a[q]]]).applyMatrix4(g), b.copy(t).transformDirection(g).multiplyScalar(this.size), b.add(d[h]), h += 1, d[h].copy(b), h += 1
        }
        return this.geometry.verticesNeedUpdate = !0, this
    }
}(),THREE.WireframeHelper = function (a, b) {
    var c = void 0 !== b ? b : 16777215, d = [0, 0], e = {}, f = function (a, b) {
        return a - b
    }, g = ["a", "b", "c"], h = new THREE.BufferGeometry;
    if (a.geometry instanceof THREE.Geometry) {
        for (var k = a.geometry.vertices, l = a.geometry.faces, p = 0, q = new Uint32Array(6 * l.length), n = 0, t = l.length; t > n; n++)for (var r = l[n], s = 0; 3 > s; s++) {
            d[0] = r[g[s]], d[1] = r[g[(s + 1) % 3]], d.sort(f);
            var u = d.toString();
            void 0 === e[u] && (q[2 * p] = d[0], q[2 * p + 1] = d[1], e[u] = !0, p++)
        }
        for (d = new Float32Array(6 * p), n = 0, t = p; t > n; n++)for (s = 0; 2 > s; s++)p = k[q[2 * n + s]], g = 6 * n + 3 * s, d[g + 0] = p.x, d[g + 1] = p.y, d[g + 2] = p.z;
        h.addAttribute("position", new THREE.BufferAttribute(d, 3))
    } else if (a.geometry instanceof THREE.BufferGeometry) {
        if (void 0 !== a.geometry.attributes.index) {
            k = a.geometry.attributes.position.array, t = a.geometry.attributes.index.array, l = a.geometry.drawcalls, p = 0, 0 === l.length && (l = [{
                count: t.length,
                index: 0,
                start: 0
            }]);
            for (var q = new Uint32Array(2 * t.length), r = 0, v = l.length; v > r; ++r)for (var s = l[r].start, u = l[r].count, g = l[r].index, n = s, x = s + u; x > n; n += 3)for (s = 0; 3 > s; s++)d[0] = g + t[n + s], d[1] = g + t[n + (s + 1) % 3], d.sort(f), u = d.toString(), void 0 === e[u] && (q[2 * p] = d[0], q[2 * p + 1] = d[1], e[u] = !0, p++);
            for (d = new Float32Array(6 * p), n = 0, t = p; t > n; n++)for (s = 0; 2 > s; s++)g = 6 * n + 3 * s, p = 3 * q[2 * n + s], d[g + 0] = k[p], d[g + 1] = k[p + 1], d[g + 2] = k[p + 2]
        } else for (k = a.geometry.attributes.position.array, p = k.length / 3, q = p / 3, d = new Float32Array(6 * p), n = 0, t = q; t > n; n++)for (s = 0; 3 > s; s++)g = 18 * n + 6 * s, q = 9 * n + 3 * s, d[g + 0] = k[q], d[g + 1] = k[q + 1], d[g + 2] = k[q + 2], p = 9 * n + (s + 1) % 3 * 3, d[g + 3] = k[p], d[g + 4] = k[p + 1], d[g + 5] = k[p + 2];
        h.addAttribute("position", new THREE.BufferAttribute(d, 3))
    }
    THREE.Line.call(this, h, new THREE.LineBasicMaterial({color: c}), THREE.LinePieces), this.matrix = a.matrixWorld, this.matrixAutoUpdate = !1
},THREE.WireframeHelper.prototype = Object.create(THREE.Line.prototype),THREE.WireframeHelper.prototype.constructor = THREE.WireframeHelper,THREE.ImmediateRenderObject = function () {
    THREE.Object3D.call(this), this.render = function (a) {
    }
},THREE.ImmediateRenderObject.prototype = Object.create(THREE.Object3D.prototype),THREE.ImmediateRenderObject.prototype.constructor = THREE.ImmediateRenderObject,THREE.MorphBlendMesh = function (a, b) {
    THREE.Mesh.call(this, a, b), this.animationsMap = {}, this.animationsList = [];
    var c = this.geometry.morphTargets.length;
    this.createAnimation("__default", 0, c - 1, c / 1), this.setAnimationWeight("__default", 1)
},THREE.MorphBlendMesh.prototype = Object.create(THREE.Mesh.prototype),THREE.MorphBlendMesh.prototype.constructor = THREE.MorphBlendMesh,THREE.MorphBlendMesh.prototype.createAnimation = function (a, b, c, d) {
    b = {
        startFrame: b,
        endFrame: c,
        length: c - b + 1,
        fps: d,
        duration: (c - b) / d,
        lastFrame: 0,
        currentFrame: 0,
        active: !1,
        time: 0,
        direction: 1,
        weight: 1,
        directionBackwards: !1,
        mirroredLoop: !1
    }, this.animationsMap[a] = b, this.animationsList.push(b)
},THREE.MorphBlendMesh.prototype.autoCreateAnimations = function (a) {
    for (var c, b = /([a-z]+)_?(\d+)/, d = {}, e = this.geometry, f = 0, g = e.morphTargets.length; g > f; f++) {
        var h = e.morphTargets[f].name.match(b);
        if (h && 1 < h.length) {
            var k = h[1];
            d[k] || (d[k] = {
                start: 1 / 0,
                end: -(1 / 0)
            }), h = d[k], f < h.start && (h.start = f), f > h.end && (h.end = f), c || (c = k)
        }
    }
    for (k in d)h = d[k], this.createAnimation(k, h.start, h.end, a);
    this.firstAnimation = c
},THREE.MorphBlendMesh.prototype.setAnimationDirectionForward = function (a) {
    (a = this.animationsMap[a]) && (a.direction = 1, a.directionBackwards = !1)
},THREE.MorphBlendMesh.prototype.setAnimationDirectionBackward = function (a) {
    (a = this.animationsMap[a]) && (a.direction = -1, a.directionBackwards = !0)
},THREE.MorphBlendMesh.prototype.setAnimationFPS = function (a, b) {
    var c = this.animationsMap[a];
    c && (c.fps = b, c.duration = (c.end - c.start) / c.fps)
},THREE.MorphBlendMesh.prototype.setAnimationDuration = function (a, b) {
    var c = this.animationsMap[a];
    c && (c.duration = b, c.fps = (c.end - c.start) / c.duration)
},THREE.MorphBlendMesh.prototype.setAnimationWeight = function (a, b) {
    var c = this.animationsMap[a];
    c && (c.weight = b)
},THREE.MorphBlendMesh.prototype.setAnimationTime = function (a, b) {
    var c = this.animationsMap[a];
    c && (c.time = b)
},THREE.MorphBlendMesh.prototype.getAnimationTime = function (a) {
    var b = 0;
    return (a = this.animationsMap[a]) && (b = a.time), b
},THREE.MorphBlendMesh.prototype.getAnimationDuration = function (a) {
    var b = -1;
    return (a = this.animationsMap[a]) && (b = a.duration), b
},THREE.MorphBlendMesh.prototype.playAnimation = function (a) {
    var b = this.animationsMap[a];
    b ? (b.time = 0, b.active = !0) : THREE.warn("THREE.MorphBlendMesh: animation[" + a + "] undefined in .playAnimation()")
},THREE.MorphBlendMesh.prototype.stopAnimation = function (a) {
    (a = this.animationsMap[a]) && (a.active = !1)
},THREE.MorphBlendMesh.prototype.update = function (a) {
    for (var b = 0, c = this.animationsList.length; c > b; b++) {
        var d = this.animationsList[b];
        if (d.active) {
            var e = d.duration / d.length;
            d.time += d.direction * a, d.mirroredLoop ? (d.time > d.duration || 0 > d.time) && (d.direction *= -1, d.time > d.duration && (d.time = d.duration, d.directionBackwards = !0), 0 > d.time && (d.time = 0, d.directionBackwards = !1)) : (d.time %= d.duration, 0 > d.time && (d.time += d.duration));
            var f = d.startFrame + THREE.Math.clamp(Math.floor(d.time / e), 0, d.length - 1), g = d.weight;
            f !== d.currentFrame && (this.morphTargetInfluences[d.lastFrame] = 0, this.morphTargetInfluences[d.currentFrame] = 1 * g, this.morphTargetInfluences[f] = 0, d.lastFrame = d.currentFrame, d.currentFrame = f), e = d.time % e / e, d.directionBackwards && (e = 1 - e), this.morphTargetInfluences[d.currentFrame] = e * g, this.morphTargetInfluences[d.lastFrame] = (1 - e) * g
        }
    }
};
var Detector = {
    canvas: !!window.CanvasRenderingContext2D,
    webgl: function () {
        try {
            var canvas = document.createElement("canvas");
            return !(!window.WebGLRenderingContext || !canvas.getContext("webgl") && !canvas.getContext("experimental-webgl"))
        } catch (e) {
            return !1
        }
    }(),
    workers: !!window.Worker,
    fileapi: window.File && window.FileReader && window.FileList && window.Blob,
    getWebGLErrorMessage: function () {
        var element = document.createElement("div");
        return element.id = "webgl-error-message", element.style.fontFamily = "monospace", element.style.fontSize = "13px", element.style.fontWeight = "normal", element.style.textAlign = "center", element.style.background = "#fff", element.style.color = "#000", element.style.padding = "1.5em", element.style.width = "400px", element.style.margin = "5em auto 0", this.webgl || (element.innerHTML = window.WebGLRenderingContext ? ['Your graphics card does not seem to support <a href="http://khronos.org/webgl/wiki/Getting_a_WebGL_Implementation" style="color:#000">WebGL</a>.<br />', 'Find out how to get it <a href="http://get.webgl.org/" style="color:#000">here</a>.'].join("\n") : ['Your browser does not seem to support <a href="http://khronos.org/webgl/wiki/Getting_a_WebGL_Implementation" style="color:#000">WebGL</a>.<br/>', 'Find out how to get it <a href="http://get.webgl.org/" style="color:#000">here</a>.'].join("\n")), element
    },
    addGetWebGLMessage: function (parameters) {
        var parent, id, element;
        parameters = parameters || {}, parent = void 0 !== parameters.parent ? parameters.parent : document.body, id = void 0 !== parameters.id ? parameters.id : "oldie", element = Detector.getWebGLErrorMessage(), element.id = id, parent.appendChild(element)
    }
};

"object" == typeof module && (module.exports = Detector), $(function () {

}),

  $(window).on("load resize scroll", function () {
  //  navTransitionEffect(),
  //    $(".dropdown").hover(function () {
  //      $(this).addClass("open")
  //    }, function () {
  //        $(this).removeClass("open")
  //    }),

	  w = $(window).width(), w < 992 ? $(".value").show() : ($(".value").hide(), $(".value-detail .t").show())
		      Detector.webgl ? particleUniverse() : ($("#fallback").show(), $("#particles").hide(), $("#staticParticle").show(),$("#under-fold").hide(), $("#scroll-down").hide())
  });
