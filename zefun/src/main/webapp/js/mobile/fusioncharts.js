/*
 FusionCharts JavaScript Library
 Copyright FusionCharts Technologies LLP
 License Information at <http://www.fusioncharts.com/license>
 FusionCharts JavaScript Library
 Copyright FusionCharts Technologies LLP
 License Information at <http://www.fusioncharts.com/license>

 @version 3.8.0

 @attributions (infers respective third-party copyrights)
 Raphael 2.1.0 (modified as 'Red Raphael') <http://raphaeljs.com/license.html>
 JSON v2 <http://www.JSON.org/js.html>
 Firebug Lite 1.3.0 <http://getfirebug.com/firebuglite>
 */
(function () {
  if (!window.FusionCharts || !window.FusionCharts.version) {
    var d = window, k = d.document, B = d.navigator, t = {window: d}, E = t.modules = {}, b = t.interpreters = {}, K = Object.prototype.toString, G = /msie/i.test(B.userAgent) && !d.opera, c = /loaded|complete/, l = !1, a = function () {
      var a = t.ready;
      t.ready = !0;
      t.raiseEvent && (t.readyNotified = !0, t.raiseEvent("ready", {version: t.core.version, now: !a}, t.core));
      t.readyNow = !a
    }, q = function (a, b) {
      var f, g;
      if (b instanceof Array)for (f = 0; f < b.length; f += 1)"object" !== typeof b[f] ? a[f] = b[f] : ("object" !== typeof a[f] && (a[f] = b[f]instanceof Array ? [] : {}), q(a[f], b[f])); else for (f in b)"object" === typeof b[f] ? (g = K.call(b[f]), "[object Object]" === g ? ("object" !== typeof a[f] && (a[f] = {}), q(a[f], b[f])) : "[object Array]" === g ? (a[f]instanceof Array || (a[f] = []), q(a[f], b[f])) : a[f] = b[f]) : a[f] = b[f];
      return a
    };
    t.extend = function (a, b, f, g) {
      var s;
      f && a.prototype && (a = a.prototype);
      if (!0 === g)q(a, b); else for (s in b)a[s] = b[s];
      return a
    };
    t.uniqueId = function () {
      return "chartobject-" + (t.uniqueId.lastId += 1)
    };
    t.uniqueId.lastId = 0;
    t.policies =
    {
      options: {
        chartTypeSourcePath: ["typeSourcePath", ""],
        product: ["product", "v3"],
        insertMode: ["insertMode", "replace"],
        safeMode: ["safeMode", !0],
        overlayButton: ["overlayButton", void 0],
        containerBackgroundColor: ["containerBackgroundColor", "#ffffff"],
        containerBackgroundOpacity: ["containerBackgroundOpacity", 1],
        containerClassName: ["containerClassName", "fusioncharts-container"],
        chartType: ["type", void 0],
        baseChartMessageFont: ["baseChartMessageFont", "Verdana,sans"],
        baseChartMessageFontSize: ["baseChartMessageFontSize",
          "10"],
        baseChartMessageColor: ["baseChartMessageColor", "#666666"],
        baseChartMessageImageHAlign: ["baseChartMessageImageHAlign", "middle"],
        baseChartMessageImageVAlign: ["baseChartMessageImageVAlign", "middle"],
        baseChartMessageImageAlpha: ["baseChartMessageImageAlpha", 100],
        baseChartMessageImageScale: ["baseChartMessageImageScale", 100],
        dataLoadStartMessage: ["dataLoadStartMessage", "Retrieving data. Please wait."],
        dataLoadErrorMessage: ["dataLoadErrorMessage", "Error in loading data."],
        dataInvalidMessage: ["dataInvalidMessage",
          "Invalid data."],
        dataEmptyMessage: ["dataEmptyMessage", "No data to display."],
        typeNotSupportedMessage: ["typeNotSupportedMessage", "Chart type not supported."],
        loadMessage: ["loadMessage", "Loading chart. Please wait."],
        renderErrorMessage: ["renderErrorMessage", "Unable to render chart."]
      },
      attributes: {lang: ["lang", "EN"], id: ["id", void 0]},
      width: ["width", "400"],
      height: ["height", "300"],
      src: ["swfUrl", ""]
    };
    b.stat = "swfUrl id width height debugMode registerWithJS backgroundColor scaleMode lang detectFlashVersion autoInstallRedirect".split(" ");
    t.parsePolicies = function (a, b, f) {
      var g, s, c;
      for (s in b)if (t.policies[s]instanceof Array)c = f[b[s][0]], a[s] = void 0 === c ? b[s][1] : c; else for (g in"object" !== typeof a[s] && (a[s] = {}), b[s])c = f[b[s][g][0]], a[s][g] = void 0 === c ? b[s][g][1] : c
    };
    t.parseCommands = function (a, c, f) {
      var g, s;
      "string" === typeof c && (c = b[c] || []);
      g = 0;
      for (s = c.length; g < s; g++)a[c[g]] = f[g];
      return a
    };
    t.registrars = {
      module: function () {
        return t.core.apply(t.core, arguments)
      }
    };
    t.core = function (a) {
      if (!(this instanceof t.core)) {
        if (1 === arguments.length && a instanceof
          Array && "private" === a[0]) {
          if (E[a[1]])return;
          E[a[1]] = {};
          a[3]instanceof Array && (t.core.version[a[1]] = a[3]);
          return "function" === typeof a[2] ? a[2].call(t, E[a[1]]) : t
        }
        if (1 === arguments.length && "string" === typeof a)return t.core.items[a];
        t.raiseError && t.raiseError(this, "25081840", "run", "", new SyntaxError('Use the "new" keyword while creating a new FusionCharts object'))
      }
      var c = {};
      this.__state = {};
      1 === arguments.length && "object" === typeof arguments[0] ? c = arguments[0] : t.parseCommands(c, b.stat, arguments);
      1 < arguments.length &&
      "object" === typeof arguments[arguments.length - 1] && (delete c[b.stat[arguments.length - 1]], t.extend(c, arguments[arguments.length - 1]));
      this.id = "undefined" === typeof c.id ? this.id = t.uniqueId() : c.id;
      this.args = c;
      t.core.items[this.id]instanceof t.core && t.raiseWarning(this, "06091847", "param", "", Error('A FusionChart oject with the specified id "' + this.id + '" already exists. Renaming it to ' + (this.id = t.uniqueId())));
      t.parsePolicies(this, t.policies, c);
      this.attributes.id = this.id;
      this.resizeTo && this.resizeTo(c.width,
        c.height, !0);
      this.chartType && this.chartType(c.type || c.swfUrl, !0);
      t.raiseEvent("beforeInitialize", c, this);
      t.core.items[this.id] = this;
      t.core.defaultOptions = t.core.options;
      t.raiseEvent("initialized", c, this);
      return this
    };
    t.core.prototype = {};
    t.core.prototype.constructor = t.core;
    t.extend(t.core, {
      id: "FusionCharts", version: ["3", "8", "0"], items: {}, options: {}, getObjectReference: function (a) {
        return t.core.items[a].ref
      }, register: function (a) {
        return t.registrars[a = a && a.toString && a.toString().toLowerCase()] && t.registrars[a].apply(t.core,
            Array.prototype.slice.call(arguments, 1))
      }
    });
    d.FusionCharts = t.core;
    d.FusionMaps && d.FusionMaps.legacy && (t.core(["private", "modules.core.geo", d.FusionMaps.legacy, d.FusionMaps.version]), l = !0);
    c.test(k.readyState) || k.loaded ? (t.ready = !0, setTimeout(a, 1)) : function () {
      function b() {
        arguments.callee.done || (arguments.callee.done = !0, f && clearTimeout(f), l || (d.FusionMaps && d.FusionMaps.legacy && t.core(["private", "modules.core.geo", d.FusionMaps.legacy, d.FusionMaps.version]), d.FusionMaps = t.core), setTimeout(a, 1))
      }

      function q() {
        c.test(k.readyState) ?
          b() : f = setTimeout(q, 10)
      }

      var f, g;
      k.addEventListener ? k.addEventListener("DOMContentLoaded", b, !1) : k.attachEvent && d.attachEvent("onLoad", b);
      if (G)try {
        "https:" === d.location.protocol ? k.write('<script id="__ie_onload_fusioncharts" defer="defer" src="//:">\x3c/script>') : k.write('<script id="__ie_onload_fusioncharts" defer="defer" src="javascript:void(0)">\x3c/script>'), g = k.getElementById("__ie_onload_fusioncharts"), g.onreadystatechange = function () {
          "complete" == this.readyState && b()
        }
      } catch (s) {
      }
      /WebKit/i.test(B.userAgent) &&
      (f = setTimeout(q, 10));
      d.onload = function (a) {
        return function () {
          b();
          a && a.call && a.call(d)
        }
      }(d.onload)
    }();
    d.FusionMaps = t.core
  }
})();
FusionCharts.register("module", ["private", "modules.mantle.errormanager", function () {
  var d = this, k = d.window, B = {
    type: "TypeException",
    range: "ValueRangeException",
    impl: "NotImplementedException",
    param: "ParameterException",
    run: "RuntimeException",
    comp: "DesignTimeError",
    undefined: "UnspecifiedException"
  }, t = function (b, t, G, c, l, a) {
    var q = "#" + t + " " + (b ? b.id : "unknown-source") + c + " " + a + " >> ";
    l instanceof Error ? (l.name = B[G], l.module = "FusionCharts" + c, l.level = a, l.message = q + l.message, q = l.message, k.setTimeout(function () {
      throw l;
    }, 0)) : q += l;
    t = {id: t, nature: B[G], source: "FusionCharts" + c, message: q};
    d.raiseEvent(a, t, b);
    if ("function" === typeof k["FC_" + a])k["FC_" + a](t)
  }, E;
  d.raiseError = function (b, d, k, c, l) {
    t(b, d, k, c, l, "Error")
  };
  d.raiseWarning = function (b, d, k, c, l) {
    t(b, d, k, c, l, "Warning")
  };
  E = {
    outputHelpers: {
      text: function (b, d) {
        E.outputTo("#" + b.eventId + " [" + (b.sender.id || b.sender).toString() + '] fired "' + b.eventType + '" event. ' + ("error" === b.eventType || "warning" === b.eventType ? d.message : ""))
      }, event: function (b, d) {
        this.outputTo(b, d)
      }, verbose: function (b,
                            d) {
        E.outputTo(b.eventId, b.sender.id, b.eventType, d)
      }
    }, outputHandler: function (b, k) {
      "function" !== typeof E.outputTo ? d.core["debugger"].outputFailed = !0 : (d.core["debugger"].outputFailed = !1, E.currentOutputHelper(b, k))
    }, currentOutputHelper: void 0, outputTo: void 0, enabled: !1
  };
  E.currentOutputHelper = E.outputHelpers.text;
  d.extend(d.core, {
    "debugger": {
      syncStateWithCharts: !0, outputFormat: function (b) {
        return b && "function" === typeof b.toLowerCase && "function" === typeof E.outputHelpers[b = b.toLowerCase()] ? (E.currentOutputHelper =
          E.outputHelpers[b], !0) : !1
      }, outputTo: function (b) {
        "function" === typeof b ? E.outputTo = b : null === b && (d.core["debugger"].enable(!1), delete E.outputTo)
      }, enable: function (b, k, t) {
        var c;
        "object" === typeof b && 1 === arguments.length && (c = b, b = c.state, k = c.outputTo, t = c.outputFormat);
        "function" === typeof b && ("string" !== typeof k || 2 !== arguments.length && !c || (t = k), k = b, b = !0);
        if ("boolean" === typeof b && b !== E.enabled)d.core[(E.enabled = b) ? "addEventListener" : "removeEventListener"]("*", E.outputHandler);
        "function" === typeof k && (E.outputTo =
          k);
        d.core["debugger"].outputFormat(t);
        return E.enabled
      }, enableFirebugLite: function () {
        var b;
        k.console && k.console.firebug ? d.core["debugger"].enable(k.console.log, "verbose") : ((b = k.document.getElementsByTagName("html")) && b[0].setAttribute("debug", "true"), d.loadScript("https://getfirebug.com/firebug-lite.js#overrideConsole=false,startOpened=true", function () {
          d.core["debugger"].enable(k.console.log, "verbose")
        }, "{ startOpened: true }", !0, !0))
      }
    }, debugMode: {
      enabled: function () {
        k.setTimeout(function () {
          throw Error("Deprecated! Please use FusionCharts.debugger.enable instead.");
        }, 0);
        return d.core["debugger"].enable.apply(d.core["debugger"], arguments)
      }
    }
  }, !1)
}]);
FusionCharts.register("module", ["private", "modules.mantle.eventmanager", function () {
  var d = this, k = d.window, B = d.core, t = k.Object.prototype.toString, E = t.call([]), b = function (a, b, c, f) {
    try {
      a[0].call(b, c, f || {})
    } catch (g) {
      setTimeout(function () {
        throw g;
      }, 0)
    }
  }, K = function (a, c, l) {
    if (a instanceof Array)for (var f = 0, g; f < a.length; f += 1) {
      if (a[f][1] === c.sender || void 0 === a[f][1])g = a[f][1] === c.sender ? c.sender : d.core, b(a[f], g, c, l), !0 === c.detached && (a.splice(f, 1), --f, c.detached = !1);
      if (!0 === c.cancelled)break
    }
  }, G = {
    unpropagator: function () {
      return !1 ===
        (this.cancelled = !0)
    }, detacher: function () {
      return !1 === (this.detached = !0)
    }, undefaulter: function () {
      return !1 === (this.prevented = !0)
    }, listeners: {}, lastEventId: 0, addListener: function (a, b, c) {
      var f, g;
      if (t.call(a) === E) {
        f = [];
        for (g = 0; g < a.length; g += 1)f.push(G.addListener(a[g], b, c));
        return f
      }
      if ("string" !== typeof a)d.raiseError(c || d.core, "03091549", "param", "::EventTarget.addListener", Error("Unspecified Event Type")); else if ("function" !== typeof b)d.raiseError(c || d.core, "03091550", "param", "::EventTarget.addListener",
        Error("Invalid Event Listener")); else return a = a.toLowerCase(), G.listeners[a]instanceof Array || (G.listeners[a] = []), G.listeners[a].push([b, c]), b
    }, removeListener: function (a, b, c) {
      var f;
      if ("function" !== typeof b)d.raiseError(c || d.core, "03091560", "param", "::EventTarget.removeListener", Error("Invalid Event Listener")); else if (a instanceof Array)for (f = 0; f < a.length; f += 1)G.removeListener(a[f], b, c); else if ("string" !== typeof a)d.raiseError(c || d.core, "03091559", "param", "::EventTarget.removeListener", Error("Unspecified Event Type"));
      else if (a = a.toLowerCase(), a = G.listeners[a], a instanceof Array)for (f = 0; f < a.length; f += 1)a[f][0] === b && a[f][1] === c && (a.splice(f, 1), --f)
    }, triggerEvent: function (a, b, c, f, g, s) {
      if ("string" !== typeof a)d.raiseError(b, "03091602", "param", "::EventTarget.dispatchEvent", Error("Invalid Event Type")); else {
        a = a.toLowerCase();
        var n = {
          eventType: a,
          eventId: G.lastEventId += 1,
          sender: b || Error("Orphan Event"),
          cancelled: !1,
          stopPropagation: this.unpropagator,
          prevented: !1,
          preventDefault: this.undefaulter,
          detached: !1,
          detachHandler: this.detacher
        };
        K(G.listeners[a], n, c);
        K(G.listeners["*"], n, c);
        switch (n.prevented) {
          case !0:
            if ("function" === typeof s)try {
              s.call(f || b || k, n, c || {})
            } catch (l) {
              setTimeout(function () {
                throw l;
              }, 0)
            }
            break;
          default:
            if ("function" === typeof g)try {
              g.call(f || b || k, n, c || {})
            } catch (A) {
              setTimeout(function () {
                throw A;
              }, 0)
            }
        }
        return !0
      }
    }
  }, c = d.raiseEvent = function (a, b, c, f, g, s) {
    return G.triggerEvent(a, c, b, f, g, s)
  }, l = d.legacyEventList = {}, a = {};
  d.disposeEvents = function (a) {
    var b, c;
    for (b in G.listeners)for (c = 0; c < G.listeners[b].length; c += 1)G.listeners[b][c][1] ===
    a && G.listeners[b].splice(c, 1)
  };
  d.raiseEventWithLegacy = function (a, b, d, f, g, s, n) {
    var Q = l[a];
    c(a, b, d, g, s, n);
    Q && "function" === typeof k[Q] && setTimeout(function () {
      k[Q].apply(g || k, f)
    }, 0)
  };
  d.raiseEventGroup = function (b, d, l, f, g, s, n) {
    var Q = f.id, A = b + Q;
    a[A] ? (clearTimeout(a[A]), delete a[A]) : Q && A ? a[A] = setTimeout(function () {
      c(d, l, f, g, s, n);
      delete a[A]
    }, 0) : c(d, l, f, g, s, n)
  };
  d.addEventListener = function (a, b) {
    return G.addListener(a, b)
  };
  d.removeEventListener = function (a, b) {
    return G.removeListener(a, b)
  };
  d.extend(B, {
    addEventListener: function (a,
                                b) {
      return G.addListener(a, b)
    }, removeEventListener: function (a, b) {
      return G.removeListener(a, b)
    }, ready: function (a, b, c) {
      d.ready ? (B.ready = function (a, g) {
        "function" === typeof a && setTimeout(function () {
          a.call(g || B, b || B)
        }, 0)
      }, B.ready(a, c)) : "function" === typeof a && B.addEventListener("ready", function () {
        B.ready(a, b, c)
      });
      return this
    }
  });
  B.on = B.addEventListener;
  d.extend(B.prototype, {
    addEventListener: function (a, b) {
      return G.addListener(a, b, this)
    }, removeEventListener: function (a, b) {
      return G.removeListener(a, b, this)
    }
  });
  B.prototype.on = B.prototype.addEventListener;
  d.policies.options.events = ["events", {}];
  d.addEventListener("beforeInitialize", function (a) {
    a = a.sender;
    var b = a.options.events, c;
    if (b)for (c in b)"function" === typeof b[c] && a.addEventListener(c, b[c])
  });
  d.ready && !d.readyNotified && (d.readyNotified = !0, d.raiseEvent("ready", {
    version: d.core.version,
    now: d.readyNow
  }, d.core))
}]);
FusionCharts.register("module", ["private", "modules.mantle.ajax", function () {
  var d = this, k = d.window, B = parseFloat(k.navigator.appVersion.split("MSIE")[1]), t = 5.5 <= B && 7 >= B ? !0 : !1, E = "file:" === k.location.protocol, b = k.ActiveXObject, K = (!b || !E) && k.XMLHttpRequest, G = {
    objects: 0,
    xhr: 0,
    requests: 0,
    success: 0,
    failure: 0,
    idle: 0
  }, c = function () {
    var d;
    if (K)return c = function () {
      G.xhr++;
      return new K
    }, c();
    try {
      d = new b("Msxml2.XMLHTTP"), c = function () {
        G.xhr++;
        return new b("Msxml2.XMLHTTP")
      }
    } catch (a) {
      try {
        d = new b("Microsoft.XMLHTTP"),
          c = function () {
            G.xhr++;
            return new b("Microsoft.XMLHTTP")
          }
      } catch (q) {
        d = !1
      }
    }
    return d
  };
  d.core.ajax = {
    stats: function (b) {
      return b ? G[b] : d.extend({}, G)
    },
    headers: {
      "If-Modified-Since": "Sat, 29 Oct 1994 19:43:31 GMT",
      "X-Requested-With": "XMLHttpRequest",
      "X-Requested-By": "FusionCharts",
      Accept: "text/plain, */*",
      "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
    }
  };
  B = d.ajax = function (b, a) {
    this.onSuccess = b;
    this.onError = a;
    this.open = !1;
    G.objects++;
    G.idle++
  };
  d.extend(B.prototype, {
    headers: d.core.ajax.headers,
    transact: function (b, a, q, S) {
      var C = this, f = C.xmlhttp, g = C.headers, s = C.onError, n = C.onSuccess;
      b = "POST" === b;
      var Q, A;
      if (!f || t)f = c(), C.xmlhttp = f;
      f.onreadystatechange = function () {
        try {
          4 === f.readyState && (!f.status && E || 200 <= f.status && 300 > f.status || 304 === f.status || 1223 === f.status || 0 === f.status ? (n && n(f.responseText, C, S, a), G.success++) : s && (s(Error("XmlHttprequest Error"), C, S, a), G.failure++), G.idle--, C.open = !1)
        } catch (b) {
          s && s(b, C, S, a), k.FC_DEV_ENVIRONMENT && setTimeout(function () {
            throw b;
          }, 0), G.failure++
        }
      };
      try {
        f.open(b ?
          "POST" : "GET", a, !0);
        f.overrideMimeType && f.overrideMimeType("text/plain");
        if (b)if ("string" === typeof q)Q = q; else {
          Q = [];
          for (A in q)Q.push(A + "=" + (q[A] + "").replace(/\=/g, "%3D").replace(/\&/g, "%26"));
          Q = Q.join("&")
        } else Q = null;
        for (A in g)f.setRequestHeader(A, g[A]);
        f.send(Q);
        G.requests++;
        G.idle++;
        C.open = !0
      } catch (m) {
        d.raiseError(d.core, "1110111515A", "run", "XmlHttprequest Error", m.message)
      }
      return f
    }, get: function (b, a) {
      return this.transact("GET", b, void 0, a)
    }, post: function (b, a, c) {
      return this.transact("POST", b, a,
        c)
    }, abort: function () {
      var b = this.xmlhttp;
      this.open = !1;
      return b && "function" === typeof b.abort && b.readyState && 0 !== b.readyState && b.abort()
    }, dispose: function () {
      this.open && this.abort();
      delete this.onError;
      delete this.onSuccess;
      delete this.xmlhttp;
      delete this.open;
      G.objects--;
      return null
    }
  })
}]);
FusionCharts.register("module", ["private", "modules.mantle.runtime;1.1", function () {
  var d = this, k = d.window, B = /(^|[\/\\])(fusioncharts\.js)([\?#].*)?$/ig, t = /[\\\"<>;&]/, E = /^[^\S]*?(sf|f|ht)(tp|tps):\/\//i, b = {}, K = {}, G = {}, c = {}, l = d.purgeDOM = function (a) {
    var b = a.attributes, f, g;
    if (b)for (f = b.length - 1; 0 <= f; --f)g = b[f].name, "function" === typeof a[g] && (a[g] = null);
    if (b = a.childNodes)for (b = b.length, f = 0; f < b; f += 1)l(a.childNodes[f])
  }, a = function (a, b, f) {
    var g, c;
    for (g in a)if (a[g]instanceof Array)b[a[g][0]] = f[g]; else for (c in a[g])b[a[g][c][0]] =
      f[g][c]
  }, q = /^(FusionCharts|FusionWidgets|FusionMaps)/;
  d.getScriptBaseUri = function (a) {
    var b = k.document.getElementsByTagName("script"), f = b.length, g, c;
    for (c = 0; c < f; c += 1)if (g = b[c].getAttribute("src"), void 0 !== g && null !== g && null !== g.match(a))return g.replace(a, "$1")
  };
  d.core.options.scriptBaseUri = function () {
    var a = d.getScriptBaseUri(B);
    return void 0 === a ? (d.raiseError(FusionCharts, "1603111624", "run", ">GenericRuntime~scriptBaseUri", "Unable to locate FusionCharts script source location (URL)."), "") : a
  }();
  d.isXSSSafe =
    function (a, b) {
      return b && null !== E.exec(a) ? !1 : null === t.exec(a)
    };
  d.xssEncode = function (a) {
    return null === a || void 0 === a || "function" !== typeof a.toString ? "" : a = a.toString().replace(/&/g, "&amp;").replace(/\'/g, "&#39;").replace(/\"/g, "&quot;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
  };
  d.loadScript = function (a, l, f, g, s) {
    if (!a)return !1;
    var n = l && l.success || l, Q = l && l.failure, A, m = {type: "script", success: !1}, P = function () {
      c[A] = clearTimeout(c[A]);
      m.success ? n && n(a, A) : Q && Q(a, A);
      d.raiseEvent("externalresourceload", m, d.core)
    };
    s = s ? "" : d.core.options.scriptBaseUri;
    A = s + a;
    d.isXSSSafe(A, !1) || (A = "function" === typeof k.encodeURIComponent ? k.encodeURIComponent(A) : k.escape(A));
    m.path = s;
    m.src = A;
    m.file = a;
    if (!0 === G[A] && g)return m.success = !0, m.notReloaded = !0, "function" === typeof l && (l(), d.raiseEvent("externalresourceload", m, d.core)), !0;
    if (b[A] && g)return !1;
    b[A] = !0;
    K[A] && K[A].parentNode && K[A].parentNode.removeChild(K[A]);
    l = K[A] = k.document.createElement("script");
    l.type = "text/javascript";
    l.src = A;
    f && (l["\v" === "v" ? "text" : "innerHTML"] = f);
    "function" === typeof n && (G[A] = !1, c[A] = clearTimeout(c[A]), l.onload = function () {
      G[A] = !0;
      m.success = !0;
      P()
    }, l.onerror = function () {
      G[A] = !1;
      b[A] = !1;
      P()
    }, l.onreadystatechange = function () {
      if ("complete" === this.readyState || "loaded" === this.readyState)G[A] = !0, m.success = !0, P()
    });
    k.document.getElementsByTagName("head")[0].appendChild(l);
    "function" === typeof Q && (c[A] = setTimeout(function () {
      G[A] || P()
    }, d.core.options.html5ResourceLoadTimeout || 15E3));
    return !0
  };
  d.capitalizeString = function (a, b) {
    return a ? a.replace(b ? /(^|\s)([a-z])/g : /(^|\s)([a-z])/,
      function (a, b, c) {
        return b + c.toUpperCase()
      }) : a
  };
  d.extend(d.core, {
    clone: function (b, c) {
      var f = typeof b, g, s = d.extend({}, this.args, !1, !1);
      a(d.policies, s, this);
      a(d.renderer.getRendererPolicy(this.options.renderer), s, this);
      delete s.id;
      delete s.animate;
      delete s.stallLoad;
      g = s.link;
      s = d.extend({}, s, !1, !1);
      s.link = g;
      switch (f) {
        case "object":
          d.extend(s, b);
          break;
        case "boolean":
          c = b
      }
      return c ? s : new d.core(s)
    }, isActive: function () {
      if (!this.ref || k.document.getElementById(this.id) !== this.ref)return !1;
      try {
        return q.test(this.ref.signature())
      } catch (a) {
        return !1
      }
    },
    chartType: function (a, b) {
      var f = this.src, g = !0 === b, c = this.options, n;
      "string" === typeof a && (b = "object" === typeof b ? b : {}, f = a.replace(/[\?\#][\s\S]*$/g, ""), n = null !== f.match(/\.swf\s*?$/ig), f = f.replace(/\.swf\s*?$/ig, ""), c.chartType = f.replace(/^[\s\S]*\//ig, "").replace(/^fcmap_/i, ""), c.chartTypeSourcePath = -1 === f.indexOf("/") ? b.chartTypeSourcePath || this.options.chartTypeSourcePath || d.core.options.chartTypeSourcePath || "" : f.replace(/[^\/]*?$/ig, ""), this.src = ((d.core.options.scriptBaseUri || "") + (c.chartTypeSourcePath ||
      d.core.options.chartTypeSourcePath || "")).replace(/\/\s*$/g, "") + "/" + c.chartType.replace(/\.swf\s*?$/ig, "") + ".swf", n && (d.raiseWarning(this, "08101320181", "comp", "FusionCharts#chartType", 'Chart type has ".swf" in alias and as such has been deprecated. Please use chart type alias.'), c.chartTypeSourcePath = d.core.options.chartTypeSourcePath || ""), void 0 !== b.dataSource && null !== b.dataSource ? this.setChartData(b.dataSource, b.dataFormat, b.dataConfiguration) : this.isActive() && !g && this.render());
      return (c.chartType ||
      "").toLowerCase()
    }
  }, !0);
  k.getChartFromId = function (a) {
    d.raiseWarning(this, "11133001041", "comp", "GenericRuntime~getObjectFromId()", 'Use of deprecated getChartFromId() or getMapFromId(). Replace with "FusionCharts()" or FusionCharts.items[].');
    return d.core.items[a]instanceof d.core ? d.core.items[a].ref : k.swfobject && k.swfobject.getObjectById(a)
  };
  k.getMapFromId = k.getChartFromId
}]);
FusionCharts.register("module", ["private", "api.printmanager", function () {
  var d = this;
  d.extend(d.core, {
    printManager: {
      configure: function () {
        d.raiseWarning(d.core, "28141714", "impl", ".printManager.configure", "PrintManager is deprecated")
      }, isReady: function () {
        d.raiseWarning(d.core, "28141714", "impl", ".printManager.isReady", "PrintManager is deprecated");
        return !1
      }, enabled: function () {
        d.raiseWarning(d.core, "28141714", "impl", ".printManager.enabled", "PrintManager is deprecated");
        return !1
      }, managedPrint: function () {
        d.raiseWarning(d.core,
          "28141714", "impl", ".printManager.managedPrint", "PrintManager is deprecated")
      }
    }
  }, !1)
}]);
FusionCharts.register("module", ["private", "modules.interface.renderer", function () {
  var d = this, k = d.window, B = k.document, t = function () {
    d.raiseError(this, "25081845", "run", "::RendererManager", Error("No active renderer"))
  }, E = d.FusionChartsDOMInsertModes = {
    REPLACE: "replace",
    APPEND: "append",
    PREPEND: "prepend"
  }, b = {undefined: {render: t, remove: t, update: t, resize: t, config: t, policies: {}}}, K = {}, G = function (a) {
    return function () {
      var b = this.ref;
      if (void 0 === b || null === b || "function" !== typeof b[a])d.raiseError(this, "25081617",
        "run", "#" + a + "()", "ExternalInterface call failed. Check whether chart has been rendered."); else return b[a].apply(b, arguments)
    }
  }, c = function (a, b) {
    return "function" === typeof a[b] ? function () {
      return a[b].apply(a, arguments)
    } : a[b]
  }, l = function (a, b) {
    var c = B.getElementById(a), n = b.id || b.getAttribute("id"), d, l;
    if (null === c)return !1;
    if (a === n)return !0;
    n = b.getElementsByTagName("*");
    d = 0;
    for (l = n.length; d < l; d++)if (n[d] === c)return !1;
    return !0
  }, a = /[^\%\d]*$/ig, q = function () {
    var a = function (b) {
      var c = b.parentNode;
      if (9 === c.nodeType)return !1;
      var n = "display";
      k.getComputedStyle ? n = k.getComputedStyle(b)[n] : b.currentStyle && (n = b.currentStyle[n]);
      return "none" === n ? !0 : c ? a(c) : !1
    };
    return a
  }(), S = function () {
    var a = {}, b, c = function () {
      var n = 0, l = parseInt(d.core.options.visibilityTrackingInterval, 10) || 300, A, m, P, z, k;
      for (A in a)if (n += 1, m = a[A].chart, P = a[A].container, z = a[A].insertMode, k = a[A].callback, m.disposed || !q(P))delete a[A], --n, !m.disposed && m.renderChart.call(m, P, z, k);
      b = n ? setTimeout(c, l) : clearTimeout(b)
    };
    return function (n, l, A, m) {
      a[n.id] = {
        chart: n, container: l,
        insertMode: A, callback: m
      };
      b || (b = setTimeout(c, parseInt(d.core.options.visibilityTrackingInterval, 10) || 300))
    }
  }(), C;
  d.policies.options.containerElementId = ["renderAt", void 0];
  d.policies.options.renderer = ["renderer", void 0];
  d.policies.options.containerElementType = ["containerElementType", void 0];
  d.policies.options.visibilityTrackingInterval = 300;
  d.normalizeCSSDimension = function (a, b, c) {
    a = void 0 === a ? c.offsetWidth || parseFloat(c.style.width) : a;
    b = void 0 === b ? c.offsetHeight || parseFloat(c.style.height) : b;
    var n = {},
      d = c.style, l;
    d.width = a = a.toString ? a.toString() : "0";
    d.height = b = b.toString ? b.toString() : "0";
    if ((n.widthIsRelative = a.match(/^\s*\d*\.?\d*\%\s*$/) && !a.match(/^\s*0\%\s*$/)) && 0 === c.offsetWidth)for (l = c; l = l.offsetParent;)if (0 < l.offsetWidth) {
      a = (l.offsetWidth * parseFloat(a.match(/\d*/)[0]) / 100).toString();
      break
    }
    if ((n.heightIsRelative = b.match(/^\s*\d*\.?\d*\%\s*$/) && !b.match(/^\s*0\%\s*$/)) && 20 >= c.offsetHeight)for (l = c; l = l.offsetParent;)if (0 < l.offsetHeight) {
      b = (l.offsetHeight * parseFloat(b.match(/\d*/)[0]) / 100).toString();
      break
    }
    n.width = a.replace ? a.replace(/^\s*(\d*\.?\d*)\s*$/ig, "$1px") : a;
    n.height = b.replace ? b.replace(/^\s*(\d*\.?\d*)\s*$/ig, "$1px") : b;
    d.width = n.width;
    d.height = n.height;
    n.pixelWidth = n.widthIsRelative ? c.offsetWidth : parseInt(n.width, 10) || 0;
    n.pixelHeight = n.heightIsRelative ? c.offsetHeight : parseInt(n.height, 10) || 0;
    return n
  };
  C = d.renderer = {
    register: function (a, g) {
      if (!a || "function" !== typeof a.toString)throw Error("#03091436 ~renderer.register() Invalid value for renderer name.");
      a = a.toString().toLowerCase();
      if (void 0 !==
        b[a])return d.raiseError(d.core, "03091438", "param", "::RendererManager>register", 'Duplicate renderer name specified in "name"'), !1;
      b[a] = g;
      return !0
    }, userSetDefault: !1, setDefault: function (a) {
      if (!a || "function" !== typeof a.toString)return d.raiseError(d.core, "25081731", "param", "::RendererManager>setDefault", 'Invalid renderer name specified in "name"'), !1;
      if (void 0 === b[a = a.toString().toLowerCase()])return d.raiseError(d.core, "25081733", "range", "::RendererManager>setDefault", "The specified renderer does not exist."),
        !1;
      this.userSetDefault = !1;
      d.policies.options.renderer = ["renderer", a];
      return !0
    }, notifyRender: function (a) {
      var b = d.core.items[a && a.id];
      b && (!1 !== a.success || a.silent) || d.raiseError(d.core.items[a.id], "25081850", "run", "::RendererManager", Error("There was an error rendering the chart. Enable FusionCharts JS debugger for more information."));
      if (b.ref = a.ref)a.ref.FusionCharts = d.core.items[a.id];
      d.raiseEvent("internal.DOMElementCreated", {}, b)
    }, protectedMethods: {
      options: !0, attributes: !0, src: !0, ref: !0, constructor: !0,
      signature: !0, link: !0, addEventListener: !0, removeEventListener: !0
    }, getRenderer: function (a) {
      return b[a]
    }, getRendererPolicy: function (a) {
      a = b[a].policies;
      return "object" === typeof a ? a : {}
    }, currentRendererName: function () {
      return d.policies.options.renderer[1]
    }, update: function (a) {
      K[a.id].update.apply(a, Array.prototype.slice.call(arguments, 1))
    }, render: function (a) {
      K[a.id].render.apply(a, Array.prototype.slice.call(arguments, 1))
    }, remove: function (a) {
      K[a.id].remove.apply(a, Array.prototype.slice.call(arguments, 1))
    },
    resize: function (a) {
      K[a.id].resize.apply(a, Array.prototype.slice.call(arguments, 1))
    }, config: function (a) {
      K[a.id].config.apply(a, Array.prototype.slice.call(arguments, 1))
    }, dispose: function (a) {
      K[a.id].dispose.apply(a, Array.prototype.slice.call(arguments, 1))
    }
  };
  d.addEventListener("beforeInitialize", function (a) {
    a = a.sender;
    var g = a.options.renderer.toLowerCase(), c;
    "string" === typeof a.options.renderer && void 0 === b[g] && (a.options.renderer = d.policies.options.renderer[1]);
    a.options.renderer = g;
    K[a.id] = b[a.options.renderer];
    !0 !== K[a.id].initialized && "function" === typeof K[a.id].init && (K[a.id].init(), K[a.id].initialized = !0);
    d.parsePolicies(a, K[a.id].policies || {}, a.args);
    for (c in K[a.id].prototype)a[c] = K[a.id].prototype[c];
    for (c in K[a.id].events)a.addEventListener(c, K[a.id].events[c])
  });
  d.addEventListener(["rendered", "dataloaderror", "nodatatodisplay", "rendercancelled"], function (a, b) {
    var c = a.sender;
    c instanceof d.core && c.__state.rendering && (d.raiseEvent("internal.rendered", b, c), delete c.__state.rendering)
  });
  d.addEventListener("loaded",
    function (a) {
      var b = a.sender;
      a = a.sender.ref;
      var s, n;
      if (void 0 !== a && null !== a && "function" === typeof a.getExternalInterfaceMethods) {
        try {
          s = a.getExternalInterfaceMethods(), s = "string" === typeof s ? s.split(",") : []
        } catch (l) {
          s = [], d.raiseError(b, "13111126041", "run", "RendererManager^Loaded", Error("Error while retrieving data from the chart-object." + (l.message && 0 <= l.message.indexOf("NPObject") ? " Possible cross-domain security restriction." : "")))
        }
        for (a = 0; a < s.length; a += 1)n = s[a], void 0 === b[n] && (b[n] = G(n));
        if (b.ref)for (n in s =
          C.protectedMethods, a = C.getRenderer(b.options.renderer).protectedMethods, b)if (a && !s[n] && !a[n] && void 0 === b.ref[n])try {
          b.ref[n] = c(b, n)
        } catch (A) {
        }
      }
    });
  d.legacyEventList.resized = "FC_Resized";
  d.extend(d.core.prototype, {
    render: function (a, b, c) {
      void 0 === a && (a = this.options.containerElementId);
      "string" === typeof a && (a = B.getElementById(a));
      if (void 0 === a || null === a)return d.raiseError(this, "03091456", "run", ".render()", Error("Unable to find the container DOM element.")), this;
      q(a) ? S(this, a, b, c) : this.renderChart.call(this,
        a, b, c);
      return this
    }, renderChart: function (a, b, c) {
      var n = this, Q, A, m;
      if ((m = k[this.id]) && m.FusionCharts && m.FusionCharts === this || (m = this.ref) && m.FusionCharts && m.FusionCharts === this)d.renderer.dispose(this), m === k[this.id] && (k[this.id] = void 0);
      void 0 !== k[this.id] && d.raiseError(this, "25081843", "comp", ".render", Error("#25081843:IECompatibility() Chart Id is same as a JavaScript variable name. Variable naming error. Please use unique name forchart JS variable, chart-id and container id."));
      c ? "function" !== typeof c &&
      (c = void 0) : "function" === typeof b ? (c = b, b = void 0) : b || "function" !== typeof a || (c = a, a = void 0);
      b = (b || this.options.insertMode).toLowerCase() || E.REPLACE;
      void 0 === a && (a = this.options.containerElementId);
      "string" === typeof a && (a = B.getElementById(a));
      if (void 0 === a || null === a)return d.raiseError(this, "03091456", "run", ".render()", Error("Unable to find the container DOM element.")), this;
      if (l(this.id, a))return d.raiseError(this, "05102109", "run", ".render()", Error("A duplicate object already exists with the specific Id: " +
      this.id)), this;
      Q = B.createElement(this.options.containerElementType || "span");
      Q.setAttribute("id", this.id);
      if ("append" !== b && "prepend" !== b)for (; a.hasChildNodes();)a.removeChild(a.firstChild);
      "prepend" === b && a.firstChild ? a.insertBefore(Q, a.firstChild) : a.appendChild(Q);
      this.options.containerElement = a;
      this.options.containerElementId = a.id;
      if (b = Q.style)b.position = "relative", b.textAlign = "left", b.lineHeight = "normal", b.display = "inline-block", b.zoom = "1", b.fontWeight = "normal", b.fontVariant = "normal", b.fontStyle =
        "normal", b.textDecoration = "none", b["*DISPLAY"] = "inline", b.padding = "0", b.margin = "0", b.border = "none", b.direction = "ltr";
      this.options.containerClassName && (Q.className = this.options.containerClassName);
      b = d.normalizeCSSDimension(this.width, this.height, Q);
      this.__state.renderedWidth = b.pixelWidth;
      this.__state.renderedHeight = b.pixelHeight;
      this.__state.rendering = !0;
      d.raiseEvent("beforeRender", A = {
        container: a,
        width: this.width,
        height: this.height,
        renderer: this.options.renderer
      }, this, void 0, function (a, b) {
        d.renderer.render(n,
          Q, function () {
            d.renderer.notifyRender.apply(this, arguments);
            if (c)try {
              c.call(a.sender, b.container)
            } catch (g) {
              setTimeout(function () {
                throw g;
              })
            }
          })
      }, function () {
        d.raiseEvent("renderCancelled", A, n)
      });
      return this
    }, remove: function () {
      d.renderer.remove(this);
      return this
    }, resizeTo: function (b, c, s) {
      var n = this, l = n.width, A = n.height, m = n.__state;
      "object" === typeof b && (s = c, c = b.h, b = b.w);
      b = null === b || void 0 === b ? l : b.toString().replace(a, "");
      c = null === c || void 0 === c ? A : c.toString().replace(a, "");
      !0 !== s ? d.raiseEvent("beforeresize",
        {currentWidth: l, currentHeight: A, newWidth: b, newHeight: c}, n, void 0, function () {
          n.width = b;
          n.height = c;
          d.renderer.resize(n, {width: b, height: c});
          d.raiseEventWithLegacy("resized", {
            width: n.width,
            height: n.height,
            prevWidth: l,
            prevHeight: A,
            pixelWidth: n.ref && n.ref.offsetWidth || 0,
            pixelHeight: n.ref && n.ref.offsetHeight || 0,
            originalWidth: m.renderedWidth,
            originalHeight: m.renderedHeight
          }, n, [n.id, n.width, n.height])
        }, function () {
          d.raiseEvent("resizecancelled", {
              currentWidth: l,
              currentHeight: A,
              cancelledTargetWidth: b,
              cancelledTargetHeight: c
            },
            n)
        }) : (n.width = b, n.height = c);
      return this
    }, dispose: function () {
      var a = this, b = {};
      d.raiseEvent("beforeDispose", b, a, void 0, function () {
        d.renderer.dispose(a);
        d.raiseEvent("disposed", b, a);
        d.disposeEvents(a);
        delete d.core.items[a.id];
        for (var c in a)a.hasOwnProperty(c) && delete a[c];
        a.disposed = !0
      }, function () {
        d.raiseEvent("disposeCancelled", b, a)
      })
    }, configure: function (a, b) {
      var c;
      a && ("string" === typeof a ? (c = {}, c[a] = b) : c = a, d.renderer.config(this, c))
    }
  });
  d.extend(d.core, {
    setCurrentRenderer: function () {
      var a = C.setDefault.apply(C,
        arguments);
      C.userSetDefault = !0;
      return a
    }, getCurrentRenderer: function () {
      return C.currentRendererName.apply(C, arguments)
    }, render: function (a, b) {
      return a instanceof d.core ? (a.render(b), a) : (new d.core(a)).render(b)
    }
  }, !1)
}]);
FusionCharts.register("module", ["private", "modules.interface.transcoder", function () {
  var d = this, k = d.window, B = d.transcoders = {}, t = {}, E = {}, b = /url$/i, K = d._interactiveCharts = {
    selectscatter: [!0, !1],
    dragcolumn2d: [!0, !0],
    dragarea: [!0, !0],
    dragline: [!0, !0],
    dragnode: [!0, !0]
  }, G = function (b, c, n, f) {
    var A = n.obj;
    n = n.args;
    n.dataSource = b;
    n.xmlHttpRequestObject = c;
    n.source = "XmlHttpRequest";
    n.url = f;
    d.raiseEvent("dataLoadRequestCompleted", n, A, void 0, l, a)
  }, c = function (a, b, c) {
    var f = c.obj;
    c = c.args;
    c.error = a;
    c.httpStatus = b.xhr &&
    b.xhr.status ? b.xhr.status : -1;
    c.xmlHttpRequestObject = b;
    d.raiseEvent("dataLoadError", c, f);
    "function" === typeof k.FC_DataLoadError && k.FC_DataLoadError(f.id, c)
  }, l = function (a, b) {
    a.sender.setChartData(b.dataSource, b.dataFormat, b.config, b.successcallback, b.silent)
  }, a = function (a, b) {
    d.raiseEvent("dataLoadCancelled", b, a.sender);
    b.xmlHttpRequestObject.abort()
  }, q = function (a, b) {
    var n = a.sender, f = n.__state, l = b.url;
    n.options.dataSource = b.url;
    f.dhmXhrObj || (f.dhmXhrObj = new d.ajax(G, c));
    f.dhmXhrObj.get("function" === typeof k.decodeURIComponent ? k.decodeURIComponent(l) : k.unescape(l), {
      obj: n,
      args: b
    })
  }, S = function (a, b) {
    var c = a.sender, f = c.__state;
    d.raiseEvent("dataLoadRequestCancelled", b, c);
    f && f.dhmXhrObj && f.dhmXhrObj.abort()
  }, C = function (a, b) {
    var c = a.sender, f = c.__state, l = c.id;
    t[l] = b;
    E[l] && delete E[l];
    E[l] = {};
    f.dataReady = void 0;
    f.dataAvailable = !0;
    !0 !== b.silent && (!0 !== c.options.safeMode || !0 !== f.rendering || c.isActive() ? (delete f.args, d.renderer.update(c, b)) : (f.updatePending = b, d.raiseWarning(c, "23091255", "run", "::DataHandler~update",
      "Renderer update was postponed due to async loading.")));
    d.raiseEvent("dataUpdated", b, c, void 0, b.successcallback)
  }, f = function (a, b) {
    d.raiseEvent("dataUpdateCancelled", b, a.sender, void 0, b.failurecallback)
  };
  d.dataFormats = {};
  d.policies.options.dataSource = ["dataSource", void 0];
  d.policies.options.dataFormat = ["dataFormat", void 0];
  d.policies.options.dataConfiguration = ["dataConfiguration", void 0];
  d.policies.options.showDataLoadingMessage = ["showDataLoadingMessage", !1];
  d.addDataHandler = function (a, b) {
    if ("string" !== typeof a || void 0 !== B[a.toLowerCase()])d.raiseError(d.core, "03091606", "param", "::DataManager.addDataHandler", Error("Invalid Data Handler Name")); else {
      var c = {}, f = a.toLowerCase();
      B[f] = b;
      b.name = a;
      c["set" + a + "Data"] = function (b, c, s) {
        return this.setChartData(b, a, c, s)
      };
      b.transportable && (c["set" + a + "Url"] = function (b, c, s) {
        return this.setChartDataUrl(b, a, c, s)
      }, d.dataFormats[a + "URL"] = f + "Url");
      c["get" + a + "Data"] = function () {
        return this.getChartData(a)
      };
      d.dataFormats[a] = f;
      d.extend(d.core, c, !0)
    }
  };
  d.extend(d.core.prototype,
    {
      setChartDataUrl: function (a, c, f, l, A) {
        if (void 0 === c || null === c || "function" !== typeof c.toString)c = this.options.dataFormat, d.raiseWarning(this, "03091609", "param", "FusionCharts#setChartDataUrl", "Invalid Data Format. Reverting to current data format - " + c);
        c = c.toString().toLowerCase();
        c = b.test(c) ? c.slice(0, -3) : c;
        d.raiseEvent("dataLoadRequested", {
          source: "XmlHttpRequest",
          url: a,
          dataFormat: c,
          silent: !!A,
          config: f,
          successcallback: l
        }, this, void 0, q, S)
      }, setChartData: function (a, c, n, l, q) {
      var m = this.options, P, z;
      if (void 0 ===
        c || null === c || "function" !== typeof c.toString)c = m.dataFormat, d.raiseWarning(this, "03091610", "param", "FusionCharts#setChartData", "Invalid Data Format. Reverting to current data format - " + c);
      c = c.toString().toLowerCase();
      b.test(c) ? this.setChartDataUrl(a, c, n, l, q) : (m.dataSource = a, P = c, m.dataFormat = c, z = B[P], "undefined" === typeof z ? d.raiseError(d.core, "03091611", "param", "FusionCharts#setChartData", Error("Data Format not recognized")) : (c = (c = d.renderer && d.renderer.getRenderer(m.renderer || d.renderer.currentRendererName())) &&
      c.dataFormat, n = c === P ? z.passthrough ? z.passthrough(a, n) : {data: a} : z.encode(a, this, n || m.dataConfiguration) || {}, n["native"] = c === P, n.format = n["native"] ? c : "xml", n.dataFormat = P, n.dataSource = a, n.silent = !!q, "function" === typeof l && (n.successcallback = l), d.raiseEvent("beforeDataUpdate", n, this, void 0, C, f)))
    }, getChartData: function (a, b) {
      var c = this.options, f = this.id, l;
      if (void 0 === a || "function" !== typeof a.toString || void 0 === (l = B[a = a.toString().toLowerCase()]))d.raiseError(this, "25081543", "param", "::transcoder~getChartData()",
        Error('Unrecognized data-format specified in "format"')); else return E[f][a] ? c = E[f][a] : t[f] ? (a === t[f].format ? E[f][a] = t[f] : (E[f].xml || (E[f].xml = "xml" === t[f].format ? t[f] : B[t[f].format].encode(t[f].data, this, c.dataConfiguration)), E[f][a] || (E[f][a] = l.decode(E[f].xml.data, this, c.dataConfiguration))), c = E[f][a]) : c = {error: Error("Data not defined")}, !0 === Boolean(b) ? c : c.data
    }, dataReady: function (a) {
      return a ? this.__state.dataAvailable : this.__state.dataReady
    }
    });
  d.extend(d.core, {
    transcodeData: function (a, b, c, f,
                             l) {
      if (b && "function" === typeof b.toString && c && "function" === typeof c.toString && void 0 !== B[c = c.toString().toLowerCase()] && void 0 !== B[b = b.toString().toLowerCase()])return a = B[b].encode(a, this, l), c = B[c].decode(a.data, this, l), c.error instanceof Error || (c.error = a.error), f ? c : c.data;
      d.raiseError(this, "14090217", "param", ".transcodeData()", Error("Unrecognized data-format specified during transcoding."))
    }
  }, !1);
  d.getRenderer && !d.getRenderer("flash") || d.addEventListener("DataLoadRequested", function (a) {
    var b = a.sender;
    b.options && "flash" === b.options.renderer && b.options.useLegacyXMLTransport && a.preventDefault()
  });
  d.addEventListener("beforeInitialize", function (a) {
    a = a.sender;
    var c = a.options, f = c.dataSource, l = d.renderer && d.renderer.getRenderer(c.renderer);
    delete t[a.id];
    E[a.id] = {};
    if (void 0 !== f && null !== f) {
      a.__state.dataSetDuringConstruction = !0;
      if ("string" !== typeof c.dataFormat)switch (typeof f) {
        case "function":
          f = c.dataSource = f.call(a, c.dataConfiguration);
          c.dataFormat = "JSON";
          break;
        case "string":
          c.dataFormat = /^\s*?\{[\s\S]*\}\s*?$/g.test(a.options.dataFormat) ?
            "JSON" : "XML";
          break;
        case "object":
          c.dataFormat = "JSON"
      }
      c.dataFormat && c.dataFormat.toString && (a.__state.dataFetchDuringConstruction = b.test(c.dataFormat.toString()));
      a.setChartData(f, c.dataFormat, void 0, void 0, !0)
    } else l && (a.__state.dataSetDuringConstruction = !1, d.raiseWarning(a, "1810131922A", "param", ":dataHandler~event:beforeInitialize", "Data source was not defined during construction, hence set to blank renderer default - " + l.dataFormat), a.setChartData("", l.dataFormat, void 0, void 0, !0), a.__state.dataAvailable = !1)
  });
  d.addEventListener("beforeDispose", function (a) {
    var b = a.sender;
    delete t[a.sender.id];
    delete E[a.sender.id];
    b && b.__state && b.__state.dhmXhrObj && b.__state.dhmXhrObj.abort()
  });
  d.addEventListener("disposed", function (a) {
    delete E[a.sender.id]
  });
  d.addEventListener("loaded", function (a) {
    a = a.sender;
    var b = a.__state.updatePending;
    a instanceof d.core && void 0 !== b && (delete a.__state.updatePending, d.renderer.update(a, b))
  });
  d.addEventListener("dataUpdated", function (a, b) {
    var c = a.sender, f = c.__state;
    f.rendering &&
    (f.dataFetchDuringConstruction || f.updatePending) && (delete f.dataFetchDuringConstruction, delete f.updatePending, d.renderer.update(c, b))
  });
  d.addEventListener(["dataLoadError", "dataInvalid"], function (a) {
    a.sender.__state.dataAvailable = !1
  });
  d.addEventListener("loaded", function (a) {
    a = a.sender;
    var b = a.__state, c, f, l;
    l = function (a, b) {
      return function (c) {
        return !1 === c ? b.apply(this) : this.ref.getUpdatedXMLData ? d.core.transcodeData(this.ref.getUpdatedXMLData(), "xml", a) : this.getData ? this.getData(a) : b.apply(this)
      }
    };
    if (a.chartType && K[a.chartType()] && K[a.chartType()][0]) {
      for (c in d.transcoders)f = d.transcoders[c].name, f = "get" + f + "Data", a[f] = l(c, a.constructor.prototype[f]), a[f]._dynamicdatarouter = !0;
      b.dynamicDataRoutingEnabled = !0
    } else if (b.dynamicDataRoutingEnabled) {
      for (c in d.transcoders)f = d.transcoders[c].name, f = "get" + f + "Data", a.hasOwnProperty(f) && a[f]._dynamicdatarouter && delete a[f];
      b.dynamicDataRoutingEnabled = !1
    }
  })
}]);
"object" !== typeof JSON && (JSON = {});
(function () {
  function d(b) {
    return 10 > b ? "0" + b : b
  }

  function k(b) {
    E.lastIndex = 0;
    return E.test(b) ? '"' + b.replace(E, function (a) {
      var b = G[a];
      return "string" === typeof b ? b : "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
    }) + '"' : '"' + b + '"'
  }

  function B(d, a) {
    var q, t, C, f, g = b, s, n = a[d];
    n && "object" === typeof n && "function" === typeof n.toJSON && (n = n.toJSON(d));
    "function" === typeof c && (n = c.call(a, d, n));
    switch (typeof n) {
      case "string":
        return k(n);
      case "number":
        return isFinite(n) ? String(n) : "null";
      case "boolean":
      case "null":
        return String(n);
      case "object":
        if (!n)return "null";
        b += K;
        s = [];
        if ("[object Array]" === Object.prototype.toString.apply(n)) {
          f = n.length;
          for (q = 0; q < f; q += 1)s[q] = B(q, n) || "null";
          C = 0 === s.length ? "[]" : b ? "[\n" + b + s.join(",\n" + b) + "\n" + g + "]" : "[" + s.join(",") + "]";
          b = g;
          return C
        }
        if (c && "object" === typeof c)for (f = c.length, q = 0; q < f; q += 1)"string" === typeof c[q] && (t = c[q], (C = B(t, n)) && s.push(k(t) + (b ? ": " : ":") + C)); else for (t in n)Object.prototype.hasOwnProperty.call(n, t) && (C = B(t, n)) && s.push(k(t) + (b ? ": " : ":") + C);
        C = 0 === s.length ? "{}" : b ? "{\n" + b + s.join(",\n" +
        b) + "\n" + g + "}" : "{" + s.join(",") + "}";
        b = g;
        return C
    }
  }

  "function" !== typeof Date.prototype.toJSON && (Date.prototype.toJSON = function () {
    return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + d(this.getUTCMonth() + 1) + "-" + d(this.getUTCDate()) + "T" + d(this.getUTCHours()) + ":" + d(this.getUTCMinutes()) + ":" + d(this.getUTCSeconds()) + "Z" : null
  }, String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function () {
    return this.valueOf()
  });
  var t = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
    E = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, b, K, G = {
      "\b": "\\b",
      "\t": "\\t",
      "\n": "\\n",
      "\f": "\\f",
      "\r": "\\r",
      '"': '\\"',
      "\\": "\\\\"
    }, c;
  "function" !== typeof JSON.stringify && (JSON.stringify = function (d, a, q) {
    var k;
    K = b = "";
    if ("number" === typeof q)for (k = 0; k < q; k += 1)K += " "; else"string" === typeof q && (K = q);
    if ((c = a) && "function" !== typeof a && ("object" !== typeof a || "number" !== typeof a.length))throw Error("JSON.stringify");
    return B("", {"": d})
  });
  "function" !== typeof JSON.parse && (JSON.parse = function (b, a) {
    function c(b, f) {
      var g, d, n = b[f];
      if (n && "object" === typeof n)for (g in n)Object.prototype.hasOwnProperty.call(n, g) && (d = c(n, g), void 0 !== d ? n[g] = d : delete n[g]);
      return a.call(b, f, n)
    }

    var d;
    b = String(b);
    t.lastIndex = 0;
    t.test(b) && (b = b.replace(t, function (a) {
      return "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
    }));
    if (/^[\],:{}\s]*$/.test(b.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
        "]").replace(/(?:^|:|,)(?:\s*\[)+/g, "")))return d = eval("(" + b + ")"), "function" === typeof a ? c({"": d}, "") : d;
    throw new SyntaxError("JSON.parse");
  })
})();
FusionCharts.register("module", ["private", "modules.data.json", function () {
  var d = this, k = d.window, B = k.document, t = d.xssEncode, E, b;
  void 0 === k.JSON && d.raiseError(this, "1113062012", "run", "JSONDataHandler", Error("Could not find library support for JSON parsing."));
  d.policies.options.allowIESafeXMLParsing = ["_allowIESafeXMLParsing", !0];
  E = function () {
    var b = {
        set: !0,
        trendlines: !0,
        vtrendlines: !0,
        line: {trendlines: !0, vtrendlines: !0},
        data: !0,
        dataset: !0,
        lineset: !0,
        categories: !0,
        category: !0,
        linkeddata: !0,
        application: !0,
        definition: !0,
        axis: !0,
        connectors: !0,
        connector: {connectors: !0},
        trendset: !0,
        row: {rows: !0},
        column: {columns: !0},
        label: {labels: !0},
        color: {colorrange: !0},
        dial: {dials: !0},
        pointer: {pointers: !0},
        point: {trendpoints: !0},
        process: {processes: !0},
        task: {tasks: !0},
        milestone: {milestones: !0},
        datacolumn: {datatable: !0},
        text: {datacolumn: !0},
        item: {legend: !0},
        alert: {alerts: !0},
        groups: {annotations: !0},
        items: {groups: !0, data: !0},
        shapes: !0,
        shape: {shapes: !0},
        entitydef: !0,
        entity: {entitydef: !0}
      }, t = {
        chart: "linkedchart",
        map: "linkedmap",
        set: "data",
        vline: {chart: "data", graph: "data", dataset: "data", categories: "category", linkedchart: "data"},
        apply: {application: "application"},
        style: {definition: "definition"},
        marker: {application: "application", definition: "definition", data: "items"},
        entity: {entitydef: "entitydef", data: "data"},
        shape: {shapes: "shapes"},
        connector: {
          connectors: {
            chart: "connector",
            linkedchart: "connector",
            map: "connectors",
            linkedmap: "connectors"
          }
        },
        annotationgroup: {annotations: "groups"},
        annotation: {groups: "items"}
      }, c = {vline: {vline: "true"}},
      l = {chart: !0, map: !0, graph: !0}, a = {dataset: "data", categories: "category"}, q = {
        target: "target",
        value: "value"
      }, E = {
        styles: {definition: !0, application: !0},
        chart: {value: !0, target: !0},
        graph: {value: !0, target: !0},
        linkedchart: {value: !0, target: !0},
        markers: {definition: !0, application: !0, shapes: !0, connectors: !0, data: !0},
        map: {entitydef: !0, data: !0},
        linkedmap: {entitydef: !0, data: !0}
      }, C, f;
    C = {
      append: function (a, c, f, d) {
        !b[f] || !0 !== b[f] && !0 !== b[f][d] ? c[f] = a : (c[f]instanceof Array || (c[f] = []), c[f].push(a))
      }, child: function (b, f,
                          n, k) {
        var A, m, P, z, X, u;
        for (A = 0; A < f.length; A += 1)switch (P = f[A], m = P.nodeName.toLowerCase(), P.nodeType) {
          case 1:
            z = C.attr(P.attributes);
            u = l[m];
            !0 === u && (X = z, z = {}, z[m] = X);
            u = c[m];
            "object" === typeof u && d.extend(z, u);
            if (u = t[m])if ("object" === typeof u && "object" === typeof u[n])for (X in X = void 0, u[n]) {
              if (k[X]) {
                m = u[n][X];
                break
              }
            } else"object" === typeof u && "string" === typeof u[n] ? m = u[n] : "string" === typeof u && (m = u);
            P.childNodes.length && ((u = E[n]) && u[m] ? C.child(b, P.childNodes, m, k) : C.child(z, P.childNodes, m, k));
            (u = E[n]) && u[m] ||
            C.append(z, b, m, n);
            break;
          case 3:
            if (u = q[n])m = u, z = P.data, C.append(z, b, m, n);
            u = a[n];
            "string" === typeof u && k.chart && parseInt(k.chart.compactdatamode, 10) && (m = u, z = P.data, b[m] = b[m] ? b[m] + z : z)
        }
      }, attr: function (a) {
        var b, c = {};
        if (!a || !a.length)return c;
        for (b = 0; b < a.length; b += 1)c[a[b].nodeName.toLowerCase()] = a[b].value || a[b].nodeValue;
        return c
      }
    };
    f = function (a) {
      var b = {}, c, l, q, m, P, z, X, u, t;
      if ("object" !== typeof a && a && "function" !== typeof a.toString)return f.errorObject = new TypeError("xml2json.parse()"), b;
      a = a.toString().replace(/<\!--[\s\S]*?--\x3e/g,
        "").replace(/<\?xml[\s\S]*?\?>/ig, "").replace(/&(?!([^;\n\r]+?;))/g, "&amp;$1");
      a = a.replace(/^\s\s*/, "");
      for (var E = /\s/, G = a.length; E.test(a.charAt(--G)););
      a = a.slice(0, G + 1);
      if (!a)return b;
      try {
        k.DOMParser ? c = (new k.DOMParser).parseFromString(a, "text/xml") : B.body && d.core.options.allowIESafeXMLParsing ? (l = B.createElement("xml"), l.innerHTML = a, B.body.appendChild(l), c = l.XMLDocument, B.body.removeChild(l)) : (c = new k.ActiveXObject("Microsoft.XMLDOM"), c.async = "false", c.loadXML(a));
        if (!(c && c.childNodes && 1 === c.childNodes.length &&
          (q = c.childNodes[0]) && q.nodeName && (m = q.nodeName.toLowerCase())) || "chart" !== m && "map" !== m && "graph" !== m)return f.errorObject = new TypeError("xml2json.parse()"), b;
        if ("graph" === m) {
          P = c.createElement("chart");
          for (t = (X = q.attributes) && X.length || 0; t--;)P.setAttribute(X[t].name, X[t].value), X.removeNamedItem(X[t].name);
          if (t = (u = q.childNodes) && u.length || 0)--t, z = q.removeChild(u[t]), P.appendChild(z);
          for (; t--;)z = q.removeChild(u[t]), P.insertBefore(z, P.firstChild);
          c.replaceChild(P, q);
          q = P
        }
      } catch (Z) {
        f.errorObject = Z
      }
      q ? (q.attributes &&
      (b[m] = C.attr(q.attributes)), q.childNodes && C.child(b, q.childNodes, m, b), delete f.errorObject) : f.errorObject = new TypeError("xml2json.parse()");
      return b
    };
    return function (a) {
      delete f.errorObject;
      return {data: f(a), error: f.errorObject}
    }
  }();
  b = function () {
    var b, d;
    b = {
      items: {
        explode: {data: "set", groups: {annotations: "annotationgroup"}, items: {groups: "annotation"}},
        text: {chart: {target: "target", value: "value"}, graph: {target: "target", value: "value"}},
        dsv: {dataset: {data: "dataset"}, categories: {category: "categories"}},
        attr: {
          chart: {chart: "chart"},
          graph: {graph: "graph"}, map: {map: "map"}, linkedmap: {map: "map"}, linkedchart: {chart: "chart"}
        },
        group: {
          styles: {definition: "style", application: "apply"},
          map: {data: "entity", entitydef: "entity"},
          markers: {
            definition: "marker",
            application: "marker",
            shapes: "shape",
            connectors: "connector",
            items: "marker"
          }
        },
        tag: {markers: {items: "data"}}
      }, qualify: function (b, d, a) {
        return "object" === typeof this.items[b][a] ? this.items[b][a][d] : this.items[b][a]
      }
    };
    d = function (c, l, a, q) {
      var k = "", C = "", f = "", g = "", s, n, Q;
      l && "function" === typeof l.toLowerCase &&
      (l = l.toLowerCase());
      if (void 0 === a && c[l])for (s in c[l])n = s.toLowerCase(), "compactdatamode" === n && (q.applyDSV = 1 == c[l][s]);
      if (c instanceof Array)for (s = 0; s < c.length; s += 1)f = "string" === typeof c[s] ? f + t(c[s]) : f + d(c[s], l, a, q); else {
        for (s in c)n = s.toLowerCase(), c[s]instanceof Array && (Q = b.qualify("group", n, l)) ? (f = b.qualify("tag", n, l) || n, C += "<" + f + ">" + d(c[s], Q, l, q) + "</" + f + ">") : "object" === typeof c[s] ? (Q = b.qualify("attr", n, l)) ? (g = d(c[s], Q, l, q).replace(/\s*\/\>/ig, ""), l = n) : C += d(c[s], n, l, q) : q.applyDSV && (Q = b.qualify("dsv",
          n, l)) ? C += c[s] : (Q = b.qualify("text", n, l)) ? (f = b.qualify("tag", n, l) || Q, C += "<" + f + ">" + c[s] + "</" + f + ">") : "vline" === n && Boolean(c[s]) ? l = "vline" : k += " " + n + '="' + t(c[s]).toString().replace(/\"/ig, "&quot;") + '"';
        if (Q = b.qualify("explode", a, l))l = Q;
        f = l;
        f = ("" !== g ? g : "<" + f) + k + ("" !== C ? ">" + C + "</" + f + ">" : " />")
      }
      return f
    };
    return function (b) {
      delete d.errorObject;
      if (b && "string" === typeof b)try {
        b = JSON.parse(b)
      } catch (l) {
        d.errorObject = l
      }
      return {data: d(b, b && b.graph ? "graph" : b && b.map ? "map" : "chart", void 0, {}), error: d.errorObject}
    }
  }();
  d.addDataHandler("JSON", {
    encode: b, decode: E, passthrough: function (b) {
      var d = {data: {}};
      if (!b)return d;
      if ("string" !== typeof b)try {
        b = JSON.stringify(b)
      } catch (c) {
        return d.error = c, d
      }
      try {
        d.data = JSON.parse(b.replace(/"([^"]+)":/g, function (a, b) {
          return '"' + b.toLowerCase() + '":'
        }))
      } catch (l) {
        d.error = l
      }
      return d
    }, transportable: !0
  })
}]);
FusionCharts.register("module", ["private", "modules.data.xml", function () {
  var d = function (d) {
    return {data: d, error: void 0}
  };
  this.addDataHandler("XML", {encode: d, decode: d, transportable: !0})
}]);
FusionCharts.register("module", ["private", "modules.data.csv", function () {
  var d = this, k = d.window, B = d.core, t = k.parseInt, E = k.parseFloat, b = function (b) {
    return b
  }, K;
  K = function (b) {
    this.data = [];
    this.columnCount = this.rowCount = 0;
    this.configure(b)
  };
  K.decodeLiterals = function (b, c) {
    return void 0 !== b && null !== b && b.toString ? b.replace("{tab}", "\t").replace("{quot}", '"').replace("{apos}", "'") : c
  };
  K.prototype.set = function (b, c, d) {
    var a;
    if (this.rowCount <= b) {
      for (a = this.rowCount; a <= b; a += 1)this.data[a] = [];
      this.rowCount = b + 1
    }
    this.columnCount <=
    c && (this.columnCount = c + 1);
    this.data[b][c] = d
  };
  K.prototype.setRow = function (b, c) {
    var d;
    if (this.rowCount <= b) {
      for (d = this.rowCount; d <= b; d += 1)this.data[d] = [];
      this.rowCount = b + 1
    }
    this.columnCount < c.length && (this.columnCount = c.length);
    this.data[b] = c
  };
  K.prototype.get = function (b, c) {
    var d = this.data;
    return d[b] && d[b][c]
  };
  K.prototype.configure = function (b) {
    var c = K.decodeLiterals;
    this.delimiter = c(b.delimiter, ",");
    this.qualifier = c(b.qualifier, '"');
    this.eolCharacter = c(b.eolCharacter, "\r\n");
    this.numberFormatted = !!t(b.numberFormatted,
      0)
  };
  K.prototype.clear = function () {
    this.data = [];
    this.columnCount = this.rowCount = 0
  };
  K.prototype.toString = function () {
    var b, c, d = "";
    for (b = 0; b < this.rowCount; b += 1)c = this.qualifier + this.data[b].join(this.qualifier + this.delimiter + this.qualifier) + this.qualifier, d += '""' === c ? this.eolCharacter : c + this.eolCharacter;
    0 < this.rowCount && (d = d.slice(0, d.length - 2));
    return d
  };
  d.addDataHandler("CSV", {
    encode: function (b, c) {
      d.raiseError(c, "0604111215", "run", "::CSVDataHandler.encode()", "FusionCharts CSV data-handler only supports encoding of data.");
      throw Error("FeatureNotSupportedException()");
    }, decode: function (d, c) {
      var l = B.transcodeData(d, "xml", "json") || {}, a = c.jsVars, q, k, C, f, g, s, n, Q = l.chart || l.map || l.graph || {};
      n = Boolean(Q.exporterrorcolumns || 0);
      var A = l.categories && l.categories[0] && l.categories[0].category || [], m = l.map && !l.chart || a && a.instanceAPI && "geo" === a.instanceAPI.defaultSeriesType, P = !1, z = !1, t = !1, u = !1;
      k = !1;
      var ka = b, ha = {}, ba, Z, za, Aa, Ka, $, aa, qa, ga, R, T;
      g = 0;
      q = new K({
        separator: Q.exportdataseparator,
        qualifier: Q.exportdataqualifier,
        numberFormatted: Q.exportdataformattedval
      });
      B.formatNumber && q.numberFormatted && (ka = function (a) {
        return B.formatNumber(a, Q)
      });
      if (m)for (R in ha.geo = !0, A = a.hcObj && a.hcObj.entities && a.hcObj.entities.items || [], q.setRow(0, ["Id", " Short Name", "Long Name", "Value", "Formatted Value"]), a = 0, A)z = A[R], T = z.eJSON, k = z.value, q.setRow(++a, [R, T.shortLabel, T.label, void 0 === k ? "" : k, z.formattedValue]); else if (void 0 !== (ba = l.dials && l.dials.dial || l.pointers && l.pointers.pointer || l.value))if (ha.gauge = !0, "string" === typeof ba)q.set(0, 0, ka(ba)), ha.singlevalue = !0, "string" === typeof l.target && (q.set(0, 1, ka(l.target)), ha.bullet = !0); else for (q.setRow(0, ["Id", "Value"]), ha.multivalue = !0, a = 0, s = 1, g = ba.length; a < g; a += 1, s += 1)q.setRow(s, [s, ka(ba[a].value)]); else if (ba = l.dataset || !(l.data instanceof Array) && []) {
        ha.multiseries = !0;
        C = 1;
        if (Z = l.lineset)ba = ba.concat(Z), ha.lineset = !0;
        if (za = l.axis)ba = ba.concat(za), ha.multiaxis = !0;
        $ = ba.length;
        Ka = A.length;
        if (!($ = ba.length)) {
          for (a = 0; a < Ka; a += 1)aa = A[a], q.set(a + 1, 0, aa.label || aa.name);
          ha.multilevel = !0
        }
        for (a = 0; a < $; a += 1)for (qa = ba, qa[a].dataset ? (qa =
          qa[a].dataset, f = 0, Aa = qa.length) : (qa = ba, f = a, Aa = f + 1); f < Aa && !P && !t; f += 1, C += 1) {
          m = qa[f];
          q.set(0, C, m.seriesname);
          "string" === typeof m.data && (ha.compactdata = !0, m.data = m.data.split(Q.dataseparator || "|"));
          s = g = 0;
          for (ga = m.data && m.data.length || 0; g < ga || g < Ka; g += 1) {
            aa = A[g];
            k = s + 1;
            R = m.data && m.data[s] || {};
            if (void 0 !== R.x && void 0 !== R.y) {
              P = ha.xy = !0;
              break
            }
            if (void 0 !== R.open || void 0 !== R.high || void 0 !== R.close || void 0 !== R.low) {
              u = ha.ohlc = !0;
              break
            }
            if (void 0 !== R.rowid && void 0 !== R.columnid) {
              t = ha.heatmap = !0;
              break
            }
            if (g < Ka && !aa.vline) {
              q.set(k,
                0, aa.label || aa.name);
              aa = E(R ? R.value : "");
              aa = isNaN(aa) ? "" : ka(aa);
              q.set(k, C, aa);
              if (z || n || R.errorvalue)z || q.set(0, C + 1, "Error"), T = 1, q.set(k, C + 1, ka(R.errorvalue));
              s += 1
            }
          }
          T && (C += T, T = 0)
        }
        Z && (ba = ba.slice(0, -Z.length));
        za && (ba = ba.slice(0, -za.length))
      } else if (ba = l.data) {
        q.set(0, 1, Q.yaxisname || "Value");
        ha.singleseries = !0;
        k = "1" == Q.showsumatend;
        a = 0;
        for (Ka = ba.length; a < Ka; a += 1)R = ba[a], R.vline || (aa = E(R.value ? R.value : ""), q.setRow(a + 1, [R.label || R.name, isNaN(aa) ? "" : (g += aa, ka(aa))]));
        k && (ha.summation = !0, q.setRow(a + 1, [Q.sumlabel ||
        "Total", ka(g)]))
      }
      if (u)for (q.clear(), q.setRow(0, ["Open", "Close", "High", "Low"]), a = 0, k = 1, ba = l.dataset, Aa = ba.length; a < Aa; a += 1)for (g = 0, m = ba[a] && ba[a].data || [], $ = m.length; g < $; g += 1, k += 1)R = m[g] || {}, q.setRow(g + 1, [ka(R.open), ka(R.close), ka(R.high), ka(R.low)]); else if (P)for (q.clear(), z = !1, T = 0, q.setRow(0, ["Series", "x", "y"]), a = 0, k = 1, ba = l.dataset, Aa = ba.length; a < Aa; a += 1)for (g = 0, m = ba[a] && ba[a].data || [], $ = m.length; g < $; g += 1, k += 1) {
        R = m[g] || {};
        aa = [ba[a].seriesname, ka(R.x), ka(R.y)];
        void 0 !== R.z && (aa.push(ka(R.z)), T || (q.set(0,
          3, "z"), T = 1));
        if (z || n || void 0 !== R.errorvalue || void 0 !== R.horizontalerrorvalue || void 0 !== R.verticalerrorvalue)l = ka(R.errorvalue), aa.push(R.errorvalue, void 0 === R.horizontalerrorvalue ? l : ka(R.horizontalerrorvalue), void 0 === R.verticalerrorvalue ? l : ka(R.verticalerrorvalue)), z || (q.set(0, T + 3, "Error"), q.set(0, T + 4, "Horizontal Error"), q.set(0, T + 5, "Vertical Error")), z = ha.error = !0;
        q.setRow(k, aa)
      } else if (t) {
        q.clear();
        P = {};
        t = {};
        a = 0;
        g = 1;
        A = l.rows && l.rows.row || [];
        for (n = A.length; a < n; a += 1, g += 1)aa = A[a], aa.id && (P[aa.id.toLowerCase()] =
          g, q.set(g, 0, aa.label || aa.id));
        a = 0;
        g = 1;
        A = l.columns && l.columns.column || [];
        for (n = A.length; a < n; a += 1, g += 1)aa = A[a], aa.id && (t[aa.id.toLowerCase()] = g, q.set(0, g, aa.label || aa.id));
        m = l.dataset && l.dataset[0] && l.dataset[0].data || [];
        a = 0;
        for (n = m.length; a < n; a += 1)R = m[a], k = R.rowid.toLowerCase(), C = R.columnid.toLowerCase(), P[k] || (P[k] = q.rowCount, q.set(q.rowCount, 0, R.rowid)), t[C] || (t[C] = q.columnCount, q.set(0, q.columnCount, R.columnid)), q.set(P[k], t[C], ka(R.value))
      }
      ba = A = Z = za = null;
      0 < q.rowCount && void 0 === q.get(0, 0) && q.set(0,
        0, Q.xaxisname || "Label");
      return {data: q.toString(), error: void 0, predictedFormat: ha}
    }, transportable: !1
  });
  B.addEventListener("Loaded", function (b) {
    b = b.sender;
    "javascript" !== b.options.renderer || b.getDataAsCSV || (b.getDataAsCSV = b.ref.getDataAsCSV = b.getCSVData)
  })
}]);
FusionCharts.register("module", ["private", "modules.renderer.js", function () {
  var d = this, k = d.window, B = k.document, t = d.core.options, E = /msie/i.test(k.navigator.userAgent) && !k.opera, b = Boolean(k.SVGAngle || B.implementation.hasFeature("http://www.w3.org/TR/SVG11/feature#BasicStructure", "1.1")), K = function () {
  }, G = d.hcLib = {cmdQueue: []}, c = G.moduleCmdQueue = {
    base: [],
    charts: [],
    powercharts: [],
    widgets: [],
    maps: []
  }, l = G.moduleDependencies = {}, a = G.moduleMeta = {
    base: "fusioncharts.js", charts: "fusioncharts.charts.js", powercharts: "fusioncharts.powercharts.js",
    widgets: "fusioncharts.widgets.js", maps: "fusioncharts.maps.js"
  }, q = {}, S = G.getMetaSentence = function () {
    var a = {};
    return function (b) {
      b = b && b.replace(/(^\s*)|(\s*$)/g, "") || "";
      return a[b] || (a[b] = {key: b, subject: b.replace(/[^\/]*?$/ig, ""), predicate: b.replace(/^.*\//ig, "")})
    }
  }(), C = G.getDependentModuleName = function (a) {
    var b = [], c, f;
    a = S(a).predicate;
    for (c in l)void 0 !== (f = l[c][a]) && (b[f] = c);
    return b
  }, f = G.hasModule = function (a) {
    var b, c;
    if (a instanceof Array) {
      b = 0;
      for (c = a.length; b < c; b += 1)if (!Boolean(d.modules["modules.renderer.js-" +
        S(a[b]).predicate]))return !1;
      return !0
    }
    return Boolean(d.modules["modules.renderer.js-" + S(a).predicate])
  }, g = G.loadModule = function (b, c, g, n) {
    b instanceof Array || (b = [b]);
    var s = b.length, l = 0, k;
    k = function () {
      if (l >= s)c && c(); else {
        var A = b[l], C = A && A.match(/[^\/]*$/i)[0], za = a[A];
        l += 1;
        if (A) {
          if (f(C)) {
            k();
            return
          }
          if (q[C]) {
            d.raiseError(n || d.core, "1112201445A", "run", "JavaScriptRenderer~loadModule() ", "required resources are absent or blocked from loading.");
            g && g(C);
            return
          }
        } else g && g(C);
        A = d.core.options["html5" + d.capitalizeString(C) +
        "Src"];
        d.loadScript(void 0 === A ? za : A, {
          success: function () {
            f(C) ? k() : g && g(C)
          }, failure: g && function () {
            g(C)
          }
        }, void 0, !0)
      }
    };
    k()
  }, s = G.executeWaitingCommands = function (a) {
    for (var b; b = a.shift();)"object" === typeof b && K[b.cmd].apply(b.obj, b.args)
  }, n = function (a) {
    delete a.sender.jsVars._reflowData;
    a.sender.jsVars._reflowData = {};
    delete a.sender.jsVars._reflowClean
  }, Q = function () {
    var a = function () {
    };
    a.prototype = {
      LoadDataErrorText: "Error in loading data.",
      XMLLoadingText: "Retrieving data. Please wait",
      InvalidXMLText: "Invalid data.",
      ChartNoDataText: "No data to display.",
      ReadingDataText: "Reading data. Please wait",
      ChartNotSupported: "Chart type not supported.",
      PBarLoadingText: "",
      LoadingText: "Loading chart. Please wait",
      RenderChartErrorText: "Unable to render chart."
    };
    return a.prototype.constructor = a
  }(), A = G.getContainerBackgroundColor = function (a) {
    var c = a.options.containerBackgroundColor, f = a.options.containerBackgroundOpacity, d = a.jsVars.transparent;
    void 0 !== d && null !== d ? f = a.jsVars.transparent ? 0 : 1 : (f = parseFloat(f), 0 > f ? f = 0 : 1 < f && (f = 1));
    c || (c = "#ffffff");
    if (E && !b)return f ? c : "transparent";
    c = c.replace(/^#?([a-f0-9]+)/ig, "$1");
    c = G.graphics.HEXtoRGB(c);
    c[3] = f.toString();
    return "rgba(" + c.join(",") + ")"
  };
  G.injectModuleDependency = function (a, b, f) {
    var d = !1, g = S(a).subject;
    a = S(a).predicate;
    b = void 0 === b ? a : S(b).predicate;
    l[a] || (l[a] = {}, c[a] || (c[a] = [], G.moduleMeta[a] = g + t.html5ScriptNamePrefix + (b && b.replace && b.replace(/^[\s\S]*\//ig, "").replace(/\?/g, "%3F").replace(/\#/g, "%23").replace(/\:/g, "%3A") || "") + t.html5ScriptNameSuffix), d = !0);
    l[a][b] = f || 0;
    return d
  };
  G.needsModule = function (a, b) {
    a = S(a).predicate;
    b = S(b).predicate;
    return void 0 !== (G.moduleDependencies[a] && G.moduleDependencies[a][b])
  };
  G.cleanupWaitingCommands = function (a) {
    for (var b = a.chartType(), b = C(b), f, d = [], g; f = b.shift();) {
      for (f = c[f] || []; g = f.shift();)"object" === typeof g && g.obj !== a && d.push(g);
      f.concat(d);
      d = []
    }
  };
  d.extend(d.core.options, {html5ScriptNameSuffix: ".js", html5ScriptNamePrefix: "fusioncharts."});
  d.extend(K, {
    dataFormat: "json", ready: !1, policies: {
      jsVars: {}, options: {
        showChartLoadingMessage: ["showChartLoadingMessage",
          !0]
      }
    }, init: function () {
      f("base") ? K.ready = !0 : g("base", function () {
        K.ready = !0;
        s(G.cmdQueue)
      }, void 0, d.core)
    }, render: function (a) {
      var b = a, c = this.jsVars.msgStore;
      b && this.options.showChartLoadingMessage && (b.innerHTML = '<small style="display: inline-block; *zoom:1; *display:inline; width: 100%; font-family: Verdana,sans; font-size: 10px; color: #666666; text-align: center; padding-top: ' + (parseInt(b.style.height, 10) / 2 - 5) + 'px">' + (c.PBarLoadingText || c.LoadingText) + "</small>", b.style.backgroundColor = A(this));
      G.cmdQueue.push({cmd: "render", obj: this, args: arguments})
    }, update: function () {
      G.cmdQueue.push({cmd: "update", obj: this, args: arguments})
    }, resize: function () {
      G.cmdQueue.push({cmd: "resize", obj: this, args: arguments})
    }, dispose: function () {
      var a = G.cmdQueue, b, c;
      b = 0;
      for (c = a.length; b < c; b += 1)a[b].obj === this && (a.splice(b, 1), --c, --b)
    }, load: function () {
      G.cmdQueue.push({cmd: "load", obj: this, args: arguments})
    }, config: function (a, b) {
      var c, f = this.jsVars, d = f.msgStore, f = f.cfgStore, g = this.options, n;
      n = {
        LoadingText: "loadMessage",
        ChartNotSupported: "typeNotSupportedMessage",
        RenderChartErrorText: "renderErrorMessage",
        XMLLoadingText: "dataLoadStartMessage",
        ChartNoDataText: "dataEmptyMessage",
        LoadDataErrorText: "dataLoadErrorMessage",
        InvalidXMLText: "dataInvalidMessage"
      };
      "string" === typeof a && 1 < arguments.length && (c = a, a = {}, a[c] = b);
      for (c in a)void 0 !== d[c] ? d[c] = a[c] : f[c.toLowerCase()] = a[c], n[c] ? g[n[c]] = a[c] : g[c] = a[c]
    }, protectedMethods: {}, events: {
      beforeInitialize: function (a) {
        var b = a.sender;
        a = b.jsVars;
        var c;
        a.fcObj = b;
        a.msgStore = a.msgStore ||
        new Q;
        a.cfgStore = a.cfgStore || {};
        a.previousDrawCount = -1;
        a.drawCount = 0;
        a._reflowData = {};
        b.addEventListener("beforeRender", function (a) {
          a.sender.jsVars.smartLabel = new G.SmartLabelManager(b.id, B.body || B.getElementsByTagName("body")[0]);
          a.detachHandler()
        });
        a.userModules instanceof Array || (c = a.userModules, a.userModules = [], "string" === typeof c && (a.userModules = a.userModules.concat(c.split(","))));
        G.chartAPI && G.chartAPI[void 0] || (a.needsLoaderCall = !0)
      }, initialized: function (a) {
        a = a.sender;
        var b = a.jsVars;
        b.needsLoaderCall &&
        (delete b.needsLoaderCall, K.load.call(a))
      }, beforeDataUpdate: n, beforeDispose: function (a) {
        var b = a.sender.jsVars;
        b.smartLabel && !b.smartLabel.disposed && b.smartLabel.dispose();
        n.apply(this, arguments)
      }, beforeRender: function (a) {
        var b = a.sender.jsVars;
        delete b.drLoadAttempted;
        delete b.waitingModule;
        delete b.waitingModuleError;
        n.apply(this, arguments)
      }, dataLoadRequested: function (a) {
        a = a.sender;
        var b = a.jsVars;
        delete b.loadError;
        a.ref && a.options.showDataLoadingMessage ? b.hcObj && !b.hasNativeMessage && b.hcObj.showLoading ?
          b.hcObj.showMessage(b.msgStore.XMLLoadingText) : a.ref.showChartMessage ? a.ref.showChartMessage("XMLLoadingText") : b.stallLoad = !0 : b.stallLoad = !0
      }, dataLoadRequestCompleted: function (a) {
        delete a.sender.jsVars.stallLoad
      }, dataLoadError: function (a) {
        var b = a.sender, c = b.jsVars;
        delete c.stallLoad;
        c.loadError = !0;
        b.ref && "function" === typeof b.ref.showChartMessage && b.ref.showChartMessage("LoadDataErrorText");
        b.__state.dataFetchDuringConstruction && delete b.__state.dataFetchDuringConstruction;
        n.apply(this, arguments)
      }
    },
    _call: function (a, b, c) {
      a.apply(c || k, b || [])
    }
  });
  d.extend(K.prototype, {
    getSWFHTML: function () {
      d.raiseWarning(this, "11090611381", "run", "JavaScriptRenderer~getSWFHTML()", "getSWFHTML() is not supported for JavaScript charts.")
    }, addVariable: function () {
      d.raiseWarning(this, "11090611381", "run", "JavaScriptRenderer~addVariable()", 'Use of deprecated "addVariable()". Replace with "configure()".');
      d.core.prototype.configure.apply(this, arguments)
    }, getXML: function () {
      d.raiseWarning(this, "11171116291", "run", "JavaScriptRenderer~getXML()",
        'Use of deprecated "getXML()". Replace with "getXMLData()".');
      return this.getXMLData.apply(this, arguments)
    }, setDataXML: function () {
      d.raiseWarning(this, "11171116292", "run", "JavaScriptRenderer~setDataXML()", 'Use of deprecated "setDataXML()". Replace with "setXMLData()".');
      return this.setXMLData.apply(this, arguments)
    }, setDataURL: function () {
      d.raiseWarning(this, "11171116293", "run", "JavaScriptRenderer~setDataURL()", 'Use of deprecated "SetDataURL()". Replace with "setXMLUrl()".');
      return this.setXMLUrl.apply(this,
        arguments)
    }, hasRendered: function () {
      return !(!this.jsVars.hcObj || !this.jsVars.hcObj.hasRendered)
    }, setTransparent: function (a) {
      var b;
      if (b = this.jsVars)"boolean" !== typeof a && null !== a && (a = !0), b.transparent = null === a ? !1 : !0 === a ? !0 : !1
    }
  });
  d.extend(d.core, {
    _fallbackJSChartWhenNoFlash: function () {
      k.swfobject.hasFlashPlayerVersion(d.core.options.requiredFlashPlayerVersion) || d.renderer.setDefault("javascript")
    }, _enableJSChartsForSelectedBrowsers: function (a) {
      void 0 !== a && null !== a && d.renderer.setDefault((new RegExp(a)).test(k.navigator.userAgent) ?
        "javascript" : "flash")
    }, _doNotLoadExternalScript: function (b) {
      var c, f;
      for (c in b)f = c.toLowerCase(), a[f] && (q[f] = Boolean(b[c]))
    }, _preloadJSChartModule: function () {
      throw"NotImplemented()";
    }
  });
  d.renderer.register("javascript", K);
  b || E ? d.renderer.setDefault("javascript") : k.swfobject && k.swfobject.hasFlashPlayerVersion && !k.swfobject.hasFlashPlayerVersion(d.core.options.requiredFlashPlayerVersion) && (d.raiseWarning(d.core, "1204111846", "run", "JSRenderer", "Switched to JavaScript as default rendering due to absence of required Flash Player."),
    d.renderer.setDefault("javascript"))
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-lib", function () {
  var d = this, k = d.window, B = k.document, t = k.navigator, E = Boolean(k.SVGAngle || B.implementation.hasFeature("http://www.w3.org/TR/SVG11/feature#BasicStructure", "1.1")), b = /msie/i.test(t.userAgent) && !k.opera, K = k.parseFloat, G = /\s+/g, c = /^#?/, l = /^rgba/i, a = /[#\s]/ig, q = /\{br\}/ig, S = /(^#[0-9A-F]{6}$)|(^#[0-9A-F]{3}$)/i, C = Math.abs, f = Math.pow, g = Math.round, s = f(2, -24), n = Object.prototype.toString, Q = "ontouchstart"in B, t = Q && !(t.maxTouchPoints ||
      t.msMaxTouchPoints), A = "http://www.fusioncharts.com?BS=FCHSEvalMark&utm_source=FCS_trial&pver=" + k.escape(d.core.version), m = !/fusioncharts\.com$/i.test(k.location.hostname), P = Math, z = P.max, X = P.min, u = {
      pageX: 0,
      pageY: 0
    }, ka = d.hcLib || (d.hcLib = {}), ha = function (a) {
      var b = a.data, c = b.chart, p = c.paper, h = a.state, f = R(ga(a.originalEvent)), d = f.target || f.originalTarget || f.srcElement || f.relatedTarget || f.fromElement, g = c.elements.resizeBox, m = b.layerX = f.pageX - b.chartPosLeft, n = b.layerY = f.pageY - b.chartPosTop, J = m - b.ox, s = n - b.oy,
        l = b.bBox, L = b.ox, u = b.oy, q = b.zoomX, U = b.zoomY, l = b.canvasY, k = b.canvasX, A = b.canvasW, P = b.canvasH, T = b.canvasX2, za = b.canvasY2, da = b.strokeWidth, J = b.attr;
      switch (h) {
        case "start":
          a = Z(this);
          b.chartPosLeft = a.left;
          b.chartPosTop = a.top;
          m = f.pageX - b.chartPosLeft;
          n = f.pageY - b.chartPosTop;
          b.oy = n;
          b.ox = m;
          b.allowMove = !1;
          g || (g = c.elements.resizeBox = p.rect(c.layers.tracker).attr(J));
          m > k && m < T && n > l && n < za && (b.allowMove = !0);
          d && d.ishot && (b.allowMove = !1);
          g.attr({x: 0, y: 0, width: 0, height: 0}).show();
          break;
        case "end":
          l = g.getBBox();
          c = {
            chart: c,
            selectionLeft: l.x,
            selectionTop: l.y,
            selectionHeight: l.height,
            selectionWidth: l.width,
            originalEvent: a.originalEvent
          };
          b.isDragged && (b.selectionEnd && b.selectionEnd(c), b.isDragged = 0);
          g.hide();
          delete b.oy;
          delete b.ox;
          break;
        default:
          if (!b.allowMove)break;
          J = m - b.ox;
          s = n - b.oy;
          L = b.ox;
          u = b.oy;
          b.isDragged || (c = {
            chart: c,
            selectionLeft: (q ? X(L, L + J) : k) + .5 * da,
            selectionTop: (U ? X(u, u + s) : l) + .5 * da,
            selectionHeight: 0,
            selectionWidth: 0,
            originalEvent: a.originalEvent
          }, b.selectionStart && b.selectionStart(c), b.isDragged = 1);
          J = -(L - X(L -
          (L - z(L + J, k)), T));
          s = -(u - X(u - (u - z(u + s, l)), za));
          g.attr({
            x: (q ? X(L, L + J) : k) + .5 * da,
            y: (U ? X(u, u + s) : l) + .5 * da,
            width: q ? C(J) : A,
            height: U ? C(s) : P
          })
      }
    }, ba = function (a) {
      var b = a.data;
      a = a.originalEvent;
      var c = a.target || a.originalTarget || a.srcElement || a.relatedTarget || a.fromElement, p = a.type, h = a.layerX, f = a.layerY;
      void 0 === h && (h = a.pageX - b.chartPosLeft, f = a.pageY - b.chartPosTop);
      "mousedown" === p && (c.ishot = h > b.canvasX && h < b.canvasX2 && f > b.canvasY && f < b.canvasY2);
      "mouseup" === p && setTimeout(function () {
        c.ishot = !1
      }, 1)
    }, P = function () {
      var a =
        "innerWidth", b = "innerHeight", c = B.documentElement || B.body, p = c;
      "innerWidth"in k ? p = k : (a = "clientWidth", b = "clientHeight");
      return function () {
        return {width: p[a], height: p[b], scrollTop: c.scrollTop, scrollLeft: c.scrollLeft}
      }
    }(), Z = function (a, b) {
      var c = {left: a.offsetLeft || 0, top: a.offsetTop || 0};
      for (a = a.offsetParent; a;)c.left += a.offsetLeft || 0, c.top += a.offsetTop || 0, a === B.body || a === B.documentElement || b || (c.left -= a.scrollLeft, c.top -= a.scrollTop), a = a.offsetParent;
      return c
    }, za = function (a) {
      return a && a.replace(/\$/g, "$$$$")
    },
    Aa = function (a, b) {
      return a || !1 === a || 0 === a ? a : b
    }, Ka = function () {
      var a, b, c;
      b = 0;
      for (c = arguments.length; b < c; b += 1)if ((a = arguments[b]) || !1 === a || 0 === a)return a;
      return ""
    }, $ = function () {
      var a, b, c;
      b = 0;
      for (c = arguments.length; b < c; b += 1)if ((a = arguments[b]) || !1 === a || 0 === a)return a
    }, aa = function (a, b, c, p) {
      return ka.dem.listen(a, b, c, p)
    }, qa = function (a, b, c) {
      return ka.dem.unlisten(a, b, c)
    }, ga = function (a) {
      a = a.sourceEvent || a.originalEvent || a;
      return Q && a && a.touches && a.touches[0] || a || u
    }, R = function () {
      var a;
      return function (b) {
        void 0 ===
        b.pageX && (b.pageX = b.clientX + (a || (a = k.document.body || k.document.documentElement)).scrollLeft, b.pageY = b.clientY + a.scrollTop);
        return b
      }
    }(), T = function (a, b) {
      b = R(ga(b));
      var c = b.pageX, p = b.pageY, h = Z(a);
      return {chartX: c - h.left, chartY: p - h.top, pageX: c, pageY: p}
    }, h = function (a) {
      return a && a.replace(/^#?([a-f0-9]+)/ig, "#$1") || "none"
    }, J = function () {
      var a, b, c;
      b = 0;
      for (c = arguments.length; b < c; b += 1)if (((a = arguments[b]) || !1 === a || 0 === a) && !isNaN(a = Number(a)))return a
    }, L = function (a, b) {
      a = a || !1 === a || 0 === a ? Number(a) : NaN;
      return isNaN(a) ?
        null : b ? C(a) : a
    }, U = function (a) {
      return "string" === typeof a ? a.replace(q, "<br />") : ""
    }, da = function (a, b) {
      for (var c = b.length, p = -1; c--;)if (a === b[c]) {
        p = c;
        break
      }
      return p
    }, fa = function () {
      if (Array.isArray)return Array.isArray;
      var a = Object.prototype.toString, b = a.call([]);
      return function (c) {
        return a.call(c) === b
      }
    }(), xa = function (a, b, c, p, h) {
      var f, d, g, m;
      h ? (p.push(a), h.push(b)) : (p = [a], h = [b]);
      if (b instanceof Array)for (f = 0; f < b.length; f += 1) {
        try {
          d = a[f], g = b[f]
        } catch (J) {
          continue
        }
        if ("object" !== typeof g)c && void 0 === g || (a[f] =
          g); else {
          if (null === d || "object" !== typeof d)d = a[f] = g instanceof Array ? [] : {};
          m = da(g, h);
          -1 !== m ? d = a[f] = p[m] : xa(d, g, c, p, h)
        }
      } else for (f in b) {
        try {
          d = a[f], g = b[f]
        } catch (s) {
          continue
        }
        if (null !== g && "object" === typeof g)if (m = n.call(g), "[object Object]" === m) {
          if (null === d || "object" !== typeof d)d = a[f] = {};
          m = da(g, h);
          -1 !== m ? d = a[f] = p[m] : xa(d, g, c, p, h)
        } else"[object Array]" === m ? (null !== d && d instanceof Array || (d = a[f] = []), m = da(g, h), -1 !== m ? d = a[f] = p[m] : xa(d, g, c, p, h)) : a[f] = g; else a[f] = g
      }
      return a
    }, ya = function (a, b, c) {
      if ("object" !== typeof a && "object" !== typeof b)return null;
      if ("object" !== typeof b || null === b)return a;
      "object" !== typeof a && (a = b instanceof Array ? [] : {});
      xa(a, b, c);
      return a
    }, Fa = function (a, b) {
      var c;
      if (b instanceof Array)for (c = b.length - 1; 0 <= c; --c)"object" !== typeof b[c] ? !0 === b[c] && a && a.splice && a.splice(c, 1) : n.call(b[c]) === n.call(a[c]) && Fa(a[c], b[c]); else for (c in b)"object" !== typeof b[c] ? !0 === b[c] && a && a.splice && a.splice(c, 1) : n.call(b[c]) === n.call(a[c]) && Fa(a[c], b[c]);
      return a
    }, Da = function () {
      var a = /^@window_/g;
      return function (b,
                       c) {
        var p = b.replace(/\[[\'\"]/g, ".").replace(/[\'\"]\]/g, "").replace(/\[/g, ".@window_").replace(/\]/g, "").split("."), h = k, f, d;
        d = "";
        var g, m, J;
        m = p.length;
        for (J = 0; J < m; J += 1) {
          g = p[J];
          f = h;
          if (g.match(a))d = k[g.replace(a, "")], h = h[d]; else {
            if (void 0 === h || null === h)throw(d || g).replace(a, "") + " is not defined";
            h = h[g]
          }
          d = g
        }
        !h || "function" !== typeof h.call && h !== k.alert ? setTimeout(function () {
          throw g.replace(a, "") + "() is not a function";
        }, 0) : h === k.alert ? h(c) : h.call(f, c)
      }
    }(), na = function () {
      var a = "FusionChartslinkEval" + parseInt(+new Date,
          10);
      return function (b) {
        try {
          k[a] = new Function(b), eval('window["' + a + '"]();')
        } catch (c) {
          setTimeout(function () {
            throw c;
          }, 0)
        }
        E ? delete k[a] : k[a] = null
      }
    }(), ua = function (a, b) {
      a = Number(a);
      a = isNaN(a) ? 100 : a;
      void 0 !== b && (a = a * b / 100);
      return a % 101
    }, Ea = function (a, b, c) {
      a = a.split(",");
      var p;
      void 0 !== c && (c = J(c.split(",")[0]));
      a[0] = ua(a[0], c);
      for (p = 1; p < b; p += 1)a[p] = a[0] * ua(a[p], c) / 100;
      return a.join(",")
    }, pa = function (b, c, h) {
      var p = 0, f = 0, d = 0;
      h && h.match(l) && (h = h.split(","), p = h[0].slice(h[0].indexOf("(") + 1), f = h[1], d = h[2], c ||
      0 === c || (c = parseInt(100 * h[3].slice(0, h[3].indexOf(")")), 10)));
      if (b)if (b.match(l))h = b.split(","), p = h[0].slice(h[0].indexOf("(") + 1), f = h[1], d = h[2]; else {
        b = b.replace(a, "").split(",")[0];
        switch (b.length) {
          case 3:
            b = b.charAt(0) + b.charAt(0) + b.charAt(1) + b.charAt(1) + b.charAt(2) + b.charAt(2);
            break;
          case 6:
            break;
          default:
            b = (b + "FFFFFF").slice(0, 6)
        }
        p = parseInt(b.slice(0, 2), 16);
        f = parseInt(b.slice(2, 4), 16);
        d = parseInt(b.slice(4, 6), 16)
      }
      c || 0 === c || (c = 100);
      "string" === typeof c && (c = c.split(",")[0]);
      c = parseInt(c, 10) / 100;
      return "rgba(" +
        p + "," + f + "," + d + "," + c + ")"
    }, bb = function () {
      var a = {};
      return function (b) {
        var c = (b = b || this) && b.FCcolor || b, p = c.color, h = c.ratio, f = c.angle, d = c.alpha, g = c.r, m = c.cx, J = c.cy, n = c.fx, s = c.fy, l = c.gradientUnits, z = c.x1, L = c.y1, u = c.x2, R = c.y2, q = 1, k, U, A, P;
        if ("string" === typeof b)return a[P = "~" + b] || (a[P] = b.replace(/^#?([a-f0-9]{3,6})/ig, "#$1"));
        p = p || "";
        if (!p)return k;
        P = [p, d, h, f, g, m, J, l, n, s, z, u, L, R].join("_").replace(/[\(\)\s,\xb0#]/g, "_");
        if (a[P])return a[P];
        h = h && (h + "").split(",") || [];
        d = (d || 0 === d) && (d + "").split(",") || [];
        if (p =
            p.split(","))if (k = "", 1 === p.length)A = p[0].replace(/^#?([a-f0-9]{3,6})/ig, "$1"), k = d.length ? "rgba(" + Ca(A).join(",") + "," + .01 * K(d[0]) + ")" : A.replace(/^#?([a-f0-9]{3,6})/ig, "#$1"); else {
          b = 0;
          for (U = p.length; b < U; b++)A = p[b].replace(/^#?([a-f0-9]{3,6})/ig, "$1"), isNaN(h[b]) || (h[b] = K(h[b]), A += ":" + h[b], isNaN(h[b + 1]) || (h[b + 1] = K(h[b + 1]) + h[b])), isNaN(d[b]) || "" === d[b] || (q = .01 * d[b]), p[b] = "rgba(" + Ca(A).join(",") + "," + q + ")", isNaN(h[b]) || (p[b] = p[b] + ":" + h[b]);
          k += p.join("-");
          if (void 0 !== g || void 0 !== n || void 0 !== m || c.radialGradient)k =
            "xr(" + [n, s, g, m, J, l].join() + ")" + k; else {
            k = "-" + k;
            if (void 0 !== z || void 0 !== L || void 0 !== u || void 0 !== R)k = "(" + [z, L, u, R, l].join() + ")" + k;
            void 0 === f && (f = 0);
            k = 360 - K(f) % 360 + k
          }
        }
        return a[P] = k
      }
    }(), va = function () {
      return function () {
        return ""
      }
    }(), Ya = function (b) {
      return b.replace(a, "").replace(c, "#")
    }, Ba = function (b, c) {
      c = (0 > c || 100 < c ? 100 : c) / 100;
      b = b.replace(a, "");
      var h = parseInt(b, 16), p = Math.floor(h / 65536), f = Math.floor((h - 65536 * p) / 256);
      return ("000000" + (p * c << 16 | f * c << 8 | (h - 65536 * p - 256 * f) * c).toString(16)).slice(-6)
    }, jb = function (b,
                      c) {
      c = (0 > c || 100 < c ? 100 : c) / 100;
      b = b.replace(a, "");
      var h = parseInt(b, 16), p = Math.floor(h / 65536), f = Math.floor((h - 65536 * p) / 256);
      return ("000000" + (256 - (256 - p) * c << 16 | 256 - (256 - f) * c << 8 | 256 - (256 - (h - 65536 * p - 256 * f)) * c).toString(16)).slice(-6)
    }, Ca = function (a) {
      a = parseInt(a, 16);
      var b = Math.floor(a / 65536), c = Math.floor((a - 65536 * b) / 256);
      return [b, c, Math.floor(a - 65536 * b - 256 * c)]
    }, db = function (a, b) {
      if ("object" !== typeof a)return "";
      if (a.fontSize || a["font-size"])!a.fontSize && a["font-size"] && (a.fontSize = a["font-size"], delete a["font-size"]),
        a.lineHeight = (parseFloat(a.fontSize) || b || 10) * ka.lineHeightFactor + "px", delete a["line-height"];
      !a.lineHeight && a["line-height"] && (a.lineHeight = a["line-height"], delete a["line-height"]);
      return a.lineHeight
    }, Ta = function (a, b, c, p, h) {
      var f = Ka(a.labelbordercolor, b.bordercolor, c.labelbordercolor, ""), d = $(a.labelbgcolor, b.bgcolor, c.labelbgcolor), g = J(a.labelborderthickness, b.borderthickness, c.labelborderthickness, 1);
      h = J(c.usedataplotcolorforlabels, 0) ? h || p.color : p.color;
      f = f ? pa(f, J(a.labelborderalpha, b.borderalpha,
        c.labelborderalpha, a.labelalpha, b.alpha, c.labelalpha, 100)) : "";
      a = {
        fontFamily: $(a.labelfont, b.font, c.labelfont, p.fontFamily),
        fontSize: $(a.labelfontsize, b.fontsize, c.labelfontsize, parseInt(p.fontSize, 10)) + "px",
        color: pa($(a.labelfontcolor, b.fontcolor, c.labelfontcolor, h), J(a.labelfontalpha, b.fontalpha, c.labelfontalpha, a.labelalpha, b.alpha, c.labelalpha, 100)),
        fontWeight: J(a.labelfontbold, b.fontbold, c.labelfontbold) ? "bold" : "normal",
        fontStyle: J(a.labelfontitalic, b.fontitalic, c.labelfontitalic) ? "italic" : "normal",
        border: f || d ? g + "px solid" : "",
        borderColor: f,
        borderThickness: g,
        borderPadding: J(a.labelborderpadding, b.borderpadding, c.labelborderpadding, 2),
        borderRadius: J(a.labelborderradius, b.borderradius, c.labelborderradius, 0),
        backgroundColor: d ? pa(d, J(a.labelbgalpha, b.bgalpha, c.labelbgalpha, a.labelalpha, b.alpha, c.labelalpha, 100)) : "",
        borderDash: J(a.labelborderdashed, b.borderdashed, c.labelborderdashed, 0) ? eb(J(a.labelborderdashlen, b.borderdashlen, c.labelborderdashlen, 4), J(a.labelborderdashgap, b.borderdashgap, c.labelborderdashgap,
          2), g) : "none"
      };
      a.lineHeight = db(a);
      return a
    }, ra = function () {
      var a = {
        top: {align: "center", verticalAlign: "top", textAlign: "center"},
        right: {align: "right", verticalAlign: "middle", textAlign: "left"},
        bottom: {align: "center", verticalAlign: "bottom", textAlign: "center"},
        left: {align: "left", verticalAlign: "middle", textAlign: "right"}
      }, b = /([^\,^\s]+)\)$/g, c = function (a, b) {
        var c;
        /^(bar|bar3d)$/.test(a) && (this.isBar = !0, this.yPos = "bottom", this.yOppPos = "top", this.xPos = "left", this.xOppPos = "right");
        c = parseInt(b.labelstep, 10);
        this.labelStep =
          1 < c ? c : 1;
        this.showLabel = J(b.showlabels, b.shownames, 1);
        this.is3D = /3d$/.test(a)
      };
      c.prototype = {
        isBar: !1,
        yPos: "left",
        yOppPos: "right",
        xPos: "bottom",
        xOppPos: "top",
        addAxisGridLine: function (c, h, f, d, H, g, m, J) {
          var n = "" === f ? !1 : !0, s = 0 < d || 0 < g.match(b)[1] ? !0 : !1, l;
          if (n || s)s || (g = "rgba(0,0,0,0)", d = .1), l = {
            isGrid: !0,
            width: d,
            dashStyle: H,
            color: g,
            value: h,
            zIndex: void 0 === m ? 2 : m
          }, n && (h = c.opposite ? J ? this.xOppPos : this.yOppPos : J ? this.xPos : this.yPos, h = a[h], l.label = {
            text: f, style: c.labels.style, textAlign: h.textAlign, align: h.align,
            verticalAlign: h.verticalAlign, rotation: 0, x: 0, y: 0
          }), c.plotLines.push(l);
          return l
        },
        addAxisAltGrid: function (a, b) {
          if (!this.is3D) {
            var c = J(a._lastValue, a.min), h = $(a._altGrid, !1);
            h && a.plotBands.push({isGrid: !0, color: a.alternateGridColor, to: b, from: c, zIndex: 1});
            a._lastValue = b;
            a._altGrid = !h
          }
        },
        addXaxisCat: function (b, c, h, I, f, d, H, g) {
          var m = a[b.opposite ? this.xOppPos : this.xPos];
          c = {
            isGrid: !0, isDataLabel: !0, width: .1, color: "rgba(0,0,0,0)", value: c, label: {
              text: I, link: $(f.labellink, d.link, H.labellink), style: Ta(f, d, H, b.labels.style,
                g), textAlign: m.textAlign, align: m.align, verticalAlign: m.verticalAlign, rotation: 0, x: 0, y: 0
            }
          };
          0 !== h % this.labelStep && (c.stepped = !0, c.label.style = b.steppedLabels.style);
          b.plotLines.push(c)
        },
        addVline: function (a, b, c, h) {
          h = h._FCconf;
          var I = h.isBar, f = h.divlineStyle, d = U(b.label), H = Boolean(J(b.showlabelborder, h.showVLineLabelBorder, 1)), g = Boolean(J(b.showlabelbackground, 1)), m = $(b.labelhalign, I ? "left" : "center"), n = $(b.labelvalign, I ? "middle" : "bottom").toLowerCase(), s = J(b.labelposition, 0), l = J(b.lineposition, .5), z = J(b.showvlines,
            h.showVLines, 1), L = J(b.alpha, h.vLineAlpha, 80), u = $(b.color, h.vLineColor).replace(/^#?/, "#"), R = g ? $(b.labelbgcolor, h.vLineLabelBgColor, "333333").replace(/^#?/, "#") : "", k = $(b.labelcolor, h.vLineLabelColor, b.color, h.vLineColor).replace(/^#?/, "#"), q = J(b.thickness, h.vLineThickness, 1), A = .5 * q, P = Boolean(Number($(b.dashed, 0))), T = J(b.dashlen, 5), za = J(b.dashgap, 2), C = h.smartLabel, da = parseInt(f.fontSize, 10) + 2, t = 0, fa = J(b.rotatelabel, h.rotateVLineLabels) ? 270 : 0, l = 0 > l || 1 < l ? .5 : l, s = 0 > s || 1 < s ? 0 : s;
          C.setStyle(f);
          C = C.getOriSize(d);
          u = pa(u, z ? L : "0");
          if (I) {
            switch (n) {
              case "top":
                da -= C.height + A + 2;
                break;
              case "middle":
                da -= .5 * C.height + 1;
                break;
              default:
                da += A
            }
            b.labelhalign || (t -= C.width * s)
          } else {
            switch (n) {
              case "top":
                da = .5 * -C.height + 1;
                break;
              case "middle":
                da = 0;
                break;
              default:
                da = .5 * C.height
            }
            switch (m) {
              case "left":
                t += q;
                break;
              case "right":
                t -= q + 1
            }
          }
          a.plotLines.push({
            isVline: !0,
            color: u,
            width: q,
            value: c - 1 + l,
            zIndex: J(b.showontop, h.showVLinesOnTop) ? 5 : 3,
            dashStyle: P ? eb(T, za, q) : "none",
            label: {
              text: d,
              align: I ? "left" : "center",
              offsetScale: s,
              rotation: fa,
              y: da,
              x: t,
              textAlign: m,
              backgroundColor: R,
              borderWidth: z && H ? 1 : 0,
              borderType: z && H ? "solid" : "",
              borderColor: z && H ? k : "",
              backgroundOpacity: z && g ? $(b.labelbgalpha, h.vLineLabelBgAlpha) / 100 : 0,
              style: {
                color: z ? k : u,
                fontSize: f.fontSize,
                fontFamily: f.fontFamily,
                lineHeight: f.lineHeight,
                backgroundColor: R
              }
            }
          })
        }
      };
      return c.prototype.constructor = c
    }(), Qb = function () {
      var a = function (a, c, h, f, d) {
        a = Math.abs(c - a);
        c = a / (h + 1);
        b(a, h, f) || (d && Number(c) / Number(f) < (1 < f ? 2 : .5) && (f /= 10), c = (Math.floor(c / f) + 1) * f, a = c * (h + 1));
        return a
      }, b = function (a, b, h) {
        return c(a /
        (b + 1)) > c(h) ? !1 : !0
      }, c = function (a) {
        a = Math.abs(a);
        a = String(a);
        var b = 0, c = a.indexOf(".");
        -1 != c && (b = a.length - c - 1);
        return b
      };
      return function (c, h, f, d, H, g, m, J) {
        var n, l, z, u, L, R, k, q = 0;
        c = !0 === isNaN(c) || void 0 === c ? .1 : c;
        h = !0 === isNaN(h) || void 0 === h ? 0 : h;
        c === h && 0 === c && (c = .1);
        H = void 0 === typeof H ? !0 : H;
        g = void 0 === typeof g ? !0 : g;
        n = Math.floor(Math.log(Math.abs(c)) / Math.LN10);
        l = Math.floor(Math.log(Math.abs(h)) / Math.LN10);
        l = Math.max(l, n);
        n = Math.pow(10, l);
        2 > Math.abs(c) / n && 2 > Math.abs(h) / n && (l--, n = Math.pow(10, l));
        l = Math.floor(Math.log(c -
        h) / Math.LN10);
        z = Math.pow(10, l);
        0 < c - h && 10 <= n / z && (n = z);
        l = (Math.floor(c / n) + 1) * n;
        0 > h ? z = -1 * (Math.floor(Math.abs(h / n)) + 1) * n : g ? z = 0 : (z = Math.floor(Math.abs(h / n) - 1) * n, z = 0 > z ? 0 : z);
        H && 0 >= c && (l = 0);
        H = f || 0 === f ? !0 : !1;
        g = d || 0 === d ? !0 : !1;
        c = !1 === H || !0 === H && Number(f) < c && c - Number(f) > s ? l : Number(f);
        h = !1 === g || !0 === g && Number(d) > h && Number(d) - h > s ? z : Number(d);
        d = Math.abs(c - h);
        if (!1 === g && !1 === H && J)if (0 < c && 0 > h)for (f = !1, H = 10 < n ? n / 10 : n, J = a(h, c, m, H, !1), g = J - (m + 1) * H; !1 === f;) {
          if (g += (m + 1) * H, b(g, m, H))if (J = g - d, l = g / (m + 1), u = Math.min(Math.abs(h),
              c), z = u == Math.abs(h) ? -1 : 1, 0 === m)f = !0; else for (R = 1; R <= Math.floor((m + 1) / 2); R++)L = l * R, !(L - u > J) && L > u && (k = g - L, k / l == Math.floor(k / l) && L / l == Math.floor(L / l) && (d = g, c = -1 == z ? k : L, h = -1 == z ? -L : -k, f = !0))
        } else f = a(h, c, m, n, !0), J = f - d, d = f, 0 < c ? c += J : h -= J; else J && (f = function (a, c, h) {
          for (var p = 0, f = 1, d; ;) {
            d = a + p * f;
            d = 0 === d ? 1 : d;
            if (b(c, d, h))break;
            p = -1 == f || p > a ? ++p : p;
            if (25 < p) {
              d = 0;
              break
            }
            f = p <= a ? -1 * f : 1
          }
          return d
        }, 0 < m && (J = f(m, d, n), 0 === J && (J = f(m, d + 1, n), q = 1), m = J));
        return {Max: c, Min: h, Range: d, interval: n, divGap: (c - h + q) / (m + 1)}
      }
    }(), qb = function () {
      var a =
        function (a, b, c) {
          var h = c.jsVars && c.jsVars.smartLabel, f = a.offsetWidth, d = a.offsetHeight, g = this.chart;
          a = this.title;
          var m = c._chartMessageImageStyle, n = !1, J;
          void 0 !== b && (b = b.replace(/^\s+/, "").replace(/\s+$/, ""), /^i\s*[\-]\s*/i.test(b) ? (n = !0, J = b.replace(/^i\s*[\-]\s*/i, "")) : J = b.replace(/^\\/, ""));
          a.y = d / 2;
          a.x = f / 2;
          g.bgSWF = a.text = void 0;
          b = J;
          n ? (g.bgSWF = b, g.bgImageHAlign = m.imageHAlign, g.bgImageVAlign = m.imageVAlign, g.bgImageScale = m.imageScale, g.bgSWFAlpha = m.imageAlpha) : void 0 !== b && (h ? (db(a.style), h.setStyle(a.style),
            h = h.getSmartText(U(b), f, d), a.text = h.text) : a.text = U(b), a.verticalAlign = "middle");
          a.style = c._chartMessageStyle;
          delete c._chartMessageImageStyle;
          delete c._chartMessageStyle
        };
      a.prototype = {
        chart: {events: {}, margin: [0, 0, 0, 0], backgroundColor: {FCcolor: {alpha: 0}}},
        credits: {href: A, text: "FusionCharts XT Trial", enabled: "false"},
        legend: {enabled: !1},
        title: {text: "", style: {fontFamily: "Verdana,sans", fontSize: "10px", color: "#666666"}},
        plotOptions: {series: {}},
        series: [{}],
        exporting: {enabled: !1},
        nativeMessage: !0
      };
      return a.prototype.constructor =
        a
    }(), Ib = {
      "true": {"true": {"true": "center", "false": "center"}, "false": {"true": "center", "false": "center"}},
      "false": {"true": {"true": "right", "false": "left"}, "false": {"true": "left", "false": "right"}}
    }, Rb = function () {
      return function (a, b, c, h, f, d, g) {
        var m, n = c.trendStyle, l, s, z, L, u, R, k, q, A, P, da, C, T, za = d ? "xAxis" : "dataLabels";
        if (d ? c.showVLines : c.showTrendlines)for (m = 0, s = a.length; m < s; m += 1)if ((T = a[m]) && T.line)for (l = 0, z = T.line.length; l < z; l += 1)L = T.line[l], P = c.numberFormatter.getCleanValue($(L.startvalue, L.value, 0)),
          da = c.numberFormatter.getCleanValue($(L.endvalue, $(L.startvalue, L.value, 0))), d ? q = b : h && L.parentyaxis && /^s$/i.test(L.parentyaxis) ? (q = b[1], C = 1) : q = b[0], R = q.max, k = q.min, u = !1, R >= P && R >= da && k <= P && k <= da && (h && L.parentyaxis && /^s$/i.test(L.parentyaxis) ? u = "1" !== $(L.valueonleft, c.trendlineValuesOnOpp) : h || (u = "1" === $(L.valueonright, c.trendlineValuesOnOpp)), R = Boolean(J(L.istrendzone, d ? 1 : 0)), (k = (d ? c.showVLineLabels : c.showTrendlineLabels) ? U($(L.displayvalue, c.numberFormatter[za](u ? da : P, C))) : "") ? (A = P < da, u = {
          text: k,
          textAlign: f ?
            "center" : u ? "left" : "right",
          align: f ? Ib[R][!g][A] : u ? "right" : "left",
          verticalAlign: f ? "bottom" : "middle",
          rotation: 0,
          x: 0,
          y: 0,
          style: n
        }, k = $(L.color, c.trendlineColor), L.alwaysVisible = R, k && (u.style = ya({}, n), u.style.color = pa(k, J(L.alpha, 100)))) : u = void 0, k = Aa(U($(L.tooltext, T.tooltext, c.trendLineToolText))), k = lb(k, [7, 15, 16, 17, 18, 19], {
          startValue: P,
          startDataValue: c.numberFormatter[za](P, C),
          endValue: da,
          endDataValue: c.numberFormatter[za](da, C),
          axisName: q.title && q.title.text
        }, L), A = J(L.thickness, c.trendlineThickness,
          1), R ? q.plotBands.push({
          isTrend: !0,
          color: pa($(L.color, c.trendlineColor), $(L.alpha, c.trendlineAlpha, 40)),
          from: P,
          to: da,
          label: u,
          zIndex: c.is3d || "1" !== $(L.showontop, c.showTrendlinesOnTop) ? 3 : 5,
          tooltext: k,
          alwaysVisible: L.alwaysVisible
        }) : q.plotLines.push({
          isTrend: !0,
          color: pa($(L.color, c.trendlineColor, c.trendlineColor), $(L.alpha, c.trendlineAlpha, 99)),
          value: P,
          to: da,
          width: A,
          dashStyle: "1" == $(L.dashed, c.trendlinesAreDashed) ? eb(J(L.dashlen, c.trendlinesDashLen), J(L.dashgap, c.trendlinesDashGap), A) : "none",
          label: u,
          zIndex: c.is3d || "1" !== $(L.showontop, c.showTrendlinesOnTop) ? 3 : 5,
          tooltext: k
        }))
      }
    }(), eb = function (a, b, c, h) {
      return h || void 0 === h ? [a, b] : "none"
    }, Xa = function () {
    }, Va = function (a, b, c) {
      var h, f = Va[a];
      f || (f = function () {
      }, f.prototype = c instanceof Xa ? c : new Xa, f.prototype.constructor = f, f = Va[a] = new f);
      c && (f.base = c);
      f.name = a;
      for (h in b)switch (typeof b[h]) {
        case "object":
          if (b[h]instanceof Xa) {
            f[h] = b[h][h];
            break
          }
        default:
          f[h] = b[h];
          break;
        case "undefined":
          delete f[h]
      }
      return this instanceof Va ? (a = function () {
      }, a.prototype = f, a.prototype.constructor =
        a, new a) : f
    }, lb = function () {
      var a = [{
        regex: /((^|[^\\])((\\)\\)*\$cleanvalue)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$cleanvalue))/ig,
        argIndex: 2,
        argKey: "cleanvalue"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$datavalue)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$datavalue))/ig,
        argIndex: 2,
        argKey: "formattedValue"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$value)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$value))/ig,
        argIndex: 3,
        argKey: "value"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$label)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$label))/ig, argIndex: 2,
        argKey: "label"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$seriesname)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$seriesname))/ig,
        argIndex: 5,
        argKey: "seriesname"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$yaxisname)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$yaxisname))/ig,
        argIndex: 2,
        argKey: "yaxisName"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$xaxisname)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$xaxisname))/ig,
        argIndex: 2,
        argKey: "xaxisName"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$displayvalue)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$displayvalue))/ig,
        argIndex: 3,
        argKey: "displayvalue"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$xdatavalue)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$xdatavalue))/ig,
        argIndex: 2,
        argKey: "xDataValue"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$ydatavalue)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$ydatavalue))/ig,
        argIndex: 2,
        argKey: "yDataValue"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$xvalue)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$xvalue))/ig,
        argIndex: 3,
        argKey: "x"
      }, {
        regex: /((^|[^\\])((\\)\\)*\$yvalue)/ig,
        escapeRegex: /((^|[^\\])((\\)\\)*\\(\$yvalue))/ig,
        argIndex: 3,
        argKey: "y"
      },
        {
          regex: /((^|[^\\])((\\)\\)*\$zvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$zvalue))/ig,
          argIndex: 3,
          argKey: "z"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$name)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$name))/ig,
          argIndex: 3,
          argKey: "name"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$percentValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$percentValue))/ig,
          argIndex: 2,
          argKey: "percentValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$startValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$startValue))/ig,
          argIndex: 2,
          argKey: "startValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$startDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$startDataValue))/ig, argIndex: 2, argKey: "startDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$endValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$endValue))/ig,
          argIndex: 2,
          argKey: "endValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$endDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$endDataValue))/ig,
          argIndex: 2,
          argKey: "endDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$axisName)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$axisName))/ig,
          argIndex: 2,
          argKey: "axisName"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$cumulativevalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$cumulativevalue))/ig, argIndex: 2, argKey: "cumulativeValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$cumulativedatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$cumulativedatavalue))/ig,
          argIndex: 2,
          argKey: "cumulativeDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$cumulativePercentValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$cumulativePercentValue))/ig,
          argIndex: 2,
          argKey: "cumulativePercentValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$cumulativepercentdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$cumulativepercentdatavalue))/ig,
          argIndex: 2,
          argKey: "cumulativePercentDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$sum)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$sum))/ig,
          argIndex: 2,
          argKey: "sum"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedsum)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$unformattedsum))/ig,
          argIndex: 2,
          argKey: "unformattedSum"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$targetvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$targetvalue))/ig,
          argIndex: 2,
          argKey: "targetValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$targetdatavalue)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$targetdatavalue))/ig,
          argIndex: 2, argKey: "targetDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$processname)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$processname))/ig,
          argIndex: 2,
          argKey: "processName"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$start)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$start))/ig,
          argIndex: 2,
          argKey: "start"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$end)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$end))/ig,
          argIndex: 2,
          argKey: "end"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$percentcomplete)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$percentcomplete))/ig,
          argIndex: 2,
          argKey: "percentComplete"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$taskpercentcomplete)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$taskpercentcomplete))/ig,
          argIndex: 2,
          argKey: "taskPercentComplete"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$taskstartdate)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$taskstartdate))/ig,
          argIndex: 2,
          argKey: "taskStartDate"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$taskenddate)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$taskenddate))/ig,
          argIndex: 2,
          argKey: "taskEndDate"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$tasklabel)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$tasklabel))/ig,
          argIndex: 2, argKey: "taskLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$date)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$date))/ig,
          argIndex: 2,
          argKey: "date"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$percentofprevvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$percentofprevvalue))/ig,
          argIndex: 2,
          argKey: "percentOfPrevValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$sname)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$sname))/ig,
          argIndex: 2,
          argKey: "sName"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$lname)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$lname))/ig, argIndex: 2,
          argKey: "lName"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromid)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromid))/ig,
          argIndex: 2,
          argKey: "fromId"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromlabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromlabel))/ig,
          argIndex: 2,
          argKey: "fromLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toid)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toid))/ig,
          argIndex: 2,
          argKey: "toId"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$tolabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$tolabel))/ig,
          argIndex: 2,
          argKey: "toLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromxvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromxvalue))/ig, argIndex: 2, argKey: "fromXValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromyvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromyvalue))/ig,
          argIndex: 2,
          argKey: "fromYValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromxdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromxdatavalue))/ig,
          argIndex: 2,
          argKey: "fromXDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromydatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromydatavalue))/ig,
          argIndex: 2,
          argKey: "fromYDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromlabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromlabel))/ig, argIndex: 2, argKey: "fromLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toxvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toxvalue))/ig,
          argIndex: 2,
          argKey: "toXValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toyvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toyvalue))/ig,
          argIndex: 2,
          argKey: "toYValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toxdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toxdatavalue))/ig,
          argIndex: 2,
          argKey: "toXDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toydatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toydatavalue))/ig, argIndex: 2, argKey: "toYDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$tolabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$tolabel))/ig,
          argIndex: 2,
          argKey: "toLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$openvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$openvalue))/ig,
          argIndex: 2,
          argKey: "openValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$closevalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$closevalue))/ig,
          argIndex: 2,
          argKey: "closeValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$highvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$highvalue))/ig, argIndex: 2, argKey: "highValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$lowvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$lowvalue))/ig,
          argIndex: 2,
          argKey: "lowValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$opendatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$opendatavalue))/ig,
          argIndex: 2,
          argKey: "openDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$closedatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$closedatavalue))/ig,
          argIndex: 2,
          argKey: "closeDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$highdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$highdatavalue))/ig, argIndex: 2, argKey: "highDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$lowdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$lowdatavalue))/ig,
          argIndex: 2,
          argKey: "lowDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$maxvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$maxvalue))/ig,
          argIndex: 2,
          argKey: "maxValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$maxdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$maxdatavalue))/ig,
          argIndex: 2,
          argKey: "maxDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$minvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$minvalue))/ig, argIndex: 2, argKey: "minValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$mindatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$mindatavalue))/ig,
          argIndex: 2,
          argKey: "minDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$q1)/ig,
          argIndex: 2,
          argKey: "Q1"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedQ1)/ig,
          argIndex: 2,
          argKey: "unformattedQ1"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$q3)/ig,
          argIndex: 2,
          argKey: "Q3"
        }, {regex: /((^|[^\\])((\\)\\)*\$unformattedQ3)/ig, argIndex: 2, argKey: "unformattedQ3"}, {
          regex: /((^|[^\\])((\\)\\)*\$median)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$median))/ig, argIndex: 2, argKey: "median"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedMedian)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$unformattedMedian))/ig,
          argIndex: 2,
          argKey: "unformattedMedian"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$SD)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$SD))/ig,
          argIndex: 2,
          argKey: "SD"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedsd)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$unformattedsd))/ig,
          argIndex: 2,
          argKey: "unformattedsd"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$QD)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$QD))/ig, argIndex: 2, argKey: "QD"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedQD)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$unformattedQD))/ig,
          argIndex: 2,
          argKey: "unformattedQD"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$MD)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$MD))/ig,
          argIndex: 2,
          argKey: "MD"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedMD)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$unformattedMD))/ig,
          argIndex: 2,
          argKey: "unformattedMD"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$mean)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$mean))/ig,
          argIndex: 2, argKey: "mean"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedMean)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$unformattedMean))/ig,
          argIndex: 2,
          argKey: "unformattedMean"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$unformattedMean)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$unformattedMean))/ig,
          argIndex: 2,
          argKey: "unformattedMean"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$volumeValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$volumeValue))/ig,
          argIndex: 2,
          argKey: "volumeValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$volumeDataValue)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$volumeDataValue))/ig,
          argIndex: 2, argKey: "volumeDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromXValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromXValue))/ig,
          argIndex: 2,
          argKey: "fromXValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromYValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromYValue))/ig,
          argIndex: 2,
          argKey: "fromYValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromXDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromXDataValue))/ig,
          argIndex: 2,
          argKey: "fromXDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromYDataValue)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromYDataValue))/ig,
          argIndex: 2, argKey: "fromYDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$fromLabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$fromLabel))/ig,
          argIndex: 2,
          argKey: "fromLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toXValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toXValue))/ig,
          argIndex: 2,
          argKey: "toXValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toYValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toYValue))/ig,
          argIndex: 2,
          argKey: "toYValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toXDataValue)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toXDataValue))/ig,
          argIndex: 2, argKey: "toXDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$toYDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$toYDataValue))/ig,
          argIndex: 2,
          argKey: "toYDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$tolabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$tolabel))/ig,
          argIndex: 2,
          argKey: "toLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$tlLabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$tlLabel))/ig,
          argIndex: 5,
          argKey: "tlLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$trlabel)/ig, escapeRegex: /((^|[^\\])((\\)\\)*\\(\$trlabel))/ig, argIndex: 5,
          argKey: "trLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$bllabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$bllabel))/ig,
          argIndex: 5,
          argKey: "blLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$brlabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$brlabel))/ig,
          argIndex: 5,
          argKey: "brLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$rowlabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$rowlabel))/ig,
          argIndex: 5,
          argKey: "rowLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$columnlabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$columnlabel))/ig,
          argIndex: 5,
          argKey: "columnLabel"
        },
        {
          regex: /((^|[^\\])((\\)\\)*\$errorvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$errorvalue))/ig,
          argIndex: 2,
          argKey: "errorValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$errordatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$errordatavalue))/ig,
          argIndex: 2,
          argKey: "errorDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$errorpercentvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$errorpercentvalue))/ig,
          argIndex: 2,
          argKey: "errorPercentValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$errorpercentdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$errorpercentdatavalue))/ig,
          argIndex: 2,
          argKey: "errorPercentDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$horizontalErrorValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$horizontalErrorValue))/ig,
          argIndex: 2,
          argKey: "horizontalErrorValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$horizontalErrorDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$horizontalErrorDataValue))/ig,
          argIndex: 2,
          argKey: "horizontalErrorDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$verticalErrorValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$verticalErrorValue))/ig,
          argIndex: 2,
          argKey: "verticalErrorValue"
        },
        {
          regex: /((^|[^\\])((\\)\\)*\$verticalErrorDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$verticalErrorDataValue))/ig,
          argIndex: 2,
          argKey: "verticalErrorDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$horizontalErrorPercent)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$horizontalErrorPercentValue))/ig,
          argIndex: 2,
          argKey: "horizontalErrorPercentValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$horizontalErrorPercentDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$horizontalErrorPercentDataValue))/ig,
          argIndex: 2,
          argKey: "horizontalErrorPercentDataValue"
        },
        {
          regex: /((^|[^\\])((\\)\\)*\$verticalErrorPercent)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$verticalErrorPercentValue))/ig,
          argIndex: 2,
          argKey: "verticalErrorPercentValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$verticalErrorPercentDataValue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$verticalErrorPercentDataValue))/ig,
          argIndex: 2,
          argKey: "verticalErrorPercentDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$xaxispercentvalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$xaxispercentvalue))/ig,
          argIndex: 2,
          argKey: "xAxisPercentValue"
        },
        {
          regex: /((^|[^\\])((\\)\\)*\$percentdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$percentdatavalue))/ig,
          argIndex: 2,
          argKey: "percentDataValue"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$trType)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$trType))/ig,
          argIndex: 4,
          argKey: "trtype"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$tlType)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$tlType))/ig,
          argIndex: 4,
          argKey: "tltype"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$brType)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$brType))/ig,
          argIndex: 4,
          argKey: "brtype"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$blType)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$blType))/ig, argIndex: 4, argKey: "bltype"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$colorRangeLabel)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$colorRangeLabel))/ig,
          argIndex: 5,
          argKey: "colorRangeLabel"
        }, {
          regex: /((^|[^\\])((\\)\\)*\$zdatavalue)/ig,
          escapeRegex: /((^|[^\\])((\\)\\)*\\(\$zdatavalue))/ig,
          argIndex: 2,
          argKey: "zDataValue"
        }], b = [], c, h = a.length;
      for (c = 0; c < h; c += 1)b.push(c);
      return function () {
        var c = arguments[0], h = arguments[1], f, p, d, g, m;
        fa(h) || (h = b);
        if (c)for (m = h.length, g = 0; g < m; g +=
          1)if (d = a[h[g]])f = za(Aa((p = arguments[d.argIndex]) && p[d.argKey], "") + ""), c = c.replace(d.regex, "$2$4" + (d.parsingMethod ? d.parsingMethod(f) : f)), c = c.replace(d.escapeRegex, "$2$4$5");
        return c
      }
    }();
  d.core._setLineHeightFactor = function (a) {
    !(a = K(a)) || 0 > a || (ka.lineHeightFactor = a)
  };
  d.extend(ka, {
    BLANKSTRINGPLACEHOLDER: "#BLANK#",
    BLANKSTRING: "",
    COLOR_BLACK: "000000",
    COLOR_GLASS: "rgba(255, 255, 255, 0.3)",
    COLOR_WHITE: "FFFFFF",
    COLOR_TRANSPARENT: "rgba(0,0,0,0)",
    HASHSTRING: "#",
    BREAKSTRING: "<br />",
    STRINGSTRING: "string",
    OBJECTSTRING: "object",
    COMMASTRING: ",",
    ZEROSTRING: "0",
    SAMPLESTRING: "Ay0",
    TESTSTR: "Ag",
    ONESTRING: "1",
    DECIMALSTRING: ".",
    STRINGUNDEFINED: "undefined",
    POSITION_TOP: "top",
    POSITION_RIGHT: "right",
    POSITION_BOTTOM: "bottom",
    POSITION_LEFT: "left",
    POSITION_CENTER: "center",
    POSITION_MIDDLE: "middle",
    POSITION_START: "start",
    POSITION_END: "end",
    FC_CONFIG_STRING: "_FCconf",
    SHAPE_RECT: "rect",
    HUNDREDSTRING: "100",
    PXSTRING: "px",
    COMMASPACE: ", ",
    TEXTANCHOR: "text-anchor",
    TOUCH_THRESHOLD_PIXELS: 15,
    CLICK_THRESHOLD_PIXELS: 5,
    regex: {
      stripWhitespace: G,
      dropHash: c, startsRGBA: l, cleanColorCode: a, breakPlaceholder: q, hexcode: /^#?[0-9a-f]{6}/i
    },
    fireEvent: function (a, b, c, h) {
      ka.dem.fire(a, b, c, h)
    },
    plotEventHandler: function (a, c, h) {
      c = c || {};
      var f = c.type, g = T(a.container, c), g = ya(g, this.data("eventArgs")), m = a.logic.fireGroupEvent, n = this.data("groupId"), J = function (a, h) {
        c.FusionChartsPreventEvent = !0;
        b && h.toolText && ka.toolTip && ka.toolTip.preventTooltip()
      };
      "index"in g && !("dataIndex"in g) && (g.dataIndex = g.index);
      "value"in g && !("dataValue"in g) && (g.dataValue = g.value);
      h = $(h, "dataplotclick").toLowerCase();
      "dataplotrollover" === h ? (c.FusionChartsPreventEvent = !1, m ? d.raiseEventGroup(n, h, g, a.fusionCharts, void 0, void 0, J) : d.raiseEvent(h, g, a.logic.chartInstance, void 0, void 0, J)) : m && "dataplotclick" !== h ? d.raiseEventGroup(n, h, g, a.fusionCharts) : d.raiseEvent(h, g, a.logic.chartInstance);
      "click" !== f && "mouseup" !== f && "touchend" !== f || !/click/i.test(h) || a.linkClickFN.call({link: g.link}, a)
    },
    getEventCoordinate: R,
    getMouseCoordinate: T,
    addEvent: aa,
    removeEvent: qa,
    getTouchEvent: ga,
    extend2: ya,
    deltend: function (a, b) {
      if ("object" !== typeof a || "object" !== typeof b)return null;
      Fa(a, b);
      return a
    },
    imprint: function (a, b, c) {
      var h;
      if ("object" !== typeof a || null === a)return b;
      if ("object" !== typeof b || null === b)return a;
      for (h in b)if (void 0 === a[h] || !c && null === a[h])a[h] = b[h];
      return a
    },
    pluck: $,
    pluckNumber: J,
    getFirstDefinedValue: function () {
      var a, b, c;
      b = 0;
      for (c = arguments.length; b < c; b += 1)if ((a = arguments[b]) || !1 === a || 0 === a || "" == a)return a
    },
    createElement: function (a, b, c) {
      a = B.createElement(a);
      for (var h in b)a.setAttribute(h,
        b[h]);
      c && c.appendChild && c.appendChild(a);
      return a
    },
    hashify: h,
    pluckFontSize: function () {
      var a, b, c;
      b = 0;
      for (c = arguments.length; b < c; b += 1)if (((a = arguments[b]) || !1 === a || 0 === a) && !isNaN(a = Number(a)))return 1 > a ? 1 : a;
      return 1
    },
    getValidValue: Aa,
    getPosition: Z,
    getViewPortDimension: P,
    bindSelectionEvent: function (a, b) {
      b = b || {};
      var c = a.options.chart, h = a.container, f = c.zoomType, d = ya({}, b.attr || {}), g = d["stroke-width"] = J(d.strokeWidth, d["stroke-width"], 1), m = Z(h), n = a.eventListeners || (a.eventListeners = []);
      b = ya({
        chart: a,
        zoomX: /x/.test(f),
        zoomY: /y/.test(f),
        canvasY: a.canvasTop,
        canvasX: a.canvasLeft,
        canvasW: a.canvasWidth,
        canvasH: a.canvasHeight,
        canvasX2: a.canvasLeft + a.canvasWidth,
        canvasY2: a.canvasTop + a.canvasHeight,
        strokeWidth: g,
        chartPosLeft: m.left,
        chartPosTop: m.top,
        attr: d
      }, b);
      d.stroke = Ka(d.stroke, "rgba(51,153,255,0.8)");
      d.fill = Ka(d.fill, "rgba(185,213,241,0.3)");
      d.ishot = !0;
      h && (qa(h, "pointerdrag", ha), n.push(aa(h, "pointerdrag", ha, b)));
      c.link && (qa(a.container, "mouseup mousedown", ba), n.push(aa(a.container, "mouseup mousedown", ba, b)))
    },
    createContextMenu: function (a) {
      var c =
          a.chart, f = c.smartLabel, p = c.logic.hcJSON && c.logic.hcJSON.chart.useRoundEdges, d = ka.Raphael, g = function (a) {
          var b = a.menufillcolor && h(a.menufillcolor), c = a.menulabelcolor && h(a.menulabelcolor), f = a.menufillhovercolor && h(a.menufillhovercolor);
          a = a.menulabelhovercolor && h(a.menulabelhovercolor);
          return {attrs: {backgroundColor: b, color: c}, hover: {backgroundColor: f, color: a}}
        }(c.definition.chart), m = function (a, b, c) {
          b = b || {};
          a = (a = (a = a && d.tintshade(a.color, .7)) && d.getRGB(a)) && "rgb(" + [a.r, a.g, a.b].join() + ")";
          return {
            backgroundColor: b.backgroundHoverColor ||
            c.backgroundColor || a || "rgb(64, 64, 64)", color: b.hoverColor || c.color || "#FFFFFF"
          }
        }(a.basicStyle, a.hover, g.hover), n = function (a, b, c) {
          b = ya({}, b || {});
          b = ya(b, a);
          return {
            fontFamily: b.fontFamily || "Verdana,sans",
            fontSize: b.fontSize || "10px",
            color: b.color || c.color || "#000000",
            backgroundColor: b.backgroundColor || c.backgroundColor || "rgb(255, 255, 255)"
          }
        }(a.basicStyle, a.attrs, g.attrs), J = {
          textAlign: "left",
          align: "left",
          paddingLeft: "5px",
          paddingRight: "5px",
          paddingTop: "5px",
          cursor: "pointer",
          borderWidth: "0px"
        }, l = a.items,
        s = a.position, L = a.verticalPadding || 3, u = a.horizontalPadding || 6, k = {}, R, q, U, P, A, da, C, T, za, t, fa, Q, X;
      if (c)R = Z(c.container); else return !1;
      P = function () {
        var a = k.items, c = a.length, h = 0, d = 0, I = 0, g, r;
        k.menuItems || (k.menuItems = []);
        for (f.setStyle(n); c--;)g = a[c], g = f.getOriSize(g.text), I || (I = g.height + 2 * L), h += I, d = z(d, g.width + 2 * u);
        k.height = h;
        k.width = d;
        k.itemH = I;
        this.style.width = d + "px";
        k.menuRect || (h = k.menuRect = B.createElement("div"), h.style.border = "1px solid rgb(100, 100, 100)", p && (h.style.mozBorderRadius = "4px", h.style.webkitBorderRadius =
          "4px", h.style.borderRadius = "4px", h.style.overflow = "hidden"), b && !E ? h.style.filter = "progid:DXImageTransform.Microsoft.Shadow(Color=#999999,direction=135,strength=3)" : (h.style.mozBoxShadow = "3px 3px 3px #999", h.style.webkitBoxShadow = "3px 3px 3px #999", h.style.boxShadow = "3px 3px 3px #999"), this.appendChild(h));
        d = a.length;
        for (c = 0; c < d; c += 1)if (g = a[c], k.menuItems[c])k.menuItems[c].label.innerHTML = g.text; else {
          k.menuItems[c] = {};
          h = k.menuItems[c].box = B.createElement("div");
          h.style.height = I + "px";
          h.style.lineHeight =
            I + "px";
          for (r in J)h.style[r] = J[r];
          for (r in n)h.style[r] = n[r];
          k.menuRect.appendChild(h);
          h.innerHTML = g.text;
          ka.dem.listen(h, "click", X);
          ka.dem.listen(h, "pointerhover", t);
          k.menuItems[c].box._itemIdx = c
        }
        for (; k.menuItems[c];)k.menuItems[c].box.parentNode.removeChild(k.menuItems[c].box), k.menuItems.splice(c, 1)
      };
      A = function () {
        U || (U = B.createElement("div"), U.style.position = "absolute", U.style.zIndex = "50", U.style.display = "none", c.container.appendChild && c.container.appendChild(U));
        return U
      };
      da = function () {
        q = setTimeout(k.hide,
          800)
      };
      C = function () {
        q && clearTimeout(q)
      };
      T = function (a) {
        var b = a.x;
        a = a.y;
        var h = {x: b, y: a}, f = k.width, d = k.height, p = c.chartHeight, r = c.chartWidth;
        b + f > r && 0 < b - f ? h.x -= f : b + f > r && (h.x = 0);
        a + d > p && 0 < a - d && (h.y -= d);
        return h
      };
      za = function () {
        k.hide()
      };
      t = function (a) {
        a.target && a.target.parentNode && ("start" === a.state ? fa : Q).call(a.target)
      };
      fa = function () {
        var a = k.menuItems[this._itemIdx], b;
        C();
        for (b in m)a.box.style[b] = m[b]
      };
      Q = function () {
        var a = k.menuItems[this._itemIdx], b;
        for (b in n)a.box.style[b] = n[b];
        da()
      };
      X = function (a) {
        var b =
          k.items[this._itemIdx];
        b.onclick && b.onclick.call(b, a);
        a.originalEvent.stopPropagation ? a.originalEvent.stopPropagation() : a.originalEvent.cancelBubble = !0;
        k.hide()
      };
      k.showItem = function (a) {
        a = this.menuItems[a];
        var b = this.height, c = this.itemH;
        a && a._isHidden && (a.box.style.display = "", this.height = b + c, a._isHidden = !1, a = T(s), this.left = a.x, this.top = a.y)
      };
      k.hideItem = function (a) {
        a = this.menuItems[a];
        var b = this.height, c = this.itemH;
        a && !a._isHidden && (a.box.style.display = "none", this.height = b - c, a._isHidden = !0, a = T(s), this.left =
          a.x, this.top = a.y)
      };
      k.redraw = function () {
        var a = this.menuContainer;
        this.items = l;
        a ? P.call(this.menuContainer) : s && void 0 !== s.x && void 0 !== s.y ? (this.menuContainer = A(), P.call(this.menuContainer), a = T(s), this.left = a.x, this.top = a.y, this.menuContainer.style.left = this.left + "px", this.menuContainer.style.top = this.top + "px") : (this.menuContainer = A(), P.call(this.menuContainer))
      };
      k.show = function (a) {
        var b = this;
        a && void 0 !== a.x && void 0 !== a.y ? (a = T(a), b.menuContainer.style.left = a.x + "px", b.menuContainer.style.top = a.y + "px") :
          (b.menuContainer.style.left = b.left + "px", b.menuContainer.style.top = b.top + "px");
        b.menuContainer.style.display = "";
        setTimeout(function () {
          b.visible = !0;
          d.click(za)
        }, 400)
      };
      k.hide = function () {
        this.visible && (this.visible = !1, k.menuContainer.style.display = "none", k.menuContainer.style.left = -k.width + "px", k.menuContainer.style.top = -k.height + "px", d.unclick(za))
      };
      k.update = function (a) {
        a && a.length && (this.items = a, this.redraw())
      };
      k.updatePosition = function (a) {
        var b = R.left, h = R.top;
        R = Z(c.container);
        a ? (s = a, a = T(a), this.left =
          a.x, this.top = a.y) : (this.left -= b - R.left, this.top -= h - R.top)
      };
      k.add = function (a) {
        var b = this.menuItems, c = b.length, h;
        f.setStyle(n);
        this.width = z(this.width, f.getOriSize(a.text).width);
        b[c] = {};
        b = b[c].box = B.createElement("div");
        b.style.height = this.itemH + "px";
        b.style.lineHeight = this.itemH + "px";
        for (h in J)b.style[h] = J[h];
        for (h in n)b.style[h] = n[h];
        k.menuRect.appendChild(b);
        b.innerHTML = a.text;
        ka.dem.listen(b, "click", X);
        ka.dem.listen(b, "pointerhover", t);
        k.menuItems[c].box._itemIdx = c;
        this.height += this.itemH
      };
      k.removeItems =
        function () {
          for (var a = this.menuItems, b = a && a.length, c; b--;)c = a[b], ka.dem.unlisten(c.box, "click", X), ka.dem.unlisten(c.box, "pointerhover", t), c.box && c.box.parentNode && c.box.parentNode.removeChild(c.box);
          delete this.menuItems;
          delete this.items
        };
      k.setPosition = function (a) {
        void 0 !== a.x && void 0 !== a.y && (this.menuContainer.style.x = a.x, this.menuContainer.style.y = a.y)
      };
      k.destroy = function () {
        this.removeItems();
        this.menuContainer.parentNode.removeChild(this.menuContainer)
      };
      l && l.length && (k.redraw(), k.hide());
      return k
    },
    getDefinedColor: function (a, b) {
      return a || 0 === a || "" === a ? a : b
    },
    getFirstValue: Ka,
    getFirstColor: function (a) {
      a = a.split(",")[0];
      a = a.replace(G, "");
      "" == a && (a = "000000");
      return a.replace(c, "#")
    },
    getColorCodeString: function (a, b) {
      var c = "", h, f, d = 0, g = b.split(",");
      for (f = g.length; d < f; d += 1)h = g[d].split("-"), c = 2 === h.length ? "-1" !== h[0].indexOf("dark") ? c + (jb(a, 100 - parseInt(h[1], 10)) + ",") : c + (Ba(a, 100 - parseInt(h[1], 10)) + ",") : c + (g[d] + ",");
      return c.substring(0, c.length - 1)
    },
    pluckColor: function (a) {
      if (Aa(a))return a = a.split(",")[0],
        a = a.replace(G, ""), "" == a && (a = "000000"), a.replace(c, "#")
    },
    toRaphaelColor: bb,
    gradientify: va,
    trimString: function (a) {
      a = a.replace(/^\s\s*/, "");
      for (var b = /\s/, c = a.length; b.test(a.charAt(--c)););
      return a.slice(0, c + 1)
    },
    getFirstAlpha: function (a) {
      a = parseInt(a, 10);
      if (isNaN(a) || 100 < a || 0 > a)a = 100;
      return a
    },
    parsePointValue: L,
    parseUnsafeString: U,
    parseTooltext: lb,
    toPrecision: function (a, b) {
      var c = f(10, b);
      return g(a * c) / c
    },
    hasTouch: t,
    CREDIT_HREF: A,
    CREDIT_STRING: "",
    getSentenceCase: function (a) {
      a = a ||
      "";
      return a.charAt(0).toUpperCase() + a.substr(1)
    },
    getCrispValues: function (a, b, c) {
      var h = c % 2 / 2;
      c = g(a + h) - h;
      a = g(a + b + h) - h - c;
      return {position: c, distance: a}
    },
    regescape: function (a) {
      return a && a.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&")
    },
    regReplaceEscape: za,
    isArray: fa,
    stubFN: function () {
    },
    falseFN: function () {
      return !1
    },
    stableSort: function (a, b) {
      var c = a.length, h;
      for (h = 0; h < c; h++)a[h].ssI = h;
      a.sort(function (a, c) {
        var h = b(a, c);
        return 0 === h ? a.ssI - c.ssI : h
      });
      for (h = 0; h < c; h++)delete a[h].ssI
    },
    hasSVG: E,
    isIE: b,
    lineHeightFactor: 1.2,
    getLinkAction: function (a, b) {
      var c = function (a) {
        return a
      };
      return function (h) {
        h = h || this.series && this.series.chart;
        var f = a.chart || a.map || {}, g = J(f.unescapelinks, 1), f = J(f.clickurloverridesplotlinks, 0), m = Ka(this.link, "");
        h = h && h.options && h.options.chart && h.options.chart.link || "";
        var n = this.options && this.options.chart && this.options.chart.link || "", l = f ? $(h, n, m) : $(m, n, h), s = l, L, z, u, R, q, U, P, A, da, T;
        void 0 !== l && (g && (l = k.decodeURIComponent ? k.decodeURIComponent(l) : k.unescape(l)), l = l.replace(/^\s+/, "").replace(/\s+$/,
          ""), -1 !== l.search(/^[a-z]*\s*[\-\:]\s*/i) && (q = l.split(/\s*[\-\:]\s*/)[0].toLowerCase(), T = q.length), setTimeout(function () {
          switch (q) {
            case "j":
              l = l.replace(/^j\s*\-/i, "j-");
              L = l.indexOf("-", 2);
              -1 === L ? Da(l.slice(2)) : Da(l.substr(2, L - 2).replace(/\s/g, ""), l.slice(L + 1));
              break;
            case "javascript":
              na(l.replace(/^javascript\s*\:/i, ""));
              break;
            case "n":
              l.replace(/^n\s*\-/i, "n-");
              k.open(c(l.slice(2), g));
              break;
            case "f":
              l = l.replace(/^f\s*\-/i, "f-");
              L = l.indexOf("-", 2);
              -1 !== L ? (z = l.substr(2, L - 2)) && k.frames[z] ? k.frames[z].location =
                c(l.slice(L + 1), g) : k.open(c(l.slice(L + 1), g), z) : k.open(c(l.slice(2), g));
              break;
            case "p":
              l = l.replace(/p\s*\-/i, "p-");
              L = l.indexOf("-", 2);
              u = l.indexOf(",", 2);
              -1 === L && (L = 1);
              R = c(l.slice(L + 1), g);
              k.open(R, l.substr(2, u - 2), l.substr(u + 1, L - u - 1)).focus();
              break;
            case "newchart":
            case "newmap":
              ":" === l.charAt(T) && (L = l.indexOf("-", T + 1), da = l.substring(T + 1, L), T = L);
              L = l.indexOf("-", T + 1);
              U = l.substring(T + 1, L).toLowerCase();
              switch (U) {
                case "xmlurl":
                case "jsonurl":
                  A = l.substring(L + 1, l.length);
                  break;
                case "xml":
                case "json":
                  var h =
                    P = l.substring(L + 1, l.length), f = {chart: {}}, p, h = h.toLowerCase();
                  if (a.linkeddata)for (p = 0; p < a.linkeddata.length; p += 1)a.linkeddata[p].id.toLowerCase() === h && (f = a.linkeddata[p].linkedchart || a.linkeddata[p].linkedmap);
                  A = f;
                  U = "json"
              }
              d.raiseEvent("linkedChartInvoked", {alias: da, linkType: U.toUpperCase(), data: A}, b);
              break;
            default:
              k.location.href = l
          }
          d.raiseEvent("linkClicked", {linkProvided: s, linkInvoked: l, linkAction: q && q.toLowerCase()}, b)
        }, 0))
      }
    },
    graphics: {
      parseAlpha: Ea, convertColor: pa, getDarkColor: Ba, getLightColor: jb,
      mapSymbolName: function (a, b) {
        var c = "circle";
        a = L(a);
        3 <= a && (c = (b ? "spoke_" : "poly_") + a);
        return c
      }, getColumnColor: function (a, b, c, h, f, d, g, m, n) {
        var l, J;
        l = a.split(",");
        J = b.split(",");
        d = d.split(",");
        g = g.split(",");
        a = a.replace(/\s/g, "").replace(/\,$/, "");
        n ? m = {
          FCcolor: {
            color: l[0],
            alpha: J[0]
          }
        } : f ? (a = l[0], J = J[0], m = {
          FCcolor: {
            color: Ba(a, 75) + "," + jb(a, 10) + "," + Ba(a, 90) + "," + jb(a, 55) + "," + Ba(a, 80),
            alpha: J + "," + J + "," + J + "," + J + "," + J,
            ratio: "0,11,14,57,18",
            angle: m ? "90" : "0"
          }
        }, d = [Ba(a, 70)]) : (b = Ea(b, l.length), m = {
          FCcolor: {
            color: a,
            alpha: b, ratio: c, angle: m ? -h : h
          }
        });
        return [m, {FCcolor: {color: d[0], alpha: g[0]}}]
      }, getAngle: function (a, b, c) {
        a = 180 * Math.atan(b / a) / Math.PI;
        2 == c ? a = 180 - a : 3 == c ? a += 180 : 4 == c && (a = 360 - a);
        return a
      }, parseColor: Ya, getValidColor: function (a) {
        return S.test(Ya(a)) && a
      }, HSBtoRGB: function (a) {
        var b = a[0], c = 0, h = 0, f = 0, d = [], d = a[1] / 100;
        a = a[2] / 100;
        var m = b / 60 - Math.floor(b / 60), n = a * (1 - d), l = a * (1 - m * d), d = a * (1 - (1 - m) * d);
        switch (Math.floor(b / 60) % 6) {
          case 0:
            c = a;
            h = d;
            f = n;
            break;
          case 1:
            c = l;
            h = a;
            f = n;
            break;
          case 2:
            c = n;
            h = a;
            f = d;
            break;
          case 3:
            c = n;
            h = l;
            f = a;
            break;
          case 4:
            c = d;
            h = n;
            f = a;
            break;
          case 5:
            c = a, h = n, f = l
        }
        return d = [g(255 * c), g(255 * h), g(255 * f)]
      }, RGBtoHSB: function (a) {
        var b = a[0], c = a[1];
        a = a[2];
        var h = Math.max(Math.max(b, c), a), f = Math.min(Math.min(b, c), a), d = 0, m = 0;
        h == f ? d = 0 : h == b ? d = (60 * (c - a) / (h - f) + 360) % 360 : h == c ? d = 60 * (a - b) / (h - f) + 120 : h == a && (d = 60 * (b - c) / (h - f) + 240);
        m = 0 === h ? 0 : (h - f) / h;
        return [g(d), g(100 * m), g(h / 255 * 100)]
      }, RGBtoHex: function (a) {
        return ("000000" + (a[0] << 16 | a[1] << 8 | a[2]).toString(16)).slice(-6)
      }, HEXtoRGB: Ca
    },
    setImageDisplayMode: function (a, b, c, h, f, d, g, m) {
      var n =
        h / 100 * m.width;
      h = h / 100 * m.height;
      m = {};
      var l, J = d - 2 * f;
      l = g - 2 * f;
      var s = function (a, b, c, h, d, g) {
        var m = {};
        switch (a) {
          case "top":
            m.y = f;
            break;
          case "bottom":
            m.y = g - h - f;
            break;
          case "middle":
            m.y = (g - h) / 2
        }
        switch (b) {
          case "left":
            m.x = f;
            break;
          case "right":
            m.x = d - c - f;
            break;
          case "middle":
            m.x = (d - c) / 2
        }
        return m
      };
      switch (a) {
        case "center":
          m.width = n;
          m.height = h;
          m.y = g / 2 - h / 2;
          m.x = d / 2 - n / 2;
          break;
        case "stretch":
          m.width = d - 2 * f;
          m.height = g - 2 * f;
          m.y = f;
          m.x = f;
          break;
        case "tile":
          m.width = n;
          m.height = h;
          m.tileInfo = {};
          m.tileInfo.xCount = a = Math.ceil(J / n);
          m.tileInfo.yCount = l = Math.ceil(l / h);
          b = s(b, c, n * a, h * l, d, g);
          m.y = b.y;
          m.x = b.x;
          break;
        case "fit":
          a = n / h > J / l ? J / n : l / h;
          m.width = n * a;
          m.height = h * a;
          b = s(b, c, m.width, m.height, d, g);
          m.y = b.y;
          m.x = b.x;
          break;
        case "fill":
          a = n / h > J / l ? l / h : J / n;
          m.width = n * a;
          m.height = h * a;
          b = s(b, c, m.width, m.height, d, g);
          m.y = b.y;
          m.x = b.x;
          break;
        default:
          b = s(b, c, n, h, d, g), m.width = n, m.height = h, m.y = b.y, m.x = b.x
      }
      return m
    },
    setLineHeight: db,
    parsexAxisStyles: Ta,
    supportedStyle: {
      font: "font",
      fontFamily: "font-family",
      "font-family": "font-family",
      fontWeight: "font-weight",
      "font-weight": "font-weight",
      fontSize: "font-size",
      "font-size": "font-size",
      lineHeight: "line-height",
      "line-height": "line-height",
      textDecoration: "text-decoration",
      "text-decoration": "text-decoration",
      color: "color",
      whiteSpace: "white-space",
      "white-space": "white-space",
      padding: "padding",
      margin: "margin",
      background: "background",
      backgroundColor: "background-color",
      "background-color": "background-color",
      backgroundImage: "background-image",
      "background-image": "background-image",
      backgroundPosition: "background-position",
      "background-position": "background-position",
      backgroundPositionLeft: "background-position-left",
      "background-position-left": "background-position-left",
      backgroundPositionTop: "background-position-top",
      "background-position-top": "background-position-top",
      backgroundRepeat: "background-repeat",
      "background-repeat": "background-repeat",
      border: "border",
      borderColor: "border-color",
      "border-color": "border-color",
      borderStyle: "border-style",
      "border-style": "border-style",
      borderThickness: "border-thickness",
      "border-thickness": "border-thickness",
      borderTop: "border-top",
      "border-top": "border-top",
      borderTopColor: "border-top-color",
      "border-top-color": "border-top-color",
      borderTopStyle: "border-top-style",
      "border-top-style": "border-top-style",
      borderTopThickness: "border-top-thickness",
      "border-top-thickness": "border-top-thickness",
      borderRight: "border-right",
      "border-right": "border-right",
      borderRightColor: "border-right-color",
      "border-right-color": "border-right-color",
      borderRightStyle: "border-right-style",
      "border-right-style": "border-right-style",
      borderRightThickness: "border-right-thickness",
      "border-right-thickness": "border-right-thickness",
      borderBottom: "border-bottom",
      "border-bottom": "border-bottom",
      borderBottomColor: "border-bottom-color",
      "border-bottom-color": "border-bottom-color",
      borderBottomStyle: "border-bottom-style",
      "border-bottom-style": "border-bottom-style",
      borderBottomThickness: "border-bottom-thickness",
      "border-bottom-thickness": "border-bottom-thickness",
      borderLeft: "border-left",
      "border-left": "border-left",
      borderLeftColor: "border-left-color",
      "border-left-color": "border-left-color",
      borderLeftStyle: "border-left-style",
      "border-left-Style": "border-left-style",
      borderLeftThickness: "border-left-thickness",
      "border-left-thickness": "border-left-thickness"
    },
    getAxisLimits: Qb,
    createTrendLine: Rb,
    getDashStyle: eb,
    axisLabelAdder: ra,
    chartAPI: Va,
    createDialog: qb,
    isCanvasElemSupported: function () {
      var a = B.createElement("canvas");
      return !(!a.getContext || !a.getContext("2d"))
    }
  })
}]);
window.FusionCharts && window.FusionCharts.register("module", ["private", "vendor.redraphael", function () {
  var d = this.hcLib, k = window.Raphael, B;
  (function () {
    (function (d, k) {
      var b = /[\.\/]/, B = function () {
      }, G = function (a, b) {
        return a - b
      }, c, l, a = {n: {}}, q = function (a, b) {
        a = String(a);
        var f = l, d = Array.prototype.slice.call(arguments, 2), s = q.listeners(a), n = 0, k, A = [], m = {}, P = [], z = c;
        c = a;
        for (var t = l = 0, u = s.length; t < u; t++)"zIndex"in s[t] && (A.push(s[t].zIndex), 0 > s[t].zIndex && (m[s[t].zIndex] = s[t]));
        for (A.sort(G); 0 > A[n];)if (k = m[A[n++]],
            P.push(k.apply(b, d)), l)return l = f, P;
        for (t = 0; t < u; t++)if (k = s[t], "zIndex"in k)if (k.zIndex == A[n]) {
          P.push(k.apply(b, d));
          if (l)break;
          do if (n++, (k = m[A[n]]) && P.push(k.apply(b, d)), l)break; while (k)
        } else m[k.zIndex] = k; else if (P.push(k.apply(b, d)), l)break;
        l = f;
        c = z;
        return P.length ? P : null
      };
      q._events = a;
      q.listeners = function (c) {
        c = c.split(b);
        var d = a, f, g, l, n, k, q, m, P = [d], z = [];
        l = 0;
        for (n = c.length; l < n; l++) {
          m = [];
          k = 0;
          for (q = P.length; k < q; k++)for (d = P[k].n, f = [d[c[l]], d["*"]], g = 2; g--;)if (d = f[g])m.push(d), z = z.concat(d.f || []);
          P = m
        }
        return z
      };
      q.on = function (c, d) {
        c = String(c);
        if ("function" != typeof d)return function () {
        };
        for (var f = c.split(b), g = a, l = 0, n = f.length; l < n; l++)g = g.n, g = g.hasOwnProperty(f[l]) && g[f[l]] || (g[f[l]] = {n: {}});
        g.f = g.f || [];
        l = 0;
        for (n = g.f.length; l < n; l++)if (g.f[l] == d)return B;
        g.f.push(d);
        return function (a) {
          +a == +a && (d.zIndex = +a)
        }
      };
      q.f = function (a) {
        var b = [].slice.call(arguments, 1);
        return function () {
          q.apply(null, [a, null].concat(b).concat([].slice.call(arguments, 0)))
        }
      };
      q.stop = function () {
        l = 1
      };
      q.nt = function (a) {
        return a ? (new RegExp("(?:\\.|\\/|^)" +
        a + "(?:\\.|\\/|$)")).test(c) : c
      };
      q.nts = function () {
        return c.split(b)
      };
      q.off = q.unbind = function (c, d) {
        if (c) {
          var f = c.split(b), g, l, n, k, A, m, P = [a];
          k = 0;
          for (A = f.length; k < A; k++)for (m = 0; m < P.length; m += n.length - 2) {
            n = [m, 1];
            g = P[m].n;
            if ("*" != f[k])g[f[k]] && n.push(g[f[k]]); else for (l in g)g.hasOwnProperty(l) && n.push(g[l]);
            P.splice.apply(P, n)
          }
          k = 0;
          for (A = P.length; k < A; k++)for (g = P[k]; g.n;) {
            if (d) {
              if (g.f) {
                m = 0;
                for (f = g.f.length; m < f; m++)if (g.f[m] == d) {
                  g.f.splice(m, 1);
                  break
                }
                !g.f.length && delete g.f
              }
              for (l in g.n)if (g.n.hasOwnProperty(l) &&
                g.n[l].f) {
                n = g.n[l].f;
                m = 0;
                for (f = n.length; m < f; m++)if (n[m] == d) {
                  n.splice(m, 1);
                  break
                }
                !n.length && delete g.n[l].f
              }
            } else for (l in delete g.f, g.n)g.n.hasOwnProperty(l) && g.n[l].f && delete g.n[l].f;
            g = g.n
          }
        } else q._events = a = {n: {}}
      };
      q.once = function (a, b) {
        var c = function () {
          q.unbind(a, c);
          return b.apply(this, arguments)
        };
        return q.on(a, c)
      };
      q.version = "0.4.2";
      q.toString = function () {
        return "You are running Eve 0.4.2"
      };
      "undefined" != typeof module && module.exports ? module.exports = q : k || "undefined" == typeof define ? d.eve = q : define("eve",
        [], function () {
          return q
        })
    })(this, !0);
    (function (d, k, b) {
      !b && "function" === typeof define && define.amd ? define(["eve"], function (b) {
        return k(d, b)
      }) : k(d, d.eve)
    })(this, function (d, k) {
      function b(a) {
        var c, e;
        void 0 === b._url && (b._url = "");
        if (b.is(a, "function"))return n ? a() : k.on("raphael.DOMload", a);
        if (b.is(a, m))return b._engine.create[Q](b, a.splice(0, 3 + b.is(a[0], A))).add(a);
        c = Array.prototype.slice.call(arguments, 0);
        return b.is(c[c.length - 1], "function") ? (e = c.pop(), n ? e.call(b._engine.create[Q](b, c)) : k.on("raphael.DOMload",
          function () {
            e.call(b._engine.create[Q](b, c))
          })) : b._engine.create[Q](b, arguments)
      }

      function K() {
        return this.hex
      }

      function G(a, b) {
        for (var c = [], e = 0, r = a.length; r - 2 * !b > e; e += 2) {
          var h = [{x: +a[e - 2], y: +a[e - 1]}, {x: +a[e], y: +a[e + 1]}, {x: +a[e + 2], y: +a[e + 3]}, {
            x: +a[e + 4],
            y: +a[e + 5]
          }];
          b ? e ? r - 4 == e ? h[3] = {x: +a[0], y: +a[1]} : r - 2 == e && (h[2] = {
            x: +a[0],
            y: +a[1]
          }, h[3] = {x: +a[2], y: +a[3]}) : h[0] = {
            x: +a[r - 2],
            y: +a[r - 1]
          } : r - 4 == e ? h[3] = h[2] : e || (h[0] = {x: +a[e], y: +a[e + 1]});
          c.push(["C", (-h[0].x + 6 * h[1].x + h[2].x) / 6, (-h[0].y + 6 * h[1].y + h[2].y) / 6, (h[1].x +
          6 * h[2].x - h[3].x) / 6, (h[1].y + 6 * h[2].y - h[3].y) / 6, h[2].x, h[2].y])
        }
        return c
      }

      function c(a, b, c, e, r, h, w, v, D) {
        null == D && (D = 1);
        D = (1 < D ? 1 : 0 > D ? 0 : D) / 2;
        for (var f = [-.1252, .1252, -.3678, .3678, -.5873, .5873, -.7699, .7699, -.9041, .9041, -.9816, .9816], d = [.2491, .2491, .2335, .2335, .2032, .2032, .1601, .1601, .1069, .1069, .0472, .0472], g = 0, m = 0; 12 > m; m++)var p = D * f[m] + D, I = p * (p * (-3 * a + 9 * c - 9 * r + 3 * w) + 6 * a - 12 * c + 6 * r) - 3 * a + 3 * c, p = p * (p * (-3 * b + 9 * e - 9 * h + 3 * v) + 6 * b - 12 * e + 6 * h) - 3 * b + 3 * e, g = g + d[m] * na(I * I + p * p);
        return D * g
      }

      function l(a, b, e, r, h, w, v, D, f) {
        if (!(0 > f ||
          c(a, b, e, r, h, w, v, D) < f)) {
          var d = .5, g = 1 - d, m;
          for (m = c(a, b, e, r, h, w, v, D, g); .01 < xa(m - f);)d /= 2, g += (m < f ? 1 : -1) * d, m = c(a, b, e, r, h, w, v, D, g);
          return g
        }
      }

      function a(a, e, r) {
        a = b._path2curve(a);
        e = b._path2curve(e);
        for (var h, w, v, D, f, d, g, m, p, I, W = r ? 0 : [], N = 0, l = a.length; N < l; N++)if (p = a[N], "M" == p[0])h = f = p[1], w = d = p[2]; else {
          "C" == p[0] ? (p = [h, w].concat(p.slice(1)), h = p[6], w = p[7]) : (p = [h, w, h, w, f, d, f, d], h = f, w = d);
          for (var n = 0, J = e.length; n < J; n++)if (I = e[n], "M" == I[0])v = g = I[1], D = m = I[2]; else {
            "C" == I[0] ? (I = [v, D].concat(I.slice(1)), v = I[6], D = I[7]) :
              (I = [v, D, v, D, g, m, g, m], v = g, D = m);
            var F;
            var ja = p, k = I;
            F = r;
            var H = b.bezierBBox(ja), s = b.bezierBBox(k);
            if (b.isBBoxIntersect(H, s)) {
              for (var H = c.apply(0, ja), s = c.apply(0, k), H = da(~~(H / 5), 1), s = da(~~(s / 5), 1), M = [], L = [], z = {}, Wa = F ? 0 : [], O = 0; O < H + 1; O++) {
                var u = b.findDotsAtSegment.apply(b, ja.concat(O / H));
                M.push({x: u.x, y: u.y, t: O / H})
              }
              for (O = 0; O < s + 1; O++)u = b.findDotsAtSegment.apply(b, k.concat(O / s)), L.push({
                x: u.x,
                y: u.y,
                t: O / s
              });
              for (O = 0; O < H; O++)for (ja = 0; ja < s; ja++) {
                var Ha = M[O], Dc = M[O + 1], k = L[ja], u = L[ja + 1], ca = .001 > xa(Dc.x - Ha.x) ? "y" :
                  "x", R = .001 > xa(u.x - k.x) ? "y" : "x", q;
                q = Ha.x;
                var Sa = Ha.y, sa = Dc.x, U = Dc.y, P = k.x, A = k.y, ea = u.x, T = u.y;
                if (da(q, sa) < fa(P, ea) || fa(q, sa) > da(P, ea) || da(Sa, U) < fa(A, T) || fa(Sa, U) > da(A, T))q = void 0; else {
                  var za = (q * U - Sa * sa) * (P - ea) - (q - sa) * (P * T - A * ea), $a = (q * U - Sa * sa) * (A - T) - (Sa - U) * (P * T - A * ea), ic = (q - sa) * (A - T) - (Sa - U) * (P - ea);
                  if (ic) {
                    var za = za / ic, $a = $a / ic, ic = +za.toFixed(2), t = +$a.toFixed(2);
                    q = ic < +fa(q, sa).toFixed(2) || ic > +da(q, sa).toFixed(2) || ic < +fa(P, ea).toFixed(2) || ic > +da(P, ea).toFixed(2) || t < +fa(Sa, U).toFixed(2) || t > +da(Sa, U).toFixed(2) ||
                    t < +fa(A, T).toFixed(2) || t > +da(A, T).toFixed(2) ? void 0 : {x: za, y: $a}
                  } else q = void 0
                }
                q && z[q.x.toFixed(4)] != q.y.toFixed(4) && (z[q.x.toFixed(4)] = q.y.toFixed(4), Ha = Ha.t + xa((q[ca] - Ha[ca]) / (Dc[ca] - Ha[ca])) * (Dc.t - Ha.t), k = k.t + xa((q[R] - k[R]) / (u[R] - k[R])) * (u.t - k.t), 0 <= Ha && 1.001 >= Ha && 0 <= k && 1.001 >= k && (F ? Wa++ : Wa.push({
                  x: q.x,
                  y: q.y,
                  t1: fa(Ha, 1),
                  t2: fa(k, 1)
                })))
              }
              F = Wa
            } else F = F ? 0 : [];
            if (r)W += F; else {
              H = 0;
              for (s = F.length; H < s; H++)F[H].segment1 = N, F[H].segment2 = n, F[H].bez1 = p, F[H].bez2 = I;
              W = W.concat(F)
            }
          }
        }
        return W
      }

      function q(a, b, c, e, r,
                 h) {
        null != a ? (this.a = +a, this.b = +b, this.c = +c, this.d = +e, this.e = +r, this.f = +h) : (this.a = 1, this.c = this.b = 0, this.d = 1, this.f = this.e = 0)
      }

      function S() {
        return this.x + " " + this.y + " " + this.width + " × " + this.height
      }

      function C(a, b, c, e, r, h) {
        function w(a, b) {
          var c, e, fb, r;
          fb = a;
          for (e = 0; 8 > e; e++) {
            r = ((f * fb + D) * fb + v) * fb - a;
            if (xa(r) < b)return fb;
            c = (3 * f * fb + 2 * D) * fb + v;
            if (1E-6 > xa(c))break;
            fb -= r / c
          }
          c = 0;
          e = 1;
          fb = a;
          if (fb < c)return c;
          if (fb > e)return e;
          for (; c < e;) {
            r = ((f * fb + D) * fb + v) * fb;
            if (xa(r - a) < b)break;
            a > r ? c = fb : e = fb;
            fb = (e - c) / 2 + c
          }
          return fb
        }

        var v =
          3 * b, D = 3 * (e - b) - v, f = 1 - v - D, d = 3 * c, g = 3 * (r - c) - d, m = 1 - d - g;
        return function (a, b) {
          var c = w(a, b);
          return ((m * c + g) * c + d) * c
        }(a, 1 / (200 * h))
      }

      function f(a, b) {
        var c = [], e = {};
        this.ms = b;
        this.times = 1;
        if (a) {
          for (var r in a)a.hasOwnProperty(r) && (e[J(r)] = a[r], c.push(J(r)));
          c.sort(I)
        }
        this.anim = e;
        this.top = c[c.length - 1];
        this.percents = c
      }

      function g(a, c, r, w, D, f) {
        r = J(r);
        var d, g, m, I, W, N, l = a.ms, n = {}, F = {}, ja = {};
        if (w)for (N = 0, s = Ra.length; N < s; N++) {
          var H = Ra[N];
          if (H.el.id == c.id && H.anim == a) {
            H.percent != r ? (Ra.splice(N, 1), m = 1) : g = H;
            c.attr(H.totalOrigin);
            break
          }
        } else w = +F;
        N = 0;
        for (var s = a.percents.length; N < s; N++)if (a.percents[N] == r || a.percents[N] > w * a.top) {
          r = a.percents[N];
          W = a.percents[N - 1] || 0;
          l = l / a.top * (r - W);
          I = a.percents[N + 1];
          d = a.anim[r];
          break
        } else w && c.attr(a.anim[a.percents[N]]);
        if (d) {
          if (g)g.initstatus = w, g.start = new Date - g.ms * w; else {
            for (var M in d)if (d.hasOwnProperty(M) && (lb.hasOwnProperty(M) || c.ca[M]))switch (n[M] = c.attr(M), null == n[M] && (n[M] = Va[M]), F[M] = d[M], lb[M]) {
              case A:
                ja[M] = (F[M] - n[M]) / l;
                break;
              case "colour":
                n[M] = b.getRGB(n[M]);
                N = b.getRGB(F[M]);
                ja[M] = {r: (N.r - n[M].r) / l, g: (N.g - n[M].g) / l, b: (N.b - n[M].b) / l};
                break;
              case "path":
                N = mb(n[M], F[M]);
                H = N[1];
                n[M] = N[0];
                ja[M] = [];
                N = 0;
                for (s = n[M].length; N < s; N++) {
                  ja[M][N] = [0];
                  for (var L = 1, z = n[M][N].length; L < z; L++)ja[M][N][L] = (H[N][L] - n[M][N][L]) / l
                }
                break;
              case "transform":
                N = c._;
                if (s = e(N[M], F[M]))for (n[M] = s.from, F[M] = s.to, ja[M] = [], ja[M].real = !0, N = 0, s = n[M].length; N < s; N++)for (ja[M][N] = [n[M][N][0]], L = 1, z = n[M][N].length; L < z; L++)ja[M][N][L] = (F[M][N][L] - n[M][N][L]) / l; else s = c.matrix || new q, N = {
                  _: {transform: N.transform},
                  getBBox: function () {
                    return c.getBBox(1)
                  }
                }, n[M] = [s.a, s.b, s.c, s.d, s.e, s.f], v(N, F[M]), F[M] = N._.transform, ja[M] = [(N.matrix.a - s.a) / l, (N.matrix.b - s.b) / l, (N.matrix.c - s.c) / l, (N.matrix.d - s.d) / l, (N.matrix.e - s.e) / l, (N.matrix.f - s.f) / l];
                break;
              case "csv":
                s = h(d[M]).split(jb);
                H = h(n[M]).split(jb);
                if ("clip-rect" == M)for (n[M] = H, ja[M] = [], N = H.length; N--;)ja[M][N] = (s[N] - n[M][N]) / l;
                F[M] = s;
                break;
              default:
                for (s = [].concat(d[M]), H = [].concat(n[M]), ja[M] = [], N = c.ca[M].length; N--;)ja[M][N] = ((s[N] || 0) - (H[N] || 0)) / l
            }
            N = d.easing;
            M = b.easing_formulas[N];
            if (!M)if ((M = h(N).match(Ta)) && 5 == M.length) {
              var O = M;
              M = function (a) {
                return C(a, +O[1], +O[2], +O[3], +O[4], l)
              }
            } else M = p;
            N = d.start || a.start || +new Date;
            H = {
              anim: a,
              percent: r,
              timestamp: N,
              start: N + (a.del || 0),
              status: 0,
              initstatus: w || 0,
              stop: !1,
              ms: l,
              easing: M,
              from: n,
              diff: ja,
              to: F,
              el: c,
              callback: d.callback,
              prev: W,
              next: I,
              repeat: f || a.times,
              origin: c.attr(),
              totalOrigin: D
            };
            Ra.push(H);
            if (w && !g && !m && (H.stop = !0, H.start = new Date - l * w, 1 == Ra.length))return bd();
            m && (H.start = new Date - H.ms * w);
            1 == Ra.length && md(bd)
          }
          k("raphael.anim.start." +
          c.id, c, a)
        }
      }

      function s(a) {
        for (var b = 0; b < Ra.length; b++)Ra[b].el.paper == a && Ra.splice(b--, 1)
      }

      b.upgrade = "1.0.0";
      b.version = "2.1.0";
      b.eve = k;
      B = b;
      var n, Q = "apply", A = "number", m = "array", P = Array.prototype.slice, z = Array.prototype.splice, X = function () {
          return function () {
          }.hasOwnProperty("prototype")
        }(), u = {
          doc: document,
          win: d
        }, ka = Object.prototype.hasOwnProperty.call(u.win, "Raphael"), ha = u.win.Raphael, ba = u.doc, Z = u.win, za = b.supportsTouch = "createTouch"in ba, Aa = b.supportsOnlyTouch = za && !(Z.navigator.maxTouchPoints || Z.navigator.msMaxTouchPoints),
        Ka = function () {
        };
      b.ca = b.customAttributes = Ka.prototype;
      var $ = function () {
        this.ca = this.customAttributes = new Ka;
        this._CustomAttributes = function () {
        };
        this._CustomAttributes.prototype = this.ca;
        this._elementsById = {};
        this.id = b._oid++;
        k("raphael.new", this)
      }, aa = b.fn = $.prototype = b.prototype, qa = {
        circle: 1,
        rect: 1,
        path: 1,
        ellipse: 1,
        text: 1,
        image: 1,
        group: 1
      }, ga = "click dblclick mousedown mousemove mouseout mouseover mouseup touchstart touchmove touchend touchcancel dragstart dragmove dragend".split(" "), R = b._touchMap = {
        mousedown: "touchstart",
        mousemove: "touchmove", mouseup: "touchend"
      }, T = b._dragEventMap = {
        dragstart: "mousedown",
        dragmove: "mousemove",
        dragend: "mouseup"
      }, h = Z.String, J = Z.parseFloat, L = Z.parseInt, U = Z.Math, da = U.max, fa = U.min, xa = U.abs, ya = U.pow, Fa = U.cos, Da = U.sin, na = U.sqrt, ua = U.round, Ea = U.PI, pa = Ea / 180, bb = 180 / Ea, va = h.prototype.toLowerCase, Ya = h.prototype.toUpperCase, Ba = Z.Object.prototype.toString, jb = /[, ]+/, Ca = /\{(\d+)\}/g;
      b._ISURL = /^url\(['"]?([^\)]+?)['"]?\)$/i;
      var db = /^\s*((#[a-f\d]{6})|(#[a-f\d]{3})|rgba?\(\s*([\d\.]+%?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+%?(?:\s*,\s*[\d\.]+%?)?)\s*\)|hsba?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+(?:%?\s*,\s*[\d\.]+)?)%?\s*\)|hsla?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+(?:%?\s*,\s*[\d\.]+)?)%?\s*\))\s*$/i,
        Ta = /^(?:cubic-)?bezier\(([^,]+),([^,]+),([^,]+),([^\)]+)\)/, ra = /[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*/, Qb = /,?([achlmqrstvxz]),?/gi, qb = /([achlmrqstvz])[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029,]*((-?\d*\.?\d*(?:e[\-+]?\d+)?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*)+)/ig,
        Ib = /([rstm])[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029,]*((-?\d*\.?\d*(?:e[\-+]?\d+)?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*)+)/ig, Rb = /(-?\d*\.?\d*(?:e[\-+]?\d+)?)[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*,?[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\u2028\u2029]*/ig;
      b._radial_gradient = /^x?r(?:\(([^\)]*?)\))?/;
      var eb = {NaN: 1, Infinity: 1, "-Infinity": 1}, Xa = {hs: 1, rg: 1}, Va = b._availableAttrs = {
        "arrow-end": "none",
        "arrow-start": "none",
        blur: 0,
        "clip-rect": "0 0 1e9 1e9",
        "clip-path": "",
        cursor: "default",
        cx: 0,
        cy: 0,
        fill: "#fff",
        "fill-opacity": 1,
        font: '10px "Arial"',
        "font-family": '"Arial"',
        "font-size": "10",
        "font-style": "normal",
        "font-weight": 400,
        gradient: 0,
        height: 0,
        href: "about:blank",
        "letter-spacing": 0,
        "line-height": 12,
        "vertical-align": "middle",
        opacity: 1,
        path: "M0,0",
        r: 0,
        rx: 0,
        ry: 0,
        src: "",
        stroke: "#000",
        "stroke-dasharray": "",
        "stroke-linecap": "butt",
        "stroke-linejoin": "butt",
        "stroke-miterlimit": 0,
        "stroke-opacity": 1,
        "stroke-width": 1,
        target: "_blank",
        "text-anchor": "middle",
        visibility: "",
        title: "",
        transform: "",
        rotation: 0,
        width: 0,
        x: 0,
        y: 0
      }, lb = b._availableAnimAttrs = {
        blur: A,
        "clip-rect": "csv",
        "clip-path": "path",
        cx: A,
        cy: A,
        fill: "colour",
        "fill-opacity": A,
        "font-size": A,
        height: A,
        opacity: A,
        path: "path",
        r: A,
        rx: A,
        ry: A,
        stroke: "colour",
        "stroke-opacity": A,
        "stroke-width": A,
        transform: "transform",
        width: A,
        x: A,
        y: A
      }, xb = {}, I = function (a, b) {
        return J(a) - J(b)
      }, H = function () {
      }, p = function (a) {
        return a
      }, O = b._rectPath = function (a, b, c, e, r) {
        return r ? [["M", a + r, b], ["l", c - 2 * r, 0], ["a", r, r, 0, 0, 1, r, r], ["l", 0, e - 2 * r], ["a", r, r, 0, 0, 1, -r, r], ["l", 2 * r - c, 0], ["a", r, r, 0, 0, 1, -r, -r], ["l", 0, 2 * r - e], ["a", r, r, 0, 0, 1, r, -r], ["z"]] : [["M", a, b], ["l", c, 0], ["l", 0, e], ["l", -c, 0], ["z"]]
      }, ca = function (a, b, c, e) {
        null == e && (e = c);
        return [["M", a, b], ["m", 0, -e], ["a", c, e, 0, 1, 1, 0, 2 * e], ["a", c, e, 0, 1, 1, 0, -2 * e], ["z"]]
      }, ea = b._getPath = {
        group: function () {
          return !1
        }, path: function (a) {
          return a.attr("path")
        },
        circle: function (a) {
          a = a.attrs;
          return ca(a.cx, a.cy, a.r)
        }, ellipse: function (a) {
          a = a.attrs;
          return ca(a.cx, a.cy, a.rx, a.ry)
        }, rect: function (a) {
          a = a.attrs;
          return O(a.x, a.y, a.width, a.height, a.r)
        }, image: function (a) {
          a = a.attrs;
          return O(a.x, a.y, a.width, a.height)
        }, text: function (a) {
          a = a._getBBox();
          return O(a.x, a.y, a.width, a.height)
        }
      }, ma = b.mapPath = function (a, b) {
        if (!b)return a;
        var c, e, r, h, w, v, D;
        a = mb(a);
        r = 0;
        for (w = a.length; r < w; r++)for (D = a[r], h = 1, v = D.length; h < v; h += 2)c = b.x(D[h], D[h + 1]), e = b.y(D[h], D[h + 1]), D[h] = c, D[h + 1] = e;
        return a
      };
      b.pick = function () {
        for (var a, b = 0, c = arguments.length; b < c; b += 1)if ((a = arguments[b]) || !1 === a || 0 === a)return a
      };
      var oa = b._lastArgIfGroup = function (a, c) {
        var e = a.length - 1, r = a[e];
        if (r && r.constructor === b.el.constructor && "group" === r.type)return c && (a[e] = void 0, delete a[e], z.call(a, e, 1)), r
      }, la = b._serializeArgs = function (a) {
        var c = a[0], e, r;
        if (b.is(c, "object") && !b.is(c, "array") && "group" !== c.type)for (e = c, c.path && (c = c.path) && !b.is(c, "string") && b.is(c[0], m), c = 1, r = arguments.length; c < r; c += 2)e[arguments[c]] || (e[arguments[c]] =
          arguments[c + 1]); else for (e = {}, c = 1, r = arguments.length; c < r; c += 2)e[arguments[c]] = a[(c - 1) / 2] || arguments[c + 1];
        return e
      }, La = b.merge = function (a, b, c, e, r) {
        var h, w, v, D;
        r ? (e.push(a), r.push(b)) : (e = [a], r = [b]);
        if (b instanceof Array)for (h = 0; h < b.length; h += 1) {
          try {
            w = a[h], v = b[h]
          } catch (f) {
            continue
          }
          if ("object" !== typeof v)c && void 0 === v || (a[h] = v); else {
            if (null === w || "object" !== typeof w)w = a[h] = v instanceof Array ? [] : {};
            D = checkCyclicRef(v, r);
            -1 !== D ? w = a[h] = e[D] : La(w, v, c, e, r)
          }
        } else for (h in b) {
          try {
            w = a[h], v = b[h]
          } catch (d) {
            continue
          }
          if (null !==
            v && "object" === typeof v)if (D = Ba.call(v), "[object Object]" === D) {
            if (null === w || "object" !== typeof w)w = a[h] = {};
            D = checkCyclicRef(v, r);
            -1 !== D ? w = a[h] = e[D] : La(w, v, c, e, r)
          } else"[object Array]" === D ? (null !== w && w instanceof Array || (w = a[h] = []), D = checkCyclicRef(v, r), -1 !== D ? w = a[h] = e[D] : La(w, v, c, e, r)) : a[h] = v; else a[h] = v
        }
        return a
      };
      b.extend = function (a, b, c) {
        if ("object" !== typeof a && "object" !== typeof b)return null;
        if ("object" !== typeof b || null === b)return a;
        "object" !== typeof a && (a = b instanceof Array ? [] : {});
        La(a, b, c);
        return a
      };
      var Ja = b.is = function (a, b) {
        b = va.call(b);
        return "finite" == b ? !eb.hasOwnProperty(+a) : b == m ? a instanceof Array : "object" !== b || void 0 !== a && null !== a ? "null" == b && null === a || b == typeof a && null !== a || "object" == b && a === Object(a) || "array" == b && Array.isArray && Array.isArray(a) || Ba.call(a).slice(8, -1).toLowerCase() == b : !1
      };
      b.createUUID = function (a, b) {
        return function () {
          return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(a, b).toUpperCase()
        }
      }(/[xy]/g, function (a) {
        var b = 16 * U.random() | 0;
        return ("x" == a ? b : b & 3 | 8).toString(16)
      });
      var V =
        b.clone = X ? function (a) {
          if (Object(a) !== a)return a;
          var b = new a.constructor, c;
          for (c in a)"prototype" !== c && a.hasOwnProperty(c) && (b[c] = V(a[c]));
          return b
        } : function (a) {
          if (Object(a) !== a)return a;
          var b = new a.constructor, c;
          for (c in a)a.hasOwnProperty(c) && (b[c] = V(a[c]));
          return b
        };
      b._g = u;
      b.type = Z.ENABLE_RED_CANVAS && (Z.CanvasRenderingContext2D || ba.createElement("canvas").getContext) ? "CANVAS" : Z.SVGAngle || ba.implementation.hasFeature("http://www.w3.org/TR/SVG11/feature#BasicStructure", "1.1") ? "SVG" : "VML";
      if ("VML" ==
        b.type) {
        var ia = ba.createElement("div"), Ia;
        ia.innerHTML = '<v:shape adj="1"/>';
        Ia = ia.firstChild;
        Ia.style.behavior = "url(#default#VML)";
        if (!Ia || "object" != typeof Ia.adj)return b.type = "";
        ia = null
      }
      b.svg = !((b.vml = "VML" == b.type) || (b.canvas = "CANVAS" == b.type));
      b._Paper = $;
      b._id = 0;
      b._oid = 0;
      b.angle = function (a, c, e, r, h, w) {
        return null == h ? (a -= e, c -= r, a || c ? (U.atan2(-c, -a) * bb + 540) % 360 : 0) : b.angle(a, c, h, w) - b.angle(e, r, h, w)
      };
      b.rad = function (a) {
        return a % 360 * pa
      };
      b.deg = function (a) {
        return a * bb % 360
      };
      b.snapTo = function (a, b, c) {
        var e;
        Ja(c, "finite") || (c = 10);
        if (Ja(a, m))for (e = a.length; e--;) {
          if (xa(a[e] - b) <= c)return a[e]
        } else {
          a = +a;
          e = b % a;
          if (e < c)return b - e;
          if (e > a - c)return b - e + a
        }
        return b
      };
      b.setWindow = function (a) {
        k("raphael.setWindow", b, u.win, a);
        Z = u.win = a;
        ba = u.doc = u.win.document;
        b._engine.initWin && b._engine.initWin(u.win)
      };
      var wa = function (a) {
        if (b.vml) {
          var c = /^\s+|\s+$/g, e;
          try {
            var r = new ActiveXObject("htmlfile");
            r.write("<body>");
            r.close();
            e = r.body
          } catch (w) {
            e = createPopup().document.body
          }
          var v = e.createTextRange();
          wa = Oa(function (a) {
            try {
              e.style.color =
                h(a).replace(c, "");
              var b = v.queryCommandValue("ForeColor");
              return "#" + ("000000" + ((b & 255) << 16 | b & 65280 | (b & 16711680) >>> 16).toString(16)).slice(-6)
            } catch (r) {
              return "none"
            }
          })
        } else {
          var D = u.doc.createElement("i");
          D.title = "Raphaël Colour Picker";
          D.style.display = "none";
          u.doc.body.appendChild(D);
          wa = Oa(function (a) {
            D.style.color = a;
            return u.doc.defaultView.getComputedStyle(D, "").getPropertyValue("color")
          })
        }
        return wa(a)
      }, hb = function () {
        return "hsb(" + [this.h, this.s, this.b] + ")"
      }, Ga = function () {
        return "hsl(" + [this.h, this.s,
            this.l] + ")"
      }, ab = function () {
        return this.hex
      }, Bb = function (a, c, e) {
        null == c && Ja(a, "object") && "r"in a && "g"in a && "b"in a && (e = a.b, c = a.g, a = a.r);
        null == c && Ja(a, "string") && (e = b.getRGB(a), a = e.r, c = e.g, e = e.b);
        if (1 < a || 1 < c || 1 < e)a /= 255, c /= 255, e /= 255;
        return [a, c, e]
      }, Eb = function (a, c, e, r) {
        var h = {r: a *= 255, g: c *= 255, b: e *= 255, hex: b.rgb(a, c, e), toString: ab};
        Ja(r, "finite") && (h.opacity = r);
        return h
      };
      b.color = function (a) {
        var c;
        b.is(a, "object") && "h"in a && "s"in a && "b"in a ? (c = b.hsb2rgb(a), a.r = c.r, a.g = c.g, a.b = c.b, a.hex = c.hex) : b.is(a,
          "object") && "h"in a && "s"in a && "l"in a ? (c = b.hsl2rgb(a), a.r = c.r, a.g = c.g, a.b = c.b, a.hex = c.hex) : (b.is(a, "string") && (a = b.getRGB(a)), b.is(a, "object") && "r"in a && "g"in a && "b"in a ? (c = b.rgb2hsl(a), a.h = c.h, a.s = c.s, a.l = c.l, c = b.rgb2hsb(a), a.v = c.b) : (a = {hex: "none"}, a.r = a.g = a.b = a.h = a.s = a.v = a.l = -1));
        a.toString = ab;
        return a
      };
      b.hsb2rgb = function (a, b, c, e) {
        this.is(a, "object") && "h"in a && "s"in a && "b"in a && (c = a.b, b = a.s, a = a.h, e = a.o);
        var r, h, w;
        a = 360 * a % 360 / 60;
        w = c * b;
        b = w * (1 - xa(a % 2 - 1));
        c = r = h = c - w;
        a = ~~a;
        c += [w, b, 0, 0, b, w][a];
        r += [b, w,
          w, b, 0, 0][a];
        h += [0, 0, b, w, w, b][a];
        return Eb(c, r, h, e)
      };
      b.hsl2rgb = function (a, b, c, e) {
        this.is(a, "object") && "h"in a && "s"in a && "l"in a && (c = a.l, b = a.s, a = a.h);
        if (1 < a || 1 < b || 1 < c)a /= 360, b /= 100, c /= 100;
        var r, h, w;
        a = 360 * a % 360 / 60;
        w = 2 * b * (.5 > c ? c : 1 - c);
        b = w * (1 - xa(a % 2 - 1));
        c = r = h = c - w / 2;
        a = ~~a;
        c += [w, b, 0, 0, b, w][a];
        r += [b, w, w, b, 0, 0][a];
        h += [0, 0, b, w, w, b][a];
        return Eb(c, r, h, e)
      };
      b.rgb2hsb = function (a, b, c) {
        c = Bb(a, b, c);
        a = c[0];
        b = c[1];
        c = c[2];
        var e, r;
        e = da(a, b, c);
        r = e - fa(a, b, c);
        a = ((0 == r ? null : e == a ? (b - c) / r : e == b ? (c - a) / r + 2 : (a - b) / r + 4) + 360) % 6 * 60 /
        360;
        return {h: a, s: 0 == r ? 0 : r / e, b: e, toString: hb}
      };
      b.rgb2hsl = function (a, b, c) {
        c = Bb(a, b, c);
        a = c[0];
        b = c[1];
        c = c[2];
        var e, r, h;
        e = da(a, b, c);
        r = fa(a, b, c);
        h = e - r;
        a = ((0 == h ? null : e == a ? (b - c) / h : e == b ? (c - a) / h + 2 : (a - b) / h + 4) + 360) % 6 * 60 / 360;
        e = (e + r) / 2;
        return {h: a, s: 0 == h ? 0 : .5 > e ? h / (2 * e) : h / (2 - 2 * e), l: e, toString: Ga}
      };
      b._path2string = function () {
        return this.join(",").replace(Qb, "$1")
      };
      var Oa = b._cacher = function (a, b, c) {
        function e() {
          var r = P.call(arguments, 0), h = r.join("␀"), w = e.cache = e.cache || {}, v = e.count = e.count || [];
          if (w.hasOwnProperty(h)) {
            a:for (var r =
              v, v = h, D = 0, f = r.length; D < f; D++)if (r[D] === v) {
              r.push(r.splice(D, 1)[0]);
              break a
            }
            return c ? c(w[h]) : w[h]
          }
          1E3 <= v.length && delete w[v.shift()];
          v.push(h);
          w[h] = a[Q](b, r);
          return c ? c(w[h]) : w[h]
        }

        return e
      };
      b._preload = function (a, b) {
        var c = ba.createElement("img");
        c.style.cssText = "position:absolute;left:-9999em;top:-9999em";
        c.onload = function () {
          b.call(this);
          this.onload = null;
          ba.body.removeChild(this)
        };
        c.onerror = function () {
          ba.body.removeChild(this)
        };
        ba.body.appendChild(c);
        c.src = a
      };
      b.getRGB = Oa(function (a) {
        var c, e, r, w, v;
        a &&
        Ja(a, "object") && "opacity"in a && (c = a.opacity);
        if (!a || (a = h(a)).indexOf("-") + 1)return {r: -1, g: -1, b: -1, hex: "none", error: 1, toString: K};
        if ("none" == a)return {r: -1, g: -1, b: -1, hex: "none", toString: K};
        !Xa.hasOwnProperty(a.toLowerCase().substring(0, 2)) && "#" !== a.charAt() && (a = wa(a));
        if (a = a.match(db)) {
          a[2] && (w = L(a[2].substring(5), 16), r = L(a[2].substring(3, 5), 16), e = L(a[2].substring(1, 3), 16));
          a[3] && (w = L((v = a[3].charAt(3)) + v, 16), r = L((v = a[3].charAt(2)) + v, 16), e = L((v = a[3].charAt(1)) + v, 16));
          a[4] && (v = a[4].split(ra), e = J(v[0]),
          "%" == v[0].slice(-1) && (e *= 2.55), r = J(v[1]), "%" == v[1].slice(-1) && (r *= 2.55), w = J(v[2]), "%" == v[2].slice(-1) && (w *= 2.55), "rgba" == a[1].toLowerCase().slice(0, 4) && (c = J(v[3])), v[3] && "%" == v[3].slice(-1) && (c /= 100));
          if (a[5])return v = a[5].split(ra), e = J(v[0]), "%" == v[0].slice(-1) && (e *= 2.55), r = J(v[1]), "%" == v[1].slice(-1) && (r *= 2.55), w = J(v[2]), "%" == v[2].slice(-1) && (w *= 2.55), "deg" != v[0].slice(-3) && "°" != v[0].slice(-1) || (e /= 360), "hsba" == a[1].toLowerCase().slice(0, 4) && (c = J(v[3])), v[3] && "%" == v[3].slice(-1) && (c /= 100), b.hsb2rgb(e,
            r, w, c);
          if (a[6])return v = a[6].split(ra), e = J(v[0]), "%" == v[0].slice(-1) && (e *= 2.55), r = J(v[1]), "%" == v[1].slice(-1) && (r *= 2.55), w = J(v[2]), "%" == v[2].slice(-1) && (w *= 2.55), "deg" != v[0].slice(-3) && "°" != v[0].slice(-1) || (e /= 360), "hsla" == a[1].toLowerCase().slice(0, 4) && (c = J(v[3])), v[3] && "%" == v[3].slice(-1) && (c /= 100), b.hsl2rgb(e, r, w, c);
          a = {r: e, g: r, b: w, toString: K};
          a.hex = "#" + (16777216 | w | r << 8 | e << 16).toString(16).slice(1);
          b.is(c, "finite") && (a.opacity = c);
          return a
        }
        return {r: -1, g: -1, b: -1, hex: "none", error: 1, toString: K}
      }, b);
      b.tintshade = Oa(function (a, c) {
        var e = b.getRGB(a), r;
        r = 255;
        0 > c && (c *= -1, r = 0);
        1 < c && (c = 1);
        r = 0 === c ? e : {r: r - (r - e.r) * c, g: r - (r - e.g) * c, b: r - (r - e.b) * c, toString: K};
        r.hex = b.rgb(r.r, r.g, r.b);
        e.error && (r.error = e.error);
        "opacity"in e ? (r.rgba = "rgba(" + [r.r, r.g, r.b, e.opacity].join() + ")", r.opacity = e.opacity) : r.rgba = "rgb(" + [r.r, r.g, r.b].join() + ")";
        return r
      }, b);
      b.hsb = Oa(function (a, c, e) {
        return b.hsb2rgb(a, c, e).hex
      });
      b.hsl = Oa(function (a, c, e) {
        return b.hsl2rgb(a, c, e).hex
      });
      b.rgb = Oa(function (a, b, c) {
        return "#" + (16777216 | c | b << 8 | a <<
          16).toString(16).slice(1)
      });
      b.getColor = function (a) {
        a = this.getColor.start = this.getColor.start || {h: 0, s: 1, b: a || .75};
        var b = this.hsb2rgb(a.h, a.s, a.b);
        a.h += .075;
        1 < a.h && (a.h = 0, a.s -= .2, 0 >= a.s && (this.getColor.start = {h: 0, s: 1, b: a.b}));
        return b.hex
      };
      b.getColor.reset = function () {
        delete this.start
      };
      b.parsePathString = function (a) {
        if (!a)return null;
        var c = sb(a);
        if (c.arr)return Yb(c.arr);
        var e = {a: 7, c: 6, h: 1, l: 2, m: 2, r: 4, q: 4, s: 4, t: 2, v: 1, z: 0}, r = [];
        b.is(a, m) && b.is(a[0], m) && (r = Yb(a));
        r.length || h(a).replace(qb, function (a, b,
                                               c) {
          var h = [];
          a = b.toLowerCase();
          c.replace(Rb, function (a, b) {
            b && h.push(+b)
          });
          "m" == a && 2 < h.length && (r.push([b].concat(h.splice(0, 2))), a = "l", b = "m" == b ? "l" : "L");
          if ("r" == a)r.push([b].concat(h)); else for (; h.length >= e[a] && (r.push([b].concat(h.splice(0, e[a]))), e[a]););
        });
        r.toString = b._path2string;
        c.arr = Yb(r);
        return r
      };
      b.parseTransformString = Oa(function (a) {
        if (!a)return null;
        var c = [];
        b.is(a, m) && b.is(a[0], m) && (c = Yb(a));
        c.length || h(a).replace(Ib, function (a, b, e) {
          var r = [];
          va.call(b);
          e.replace(Rb, function (a, b) {
            b && r.push(+b)
          });
          c.push([b].concat(r))
        });
        c.toString = b._path2string;
        return c
      });
      var sb = function (a) {
        var b = sb.ps = sb.ps || {};
        b[a] ? b[a].sleep = 100 : b[a] = {sleep: 100};
        setTimeout(function () {
          for (var c in b)b.hasOwnProperty(c) && c != a && (b[c].sleep--, !b[c].sleep && delete b[c])
        });
        return b[a]
      };
      b.findDotsAtSegment = function (a, b, c, e, r, h, w, v, D) {
        var f = 1 - D, d = ya(f, 3), g = ya(f, 2), m = D * D, N = m * D, p = d * a + 3 * g * D * c + 3 * f * D * D * r + N * w, d = d * b + 3 * g * D * e + 3 * f * D * D * h + N * v, g = a + 2 * D * (c - a) + m * (r - 2 * c + a), N = b + 2 * D * (e - b) + m * (h - 2 * e + b), I = c + 2 * D * (r - c) + m * (w - 2 * r + c), m = e + 2 * D * (h - e) + m * (v -
          2 * h + e);
        a = f * a + D * c;
        b = f * b + D * e;
        r = f * r + D * w;
        h = f * h + D * v;
        v = 90 - 180 * U.atan2(g - I, N - m) / Ea;
        (g > I || N < m) && (v += 180);
        return {x: p, y: d, m: {x: g, y: N}, n: {x: I, y: m}, start: {x: a, y: b}, end: {x: r, y: h}, alpha: v}
      };
      b.bezierBBox = function (a, c, e, r, h, w, v, D) {
        b.is(a, "array") || (a = [a, c, e, r, h, w, v, D]);
        a = Cb.apply(null, a);
        return {x: a.min.x, y: a.min.y, x2: a.max.x, y2: a.max.y, width: a.max.x - a.min.x, height: a.max.y - a.min.y}
      };
      b.isPointInsideBBox = function (a, b, c) {
        return b >= a.x && b <= a.x2 && c >= a.y && c <= a.y2
      };
      b.isBBoxIntersect = function (a, c) {
        var e = b.isPointInsideBBox;
        return e(c, a.x, a.y) || e(c, a.x2, a.y) || e(c, a.x, a.y2) || e(c, a.x2, a.y2) || e(a, c.x, c.y) || e(a, c.x2, c.y) || e(a, c.x, c.y2) || e(a, c.x2, c.y2) || (a.x < c.x2 && a.x > c.x || c.x < a.x2 && c.x > a.x) && (a.y < c.y2 && a.y > c.y || c.y < a.y2 && c.y > a.y)
      };
      b.pathIntersection = function (b, c) {
        return a(b, c)
      };
      b.pathIntersectionNumber = function (b, c) {
        return a(b, c, 1)
      };
      b.isPointInsidePath = function (c, e, r) {
        var h = b.pathBBox(c);
        return b.isPointInsideBBox(h, e, r) && (1 == a(c, [["M", e, r], ["H", h.x2 + 10]], 1) % 2 || 1 == a(c, [["M", e, r], ["V", h.y2 + 10]], 1) % 2)
      };
      b._removedFactory = function (a) {
        return function () {
          k("raphael.log",
            null, "Raphaël: you are calling to method “" + a + "” of removed object", a)
        }
      };
      var Y = b.pathBBox = function (a) {
        var b = sb(a);
        if (b.bbox)return b.bbox;
        if (!a)return {x: 0, y: 0, width: 0, height: 0, x2: 0, y2: 0};
        a = mb(a);
        for (var c = 0, e = 0, r = [], h = [], w, v = 0, D = a.length; v < D; v++)w = a[v], "M" == w[0] ? (c = w[1], e = w[2], r.push(c), h.push(e)) : (c = Cb(c, e, w[1], w[2], w[3], w[4], w[5], w[6]), r = r.concat(c.min.x, c.max.x), h = h.concat(c.min.y, c.max.y), c = w[5], e = w[6]);
        a = fa[Q](0, r);
        w = fa[Q](0, h);
        r = da[Q](0, r);
        h = da[Q](0, h);
        h = {
          x: a, y: w, x2: r, y2: h, width: r - a, height: h -
          w
        };
        b.bbox = V(h);
        return h
      }, Yb = function (a) {
        a = V(a);
        a.toString = b._path2string;
        return a
      }, oc = b._pathToRelative = function (a) {
        var c = sb(a);
        if (c.rel)return Yb(c.rel);
        b.is(a, m) && b.is(a && a[0], m) || (a = b.parsePathString(a));
        var e = [], r = 0, h = 0, w = 0, v = 0, D = 0;
        "M" == a[0][0] && (r = a[0][1], h = a[0][2], w = r, v = h, D++, e.push(["M", r, h]));
        for (var f = a.length; D < f; D++) {
          var d = e[D] = [], g = a[D];
          if (g[0] != va.call(g[0]))switch (d[0] = va.call(g[0]), d[0]) {
            case "a":
              d[1] = g[1];
              d[2] = g[2];
              d[3] = g[3];
              d[4] = g[4];
              d[5] = g[5];
              d[6] = +(g[6] - r).toFixed(3);
              d[7] = +(g[7] -
              h).toFixed(3);
              break;
            case "v":
              d[1] = +(g[1] - h).toFixed(3);
              break;
            case "m":
              w = g[1], v = g[2];
            default:
              for (var N = 1, p = g.length; N < p; N++)d[N] = +(g[N] - (N % 2 ? r : h)).toFixed(3)
          } else for (e[D] = [], "m" == g[0] && (w = g[1] + r, v = g[2] + h), d = 0, N = g.length; d < N; d++)e[D][d] = g[d];
          g = e[D].length;
          switch (e[D][0]) {
            case "z":
              r = w;
              h = v;
              break;
            case "h":
              r += +e[D][g - 1];
              break;
            case "v":
              h += +e[D][g - 1];
              break;
            default:
              r += +e[D][g - 2], h += +e[D][g - 1]
          }
        }
        e.toString = b._path2string;
        c.rel = Yb(e);
        return e
      }, Kb = b._pathToAbsolute = function (a) {
        var c = sb(a), e;
        if (c.abs)return Yb(c.abs);
        b.is(a, m) && b.is(a && a[0], m) || (a = b.parsePathString(a));
        if (!a || !a.length)return e = ["M", 0, 0], e.toString = b._path2string, e;
        var r = 0, h = 0, w = 0, v = 0, D = 0;
        e = [];
        "M" == a[0][0] && (r = +a[0][1], h = +a[0][2], w = r, v = h, D++, e[0] = ["M", r, h]);
        for (var f = 3 == a.length && "M" == a[0][0] && "R" == a[1][0].toUpperCase() && "Z" == a[2][0].toUpperCase(), d, g = D, N = a.length; g < N; g++) {
          e.push(D = []);
          d = a[g];
          if (d[0] != Ya.call(d[0]))switch (D[0] = Ya.call(d[0]), D[0]) {
            case "A":
              D[1] = d[1];
              D[2] = d[2];
              D[3] = d[3];
              D[4] = d[4];
              D[5] = d[5];
              D[6] = +(d[6] + r);
              D[7] = +(d[7] + h);
              break;
            case "V":
              D[1] =
                +d[1] + h;
              break;
            case "H":
              D[1] = +d[1] + r;
              break;
            case "R":
              for (var p = [r, h].concat(d.slice(1)), I = 2, W = p.length; I < W; I++)p[I] = +p[I] + r, p[++I] = +p[I] + h;
              e.pop();
              e = e.concat(G(p, f));
              break;
            case "M":
              w = +d[1] + r, v = +d[2] + h;
            default:
              for (I = 1, W = d.length; I < W; I++)D[I] = +d[I] + (I % 2 ? r : h)
          } else if ("R" == d[0])p = [r, h].concat(d.slice(1)), e.pop(), e = e.concat(G(p, f)), D = ["R"].concat(d.slice(-2)); else for (p = 0, I = d.length; p < I; p++)D[p] = d[p];
          switch (D[0]) {
            case "Z":
              r = w;
              h = v;
              break;
            case "H":
              r = D[1];
              break;
            case "V":
              h = D[1];
              break;
            case "M":
              w = D[D.length - 2],
                v = D[D.length - 1];
            default:
              r = D[D.length - 2], h = D[D.length - 1]
          }
        }
        e.toString = b._path2string;
        c.abs = Yb(e);
        return e
      }, Gb = function (a, b, c, e) {
        return [a, b, c, e, c, e]
      }, Vb = function (a, b, c, e, r, h) {
        var w = 1 / 3, v = 2 / 3;
        return [w * a + v * c, w * b + v * e, w * r + v * c, w * h + v * e, r, h]
      }, ub = function (a, b, c, e, r, h, w, v, D, d) {
        var f = 120 * Ea / 180, g = pa * (+r || 0), m = [], p, N = Oa(function (a, b, c) {
          var e = a * Fa(c) - b * Da(c);
          a = a * Da(c) + b * Fa(c);
          return {x: e, y: a}
        });
        if (d)l = d[0], p = d[1], h = d[2], I = d[3]; else {
          p = N(a, b, -g);
          a = p.x;
          b = p.y;
          p = N(v, D, -g);
          v = p.x;
          D = p.y;
          Fa(pa * r);
          Da(pa * r);
          p = (a - v) / 2;
          l = (b -
          D) / 2;
          I = p * p / (c * c) + l * l / (e * e);
          1 < I && (I = na(I), c *= I, e *= I);
          var I = c * c, W = e * e, I = (h == w ? -1 : 1) * na(xa((I * W - I * l * l - W * p * p) / (I * l * l + W * p * p)));
          h = I * c * l / e + (a + v) / 2;
          var I = I * -e * p / c + (b + D) / 2, l = U.asin(((b - I) / e).toFixed(9));
          p = U.asin(((D - I) / e).toFixed(9));
          l = a < h ? Ea - l : l;
          p = v < h ? Ea - p : p;
          0 > l && (l = 2 * Ea + l);
          0 > p && (p = 2 * Ea + p);
          w && l > p && (l -= 2 * Ea);
          !w && p > l && (p -= 2 * Ea)
        }
        if (xa(p - l) > f) {
          var m = p, W = v, n = D;
          p = l + f * (w && p > l ? 1 : -1);
          v = h + c * Fa(p);
          D = I + e * Da(p);
          m = ub(v, D, c, e, r, 0, w, W, n, [p, m, h, I])
        }
        h = p - l;
        r = Fa(l);
        f = Da(l);
        w = Fa(p);
        p = Da(p);
        h = U.tan(h / 4);
        c = 4 / 3 * c * h;
        h *= 4 / 3 * e;
        e = [a,
          b];
        a = [a + c * f, b - h * r];
        b = [v + c * p, D - h * w];
        v = [v, D];
        a[0] = 2 * e[0] - a[0];
        a[1] = 2 * e[1] - a[1];
        if (d)return [a, b, v].concat(m);
        m = [a, b, v].concat(m).join().split(",");
        d = [];
        v = 0;
        for (D = m.length; v < D; v++)d[v] = v % 2 ? N(m[v - 1], m[v], g).y : N(m[v], m[v + 1], g).x;
        return d
      }, Pa = function (a, b, c, e, r, h, w, v, D) {
        var d = 1 - D;
        return {
          x: ya(d, 3) * a + 3 * ya(d, 2) * D * c + 3 * d * D * D * r + ya(D, 3) * w,
          y: ya(d, 3) * b + 3 * ya(d, 2) * D * e + 3 * d * D * D * h + ya(D, 3) * v
        }
      }, Cb = Oa(function (a, b, c, e, r, h, w, v) {
        var D = r - 2 * c + a - (w - 2 * r + c), d = 2 * (c - a) - 2 * (r - c), f = a - c, g = (-d + na(d * d - 4 * D * f)) / 2 / D, D = (-d - na(d * d - 4 * D * f)) /
          2 / D, p = [b, v], m = [a, w];
        "1e12" < xa(g) && (g = .5);
        "1e12" < xa(D) && (D = .5);
        0 < g && 1 > g && (g = Pa(a, b, c, e, r, h, w, v, g), m.push(g.x), p.push(g.y));
        0 < D && 1 > D && (g = Pa(a, b, c, e, r, h, w, v, D), m.push(g.x), p.push(g.y));
        D = h - 2 * e + b - (v - 2 * h + e);
        d = 2 * (e - b) - 2 * (h - e);
        f = b - e;
        g = (-d + na(d * d - 4 * D * f)) / 2 / D;
        D = (-d - na(d * d - 4 * D * f)) / 2 / D;
        "1e12" < xa(g) && (g = .5);
        "1e12" < xa(D) && (D = .5);
        0 < g && 1 > g && (g = Pa(a, b, c, e, r, h, w, v, g), m.push(g.x), p.push(g.y));
        0 < D && 1 > D && (g = Pa(a, b, c, e, r, h, w, v, D), m.push(g.x), p.push(g.y));
        return {
          min: {x: fa[Q](0, m), y: fa[Q](0, p)}, max: {
            x: da[Q](0, m), y: da[Q](0,
              p)
          }
        }
      }), mb = b._path2curve = Oa(function (a, b) {
        var c = !b && sb(a);
        if (!b && c.curve)return Yb(c.curve);
        var e = Kb(a), r = b && Kb(b), h = {x: 0, y: 0, bx: 0, by: 0, X: 0, Y: 0, qx: null, qy: null}, w = {
          x: 0,
          y: 0,
          bx: 0,
          by: 0,
          X: 0,
          Y: 0,
          qx: null,
          qy: null
        }, v = function (a, b) {
          var c, e;
          if (!a)return ["C", b.x, b.y, b.x, b.y, b.x, b.y];
          a[0]in{T: 1, Q: 1} || (b.qx = b.qy = null);
          switch (a[0]) {
            case "M":
              b.X = a[1];
              b.Y = a[2];
              break;
            case "A":
              a = ["C"].concat(ub[Q](0, [b.x, b.y].concat(a.slice(1))));
              break;
            case "S":
              c = b.x + (b.x - (b.bx || b.x));
              e = b.y + (b.y - (b.by || b.y));
              a = ["C", c, e].concat(a.slice(1));
              break;
            case "T":
              b.qx = b.x + (b.x - (b.qx || b.x));
              b.qy = b.y + (b.y - (b.qy || b.y));
              a = ["C"].concat(Vb(b.x, b.y, b.qx, b.qy, a[1], a[2]));
              break;
            case "Q":
              b.qx = a[1];
              b.qy = a[2];
              a = ["C"].concat(Vb(b.x, b.y, a[1], a[2], a[3], a[4]));
              break;
            case "L":
              a = ["C"].concat(Gb(b.x, b.y, a[1], a[2]));
              break;
            case "H":
              a = ["C"].concat(Gb(b.x, b.y, a[1], b.y));
              break;
            case "V":
              a = ["C"].concat(Gb(b.x, b.y, b.x, a[1]));
              break;
            case "Z":
              a = ["C"].concat(Gb(b.x, b.y, b.X, b.Y))
          }
          return a
        }, D = function (a, b) {
          if (7 < a[b].length) {
            a[b].shift();
            for (var c = a[b]; c.length;)a.splice(b++,
              0, ["C"].concat(c.splice(0, 6)));
            a.splice(b, 1);
            g = da(e.length, r && r.length || 0)
          }
        }, d = function (a, b, c, h, w) {
          a && b && "M" == a[w][0] && "M" != b[w][0] && (b.splice(w, 0, ["M", h.x, h.y]), c.bx = 0, c.by = 0, c.x = a[w][1], c.y = a[w][2], g = da(e.length, r && r.length || 0))
        }, f = 0, g = da(e.length, r && r.length || 0);
        for (; f < g; f++) {
          e[f] = v(e[f], h);
          D(e, f);
          r && (r[f] = v(r[f], w));
          r && D(r, f);
          d(e, r, h, w, f);
          d(r, e, w, h, f);
          var p = e[f], m = r && r[f], I = p.length, N = r && m.length;
          h.x = p[I - 2];
          h.y = p[I - 1];
          h.bx = J(p[I - 4]) || h.x;
          h.by = J(p[I - 3]) || h.y;
          w.bx = r && (J(m[N - 4]) || w.x);
          w.by = r && (J(m[N -
          3]) || w.y);
          w.x = r && m[N - 2];
          w.y = r && m[N - 1]
        }
        r || (c.curve = Yb(e));
        return r ? [e, r] : e
      }, null, Yb);
      b._parseDots = Oa(function (a) {
        for (var c = [], e = 0, r = a.length; e < r; e++) {
          var h = {}, w = a[e].match(/^([^:]*):?([\d\.]*)/);
          h.color = b.getRGB(w[1]);
          if (h.color.error)return null;
          h.opacity = h.color.opacity;
          h.color = h.color.hex;
          w[2] && (h.offset = w[2] + "%");
          c.push(h)
        }
        e = 1;
        for (r = c.length - 1; e < r; e++)if (!c[e].offset) {
          a = J(c[e - 1].offset || 0);
          w = 0;
          for (h = e + 1; h < r; h++)if (c[h].offset) {
            w = c[h].offset;
            break
          }
          w || (w = 100, h = r);
          w = J(w);
          for (w = (w - a) / (h - e + 1); e < h; e++)a +=
            w, c[e].offset = a + "%"
        }
        return c
      });
      var r = b._tear = function (a, b) {
        a == b.top && (b.top = a.prev);
        a == b.bottom && (b.bottom = a.next);
        a.next && (a.next.prev = a.prev);
        a.prev && (a.prev.next = a.next)
      };
      b._tofront = function (a, b) {
        if (b.top === a)return !1;
        r(a, b);
        a.next = null;
        a.prev = b.top;
        b.top.next = a;
        b.top = a;
        return !0
      };
      b._toback = function (a, b) {
        if (b.bottom === a)return !1;
        r(a, b);
        a.next = b.bottom;
        a.prev = null;
        b.bottom.prev = a;
        b.bottom = a;
        return !0
      };
      b._insertafter = function (a, b, c, e) {
        r(a, c);
        a.parent = e;
        b === e.top && (e.top = a);
        b.next && (b.next.prev = a);
        a.next = b.next;
        a.prev = b;
        b.next = a
      };
      b._insertbefore = function (a, b, c, e) {
        r(a, c);
        a.parent = e;
        b === e.bottom && (e.bottom = a);
        b.prev && (b.prev.next = a);
        a.prev = b.prev;
        b.prev = a;
        a.next = b
      };
      var w = b.toMatrix = function (a, b) {
        var c = Y(a), e = {
          _: {transform: ""}, getBBox: function () {
            return c
          }
        };
        v(e, b);
        return e.matrix
      };
      b.transformPath = function (a, b) {
        return ma(a, w(a, b))
      };
      var v = b._extractTransform = function (a, c) {
        if (null == c)return a._.transform;
        c = h(c).replace(/\.{3}|\u2026/g, a._.transform || "");
        var e = b.parseTransformString(c), r = 0, w = 0, v = 0,
          D = 1, d = 1, f = a._, v = new q;
        f.transform = e || [];
        if (e)for (var w = 0, g = e.length; w < g; w++) {
          var p = e[w], m = p.length, I = h(p[0]).toLowerCase(), N = p[0] != I, l = N ? v.invert() : 0, W;
          "t" == I && 3 == m ? N ? (m = l.x(0, 0), I = l.y(0, 0), N = l.x(p[1], p[2]), l = l.y(p[1], p[2]), v.translate(N - m, l - I)) : v.translate(p[1], p[2]) : "r" == I ? 2 == m ? (W = W || a.getBBox(1), v.rotate(p[1], W.x + W.width / 2, W.y + W.height / 2), r += p[1]) : 4 == m && (N ? (N = l.x(p[2], p[3]), l = l.y(p[2], p[3]), v.rotate(p[1], N, l)) : v.rotate(p[1], p[2], p[3]), r += p[1]) : "s" == I ? 2 == m || 3 == m ? (W = W || a.getBBox(1), v.scale(p[1],
            p[m - 1], W.x + W.width / 2, W.y + W.height / 2), D *= p[1], d *= p[m - 1]) : 5 == m && (N ? (N = l.x(p[3], p[4]), l = l.y(p[3], p[4]), v.scale(p[1], p[2], N, l)) : v.scale(p[1], p[2], p[3], p[4]), D *= p[1], d *= p[2]) : "m" == I && 7 == m && v.add(p[1], p[2], p[3], p[4], p[5], p[6]);
          f.dirtyT = 1;
          a.matrix = v
        }
        a.matrix = v;
        f.sx = D;
        f.sy = d;
        f.deg = r;
        f.dx = w = v.e;
        f.dy = v = v.f;
        1 == D && 1 == d && !r && f.bbox ? (f.bbox.x += +w, f.bbox.y += +v) : f.dirtyT = 1
      }, D = function (a) {
        var b = a[0];
        switch (b.toLowerCase()) {
          case "t":
            return [b, 0, 0];
          case "m":
            return [b, 1, 0, 0, 1, 0, 0];
          case "r":
            return 4 == a.length ? [b, 0, a[2],
              a[3]] : [b, 0];
          case "s":
            return 5 == a.length ? [b, 1, 1, a[3], a[4]] : 3 == a.length ? [b, 1, 1] : [b, 1]
        }
      }, e = b._equaliseTransform = function (a, c) {
        c = h(c).replace(/\.{3}|\u2026/g, a);
        a = b.parseTransformString(a) || [];
        c = b.parseTransformString(c) || [];
        for (var e = da(a.length, c.length), r = [], w = [], v = 0, d, f, g, p; v < e; v++) {
          g = a[v] || D(c[v]);
          p = c[v] || D(g);
          if (g[0] != p[0] || "r" == g[0].toLowerCase() && (g[2] != p[2] || g[3] != p[3]) || "s" == g[0].toLowerCase() && (g[3] != p[3] || g[4] != p[4]))return;
          r[v] = [];
          w[v] = [];
          d = 0;
          for (f = da(g.length, p.length); d < f; d++)d in g && (r[v][d] =
            g[d]), d in p && (w[v][d] = p[d])
        }
        return {from: r, to: w}
      };
      b._getContainer = function (a, c, e, r) {
        var h;
        h = null != r || b.is(a, "object") ? a : u.doc.getElementById(a);
        if (null != h)return h.tagName ? null == c ? {
          container: h,
          width: h.style.pixelWidth || h.offsetWidth,
          height: h.style.pixelHeight || h.offsetHeight
        } : {container: h, width: c, height: e} : {container: 1, x: a, y: c, width: e, height: r}
      };
      b.pathToRelative = oc;
      b._engine = {};
      b.path2curve = mb;
      b.matrix = function (a, b, c, e, r, h) {
        return new q(a, b, c, e, r, h)
      };
      (function (a) {
        function c(a) {
          return a[0] * a[0] + a[1] *
            a[1]
        }

        function e(a) {
          var b = na(c(a));
          a[0] && (a[0] /= b);
          a[1] && (a[1] /= b)
        }

        a.add = function (a, b, c, e, r, h) {
          var w = [[], [], []], v = [[this.a, this.c, this.e], [this.b, this.d, this.f], [0, 0, 1]];
          b = [[a, c, r], [b, e, h], [0, 0, 1]];
          a && a instanceof q && (b = [[a.a, a.c, a.e], [a.b, a.d, a.f], [0, 0, 1]]);
          for (a = 0; 3 > a; a++)for (c = 0; 3 > c; c++) {
            for (e = r = 0; 3 > e; e++)r += v[a][e] * b[e][c];
            w[a][c] = r
          }
          this.a = w[0][0];
          this.b = w[1][0];
          this.c = w[0][1];
          this.d = w[1][1];
          this.e = w[0][2];
          this.f = w[1][2]
        };
        a.invert = function () {
          var a = this.a * this.d - this.b * this.c;
          return new q(this.d /
          a, -this.b / a, -this.c / a, this.a / a, (this.c * this.f - this.d * this.e) / a, (this.b * this.e - this.a * this.f) / a)
        };
        a.clone = function () {
          return new q(this.a, this.b, this.c, this.d, this.e, this.f)
        };
        a.translate = function (a, b) {
          this.add(1, 0, 0, 1, a, b)
        };
        a.scale = function (a, b, c, e) {
          null == b && (b = a);
          (c || e) && this.add(1, 0, 0, 1, c, e);
          this.add(a, 0, 0, b, 0, 0);
          (c || e) && this.add(1, 0, 0, 1, -c, -e)
        };
        a.rotate = function (a, c, e) {
          a = b.rad(a);
          c = c || 0;
          e = e || 0;
          var r = +Fa(a).toFixed(9);
          a = +Da(a).toFixed(9);
          this.add(r, a, -a, r, c, e);
          this.add(1, 0, 0, 1, -c, -e)
        };
        a.x = function (a,
                        b) {
          return a * this.a + b * this.c + this.e
        };
        a.y = function (a, b) {
          return a * this.b + b * this.d + this.f
        };
        a.get = function (a) {
          return +this[h.fromCharCode(97 + a)].toFixed(4)
        };
        a.toString = function () {
          return b.svg ? "matrix(" + [this.get(0), this.get(1), this.get(2), this.get(3), this.get(4), this.get(5)].join() + ")" : [this.get(0), this.get(2), this.get(1), this.get(3), 0, 0].join()
        };
        a.toMatrixString = function () {
          return "matrix(" + [this.get(0), this.get(1), this.get(2), this.get(3), this.get(4), this.get(5)].join() + ")"
        };
        a.toFilter = function () {
          return "progid:DXImageTransform.Microsoft.Matrix(M11=" +
            this.get(0) + ", M12=" + this.get(2) + ", M21=" + this.get(1) + ", M22=" + this.get(3) + ", Dx=" + this.get(4) + ", Dy=" + this.get(5) + ", sizingmethod='auto expand')"
        };
        a.offset = function () {
          return [this.e.toFixed(4), this.f.toFixed(4)]
        };
        a.split = function () {
          var a = {};
          a.dx = this.e;
          a.dy = this.f;
          var r = [[this.a, this.c], [this.b, this.d]];
          a.scalex = na(c(r[0]));
          e(r[0]);
          a.shear = r[0][0] * r[1][0] + r[0][1] * r[1][1];
          r[1] = [r[1][0] - r[0][0] * a.shear, r[1][1] - r[0][1] * a.shear];
          a.scaley = na(c(r[1]));
          e(r[1]);
          a.shear /= a.scaley;
          var h = -r[0][1], r = r[1][1];
          0 > r ? (a.rotate = b.deg(U.acos(r)), 0 > h && (a.rotate = 360 - a.rotate)) : a.rotate = b.deg(U.asin(h));
          a.isSimple = !+a.shear.toFixed(9) && (a.scalex.toFixed(9) == a.scaley.toFixed(9) || !a.rotate);
          a.isSuperSimple = !+a.shear.toFixed(9) && a.scalex.toFixed(9) == a.scaley.toFixed(9) && !a.rotate;
          a.noRotation = !+a.shear.toFixed(9) && !a.rotate;
          return a
        };
        a.toTransformString = function (a) {
          a = a || this.split();
          return a.isSimple ? (a.scalex = +a.scalex.toFixed(4), a.scaley = +a.scaley.toFixed(4), a.rotate = +a.rotate.toFixed(4), (a.dx || a.dy ? "t" + [a.dx, a.dy] :
            "") + (1 != a.scalex || 1 != a.scaley ? "s" + [a.scalex, a.scaley, 0, 0] : "") + (a.rotate ? "r" + [a.rotate, 0, 0] : "")) : "m" + [this.get(0), this.get(1), this.get(2), this.get(3), this.get(4), this.get(5)]
        }
      })(q.prototype);
      var ja = navigator.userAgent.match(/Version\/(.*?)\s/) || navigator.userAgent.match(/Chrome\/(\d+)/);
      "Apple Computer, Inc." == navigator.vendor && (ja && 4 > ja[1] || "iP" == navigator.platform.slice(0, 2)) || "Google Inc." == navigator.vendor && ja && 8 > ja[1] ? aa.safari = function () {
        var a = this.rect(-99, -99, this.width + 99, this.height + 99).attr({stroke: "none"});
        setTimeout(function () {
          a.remove()
        });
        return !0
      } : aa.safari = H;
      for (var N = function () {
        this.returnValue = !1
      }, W = function () {
        return this.originalEvent.preventDefault()
      }, Dc = function () {
        this.cancelBubble = !0
      }, Wa = function () {
        return this.originalEvent.stopPropagation()
      }, Sa = b.addEvent = function () {
        if (u.doc.addEventListener)return function (a, b, c, e) {
          var r = Aa && R[b] || b, h;
          R[T[b]] && (r = R[T[b]]);
          h = function (r) {
            var h = u.doc.documentElement.scrollTop || u.doc.body.scrollTop, w = u.doc.documentElement.scrollLeft || u.doc.body.scrollLeft, v;
            if (za && R.hasOwnProperty(Aa ? b : T[b]))for (var D = 0, d = r.targetTouches && r.targetTouches.length; D < d; D++)if (v = r.targetTouches[D].target, v == a || "tspan" == v.nodeName && v.parentNode == a) {
              v = r;
              r = r.targetTouches[D];
              r.originalEvent = v;
              r.preventDefault = W;
              r.stopPropagation = Wa;
              break
            }
            return c.call(e, r, r.clientX + w, r.clientY + h)
          };
          a.addEventListener(r, h, !1);
          return function () {
            a.removeEventListener(r, h, !1);
            return !0
          }
        };
        if (u.doc.attachEvent)return function (a, b, c, e) {
          var r = function (a) {
            a = a || u.win.event;
            var b = a.clientX + (u.doc.documentElement.scrollLeft ||
              u.doc.body.scrollLeft), r = a.clientY + (u.doc.documentElement.scrollTop || u.doc.body.scrollTop);
            a.preventDefault = a.preventDefault || N;
            a.stopPropagation = a.stopPropagation || Dc;
            return c.call(e, a, b, r)
          };
          a.attachEvent("on" + b, r);
          return function () {
            a.detachEvent("on" + b, r);
            return !0
          }
        }
      }(), sa = [], ic = function (a) {
        for (var c = a.clientX, e = a.clientY, r = u.doc.documentElement.scrollTop || u.doc.body.scrollTop, h = u.doc.documentElement.scrollLeft || u.doc.body.scrollLeft, w, v = sa.length; v--;) {
          w = sa[v];
          if (za && "touchmove" === a.type)for (var D =
            a.touches.length, d; D--;) {
            if (d = a.touches[D], d.identifier == w.el._drag.id) {
              c = d.clientX;
              e = d.clientY;
              (a.originalEvent ? a.originalEvent : a).preventDefault();
              break
            }
          } else a.preventDefault();
          if (!w.el.removed) {
            var D = b._engine.getNode(w.el), f = D.nextSibling, g = D.parentNode, p = D.style.display;
            u.win.opera && g.removeChild(D);
            D.style.display = "none";
            d = w.el.paper.getElementByPoint(c, e);
            D.style.display = p;
            u.win.opera && (f ? g.insertBefore(D, f) : g.appendChild(D));
            d && k("raphael.drag.over." + w.el.id, w.el, d);
            c += h;
            e += r;
            k("raphael.drag.move." +
            w.el.id, w.move_scope || w.el, c - w.el._drag.x, e - w.el._drag.y, c, e, a)
          }
        }
      }, F = function (a) {
        b.unmousemove(ic).unmouseup(F);
        for (var c = sa.length, e; c--;)e = sa[c], e.el._drag = {}, k("raphael.drag.end." + e.el.id, e.end_scope || e.start_scope || e.move_scope || e.el, a);
        sa = []
      }, M = b.el = {}, nd = ga.length; nd--;)(function (a) {
        b[a] = M[a] = function (c, e) {
          b.is(c, "function") && (this.events = this.events || [], this.events.push({
            name: a,
            f: c,
            unbind: Sa(this.shape || this.node || u.doc, a, c, e || this)
          }));
          return this
        };
        b["un" + a] = M["un" + a] = function (b) {
          for (var c =
            this.events || [], e = c.length; e--;)if (c[e].name == a && c[e].f == b) {
            c[e].unbind();
            c.splice(e, 1);
            !c.length && delete this.events;
            break
          }
          return this
        }
      })(ga[nd]);
      M.data = function (a, c) {
        var e = xb[this.id] = xb[this.id] || {};
        if (1 == arguments.length) {
          if (b.is(a, "object")) {
            for (var r in a)a.hasOwnProperty(r) && this.data(r, a[r]);
            return this
          }
          k("raphael.data.get." + this.id, this, e[a], a);
          return e[a]
        }
        e[a] = c;
        k("raphael.data.set." + this.id, this, c, a);
        return this
      };
      M.removeData = function (a) {
        null == a ? delete xb[this.id] : xb[this.id] && delete xb[this.id][a];
        return this
      };
      M.getData = function () {
        return V(xb[this.id] || {})
      };
      var $a = [], sd = function () {
        this.untrack = Sa(u.doc, "mouseup", rd, this)
      }, rd = function () {
        this.untrack();
        this.untrack = null;
        return this.fn && this.fn.apply(this.scope || this.el, arguments)
      };
      M.mouseup = function (a, c, e) {
        if (!e)return b.mouseup.apply(this, arguments);
        $a.push(e = {el: this, fn: a, scope: c});
        e.unbind = Sa(this.shape || this.node || u.doc, "mousedown", sd, e);
        return this
      };
      M.unmouseup = function (a) {
        for (var c = $a.length, e; c--;)$a[c].el === this && $a[c].fn === a && (e = $a[c],
          e.unbind(), e.untrack && e.untrack(), $a.splice(c, 1));
        return e ? this : b.unmouseup.apply(this, arguments)
      };
      M.hover = function (a, b, c, e) {
        return this.mouseover(a, c).mouseout(b, e || c)
      };
      M.unhover = function (a, b) {
        return this.unmouseover(a).unmouseout(b)
      };
      var Jc = [];
      M.drag = function (a, c, e, r, h, w) {
        function v(D) {
          (D.originalEvent || D).preventDefault();
          var d = u.doc.documentElement.scrollTop || u.doc.body.scrollTop, f = u.doc.documentElement.scrollLeft || u.doc.body.scrollLeft;
          this._drag.x = D.clientX + f;
          this._drag.y = D.clientY + d;
          this._drag.id =
            D.identifier;
          !sa.length && b.mousemove(ic).mouseup(F);
          za && !Aa && !sa.length && b.dragmove(ic).dragend(F);
          sa.push({el: this, move_scope: r, start_scope: h, end_scope: w});
          c && k.on("raphael.drag.start." + this.id, c);
          a && k.on("raphael.drag.move." + this.id, a);
          e && k.on("raphael.drag.end." + this.id, e);
          k("raphael.drag.start." + this.id, h || r || this, D.clientX + f, D.clientY + d, D)
        }

        this._drag = {};
        Jc.push({el: this, start: v});
        this.mousedown(v);
        za && !Aa && this.dragstart(v);
        return this
      };
      M.onDragOver = function (a) {
        a ? k.on("raphael.drag.over." + this.id,
          a) : k.unbind("raphael.drag.over." + this.id)
      };
      M.undrag = function () {
        for (var a = Jc.length; a--;)Jc[a].el == this && (this.unmousedown(Jc[a].start), Jc.splice(a, 1), k.unbind("raphael.drag.*." + this.id));
        !Jc.length && b.unmousemove(ic).unmouseup(F);
        delete this._drag
      };
      M.follow = function (a, c, e) {
        if (a.removed || a.constructor !== b.el.constructor)return this;
        a.followers.push({el: this, stalk: e = {before: "insertBefore", after: "insertAfter"}[e], cb: c});
        e && this[e](a);
        return this
      };
      M.unfollow = function (a) {
        if (a.removed || a.constructor !==
          b.el.constructor)return this;
        for (var c = 0, e = a.followers.length; c < e; c++)if (a.followers[c].el === this) {
          a.followers.splice(c, 1);
          break
        }
        return this
      };
      aa.hide = function () {
        this.canvas.style.visibility = "hidden";
        return this
      };
      aa.show = function () {
        this.canvas.style.visibility = "";
        return this
      };
      aa.group = function () {
        var a = arguments, c = oa(a, !0), a = b._engine.group(this, a[0], c);
        return this.__set__ && this.__set__.push(a), this._elementsById[a.id] = a
      };
      aa.circle = function () {
        var a = arguments, c = oa(a, !0), a = la(a, "cx", 0, "cy", 0, "r", 0, "fill",
          "none", "stroke", "#000"), c = b._engine.circle(this, a, c);
        return this.__set__ && this.__set__.push(c), this._elementsById[c.id] = c
      };
      aa.rect = function () {
        var a = arguments, c = oa(a, !0), a = la(a, "x", 0, "y", 0, "width", 0, "height", 0, "r", 0, "fill", "none", "stroke", "#000"), c = b._engine.rect(this, a, c);
        return this.__set__ && this.__set__.push(c), this._elementsById[c.id] = c
      };
      aa.ellipse = function () {
        var a = arguments, c = oa(a, !0), a = la(a, "x", 0, "y", 0, "rx", 0, "ry", 0, "fill", "none", "stroke", "#000"), c = b._engine.ellipse(this, a, c);
        return this.__set__ &&
        this.__set__.push(c), this._elementsById[c.id] = c
      };
      aa.path = function () {
        var a = arguments, c = oa(a, !0), a = la(a, "path", "", "fill", "none", "stroke", "#000"), c = b._engine.path(this, a, c);
        return this.__set__ && this.__set__.push(c), this._elementsById[c.id] = c
      };
      aa.image = function () {
        var a = arguments, c = oa(a, !0), a = la(a, "src", "about:blank", "x", 0, "y", 0, "width", 0, "height", 0);
        out = b._engine.image(this, a, c);
        return this.__set__ && this.__set__.push(out), this._elementsById[out.id] = out
      };
      aa.text = function () {
        var a = arguments, c = oa(a, !0), a =
          la(a, "x", 0, "y", 0, "text", "", "stroke", "none", "fill", "#000", "text-anchor", "middle", "vertical-align", "middle"), c = b._engine.text(this, a, c);
        return this.__set__ && this.__set__.push(c), this._elementsById[c.id] = c
      };
      aa.set = function (a) {
        !b.is(a, "array") && (a = z.call(arguments, 0, arguments.length));
        var c = new Kc(a);
        this.__set__ && this.__set__.push(c);
        return c
      };
      aa.setStart = function (a) {
        this.__set__ = a || this.set()
      };
      aa.setFinish = function (a) {
        a = this.__set__;
        delete this.__set__;
        return a
      };
      aa.setSize = function (a, c) {
        return b._engine.setSize.call(this,
          a, c)
      };
      aa.setViewBox = function (a, c, e, r, h) {
        return b._engine.setViewBox.call(this, a, c, e, r, h)
      };
      aa.top = aa.bottom = null;
      aa.raphael = b;
      aa.getElementByPoint = function (a, b) {
        var c, e, r = this.canvas, h = u.doc.elementFromPoint(a, b);
        if (u.win.opera && "svg" == h.tagName) {
          e = r.getBoundingClientRect();
          c = r.ownerDocument;
          var w = c.body, v = c.documentElement;
          c = e.top + (u.win.pageYOffset || v.scrollTop || w.scrollTop) - (v.clientTop || w.clientTop || 0);
          e = e.left + (u.win.pageXOffset || v.scrollLeft || w.scrollLeft) - (v.clientLeft || w.clientLeft || 0);
          w = r.createSVGRect();
          w.x = a - e;
          w.y = b - c;
          w.width = w.height = 1;
          c = r.getIntersectionList(w, null);
          c.length && (h = c[c.length - 1])
        }
        if (!h)return null;
        for (; h.parentNode && h != r.parentNode && !h.raphael;)h = h.parentNode;
        h == this.canvas.parentNode && (h = r);
        return h = h && h.raphael ? this.getById(h.raphaelid) : null
      };
      aa.getElementsByBBox = function (a) {
        var c = this.set();
        this.forEach(function (e) {
          b.isBBoxIntersect(e.getBBox(), a) && c.push(e)
        });
        return c
      };
      aa.getById = function (a) {
        return this._elementsById[a] || null
      };
      aa.forEach = function (a, b) {
        for (var c = this.bottom; c &&
        !1 !== a.call(b, c);)c = c.next;
        return this
      };
      aa.getElementsByPoint = function (a, b) {
        var c = this.set();
        this.forEach(function (e) {
          e.isPointInside(a, b) && c.push(e)
        });
        return c
      };
      M.isPointInside = function (a, c) {
        var e = this.realPath = this.realPath || ea[this.type](this), r;
        return b.isPointInsidePath((r = this.attr("transform")) && r.length && b.transformPath(e, r) || e, a, c)
      };
      M.getBBox = function (a) {
        if (this.removed)return {};
        var b = this._;
        if (a) {
          if (b.dirty || !b.bboxwt)this.realPath = ea[this.type](this), b.bboxwt = Y(this.realPath), b.bboxwt.toString =
            S, b.dirty = 0;
          return b.bboxwt
        }
        if (b.dirty || b.dirtyT || !b.bbox) {
          if (b.dirty || !this.realPath)b.bboxwt = 0, this.realPath = ea[this.type](this);
          b.bbox = Y(ma(this.realPath, this.matrix));
          b.bbox.toString = S;
          b.dirty = b.dirtyT = 0
        }
        return b.bbox
      };
      M.clone = function () {
        if (this.removed)return null;
        var a = this.paper[this.type]().attr(this.attr());
        this.__set__ && this.__set__.push(a);
        return a
      };
      M.glow = function (a) {
        if ("text" == this.type)return null;
        a = a || {};
        var b = (a.width || 10) + (+this.attr("stroke-width") || 1), c = a.fill || !1, e = a.opacity ||
          .5, r = a.offsetx || 0, h = a.offsety || 0;
        a = a.color || "#000";
        for (var w = b / 2, v = this.paper, D = v.set(), d = this.realPath || ea[this.type](this), d = this.matrix ? ma(d, this.matrix) : d, f = 1; f < w + 1; f++)D.push(v.path(d).attr({
          stroke: a,
          fill: c ? a : "none",
          "stroke-linejoin": "round",
          "stroke-linecap": "round",
          "stroke-width": +(b / w * f).toFixed(3),
          opacity: +(e / w).toFixed(3)
        }));
        return D.insertBefore(this).translate(r, h)
      };
      var cd = function (a, e, r, h, w, v, D, d, f) {
        return null == f ? c(a, e, r, h, w, v, D, d) : b.findDotsAtSegment(a, e, r, h, w, v, D, d, l(a, e, r, h, w, v, D,
          d, f))
      }, dd = function (a, c) {
        return function (e, r, h) {
          e = mb(e);
          for (var w, v, D, d, f = "", g = {}, p = 0, m = 0, I = e.length; m < I; m++) {
            D = e[m];
            if ("M" == D[0])w = +D[1], v = +D[2]; else {
              d = cd(w, v, D[1], D[2], D[3], D[4], D[5], D[6]);
              if (p + d > r) {
                if (c && !g.start) {
                  w = cd(w, v, D[1], D[2], D[3], D[4], D[5], D[6], r - p);
                  f += ["C" + w.start.x, w.start.y, w.m.x, w.m.y, w.x, w.y];
                  if (h)return f;
                  g.start = f;
                  f = ["M" + w.x, w.y + "C" + w.n.x, w.n.y, w.end.x, w.end.y, D[5], D[6]].join();
                  p += d;
                  w = +D[5];
                  v = +D[6];
                  continue
                }
                if (!a && !c)return w = cd(w, v, D[1], D[2], D[3], D[4], D[5], D[6], r - p), {
                  x: w.x, y: w.y,
                  alpha: w.alpha
                }
              }
              p += d;
              w = +D[5];
              v = +D[6]
            }
            f += D.shift() + D
          }
          g.end = f;
          w = a ? p : c ? g : b.findDotsAtSegment(w, v, D[0], D[1], D[2], D[3], D[4], D[5], 1);
          w.alpha && (w = {x: w.x, y: w.y, alpha: w.alpha});
          return w
        }
      }, od = dd(1), pd = dd(), ed = dd(0, 1);
      b.getTotalLength = od;
      b.getPointAtLength = pd;
      b.getSubpath = function (a, b, c) {
        if (1E-6 > this.getTotalLength(a) - c)return ed(a, b).end;
        a = ed(a, c, 1);
        return b ? ed(a, b).end : a
      };
      M.getTotalLength = function () {
        if ("path" == this.type)return this.node.getTotalLength ? this.node.getTotalLength() : od(this.attrs.path)
      };
      M.getPointAtLength =
        function (a) {
          if ("path" == this.type)return pd(this.attrs.path, a)
        };
      M.getSubpath = function (a, c) {
        if ("path" == this.type)return b.getSubpath(this.attrs.path, a, c)
      };
      var Wb = b.easing_formulas = {
        linear: function (a) {
          return a
        }, "<": function (a) {
          return ya(a, 1.7)
        }, ">": function (a) {
          return ya(a, .48)
        }, "<>": function (a) {
          var b = .48 - a / 1.04, c = na(.1734 + b * b);
          a = c - b;
          a = ya(xa(a), 1 / 3) * (0 > a ? -1 : 1);
          b = -c - b;
          b = ya(xa(b), 1 / 3) * (0 > b ? -1 : 1);
          a = a + b + .5;
          return 3 * (1 - a) * a * a + a * a * a
        }, backIn: function (a) {
          return a * a * (2.70158 * a - 1.70158)
        }, backOut: function (a) {
          --a;
          return a * a * (2.70158 * a + 1.70158) + 1
        }, elastic: function (a) {
          return a == !!a ? a : ya(2, -10 * a) * Da(2 * (a - .075) * Ea / .3) + 1
        }, bounce: function (a) {
          a < 1 / 2.75 ? a *= 7.5625 * a : a < 2 / 2.75 ? (a -= 1.5 / 2.75, a = 7.5625 * a * a + .75) : a < 2.5 / 2.75 ? (a -= 2.25 / 2.75, a = 7.5625 * a * a + .9375) : (a -= 2.625 / 2.75, a = 7.5625 * a * a + .984375);
          return a
        }
      };
      Wb.easeIn = Wb["ease-in"] = Wb["<"];
      Wb.easeOut = Wb["ease-out"] = Wb[">"];
      Wb.easeInOut = Wb["ease-in-out"] = Wb["<>"];
      Wb["back-in"] = Wb.backIn;
      Wb["back-out"] = Wb.backOut;
      var Ra = [], md = d.requestAnimationFrame || d.webkitRequestAnimationFrame ||
        d.mozRequestAnimationFrame || d.oRequestAnimationFrame || d.msRequestAnimationFrame || function (a) {
          setTimeout(a, 16)
        }, bd = function () {
        for (var a = +new Date, c = 0; c < Ra.length; c++) {
          var e = Ra[c];
          if (!e.el.removed && !e.paused) {
            var r = a - e.start, h = e.ms, w = e.easing, v = e.from, D = e.diff, d = e.to, f = e.el, p = {}, m, I = {}, N;
            e.initstatus ? (r = (e.initstatus * e.anim.top - e.prev) / (e.percent - e.prev) * h, e.status = e.initstatus, delete e.initstatus, e.stop && Ra.splice(c--, 1)) : e.status = (e.prev + r / h * (e.percent - e.prev)) / e.anim.top;
            if (!(0 > r))if (r < h) {
              var l =
                w(r / h), W;
              for (W in v)if (v.hasOwnProperty(W)) {
                switch (lb[W]) {
                  case A:
                    m = +v[W] + l * h * D[W];
                    break;
                  case "colour":
                    m = "rgb(" + [fd(ua(v[W].r + l * h * D[W].r)), fd(ua(v[W].g + l * h * D[W].g)), fd(ua(v[W].b + l * h * D[W].b))].join() + ")";
                    break;
                  case "path":
                    m = [];
                    r = 0;
                    for (w = v[W].length; r < w; r++) {
                      m[r] = [v[W][r][0]];
                      d = 1;
                      for (I = v[W][r].length; d < I; d++)m[r][d] = (+v[W][r][d] + l * h * D[W][r][d]).toFixed(4);
                      m[r] = m[r].join(" ")
                    }
                    m = m.join(" ");
                    break;
                  case "transform":
                    if (D[W].real)for (m = [], r = 0, w = v[W].length; r < w; r++)for (m[r] = [v[W][r][0]], d = 1, I = v[W][r].length; d <
                    I; d++)m[r][d] = v[W][r][d] + l * h * D[W][r][d]; else m = function (a) {
                      return +v[W][a] + l * h * D[W][a]
                    }, m = [["m", m(0), m(1), m(2), m(3), m(4), m(5)]];
                    break;
                  case "csv":
                    if ("clip-rect" == W)for (m = [], r = 4; r--;)m[r] = +v[W][r] + l * h * D[W][r];
                    break;
                  default:
                    for (w = [].concat(v[W]), m = [], r = f.ca[W].length; r--;)m[r] = +w[r] + l * h * D[W][r]
                }
                p[W] = m
              }
              f.attr(p);
              (function (a, b, c) {
                setTimeout(function () {
                  k("raphael.anim.frame." + a, b, c)
                })
              })(f.id, f, e.anim)
            } else {
              (function (a, c, e) {
                setTimeout(function () {
                  k("raphael.anim.frame." + c.id, c, e);
                  k("raphael.anim.finish." +
                  c.id, c, e);
                  b.is(a, "function") && a.call(c)
                })
              })(e.callback, f, e.anim);
              f.attr(d);
              Ra.splice(c--, 1);
              if (1 < e.repeat && !e.next) {
                for (N in d)d.hasOwnProperty(N) && (I[N] = e.totalOrigin[N]);
                e.el.attr(I);
                g(e.anim, e.el, e.anim.percents[0], null, e.totalOrigin, e.repeat - 1)
              }
              e.next && !e.stop && g(e.anim, e.el, e.next, null, e.totalOrigin, e.repeat)
            }
          }
        }
        b.svg && f && f.paper && f.paper.safari();
        Ra.length && md(bd)
      }, fd = function (a) {
        return 255 < a ? 255 : 0 > a ? 0 : a
      };
      M.animateWith = function (a, c, e, r, h, w) {
        if (this.removed)return w && w.call(this), this;
        e = e instanceof
        f ? e : b.animation(e, r, h, w);
        g(e, this, e.percents[0], null, this.attr());
        e = 0;
        for (r = Ra.length; e < r; e++)if (Ra[e].anim == c && Ra[e].el == a) {
          Ra[r - 1].start = Ra[e].start;
          break
        }
        return this
      };
      M.onAnimation = function (a) {
        a ? k.on("raphael.anim.frame." + this.id, a) : k.unbind("raphael.anim.frame." + this.id);
        return this
      };
      f.prototype.delay = function (a) {
        var b = new f(this.anim, this.ms);
        b.times = this.times;
        b.del = +a || 0;
        return b
      };
      f.prototype.repeat = function (a) {
        var b = new f(this.anim, this.ms);
        b.del = this.del;
        b.times = U.floor(da(a, 0)) || 1;
        return b
      };
      b.animation = function (a, c, e, r) {
        if (a instanceof f)return a;
        if (b.is(e, "function") || !e)r = r || e || null, e = null;
        a = Object(a);
        c = +c || 0;
        var h = {}, w, v;
        for (v in a)a.hasOwnProperty(v) && J(v) != v && J(v) + "%" != v && (w = !0, h[v] = a[v]);
        return w ? (e && (h.easing = e), r && (h.callback = r), new f({100: h}, c)) : new f(a, c)
      };
      M.animate = function (a, c, e, r) {
        if (this.removed)return r && r.call(this), this;
        a = a instanceof f ? a : b.animation(a, c, e, r);
        g(a, this, a.percents[0], null, this.attr());
        return this
      };
      M.setTime = function (a, b) {
        a && null != b && this.status(a, fa(b,
          a.ms) / a.ms);
        return this
      };
      M.status = function (a, b) {
        var c = [], e = 0, r, h;
        if (null != b)return g(a, this, -1, fa(b, 1)), this;
        for (r = Ra.length; e < r; e++)if (h = Ra[e], h.el.id == this.id && (!a || h.anim == a)) {
          if (a)return h.status;
          c.push({anim: h.anim, status: h.status})
        }
        return a ? 0 : c
      };
      M.pause = function (a) {
        for (var b = 0; b < Ra.length; b++)Ra[b].el.id != this.id || a && Ra[b].anim != a || !1 === k("raphael.anim.pause." + this.id, this, Ra[b].anim) || (Ra[b].paused = !0);
        return this
      };
      M.resume = function (a) {
        for (var b = 0; b < Ra.length; b++)if (Ra[b].el.id == this.id && (!a ||
          Ra[b].anim == a)) {
          var c = Ra[b];
          !1 !== k("raphael.anim.resume." + this.id, this, c.anim) && (delete c.paused, this.status(c.anim, c.status))
        }
        return this
      };
      M.stop = function (a) {
        for (var b = 0; b < Ra.length; b++)Ra[b].el.id != this.id || a && Ra[b].anim != a || !1 !== k("raphael.anim.stop." + this.id, this, Ra[b].anim) && Ra.splice(b--, 1);
        return this
      };
      k.on("raphael.remove", s);
      k.on("raphael.clear", s);
      M.toString = function () {
        return "Raphaël’s object"
      };
      M.toFront = function () {
        if (this.removed)return this;
        var a = b._engine.getNode(this), c = this.parent,
          e = this.followers, r;
        b._tofront(this, c) && c.canvas.appendChild(a);
        a = 0;
        for (c = e.length; a < c; a++)(r = e[a]).stalk && r.el[r.stalk](this);
        return this
      };
      M.toBack = function () {
        if (this.removed)return this;
        var a = b._engine.getNode(this), c = this.parent, e = this.followers, r;
        b._toback(this, c) && c.canvas.insertBefore(a, c.canvas.firstChild);
        a = 0;
        for (c = e.length; a < c; a++)(r = e[a]).stalk && r.el[r.stalk](this);
        return this
      };
      M.insertAfter = function (a) {
        if (this.removed)return this;
        var c = b._engine.getNode(this), e = b._engine.getLastNode(a),
          r = a.parent.canvas, h = this.followers, w;
        e.nextSibling ? r.insertBefore(c, e.nextSibling) : r.appendChild(c);
        b._insertafter(this, a, this.parent, a.parent);
        c = 0;
        for (e = h.length; c < e; c++)(w = h[c]).stalk && w.el[w.stalk](a);
        return this
      };
      M.insertBefore = function (a) {
        if (this.removed)return this;
        var c = b._engine.getNode(this), e = b._engine.getNode(a), r = this.followers, h;
        a.parent.canvas.insertBefore(c, e);
        b._insertbefore(this, a, this.parent, a.parent);
        this.parent = a.parent;
        c = 0;
        for (e = r.length; c < e; c++)(h = r[c]).stalk && h.el[h.stalk](a);
        return this
      };
      M.appendChild = function (a) {
        if (this.removed || "group" !== this.type)return this;
        var c = this.followers, e, r, h;
        if (a.parent === this)return a.toFront(), this;
        r = b._engine.getNode(a);
        b._tear(a, a.parent);
        this.canvas.appendChild(r);
        a.parent = this;
        !this.bottom && (this.bottom = a);
        a.prev = this.top;
        a.next = null;
        this.top && (this.top.next = a);
        this.top = a;
        r = 0;
        for (h = c.length; r < h; r++)(e = c[r]).stalk && e.el[e.stalk](a);
        return this
      };
      M.removeChild = function (a) {
        if (this.removed || "group" !== this.type || a.parent !== this)return this;
        var c = b._engine.getNode(a), e = this.paper;
        b._tear(a, this);
        e.canvas.appendChild(c);
        this.parent = e;
        !e.bottom && (e.bottom = this);
        (this.prev = e.top) && (e.top.next = this);
        e.top = this;
        this.next = null;
        return this
      };
      var Kc = function (a) {
        this.items = [];
        this.length = 0;
        this.type = "set";
        if (a)for (var b = 0, c = a.length; b < c; b++)!a[b] || a[b].constructor != M.constructor && a[b].constructor != Kc || (this[this.items.length] = this.items[this.items.length] = a[b], this.length++)
      }, Qa = Kc.prototype;
      Qa.push = function () {
        for (var a, b, c = 0, e = arguments.length; c <
        e; c++)!(a = arguments[c]) || a.constructor != M.constructor && a.constructor != Kc || (b = this.items.length, this[b] = this.items[b] = a, this.length++);
        return this
      };
      Qa.pop = function () {
        this.length && delete this[this.length--];
        return this.items.pop()
      };
      Qa.forEach = function (a, b) {
        for (var c = 0, e = this.items.length; c < e && !1 !== a.call(b, this.items[c], c); c++);
        return this
      };
      for (var gd in M)M.hasOwnProperty(gd) && (Qa[gd] = function (a) {
        return function () {
          var b = arguments;
          return this.forEach(function (c) {
            c[a][Q](c, b)
          })
        }
      }(gd));
      Qa.attr = function (a,
                          c) {
        if (a && b.is(a, m) && b.is(a[0], "object"))for (var e = 0, r = a.length; e < r; e++)this.items[e].attr(a[e]); else for (e = 0, r = this.items.length; e < r; e++)this.items[e].attr(a, c);
        return this
      };
      Qa.clear = function () {
        for (; this.length;)this.pop()
      };
      Qa.splice = function (a, b, c) {
        a = 0 > a ? da(this.length + a, 0) : a;
        b = da(0, fa(this.length - a, isNaN(b) && this.length || b));
        var e = [], r = [], h = [], w;
        for (w = 2; w < arguments.length; w++)h.push(arguments[w]);
        for (w = 0; w < b; w++)r.push(this[a + w]);
        for (; w < this.length - a; w++)e.push(this[a + w]);
        var v = h.length;
        for (w = 0; w <
        v + e.length; w++)this.items[a + w] = this[a + w] = w < v ? h[w] : e[w - v];
        for (w = this.items.length = this.length -= b - v; this[w];)delete this[w++];
        return new Kc(r)
      };
      Qa.exclude = function (a) {
        for (var b = 0, c = this.length; b < c; b++)if (this[b] == a)return this.splice(b, 1), !0
      };
      Qa.animate = function (a, c, e, r) {
        !b.is(e, "function") && e || (r = e || null);
        var h = this.items.length, w = h, v = this, D;
        if (!h)return this;
        r && (D = function () {
          !--h && r.call(v)
        });
        e = b.is(e, "string") ? e : D;
        c = b.animation(a, c, e, D);
        for (a = this.items[--w].animate(c); w--;)this.items[w] && !this.items[w].removed &&
        this.items[w].animateWith(a, c, c);
        return this
      };
      Qa.insertAfter = function (a) {
        for (var b = this.items.length; b--;)this.items[b].insertAfter(a);
        return this
      };
      Qa.getBBox = function () {
        for (var a = [], b = [], c = [], e = [], r = this.items.length; r--;)if (!this.items[r].removed) {
          var h = this.items[r].getBBox();
          a.push(h.x);
          b.push(h.y);
          c.push(h.x + h.width);
          e.push(h.y + h.height)
        }
        a = fa[Q](0, a);
        b = fa[Q](0, b);
        c = da[Q](0, c);
        e = da[Q](0, e);
        return {x: a, y: b, x2: c, y2: e, width: c - a, height: e - b}
      };
      Qa.clone = function (a) {
        a = new Kc;
        for (var b = 0, c = this.items.length; b <
        c; b++)a.push(this.items[b].clone());
        return a
      };
      Qa.toString = function () {
        return "Raphaël‘s set"
      };
      Qa.glow = function (a) {
        var b = this.paper.set();
        this.forEach(function (c, e) {
          var r = c.glow(a);
          null != r && r.forEach(function (a, c) {
            b.push(a)
          })
        });
        return b
      };
      b.registerFont = function (a) {
        if (!a.face)return a;
        this.fonts = this.fonts || {};
        var b = {w: a.w, face: {}, glyphs: {}}, c = a.face["font-family"], e;
        for (e in a.face)a.face.hasOwnProperty(e) && (b.face[e] = a.face[e]);
        this.fonts[c] ? this.fonts[c].push(b) : this.fonts[c] = [b];
        if (!a.svg) {
          b.face["units-per-em"] =
            L(a.face["units-per-em"], 10);
          for (var r in a.glyphs)if (a.glyphs.hasOwnProperty(r) && (c = a.glyphs[r], b.glyphs[r] = {
              w: c.w,
              k: {},
              d: c.d && "M" + c.d.replace(/[mlcxtrv]/g, function (a) {
                return {l: "L", c: "C", x: "z", t: "m", r: "l", v: "c"}[a] || "M"
              }) + "z"
            }, c.k))for (var h in c.k)c.hasOwnProperty(h) && (b.glyphs[r].k[h] = c.k[h])
        }
        return a
      };
      aa.getFont = function (a, c, e, r) {
        r = r || "normal";
        e = e || "normal";
        c = +c || {normal: 400, bold: 700, lighter: 300, bolder: 800}[c] || 400;
        if (b.fonts) {
          var h = b.fonts[a];
          if (!h) {
            a = new RegExp("(^|\\s)" + a.replace(/[^\w\d\s+!~.:_-]/g,
              "") + "(\\s|$)", "i");
            for (var w in b.fonts)if (b.fonts.hasOwnProperty(w) && a.test(w)) {
              h = b.fonts[w];
              break
            }
          }
          var v;
          if (h)for (w = 0, a = h.length; w < a && (v = h[w], v.face["font-weight"] != c || v.face["font-style"] != e && v.face["font-style"] || v.face["font-stretch"] != r); w++);
          return v
        }
      };
      aa.print = function (a, c, e, r, w, v, D) {
        v = v || "middle";
        D = da(fa(D || 0, 1), -1);
        var d = h(e).split(""), f = 0, g = 0, p = "";
        b.is(r, e) && (r = this.getFont(r));
        if (r) {
          e = (w || 16) / r.face["units-per-em"];
          var m = r.face.bbox.split(jb);
          w = +m[0];
          var I = m[3] - m[1], N = 0;
          v = +m[1] + ("baseline" ==
          v ? I + +r.face.descent : I / 2);
          for (var m = 0, W = d.length; m < W; m++) {
            if ("\n" == d[m])g = n = f = 0, N += I; else var l = g && r.glyphs[d[m - 1]] || {}, n = r.glyphs[d[m]], f = f + (g ? (l.w || r.w) + (l.k && l.k[d[m]] || 0) + r.w * D : 0), g = 1;
            n && n.d && (p += b.transformPath(n.d, ["t", f * e, N * e, "s", e, e, w, v, "t", (a - w) / e, (c - v) / e]))
          }
        }
        return this.path(p).attr({fill: "#000", stroke: "none"})
      };
      aa.add = function (a) {
        if (b.is(a, "array"))for (var c = this.set(), e = 0, r = a.length, h; e < r; e++)h = a[e] || {}, qa.hasOwnProperty(h.type) && c.push(this[h.type]().attr(h));
        return c
      };
      b.format = function (a,
                           c) {
        var e = b.is(c, m) ? [0].concat(c) : arguments;
        a && b.is(a, "string") && e.length - 1 && (a = a.replace(Ca, function (a, b) {
          return null == e[++b] ? "" : e[b]
        }));
        return a || ""
      };
      b.fullfill = function () {
        var a = /\{([^\}]+)\}/g, b = /(?:(?:^|\.)(.+?)(?=\[|\.|$|\()|\[('|")(.+?)\2\])(\(\))?/g, c = function (a, c, e) {
          var r = e;
          c.replace(b, function (a, b, c, e, h) {
            b = b || e;
            r && (b in r && (r = r[b]), "function" == typeof r && h && (r = r()))
          });
          return r = (null == r || r == e ? a : r) + ""
        };
        return function (b, e) {
          return String(b).replace(a, function (a, b) {
            return c(a, b, e)
          })
        }
      }();
      b.ninja =
        function () {
          ka ? u.win.Raphael = ha : delete Raphael;
          return b
        };
      var td = b.vml && .5 || 0;
      b.crispBound = Oa(function (a, b, c, e, r) {
        var h = {}, w;
        a = a || 0;
        b = b || 0;
        c = c || 0;
        e = e || 0;
        r = r || 0;
        w = r % 2 / 2 + td;
        h.x = ua(a + w) - w;
        h.y = ua(b + w) - w;
        h.width = ua(a + c + w) - w - h.x;
        h.height = ua(b + e + w) - w - h.y;
        h["stroke-width"] = r;
        0 === h.width && 0 !== c && (h.width = 1);
        0 === h.height && 0 !== e && (h.height = 1);
        return h
      }, b);
      M.crisp = function () {
        var a = this.attrs, c, e = this.attr(["x", "y", "width", "height", "stroke-width"]), e = b.crispBound(e.x, e.y, e.width, e.height, e["stroke-width"]);
        for (c in e)a[c] ===
        e[c] && delete e[c];
        return this.attr(e)
      };
      b.st = Qa;
      b.define = function (a, c, e, r, h, w) {
        var v;
        if (b.is(a, m))for (w = 0, v = a.length; w < v; w++)b.define(a[w]); else if (b.is(a, "object"))b.define(a.name, a[a.name], a.ca, a.fn, a.e, a.data); else if (a && !b.fn[a])return b.fn[a] = function () {
          var w = arguments, v = c.apply(this, w), D;
          if (r && b.is(r, "object"))for (D in r)v[D] = r[D];
          if (h && b.is(h, "object"))for (D in h)v[D] && v[D](h[D]);
          if (e) {
            if (b.is(e, "function"))v.ca[a] = e; else for (D in e)v.ca[D] = e[D];
            v.ca[a] && (b._lastArgIfGroup(w, !0), v.attr(a, P.call(w)))
          }
          return v
        },
        e && (b.fn[a].ca = e), r && (b.fn[a].fn = r), h && (b.fn[a].e = h), w && (b.fn[a].data = w), b.fn[a]
      };
      (function (a, c, e) {
        function r() {
          /in/.test(a.readyState) ? setTimeout(r, 9) : b.eve("raphael.DOMload")
        }

        null == a.readyState && a.addEventListener && (a.addEventListener(c, e = function () {
          a.removeEventListener(c, e, !1);
          a.readyState = "complete"
        }, !1), a.readyState = "loading");
        r()
      })(document, "DOMContentLoaded");
      k.on("raphael.DOMload", function () {
        n = !0
      });
      (function () {
        if (b.svg) {
          var a = String, c = parseFloat, e = parseInt, r = Math, h = r.max, w = r.abs, v = r.pow,
            D = r.sqrt, d = /[, ]+/, f = !(!/AppleWebKit/.test(b._g.win.navigator.userAgent) || /Chrome/.test(b._g.win.navigator.userAgent) && !(29 > b._g.win.navigator.appVersion.match(/Chrome\/(\d+)\./)[1])), g = b.eve, p = {
              block: "M5,0 0,2.5 5,5z",
              classic: "M5,0 0,2.5 5,5 3.5,3 3.5,2z",
              diamond: "M2.5,0 5,2.5 2.5,5 0,2.5z",
              open: "M6,1 1,3.5 6,6",
              oval: "M2.5,0A2.5,2.5,0,0,1,2.5,5 2.5,2.5,0,0,1,2.5,0z"
            }, m = {};
          b.toString = function () {
            return "Your browser supports SVG.\nYou are running Raphaël " + this.version
          };
          b._url = "";
          var I = function (a, b) {
              var c =
                a.gradient;
              if (c) {
                if (c === b)return;
                c.refCount--;
                c.refCount || c.parentNode.removeChild(c);
                delete a.gradient
              }
              b && (a.gradient = b, b.refCount++)
            }, N = b._createNode = function (c, e) {
              if (e) {
                "string" == typeof c && (c = N(c));
                for (var r in e)e.hasOwnProperty(r) && ("xlink:" == r.substring(0, 6) ? c.setAttributeNS("http://www.w3.org/1999/xlink", r.substring(6), a(e[r])) : c.setAttribute(r, a(e[r])))
              } else c = b._g.doc.createElementNS("http://www.w3.org/2000/svg", c);
              return c
            }, W = {userSpaceOnUse: "userSpaceOnUse", objectBoundingBox: "objectBoundingBox"},
            l = {pad: "pad", redlect: "reflect", repeat: "repeat"}, n = function (e, d) {
              if (!e.paper || !e.paper.defs)return 0;
              var f = "linear", g = e.paper, p = (g.id + "-" + d).replace(/[\(\)\s%:,\xb0#]/g, "_"), m = .5, n = .5, F, J, ja, M, H, k = e.node, s = k.style, L = b._g.doc.getElementById(p);
              if (!L) {
                d = a(d).replace(b._radial_gradient, function (a, b) {
                  f = "radial";
                  b = b && b.split(",") || [];
                  M = b[5];
                  H = b[6];
                  var e = b[0], r = b[1], h = b[2], w = b[3], d = b[4], g = e && r, p;
                  h && (F = /\%/.test(h) ? h : c(h));
                  if (M === W.userSpaceOnUse)return g && (m = e, n = r), w && d && (J = w, ja = d, g || (m = J, n = ja)), "";
                  g && (m =
                    c(e), n = c(r), e = 2 * (.5 < n) - 1, .25 < (p = v(m - .5, 2)) + v(n - .5, 2) && .25 > p && (n = D(.25 - p) * e + .5) && .5 !== n && (n = n.toFixed(5) - 1E-5 * e));
                  w && d && (J = c(w), ja = c(d), e = 2 * (.5 < ja) - 1, .25 < (p = v(J - .5, 2)) + v(ja - .5, 2) && .25 > p && (ja = D(.25 - p) * e + .5) && .5 !== ja && (ja = ja.toFixed(5) - 1E-5 * e), g || (m = J, n = ja));
                  return ""
                });
                d = d.split(/\s*\-\s*/);
                if ("linear" == f) {
                  var L = d.shift(), z = L.match(/\((.*)\)/), O, z = z && z[1] && z[1].split(/\s*\,\s*/), L = -c(L);
                  if (isNaN(L))return null;
                  z && z.length ? (z[0]in W ? (M = z.shift(), z[0]in l && (H = z.shift())) : (z[4] && (M = z[4]), z[5] && (H = z[5])),
                    O = [z[0] || "0%", z[1] || "0%", z[2] || "100%", z[3] || "0%"]) : (O = [0, 0, r.cos(b.rad(L)), r.sin(b.rad(L))], L = 1 / (h(w(O[2]), w(O[3])) || 1), O[2] *= L, O[3] *= L, 0 > O[2] && (O[0] = -O[2], O[2] = 0), 0 > O[3] && (O[1] = -O[3], O[3] = 0))
                }
                z = b._parseDots(d);
                if (!z)return null;
                L = N(f + "Gradient", {id: p});
                L.refCount = 0;
                M in W && L.setAttribute("gradientUnits", a(M));
                H in l && L.setAttribute("spreadMethod", a(H));
                "radial" === f ? (void 0 !== F && L.setAttribute("r", a(F)), void 0 !== J && void 0 !== ja && (L.setAttribute("cx", a(J)), L.setAttribute("cy", a(ja))), L.setAttribute("fx",
                  a(m)), L.setAttribute("fy", a(n))) : N(L, {x1: O[0], y1: O[1], x2: O[2], y2: O[3]});
                O = 0;
                for (var Wa = z.length; O < Wa; O++)L.appendChild(N("stop", {
                  offset: z[O].offset ? z[O].offset : O ? "100%" : "0%",
                  "stop-color": z[O].color || "#fff",
                  "stop-opacity": void 0 === z[O].opacity ? 1 : z[O].opacity
                }));
                g.defs.appendChild(L)
              }
              I(e, L);
              N(k, {fill: "url('" + b._url + "#" + p + "')", opacity: 1, "fill-opacity": 1});
              s.fill = "";
              s.opacity = 1;
              return s.fillOpacity = 1
            }, F = function (a) {
              var b = a.getBBox(1);
              N(a.pattern, {
                patternTransform: a.matrix.invert() + " translate(" + b.x + "," +
                b.y + ")"
              })
            }, J = function (c, e, r) {
              if ("path" == c.type) {
                for (var h = a(e).toLowerCase().split("-"), w = c.paper, v = r ? "end" : "start", D = c.node, d = c.attrs, f = d["stroke-width"], g = h.length, I = "classic", W, l, n = 3, F = 3, J = 5; g--;)switch (h[g]) {
                  case "block":
                  case "classic":
                  case "oval":
                  case "diamond":
                  case "open":
                  case "none":
                    I = h[g];
                    break;
                  case "wide":
                    F = 5;
                    break;
                  case "narrow":
                    F = 2;
                    break;
                  case "long":
                    n = 5;
                    break;
                  case "short":
                    n = 2
                }
                "open" == I ? (n += 2, F += 2, J += 2, W = 1, l = r ? 4 : 1, h = {
                  fill: "none",
                  stroke: d.stroke
                }) : (l = W = n / 2, h = {fill: d.stroke, stroke: "none"});
                c._.arrows ? r ? (c._.arrows.endPath && m[c._.arrows.endPath]--, c._.arrows.endMarker && m[c._.arrows.endMarker]--) : (c._.arrows.startPath && m[c._.arrows.startPath]--, c._.arrows.startMarker && m[c._.arrows.startMarker]--) : c._.arrows = {};
                if ("none" != I) {
                  var g = "raphael-marker-" + I, ja = "raphael-marker-" + v + I + n + F + "-obj" + c.id;
                  b._g.doc.getElementById(g) ? m[g]++ : (w.defs.appendChild(N(N("path"), {
                    "stroke-linecap": "round",
                    d: p[I],
                    id: g
                  })), m[g] = 1);
                  var M = b._g.doc.getElementById(ja);
                  M ? (m[ja]++, n = M.getElementsByTagName("use")[0]) : (M =
                    N(N("marker"), {
                      id: ja,
                      markerHeight: F,
                      markerWidth: n,
                      orient: "auto",
                      refX: l,
                      refY: F / 2
                    }), n = N(N("use"), {
                    "xlink:href": "#" + g,
                    transform: (r ? "rotate(180 " + n / 2 + " " + F / 2 + ") " : "") + "scale(" + n / J + "," + F / J + ")",
                    "stroke-width": (1 / ((n / J + F / J) / 2)).toFixed(4)
                  }), M.appendChild(n), w.defs.appendChild(M), m[ja] = 1);
                  N(n, h);
                  w = W * ("diamond" != I && "oval" != I);
                  r ? (r = c._.arrows.startdx * f || 0, f = b.getTotalLength(d.path) - w * f) : (r = w * f, f = b.getTotalLength(d.path) - (c._.arrows.enddx * f || 0));
                  h = {};
                  h["marker-" + v] = "url('" + b._url + "#" + ja + "')";
                  if (f || r)h.d = b.getSubpath(d.path,
                    r, f);
                  N(D, h);
                  c._.arrows[v + "Path"] = g;
                  c._.arrows[v + "Marker"] = ja;
                  c._.arrows[v + "dx"] = w;
                  c._.arrows[v + "Type"] = I;
                  c._.arrows[v + "String"] = e
                } else r ? (r = c._.arrows.startdx * f || 0, f = b.getTotalLength(d.path) - r) : (r = 0, f = b.getTotalLength(d.path) - (c._.arrows.enddx * f || 0)), c._.arrows[v + "Path"] && N(D, {d: b.getSubpath(d.path, r, f)}), delete c._.arrows[v + "Path"], delete c._.arrows[v + "Marker"], delete c._.arrows[v + "dx"], delete c._.arrows[v + "Type"], delete c._.arrows[v + "String"];
                for (h in m)m.hasOwnProperty(h) && !m[h] && (c = b._g.doc.getElementById(h)) &&
                c.parentNode.removeChild(c)
              }
            }, ja = {
              "": ["none"],
              none: ["none"],
              "-": [3, 1],
              ".": [1, 1],
              "-.": [3, 1, 1, 1],
              "-..": [3, 1, 1, 1, 1, 1],
              ". ": [1, 3],
              "- ": [4, 3],
              "--": [8, 3],
              "- .": [4, 3, 1, 3],
              "--.": [8, 3, 1, 3],
              "--..": [8, 3, 1, 3, 1, 3]
            }, M = function (c, e, r) {
              var h = ja[a(e).toLowerCase()], w, v;
              if (e = h || void 0 !== e && [].concat(e)) {
                w = c.attrs["stroke-width"] || 1;
                r = {round: w, square: w, butt: 0}[c.attrs["stroke-linecap"] || r["stroke-linecap"]] || 0;
                v = e.length;
                w = h ? w : 1;
                for (h = []; v--;)h[v] = e[v] * w + (v % 2 ? 1 : -1) * r || e[v], 0 > h[v] && (h[v] = 0);
                b.is(e, "array") && N(c.node,
                  {"stroke-dasharray": h.join(",")})
              }
            }, H = function (a, b) {
              for (var c in b)g("raphael.attr." + c + "." + a.id, a, b[c], c), a.ca[c] && a.attr(c, b[c])
            }, k = b._setFillAndStroke = function (c, r) {
              if (c.paper.canvas) {
                var v = c.node, D = c.attrs, g = c.paper, p = v.style, m = p.visibility;
                p.visibility = "hidden";
                for (var W in r)if (r.hasOwnProperty(W) && b._availableAttrs.hasOwnProperty(W)) {
                  var l = r[W];
                  D[W] = l;
                  switch (W) {
                    case "blur":
                      c.blur(l);
                      break;
                    case "href":
                    case "title":
                    case "target":
                      var ja = v.parentNode;
                      if ("a" != ja.tagName.toLowerCase()) {
                        if ("" == l)break;
                        var H = N("a");
                        H.raphael = !0;
                        H.raphaelid = v.raphaelid;
                        ja.insertBefore(H, v);
                        H.appendChild(v);
                        ja = H
                      }
                      "target" == W ? ja.setAttributeNS("http://www.w3.org/1999/xlink", "show", "blank" == l ? "new" : l) : ja.setAttributeNS("http://www.w3.org/1999/xlink", W, l);
                      v.titleNode = ja;
                      break;
                    case "cursor":
                      p.cursor = l;
                      break;
                    case "transform":
                      c.transform(l);
                      break;
                    case "rotation":
                      b.is(l, "array") ? c.rotate.apply(c, l) : c.rotate(l);
                      break;
                    case "arrow-start":
                      J(c, l);
                      break;
                    case "arrow-end":
                      J(c, l, 1);
                      break;
                    case "clip-path":
                      var k = !0;
                    case "clip-rect":
                      ja =
                        !k && a(l).split(d);
                      c._.clipispath = !!k;
                      if (k || 4 == ja.length) {
                        c.clip && c.clip.parentNode.parentNode.removeChild(c.clip.parentNode);
                        var H = N("clipPath"), L = N(k ? "path" : "rect");
                        H.id = b.createUUID();
                        N(L, k ? {
                          d: l ? D["clip-path"] = b._pathToAbsolute(l) : b._availableAttrs.path,
                          fill: "none"
                        } : {x: ja[0], y: ja[1], width: ja[2], height: ja[3], transform: c.matrix.invert()});
                        H.appendChild(L);
                        g.defs.appendChild(H);
                        N(v, {"clip-path": "url('" + b._url + "#" + H.id + "')"});
                        c.clip = L
                      }
                      !l && (l = v.getAttribute("clip-path")) && ((l = b._g.doc.getElementById(l.replace(/(^url\(#|\)$)/g,
                        ""))) && l.parentNode.removeChild(l), N(v, {"clip-path": ""}), delete c.clip);
                      break;
                    case "path":
                      "path" == c.type && (N(v, {d: l ? D.path = b._pathToAbsolute(l) : b._availableAttrs.path}), c._.dirty = 1, c._.arrows && ("startString"in c._.arrows && J(c, c._.arrows.startString), "endString"in c._.arrows && J(c, c._.arrows.endString, 1)));
                      break;
                    case "width":
                      if (v.setAttribute(W, l), c._.dirty = 1, D.fx)W = "x", l = D.x; else break;
                    case "x":
                      D.fx && (l = -D.x - (D.width || 0));
                    case "rx":
                      if ("rx" == W && "rect" == c.type)break;
                    case "cx":
                      v.setAttribute(W, l);
                      c.pattern &&
                      F(c);
                      c._.dirty = 1;
                      break;
                    case "height":
                      if (v.setAttribute(W, l), c._.dirty = 1, D.fy)W = "y", l = D.y; else break;
                    case "y":
                      D.fy && (l = -D.y - (D.height || 0));
                    case "ry":
                      if ("ry" == W && "rect" == c.type)break;
                    case "cy":
                      v.setAttribute(W, l);
                      c.pattern && F(c);
                      c._.dirty = 1;
                      break;
                    case "r":
                      "rect" == c.type ? N(v, {rx: l, ry: l}) : v.setAttribute(W, l);
                      c._.dirty = 1;
                      break;
                    case "src":
                      "image" == c.type && v.setAttributeNS("http://www.w3.org/1999/xlink", "href", l);
                      break;
                    case "stroke-width":
                      if (1 != c._.sx || 1 != c._.sy)l /= h(w(c._.sx), w(c._.sy)) || 1;
                      g._vbSize && (l *=
                        g._vbSize);
                      f && 0 === l && (l = 1E-6);
                      v.setAttribute(W, l);
                      D["stroke-dasharray"] && M(c, D["stroke-dasharray"], r);
                      c._.arrows && ("startString"in c._.arrows && J(c, c._.arrows.startString), "endString"in c._.arrows && J(c, c._.arrows.endString, 1));
                      break;
                    case "stroke-dasharray":
                      M(c, l, r);
                      break;
                    case "fill":
                      var z = a(l).match(b._ISURL);
                      if (z) {
                        var H = N("pattern"), O = N("image");
                        H.id = b.createUUID();
                        N(H, {x: 0, y: 0, patternUnits: "userSpaceOnUse", height: 1, width: 1});
                        N(O, {x: 0, y: 0, "xlink:href": z[1]});
                        H.appendChild(O);
                        (function (a) {
                          b._preload(z[1],
                            function () {
                              var b = this.offsetWidth, c = this.offsetHeight;
                              N(a, {width: b, height: c});
                              N(O, {width: b, height: c});
                              g.safari()
                            })
                        })(H);
                        g.defs.appendChild(H);
                        p.fill = "url('" + b._url + "#" + H.id + "')";
                        N(v, {fill: p.fill});
                        c.pattern = H;
                        c.pattern && F(c);
                        break
                      }
                      ja = b.getRGB(l);
                      if (!ja.error)delete r.gradient, delete D.gradient, !b.is(D.opacity, "undefined") && b.is(r.opacity, "undefined") && N(v, {opacity: D.opacity}), !b.is(D["fill-opacity"], "undefined") && b.is(r["fill-opacity"], "undefined") && N(v, {"fill-opacity": D["fill-opacity"]}), c.gradient &&
                      I(c); else if (("circle" == c.type || "ellipse" == c.type || "r" != a(l).charAt()) && n(c, l)) {
                        if ("opacity"in D || "fill-opacity"in D)if (ja = b._g.doc.getElementById(v.getAttribute("fill").replace(/^url\(#|\)$/g, "")))ja = ja.getElementsByTagName("stop"), N(ja[ja.length - 1], {"stop-opacity": ("opacity"in D ? D.opacity : 1) * ("fill-opacity"in D ? D["fill-opacity"] : 1)});
                        D.gradient = l;
                        D.fill = "none";
                        p.fill = "";
                        break
                      }
                      ja.hasOwnProperty("opacity") ? (N(v, {"fill-opacity": p.fillOpacity = 1 < ja.opacity ? ja.opacity / 100 : ja.opacity}), c._.fillOpacityDirty = !0) : c._.fillOpacityDirty && b.is(D["fill-opacity"], "undefined") && b.is(r["fill-opacity"], "undefined") && (v.removeAttribute("fill-opacity"), p.fillOpacity = "", delete c._.fillOpacityDirty);
                    case "stroke":
                      ja = b.getRGB(l);
                      v.setAttribute(W, ja.hex);
                      p[W] = ja.hex;
                      "stroke" == W && (ja.hasOwnProperty("opacity") ? (N(v, {"stroke-opacity": p.strokeOpacity = 1 < ja.opacity ? ja.opacity / 100 : ja.opacity}), c._.strokeOpacityDirty = !0) : c._.strokeOpacityDirty && b.is(D["stroke-opacity"], "undefined") && b.is(r["stroke-opacity"], "undefined") && (v.removeAttribute("stroke-opacity"),
                        p.strokeOpacity = "", delete c._.strokeOpacityDirty), c._.arrows && ("startString"in c._.arrows && J(c, c._.arrows.startString), "endString"in c._.arrows && J(c, c._.arrows.endString, 1)));
                      break;
                    case "gradient":
                      "circle" != c.type && "ellipse" != c.type && "r" == a(l).charAt() || n(c, l);
                      break;
                    case "line-height":
                    case "vertical-align":
                      break;
                    case "visibility":
                      "hidden" === l ? c.hide() : c.show();
                      break;
                    case "opacity":
                      D.gradient && !D.hasOwnProperty("stroke-opacity") && N(v, {"stroke-opacity": 1 < l ? l / 100 : l});
                    case "fill-opacity":
                      if (D.gradient) {
                        if (ja =
                            b._g.doc.getElementById(v.getAttribute("fill").replace(/^url\(#|\)$/g, "")))ja = ja.getElementsByTagName("stop"), N(ja[ja.length - 1], {"stop-opacity": l});
                        break
                      }
                    default:
                      "font-size" == W && (l = e(l, 10) + "px"), ja = W.replace(/(\-.)/g, function (a) {
                        return a.substring(1).toUpperCase()
                      }), p[ja] = l, c._.dirty = 1, v.setAttribute(W, l)
                  }
                }
                "text" === c.type && s(c, r);
                p.visibility = m
              }
            }, s = function (e, h) {
              if ("text" == e.type && (h.hasOwnProperty("text") || h.hasOwnProperty("font") || h.hasOwnProperty("font-size") || h.hasOwnProperty("x") || h.hasOwnProperty("y") ||
                h.hasOwnProperty("line-height") || h.hasOwnProperty("vertical-align"))) {
                var w = e.attrs, v = e.node, D = v.firstChild && b._g.doc.defaultView.getComputedStyle(v.firstChild, ""), d = D ? c(b._g.doc.defaultView.getComputedStyle(v.firstChild, "").getPropertyValue("font-size")) : 10, f = c(h["line-height"] || w["line-height"]) || 1.2 * d, g = w.hasOwnProperty("vertical-align") ? w["vertical-align"] : "middle", p = (h.direction || (D ? D.getPropertyValue("direction") : "initial")).toLowerCase(), m = !!document.documentMode;
                isNaN(f) && (f = 1.2 * d);
                b.is(h.text,
                  "array") && (h.text = h.text.join("<br>"));
                g = "top" === g ? -.5 : "bottom" === g ? .5 : 0;
                if (h.hasOwnProperty("text") && (h.text !== w.text || e._textdirty)) {
                  for (w.text = h.text; v.firstChild;)v.removeChild(v.firstChild);
                  for (var l = a(h.text).split(/\n|<br\s*?\/?>/ig), d = [], I = 0, W = l.length; I < W; I++)D = N("tspan"), I ? N(D, {
                    dy: f,
                    x: w.x
                  }) : N(D, {
                    dy: f * l.length * g,
                    x: w.x
                  }), l[I] || (D.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", "preserve"), l[I] = " "), D.appendChild(b._g.doc.createTextNode(l[I])), v.appendChild(D), d[I] = D, !m &&
                  "rtl" === p && I < W - 1 && (D = N("tspan"), N(D, {
                    visibility: "hidden",
                    "font-size": "0px"
                  }), D.appendChild(b._g.doc.createTextNode("i")), v.appendChild(D));
                  e._textdirty = !1
                } else for (d = v.getElementsByTagName("tspan"), I = m = 0, W = d.length; I < W; I++)if (D = d[I], p = D.attributes[0], !p || "visibility" !== p.name && "visibility" !== p.nodeName || "hidden" !== p.value && "hidden" !== p.nodeValue)I ? N(D, {
                  dy: f,
                  x: w.x
                }) : (p = d[1] && d[1].attributes[0], !p || "visibility" !== p.name && "visibility" !== p.nodeName || "hidden" !== p.value && "hidden" !== p.nodeValue || (m = r.floor(.5 *
                d.length)), N(d[0], {dy: f * (d.length - m) * g, x: w.x}));
                N(v, {x: w.x, y: w.y});
                e._.dirty = 1;
                v = e._getBBox();
                f = w.y - (v.y + v.height / 2);
                if (v.isCalculated)switch (w["vertical-align"]) {
                  case "top":
                    f = .75 * v.height;
                    break;
                  case "bottom":
                    f = -(.25 * v.height);
                    break;
                  default:
                    f = w.y - (v.y + .25 * v.height)
                }
                f && b.is(f, "finite") && d[0] && N(d[0], {dy: f})
              }
            }, L = function (a, c, e) {
              e = e || c;
              e.canvas && e.canvas.appendChild(a);
              this.node = this[0] = a;
              a.raphael = !0;
              a.raphaelid = this.id = b._oid++;
              this.matrix = b.matrix();
              this.realPath = null;
              this.attrs = this.attrs || {};
              this.followers =
                this.followers || [];
              this.paper = c;
              this.ca = this.customAttributes = this.customAttributes || new c._CustomAttributes;
              this._ = {transform: [], sx: 1, sy: 1, deg: 0, dx: 0, dy: 0, dirty: 1};
              this.parent = e;
              !e.bottom && (e.bottom = this);
              (this.prev = e.top) && (e.top.next = this);
              e.top = this;
              this.next = null
            }, z = b.el;
          L.prototype = z;
          z.constructor = L;
          b._engine.getNode = function (a) {
            a = a.node || a[0].node;
            return a.titleNode || a
          };
          b._engine.getLastNode = function (a) {
            a = a.node || a[a.length - 1].node;
            return a.titleNode || a
          };
          z.rotate = function (b, e, r) {
            if (this.removed)return this;
            b = a(b).split(d);
            b.length - 1 && (e = c(b[1]), r = c(b[2]));
            b = c(b[0]);
            null == r && (e = r);
            if (null == e || null == r)r = this.getBBox(1), e = r.x + r.width / 2, r = r.y + r.height / 2;
            this.transform(this._.transform.concat([["r", b, e, r]]));
            return this
          };
          z.scale = function (b, e, r, h) {
            var w;
            if (this.removed)return this;
            b = a(b).split(d);
            b.length - 1 && (e = c(b[1]), r = c(b[2]), h = c(b[3]));
            b = c(b[0]);
            null == e && (e = b);
            null == h && (r = h);
            if (null == r || null == h)w = this.getBBox(1);
            r = null == r ? w.x + w.width / 2 : r;
            h = null == h ? w.y + w.height / 2 : h;
            this.transform(this._.transform.concat([["s",
              b, e, r, h]]));
            return this
          };
          z.translate = function (b, e) {
            if (this.removed)return this;
            b = a(b).split(d);
            b.length - 1 && (e = c(b[1]));
            b = c(b[0]) || 0;
            this.transform(this._.transform.concat([["t", b, +e || 0]]));
            return this
          };
          z.transform = function (a) {
            var c = this._;
            if (null == a)return c.transform;
            b._extractTransform(this, a);
            this.clip && !c.clipispath && N(this.clip, {transform: this.matrix.invert()});
            this.pattern && F(this);
            this.node && N(this.node, {transform: this.matrix});
            if (1 != c.sx || 1 != c.sy)a = this.attrs.hasOwnProperty("stroke-width") ?
              this.attrs["stroke-width"] : 1, this.attr({"stroke-width": a});
            return this
          };
          z.hide = function () {
            !this.removed && this.paper.safari(this.node.style.display = "none");
            return this
          };
          z.show = function () {
            !this.removed && this.paper.safari(this.node.style.display = "");
            return this
          };
          z.remove = function () {
            if (!this.removed && this.parent.canvas) {
              var a = b._engine.getNode(this), c = this.paper, e = c.defs;
              c.__set__ && c.__set__.exclude(this);
              g.unbind("raphael.*.*." + this.id);
              for (this.gradient && e && I(this); e = this.followers.pop();)e.el.remove();
              for (; e = this.bottom;)e.remove();
              this._drag && this.undrag();
              if (this.events)for (; e = this.events.pop();)e.unbind();
              this.parent.canvas.removeChild(a);
              this.removeData();
              delete c._elementsById[this.id];
              b._tear(this, this.parent);
              for (e in this)this[e] = "function" === typeof this[e] ? b._removedFactory(e) : null;
              this.removed = !0
            }
          };
          z._getBBox = function () {
            var a = this.node, b = {}, c = this.attrs, e, r;
            "none" === a.style.display && (this.show(), r = !0);
            try {
              b = a.getBBox(), "text" == this.type && (void 0 === b.x && (b.isCalculated = !0, e = c["text-anchor"],
                b.x = (c.x || 0) - b.width * ("start" === e ? 0 : "middle" === e ? .5 : 1)), void 0 === b.y && (b.isCalculated = !0, e = c["vertical-align"], b.y = (c.y || 0) - b.height * ("bottom" === e ? 1 : "middle" === e ? .5 : 0)))
            } catch (h) {
            } finally {
              b = b || {}
            }
            r && this.hide();
            return b
          };
          z.attr = function (a, c) {
            if (this.removed)return this;
            if (null == a) {
              var e = {}, r;
              for (r in this.attrs)this.attrs.hasOwnProperty(r) && (e[r] = this.attrs[r]);
              e.gradient && "none" == e.fill && (e.fill = e.gradient) && delete e.gradient;
              e.transform = this._.transform;
              e.visibility = "none" === this.node.style.display ?
                "hidden" : "visible";
              return e
            }
            if (null == c && b.is(a, "string")) {
              if ("fill" == a && "none" == this.attrs.fill && this.attrs.gradient)return this.attrs.gradient;
              if ("transform" == a)return this._.transform;
              if ("visibility" == a)return "none" === this.node.style.display ? "hidden" : "visible";
              var e = a.split(d), h = {}, w = 0;
              for (r = e.length; w < r; w++)a = e[w], a in this.attrs ? h[a] = this.attrs[a] : b.is(this.ca[a], "function") ? h[a] = this.ca[a].def : h[a] = b._availableAttrs[a];
              return r - 1 ? h : h[e[0]]
            }
            if (null == c && b.is(a, "array")) {
              h = {};
              w = 0;
              for (r = a.length; w <
              r; w++)h[a[w]] = this.attr(a[w]);
              return h
            }
            null != c ? (e = {}, e[a] = c) : null != a && b.is(a, "object") && (e = a);
            for (w in e)g("raphael.attr." + w + "." + this.id, this, e[w], w);
            var v = {};
            for (w in this.ca)if (this.ca[w] && e.hasOwnProperty(w) && b.is(this.ca[w], "function") && !this.ca["_invoked" + w]) {
              this.ca["_invoked" + w] = !0;
              r = this.ca[w].apply(this, [].concat(e[w]));
              delete this.ca["_invoked" + w];
              for (h in r)r.hasOwnProperty(h) && (e[h] = r[h]);
              this.attrs[w] = e[w];
              !1 === r && (v[w] = e[w], delete e[w])
            }
            k(this, e);
            var D, w = 0;
            for (r = this.followers.length; w <
            r; w++)D = this.followers[w], D.cb && !D.cb.call(D.el, e, this) || D.el.attr(e);
            for (h in v)e[h] = v[h];
            return this
          };
          z.blur = function (a) {
            if (0 !== +a) {
              var c = N("filter"), e = N("feGaussianBlur");
              this.attrs.blur = a;
              c.id = b.createUUID();
              N(e, {stdDeviation: +a || 1.5});
              c.appendChild(e);
              this.paper.defs.appendChild(c);
              this._blur = c;
              N(this.node, {filter: "url('" + b._url + "#" + c.id + "')"})
            } else this._blur && (this._blur.parentNode.removeChild(this._blur), delete this._blur, delete this.attrs.blur), this.node.removeAttribute("filter")
          };
          z.on = function (a,
                           c) {
            if (this.removed)return this;
            var e = c;
            b.supportsTouch && (a = b._touchMap[a] || "click" === a && "touchstart" || a, e = function (a) {
              a.preventDefault();
              c()
            });
            this.node["on" + a] = e;
            return this
          };
          b._engine.path = function (a, b, c) {
            var e = N("path");
            a = new L(e, a, c);
            a.type = "path";
            k(a, b);
            H(a, b);
            return a
          };
          b._engine.group = function (a, b, c) {
            var e = N("g");
            a = new L(e, a, c);
            a.type = "group";
            a.canvas = a.node;
            a.top = a.bottom = null;
            a._id = b || "";
            b && e.setAttribute("class", "raphael-group-" + a.id + "-" + b);
            return a
          };
          b._engine.circle = function (a, b, c) {
            var e =
              N("circle");
            a = new L(e, a, c);
            a.type = "circle";
            k(a, b);
            H(a, b);
            return a
          };
          b._engine.rect = function (a, b, c) {
            var e = N("rect");
            a = new L(e, a, c);
            a.type = "rect";
            b.rx = b.ry = b.r;
            k(a, b);
            H(a, b);
            return a
          };
          b._engine.ellipse = function (a, b, c) {
            var e = N("ellipse");
            a = new L(e, a, c);
            a.type = "ellipse";
            k(a, b);
            H(a, b);
            return a
          };
          b._engine.image = function (a, b, c) {
            var e = N("image");
            a = new L(e, a, c);
            a.type = "image";
            e.setAttribute("preserveAspectRatio", "none");
            k(a, b);
            H(a, b);
            return a
          };
          b._engine.text = function (a, b, c) {
            var e = N("text");
            a = new L(e, a, c);
            a.type =
              "text";
            a._textdirty = !0;
            k(a, b);
            H(a, b);
            return a
          };
          b._engine.setSize = function (a, b) {
            this.width = a || this.width;
            this.height = b || this.height;
            this.canvas.setAttribute("width", this.width);
            this.canvas.setAttribute("height", this.height);
            this._viewBox && this.setViewBox.apply(this, this._viewBox);
            return this
          };
          b._engine.create = function () {
            var a = b._getContainer.apply(0, arguments), c = a && a.container, e = a.x, r = a.y, h = a.width, a = a.height;
            if (!c)throw Error("SVG container not found.");
            var w = N("svg"), v, e = e || 0, r = r || 0, h = h || 512, a = a ||
              342;
            N(w, {height: a, version: 1.1, width: h, xmlns: "http://www.w3.org/2000/svg"});
            1 == c ? (w.style.cssText = "overflow:hidden;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-user-select:none;-moz-user-select:-moz-none;-khtml-user-select:none;-ms-user-select:none;user-select:none;-o-user-select:none;cursor:default;position:absolute;left:" + e + "px;top:" + r + "px", b._g.doc.body.appendChild(w), v = 1) : (w.style.cssText = "overflow:hidden;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-user-select:none;-moz-user-select:-moz-none;-khtml-user-select:none;-ms-user-select:none;user-select:none;-o-user-select:none;cursor:default;position:relative",
              c.firstChild ? c.insertBefore(w, c.firstChild) : c.appendChild(w));
            c = new b._Paper;
            c.width = h;
            c.height = a;
            c.canvas = w;
            N(w, {id: "raphael-paper-" + c.id});
            c.clear();
            c._left = c._top = 0;
            v && (c.renderfix = function () {
            });
            c.renderfix();
            return c
          };
          b._engine.setViewBox = function (a, b, c, e, r) {
            g("raphael.setViewBox", this, this._viewBox, [a, b, c, e, r]);
            var w = h(c / this.width, e / this.height), v = this.top, D = r ? "meet" : "xMinYMin", d;
            null == a ? (this._vbSize && (w = 1), delete this._vbSize, d = "0 0 " + this.width + " " + this.height) : (this._vbSize = w, d = a + " " + b +
            " " + c + " " + e);
            for (N(this.canvas, {
              viewBox: d,
              preserveAspectRatio: D
            }); w && v;)D = "stroke-width"in v.attrs ? v.attrs["stroke-width"] : 1, v.attr({"stroke-width": D}), v._.dirty = 1, v._.dirtyT = 1, v = v.prev;
            this._viewBox = [a, b, c, e, !!r];
            return this
          };
          b.prototype.renderfix = function () {
            var a = this.canvas, b = a.style, c;
            try {
              c = a.getScreenCTM() || a.createSVGMatrix()
            } catch (e) {
              c = a.createSVGMatrix()
            }
            a = -c.e % 1;
            c = -c.f % 1;
            if (a || c)a && (this._left = (this._left + a) % 1, b.left = this._left + "px"), c && (this._top = (this._top + c) % 1, b.top = this._top + "px")
          };
          b.prototype._desc =
            function (a) {
              var c = this.desc;
              if (c)for (; c.firstChild;)c.removeChild(c.firstChild); else this.desc = c = N("desc"), this.canvas.appendChild(c);
              c.appendChild(b._g.doc.createTextNode(b.is(a, "string") ? a : "Created with Red Raphaël " + b.version))
            };
          b.prototype.clear = function () {
            var a;
            for (g("raphael.clear", this); a = this.bottom;)a.remove();
            for (a = this.canvas; a.firstChild;)a.removeChild(a.firstChild);
            this.bottom = this.top = null;
            a.appendChild(this.desc = N("desc"));
            a.appendChild(this.defs = N("defs"))
          };
          b.prototype.remove = function () {
            var a;
            for (g("raphael.remove", this); a = this.bottom;)a.remove();
            this.defs && this.defs.parentNode.removeChild(this.defs);
            this.desc && this.desc.parentNode.removeChild(this.desc);
            this.canvas.parentNode && this.canvas.parentNode.removeChild(this.canvas);
            for (a in this)this[a] = "function" == typeof this[a] ? b._removedFactory(a) : null;
            this.removed = !0
          };
          var O = b.st, Wa;
          for (Wa in z)z.hasOwnProperty(Wa) && !O.hasOwnProperty(Wa) && (O[Wa] = function (a) {
            return function () {
              var b = arguments;
              return this.forEach(function (c) {
                c[a].apply(c, b)
              })
            }
          }(Wa))
        }
      })();
      (function () {
        if (b.vml) {
          var a = String, c = parseFloat, e = Math, r = e.round, h = e.max, w = e.min, v = e.sqrt, D = e.abs, d = /[, ]+/, f = b.eve, g = {
            M: "m",
            L: "l",
            C: "c",
            Z: "x",
            m: "t",
            l: "r",
            c: "v",
            z: "x"
          }, p = /([clmz]),?([^clmz]*)/gi, m = / progid:\S+Blur\([^\)]+\)/g, N = /-?[^,\s-]+/g, I = {
            path: 1,
            rect: 1,
            image: 1
          }, l = {circle: 1, ellipse: 1}, W = function (c) {
            var e = /[ahqstv]/ig, h = b._pathToAbsolute;
            a(c).match(e) && (h = b._path2curve);
            e = /[clmz]/g;
            if (h == b._pathToAbsolute && !a(c).match(e))return (c = a(c).replace(p, function (a, b, c) {
                var e = [], h = "m" == b.toLowerCase(),
                  w = g[b];
                c.replace(N, function (a) {
                  h && 2 == e.length && (w += e + g["m" == b ? "l" : "L"], e = []);
                  e.push(r(21600 * a))
                });
                return w + e
              })) || "m0,0";
            var e = h(c), w;
            c = [];
            for (var v = 0, D = e.length; v < D; v++) {
              h = e[v];
              w = e[v][0].toLowerCase();
              "z" == w && (w = "x");
              for (var d = 1, f = h.length; d < f; d++)w += r(21600 * h[d]) + (d != f - 1 ? "," : "");
              c.push(w)
            }
            return c.length ? c.join(" ") : "m0,0"
          }, n = function (a, c, e) {
            var r = b.matrix();
            r.rotate(-a, .5, .5);
            return {dx: r.x(c, e), dy: r.y(c, e)}
          }, ja = function (a, b, c, e, r, h) {
            var w = a._, v = a.matrix, d = w.fillpos;
            a = a.node;
            var f = a.style, g = 1, p =
              "", m = 21600 / b, N = 21600 / c;
            f.visibility = "hidden";
            if (b && c) {
              a.coordsize = D(m) + " " + D(N);
              f.rotation = h * (0 > b * c ? -1 : 1);
              h && (r = n(h, e, r), e = r.dx, r = r.dy);
              0 > b && (p += "x");
              0 > c && (p += " y") && (g = -1);
              f.flip = p;
              a.coordorigin = e * -m + " " + r * -N;
              if (d || w.fillsize)if (e = (e = a.getElementsByTagName("fill")) && e[0])a.removeChild(e), d && (r = n(h, v.x(d[0], d[1]), v.y(d[0], d[1])), e.position = r.dx * g + " " + r.dy * g), w.fillsize && (e.size = w.fillsize[0] * D(b) + " " + w.fillsize[1] * D(c)), a.appendChild(e);
              f.visibility = "visible"
            }
          };
          b._url = "";
          b.toString = function () {
            return "Your browser doesn’t support SVG. Falling down to VML.\nYou are running Raphaël " +
              this.version
          };
          var F = function (b, c, e) {
            c = a(c).toLowerCase().split("-");
            e = e ? "end" : "start";
            for (var r = c.length, h = "classic", w = "medium", v = "medium"; r--;)switch (c[r]) {
              case "block":
              case "classic":
              case "oval":
              case "diamond":
              case "open":
              case "none":
                h = c[r];
                break;
              case "wide":
              case "narrow":
                v = c[r];
                break;
              case "long":
              case "short":
                w = c[r]
            }
            b = b.node.getElementsByTagName("stroke")[0];
            b[e + "arrow"] = h;
            b[e + "arrowlength"] = w;
            b[e + "arrowwidth"] = v
          }, J = function (a, b) {
            for (var c in b)f("raphael.attr." + c + "." + a.id, a, b[c], c), a.ca[c] && a.attr(c,
              b[c])
          }, H = b._setFillAndStroke = function (e, v) {
            if (e.paper.canvas) {
              e.attrs = e.attrs || {};
              var D = e.node, f = e.attrs, g = D.style, p = I[e.type] && (v.x != f.x || v.y != f.y || v.width != f.width || v.height != f.height || v.cx != f.cx || v.cy != f.cy || v.rx != f.rx || v.ry != f.ry || v.r != f.r), m = l[e.type] && (f.cx != v.cx || f.cy != v.cy || f.r != v.r || f.rx != v.rx || f.ry != v.ry), N = "group" === e.type, n;
              for (n in v)v.hasOwnProperty(n) && (f[n] = v[n]);
              p && (f.path = b._getPath[e.type](e), e._.dirty = 1);
              v.href && (D.href = v.href);
              v.title && (D.title = v.title);
              v.target && (D.target = v.target);
              v.cursor && (g.cursor = v.cursor);
              "blur"in v && e.blur(v.blur);
              if (v.path && "path" == e.type || p)D.path = W(~a(f.path).toLowerCase().indexOf("r") ? b._pathToAbsolute(f.path) : f.path), "image" == e.type && (e._.fillpos = [f.x, f.y], e._.fillsize = [f.width, f.height], ja(e, 1, 1, 0, 0, 0));
              "transform"in v && e.transform(v.transform);
              "rotation"in v && (g = v.rotation, b.is(g, "array") ? e.rotate.apply(e, g) : e.rotate(g));
              "visibility"in v && ("hidden" === v.visibility ? e.hide() : e.show());
              m && (g = +f.cx, m = +f.cy, p = +f.rx || +f.r || 0, n = +f.ry || +f.r || 0, D.path = b.format("ar{0},{1},{2},{3},{4},{1},{4},{1}x",
                r(21600 * (g - p)), r(21600 * (m - n)), r(21600 * (g + p)), r(21600 * (m + n)), r(21600 * g)));
              "clip-rect"in v && (g = a(v["clip-rect"]).split(d), 4 == g.length && (g[0] = +g[0], g[1] = +g[1], g[2] = +g[2] + g[0], g[3] = +g[3] + g[1], p = N ? D : D.clipRect || b._g.doc.createElement("div"), m = p.style, N ? (e.clip = g.slice(), p = e.matrix.offset(), p = [c(p[0]), c(p[1])], g[0] -= p[0], g[1] -= p[1], g[2] -= p[0], g[3] -= p[1], m.width = "1px", m.height = "1px") : D.clipRect || (m.top = "0", m.left = "0", m.width = e.paper.width + "px", m.height = e.paper.height + "px", D.parentNode.insertBefore(p, D),
                p.appendChild(D), p.raphael = !0, p.raphaelid = D.raphaelid, D.clipRect = p), m.position = "absolute", m.clip = b.format("rect({1}px {2}px {3}px {0}px)", g)), v["clip-rect"] || (N && e.clip ? (D.style.clip = "rect(auto auto auto auto)", delete e.clip) : D.clipRect && (D.clipRect.style.clip = "rect(auto auto auto auto)")));
              e.textpath && (N = e.textpath.style, v.font && (N.font = v.font), v["font-family"] && (N.fontFamily = '"' + v["font-family"].split(",")[0].replace(/^['"]+|['"]+$/g, "") + '"'), v["font-size"] && (N.fontSize = v["font-size"]), v["font-weight"] &&
              (N.fontWeight = v["font-weight"]), v["font-style"] && (N.fontStyle = v["font-style"]));
              "arrow-start"in v && F(e, v["arrow-start"]);
              "arrow-end"in v && F(e, v["arrow-end"], 1);
              if (null != v.opacity || null != v["stroke-width"] || null != v.fill || null != v.src || null != v.stroke || null != v["stroke-width"] || null != v["stroke-opacity"] || null != v["fill-opacity"] || null != v["stroke-dasharray"] || null != v["stroke-miterlimit"] || null != v["stroke-linejoin"] || null != v["stroke-linecap"]) {
                N = D.getElementsByTagName("fill");
                g = -1;
                N = N && N[0];
                !N && (N = s("fill"));
                "image" == e.type && v.src && (N.src = v.src);
                v.fill && (N.on = !0);
                if (null == N.on || "none" == v.fill || null === v.fill)N.on = !1;
                N.on && v.fill && ((m = a(v.fill).match(b._ISURL)) ? (N.parentNode == D && D.removeChild(N), N.rotate = !0, N.src = m[1], N.type = "tile", p = e.getBBox(1), N.position = p.x + " " + p.y, e._.fillpos = [p.x, p.y], b._preload(m[1], function () {
                  e._.fillsize = [this.offsetWidth, this.offsetHeight]
                })) : (m = b.getRGB(v.fill), N.color = m.hex, N.src = "", N.type = "solid", m.error && (e.type in{
                  circle: 1,
                  ellipse: 1
                } || "r" != a(v.fill).charAt()) && M(e, v.fill,
                  N) ? (f.fill = "none", f.gradient = v.fill, N.rotate = !1) : "opacity"in m && !("fill-opacity"in v) && (g = m.opacity)));
                if (-1 !== g || "fill-opacity"in v || "opacity"in v)m = ((+f["fill-opacity"] + 1 || 2) - 1) * ((+f.opacity + 1 || 2) - 1) * ((+g + 1 || 2) - 1), m = w(h(m, 0), 1), N.opacity = m, N.src && (N.color = "none");
                D.appendChild(N);
                N = D.getElementsByTagName("stroke") && D.getElementsByTagName("stroke")[0];
                g = !1;
                !N && (g = N = s("stroke"));
                if (v.stroke && "none" != v.stroke || v["stroke-width"] || null != v["stroke-opacity"] || v["stroke-dasharray"] || v["stroke-miterlimit"] ||
                  v["stroke-linejoin"] || v["stroke-linecap"])N.on = !0;
                "none" != v.stroke && null !== v.stroke && null != N.on && 0 != v.stroke && 0 != v["stroke-width"] || (N.on = !1);
                m = b.getRGB("stroke"in v ? v.stroke : f.stroke);
                N.on && v.stroke && (N.color = m.hex);
                m = ((+f["stroke-opacity"] + 1 || 2) - 1) * ((+f.opacity + 1 || 2) - 1) * ((+m.opacity + 1 || 2) - 1);
                p = .75 * (c(v["stroke-width"]) || 1);
                m = w(h(m, 0), 1);
                null == v["stroke-width"] && (p = f["stroke-width"]);
                v["stroke-width"] && (N.weight = p);
                p && 1 > p && (m *= p) && (N.weight = 1);
                N.opacity = m;
                v["stroke-linejoin"] && (N.joinstyle = v["stroke-linejoin"]) ||
                g && (g.joinstyle = "miter");
                N.miterlimit = v["stroke-miterlimit"] || 8;
                v["stroke-linecap"] && (N.endcap = "butt" == v["stroke-linecap"] ? "flat" : "square" == v["stroke-linecap"] ? "square" : "round");
                v["stroke-dasharray"] && (m = {
                  "-": "shortdash",
                  ".": "shortdot",
                  "-.": "shortdashdot",
                  "-..": "shortdashdotdot",
                  ". ": "dot",
                  "- ": "dash",
                  "--": "longdash",
                  "- .": "dashdot",
                  "--.": "longdashdot",
                  "--..": "longdashdotdot"
                }, N.dashstyle = m.hasOwnProperty(v["stroke-dasharray"]) ? m[v["stroke-dasharray"]] : v["stroke-dasharray"].join && v["stroke-dasharray"].join(" ") ||
                "");
                g && D.appendChild(N)
              }
              if ("text" == e.type) {
                e.paper.canvas.style.display = "";
                D = e.paper.span;
                N = f.font && f.font.match(/\d+(?:\.\d*)?(?=px)/);
                m = f["line-height"] && (f["line-height"] + "").match(/\d+(?:\.\d*)?(?=px)/);
                g = D.style;
                f.font && (g.font = f.font);
                f["font-family"] && (g.fontFamily = f["font-family"]);
                f["font-weight"] && (g.fontWeight = f["font-weight"]);
                f["font-style"] && (g.fontStyle = f["font-style"]);
                N = c(f["font-size"] || N && N[0]) || 10;
                g.fontSize = 100 * N + "px";
                m = c(f["line-height"] || m && m[0]) || 12;
                f["line-height"] && (g.lineHeight =
                  100 * m + "px");
                b.is(v.text, "array") && (v.text = e.textpath.string = v.text.join("\n").replace(/<br\s*?\/?>/ig, "\n"));
                e.textpath.string && (D.innerHTML = a(e.textpath.string).replace(/</g, "&#60;").replace(/&/g, "&#38;").replace(/\n/g, "<br>"));
                D = D.getBoundingClientRect();
                e.W = f.w = (D.right - D.left) / 100;
                e.H = f.h = (D.bottom - D.top) / 100;
                e.X = f.x;
                e.Y = f.y;
                switch (f["vertical-align"]) {
                  case "top":
                    e.bby = e.H / 2;
                    break;
                  case "bottom":
                    e.bby = -e.H / 2;
                    break;
                  default:
                    e.bby = 0
                }
                ("x"in v || "y"in v || void 0 !== e.bby) && (e.path.v = b.format("m{0},{1}l{2},{1}",
                  r(21600 * f.x), r(21600 * (f.y + (e.bby || 0))), r(21600 * f.x) + 1));
                D = "x y text font font-family font-weight font-style font-size line-height".split(" ");
                N = 0;
                for (g = D.length; N < g; N++)if (D[N]in v) {
                  e._.dirty = 1;
                  break
                }
                switch (f["text-anchor"]) {
                  case "start":
                    e.textpath.style["v-text-align"] = "left";
                    e.bbx = e.W / 2;
                    break;
                  case "end":
                    e.textpath.style["v-text-align"] = "right";
                    e.bbx = -e.W / 2;
                    break;
                  default:
                    e.textpath.style["v-text-align"] = "center", e.bbx = 0
                }
                e.textpath.style["v-text-kern"] = !0
              }
            }
          }, M = function (e, r, h) {
            e.attrs = e.attrs || {};
            var w = Math.pow, D = "linear", f = ".5 .5";
            e.attrs.gradient = r;
            r = a(r).replace(b._radial_gradient, function (a, b) {
              D = "radial";
              b = b && b.split(",") || [];
              var e = b[3], r = b[4];
              e && r && (e = c(e), r = c(r), .25 < w(e - .5, 2) + w(r - .5, 2) && (r = v(.25 - w(e - .5, 2)) * (2 * (.5 < r) - 1) + .5), f = e + " " + r);
              return ""
            });
            r = r.split(/\s*\-\s*/);
            if ("linear" == D) {
              var d = r.shift(), d = -c(d);
              if (isNaN(d))return null
            }
            r = b._parseDots(r);
            if (!r)return null;
            e = e.shape || e.node;
            if (r.length) {
              h.parentNode == e && e.removeChild(h);
              h.on = !0;
              h.method = "none";
              h.color = r[0].color;
              h.color2 = r[r.length -
              1].color;
              for (var g = [], p = 1, m = void 0 === r[0].opacity ? 1 : r[0].opacity, N = 0, I = r.length; N < I; N++)r[N].offset && g.push(r[N].offset + " " + r[N].color), void 0 !== r[N].opacity && (p = r[N].opacity);
              h.colors = g.length ? g.join() : "0% " + h.color;
              h.opacity = p;
              h["o:opacity2"] = m;
              "radial" == D ? (h.type = "gradientTitle", h.focus = "100%", h.focussize = "0 0", h.focusposition = f, h.angle = 0) : (h.type = "gradient", h.angle = (270 - d) % 360);
              e.appendChild(h)
            }
            return 1
          }, k = function (a, c, e) {
            e = e || c;
            var r;
            e.canvas && e.canvas.appendChild(a);
            r = s("skew");
            r.on = !0;
            a.appendChild(r);
            this.skew = r;
            this.node = this[0] = a;
            a.raphael = !0;
            a.raphaelid = this.id = b._oid++;
            this.Y = this.X = 0;
            this.attrs = this.attrs || {};
            this.followers = this.followers || [];
            this.paper = c;
            this.ca = this.customAttributes = this.customAttributes || new c._CustomAttributes;
            this.matrix = b.matrix();
            this._ = {transform: [], sx: 1, sy: 1, dx: 0, dy: 0, deg: 0, dirty: 1, dirtyT: 1};
            this.parent = e;
            !e.bottom && (e.bottom = this);
            (this.prev = e.top) && (e.top.next = this);
            e.top = this;
            this.next = null
          }, e = b.el;
          k.prototype = e;
          e.constructor = k;
          e.transform = function (c) {
            if (null ==
              c)return this._.transform;
            var e = this.paper._viewBoxShift, r = e ? "s" + [e.scale, e.scale] + "-1-1t" + [e.dx, e.dy] : "", h;
            e && (h = c = a(c).replace(/\.{3}|\u2026/g, this._.transform || ""));
            b._extractTransform(this, r + c);
            var e = this.matrix.clone(), w = this.skew;
            c = this.node;
            var r = ~a(this.attrs.fill).indexOf("-"), v = !a(this.attrs.fill).indexOf("url(");
            e.translate(-.5, -.5);
            v || r || "image" == this.type ? (w.matrix = "1 0 0 1", w.offset = "0 0", w = e.split(), r && w.noRotation || !w.isSimple ? (c.style.filter = e.toFilter(), e = this.getBBox(), r = this.getBBox(1),
              v = e.x2 && r.x2 && "x2" || "x", w = e.y2 && r.y2 && "y2" || "y", v = e[v] - r[v], e = e[w] - r[w], c.coordorigin = -21600 * v + " " + -21600 * e, ja(this, 1, 1, v, e, 0)) : (c.style.filter = "", ja(this, w.scalex, w.scaley, w.dx, w.dy, w.rotate))) : (c.style.filter = "", w.matrix = a(e), w.offset = e.offset());
            h && (this._.transform = h);
            return this
          };
          e.rotate = function (b, e, r) {
            if (this.removed)return this;
            if (null != b) {
              b = a(b).split(d);
              b.length - 1 && (e = c(b[1]), r = c(b[2]));
              b = c(b[0]);
              null == r && (e = r);
              if (null == e || null == r)r = this.getBBox(1), e = r.x + r.width / 2, r = r.y + r.height / 2;
              this._.dirtyT =
                1;
              this.transform(this._.transform.concat([["r", b, e, r]]));
              return this
            }
          };
          e.translate = function (b, e) {
            if (this.removed)return this;
            b = a(b).split(d);
            b.length - 1 && (e = c(b[1]));
            b = c(b[0]) || 0;
            e = +e || 0;
            this._.bbox && (this._.bbox.x += b, this._.bbox.y += e);
            this.transform(this._.transform.concat([["t", b, e]]));
            return this
          };
          e.scale = function (b, e, r, h) {
            if (this.removed)return this;
            b = a(b).split(d);
            b.length - 1 && (e = c(b[1]), r = c(b[2]), h = c(b[3]), isNaN(r) && (r = null), isNaN(h) && (h = null));
            b = c(b[0]);
            null == e && (e = b);
            null == h && (r = h);
            if (null ==
              r || null == h)var w = this.getBBox(1);
            r = null == r ? w.x + w.width / 2 : r;
            h = null == h ? w.y + w.height / 2 : h;
            this.transform(this._.transform.concat([["s", b, e, r, h]]));
            this._.dirtyT = 1;
            return this
          };
          e.hide = function (a) {
            !this.removed && (this.node.style.display = "none");
            return this
          };
          e.show = function (a) {
            !this.removed && (this.node.style.display = "");
            return this
          };
          e._getBBox = function () {
            return this.removed ? {} : {
              x: this.X + (this.bbx || 0) - this.W / 2,
              y: this.Y + (this.bby || 0) - this.H / 2,
              width: this.W,
              height: this.H
            }
          };
          e.remove = function () {
            if (!this.removed &&
              this.parent.canvas) {
              var a = b._engine.getNode(this), c = this.paper, e = this.shape;
              c.__set__ && c.__set__.exclude(this);
              f.unbind("raphael.*.*." + this.id);
              e && e.parentNode.removeChild(e);
              for (a.parentNode && a.parentNode.removeChild(a); a = this.followers.pop();)a.el.remove();
              for (; a = this.bottom;)a.remove();
              this._drag && this.undrag();
              if (this.events)for (; a = this.events.pop();)a.unbind();
              this.removeData();
              delete c._elementsById[this.id];
              b._tear(this, this.parent);
              for (a in this)this[a] = "function" === typeof this[a] ? b._removedFactory(a) :
                null;
              this.removed = !0
            }
          };
          e.attr = function (a, c) {
            if (this.removed)return this;
            if (null == a) {
              var e = {}, r;
              for (r in this.attrs)this.attrs.hasOwnProperty(r) && (e[r] = this.attrs[r]);
              e.gradient && "none" == e.fill && (e.fill = e.gradient) && delete e.gradient;
              e.transform = this._.transform;
              e.visibility = "none" === this.node.style.display ? "hidden" : "visible";
              return e
            }
            if (null == c && b.is(a, "string")) {
              if ("fill" == a && "none" == this.attrs.fill && this.attrs.gradient)return this.attrs.gradient;
              if ("visibility" == a)return "none" === this.node.style.display ?
                "hidden" : "visible";
              var e = a.split(d), h = {}, w = 0;
              for (r = e.length; w < r; w++)a = e[w], a in this.attrs ? h[a] = this.attrs[a] : b.is(this.ca[a], "function") ? h[a] = this.ca[a].def : h[a] = b._availableAttrs[a];
              return r - 1 ? h : h[e[0]]
            }
            if (this.attrs && null == c && b.is(a, "array")) {
              h = {};
              w = 0;
              for (r = a.length; w < r; w++)h[a[w]] = this.attr(a[w]);
              return h
            }
            null != c && (e = {}, e[a] = c);
            null == c && b.is(a, "object") && (e = a);
            for (w in e)f("raphael.attr." + w + "." + this.id, this, e[w], w);
            if (e) {
              var v = {};
              for (w in this.ca)if (this.ca[w] && e.hasOwnProperty(w) && b.is(this.ca[w],
                  "function") && !this.ca["_invoked" + w]) {
                this.ca["_invoked" + w] = !0;
                r = this.ca[w].apply(this, [].concat(e[w]));
                delete this.ca["_invoked" + w];
                for (h in r)r.hasOwnProperty(h) && (e[h] = r[h]);
                this.attrs[w] = e[w];
                !1 === r && (v[w] = e[w], delete e[w])
              }
              "text"in e && "text" == this.type && (b.is(e.text, "array") && (e.text = e.text.join("\n")), this.textpath.string = e.text.replace(/<br\s*?\/?>/ig, "\n"));
              H(this, e);
              var D, w = 0;
              for (r = this.followers.length; w < r; w++)D = this.followers[w], D.cb && !D.cb.call(D.el, e, this) || D.el.attr(e);
              for (h in v)e[h] =
                v[h]
            }
            return this
          };
          e.blur = function (a) {
            var c = this.node.runtimeStyle, e = c.filter, e = e.replace(m, "");
            0 !== +a ? (this.attrs.blur = a, c.filter = e + "  progid:DXImageTransform.Microsoft.Blur(pixelradius=" + (+a || 1.5) + ")", c.margin = b.format("-{0}px 0 0 -{0}px", r(+a || 1.5))) : (c.filter = e, c.margin = 0, delete this.attrs.blur);
            return this
          };
          e.on = function (a, c) {
            if (this.removed)return this;
            this.node["on" + a] = function () {
              var a = b._g.win.event;
              a.target = a.srcElement;
              c(a)
            };
            return this
          };
          b._engine.getNode = function (a) {
            a = a.node || a[0].node;
            return a.clipRect || a
          };
          b._engine.getLastNode = function (a) {
            a = a.node || a[a.length - 1].node;
            return a.clipRect || a
          };
          b._engine.group = function (a, c, e) {
            var r = b._g.doc.createElement("div"), h = new k(r, a, e);
            r.style.cssText = "position:absolute;left:0;top:0;width:1px;height:1px";
            h._id = c || "";
            c && (r.className = "raphael-group-" + h.id + "-" + c);
            (e || a).canvas.appendChild(r);
            h.type = "group";
            h.canvas = h.node;
            h.transform = b._engine.group.transform;
            h.top = null;
            h.bottom = null;
            return h
          };
          b._engine.group.transform = function (e) {
            if (null == e)return this._.transform;
            var r = this.node.style, h = this.clip, w = this.paper._viewBoxShift, v = w ? "s" + [w.scale, w.scale] + "-1-1t" + [w.dx, w.dy] : "";
            w && (e = a(e).replace(/\.{3}|\u2026/g, this._.transform || ""));
            b._extractTransform(this, v + e);
            e = this.matrix;
            v = e.offset();
            w = c(v[0]) || 0;
            v = c(v[1]) || 0;
            r.left = w + "px";
            r.top = v + "px";
            r.zoom = (this._.tzoom = e.get(0)) + "";
            h && (r.clip = b.format("rect({1}px {2}px {3}px {0}px)", [h[0] - w, h[1] - v, h[2] - w, h[3] - v]));
            return this
          };
          b._engine.path = function (a, b, c) {
            var e = s("shape");
            e.style.cssText = "position:absolute;left:0;top:0;width:1px;height:1px";
            e.coordsize = "21600 21600";
            e.coordorigin = a.coordorigin;
            a = new k(e, a, c);
            a.type = b.type || "path";
            a.path = [];
            a.Path = "";
            b.type && delete b.type;
            H(a, b);
            J(a, b);
            return a
          };
          b._engine.rect = function (a, c, e) {
            var r = b._rectPath(c.x, c.y, c.w, c.h, c.r);
            c.path = r;
            c.type = "rect";
            a = a.path(c, e);
            c = a.attrs;
            a.X = c.x;
            a.Y = c.y;
            a.W = c.width;
            a.H = c.height;
            c.path = r;
            return a
          };
          b._engine.ellipse = function (a, b, c) {
            b.type = "ellipse";
            a = a.path(b, c);
            b = a.attrs;
            a.X = b.x - b.rx;
            a.Y = b.y - b.ry;
            a.W = 2 * b.rx;
            a.H = 2 * b.ry;
            return a
          };
          b._engine.circle = function (a, b, c) {
            b.type =
              "circle";
            a = a.path(b, c);
            b = a.attrs;
            a.X = b.x - b.r;
            a.Y = b.y - b.r;
            a.W = a.H = 2 * b.r;
            return a
          };
          b._engine.image = function (a, c, e) {
            var r = b._rectPath(c.x, c.y, c.w, c.h);
            c.path = r;
            c.type = "image";
            c.stroke = "none";
            a = a.path(c, e);
            e = a.attrs;
            var r = a.node, h = r.getElementsByTagName("fill")[0];
            e.src = c.src;
            a.X = e.x = c.x;
            a.Y = e.y = c.y;
            a.W = e.width = c.w;
            a.H = e.height = c.h;
            h.parentNode == r && r.removeChild(h);
            h.rotate = !0;
            h.src = e.src;
            h.type = "tile";
            a._.fillpos = [e.x, e.y];
            a._.fillsize = [e.w, e.h];
            r.appendChild(h);
            ja(a, 1, 1, 0, 0, 0);
            return a
          };
          b._engine.text =
            function (c, e, h) {
              var w = s("shape"), v = s("path"), D = s("textpath");
              x = e.x || 0;
              y = e.y || 0;
              text = e.text;
              v.v = b.format("m{0},{1}l{2},{1}", r(21600 * e.x), r(21600 * e.y), r(21600 * e.x) + 1);
              v.textpathok = !0;
              D.string = a(e.text).replace(/<br\s*?\/?>/ig, "\n");
              D.on = !0;
              w.style.cssText = "position:absolute;left:0;top:0;width:1px;height:1px";
              w.coordsize = "21600 21600";
              w.coordorigin = "0 0";
              c = new k(w, c, h);
              c.shape = w;
              c.path = v;
              c.textpath = D;
              c.type = "text";
              c.attrs.text = a(e.text || "");
              c.attrs.x = e.x;
              c.attrs.y = e.y;
              c.attrs.w = 1;
              c.attrs.h = 1;
              H(c, e);
              J(c, e);
              w.appendChild(D);
              w.appendChild(v);
              return c
            };
          b._engine.setSize = function (a, c) {
            var e = this.canvas.style;
            this.width = a;
            this.height = c;
            a == +a && (a += "px");
            c == +c && (c += "px");
            e.width = a;
            e.height = c;
            e.clip = "rect(0 " + a + " " + c + " 0)";
            this._viewBox && b._engine.setViewBox.apply(this, this._viewBox);
            return this
          };
          b._engine.setViewBox = function (a, b, c, e, r) {
            f("raphael.setViewBox", this, this._viewBox, [a, b, c, e, r]);
            var w = this.width, v = this.height, D = 1 / h(c / w, e / v), d, g;
            r && (d = v / e, g = w / c, c * d < w && (a -= (w - c * d) / 2 / d), e * g < v && (b -= (v - e * g) / 2 /
            g));
            this._viewBox = [a, b, c, e, !!r];
            this._viewBoxShift = {dx: -a, dy: -b, scale: D};
            this.forEach(function (a) {
              a.transform("...")
            });
            return this
          };
          var s;
          b._engine.initWin = function (c) {
            var e = c.document;
            e.createStyleSheet().addRule(".rvml", "behavior:url(#default#VML)");
            try {
              !e.namespaces.rvml && e.namespaces.add("rvml", "urn:schemas-microsoft-com:vml"), s = b._createNode = function (b, c) {
                var r = e.createElement("<rvml:" + b + ' class="rvml">'), h;
                for (h in c)r[h] = a(c[h]);
                return r
              }
            } catch (r) {
              s = b._createNode = function (b, c) {
                var r = e.createElement("<" +
                b + ' xmlns="urn:schemas-microsoft.com:vml" class="rvml">'), h;
                for (h in c)r[h] = a(c[h]);
                return r
              }
            }
          };
          b._engine.initWin(b._g.win);
          b._engine.create = function () {
            var a = b._getContainer.apply(0, arguments), c = a.container, e = a.height, r = a.width, h = a.x, a = a.y;
            if (!c)throw Error("VML container not found.");
            var w = new b._Paper, v = w.canvas = b._g.doc.createElement("div"), D = v.style, h = h || 0, a = a || 0, r = r || 512, e = e || 342;
            w.width = r;
            w.height = e;
            r == +r && (r += "px");
            e == +e && (e += "px");
            w.coordsize = "21600000 21600000";
            w.coordorigin = "0 0";
            v.id = "raphael-paper-" +
            w.id;
            w.span = b._g.doc.createElement("span");
            w.span.style.cssText = "position:absolute;left:-9999em;top:-9999em;padding:0;margin:0;line-height:1;";
            v.appendChild(w.span);
            D.cssText = b.format("top:0;left:0;width:{0};height:{1};display:inline-block;cursor:default;position:relative;clip:rect(0 {0} {1} 0);overflow:hidden", r, e);
            1 == c ? (b._g.doc.body.appendChild(v), D.left = h + "px", D.top = a + "px", D.position = "absolute") : c.firstChild ? c.insertBefore(v, c.firstChild) : c.appendChild(v);
            w.renderfix = function () {
            };
            return w
          };
          b.prototype.clear =
            function () {
              var a;
              for (f("raphael.clear", this); a = this.bottom;)a.remove();
              this.canvas.innerHTML = "";
              this.span = b._g.doc.createElement("span");
              this.span.style.cssText = "position:absolute;left:-9999em;top:-9999em;padding:0;margin:0;line-height:1;display:inline;";
              this.canvas.appendChild(this.span);
              this.bottom = this.top = null
            };
          b.prototype.remove = function () {
            var a;
            for (f("raphael.remove", this); a = this.bottom;)a.remove();
            this.canvas.parentNode.removeChild(this.canvas);
            for (a in this)this[a] = "function" == typeof this[a] ?
              b._removedFactory(a) : null;
            return !0
          };
          var L = b.st, z;
          for (z in e)e.hasOwnProperty(z) && !L.hasOwnProperty(z) && (L[z] = function (a) {
            return function () {
              var b = arguments;
              return this.forEach(function (c) {
                c[a].apply(c, b)
              })
            }
          }(z))
        }
      })();
      ka ? u.win.Raphael = b : Raphael = b;
      return b
    }, !0)
  })();
  d.Raphael = B;
  d.Raphael.desc = "";
  k && k !== B ? window.Raphael = k : window.Raphael === B && (window.Raphael = void 0)
}]);
FusionCharts.register("module", ["private", "fusioncharts.redraphael.helper", function () {
  var d = {};
  this.hcLib.Raphael.fn._elementFromEvent = function (k) {
    if (!k || this.removed)return null;
    var B = k.srcElement || k.target || (k = k.originalEvent) && (k.srcElement || k.target) || d;
    "tspan" === B.nodeName && (B = B.parentNode);
    return this.getById(B.raphaelid)
  }
}]);
FusionCharts.register("module", ["private", "fusioncharts.redraphael.css", function () {
  var d = this.hcLib.Raphael, k = d.eve, B = d._g, t = d.fn, E = d.el, b = /[, ]+/, K = /\B([A-Z]{1})/g, G, c;
  G = function (b) {
    this.styleSheet = "";
    this.rules = {};
    this.ns = b || ""
  };
  c = G.prototype;
  c.getSheet = function () {
    var b = this.node;
    this.styleSheet = c.getStyleSheet("fusioncharts-raphael-stylesheet");
    this.styleSheet || (b = this.node = B.doc.createElement("style"), b.setAttribute("id", d.format("raphael-stylesheet-{0}", d._oid++)), b.setAttribute("type", "text/css"),
      b.setAttribute("title", "fusioncharts-raphael-stylesheet"), (B.doc.head || B.doc.getElementsByTagName("head")[0]).appendChild(this.node), this.styleSheet = c.getStyleSheet("fusioncharts-raphael-stylesheet"));
    return this.styleSheet
  };
  c.getStyleSheet = function (b) {
    for (var a = B.doc.styleSheets, c = a.length, d; c--;)if (d = a[c], d.title === b)return d
  };
  c.applyCSSRule = function (b, a) {
    var c = this.styleSheet, d;
    if (!c)if (b && a)c = this.getSheet(); else return;
    d = (c.rules || c.cssRules || {}).length || 0;
    c.insertRule ? c.insertRule(b + "{" + a + "}",
      d) : c.addRule && c.addRule(b, a, d)
  };
  c.removeCSS = function (b) {
    var a = this.styleSheet, c = a.rules || a.cssRules || {}, d = c.length || 0, k;
    for (b = b || this.ns; d--;)k = c[d], (new RegExp(b)).test(k.selectorText) && (a.removeRule ? a.removeRule(d) : a.deleteRule(d))
  };
  c.destroy = function () {
    this.removeCSS();
    delete this.node;
    delete this.styleSheet;
    delete this.ns;
    delete this.rules
  };
  c.clear = function () {
    this.removeCSS();
    this.rules = {}
  };
  c.add = function (b, a) {
    var c = this.rules[b] || (this.rules[b] = {}), d;
    for (d in a)c[d] = a[d]
  };
  c.render = function () {
    this.setCssText()
  };
  c.setCssText = function (b) {
    var a = b ? "" : "\t";
    b = b ? ":" : ": ";
    var c = "", d = "", k, f;
    for (k in this.rules) {
      c = "";
      d = k.replace(/(^|\,)/g, "$1" + this.ns + " ");
      k = this.rules[k];
      for (f in k)k[f] && (c += a + f.replace(K, "-$1").toLowerCase() + b + k[f] + ";");
      this.applyCSSRule(d, c)
    }
  };
  k.on("raphael.new", function () {
    this._stylesheet = this._stylesheet || new G;
    this.cssNamespace("")
  });
  k.on("raphael.remove", function () {
    this._stylesheet && this._stylesheet.destroy();
    delete this._stylesheet
  });
  t.cssNamespace = function (b) {
    arguments.length && (this._stylesheet.ns =
      d.format("{0}#raphael-paper-{1}", b && b + " " || "", this.id));
    return this._stylesheet.ns
  };
  t.cssAddRule = function (b, a) {
    if (1 === arguments.length && "object" === typeof b) {
      for (var c in b)this.cssAddRule(c, b[c]);
      return this
    }
    return this._stylesheet.add(b, a), this
  };
  t.cssRender = function () {
    return d.svg && this._stylesheet.render(), this
  };
  t.cssClear = function () {
    return this._stylesheet.clear(), this
  };
  d._availableAttrs["class"] = "";
  d.svg && k.on("raphael.attr.class", function (b) {
    var a = this.node;
    b = b || "";
    a.setAttribute("class", "group" ===
    this.type && this._id ? "raphael-group-" + this.id + "-" + this._id + " " + b : b)
  });
  d.vml && k.on("raphael.attr.class", function (b) {
    var a = this.paper, c = "." + b, a = a._stylesheet && a._stylesheet.rules, d = this.parent, k = this.attrs, f = {}, g;
    this.node.className = "group" === this.type ? b && this._id + " " + b || this._id : "rvml " + b;
    if (c && a) {
      b = a[c];
      for (g in b)"color" === g && "text" === this.type && (g = "fill"), !k[g] && (f[g] = b[g]);
      for (; d && d.attr;) {
        if (b = d.attr("class"))for (g in c = "." + b + " " + c, b = a[c], b)"color" === g && "text" === this.type && (g = "fill"), k[g] || f[g] ||
        (f[g] = b[g]);
        d = d.parent
      }
      this.css(f)
    }
  });
  E.css = function (c, a) {
    var q, t, C, f;
    if (this.removed)return this;
    this.styles || (this.styles = {});
    if (null == a && d.is(c, "string")) {
      q = c.split(b);
      t = {};
      f = 0;
      for (C = q.length; f < C; f++)c = q[f], c in this.styles && (t[c] = this.styles[c]);
      return C - 1 ? t : t[q[0]]
    }
    if (null == a && d.is(c, "array")) {
      t = {};
      f = 0;
      for (C = c.length; f < C; f++)t[c[f]] = this.styles(c[f]);
      return t
    }
    null != a ? (q = {}, q[c] = a) : null != c && d.is(c, "object") && (q = c);
    t = {};
    for (f in q)C = f.replace(/\B([A-Z]{1})/g, "-$1").toLowerCase(), d._availableAttrs.hasOwnProperty(C) ||
    "color" === C ? ("color" === C && "text" === this.type && (C = "fill"), t[C] = q[f], t.dirty = !0) : (k("raphael.css." + C + "." + this.id, this, q[f], C), this.node.style[C] = q[f], this.styles[C] = q[f]);
    f = 0;
    for (C = this.followers.length; f < C; f++)this.followers[f].el.attr(q);
    t.hasOwnProperty("dirty") && (delete t.dirty, this.attr(t));
    return this
  }
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-raphaelexport", function () {
  var d = this.hcLib, k = d.Raphael, B = d.pluckNumber, t = d.pluck, E = k._availableAttrs, b = /^matrix\(|\)$/g, K = /\,/g, G = /\n|<br\s*?\/?>/ig, c = /[^\d\.]/ig, l = /[\%\(\)\s,\xb0#]/g, a = /group/ig, q = /&/g, S = /"/g, C = /'/g, f = /</g, g = />/g, s = 0;
  (function (d) {
    var k = Math, A = parseFloat, m = k.max, P = k.abs, z = k.pow, X = String, u = /[, ]+/, ka = [{
      reg: /xmlns\=\"http\:\/\/www.w3.org\/2000\/svg\"/ig,
      repStr: ""
    }, {reg: /^.*<svg /, repStr: '<svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" '},
      {reg: /\/svg>.*$/, repStr: "/svg>"}, {reg: /<desc\>[^<]*<\/desc\>/, repStr: ""}, {
        reg: /zIndex="[^"]+"/g,
        repStr: ""
      }, {reg: /url\((\\?[\'\"])[^#]+#/g, repStr: "url($1#"}, {
        reg: / href=/g,
        repStr: " xlink:href="
      }, {reg: /(id|class|width|height)=([^" >]+)/g, repStr: '$1="$2"'}, {
        reg: /:(path|rect)/g,
        repStr: "$1"
      }, {reg: /<ima?ge? ([^\>]+?[^\/])\>/gi, repStr: "<image $1 />"}, {
        reg: /<\/ima?ge?\>/g,
        repStr: ""
      }, {
        reg: /style="([^"]+)"/g, repStr: function (a) {
          return a.toLowerCase()
        }
      }], ha = {
      blur: function () {
      }, transform: function () {
      }, src: function (a,
                        b) {
        b.attrSTR += ' xlink:href="' + b.attrs.src + '"'
      }, path: function (a, b) {
        var c = b.attrs.path, c = d._pathToAbsolute(c || "");
        b.attrSTR += ' d="' + (c.toString && c.toString() || "").replace(K, " ") + '"'
      }, gradient: function (a, b, c) {
        var f = a.attrs.gradient, g = "linear", s, u, q, R = .5, T = .5, h = u = "", J = "", L, U, da, t;
        s = f.replace(l, "_");
        if (!c[s]) {
          f = X(f).replace(d._radial_gradient, function (a, b) {
            var c, h, d, f, m, l, n;
            b = b && b.split(",") || [];
            g = "radial";
            c = b[0];
            h = b[1];
            d = b[2];
            f = b[3];
            m = b[4];
            t = b[5];
            n = c && h;
            d && (da = /\%/.test(d) ? d : A(d));
            if ("userSpaceOnUse" ===
              t)return n && (R = c, T = h), f && m && (L = f, U = m, n || (R = L, T = U)), "";
            n && (R = A(c), T = A(h), c = 2 * (.5 < T) - 1, .25 < (l = z(R - .5, 2)) + z(T - .5, 2) && .25 > l && (T = k.sqrt(.25 - l) * c + .5) && .5 !== T && (T = T.toFixed(5) - 1E-5 * c));
            f && m && (L = A(f), U = A(m), c = 2 * (.5 < U) - 1, .25 < (l = z(L - .5, 2)) + z(U - .5, 2) && .25 > l && (U = k.sqrt(.25 - l) * c + .5) && .5 !== U && (U = U.toFixed(5) - 1E-5 * c), n || (R = L, T = U));
            return ""
          });
          f = f.split(/\s*\-\s*/);
          if ("linear" === g) {
            u = f.shift();
            u = -A(u);
            if (isNaN(u))return null;
            q = [0, 0, k.cos(d.rad(u)), k.sin(d.rad(u))];
            u = 1 / (m(P(q[2]), P(q[3])) || 1);
            q[2] *= u;
            q[3] *= u;
            0 > q[2] &&
            (q[0] = -q[2], q[2] = 0);
            0 > q[3] && (q[1] = -q[3], q[3] = 0)
          }
          f = d._parseDots(f);
          if (!f)return null;
          "radial" === g ? (u = '<radialGradient fx = "' + R + '" fy = "' + T + '" cy = "' + U + '" cx = "' + L + '" r = "' + da + '" gradientUnits = "' + t + '" id = "' + s + '">', h = "</radialGradient>") : (u = '<linearGradient x1 = "' + q[0] + '" y1 = "' + q[1] + '" x2 = "' + q[2] + '" y2 = "' + q[3] + '" gradientTransform ="matrix(' + a.matrix.invert() + ')" id = "' + s + '">', h = "</linearGradient>");
          a = 0;
          for (q = f.length; a < q; a++)J += '<stop offset="' + (f[a].offset ? f[a].offset : a ? "100%" : "0%") +
          '" stop-color="' + (f[a].color || "#fff") + '" stop-opacity="' + (void 0 === f[a].opacity ? 1 : f[a].opacity) + '" />';
          c[s] = !0;
          c.str += u + J + h
        }
        b.attrSTR += " fill=\"url('#" + s + "')\""
      }, fill: function (a, b) {
        var c = b.attrs, f = c.fill, g;
        a.attrs.gradient || (f = d.color(f), g = f.opacity, "text" === a.type ? b.styleSTR += "fill:" + f + "; stroke-opacity:0; " : (b.attrSTR += ' fill="' + f + '"', c["fill-opacity"] || !g && 0 !== g || (b.attrSTR += ' fill-opacity="' + g + '"')))
      }, stroke: function (a, b) {
        var c = b.attrs, f, g;
        f = d.color(c.stroke);
        g = f.opacity;
        "text" !== a.type && (b.attrSTR +=
          ' stroke="' + f + '"', c["stroke-opacity"] || !g && 0 !== g || (b.attrSTR += ' stroke-opacity="' + g + '"'))
      }, "clip-rect": function (a, c, f) {
        var d = X(c.attrs["clip-rect"]), g = d.split(u), d = d.replace(l, "_") + "__" + s++;
        4 === g.length && (f[d] || (f[d] = !0, f.str += '<clipPath id="' + d + '"><rect x="' + g[0] + '" y="' + g[1] + '" width="' + g[2] + '" height="' + g[3] + '" transform="matrix(' + a.matrix.invert().toMatrixString().replace(b, "") + ')"/></clipPath>'), c.attrSTR += ' clip-path="url(#' + d + ')"')
      }, cursor: function (a, b) {
        var c = b.attrs.cursor;
        c && (b.styleSTR +=
          "cursor:" + c + "; ")
      }, font: function (a, b) {
        b.styleSTR += "font:" + b.attrs.font.replace(/\"/ig, " ") + "; "
      }, "font-size": function (a, b) {
        var d = t(b.attrs["font-size"], "10");
        d && d.replace && (d = d.replace(c, ""));
        b.styleSTR += "font-size:" + d + "px; "
      }, "font-weight": function (a, b) {
        b.styleSTR += "font-weight:" + b.attrs["font-weight"] + "; "
      }, "font-family": function (a, b) {
        b.styleSTR += "font-family:" + b.attrs["font-family"] + "; "
      }, "line-height": function () {
      }, "clip-path": function () {
      }, visibility: function () {
      }, "vertical-align": function () {
      },
      "text-anchor": function (a, b) {
        var c = b.attrs["text-anchor"] || "middle";
        "text" === a.type && (b.attrSTR += ' text-anchor="' + c + '"')
      }, title: function () {
      }, text: function (a, b) {
        var d = b.attrs, m = d.text, l = t(d["font-size"], d.font, "10"), n = t(d["line-height"]), k, s, z;
        l && l.replace && (l = l.replace(c, ""));
        l = B(l);
        n && n.replace && (n = n.replace(c, ""));
        n = B(n, l && 1.2 * l);
        k = l ? .85 * l : .75 * n;
        l = d.x;
        s = t(d["vertical-align"], "middle").toLowerCase();
        m = X(m).split(G);
        z = m.length;
        d = 0;
        for (k = "top" === s ? k : "bottom" === s ? k - n * z : k - n * z * .5; d < z; d++)b.textSTR += "<tspan ",
          s = (m[d] || "").replace(q, "&amp;").replace(S, "&quot;").replace(C, "&#39;").replace(f, "&lt;").replace(g, "&gt;"), b.textSTR = d ? b.textSTR + ('dy="' + n + '" x="' + l + '" ') : b.textSTR + ('dy="' + k + '"'), b.textSTR += ">" + s + "</tspan>"
      }
    }, ba = function (c, d) {
      var f = "", g = {
        attrSTR: "",
        styleSTR: "",
        textSTR: "",
        attrs: c.attr()
      }, m = c.isShadow, l = "", n = "", k, s, z = g.attrs;
      if ("none" === c.node.style.display || m)c.next && (f += ba(c.next, d)); else {
        for (k in z)if ("gradient" !== k && (void 0 !== E[k] || ha[k]) && void 0 !== z[k])if (ha[k])ha[k](c, g, d); else g.attrSTR += " " +
        k + '="' + z[k] + '"';
        c.attrs.gradient && ha.gradient(c, g, d);
        "rect" === c.type && z.r && (g.attrSTR += ' rx="' + z.r + '" ry="' + z.r + '"');
        for (s in c.styles)g.styleSTR += s + ":" + c.styles[s] + "; ";
        "image" === c.type && (g.attrSTR += ' preserveAspectRatio="none"');
        if ("text" === c.type && !z["text-anchor"])ha["text-anchor"](c, g);
        c.bottom && (l = ba(c.bottom, d));
        c.next && (n = ba(c.next, d));
        m = c.type;
        m.match(a) && (m = "g");
        f += "<" + m + ' transform="matrix(' + c.matrix.toMatrixString().replace(b, "") + ')" style="' + g.styleSTR + '"' + g.attrSTR + ">" + g.textSTR + l + "</" +
        m + ">" + n
      }
      return f
    };
    d.fn.toSVG = function (a) {
      var b = "", c = {str: ""}, f = 0, g = ka.length, m = "";
      if (d.svg) {
        if (this.canvas && this.canvas.parentNode) {
          for (b = this.canvas.parentNode.innerHTML; f < g; f += 1)c = ka[f], b = b.replace(c.reg, c.repStr);
          this._stylesheet && (b = b.replace(/^(<svg\s[\s\S]*?>)/ig, '$1<style type="text/css">' + this._stylesheet.toString(!0) + "</style>"))
        }
      } else b = '<svg style="overflow: hidden; position: relative;" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="' + this.width + '" version="1.1" height="' +
      this.height + '">', this.bottom && (m = ba(this.bottom, c)), b += "<defs>" + c.str + "</defs>" + m + "</svg>";
      a || (b = b.replace(/<image [^\>]*\>/gi, ""));
      return b
    }
  })(k)
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-raphaelshadow", function () {
  var d = this.window, k = d.Math.sqrt, B = d.parseFloat, t = d.parseInt, d = d.SVGFilterElement || d.SVGFEColorMatrixElement && 2 === d.SVGFEColorMatrixElement.SVG_FECOLORMATRIX_TYPE_SATURATE, E = this.hcLib.Raphael, b = {
    "drop-shadow": "drop-shadow",
    stroke: "stroke",
    fill: "fill",
    "stroke-width": "stroke-width",
    "stroke-opacity": "stroke-opacity",
    "stroke-linecap": "stroke-linecap",
    "stroke-linejoin": "stroke-linejoin",
    "shape-rendering": "shape-rendering",
    transform: "transform"
  }, K = E._createNode, G;
  E.svg ? (d && (E.el.dropshadow = function (b, d, a, q) {
    var t = this.node, C = this._.shadowFilter, f = this.paper.cacheShadows || (this.paper.cacheShadows = {}), g = "drop-shadow" + [b, d, a, q].join(" "), s;
    if ("none" === b) {
      if (C) {
        --C.use;
        this.node.removeAttribute("filter");
        if (!C.use) {
          g = C.hash;
          for (s in C)b = C[s], b.parentNode && b.parentNode.removeChild(b), delete C[s];
          delete f[g]
        }
        delete this._.shadowFilter
      }
    } else C && f[g] === C || (C = this.paper.defs.appendChild(K("filter", {
      id: E.createUUID(), width: "200%",
      height: "200%"
    })), q = E.color(q), q.error && (q = E.color("rgba(0,0,0,1)")), s = E.pick(q.opacity, 1), this._.shadowFilter = f[g] = {
      use: 1,
      filter: C,
      hash: g,
      offset: C.appendChild(K("feOffset", {result: "offOut", "in": "SourceGraphic", dx: B(b), dy: B(d)})),
      matrix: C.appendChild(K("feColorMatrix", {
        result: "matrixOut",
        "in": "offOut",
        type: "matrix",
        values: "0 0 0 0 " + q.r / 255 + " 0 0 0 0 " + q.g / 255 + " 0 0 0 0 " + q.b / 255 + " 0 0 0 " + s + " 0"
      })),
      blur: C.appendChild(K("feGaussianBlur", {result: "blurOut", "in": "matrixOut", stdDeviation: k(B(a))})),
      blend: C.appendChild(K("feComposite",
        {"in": "SourceGraphic", in2: "blurOut", operator: "over"}))
    }, t.setAttribute("filter", 'url("' + E._url + "#" + C.id + '")'));
    return this
  }), G = function (c, d) {
    var a = this.__shadowscale, k = {}, t, C;
    for (C in c)switch (b[C] && (k[C] = c[C], delete c[C]), C) {
      case "transform":
        t = d.matrix.clone();
        t.translate(this.__shadowx, this.__shadowy);
        this.transform(t.toTransformString());
        break;
      case "stroke-width":
        c[C] = ((k[C] || 1) + 6 - 2 * this.__shadowlevel) * a
    }
    this.attr(c);
    for (C in k)c[C] = k[C]
  }, E.ca["drop-shadow"] = function (b, d, a, k, B, C) {
    a = this._.shadows ||
    (this._.shadows = []);
    var f, g, s, n, Q;
    if (!this.__shadowblocked)if ("none" === b)for (; g = a.pop();)g.remove(); else for (k = E.color(k), k.error && (k = E.color("rgba(0,0,0,1)")), B instanceof Array ? (f = B[0], B = B[1]) : f = B, f = 1 / E.pick(f, 1), B = 1 / E.pick(B, 1), b = E.pick(b, 1) * f, d = E.pick(d, 1) * f, f = .05 * E.pick(k.opacity, 1), s = t(this.attr("stroke-width") || 1, 10) + 6, n = this.matrix.clone(), n.translate(b, d), Q = 1; 3 >= Q; Q++)g = (a[Q - 1] || this.clone().follow(this, G, !C && "before")).attr({
      stroke: k.hex, "stroke-opacity": f * Q, "stroke-width": (s - 2 * Q) * B, transform: n.toTransformString(),
      "stroke-linecap": "round", "stroke-linejoin": "round", fill: "none"
    }), g.__shadowlevel = Q, g.__shadowscale = B, g.__shadowx = b, g.__shadowy = d, C && C.appendChild(g), a.push(g);
    return !1
  }, E.el.shadow = function (b, d, a, k) {
    var t;
    a && a.constructor === E.el.constructor && (k = a, a = void 0);
    "object" === typeof b && (d && d.constructor === E.el.constructor && (k = d), d = b.opacity, a = b.scalefactor, t = !!b.useFilter, b = void 0 === b.apply ? !!d : b.apply);
    void 0 === d && (d = 1);
    if (this.dropshadow) {
      if (t)return b && this.dropshadow(1, 1, 3, "rgb(64,64,64)") || this.dropshadow("none"),
        this;
      this._.shadowFilter && this.dropshadow("none")
    }
    return this.attr("drop-shadow", b ? [1, 1, 3, "rgba(64,64,64," + d + ")", a, k] : "none")
  }) : E.vml ? (E.ca["drop-shadow"] = function (b, d, a, k, t, C) {
    var f = this._.shadow, g, s;
    if (this.isShadow)return !1;
    "none" === b ? f && (this._.shadow = f.remove()) : (f || (f = this._.shadow = this.clone(), C && C.appendChild(f.follow(this)) || f.follow(this, void 0, "before"), f.attr({
      fill: "none",
      "fill-opacity": .5,
      "stroke-opacity": 1
    }).isShadow = !0, 0 >= f.attr("stroke-width") && f.attr("stroke-width", 1)), C = f.node.runtimeStyle,
      g = C.filter.replace(/ progid:\S+Blur\([^\)]+\)/g, ""), k = E.color(k), k.error && (k = E.color("rgba(0,0,0,1)")), s = E.pick(k.opacity, 1) / 5, t = 1 / E.pick(t, 1), b = E.pick(b, 1) * t, d = E.pick(d, 1) * t, f.translate(b, d), C.filter = g + " progid:DXImageTransform.Microsoft.Blur(pixelRadius=" + B(.4 * a) + " makeShadow=True Color=" + k.hex + ' shadowOpacity="' + s + '");');
    return !1
  }, E.el.shadow = function (b, d, a, k) {
    a && a.constructor === E.el.constructor && (k = a, a = void 0);
    "object" === typeof b && (d && "group" === d.type && (k = d), d = b.opacity, a = b.scalefactor, b = void 0 ===
    b.apply ? !!d : b.apply);
    void 0 === d && (d = 1);
    return this.attr("drop-shadow", b || !d ? [1, 1, 5, "rgba(64,64,64," + d + ")", a, k] : "none")
  }) : E.canvas && (E.el.shadow = function () {
    return this
  })
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-raphaelshapes", function () {
  var d = this.window, k = "createTouch"in d.document, B = /msie/i.test(d.navigator.userAgent) && !d.opera, t = d.Math, E = t.cos, b = t.sin, K = t.abs, G = t.pow, c = t.atan2, l = t.tan, a = t.acos, q = t.min, S = t.round, C = t.PI, f = t.sqrt, g = 2 * C, s = d.parseInt, n = d.parseFloat, Q = String, A = Array.prototype.slice, m = G(2, -24), P = "rgba(192,192,192," + (B ? .002 : 1E-6) + ")", z = this.hcLib.Raphael, X = z.eve, u = z._createNode, ka = z._setFillAndStroke, ha = z.el.constructor, ba = {
    speed: "optimizeSpeed",
    crisp: "crispEdges", precision: "geometricPrecision"
  }, Z = {enabled: !1, "false": !1, 0: !1, disabled: !0, "true": !0, 1: !0}, za = {
    Q: "L",
    Z: "X",
    q: "l",
    z: "x",
    ",": " "
  }, Aa = /,?([achlmqrstvxz]),?/gi, Ka = /\s*\,\s*/g, $, aa = function () {
    return this.join(",").replace(Aa, $)
  }, qa, ga, R = z._cacher(function (a, b, c, d) {
    return f(G(c - a, 2) + G(d - b, 2))
  }), T = z._cacher(function (a, b, c, d, f) {
    var g = c - a, m = d - b;
    c = R(a, b, c, d);
    return {x: a + g / c * f, y: b + m / c * f}
  });
  if (z.svg)X.on("raphael.attr.shape-rendering", function (a, b) {
    var c = this.node;
    this.attrs[b] = a = ba[a] || a || "auto";
    c.setAttribute(b, a);
    c.style.shapeRendering = a
  }); else if (z.vml)X.on("raphael.attr.shape-rendering", function (a) {
    this.node.style.antialias = "crisp" !== a
  });
  z.define && z.define([{
    name: "polypath", polypath: function () {
      return this.path(void 0, z._lastArgIfGroup(arguments))
    }, ca: {
      polypath: function (a, c, d, f, g, m) {
        var l, k, u;
        l = [];
        a = s(a, 10) || 0;
        c = n(c) || 0;
        d = n(d) || 0;
        f = n(f) || 0;
        g = null === g || isNaN(g) ? .5 * C : z.rad(g);
        m = null === m || isNaN(m) ? 0 : n(m);
        k = g;
        if (2 < a)switch (g = 2 * C / a, m) {
          case 0:
            for (m = 0; m < a; m++)l.push("L", c + f * E(-k), d + f * b(-k)),
              k += g;
            l[0] = "M";
            l.push("Z");
            break;
          case 1:
            for (m = 0; m < a; m++)l.push("M", c, d, "L", c + f * E(-k), d + f * b(-k)), k += g;
            break;
          default:
            g *= .5;
            u = f * E(g) * (1 - m);
            for (m = 0; m < a; m++)l.push("L", c + f * E(-k), d + f * b(-k)), k += g, l.push("L", c + u * E(-k), d + u * b(-k)), k += g;
            l[0] = "M";
            l.push("Z")
        } else 0 === f ? l.push("M", c, d, "L", c, d, "Z") : l.push("M", c - f, d, "A", f, f, 0, 0, 0, c + f, d, "A", f, f, 0, 0, 0, c - f, d, "Z");
        return {path: l}
      }, r: function (a) {
        var b = this.attrs.polypath;
        b[3] = a;
        this.attr("polypath", b);
        return !1
      }
    }
  }, {
    name: "ringpath", ringpath: function () {
      return this.path(void 0,
        z._lastArgIfGroup(arguments))
    }, ca: function (a, c, d, f, l, n) {
      var k = n % g - l % g, s = n - l, z, u, R, q, P;
      this._.ringangle = .5 * (l + n);
      K(s) < m ? (s = E(l), l = b(l), d = ["M", a + d * s, c + d * l, "L", a + f * s, c + f * l, "Z"]) : (K(s) > m && K(s) % g < m ? (d = ["M", a - d, c, "A", d, d, 0, 0, 0, a + d, c, "A", d, d, 0, 0, 0, a - d, c], 0 !== f && (d = d.concat(["M", a - f, c, "A", f, f, 0, 0, 1, a + f, c, "A", f, f, 0, 0, 1, a - f, c]))) : (s = E(l), l = b(l), z = E(n), n = b(n), k %= g, 0 > k && (k += g), k = k < C ? 0 : 1, u = a + d * s, q = c + d * l, R = a + d * z, P = c + d * n, z = a + f * z, n = c + f * n, .01 > K(u - R) && .01 > K(q - P) && (q = P + .01), d = ["M", u, q, "A", d, d, 0, k, 1, R, P, "L", z, n], 0 !==
      f && (a += f * s, c += f * l, .01 > K(z - a) && .01 > K(n - c) && (c = n + .01), d.push("A", f, f, 0, k, 0, a, c))), d.push("Z"));
      return {path: d}
    }
  }, {
    name: "cubepath", cubepath: function () {
      var a = {
        "stroke-linejoin": "round",
        "shape-rendering": "precision",
        stroke: "none"
      }, b = arguments, c = b.length - 1, d = b[c], f, g;
      d && d.constructor === z.el.constructor ? b[c] = void 0 : d = void 0;
      c = this.path(a, d);
      f = this.path(a, d);
      a = this.path(a, d);
      a._.cubetop = c.follow(a, void 0, "before");
      a._.cubeside = f.follow(a, void 0, "before");
      for (g in z.fn.cubepath.ca)a.ca[g] = z.fn.cubepath.ca[g];
      return a.attr("cubepath", [b[0], b[1], b[2], b[3], b[4], b[5]])
    }, fn: {
      _getBBox2: function () {
        var a = this._.cubeside.getBBox(), b = this._.cubetop.getBBox(), c = this.getBBox();
        return {x: c.x + b.height, y: c.y - a.width, width: c.width, height: c.height}
      }
    }, ca: {
      cubepath: function (a, b, c, d, f, g) {
        var m = this._.cubetop, l = this._.cubeside;
        a = a || 0;
        b = b || 0;
        c = c || 0;
        d = d || 0;
        f = f || 0;
        g = g || 0;
        this.attr("path", ["M", a + c, b, "l", 0, d, -c, 0, 0, -d, "z"]);
        m.attr("path", ["M", a, b, "l", 1, 1, c - 1, 0, 0, -1, f, -g, -c, 0, "z"]);
        l.attr("path", ["M", a + c - 1, b + 1, "l", 0, d - 1, 1, 0, f, -g,
          0, -d, -f, g]);
        return !1
      }, "stroke-linejoin": function () {
        return {"stroke-linejoin": "round"}
      }, "drop-shadow": function (a, b, c, d) {
        var f = this._.cubetop, g = this._.cubeside;
        this.dropshadow && (f.dropshadow(a, -b, c, d), g.dropshadow(a, -b, c, d));
        return !1
      }, fill: function (a, b) {
        var d = this._.cubetop, f = this._.cubeside, g = this.attr("cubepath") || [0, 0, 0, 0, 0, 0], m = g[2], l = g[4], g = g[5], n;
        a = z.color(a);
        b ? (this.attr("fill", a), d.attr("fill", z.tintshade(a, -.78).rgba), f.attr("fill", z.tintshade(a, -.65).rgba)) : (n = "opacity"in a ? "rgba(" + [a.r, a.g,
          a.b, a.opacity] + ")" : "rgb(" + [a.r, a.g, a.b] + ")", this.attr("fill", [270, z.tintshade(n, .55).rgba, z.tintshade(n, -.65).rgba].join("-")), f.attr("fill", [270, z.tintshade(n, -.75).rgba, z.tintshade(n, -.35).rgba].join("-")), d.attr("fill", [45 + z.deg(c(g, l + m)), z.tintshade(n, -.78).rgba, z.tintshade(n, .22).rgba].join("-")));
        return !1
      }
    }
  }, {
    name: "scroller", scroller: function (a, b, c, d, f, g, m) {
      var l = this.group("scroller", m), k = l.attrs, s = l._.scroller = {};
      f = f && "horizontal" || "vertical";
      var u, R = {}, q, P, A;
      s.track = this.rect(l).mousedown(function (a) {
        var b =
          k["scroll-position"];
        a = "horizontal" === k["scroll-orientation"] ? a.layerX || a.x : a.layerY || a.y;
        a = (a - s.anchorOffset) / s.trackLength;
        u = z.animation({"scroll-position": a}, 2E3 * K(b - a), "easeIn");
        l.animate(u);
        X("raphael.scroll.start." + l.id, l, b)
      }).mouseup(s._mouseupTrack = function () {
        this.stop(u);
        X("raphael.scroll.end." + this.id, this, k["scroll-position"])
      }, l, !0);
      s.anchor = this.rect(l).drag(function () {
        R["scroll-position"] = q + arguments[P] / s.trackLength;
        l.animate(R, 0)
      }, function (a, b, c) {
        P = "horizontal" === k["scroll-orientation"] ?
          0 : 1;
        X("raphael.scroll.start." + l.id, l, q = k["scroll-position"]);
        c.stopPropagation()
      }, function () {
        X("raphael.scroll.end." + l.id, l, q = k["scroll-position"])
      });
      for (A in z.fn.scroller.fn)l[A] = z.fn.scroller.fn[A];
      for (A in z.fn.scroller.ca)l.ca[A] = z.fn.scroller.ca[A];
      k["scroll-orientation"] = f;
      k["stroke-width"] = 1;
      l.ca["scroll-repaint"] = l.ca["scroll-repaint-" + f];
      !z.is(g, "object") && (g = {});
      return l.attr({
        ishot: !0,
        "scroll-display-buttons": g.showButtons && "arrow" || "none",
        "scroll-display-style": g.displayStyleFlat && "flat" ||
        "3d",
        "scroll-ratio": n(g.scrollRatio) || 1,
        "scroll-position": n(g.scrollPosition) || 0,
        "scroll-repaint": [a, b, c, d]
      })
    }, fn: {
      scroll: function (a, b) {
        var c = this._.scroller;
        b = b || this;
        c.callback = function () {
          return a.apply(b, arguments)
        };
        return this
      }, remove: function () {
        var a = this._.scroller, b;
        this.attr("scroll-display-buttons", "none");
        a.track.unmouseup(a._mouseupTrack);
        for (b in a)a[b] && a[b].remove && a[b].remove(), a[b] = null;
        delete this._.scroller;
        z.el.remove.apply(this, arguments)
      }
    }, ca: {
      "stroke-width": function () {
        return !1
      },
      "drop-shadow": function (a, b, c, d, f, g) {
        this._.scroller.track.attr("drop-shadow", [a, b, c, d, f, g]);
        return !1
      }, "scroll-display-style": function (a) {
        var b = this.attrs, c = b["scroll-display-style"], d = b.fill;
        a = {flat: "flat", "3d": "3d", transparent: "transparent"}[a] || c;
        d && a !== c && (b["scroll-display-style"] = a, this.attr("fill", d));
        return {"scroll-display-style": a}
      }, "scroll-display-buttons": function (a) {
        var b = this, c = b.paper, d = b._.scroller, f = b.attrs, g = f["scroll-display-buttons"], m = f["scroll-repaint"], l, n;
        void 0 === g && (g = "none");
        a = {none: "none", arrow: "arrow"}[a] || g;
        a !== g && (f["scroll-display-buttons"] = a, "none" === a && d.start ? (d.arrowstart.remove(), delete d.arrowstart, d.arrowend.remove(), delete d.arrowend, d.start.unmouseup(d._mouseupStart), d.start.remove(), delete d.start, d.end.unmouseup(d._mouseupEnd), d.end.remove(), delete d.end) : (d.arrowstart = c.polypath(b), d.arrowend = c.polypath(b), d.start = c.rect(b).mousedown(function () {
          var a;
          0 !== (a = f["scroll-position"]) && (b.animate({"scroll-position": a - .1}, 100).animate(l = z.animation({"scroll-position": 0},
            4500 * a, "easeIn")), X("raphael.scroll.start." + b.id, b, a))
        }).mouseup(d._mouseupStart = function () {
          b.stop(l);
          X("raphael.scroll.end." + b.id, b, f["scroll-position"])
        }, b, !0), d.end = c.rect(b).mousedown(function () {
          var a;
          1 !== (a = f["scroll-position"]) && (b.animate({"scroll-position": a + .1}, 100).animate(n = z.animation({"scroll-position": 1}, 4500 * (1 - a), "easeIn")), X("raphael.scroll.start." + b.id, b, a))
        }).mouseup(d._mouseupEnd = function () {
          b.stop(n);
          X("raphael.scroll.end." + b.id, b, f["scroll-position"])
        }, b, !0), f.fill && b.attr("fill",
          f.fill)), m && b.attr("scroll-repaint", m));
        return {"scroll-display-buttons": a}
      }, "scroll-orientation": function (a) {
        var b = this.attrs, c = b["scroll-repaint"], d = b["scroll-orientation"];
        a = {horizontal: "horizontal", vertical: "vertical"}[a] || d;
        d !== a && (this.ca["scroll-repaint"] = this.ca["scroll-repaint-" + a], c && (c[2] += c[3], c[3] = c[2] - c[3], c[2] -= c[3], this.attr("scroll-repaint", c)), b.fill && this.attr("fill", b.fill));
        return {"scroll-orientation": a}
      }, "scroll-ratio": function (a) {
        var b = this.attrs, c = b["scroll-ratio"], d = b["scroll-repaint"];
        a = 1 < a ? 1 : .01 > a ? .01 : n(a);
        d && a !== c && (b["scroll-ratio"] = a, this.attr("scroll-repaint", d));
        return {"scroll-ratio": a}
      }, "scroll-position": function (a, b) {
        var c = this.attrs, d = "horizontal" === c["scroll-orientation"], f = c["scroll-repaint"], g = c["scroll-position"], m = this._.scroller, l = m.anchor;
        a = 1 < a ? 1 : 0 > a ? 0 : n(a);
        isNaN(a) && (a = g);
        f && (g !== a || b) && (g = m.start && m.start.attr(d && "width" || "height") || 0, d && l.attr("x", f[0] + g + (f[2] - 2 * g - l.attr("width")) * a + .5) || l.attr("y", f[1] + g + (f[3] - 2 * g - l.attr("height")) * a + .5), !b && 1 > c["scroll-ratio"] &&
        (X("raphael.scroll.change." + this.id, this, a), m.callback && m.callback(a)));
        return {"scroll-position": a}
      }, r: function (a) {
        var b = this._.scroller;
        b.track.attr("r", a);
        b.anchor.attr("r", "none" === this.attrs["scroll-display-buttons"] && a || 0);
        return !1
      }, "scroll-repaint-horizontal": function (a, b, c, d) {
        var f = this.attrs, g = this._.scroller, m = f["scroll-ratio"], l = f["scroll-position"], n = 0, k = c * m, f = "none" === f["scroll-display-buttons"];
        c && --c;
        a && (a += .5);
        d && --d;
        b && (b += .5);
        g.track.attr({width: c, height: d, y: b, x: a}).crisp();
        f || (n =
          q(d, .5 * c), k -= 2 * n * m, g.start.attr({
          width: n,
          height: d,
          x: a,
          y: b
        }), g.arrowstart.attr("polypath", [3, a + .5 * n, b + .5 * d, .25 * n, 180]), g.end.attr({
          width: n,
          height: d,
          x: a + c - n,
          y: b
        }), g.arrowend.attr("polypath", [3, a + c - .5 * n, b + .5 * n, .25 * n, 0]));
        g.trackLength = c - 2 * n - k;
        g.trackOffset = a + n + .5;
        g.anchorOffset = g.trackOffset + .5 * (k - 1);
        g.anchor.attr({height: d, width: k - 1, y: b, x: g.trackOffset + g.trackLength * l}).crisp()
      }, "scroll-repaint-vertical": function (a, b, c, d) {
        var f = this.attrs, g = this._.scroller, m = f["scroll-ratio"], l = f["scroll-position"],
          n = 0, k = d * m, f = "none" === f["scroll-display-buttons"];
        c && --c;
        a && (a += .5);
        d && --d;
        b && (b += .5);
        g.track.attr({width: c, height: d, y: b, x: a}).crisp();
        f || (n = q(c, .5 * d), k -= 2 * n * m, g.start.attr({
          width: c,
          height: n,
          x: a,
          y: b
        }), g.arrowstart.attr("polypath", [3, a + .5 * c, b + .5 * n, .25 * n, 90]), g.end.attr({
          width: c,
          height: n,
          x: a,
          y: b + d - n
        }), g.arrowend.attr("polypath", [3, a + .5 * c, b + d - .5 * n, .25 * n, -90]));
        g.trackLength = d - 2 * n - k;
        g.trackOffset = b + n + .5;
        g.anchorOffset = g.trackOffset + .5 * (k - 1);
        g.anchor.attr({
          height: k - 1, width: c, y: g.trackOffset + g.trackLength *
          l, x: a
        }).crisp()
      }, fill: function (a) {
        var b = this.attrs, c = this._.scroller, d = b["scroll-repaint"], f = "flat" === b["scroll-display-style"], g = "horizontal" === b["scroll-orientation"], m = {stroke: "none"}, l;
        k && d && 3 < (l = 16 - d[g && 3 || 2]) && (m.stroke = P, m["stroke-width"] = l);
        a = z.color(a);
        a.error && (a = z.color("#000000"));
        a = "opacity"in a ? "rgba(" + [a.r, a.g, a.b, a.opacity] + ")" : "rgb(" + [a.r, a.g, a.b] + ")";
        m.fill = f && a || [90 * g, z.tintshade(a, .15).rgba, a].join("-");
        m.stroke = z.tintshade(a, -.75).rgba;
        c.track.attr(m);
        m.fill = f && z.tintshade(a,
          -.6).rgba || [270 * g, z.tintshade(a, .3).rgba + ":40", z.tintshade(a, -.7).rgba].join("-");
        m.stroke = z.tintshade(a, -.6).rgba;
        c.anchor.attr(m);
        m.stroke = "none";
        "none" !== b["scroll-display-buttons"] && (m.fill = P, c.start.attr(m), c.end.attr(m), m.fill = z.tintshade(a, -.4).rgba, c.arrowstart.attr(m), c.arrowend.attr(m));
        return !1
      }
    }
  }, {
    name: "button", button: function (a, b, c, d, f, g) {
      g = this.group("button", g);
      var m;
      g._.button = {
        bound: this.rect(g), tracker: this.rect(g).attr({fill: P, stroke: P, cursor: "pointer"}).data("compositeButton",
          g)
      };
      !z.is(f, "object") && (f = {});
      for (m in z.fn.button.fn)g[m] = z.fn.button.fn[m];
      for (m in z.fn.button.ca)g.ca[m] = z.fn.button.ca[m];
      return g.attr({
        ishot: !0,
        "button-padding": [f.horizontalPadding, f.verticalPadding],
        "button-label": c,
        "button-symbol": d,
        "button-disabled": f.disabled || "false",
        "button-symbol-position": f.symbolPosition,
        "button-symbol-padding": f.symbolPadding
      }).attr("button-repaint", [a, b, f.width, f.height, f.r])
    }, data: {
      hoverin: function () {
        var a = this._.button.hoverbackIn;
        a && !1 === a() || (this.attr("fill",
          "hover").hovered = !0)
      }, hoverout: function () {
        var a = this._.button.hoverbackOut;
        a && !1 === a() || (this.attr("fill", (this.pressed || this.active) && "active" || "normal").hovered = !1)
      }, mousedown: function () {
        this.attr("fill", "active").pressed = !0
      }, mouseup: function () {
        var a = this._.button.callback;
        this.attr("fill", this.hovered && "hover" || this.active && "active" || "normal").pressed = !1;
        a()
      }
    }, fn: {
      tooltip: function () {
        z.el.tooltip && z.el.tooltip.apply(this._.button.tracker, arguments);
        return this
      }, buttonclick: function (a, b) {
        var c = this._.button;
        b = b || this;
        c.callback = function () {
          return a.apply(b, arguments)
        };
        return this
      }, labelcss: function () {
        var a = this._.button, b = a.label;
        a.cssArg = arguments;
        b && b.css.apply(b, arguments);
        return this.attr("button-repaint", this.attrs["button-repaint"])
      }, buttonhover: function (a, b, c, d) {
        var f = this._.button;
        c = c || this;
        d = d || this;
        f.hoverbackIn = function () {
          return a.apply(c, arguments)
        };
        f.hoverbackOut = function () {
          return b.apply(d, arguments)
        };
        return this
      }, remove: function () {
        var a = this._.button, b;
        this.attr("button-disabled", "true");
        for (b in a)a[b] && a[b].remove && a[b].remove(), a[b] = null;
        delete this._.button;
        z.el.remove.apply(this, arguments)
      }
    }, ca: {
      "button-active": function (a) {
        this.attr("fill", (this.active = !!a) ? "active" : this.hovered && "hover" || "normal")
      }, "button-disabled": function (a) {
        var b = this._.button.tracker, c = this.attrs["button-disabled"], d = this.paper.button.data;
        a = Z[a];
        c = Z[c];
        if (void 0 !== a && a !== c)switch (a) {
          case !0:
            b.attr("fill", "rgba(204,204,205,.5)").unmousedown(d.mousedown).unmouseup(d.mouseup).unhover(d.hoverin, d.hoverout);
            break;
          case !1:
            b.attr("fill", P).mousedown(d.mousedown, this).mouseup(d.mouseup, this, !0).hover(d.hoverin, d.hoverout, this, this)
        }
      }, "button-label": function (a) {
        var b = this._.button, c = this.attrs, d = b.label, f = b.cssArg, g = this.attrs["button-repaint"];
        a = Q(a || "");
        "none" === a ? d && (b.label = d.remove()) : a && (!d && (d = b.label = this.paper.text(this).insertBefore(b.tracker)), d.attr({
          text: a,
          "text-anchor": "middle",
          "vertical-align": "middle"
        }), f && f.length && d.css.apply(d, f));
        g && c["button-label"] !== a && this.attr("button-repaint", g)
      },
      "button-symbol": function (a) {
        var b = this.attrs, c = this._.button, d = c.symbol, f = this.attrs["button-repaint"];
        a = Q(a || "");
        "none" === a ? d && (c.symbol = d.remove()) : a && !d && (c.symbol = this.paper.symbol(this).insertAfter(c.bound));
        f && b["button-symbol"] !== a && this.attr("button-repaint", f)
      }, "button-symbol-position": function (a) {
        return {
          "button-symbol-position": {
            top: "top",
            right: "right",
            bottom: "bottom",
            left: "left",
            none: "none"
          }[Q(a).toLowerCase()] || "none"
        }
      }, "button-symbol-padding": function (a) {
        return {"button-symbol-padding": n(a)}
      },
      "button-padding": function (a, b) {
        return {"button-padding": [null == a && (a = 5) || n(a), null == b && a || n(b)]}
      }, "button-repaint": function (a, b, c, d, f) {
        var g = this._.button, m = g.bound, l = g.label, n = g.symbol, k = this.attrs, s = k["button-padding"], u = s[0], R = s[1], P, A;
        void 0 === a && (a = 0);
        void 0 === b && (b = 0);
        if (void 0 === c || void 0 === d)P = l && l.getBBox() || {
          width: 0,
          height: 0
        }, void 0 === c && (c = 2 * u + P.width), void 0 === d && (d = 2 * R + P.height);
        m = z.crispBound(a, b, c, d, m.attr("stroke-width"));
        m.r = z.pick(f, S(.1 * q(d, c)));
        a = m.x;
        b = m.y;
        c = m.width;
        d = m.height;
        l && l.attr({x: a + c / 2, y: b + d / 2});
        if (n) {
          !z.is(A = k["button-symbol-padding"], "finite") && (A = .2 * d);
          f = d - R;
          P = .5 * f;
          switch (k["button-symbol-position"] + (l && "+" || "-")) {
            case "right+":
              a = a + (c + (2 * P + R)) - P - u;
              b += .5 * d;
              l.attr("transform", ["t", -(f + A), 0]);
              break;
            case "left+":
              a = a + u + P;
              b += .5 * d;
              l.attr("transform", ["t", f + A, 0]);
              break;
            case "top+":
              a += .5 * c;
              b = b + s[1] + P;
              l.attr("transform", ["t", 0, f + A]);
              break;
            case "bottom+":
              a += .5 * c;
              b = b + (d + (2 * P + A)) - R - P;
              l.attr("transform", ["t", 0, -(f + A)]);
              break;
            default:
              a += .5 * c, b += .5 * d
          }
          n.attr("symbol", [k["button-symbol"],
            a, b, P])
        }
        g.bound.attr(m);
        g.tracker.attr(m)
      }, fill: function (a, b, c, d) {
        var f = this._.button, g = f.bound, m = f.symbol, l = f.label, n = {
          normal: f.gradient,
          active: f.gradientActive,
          hover: f.gradientHover
        }[a];
        n || (a = z.getRGB(a), a.error && (a = z.color("#cccccc")), a = "opacity"in a ? "rgba(" + [a.r, a.g, a.b, a.opacity] + ")" : "rgb(" + [a.r, a.g, a.b] + ")", f.gradient = [90, z.tintshade(a, -.8).rgba + ":0", z.tintshade(a, .8).rgba + ":100"].join("-"), f.gradientActive = [270, z.tintshade(a, -.8).rgba + ":0", z.tintshade(a, .8).rgba + ":100"].join("-"), d = z.getRGB(d),
        d.error && (d = a) || (d = "opacity"in d ? "rgba(" + [d.r, d.g, d.b, d.opacity] + ")" : "rgb(" + [d.r, d.g, d.b] + ")"), f.gradientHover = [90, z.tintshade(d, -.9).rgba + ":0", z.tintshade(d, .7).rgba + ":100"].join("-"), c = c || z.tintshade(a, .2).rgba, b = b || z.tintshade(a, -.2).rgba, f.symbolFill = c, f.labelFill = b, n = (this.pressed || this.active) && f.gradientActive || this.hovered && f.gradienthover || f.gradient);
        g.attr("fill", n);
        m && m.attr("fill", f.symbolFill);
        l && l.attr("fill", f.labelFill);
        return !1
      }, stroke: function (a, b) {
        var c = this._.button, d = c.symbol;
        a = z.color(a);
        a.error && (a = z.color("#999999"));
        c.bound.attr("stroke", a);
        d && d.attr("stroke", b || a);
        return !1
      }, "stroke-width": function (a, b) {
        var c = this._.button, d = c.symbol;
        c.bound.attr("stroke-width", a);
        c.tracker.attr("stroke-width", a);
        d && d.attr("stroke-width", b);
        return !1
      }
    }
  }, {
    name: "trianglepath", trianglepath: function () {
      var a = arguments, b = z._lastArgIfGroup(a);
      return this.path(b).attr("trianglepath", [a[0], a[1], a[2], a[3], a[4], a[5], a[6] || 0, a[7] || 0, a[8] || 0])
    }, fn: {
      sides: function () {
        var a = this._args;
        return [R(a[0],
          a[1], a[2], a[3]), R(a[2], a[3], a[4], a[5]), R(a[4], a[5], a[0], a[1])]
      }, enclosedAngles: function () {
        var b = this._sides;
        return [a((G(b[0], 2) + G(b[2], 2) - G(b[1], 2)) / (2 * b[0] * b[2])), a((G(b[0], 2) + G(b[1], 2) - G(b[2], 2)) / (2 * b[0] * b[1])), a((G(b[2], 2) + G(b[1], 2) - G(b[0], 2)) / (2 * b[2] * b[1]))]
      }, semiperimeter: function () {
        var a = this._sides || this.sides();
        return (a[0] + a[1] + a[2]) / 2
      }
    }, ca: {
      trianglepath: function (a, b, c, d, g, m, n, k, s) {
        if (n || k || s) {
          this._args = arguments;
          this._sides = this.sides();
          var z = this.enclosedAngles(), u;
          u = this.semiperimeter();
          u = f(u * (u - this._sides[0]) * (u - this._sides[1]) * (u - this._sides[2])) / u;
          z = [q(n, u) / l(z[0] / 2), q(k, u) / l(z[1] / 2), q(s, u) / l(z[2] / 2)];
          z = [T(a, b, g, m, z[0]), T(a, b, c, d, z[0]), T(c, d, a, b, z[1]), T(c, d, g, m, z[1]), T(g, m, c, d, z[2]), T(g, m, a, b, z[2])];
          this.attr({path: ["M", z[0].x, z[0].y, "Q", a, b, z[1].x, z[1].y, "L", z[2].x, z[2].y, "Q", c, d, z[3].x, z[3].y, "L", z[4].x, z[4].y, "Q", g, m, z[5].x, z[5].y, "L", z[0].x, z[0].y]})
        } else this.attr({path: ["M", a, b, "L", c, d, g, m, "Z"]})
      }
    }
  }]);
  z.ca["text-bound"] = function (a, b, c, d, f, g) {
    d = this.paper;
    var m = this._.textbound;
    if ("text" === this.type) {
      if (!(b && "none" !== b || a && "none" !== a))return this._.textbound = m && m.unfollow(this).remove(), !1;
      c && z.is(c, "finite") || (c = 0);
      f && z.is(f, "finite") || (f = 0);
      !m && (m = this._.textbound = d.rect(0, 0, 0, 0, this.group).follow(this, z.ca["text-bound"].reposition, "before"));
      m.attr({stroke: b, "stroke-width": c, fill: a, "shape-rendering": 1 === c && "crisp" || "", r: f});
      g && m.attr("stroke-dasharray", g);
      z.ca["text-bound"].reposition.call(m, this.attr(), this);
      return !1
    }
  };
  z.ca["text-bound"].reposition = function (a, b) {
    var c =
    {}, d, f, g, m, l;
    a.hasOwnProperty("visibility") && this.attr("visibility", a.visibility);
    if (a.hasOwnProperty("text-bound") || a.hasOwnProperty("x") || a.hasOwnProperty("y") || a.hasOwnProperty("text") || a.hasOwnProperty("text-anchor") || a.hasOwnProperty("text-align") || a.hasOwnProperty("font-size") || a.hasOwnProperty("line-height") || a.hasOwnProperty("vertical-align") || a.hasOwnProperty("transform") || a.hasOwnProperty("rotation"))d = b.attrs["text-bound"], f = Q(d && d[3] || "0").split(Ka), d = n(f[0]) || 0, f = z.pick(n(f[1]), d), g =
      b.getBBox(), m = g.width, l = g.height, isNaN(m) || (c.x = g.x - d, c.y = g.y - f, c.width = m + 2 * d, c.height = l + 2 * f), this.attr(c)
  };
  z.fn.symbol = function () {
    var a = arguments, b = a.length - 1, c = a[b];
    c && c.constructor === z.el.constructor ? a[b] = void 0 : c = void 0;
    b = this.path(void 0, c);
    b.ca.symbol = z.fn.symbol.ca.symbol;
    return a.length === !!c + 0 ? b : b.attr("symbol", a)
  };
  z.fn.symbol.cache = {
    "": z._cacher(function (a, b, c, d) {
      return 3 < arguments.length ? ["M", a, b, "h", c, "v", d, "h", -c, "v", -d, "z"] : ["M", a - c, b - c, "h", c *= 2, "v", c, "h", -c, "v", -c, "z"]
    })
  };
  z.fn.symbol.ca =
  {
    symbol: function (a) {
      var b = z.is(a, "object") && 1 === arguments.length && !z.is(a, "function") ? a : arguments, c;
      b === a && (a = b[0]);
      b = (c = z.is(a, "function") && a || z.fn.symbol.cache[a] || z.fn.symbol.cache[""]) && c.apply(z, A.call(b, 1));
      z.is(b, "array") || z.is(b, "string") ? this.attr("path", b) : b && this.attr(b)
    }
  };
  z.addSymbol = function (a, b) {
    var c = z.is(b, "function") && (c = {}, c[a] = b, c) || a, d = z.fn.symbol.cache, f = [], g;
    for (g in c)b = c[g], d[g] = z.is(b, "function") && z._cacher(b, z) || (f.push(g), b);
    for (; g = f.pop();)d[g] = d[d[g]]
  };
  z.svg ? ($ = "$1", qa =
    function (a) {
      a ? "string" === typeof a ? a = a.replace(Aa, $) : a.toString = aa : a = "M0,0";
      this.node.setAttribute("d", a.toString());
      return this
    }, z._engine.litepath = function (a, b, c, d) {
    a = u("path");
    (d || b).canvas.appendChild(a);
    b = new ha(a, b, d);
    b.type = "litepath";
    b.id = a.raphaelid = z._oid++;
    a.raphael = !0;
    ka(b, {fill: "none", stroke: "#000"});
    return b
  }, z._getPath.litepath = function (a) {
    return z.parsePathString(a.node.getAttribute("d"))
  }) : z.vml && ($ = function (a, b) {
    return za[b] || b
  }, ga = function () {
    this._transform.apply(this, arguments);
    this._.bcoord && (this.node.coordsize = this._.bcoord);
    return this
  }, qa = function (a) {
    a ? "string" === typeof a ? a = a.replace(Aa, $) : a.toString = aa : a = "M0,0";
    this.node.path = a;
    return this
  }, z._engine.litepath = function (a, b, c, d) {
    a = u("shape");
    var f = a.style, g = new ha(a, b, d);
    f.cssText = "position:absolute;left:0;top:0;width:21600px;height:21600px;";
    c = n(c);
    isNaN(c) ? a.coordsize = "21600 21600" : (g._.bzoom = c, f.width = "1px", f.height = "1px", a.coordsize = g._.bcoord = c + " " + c);
    a.coordorigin = b.coordorigin;
    g.type = "litepath";
    g.id = a.raphaelid =
      z._oid++;
    a.raphael = !0;
    g._transform = g.transform;
    g.transform = ga;
    z._setFillAndStroke(g, {fill: "none", stroke: "#000"});
    (d || b).canvas.appendChild(a);
    b = u("skew");
    b.on = !0;
    a.appendChild(b);
    g.skew = b;
    return g
  }, z._getPath.litepath = function (a) {
    return z.parsePathString(a.node.path || "")
  });
  z.fn.litepath = function (a, b, c) {
    b && b.constructor === ha && (c = b, b = void 0);
    a && a.constructor === ha && (c = a, a = "");
    b = z._engine.litepath(a, this, b, c);
    b.ca.litepath = qa;
    a && b.attr("litepath", z.is(a, "array") ? [a] : a);
    return this.__set__ && this.__set__.push(b),
      this._elementsById[b.id] = b
  }
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-htmlrenderer", function () {
  var d = this.hcLib, k = d.Raphael, B = d.dem, t = this.window, E = t.document, b = /msie/i.test(t.navigator.userAgent) && !t.opera, K = "VML" === k.type, G = d.hasTouch, c = {cursor: "cursor"}, l = {
    x: "left",
    y: "top",
    strokeWidth: "borderThickness",
    "stroke-width": "borderThickness",
    width: "width",
    height: "height"
  }, a = {fill: "backgroundColor", stroke: "borderColor", color: "color"}, q = {
    left: 0, top: 0, padding: 0, border: "none", margin: 0, outline: "none", "-webkit-apperance": "none",
    position: "absolute", zIndex: 20
  }, S, C = function (a, b, c, d) {
    a = E.createElement(a);
    for (var f in b)l[f] ? a.style[f] = b[f] : a.setAttribute(f, b[f]);
    for (f in c)a.style[f] = c[f];
    d && d.appendChild && d.appendChild(a);
    return a
  }, f;
  f = function (a, b, c) {
    b && b instanceof f && (b = b.element);
    (this.element = C(a, c, q, b)).ishot = "true";
    this.nodeName = a.toLowerCase();
    this.added = Boolean(b)
  };
  f.prototype = {
    attr: function (d) {
      var f = this.element, n = {}, k, q, m, P, z, t, u;
      if ("object" !== typeof d) {
        if (!(n = this[d])) {
          if ("string" === typeof d)f && f.getAttribute &&
          (P = f.getAttribute(d)); else if (void 0 !== d && null !== d && "object" === typeof d)for (m in d)f.setAttribute(m, d[m]);
          n = P
        }
        return n
      }
      for (k in d) {
        m = d[k];
        if (c[k]) {
          switch (k) {
            case "cursor":
              "pointer" === m && K && (m = "hand")
          }
          f.style[c[k]] = m;
          q = !0
        } else if (l[k])f.style[l[k]] = m + "px", q = !0; else if (a[k])f.style[a[k]] = m && m.replace(/^#?([a-f0-9]+)/ig, "#$1") || "none", q = !0; else if (/^visibility$/i.test(k))q = "hidden" === m, f.style.display = q ? "none" : "", this.hidden = q, q = !0; else if (/^opacity$/i.test(k))f.style.opacity = m, b && (q = 100 * Number(m), f.style.filter =
          "progid:DXImageTransform.Microsoft.Alpha(Opacity=" + q + ")"), q = !0; else if (/^innerhtml$/i.test(k)) {
          if (K && "select" == f.nodeName.toLowerCase()) {
            for (q = m.match(/<option\s?[\s\S]*?(\/>|><\/option>|>[\s\S]*?<\/option>)/ig); f.firstChild;)f.removeChild(f.firstChild);
            z = 0;
            for (t = q.length; z < t; z += 1)P = q[z], u = E.createElement("option"), /<option\s([\s\S]*[\'\"])\s*?(\/>|>[\s\S]*<\/option>)/ig.test(P) && (u.value = P.replace(/<option\s([\s\S]*[\'\"])\s*?(\/>|>[\s\S]*<\/option>)/ig, "$1").replace(/[\s\S]*value\s*\=\s*[\'\"]([\s\S]*)[\'\"]/,
              "$1")), u.text = P.replace(/<option\s*[\s\S]*[\'\"]?\s*?[\/>|\>]([\s\S]*)<\/option>/ig, "$1 "), f.options.add(u)
          } else"input" !== f.nodeName.toLowerCase() && void 0 !== m && (f.innerHTML = m || "");
          q = !0
        } else/^text$/i.test(k) ? ("input" !== f.nodeName.toLowerCase() && (f.innerHTML = "", void 0 !== m && f.appendChild(E.createTextNode(m))), q = !0) : /^type$/i.test(k) && b && this.added && (q = !0);
        q && (n[k] = m, delete d[k], q = !1)
      }
      for (k in d)f.setAttribute(k, d[k]);
      for (k in n)this[k] = d[k] = n[k], delete n[k];
      return this
    }, val: function (a) {
      var b = this.element,
        c = void 0 === a;
      return "input" === this.nodeName && "checkbox" === b.getAttribute("type") ? c ? this.checked() ? 1 : 0 : this.checked(a) : c ? b.value : (b.value = a, this)
    }, checked: function (a) {
      var b = this.element;
      return void 0 === a ? b.checked : (a ? b.setAttribute("checked", "checked") : b.removeAttribute("checked"), this)
    }, css: function (a, b) {
      var c = this.element.style, d;
      if ("object" === typeof a)for (d in a)c[d] = a[d]; else d && void 0 !== b && (c[d] = b);
      return this
    }, translate: function (a, b) {
      var c = this.element;
      void 0 !== a && (c.style.left = a + "px");
      void 0 !==
      b && (c.style.top = b + "px");
      return this
    }, add: function (a, b) {
      var c = this.element, d = a.element;
      b ? d.insertBefore(c, d.firstChild) : d.appendChild(c);
      this.added = !0;
      return this
    }, hide: function () {
      this.element.style.display = "none";
      return this
    }, show: function () {
      this.element.style.display = "";
      return this
    }, focus: function () {
      "function" === typeof this.element.focus ? this.element.focus() : d.dem.fire(this.element, "focus")
    }, destroy: function () {
      var a = this.element || {};
      a.onclick = a.onmouseout = a.onmouseover = a.onmousemove = a.onblur = a.onfocus =
        null;
      S || (S = C("div"));
      a && S.appendChild(a);
      S.innerHTML = "";
      delete this.element;
      return null
    }, on: K ? function (a, b) {
      this.element["on" + a] = function () {
        var a = t.event;
        a.target = a.srcElement;
        b(a)
      };
      return this
    } : function (a, b) {
      var c = b;
      G && "click" === a && (a = "touchstart", c = function (a) {
        a.preventDefault();
        b()
      });
      this.element["on" + a] = c;
      return this
    }, bind: function (a, b, c) {
      B.listen(this.element, a, b, c);
      return this
    }, unbind: function (a, b) {
      B.unlisten(this.element, a, b);
      return this
    }, trigger: function (a, b) {
      B.fire(this.element, a, b);
      return this
    },
    fadeIn: function (a, b) {
      var c = "fast" === a ? 400 : 1E3;
      this.show();
      this.attr({opacity: 0});
      d.danimate.animate(this.element, {opacity: 1}, c, "linear", b)
    }
  };
  f.prototype.constructor = f;
  k.fn.html = function (a, b, c, d) {
    var l = {}, m;
    b && "type"in b && (l.type = b.type, delete b.type);
    a = (new f(a, d, l)).css(c).attr(b);
    for (m in l)b[m] = l[m];
    return a
  }
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-raphaeltooltip", function () {
  var d = this, k = d.window, B = k.document, t = B.body || B.getElementsByTagName("body")[0], E = d.hcLib, b = E.Raphael, K = b.eve, G = E.createElement, c = E.addEvent, l = E.removeEvent, a = E.getPosition, q = E.hasTouch, S = E.getTouchEvent, C = k.Math, f = C.ceil, g = C.floor, s = {}, n = k.screen.availHeight, Q = k.screen.availWidth, A = {
      "": 1,
      moz: 1,
      webkit: 1,
      o: 1,
      ms: 1
    }, m = {borderRadius: "borderRadius", boxShadow: "boxShadow"}, P = /\-([a-z])/ig, z = function (a, b) {
      return b.toUpperCase()
    },
    X = function (a) {
      var c = u.forbiddenStyle, d, f, g;
      for (d in a)f = P.test(d) ? d.replace(P, z) : d, void 0 !== a[d] && !c[f] && (this[f] = a[d]), b.vml && /color/ig.test(f) && (this[f] = b.getRGB(this[f]).toString());
      for (d in m)if (this[d])for (g in A)this[g + d] = this[d]
    }, u = E.toolTip = {
      elementId: "fusioncharts-tooltip-element",
      element: null,
      lastTarget: null,
      currentTarget: null,
      currentPaper: null,
      pointeroffset: 12,
      prevented: !1,
      defaultStyle: E.extend2(X.prototype, {
        backgroundColor: "#ffffee", borderColor: "#000000", borderWidth: "1px", color: "#000000",
        fontSize: "10px", lineHeight: "12px", padding: "3px", borderStyle: "solid"
      }),
      defaultContainerStyle: {
        position: "absolute",
        textAlign: "left",
        margin: "0",
        zIndex: "99999",
        pointer: "default",
        display: "block"
      },
      forbiddenStyle: {}
    }, ka = function (a) {
      !0 === u._oobready ? u._oobready = !1 : (l(t, "touchstart", ka), !u.hidden && u.currentTarget && (a = a.srcElement || a.target || s, a.raphael && u.currentTarget.paper.getById(a.raphaelid) === u.currentTarget || u.hide()))
    };
  b.svg && (u.defaultContainerStyle.pointerEvents = "none", u.defaultStyle.borderRadius =
    "0", u.defaultStyle.boxShadow = "none");
  b.vml && (u.forbiddenStyle.borderRadius = !0, u.forbiddenStyle.boxShadow = !0, u.defaultStyle.filter = "");
  u.setup = function () {
    var a = u.container, c = u.textElement, f = u.style, g = u.defaultContainerStyle, m = u.forbiddenStyle, l;
    a || (a = u.element = G("span"), (B.body || B.getElementsByTagName("body")[0]).appendChild(a), a.setAttribute("id", u.elementId), f = u.containerStyle = a.style, c = u.textElement = G("span"), a.appendChild(c), u.style = b.vml ? c.runtimeStyle : c.style, u.style.overflow = "hidden", u.style.display =
      "block", u.hidden = !1, u.hide());
    for (l in g)!m[l] && (f[l] = g[l]);
    u.scatted = !0;
    K.on("raphael.drag.start.*", function () {
      u.scatted && (u.waitingScat = !0)
    });
    K.on("raphael.drag.move.*", function () {
      u.waitingScat && (u.block(), u.waitingScat = !1)
    });
    K.on("raphael.drag.end.*", function () {
      u.waitingScat = !1;
      u.scatted && u.unblock(!0)
    });
    K.on("raphael.remove", function () {
      if (u.currentPaper === this || u.currentTarget && u.currentTarget.paper === this)u.hide(), u.currentTarget = u.currentPaper = null
    });
    d.addEventListener("LinkedChartInvoked",
      function (a) {
        u.currentPaper === a.sender.jsVars.hcObj.paper && u.hide()
      });
    d.addEventListener("realTimeUpdateComplete", function (a) {
      u.currentPaper === a.sender.jsVars.hcObj.paper && u.hide()
    })
  };
  u.restyle = function (a) {
    var b = u.style, c;
    for (c in a)b[c] = a[c]
  };
  u.onelement = function (b) {
    if (!b.__tipProcessed) {
      var d = this.paper, f = "group" === this.type ? d && d._elementFromEvent(b) : this, g = d.__tipStyle;
      f && g && f.__tipNeeded && ((b.originalEvent || b).FusionChartsPreventEvent && u.preventTooltip(), u.hiding && (u.hiding = clearTimeout(u.hiding)),
      u.currentPaper !== d && (d.__tipCp = d.canvas && a(d.canvas.parentNode, !0) || {}, u.restyle(d.__tipStyle), u.currentPaper = d), u.lastTarget = u.currentTarget, u.currentTarget = f, u.scatted = f.__tipScatted, u.onredraw.call(this, b), b.__tipProcessed = !0, q && (u._oobready = !0, c(t || (t = B.body || B.getElementsByTagName("body")[0]), "touchstart", ka)))
    }
  };
  u.onredraw = function (a) {
    a.__tipProcessed || (a.__tipProcessed = !0, (this.paper && this.paper._elementFromEvent(a)) === u.currentTarget && (a = S(a), u.x = g(a.pageX || a.clientX + B.body.scrollLeft + B.documentElement.scrollLeft ||
    0), u.y = g(a.pageY || a.clientY + B.body.scrollTop + B.documentElement.scrollTop || 0), u.redraw()))
  };
  u.onhide = function (a) {
    a.__tipProcessed || (a.__tipProcessed = !0, (this.paper && this.paper._elementFromEvent(a)) === u.currentTarget && (u.hiding = setTimeout(u.hide, 200)))
  };
  u.redraw = function () {
    if (!u.prevented && !u.blocked && u.currentTarget && u.currentTarget.__tipNeeded) {
      var a = u.currentTarget, b = a.paper, c = u.textElement, d = u.containerStyle, g = u.style, m = a.__tipText, a = u.pointeroffset, l = b.__tipCp, k = B.documentElement || B.body, z = k.scrollLeft,
        k = k.scrollTop, s = u.x, q = u.y, P, h = b.width, A = b.height, b = b.__tipConstrain;
      if (100 > h || 100 > A)b = !1;
      u.hidden && (u.containerStyle.top = "-999em", u.show());
      m !== u.text && (u.text = m, d.width = d.height = "", c.innerHTML = m, g.whiteSpace = "nowrap", m = f(g.pixelWidth || c.offsetWidth || 0), P = f(g.pixelHeight || c.offsetHeight || 0), (u.textWidthOverflow = s + m > l.left + h) ? (d.width = (h > m ? m + 2 * a : h - 2 * a || 0) + "px", g.whiteSpace = "normal") : d.width = "", (u.textHeightOverflow = P > A) ? (d.height = (A || 0) - 2 * a + "px", g.whiteSpace = "normal") : d.height = "");
      m = f(g.pixelWidth ||
      c.offsetWidth || 0);
      P = f(g.pixelHeight || c.offsetHeight || 0);
      b ? (u.textWidthOverflow ? s = (s - m < l.left ? l.left : s - m) - z : s + a + m > l.left - z + h - a && (s = s - m - a), u.textHeightOverflow ? q = l.top - k : q + a + P > l.top - k + A - a && (q = q - P - 1.5 * a)) : (z + Q < s + a + m && (s = s - m - a), k + n < q + a + P && (q = q - P - 1.5 * a));
      d.left = (s + a || 0) + "px";
      d.top = (q + a || 0) + "px";
      u.hidden && u.show()
    }
  };
  u.hide = function () {
    u.hiding && (u.hiding = clearTimeout(u.hiding));
    u.containerStyle.display = "none";
    u.hidden = !0;
    u.prevented = !1
  };
  u.show = function () {
    u.blocked || (u.hiding && (u.hiding = clearTimeout(u.hiding)),
      u.containerStyle.display = "inline", u.hidden = !1)
  };
  u.preventTooltip = function () {
    u.prevented = !0
  };
  u.block = function () {
    u.blocked = !0;
    u.containerStyle.display = "none"
  };
  u.unblock = function (a) {
    u.blocked = !1;
    a && (u.containerStyle.display = u.hidden && "none" || "inline")
  };
  b.fn.tooltip = function (c, d, f) {
    d && (d = .4 * (void 0 === d.opacity ? 1 : d.opacity), b.svg ? c.boxShadow = "1px 1px 3px rgba(64,64,64," + d + ")" : c.filter = 'progid:DXImageTransform.Microsoft.Shadow(Strength=2, Direction=135, Color="#404040", shadowOpacity="' + d / 2 + '")');
    this.__tipStyle =
      new X(c);
    this.__tipCp = this.canvas && a(this.canvas.parentNode, !0) || {};
    this.__tipConstrain = Boolean(f);
    return this
  };
  b.el.trackTooltip = function (a) {
    var b = !!this.__tiptracking;
    if (void 0 === a || (a = !!a) === b)return this;
    a ? q ? this.touchstart(u.onelement) : (this.mouseover(u.onelement), this.mousemove(u.onredraw), this.mouseout(u.onhide)) : q ? this.untouchstart(u.onelement) : (this.unmouseover(u.onelement), this.unmousemove(u.onredraw), this.unmouseout(u.onhide));
    this.__tiptracking = a;
    return this
  };
  b.el.tooltip = function (a, c,
                           d, f, g) {
    u.setup();
    b.el.tooltip = function (a, b, c, d, f) {
      b = !1 === a || void 0 === a || "" === a;
      this.__tipScatted = void 0 === d ? this.__tipScatted : !d;
      void 0 === this.__tipScatted && (this.__tipScatted = !0);
      null !== f && (this.__tip_blocked = f);
      b ^ !this.__tipText && (this.__tipNeeded = !b);
      this.__tipText = a;
      if (u.currentTarget === this && a !== u.text && !u.hidden)u[b ? "hide" : "redraw"]();
      return this
    };
    return b.el.tooltip.call(this, a, c, d, f, g)
  };
  d.core._setTooltipZIndex = function (a) {
    a = parseInt(a, 10);
    u && !isNaN(a) && (u.defaultContainerStyle.zIndex = a, u.containerStyle &&
    (u.containerStyle.zIndex = a))
  }
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-smartlabel", function () {
  var d = this.hcLib, k = d.isIE, B = d.hasSVG, t = Math.max, E = this.window, b = / HtmlUnit/.test(E.navigator.userAgent), K = E.document, G = / AppleWebKit\//.test(E.navigator.userAgent), c = !!K.createElement("canvas").getContext, l = !(!c || !K.createElement("canvas").getContext("2d").measureText), E = function () {
    function a(a, b, c) {
      if (!a || !a.length)return 0;
      var d = c.getWidthFunction(), f = 0, g = 0, g = d(a), m = g / a.length;
      c = b;
      f = Math.ceil(b / m);
      if (g < b)return a.length -
        1;
      f > a.length && (c = b - g, f = a.length);
      for (; 0 < c;)if (c = b - d(a.substr(0, f)), g = Math.floor(c / m))f += g; else return f;
      for (; 0 > c;)if (c = b - d(a.substr(0, f)), g = Math.floor(c / m))f += g; else break;
      return f
    }

    function q(a, b) {
      b = 5 < b ? b : 5;
      this.maxContainers = 20 > b ? b : 20;
      this.last = this.first = null;
      this.containers = {};
      this.length = 0;
      this.rootNode = a;
      if (X) {
        var c = K.createElementNS("http://www.w3.org/2000/svg", "svg");
        c.setAttributeNS("http://www.w3.org/2000/svg", "xlink", "http://www.w3.org/1999/xlink");
        c.setAttributeNS("http://www.w3.org/2000/svg",
          "height", "0");
        c.setAttributeNS("http://www.w3.org/2000/svg", "width", "0");
        this.svgRoot = c;
        this.rootNode.appendChild(c)
      }
    }

    function E(a, c, d) {
      if ("undefined" !== typeof a && "object" !== typeof a) {
        this.id = a;
        var f;
        "string" === typeof c && (c = K.getElementById(c));
        a:{
          if (c && (c.offsetWidth || c.offsetHeight)) {
            if (c.appendChild) {
              c.appendChild(c = K.createElement("div"));
              c.className = "fusioncharts-smartlabel-container";
              c.setAttribute("aria-hidden", "true");
              c.setAttribute("role", "presentation");
              a = c;
              break a
            }
          } else if ((a = K.getElementsByTagName("body")[0]) &&
            a.appendChild) {
            c = K.createElement("div");
            c.className = "fusioncharts-smartlabel-container";
            c.setAttribute("aria-hidden", "true");
            c.setAttribute("role", "presentation");
            a.appendChild(c);
            a = c;
            break a
          }
          a = void 0
        }
        a = this.parentContainer = a;
        a.innerHTML = "WgI";
        if (b || !a.offsetHeight && !a.offsetWidth)X = !0;
        a.innerHTML = "";
        for (f in g)a.style[f] = g[f];
        this.containerManager = new q(a, 10);
        this.showNoEllipses = !d;
        this.init = !0;
        this.style = {};
        this.setStyle()
      }
    }

    var C = d.supportedStyle, f = {
      fontWeight: 1, "font-weight": 1, fontStyle: 1, "font-style": 1,
      fontSize: 1, "font-size": 1, fontFamily: 1, "font-family": 1
    }, g = {
      position: "absolute",
      top: "-9999em",
      left: "-9999em",
      whiteSpace: "nowrap",
      padding: "0px",
      width: "1px",
      height: "1px",
      overflow: "hidden"
    }, s = G ? 0 : 4.5, n = 0, Q = /\b_SmartLabel\b/, A = /\b_SmartLabelBR\b/, m = /(<[^<\>]+?\>)|(&(?:[a-z]+|#[0-9]+);|.)/ig, P = RegExp("\\<span[^\\>]+?_SmartLabel[^\\>]{0,}\\>(.*?)\\<\\/span\\>", "ig"), z = /<[^>][^<]*[^>]+>/i, X = !1, u = 0, ka = 0, ha, ba, Z;
    K.getElementsByClassName ? (ha = "getElementsByClassName", ba = "_SmartLabel", Z = !0) : (ha = "getElementsByTagName",
      ba = "span", Z = !1);
    q.prototype = {
      get: function (a) {
        var b = this.containers, c = this.length, d = this.maxContainers, f, g = "", m = "", m = this.getCanvasFont(a);
        for (f in C)void 0 !== a[f] && (g += C[f] + ":" + a[f] + ";");
        if (!g)return !1;
        if (b[g])g = b[g], this.first !== g && (g.prev && (g.prev.next = g.next), g.next && (g.next.prev = g.prev), g.next = this.first, g.next.prev = g, this.last === g && (this.last = g.prev), g.prev = null, this.first = g); else {
          if (c >= d)for (a = c - d + 1; a--;)this.removeContainer(this.last);
          g = this.addContainer(g, m)
        }
        return g
      }, getCanvasFont: function (a) {
        var b,
          d = [];
        if (!c || !l)return !1;
        for (b in f)void 0 !== a[b] && d.push(a[b]);
        return d.join(" ")
      }, setMax: function (a) {
        var b = this.length;
        a = 5 < a ? a : 5;
        a = 20 > a ? a : 20;
        if (a < b) {
          for (b -= a; b--;)this.removeContainer(this.last);
          this.length = a
        }
        this.maxContainers = a
      }, addContainer: function (a, b) {
        var c, d;
        this.containers[a] = d = {
          next: null,
          prev: null,
          node: null,
          ellipsesWidth: 0,
          lineHeight: 0,
          dotWidth: 0,
          avgCharWidth: 4,
          keyStr: a,
          canvasStr: b,
          charCache: {}
        };
        d.next = this.first;
        d.next && (d.next.prev = d);
        this.first = d;
        this.last || (this.last = d);
        this.length +=
          1;
        c = d.node = K.createElement("div");
        this.rootNode.appendChild(c);
        k && !B ? c.style.setAttribute("cssText", a) : c.setAttribute("style", a);
        c.setAttribute("aria-hidden", "true");
        c.setAttribute("role", "presentation");
        c.style.display = "inline-block";
        c.innerHTML = "WgI";
        d.lineHeight = c.offsetHeight;
        d.avgCharWidth = c.offsetWidth / 3;
        X ? (c = d.svgText = K.createElementNS("http://www.w3.org/2000/svg", "text"), c.setAttribute("style", a), this.svgRoot.appendChild(c), c.textContent = "WgI", d.lineHeight = c.getBBox().height, d.avgCharWidth =
          (c.getBBox().width - s) / 3, c.textContent = "...", d.ellipsesWidth = c.getBBox().width - s, c.textContent = ".", d.dotWidth = c.getBBox().width - s) : b ? (c = d.canvas = K.createElement("canvas"), c.style.height = c.style.width = "0px", this.rootNode.appendChild(c), d.context = c = c.getContext("2d"), c.font = b, d.ellipsesWidth = c.measureText("...").width, d.dotWidth = c.measureText(".").width) : (c.innerHTML = "...", d.ellipsesWidth = c.offsetWidth, c.innerHTML = ".", d.dotWidth = c.offsetWidth, c.innerHTML = "");
        return d
      }, removeContainer: function (a) {
        var b =
          a.keyStr;
        b && this.length && a && (--this.length, a.prev && (a.prev.next = a.next), a.next && (a.next.prev = a.prev), this.first === a && (this.first = a.next), this.last === a && (this.last = a.prev), a.node.parentNode.removeChild(a.node), a.canvas && a.canvas.parentNode.removeChild(a.canvas), delete this.containers[b])
      }, dispose: function () {
        var a, b = this.containers;
        this.maxContainers = null;
        for (a in b)this.removeContainer(b[a]);
        this.rootNode.parentNode.removeChild(this.rootNode);
        this.last = this.first = this.rootNode = null
      }
    };
    q.prototype.constructor =
      q;
    E.prototype = {
      dispose: function () {
        this.init && (this.containerManager.dispose(), delete this.container, delete this.context, delete this.cache, delete this.containerManager, delete this.containerObj, delete this.id, delete this.style, delete this.parentContainer, delete this.showNoEllipses)
      }, useEllipsesOnOverflow: function (a) {
        this.init && (this.showNoEllipses = !a)
      }, getWidthFunction: function () {
        var a = this.context, b = this.container, c = this.containerObj.svgText;
        return c ? function (a) {
          var b;
          c.textContent = a;
          a = c.getBBox();
          b = a.width - s;
          1 > b && (b = a.width);
          return b
        } : a ? function (b) {
          return a.measureText(b).width
        } : function (a) {
          b.innerHTML = a;
          return b.offsetWidth
        }
      }, getSmartText: function (b, c, d, f) {
        if (!this.init)return !1;
        if (void 0 === b || null === b)b = "";
        var g = {
          text: b,
          maxWidth: c,
          maxHeight: d,
          width: null,
          height: null,
          oriTextWidth: null,
          oriTextHeight: null,
          oriText: b,
          isTruncated: !1
        }, l = !1, k, s, q = 0, h, J, L, C = -1, B = l = -1;
        s = this.container;
        var E = this.context, G = 0;
        L = 0;
        var S, Fa, Da;
        Da = [];
        var na = 0, ua = this.showNoEllipses ? "" : "...";
        J = this.lineHeight;
        var Ea, G = [],
          C = k = -1;
        Ea = function (a) {
          a = a.replace(/^\s\s*/, "");
          for (var b = /\s/, c = a.length; b.test(a.charAt(--c)););
          return a.slice(0, c + 1)
        };
        l = -1;
        Fa = this.getWidthFunction();
        if (s) {
          if (!X) {
            s.innerHTML = b;
            g.oriTextWidth = l = s.offsetWidth;
            g.oriTextHeight = L = s.offsetHeight;
            if (L <= d && l <= c)return g.width = g.oriTextWidth = l, g.height = g.oriTextHeight = L, g;
            if (J > d)return g.text = "", g.width = g.oriTextWidth = 0, g.height = g.oriTextHeight = 0, g
          }
          b = Ea(b).replace(/(\s+)/g, " ");
          l = z.test(b);
          J = this.showNoEllipses ? c : c - n;
          if (l) {
            q = b.replace(m, "$2");
            b = b.replace(m,
              '$1<span class="_SmartLabel">$2</span>');
            b = b.replace(/(<br\s*\/*\>)/g, '<span class="_SmartLabel _SmartLabelBR">$1</span>');
            s.innerHTML = b;
            na = s[ha](ba);
            E = 0;
            for (Fa = na.length; E < Fa; E += 1)if (b = na[E], Z || Q.test(b.className))Ea = b.innerHTML, "" !== Ea && (" " === Ea ? C = G.length : "-" === Ea && (k = G.length), G.push({
              spaceIdx: C,
              dashIdx: k,
              elem: b
            }), Da.push(Ea));
            na = 0;
            k = G.length;
            u = G[0].elem.offsetWidth;
            if (u > c)return g.text = "", g.width = g.oriTextWidth = g.height = g.oriTextHeight = 0, g;
            u > J && !this.showNoEllipses && (J = c - 2 * ka, J > u ? ua = ".." : (J =
              c - ka, J > u ? ua = "." : (J = 0, ua = "")));
            Da = G[0].elem.offsetLeft;
            E = G[0].elem.offsetTop;
            if (f)for (; na < k; na += 1)b = G[na].elem, Fa = b.offsetLeft - Da + b.offsetWidth, Fa > J && (S || (S = na), s.offsetWidth > c && (h = na, na = k)); else for (; na < k; na += 1)b = G[na].elem, Ea = b.offsetHeight + (b.offsetTop - E), Fa = b.offsetLeft - Da + b.offsetWidth, f = null, Fa > J ? (S || (S = na), Fa > c && (l = G[na].spaceIdx, C = G[na].dashIdx, l > B ? (G[l].elem.innerHTML = "<br/>", B = l) : C > B ? (G[C].elem.innerHTML = C === na ? "<br/>-" : "-<br/>", B = C) : b.parentNode.insertBefore(f = K.createElement("br"), b), b.offsetHeight +
            b.offsetTop > d ? (f ? f.parentNode.removeChild(f) : B === C ? G[C].elem.innerHTML = "-" : G[l].elem.innerHTML = " ", h = na, na = k) : S = null)) : Ea > d && (h = na, na = k);
            if (h < k) {
              g.isTruncated = !0;
              S = S ? S : h;
              for (na = k - 1; na >= S; --na)b = G[na].elem, b.parentNode.removeChild(b);
              for (; 0 <= na; --na)b = G[na].elem, A.test(b.className) ? b.parentNode.removeChild(b) : na = 0
            }
            g.text = s.innerHTML.replace(P, "$1");
            g.isTruncated && (g.text += ua, g.tooltext = q)
          } else {
            Da = b.split("");
            k = Da.length;
            s = "";
            h = [];
            S = Da[0];
            this.cache[S] ? u = this.cache[S].width : (u = Fa(S), this.cache[S] = {width: u});
            if (J > u)h = b.substr(0, a(b, J, this)).split(""), na = h.length; else {
              if (u > c)return g.text = "", g.width = g.oriTextWidth = g.height = g.oriTextHeight = 0, g;
              ua && (J = c - 2 * ka, J > u ? ua = ".." : (J = c - ka, J > u ? ua = "." : (J = 0, ua = "")))
            }
            G = Fa(h.join(""));
            L = this.lineHeight;
            if (f) {
              for (; na < k; na += 1)if (S = h[na] = Da[na], this.cache[S] ? u = this.cache[S].width : (u = Fa(S), this.cache[S] = {width: u}), G += u, G > J && (s || (s = h.slice(0, -1).join("")), G > c))return g.text = Ea(s) + ua, g.tooltext = g.oriText, g.width = Fa(g.text), g.height = this.lineHeight, g;
              g.text = h.join("");
              g.width =
                G;
              g.height = this.lineHeight
            } else {
              for (; na < k; na += 1)if (S = h[na] = Da[na], " " !== S || E || (S = "&nbsp;"), this.cache[S] ? u = this.cache[S].width : (u = Fa(S), this.cache[S] = {width: u}), G += u, G > J && (s || (s = h.slice(0, -1).join("")), G > c)) {
                l = b.substr(0, h.length).lastIndexOf(" ");
                C = b.substr(0, h.length).lastIndexOf("-");
                l > B ? (G = Fa(h.slice(B + 1, l).join("")), h.splice(l, 1, "<br/>"), B = l, f = l + 1) : C > B ? (C === h.length - 1 ? (G = Fa(h.slice(B + 1, l).join("")), h.splice(C, 1, "<br/>-")) : (G = Fa(h.slice(B + 1, l).join("")), h.splice(C, 1, "-<br/>")), B = C, f = C + 1) : (h.splice(h.length -
                1, 1, "<br/>" + Da[na]), l = h.length - 2, G = Fa(h.slice(B + 1, l + 1).join("")), B = l, f = na);
                L += this.lineHeight;
                if (L > d)return g.text = Ea(s) + ua, g.tooltext = g.oriText, g.width = c, g.height = L - this.lineHeight, g;
                q = t(q, G);
                s = null;
                S = a(b.substr(f), J, this);
                G = Fa(b.substr(f, S || 1));
                h.length < f + S && (h = h.concat(b.substr(h.length, f + S - h.length).split("")), na = h.length - 1)
              }
              q = t(q, G);
              g.text = h.join("");
              g.width = q;
              g.height = L
            }
            return g
          }
          g.height = s.offsetHeight;
          g.width = s.offsetWidth
        } else g.error = Error("Body Tag Missing!");
        return g
      }, setStyle: function (a) {
        if (!this.init)return !1;
        if (a !== this.style || this.styleNotSet) {
          a || (a = this.style);
          var b = a, c = b.fontSize = b.fontSize || "12px";
          b.lineHeight = b.lineHeight || b["line-height"] || 1.2 * parseInt(c, 10) + "px";
          this.style = a;
          (this.containerObj = a = this.containerManager.get(a)) ? (this.container = a.node, this.context = a.context, this.cache = a.charCache, this.lineHeight = a.lineHeight, n = a.ellipsesWidth, ka = a.dotWidth, this.styleNotSet = !1) : this.styleNotSet = !0
        }
      }, getTextSize: function (a, b, c) {
        if (!this.init)return !1;
        var d = {
          text: a, width: null, height: null, oriTextWidth: null,
          oriTextHeight: null, isTruncated: !1
        }, f = this.container;
        f && (f.innerHTML = a, d.oriTextWidth = f.offsetWidth, d.oriTextHeight = f.offsetHeight, d.width = Math.min(d.oriTextWidth, b), d.height = Math.min(d.oriTextHeight, c), d.width < d.oriTextWidth || d.height < d.oriTextHeight) && (d.isTruncated = !0);
        return d
      }, getOriSize: function (a) {
        if (!this.init)return !1;
        var b = {text: a, width: null, height: null}, c = this.container, d = this.getWidthFunction(), f = 0;
        if (X) {
          a = a.split(/(<br\s*\/*\>)/g);
          c = a.length;
          for (b.height = this.lineHeight * c; c--;)f = t(f, d(a[c]));
          b.width = f
        } else c && (c.innerHTML = a, b.width = c.offsetWidth, b.height = c.offsetHeight);
        return b
      }
    };
    return E.prototype.constructor = E
  }();
  d.SmartLabelManager = E
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-numberformatter", function () {
  var d = this, k = d.hcLib, B = k.pluckNumber, t = k.extend2, E = k.getValidValue, b = k.pluck, K = k.getFirstValue, G = Math.abs, c = Math.pow, l = Math.round, a = function (a) {
    return a && a.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&")
  }, q = {}, S = function (a) {
    var b = [], c;
    for (c in a)b.push(c + "_" + a[c]);
    b.sort();
    return b.join(",")
  }, C = function (a) {
    var b = {}, c;
    for (c in a)b[c.toLowerCase()] = a[c];
    return b
  };
  k.NumberFormatter = function () {
    function d(a, b, f) {
      var g;
      if (0 >= b)return l(a) + "";
      if (isNaN(b))return a += "", 12 < a.length && -1 != a.indexOf(".") && (b = 12 - a.split(".")[0].length, g = c(10, b), a = l(a * g) / g + ""), a;
      g = c(10, b);
      a = l(a * g) / g + "";
      if (1 == f)for (-1 == a.indexOf(".") && (a += ".0"), f = a.split("."), b -= f[1].length, f = 1; f <= b; f++)a += "0";
      return a
    }

    function g(a, b, c, d, f) {
      var g = Number(a), l = "", k = !1, n = "", s = "", q = n = 0;
      if (isNaN(g))return "";
      if (1E15 < g)return g.toExponential(f ? 1 : 14);
      n = 0;
      q = a.length;
      -1 != a.indexOf(".") && (l = a.substring(a.indexOf(".") + 1, a.length), q = a.indexOf("."));
      0 > g && (k = !0, n = 1);
      n =
        a.substring(n, q);
      a = n.length;
      f = d.length - 1;
      g = d[f];
      if (a < g)s = n; else for (; a >= g;)s = (a - g ? c : "") + n.substr(a - g, g) + s, a -= g, g = 0 >= --f ? d[0] : d[f], a < g && (s = n.substring(a, 0) + s);
      "" != l && (s = s + b + l);
      !0 === k && (s = "-" + s);
      return s
    }

    var k, n = {
      formatnumber: "1",
      formatnumberscale: "1",
      defaultnumberscale: "",
      numberscaleunit: ["K", "M"],
      numberscalevalue: [1E3, 1E3],
      numberprefix: "",
      numbersuffix: "",
      decimals: "",
      forcedecimals: "0",
      yaxisvaluedecimals: "2",
      decimalseparator: ".",
      thousandseparator: ",",
      thousandseparatorposition: [3],
      indecimalseparator: "",
      inthousandseparator: "",
      sformatnumber: "1",
      sformatnumberscale: "0",
      sdefaultnumberscale: "",
      snumberscaleunit: ["K", "M"],
      snumberscalevalue: [1E3, 1E3],
      snumberprefix: "",
      snumbersuffix: "",
      sdecimals: "2",
      sforcedecimals: "0",
      syaxisvaluedecimals: "2",
      xFormatNumber: "0",
      xFormatNumberScale: "0",
      xDefaultNumberScale: "",
      xNumberScaleUnit: ["K", "M"],
      xNumberScaleValue: [1E3, 1E3],
      xNumberPrefix: "",
      xNumberSuffix: ""
    }, q = {mscombidy2d: {formatnumberscale: "1"}}, A = function (c, d, f) {
      var g, l, k, s, A, C, G, S, Ka, $ = d.name, aa = t({}, n), qa, ga, R, T, h, J,
        L, U, da, fa, xa;
      (k = q[$]) && (aa = t(aa, k));
      this.csConf = aa;
      this.chartAPI = d;
      E(c.numberscaleunit) && (g = c.numberscaleunit.split(","));
      if (l = E(c.snumberscaleunit, c.numberscaleunit))l = l.split(",");
      if (k = E(c.xnumberscaleunit, c.numberscaleunit))k = k.split(",");
      if (s = E(c.ticknumberscaleunit, c.numberscaleunit))s = s.split(",");
      if (A = E(c.ynumberscaleunit, c.numberscaleunit))A = A.split(",");
      E(c.numberscalevalue) && (C = c.numberscalevalue.split(","));
      if (ga = E(c.snumberscalevalue, c.numberscalevalue))ga = ga.split(",");
      if (G = E(c.xnumberscalevalue,
          c.numberscalevalue))G = G.split(",");
      if (S = E(c.ticknumberscalevalue, c.numberscalevalue))S = S.split(",");
      if (Ka = E(c.ynumberscalevalue, c.numberscalevalue))Ka = Ka.split(",");
      if (E(c.thousandseparatorposition))for (qa = c.thousandseparatorposition.split(","), R = qa.length, h = n.thousandseparatorposition[0]; R--;)T = parseInt(qa[R], 10), 0 >= T && (T = h), h = qa[R] = T;
      d || (d = {});
      R = B(c.scalerecursively, 0);
      T = B(c.sscalerecursively, R);
      h = B(c.xscalerecursively, R);
      J = B(c.maxscalerecursion, -1);
      L = B(c.smaxscalerecursion, J);
      U = B(c.xmaxscalerecursion,
        J);
      da = E(c.scaleseparator, " ");
      fa = E(c.sscaleseparator, da);
      xa = E(c.xscaleseparator, da);
      J || (J = -1);
      this.baseConf = g = {
        cacheStore: [],
        formatnumber: b(c.formatnumber, d.formatnumber, aa.formatnumber),
        formatnumberscale: b(c.formatnumberscale, d.formatnumberscale, aa.formatnumberscale),
        defaultnumberscale: K(c.defaultnumberscale, d.defaultnumberscale, aa.defaultnumberscale),
        numberscaleunit: b(g, d.numberscaleunit, aa.numberscaleunit).concat(),
        numberscalevalue: b(C, d.numberscalevalue, aa.numberscalevalue).concat(),
        numberprefix: K(c.numberprefix,
          d.numberprefix, aa.numberprefix),
        numbersuffix: K(c.numbersuffix, d.numbersuffix, aa.numbersuffix),
        decimalprecision: parseInt("auto" === c.decimals ? aa.decimalprecision : b(c.decimals, c.decimalprecision, d.decimals, aa.decimals, d.decimalprecision, aa.decimalprecision), 10),
        forcedecimals: b(c.forcedecimals, d.forcedecimals, aa.forcedecimals),
        decimalseparator: b(c.decimalseparator, d.decimalseparator, aa.decimalseparator),
        thousandseparator: b(c.thousandseparator, d.thousandseparator, aa.thousandseparator),
        thousandseparatorposition: b(qa,
          d.thousandseparatorposition, aa.thousandseparatorposition),
        indecimalseparator: K(c.indecimalseparator, d.indecimalseparator, aa.indecimalseparator),
        inthousandseparator: K(c.inthousandseparator, d.inthousandseparator, aa.inthousandseparator),
        scalerecursively: R,
        maxscalerecursion: J,
        scaleseparator: da
      };
      E(g.inthousandseparator) && (this.baseConf._REGinthousandseparator = new RegExp(a(g.inthousandseparator), "g"));
      E(g.indecimalseparator) && (this.baseConf._REGindecimalseparator = new RegExp(a(g.indecimalseparator)));
      this.Y =
        [];
      f || (f = {
        cacheStore: [],
        formatnumber: g.formatnumber,
        formatnumberscale: g.formatnumberscale,
        defaultnumberscale: g.defaultnumberscale,
        numberscaleunit: g.numberscaleunit.concat(),
        numberscalevalue: g.numberscalevalue.concat(),
        numberprefix: g.numberprefix,
        numbersuffix: g.numbersuffix,
        decimalprecision: g.decimalprecision,
        forcedecimals: g.forcedecimals,
        decimalseparator: g.decimalseparator,
        thousandseparator: g.thousandseparator,
        thousandseparatorposition: g.thousandseparatorposition,
        indecimalseparator: g.indecimalseparator,
        inthousandseparator: g.inthousandseparator,
        scalerecursively: R,
        maxscalerecursion: J,
        scaleseparator: da
      }, d.useScaleRecursively && (f.numberscalevalue && f.numberscalevalue.length) == (f.numberscaleunit && f.numberscaleunit.length) || (f.scalerecursively = R = 0), C = {
        cacheStore: [],
        formatnumber: f.formatnumber,
        formatnumberscale: f.formatnumberscale,
        defaultnumberscale: f.defaultnumberscale,
        numberscaleunit: f.numberscaleunit.concat(),
        numberscalevalue: f.numberscalevalue.concat(),
        numberprefix: f.numberprefix,
        numbersuffix: f.numbersuffix,
        decimalprecision: parseInt(b(c.yaxisvaluedecimals, f.decimalprecision, 2), 10),
        forcedecimals: b(c.forceyaxisvaluedecimals, f.forcedecimals),
        decimalseparator: f.decimalseparator,
        thousandseparator: f.thousandseparator,
        thousandseparatorposition: f.thousandseparatorposition.concat(),
        indecimalseparator: f.indecimalseparator,
        inthousandseparator: f.inthousandseparator,
        scalerecursively: R,
        maxscalerecursion: J,
        scaleseparator: da
      }, ga = {
        cacheStore: [],
        formatnumber: b(c.sformatnumber, d.sformatnumber, n.sformatnumber),
        formatnumberscale: b(c.sformatnumberscale,
          d.sformatnumberscale, n.sformatnumberscale),
        defaultnumberscale: K(c.sdefaultnumberscale, d.sdefaultnumberscale, f.defaultnumberscale),
        numberscaleunit: b(l, d.snumberscaleunit, n.snumberscaleunit).concat(),
        numberscalevalue: b(ga, d.snumberscalevalue, n.snumberscalevalue).concat(),
        numberprefix: K(c.snumberprefix, d.snumberprefix, n.snumberprefix),
        numbersuffix: K(c.snumbersuffix, d.snumbersuffix, n.snumbersuffix),
        decimalprecision: parseInt(b(c.syaxisvaluedecimals, c.sdecimals, c.decimals, d.sdecimals, n.sdecimals), 10),
        forcedecimals: b(c.forcesyaxisvaluedecimals,
          c.sforcedecimals, c.forcedecimals, d.sforcedecimals, n.sforcedecimals),
        decimalseparator: b(c.decimalseparator, d.decimalseparator, n.decimalseparator),
        thousandseparator: b(c.thousandseparator, d.thousandseparator, n.thousandseparator),
        thousandseparatorposition: f.thousandseparatorposition.concat(),
        indecimalseparator: b(c.indecimalseparator, d.indecimalseparator, n.indecimalseparator),
        inthousandseparator: b(c.inthousandseparator, d.inthousandseparator, n.inthousandseparator),
        scalerecursively: T,
        maxscalerecursion: L,
        scaleseparator: fa
      }, l = t({}, ga), l.decimalprecision = parseInt(b(c.sdecimals, c.decimals, c.syaxisvaluedecimals, d.sdecimals, n.sdecimals), 10), l.forcedecimals = b(c.sforcedecimals, c.forcedecimals, c.forcesyaxisvaluedecimals, d.sforcedecimals, n.sforcedecimals), l.cacheStore = [], d.useScaleRecursively && (ga.numberscalevalue && ga.numberscalevalue.length) == (ga.numberscaleunit && ga.numberscaleunit.length) || (ga.scalerecursively = T = 0), /^(bubble|scatter|selectscatter)$/.test($) && (C.formatnumber = b(c.yformatnumber, C.formatnumber),
        C.formatnumberscale = b(c.yformatnumberscale, C.formatnumberscale), C.defaultnumberscale = K(c.ydefaultnumberscale, C.defaultnumberscale), C.numberscaleunit = b(A, C.numberscaleunit), C.numberscalevalue = b(Ka, C.numberscalevalue), C.numberprefix = b(c.ynumberprefix, C.numberprefix), C.numbersuffix = b(c.ynumbersuffix, C.numbersuffix), f.formatnumber = b(c.yformatnumber, f.formatnumber), f.formatnumberscale = b(c.yformatnumberscale, f.formatnumberscale), f.defaultnumberscale = K(c.ydefaultnumberscale, f.defaultnumberscale), f.numberscaleunit =
        b(c.ynumberscaleunit, f.numberscaleunit.concat()), f.numberscalevalue = b(c.ynumberscalevalue, f.numberscalevalue.concat()), f.numberprefix = b(c.ynumberprefix, f.numberprefix), f.numbersuffix = b(c.ynumbersuffix, f.numbersuffix)), /^(mscombidy2d|mscombidy3d)$/.test($) && (ga.formatnumberscale = B(c.sformatnumberscale)), /^(pie2d|pie3d|doughnut2d|doughnut3d|marimekko|pareto2d|pareto3d)$/.test($) && (f.decimalprecision = b(c.decimals, "2")), R && (f.numberscalevalue.push(1), f.numberscaleunit.unshift(f.defaultnumberscale), C.numberscalevalue.push(1),
        C.numberscaleunit.unshift(C.defaultnumberscale)), T && (ga.numberscalevalue.push(1), ga.numberscaleunit.unshift(ga.defaultnumberscale), l.numberscalevalue.push(1), l.numberscaleunit.unshift(l.defaultnumberscale)), this.Y[0] = {
        yAxisLabelConf: C,
        dataLabelConf: f
      }, this.Y[1] = {
        yAxisLabelConf: ga,
        dataLabelConf: l
      }, this.paramLabels = f, this.param1 = C, this.param2 = ga, this.paramLabels2 = l);
      this.paramX = {
        cacheStore: [],
        formatnumber: b(c.xformatnumber, g.formatnumber),
        formatnumberscale: b(c.xformatnumberscale, g.formatnumberscale),
        defaultnumberscale: K(c.xdefaultnumberscale, g.defaultnumberscale),
        numberscaleunit: b(k, g.numberscaleunit.concat()),
        numberscalevalue: b(G, g.numberscalevalue.concat()),
        numberprefix: b(c.xnumberprefix, g.numberprefix),
        numbersuffix: b(c.xnumbersuffix, g.numbersuffix),
        decimalprecision: parseInt(b(c.xaxisvaluedecimals, c.xaxisvaluesdecimals, g.decimalprecision, 2), 10),
        forcedecimals: b(c.forcexaxisvaluedecimals, 0),
        decimalseparator: g.decimalseparator,
        thousandseparator: g.thousandseparator,
        thousandseparatorposition: g.thousandseparatorposition.concat(),
        indecimalseparator: g.indecimalseparator,
        inthousandseparator: g.inthousandseparator,
        scalerecursively: h,
        maxscalerecursion: U,
        scaleseparator: xa
      };
      this.paramLegend = t(t({}, g), {
        cacheStore: [],
        decimalprecision: parseInt(B(c.legendvaluedecimals, g.decimalprecision, 2), 10),
        forcedecimals: B(c.legendvalueforcedecimals, g.forcedecimals, 0),
        formatnumberscale: b(c.legendvalueformatnumberscale, g.formatnumberscale),
        formatnumber: b(c.legendvalueformatnumber, g.formatnumber)
      });
      d.useScaleRecursively && (this.paramX.numberscalevalue &&
      this.paramX.numberscalevalue.length) == (this.paramX.numberscaleunit && this.paramX.numberscaleunit.length) || (this.paramX.scalerecursively = h = 0);
      h && (this.paramX.numberscalevalue.push(1), this.paramX.numberscaleunit.unshift(this.paramX.defaultnumberscale));
      this.paramScale = {
        cacheStore: [],
        formatnumber: b(c.tickformatnumber, g.formatnumber),
        formatnumberscale: b(c.tickformatnumberscale, g.formatnumberscale),
        defaultnumberscale: K(c.tickdefaultnumberscale, g.defaultnumberscale),
        numberscaleunit: b(s, g.numberscaleunit.concat()),
        numberscalevalue: b(S, g.numberscalevalue.concat()),
        numberprefix: b(c.ticknumberprefix, g.numberprefix),
        numbersuffix: b(c.ticknumbersuffix, g.numbersuffix),
        decimalprecision: parseInt(b(c.tickvaluedecimals, g.decimalprecision, "2"), 10),
        forcedecimals: b(c.forcetickvaluedecimals, g.forcedecimals, 0),
        decimalseparator: g.decimalseparator,
        thousandseparator: g.thousandseparator,
        thousandseparatorposition: g.thousandseparatorposition.concat(),
        indecimalseparator: g.indecimalseparator,
        inthousandseparator: g.inthousandseparator,
        scalerecursively: R,
        maxscalerecursion: J,
        scaleseparator: da
      };
      R && (this.paramScale.numberscalevalue.push(1), this.paramScale.numberscaleunit.unshift(this.paramScale.defaultnumberscale));
      this.timeConf = {
        inputDateFormat: b(c.inputdateformat, c.dateformat, "mm/dd/yyyy"),
        outputDateFormat: b(c.outputdateformat, c.inputdateformat, c.dateformat, "mm/dd/yyyy"),
        days: "Sunday Monday Tuesday Wednesday Thursday Friday Saturday".split(" "),
        months: "January February March April May June July August September October November December".split(" "),
        daySuffix: " st nd rd th th th th th th th th th th th th th th th th th st nd rd th th th th th th th st".split(" ")
      };
      this.cleaneValueCacheStore = {};
      this.percentStrCacheStore = {}
    };
    A.prototype = {
      cleaneValueCacheStore: {}, percentStrCacheStore: {}, dispose: function () {
        this.Y && delete this.Y;
        this.cleaneValueCacheStore && delete this.cleaneValueCacheStore;
        this.percentStrCacheStore && delete this.percentStrCacheStore;
        this.paramLabels && delete this.paramLabels;
        this.param1 && delete this.param1;
        this.param2 && delete this.param2;
        this.paramLabels2 && delete this.paramLabels2;
        this.csConf && delete this.csConf;
        this.chartAPI && delete this.chartAPI;
        this.baseConf && delete this.baseConf;
        this.timeConf && delete this.timeConf;
        this.paramX && delete this.paramX;
        this.paramScale && delete this.paramScale
      }, parseMLAxisConf: function (a, c) {
        var d = this.baseConf, f = this.csConf, g = this.chartAPI, l = B(a.scalerecursively, d.scalerecursively), k = B(a.maxscalerecursion, d.maxscalerecursion), s = E(a.scaleseparator, d.scaleseparator), q, A, t, C, Q, S;
        c = B(c, this.Y.length);
        E(a.numberscaleunit) &&
        (q = a.numberscaleunit.split(","));
        E(a.numberscalevalue) && (A = a.numberscalevalue.split(","));
        k || (k = -1);
        if (E(a.thousandseparatorposition))for (t = a.thousandseparatorposition.split(","), C = t.length, S = n.thousandseparatorposition[0]; C--;)(Q = B(G(t[C]))) ? S = Q : Q = S, t[C] = Q;
        d = {
          cacheStore: [],
          formatnumber: b(a.formatnumber, d.formatnumber),
          formatnumberscale: b(a.formatnumberscale, d.formatnumberscale),
          defaultnumberscale: K(a.defaultnumberscale, d.defaultnumberscale),
          numberscaleunit: b(q, d.numberscaleunit).concat(),
          numberscalevalue: b(A,
            d.numberscalevalue).concat(),
          numberprefix: K(a.numberprefix, d.numberprefix),
          numbersuffix: K(a.numbersuffix, d.numbersuffix),
          forcedecimals: b(a.forcedecimals, d.forcedecimals),
          decimalprecision: parseInt("auto" === a.decimals ? f.decimalprecision : b(a.decimals, d.decimalprecision), 10),
          decimalseparator: b(a.decimalseparator, d.decimalseparator),
          thousandseparator: b(a.thousandseparator, d.thousandseparator),
          thousandseparatorposition: b(t, d.thousandseparatorposition),
          indecimalseparator: K(a.indecimalseparator, d.indecimalseparator),
          inthousandseparator: K(a.inthousandseparator, d.inthousandseparator),
          scalerecursively: l,
          maxscalerecursion: k,
          scaleseparator: s
        };
        g.useScaleRecursively && (d.numberscalevalue && d.numberscalevalue.length) == (d.numberscaleunit && d.numberscaleunit.length) || (d.scalerecursively = l = 0);
        g = {
          cacheStore: [],
          formatnumber: d.formatnumber,
          formatnumberscale: d.formatnumberscale,
          defaultnumberscale: d.defaultnumberscale,
          numberscaleunit: d.numberscaleunit.concat(),
          numberscalevalue: d.numberscalevalue.concat(),
          numberprefix: d.numberprefix,
          numbersuffix: d.numbersuffix,
          decimalprecision: parseInt(b(a.yaxisvaluedecimals, d.decimalprecision, 2), 10),
          forcedecimals: b(a.forceyaxisvaluedecimals, d.forcedecimals),
          decimalseparator: d.decimalseparator,
          thousandseparator: d.thousandseparator,
          thousandseparatorposition: d.thousandseparatorposition.concat(),
          indecimalseparator: d.indecimalseparator,
          inthousandseparator: d.inthousandseparator,
          scalerecursively: l,
          maxscalerecursion: k,
          scaleseparator: s
        };
        l && (d.numberscalevalue.push(1), d.numberscaleunit.unshift(d.defaultnumberscale),
          g.numberscalevalue.push(1), g.numberscaleunit.unshift(g.defaultnumberscale));
        this.Y[c] = {dataLabelConf: d, yAxisLabelConf: g}
      }, percentValue: function (a) {
        var b = this.percentStrCacheStore[a];
        void 0 === b && (b = isNaN(this.paramLabels.decimalprecision) ? "2" : this.paramLabels.decimalprecision, b = this.percentStrCacheStore[a] = g(d(a, b, this.paramLabels.forcedecimals), this.paramLabels.decimalseparator, this.paramLabels.thousandseparator, this.paramLabels.thousandseparatorposition) + "%");
        return b
      }, getCleanValue: function (a, b) {
        var c =
          this.cleaneValueCacheStore[a];
        if (void 0 === c) {
          var d = this.baseConf, c = a + "";
          d._REGinthousandseparator && (c = c.replace(d._REGinthousandseparator, ""));
          d._REGindecimalseparator && (c = c.replace(d._REGindecimalseparator, "."));
          c = parseFloat(c);
          c = isFinite(c) ? c : NaN;
          this.cleaneValueCacheStore[a] = c = isNaN(c) ? null : b ? G(c) : c
        }
        return c
      }, dataLabels: function (a, b) {
        var c = this.Y[b] || (b ? this.Y[1] : this.Y[0]), d, c = c && c.dataLabelConf || this.baseConf;
        d = c.cacheStore[a];
        void 0 === d && (d = c.cacheStore[a] = k(a, c));
        return d
      }, yAxis: function (a,
                          b) {
        var c = this.Y[b] || (b ? this.Y[1] : this.Y[0]), d, c = c && c.yAxisLabelConf || this.baseConf;
        d = c.cacheStore[a];
        void 0 === d && (d = c.cacheStore[a] = k(a, c, !0));
        return d
      }, xAxis: function (a) {
        var b = this.paramX.cacheStore[a];
        void 0 === b && (b = this.paramX.cacheStore[a] = k(a, this.paramX, !0));
        return b
      }, sYAxis: function (a) {
        var b = this.Y[1], c, b = b && b.yAxisLabelConf || this.baseConf;
        c = b.cacheStore[a];
        void 0 === c && (c = b.cacheStore[a] = k(a, b));
        return c
      }, scale: function (a) {
        var b = this.paramScale.cacheStore[a];
        void 0 === b && (b = this.paramScale.cacheStore[a] =
          k(a, this.paramScale));
        return b
      }, getCleanTime: function (a) {
        var b;
        this.timeConf.inputDateFormat && Date.parseExact && (b = Date.parseExact(a, this.timeConf.inputDateFormat));
        return b && b.getTime()
      }, legendValue: function (a) {
        var b = this.paramLegend.cacheStore[a];
        void 0 === b && (b = this.paramLegend.cacheStore[a] = k(a, this.paramLegend));
        return b
      }, legendPercentValue: function (a) {
        var b = this.percentStrCacheStore[a], c = this.paramLegend;
        void 0 === b && (b = isNaN(c.decimalprecision) ? "2" : c.decimalprecision, b = this.percentStrCacheStore[a] =
          g(d(a, b, c.forcedecimals), c.decimalseparator, c.thousandseparator, c.thousandseparatorposition) + "%");
        return b
      }, getDateValue: function (a) {
        var b, c, d;
        a && !/\//.test(this.timeConf.inputDateFormat) && (a = a.replace(new RegExp(this.timeConf.inputDateFormat.replace(/[a-z]/ig, "").slice(0, 1), "g"), "/"));
        a = /^dd/.test(this.timeConf.inputDateFormat) && a && a.replace(/(\d{1,2})\/(\d{1,2})\/(\d{2,4})/, "$2/$1/$3") || a;
        b = new Date(a);
        c = b.getTime();
        !c && a && /\:/.test(a) && (a = a.split(":"), c = B(a[0], 0), d = B(a[1], 0), a = B(a[2], 0), c = 23 < c ?
          24 === c && 0 === d && 0 === a ? c : 23 : c, d = 59 < d ? 59 : d, a = 59 < a ? 59 : a, b = new Date, b.setHours(c), b.setMinutes(d), b.setSeconds(a), c = b.getTime());
        return {ms: c, date: b}
      }, getFormattedDate: function (a, c) {
        var d = "object" === typeof a && a || new Date(a), f = this.timeConf, g = b(c, f.outputDateFormat), l = d.getFullYear(), k = d.getMonth(), n = d.getDate(), s = d.getDay(), q = d.getMinutes(), A = d.getSeconds(), d = d.getHours(), q = 9 < q ? "" + q : "0" + q, A = 9 < A ? "" + A : "0" + A, d = 9 < d ? "" + d : "0" + d;
        g.match(/dnl/) && (g = g.replace(/dnl/ig, f.days[s]));
        g.match(/dns/) && (g = g.replace(/dns/ig,
          f.days[s] && f.days[s].substr(0, 3)));
        g.match(/dd/) && (g = g.replace(/dd/ig, n));
        g.match(/mnl/) && (g = g.replace(/mnl/ig, f.months[k]));
        g.match(/mns/) && (g = g.replace(/mns/ig, f.months[k] && f.months[k].substr(0, 3)));
        g.match(/mm/) && (g = g.replace(/mm/ig, k + 1));
        g.match(/yyyy/) && (g = g.replace(/yyyy/ig, l));
        g.match(/yy/) && (g = g.replace(/yy/ig, (l % 1E3 % 100 + "").replace(/^(\d)$/, "0$1")));
        g.match(/hh12/) && (g = g.replace(/hh12/ig, d % 12 || 12));
        g.match(/hh/) && (g = g.replace(/hh/ig, d));
        g.match(/mn/) && (g = g.replace(/mn/ig, q));
        g.match(/ss/) &&
        (g = g.replace(/ss/ig, A));
        g.match(/ampm/) && (g = g.replace(/ampm/ig, 12 > d ? "AM" : "PM"));
        g.match(/ds/) && (g = g.replace(/ds/ig, f.daySuffix[n]));
        return g
      }
    };
    A.prototype.constructor = A;
    k = function (a, c, l) {
      if (null !== a) {
        a = Number(a);
        var k = a + "", n, s, q, A, t;
        n = 1 == c.formatnumberscale ? c.defaultnumberscale : "";
        t = (t = k.split(".")[1]) ? t.length : c.forcedecimals ? "2" : "";
        if (1 == c.formatnumberscale) {
          k = a;
          s = c.numberscalevalue;
          a = c.numberscaleunit;
          n = {};
          var C = c.defaultnumberscale;
          q = 0;
          var G = [], E = [];
          if (c.scalerecursively) {
            for (q = 0; q < s.length; q++)if (A =
                B(s[q]) || 1E3, Math.abs(Number(k)) >= A && q < s.length - 1)C = k % A, k = (k - C) / A, 0 !== C && (G.push(C), E.push(a[q])); else {
              G.push(k);
              E.push(a[q]);
              break
            }
            G.reverse();
            E.reverse();
            n.value = G;
            n.scale = E
          } else {
            if (s.length === a.length)for (q = 0; q < s.length; q++)if (A = B(s[q]) || 1E3, Math.abs(Number(k)) >= A)C = a[q] || "", k = Number(k) / A; else break;
            n.value = k;
            n.scale = C
          }
          s = n;
          a = k = s.value;
          n = s.scale
        }
        if (c.scalerecursively && 0 !== c.formatnumberscale && "0" !== c.formatnumberscale) {
          l = s.value;
          s = s.scale;
          a = -1 == c.maxscalerecursion ? l.length : Math.min(l.length, c.maxscalerecursion);
          if (1 == c.formatnumber)for (k = "", A = 0; A < a; A++)n = 0 === A ? l[A] : Math.abs(l[A]), q = n + "", A == a - 1 && (q = d(n, b(c.decimalprecision, t), c.forcedecimals)), k = k + g(q, c.decimalseparator, c.thousandseparator, c.thousandseparatorposition) + s[A] + (A < a - 1 ? c.scaleseparator : ""); else for (k = "", A = 0; A < a; A++)k = k + (0 === A ? l[A] : Math.abs(l[A]) + "") + s[A] + (A < a - 1 ? c.scaleseparator : "");
          k = (c.numberprefix || "") + k + (c.numbersuffix || "")
        } else 1 == c.formatnumber && (k = d(a, b(c.decimalprecision, t), c.forcedecimals), k = g(k, c.decimalseparator, c.thousandseparator, c.thousandseparatorposition,
          l)), k = (c.numberprefix || "") + k + n + (c.numbersuffix || "");
        return k
      }
    };
    return A
  }();
  d.extend(d.core, {
    formatNumber: function (a, b) {
      b = b && C(b) || {};
      var c = S(b), d;
      q[c] ? d = q[c] : q[c] = d = new k.NumberFormatter(b, {useScaleRecursively: !0});
      return d.dataLabels(a)
    }
  }, !1);
  d.extend(d.core, {
    formatNumber: function (a, b, c, l) {
      c = c && C(c) || {};
      var B = this.jsVars.instanceAPI || {}, A = B.numberFormatter, m;
      "" === S(c) ? A ? m = A : (A = this.getChartData(d.dataFormats.JSON, !0), A = A.data || {}, A = A.chart || {}, c = S(A), q[c] ? m = q[c] : q[c] = m = new k.NumberFormatter(A, B)) :
        (A = this.getChartData(d.dataFormats.JSON, !0), A = A.data || {}, A = A.chart || {}, A = t(t({}, A), c), c = S(A), q[c] ? m = q[c] : q[c] = m = new k.NumberFormatter(A, B));
      switch ((b && b.toLowerCase ? b : "").toLowerCase()) {
        case "yaxisvalues":
          a = m.yAxis(a, l);
          break;
        case "xaxisvalues":
          a = m.xAxis(a);
          break;
        case "scale":
          a = m.scale(a);
          break;
        default:
          a = m.dataLabels(a, l)
      }
      return a
    }
  }, !0)
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-dom", function () {
  var d = this.hcLib, k = this.window, B = k.document, t = d.extend2, E = "ontouchstart"in k;
  (function (b) {
    var d = function () {
      var b = {}, d;
      b.pointerdrag = {
        start: ["mousedown"],
        end: ["mouseup"],
        onStart: ["mousemove"],
        postHandlers: {},
        preHandlers: {}
      };
      b.pointerhover = {start: ["mouseover"], end: ["mouseout"]};
      b.click = {start: ["click"]};
      b.escape = {
        start: ["keydown"], preHandlers: {
          start: function (a) {
            a = a || k.event;
            return a.keyCode && 27 === a.keyCode ? !0 : !1
          }
        }
      };
      E && (d =
        b.pointerdrag, d.start.push("touchstart"), d.end.push("touchend"), d.onStart.push("touchmove"), d.postHandlers.onStart = function (a) {
        a.preventDefault ? a.preventDefault() : a.returnValue = !1
      });
      return b
    }(), G;
    G = t({}, d);
    b.dem = new function () {
      var b = {}, d = {}, a = B.addEventListener ? function (a, b, c) {
        a.addEventListener(b, c, !1)
      } : function (a, b, c) {
        a.attachEvent("on" + b, c)
      }, q = B.removeEventListener ? function (a, b, c) {
        a.removeEventListener(b, c, !1)
      } : function (a, b, c) {
        a.detachEvent("on" + b, c)
      }, E = function (a, b, c) {
        var d = [], f, l, q;
        q = G[b];
        c.start =
          function (b) {
            b = b || k.event;
            for (var d = q.onStart, f = q.end, l = [], m = [], s = d && d.length || 0; s--;)l.push(C(a, d[s], c, "onStart"));
            for (s = f && f.length || 0; s--;)m.push(C(a, f[s], c, "end"));
            c.startUn = c.startUn ? c.startUn.concat(l) : l;
            c.endUn = c.endUn ? c.endUn.concat(m) : m;
            c.state = "start";
            c.closure(b)
          };
        c.onStart = function (a) {
          a = a || k.event;
          c.state = "on";
          if (c.gDef && c.gDef.preHandlers && "function" === typeof c.gDef.preHandlers.onStart)c.gDef.preHandlers.onStart(a);
          c.closure(a);
          if (c.gDef && c.gDef.postHandlers && "function" === typeof c.gDef.postHandlers.onStart)c.gDef.postHandlers.onStart(a)
        };
        c.end = function (a) {
          a = a || k.event;
          for (var b = c.startUn, d = c.endUn, f = b && b.length || 0; f--;)b[f]();
          delete c.startUn;
          c.startUn = [];
          for (f = d && d.length || 0; f--;)d[f]();
          delete c.endUn;
          c.endUn = [];
          c.state = "end";
          c.closure(a)
        };
        if (q)for (b = q.start, l = b.length; l--;)(f = b[l]) && d.push(C(a, f, c, "start"));
        return d
      }, C = function (b, c, d, f) {
        f = f || "closure";
        a(b, c, d[f]);
        return function () {
          q(b, c, d[f])
        }
      }, f = function (a) {
        return function (b) {
          b = b || k.event;
          a.handler.call(a.context || a.elem, {
            data: a.data, type: a.type, state: a.state, isGesture: a.isGesture,
            target: b.target || b.srcElement, originalEvent: b
          })
        }
      };
      return {
        listen: function (a, k, n, q, A) {
          var m = this;
          k = "string" === typeof k ? k.split(" ") : k;
          var t = k.length, z = [], K = function (a, b, c) {
            z.push(function () {
              m.unlisten(a, b, c)
            })
          }, u, ka, ha, ba, Z;
          if (a.ownerDocument && a.ownerDocument === B)for (; t--;)ka = k[t], ba = Boolean(G[ka]), Z = "function" === typeof n ? n : n[t], ha = {
            handler: Z,
            elem: a,
            type: ka,
            isGesture: ba,
            gDef: ba ? G[ka] : null,
            data: q,
            context: A,
            start: [],
            end: [],
            links: {prev: null, next: null}
          }, ha.closure = f(ha), ba ? ((u = d[ka]) || (u = d[ka] = []), u.push(ha),
            E(a, ka, ha)) : ((u = b[ka]) || (u = b[ka] = []), u.push(ha), C(a, ka, ha)), K(a, ka, Z); else for (; t--;)ka = k[t], Z = "function" === typeof n ? n : n[t], ha = {
            handler: Z,
            elem: a,
            type: ka,
            isGesture: ba,
            data: q,
            context: A,
            start: [],
            end: [],
            links: {prev: null, next: null}
          }, ha.closure = f(ha), (u = b[ka]) || (u = b[ka] = []), u.push(ha), C(a, ka, ha), K(a, ka, Z);
          return {
            unlisten: function () {
              for (var a = z.length; a--;)z[a]();
              z.length = 0;
              z = null
            }
          }
        }, unlisten: function (a, f, k) {
          var t, A = !1, m, C;
          if (Boolean(G[f]))for (m = (t = d[f]) && t.length || 0; m--;) {
            if (C = t[m], C.handler === k && C.elem ===
              a) {
              var A = a, z = void 0, B = void 0, u = void 0, z = void 0;
              if (z = G[f])for (z = z.start, u = z.length; u--;)(B = z[u]) && q(A, B, C.start);
              t.splice(m, 1);
              A = !0
            }
          } else for (m = (t = b[f]) && t.length || 0; m--;)C = t[m], C.handler === k && C.elem === a && (q(a, f, C.closure), t.splice(m, 1), A = !0);
          return A
        }, fire: function (a, d, f, l) {
          var k;
          if (a.ownerDocument && a.ownerDocument === B)B.createEvent ? (k = B.createEvent("HTMLEvents"), k.initEvent(d, !0, !0), f && (f.originalEvent ? f.originalEvent = k : t(k, f)), "function" === typeof a[d] && a[d].call(a), a.dispatchEvent(k)) : (k = B.createEventObject(),
            k.eventType = d, f && (f.originalEvent ? f.originalEvent = k : t(k, f)), "function" === typeof a[d] && a[d].call(a), a.fireEvent("on" + d, k)), l && !k.returnValue && l(k); else for (l = (d = b[d]) && d.length || 0; l--;)k = d[l], k.elem === a && k.closure(f)
        }
      }
    }
  })(d || k);
  (function (b) {
    function d(a, b) {
      var c = "";
      B.defaultView && B.defaultView.getComputedStyle ? c = B.defaultView.getComputedStyle(a, "").getPropertyValue(b) : a.currentStyle && (b = b.replace(/\-(\w)/g, function (a, b) {
        return b.toUpperCase()
      }), c = a.currentStyle[b]);
      c = parseInt(c, 10);
      return isNaN(c) ?
        0 : c
    }

    function E(b, c, d, f, g, k, n, t) {
      var A = c / 40, m = l[k || "linear"](f - d, A), B = 0, z = function () {
        var c;
        B < A ? (c = m[B], b.style[g] = d + c + t, a && "opacity" === g && (c = 100 * Number(c), b.style.filter = "progid:DXImageTransform.Microsoft.Alpha(Opacity=" + c + ")"), B += 1, setTimeout(z, 40)) : n && n()
      };
      t = t || "";
      setTimeout(z, 40)
    }

    var c = {
      width: {suffix: "px"},
      height: {suffix: "px"},
      opacity: !0,
      top: {suffix: "px"},
      left: {suffix: "px"}
    }, l = {
      linear: function (a, b) {
        for (var c = [], d = a / b, g = 0; g < b; g += 1)c[g] = d * (g + 1);
        return c
      }
    }, a = /msie/i.test(k.navigator.userAgent) && !k.opera;
    b.danimate = t({
      animate: function (a, b, l, f, g) {
        f = {};
        var k = {}, n = function () {
          A += 1;
          A === t && "function" === typeof g && g()
        }, t = 0, A = 0, m, B;
        if (40 > l) {
          for (B in b)a.style[B] = b[B];
          g && g()
        } else for (B in b)c[B] && (t += 1, f[B] = b[B], k[B] = d(a, B), m = "object" === typeof c[B] && c[B].suffix, E(a, l, k[B], f[B], B, "linear", n, m))
      }
    }, {})
  })(d || k)
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-colormanager", function () {
  var d = this.hcLib, k = d.pluckNumber, B = d.graphics.getDarkColor, t = d.graphics.getLightColor, E = "AFD8F8 F6BD0F 8BBA00 FF8E46 008E8E D64646 8E468E 588526 B3AA00 008ED6 9D080D A186BE CC6600 FDC689 ABA000 F26D7D FFF200 0054A6 F7941C CC3300 006600 663300 6DCFF6".split(" "), b = "8BBA00 F6BD0F FF654F AFD8F8 FDB398 CDC309 B1D0D2 FAD1B9 B8A79E D7CEA5 C4B3CE E9D3BE EFE9AD CEA7A2 B2D9BA".split(" "), K = d.defaultPaletteOptions = {
    paletteColors: [E,
      E, E, E, E],
    bgColor: ["CBCBCB,E9E9E9", "CFD4BE,F3F5DD", "C5DADD,EDFBFE", "A86402,FDC16D", "FF7CA0,FFD1DD"],
    bgAngle: [270, 270, 270, 270, 270],
    bgRatio: ["0,100", "0,100", "0,100", "0,100", "0,100"],
    bgAlpha: ["50,50", "60,50", "40,20", "20,10", "30,30"],
    canvasBgColor: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    canvasBgAngle: [0, 0, 0, 0, 0],
    canvasBgAlpha: ["100", "100", "100", "100", "100"],
    canvasBgRatio: ["", "", "", "", ""],
    canvasBorderColor: ["545454", "545454", "415D6F", "845001", "68001B"],
    canvasBorderAlpha: [100, 100, 100, 90, 100],
    showShadow: [0,
      1, 1, 1, 1],
    divLineColor: ["717170", "7B7D6D", "92CDD6", "965B01", "68001B"],
    divLineAlpha: [40, 45, 65, 40, 30],
    altHGridColor: ["EEEEEE", "D8DCC5", "99C4CD", "DEC49C", "FEC1D0"],
    altHGridAlpha: [50, 35, 10, 20, 15],
    altVGridColor: ["767575", "D8DCC5", "99C4CD", "DEC49C", "FEC1D0"],
    altVGridAlpha: [10, 20, 10, 15, 10],
    anchorBgColor: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    toolTipBgColor: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    toolTipBorderColor: ["545454", "545454", "415D6F", "845001", "68001B"],
    baseFontColor: ["555555", "60634E",
      "025B6A", "A15E01", "68001B"],
    borderColor: ["767575", "545454", "415D6F", "845001", "68001B"],
    borderAlpha: [50, 50, 50, 50, 50],
    legendBgColor: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    legendBorderColor: ["545454", "545454", "415D6F", "845001", "D55979"],
    plotGradientColor: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    plotBorderColor: ["333333", "8A8A8A", "FFFFFF", "FFFFFF", "FFFFFF"],
    plotFillColor: ["767575", "D8DCC5", "99C4CD", "DEC49C", "FEC1D0"],
    bgColor3D: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    bgAlpha3D: ["100",
      "100", "100", "100", "100"],
    bgAngle3D: [90, 90, 90, 90, 90],
    bgRatio3D: ["", "", "", "", ""],
    canvasBgColor3D: ["DDE3D5", "D8D8D7", "EEDFCA", "CFD2D8", "FEE8E0"],
    canvasBaseColor3D: ["ACBB99", "BCBCBD", "C8A06C", "96A4AF", "FAC7BC"],
    divLineColor3D: ["ACBB99", "A4A4A4", "BE9B6B", "7C8995", "D49B8B"],
    divLineAlpha3D: [100, 100, 100, 100, 100],
    legendBgColor3D: ["F0F3ED", "F3F3F3", "F7F0E8", "EEF0F2", "FEF8F5"],
    legendBorderColor3D: ["C6CFB8", "C8C8C8", "DFC29C", "CFD5DA", "FAD1C7"],
    toolTipbgColor3D: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    toolTipBorderColor3D: ["49563A",
      "666666", "49351D", "576373", "681C09"],
    baseFontColor3D: ["49563A", "4A4A4A", "49351D", "48505A", "681C09"],
    anchorBgColor3D: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"]
  }, E = d.colorManager = function (b, c) {
    var l = b.chart, a = d.extend2({}, K), q = c.defaultPaletteOptions || {}, E;
    a || (a = {});
    for (E in q)a[E] = q[E];
    a = this.paletteOptions = a;
    q = this.themeEnabled = l.palettethemecolor;
    this.paletteIndex = (0 < l.palette && 6 > l.palette ? l.palette : k(c.paletteIndex, 1)) - 1;
    this.iterator = 0;
    this.paletteColors = a.paletteColors[this.themeEnabled ? 0 :
      this.paletteIndex];
    E = l.palettecolors;
    void 0 !== E && null !== E && "" !== l.palettecolors && (this.paletteColors = l.palettecolors.split(/\s*\,\s*/));
    this.paletteLen = this.paletteColors.length;
    this.useFlatColors = k(l.useflatdataplotcolor, c.useFlatColor, 0);
    q && (this.paletteIndex = 5, a.bgColor.push(t(q, 35) + "," + t(q, 10)), a.bgAngle.push(270), a.bgRatio.push("0,100"), a.bgAlpha.push("50,50"), a.canvasBgColor.push("FFFFFF"), a.canvasBgAngle.push(0), a.canvasBgAlpha.push("100"), a.canvasBgRatio.push(""), a.canvasBorderColor.push(B(q,
      80)), a.canvasBorderAlpha.push(100), a.showShadow.push(1), a.divLineColor.push(B(q, 20)), a.divLineAlpha.push(40), a.altHGridColor.push(t(q, 20)), a.altHGridAlpha.push(15), a.altVGridColor.push(t(q, 80)), a.altVGridAlpha.push(10), a.anchorBgColor.push("FFFFFF"), a.toolTipBgColor.push("FFFFFF"), a.toolTipBorderColor.push(B(q, 80)), a.baseFontColor.push(q.split && q.split(",")[0]), a.borderColor.push(B(q, 60)), a.borderAlpha.push(50), a.legendBgColor.push("FFFFFF"), a.legendBorderColor.push(B(q, 80)), a.plotGradientColor.push("FFFFFF"),
      a.plotBorderColor.push(B(q, 85)), a.plotFillColor.push(B(q, 85)), a.bgColor3D.push("FFFFFF"), a.bgAlpha3D.push("100"), a.bgAngle3D.push(90), a.bgRatio3D.push(""), a.canvasBgColor3D.push(t(q, 20)), a.canvasBaseColor3D.push(t(q, 40)), a.divLineColor3D.push(B(q, 20)), a.divLineAlpha3D.push(40), a.legendBgColor3D.push("FFFFFF"), a.legendBorderColor3D.push(B(q, 80)), a.toolTipbgColor3D.push("FFFFFF"), a.toolTipBorderColor3D.push(B(q, 80)), a.baseFontColor3D.push(q.split && q.split(",")[0]), a.anchorBgColor3D.push("FFFFFF"), a.tickColor &&
    a.tickColor.push(B(q, 90)), a.trendDarkColor && a.trendDarkColor.push(B(q, 90)), a.trendLightColor && a.trendLightColor.push(t(q, a.TrendLightShadeOffset)), a.msgLogColor && a.msgLogColor.push(t(q, 80)), a.dialColor && a.dialColor.push(B(q, 95) + ",FFFFFF," + B(q, 95)), a.dialBorderColor && a.dialBorderColor.push(B(q, 95) + ",FFFFFF," + B(q, 95)), a.pivotColor && a.pivotColor.push(t(q, 95) + ",FFFFFF," + t(q, 95)), a.pivotBorderColor && a.pivotBorderColor.push(B(q, 95) + ",FFFFFF," + B(q, 95)), a.pointerBorderColor && a.pointerBorderColor.push(B(q,
      75)), a.pointerBgColor && a.pointerBgColor.push(B(q, 75)), a.thmBorderColor && a.thmBorderColor.push(B(q, 90)), a.thmFillColor && a.thmFillColor.push(t(q, 55)), a.cylFillColor && a.cylFillColor.push(t(q, 55)), a.periodColor && a.periodColor.push(t(q, 10)), a.winColor && a.winColor.push("666666"), a.lossColor && a.lossColor.push("CC0000"), a.drawColor && a.drawColor.push("666666"), a.scorelessColor && a.scorelessColor.push("FF0000"), a.gridColor && a.gridColor.push(t(q, 30)), a.categoryBgColor && a.categoryBgColor.push(t(q, 10)), a.dataTableBgColor &&
    a.dataTableBgColor.push(t(q, 10)), a.gridResizeBarColor && a.gridResizeBarColor.push(B(q, 90)), a.scrollBarColor && a.scrollBarColor.push(t(q, 50)))
  };
  E.prototype = {
    getColor: function (b) {
      return this.paletteOptions[b][this.paletteIndex]
    }, getPlotColor: function (b) {
      var c = this.paletteColors;
      b = this.useFlatColors ? this.getColor("plotFillColor") : c[b % this.paletteLen];
      b || (this.iterator === this.paletteLen && (this.iterator = 0), b = c[this.iterator], this.iterator += 1);
      return b
    }, parseColorMix: function (b, c) {
      var d = [], a, k, E, C, f, g, s,
        n, K, A;
      c = c.replace(/\s/g, "");
      c = c.toLowerCase();
      if ("" === c || null === c || void 0 === c)d = [b]; else for (k = c.split(","), E = b.split(","), C = Math.max(k.length, E.length, 1), f = k[0], g = E[0], K = /[\{\}]/ig, A = 0; A < C; A++)s = (k[A] || f).replace(K, ""), n = E[A] || g, "color" == s ? d.push(n) : "light" == s.substr(0, 5) ? (a = s.indexOf("-"), a = -1 == a ? 1 : s.substr(a + 1, s.length - a), a = 100 - a, d.push(t(n, a))) : "dark" == s.substr(0, 4) ? (a = s.indexOf("-"), a = -1 == a ? 1 : s.substr(a + 1, s.length - a), a = 100 - a, d.push(B(n, a))) : d.push(s);
      return d
    }, parseAlphaList: function (b, c) {
      var d =
        b.split(","), a = [], q, t = 100, C;
      for (C = 0; C < c; C++)q = k(d[C]), void 0 !== q && null !== q && (t = q), a[C] = t;
      return a.join()
    }, parseRatioList: function (b, c) {
      var d = b.split(","), a = [], k = 0, t, C;
      for (C = 0; C < c; C++)t = d[C], t = isNaN(t) || void 0 === t ? 0 : Math.abs(Number(t)), t = 100 < t ? 100 : t, a[C] = t, k += t;
      k = 100 < k ? 100 : k;
      if (d.length < c)for (C = d.length; C < c; C++)a[C] = (100 - k) / (c - d.length);
      a[-1] = 0;
      return a.join()
    }
  };
  E.prototype.constructor = E;
  d.defaultGaugePaletteOptions = {
    paletteColors: [b, b, b, b, b],
    bgColor: ["CBCBCB,E9E9E9", "CFD4BE,F3F5DD", "C5DADD,EDFBFE",
      "A86402,FDC16D", "FF7CA0,FFD1DD"],
    bgAngle: [270, 270, 270, 270, 270],
    bgRatio: ["0,100", "0,100", "0,100", "0,100", "0,100"],
    bgAlpha: ["50,50", "60,50", "40,20", "20,10", "30,30"],
    toolTipBgColor: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    toolTipBorderColor: ["545454", "545454", "415D6F", "845001", "68001B"],
    baseFontColor: ["555555", "60634E", "025B6A", "A15E01", "68001B"],
    tickColor: ["333333", "60634E", "025B6A", "A15E01", "68001B"],
    trendDarkColor: ["333333", "60634E", "025B6A", "A15E01", "68001B"],
    trendLightColor: ["f1f1f1", "F3F5DD",
      "EDFBFE", "FFF5E8", "FFD1DD"],
    pointerBorderColor: ["545454", "60634E", "415D6F", "845001", "68001B"],
    pointerBgColor: ["545454", "60634E", "415D6F", "845001", "68001B"],
    canvasBgColor: ["FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF", "FFFFFF"],
    canvasBgAngle: [0, 0, 0, 0, 0],
    canvasBgAlpha: ["100", "100", "100", "100", "100"],
    canvasBgRatio: ["", "", "", "", ""],
    canvasBorderColor: ["545454", "545454", "415D6F", "845001", "68001B"],
    canvasBorderAlpha: [100, 100, 100, 90, 100],
    altHGridColor: ["EEEEEE", "D8DCC5", "99C4CD", "DEC49C", "FEC1D0"],
    altHGridAlpha: [50,
      35, 10, 20, 15],
    altVGridColor: ["767575", "D8DCC5", "99C4CD", "DEC49C", "FEC1D0"],
    altVGridAlpha: [10, 20, 10, 15, 10],
    borderColor: ["767575", "545454", "415D6F", "845001", "68001B"],
    borderAlpha: [50, 50, 50, 50, 50],
    legendBgColor: ["ffffff", "ffffff", "ffffff", "ffffff", "ffffff"],
    legendBorderColor: ["545454", "545454", "415D6F", "845001", "D55979"],
    plotFillColor: ["767575", "D8DCC5", "99C4CD", "DEC49C", "FEC1D0"],
    plotBorderColor: ["999999", "8A8A8A", "6BA9B6", "C1934D", "FC819F"],
    msgLogColor: ["717170", "7B7D6D", "92CDD6", "965B01", "68001B"],
    TrendLightShadeOffset: 30
  }
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-annotations", function () {
  var d = this, k = d.core, B = d.hcLib, t = d.window, E = /msie/i.test(t.navigator.userAgent) && !t.opera, b = B.addEvent, K = B.removeEvent, G = B.hasTouch, c = t.Number, l = G ? 6 : 5, a = "rgba(192,192,192," + (E ? .002 : 1E-6) + ")", E = t.Math, q = E.min, S = E.max, C = E.sin, f = E.cos, g = E.PI, s = g / 180, n = E.abs, Q = d.extend, A = B.pluck, m = B.pluckNumber, P = B.graphics.convertColor, z = B.getValidValue, X = B.parseUnsafeString, u = B.setImageDisplayMode, ka = B.graphics.parseColor, ha = B.setLineHeight,
    ba = B.getMouseCoordinate, Z = {style: {}}, za = B.toRaphaelColor, Aa = function (a, b) {
      return {start: -a, end: -b, angle: a - b}
    }, Ka = function (a, b, d, f, g) {
      var l, k, m = 0, n = 0;
      k = void 0 === b || null === b ? 1 : b;
      var s;
      if (!a || !a.toString)return {value: d, hasDynamicMacros: !1};
      a = a.toString();
      a = a.toLowerCase().replace(/\s/g, "");
      if (d = a.match(/^[\+\-]?\d+(\.\d+)?|[\+\-]\d+(\.\d+)?/g)) {
        for (b = 0; b < d.length; b += 1)m += Number(d[b]) || 0;
        m *= k
      }
      if (d = a.match(/^[\+\-]?(\$[a-z0-9\.]+)|[\+\-](\$[a-z0-9\.]+)/g))for (b = 0; b < d.length; b += 1) {
        l = d[b];
        var q = f, t = g, u = l.split("."),
          A = void 0, z = void 0, C = 0;
        for (k = void 0; A = u.shift();)switch (typeof(z = q[A])) {
          case "object":
            q = z[A];
            break;
          case "function":
            z = z(u, t), "-" === l.charAt() && (z *= -1), k = !0;
          default:
            C += c(z) || 0, u.length = 0
        }
        l = C;
        k && (s = !0);
        n += l
      }
      if (d = a.match(/^[\+\-]?\$\d+(\.\d+)?|[\+\-]\$\d+(\.\d+)?/g))for (b = 0; b < d.length; b += 1)n = n + Number(d[b].replace("$", "")) || 0;
      return {value: m + n, hasDynamicMacros: s}
    }, $ = function (a, b, c) {
      if (!b.removed) {
        b = b.data("annotation");
        var f = b.getRenderer(), g = ba(f.container, c), l = g.annotationOptions = b.options, k = g.groupOptions =
          b.group.options;
        g._shape = b;
        "id"in l && (g.annotationId = l.id);
        "id"in k && (g.groupId = k.id);
        d.raiseEvent(a, g, f.fusionCharts, c)
      }
    }, aa, qa, ga;
  qa = function (a, b, c, d, f) {
    this.options = a;
    this.attrs = {};
    this.css = {};
    this.bounds = {};
    this.shared = b;
    this.snaps = c || {};
    this.annotations = f;
    this.items = b = [];
    this._idstore = d;
    a.id && (this._id = a.id, d[a.id] = this);
    if (a = a.items)for (d = 0, c = a.length; d < c; d += 1)b.push(new ga(a[d], this))
  };
  Q(qa.prototype, {
    scaleImageX: 1,
    scaleImageY: 1,
    scaleText: 1,
    scaleValue: 1,
    scaleValueComplement: 1,
    scaleX: 1,
    scaleY: 1
  });
  qa.prototype.setup = function () {
    var a = this.options, b = this.shared, c = this.getRenderer();
    c && (this.isBelow = 0 !== m(a.showbelow, a.showbelowchart, b.showbelow), this.useTracker = !this.isBelow && c.layers.tracker && this.shared.useTracker, this.raiseOwnEvents = b.interactionevents)
  };
  qa.prototype.scale = function () {
    var a = this.options, b = this.shared, c = this.bounds, d = this.snaps, f = this.getRenderer(), g = b.rootxscale, l = b.rootyscale, k = c.xs = m(a.xscale, b.xscale, 100) / 100, n = c.ys = m(a.yscale, b.yscale, 100) / 100, s, q, t;
    f && (this.scaleText *=
      n, this.scaleImageX *= k, this.scaleImageY *= n, 0 !== m(a.autoscale, b.autoscale) && (k = m(a.origw, b.origw), n = m(a.origh, b.origh), k = f.chartWidth / k, n = f.chartHeight / n, f = 0 !== m(a.constrainedscale, b.constrainedscale), s = k < n ? k : n, q = f ? s : k, t = f ? s : n, this.scaleValue = qa.prototype.scaleValue * s, this.scaleValueComplement = qa.prototype.scaleValueComplement * (f ? s : k < n ? n : k), this.scaleX = qa.prototype.scaleX * q, this.scaleY = qa.prototype.scaleX * t, c.xs *= q, c.ys *= t, g *= q, l *= t, "1" == A(a.scaletext, b.scaletext) && (this.scaleText = qa.prototype.scaleText *
    t), "1" == A(a.scaleimages, b.scaleimages) && (this.scaleImageX = qa.prototype.scaleImageX * q, this.scaleImageY = qa.prototype.scaleImageY * t)), c.x = Ka(A(a.x, a.xpos), g, 0, d, this.isBelow).value + m(a.grpxshift, b.grpxshift, 0), c.y = Ka(A(a.y, a.ypos), l, 0, d, this.isBelow).value + m(a.grpyshift, b.grpyshift, 0), this.xshift = m(a.xshift, b.xshift, 0), this.yshift = m(a.yshift, b.yshift, 0))
  };
  qa.prototype.draw = function () {
    var a = this.getRenderer(), b = this.options, c = this.bounds, d = this.items, f = a && a.layers.dataset, g = this.wrapper;
    if (a) {
      g || (this.wrapper =
        g = a.paper.group("annotations"), f && (this.isBelow ? g.insertBefore(f) : g.insertAfter(a.layers.datalabels || f)));
      this.wrapper.attr({x: 0, y: 0, visibility: m(b.visible, 1) ? "" : "hidden"}).translate(c.x, c.y);
      b = 0;
      for (c = d.length; b < c; b += 1)a = d[b], a.scale(!0), a.queueDraw ? a.queue() : (a.setup(), a.draw());
      return this
    }
  };
  qa.prototype.destroy = function () {
    for (var a = this.wrapper, b = this.items, c; c = b.shift();)c.destroy();
    a && (this.wrapper = a.remove());
    this._idstore[this._id] === this && delete this._idstore[this._id]
  };
  qa.prototype.addItem =
    function (a, b) {
      var c;
      this.items.push(c = new ga(a, this, this._idstore));
      b && null !== this.getRenderer() && (c.scale(), c.setup(), c.draw());
      return c
    };
  qa.prototype.removeItem = function (a) {
    for (var b = this.items, c = b.length; c--;)if (a === b[c]._id)return b.splice(c, 1)
  };
  qa.prototype.getRenderer = function () {
    return this.annotations && this.annotations.getRenderer() || null
  };
  ga = function (a, b) {
    var c = !1, d;
    this.options = a;
    this.group = b;
    this.args = [];
    this.attrs = {};
    this.attrsTracker = {};
    this.style = {};
    this.bounds = {};
    this._idstore = b._idstore;
    a.id && (this._id = a.id, b._idstore[a.id] = this);
    this.type = a.type && a.type.toLowerCase && a.type.toLowerCase();
    for (d in ga.eventNames)"function" === typeof a[d] && (this[d] = a[d], c = !0);
    this.hasEvents = c;
    "function" === typeof a.onload && (this.onload = a.onload)
  };
  d.extend(ga.prototype, {
    getAbsoluteBounds: function () {
      var a = this.bounds, b = a.x1, c = a.y1, d = a.x2, f = a.y2, g = q(b, d), l = q(c, f), b = S(b, d) - g, c = S(c, f) - l;
      return {x: g, width: b, y: l, height: c, r: a.r, unscaled: {width: b / a.xs, height: c / a.ys}}
    }, queue: function () {
      this.group.annotations.shapesToDraw.push(this)
    },
    scale: function (a) {
      var b = this, c = b.group, d = c.bounds, f = b.bounds, g = b.options, l = c.snaps, k = A(g.x, g.xpos), n = A(g.y, g.ypos), s = A(g.tox, g.toxpos), q = A(g.toy, g.toypos), t = f.xs = d.xs, d = f.ys = d.ys, u = m(g.xshift, c.xshift, 0), z = m(g.yshift, c.yshift, 0), C;
      C = function (d, f, g, l) {
        d = Ka(d, f, g, l, c.isBelow);
        d.hasDynamicMacros && a && (b.queueDraw = !0);
        return d.value
      };
      b.hasDimension = !0;
      b.hasDimensionX = !0;
      b.hasDimensionY = !0;
      f.x1 = C(k, t, 0, l) + u;
      void 0 === s ? (b.hasDimension = !1, b.hasDimensionX = !1, f.x2 = f.x1) : f.x2 = C(s, t, 0, l) + u;
      f.y1 = C(n, d, 0, l) + z;
      void 0 === q ? (b.hasDimension = !1, b.hasDimensionY = !1, f.y2 = f.y1) : f.y2 = C(q, d, 0, l) + z;
      ga.angularShapeTypes[b.type] && (f.angles = Aa(C(g.startangle, 1, 0, l), C(g.endangle, 1, 360, l)));
      f.r = C(g.radius, c.scaleValue, 0, l)
    }, setup: function () {
      var b = this.options, c = this.group, d = c.options, f = this.attrs, g = this.style, l = c.scaleValue, k = m(d.fillalpha, d.alpha, 100), n = this.fillAlpha = A(b.fillalpha, b.alpha, k), s = this.fillColor = A(b.fillcolor, b.color, d.color), q = this.fillPattern = A(b.fillpattern && b.fillpattern.toLowerCase && b.fillpattern.toLowerCase(),
        d.fillpattern && d.fillpattern.toLowerCase && d.fillpattern.toLowerCase()), t = this.bordered = m(b.showborder, ga.borderedShapeTypes[this.type], !!z(b.bordercolor)), u = this.borderColor = A(b.bordercolor, d.bordercolor, s), k = this.borderAlpha = m(b.borderalpha, b.alpha, d.borderalpha, k), C = this.dashed = !!m(b.dashed, 0), B = m(b.borderthickness, b.thickness, 2) * l;
      this.link = A(b.link, d.link);
      this.shadow = "1" == A(b.showshadow, d.showshadow);
      void 0 === s && (s = ga.borderedShapeTypes[this.type] && "none" || "#ff0000", void 0 === u && (u = "#ff0000"));
      t && B ? (f.stroke = P(u, k), f["stroke-linecap"] = "round", f["stroke-width"] = B, C && (f["stroke-dasharray"] = [m(b.dashlen, 5) * l, m(b.dashgap, 3) * l])) : f.stroke = "none";
      this.fillOptions = {
        gradientUnits: "objectBoundingBox",
        color: s,
        alpha: n,
        ratio: A(b.fillratio, d.fillratio),
        angle: 360 - m(b.fillangle, 0),
        radialGradient: "radial" === q
      };
      this.link && (g.cursor = "pointer", g._cursor = "hand");
      f.visibility = m(b.visible, 1) ? "" : "hidden";
      this.useTracker = c.useTracker;
      this.toolText = X(A(b.tooltext, d.tooltext));
      if (this.useTracker || this.link || this.toolText)Q(this.attrsTracker,
        {stroke: a, fill: a}), this.link && (this.attrsTracker.ishot = +new Date);
      this.raiseOwnEvents = c.raiseOwnEvents
    }, draw: function () {
      var a = this.getRenderer(), c = this.type, d = this.attrs, f = this.style, g = a && a.paper, l = ga.types[c] && ga.types[c].call && ga.types[c].call(this, a), k = ga.imageShapeTypes[l], m = ga.textShapeTypes[l], n = k || m || ga.trackerShapeTypes[l], s = this.link || this.toolText, q = this.wrapper, c = this.tracker, t = a && a.layers.tracker || this.group.wrapper, u = !1, z = c || q, A = ga.eventNames, C = ga.ownEvents, B, E;
      if (a) {
        if (l) {
          if (q)if (q.elemType !==
            l) {
            if (this.ownEventsAttached) {
              for (E in C)z["un" + E].apply(q, C[E]);
              this.ownEventsAttached = !1
            }
            q = q.remove()
          } else if (this.hasEvents)for (B in A)(E = this[B]) && E.eventAttached && (K(z.node, A[B], E), E.eventAttached = !1);
          k || (d.fill = za(this.fillOptions));
          q ? q.attr(d).css(f) : (this.args.push(this.group.wrapper), q = this.wrapper = g[l].apply(g, this.args).attr(d).css(f), q.elemType = l, q.data("annotation", this), u = !0, this.args.pop());
          !this.shadow || this.shadowAdded || k || m ? q.shadow(this.shadowAdded = !1) : q.shadow(this.shadowAdded = !0, S(this.borderAlpha, this.fillOptions.alpha) / 100);
          s ? this.useTracker && (c || (this.args.push(t), c = this.tracker = n ? g.rect(0, 0, 0, 0, 0, t) : g[l].apply(g, this.args), this.args.pop()), c.attr(d).attr(this.attrsTracker)) : c && (c = c.remove());
          z = c || q;
          if (this.raiseOwnEvents && !this.ownEventsAttached) {
            for (E in C)z[E].apply(q, C[E]);
            this.ownEventsAttached = !0
          }
          this.link && z.click(a.linkClickFN, this);
          this.toolText && (z.tooltip(this.toolText || ""), this.group.wrapper.trackTooltip(!0));
          if (this.hasEvents)for (B in A)(E = this[B]) && !E.eventAttached &&
          (b(z.node, A[B], E, this), E.eventAttached = !0);
          k || (c && n && (a = q.getBBox(), c.attr({
            x: a.x,
            y: a.y,
            width: a.width,
            height: a.height
          })), u && this.onload && this.onload(d))
        }
        return this
      }
    }, destroy: function () {
      var a = this.wrapper, b = this.tracker, c = b || a, d = ga.eventNames, f = ga.ownEvents, g, l;
      if (a) {
        if (this.ownEventsAttached) {
          for (l in f)c["un" + l].apply(a, f[l]);
          this.ownEventsAttached = !1
        }
        if (this.hasEvents)for (g in d)(l = this[g]) && l.eventAttached && (K(c.node, d[g], l), l.eventAttached = !1);
        b && (this.tracker = b.remove());
        this.wrapper = a.remove()
      }
      this._idstore[this._id] ===
      this && delete this._idstore[this._id]
    }, getRenderer: function () {
      return this.group && this.group.getRenderer() || null
    }
  });
  d.extend(ga, {
    imageShapeTypes: {image: !0},
    angularShapeTypes: {circle: !0, arc: !0},
    textShapeTypes: {text: !0},
    trackerShapeTypes: {image: !0, text: !0},
    borderedShapeTypes: {path: !0, line: !0},
    eventNames: {
      onmouseover: G ? "touchstart" : "mouseover",
      onmouseout: "mouseout",
      onmousemove: G ? "touchmove" : "mousemove",
      onclick: "click"
    },
    ownEvents: {
      click: [function (a) {
        $("annotationClick", this, a)
      }], hover: [function (a) {
        $("annotationRollOver",
          this, a)
      }, function (a) {
        $("annotationRollOut", this, a)
      }]
    },
    textAlignOptions: {left: "start", right: "end", center: "middle"},
    textVerticalAlignOptions: {top: "bottom", middle: "middle", bottom: "top"},
    textRotationOptions: {0: "0", 1: "270", right: "90", cw: "90", left: "270", ccw: "270"},
    types: {
      rectangle: function () {
        var a = this.args, b = this.attrs, c = this.getAbsoluteBounds(), d = .5 * c.width;
        c.r > d && (c.r = d);
        a[0] = b.x = c.x;
        a[1] = b.y = c.y;
        a[2] = b.width = c.width;
        a[3] = b.height = c.height;
        a[4] = b.r = c.r;
        return "rect"
      }, line: function () {
        var a = this.attrs,
          b = this.bounds;
        this.args[0] = a.path = ["M", b.x1, b.y1, "L", b.x2, b.y2];
        1 === a["stroke-width"] && (a["shape-rendering"] = "crisp");
        a["stroke-width"] < l && (this.attrsTracker["stroke-width"] = l);
        this.bordered && this.dashed && (this.attrsTracker["stroke-dasharray"] = "solid");
        return "path"
      }, path: function () {
        var a = this.attrs, b = this.bounds;
        this.args[0] = a.path = this.options.path;
        a.transform = ["T", b.x1, b.y1, "S", b.xs, b.ys, b.x1, b.y1];
        1 === a["stroke-width"] && (a["shape-rendering"] = "crisp");
        return "path"
      }, polygon: function () {
        var a = this.args,
          b = this.attrs, c = this.options, d = this.bounds, f = this.group, g = f.snaps;
        a[0] = Ka(c.sides, 1, 5, g, f.isBelow).value;
        a[1] = d.x1;
        a[2] = d.y1;
        a[3] = d.r;
        a[4] = Ka(c.startangle, 1, 0, g, f.isBelow).value;
        a[5] = 0;
        b.polypath = a.slice(0);
        return "polypath"
      }, circle: function (a) {
        var b = this.args, c = this.attrs, d = this.options, l = this.bounds, k = a.chartWidth, m = a.chartHeight, q = this.group.scaleValueComplement, t = this.group.snaps, u = l.angles, z = this.group;
        a = l.r;
        A(d.radius) || (l.r = k < m ? k * l.xs : m * l.ys, l.r = a = .3 * l.r);
        d = Ka(d.yradius, q, a, t, z.isBelow).value;
        this.fillPattern || (this.fillOptions.radialGradient = !0, this.fillPattern = "radial");
        "radial" === this.fillPattern && (this.fillOptions.cx = this.fillOptions.cy = .5);
        m = u.angle % 360;
        if (!m && a === d)return b[0] = c.cx = l.x1, b[1] = c.cy = l.y1, b[2] = c.r = l.r, "circle";
        m || (u.angle = 0 < u.angle ? u.angle - .001 : u.angle + .001, u.start = u.end - u.angle);
        m || m == u.angle || (m = 360);
        k = u.start * s;
        u = u.end * s;
        m *= s;
        t = l.x1;
        z = l.y1;
        l = t + f(k) * a;
        q = z + C(k) * d;
        t += f(u) * a;
        z += C(u) * d;
        a = ["M", l, q, "A", a, d, 0, n(m) >= g ? 1 : 0, k < u ? 1 : 0, t, z, "Z"];
        b[0] = c.path = a;
        return "path"
      }, arc: function (a) {
        var b =
          this.options, c = this.args, d = this.attrs, f = this.bounds, g = a.chartWidth;
        a = a.chartHeight;
        var l = this.group, k = l.scaleValue, m = f.angles;
        A(b.radius) || (f.r = g < a ? g * f.xs : a * f.ys, f.r *= .3);
        f.innerR = Ka(b.innerradius, k, .8 * f.r, this.group.snaps, l.isBelow).value;
        f.innerR > f.r && (f.innerR += f.r, f.r = f.innerR - f.r, f.innerR -= f.r);
        this.fillPattern || (this.fillOptions.radialGradient = !0, this.fillPattern = "radial");
        "radial" === this.fillPattern && (this.fillOptions.cx = this.fillOptions.cy = .5);
        c[0] = f.x1;
        c[1] = f.y1;
        c[2] = f.r;
        c[3] = f.innerR;
        c[4] =
          m.end * s;
        c[5] = m.start * s;
        d.ringpath = c.slice(0);
        return "ringpath"
      }, text: function (a) {
        var b = this.args, c = this.style, d = this.attrs, f = this.group, g = this.bounds, l = this.options, k = this.getAbsoluteBounds(), n = A(l.align, f.options.textalign, "center").toLowerCase(), s = A(l.valign, f.options.textvalign, "middle").toLowerCase(), q = X(A(l.text, l.label)), t = a.logic.smartLabel, u = m(l.wrap, f.options.wraptext, 1), z, C, B = A(l.rotatetext, f.options.rotatetext, "0").toLowerCase(), B = ga.textRotationOptions[B], E = "0" !== B ? "y" : "x", G = a.options.orphanStyles;
        a = Q({}, G.defaultStyle.style || {});
        G = f.id && G[f.id.toLowerCase()] || Z;
        a = Q(a, G.style);
        var G = parseFloat(a.fontSize), P = A(l.font, f.options.font, a.fontFamily), f = m(l.fontsize, f.options.fontsize, G) * f.scaleText;
        u && (z = m(l.wrapwidth, this.hasDimensionX ? k.width / g.xs : void 0), C = m(l.wrapheight, this.hasDimensionY ? k.height / g.ys : void 0), z && (z *= g.xs), C && (C *= g.ys));
        c.fontFamily = P;
        c.fontWeight = m(l.bold, l.isbold, 0) ? "bold" : "normal";
        m(l.italic, l.isitalic, 0) && (c.fontStyle = "italic");
        l.bgcolor && (!d["text-bound"] && (d["text-bound"] =
          []), d["text-bound"][0] = ka(l.bgcolor));
        l.bordercolor && (!d["text-bound"] && (d["text-bound"] = []), d["text-bound"][1] = ka(l.bordercolor), d["text-bound"][2] = m(l.borderthickness, 1), d["text-bound"][3] = m(l.padding, 1));
        l.fontcolor && (d.fill = ka(l.fontcolor), this.fillOptions && (this.fillOptions.color = d.fill));
        c.fontSize = f + "px";
        f === G ? c.lineHeight = a.lineHeight : ha(c);
        d["text-anchor"] = ga.textAlignOptions[n] || ga.textAlignOptions.center;
        t.setStyle(c);
        c = t.getSmartText(q, z, C, !1);
        d["vertical-align"] = ga.textVerticalAlignOptions[s] ||
        ga.textVerticalAlignOptions.middle;
        d["text-anchor"] === ga.textAlignOptions.left ? k[E] += m(l.leftmargin, 0) : d["text-anchor"] === ga.textAlignOptions.center && (k[E] += .5 * m(l.leftmargin, 0));
        "0" !== B && (d.rotation = [parseFloat(B), k.x, k.y]);
        b[0] = d.x = k.x;
        b[1] = d.y = k.y;
        b[2] = d.text = c.text;
        c.tooltext && (d.title = c.tooltext);
        delete d.stroke;
        delete d["stroke-weight"];
        return "text"
      }, image: function (a) {
        var b = this, c = b.style, d = a.chartWidth, f = a.chartHeight;
        a = b.options;
        var g = b.attrs, l = b.args, k = z(a.url), n = b.group.scaleImageX * A(Number(a.xscale),
            100) / 100, s = b.group.scaleImageY * A(Number(a.yscale), 100) / 100, q = b.getAbsoluteBounds(), C = {
          width: 1,
          height: 1
        }, B;
        if (!k)return l[0] = g.x = q.x, l[1] = g.y = q.y, l[2] = g.width = q.width, l[3] = g.height = q.height, l[4] = g.r = q.r, "rect";
        B = new t.Image;
        B.onload = function () {
          C = u("none", "top", "left", 100, 0, d, f, B);
          delete C.x;
          delete C.y;
          C = Q(C, {
            width: (b.hasDimensionX ? q.unscaled.width : C.width) * n,
            height: (b.hasDimensionY ? q.unscaled.height : C.height) * s
          });
          setTimeout(function () {
            var a, d, f;
            if (a = b.wrapper) {
              a.attr(C);
              if (d = b.tracker)f = a.getBBox(),
                d.attr({x: f.x, y: f.y, width: f.width, height: f.height});
              a.css({opacity: c.opacity = S(m(b.fillAlpha, b.borderAlpha), b.borderAlpha) / 100})
            }
            b.onload && b.onload(C)
          }, 0)
        };
        B.src = k;
        l[0] = g.src = k;
        l[1] = g.x = q.x;
        l[2] = g.y = q.y;
        l[3] = g.width = (b.hasDimensionX ? q.unscaled.width : C.width) * n;
        l[4] = g.height = (b.hasDimensionY ? q.unscaled.height : C.height) * s;
        c.opacity = S(m(b.fillAlpha, b.borderAlpha), b.borderAlpha) / 100;
        delete g.stroke;
        delete g.fill;
        delete g["stroke-linecap"];
        return "image"
      }
    }
  });
  aa = function () {
    this.groups = [];
    this._idstore = {};
    this._options = {}
  };
  B.Annotations = aa;
  d.extend(aa.prototype, {
    reset: function (a, b, c) {
      var d = this.groups, f;
      this.clear();
      if (c) {
        f = {};
        for (var g in c)switch (typeof c[g]) {
          case "object":
          case "function":
            f["-$" + g] = f["$" + g] = f["+$" + g] = c[g];
            break;
          default:
            f["$" + g] = f["+$" + g] = c[g], f["-$" + g] = -1 * c[g]
        }
        f = this._literals = f
      }
      b && (this._options = b);
      if (a && a.groups && d)for (c = 0; c < a.groups.length; c += 1)d.push(new qa(a.groups[c], b, f, this._idstore, this))
    }, getRenderer: function () {
      return this._renderer
    }, addGroup: function (a) {
      var b = this.getRenderer();
      this.groups.push(a = new qa(a, this._options, this._literals, this._idstore, this));
      b && (a.setup(), a.scale(), a.draw());
      return a
    }, addItem: function (a, b, c) {
      var f, g = this.getRenderer();
      "string" === typeof a ? f = this._idstore[a] : (c = b, b = a);
      if (f && f.addItem) {
        if (!g && c) {
          d.raiseWarning(this, "04031411430", "run", "Annotations~addItem()", "Cannot draw the shapeif the group has not been drawn. Use Annotations~draw() to draw the group and pass the renderer to it.");
          return
        }
        a = f.addItem(b, c)
      } else a = this.addGroup({}).addItem(b, c);
      return a
    }, draw: function (a) {
      var b = this.groups, c, d;
      if (b && (this._renderer = a || this._renderer))for (c = 0, d = b.length; c < d; c++)a = b[c], a.setup(), a.scale(), a.draw()
    }, clear: function () {
      var a = this.groups, b;
      if (a) {
        for (; b = a.shift();)b.destroy();
        this.shapesToDraw = []
      }
    }, dispose: function () {
      var a;
      this.disposing = !0;
      this.clear();
      for (a in this)delete this[a];
      this.disposed = !0
    }, hide: function (a) {
      if (a = this._idstore[a])return a.attrs.visibility = "hidden", a.wrapper && a.wrapper.hide(), a
    }, show: function (a) {
      if (a = this._idstore[a])return a.attrs.visibility =
        "", a.wrapper && a.wrapper.show(), a
    }, update: function (a, b, c) {
      a = this._idstore[a];
      var d;
      if (a && b) {
        if ("object" === typeof b)for (d in b.id && delete b.id, b.type && delete b.type, b)a.options[(d + "").toLowerCase()] = b[d] + ""; else a.options[(b + "").toLowerCase()] = c + "";
        a.wrapper && (a.scale(), a.setup(), a.draw());
        return a
      }
    }, destroy: function (a) {
      var b = this._idstore[a], c = b.group;
      b && "function" === typeof b.destroy && (c && c.removeItem(a), b.destroy())
    }, shapesToDraw: []
  });
  d.core.addEventListener("beforeinitialize", function (a) {
    "javascript" ===
    a.sender.options.renderer && (a.sender.annotations = new aa)
  });
  d.core.addEventListener("disposed", function (a) {
    a.sender.annotations && a.sender.annotations.dispose()
  });
  d.addEventListener("internal.animationComplete", function (a) {
    var b = (a = a.sender.annotations) && a.shapesToDraw, c = b && b.length, d, f;
    if (c) {
      for (f = 0; f < c; f++)d = b[f], d.queueDraw = !1, d.scale(), d.setup(), d.draw();
      a.shapesToDraw = []
    }
  });
  k.addEventListener("rendered", function (a, b) {
    if ("javascript" === b.renderer) {
      var c = a.sender, d = c.jsVars || {}, f = d.instanceAPI;
      d.hcObj &&
      f && f.drawAnnotations ? (c.showAnnotation || (c.showAnnotation = function () {
        c.annotations.show.apply(c.annotations, arguments)
      }), c.hideAnnotation || (c.hideAnnotation = function () {
        c.annotations.hide.apply(c.annotations, arguments)
      })) : (delete c.showAnnotation, delete c.hideAnnotation)
    }
  })
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-base", function () {
  var d = this, k = d.hcLib, B = k.Raphael, t = d.window, E = t.document, b = k.BLANKSTRING, K = k.createTrendLine, G = "https:" === t.location.protocol ? "https://export.api3.fusioncharts.com/" : "http://export.api3.fusioncharts.com/", c = k.pluck, l = k.getValidValue, a = k.pluckNumber, q = k.getFirstValue, S = k.getDefinedColor, C = k.parseUnsafeString, f = k.FC_CONFIG_STRING, g = k.extend2, s = k.getDashStyle, n = k.parseTooltext, Q = k.toPrecision, A = k.regex.dropHash, m = k.HASHSTRING,
    P = k.getSentenceCase, z = k.addEvent, X = function (a) {
      return void 0 !== a && null !== a
    }, u = t.Math, ka = k.TOUCH_THRESHOLD_PIXELS, ha = k.CLICK_THRESHOLD_PIXELS, ba = u.min, Z = u.max, za = u.abs, Aa = u.ceil, Ka = u.floor, $ = u.log, aa = u.pow, qa = u.sqrt, ga = u.round, R = k.graphics.getColumnColor, T = k.getFirstColor, h = k.setLineHeight, J = k.pluckFontSize, L = k.getFirstAlpha, U = k.graphics.getDarkColor, da = k.graphics.getLightColor, fa = k.graphics.convertColor, xa = k.COLOR_TRANSPARENT, ya = k.POSITION_CENTER, Fa = k.POSITION_TOP, Da = k.POSITION_BOTTOM, na = k.POSITION_RIGHT,
    ua = k.POSITION_LEFT, Ea = k.parsexAxisStyles, pa = k.chartAPI, bb = k.graphics.mapSymbolName, va = pa.singleseries, Ya = pa.multiseries, Ba = k.COMMASTRING, jb = k.STRINGUNDEFINED, Ca = k.ZEROSTRING, db = k.ONESTRING, Ta = k.HUNDREDSTRING, ra = k.PXSTRING, Qb = k.COMMASPACE, qb = t.navigator.userAgent.match(/(iPad|iPhone|iPod)/g), Ib = !/fusioncharts\.com$/i.test(t.location.hostname), Rb = {
      left: "start",
      right: "end",
      center: "middle"
    }, eb = k.BLANKSTRINGPLACEHOLDER, Xa = k.BGRATIOSTRING, Va = k.COLOR_WHITE, lb = k.TESTSTR, xb = k.graphics.getAngle, I = k.axisLabelAdder,
    H = k.falseFN, p = k.NumberFormatter, O = k.getLinkAction, ca = k.getAxisLimits, ea = k.createDialog, ma = function (a, b) {
      return 0 < a ? $(a) / $(b || 10) : null
    }, oa = k.hasTouch = void 0 !== E.documentElement.ontouchstart, la = k.fireEvent = function (a, b, c, d) {
      k.dem.fire(a, b, c, d)
    }, La = {1: "bold", 0: "normal"}, Ja = {1: "italic", 0: "normal"}, V = {1: "underline", 0: "none"}, ia = {
      font: function (a, b) {
        b.style.fontFamily = a
      }, size: function (a, b) {
        a && (b.style.fontSize = J(a) + ra)
      }, color: function (a, c, d) {
        c.style.color = a && a.replace && a.replace(A, m) || b;
        d && (c.color = c.style.color)
      },
      bgcolor: function (a, c) {
        c.style.backgroundColor = a && a.replace && a.replace(A, m) || b
      }, bordercolor: function (a, c) {
        c.style.border = "1px solid";
        c.style.borderColor = a && a.replace && a.replace(A, m) || b
      }, ishtml: b, leftmargin: function (b, c) {
        c.style.marginLeft = a(b, 0) + ra
      }, letterspacing: function (b, c) {
        c.style.letterSpacing = a(b, 0) + ra
      }, bold: function (a, b) {
        b.style.fontWeight = La[a] || ""
      }, italic: function (a, b) {
        b.style.fontStyle = Ja[a] || ""
      }, underline: function (a, b) {
        b.style.textDecoration = V[a] || ""
      }
    }, Ia = k.chartPaletteStr = {
      chart2D: {
        bgColor: "bgColor",
        bgAlpha: "bgAlpha",
        bgAngle: "bgAngle",
        bgRatio: "bgRatio",
        canvasBgColor: "canvasBgColor",
        canvasBaseColor: "canvasBaseColor",
        divLineColor: "divLineColor",
        legendBgColor: "legendBgColor",
        legendBorderColor: "legendBorderColor",
        toolTipbgColor: "toolTipbgColor",
        toolTipBorderColor: "toolTipBorderColor",
        baseFontColor: "baseFontColor",
        anchorBgColor: "anchorBgColor"
      }, chart3D: {
        bgColor: "bgColor3D",
        bgAlpha: "bgAlpha3D",
        bgAngle: "bgAngle3D",
        bgRatio: "bgRatio3D",
        canvasBgColor: "canvasBgColor3D",
        canvasBaseColor: "canvasBaseColor3D",
        divLineColor: "divLineColor3D",
        divLineAlpha: "divLineAlpha3D",
        legendBgColor: "legendBgColor3D",
        legendBorderColor: "legendBorderColor3D",
        toolTipbgColor: "toolTipbgColor3D",
        toolTipBorderColor: "toolTipBorderColor3D",
        baseFontColor: "baseFontColor3D",
        anchorBgColor: "anchorBgColor3D"
      }
    }, wa = function () {
      var a = {}, b, c = function () {
        var f, e, g, h, l = 0, p, k, m = parseInt(d.core.options.resizeTrackingInterval, 10) || 300, I;
        for (f in a)l += 1, e = a[f], g = e.jsVars, p = e.ref, !e.disposed && (h = p && p.parentNode) && (k = p.style) && (/\%/g.test(k.width) ||
        /\%/g.test(k.height)) ? (p = h.offsetWidth, I = h.offsetHeight, !g.resizeLocked && (p && g._containerOffsetW !== p || I && g._containerOffsetH !== I) && (e.resizeTo && e.resizeTo(), g._containerOffsetW = p, g._containerOffsetH = I)) : (delete a[f], --l);
        b = l ? setTimeout(c, m) : clearTimeout(b)
      };
      return function (f, e) {
        var g = f.jsVars, h = e || f.ref && f.ref.parentNode || {};
        g._containerOffsetW = h.parentNode.offsetWidth;
        g._containerOffsetH = h.parentNode.offsetHeight;
        a[f.id] = f;
        b || (b = setTimeout(c, parseInt(d.core.options.resizeTrackingInterval, 10) || 300))
      }
    }(),
    hb = {
      getExternalInterfaceMethods: function () {
        var a = pa[this.jsVars.type], a = a && a.eiMethods, b = "saveAsImage,print,exportChart,getXML,hasRendered,signature,cancelExport,getSVGString,lockResize,showChartMessage,", c;
        if ("string" === typeof a)b += a + Ba; else if (void 0 !== a || null !== a)for (c in a)b += c + Ba;
        return b.substr(0, b.length - 1)
      }, drawOverlayButton: function (a) {
        var b = this.jsVars, c = b.overlayButton, f, e;
        if (a && a.show) {
          c || (c = b.overlayButton = E.createElement("span"), k.dem.listen(c, "click", function () {
            d.raiseEvent("OverlayButtonClick",
              a, b.fcObj)
          }));
          for (f = a.message ? a.message : "Back"; c.firstChild;)c.removeChild(c.firstChild);
          c.appendChild(E.createTextNode(f));
          b.overlayButtonMessage = f;
          f = {
            border: "1px solid " + (a.borderColor ? a.borderColor.replace(A, m) : "#7f8975"),
            backgroundColor: a.bgColor ? a.bgColor.replace(A, m) : "#edefec",
            fontFamily: a.font ? a.font : "Verdana,sans",
            color: "#" + a.fontColor ? a.fontColor : "49563a",
            fontSize: (a.fontSize ? a.fontSize : "10") + ra,
            padding: (a.padding ? a.padding : "3") + ra,
            fontWeight: 0 === parseInt(a.bold, 10) ? "normal" : "bold",
            position: "absolute",
            top: "0",
            right: "0",
            _cursor: "hand",
            cursor: "pointer"
          };
          for (e in f)c.style[e] = f[e];
          b.hcObj.container.appendChild(c);
          b.overlayButtonActive = !0
        } else c && (b.overlayButton = c.parentNode.removeChild(c), b.overlayButtonActive = !1, delete b.overlayButtonMessage)
      }, print: function (a) {
        return this.jsVars.hcObj && this.jsVars.hcObj.hasRendered && this.jsVars.hcObj.print(a)
      }, exportChart: function (a) {
        var b = this.jsVars.hcObj;
        return b && b.options && b.options.exporting && b.options.exporting.enabled ? b.exportChart(a) : !1
      }, getSVGString: function () {
        return this.jsVars &&
          this.jsVars.hcObj && this.jsVars.hcObj.paper && this.jsVars.hcObj.paper.toSVG()
      }, resize: function () {
        var a = this.jsVars, b = a.container, c = a.hcObj;
        c && (c && c.destroy && c.destroy(), k.createChart(a.fcObj, b, a.type, void 0, void 0, !1, !0), delete a.isResizing)
      }, lockResize: function (a) {
        return "boolean" !== typeof a ? !!this.jsVars.resizeLocked : this.jsVars.resizeLocked = a
      }, showChartMessage: function (a, b, c) {
        var d = this.jsVars, e = d.hcObj, f = d.fcObj, g = f.options;
        d.msgStore[a] && (a = d.msgStore[a]);
        b && e && e.hasRendered ? a ? e.showMessage(a, c) :
          e.hideLoading() : (e && e.destroy && e.destroy(), f._chartMessageImageStyle = {
          imageHAlign: g.baseChartMessageImageHAlign,
          imageVAlign: g.baseChartMessageImageVAlign,
          imageAlpha: g.baseChartMessageImageAlpha,
          imageScale: g.baseChartMessageImageScale
        }, f._chartMessageStyle = {
          color: g.baseChartMessageColor,
          fontFamily: g.baseChartMessageFont,
          fontSize: g.baseChartMessageFontSize
        }, k.createChart(d.fcObj, d.container, d.type, void 0, a));
        return a
      }, signature: function () {
        return "FusionCharts/3.4.0 (XT)"
      }
    }, Ga = k.HCstub = function (c, d, f,
                                 g) {
      c = c.chart;
      var e = a(c.showborder, 1) ? a(c.borderthickness, 1) : 0, h = a(c.charttopmargin, g.charttopmargin, 15) + e, l = a(c.chartrightmargin, g.chartrightmargin, 15) + e, p = a(c.chartbottommargin, g.chartbottommargin, 15) + e, e = a(c.chartleftmargin, g.chartleftmargin, 15) + e, m = h + p, I = e + l;
      f *= .7;
      d *= .7;
      m > f && (h -= (m - f) * h / m, p -= (m - f) * p / m);
      I > d && (e -= (I - d) * e / I, l -= (I - d) * l / I);
      d = {
        _FCconf: {
          0: {stack: {}},
          1: {stack: {}},
          x: {stack: {}},
          oriCatTmp: [],
          isSpline: -1 !== g.chartInstance.options.chartType.search(/spline/i),
          noWrap: !1,
          marginLeftExtraSpace: 0,
          marginRightExtraSpace: 0,
          marginBottomExtraSpace: 0,
          marginTopExtraSpace: 0,
          marimekkoTotal: 0
        },
        chart: {
          alignTicks: !1,
          ignoreHiddenSeries: !1,
          events: {},
          reflow: !1,
          spacingTop: h,
          spacingRight: l,
          spacingBottom: p,
          spacingLeft: e,
          marginTop: h,
          marginRight: l,
          marginBottom: p,
          marginLeft: e,
          borderRadius: 0,
          plotBackgroundColor: "#FFFFFF",
          textDirection: "1" === c.hasrtltext ? "rtl" : "",
          style: {},
          animation: a(c.defaultanimation, c.animation, 1) ? {duration: 500 * a(c.animationduration, 1)} : !1
        },
        colors: "AFD8F8 F6BD0F 8BBA00 FF8E46 008E8E D64646 8E468E 588526 B3AA00 008ED6 9D080D A186BE CC6600 FDC689 ABA000 F26D7D FFF200 0054A6 F7941C CC3300 006600 663300 6DCFF6".split(" "),
        credits: {href: k.CREDIT_HREF, text: k.CREDIT_STRING, enabled: Ib},
        global: {},
        labels: {items: []},
        lang: {},
        legend: {
          enabled: !0,
          symbolWidth: 12,
          borderRadius: 1,
          backgroundColor: "#FFFFFF",
          initialItemX: 0,
          title: {text: b, x: 0, y: 0, padding: 2},
          scroll: {},
          itemStyle: {}
        },
        loading: {},
        plotOptions: {
          series: {
            pointPadding: 0,
            borderColor: "#333333",
            events: {},
            animation: a(c.animation, c.defaultanimation, 1) ? {duration: 1E3 * a(c.animationduration, 1)} : !1,
            states: {hover: {enabled: !1}, select: {enabled: !1}},
            dataLabels: {
              enabled: !0, color: "#555555", style: {},
              formatter: function () {
                return this.point.showPercentValues ? g.numberFormatter.percentValue(this.percentage) : this.point.displayValue
              }
            },
            point: {events: {}}
          },
          area: {
            states: {hover: {enabled: !1}},
            marker: {lineWidth: 1, radius: 3, states: {hover: {enabled: !1}, select: {enabled: !1}}}
          },
          radar: {
            states: {hover: {enabled: !1}},
            marker: {lineWidth: 1, radius: 3, states: {hover: {enabled: !1}, select: {enabled: !1}}}
          },
          areaspline: {
            states: {hover: {enabled: !1}},
            marker: {lineWidth: 1, radius: 3, states: {hover: {enabled: !1}, select: {enabled: !1}}}
          },
          line: {
            shadow: !0,
            states: {hover: {enabled: !1}},
            marker: {lineWidth: 1, radius: 3, states: {hover: {enabled: !1}, select: {enabled: !1}}}
          },
          scatter: {
            states: {hover: {enabled: !1}},
            marker: {lineWidth: 1, radius: 3, states: {hover: {enabled: !1}, select: {enabled: !1}}}
          },
          bubble: {
            states: {hover: {enabled: !1}},
            marker: {lineWidth: 1, radius: 3, states: {hover: {enabled: !1}, select: {enabled: !1}}}
          },
          spline: {
            states: {hover: {enabled: !1}},
            marker: {lineWidth: 1, radius: 3, states: {hover: {enabled: !1}, select: {enabled: !1}}}
          },
          pie: {
            size: "80%", allowPointSelect: !0, cursor: "pointer",
            point: {
              events: {
                legendItemClick: c.interactivelegend === Ca ? H : function () {
                  this.slice()
                }
              }
            }
          },
          pie3d: {
            size: "80%",
            allowPointSelect: !0,
            cursor: "pointer",
            point: {
              events: {
                legendItemClick: c.interactivelegend === Ca ? H : function () {
                  this.slice()
                }
              }
            }
          },
          column: {},
          floatedcolumn: {},
          column3d: {},
          bar: {},
          bar3d: {}
        },
        point: {},
        series: [],
        subtitle: {text: b, style: {}},
        symbols: [],
        title: {text: b, style: {}},
        toolbar: {},
        tooltip: {style: {}},
        xAxis: {
          steppedLabels: {style: {}},
          labels: {x: 0, style: {}, enabled: !1},
          lineWidth: 0,
          plotLines: [],
          plotBands: [],
          title: {
            style: {},
            text: b
          },
          tickWidth: 0,
          scroll: {enabled: !1}
        },
        yAxis: [{
          startOnTick: !1,
          endOnTick: !1,
          title: {style: {}, text: b},
          tickLength: 0,
          labels: {x: 0, style: {}},
          plotBands: [],
          plotLines: []
        }, {
          tickLength: 0,
          gridLineWidth: 0,
          startOnTick: !1,
          endOnTick: !1,
          title: {style: {}, text: b},
          labels: {
            x: 0, style: {}, enabled: !1, formatter: function () {
              return this.value !== eb ? this.value : b
            }
          },
          opposite: !0,
          plotBands: [],
          plotLines: []
        }],
        exporting: {buttons: {exportButton: {}, printButton: {enabled: !1}}}
      };
      c.palettecolors && "string" === typeof c.palettecolors && (d.colors =
        c.palettecolors.split(/\s*\,\s*/));
      return g.hcJSON = d
    }, ab = function (a, b, c, d, e) {
      var f = [], g = !1;
      d = d || 0;
      for (e = e || {
        max: Number.MIN_VALUE,
        min: Number.MAX_VALUE
      }; d < a.length; ++d)if (g)if (isNaN(a[d].y) || null === a[d].y) {
        if (!c)break
      } else f.push({index: d, y: a[d].y}); else isNaN(a[d].y) || null === a[d].y || (g = !0, f.push({
        index: d,
        y: a[d].y
      }));
      if (2 < f.length) {
        var g = e, h = {}, l, p, k;
        for (p = 0; p < f.length; ++p)l = f[p].index, h["D" + l] = 0;
        for (l = 0; 10 > l; ++l)for (p = 0; p < f.length; ++p)k = 0 === p ? (3 * (f[p + 1].y - f[p].y) - h["D" + f[p + 1].index]) / 2 : p === f.length -
        1 ? (3 * (f[p].y - f[p - 1].y) - h["D" + f[p - 1].index]) / 2 : (3 * (f[p + 1].y - f[p - 1].y) - h["D" + f[p + 1].index] - h["D" + f[p - 1].index]) / 4, h["D" + f[p].index] = k;
        l = ga(b / (f.length - 1));
        for (p = 1; p < f.length; ++p) {
          k = g;
          for (var m = void 0, I = void 0, n = void 0, H = void 0, s = I = void 0, q = void 0, O = void 0, t = void 0, t = void 0, m = h["D" + f[0].index], I = h["D" + f[p].index], n = f[0].y, H = 3 * (f[p].y - f[0].y) - 2 * m - I, I = 2 * (f[0].y - f[p].y) + m + I, s = k.max, q = k.min, O = 0; O <= l; O++)t = O / l, t = n + m * t + H * t * t + I * t * t * t, t < q && (q = t), t > s && (s = t);
          k.max = s;
          k.min = q
        }
      }
      d < a.length && !c && ab(a, b, c, d, e);
      return e
    },
    Bb = k.placeVerticalAxis = function (c, d, v, g, e, h, p, k, m, I) {
      var n = v[f], H = n.smartLabel, s, F, M, q, O = 0, t = n.marginRightExtraSpace, u = n.marginLeftExtraSpace, ca = {}, z = {}, A = {}, C = c.plotLines, ea = c.plotBands, n = d.verticalAxisValuesPadding, ma = isNaN(d.fixedValuesPadding) ? 0 : d.fixedValuesPadding, B = n - ma, E = d.verticalAxisValuesPadding, la = d.verticalAxisNamePadding, oa = d.verticalAxisNameWidth, G = d.rotateVerticalAxisName && String(d.rotateVerticalAxisName).toLowerCase(), La = "none" !== G, P = c.offset ? c.offset : 0, V = 0, Ja = 0, K = 0, J = 0, Ga = 0, Q = 0,
        ia = 0, Oa, L, Ia, wa, n = 2, ia = p ? t + 5 : u + 4, hb = Z(a(v.chart.plotBorderWidth, 1), 0), Y = c.showLine ? c.lineThickness : hb, R = function (a, b) {
          var c, e;
          a && a.label && void 0 !== l(a.label.text) && (Ia = a.label, Ia.style && Ia.style !== L && (L = Ia.style, H.setStyle(L)), s = H.getOriSize(a.label.text), e = (c = s.width) ? c + 2 : 0, a.isGrid ? (ca[b] = {
            width: c,
            height: s.height,
            label: Ia
          }, J <= e && (J = e, d.lYLblIdx = b)) : a.isTrend && (p && Ia.textAlign === ua || Ia.textAlign === na ? (z[b] = {
            width: c,
            height: s.height,
            label: Ia
          }, Ga = Z(Ga, e)) : (A[b] = {width: c, height: s.height, label: Ia}, Q =
            Z(Q, e))))
        }, S = function (a, d) {
          var f, w = d ? O : O + a;
          f = c.title.style;
          F = F || {};
          if (0 < w)return La ? (w < F.height && (H.setStyle(f), F = H.getSmartText(c.title.text, e, w)), f = F.height) : (w < F.width && (H.setStyle(f), F = H.getSmartText(c.title.text, w, e)), f = F.width), c.title._actualWidth = f, c.title.text = F.text, F.tooltext && (c.title.originalText = F.tooltext), d ? w - f + a : w - f;
          c.title.text = b;
          return 0
        }, ab = function (a, b, c) {
          for (var e in a)a[e].label.x = b, a[e].label.y = c
        }, K = 0;
      for (Oa = ea.length; K < Oa; K += 1)R(ea[K], K);
      K = 0;
      for (Oa = C.length; K < Oa; K += 1)R(C[K],
        K);
      c.title && c.title.text != b && (L = c.title.style, H.setStyle(L), M = H.getOriSize(lb).height, c.title._originalText = c.title.text, La ? (c.title.rotation = "cw" === G ? 90 : 270, F = H.getSmartText(c.title.text, e, h), O = F.height, q = M) : (c.title.rotation = 0, F = H.getSmartText(c.title.text, void 0 !== oa ? oa : h, e), O = F.width, q = 20));
      0 < Q && (Ja = Q + E);
      m && (g = a(g.chart.maxlabelwidthpercent, 0), 1 <= g && 100 >= g && (m = g * m / 100, J > m && (J = m)));
      V = Z(Ga, J);
      V += V ? B + ma : 0;
      0 < O && (V += O + la + ia);
      (function () {
        if (Ja + V > h) {
          wa = Ja + V - h;
          if (Ja) {
            if (E >= wa) {
              E -= wa;
              return
            }
            wa -= E;
            E = 0
          }
          if (B +
            la >= wa)la >= wa ? la -= wa : (B -= wa - la, la = 0); else {
            wa -= B + la;
            la = B = 0;
            if (20 < Q)if (Ga > J) {
              if (Q - Ga >= wa) {
                Q -= wa;
                return
              }
              if (Ga - Q >= wa) {
                Ga -= wa;
                return
              }
              Ga > Q ? (wa -= Ga - Q, Ga = Q) : (wa -= Q - Ga, Q = Ga);
              if (2 * (Ga - J) >= wa) {
                Q -= wa / 2;
                Ga -= wa / 2;
                return
              }
              wa -= 2 * (Ga - J);
              Q = Ga = J
            } else {
              if (Q - 20 >= wa) {
                Q -= wa;
                return
              }
              wa -= Q - 20;
              Q = 20
            }
            if (Ga > J) {
              if (Ga - J >= wa) {
                Ga -= wa;
                return
              }
              wa -= Ga - J;
              Ga = J
            }
            O - q >= wa ? O -= wa : (wa -= O - q, O = q, Q >= wa ? Q = 0 : (wa -= Q, Q = 0, O >= wa ? O = 0 : (wa -= O, O = 0, J >= wa && (Ga = J -= wa))))
          }
        }
      })();
      K = function (a, c) {
        var d, r = 0, f = c ? Q - 2 : Q + a - 2, w;
        if (0 < Q) {
          for (w in A)Ia = A[w].label, A[w].width >
          f ? (Ia.style && Ia.style !== L && (L = Ia.style, H.setStyle(L)), d = H.getSmartText(Ia.text, f, e, !0), Ia.text = d.text, d.tooltext && (Ia.originalText = d.tooltext), A[w].height = d.height, r = Z(r, d.width)) : r = Z(r, A[w].width);
          return c ? f - r + a : f - r
        }
        for (w in A)A[w].label.text = b;
        return 0
      }(0, !0);
      K = S(K, !0);
      K = function (a) {
        var c = 0, d = Z(J, Ga) + a - 2, r;
        if (0 < d) {
          for (r in ca)Ia = ca[r].label, ca[r].width > d ? (Ia.style && Ia.style !== L && (L = Ia.style, H.setStyle(L)), a = H.getSmartText(Ia.text, d, e, !0), Ia.text = a.text, a.tooltext && (Ia.originalText = a.tooltext), ca[r].height =
            a.height, c = Z(c, a.width)) : c = Z(c, ca[r].width);
          for (r in z)Ia = z[r].label, z[r].width > d ? (Ia.style && Ia.style !== L && (L = Ia.style, H.setStyle(L)), a = H.getSmartText(Ia.text, d, e, !0), Ia.text = a.text, a.tooltext && (Ia.originalText = a.tooltext), z[r].height = a.height, c = Z(c, a.width)) : c = Z(c, z[r].width);
          return d - c
        }
        for (r in ca)ca[r].label.text = b;
        for (r in z)z[r].label.text = b;
        return 0
      }(K);
      K = S(K);
      m = d.verticalAxisNamePadding - la;
      K && m && (K > m ? (la += m, K -= m) : (la += K, K = 0));
      m = d.verticalAxisValuesPadding - (B + ma);
      K && m && (K > m ? (B += m, K -= m) : (B +=
        K, K = 0));
      m = d.verticalAxisValuesPadding - E;
      K && m && (K > m ? (E += m, K -= m) : (E += K, K = 0));
      0 < Q && (Ja = Q + E);
      V = Z(Ga, J);
      V += V ? B + ma : 0;
      0 < O && (V += O + la + ia);
      m = Z(Ga, J);
      m += 0 < m ? B + ma : 0;
      0 < O ? (La ? O < F.height && (F = H.getSmartText(c.title.text, e, O)) : (O < F.width && (F = H.getSmartText(c.title.text, O, e)), c.title.y = -((F.height - M) / 2)), c.title.text = F.text, F.tooltext && (c.title.originalText = F.tooltext), c.title.margin = m + la + ia + (La ? O - M : O / 2)) : c.title.text = b;
      M = -(B + ma + P + u + 2);
      t = t + E + P + 2;
      ia = Z(Ga, J);
      c.labels.style && (n = .35 * parseInt(c.labels.style.fontSize, 10));
      p ? (0 < Q && ab(A, M, n), 0 < ia && (ab(ca, t, n), ab(z, t, n))) : (0 < Q && ab(A, t, n), 0 < ia && (ab(ca, M, n), ab(z, M, n)));
      c.labels._textY = n;
      c.labels._righttX = t;
      c.labels._leftX = M;
      V = V || Y;
      Ja = Ja || (k ? 0 : hb);
      I ? (v.chart.marginLeft += p ? Ja : V - I, v.chart.marginRight += p ? V - I : Ja) : (v.chart.marginLeft += p ? Ja : V, v.chart.marginRight += p ? V : Ja);
      return Ja + V
    }, Eb = k.titleSpaceManager = function (c, d, v, g) {
      var e = this.snapLiterals || (this.snapLiterals = {}), h = d.chart, l = C(h.caption);
      d = C(h.subcaption);
      var p = h = a(h.captionpadding, 10), k = c[f], m = this.smartLabel || k.smartLabel,
        I = !1, n = 0, H, F, M = 0, s = 0, O = 0, q = 0, t = c.title, ca = c.subtitle, u = Z(a(c.chart.plotBorderWidth, 1), 0), z = 0, A = 0;
      if (3 < g) {
        h < u && (h = u + 2);
        l !== b && (H = t.style, O = Aa(a(parseFloat(H.fontHeight, 10), parseFloat(H.lineHeight, 10), 12)));
        d !== b && (F = ca.style, q = a(parseInt(F.fontHeight, 10), parseInt(F.lineHeight, 10), 12));
        if (0 < O || 0 < q)g = Z(g, 0), n = O + q + h, n > g ? (M = g - n, I = !0, M < h ? h = Z(M, 5) : (M -= h, h = 0, q > M ? (s = q - M + 10, q = 0, ca._originalText = ca.text, ca.text = "") : (M -= q, q = 0, O > M && (s = O - M)))) : s = g - n, 0 < O && (m.setStyle(H), O += s, g = m.getSmartText(l, v, O), s = O - g.height,
          t.height = O = g.height, t.text = g.text, g.tooltext && (t.originalText = g.tooltext), z = g.width), 0 < q && (m.setStyle(F), q += s, v = m.getSmartText(d, v, q), s = q - v.height, q = v.height, ca.text = v.text, ca.height = v.height, v.tooltext && (ca.originalText = v.tooltext), A = v.width), I && 0 < s && (h += ba(p - h, s)), n = O + q + h;
        n = n || u;
        t.isOnTop ? (e.captionstarty = c.chart.marginTop, c.chart.marginTop += n) : (c.chart.marginBottom += n, e.captionstarty = t.y = k.height - c.chart.marginBottom + h, c.chart.marginTop += 5, n += 5);
        t._captionWidth = z;
        ca._subCaptionWidth = A;
        t._lineHeight =
          O;
        ca._lineHeight = q
      } else ca && (ca.text = ""), t && (t.text = "");
      return n
    }, Oa = k.stepYAxisValues = function (a, c, d, g, e, h) {
      var l = 0, p = g.plotLines, k = [], m = 0, I = g.plotLines.length, n = "W";
      c = c[f].smartLabel;
      for (var H = parseFloat(J(d.basefontsize, 10)), F, M, s = 0; l < I; l += 1)d = p[l], d.isGrid && d.label && d.label.text && (h && (M = d.label.text.match(/<br \/>/gi)) && (s = Z(s, M.length), M = null), k.push(d), 0 === d.value && (m = k.length - 1));
      h && 0 < s && (n += Array(s + 1).join("<br />W"));
      if (I = k.length)if (g.labels.style ? c.setStyle(g.labels.style) : k[0].label &&
        k[0].label.style && c.setStyle(g.labels.style), l = c.getOriSize(n).height, h || (l += .4 * H), a /= I - 1, a < l) {
        h = Z(1, Aa(l / a));
        for (l = a = m; l < I; l += 1)d = k[l], l === e && ((l - a) % h && F && (F.label.text = ""), a = e), d && d.label && ((l - a) % h ? d.label.text = b : F = d);
        for (l = a = m; 0 <= l; --l)d = k[l], l === e && ((a - l) % h && F && (F.label.text = ""), a = e), d && d.label && ((a - l) % h ? d.label.text = b : F = d)
      }
    }, sb = k.placeHorizontalAxis = function (c, d, v, g, e, h, p) {
      var k = v[f], m = g && g.chart || {}, I, n, H, s, F, M, O, q, t, ca, u, z, A = 0, C = 0, ea = 10, ma = 1, B = 0, E = 0, la = 0, K = 0, V = !1, oa = !1, G = !1, La = a(m.labelstep,
          0), P = a(m.xaxisminlabelwidth, 0), Ja = a(m.maxlabelheight, h), J = d.labelDisplay, Ga = d.rotateLabels, Q = d.horizontalLabelPadding, Ia = k.marginBottomExtraSpace, wa = v.chart.marginLeft, L = v.chart.marginRight, ia = k.smartLabel, Oa = k.plotBorderThickness, hb = d.catCount, R = d.slantLabels, Y = e / (c.max - c.min), ab = 0, S = 0, Bb = 0, T = 0, ha = g && g.chart || {}, ka = 1E3 * a(ha.updateinterval, ha.refreshinterval), oc = ha.datastreamurl, Yb = Boolean(this.realtimeEnabled && ka && void 0 !== oc), Eb, X, Pa, mb, aa, fa, Cb, Gb, ub, sb, Vb, Kb, da, $, U, ga, pa, Ba, va, za, xa, Fa, ra, Ea,
        pb, Ca = null, Pb = null, Ma, Ta, Ya, Tb, gc, ec, db, bb, Zb, ta, Xb, Xa, Na = [], Fb = [], kb, yb = 0, zb = 0, $b, Va, rb, fc, jc, eb, gb, kc = d.horizontalAxisNamePadding, wb = 0, Ua = d.staggerLines, Hb = ab, lb = !1, xb = !1, qb = 0, lc, Ab, Jb, Ub, Rb, Qb, Ib, Yc, Zc, Cc, Tc, $c, nc, Mc, Uc, Ec, ad, Nc, Vc, vc;
      Xb = c.plotLines;
      ea = ta = 0;
      for (gb = Xb.length; ta < gb; ta += 1)(n = Xb[ta]) && n.label && !n.isTrend && ea < parseFloat(n.label.style.lineHeight, 10) && (ea = parseInt(n.label.style.lineHeight, 10), M = n.label.style);
      if (M || c.labels.style)M = M || c.labels.style, ia.setStyle(M), q = ia.getOriSize("W"),
        ea = ia.lineHeight, O = q.width + 4, z = ia.getOriSize("WWW").width + 4;
      c.title && c.title.text != b && (M = c.title.style, ia.setStyle(M), E = ia.getOriSize("W").height, c.title.rotation = 0, s = ia.getSmartText(c.title.text, e, h), C = s.height);
      wa != parseInt(m.chartleftmargin, 10) && (db = !0);
      L != parseInt(m.chartrightmargin, 10) && (bb = !0);
      void 0 !== m.canvaspadding && "" !== m.canvaspadding && (xb = !0);
      Zb = e - p;
      switch (J) {
        case "none":
          V = G = !0;
          Ga && (A = R ? 300 : 270, q = ea, ea = O, O = q);
          break;
        case "rotate":
          A = R ? 300 : 270;
          q = ea;
          ea = O;
          O = q;
          V = !0;
          break;
        case "stagger":
          oa = V = !0;
          t = Ka((h - E) / ea);
          t < Ua && (Ua = t);
          break;
        default:
          Ga && (A = R ? 300 : 270, q = ea, ea = O, O = q)
      }
      k.isBar && (V = !0);
      ta = 0;
      Xb = c.plotLines;
      if (typeof v._FCconf.isXYPlot !== jb || k.isBar) {
        Eb = {};
        fa = aa = 0;
        sb = ub = null;
        Ba = {};
        lb = !0;
        Y = e / (c.max - c.min);
        Ub = function (a, e, d) {
          var f, w, g, D, h, l;
          l = a.plotObj;
          h = a.labelTextWidth;
          h || (F = l.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), h = ia.getOriSize(F.text).width + 4, a.oriWidth = h, h > X && (h = X), a.labelTextWidth = h, a.leftEdge = l.value * Y - h / 2, a.rightEdge = l.value * Y + h / 2, d && (h = ba(h, 2 * (n.value - c.min) * Y + v.chart.marginLeft),
            a.labelTextWidth = h));
          if (typeof e !== jb) {
            if (d = e.plotObj, F = d.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), e.oriWidth ? g = e.oriWidth : (g = ia.getOriSize(F.text).width + 4, e.oriWidth = g), g > X && (g = X), e.labelTextWidth = g, e.leftEdge = d.value * Y - g / 2, e.rightEdge = d.value * Y + g / 2, f = l.value * Y, w = f + h / 2, D = d.value * Y, g = D - g / 2, g < w)if (f + O < D - O)w -= g, f = D - f, a.labelTextWidth = w > f ? ba(h, f) : Z(O, h - w / 2), e.labelTextWidth = 2 * (f - a.labelTextWidth / 2), a.leftEdge = l.value * Y - a.labelTextWidth / 2, a.rightEdge = l.value * Y + a.labelTextWidth / 2, e.leftEdge =
              d.value * Y - e.labelTextWidth / 2, e.rightEdge = d.value * Y + e.labelTextWidth / 2; else return e.labelTextWidth = 0, d.label.text = b, !1
          } else d && (h = ba(h, 2 * (c.max - n.value) * Y + v.chart.marginRight), a.labelTextWidth = h, a.leftEdge = l.value * Y - h / 2, a.rightEdge = l.value * Y + h / 2);
          a.nextCat = e;
          return !0
        };
        oa ? Ua > Va ? Ua = Va : 2 > Ua && (Ua = 2) : Ua = 1;
        for (gb = Xb.length; ta < gb; ta += 1)(n = Xb[ta]) && n.label && typeof n.label.text !== jb && (n.isGrid ? (mb = {plotObj: n}, n.isCat && (Gb = ta % Ua, Eb[Gb] || (Eb[Gb] = []), ub ? (sb = mb, Eb[Gb].push(sb)) : (sb = ub = mb, Eb[Gb].push(ub))), Na.push(mb)) :
        n.isTrend && Fb.push({plotObj: n}));
        Xa = c.plotBands;
        ta = 0;
        for (gb = Xa.length; ta < gb; ta += 1)(n = Xa[ta]) && n.isTrend && n.label && typeof n.label.text !== jb && Fb.push({plotObj: n});
        if (Na.length)if (!G && !A)if (k.distributedColumns)for (ta = 0, gb = Na.length; ta < gb; ta += 1)ga = Na[ta], pa = ta % Ua, n = ga.plotObj, n.label && n.isCat && (0 <= ta - Ua ? (Kb = Na[ta - Ua], ra = Kb.plotObj.value * Y + Kb.plotObj._weight * Y / 2) : (Kb = null, ra = c.min * Y - wa), ta + Ua < gb ? (Vb = Na[ta + Ua], Ea = Vb.plotObj.value * Y - Vb.plotObj._weight * Y / 2) : (Vb = null, Ea = c.max * Y + L), F = n.label, F.style && F.style !==
        M && (M = F.style, ia.setStyle(M)), da = n.value * Y, Zc = da - n._weight * Y / 2, Yc = da + n._weight * Y / 2, 1 < Ua ? (za = Zc - ra, xa = Yc + Ea, pb = Yc - Zc + ba(za, xa)) : pb = Yc - Zc, F = n.label, F.style && F.style !== M && ia.setStyle(F.style), pb < O && O < ia.getOriSize(F.text).width ? (n.label.text = b, ga.labelTextWidth = 0) : (ga.labelTextWidth = pb, I = ia.getSmartText(F.text, pb - 4, h, V), pb = I.width + 4, ga.labelTextWidth = pb, T = Z(T, I.height))); else {
          Va = Na.length;
          $b = Na.length - 1;
          (kb = (Na[$b].plotObj.value - Na[0].plotObj.value) * Y) ? (X = .1 * kb, Pa = Z(.2 * kb, kb / Va)) : Pa = X = e;
          for (H in Eb)for (ta =
                              0, $ = Eb[H].length; ta < $;) {
            for (Cb = ta + 1; !Ub(Eb[H][ta], Eb[H][Cb]);)Cb += 1;
            ta = Cb
          }
          ub && (fa = (ub.plotObj.value - c.min) * Y + wa - ub.labelTextWidth / 2);
          n = Na[0].plotObj;
          ub && n === ub.plotObj || (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), u = ia.getOriSize(F.text).width + 4, da = (n.value - c.min) * Y + wa, ub && (Ma = fa - da, u = Ma < u && Ma > O / 2 ? 2 * Ma : 0), Na[0].labelTextWidth = u, 0 < u && (q = da - u / 2), q < fa && (fa = q));
          sb && (u = sb.labelTextWidth, aa = (c.max - sb.plotObj.value) * Y + L - u / 2);
          n = Na[$b].plotObj;
          sb && n === sb.plotObj || (F = n.label, F.style && F.style !==
          M && (M = F.style, ia.setStyle(M)), u = ia.getOriSize(F.text).width + 4, da = (c.max - n.value) * Y + L, sb && (Ma = da - aa, u = Ma < u && Ma > O / 2 ? 2 * Ma : 0), Na[$b].labelTextWidth = u, 0 < u && (q = da - u / 2), q < aa && (aa = q));
          yb = 0 > fa ? -fa : 0;
          zb = 0 > aa ? -aa : 0;
          eb = yb + zb;
          if (0 < eb)for (H in Zb > eb ? (U = (U = zb * e / (zb + e)) ? U + 4 : 0, v.chart.marginRight += U, e -= U, U = (U = yb * e / (yb + e)) ? U + 4 : 0, v.chart.marginLeft += U, e -= U, Y = e / (c.max - c.min)) : yb < zb ? Zb >= zb && bb ? (U = (U = zb * e / (zb + e)) ? U + 4 : 0, v.chart.marginRight += U, e -= U, Y = e / (c.max - c.min)) : db && (U = (U = yb * e / (yb + e)) ? U + 4 : 0, v.chart.marginLeft += U, e -=
            U, Y = e / (c.max - c.min)) : Zb >= yb && db ? (U = (U = yb * e / (yb + e)) ? U + 4 : 0, v.chart.marginLeft += U, e -= U, Y = e / (c.max - c.min)) : bb && (U = (U = zb * e / (zb + e)) ? U + 4 : 0, v.chart.marginRight += U, e -= U, Y = e / (c.max - c.min)), L = v.chart.marginRight, wa = v.chart.marginLeft, kb = (Na[$b].plotObj.value - Na[0].plotObj.value) * Y, X = .1 * kb, Pa = Z(.2 * kb, kb / Va), Eb) {
            ta = 0;
            for ($ = Eb[H].length; ta < $;) {
              for (Cb = ta + 1; !Ub(Eb[H][ta], Eb[H][Cb], !0);)Cb += 1;
              ta = Cb
            }
            H += 1
          }
          ta = 0;
          for (gb = Na.length; ta < gb; ta += 1)if (ga = Na[ta], pa = ta % Ua, n = ga.plotObj, n.label)if (n.isCat)ga.labelTextWidth && (Ba[pa] =
            ga); else {
            Vb = (Kb = Ba[pa]) ? Kb.nextCat : Eb[pa] ? Eb[pa][0] : null;
            va = null;
            if (ta >= Ua)for (Pb = ta - Ua, va = Na[Pb]; !va.labelTextWidth;)if (Pb >= Ua)Pb -= Ua, va = Na[Pb]; else {
              va = null;
              break
            }
            ra = va ? va.rightEdge : c.min * Y - wa;
            Ea = Vb ? Vb.leftEdge : c.max * Y + L;
            F = n.label;
            F.style && F.style !== M && (M = F.style, ia.setStyle(M));
            u = ia.getOriSize(F.text).width + 4;
            ec = n.value * Y - u / 2;
            if (k.isBar && ta == gb - 1 && va)ra > ec && (va.plotObj.label.text = b, va.labelTextWidth = 0, ra = va.leftEdge); else if (ra > ec || Ea < ec + u) {
              n.label.text = b;
              ga.labelTextWidth = 0;
              continue
            }
            ra = Z(ra, ec);
            da = n.value * Y;
            pb = 2 * ba(da - ra, Ea - da);
            pb.toFixed && (pb = pb.toFixed(2));
            F = n.label;
            F.style && F.style !== M && ia.setStyle(F.style);
            pb < O && O < ia.getOriSize(F.text).width ? (n.label.text = b, ga.labelTextWidth = 0) : (ga.labelTextWidth = pb, I = ia.getSmartText(F.text, pb - 4, h, V), pb = I.width + 4, ga.labelTextWidth = pb, ga.leftEdge = da - pb / 2, ga.rightEdge = da + pb / 2, T = Z(T, I.height))
          }
          va = Fa = null;
          ta = 0;
          for (gb = Na.length; ta < gb; ta += 1)if (ga = Na[ta], n = ga.plotObj, pa = ta % Ua, n.isCat && ga.labelTextWidth) {
            va = Fa = null;
            da = n.value * Y;
            if (ta >= Ua)for (Pb = ta - Ua, va = Na[Pb]; !va.labelTextWidth;)if (Pb >
              Ua)Pb -= Ua, va = Na[Pb]; else {
              va = null;
              break
            }
            za = va ? da - va.rightEdge : da - c.min * Y + v.chart.marginLeft;
            if (ta + Ua < gb)for (Ca = ta + Ua, Fa = Na[Ca]; !Fa.labelTextWidth;)if (Ca + Ua < gb - 1)Ca += Ua, Fa = Na[Ca]; else {
              Fa = null;
              break
            }
            xa = Fa ? Fa.leftEdge - da : c.max * Y + v.chart.marginRight - da;
            pb = 2 * ba(za, xa);
            pb > Pa && (pb = Pa);
            pb > ga.oriWidth && (pb = ga.oriWidth);
            ga.labelTextWidth = pb;
            F = n.label;
            F.style && F.style !== M && ia.setStyle(F.style);
            I = ia.getSmartText(F.text, pb - 4, h, V);
            ga.labelTextWidth = I.width + 4;
            T = Z(T, I.height);
            ga.rightEdge = da + ga.labelTextWidth / 2
          }
        } else if (A)for (ta =
                            0, gb = Na.length; ta < gb; ta += 1)if ((n = Na[ta].plotObj) && n.label && n.label.text) {
          F = n.label;
          F.style && F.style !== M && (M = F.style, ia.setStyle(M));
          H = 1;
          if (ta + H < gb)for (Cc = Na[H + ta].plotObj; Cc && (Cc.value - n.value) * Y < O;)if (n.isCat) {
            if (Cc.label) {
              Cc.label.text = b;
              H += 1;
              if (H + ta >= gb - 1)break;
              Cc = Xb[H + ta].plotObj
            }
          } else if (Cc.isCat) {
            n.label.text = b;
            n = Cc;
            ta += H - 1;
            F = n.label;
            F.style && F.style !== M && (M = F.style, ia.setStyle(M));
            break
          }
          Bb = Z(Bb, ia.getOriSize(F.text).width + 4)
        }
        H = 0;
        for (gb = Fb.length; H < gb; H += 1)(n = Fb[H].plotObj) && n.label && void 0 !==
        l(n.label.text) && (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), I = ia.getOriSize(F.text), F.verticalAlign === Da ? ab = Z(ab, I.height) : S = Z(S, I.height))
      } else {
        for (gb = Xb.length; ta < gb; ta += 1)(n = Xb[ta]) && (n.isGrid ? Na.push(n) : n.isTrend && Fb.push(n));
        Xa = c.plotBands;
        ta = 0;
        for (gb = Xa.length; ta < gb; ta += 1)(n = Xa[ta]) && Fb.push(n);
        $b = Na.length - 1;
        Va = Na.length;
        oa && (Ua > Va ? Ua = Va : 2 > Ua && (Ua = 2));
        if (Va)for (c.scroll && c.scroll.viewPortMin && c.scroll.viewPortMax ? (Tb = c.scroll.viewPortMin, gc = c.scroll.viewPortMax, bb = db = !1) : (Tb =
          c.min, gc = c.max), kb = (Na[$b].value - Na[0].value) * Y, rb = Rb = kb / (hb - 1), fc = (Na[0].value - Tb) * Y, jc = (gc - Na[$b].value) * Y, "auto" === J ? rb < z && (A = R ? 300 : 270, q = ea, ea = O, O = q, V = !0) : "stagger" === J && (rb *= Ua), "line" !== this.defaultSeriesType && ("area" === this.defaultSeriesType ? k.drawFullAreaBorder && (Oa > fc && (Tb = c.min -= Oa / (2 * Y), fc += (Na[0].value - Tb) * Y), Oa > jc && (gc = c.max += Oa / (2 * Y), jc += (gc - Na[$b].value) * Y)) : (Oa > fc && (Tb = c.min -= Oa / (2 * Y), fc += (Na[0].value - Tb) * Y), Oa > jc && (gc = c.max += Oa / (2 * Y), jc += (gc - Na[$b].value) * Y))), O < P && (O = P), ma = oa || G ?
          Z(1, La) : Z(1, La, Aa(O / rb)), k.x && (k.x.stepValue = ma), rb *= ma, ca = 2 * (fc + wa), (F = Xb[0].label) && F.text && (F.style && ia.setStyle(F.style), u = 270 === A ? ba(rb, ia.getOriSize(F.text).height + 4) : ba(rb, ia.getOriSize(F.text).width + 4), u > ca && (G || (yb = (u - ca) / 2), db || (xb && (yb = 0), rb -= yb / (hb - 1), Jb = rb * (hb - 1), Y = rb, lc = (kb - Jb) / Y, gc = c.max += lc, Tb = c.min -= lc, yb = 0, kb = Jb, fc = (Na[0].value - Tb) * Y, jc = (gc - Na[$b].value) * Y))), ca = 2 * (jc + L), (F = Xb[$b].label) && F.text && (F.style && ia.setStyle(F.style), u = 270 === A ? ba(rb, ia.getOriSize(F.text).height + 4) : ba(rb,
          ia.getOriSize(F.text).width + 4), u > ca && (G || (zb = (u - ca) / 2), bb || (xb && (zb = 0), rb -= zb / (hb - 1), Jb = rb * (hb - 1), Y = rb, lc = (kb - Jb) / Y, zb = 0, kb = Jb, fc = (Na[0].value - Tb) * Y, jc = (gc - Na[$b].value) * Y))), eb = yb + zb, 0 < eb && (Zb > eb ? (U = (U = zb * e / (zb + e)) ? U + 4 : 0, v.chart.marginRight += U, e -= U, U = (U = yb * e / (yb + e)) ? U + 4 : 0, v.chart.marginLeft += U, e -= U, Y = e / (c.max - c.min)) : yb < zb ? Zb >= zb && bb ? (U = (U = zb * e / (zb + e)) ? U + 4 : 0, v.chart.marginRight += U, e -= U, Y = e / (c.max - c.min)) : db && (U = (U = yb * e / (yb + e)) ? U + 4 : 0, v.chart.marginLeft += U, e -= U, Y = e / (c.max - c.min)) : Zb >= yb && db ? (U = (U =
          yb * e / (yb + e)) ? U + 4 : 0, v.chart.marginLeft += U, e -= U, Y = e / (c.max - c.min)) : bb && (U = (U = zb * e / (zb + e)) ? U + 4 : 0, v.chart.marginRight += U, e -= U, Y = e / (c.max - c.min)), kb = (Na[$b].value - Na[0].value) * Y, rb = kb / (hb - 1), oa && (rb *= Ua), ma = oa || G ? Z(1, La) : A ? Z(1, La, Aa(ea / rb)) : Z(1, La, Aa(O / rb)), k.x && (k.x.stepValue = ma), rb *= ma), H = 0; H < Va; H += 1) {
          n = Na[H];
          if (H % ma && n.label) {
            if (n.stepped = !0, n.label.style = c.steppedLabels.style, !Yb)continue
          } else n.stepped = !1;
          n && n.label && void 0 !== l(n.label.text) && (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)),
            A && G ? (I = ia.getOriSize(F.text), Bb = Z(Bb, I.width + 4), T = Z(T, I.height)) : G || (I = A || oa ? ia.getOriSize(F.text) : ia.getSmartText(F.text, rb - 4, h, V), Bb = Z(Bb, I.width + 4), T = Z(T, I.height)))
        }
        H = 0;
        for (gb = Fb.length; H < gb; H += 1)(n = Fb[H]) && n.label && void 0 !== l(n.label.text) && (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), I = ia.getOriSize(F.text), F.verticalAlign === Da ? ab = Z(ab, I.height) : S = Z(S, I.height));
        c.scroll && c.scroll.enabled && !A && !G && (lc = Bb / 2, v.chart.marginLeft < lc && (Ab = lc - v.chart.marginLeft, Zb > Ab && (e -= Ab, Zb -= Ab,
          v.chart.marginLeft += Ab)), v.chart.marginRight < lc && (Ab = lc - v.chart.marginRight, Zb > Ab && (e -= Ab, Zb -= Ab, v.chart.marginRight += Ab)))
      }
      G ? (wb = ea, A && (wb = Bb)) : wb = A ? Bb : oa ? Ua * T : T;
      0 < wb && (wb + Q > Ja && (wb = Ja - Q, Ua = Math.floor(wb / T)), Hb += Q + wb);
      0 < C && (Hb += C + kc);
      Ta = Q - 4;
      Ya = S + Hb + 2;
      q = 0;
      Ya > h && (Ma = Ya - h, kc > Ma ? (kc -= Ma, Ma = 0) : (Ma -= kc, kc = 0, Ta > Ma ? (Ta -= Ma, Ma = 0) : (Ma -= Ta, Ta = 0), Q = Ta + 4), S > Ma ? (S -= Ma, Ma = 0) : (0 < S && (Ma -= S, S = 0), 0 < Ma && (ab > Ma ? (ab -= Ma, Ma = 0) : (0 < ab && (Ma -= ab, ab = 0), 0 < Ma && ((q = C - E) > Ma ? (C -= Ma, Ma = 0) : (Ma -= q, C = E, 0 < Ma && ((q = wb - ea) > Ma ? (wb -= Ma, Ma =
        0) : (Ma -= q, wb = ea, 0 < Ma && (Ma -= C + kc, C = 0, 0 < Ma && (Ma -= wb, wb = 0, 0 < Ma && (Q -= Ma)))))))))));
      Q += Ia;
      Tc = k.is3d ? -v.chart.xDepth : 0;
      $c = wb + Q;
      Ec = Tc;
      ad = .5 * ea;
      B = ea + Q;
      gb = Na.length;
      la = 0;
      if (lb)if (A)for (vc = na, nc = R ? Q + 8 : Q + 4, gb = Na.length, H = 0; H < gb; H += 1)(n = Na[H].plotObj) && n.label && void 0 !== l(n.label.text) && (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), ta = 1, I = ia.getSmartText(F.text, wb - 4, O, V), F.text = I.text, I.tooltext && (F.originalText = I.tooltext), Ec = Tc + ad / 2, F.y = nc, F.x = Ec, F.rotation = A, F.textAlign = vc, la += 1); else for (Mc =
                                                                                                                                                                                                                                                                                                                                                                                                                                                       wb, vc = ya, nc = B, H = 0; H < gb; H += ma)n = Na[H].plotObj, ea = parseInt(n.label.style.lineHeight, 10), n && n.label && void 0 !== l(n.label.text) && (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), G || (I = ia.getSmartText(F.text, Na[H].labelTextWidth - 4, Mc, V), F.text = I.text, I.tooltext && (F.originalText = I.tooltext), oa && (nc = B + la % Ua * ea)), F.y = nc, F.x = Ec, F.rotation = A, F.textAlign = vc, la += 1); else {
        A ? (Mc = rb, Uc = wb - 4, vc = na, nc = R ? Q + 8 : Q + 4) : oa ? (Uc = rb - 4, vc = ya) : (Mc = wb, Uc = rb - 4, vc = ya, nc = B);
        for (H = 0; H < gb; H += ma)n = Na[H], ea = Aa(parseFloat(n.label.style.lineHeight)),
          ad = .5 * ea, B = ea + Q, n && n.label && void 0 !== l(n.label.text) && (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), G || (oa && (Mc = ea), Qb = wa + (H - Tb) * Rb - v.chart.spacingLeft, Ib = 300 === A ? ba(qa(2.999 * Qb * Qb + Qb * Qb) - Q, Uc) : Uc, I = ia.getSmartText(F.text, Ib, Mc, V), qb = Z(qb, A ? I.width : I.height), F.text = I.text, I.tooltext && (F.originalText = I.tooltext), oa && (nc = B + la % Ua * ea)), A ? Ec = Tc + .5 * ea : oa || (nc = ea + Q), F.y = nc, F.x = Ec, F.rotation = A, F.textAlign = vc, la += 1);
        300 === A && (wb = qb, $c = wb + Q);
        d._labelY = B;
        d._labelX = Tc;
        d._yShipment = nc;
        d._isStagger =
          oa;
        d._rotation = A;
        d._textAlign = vc;
        d._adjustedPx = ad;
        d._staggerLines = Ua;
        d._labelHeight = ea
      }
      gb = Fb.length;
      for (H = Vc = Nc = 0; H < gb; H += 1)(n = Fb[H].plotObj ? Fb[H].plotObj : Fb[H]) && n.label && void 0 !== l(n.label.text) && (F = n.label, F.style && F.style !== M && (M = F.style, ia.setStyle(M)), F.verticalAlign === Da ? (I = ia.getSmartText(F.text, e, ab, !0), Vc = Z(Vc, I.height), F.text = I.text, I.tooltext && (F.originalText = I.tooltext), F.y = $c + ia.getOriSize(F.text).height, F.x = Ec) : (I = ia.getSmartText(F.text, e, S, !0), Nc = Z(Nc, I.height), F.text = I.text, I.tooltext &&
      (F.originalText = I.tooltext), F.y = -(S - ia.getOriSize("W").height + Q + 2)));
      0 < C && (ia.setStyle(c.title.style), s = ia.getSmartText(c.title.text, e, C), c.title.text = s.text, s.tooltext && (c.title.originalText = s.tooltext), c.title.margin = $c + Vc + kc);
      Hb = Vc;
      0 < wb && (k.horizontalAxisHeight = Q + wb - Ia, Hb += k.horizontalAxisHeight);
      0 < C && (Hb += K = C + kc);
      Hb = Hb || Q - Ia;
      v.chart.marginBottom += Hb;
      0 < Nc && (v.chart.marginTop += Nc, Hb += Nc);
      if (c.opposite)for (c.title.margin -= wb - (s && s.height || 0) + Q, Hb -= K, v.chart.marginTop += Hb, v.chart.marginBottom -= Hb,
                            v.xAxis.opposite = 1, gb = Xb.length, ta = 0; ta < gb; ta += 1)(n = Xb[ta]) && n.isGrid && (F = n.label) && void 0 !== F.text && (F.textAlign = ua, F.y -= nc + Q + 4);
      return Hb
    }, Y = k.configureLegendOptions = function (d, f, v, g, e) {
      g = d.legend;
      var h = d.chart, l = h.is3D ? Ia.chart3D : Ia.chart2D, p = h.useRoundEdges, k = a(f.legendiconscale, 1), m = (parseInt(g.itemStyle.fontSize, 10) || 10) + 1, I = this.colorManager, n;
      if (0 >= k || 5 < k)k = 1;
      g.padding = 4;
      0 >= m && (m = 1);
      n = 3 * k;
      m = ba(m * k, e - 8);
      0 >= m && (n = m = 0);
      g.symbolWidth = m;
      g.symbolPadding = n;
      g.textPadding = 4;
      g.legendHeight = e = m + 2 * n;
      g.rowHeight = Z(parseInt(g.itemStyle.lineHeight, 10) || 12, e);
      v ? (g.align = na, g.verticalAlign = "middle", g.layout = "vertical") : g.x = (h.marginLeft - h.spacingLeft - h.marginRight + h.spacingRight) / 2;
      v = c(f.legendbordercolor, I.getColor(l.legendBorderColor));
      e = a(f.legendborderalpha, 100);
      h = a(f.legendbgalpha, 100);
      g.backgroundColor = fa(c(f.legendbgcolor, I.getColor(l.legendBgColor)), h);
      g.borderColor = fa(v, e);
      g.borderWidth = a(f.legendborderthickness, !p || f.legendbordercolor ? 1 : 0);
      g.shadow = Boolean(a(f.legendshadow, 1));
      g.symbol3DLighting =
        Boolean(a(f.use3dlighting, f.useplotgradientcolor, 1));
      g.shadow && (g.shadow = {enabled: g.shadow, opacity: Z(e, h) / 100});
      g.reversed = Boolean(a(f.reverselegend, 0) - a(this.reverseLegend, 0));
      g.style = {padding: 4};
      Boolean(a(f.interactivelegend, 1)) ? g.symbolStyle = {
        _cursor: "hand",
        cursor: "pointer"
      } : (d.legend.interactiveLegend = !1, g.itemStyle.cursor = "default", g.itemHoverStyle = {cursor: "inherit"});
      g.borderRadius = a(f.legendborderradius, p ? 3 : 0);
      g.legendAllowDrag = Boolean(a(f.legendallowdrag, 0));
      g.title.text = C(q(f.legendcaption,
        b));
      g.legendScrollBgColor = T(c(f.legendscrollbgcolor, f.scrollcolor, I.getColor("altHGridColor")));
      g.legendScrollBarColor = c(f.legendscrollbarcolor, v);
      g.legendScrollBtnColor = c(f.legendscrollbtncolor, v)
    }, Yb = k.placeLegendBlockRight = function (c, d, g, D, e) {
      this.configureLegendOptions(c, d.chart, !0, e, g);
      var h = this.snapLiterals || (this.snapLiterals = {}), l = 0, p = c.series, k, m = c[f], n = this.smartLabel || m.smartLabel, I = c.chart.spacingRight, H = c.legend, F, M = H.textPadding, s = H.title.padding, O = H.symbolWidth, q = H.symbolPadding, t =
        O + 2 * q, u = 2 * D, ca = 0, A = a(d.chart.legendpadding, 7);
      d = A + H.borderWidth / 2 + a(d.chart.canvasborderthickness, 1);
      var z = 2 * H.padding, ea = z, C = !1, ma = [];
      g -= z + A;
      e && (p = p && p[0] && p[0].data);
      if (typeof p === jb || typeof p.length === jb)return 0;
      e = p.length;
      for (l = 0; l < e; l += 1)(k = p[l]) && !1 !== k.showInLegend && (k.__i = l, ma.push(k));
      ma.sort(function (a, b) {
        return a.legendIndex - b.legendIndex || a.__i - b.__i
      });
      e = ma.length;
      F = g - t - A - M;
      0 > F && (F = 0);
      n.setStyle(H.itemStyle);
      H.reversed && ma.reverse();
      for (l = 0; l < e; l += 1)k = ma[l], C = !0, k._legendX = 0, k._legendY =
        ea, 0 === F ? (ea += k._legendH = t, k.name = b, k._totalWidth = O + q) : (p = n.getSmartText(k.name, F, u), k.name = p.text, p.tooltext && (k.originalText = p.tooltext), p.height < t && (k._legendTestY = (t - p.height) / 2), k._totalWidth = O + q + M + p.width + A, ea += k._legendH = Z(p.height, t), ca = Z(p.width, ca));
      if (C)return H.itemWidth = ca + t + A + M, H.width = H.itemWidth + z, H.title.text !== b && (n.setStyle(H.title.style), p = n.getSmartText(H.title.text, g, u), H.title.text = p.text, p.tooltext && (H.title.originalText = p.tooltext), l = p.width + z, H.width < l && (H.initialItemX = (l -
      H.width) / 2, H.width = l), H.initialItemY = p.height + s, ea += H.initialItemY), H.height = H.totalHeight = ea, H.height > D && (H.height = D, H.scroll.enabled = !0, H.scroll.flatScrollBars = m.flatScrollBars, H.scroll.scrollBar3DLighting = m.scrollBar3DLighting, H.width += (H.scroll.scrollBarWidth = 10) + (H.scroll.scrollBarPadding = 2)), h.legendstartx = m.width - I - H.width, h.legendwidth = H.width, h.legendendx = h.legendstartx + h.legendwidth, h.legendheight = H.height, d = ba(H.width + d, g), c.chart.marginRight += d + A, d;
      H.enabled = !1;
      return 0
    }, oc = k.placeLegendBlockBottom =
      function (c, d, g, D, e) {
        this.configureLegendOptions(c, d.chart, !1, e, g);
        var h = this.snapLiterals || (this.snapLiterals = {}), l = 0, p = c.series, k = c[f], m = k.smartLabel || this.smartLabel, n = c.chart, I = n.spacingBottom, H = n.spacingLeft, n = n.spacingRight, F = c.legend, M, s = F.textPadding, O = F.title.padding, q, t = F.symbolWidth, u = F.symbolPadding, ca = F.legendHeight, A = d.chart;
        q = 0;
        var z = 2 * D, ea = F.rowHeight, ma = .05 * ea, B = [];
        M = a(A.minimisewrappinginlegend, 0);
        var A = a(parseInt(A.legendnumcolumns, 10), 0), E = 0, la = 0, V = 0, oa = l = 0, G = 0, K = 0, La = F.padding,
          ia = 2 * La, La = s + u + La;
        d = a(d.chart.legendpadding, 7) + F.borderWidth / 2 + 1;
        var Q = ia, P = !1, Ja, J = [], Ga = !1, Y = 0, Ia = 0;
        0 > A && (A = 0);
        g -= ia;
        m.setStyle(F.itemStyle);
        l = m.getOriSize(lb).height;
        d = ba(d, D - l - 8);
        D -= d;
        e && (p = p && p[0] && p[0].data);
        if (typeof p === jb || typeof p.length === jb)return 0;
        e = p.length;
        for (l = 0; l < e; l += 1)(Ja = p[l]) && !1 !== Ja.showInLegend && (Ja.__i = l, J.push(Ja));
        J.sort(function (a, b) {
          return a.legendIndex - b.legendIndex || a.__i - b.__i
        });
        e = J.length;
        m.setStyle(F.itemStyle);
        for (l = 0; l < e; l += 1)P = !0, J[l].name = C(J[l].name), p = m.getOriSize(J[l].name),
          E = Z(E, p.width), G = Z(G, ba(p.height, z)), la += p.width, V += 1;
        l = la / V;
        ca = ca + ma + s + u + ia;
        la += ca * V;
        if (P) {
          l += ca;
          E += ca;
          0 < A && V < A && (A = V);
          la <= g && (0 >= A || A === V) ? (A = V, oa = l = la / V, Ga = !0, G > ea && (K = (G - ea) / 2, ea = G)) : 0 < A && (oa = g / A) > l ? oa > E && (oa = E) : g > E && (M || 1.5 * l > E) ? (A = Ka(g / E), V < A && (A = V), oa = E) : g >= 2 * l ? (A = Ka(g / l), V < A && (A = V), oa = Ka(g / A), oa > E && (oa = E)) : (A = 1, oa = g);
          F.itemWidth = oa;
          M = Aa(oa - ca);
          0 > M && (u = M = s = 0);
          F.symbolPadding = u;
          F.textPadding = s;
          F.width = oa * A - ma;
          F.title.text !== b && (m.setStyle(F.title.style), p = m.getSmartText(F.title.text, g, z), F.title.text =
            p.text, p.tooltext && (F.title.originalText = p.tooltext), q = p.width + ia, F.width < q && (F.initialItemX = (q - F.width) / 2, F.width = q), F.initialItemY = q = p.height + O);
          m.setStyle(F.itemStyle);
          F.reversed && J.reverse();
          for (l = 0; l < e; l += 1) {
            g = J[l];
            0 === M && (B[Y] = !0, g.name = b, O = 1, s = parseInt(Y / A, 10), ma = Y % A, g._legendX = ma * oa, g._legendY = s * ea + ia, g._legendH = O * ea, g._totalWidth = t + u);
            if (Ga)p = m.getOriSize(g.name), p.height < ea && (g._legendTestY = (ea - p.height) / 2), g._markerYGutter = K, g._legendX = Ia, g._legendY = ia, g._legendH = ea, g._totalWidth = t + La +
            p.width, Ia += p.width + ca; else {
              p = m.getSmartText(g.name, M, z);
              g.name = p.text;
              for (p.tooltext && (g.originalText = p.tooltext); !0 === B[Y];)Y += 1;
              s = p.height / ea;
              ma = Y;
              for (O = 0; O < s; O += 1, ma += A)B[ma] = !0;
              p.height < ea && (g._legendTestY = (ea - p.height) / 2);
              s = parseInt(Y / A, 10);
              ma = Y % A;
              g._legendX = ma * oa;
              g._legendY = s * ea + ia;
              g._legendH = O * ea;
              g._totalWidth = t + La + p.width
            }
            Y += 1
          }
          m = Ga ? 1 : Aa(B.length / A);
          F.height = F.totalHeight = Q + (m * ea + q);
          F.rowHeight = ea;
          F.legendNumColumns = A;
          F.height > D && (F.height = D, F.scroll.enabled = !0, F.scroll.flatScrollBars = k.flatScrollBars,
            F.scroll.scrollBar3DLighting = k.scrollBar3DLighting, F.width += (F.scroll.scrollBarWidth = 10) + (F.scroll.scrollBarPadding = 2));
          h.legendstartx = H + .5 * (k.width - H - n - F.width) + (F.x || 0);
          h.legendwidth = F.width;
          h.legendendx = h.legendstartx + h.legendwidth;
          h.legendstarty = k.height - I - F.height;
          h.legendheight = F.height;
          h.legendendy = h.legendstarty + h.legendheight;
          d += F.height;
          c.chart.marginBottom += d;
          return d
        }
        F.enabled = !1;
        return 0
      }, Kb = function (a, b) {
      return a.value - b.value
    }, Gb = function (a, b, c) {
      var d = b._originalText;
      a = a[f].smartLabel;
      b.text = b.rotation ? a.getSmartText(d, c, b._actualWidth).text : a.getSmartText(d, b._actualWidth, c).text;
      b.centerYAxisName = !0
    }, Vb = k.adjustVerticalAxisTitle = function (a, b, c) {
      if (b && b.text) {
        var d = b.text, e = a[f].smartLabel, g = 2 * ba(a.chart.marginTop, a.chart.marginBottom) + c, h = c + a.chart.marginTop + a.chart.marginBottom;
        b.style && e.setStyle(b.style);
        d = e.getOriSize(d);
        void 0 === b.centerYAxisName && (b.centerYAxisName = !0);
        b.rotation ? d.width > g && (b.y = h / 2 - (c / 2 + a.chart.marginTop), b.centerYAxisName = !1) : d.height > g && (b.y = (h / 2 - (c /
        2 + a.chart.marginTop)) / 2, b.centerYAxisName = !1)
      }
    }, ub = k.adjustVerticalCanvasMargin = function (b, c, d, f) {
      var e = c.chart, g = c = 0, h = 0, l = a(e.canvastopmargin, 0), e = a(e.canvasbottommargin, 0), p = l / (l + e), k = b.chart.marginTop, m = b.chart.marginBottom;
      e > m && (c += e - m);
      l > k && (c += l - k);
      c > d ? l > k && e > m ? (g = d * p, h = d * (1 - p)) : l > k ? g = d : h = d : 0 < c && (e > m && (h = e - m), l > k && (g = l - k));
      g && (b.chart.marginTop += g);
      h && (b.chart.marginBottom += h, f && f.title && (f.title.margin += h));
      return g + h
    }, Pa = k.adjustHorizontalCanvasMargin = function (b, c, d, f, e) {
      var g = c.chart;
      c =
        a(g.canvasleftmargin, 0);
      var g = a(g.canvasrightmargin, 0), h = c / (c + g), l = 0, p = b.chart.marginLeft, k = b.chart.marginRight, m = 0, n = 0;
      c > p && (l += c - p);
      g > k && (l += g - k);
      l > d ? c > p && g > k ? (m = d * h, n = d * (1 - h)) : g > k ? n = d : m = d : 0 < l && (c > p && (m = c - p), g > k && (n = g - k));
      m && (b.chart.marginLeft += m, f && f.title && (f.title.margin += m));
      n && (b.chart.marginRight += n, e && e.title && (e.title.margin += n));
      return n + m
    }, Cb = function (a, b) {
      return a - b
    }, mb = k.getDataParser = {
      column: function (b, d, g) {
        var D = b[f], e = d.borderWidth;
        return function (f, h, l) {
          var p = d.plotgradientcolor,
            k = d.is3d, m = d.isRoundEdges, n = d.plotBorderColor, I = c(f.color, d.color), H = c(f.ratio, d.ratio), O = L(d.plotBorderAlpha), q = a(f.dashed, d.dashed), t = c(f.dashlen, d.dashLen), u = c(f.dashgap, d.dashGap), A = d.use3DLighting, ca = L(c(f.alpha, d.alpha)).toString(), z = {opacity: ca / 100}, ea = d.isBar, C = d.fillAangle, ma = 0 > l ? ea ? 180 - C : 360 - C : C, C = R(I + Ba + p, ca, H, ma, m, n, c(f.alpha, O).toString(), ea, k), B = q ? s(t, u, e) : "none";
          h = g.getPointStub(f, l, D.oriCatTmp[h], b, d, d.showValues, d.yAxis);
          f = g.pointHoverOptions(f, d, {
            plotType: "column",
            is3d: k,
            isBar: ea,
            use3DLighting: A,
            isRoundEdged: m,
            color: I,
            gradientColor: p,
            alpha: ca,
            ratio: H,
            angle: ma,
            borderWidth: e,
            borderColor: n,
            borderAlpha: O,
            borderDashed: q,
            borderDashGap: u,
            borderDashLen: t,
            shadow: z
          });
          h.y = l;
          h.shadow = z;
          h.color = C[0];
          h.borderColor = C[1];
          h.borderWidth = e;
          h.use3DLighting = A;
          h.dashStyle = B;
          h.tooltipConstraint = g.tooltipConstraint;
          h.hoverEffects = f.enabled && f.options;
          h.rolloverProperties = f.enabled && f.rolloverOptions;
          return h
        }
      }, line: function (b, d, g) {
        var D = b[f];
        return function (e, f, h) {
          var l = a(e.alpha, d.lineAlpha), p =
          {opacity: l / 100}, k = a(e.anchorsides, d.anchorSides, 0), m = a(e.anchorborderthickness, d.anchorBorderThickness, 1), n = T(c(e.anchorbordercolor, d.anchorBorderColor)), I = T(c(e.anchorbgcolor, d.anchorBgColor)), H = a(e.anchorstartangle, d.anchorStartAngle, 90), O = c(e.anchoralpha, d.anchorAlpha), q = c(e.anchorbgalpha, O), t = a(e.anchorradius, d.anchorRadius), u = Boolean(a(e.anchorshadow, d.anchorShadow, 0));
          f = g.getPointStub(e, h, D.oriCatTmp[f], b, d, d.showValues, d.yAxis);
          var A = c(e.anchorimageurl, d.imageUrl), ca = c(e.anchorimagescale, d.imageScale),
            z = c(e.anchorimagealpha, d.imageAlpha);
          f.y = h;
          f.shadow = p;
          f.anchorShadow = d.anchorShadow;
          f.dashStyle = a(e.dashed, d.lineDashed) ? s(d.lineDashLen, d.lineDashGap, d.lineThickness) : "none";
          f.color = {FCcolor: {color: T(c(e.color, d.lineColor)), alpha: l}};
          f.valuePosition = c(e.valueposition, d.valuePosition);
          h = g.pointHoverOptions(e, d, {
            plotType: "anchor",
            anchorBgColor: I,
            anchorAlpha: O,
            anchorBgAlpha: q,
            anchorAngle: H,
            anchorBorderThickness: m,
            anchorBorderColor: n,
            anchorBorderAlpha: O,
            anchorSides: k,
            anchorRadius: t,
            imageUrl: A,
            imageScale: ca,
            imageAlpha: z,
            shadow: p
          });
          f.marker = {
            enabled: void 0 === d.drawAnchors ? 0 !== l : !!d.drawAnchors,
            shadow: u && {opacity: O / 100},
            fillColor: {
              FCcolor: {
                color: T(c(e.anchorbgcolor, d.anchorBgColor)),
                alpha: (c(e.anchorbgalpha, d.anchorBgAlpha) * O / 100).toString()
              }
            },
            lineColor: {FCcolor: {color: T(c(e.anchorbordercolor, d.anchorBorderColor)), alpha: O}},
            imageUrl: A,
            imageScale: ca,
            imageAlpha: z,
            lineWidth: a(e.anchorborderthickness, d.anchorBorderThickness),
            radius: a(e.anchorradius, d.anchorRadius),
            symbol: bb(a(e.anchorsides, d.anchorSides)),
            startAngle: c(e.anchorstartangle, d.anchorAngle)
          };
          f.hoverEffects = h.enabled && h.options;
          f.rolloverProperties = h.enabled && h.rolloverOptions;
          return f
        }
      }, area: function (b, d, g) {
        var h = b[f];
        return function (e, f, l) {
          var p = c(e.alpha, d.fillAlpha), k = {
            opacity: Z(p, d.lineAlpha) / 100,
            inverted: !0
          }, m = a(e.anchorsides, d.anchorSides, 0), n = a(e.anchorborderthickness, d.anchorBorderThickness, 1), I = T(c(e.anchorbordercolor, d.anchorBorderColor)), H = T(c(e.anchorbgcolor, d.anchorBgColor)), F = a(e.anchorstartangle, d.anchorStartAngle, 90), O = c(e.anchoralpha,
            d.anchorAlpha), s = c(e.anchorbgalpha, O), q = a(e.anchorradius, d.anchorRadius), t = Boolean(a(e.anchorshadow, d.anchorShadow, 0));
          f = g.getPointStub(e, l, h.oriCatTmp[f], b, d, d.showValues, d.yAxis);
          var u = c(e.anchorimageurl, d.imageUrl), A = c(e.anchorimagescale, d.imageScale), ca = c(e.anchorimagealpha, d.imageAlpha);
          f.y = l;
          f.shadow = k;
          f.anchorShadow = d.anchorShadow;
          f.color = {FCcolor: {color: T(c(e.color, d.fillColor)), alpha: p}};
          f.valuePosition = c(e.valueposition, d.valuePosition);
          l = g.pointHoverOptions(e, d, {
            plotType: "anchor",
            anchorBgColor: H,
            anchorAlpha: O,
            anchorBgAlpha: s,
            anchorAngle: F,
            anchorBorderThickness: n,
            anchorBorderColor: I,
            anchorBorderAlpha: O,
            anchorSides: m,
            anchorRadius: q,
            imageUrl: u,
            imageScale: A,
            imageAlpha: ca,
            shadow: k
          });
          f.marker = {
            enabled: d.drawAnchors,
            shadow: t && {opacity: O / 100},
            fillColor: {
              FCcolor: {
                color: T(c(e.anchorbgcolor, d.anchorBgColor)),
                alpha: (c(e.anchorbgalpha, d.anchorBgAlpha) * O / 100).toString()
              }
            },
            lineColor: {FCcolor: {color: T(c(e.anchorbordercolor, d.anchorBorderColor)), alpha: O}},
            imageUrl: u,
            imageScale: A,
            imageAlpha: ca,
            lineWidth: a(e.anchorborderthickness,
              d.anchorBorderThickness),
            radius: q,
            symbol: bb(a(e.anchorsides, d.anchorSides)),
            startAngle: c(e.anchorstartangle, d.anchorAngle)
          };
          f.hoverEffects = l.enabled && l.options;
          f.rolloverProperties = l.enabled && l.rolloverOptions;
          f.events = {click: d.getLink};
          return f
        }
      }
    };
  d.core.options.resizeTrackingInterval = 300;
  d.core.options.preventTrackResize = !1;
  d.core.options.SVGDefinitionURL = "relative";
  k.createChart = function (b, f, g, h, e, l, p) {
    var m = b.jsVars, n, I, H = pa[g || (g = b.chartType())], O, s = m.hasNativeMessage, F = b.options, q = b.args, t;
    t = function (a) {
      var c = {renderer: "javascript"}, e = m.fcObj, l = e.width, n = e.height, I = H && H.eiMethods, q = m.overlayButton, F;
      f.jsVars = b.jsVars;
      m.container = f;
      m.hcObj = a;
      m.type = g;
      m.width = f.offsetWidth;
      m.height = f.offsetHeight;
      m.instanceAPI = O;
      if (a.hasRendered) {
        d.extend(f, hb);
        if (I && "string" !== typeof I)for (F in I)f[F] = I[F];
        m.overlayButtonActive && q && (q.innerHTML = "", q.appendChild(E.createTextNode(m.overlayButtonMessage)), a.container.appendChild(q))
      }
      (/\%/g.test(l) || /\%/g.test(n)) && f && f.parentNode && !d.core.options.preventTrackResize &&
      wa(e, f);
      h && (h({success: a.hasRendered, ref: f, id: b.id}), a.hasRendered && (k.raiseEvent("loaded", {
        type: g,
        renderer: "javascript"
      }, b, [b.id]), s || (e.__state.firstRenderNotified = !0, setTimeout(function () {
        k.raiseEvent("rendered", {renderer: "javascript"}, e, [e.id])
      }, 0))));
      a.hasRendered && m.previousDrawCount < m.drawCount && (c.width = m.width, c.height = m.height, c.drawCount = m.drawCount, c.displayingMessage = s, c.renderer = e.options.renderer, k.raiseEvent("drawcomplete", c, e, [e.id]), s || p || setTimeout(function () {
        e.__state && !e.__state.firstRenderNotified &&
        k.raiseEvent("rendered", {renderer: "javascript"}, e, [e.id]);
        d.raiseEvent("renderComplete", c, e)
      }, 0))
    };
    m.instanceAPI && m.instanceAPI.dispose && m.instanceAPI.dispose();
    O = H ? new pa(g) : new pa("stub");
    O.chartInstance = b;
    O.origRenderWidth = b.__state.renderedWidth;
    O.origRenderHeight = b.__state.renderedHeight;
    void 0 !== e ? "string" === typeof e && (e = new ea(f, e, b), s = m.hasNativeMessage = !0) : !H || !H.init || H && "stub" === H.name ? (b._chartMessageImageStyle = {
      imageHAlign: c(q.typeNotSupportedMessageImageHAlign, F.baseChartMessageImageHAlign).toLowerCase(),
      imageVAlign: c(q.typeNotSupportedMessageImageVAlign, F.baseChartMessageImageVAlign).toLowerCase(),
      imageAlpha: a(q.typeNotSupportedMessageImageAlpha, F.baseChartMessageImageAlpha),
      imageScale: a(q.typeNotSupportedMessageImageScale, F.baseChartMessageImageScale)
    }, b._chartMessageStyle = {
      color: q.typeNotSupportedMessageColor || F.baseChartMessageColor,
      fontFamily: q.typeNotSupportedMessageFont || F.baseChartMessageFont,
      fontSize: q.typeNotSupportedMessageFontSize || F.baseChartMessageFontSize
    }, e = new ea(f, F.typeNotSupportedMessage,
      b), s = m.hasNativeMessage = !0) : m.message ? (e = new ea(f, m.message, b), s = m.hasNativeMessage = !0) : m.loadError ? (b._chartMessageImageStyle = {
      imageHAlign: c(q.dataLoadErrorMessageImageHAlign, F.baseChartMessageImageHAlign).toLowerCase(),
      imageVAlign: c(q.dataLoadErrorMessageImageVAlign, F.baseChartMessageImageVAlign).toLowerCase(),
      imageAlpha: a(q.dataLoadErrorMessageImageAlpha, F.baseChartMessageImageAlpha),
      imageScale: a(q.dataLoadErrorMessageImageScale, F.baseChartMessageImageScale)
    }, b._chartMessageStyle = {
      color: q.dataLoadErrorMessageColor ||
      F.baseChartMessageColor,
      fontFamily: q.dataLoadErrorMessageFont || F.baseChartMessageFont,
      fontSize: q.dataLoadErrorMessageFontSize || F.baseChartMessageFontSize
    }, e = new ea(f, F.dataLoadErrorMessage, b), s = m.hasNativeMessage = !0) : m.stallLoad ? (b._chartMessageImageStyle = {
      imageHAlign: c(q.dataLoadStartMessageImageHAlign, F.baseChartMessageImageHAlign).toLowerCase(),
      imageVAlign: c(q.dataLoadStartMessageImageVAlign, F.baseChartMessageImageVAlign).toLowerCase(),
      imageAlpha: a(q.dataLoadStartMessageImageAlpha, F.baseChartMessageImageAlpha),
      imageScale: a(q.dataLoadStartMessageImageScale, F.baseChartMessageImageScale)
    }, b._chartMessageStyle = {
      fontFamily: q.dataLoadStartMessageFont || F.baseChartMessageFont,
      fontSize: q.dataLoadStartMessageFontSize || F.baseChartMessageFontSize,
      color: q.dataLoadStartMessageColor || F.baseChartMessageColor
    }, e = new ea(f, F.dataLoadStartMessage, b), s = m.hasNativeMessage = !0) : (d.raiseEvent("internal.drawStart", {
      chartType: g,
      logicName: O.name,
      logicBase: O.base && O.base.name,
      defaultSeriesType: O.defaultSeriesType
    }, b), n = b.jsVars && b.jsVars.themeObject &&
    b.jsVars.themeObject.getThemedJSONData() || b.getChartData(d.dataFormats.JSON, !0), I = n.data, n.error instanceof Error ? (b._chartMessageImageStyle = {
      imageHAlign: c(q.dataInvalidMessageImageHAlign, F.baseChartMessageImageHAlign).toLowerCase(),
      imageVAlign: c(q.dataInvalidMessageImageVAlign, F.baseChartMessageImageVAlign).toLowerCase(),
      imageAlpha: a(q.dataInvalidMessageImageAlpha, F.baseChartMessageImageAlpha),
      imageScale: a(q.dataInvalidMessageImageScale, F.baseChartMessageImageScale)
    }, b._chartMessageStyle = {
      fontFamily: q.dataInvalidMessageFont ||
      F.baseChartMessageFont,
      fontSize: q.dataInvalidMessageFontSize || F.baseChartMessageFontSize,
      color: q.dataInvalidMessageColor || F.baseChartMessageColor
    }, e = new ea(f, F.dataInvalidMessage, b), s = m.hasNativeMessage = !0, b.__state.dataReady = !1, p || d.raiseEvent("dataInvalid", {error: n.error}, m.fcObj, void 0, function () {
      k.raiseEvent("dataxmlinvalid", {}, b, [b.id])
    })) : (p || k.raiseEvent("dataloaded", {}, b, [b.id]), e = O.init(f, I, b, t), O.inited = !0, m.previousDrawCount = m.drawCount, m.drawCount += 1, 0 === e.series.length ? (b._chartMessageImageStyle =
    {
      imageHAlign: c(q.dataEmptyMessageImageHAlign, F.baseChartMessageImageHAlign).toLowerCase(),
      imageVAlign: c(q.dataEmptyMessageImageVAlign, F.baseChartMessageImageVAlign).toLowerCase(),
      imageAlpha: a(q.dataEmptyMessageImageAlpha, F.baseChartMessageImageAlpha),
      imageScale: a(q.dataEmptyMessageImageScale, F.baseChartMessageImageScale)
    }, b._chartMessageStyle = {
      fontFamily: q.dataEmptyMessageFont || F.baseChartMessageFont,
      fontSize: q.dataEmptyMessageFontSize || F.baseChartMessageFontSize,
      color: q.dataEmptyMessageColor || F.baseChartMessageColor
    },
      e = new ea(f, F.dataEmptyMessage, b), s = m.hasNativeMessage = !0, b.__state.dataReady = !1, p || k.raiseEvent("nodatatodisplay", {}, b, [b.id])) : (b.__state.dataReady = !0, s = m.hasNativeMessage = !1, delete m.message)));
    e || (b._chartMessageImageStyle = {
      imageHAlign: F.baseChartMessageImageHAlign,
      imageVAlign: F.baseChartMessageImageVAlign,
      imageAlpha: F.baseChartMessageImageAlpha,
      imageScale: F.baseChartMessageImageScale
    }, b._chartMessageStyle = {
      fontFamily: F.baseChartMessageFont,
      fontSize: F.baseChartMessageFontSize,
      color: F.baseChartMessageColor
    },
      e = new ea(f, "Error rendering chart {0x01}", b), s = m.hasNativeMessage = !0);
    s && !O.inited && O.init(f, I, b, t);
    e.chart = e.chart || {};
    e.credits = e.credits || {};
    e.credits.enabled = H && !0 === H.creditLabel ? !0 : !1;
    !1 === l && (e.chart.animation = !1, e.plotOptions || (e.plotOptions = {}), e.plotOptions.series || (e.plotOptions.series = {}), e.plotOptions.series.animation = !1);
    f.style && (e.chart.containerBackgroundColor = k.getContainerBackgroundColor(b));
    return O.draw(e, t)
  };
  pa("base", {
    useScaleRecursively: !0,
    tooltipConstraint: "chart",
    rendererId: "root",
    canvasPaddingModifiers: ["anchor", "anchorlabel"],
    drawAnnotations: !0,
    draw: function (a, b) {
      var c = this.renderer;
      c || (c = this.renderer = new pa("renderer." + this.rendererId));
      this.updateDefaultAnnotations();
      return c.init(this, a, b)
    },
    init: function (f, w, v) {
      var h = this.chartInstance || v, e = h.jsVars;
      v = e._reflowData || (e._reflowData = {});
      var l = e._reflowClean, m = h.options, n = m.args, I, H;
      /^\s*absolute\s*$/i.test(d.core.options.SVGDefinitionURL) && (B._url = (B._g && B._g.win || t).location.href.replace(/#.*?$/, b));
      this.dataObj = w = g({},
        w);
      H = w.chart = w.chart || w.graph || w.map || {};
      delete w.graph;
      delete w.map;
      v && !this.stateless && (I = v.hcJSON, delete v.hcJSON, g(this, v, !0), this.preReflowAdjustments && this.preReflowAdjustments.call(this), v.hcJSON = I);
      this.containerElement = f;
      this.config = {};
      this.smartLabel = e.smartLabel;
      this.smartLabel.useEllipsesOnOverflow(a(H.useellipseswhenoverflow, H.useellipsewhenoverflow, 1));
      this.colorManager = new k.colorManager(w, this);
      this.linkClickFN = O(w, h);
      this.numberFormatter = new p(w.chart, this);
      if (!this.standaloneInit)return h._chartMessageImageStyle =
      {
        imageHAlign: c(n.typeNotSupportedMessageImageHAlign, m.baseChartMessageImageHAlign).toLowerCase(),
        imageVAlign: c(n.typeNotSupportedMessageImageVAlign, m.baseChartMessageImageVAlign).toLowerCase(),
        imageAlpha: a(n.typeNotSupportedMessageImageAlpha, m.baseChartMessageImageAlpha),
        imageScale: a(n.typeNotSupportedMessageImageScale, m.baseChartMessageImageScale)
      }, h._chartMessageStyle = {
        fontFamily: n.typeNotSupportedMessageFont || m.baseChartMessageFont,
        fontSize: n.typeNotSupportedMessageFontSize || m.baseChartMessageFontSize,
        color: n.typeNotSupportedMessageColor || m.baseChartMessageColor
      }, new k.createDialog(f, m.typeNotSupportedMessage, h);
      f = this.chart(f.offsetWidth || parseFloat(f.style.width), f.offsetHeight || parseFloat(f.style.height), h);
      v && !this.stateless && (v.hcJSON && g(f, v.hcJSON, !0), this.postReflowAdjustments && this.postReflowAdjustments.call(this), l && this.cleanedData && (this.cleanedData(this, l), this.cleanedData(v, l)));
      return f
    },
    postSpaceManager: function () {
      var b = this.hcJSON, c = b._FCconf, d = b.chart, f = d.marginLeft, e = d.spacingLeft,
        g = d.spacingRight, h = c.width - f - d.marginRight, l = b.title, b = b.subtitle, p = c.width, k = l.align, c = l.x, m = l.horizontalPadding, n = l.alignWithCanvas, I = (ga(f) || 0) + a(h, p) / 2, f = this.snapLiterals || (this.snapLiterals = {}), h = l._captionWidth, H = b._subCaptionWidth, q = l._lineHeight, O = b._lineHeight, s = l.text;
      if (void 0 === c) {
        switch (k) {
          case na:
            c = n ? p - d.marginRight - m : p - m;
            break;
          case ua:
            c = n ? d.marginLeft + m : m;
            break;
          default:
            c = n ? I : e + .5 * (p - e - g) || p / 2
        }
        l.align === ua ? (g = e = 0, l.align = "start") : l.align === na ? (e = h, g = H, l.align = "end") : (e = h / 2, g = H / 2, l.align =
          "middle");
        l.x = c;
        l.y = l.y || d.spacingTop || 0;
        b.y = s ? l.y + q + 2 : l.y || d.spacingTop || 0;
        f.captionstartx = c - e - 2;
        f.captionwidth = h + 4;
        f.captionendx = f.captionstartx + f.captionwidth;
        f.captionstarty = l.y || 0;
        f.captionheight = q + 2;
        f.captionendy = f.captionstarty + f.captionheight;
        f.subcaptionstartx = c - g - 2;
        f.subcaptionwidth = H + 4;
        f.subcaptionendx = f.subcaptionstartx + f.subcaptionwidth;
        f.subcaptionstarty = b.y || 0;
        f.subcaptionheight = O + 2;
        f.subcaptionendy = f.subcaptionstarty + f.subcaptionheight
      }
    },
    chart: function (r, w) {
      var v = this.name, D = this.dataObj,
        e = D.chart, p = this.colorManager, k, m, n, H, O, u, A, F = this.defaultSeriesType, M, ca, z, ea, ma, B, E, la, V, G, K, ia, Q, P, Ja, Y, wa, L, Oa, hb, ab, S, R, Bb, Eb, oc, Yb, X, Pa, aa, Cb, mb, ub, Gb, sb, ba, Vb, Kb, ga, $, pa, va, Ka, qa, xa, Aa, Za, Ea, Ya, Ha, tb, bb, jb, Va, eb, ob, Ob, Sb, sc, mc, uc, lb, qb, yc, pb, Ib, Pb, Ma, zc, Ac, Tb, gc, ec, hd, Wc, Zb, ta, Xb, Bc, Na, Fb, kb, yb, zb, $b, id, rb, fc, jc, qd, gb, kc, wb, Ua, Hb, Rc, Xc, jd, lc, Ab, Jb, Ub, Sc;
      k = Ga(D, r, w, this);
      V = k.chart;
      la = k.xAxis;
      M = k[f];
      this.snapLiterals || (this.snapLiterals = {});
      K = this.snapLiterals;
      K.chartstartx = 0;
      K.chartstarty = 0;
      K.chartwidth = r;
      K.chartheight = w;
      K.chartendx = r;
      K.chartendy = w;
      K.chartcenterx = r / 2;
      K.chartcentery = w / 2;
      K.chartbottommargin = V.spacingBottom;
      K.chartleftmargin = V.spacingLeft;
      K.chartrightmargin = V.spacingRight;
      K.charttopmargin = V.spacingTop;
      this.updateSnapPoints && this.updateSnapPoints();
      this.postHCJSONCreation && this.postHCJSONCreation.call(this, k);
      d.raiseEvent("internal.postlogic", this, this.chartInstance);
      k.labels.smartLabel = u = M.smartLabel = this.smartLabel;
      M.width = r;
      M.height = w;
      z = k.plotOptions;
      M.isDual = this.isDual;
      M.numberFormatter = this.numberFormatter;
      M.axisGridManager = new I(F, e);
      M.tooltext = e.plottooltext;
      M.trendLineToolText = e.trendlinetooltext;
      V.is3D = m = M.is3d = /3d$/.test(F);
      V.isBar = ca = M.isBar = this.isBar;
      A = /^pie/.test(F);
      E = 1 == e.useroundedges;
      B = m ? Ia.chart3D : Ia.chart2D;
      V.events.click = k.plotOptions.series.point.events.click = this.linkClickFN;
      V.defaultSeriesType = F;
      Y = 0 < e.palette && 6 > e.palette ? e.palette : a(this.paletteIndex, 1);
      --Y;
      V.paletteIndex = Y;
      V.usePerPointLabelColor = e.colorlabelsfromplot == db;
      V.syncLabelWithAnchor =
        a(e.synclabelwithanchoronhover, 1);
      V.minimizeTendency = a(e.minimizetendency, e.minimisetendency, 0);
      V.useRoundEdges = E && !m && !this.distributedColumns && "pie" !== this.defaultSeriesType;
      void 0 !== c(e.clickurl) && (V.link = e.clickurl, V.style.cursor = "pointer", k.plotOptions.series.point.events.click = function () {
        V.events.click.call({link: e.clickurl})
      });
      wa = c(e.basefont, "Verdana,sans");
      L = J(e.basefontsize, 10);
      Oa = c(e.basefontcolor, p.getColor(B.baseFontColor));
      hb = c(e.outcnvbasefont, wa);
      ab = J(e.outcnvbasefontsize, L);
      S = ab +
      ra;
      R = c(e.outcnvbasefontcolor, Oa).replace(/^#?([a-f0-9]+)/ig, "#$1");
      oc = L;
      L += ra;
      Oa = Oa.replace(/^#?([a-f0-9]+)/ig, "#$1");
      Sc = (Sc = q(e.trendvaluebordercolor, b)) ? fa(Sc, a(e.trendvalueborderalpha, e.trendvaluealpha, 100)) : b;
      M.trendStyle = M.outCanvasStyle = {
        fontFamily: c(e.trendvaluefont, hb),
        color: c(e.trendvaluefontcolor, R),
        fontSize: J(e.trendvaluefontsize, ab) + ra,
        fontWeight: a(e.trendvaluefontbold) ? "bold" : "normal",
        fontStyle: a(e.trendvaluefontitalic) ? "italic" : "normal",
        border: Sc || e.trendvaluebgcolor ? a(e.trendvalueborderthickness,
          1) + "px solid" : "",
        borderColor: Sc,
        borderThickness: a(e.trendvalueborderthickness, 1),
        borderPadding: a(e.trendvalueborderpadding, 2),
        borderRadius: a(e.trendvalueborderradius, 0),
        backgroundColor: e.trendvaluebgcolor ? fa(e.trendvaluebgcolor, a(e.trendvaluebgalpha, e.trendvaluealpha, 100)) : b,
        borderDash: a(e.trendvalueborderdashed, 0) ? s(a(e.trendvalueborderdashlen, 4), a(e.trendvalueborderdashgap, 2), a(e.trendvalueborderthickness, 1)) : "none"
      };
      Bb = h(M.trendStyle);
      M.inCanvasStyle = {fontFamily: wa, fontSize: L, color: Oa};
      Eb = h(M.inCanvasStyle);
      M.divlineStyle = {fontFamily: wa, fontSize: L, color: Oa, lineHeight: Eb};
      la.labels.style = {
        fontFamily: c(e.labelfont, hb),
        fontSize: a(e.labelfontsize, ab) + ra,
        color: c(e.labelfontcolor, R)
      };
      la.labels.style.lineHeight = h(la.labels.style);
      la.steppedLabels.style = {fontFamily: hb, fontSize: S, lineHeight: Bb, color: R, visibility: "hidden"};
      k.yAxis[0].labels.style = {fontFamily: hb, fontSize: S, lineHeight: Bb, color: R};
      k.yAxis[1].labels.style = {fontFamily: hb, fontSize: S, lineHeight: Bb, color: R};
      X = c(e.legenditemfont, hb);
      Pa = J(e.legenditemfontsize,
        ab);
      aa = c(e.legenditemfontcolor, R).replace(/^#?([a-f0-9]+)/ig, "#$1");
      Cb = La[a(e.legenditemfontbold, 0)] || "";
      Yb = J(e.legendcaptionfontsize, ab) + ra;
      Pa += ra;
      k.legend.itemStyle = {fontFamily: X, fontSize: Pa, color: aa, fontWeight: Cb};
      h(k.legend.itemStyle);
      k.legend.itemHiddenStyle = {
        fontFamily: X,
        fontSize: Pa,
        color: c(e.legenditemhiddencolor, "cccccc").replace(/^#?([a-f0-9]+)/ig, "#$1"),
        fontWeight: Cb
      };
      h(k.legend.itemHiddenStyle);
      k.legend.itemHoverStyle = {color: c(e.legenditemhoverfontcolor, aa).replace(/^#?([a-f0-9]+)/ig, "#$1")};
      k.legend.title.style = {
        fontFamily: c(e.legendcaptionfont, X),
        fontSize: Yb,
        color: c(e.legendcaptionfontcolor, R).replace(/^#?([a-f0-9]+)/ig, "#$1"),
        fontWeight: La[a(e.legendcaptionfontbold, 1)] || ""
      };
      h(k.legend.title.style);
      k.legend.title.align = Rb[e.legendcaptionalignment && e.legendcaptionalignment.toLowerCase() || ya] || Rb.center;
      G = (G = q(e.valuebordercolor, b)) ? fa(G, a(e.valueborderalpha, e.valuealpha, 100)) : b;
      k.plotOptions.series.dataLabels.style = {
        fontFamily: c(e.valuefont, wa),
        fontSize: c(e.valuefontsize, parseInt(L,
          10)) + ra,
        lineHeight: Eb,
        color: fa(c(e.valuefontcolor, Oa), a(e.valuefontalpha, e.valuealpha, 100)),
        fontWeight: a(e.valuefontbold) ? "bold" : "normal",
        fontStyle: a(e.valuefontitalic) ? "italic" : "normal",
        border: G || e.valuebgcolor ? a(e.valueborderthickness, 1) + "px solid" : "",
        borderColor: G,
        borderThickness: a(e.valueborderthickness, 1),
        borderPadding: a(e.valueborderpadding, 2),
        borderRadius: a(e.valueborderradius, 0),
        backgroundColor: e.valuebgcolor ? fa(e.valuebgcolor, a(e.valuebgalpha, e.valuealpha, 100)) : b,
        borderDash: a(e.valueborderdashed,
          0) ? s(a(e.valueborderdashlen, 4), a(e.valueborderdashgap, 2), a(e.valueborderthickness, 1)) : "none"
      };
      h(k.plotOptions.series.dataLabels.style);
      k.plotOptions.series.dataLabels.color = k.plotOptions.series.dataLabels.style.color;
      k.tooltip.style = {fontFamily: wa, fontSize: L, lineHeight: Eb, color: Oa};
      k.title.style = {
        fontFamily: c(e.captionfont, hb),
        color: c(e.captionfontcolor, R).replace(/^#?([a-f0-9]+)/ig, "#$1"),
        fontSize: a(e.captionfontsize, ab + 3) + ra,
        fontWeight: 0 === a(e.captionfontbold) ? "normal" : "bold"
      };
      k.title.align = c(e.captionalignment,
        ya);
      k.title.isOnTop = a(e.captionontop, 1);
      k.title.alignWithCanvas = a(e.aligncaptionwithcanvas, this.alignCaptionWithCanvas, 1);
      k.title.horizontalPadding = a(e.captionhorizontalpadding, k.title.alignWithCanvas ? 0 : 15);
      h(k.title.style);
      k.subtitle.style = {
        fontFamily: c(e.subcaptionfont, e.captionfont, hb),
        color: c(e.subcaptionfontcolor, e.captionfontcolor, R).replace(/^#?([a-f0-9]+)/ig, "#$1"),
        fontSize: a(e.subcaptionfontsize, a(Z(a(e.captionfontsize) - 3, -1), ab) + a(this.subTitleFontSizeExtender, 1)) + ra,
        fontWeight: 0 === a(e.subcaptionfontbold,
          this.subTitleFontWeight, e.captionfontbold) ? "normal" : "bold"
      };
      k.subtitle.align = k.title.align;
      k.subtitle.isOnTop = k.title.isOnTop;
      k.subtitle.alignWithCanvas = k.title.alignWithCanvas;
      k.subtitle.horizontalPadding = k.title.horizontalPadding;
      h(k.subtitle.style);
      G = (G = q(e.xaxisnamebordercolor, b)) ? fa(G, a(e.xaxisnameborderalpha, e.xaxisnamealpha, 100)) : b;
      la.title.style = {
        fontFamily: c(e.xaxisnamefont, hb),
        fontSize: c(e.xaxisnamefontsize, parseInt(S, 10)) + ra,
        color: fa(c(e.xaxisnamefontcolor, R), a(e.xaxisnamefontalpha, e.xaxisnamealpha,
          100)),
        fontWeight: a(e.xaxisnamefontbold, 1) ? "bold" : "normal",
        fontStyle: a(e.xaxisnamefontitalic) ? "italic" : "normal",
        border: G || e.xaxisnamebgcolor ? a(e.xaxisnameborderthickness, 1) + "px solid" : void 0,
        borderColor: G,
        borderThickness: a(e.xaxisnameborderthickness, 1),
        borderPadding: a(e.xaxisnameborderpadding, 2),
        borderRadius: a(e.xaxisnameborderradius, 0),
        backgroundColor: e.xaxisnamebgcolor ? fa(e.xaxisnamebgcolor, a(e.xaxisnamebgalpha, e.xaxisnamealpha, 100)) : b,
        borderDash: a(e.xaxisnameborderdashed, 0) ? s(a(e.xaxisnameborderdashlen,
          4), a(e.xaxisnameborderdashgap, 2), a(e.xaxisnameborderthickness, 1)) : "none"
      };
      h(la.title.style);
      G = (G = c(e.pyaxisnamebordercolor, e.yaxisnamebordercolor, b)) ? fa(G, a(e.pyaxisnameborderalpha, e.yaxisnameborderalpha, e.pyaxisnamealpha, e.yaxisnamealpha, 100)) : b;
      k.yAxis[0].title.style = {
        fontFamily: c(e.pyaxisnamefont, e.yaxisnamefont, hb),
        fontSize: c(e.pyaxisnamefontsize, e.yaxisnamefontsize, parseInt(S, 10)) + ra,
        color: fa(c(e.pyaxisnamefontcolor, e.yaxisnamefontcolor, R), a(e.pyaxisnamefontalpha, e.yaxisnamefontalpha, e.pyaxisnamealpha,
          e.yaxisnamealpha, 100)),
        fontWeight: a(e.pyaxisnamefontbold, e.yaxisnamefontbold, 1) ? "bold" : "normal",
        fontStyle: a(e.pyaxisnamefontitalic, e.yaxisnamefontitalic) ? "italic" : "normal",
        border: G || e.pyaxisnamebgcolor || e.yaxisnamebgcolor ? a(e.pyaxisnameborderthickness, e.yaxisnameborderthickness, 1) + "px solid" : void 0,
        borderColor: G,
        borderThickness: a(e.pyaxisnameborderthickness, e.yaxisnameborderthickness, 1),
        borderPadding: a(e.pyaxisnameborderpadding, e.yaxisnameborderpadding, 2),
        borderRadius: a(e.pyaxisnameborderradius, e.yaxisnameborderradius,
          0),
        backgroundColor: e.pyaxisnamebgcolor || e.yaxisnamebgcolor ? fa(c(e.pyaxisnamebgcolor, e.yaxisnamebgcolor), a(e.pyaxisnamebgalpha, e.yaxisnamebgalpha, e.pyaxisnamealpha, e.yaxisnamealpha, 100)) : b,
        borderDash: a(e.pyaxisnameborderdashed, e.yaxisnameborderdashed, 0) ? s(a(e.pyaxisnameborderdashlen, e.yaxisnameborderdashlen, 4), a(e.pyaxisnameborderdashgap, e.yaxisnameborderdashgap, 2), a(e.pyaxisnameborderthickness, e.yaxisnameborderthickness, 1)) : "none"
      };
      h(k.yAxis[0].title.style);
      k.yAxis[1].title.style = {
        fontFamily: hb, color: R,
        fontSize: S, lineHeight: void 0, fontWeight: "bold"
      };
      G = (G = c(e.syaxisnamebordercolor, e.yaxisnamebordercolor, b)) ? fa(G, a(e.syaxisnameborderalpha, e.yaxisnameborderalpha, e.syaxisnamealpha, e.yaxisnamealpha, 100)) : b;
      k.yAxis[1].title.style = {
        fontFamily: c(e.syaxisnamefont, e.yaxisnamefont, hb),
        fontSize: c(e.syaxisnamefontsize, e.yaxisnamefontsize, parseInt(S, 10)) + ra,
        color: fa(c(e.syaxisnamefontcolor, e.yaxisnamefontcolor, R), a(e.syaxisnamefontalpha, e.yaxisnamefontalpha, e.syaxisnamealpha, e.yaxisnamealpha, 100)),
        fontWeight: a(e.syaxisnamefontbold,
          e.yaxisnamefontbold, 1) ? "bold" : "normal",
        fontStyle: a(e.syaxisnamefontitalic, e.yaxisnamefontitalic) ? "italic" : "normal",
        border: G || e.syaxisnamebgcolor || e.yaxisnamebgcolor ? a(e.syaxisnameborderthickness, e.yaxisnameborderthickness, 1) + "px solid" : void 0,
        borderColor: G,
        borderThickness: a(e.syaxisnameborderthickness, e.yaxisnameborderthickness, 1),
        borderPadding: a(e.syaxisnameborderpadding, e.yaxisnameborderpadding, 2),
        borderRadius: a(e.syaxisnameborderradius, e.yaxisnameborderradius, 0),
        backgroundColor: e.syaxisnamebgcolor ||
        e.yaxisnamebgcolor ? fa(c(e.syaxisnamebgcolor, e.yaxisnamebgcolor), a(e.syaxisnamebgalpha, e.yaxisnamebgalpha, e.syaxisnamealpha, e.yaxisnamealpha, 100)) : b,
        borderDash: a(e.syaxisnameborderdashed, e.yaxisnameborderdashed, 0) ? s(a(e.syaxisnameborderdashlen, e.yaxisnameborderdashlen, 4), a(e.syaxisnameborderdashgap, e.yaxisnameborderdashgap, 2), a(e.syaxisnameborderthickness, e.yaxisnameborderthickness, 1)) : "none"
      };
      h(k.yAxis[1].title.style);
      V.overlapColumns = a(e[ca && "overlapbars" || "overlapcolumns"], m ? 0 : 1);
      k.orphanStyles =
      {
        defaultStyle: {style: g({}, M.inCanvasStyle)},
        connectorlabels: {style: g({}, k.plotOptions.series.dataLabels)},
        vyaxisname: {style: g({}, k.yAxis[0].title.style)}
      };
      k.plotOptions.series.dataLabels.tlLabelStyle = {
        fontFamily: l(e.tlfont, wa),
        color: T(l(e.tlfontcolor, Oa)),
        fontSize: J(e.tlfontsize, oc) + "px"
      };
      h(k.plotOptions.series.dataLabels.tlLabelStyle);
      k.plotOptions.series.dataLabels.trLabelStyle = {
        fontFamily: l(e.trfont, wa),
        color: T(l(e.trfontcolor, Oa)),
        fontSize: J(e.trfontsize, oc) + "px"
      };
      h(k.plotOptions.series.dataLabels.trLabelStyle);
      k.plotOptions.series.dataLabels.blLabelStyle = {
        fontFamily: l(e.blfont, wa),
        color: T(l(e.blfontcolor, Oa)),
        fontSize: J(e.blfontsize, oc) + "px"
      };
      h(k.plotOptions.series.dataLabels.blLabelStyle);
      k.plotOptions.series.dataLabels.brLabelStyle = {
        fontFamily: l(e.brfont, wa),
        color: T(l(e.brfontcolor, Oa)),
        fontSize: J(e.brfontsize, oc) + "px"
      };
      h(k.plotOptions.series.dataLabels.brLabelStyle);
      this.parseStyles(k);
      delete k.xAxis.labels.style.backgroundColor;
      delete k.xAxis.labels.style.borderColor;
      delete k.yAxis[0].labels.style.backgroundColor;
      delete k.yAxis[0].labels.style.borderColor;
      delete k.yAxis[1].labels.style.backgroundColor;
      delete k.yAxis[1].labels.style.borderColor;
      M.showTooltip = a(e.showtooltip, this.showtooltip, 1);
      M.tooltipSepChar = c(e.tooltipsepchar, this.tooltipsepchar, Qb);
      M.showValues = a(e.showvalues, this.showValues, 1);
      M.seriesNameInToolTip = a(e.seriesnameintooltip, 1);
      M.showVLines = a(e.showvlines, 1);
      M.showVLinesOnTop = a(e.showvlinesontop, 0);
      M.showVLineLabels = a(e.showvlinelabels, this.showVLineLabels, 1);
      M.showVLineLabelBorder = a(e.showvlinelabelborder,
        1);
      M.rotateVLineLabels = a(e.rotatevlinelabels, 0);
      M.vLineColor = c(e.vlinecolor, "333333");
      M.vLineLabelColor = c(e.vlinelabelcolor);
      M.vLineThickness = c(e.vlinethickness, 1);
      M.vLineAlpha = a(e.vlinealpha, 80);
      M.vLineLabelBgColor = c(e.vlinelabelbgcolor, "ffffff");
      M.vLineLabelBgAlpha = a(e.vlinelabelbgalpha, m ? 50 : 100);
      M.trendlineColor = c(e.trendlinecolor, "333333");
      M.trendlineThickness = c(e.trendlinethickness, 1);
      M.trendlineAlpha = a(e.trendlinealpha);
      M.showTrendlinesOnTop = c(e.showtrendlinesontop, 0);
      M.trendlineValuesOnOpp =
        c(e.trendlinevaluesonopp, e.trendlinevaluesonright, 0);
      M.trendlinesAreDashed = a(e.trendlinesaredashed, 0);
      M.trendlinesDashLen = a(e.trendlinedashlen, 5);
      M.trendlinesDashGap = a(e.trendlinedashgap, 2);
      M.showTrendlines = a(e.showtrendlines, 1);
      M.showTrendlineLabels = a(e.showtrendlinelabels, this.showTrendlineLabels, 1);
      M.flatScrollBars = a(e.flatscrollbars, 0);
      M.scrollBar3DLighting = a(e.scrollbar3dlighting, 1);
      V.anchorTrackingRadius = a(e.anchortrackingradius, oa ? ka : ha);
      k.plotOptions.series.connectNullData = a(e.connectnulldata,
        0);
      V.backgroundColor = {
        FCcolor: {
          color: c(e.bgcolor, p.getColor(B.bgColor)),
          alpha: c(e.bgalpha, p.getColor(B.bgAlpha)),
          angle: c(e.bgangle, p.getColor(B.bgAngle)),
          ratio: c(e.bgratio, p.getColor(B.bgRatio))
        }
      };
      V.rotateValues = a(e.rotatevalues, 0);
      V.placeValuesInside = a(e.placevaluesinside, 0);
      V.valuePosition = c(e.valueposition, "auto");
      V.valuePadding = a(e.valuepadding, 2);
      V.managePlotOverflow = a(e.manageplotoverflow, 1);
      V.borderColor = fa(c(e.bordercolor, m ? "#666666" : p.getColor("borderColor")), c(e.borderalpha, m ? "100" : p.getColor("borderAlpha")));
      ea = a(e.showborder, m ? 0 : 1);
      V.borderWidth = ea ? a(e.borderthickness, 1) : 0;
      V.borderRadius = a(e.borderradius, 0);
      V.borderDashStyle = a(e.borderdashed, 0) ? s(a(e.borderdashlen, 4), a(e.borderdashgap, 2), V.borderWidth) : "none";
      V.plotBorderColor = fa(c(e.canvasbordercolor, p.getColor("canvasBorderColor")), c(e.canvasborderalpha, p.getColor("canvasBorderAlpha")));
      "0" !== e.showcanvasborder && (ma = Boolean(c(e.canvasborderthickness, E ? 0 : 1)), "1" !== e.showaxislines && "1" !== e.showxaxisline && "1" !== e.showyaxisline && "1" !== e.showsyaxisline ||
      "1" === e.showcanvasborder || (ma = 0));
      V.plotBorderWidth = m || !ma ? 0 : a(e.canvasborderthickness, this.canvasborderthickness, V.useRoundEdges ? 1 : 2);
      V.bgSWF = c(e.bgimage, e.bgswf);
      V.bgSWFAlpha = a(e.bgimagealpha, e.bgswfalpha, 100);
      mb = c(e.bgimagedisplaymode, "none").toLowerCase();
      ub = l(e.bgimagevalign, b).toLowerCase();
      Gb = l(e.bgimagehalign, b).toLowerCase();
      "tile" == mb || "fill" == mb || "fit" == mb ? (ub != Fa && "middle" != ub && ub != Da && (ub = "middle"), Gb != ua && "middle" != Gb && Gb != na && (Gb = "middle")) : (ub != Fa && "middle" != ub && ub != Da && (ub = Fa), Gb !=
      ua && "middle" != Gb && Gb != na && (Gb = ua));
      V.bgImageDisplayMode = mb;
      V.bgImageVAlign = ub;
      V.bgImageHAlign = Gb;
      V.bgImageScale = a(e.bgimagescale, 100);
      V.logoURL = l(e.logourl);
      V.logoPosition = c(e.logoposition, "tl").toLowerCase();
      V.logoAlpha = a(e.logoalpha, 100);
      V.logoLink = l(e.logolink);
      V.logoScale = a(e.logoscale, 100);
      V.logoLeftMargin = a(e.logoleftmargin, 0);
      V.logoTopMargin = a(e.logotopmargin, 0);
      sb = V.toolbar = {button: {}};
      ba = sb.button;
      ba.scale = a(e.toolbarbuttonscale, 1.15);
      ba.width = a(e.toolbarbuttonwidth, 15);
      ba.height = a(e.toolbarbuttonheight,
        15);
      ba.radius = a(e.toolbarbuttonradius, 2);
      ba.spacing = a(e.toolbarbuttonspacing, 5);
      ba.fill = fa(c(e.toolbarbuttoncolor, "ffffff"));
      ba.labelFill = fa(c(e.toolbarlabelcolor, "cccccc"));
      ba.symbolFill = fa(c(e.toolbarsymbolcolor, "ffffff"));
      ba.hoverFill = fa(c(e.toolbarbuttonhovercolor, "ffffff"));
      ba.stroke = fa(c(e.toolbarbuttonbordercolor, "bbbbbb"));
      ba.symbolStroke = fa(c(e.toolbarsymbolbordercolor, "9a9a9a"));
      ba.strokeWidth = a(e.toolbarbuttonborderthickness, 1);
      ba.symbolStrokeWidth = a(e.toolbarsymbolborderthickness, 1);
      Vb =
        ba.symbolPadding = a(e.toolbarsymbolpadding, 5);
      ba.symbolHPadding = a(e.toolbarsymbolhpadding, Vb);
      ba.symbolVPadding = a(e.toolbarsymbolvpadding, Vb);
      Kb = sb.position = c(e.toolbarposition, "tr").toLowerCase();
      switch (Kb) {
        case "tr":
        case "rt":
        case "top right":
        case "right top":
          Kb = "tr";
          break;
        case "br":
        case "rb":
        case "bottom right":
        case "right bottom":
          Kb = "br";
          break;
        case "tl":
        case "lt":
        case "top left":
        case "left top":
          Kb = "tl";
          break;
        case "bl":
        case "lb":
        case "bottom left":
        case "left bottom":
          Kb = "bl";
          break;
        default:
          Kb = "tr"
      }
      ga =
        sb.hAlign = "left" === (b + e.toolbarhalign).toLowerCase() ? "l" : Kb.charAt(1);
      $ = sb.vAlign = "bottom" === (b + e.toolbarvalign).toLowerCase() ? "b" : Kb.charAt(0);
      sb.hDirection = a(e.toolbarhdirection, "r" === ga ? -1 : 1);
      sb.vDirection = a(e.toolbarvdirection, "b" === $ ? -1 : 1);
      sb.vMargin = a(e.toolbarvmargin, 6);
      sb.hMargin = a(e.toolbarhmargin, 10);
      sb.x = a(e.toolbarx, "l" === ga ? 0 : r);
      sb.y = a(e.toolbary, "t" === $ ? 0 : w);
      pa = c(e.divlinecolor, p.getColor(B.divLineColor));
      va = c(e.divlinealpha, m ? p.getColor("divLineAlpha3D") : p.getColor("divLineAlpha"));
      Ka = a(e.divlinethickness, 1);
      qa = Boolean(a(e.divlinedashed, e.divlineisdashed, this.divLineIsDashed, 0));
      xa = a(e.divlinedashlen, 4);
      Aa = a(e.divlinedashgap, 2);
      k.yAxis[0].gridLineColor = fa(pa, va);
      k.yAxis[0].gridLineWidth = Ka;
      k.yAxis[0].gridLineDashStyle = qa ? s(xa, Aa, Ka) : "none";
      k.yAxis[0].alternateGridColor = ca ? fa(c(e.alternatevgridcolor, p.getColor("altVGridColor")), 1 === a(e.showalternatevgridcolor, 1) ? c(e.alternatevgridalpha, p.getColor("altVGridAlpha")) : Ca) : fa(c(e.alternatehgridcolor, p.getColor("altHGridColor")), "0" ===
      e.showalternatehgridcolor ? 0 : c(e.alternatehgridalpha, p.getColor("altHGridAlpha")));
      mc = a(e.vdivlinethickness, 1);
      uc = Boolean(a(e.vdivlinedashed, e.vdivlineisdashed, 0));
      lb = a(e.vdivlinedashlen, 4);
      qb = a(e.vdivlinedashgap, 2);
      la.gridLineColor = fa(c(e.vdivlinecolor, p.getColor(B.divLineColor)), c(e.vdivlinealpha, p.getColor("divLineAlpha")));
      la.gridLineWidth = mc;
      la.gridLineDashStyle = uc ? s(lb, qb, mc) : "none";
      la.alternateGridColor = fa(c(e.alternatevgridcolor, p.getColor("altVGridColor")), "1" === e.showalternatehgridcolor ?
        c(e.alternatevgridalpha, p.getColor("altVGridAlpha")) : 0);
      Ea = c(e.canvasbgcolor, p.getColor(B.canvasBgColor));
      Ha = c(e.canvasbgalpha, p.getColor("canvasBgAlpha"));
      c(e.showcanvasbg, db) == Ca && (Ha = "0");
      k.plotOptions.series.shadow = a(e.showshadow, e.showcolumnshadow, this.defaultPlotShadow, p.getColor("showShadow"));
      this.inversed && (k.yAxis[0].reversed = !0, k.yAxis[1].reversed = !0);
      this.isStacked && (this.distributedColumns ? (M.showStackTotal = Boolean(a(e.showsum, 1)), O = a(e.usepercentdistribution, 1), Za = a(e.showpercentvalues,
        0), Ya = a(e.showpercentintooltip, O, 0), M.showXAxisPercentValues = a(e.showxaxispercentvalues, 1)) : (M.showStackTotal = Boolean(a(this.showSum, e.showsum, 0)), O = a(this.stack100percent, e.stack100percent, 0), Za = a(e.showpercentvalues, O, 0), Ya = a(e.showpercentintooltip, Za)), M.showPercentValues = Za, M.showPercentInToolTip = Ya, O ? (M.isValueAbs = !0, z[F].stacking = "percent", M[0].stacking100Percent = !0) : z[F].stacking = "normal");
      this.isDual && ("0" === e.primaryaxisonleft && (k.yAxis[0].opposite = !0, k.yAxis[1].opposite = !1), k.yAxis[0].showAlways = !0, k.yAxis[1].showAlways = !0);
      V.useRoundEdges && (k.plotOptions.series.shadow = a(e.showshadow, e.showcolumnshadow, 1), k.plotOptions.series.borderRadius = 1, k.tooltip.style.borderRadius = "2px", V.plotBorderRadius = 3, ma || (V.plotBorderWidth = 0), V.plotShadow = k.plotOptions.series.shadow ? {
        enabled: !0,
        opacity: Ha / 100
      } : 0);
      1 === a(e.use3dlighting, 1) && (k.legend.lighting3d = !0);
      k.plotOptions.series.userMaxColWidth = ca ? e.maxbarheight : a(e.maxcolwidth, this.maxColWidth);
      k.plotOptions.series.maxColWidth = za(a(k.plotOptions.series.userMaxColWidth,
        50)) || 1;
      k.title.text = C(e.caption);
      k.subtitle.text = C(e.subcaption);
      0 === a(e.showtooltip, this.showtooltip) && (k.tooltip.enabled = !1);
      tb = k.tooltip.style;
      tb.backgroundColor = fa(c(tb.backgroundColor, e.tooltipbgcolor, p.getColor("toolTipBgColor")), c(e.tooltipbgalpha, 100));
      tb.borderColor = fa(c(tb.borderColor, e.tooltipbordercolor, p.getColor("toolTipBorderColor")), c(e.tooltipborderalpha, 100));
      k.tooltip.shadow = a(e.showtooltipshadow, e.showshadow, 1) ? {
        enabled: !0, opacity: Z(a(e.tooltipbgalpha, 100), a(e.tooltipborderalpha,
          100)) / 100
      } : !1;
      k.tooltip.constrain = a(e.constraintooltip, 1);
      tb.borderWidth = a(e.tooltipborderthickness, 1) + "px";
      e.tooltipborderradius && (tb.borderRadius = a(e.tooltipborderradius, 1) + "px");
      tb.padding = a(e.tooltippadding, this.tooltippadding, 3) + "px";
      e.tooltipcolor && (tb.color = T(e.tooltipcolor));
      M.userPlotSpacePercent = k.plotOptions.series.userPlotSpacePercent = e.plotspacepercent;
      bb = a(e.plotspacepercent, 20) % 100;
      M.plotSpacePercent = k.plotOptions.series.groupPadding = bb / 200;
      m && !A ? (V.series2D3Dshift = "mscombi3d" === v ?
        !0 : Boolean(a(e.use3dlineshift, 0)), V.canvasBaseColor3D = c(e.canvasbasecolor, p.getColor("canvasBaseColor3D")), V.canvasBaseDepth = a(e.canvasbasedepth, 10), V.canvasBgDepth = a(e.canvasbgdepth, 3), V.showCanvasBg = Boolean(a(e.showcanvasbg, 1)), V.showCanvasBase = Boolean(a(e.showcanvasbase, 1)), ca ? (V.xDepth = 5, V.yDepth = 5, V.showCanvasBg && (M.marginTopExtraSpace += V.canvasBgDepth), M.marginLeftExtraSpace += V.yDepth + (V.showCanvasBase ? V.canvasBaseDepth : 0), M.marginBottomExtraSpace += 5) : (V.xDepth = 10, V.yDepth = 10, V.showCanvasBg &&
      (M.marginRightExtraSpace += V.canvasBgDepth), M.marginBottomExtraSpace += V.yDepth + (V.showCanvasBase ? V.canvasBaseDepth : 0)), Ea = Ea.split(Ba)[0], Ha = Ha.split(Ba)[0], V.use3DLighting = Boolean(a(e.use3dlighting, 1)), V.plotBackgroundColor = V.use3DLighting ? {
        FCcolor: {
          color: U(Ea, 85) + Ba + da(Ea, 55),
          alpha: Ha + Ba + Ha,
          ratio: Xa,
          angle: xb(r - (V.marginLeft + V.marginRight), w - (V.marginTop + V.marginBottom), 1)
        }
      } : fa(Ea, Ha), V.canvasBgColor = fa(U(Ea, 80), Ha), n = c(e.zeroplanecolor, e.divlinecolor, p.getColor(B.divLineColor)), H = c(e.zeroplanealpha,
        e.divlinealpha, p.getColor("divLineAlpha")), V.zeroPlaneColor = fa(n, H), V.zeroPlaneBorderColor = fa(c(e.zeroplanebordercolor, n), a(e.zeroplaneshowborder, 1) ? H : 0), V.zeroPlaneShowBorder = a(e.zeroplaneshowborder, 1)) : (V.is3D = !1, V.plotBackgroundColor = {
        FCcolor: {
          color: Ea,
          alpha: Ha,
          angle: c(e.canvasbgangle, p.getColor("canvasBgAngle")),
          ratio: c(e.canvasbgratio, p.getColor("canvasBgRatio"))
        }
      });
      this.parseExportOptions(k);
      this.parseHoverEffectOptions(V);
      this.preSeriesAddition && this.preSeriesAddition(k, D, r, w);
      this.series &&
      this.series(D, k, v, r, w);
      this.postSeriesAddition(k, D, r, w);
      this.spaceManager(k, D, r, w);
      this.postSpaceManager && this.postSpaceManager(k, D, r, w);
      jb = a(e.drawquadrant, 0);
      M.isXYPlot && jb && (Va = la.min, eb = la.max, ob = k.yAxis[0].min, Ob = k.yAxis[0].max, Sb = a(e.quadrantxval, (Va + eb) / 2), sc = a(e.quadrantyval, (ob + Ob) / 2), sc >= ob && sc <= Ob && Sb >= Va && Sb <= eb && (yc = fa(c(e.quadrantlinecolor, V.plotBorderColor), c(e.quadrantlinealpha, Ta)), pb = a(e.quadrantlinethickness, V.plotBorderWidth), Ib = a(e.quadrantlinedashed, e.quadrantlineisdashed, 0),
        Pb = a(e.quadrantlinedashLen, 4), Ma = a(e.quadrantlinedashgap, 2), zc = l(e.quadrantlabeltl, b), Ac = l(e.quadrantlabeltr, b), Tb = l(e.quadrantlabelbl, b), gc = l(e.quadrantlabelbr, b), ec = a(e.quadrantlabelpadding, 3), hd = Ib ? s(Pb, Ma, pb) : "none", la.plotLines.push({
        color: yc,
        value: Sb,
        width: pb,
        dashStyle: hd,
        zIndex: 3
      }), k.yAxis[0].plotLines.push({
        color: yc,
        value: sc,
        width: pb,
        dashStyle: hd,
        zIndex: 3
      }), Wc = r - V.marginRight - V.marginLeft, Zb = w - V.marginTop - V.marginBottom, kb = M.inCanvasStyle, ta = Wc / (eb - Va) * (Sb - Va), Xb = Wc - ta, Na = Zb / (Ob - ob) * (sc - ob),
        Bc = Zb - Na, ta -= ec, Xb -= ec, Bc -= ec, Na -= ec, yb = ec + ra, zb = Zb - ec + ra, $b = ec + ra, id = Wc - ec + ra, u.setStyle(kb), 0 < Bc && (zc !== b && 0 < ta && (G = q(e.quadrantlabeltlbordercolor, e.quadrantlabelbordercolor, b), Ab = c(e.quadrantlabeltlbgcolor, e.quadrantlabelbgcolor), Jb = a(e.quadrantlabeltlborderthickness, e.quadrantlabelborderthickness, 1), G = G ? fa(G, a(e.quadrantlabeltlborderalpha, e.quadrantlabelborderalpha, e.quadrantlabeltlalpha, e.quadrantlabelalpha, 100)) : b, Ub = {
        left: $b,
        top: yb,
        fontSize: c(a(e.quadrantlabeltlfontsize, e.quadrantlabelfontsize),
          parseInt(kb.fontSize, 10)) + ra,
        lineHeight: kb.lineHeight,
        fontFamily: c(e.quadrantlabeltlfont, e.quadrantlabelfont, kb.fontFamily),
        color: fa(c(e.quadrantlabeltlfontcolor, e.quadrantlabelfontcolor, kb.color), a(e.quadrantlabeltlfontalpha, e.quadrantlabelfontalpha, 100)),
        fontWeight: a(e.quadrantlabeltlfontbold, e.quadrantlabelfontbold) ? "bold" : "normal",
        fontStyle: a(e.quadrantlabeltlfontitalic, e.quadrantlabelfontitalic) ? "italic" : "normal",
        border: G || Ab ? Jb + "px solid" : b,
        borderColor: G,
        borderThickness: Jb,
        borderPadding: a(e.quadrantlabeltlborderpadding,
          e.quadrantlabelborderpadding, 2),
        borderRadius: a(e.quadrantlabeltlborderradius, e.quadrantlabelborderradius, 0),
        backgroundColor: Ab ? fa(Ab, a(e.quadrantlabeltlbgalpha, e.quadrantlabelbgalpha, e.quadrantlabeltlalpha, e.quadrantlabelalpha, 100)) : b,
        borderDash: a(e.quadrantlabeltlborderdashed, e.quadrantlabelborderdashed, 0) ? s(a(e.quadrantlabeltlborderdashlen, e.quadrantlabelborderdashlen, 4), a(e.quadrantlabeltlborderdashgap, e.quadrantlabelborderdashgap, 2), Jb) : "none"
      }, h(Ub), u.setStyle(Ub), Fb = u.getSmartText(zc, ta, Bc),
        k.labels.items.push({
          html: Fb.text,
          title: Fb.title,
          zIndex: 3,
          vAlign: Fa,
          style: Ub
        })), Ac !== b && 0 < Xb && (G = q(e.quadrantlabeltrbordercolor, e.quadrantlabelbordercolor, b), Ab = c(e.quadrantlabeltrbgcolor, e.quadrantlabelbgcolor), Jb = a(e.quadrantlabeltrborderthickness, e.quadrantlabelborderthickness, 1), G = G ? fa(G, a(e.quadrantlabeltrborderalpha, e.quadrantlabelborderalpha, e.quadrantlabeltralpha, e.quadrantlabelalpha, 100)) : b, Ub = {
        left: id,
        top: yb,
        fontSize: c(a(e.quadrantlabeltrfontsize, e.quadrantlabelfontsize), parseInt(kb.fontSize,
          10)) + ra,
        lineHeight: kb.lineHeight,
        fontFamily: c(e.quadrantlabeltrfont, e.quadrantlabelfont, kb.fontFamily),
        color: fa(c(e.quadrantlabeltrfontcolor, e.quadrantlabelfontcolor, kb.color), a(e.quadrantlabeltrfontalpha, e.quadrantlabelfontalpha, 100)),
        fontWeight: a(e.quadrantlabeltrfontbold, e.quadrantlabelfontbold) ? "bold" : "normal",
        fontStyle: a(e.quadrantlabeltrfontitalic, e.quadrantlabelfontitalic) ? "italic" : "normal",
        border: G || Ab ? Jb + "px solid" : b,
        borderColor: G,
        borderThickness: Jb,
        borderPadding: a(e.quadrantlabeltrborderpadding,
          e.quadrantlabelborderpadding, 2),
        borderRadius: a(e.quadrantlabeltrborderradius, e.quadrantlabelborderradius, 0),
        backgroundColor: Ab ? fa(Ab, a(e.quadrantlabeltrbgalpha, e.quadrantlabelbgalpha, e.quadrantlabeltralpha, e.quadrantlabelalpha, 100)) : b,
        borderDash: a(e.quadrantlabeltrborderdashed, e.quadrantlabelborderdashed, 0) ? s(a(e.quadrantlabeltrborderdashlen, e.quadrantlabelborderdashlen, 4), a(e.quadrantlabeltrborderdashgap, e.quadrantlabelborderdashgap, 2), Jb) : "none"
      }, h(Ub), u.setStyle(Ub), Fb = u.getSmartText(Ac, ta, Bc),
        k.labels.items.push({
          html: Fb.text,
          textAlign: na,
          title: Fb.title,
          zIndex: 3,
          vAlign: Fa,
          style: Ub
        }))), 0 < Na && (Tb !== b && 0 < ta && (G = q(e.quadrantlabelblbordercolor, e.quadrantlabelbordercolor, b), Ab = c(e.quadrantlabelblbgcolor, e.quadrantlabelbgcolor), Jb = a(e.quadrantlabelblborderthickness, e.quadrantlabelborderthickness, 1), G = G ? fa(G, a(e.quadrantlabelblborderalpha, e.quadrantlabelborderalpha, e.quadrantlabelblalpha, e.quadrantlabelalpha, 100)) : b, Ub = {
        left: $b,
        top: zb,
        fontSize: c(a(e.quadrantlabelblfontsize, e.quadrantlabelfontsize),
          parseInt(kb.fontSize, 10)) + ra,
        lineHeight: kb.lineHeight,
        fontFamily: c(e.quadrantlabelblfont, e.quadrantlabelfont, kb.fontFamily),
        color: fa(c(e.quadrantlabelblfontcolor, e.quadrantlabelfontcolor, kb.color), a(e.quadrantlabelblfontalpha, e.quadrantlabelfontalpha, 100)),
        fontWeight: a(e.quadrantlabelblfontbold, e.quadrantlabelfontbold) ? "bold" : "normal",
        fontStyle: a(e.quadrantlabelblfontitalic, e.quadrantlabelfontitalic) ? "italic" : "normal",
        border: G || Ab ? Jb + "px solid" : b,
        borderColor: G,
        borderThickness: Jb,
        borderPadding: a(e.quadrantlabelblborderpadding,
          e.quadrantlabelborderpadding, 2),
        borderRadius: a(e.quadrantlabelblborderradius, e.quadrantlabelborderradius, 0),
        backgroundColor: Ab ? fa(Ab, a(e.quadrantlabelblbgalpha, e.quadrantlabelbgalpha, e.quadrantlabelblalpha, e.quadrantlabelalpha, 100)) : b,
        borderDash: a(e.quadrantlabelblborderdashed, e.quadrantlabelborderdashed, 0) ? s(a(e.quadrantlabelblborderdashlen, e.quadrantlabelborderdashlen, 4), a(e.quadrantlabelblborderdashgap, e.quadrantlabelborderdashgap, 2), Jb) : "none"
      }, h(Ub), u.setStyle(Ub), Fb = u.getSmartText(Tb, ta, Bc),
        k.labels.items.push({
          html: Fb.text,
          textAlign: ua,
          title: Fb.title,
          zIndex: 3,
          vAlign: Da,
          style: Ub
        })), gc !== b && 0 < Xb && (G = q(e.quadrantlabelbrbordercolor, e.quadrantlabelbordercolor, b), Ab = c(e.quadrantlabelbrbgcolor, e.quadrantlabelbgcolor), Jb = a(e.quadrantlabelbrborderthickness, e.quadrantlabelborderthickness, 1), G = G ? fa(G, a(e.quadrantlabelbrborderalpha, e.quadrantlabelborderalpha, e.quadrantlabelbralpha, e.quadrantlabelalpha, 100)) : b, Ub = {
        left: id,
        top: zb,
        fontSize: c(a(e.quadrantlabelbrfontsize, e.quadrantlabelfontsize), parseInt(kb.fontSize,
          10)) + ra,
        lineHeight: kb.lineHeight,
        fontFamily: c(e.quadrantlabelbrfont, e.quadrantlabelfont, kb.fontFamily),
        color: fa(c(e.quadrantlabelbrfontcolor, e.quadrantlabelfontcolor, kb.color), a(e.quadrantlabelbrfontalpha, e.quadrantlabelfontalpha, 100)),
        fontWeight: a(e.quadrantlabelbrfontbold, e.quadrantlabelfontbold) ? "bold" : "normal",
        fontStyle: a(e.quadrantlabelbrfontitalic, e.quadrantlabelfontitalic) ? "italic" : "normal",
        border: G || Ab ? Jb + "px solid" : b,
        borderColor: G,
        borderThickness: Jb,
        borderPadding: a(e.quadrantlabelbrborderpadding,
          e.quadrantlabelborderpadding, 2),
        borderRadius: a(e.quadrantlabelbrborderradius, e.quadrantlabelborderradius, 0),
        backgroundColor: Ab ? fa(Ab, a(e.quadrantlabelbrbgalpha, e.quadrantlabelbgalpha, e.quadrantlabelbralpha, e.quadrantlabelalpha, 100)) : b,
        borderDash: a(e.quadrantlabelbrborderdashed, e.quadrantlabelborderdashed, 0) ? s(a(e.quadrantlabelbrborderdashlen, e.quadrantlabelborderdashlen, 4), a(e.quadrantlabelbrborderdashgap, e.quadrantlabelborderdashgap, 2), Jb) : "none"
      }, h(Ub), u.setStyle(Ub), Fb = u.getSmartText(gc, ta, Bc),
        k.labels.items.push({html: Fb.text, textAlign: na, vAlign: Da, title: Fb.title, zIndex: 3, style: Ub})))));
      if (this.hasVDivLine && (rb = a(e.showvdivlines, 0), fc = a(e.numvdivlines, 0) + 1, rb && (fc = M.x.catCount - 1), 1 < fc)) {
        gb = la.min;
        kc = M.x.catCount - 1;
        wb = la.max;
        Hb = kc / fc;
        Rc = !0;
        Xc = gb;
        la.scroll && !isNaN(la.scroll.viewPortMax) && (wb = la.scroll.viewPortMax);
        jc = c(e.vdivlinecolor, pa);
        qd = a(e.vdivlinealpha, va);
        mc = a(e.vdivlinethickness, Ka);
        uc = a(e.vdivlinedashed, e.vdivlineisdashed, qa);
        lb = a(e.vdivlinedashlen, xa);
        qb = a(e.vdivlinedashgap, Aa);
        (lc = a(e.showalternatevgridcolor, 0)) && (jd = fa(c(e.alternatevgridcolor, p.getColor("altVGridColor")), c(e.alternatevgridalpha, p.getColor("altVGridAlpha"))));
        for (Ua = Hb; Ua < kc; Ua += Hb, Rc = !Rc)Rc && lc && la.plotBands.push({
          isNumVDIV: !0,
          color: jd,
          from: Xc,
          to: Ua,
          zIndex: 1
        }), la.plotLines.push({
          isNumVDIV: !0,
          width: mc,
          color: fa(jc, qd),
          dashStyle: uc ? s(lb, qb, mc) : "none",
          value: Ua,
          zIndex: 1
        }), Xc = Ua;
        Rc && lc && la.plotBands.push({isNumVDIV: !0, color: jd, from: Xc, to: wb, zIndex: 1})
      }
      ia = V.marginTop;
      Q = V.marginBottom;
      P = V.marginLeft;
      Ja = V.marginRight;
      K.canvasstartx = P;
      K.canvasstarty = ia;
      K.canvasendx = r - Ja;
      K.canvasendy = w - Q;
      K.canvaswidth = K.canvasendx - K.canvasstartx;
      K.canvasheight = K.canvasendy - K.canvasstarty;
      k.legend && k.legend.enabled && "vertical" === k.legend.layout && (K.legendstarty = ia + .5 * (M.height - Q - ia - K.legendheight) + (k.legend.y || 0), K.legendendy = K.legendstarty + K.legendheight);
      m && V.xDepth > V.marginLeft && (V.marginLeft = V.xDepth);
      t.console && t.console.log && t.FC_DEV_ENVIRONMENT && console.log(k);
      return k
    },
    parseHoverEffectOptions: function (b) {
      var d = this.dataObj.chart,
        f;
      b.showHoverEffect = d.showhovereffect;
      b.plotHoverEffect = a(d.plothovereffect, d.anchorhovereffect, b.showHoverEffect);
      f = b.plotHoverEffects = {enabled: b.plotHoverEffect};
      f.highlight = a(d.highlightonhover, d.highlightplotonhover, b.plotHoverEffect);
      f.columnHighlight = a(f.highlight, d.highlightcolumnonhover, d.highlightbaronhover);
      f.anchorHighlight = a(f.highlight, d.highlightanchoronhover);
      f.imageHighlight = a(f.highlight, d.highlightanchorimageonhover);
      f.anchorImageHoverAlpha = c(d.anchorimagehoveralpha);
      f.anchorImageHoverScale =
        c(d.anchorimagehoverscale);
      f.bubbleHighlight = a(f.highlight, d.highlightbubbleonhover);
      f.color = c(d.plotfillhovercolor, d.columnhovercolor, d.barhovercolor, d.bubblehovercolor);
      f.alpha = c(d.plotfillhoveralpha, d.columnhoveralpha, d.barhoveralpha, d.bubblehoveralpha);
      f.scale = c(d.plothoverscale, d.columnhoverscale, d.barhoverscale, d.bubblehoverscale);
      f.gradientColor = d.plothovergradientcolor;
      f.ratio = d.plothoverratio;
      f.angle = d.plothoverangle;
      f.borderColor = d.plotborderhovercolor;
      f.borderAlpha = d.plotborderhoveralpha;
      f.borderThickness = d.plotborderhoverthickness;
      f.borderDashed = d.plotborderhoverdashed;
      f.borderDashGap = d.plotborderhoverdashgap;
      f.borderDashLen = d.plotborderhoverdashlen;
      f.shadow = d.plothovershadow;
      f.anchorScale = d.anchorhoverscale;
      f.anchorSides = d.anchorhoversides;
      f.anchorRadius = d.anchorhoverradius;
      f.anchorAlpha = d.anchorhoveralpha;
      f.anchorBgColor = c(d.anchorbghovercolor, d.anchorhovercolor);
      f.anchorBgAlpha = d.anchorbghoveralpha;
      f.anchorBorderColor = d.anchorborderhovercolor;
      f.anchorBorderAlpha = d.anchorborderhoveralpha;
      f.anchorBorderThickness = d.anchorborderhoverthickness;
      f.anchorStartAngle = d.anchorhoverstartangle;
      f.anchorDip = a(d.anchorhoverdip);
      f.anchorAnimation = a(d.anchorhoveranimation, 1);
      f.negativeColor = c(d.negativehovercolor, d.negativecolor);
      f.is3DBubble = a(d.is3donhover)
    },
    parseExportOptions: function (d) {
      var f = this.chartInstance, v = this.dataObj.chart;
      g(d.exporting, {
        enabled: a(v.exportenabled, 0),
        bgcolor: f.jsVars.transparent || 0 === a(f.options.containerBackgroundOpacity, 1) ? b : f.options.containerBackgroundColor || "#ffffff",
        bgalpha: (f.jsVars.transparent ? 0 : a(f.options.containerBackgroundOpacity, 1)) + b,
        exporttargetwindow: c(v.exporttargetwindow, qb ? "_blank" : "_self"),
        exportaction: v.exportaction && "save" === v.exportaction.toString().toLowerCase() && "save" || "download",
        exportfilename: c(v.exportfilename, "FusionCharts"),
        exporthandler: c(v.html5exporthandler, v.exporthandler, G),
        exportparameters: c(v.exportparameters, b),
        exportformat: c(v.exportformat, "PNG"),
        exportcallback: c(v.exportcallback, b),
        exportwithimages: a(v.exportwithimages, 0),
        exportatclientside: a(v.exportatclientside,
          0),
        buttons: {
          printButton: {enabled: !!a(v.printshowbutton, v.showprintmenuitem, 0)},
          exportButton: {enabled: !(!a(v.exportenabled, 0) || !a(v.exportshowbutton, v.exportshowmenuitem, 1))}
        }
      });
      var f = d.exporting, h;
      v = v.exportformats;
      d = P(d.exporting.exportaction);
      d = {
        JPG: d + " as JPEG image",
        PNG: d + " as PNG image",
        PDF: d + " as PDF document",
        SVG: d + " as SVG vector image"
      };
      var e, l, k;
      if (v) {
        v = v.split(/\s*?\|\s*?/);
        for (k = 0; k < v.length; k++)l = (e = v[k].split(/\s*?=\s*?/)) && e[0].toUpperCase() || b, e = e && e[1] || b, d[l] && (h || (h = {})) && (h[l] =
          e || d[l]);
        h = h || d
      } else h = d;
      f.exportformats = h
    },
    defaultSeriesType: b,
    paletteIndex: 1,
    creditLabel: Ib,
    titleSpaceManager: Eb,
    placeLegendBlockBottom: oc,
    configureLegendOptions: Y,
    placeLegendBlockRight: Yb,
    placeHorizontalAxis: sb,
    placeVerticalAxis: Bb,
    placeHorizontalCanvasMarginAdjustment: Pa,
    placeVerticalCanvasMarginAdjustment: ub,
    placeHorizontalXYSpaceManager: function (b, d, g, h) {
      var e = b[f], l, k, p, m, n = d.chart, I, H, q, O, s, t, u, ca = b.chart, A = e.marginLeftExtraSpace, z = e.marginTopExtraSpace, ea = e.marginBottomExtraSpace, C = e.marginRightExtraSpace;
      m = g - (A + C + ca.marginRight + ca.marginLeft);
      var V = h - (ea + ca.marginBottom + ca.marginTop), ma = .3 * m;
      g = .3 * V;
      var B = b.xAxis.showLine ? b.xAxis.lineThickness : 0;
      p = b.yAxis[0].showLine ? b.yAxis[0].lineThickness : 0;
      l = m - ma;
      h = V - g;
      I = c(n.legendposition, Da).toLowerCase();
      b.legend.enabled && I === na && (l -= this.placeLegendBlockRight(b, d, l / 2, V));
      s = a(n.xaxisnamepadding, 5);
      t = a(n.labelpadding, 4);
      u = c(n.rotatexaxisname, "ccw");
      u = u === Ca ? "none" : u;
      H = c(n.showplotborder, e.is3d ? Ca : db) === db;
      H = e.plotBorderThickness = H ? e.is3d ? 1 : a(n.plotborderthickness,
        1) : 0;
      q = Z(a(ca.plotBorderWidth, 1), 0);
      !e.isDual && ca.marginRight < q && void 0 === n.chartrightmargin && (k = q - ca.marginRight, m > ma + k && (ca.marginRight = q, m -= k, ma = .3 * m, l = m - ma));
      k = e.x;
      O = Z(q, H / 2);
      t < O && (t = O);
      k.verticalAxisNamePadding = s;
      k.verticalAxisValuesPadding = t + B;
      k.rotateVerticalAxisName = u;
      k.verticalAxisNameWidth = a(n.xaxisnamewidth);
      l -= Bb(b.xAxis, k, b, d, V, l, !1, !1, m);
      b.xAxis.lineEndExtension = p;
      l -= Pa(b, d, l, b.xAxis);
      m = l + ma;
      b.legend.enabled && I !== na && (h -= this.placeLegendBlockBottom(b, d, m, h / 2));
      h -= this.titleSpaceManager(b,
        d, m, h / 2);
      k = e[0];
      k.horizontalAxisNamePadding = a(n.yaxisnamepadding, 5);
      k.horizontalLabelPadding = Z(a(n.yaxisvaluespadding, 4)) + p;
      k.labelDisplay = "auto";
      k.staggerLines = a(n.staggerlines, 2);
      k.slantLabels = a(n.slantlabels, 0);
      k.horizontalLabelPadding = k.horizontalLabelPadding < q ? q : k.horizontalLabelPadding;
      this.xAxisMinMaxSetter(b, d, m);
      p = b.xAxis;
      s = p.plotLines;
      l = h / (p.max - p.min);
      s && s.length && (q = (s[0].value - p.min) * l, s = (p.max - s[s.length - 1].value) * l, e.isBar && (H > q && (p.min -= (H - q) / (2 * l)), H > s && (p.max += (H - s) / (2 * l))));
      h -=
        this.placeHorizontalAxis(b.yAxis[0], k, b, d, m, h, ma);
      h -= ub(b, d, h, b.yAxis[0]);
      Oa(g + h, b, n, b.xAxis, e.x.lYLblIdx, !0);
      Vb(b, b.xAxis.title, h);
      b.legend.enabled && I === na && (b = b.legend, d = g + h, b.height > d && (b.height = d, b.scroll.enabled = !0, d = (b.scroll.scrollBarWidth = 10) + (b.scroll.scrollBarPadding = 2), b.width += d, ca.marginRight += d), b.y = 20);
      ca.marginLeft += A;
      ca.marginTop += z;
      ca.marginBottom += ea;
      ca.marginRight += C
    },
    placeVerticalXYSpaceManager: function (b, d, g, h) {
      var e = b[f], l, k, p = !0;
      l = 0;
      var m = d.chart, n = !1, I, H, q, O, s = b.chart, ca,
        t, u, A = e.marginLeftExtraSpace, z = e.marginTopExtraSpace, ea = e.marginBottomExtraSpace, C = e.marginRightExtraSpace;
      O = g - (A + C + s.marginRight + s.marginLeft);
      var V = h - (ea + s.marginBottom + s.marginTop), ma = .3 * O;
      h = .3 * V;
      var B = O - ma;
      g = V - h;
      l = e.drawFullAreaBorder = a(m.drawfullareaborder, 1);
      var E = c(m.legendposition, Da).toLowerCase();
      ca = b.xAxis.showLine ? b.xAxis.lineThickness : 0;
      t = b.yAxis[0].showLine ? b.yAxis[0].lineThickness : 0;
      u = e.isDual && b.yAxis[1].showLine ? b.yAxis[1].lineThickness : 0;
      I = a(m.yaxisnamepadding, 5);
      H = a(m.yaxisvaluespadding,
        m.labelypadding, 4);
      k = c(m.showplotborder, e.is3d ? Ca : db) === db;
      k = e.plotBorderThickness = k ? e.is3d ? 1 : a(m.plotborderthickness, 1) : 0;
      q = Z(a(s.plotBorderWidth, 1), 0);
      k = Z(q, k / 2);
      "area" !== this.defaultSeriesType || l || (k = q);
      H < q && (H = q);
      !e.isDual && s.marginRight < q && void 0 === m.chartrightmargin && (l = q - b.chart.marginRight, O > ma + l && (O -= l, ma = .3 * O, B = O - ma));
      b.legend.enabled && E === na && (B -= this.placeLegendBlockRight(b, d, B / 2, V));
      e.isDual && (n = !0, l = e[1], p = b.yAxis[1].opposite, q = c(m.rotateyaxisname, p ? "cw" : "ccw"), q = q === Ca ? "none" : q, l.verticalAxisNamePadding =
        I, l.verticalAxisValuesPadding = H + u, l.rotateVerticalAxisName = q, l.verticalAxisNameWidth = a(m.syaxisnamewidth), B -= Bb(b.yAxis[1], l, b, d, V, B / 2, p, n));
      l = e[0];
      p = !p;
      q = c(m.rotateyaxisname, p ? "cw" : "ccw");
      q = q === Ca ? "none" : q;
      l.verticalAxisNamePadding = I;
      l.verticalAxisValuesPadding = H + t;
      l.rotateVerticalAxisName = q;
      l.verticalAxisNameWidth = a(n ? m.pyaxisnamewidth : m.yaxisnamewidth);
      B -= Bb(b.yAxis[0], l, b, d, V, B, p, n, O);
      B -= Pa(b, d, B, b.yAxis[0], b.yAxis[1]);
      p = B + ma;
      b.legend.enabled && E !== na && (g -= this.placeLegendBlockBottom(b, d, p, g / 2));
      g -= this.titleSpaceManager(b, d, p, g / 2);
      l = e.x;
      l.horizontalAxisNamePadding = a(m.xaxisnamepadding, 5);
      l.horizontalLabelPadding = a(m.labelpadding, m.labelxpadding, 4) + ca;
      l.labelDisplay = c(m.labeldisplay, "auto").toLowerCase();
      l.rotateLabels = a(m.rotatelabels, m.rotatexaxislabels, 0);
      l.staggerLines = a(m.staggerlines, 2);
      l.slantLabels = a(m.slantlabels, m.slantlabel, 0);
      b.yAxis[0].opposite ? (b.xAxis.lineEndExtension = t, b.xAxis.lineStartExtension = u) : (b.xAxis.lineEndExtension = u, b.xAxis.lineStartExtension = t);
      l.horizontalLabelPadding <
      k && (l.horizontalLabelPadding = k);
      O = {left: 0, right: 0};
      O = s.managePlotOverflow && this.canvasPaddingModifiers && this.calculateCanvasOverflow(b, !0) || O;
      ca = O.left + O.right;
      t = .6 * p;
      ca > t && (u = O.left / ca, O.left -= u * (ca - t), O.right -= (1 - u) * (ca - t));
      this.xAxisMinMaxSetter(b, d, p, O.left, O.right);
      g -= this.placeHorizontalAxis(b.xAxis, l, b, d, p, g, ma);
      g -= ub(b, d, g, b.xAxis);
      b.title.alignWithCanvas || ("left" === b.title.align && b.yAxis[0].title.text && Gb(b, b.yAxis[0].title, h + g), "right" === b.title.align && n && b.yAxis[1].title.text && Gb(b, b.yAxis[1].title,
        h + g));
      n && (Oa(h + g, b, m, b.yAxis[1], e[1].lYLblIdx), Vb(b, b.yAxis[1].title, h + g));
      Oa(h + g, b, m, b.yAxis[0], e[0].lYLblIdx);
      Vb(b, b.yAxis[0].title, h + g);
      b.legend.enabled && E === na && (b = b.legend, d = h + g, b.height > d && "gradient" !== b.type && (b.height = d, b.scroll.enabled = !0, d = (b.scroll.scrollBarWidth = 10) + (b.scroll.scrollBarPadding = 2), b.width += d, s.marginRight += d));
      s.marginLeft += A;
      s.marginTop += z;
      s.marginBottom += ea;
      s.marginRight += C
    },
    placeVerticalAxisTitle: Vb,
    calculateCanvasOverflow: function (a, c) {
      for (var d = this.canvasPaddingModifiers,
             f = a.chart, e = this.smartLabel, g = 0, h = 0, l = 0, k = 0, p = g = !1, m = !1, n = d && d.length || 0, I, H, q, O, s; n--;)switch (h = d[n], h) {
        case "anchor":
          p = H = g = !0;
          break;
        case "anchorlabel":
          q = H = g = !0;
          break;
        case "errorbar":
          m = g = !0
      }
      if (g && (n = (d = a.series) && d.length || 0, c))for (; n--;)I = d[n], H && (g = I && I.data || [], 1 < g.length && (O = g[0], s = g[g.length - 1], p && (g = O && O.marker && O.marker.enabled && (O.marker.radius || 0) + (O.marker.lineWidth || 0) || 0, h = s && s.marker && s.marker.enabled && (s.marker.radius || 0) + (s.marker.lineWidth || 0) || 0, l = Z(g + 2, l), k = Z(h + 2, k)), q && (e.setStyle(a.plotOptions.series.dataLabels.style),
        f.rotateValues ? (h = e.getOriSize(O && O.displayValue || b), g = h.height / 2, h = e.getOriSize(s && s.displayValue || b), h = h.height / 2) : (h = e.getOriSize(O && O.displayValue || b), g = h.width / 2, h = e.getOriSize(s && s.displayValue || b), h = h.width / 2), l = Z(g + 2, l), k = Z(h + 2, k)))), m && (h = g = I.errorBarWidth / 2 + I.errorBarThickness || 0, l = Z(g + 2, l), k = Z(h + 2, k));
      return {left: l, right: k}
    },
    spaceManager: function () {
      return this.placeVerticalXYSpaceManager.apply(this, arguments)
    },
    axisMinMaxSetter: function (b, c, d, f, e, g, h, l) {
      d = c.stacking100Percent ? ca(99, 1, 100,
        0, e, g, h, l) : ca(a(c.max, d), a(c.min, f), d, f, e, g, h, l);
      b.min = Number(Q(d.Min, 10));
      b.max = Number(Q(d.Max, 10));
      b.tickInterval = Number(Q(d.divGap, 10));
      c.numdivlines = u.round((b.max - b.min) / b.tickInterval) - 1;
      2 >= d.Range / d.divGap && (b.alternateGridColor = xa);
      this.highValue = c.max;
      this.lowValue = c.min;
      delete c.max;
      delete c.min
    },
    configurePlotLines: function (d, g, h, l, e, k, p, m, n, I, H) {
      var O = h.min, q = h.max, s = h.tickInterval, ca = I ? "xAxis" : l.stacking100Percent ? "percentValue" : "yAxis", t = O, u = 1, A = h.gridLineColor, z = h.gridLineWidth, ea = h.gridLineDashStyle,
        C = 0 > O && 0 < q ? !0 : !1, V = 0 === O, ma = 0 === q, B = 0 === a(l.showzeroplanevalue, d.showzeroplanevalue), E = !0, la, G = 1, K = 0 < a(d.numdivlines, 0), oa = g[f].axisGridManager, ia = this.colorManager, La = this.is3D, P = a(d.showaxislimitgridlines, this.showAxisLimitGridLines), La = a(P, La || g.chart.plotBorderWidth ? 0 : 1), Ja = this.inversed;
      g = g.xAxis;
      H = a(H, n ? 1 : 0);
      delete h._altGrid;
      delete h._lastValue;
      I && !l.catOccupied && (l.catOccupied = {});
      !C || I && l.catOccupied[0] || (I ? (E = a(d.showvzeroplane, 1), la = a(d.showvzeroplanevalue, k), K = a(d.vzeroplanethickness,
        1), ia = c(d.vzeroplanealpha, d.vdivlinealpha, ia.getColor("divLineAlpha")), d = 0 < K ? fa(c(d.vzeroplanecolor, A), ia) : xa) : (ia = a(d.divlinealpha, ia.getColor("divLineAlpha")), la = a(l.showzeroplanevalue, d.showzeroplanevalue, k), !1 === this.defaultZeroPlaneHighlighted ? (E = a(l.showzeroplane, d.showzeroplane, !(this.defaultZeroPlaneHidden && !K)), K = z) : (K = 1 === z ? 2 : z, G = 5, ia = ba(2 * ia, 100)), K = a(l.zeroplanethickness, d.zeroplanethickness, K), ia = c(l.zeroplanealpha, d.zeroplanealpha, ia), d = 0 < K ? fa(c(l.zeroplanecolor, d.zeroplanecolor, A),
        ia) : xa), E && (la = la ? m[ca](0, H) : b, (G = oa.addAxisGridLine(h, 0, la, K, ea, d, G, I)) && (G.isZeroPlane = !0)), h.effectiveZeroPlaneThickness = E && parseInt(ia, 10) && K);
      I && l.catOccupied[O] || (la = !e || V && B ? b : m[ca](O, H), (G = P || La && (Ja || !g.showLine) ? oa.addAxisGridLine(h, O, la, z, ea, A || xa, 2, I) : oa.addAxisGridLine(h, O, la, .1, void 0, xa, 2, I)) && (G.isMinLabel = !0));
      0 >= z && (z = .1, A = xa);
      for (O = Number(Q(t + s, 10)); O < q; O = Number(Q(O + s, 10)), u += 1)C && 0 > t && 0 < O && !n && (oa.addAxisAltGrid(h, 0), u += 1), 0 === O || I && l.catOccupied[O] || (la = 1 === k && 0 === u % p ? m[ca](O,
        H) : b, oa.addAxisGridLine(h, O, la, z, ea, A, 2, I)), t = O, n || oa.addAxisAltGrid(h, O);
      n || oa.addAxisAltGrid(h, q);
      0 !== u % p || I && l.catOccupied[q] || (la = !e || ma && B ? b : m[ca](q, H), (G = P || La && (!Ja || !g.showLine) ? oa.addAxisGridLine(h, q, la, z, ea, A || xa, 2, I) : oa.addAxisGridLine(h, q, la, .1, ea, xa, 2, I)) && (G.isMaxLabel = !0));
      this.realtimeEnabled && (h.labels._enabled = h.labels.enabled, h._gridLineWidth = h.gridLineWidth, h._alternateGridColor = h.alternateGridColor);
      h.labels.enabled = !1;
      h.gridLineWidth = 0;
      h.alternateGridColor = xa;
      h.plotLines.sort(Kb)
    },
    xAxisMinMaxSetter: function (b, c, d, g, e) {
      var h = b[f], l = h.x, k = c.chart, p = l.min = a(l.min, 0), m = l.max = a(l.max, l.catCount - 1), n = 0, I = 0, H = b.chart.defaultSeriesType, O = /^(column|column3d|bar|bar3d|floatedcolumn|sparkwinloss|boxandwhisker2d|dragcolumn)$/.test(H), q = /^(line|area|spline|areaspline)$/.test(H), H = /^(scatter|bubble|candlestick|dragnode)$/.test(H), s = b.xAxis, ca = s.scroll, t = ca && ca.enabled, u = a(k.canvaspadding), A = X(u), z = Aa(ba(a(u, g, 0), d / 2 - 10)), u = Aa(ba(a(u, e, 0), d / 2 - 10)), ea, C, V, ma;
      l.adjustMinMax && (ea = a(k.setadaptivexmin,
        1), m = p = !ea, C = a(this.numVDivLines, k.numvdivlines, 4), V = k.adjustvdiv !== Ca, ma = a(k.showxaxisvalues, k.showxaxisvalue, 1), ea = a(k.showvlimits, ma), ma = a(k.showvdivlinevalue, k.showvdivlinevalues, ma), this.axisMinMaxSetter(s, l, k.xaxismaxvalue, k.xaxisminvalue, p, m, C, V), p = s.min, m = s.max, l.requiredAutoNumericLabels && (C = a(parseInt(k.xaxisvaluesstep, 10), 1), this.configurePlotLines(k, b, s, l, ea, ma, 1 > C ? 1 : C, h.numberFormatter, !1, !0)), s.plotLines.sort(Kb));
      s.labels.enabled = !1;
      s.gridLineWidth = 0;
      s.alternateGridColor = xa;
      (O || h.isScroll) && !h.hasNoColumn && !A && X(g) && X(e) && (I = d / (m - p + 1) * .5, z = 0 < I - g ? 0 : z, u = 0 < I - e ? 0 : u, n = 0 < I - g ? .5 : 0, I = 0 < I - e ? .5 : 0);
      O && !h.hasNoColumn && (I = n = .5);
      h.is3d && (z += a(b.chart.xDepth, 0));
      b = (d - (z + u)) / ((t ? ca.vxLength : m) - p + (n + I));
      s.min = p - (n + z / b);
      s.max = m + (I + u / b);
      t && (n = ca.vxLength, b = s.max - s.min, ca.viewPortMin = s.min, ca.viewPortMax = s.max, ca.scrollRatio = n / b, ca.flatScrollBars = h.flatScrollBars, ca.scrollBar3DLighting = h.scrollBar3DLighting, s.max = s.min + n);
      q && s.min === s.max && (s.min -= .65, s.max += .65);
      H && c.vtrendlines && K(c.vtrendlines, s, h, !1,
        !0, !0)
    },
    postSeriesAddition: function (b) {
      var d = b[f], h = d.isBar, l = d.is3d, e = b.chart.rotateValues && !h ? 270 : 0, k = d[0], p = k && k.stacking100Percent, m, I, H, O, s, q, F, ca, u, t, A, z, ea, C, V, ma, B, E, la, G, K, oa, ia;
      if (this.isStacked)for (H in m = d.plotSpacePercent, I = b.chart.defaultSeriesType, m = 1 - 2 * m, ma = b.series, B = this.numberFormatter, K = g({}, b.plotOptions.series.dataLabels.style), oa = parseFloat(K.fontSize), ia = !k.stacking100Percent, K.color = b.plotOptions.series.dataLabels.color, O = k.stack, O) {
        k = O[H].length;
        s = m / k;
        F = -(m - s) / 2;
        C = [];
        z = 0;
        for (ca = ma.length; z < ca; z += 1)u = ma[z], u.yAxis || c(u.type, I) !== H || C.push(u);
        for (q = 0; q < k; q += 1, F += s) {
          A = O[H][q];
          V = [];
          z = 0;
          for (ca = C.length; z < ca; z += 1)u = C[z], a(u.columnPosition, 0) === q && V.push(u.data);
          if (A && A.length)for (t = 0, u = A.length; t < u; t += 1)if (z = A[t])for (ea = (z.n || 0) + (z.p || 0), d.showStackTotal && (ca = t, ca += F, z = 0 > ea ? z.n : z.p, b.xAxis.plotLines.push({
            value: ca,
            width: 0,
            isVline: ia,
            isTrend: !ia,
            zIndex: 4,
            _isStackSum: 1,
            _catPosition: t,
            _stackIndex: q,
            label: {
              align: ya,
              textAlign: l || 270 !== e ? h ? 0 > ea ? na : ua : ya : 0 > ea ? na : ua,
              offsetScale: ia ?
                z : void 0,
              offsetScaleIndex: 0,
              rotation: e,
              style: K,
              verticalAlign: Fa,
              y: h ? 0 : 0 > ea ? 270 === e ? 4 : oa : -4,
              x: 0,
              text: d.numberFormatter.yAxis(ea)
            }
          })), z = 0, ca = V.length; z < ca; z += 1)if (la = V[z][t])if (G = ea && (la.y || 0) / ea * 100, E = B.percentValue(G), la.toolText = n(la.toolText, [14, 24, 25, 112], {
              percentValue: E,
              sum: B.dataLabels(ea),
              unformattedSum: ea
            }), la.y || 0 === la.y)p && (la.y = G, la.previousY || 0 === la.previousY) && (la.previousY = la.previousY / ea * 100), la.showPercentValues && (la.displayValue = E)
        }
      }
    },
    styleMapForFont: ia,
    styleApplicationDefinition_font: function (a,
                                               b, c) {
      var d, e, g = !1, l, k, p = this.styleMapForFont;
      switch (b) {
        case "caption":
          d = a.title;
          break;
        case "datalabels":
          d = a.xAxis.labels;
          break;
        case "datavalues":
          d = a.plotOptions.series.dataLabels;
          g = !0;
          break;
        case "tldatavalues":
          d = {style: a.plotOptions.series.dataLabels.tlLabelStyle};
          break;
        case "trdatavalues":
          d = {style: a.plotOptions.series.dataLabels.trLabelStyle};
          break;
        case "bldatavalues":
          d = {style: a.plotOptions.series.dataLabels.blLabelStyle};
          break;
        case "brdatavalues":
          d = {style: a.plotOptions.series.dataLabels.brLabelStyle};
          break;
        case "subcaption":
          d = a.subtitle;
          break;
        case "tooltip":
          d = a.tooltip;
          break;
        case "trendvalues":
          d = {style: a[f].trendStyle};
          break;
        case "xaxisname":
          d = a.xAxis.title;
          break;
        case "yaxisname":
        case "pyaxisname":
        case "axistitle":
          d = [];
          b = 0;
          for (l = a.yAxis.length; b < l; b += 1)d.push(a.yAxis[b].title);
          break;
        case "yaxisvalues":
          d = [];
          b = 0;
          for (l = a.yAxis.length; b < l; b += 1)d.push(a.yAxis[b].labels);
          break;
        case "vlinelabels":
          d = {style: a[f].divlineStyle};
          break;
        case "legend":
          d = {style: a.legend.itemStyle};
          break;
        default:
          (d = a.orphanStyles[b]) ||
          (a.orphanStyles[b] = d = {text: "", style: {}})
      }
      if ("object" === typeof d)if (d instanceof Array)for (b = 0, l = d.length; b < l; b += 1) {
        k = d[b];
        for (e in c)if (a = e.toLowerCase(), "function" === typeof p[a])p[a](c[e], k, g);
        h(k.style)
      } else {
        for (e in c)if (a = e.toLowerCase(), "function" === typeof p[a])p[a](c[e], d, g);
        h(d.style)
      }
    },
    parseStyles: function (a) {
      var b, c, d, e = {}, f, g = this.dataObj;
      if (g.styles && g.styles.definition instanceof Array && g.styles.application instanceof Array) {
        for (b = 0; b < g.styles.definition.length; b += 1)c = g.styles.definition[b],
        c.type && c.name && this["styleApplicationDefinition_" + c.type.toLowerCase()] && (e[c.name.toLowerCase()] = c);
        for (b = 0; b < g.styles.application.length; b += 1)for (c = g.styles.application[b].styles && g.styles.application[b].styles.split(Ba) || [], f = 0; f < c.length; f += 1)if (d = c[f].toLowerCase(), e[d] && g.styles.application[b].toobject)this["styleApplicationDefinition_" + e[d].type.toLowerCase()](a, g.styles.application[b].toobject.toLowerCase(), e[d])
      }
    },
    updateDefaultAnnotations: function () {
      var b = this.renderer, d = this.dataObj, f =
        this.chartInstance, g = d && d.annotations || {}, e = {}, h;
      if (this.drawAnnotations && f.dataReady() && d && d.chart && a(d.chart.showannotations, 1)) {
        h = a(g.scaleonresize, d.chart.scaleonresize, 1);
        var b = {
          interactionevents: c(this.annotationInteractionEvents, !0),
          showbelow: c(g.showbelow, g.showbelowchart),
          autoscale: g.autoscale,
          scaletext: g.scaletext,
          scaleimages: g.scaleimages,
          constrainedscale: g.constrainedscale,
          scaleonresize: h,
          origw: c(g.origw, d.chart.origw, h ? this.origRenderWidth : b.chartWidth),
          origh: c(g.origh, d.chart.origh,
            h ? this.origRenderHeight : b.chartHeight),
          xshift: g.xshift,
          yshift: g.yshift,
          grpxshift: g.grpxshift,
          grpyshift: g.grpyshift,
          xscale: g.xscale,
          yscale: g.yscale,
          rootxscale: a(g.xscale, 100) / 100,
          rootyscale: a(g.yscale, 100) / 100
        }, l;
        b || (b = {});
        for (l in e)b[l] = e[l];
        f.annotations.reset(g, b, this.snapLiterals)
      } else f.annotations.clear()
    },
    dispose: function () {
      var a;
      this.disposing = !0;
      this.renderer && this.renderer.dispose();
      this.numberFormatter && this.numberFormatter.dispose();
      this.hcJSON && this.hcJSON.chart && this.hcJSON.chart.renderTo && delete this.hcJSON.chart.renderTo;
      for (a in this)delete this[a];
      delete this.disposing;
      this.disposed = !0
    }
  });
  pa("stub", {
    init: function (a, b, c) {
      this.containerElement = a;
      this.smartLabel = c.jsVars.smartLabel
    }, standaloneInit: !0
  }, pa.base);
  pa("barbase", {
    spaceManager: function () {
      return this.placeHorizontalXYSpaceManager.apply(this, arguments)
    }
  }, pa.base);
  pa("singleseries", {
    series: function (a, b, c) {
      var d = a.data || a.dataset && a.dataset[0] && a.dataset[0].data, e;
      d && 0 < d.length && d instanceof Array && (e = {
        data: [], hoverEffects: this.parseSeriesHoverOptions(a,
          b, {}, c), colorByPoint: !0
      }, b.legend.enabled = !1, c = this.point(c, e, d, a.chart, b), c instanceof Array ? b.series = b.series.concat(c) : b.series.push(c), this.configureAxis(b, a), a.trendlines && K(a.trendlines, b.yAxis, b[f], !1, this.isBar))
    }, defaultSeriesType: b, configureAxis: function (b, d) {
      var g = b[f], h = b.xAxis, e = d.chart, l = b.chart.is3D, k, p, m, n, I, H, O, q, s, ca, u, t, A = 0, z, ea, V, ma, B, la, E, G = this.numberFormatter, K = a(e.syncaxislimits, 0), oa;
      h.title.text = C(e.xaxisname);
      oa = a(parseInt(e.yaxisvaluesstep, 10), parseInt(e.yaxisvaluestep,
        10), 1);
      oa = 1 > oa ? 1 : oa;
      k = b.yAxis[0];
      p = g[0];
      if (g.isDual)m = G.getCleanValue(e.pyaxismaxvalue), n = G.getCleanValue(e.pyaxisminvalue), k.title.text = C(e.pyaxisname), K && !p.stacking100Percent ? (t = g[1], u = a(t.max), t = a(t.min), void 0 !== u && void 0 !== t && (p.min = ba(p.min, t), p.max = Z(p.max, u)), u = G.getCleanValue(e.syaxismaxvalue), t = G.getCleanValue(e.syaxisminvalue), null !== t && (n = null !== n ? ba(n, t) : t), null !== u && (m = null !== m ? Z(m, u) : u)) : K = 0; else {
        m = G.getCleanValue(e.yaxismaxvalue);
        n = G.getCleanValue(e.yaxisminvalue);
        if (g.isSpline)for (t =
                              0; t < b.series.length; t++)u = ab(b.series[t].data, g.width, b.plotOptions.series.connectNullData), p.min = ba(p.min, u.min), p.max = Z(p.max, u.max);
        k.title.text = C(e.yaxisname)
      }
      O = a(this.isStacked ? 0 : this.setAdaptiveYMin, e.setadaptiveymin, this.defSetAdaptiveYMin, 0);
      H = I = !O;
      q = a(g.numdivlines, e.numdivlines, this.numdivlines, 4);
      s = e.adjustdiv !== Ca;
      ca = a(this.showYAxisValues, e.showyaxisvalues, e.showyaxisvalue, 1);
      u = a(e.showyaxislimits, e.showlimits, ca);
      t = a(e.showdivlinevalue, e.showdivlinevalues, ca);
      l || (A = a(e.showaxislines,
        e.drawAxisLines, 0), V = a(e.axislinethickness, 1), B = a(e.axislinealpha, 100), 100 < B && (B = 100), ea = fa(c(e.axislinecolor, "#000000"), B), k.showLine = a(e.showyaxisline, A), z = h.showLine = a(e.showxaxisline, A), ma = h.lineThickness = a(e.xaxislinethickness, V), k.lineThickness = a(e.yaxislinethickness, V), la = h.lineAlpha = a(e.xaxislinealpha, B), 100 < la && (la = h.lineAlpha = 100), E = k.lineAlpha = a(e.yaxislinealpha, B), 100 < E && (E = k.lineAlpha = 100), h.lineColor = fa(c(e.xaxislinecolor, ea), la), k.lineColor = fa(c(e.yaxislinecolor, ea), E), b.chart.xAxisLineVisible =
        z && !!ma && 0 < la);
      this.axisMinMaxSetter(k, p, m, n, I, H, q, s);
      this.configurePlotLines(e, b, k, p, u, t, oa, g.numberFormatter, !1);
      k.reversed && 0 <= k.min && (b.plotOptions.series.threshold = k.max);
      g.isDual && (k = b.yAxis[1], p = g[1], u = a(e.showsecondarylimits, u), t = a(e.showdivlinesecondaryvalue, ca), K ? (h = b.yAxis[0], k.min = h.min, k.max = h.max, k.tickInterval = h.tickInterval, delete p.max, delete p.min) : (m = G.getCleanValue(e.syaxismaxvalue), n = G.getCleanValue(e.syaxisminvalue), O = a(e.setadaptivesymin, O), H = I = !O, this.axisMinMaxSetter(k, p,
        m, n, I, H, q, s)), l || (k.showLine = a(e.showsyaxisline, A), k.lineThickness = a(e.syaxislinethickness, V), l = k.lineAlpha = a(e.syaxislinealpha, B), 100 < l && (l = 100), k.lineColor = fa(c(e.syaxislinecolor, ea), l)), this.configurePlotLines(e, b, k, p, u, t, oa, g.numberFormatter, !0), k.title.text = C(e.syaxisname))
    }, pointValueWatcher: function (b, d, g, h, e, l, k) {
      b = b[f];
      var p;
      if (null !== d)return g = a(g, 0), b[g] || (b[g] = {}), g = b[g], h && (this.distributedColumns && (b.marimekkoTotal += d), h = g.stack, e = a(e, 0), l = a(l, 0), k = c(k, jb), h[k] || (h[k] = []), k = h[k], k[l] ||
      (k[l] = []), l = k[l], l[e] || (l[e] = {}), e = l[e], 0 <= d ? e.p ? (p = e.p, d = e.p += d) : e.p = d : e.n ? (p = e.n, d = e.n += d) : e.n = d), g.max = g.max > d ? g.max : d, g.min = g.min < d ? g.min : d, p
    }, parseSeriesHoverOptions: function (b, d, f) {
      b = d.chart.plotHoverEffects;
      d = {enabled: c(f.showhovereffect, f.hovereffect, b.enabled)};
      d.highlight = a(f.highlightonhover, f.highlightplotonhover, b.highlight);
      d.columnHighlight = a(d.highlight, f.highlightcolumnonhover, f.highlightbaronhover, b.columnHighlight);
      d.anchorHighlight = a(d.highlight, f.highlightanchoronhover, b.anchorHighlight);
      d.anchorHighlight = a(d.highlight, f.highlightimageonhover, b.imageHighlight);
      d.bubbleHighlight = a(d.highlight, f.highlightbubbleonhover, f.highlightbaronhover, b.bubbleHighlight);
      d.imageHoverAlpha = c(f.anchorimagehoveralpha, b.anchorImageHoverAlpha);
      d.imageHoverScale = c(f.anchorimagehoverscale, b.anchorImageHoverScale);
      d.color = c(f.hovercolor, f.bubblehovercolor, b.color);
      d.alpha = c(f.hoveralpha, b.alpha);
      d.scale = c(f.hoverscale, f.bubblehoverscale, b.scale);
      d.gradientColor = void 0 !== f.hovergradientcolor ? f.hovergradientcolor :
        b.gradientColor;
      d.ratio = c(f.hoverratio, b.ratio);
      d.angle = c(f.hoverangle, b.angle);
      d.borderColor = c(f.borderhovercolor, b.borderColor);
      d.borderAlpha = c(f.borderhoveralpha, b.borderAlpha);
      d.borderThickness = a(f.borderhoverthickness, b.borderThickness);
      d.borderDashed = a(f.borderhoverdashed, b.borderDashed);
      d.borderDashGap = a(f.borderhoverdashgap, b.borderDashGap);
      d.borderDashLen = a(f.borderhoverdashlen, b.borderDashLen);
      d.shadow = c(f.hovershadow, b.shadow);
      d.anchorSides = c(f.anchorhoversides, b.anchorSides);
      d.anchorRadius =
        c(f.anchorhoverradius, b.anchorRadius);
      d.anchorScale = c(f.anchorhoverscale, b.anchorScale);
      d.anchorAlpha = c(f.anchorhoveralpha, f.hoveralpha, b.anchorAlpha);
      d.anchorBgColor = c(f.anchorbghovercolor, f.anchorhovercolor, b.anchorBgColor);
      d.anchorBgAlpha = c(f.anchorbghoveralpha, b.anchorBgAlpha);
      d.anchorBorderColor = c(f.anchorborderhovercolor, b.anchorBorderColor);
      d.anchorBorderAlpha = c(f.anchorborderhoveralpha, b.anchorBorderAlpha);
      d.anchorBorderThickness = a(f.anchorborderhoverthickness, b.anchorBorderThickness);
      d.anchorStartAngle =
        c(f.anchorhoverstartangle, b.anchorStartAngle);
      d.anchorDip = c(f.anchorhoverdip, b.anchorDip);
      d.anchorAnimation = a(f.anchorhoveranimation, b.anchorAnimation, 1);
      d.negativeColor = c(f.negativehovercolor, b.negativeColor);
      d.is3DBubble = a(f.is3donhover, b.is3DBubble);
      return d
    }, parseLegendOptions: function (b, d) {
      var f = {
        borderColor: c(d.legendiconbordercolor, b.legendiconbordercolor),
        bgColor: c(d.legendiconbgcolor, b.legendiconbgcolor),
        alpha: c(d.legendiconalpha, b.legendiconalpha),
        bgAlpha: c(d.legendiconbgalpha, b.legendiconbgalpha),
        sides: a(d.legendiconsides, b.legendiconsides),
        borderThickness: a(d.legendiconborderthickness, b.legendiconborderthickness),
        startAngle: a(d.legendiconstartangle, b.legendiconstartangle)
      };
      if (f.customLegendIcon = a(b.drawcustomlegendicon, d.drawcustomlegendicon))f.startAngle = a(f.startAngle, 45), f.symbol = bb(f.sides || 4);
      return f
    }, pointHoverOptions: function (d, f, g) {
      var h, e, l, k = {};
      h = f.hoverEffects;
      f = a(d.hovereffect, h && h.enabled);
      e = !1;
      var p = {enabled: f}, m = g && b + g.plotType.toLowerCase();
      if (void 0 === f)if (this.forceHoverEnable)e =
        f = p.enabled = !0; else {
        "anchor" == m && (e = g.imageUrl ? f = p.enabled = void 0 !== c(d.anchorimagehoveralpha, h.imageHoverAlpha, d.anchorimagehoverscale, h.imageHoverScale, void 0) : f = p.enabled = void 0 !== c(d.hovercolor, d.anchorhovercolor, d.anchorbghovercolor, h.anchorBgColor, h.color, d.hoveralpha, d.anchorhoveralpha, h.anchorAlpha, d.bghoveralpha, d.anchorbghoveralpha, h.anchorBgAlpha, d.anchorborderhovercolor, d.borderhovercolor, h.anchorBorderColor, d.anchorborderhoverthickness, d.borderhoverthickness, h.anchorBorderThickness, d.anchorborderhoveralpha,
          d.borderhoveralpha, h.anchorBorderAlpha, d.hoverdip, d.anchorhoverdip, h.anchorDip, d.anchorhoverstartangle, h.anchorStartAngle, d.hoversides, d.anchorhoversides, h.anchorSides, d.hoverradius, d.anchorhoverradius, h.anchorRadius, void 0));
        if ("column" == m || "bubble" == m)e = f = p.enabled = void 0 !== c(d.hoveralpha, h.alpha, d.hovergradientcolor, h.gradientColor, d.borderhovercolor, h.borderColor, d.borderhoverthickness, h.borderThickness, d.hoverratio, h.ratio, d.hoverangle, h.angle, d.borderhoveralpha, h.borderAlpha, d.borderhoverdashed,
          h.borderDashed, d.borderhoverdashgap, h.borderDashGap, d.borderhoverdashlen, h.borderDashLen, d.hovercolor, h.color, void 0);
        e || "bubble" != m || (e = f = p.enabled = void 0 !== c(d.negativehovercolor, h.negativeColor, d.is3donhover, h.is3DBubble, d.hoverscale, h.scale, void 0));
        "pie" == m && (e = f = p.enabled = void 0 !== c(d.hovercolor, h.color, d.hoveralpha, h.alpha, d.borderhovercolor, h.borderColor, d.borderhoverthickness, h.borderThickness, d.borderhoveralpha, h.borderAlpha, void 0))
      }
      if (f) {
        p.highlight = a(d.highlightonhover, h.highlight);
        p.columnHighlight =
          a(p.highlight, d.highlightcolumnonhover, d.highlightbaronhover);
        p.anchorHighlight = a(p.highlight, d.highlightanchoronhover);
        p.bubbleHighlight = a(p.highlight, d.highlightbubbleonhover);
        p.alpha = c(d.hoveralpha, h.alpha, g.alpha);
        p.scale = c(d.hoverscale, h.scale, 1);
        p.gradientColor = void 0 === d.hovergradientcolor ? h.gradientColor : d.hovergradientcolor;
        p.borderColor = c(d.borderhovercolor, h.borderColor, g.borderColor);
        p.borderThickness = a(d.borderhoverthickness, h.borderThickness, g.borderWidth);
        p.ratio = c(d.hoverratio, h.ratio,
          g.ratio);
        p.angle = c(d.hoverangle, h.angle, g.angle);
        p.borderAlpha = c(d.borderhoveralpha, h.borderAlpha, g.borderAlpha);
        p.borderDashed = a(d.borderhoverdashed, h.borderDashed, g.borderDashed, 0);
        p.borderDashGap = a(d.borderhoverdashgap, h.borderDashGap, g.borderDashGap);
        p.borderDashLen = a(d.borderhoverdashlen, h.borderDashLen, g.borderDashLen);
        p.shadow = c(d.hovershadow, h.shadow, 0);
        p.color = c(d.hovercolor, h.color);
        "anchor" == m && (g.imageUrl ? (p.imageHoverAlpha = a(d.anchorimagehoveralpha, h.imageHoverAlpha, 100), p.imageHoverScale =
          g.imageScale * za(a(d.anchorimagehoverscale, h.imageHoverScale, 110)) * .01, p.anchorAnimation = a(d.anchorhoveranimation, h.anchorAnimation, 1)) : (p.anchorColor = T(c(d.hovercolor, d.anchorhovercolor, d.anchorbghovercolor, h.anchorBgColor, h.color, g.anchorBgColor)), p.anchorAlpha = c(d.hoveralpha, d.anchorhoveralpha, h.anchorAlpha, g.anchorAlpha), p.anchorBgAlpha = c(d.bghoveralpha, d.anchorbghoveralpha, h.anchorBgAlpha, p.anchorAlpha, g.anchorBgAlpha), p.anchorBorderColor = c(d.anchorborderhovercolor, d.borderhovercolor, h.anchorBorderColor,
          g.anchorBorderColor), p.anchorBorderThickness = c(d.anchorborderhoverthickness, d.borderhoverthickness, h.anchorBorderThickness, g.anchorBorderThickness), p.anchorBorderAlpha = a(d.anchorborderhoveralpha, d.borderhoveralpha, h.anchorBorderAlpha, p.anchorAlpha, g.anchorBorderAlpha), p.anchorDip = a(d.hoverdip, d.anchorhoverdip, h.anchorDip), p.startAngle = c(d.anchorhoverstartangle, h.anchorStartAngle, g.anchorAngle), p.anchorSides = a(d.hoversides, d.anchorhoversides, h.anchorSides, g.anchorSides), p.anchorRadius = a(d.hoverradius,
          d.anchorhoverradius, h.anchorRadius), p.anchorScale = a(d.hoverscale, d.anchorhoverscale, h.anchorScale), p.anchorAnimation = a(d.anchorhoveranimation, h.anchorAnimation, 1), void 0 === p.anchorRadius && (p.anchorRadius = !e || p.anchorHighlight ? g.anchorRadius && g.anchorRadius + 1 : g.anchorRadius)));
        if (e || (p.columnHighlight || p.bubbleHighlight) && p.color && 1 == p.highlight)p.highlight = 0;
        "column" == m && (p.color = (c(p.color, g.color) + Ba + (void 0 === p.gradientColor ? g.gradientColor : p.gradientColor)).replace(/,+?$/, ""));
        "pie" === m && (p.color =
          c(p.color, g.color).replace(/,+?$/, ""));
        "bubble" == m && (p.negativeColor = c(d.negativehovercolor, h.negativeColor, g.negativeColor), p.is3d = a(d.is3donhover, h.is3DBubble, g.is3d), p.color = p.negativeColor && 0 > d.z ? p.negativeColor : p.color || g.color, l = "string" == typeof p.color, p.color = T(l ? p.color : p.color.FCcolor.color), p.color = p.is3d ? pa.bubble.getPointColor(p.color, p.alpha) : {
          FCcolor: {
            color: p.color,
            alpha: p.alpha
          }
        });
        if (1 == p.highlight && "anchor" !== m) {
          d = (l = "string" == typeof p.color) ? p.color.split(/\s{0,},\s{0,}/) : p.color.FCcolor.color.split(/\s{0,},\s{0,}/);
          h = d.length;
          for (e = 0; e < h; e++)d[e] = da(d[e], 70);
          l ? p.color = d.join(",") : p.color.FCcolor.color = d.join(",")
        }
        "pie" === m && (k = {
          color: this.getPointColor(p.color, p.alpha, g.radius3D),
          alpha: p.alpha,
          borderColor: fa(p.borderColor, p.borderAlpha),
          borderWidth: p.borderThickness
        });
        "column" == m && (p.colorArr = R(p.color, p.alpha, p.ratio, p.angle, g.isRoundEdged, p.borderColor, ba(p.alpha, p.borderAlpha).toString(), g.isBar, g.is3d), p.dashStyle = p.borderDashed ? s(p.borderDashLen, p.borderDashGap, p.borderThickness) : "none", k = {
          shadow: p.shadow,
          color: p.colorArr[0],
          borderColor: p.colorArr[1],
          borderWidth: p.borderThickness,
          use3DLighting: g.use3DLighting,
          dashStyle: p.dashStyle
        });
        "anchor" == m && (k = g.imageUrl ? {
          animation: p.anchorAnimation,
          imageHoverAlpha: p.imageHoverAlpha,
          imageHoverScale: p.imageHoverScale
        } : {
          animation: p.anchorAnimation,
          shadow: p.shadow,
          fillColor: {FCcolor: {color: p.anchorColor, alpha: p.anchorBgAlpha * p.anchorAlpha / 100 + b}},
          lineColor: {FCcolor: {color: p.anchorBorderColor, alpha: p.anchorBorderAlpha}},
          lineWidth: p.anchorBorderThickness,
          radius: p.anchorRadius,
          symbol: bb(p.anchorSides),
          startAngle: p.startAngle,
          sides: p.anchorSides,
          scale: p.anchorScale,
          dip: p.anchorDip
        });
        "bubble" == m && (k = {
          symbol: p.seriesAnchorSymbol,
          shadow: p.shadow,
          scale: p.scale,
          fillColor: p.color,
          lineColor: {FCcolor: {color: p.borderColor, alpha: p.alpha}},
          lineWidth: p.borderThickness
        })
      }
      return {enabled: f, options: p, rolloverOptions: k}
    }, getPointStub: function (d, g, h, p) {
      var e = this.dataObj.chart;
      p = p[f];
      g = null === g ? g : p.numberFormatter.dataLabels(g);
      var k = l(C(c(d.tooltext, p.tooltext))), m = l(C(d.displayvalue)),
        e = p.showTooltip ? void 0 !== k ? n(k, [1, 2, 3, 5, 6, 7], {
          formattedValue: g,
          label: h,
          yaxisName: C(e.yaxisname),
          xaxisName: C(e.xaxisname)
        }, d, e) : null === g ? !1 : h !== b ? h + p.tooltipSepChar + g : g : b;
      p = a(d.showvalue, p.showValues) ? void 0 !== m ? m : g : b;
      d = c(d.link);
      return {displayValue: p, categoryLabel: h, toolText: e, link: d}
    }, updateSnapPoints: function () {
      var a = this, b = a.snapLiterals, c = function (a, b) {
        var c = 0;
        switch (a) {
          case "startx":
            c = b.x;
            break;
          case "starty":
            c = b.y;
            break;
          case "x":
          case "middlex":
          case "centerx":
            c = b.x + b.width / 2;
            break;
          case "y":
          case "middley":
          case "centery":
            c =
              b.y + b.height / 2;
            break;
          case "endx":
            c = b.x + b.width;
            break;
          case "endy":
            c = b.y + b.height;
            break;
          default:
            c = 0
        }
        return c
      };
      b.dataset = function (b, d) {
        var f = a.renderer && a.renderer.plots, g, h, p, l;
        p = a.is3D;
        if (!f || !f.length)return 0;
        isNaN(b[0]) ? g = 0 : (g = Number(b[0]), b = b.slice(1));
        h = b[0];
        if ("set" === h) {
          isNaN(b[1]) ? (l = 0, b = b.slice(1)) : (l = Number(b[1]), b = b.slice(2));
          h = b[0];
          f = (f = f[g] && f[g].items[l]) && f.graphic;
          if (!f)return 0;
          p = d && p ? f._getBBox2() : f.getBBox();
          l = c(h, p)
        }
        return l
      };
      b.xaxis = function (b) {
        var d = a.renderer && a.renderer.xAxis &&
          a.renderer.xAxis[0] && a.renderer.xAxis[0].labels, f, g;
        if (!d || !d.length)return 0;
        g = b[0];
        if ("label" === g) {
          isNaN(b[1]) ? (f = 0, b = b.slice(1)) : (f = Number(b[1]), b = b.slice(2));
          g = b[0];
          b = d[f];
          if (!b)return 0;
          b = b.getBBox();
          f = c(g, b)
        }
        return f
      };
      b.yaxis = function (b) {
        var d = a.renderer && a.renderer.yAxis, f, g;
        if (!d || !d.length)return 0;
        isNaN(b[0]) ? f = 0 : (f = Number(b[0]), b = b.slice(1));
        f = d[f];
        if (!f)return 0;
        d = b[0];
        if ("label" === d) {
          g = f.labels;
          isNaN(b[1]) ? (f = 0, b = b.slice(1)) : (f = Number(b[1]), b = b.slice(2));
          d = b[0];
          b = g[f];
          if (!b)return 0;
          b = b.getBBox();
          g = c(d, b)
        }
        return g
      }
    }
  }, pa.base);
  pa("multiseries", {
    series: function (b, c, d) {
      var g, e, h = c[f], p, l;
      c.legend.enabled = Boolean(a(b.chart.showlegend, 1));
      if (b.dataset && 0 < b.dataset.length) {
        this.categoryAdder(b, c);
        g = 0;
        for (e = b.dataset.length; g < e; g += 1)p = b.dataset[g], l = {
          hoverEffects: this.parseSeriesHoverOptions(b, c, p, d),
          visible: !a(p.initiallyhidden, 0),
          legendCosmetics: this.parseLegendOptions(b.chart, p),
          data: []
        }, this.isStacked || (l.numColumns = e), p = this.point(d, l, p, b.chart, c, h.oriCatTmp.length, g), p instanceof
        Array ? c.series = c.series.concat(p) : c.series.push(p);
        this.configureAxis(c, b);
        b.trendlines && !this.isLog && K(b.trendlines, c.yAxis, h, !1, this.isBar, void 0, this.inversed)
      }
    }, categoryAdder: function (c, d) {
      var g, p = 0, e = d[f], l = e.axisGridManager, k = c.chart, m = d.xAxis, n, e = e.x, I, H, O, s;
      if (c.categories && c.categories[0] && c.categories[0].category)for (c.categories[0].font && (d.xAxis.labels.style.fontFamily = c.categories[0].font), void 0 !== (g = a(c.categories[0].fontsize)) && (1 > g && (g = 1), d.xAxis.labels.style.fontSize = g + ra, h(d.xAxis.labels.style)),
                                                                           c.categories[0].fontcolor && (d.xAxis.labels.style.color = c.categories[0].fontcolor.split(Ba)[0].replace(/^\#?/, "#")), H = d[f].oriCatTmp, O = c.categories[0].category, g = 0; g < O.length; g += 1)O[g].vline ? l.addVline(m, O[g], p, d) : (I = a(O[g].showlabel, k.showlabels, 1), s = c.categories[0].category[g], n = C(q(s.label, s.name)), l.addXaxisCat(m, p, p, I ? n : b, {}, s, k), H[p] = q(C(s.tooltext), n), p += 1);
      e.catCount = p
    }, getPointStub: function (d, g, h, p, e, k, m, I, H, O) {
      var s = this.dataObj.chart, u = this.isDual, t = this.isXY, F = this.isMLAxis, ca = this.isStacked,
        A = this.isErrorChart, z;
      p = p[f];
      var ea, V, ma = null === g ? g : this.numberFormatter.dataLabels(g, m), B, la = l(C(c(d.tooltext, e.plottooltext, p.tooltext))), E = p.tooltipSepChar, G, K = {}, oa, ia, La, Q, P, Ja, J, Ga, Y;
      A && (ia = null === I ? I : this.numberFormatter.dataLabels(I, m), Ja = null === g ? b : this.numberFormatter.percentValue(I / g * 100), G = [1, 2, 3, 4, 5, 6, 7, 99, 100, 101, 102], g = {
        yaxisName: Q = C(u ? m ? s.syaxisname : s.pyaxisname : s.yaxisname),
        xaxisName: P = C(s.xaxisname),
        formattedValue: ma,
        label: h,
        errorDataValue: ia,
        errorPercentValue: Ja
      }, t ? (La = null === H ?
        H : this.numberFormatter.xAxis(H), J = null === O ? b : this.numberFormatter.percentValue(H / O * 100), G.push(103, 104, 105, 106, 107, 108, 109, 110), Y = c(d.horizontalerrorvalue, d.errorvalue), g.errorValue = Ga = c(d.verticalerrorvalue, d.errorvalue), O = l(C(c(d.verticalerrorplottooltext, d.errorplottooltext, e.verticalerrorplottooltext, e.errorplottooltext, s.verticalerrorplottooltext, s.errorplottooltext))), null !== I && (g.verticalErrorDataValue = ia, g.verticalErrorPercentValue = Ja, g.verticalErrorValue = Ga), null !== H && (g.horizontalErrorDataValue =
        La, g.horizontalErrorPercentValue = J, g.horizontalErrorValue = Y), oa = l(C(c(d.horizontalerrorplottooltext, d.errorplottooltext, e.horizontalerrorplottooltext, e.errorplottooltext, s.horizontalerrorplottooltext, s.errorplottooltext))), K._hErrortoolText = p.showTooltip ? void 0 !== oa ? n(oa, G, {
        yaxisName: Q = C(u ? m ? s.syaxisname : s.pyaxisname : s.yaxisname),
        xaxisName: P = C(s.xaxisname),
        formattedValue: ma,
        label: h,
        errorDataValue: La,
        errorPercentValue: J,
        errorValue: Y,
        verticalErrorDataValue: ia,
        verticalErrorPercentValue: Ja,
        verticalErrorValue: Ga,
        horizontalErrorDataValue: La,
        horizontalErrorPercentValue: J,
        horizontalErrorValue: Y
      }, d, s, e) : null === I ? !1 : La : !1) : (O = l(C(c(d.errorplottooltext, e.errorplottooltext, s.errorplottooltext))), g.errorValue = Ga = c(d.errorvalue)), K._errortoolText = p.showTooltip ? void 0 !== O ? n(O, G, g, d, s, e) : null === I ? !1 : ia : !1);
      p.showTooltip ? void 0 !== la ? (ca = [4, 5, 6, 7], m = {
        yaxisName: Q || C(u ? m ? s.syaxisname : s.pyaxisname : F ? e._yAxisName : s.yaxisname),
        xaxisName: P || C(s.xaxisname)
      }, t ? (ca.push(8, 9, 10, 11), m.yDataValue = ma, m.xDataValue = h, A && (ca.push(103,
        104, 105, 106, 107, 108, 109, 110), null !== I && (m.verticalErrorDataValue = ia, m.verticalErrorPercentValue = Ja, m.verticalErrorValue = Ga), null !== H && (m.horizontalErrorDataValue = La, m.horizontalErrorPercentValue = J, m.horizontalErrorValue = Y))) : (ca.push(1, 2, 3), m.formattedValue = ma, m.label = h, A && (ca.push(99, 100, 101, 102), m.errorValue = Ga, null !== I && (m.errorDataValue = ia, m.errorPercentValue = Ja))), e = n(la, ca, m, d, s, e)) : null === ma ? e = !1 : (p.seriesNameInToolTip && (B = q(e && e.seriesname)), e = B ? B + E : b, e += h ? h + E : b, p.showPercentInToolTip && ca ?
        (V = !0, e += "$percentValue") : e += ma) : e = !1;
      a(d.showvalue, k) ? void 0 !== l(d.displayvalue) ? z = C(d.displayvalue) : p.showPercentValues ? ea = !0 : z = ma : z = b;
      K.link = c(d.link);
      K.displayValue = z;
      K.categoryLabel = h;
      K.toolText = e;
      K.showPercentValues = ea;
      K.showPercentInToolTip = V;
      return K
    }
  }, pa.singleseries);
  pa("xybase", {
    hideRLine: function () {
      var a = this.chart.series[this.index + 1];
      a && a.hide && a.hide()
    }, showRLine: function () {
      var a = this.chart.series[this.index + 1];
      a && a.show && a.show()
    }, getRegressionLineSeries: function (a, b, c) {
      var d, e, f,
        g;
      g = a.sumXY;
      var h = a.sumX, p = a.sumY;
      e = a.xValues;
      f = a.sumXsqure;
      d = a.yValues;
      a = a.sumYsqure;
      b ? (e.sort(Cb), d = e[0], e = e[e.length - 1], g = (c * g - h * p) / (c * f - aa(h, 2)), f = !isNaN(g) && isFinite(g) ? g * (d - h / c) + p / c : p / c, c = !isNaN(g) && isFinite(g) ? g * (e - h / c) + p / c : p / c, c = [{
        x: d,
        y: f
      }, {
        x: e,
        y: c
      }]) : (d.sort(Cb), f = d[0], d = d[d.length - 1], g = (c * g - h * p) / (c * a - aa(p, 2)), e = isNaN(g) ? h / c : g * (f - p / c) + h / c, c = isNaN(g) ? h / c : g * (d - p / c) + h / c, c = [{
        x: e,
        y: f
      }, {x: c, y: d}]);
      return c
    }, pointValueWatcher: function (a, b, c, d) {
      var e = a[f];
      null !== b && (a = e[0], a.max = a.max > b ? a.max : b,
        a.min = a.min < b ? a.min : b);
      null !== c && (a = e.x, a.max = a.max > c ? a.max : c, a.min = a.min < c ? a.min : c);
      d && (c = c || 0, b = b || 0, d.sumX += c, d.sumY += b, d.sumXY += c * b, d.sumXsqure += aa(c, 2), d.xValues.push(c), d.sumYsqure += aa(b, 2), d.yValues.push(b))
    }
  }, pa.multiseries);
  pa("scrollbase", {
    postSeriesAddition: function () {
      var b = this.hcJSON, d = b.xAxis.scroll, g = b[f], h = g.width, e = g.x.catCount, p = this.dataObj.chart, l = this.colorManager, k, m, n, I, H, s;
      g.isScroll = !0;
      b.chart.hasScroll = !0;
      if (this.isStacked)k = 1; else {
        m = k = 0;
        I = b.series;
        s = b.chart.defaultSeriesType;
        for (n = I.length; m < n; m++)H = c(I[m].type, s), "column" === H && (k += 1);
        1 > k && (k = 1)
      }
      e *= k;
      h = a(p.numvisibleplot, Ka(h / this.avgScrollPointWidth));
      d && 2 <= h && h < e && (d.enabled = !0, d.vxLength = h / k, d.startPercent = ba(1, Z(0, parseFloat(p.scrolltoend) || 0)), d.padding = a(p.scrollpadding, 0), d.height = a(p.scrollheight, 16), d.showButtons = !!a(p.scrollshowbuttons, 1), d.buttonPadding = a(p.scrollbtnpadding, 0), d.color = T(c(p.scrollcolor, l.getColor("altHGridColor"))), g.marginBottomExtraSpace += d.padding + d.height);
      if (oa || a(p.enabletouchscroll,
          0))b.chart.zoomType = "x", b.chart.nativeZoom = !1, b.chart.selectionMarkerFill = "rgba(255,255,255,0)", (b.callbacks || (b.callbacks = [])).push(function (a) {
        z(a, "selectionstart selectiondrag", pa.scrollbase.performTouchScroll, {})
      })
    }, performTouchScroll: function (a) {
      var b = this.xAxis[0].scroller, c = b.config, c = c.trackLength / (c.width / c.scrollRatio) * (a.chartX || 1);
      !0 !== a.isOutsidePlot && la(b.elements.anchor.element, "selectionstart" === a.type ? "dragstart" : "drag", {
        pageX: -c,
        pageY: -a.chartY
      })
    }
  }, pa.multiseries);
  pa("logbase", {
    isLog: !0,
    isValueAbs: !0, configureAxis: function (d, h) {
      var p = d[f], l = p.axisGridManager, e = this.numberFormatter, k = d.series, m = d.xAxis, n = d.yAxis[0], I = p[0], H = h.chart, s = !a(H.showyaxislimits, H.showlimits, H.showyaxisvalues, 1), O = !a(H.showdivlinevalues, H.showyaxisvalues, 1), q = a(H.base, H.logbase, 10), F = a(H.yaxismaxvalue), u = a(H.yaxisminvalue), ca = this.colorManager, t = 1 === a(H.showminordivlinevalues), A = c(H.minordivlinecolor, n.gridLineColor, ca.getColor("divLineColor")), z = a(H.minordivlinealpha, H.divlinealpha, ca.getColor("divLineAlpha")),
        ca = [n, void 0, void 0, a(H.divlinethickness, 2), n.gridLineDashStyle, n.gridLineColor, 2], A = [n, void 0, void 0, a(H.minordivlinethickness, 1), n.gridLineDashStyle, fa(c(H.minordivlinecolor, A), a(H.minordivlinealpha, z / 2)), 2], z = t || z && A[3], ea = a(H.showaxislimitgridlines, this.showAxisLimitGridLines), V = a(ea, this.is3D || d.chart.plotBorderWidth ? 0 : 1), B, la;
      0 >= q && (q = 10);
      0 >= F && (F = void 0);
      0 >= u && (u = void 0);
      F = this.getLogAxisLimits(I.max || q, I.min || 1, F, u, q, z ? H.numminordivlines : 0);
      m.title.text = C(H.xaxisname);
      m.showLine = a(H.showxaxisline,
        H.showaxislines, 0);
      m.lineThickness = a(H.xaxislinethickness, H.axislinethickness, 1);
      m.lineAlpha = a(H.xaxislinealpha, H.axislinealpha, 100);
      m.lineColor = fa(c(H.xaxislinecolor, H.axislinecolor, "000"));
      g(n, {
        title: {text: C(H.yaxisname)},
        labels: {enabled: !1},
        gridLineWidth: 0,
        alternateGridColor: xa,
        reversed: "1" === H.invertyaxis,
        max: ma(F.Max, q),
        min: ma(F.Min, q),
        showLine: a(H.showyaxisline, H.showaxislines, 0),
        lineThickness: a(H.yaxislinethickness, H.axislinethickness, 1),
        lineAlpha: a(H.yaxislinealpha, H.axislinealpha, 100),
        lineColor: fa(c(H.yaxislinecolor,
          H.axislinecolor, "000"))
      });
      for (H = k.length; H--;)if (u = k[H])for (u.threshold = n.min, la = (u = u.data) && u.length || 0; la--;)B = u[la], B.y = ma(B.y, q);
      delete I.max;
      delete I.min;
      I.isLog = !0;
      n.reversed && 0 <= n.min && (d.plotOptions.series.threshold = n.max);
      h.trendlines && K(h.trendlines, [{
        max: F.Max,
        min: F.Min,
        plotLines: n.plotLines,
        plotBands: n.plotBands,
        title: n.title
      }], p);
      for (H = n.plotLines.length; H--;)B = n.plotLines[H], B.value && (B.value = ma(B.value, q)), B.from && (B.from = ma(B.from, q)), B.to && (B.to = ma(B.to, q));
      for (H = n.plotBands.length; H--;)B =
        n.plotBands[H], B.from && (B.from = ma(B.from, q)), B.to && (B.to = ma(B.to, q));
      for (H = F.divArr.length; H--;) {
        B = F.divArr[H];
        if (B.ismajor)ca[1] = ma(B.value, q), ca[2] = e.yAxis(B.value), l.addAxisGridLine.apply(l, ca); else if (z || B.isextreme)A[1] = ma(B.value, q), A[2] = t || B.isextreme ? e.yAxis(B.value) : b, l.addAxisGridLine.apply(l, A);
        u = n.plotLines[n.plotLines.length - 1];
        B.isextreme ? (u.width = ea || V && (!B.isMin || !m.showLine) ? u.width : .1, s && (u.label.text = b)) : O && u.label && (u.label.text = b)
      }
    }, getLogAxisLimits: function (a, b, c, d, e, f) {
      var g =
        function (a) {
          return null === a || void 0 === a || "" === a || isNaN(a) ? !1 : !0
        }, h = 0, p = [], l, k, m, n, I, H, s, O;
      g(c) && Number(c) >= a ? a = Number(c) : (c = 1 < e ? Aa($(a) / $(e)) : Ka($(a) / $(e)), a = aa(e, c), k = c);
      k || (k = 1 < e ? Aa($(a) / $(e)) : Ka($(a) / $(e)));
      g(d) && Number(d) <= b ? b = Number(d) : (c = 1 < e ? Ka($(b) / $(e)) : Aa($(b) / $(e)), b = aa(e, c), l = c);
      l || (l = 1 < e ? Ka($(b) / $(e)) : Aa($(b) / $(e)));
      d = Number(String($(e) / $(10)));
      f = Number(f) || (Ka(d) == d ? 8 : 4);
      1 < e ? (m = k, n = l) : 0 < e && 1 > e && (m = l, n = k);
      d = k;
      for (l = m; l >= n; --l)if (m = aa(e, d), b <= m && a >= m && (p[h++] = {value: m, ismajor: !0}), l != n) {
        k =
          1 < e ? -1 : 1;
        m = aa(e, d) - aa(e, d + k);
        c = m / (f + 1);
        for (g = 1; g <= f; ++g)m = aa(e, d + k) + c * g, b <= m && a >= m && (p[h++] = {value: m, ismajor: !1});
        1 < e ? d-- : d++
      }
      for (s in p)for (O in p[s])"value" == O && (I || (I = p[s][O] == b && (p[s].isextreme = p[s].isMin = !0)), H || (H = p[s][O] == a && (p[s].isextreme = p[s].isMax = !0)));
      I || (p[h++] = {value: b, ismajor: !0, isMin: !0, isextreme: !0});
      H || (p[h] = {value: a, ismajor: !0, isMax: !0, isextreme: !0});
      return {Max: a, Min: b, divArr: p}
    }, pointValueWatcher: function (b, c, d) {
      b = b[f];
      d = a(d, 0);
      0 < c && (b[d] || (b[d] = {}), d = b[d], d.max = d.max > c ? d.max :
        c, d.min = d.min < c ? d.min : c)
    }
  }, pa.mslinebase);
  va = pa.singleseries;
  Ya = pa.multiseries;
  pa("column2dbase", {
    point: function (d, h, p, l, e) {
      var k = p.length, m = e[f], n = m.axisGridManager, I = e.xAxis, m = m.x, H = this.colorManager, O = /3d$/.test(e.chart.defaultSeriesType), u = this.isBar, ca = /^spark/i.test(d);
      d = c(l.showplotborder, ca || O ? Ca : db) === db ? O ? 1 : a(l.plotborderthickness, 1) : 0;
      var F = e.chart.useRoundEdges, t = a(l.plotborderalpha, l.plotfillalpha, 100), A = c(l.plotbordercolor, H.getColor("plotBorderColor")).split(Ba)[0], ca = ca ? "" : a(l.useplotgradientcolor,
        1) ? S(l.plotgradientcolor, H.getColor("plotGradientColor")) : b, z = 0, ea = Boolean(a(l.use3dlighting, 1)), B = e[f].numberFormatter, V, ma = a(l.plotborderdashed, 0), la = a(l.plotborderdashlen, 5), E = a(l.plotborderdashgap, 4), G, K, oa, ia, La, Ja, Q, P, J, Ga, Y, wa, L, Ia;
      for (oa = 0; oa < k; oa += 1)L = p[oa], L.vline ? n.addVline(I, L, z, e) : (K = B.getCleanValue(L.value), V = a(L.showlabel, l.showlabels, 1), ia = C(q(L.label, L.name)), G = c(L.color, H.getPlotColor()), La = c(L.alpha, l.plotfillalpha, Ta), Ja = c(L.ratio, l.plotfillratio), Q = c(360 - l.plotfillangle, u ? 180 :
        90), P = c(L.alpha, t), J = a(L.dashed, ma), Ga = c(L.dashgap, E), Y = c(L.dashlen, la), n.addXaxisCat(I, z, z, V ? ia : b, L, {}, l, G), z += 1, 0 > K && (Q = u ? 180 - Q : 360 - Q), Ia = {opacity: La / 100}, wa = R(G + Ba + ca.replace(/,+?$/, ""), La, Ja, Q, F, A, P + b, u, O), V = J ? s(Y, Ga, d) : "none", G = this.pointHoverOptions(L, h, {
        plotType: "column",
        is3d: O,
        isBar: u,
        use3DLighting: ea,
        isRoundEdged: F,
        color: G,
        gradientColor: ca,
        alpha: La,
        ratio: Ja,
        angle: Q,
        borderWidth: d,
        borderColor: A,
        borderAlpha: P,
        borderDashed: J,
        borderDashGap: Ga,
        borderDashLen: Y,
        shadow: Ia
      }), h.data.push(g(this.getPointStub(L,
        K, ia, e), {
        y: K,
        shadow: Ia,
        color: wa[0],
        borderColor: wa[1],
        borderWidth: d,
        use3DLighting: ea,
        dashStyle: V,
        tooltipConstraint: this.tooltipConstraint,
        hoverEffects: G.enabled && G.options,
        rolloverProperties: G.enabled && G.rolloverOptions
      })), this.pointValueWatcher(e, K));
      m.catCount = z;
      return h
    }, defaultSeriesType: "column"
  }, va);
  pa("linebase", {
    defaultSeriesType: "line", hasVDivLine: !0, defaultPlotShadow: 1, point: function (d, h, p, l, e) {
      var k, m, n, I, H, O, u, ca, F, t, A, z, ea, V, B, ma, la, E, G, K, oa, ia, La, Q, Ja, P;
      d = e.chart;
      var J = p.length, Ga = e.xAxis;
      k = e[f];
      var Y = this.colorManager, L, wa = k.axisGridManager, Ia = 0, Oa = k.x, hb = e[f].numberFormatter, R, ab, S;
      V = T(c(l.linecolor, l.palettecolors, Y.getColor("plotFillColor")));
      B = c(l.linealpha, Ta);
      A = a(l.linethickness, this.lineThickness, 4);
      z = Boolean(a(l.linedashed, 0));
      u = a(l.linedashlen, 5);
      ca = a(l.linedashgap, 4);
      Ja = a(l.anchorshadow, 0);
      h.color = {FCcolor: {color: V, alpha: B}};
      h.lineWidth = A;
      h.anchorShadow = Ja;
      h.step = c(this.stepLine, h.step);
      h.drawVerticalJoins = Boolean(a(h.drawVerticalJoins, l.drawverticaljoins, 1));
      h.useForwardSteps =
        Boolean(a(h.useForwardSteps, l.useforwardsteps, 1));
      ea = a(l.drawanchors, l.showanchors);
      for (m = 0; m < J; m += 1)I = p[m], I.vline ? wa.addVline(Ga, I, Ia, e) : (k = hb.getCleanValue(I.value), H = a(I.showlabel, l.showlabels, 1), n = C(q(I.label, I.name)), wa.addXaxisCat(Ga, Ia, Ia, H ? n : b, I, {}, l), Ia += 1, F = T(c(I.color, V)), t = a(I.alpha, B), H = a(I.dashed, z) ? s(u, ca, A) : "none", O = {opacity: t / 100}, la = a(I.anchorsides, l.anchorsides, 0), Q = a(I.anchorstartangle, l.anchorstartangle, 90), K = a(I.anchorradius, l.anchorradius, this.anchorRadius, 3), G = T(c(I.anchorbordercolor,
        l.anchorbordercolor, V)), E = a(I.anchorborderthickness, l.anchorborderthickness, this.anchorBorderThickness, 1), oa = T(c(I.anchorbgcolor, l.anchorbgcolor, Y.getColor("anchorBgColor"))), ia = c(I.anchoralpha, l.anchoralpha, Ta), La = c(I.anchorbgalpha, l.anchorbgalpha, ia), R = c(I.anchorimageurl, l.anchorimageurl), ab = c(I.anchorimagescale, l.anchorimagescale, 100), S = c(I.anchorimagealpha, l.anchorimagealpha, 100), ma = void 0 === ea ? 0 !== t : !!ea, P = Boolean(a(I.anchorshadow, Ja, 0)), L = this.pointHoverOptions(I, h, {
        plotType: "anchor",
        anchorBgColor: oa,
        anchorAlpha: ia,
        anchorBgAlpha: La,
        anchorAngle: Q,
        anchorBorderThickness: E,
        anchorBorderColor: G,
        anchorBorderAlpha: ia,
        anchorSides: la,
        anchorRadius: K,
        imageUrl: R,
        imageScale: ab,
        imageAlpha: S,
        shadow: O
      }), h.data.push(g(this.getPointStub(I, k, n, e), {
        y: k,
        color: {FCcolor: {color: F, alpha: t}},
        shadow: O,
        dashStyle: H,
        valuePosition: c(I.valueposition, d.valuePosition),
        marker: {
          enabled: !!ma,
          shadow: P && {opacity: ia / 100},
          fillColor: {FCcolor: {color: oa, alpha: La * ia / 100 + b}},
          lineColor: {FCcolor: {color: G, alpha: ia}},
          lineWidth: E,
          radius: K,
          startAngle: Q,
          symbol: bb(la),
          imageUrl: R,
          imageScale: ab,
          imageAlpha: S
        },
        tooltipConstraint: this.tooltipConstraint,
        hoverEffects: L.enabled && L.options,
        rolloverProperties: L.enabled && L.rolloverOptions
      })), this.pointValueWatcher(e, k));
      Oa.catCount = Ia;
      return h
    }, defaultZeroPlaneHighlighted: !1
  }, va);
  pa("area2dbase", {
    defaultSeriesType: "area", hasVDivLine: !0, parseAnchorVisibility: function (b, d, f) {
      var g = a(b.drawanchors, d.drawanchors, d.showanchors);
      return X(g) ? g : X(c(b.anchorsides, d.anchorsides, b.anchorstartangle, d.anchorstartangle, b.anchorradius,
        d.anchorradius, b.anchorbordercolor, d.anchorbordercolor, b.anchorborderthickness, d.anchorborderthickness, b.anchorbgcolor, d.anchorbgcolor, b.anchoralpha, d.anchoralpha, b.anchorbgalpha, d.anchorbgalpha, b.anchorshadow, d.anchorshadow)) || f
    }, point: function (d, h, p, k, e) {
      d = e.chart;
      var m = p.length, n = e.xAxis, I = e[f], H = I.axisGridManager, I = I.x, O = e[f].numberFormatter, u = this.colorManager, ca = 0, t, F, A, z, ea, V, B, ma, la, E, G, K, oa, ia, La, Q, Ja, P, J, Ga, Y, Ia, wa, Oa, hb, R, ab, Bb, U;
      ea = c(k.plotfillcolor, k.areabgcolor, l(k.palettecolors) ? u.getPlotColor(0) :
        u.getColor("plotFillColor")).split(/\s*\,\s*/)[0];
      Y = Ba + (a(k.useplotgradientcolor, 1) ? S(k.plotgradientcolor, u.getColor("plotGradientColor")) : b);
      V = c(k.plotfillalpha, k.areaalpha, this.isStacked ? Ta : "90");
      B = a(k.plotfillangle, 270);
      ma = c(k.plotbordercolor, k.areabordercolor, l(k.palettecolors) ? u.getPlotColor(0) : u.getColor("plotBorderColor")).split(/\s*\,\s*/)[0];
      la = k.showplotborder == Ca ? Ca : c(k.plotborderalpha, k.plotfillalpha, k.areaalpha, Ta);
      t = a(k.plotborderangle, 270);
      F = Boolean(a(k.plotborderdashed, 0));
      A = a(k.plotborderdashlen,
        5);
      oa = a(k.plotborderdashgap, 4);
      Ja = a(k.plotborderthickness, k.areaborderthickness, 1);
      Ia = h.fillColor = {FCcolor: {color: ea + Y.replace(/,+?$/, ""), alpha: V, ratio: Xa, angle: B}};
      h.lineWidth = Ja;
      h.dashStyle = F ? s(A, oa, Ja) : "none";
      h.lineColor = {FCcolor: {color: ma, alpha: la, ratio: Ta, angle: t}};
      h.step = c(this.stepLine, h.step);
      h.drawVerticalJoins = Boolean(a(h.drawVerticalJoins, k.drawverticaljoins, 1));
      h.useForwardSteps = Boolean(a(h.useForwardSteps, k.useforwardsteps, 1));
      Ja = Boolean(a(k.drawanchors, k.showanchors, 1));
      hb = Boolean(this.parseAnchorVisibility({},
        k, 0));
      h.anchorShadow = wa = a(k.anchorshadow, 0);
      for (F = 0; F < m; F += 1)oa = p[F], oa.vline ? H.addVline(n, oa, ca, e) : (t = O.getCleanValue(oa.value), z = a(oa.showlabel, k.showlabels, 1), A = C(q(oa.label, oa.name)), H.addXaxisCat(n, ca, ca, z ? A : b, oa, {}, k), ca += 1, z = a(oa.anchorsides, k.anchorsides, 0), K = a(oa.anchorstartangle, k.anchorstartangle, 90), E = a(oa.anchorradius, k.anchorradius, 3), G = T(c(oa.anchorbordercolor, k.anchorbordercolor, ma)), P = a(oa.anchorborderthickness, k.anchorborderthickness, 1), ia = T(c(oa.anchorbgcolor, k.anchorbgcolor, u.getColor("anchorBgColor"))),
        La = c(oa.anchoralpha, k.anchoralpha, this.anchorAlpha, hb ? Ta : 0), Q = c(oa.anchorbgalpha, k.anchorbgalpha, La), Oa = Boolean(a(oa.anchorshadow, wa, 0)), J = l(oa.color), Ga = a(oa.alpha), J = void 0 !== J || void 0 !== Ga ? {
        FCcolor: {
          color: J ? T(J) + Y : ea,
          alpha: void 0 === Ga ? L(Ga) + b : V,
          ratio: Xa,
          angle: B
        }
      } : Ia, R = c(oa.anchorimageurl, k.anchorimageurl), ab = c(oa.anchorimagescale, k.anchorimagescale, 100), Bb = c(oa.anchorimagealpha, k.anchorimagealpha, 100), Ga = {
        opacity: Z(Ga, la) / 100,
        inverted: !0
      }, U = this.pointHoverOptions(oa, h, {
        plotType: "anchor",
        anchorBgColor: ia,
        anchorAlpha: La,
        anchorBgAlpha: Q,
        anchorAngle: K,
        anchorBorderThickness: P,
        anchorBorderColor: G,
        anchorBorderAlpha: La,
        anchorSides: z,
        anchorRadius: E,
        imageUrl: R,
        imageScale: ab,
        imageAlpha: Bb,
        shadow: Ga
      }), h.data.push(g(this.getPointStub(oa, t, A, e), {
        y: t,
        shadow: Ga,
        color: J,
        valuePosition: c(oa.valueposition, d.valuePosition),
        marker: {
          enabled: Ja,
          shadow: Oa && {opacity: La / 100},
          fillColor: {FCcolor: {color: ia, alpha: Q * La / 100 + b}},
          lineColor: {FCcolor: {color: G, alpha: La}},
          lineWidth: P,
          radius: E,
          symbol: bb(z),
          startAngle: K,
          imageUrl: R,
          imageScale: ab,
          imageAlpha: Bb
        },
        tooltipConstraint: this.tooltipConstraint,
        previousY: this.pointValueWatcher(e, t),
        hoverEffects: U.enabled && U.options,
        rolloverProperties: U.enabled && U.rolloverOptions
      })));
      I.catCount = ca;
      return h
    }
  }, va);
  pa("mscolumn2dbase", {
    point: function (d, g, h, p, e, k, m, n, I) {
      d = a(p.ignoreemptydatasets, 0);
      var H = !1, O = h.data || [], s = e[f], q = c(g.type, this.defaultSeriesType), u = c(g.isStacked, e.plotOptions[q] && e.plotOptions[q].stacking), ca = c(this.isValueAbs, s.isValueAbs, !1), t = a(g.yAxis, 0), A = e[f].numberFormatter, z = this.colorManager,
        ea = z.getPlotColor(), C, V = e._FCconf.isBar, B = g.hoverEffects;
      u || (g.columnPosition = a(I, n, m));
      g.name = l(h.seriesname);
      if (0 === a(h.includeinlegend) || void 0 === g.name)g.showInLegend = !1;
      g.color = c(h.color, ea).split(Ba)[0].replace(/^#?/g, "#");
      m = /3d$/.test(e.chart.defaultSeriesType);
      I = c(360 - p.plotfillangle, V ? 180 : 90);
      0 > C && (I = 360 - I);
      h = g._dataParser = mb.column(e, {
        seriesname: g.name,
        plottooltext: h.plottooltext,
        color: c(h.color, ea),
        alpha: c(h.alpha, p.plotfillalpha, Ta),
        plotgradientcolor: a(p.useplotgradientcolor, 1) ? S(p.plotgradientcolor,
          z.getColor("plotGradientColor")) : b,
        ratio: c(h.ratio, p.plotfillratio),
        fillAangle: I,
        isRoundEdges: e.chart.useRoundEdges,
        plotBorderColor: c(p.plotbordercolor, m ? Va : z.getColor("plotBorderColor")).split(Ba)[0],
        plotBorderAlpha: p.showplotborder == Ca || m && p.showplotborder != db ? Ca : c(p.plotborderalpha, h.alpha, p.plotfillalpha, Ta),
        isBar: this.isBar,
        is3d: m,
        dashed: a(h.dashed, p.plotborderdashed, 0),
        dashLen: a(h.dashlen, p.plotborderdashlen, 5),
        dashGap: a(h.dashgap, p.plotborderdashgap, 4),
        borderWidth: a(p.plotborderthickness, db),
        showValues: a(h.showvalues, s.showValues),
        yAxis: t,
        use3DLighting: a(p.use3dlighting, 1),
        _sourceDataset: h,
        hoverEffects: B
      }, this);
      for (p = 0; p < k; p += 1)(s = O[p]) ? (C = A.getCleanValue(s.value, ca), null === C ? g.data.push({y: null}) : (H = !0, s = h(s, p, C), g.data.push(s), s.previousY = this.pointValueWatcher(e, C, t, u, p, n, q))) : g.data.push({y: null});
      !d || H || this.realtimeEnabled || (g.showInLegend = !1);
      return g
    }, defaultSeriesType: "column"
  }, Ya);
  pa("mslinebase", {
      hasVDivLine: !0, point: function (d, g, h, p, e, k) {
        d = a(p.ignoreemptydatasets, 0);
        var m =
          !1, n = this.colorManager, I, H;
        I = e.chart;
        var s = h.data || [];
        H = e[f];
        var O = c(g.type, this.defaultSeriesType), q = c(g.isStacked, e.plotOptions[O] && e.plotOptions[O].stacking), u = c(this.isValueAbs, H.isValueAbs, !1), ca = a(g.yAxis, 0), t = this.numberFormatter, A = T(c(h.color, p.linecolor, n.getPlotColor())), z = a(h.alpha, p.linealpha, Ta), ea = a(p.showshadow, this.defaultPlotShadow, 1), C = a(h.drawanchors, h.showanchors, p.drawanchors, p.showanchors), V = a(h.anchorsides, p.anchorsides, 0), B = a(h.anchorstartangle, p.anchorstartangle, 90), ma = a(h.anchorradius,
          p.anchorradius, 3), la = T(c(h.anchorbordercolor, p.anchorbordercolor, A)), E = a(h.anchorborderthickness, p.anchorborderthickness, 1), n = T(c(h.anchorbgcolor, p.anchorbgcolor, n.getColor("anchorBgColor"))), G = c(h.anchoralpha, p.anchoralpha, Ta), oa = c(h.anchorbgalpha, p.anchorbgalpha, G), K = G && c(h.anchorshadow, p.anchorshadow, 0), ia = g.hoverEffects;
        g.name = l(h.seriesname);
        if (0 === a(h.includeinlegend) || void 0 === g.name || 0 === z && 1 !== C)g.showInLegend = !1;
        g.marker = {
          enabled: Boolean(a(C, 1)), fillColor: {
            FCcolor: {
              color: n, alpha: oa * G / 100 +
              b
            }
          }, lineColor: {FCcolor: {color: la, alpha: G + b}}, lineWidth: E, radius: ma, symbol: bb(V), startAngle: B
        };
        g.color = {FCcolor: {color: A, alpha: z}};
        g.shadow = ea ? {opacity: ea ? z / 100 : 0} : !1;
        g.anchorShadow = K;
        g.step = c(this.stepLine, g.step);
        g.drawVerticalJoins = Boolean(a(g.drawVerticalJoins, p.drawverticaljoins, 1));
        g.useForwardSteps = Boolean(a(g.useForwardSteps, p.useforwardsteps, 1));
        g.lineWidth = a(h.linethickness, p.linethickness, 2);
        I = g._dataParser = mb.line(e, {
          seriesname: g.name,
          plottooltext: h.plottooltext,
          lineAlpha: z,
          anchorAlpha: G,
          showValues: a(h.showvalues, H.showValues),
          yAxis: ca,
          lineDashed: Boolean(a(h.dashed, p.linedashed, 0)),
          lineDashLen: a(h.linedashlen, p.linedashlen, 5),
          lineDashGap: a(h.linedashgap, p.linedashgap, 4),
          lineThickness: g.lineWidth,
          lineColor: A,
          valuePosition: c(h.valueposition, I.valuePosition),
          drawAnchors: C,
          anchorBgColor: n,
          anchorBgAlpha: oa,
          anchorBorderColor: la,
          anchorBorderThickness: E,
          anchorRadius: ma,
          anchorSides: V,
          anchorAngle: B,
          anchorShadow: g.anchorShadow,
          anchorStartAngle: a(h.anchorstartangle, p.anchorstartangle),
          _sourceDataset: h,
          hoverEffects: ia,
          imageUrl: c(h.anchorimageurl, p.anchorimageurl),
          imageScale: c(h.anchorimagescale, p.anchorimagescale, 100),
          imageAlpha: c(h.anchorimagealpha, p.anchorimagealpha, 100)
        }, this);
        for (p = 0; p < k; p += 1)(H = s[p]) ? (h = t.getCleanValue(H.value, u), null === h ? g.data.push({y: null}) : (m = !0, H = I(H, p, h), g.data.push(H), H.previousY = this.pointValueWatcher(e, h, ca, q, p, 0, O))) : g.data.push({y: null});
        !d || m || this.realtimeEnabled || (g.showInLegend = !1);
        return g
      }, defaultSeriesType: "line", defaultPlotShadow: 1, defaultZeroPlaneHighlighted: !1
    },
    Ya);
  pa("msareabase", {
    hasVDivLine: !0, point: function (d, g, h, p, e, l) {
      d = a(p.ignoreemptydatasets, 0);
      var k = !1, m = e.chart, n = h.data || [], I = e[f], H = c(g.type, this.defaultSeriesType), O = c(g.isStacked, e.plotOptions[H] && e.plotOptions[H].stacking), q = c(this.isValueAbs, I.isValueAbs, !1), u = a(g.yAxis, 0), ca = e[f].numberFormatter, t = this.colorManager, A = t.getPlotColor(), z = c(h.color, p.plotfillcolor, A).split(Ba)[0].replace(/^#?/g, "#").split(Ba)[0], ea = c(h.alpha, p.plotfillalpha, p.areaalpha, this.areaAlpha, 70), C = a(p.plotfillangle, 270),
        A = c(h.plotbordercolor, p.plotbordercolor, p.areabordercolor, this.isRadar ? A : "666666").split(Ba)[0], V = c(h.showplotborder, p.showplotborder) == Ca ? Ca : c(h.plotborderalpha, p.plotborderalpha, h.alpha, p.plotfillalpha, p.areaalpha, "95"), B = a(p.plotborderangle, 270), ma = a(h.anchorsides, p.anchorsides, 0), la = a(h.anchorstartangle, p.anchorstartangle, 90), G = a(h.anchorradius, p.anchorradius, 3), E = T(c(h.anchorbordercolor, p.anchorbordercolor, z)), oa = a(h.anchorborderthickness, p.anchorborderthickness, 1), K = T(c(h.anchorbgcolor, p.anchorbgcolor,
          t.getColor("anchorBgColor"))), ia = Boolean(pa.area2dbase.parseAnchorVisibility(h, p, 0)), ia = a(h.anchoralpha, p.anchoralpha, this.anchorAlpha, ia ? Ta : 0), La = a(h.anchorbgalpha, p.anchorbgalpha, ia), Ja = ia && c(h.anchorshadow, p.anchorshadow, 0), Q = g.hoverEffects;
      this.isRadar || (z += Ba + (a(p.useplotgradientcolor, 1) ? S(p.plotgradientcolor, t.getColor("plotGradientColor")) : b), z = z.replace(/,+?$/, ""));
      g.step = c(this.stepLine, g.step);
      g.drawVerticalJoins = Boolean(a(g.drawVerticalJoins, p.drawverticaljoins, 1));
      g.useForwardSteps = Boolean(a(g.useForwardSteps,
        p.useforwardsteps, 1));
      g.name = c(h.seriesname);
      if (0 === a(h.includeinlegend) || void 0 === g.name)g.showInLegend = !1;
      g.fillColor = {FCcolor: {color: z, alpha: ea, ratio: Xa, angle: C}};
      g.color = z;
      g.shadow = {opacity: a(p.showshadow, 1) ? V / 100 : 0};
      g.anchorShadow = Ja;
      g.lineColor = {FCcolor: {color: A, alpha: V, ratio: Ta, angle: B}};
      g.lineWidth = c(h.plotborderthickness, p.plotborderthickness, 1);
      g.dashStyle = Boolean(a(h.dashed, p.plotborderdashed, 0)) ? s(a(h.dashlen, p.plotborderdashlen, 5), a(h.dashgap, p.plotborderdashgap, 4), g.lineWidth) : "none";
      g.marker = {
        fillColor: {FCcolor: {color: K, alpha: La * ia / 100 + b}},
        lineColor: {FCcolor: {color: E, alpha: ia + b}},
        lineWidth: oa,
        radius: G,
        symbol: bb(ma),
        startAngle: la
      };
      h = g._dataParser = mb.area(e, {
        seriesname: g.name,
        plottooltext: h.plottooltext,
        lineAlpha: V,
        anchorAlpha: ia,
        showValues: a(h.showvalues, I.showValues),
        yAxis: u,
        fillColor: z,
        fillAlpha: ea,
        valuePosition: c(h.valueposition, m.valuePosition),
        drawAnchors: Boolean(a(h.drawanchors, p.drawanchors, p.showanchors, 1)),
        anchorBgColor: K,
        anchorBgAlpha: La,
        anchorBorderColor: E,
        anchorBorderThickness: oa,
        anchorRadius: G,
        anchorSides: ma,
        anchorAngle: la,
        anchorShadow: g.anchorShadow,
        getLink: this.linkClickFN,
        anchorStartAngle: a(h.anchorstartangle, p.anchorstartangle),
        _sourceDataset: h,
        hoverEffects: Q,
        imageUrl: c(h.anchorimageurl, p.anchorimageurl),
        imageScale: c(h.anchorimagescale, p.anchorimagescale, 100),
        imageAlpha: c(h.anchorimagealpha, p.anchorimagealpha, 100)
      }, this);
      for (m = 0; m < l; m += 1)(I = n[m]) ? (p = I ? ca.getCleanValue(I.value, q) : null, null === p ? g.data.push({y: null}) : (k = !0, I = h(I, m, p), g.data.push(I), I.previousY = this.pointValueWatcher(e,
        p, u, O, m, 0, H))) : g.data.push({y: null});
      !d || k || this.realtimeEnabled || (g.showInLegend = !1);
      return g
    }, defaultSeriesType: "area", defaultPlotShadow: 0
  }, Ya);
  pa("scatterbase", {
    showValues: 0,
    defaultPlotShadow: 0,
    rendererId: "cartesian",
    defaultSeriesType: "scatter",
    canvasPaddingModifiers: ["anchorlabel"],
    point: function (d, g, h, p, e, k, m) {
      d = a(p.ignoreemptydatasets, 0);
      var I = this.colorManager, n = I.getPlotColor(), H, O, q, u, ca, t, A, z, ea, C, V, B, ma, la, G, E, oa, K, ia;
      k = !1;
      var La, Ja;
      ca = a(h.drawline, p.drawlines, 0);
      t = a(h.drawprogressioncurve,
        0);
      u = h.data || [];
      var Q, P, J, Ga, Y, Ia = a(h.showvalues, e[f].showValues), wa = this.numberFormatter, Oa, hb = g._showRegression = a(h.showregressionline, p.showregressionline, 0);
      g.zIndex = 1;
      g.name = l(h.seriesname);
      if (0 === a(h.includeinlegend) || void 0 === g.name)g.showInLegend = !1;
      if (ca || t)q = T(c(h.color, n)), u = c(h.alpha, Ta), ca = a(h.linethickness, p.linethickness, 2), t = Boolean(a(h.linedashed, h.dashed, p.linedashed, 0)), A = a(h.linedashlen, p.linedashlen, 5), z = a(h.linedashgap, p.linedashgap, 4), g.color = fa(c(h.linecolor, p.linecolor, q),
        a(h.linealpha, p.linealpha, u)), g.lineWidth = ca, g.dashStyle = t ? s(A, z, ca) : "none";
      ca = Boolean(a(h.drawanchors, h.showanchors, p.drawanchors, p.showanchors, 1));
      m = a(h.anchorsides, p.anchorsides, m + 3);
      t = a(h.anchorradius, p.anchorradius, 3);
      q = T(c(h.anchorbordercolor, h.color, p.anchorbordercolor, q, n));
      n = a(h.anchorborderthickness, p.anchorborderthickness, 1);
      A = T(c(h.anchorbgcolor, p.anchorbgcolor, I.getColor("anchorBgColor")));
      z = c(h.anchoralpha, h.alpha, p.anchoralpha, Ta);
      V = c(h.anchorbgalpha, h.alpha, p.anchorbgalpha, z);
      La = c(h.anchorstartangle,
        p.anchorstartangle, 90);
      g.anchorShadow = I = a(p.anchorshadow, 0);
      g.marker = {
        fillColor: this.getPointColor(A, Ta),
        lineColor: {FCcolor: {color: q, alpha: z + b}},
        lineWidth: n,
        radius: t,
        symbol: bb(m)
      };
      u = h.data || [];
      Y = u.length;
      hb && (g.events = {hide: this.hideRLine, show: this.showRLine}, Q = {
        sumX: 0,
        sumY: 0,
        sumXY: 0,
        sumXsqure: 0,
        sumYsqure: 0,
        xValues: [],
        yValues: []
      }, Ga = a(h.showyonx, p.showyonx, 1), P = T(c(h.regressionlinecolor, p.regressionlinecolor, q)), J = a(h.regressionlinethickness, p.regressionlinethickness, n), H = L(a(h.regressionlinealpha,
        p.regressionlinealpha, z)), P = fa(P, H));
      for (O = 0; O < Y; O += 1)(ea = u[O]) ? (H = wa.getCleanValue(ea.y), ia = wa.getCleanValue(ea.x), null === H ? g.data.push({
        y: null,
        x: ia
      }) : (k = !0, Ja = this.getPointStub(ea, H, wa.xAxis(ia), e, h, Ia), B = a(ea.anchorsides, m), ma = a(ea.anchorradius, t), la = T(c(ea.anchorbordercolor, q)), G = a(ea.anchorborderthickness, n), E = T(c(ea.anchorbgcolor, A)), oa = c(ea.anchoralpha, ea.alpha, z), K = c(ea.anchorbgalpha, V), C = Boolean(a(ea.anchorshadow, I, 0)), Oa = this.pointHoverOptions(ea, g, {
        plotType: "anchor",
        anchorBgColor: E,
        anchorAlpha: oa,
        anchorBgAlpha: K,
        anchorAngle: La,
        anchorBorderThickness: G,
        anchorBorderColor: la,
        anchorBorderAlpha: oa,
        anchorSides: B,
        anchorRadius: ma,
        shadow: void 0
      }), g.data.push({
        y: H,
        x: ia,
        displayValue: Ja.displayValue,
        toolText: Ja.toolText,
        link: Ja.link,
        marker: {
          enabled: ca,
          shadow: C && {opacity: oa / 100},
          fillColor: {FCcolor: {color: E, alpha: K * oa / 100 + b}},
          lineColor: {FCcolor: {color: la, alpha: oa}},
          lineWidth: G,
          radius: ma,
          symbol: bb(B),
          startAngle: c(ea.anchorstartangle, h.anchorstartangle, p.anchorstartangle, 90)
        },
        hoverEffects: Oa.enabled && Oa.options,
        rolloverProperties: Oa.enabled && Oa.rolloverOptions
      }), this.pointValueWatcher(e, H, ia, hb && Q))) : g.data.push({y: null});
      hb && (h = this.getRegressionLineSeries(Q, Ga, Y), this.pointValueWatcher(e, h[0].y, h[0].x), this.pointValueWatcher(e, h[1].y, h[1].x), e = {
        type: "line",
        color: P,
        showInLegend: !1,
        lineWidth: J,
        enableMouseTracking: !1,
        marker: {enabled: !1},
        data: h,
        zIndex: 0
      }, g = [g, e]);
      d && !k && (g.showInLegend = !1);
      return g
    },
    postSeriesAddition: function (b, c) {
      for (var d = b.chart, f = c.chart, e = b.series, g = 0, h = e.length; g < h; g += 1)e[g]._showRegression &&
      (e[g].relatedSeries = [g + 1]);
      d.clipBubbles = a(f.clipbubbles, 1)
    },
    categoryAdder: function (d, g) {
      var p, l = 0, e, k = g[f].x, m, I = g.xAxis, n, H, O = d.chart, u = parseInt(O.labelstep, 10), ca = a(O.showlabels, 1), t = c(O.xaxislabelmode, "categories").toLowerCase(), A = this.colorManager, z = g[f].numberFormatter, ea, V, B, ma, la, G;
      g._FCconf.isXYPlot = !0;
      u = 1 < u ? u : 1;
      k.catOccupied = {};
      if ("auto" !== t && d.categories && d.categories[0] && d.categories[0].category) {
        H = d.categories[0];
        H.font && (g.xAxis.labels.style.fontFamily = H.font);
        void 0 !== (e = a(H.fontsize)) &&
        (1 > e && (e = 1), g.xAxis.labels.style.fontSize = e + ra, h(g.xAxis.labels.style));
        H.fontcolor && (g.xAxis.labels.style.color = H.fontcolor.split(Ba)[0].replace(/^\#?/, "#"));
        p = c(H.verticallinecolor, A.getColor("divLineColor"));
        e = a(H.verticallinethickness, 1);
        m = a(H.verticallinealpha, A.getColor("divLineAlpha"));
        A = a(H.verticallinedashed, 0);
        ea = a(H.verticallinedashlen, 4);
        V = a(H.verticallinedashgap, 2);
        B = fa(p, m);
        for (p = 0; p < H.category.length; p += 1)ma = H.category[p], m = z.getCleanValue(ma.x), null === m || ma.vline || (k.catOccupied[m] = !0, n = a(ma.showlabel, ma.showname, ca), la = a(ma.showverticalline, ma.showline, ma.sl, 0), G = a(ma.linedashed, A), n = 0 === n || 0 !== l % u ? b : C(q(ma.label, ma.name)), I.plotLines.push({
          isGrid: !0,
          isCat: !0,
          isDataLabel: !0,
          width: la ? e : 0,
          color: B,
          dashStyle: s(ea, V, e, G),
          value: m,
          label: {
            text: n,
            link: c(ma.link, O.labellink),
            style: Ea({}, ma, O, I.labels.style),
            align: ya,
            verticalAlign: Da,
            textAlign: ya,
            rotation: 0,
            x: 0,
            y: 0
          }
        }), this.pointValueWatcher(g, null, m), l += 1);
        "mixed" === t && (k.requiredAutoNumericLabels = a(this.requiredAutoNumericLabels, 1))
      } else k.requiredAutoNumericLabels =
        a(this.requiredAutoNumericLabels, 1);
      k.adjustMinMax = !0
    },
    getPointColor: function (a, b) {
      var c, d;
      a = T(a);
      b = L(b);
      c = da(a, 70);
      d = U(a, 50);
      return {
        FCcolor: {
          gradientUnits: "objectBoundingBox",
          cx: .4,
          cy: .4,
          r: "100%",
          color: c + Ba + d,
          alpha: b + Ba + b,
          ratio: Xa,
          radialGradient: !0
        }
      }
    }
  }, pa.xybase);
  pa("mscombibase", {
    canvasPaddingModifiers: ["anchor", "anchorlabel"], series: function (b, d, g) {
      var h, e, p, l, k = b.chart, m, n = [], I = [], H = [], O, s, u = d[f], ca = this.isDual, t = 0, A;
      d.legend.enabled = Boolean(a(b.chart.showlegend, 1));
      if (b.dataset && 0 < b.dataset.length) {
        this.categoryAdder(b,
          d);
        l = u.oriCatTmp.length;
        h = 0;
        for (e = b.dataset.length; h < e; h += 1)switch (p = b.dataset[h], O = ca && "s" === c(p.parentyaxis, "p").toLowerCase() ? !0 : !1, m = {
          hoverEffects: this.parseSeriesHoverOptions(b, d, p, g),
          visible: !a(p.initiallyhidden, 0),
          legendIndex: h,
          data: []
        }, O ? (m.yAxis = 1, s = q(p.renderas, this.secondarySeriesType), this.secondarySeriesFilter && (A = this.secondarySeriesFilter[s])) : (s = q(p.renderas, this.defaultSeriesType), this.defaultSeriesFilter && (A = this.defaultSeriesFilter[s])), s = s.toLowerCase(), s) {
          case "line":
          case "spline":
            m.type =
              !0 === A ? s : "line";
            n.push(pa.mslinebase.point.call(this, g, m, p, k, d, l, h));
            break;
          case "area":
          case "splinearea":
            m.type = !0 === A ? s : "area";
            d.chart.series2D3Dshift = !0;
            H.push(pa.msareabase.point.call(this, g, m, p, k, d, l, h));
            break;
          case "column":
          case "column3d":
            I.push(pa.mscolumn2dbase.point.call(this, g, m, b.dataset[h], k, d, l, h, void 0, t));
            t += 1;
            break;
          default:
            O ? (m.type = "line", n.push(pa.mslinebase.point.call(this, g, m, p, k, d, l, h))) : (I.push(pa.mscolumn2dbase.point.call(this, g, m, b.dataset[h], k, d, l, h, void 0, t)), t += 1)
        }
        "0" !==
        k.areaovercolumns ? (d.chart.areaOverColumns = !0, d.series = d.series.concat(I, H, n)) : (d.chart.areaOverColumns = !1, d.series = d.series.concat(H, I, n));
        if (0 === I.length && 1 !== l)u.hasNoColumn = !0; else if (!this.isStacked)for (g = 0, h = I.length; g < h; g += 1)I[g].numColumns = h;
        this.configureAxis(d, b);
        b.trendlines && K(b.trendlines, d.yAxis, d[f], ca, this.isBar)
      }
    }
  }, pa.mscolumn2dbase)
}]);
FusionCharts.register("module", ["private", "modules.renderer.jpegtopdf", function () {
  var d = this.window, k = d.btoa, B = d.atob;
  this.hcLib.JpegToPdf = function (d, E) {
    var b = [], K = 0, G = d || 500, c = E || 500, l, a, q;
    l = function (a) {
      K += a.length + 1;
      b.push(a)
    };
    this.addImage = function (a) {
      a = /base64,(.+?)$/g.exec(a);
      q = B(a[1])
    };
    a = function () {
      var a = [], d, f;
      l("%PDF-1.7");
      a[1] = K;
      l("1 0 obj");
      l("/ProcSet [/PDF /Text /ImageB /ImageC /ImageI]");
      l("endobj");
      a[4] = K;
      l("4 0 obj");
      l("<< /Length 36>>");
      l("stream");
      l("q " + c + " 0 0 " + G + " 0 0 cm /Im3 Do Q");
      l("endstream");
      l("endobj");
      a[5] = K;
      l("5 0 obj");
      l("<</Type /Page /Parent 2 0 R /MediaBox [0 0 " + c + " " + G + "] /Contents [4 0 R ] /Resources <</ProcSet 1 0 R /Font <<>> /XObject <</Im3 3 0 R >> >> >>");
      l("endobj");
      a[3] = K;
      l("3 0 obj");
      l("<</Type /XObject /Subtype /Image /Width " + c + " /Height " + G + " /ColorSpace /DeviceRGB /BitsPerComponent 8 /Filter /DCTDecode /Length " + q.length + ">>");
      l("stream");
      l(q);
      l("endstream");
      l("endobj");
      a[2] = K;
      l("2 0 obj");
      l("<</Type /Pages /Kids [5 0 R ] /Count 1>>");
      l("endobj");
      a[6] = K;
      l("6 0 obj");
      l("<</Type /Catalog /Pages 2 0 R >>");
      l("endobj");
      d = K;
      l("xref");
      l("0 7");
      l("0000000000 65535 f ");
      for (f = 1; 6 >= f; f++)l(("0000000000" + a[f]).slice(-10) + " 00000 n ");
      l("trailer");
      l("<</Size 7 /Root 6 0 R >>");
      l("startxref");
      l(d);
      l("%%EOF");
      return b.join("\n")
    };
    this.getDataUrl = function () {
      return "data:application/pdf;base64," + k(a())
    }
  }
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-renderer", function () {
  function d(a, b, c, d) {
    var f = b.paper, g = b.layers, h = c ? "y-axis" : "x-axis", l = this.layerAboveDataset = g.layerAboveDataset, k = this.layerBelowDataset = g.layerBelowDataset, g = l.bands || (l.bands = []), m = g.length, n = k.bands || (k.bands = []), s = n.length, q = l.lines || (l.lines = []), u = q.length, t = k.lines || (k.lines = []), A = t.length, l = l.labels || (l.labels = []), z = l.length, k = k.labels || (k.labels = []), C = k.length;
    this.renderer = b;
    this.axisData = a || {};
    this.globalOptions =
      b.options;
    this.isVertical = c;
    this.topBandGroup = this.topBandGroup || f.group(h + "-bands", this.layerAboveDataset);
    this.belowBandGroup = this.belowBandGroup || f.group(h + "-bands", this.layerBelowDataset);
    g.push(this.topBandGroup);
    m && g[m].insertAfter(g[m - 1]);
    n.push(this.belowBandGroup);
    s && n[s].insertAfter(n[s - 1]);
    this.topLineGroup = this.topLineGroup || f.group(h + "-lines", this.layerAboveDataset);
    this.belowLineGroup = this.belowLineGroup || f.group(h + "-lines", this.layerBelowDataset);
    this.topLabelGroup = this.topLabelGroup ||
    f.group(h + "-labels", this.layerAboveDataset);
    this.belowLabelGroup = this.belowLabelGroup || f.group(h + "-labels", this.layerBelowDataset);
    q.push(this.topLineGroup);
    u && q[u].insertAfter(q[u - 1]);
    t.push(this.belowLineGroup);
    A && t[A].insertAfter(t[A - 1]);
    l.push(this.topLabelGroup);
    z && l[z].insertAfter(l[z - 1]);
    k.push(this.belowLabelGroup);
    C && k[C].insertAfter(k[C - 1]);
    this.isReverse = d;
    this.configure()
  }

  function k(a, b, c, d) {
    return bb(b - c[1] - d.top, a - c[0] - d.left)
  }

  function B(a, b) {
    var c = b ? 360 : qb;
    a = (a || 0) % c;
    return 0 > a ? c + a :
      a
  }

  var t = this, E = t.window, b = t.hcLib, K = b.Raphael, G = b.chartAPI, c = /msie/i.test(E.navigator.userAgent) && !E.opera, l = E.document, a = E.Image, q = "VML" === K.type, S = b.BLANKSTRING, C = b.getPosition, f = "rgba(192,192,192," + (c ? .002 : 1E-6) + ")", c = b.TOUCH_THRESHOLD_PIXELS, g = b.CLICK_THRESHOLD_PIXELS, s = b.stubFN, n = {
      pageX: 0,
      pageY: 0
    }, Q = parseFloat, A = parseInt, m = b.extend2, P = b.addEvent, z = b.getMouseCoordinate, X = b.removeEvent, u = b.pluck, ka = b.pluckNumber, ha = b.toRaphaelColor, ba = b.graphics.convertColor, Z = b.HUNDREDSTRING, za = b.setImageDisplayMode,
    Aa = b.FC_CONFIG_STRING, Ka = b.plotEventHandler, $ = b.isArray, aa = b.JpegToPdf, qa = b.each = function (a, b, c, d) {
      var f;
      c || (c = a);
      d || (d = {});
      if ($(a))for (f = 0; f < a.length; f += 1) {
        if (!1 === b.call(c, a[f], f, a, d))return f
      } else if (null !== a && void 0 !== a)for (f in a)if (!1 === b.call(c, a[f], f, a, d))return f
    }, ga = b.createElement, R = b.createContextMenu, T = b.hasTouch, h = T ? c : g, J = b.getSentenceCase, L = b.getCrispValues, U = b.getValidValue, da = b.getFirstValue, fa = b.regex.dropHash, xa = b.HASHSTRING, ya = function (a) {
      return a !== Da && null !== a
    }, Fa = function (a,
                      b) {
      a[1] === a[4] && (a[1] = a[4] = va(a[1]) + b % 2 / 2);
      a[2] === a[5] && (a[2] = a[5] = va(a[2]) + b % 2 / 2);
      return a
    }, Da, na = 8 === l.documentMode ? "visible" : "", ua = E.Math, Ea = ua.sin, pa = ua.cos, bb = ua.atan2, va = ua.round, Ya = ua.min, Ba = ua.max, jb = ua.abs, Ca = ua.ceil, db = ua.floor, Ta = 180 / ua.PI, ra = ua.PI, Qb = ra / 2, qb = 2 * ra, Ib = ra + Qb, Rb = b.getFirstColor, eb = b.graphics.getLightColor, Xa = b.POSITION_TOP, Va = b.POSITION_BOTTOM, lb = b.POSITION_RIGHT, xb = b.POSITION_LEFT;
  K.ca.ishot = function (a) {
    if (this.removed)return !1;
    var b = this.node;
    a = a || "";
    b.ishot = a;
    switch (this.type) {
      case "group":
        for (b =
               this.bottom; b;)b.attr("ishot", a), b = b.next;
        break;
      case "text":
        if (K.svg)for (b = b.getElementsByTagName("tspan")[0]; b;)b.ishot = a, b = b.nextSibling
    }
    return !1
  };
  K.addSymbol({
    printIcon: function (a, b, c) {
      var d = .75 * c, f = .5 * c, g = .33 * c, h = va(a - c) + .5, l = va(b - c) + .5, k = va(a + c) + .5;
      c = va(b + c) + .5;
      var m = va(a - d) + .5, n = va(b - d) + .5, d = va(a + d) + .5, s = va(b + f) + .5, q = va(a + f) + .5, u = va(b + g) + .5;
      a = va(a - f) + .5;
      g = va(b + g + g) + .5;
      return ["M", m, l, "L", d, l, d, n, m, n, "Z", "M", h, n, "L", h, s, m, s, m, b, d, b, d, s, k, s, k, n, "Z", "M", m, b, "L", m, c, d, c, d, b, "Z", "M", q, u, "L", a, u, "M",
        q, g, "L", a, g]
    }, exportIcon: function (a, b, c) {
      var d = .66 * c, f = .5 * d, g = va(a - c) + .5, h = va(b - c) + .5, l = va(a + c) + .5;
      c = va(b + c) - .5;
      var k = va(a - f) + .5, m = b < c - 3 ? c - 3 : va(b) + .5, f = va(a + f) - .5, n = va(a + d) - .5, d = va(a - d) + .5;
      return ["M", g, m, "L", g, c, l, c, l, m, l, c, g, c, "Z", "M", a, c - 1, "L", d, b, k, b, k, h, f, h, f, b, n, b, "Z"]
    }
  });
  b.rendererRoot = G("renderer.root", {
    standaloneInit: !1, isRenderer: !0, inited: !1, callbacks: [], init: function (a, b, c) {
      var d = this, f = d.container = a && a.containerElement || b.chart.renderTo, g = b.tooltip, h = d.layer, l, k;
      d.options = b;
      d.logic = a;
      d.definition = a.dataObj;
      d.smartLabel = a.smartLabel;
      d.numberFormatter = a.numberFormatter;
      d.fusionCharts = a.chartInstance;
      d.linkClickFN = a.linkClickFN;
      k = (l = b.chart) && l.animation && l.animation.duration;
      d.animationCompleteQueue = [];
      f.innerHTML = S;
      f = d.paper = d.fusionCharts.jsVars.paper = new K(f, f.offsetWidth || a.width, f.offsetHeight || a.height);
      !1 !== t.core.options._useSVGDescTag && f._desc && (l = a.friendlyName || "Vector image", d.definition && d.definition.chart && d.definition.chart.caption && (l += ' with caption "' + d.definition.chart.caption +
      '"'), f._desc(l));
      d.chartWidth = f.width;
      d.chartHeight = f.height;
      d.elements || (d.elements = {});
      h || (h = d.layers = {}, h.background = h.background || f.group("background"), h.dataset = h.dataset || f.group("dataset").insertAfter(h.background), h.tracker = h.tracker || f.group("hot").insertAfter(h.dataset));
      g && !1 !== g.enabled && (f.tooltip(g.style, g.shadow, g.constrain), h.tracker.trackTooltip(!0), h.dataset.trackTooltip(!0));
      d.disposeChartStyleSheet();
      d.setMargins();
      d.drawBackground();
      d.drawButtons();
      d.drawGraph();
      b.legend && b.legend.enabled &&
      d.drawLegend();
      d.drawCaption();
      d.drawLogo();
      d.setChartEvents();
      d.drawLabels && d.drawLabels();
      qa(b.callbacks, function (a) {
        a.apply(d, this)
      }, [a]);
      qa(d.callbacks, function (a) {
        a.apply(d, this)
      }, [a]);
      d.fusionCharts.annotations && d.fusionCharts.annotations.draw(d);
      d.createChartStyleSheet();
      d.options.nativeMessage || k || t.raiseEvent("internal.animationComplete", {}, d.fusionCharts);
      d.hasRendered = !0;
      c && c(d)
    }, disposeChartStyleSheet: function () {
      this.paper.cssClear()
    }, createChartStyleSheet: function () {
      this.paper.cssRender()
    },
    addCSSDefinition: function (a, b) {
      var c = this.paper;
      b.color && (b.fill = b.color);
      c.cssAddRule(a, b)
    }, animationCompleteQueue: [], animationComplete: function () {
      var a, b, c, d;
      this.animatedElements = this.animatedElements ? ++this.animatedElements : 1;
      if (this.animatedElements === this.animatingElementsCount) {
        c = this.animationCompleteQueue;
        a = 0;
        for (b = c.length; a < b; a++)d = c[a], d.fn && d.fn.call(d.scope);
        this.animationCompleteQueue = [];
        t.raiseEvent("internal.animationComplete", {}, this.fusionCharts)
      }
    }, getAnimationCompleteFn: function () {
      var a =
        this;
      a.animatingElementsCount = a.animatingElementsCount ? ++a.animatingElementsCount : 1;
      return function () {
        a.animationComplete()
      }
    }, reinit: function (a, b, c) {
      this.hasRendered || this.init(b, c)
    }, dispose: function () {
      var a = this.eventListeners, b = a && a.length;
      this.disposing = !0;
      if (b)for (; b--;)a[b].unlisten();
      if (this.toolbar && this.toolbar.length) {
        for (; this.toolbar.length;)a = this.toolbar.pop(), a.remove();
        this.toolbar.add = null
      }
      if (this.menus && this.menus.length)for (; this.menus.length;)a = this.menus.pop(), a.destroy();
      this.paper &&
      (this.paper.clear(), this.paper.remove(), delete this.paper);
      this.exportIframe && (this.exportIframe.parentNode.removeChild(this.exportIframe), delete this.exportIframe);
      delete this.disposing;
      this.container = null;
      this.disposed = !0
    }, onContainerClick: function (a) {
      var c = a.target || a.originalTarget || a.srcElement || a.relatedTarget || a.fromElement, d = c && c.parentElement, f = c && c.nodeName.toLowerCase(), g = a.data, h = g.fusionCharts;
      a = b.getMouseCoordinate(g.container, a.originalEvent);
      h.ref && (h = m({
        height: h.args.height,
        width: h.args.width,
        pixelHeight: h.ref.offsetHeight,
        pixelWidth: h.ref.offsetWidth,
        id: h.args.id,
        renderer: h.args.renderer,
        container: h.options.containerElement
      }, a), t.raiseEvent("chartclick", h, g.logic.chartInstance), c && c.ishot && g || "tspan" === f && d && d.ishot && g || g.options.chart.link && g.linkClickFN.call(g, g))
    }, onContainerMouseMove: function (a) {
      var c = a.data, d = c.fusionCharts;
      a = b.getMouseCoordinate(c.container, a.originalEvent);
      d.ref && (d = m({
        height: d.args.height, width: d.args.width, pixelHeight: d.ref.offsetHeight, pixelWidth: d.ref.offsetWidth,
        id: d.args.id, renderer: d.args.renderer, container: d.options.containerElement
      }, a), t.raiseEvent("chartMouseMove", d, c.logic.chartInstance))
    }, onContainerRollOver: function (a) {
      var c = a.data, d = c.fusionCharts;
      a = b.getMouseCoordinate(c.container, a.originalEvent);
      d.ref && (d = m({
        height: d.args.height,
        width: d.args.width,
        pixelHeight: d.ref.offsetHeight,
        pixelWidth: d.ref.offsetWidth,
        id: d.args.id,
        renderer: d.args.renderer,
        container: d.options.containerElement
      }, a), t.raiseEvent("chartRollOver", d, c.logic.chartInstance))
    }, onContainerRollOut: function (a) {
      var c =
        a.chart, d = c.fusionCharts;
      a = b.getMouseCoordinate(c.container, a.event);
      d.ref && (d = m({
        height: d.args.height,
        width: d.args.width,
        pixelHeight: d.ref.offsetHeight,
        pixelWidth: d.ref.offsetWidth,
        id: d.args.id,
        renderer: d.args.renderer,
        container: d.options.containerElement
      }, a), t.raiseEvent("chartRollOut", d, c.logic.chartInstance))
    }, mouseStateIn: !1, winMouseHover: function (a) {
      var b = a.originalEvent, b = b.target || b.originalTarget || b.srcElement || b.relatedTarget || b.fromElement, c = a.data, d = c.paper;
      a = {chart: c, event: a.originalEvent};
      q ? d.getById(b.parentNode.raphaelid) || (c.onContainerRollOut(a), c.mouseStateIn = !1, X(l, "mouseover", c.winMouseHover)) : b.viewportElement || (c.mouseStateIn = !1, c.onContainerRollOut(a), X(E, "mouseover", c.winMouseHover))
    }, chartHoverManager: function () {
      return function (a) {
        var b = a.type, c = a.data, d = c.eventListeners || (c.eventListeners = []);
        "mouseover" !== b && "touchstart" !== b || !1 !== c.mouseStateIn || (c.mouseStateIn = !0, c.onContainerRollOver(a), d.push(P(q ? l : E, "mouseover", c.winMouseHover, c)))
      }
    }(), setChartEvents: function () {
      var a =
        this.options, b = this.eventListeners || (this.eventListeners = []), a = this.link = a.chart.link, c = this.container, d = ka(this.definition && this.definition.chart.enablechartmousemoveevent, 0);
      X(c, "click", this.onContainerClick);
      b.push(P(c, "click", this.onContainerClick, this));
      X(this.paper.canvas, "mouseover", this.chartHoverManager, this);
      X(this.paper.canvas, "touchstart", this.chartHoverManager, this);
      X(this.paper.canvas, "mouseout", this.chartHoverManager, this);
      X(this.paper.canvas, "touchend", this.chartHoverManager, this);
      b.push(P(this.paper.canvas, "mouseover touchstart mouseout touchend", this.chartHoverManager, this));
      X(c, "mousemove", this.onContainerMouseMove, this);
      X(c, "touchmove", this.onContainerMouseMove, this);
      d && b.push(P(c, "mousemove touchmove", this.onContainerMouseMove, this));
      this.paper.canvas.style.cursor = K.svg ? a && "pointer" || "default" : a && "hand" || "default"
    }, onOverlayMessageClick: function () {
      var a = this.elements;
      K.animation({opacity: 0}, 1E3);
      a.messageText && a.messageText.hide();
      a.messageVeil && a.messageVeil.hide()
    },
    showMessage: function (a, b) {
      var c = this.paper, d = this.options.chart, f = this.elements, g = f.messageText, h = f.messageVeil, l = c.width, k = c.height;
      h || (h = f.messageVeil = c.rect(0, 0, l, k).attr({fill: "rgba(0,0,0,0.2)", stroke: "none"}));
      h.show().toFront().attr("cursor", b ? "pointer" : "default")[b ? "click" : "unclick"](this.onOverlayMessageClick, this);
      g || (g = f.messageText = c.text(l / 2, k / 2, S).attr({
        fill: "rgba(255,255,255,1)",
        "font-family": "Verdana,sans",
        "font-size": 10,
        "line-height": 14,
        direction: d.textDirection,
        ishot: !0
      }));
      a = a || S;
      this.smartLabel.setStyle({"line-height": "14px", "font-family": "Verdana,sans", "font-size": "10px"});
      c = this.smartLabel.getSmartText(a, l - (d.spacingRight || 0) - (d.spacingLeft || 0), k - (d.spacingTop || 0) - (d.spacingBotton || 0));
      g.attr({
        text: c.text,
        ishot: !0,
        cursor: b ? "pointer" : "default"
      })[b ? "click" : "unclick"](this.onOverlayMessageClick, this).show().toFront()
    }, drawButtons: function () {
      var a = this, b = a.logic, c = "zoomline" === b.rendererId, d = a.paper, f = a.elements, g = a.toolbar || (a.toolbar = []), h = a.menus || (a.menus = []), l = a.layers,
        k = a.options, m = k[Aa], b = m && m.outCanvasStyle || b.outCanvasStyle || {}, m = k.chart.toolbar || {}, n = m.hDirection, s = c ? 1 : m.vDirection, q = m.button || {}, u = q.scale, t = q.width * q.scale, A = q.height * q.scale, z = n * (q.spacing * q.scale + t), C = q.radius, B = (k = k.exporting) && k.buttons || {}, G = B.exportButton && !1 !== B.exportButton.enabled, B = B.printButton && !1 !== B.printButton.enabled, E, K = l.buttons || (l.buttons = d.group("buttons").trackTooltip(!0));
      g.y || (g.y = (c ? 0 : m.y) + m.vMargin * s + Ya(0, A * s));
      g.x || (g.x = m.x + m.hMargin * n - Ba(0, t * n));
      g.count = 0;
      g.add =
        function (a, b, c) {
          c = "string" === typeof c ? {tooltip: c} : c || {};
          var f = 0 === g.count ? z - n * q.spacing * q.scale : z, f = c.x || (g.x += f), h = c.tooltip || "";
          g.push(a = d.button(f, c.y || g.y, Da, a, {
            width: t,
            height: A,
            r: C,
            id: g.count++,
            verticalPadding: q.symbolHPadding * u,
            horizontalPadding: q.symbolHPadding
          }, K).attr({
            ishot: !0,
            fill: [q.fill, q.labelFill, q.symbolFill, q.hoverFill],
            stroke: [q.stroke, q.symbolStroke],
            "stroke-width": [q.strokeWidth, q.symbolStrokeWidth]
          }).tooltip(h).buttonclick(b));
          return a
        };
      G && (h.push(E = f.exportMenu = R({
        chart: a, basicStyle: b,
        items: function (b) {
          var c = [], d = function (b) {
            return function () {
              a.logic.chartInstance.exportChart({exportFormat: b})
            }
          }, f;
          for (f in b)c.push({text: b[f], onclick: d(f)});
          return c
        }(k.exportformats)
      })), f.exportButton = g.add("exportIcon", function (a, b) {
        return function () {
          E.visible ? E.hide() : E.show({x: a, y: b + 1})
        }
      }(g.x + t, g.y + A), {tooltip: "Export chart"}));
      B && (f.printButton = g.add("printIcon", function () {
        a.print()
      }, {tooltip: "Print chart"}))
    }, setMargins: function () {
      var a = this.paper, b = this.options.chart || {}, c = va;
      this.canvasBorderWidth =
        b.plotBorderWidth || 0;
      this.canvasTop = c(b.marginTop) || 0;
      this.canvasLeft = c(b.marginLeft) || 0;
      this.canvasWidth = c(a.width - (b.marginLeft || 0) - (b.marginRight || 0));
      this.canvasHeight = c(a.height - (b.marginTop || 0) - (b.marginBottom || 0));
      this.canvasRight = this.canvasLeft + this.canvasWidth;
      this.canvasBottom = this.canvasTop + this.canvasHeight
    }, drawBackground: function () {
      var b = this, c = b.paper, d = b.layers, f = b.elements, g = d.background, h = f.background, l = f.chartborder, k = b.options.chart || {}, m = Q(k.borderWidth) || 0, n = .5 * m, s = 2 * m, q = k.borderWidth ||
        0, u = b.chartHeight, A = b.chartWidth, z = f.backgroundImage, C = k.bgSWF, B = k.bgSWFAlpha / 100, G = k.bgImageDisplayMode, E = k.bgImageVAlign, K = k.bgImageHAlign, P = k.bgImageScale, J = q + "," + q + "," + (A - 2 * q) + "," + (u - 2 * q), Y, L, R, S, U, T, ka;
      c.canvas.style.backgroundColor = k.containerBackgroundColor;
      !g && (g = d.background = c.group("background"));
      d = {x: m, y: m, width: c.width - s, height: c.height - s, stroke: "none", fill: ha(k.backgroundColor)};
      h ? h.attr(d) : h = f.background = c.rect(d, g);
      d = {
        x: n, y: n, width: c.width - m, height: c.height - m, stroke: k.borderColor, "stroke-width": m,
        "stroke-dasharray": k.borderDashStyle, fill: "none", r: k.borderRadius || 0
      };
      l ? l.attr(d) : l = f.chartborder = c.rect(d, g);
      C && (Y = new a, U = R = 1, z = [], Y.onload = function () {
        L = za(G, E, K, P, q, A, u, Y);
        L["clip-rect"] = J;
        if (L.tileInfo)for (R = L.tileInfo.xCount, U = T = L.tileInfo.yCount, ka = L.y, delete L.tileInfo; R && L.width && L.height;)--T, S ? (z[void 0] = S.clone().attr({
          x: L.x,
          y: L.y
        }), g.appendChild(z[void 0])) : z[void 0] = S = c.image(C, g).attr(L).css({opacity: B}), L.y += L.height, 0 === T && (T = U, --R, L.x += L.width, L.y = ka); else {
          if (b.disposed || c.disposed)return;
          z[0] = c.image(C, g);
          z[0].attr(L).css({opacity: B}).attr({visibility: na, "clip-rect": J})
        }
        t.raiseEvent("BackgroundLoaded", {
          url: C,
          bgImageAlpha: 100 * B,
          bgImageDisplayMode: G,
          bgImageVAlign: E,
          bgImageHAlign: K,
          bgImageScale: P,
          imageWidth: Y.width,
          imageHeight: Y.height
        }, b.logic.chartInstance)
      }, Y.onerror = function (a) {
        t.raiseEvent("BackgroundLoadError", {
          url: C,
          bgImageAlpha: 100 * B,
          error: a,
          bgImageDisplayMode: G,
          bgImageVAlign: E,
          bgImageHAlign: K,
          bgImageScale: P
        }, b.logic.chartInstance)
      }, Y.src = C, f.backgroundImage = z)
    }, drawGraph: function () {
      var a =
        this, b = a.paper, c = a.plots = a.elements.plots, d = a.logic, f = a.layers, g = a.options, h = a.elements, k = g.chart, g = a.datasets = g.series, l = da(k.rendererId, k.defaultSeriesType), n = f.background, s = f.dataset = f.dataset || b.group("dataset").insertAfter(n), q, t, n = function (a, b) {
        return function (f) {
          var g = c[a], h, k = {hcJSON: {series: []}}, l = k.hcJSON.series[a] || (k.hcJSON.series[a] = {}), n = d.chartInstance.jsVars._reflowData;
          h = (f = da(f, !g.visible)) ? "visible" : "hidden";
          qa(g.graphics, function (a) {
            !0 !== a.data("alwaysInvisible") && a.attr("visibility",
              h)
          });
          g.visible = f;
          b.visible = f;
          l.visible = f;
          m(n, k, !0)
        }
      }, A = function (b) {
        return function (d, f) {
          a["legendClick" + l] && a["legendClick" + l](c[b], d, f) || a.legendClick && a.legendClick(c[b], d, f)
        }
      }, z = function (b) {
        return function () {
          return a.getEventArgs && a.getEventArgs(c[b])
        }
      }, C = function (b, d, f) {
        return function (g, h) {
          d.call(a, c[b], f, {numUpdate: g, hasAxisChanged: h})
        }
      };
      f.tracker = f.tracker || b.group("hot").insertAfter(s);
      a.drawCanvas();
      a.drawAxes();
      c || (c = a.plots = a.plots || [], h.plots = c);
      f = 0;
      for (h = g.length; f < h; f++)b = g[f] || {}, s =
        b.updatePlot = "updatePlot" + J(u(b.type, b.plotType, l)), s = a[s], q = b.drawPlot = "drawPlot" + J(u(b.type, b.plotType, l)), q = a[q] || a.drawPlot, (t = c[f]) || (c.push(t = {
        index: f,
        items: [],
        data: b.data || [],
        name: b.name,
        userID: b.userID,
        setVisible: n(f, b),
        legendClick: A(f),
        getEventArgs: z(f),
        realtimeUpdate: C(f, s || q, b)
      }), b.plot = t, b.legendClick = t.legendClick, b.getEventArgs = t.getEventArgs, b.setVisible = t.setVisible), q.call(a, t, b);
      k.hasScroll && (a.drawScroller(), a.finalizeScrollPlots())
    }, drawPlot: s, drawCanvas: s, drawAxes: s, drawScroller: function () {
    },
    drawLegend: function () {
      var a = this, b = a.options, c = a.paper, d = b.chart || {}, f = b.legend, g = f.scroll, b = {elements: {}}, h = b.elements, k = a.layers.legend, l = h.box, n = h.caption, s = h.elementGroup, q = "vertical" === f.layout, u = d.marginBottom, t = d.spacingBottom, A = d.spacingLeft, z = d.spacingRight, C = c.width, B = c.height, G = a.canvasTop, E = f.width, Q = f.height, P = f.borderRadius, L = f.backgroundColor, R = f.borderColor, U = f.borderWidth || 0, T = .5 * U, ha = .5 * U + 2, X = ka(f.padding, 4), Z = .5 * X, Pa, ba, mb, r, w, v, D, e;
      e = g && g.enabled;
      q ? (q = C - z - E, u = G + .5 * (B - u - G - Q) + (f.y ||
      0)) : (q = A + .5 * (C - A - z - E) + (f.x || 0), u = B - t - Q);
      t = K.crispBound(q, u, E, Q, U);
      q = t.x;
      u = t.y;
      E = t.width;
      Q = t.height;
      k || (k = a.layers.legend = c.group("legend").insertBefore(a.layers.tracker).translate(q, u).attr("class", "fusioncharts-legend"));
      a.addCSSDefinition(".fusioncharts-legend .fusioncharts-caption", m({"text-anchor": f.title.align}, f.title.style));
      f.legendAllowDrag && (a.addCSSDefinition(".fusioncharts-legend", {cursor: "move"}), ba = q, mb = u, k.drag(function (a, b) {
        r = v + a;
        w = D + b;
        r + E + ha > C && (r = C - E - ha);
        w + Q + ha > B && (w = B - Q - ha);
        r < ha &&
        (r = ha);
        w < ha && (w = ha);
        k.translate(r - ba, w - mb);
        ba = r;
        mb = w
      }, function () {
        v = ba;
        D = mb
      }));
      P = {
        x: 0,
        y: 0,
        width: E,
        height: Q,
        r: P,
        stroke: R,
        "stroke-width": U,
        fill: L || "none",
        ishot: f.legendAllowDrag
      };
      l ? l.attr(P) : l = h.box = c.rect(P, k);
      l.shadow(f && f.shadow);
      e ? (Pa = Q - X, l = "," + E + "," + Pa, s = h.elementGroup = c.group("legenditems", k).attr({"clip-rect": "0," + Z + l}), g = h.scroller || (h.scroller = c.scroller(E - 10 + Z - U, T, 10, Q - U, !1, {
          scrollPosition: g.scrollPosition || 0,
          scrollRatio: (Pa + X) / f.totalHeight,
          showButtons: !1,
          displayStyleFlat: g.flatScrollBars
        },
        k)), g.attr("fill", f.legendScrollBgColor).scroll(function (b) {
        s.transform(["T", 0, (Pa - f.totalHeight) * b]);
        m(a.fusionCharts.jsVars._reflowData, {hcJSON: {legend: {scroll: {position: b}}}}, !0)
      })) : s = h.elementGroup = k;
      if (f.title && f.title.text !== S) {
        switch (f.title.align) {
          case "start":
            e = X;
            break;
          case "end":
            e = E - X - (e ? 10 : 0);
            break;
          default:
            e = .5 * E
        }
        P = {
          "class": "fusioncharts-caption",
          "text-anchor": f.title.align,
          text: f.title.text,
          title: f.title.originalText || "",
          x: e,
          y: X,
          fill: f.title.style.color,
          "vertical-align": "top",
          direction: d.textDirection,
          "line-height": f.title.style.lineHeight
        };
        n ? n.attr(P) : n = h.caption = c.text(P, s).attr("class", "fusioncharts-caption")
      }
      this["draw" + J(f.type || "point") + "LegendItem"](b)
    }, drawPointLegendItem: function (a) {
      var b = this, c = b.paper, d = b.options, g = d.series, h = d.chart, k = h.defaultSeriesType, d = d.legend, l = d.legendHeight, n = d.symbolPadding, s = d.textPadding || 2, q = ka(d.padding, 4), h = h.textDirection, A = d.itemHoverStyle, C = d.itemHiddenStyle, B = d.itemStyle, E = B.color, C = C && C.color || "#CCCCCC", G = A && A.color || E, A = d.symbol3DLighting, K = d.symbolWidth,
        Q = !1 !== d.interactiveLegend, P = a.elements, J = P.elementGroup;
      a = a.item = [];
      var P = P.item = [], L = [], R = {
        line: !0,
        spline: !0,
        scatter: !0,
        bubble: !0,
        dragnode: !0,
        zoomline: !0
      }, Y, U, S, T, X, aa, ub, Pa, Cb, mb, r, w, v, D, e, ja, N, W, da, Wa, Sa, sa;
      v = 0;
      for (D = g.length; v < D; v += 1)if (Y = g[v], Pa = Y.legendCosmetics || {}, Y && !1 !== Y.showInLegend)if (Cb = Y.type || k, "point" === Y.legendType)for (Pa = Y.data || [], S = 0, X = Pa.length; S < X; S += 1)Y = Pa[S] || {}, !1 !== Y.showInLegend && (Y._legendType = Cb, L.push(Y)); else switch (Y._legendType = Cb, Cb) {
        case "pie":
        case "pie3d":
        case "funnel":
        case "pyramid":
          L =
            Y.data;
          break;
        default:
          L.push(Y)
      }
      L.sort(function (a, b) {
        return (a.legendIndex || 0) - (b.legendIndex || 0) || a.__i - b.__i
      });
      d.reversed && L.reverse();
      g = d.initialItemX || 0;
      k = d.initialItemY || 0;
      X = function (a) {
        var c = this.data("legendItem"), d = c.getEventArgs ? c.getEventArgs() : {}, e;
        a = z(b.logic.chartInstance.ref, a);
        d.chartX = a.chartX;
        d.chartY = a.chartY;
        d.pageX = a.pageX;
        d.pageY = a.pageY;
        d.preventDefaults = function () {
          e = !0
        };
        t.raiseEvent("LegendItemClicked", d, b.logic.chartInstance);
        Q && !e && c.legendClick()
      };
      aa = function (a) {
        var c = this.data("legendItem"),
          d = c.getEventArgs ? c.getEventArgs() : {};
        a = z(b.logic.chartInstance.ref, a);
        var e = !1 !== c.visible, c = c.plot.legend.elements.legendItemText;
        d.chartX = a.chartX;
        d.chartY = a.chartY;
        d.pageX = a.pageX;
        d.pageY = a.pageY;
        e && c && c.attr({fill: G});
        t.raiseEvent("LegendItemRollover", d, b.logic.chartInstance)
      };
      ub = function (a) {
        var c = this.data("legendItem"), d = c.getEventArgs ? c.getEventArgs() : {};
        a = z(b.logic.chartInstance.ref, a);
        var e = !1 !== c.visible, c = c.plot.legend.elements.legendItemText;
        d.chartX = a.chartX;
        d.chartY = a.chartY;
        d.pageX =
          a.pageX;
        d.pageY = a.pageY;
        e && c && c.attr({fill: E});
        t.raiseEvent("LegendItemRollout", d, b.logic.chartInstance)
      };
      b.addCSSDefinition(".fusioncharts-legend .fusioncharts-legenditem", d.itemStyle);
      v = 0;
      for (D = L.length; v < D; v += 1)!1 !== L[v].showInLegend && (sa = {
        elements: {},
        hiddenColor: C,
        itemTextColor: E,
        hoverColor: G
      }, a.push(sa), P.push(sa.elements), Y = L[v], Pa = Y.legendCosmetics || {}, mb = g + Y._legendX + q, r = k + Y._legendY - q, w = Y._legendH, U = Y._legendType || Cb, S = !1 !== Y.visible, T = sa.itemLineColor = ha(Y.color || {}), Y.plot.legend = sa, sa.elements.legendItemText =
        c.text({
          "class": "fusioncharts-legenditem",
          x: mb + l + s - 2,
          y: r + (Y._legendTestY || 0),
          text: Y.name,
          fill: S ? E : C,
          "vertical-align": "top",
          direction: h,
          "text-anchor": "start",
          cursor: B.cursor || "pointer",
          ishot: Q,
          "line-height": B.lineHeight,
          "font-size": B.fontSize
        }, J).data("legendItem", Y), Pa.customLegendIcon ? (Y && (W = Y.marker) && !1 !== W.enabled ? (da = W.lineColor, da = ba(u(Pa.borderColor, da.FCcolor && da.FCcolor.color.split(",")[0] || da, T), u(Pa.alpha, Z)), Wa = W.fillColor, Wa = ba(u(Pa.bgColor, Wa && Wa.FCcolor && Wa.FCcolor.color.split(",")[0],
        Wa, T), u(Pa.bgAlpha, Pa.alpha, Z))) : (Wa = u(Pa.bgColor, Y.color && Y.color.FCcolor && Y.color.FCcolor.color || Y.color), da = eb(Wa, 60).replace(fa, xa), da = ba(u(Pa.borderColor, da), u(Pa.alpha, Z)), Wa = ba(Wa, u(Pa.bgAlpha, Pa.alpha, Z))), sa.symbolColor = ha(Wa), sa.symbolStroke = ha(da), e = .5 * K, T = mb + n + e, U = r + (Y._markerYGutter || 0) + n + e, Sa && (e *= .6), ja = Pa.symbol.split("_"), N = "spoke" === ja[0] ? 1 : 0, U = ja[1] ? sa.elements.legendItemSymbol = c.polypath(ja[1], T, U, e, Pa.startAngle, N, J) : sa.elements.legendItemSymbol = c.circle(T, U, e, J), U.data("legendItem",
        Y).attr({
          cursor: B.cursor || "pointer",
          stroke: S ? sa.symbolStroke : C,
          fill: S ? sa.symbolColor : C,
          "stroke-width": ka(Pa.borderThickness, 1),
          ishot: Q
        })) : R[U] ? (U = r + (Y._markerYGutter || 0) + n + .5 * K, Y.lineWidth && (Sa = sa.elements.legendItemLine = c.path({
        "stroke-width": Y.lineWidth,
        stroke: S ? T : C,
        cursor: B.cursor || "pointer",
        ishot: Q,
        path: ["M", mb + n, U, "L", mb + n + K, U]
      }, J).data("legendItem", Y)), Y && (W = Y.marker) && !1 !== W.enabled && (sa.symbolStroke = ha(u((da = W.lineColor) && (da.FCcolor && da.FCcolor.color.split(",")[0] || da), T)), A ? W.fillColor &&
      W.fillColor.FCcolor ? (Pa = m({}, W.fillColor), Pa.FCcolor.alpha = "100") : Pa = u(W.fillColor, T) : Pa = {
        FCcolor: {
          color: u((Wa = W.fillColor) && (Wa.FCcolor && Wa.FCcolor.color.split(",")[0] || Wa), T),
          angle: 0,
          ratio: "0",
          alpha: "100"
        }
      }, sa.symbolColor = ha(Pa), e = .5 * K, T = mb + n + e, U = r + (Y._markerYGutter || 0) + n + e, Sa && (e *= .6), ja = W.symbol.split("_"), N = "spoke" === ja[0] ? 1 : 0, U = ja[1] ? sa.elements.legendItemSymbol = c.polypath(ja[1], T, U, e, W.startAngle, N, J) : sa.elements.legendItemSymbol = c.circle(T, U, e, J), U.data("legendItem", Y).attr({
        cursor: B.cursor ||
        "pointer", stroke: S ? sa.symbolStroke : C, fill: S ? sa.symbolColor : C, "stroke-width": 1, ishot: Q
      }))) : (Pa = b.getSymbolPath(mb + n, r + (Y._markerYGutter || 0) + n, K, K, U, Y, !A), sa.symbolColor = ha(Pa.color), sa.symbolStroke = ha(Pa.strokeColor), U = sa.elements.legendItemSymbol = c.path({
        path: Pa.path,
        "stroke-width": Pa.strokeWidth,
        stroke: S ? sa.symbolStroke : C,
        fill: S ? sa.symbolColor : C,
        cursor: B.cursor || "pointer",
        ishot: Q
      }, J).data("legendItem", Y)), sa.elements.legendItemBackground = c.rect({
        x: mb, y: r, width: Y._totalWidth, height: w, r: 0, fill: ha(Y.legendFillColor ||
        f), "stroke-width": 1, stroke: ha(Y.legendBorderColor || "none"), cursor: B.cursor || "pointer", ishot: Q
      }, J).click(X).mouseover(aa).mouseout(ub).data("legendItem", Y));
      d.reversed && L.reverse()
    }, drawCaption: function () {
      var a = this.options.chart, b = this.options.title, c = this.options.subtitle, d = this.paper, f = this.smartLabel, g = this.elements, h = this.layers, k = h.caption, l = g.caption, m = g.subcaption, n = b.text, s = c && c.text, q = b.x, u;
      !n && !s || k || (k = h.caption = d.group("caption"), h.tracker ? k.insertBefore(h.tracker) : k.insertAfter(h.dataset));
      n ? (this.addCSSDefinition(".fusioncharts-caption", b.style), u = {
        "class": "fusioncharts-caption",
        text: b.text,
        fill: b.style.color,
        x: q,
        y: b.y || a.spacingTop || 0,
        "text-anchor": b.align || "middle",
        "vertical-align": b.verticalAlign || "top",
        visibility: "visible",
        direction: a.textDirection,
        title: b.originalText || ""
      }, l ? l.attr(u) : l = g.caption = d.text(u, k).attr("class", "fusioncharts-caption"), l.css(b.style), f ? (f.setStyle(b.style), u = f.getOriSize(b.text).height) : u = 10) : l && (l = g.caption = l.remove());
      s ? (this.addCSSDefinition(".fusioncharts-subcaption",
        c.style), u = {
        "class": "fusioncharts-subcaption",
        text: c.text,
        title: c.originalText || "",
        fill: c.style.color,
        x: q,
        y: n ? l.attrs.y + u + 2 : b.y || a.spacingTop || 0,
        "text-anchor": b.align || "middle",
        "vertical-align": "top",
        direction: a.textDirection,
        visibility: "visible"
      }, m ? m.attr(u) : m = g.subcaption = d.text(u, k).attr("class", "fusioncharts-subcaption"), m.css(c.style)) : m && (g.subcaption = m.remove());
      n || s || !k || (h.caption = k.remove())
    }, drawLogo: function () {
      var b = this, c = b.paper, d = b.elements, f = b.options, g = f.credits, h = f.chart || {}, k = h.borderWidth ||
        0, l = b.chartHeight, m = b.chartWidth, n = d.logoImage, s = h.logoURL, u = h.logoAlpha / 100, A = h.logoPosition, C = h.logoLink, B = h.logoScale, G = h.logoLeftMargin, K = h.logoTopMargin, f = {
        tr: {
          vAlign: Xa,
          hAlign: lb
        }, bl: {vAlign: Va, hAlign: xb}, br: {vAlign: Va, hAlign: lb}, cc: {vAlign: "middle", hAlign: "middle"}
      }, Q, P, J;
      b.logic && g.enabled && c.text().attr({
        text: g.text,
        x: 6,
        y: l - 4,
        "vertical-align": Va,
        direction: h.textDirection,
        "text-anchor": "start",
        fill: "rgba(0,0,0,0.5)",
        title: g.title || ""
      }).css({
        fontSize: 9, fontFamily: "Verdana,sans", cursor: "pointer",
        _cursor: "hand"
      }).click(function () {
        try {
          E.open(g.href)
        } catch (a) {
          (E.top || E).location.href = g.href
        }
      });
      s && (Q = new a, (J = f[A]) || (J = {vAlign: Xa, hAlign: xb}), Q.onload = function () {
        b.disposed || c.disposed || (P = za("none", J.vAlign, J.hAlign, B, k, m, l, Q), q && (P.w = P.width || 0, P.h = P.height || 0), P.src = s, n = b.paper.image(P).translate(G, K).css("opacity", u), C && n.css({
          cursor: "pointer",
          _cursor: "hand"
        }), n.mouseover(function (a) {
          a = z(b.logic.chartInstance.ref, a);
          t.raiseEvent("LogoRollover", {
            logoURL: s, logoAlpha: 100 * u, logoPosition: A || "tl",
            logoScale: B, logoLink: C, chartX: a.chartX, chartY: a.chartY, pageX: a.pageX, pageY: a.pageY
          }, b.logic.chartInstance)
        }), n.mouseout(function (a) {
          a = z(b.logic.chartInstance.ref, a);
          t.raiseEvent("LogoRollout", {
            logoURL: s,
            logoAlpha: 100 * u,
            logoPosition: A || "tl",
            logoScale: B,
            logoLink: C,
            chartX: a.chartX,
            chartY: a.chartY,
            pageX: a.pageX,
            pageY: a.pageY
          }, b.logic.chartInstance)
        }), n.click(function (a) {
          a = z(b.logic.chartInstance.ref, a);
          t.raiseEvent("LogoClick", {
            logoURL: s, logoAlpha: 100 * u, logoPosition: A || "tl", logoScale: B, logoLink: C, chartX: a.chartX,
            chartY: a.chartY, pageX: a.pageX, pageY: a.pageY
          }, b.logic.chartInstance, void 0, function () {
            C && h.events.click.call({link: C})
          })
        }), t.raiseEvent("LogoLoaded", {
          logoURL: s,
          logoAlpha: 100 * u,
          logoPosition: A || "tl",
          logoScale: B,
          logoLink: C
        }, b.logic.chartInstance))
      }, Q.onerror = function (a) {
        t.raiseEvent("LogoLoadError", {
          logoURL: s,
          logoAlpha: 100 * u,
          logoPosition: A || "tl",
          logoScale: B,
          logoLink: C,
          error: a
        }, b.logic.chartInstance)
      }, Q.src = s, d.logoImage = n)
    }, getEventArgs: function (a) {
      a = a || {};
      return {
        datasetName: a.name, datasetIndex: a.index,
        id: a.userID, visible: a.visible
      }
    }, legendClick: function (a, b) {
      var c = a.legend, d = c && c.elements, f = d && d.legendItemText, g = d && d.legendItemSymbol, d = d && d.legendItemLine, h = c && c.hiddenColor, k = c && c.itemLineColor, l = c && c.itemTextColor, m = c && c.symbolColor, n = c && c.symbolStroke, c = u(b, !a.visible);
      a.setVisible(b);
      c ? (g && g.attr({
        fill: m || k,
        stroke: n
      }), f && f.attr({fill: l}), d && d.attr({stroke: k})) : (g && g.attr({
        fill: h,
        stroke: h
      }), f && f.attr({fill: h}), d && d.attr({stroke: h}));
      if ((f = this.datasets && this.datasets[a.index] && this.datasets[a.index].relatedSeries) &&
        f instanceof Array && 0 < f.length)for (g = f.length; g--;)d = parseFloat(f[g]), d = this.plots[d], d.legendClick.call(d, c, !1)
    }, exportChart: function (c) {
      var d = this, f = d.fusionCharts, g = d.options;
      c = "object" === typeof c && function (a) {
        var b = {}, c;
        for (c in a)b[c.toLowerCase()] = a[c];
        return b
      }(c) || {};
      var h = m(m({}, g.exporting), c), k = (h.exportformat || "png").toLowerCase(), n = h.exporthandler, s = (h.exportaction || S).toLowerCase(), q = h.exporttargetwindow || S, u = h.exportfilename, A = h.exportparameters, z = h.exportcallback, C = h.exportwithimages,
        B = h.exportatclientside;
      if (!g.exporting || !g.exporting.enabled || !n)return !1;
      t.raiseEvent("beforeExport", h, f, void 0, function () {
        function c() {
          var a;
          if ("download" === s) {
            /webkit/ig.test(E.navigator.userAgent) && "_self" === q && (q = ha = R + "export_iframe", d.exportIframe || (d.exportIframe = X = ga("IFRAME", {
              name: ha,
              width: "1px",
              height: "1px"
            }, l.body), X.style.cssText = "position:absolute;left:-10px;top:-10px;"));
            ka = ga("form", {method: "POST", action: n, target: q, style: "display:none;"}, l.body);
            for (a in T)ga("input", {
              type: "hidden",
              name: a, value: T[a]
            }, ka);
            ka.submit();
            l.body.removeChild(ka);
            ka = void 0;
            return !0
          }
          r = new t.ajax(function (a) {
            var c = {};
            a.replace(RegExp("([^?=&]+)(=([^&]*))?", "g"), function (a, b, d, e) {
              c[b] = e
            });
            z && E[z] && "function" === typeof E[z] && E[z].call(E, c);
            b.raiseEvent("exported", c, f)
          }, function (a) {
            a = {statusCode: 0, statusMessage: "failure", error: a, DOMId: R, width: U.width, height: U.height};
            z && E[z] && "function" === typeof E[z] && E[z].call(E, a);
            b.raiseEvent("exported", a, f, [a])
          });
          for (a in T)T.hasOwnProperty(a) && (T[a] = encodeURIComponent(T[a]));
          r.post(n, T)
        }

        function g() {
          -1 !== w.indexOf("<image ") ? D ? (ja = (e = w.match(/<image [^\>]*\>/gi)) && e.length, ba = function (a) {
            a = a && a.split("/");
            a = a[a.length - 1].split(".");
            return {name: a[0], type: a[1] || "png"}
          }, Z = function (b, c, d, e, f) {
            var g = new a;
            g.onload = function () {
              var a = "image/" + d, h = l.createElement("canvas"), k = h.getContext("2d"), p = "";
              h.width = g.width;
              h.height = g.height;
              k.drawImage(g, 0, 0);
              p = h.toDataURL(a);
              fa[b] = p;
              sa(p, c, d, e, f)
            };
            g.onerror = function () {
              da()
            };
            g.src = b
          }, sa = function (a, b, c, d, e) {
            W["image_" + N] = {
              name: b, type: c,
              encodedData: a, width: d, height: e
            };
            da()
          }, da = function () {
            var a = {}, b, d, f, g, h, k = !1;
            N < ja ? (b = e[N].replace(/\"/g, ""), b.split(" ").forEach(function (b) {
              b = b.split("=");
              a[b[0]] = b[1]
            }), a["xlink:href"] && (b = (d = ba(a["xlink:href"])) && d.name || "temp_image_" + N, f = d && d.type || "png", g = parseInt(a.width, 10), h = parseInt(a.height, 10), d = b + "." + f, fa[a["xlink:href"]] ? k = !0 : Z(a["xlink:href"], b, f, g, h)), b = 'xlink:href="' + a["xlink:href"], w = w.replace(b, 'xlink:href="temp/' + d), N += 1, k && da()) : (T.encodedImgData = JSON.stringify(W), T.stream = w, c())
          },
            da()) : c() : c()
        }

        function m() {
          var a = u + "." + k;
          "svg" == k ? Q(function (b) {
            G("url", b, a)
          }) : K(k, O)
        }

        function I() {
          var a = E.navigator.userAgent, b;
          return -1 != (b = a.indexOf("Chrome")) ? {
            name: "Chrome",
            version: a.substring(b + 7, b + 11)
          } : -1 != (b = a.indexOf("MSIE")) || -1 != (b = a.indexOf("rv")) && -1 != a.indexOf("Trident") ? {
            name: "ie",
            version: a.substring(b + 5, b + 9)
          } : -1 != (b = a.indexOf("Firefox")) ? {
            name: "Firefox",
            version: a.substring(b + 8, b + 12)
          } : -1 != (b = a.indexOf("Safari")) ? {
            name: "Safari",
            version: a.substring(b + 7, b + 11)
          } : {name: "default", version: "Not Known"}
        }

        function O(a, b) {
          var c = u + "." + k, d;
          switch (a) {
            case "pdf":
              d = new aa(U.height, U.width);
              d.addImage(b);
              d = d.getDataUrl();
              G("url", d, c);
              break;
            default:
              G("url", b, c)
          }
        }

        function G(a, b, c) {
          var d = I(), e;
          if ("Chrome" == d.name || "Firefox" == d.name)"blob" == a && (b = E.URL.createObjectURL(b)), e = l.createElement("a"), e.download = c, e.href = b, l.body.appendChild(e), e.onclick = function () {
            e.parentNode.removeChild(e)
          }, e.click()
        }

        function K(b, c) {
          var d = I();
          "Chrome" != d.name && "Firefox" != d.name || Q(function (d) {
            var e, f, g;
            d = E.URL.createObjectURL(J(d));
            e = l.createElement("canvas");
            f = e.getContext("2d");
            g = new a;
            g.src = d;
            g.onload = function () {
              e.width = g.width;
              e.height = g.height;
              f.drawImage(g, 0, 0);
              switch (b) {
                case "png":
                  c(b, e.toDataURL("image/png"));
                  break;
                case "jpeg":
                  c(b, e.toDataURL("image/jpeg"));
                  break;
                default:
                  c(b, e.toDataURL("image/jpeg"))
              }
            }
          })
        }

        function Q(a) {
          P(v, function (b) {
            a("data:image/svg+xml;base64," + E.btoa(E.unescape(encodeURIComponent('<?xml version="1.0" standalone="no"?><!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">' +
            b))))
          })
        }

        function P(b, c) {
          if (-1 == b.indexOf("<image "))c(b); else {
            var d = 0, e, f = 1, g;
            e = b.match(/<image [^\>]*\>/gi).length;
            b = b.replace(/NS\d+:/gi, "xlink:");
            b = b.replace(/<image [^\>]*\>/gi, function (h) {
              if (-1 != b.indexOf('href="http://'))return e == f && c(b), f++, d++, "";
              h = h.replace(/(:href=")([^"]*)(")/gi, function (a, b, c, e) {
                g = c;
                return b + "{{{" + d + "}}}" + e
              });
              var k = l.createElement("canvas"), p, m;
              p = k.getContext("2d");
              m = new a;
              m.src = g;
              m.ind = d;
              m.onload = function () {
                k.width = this.width;
                k.height = this.height;
                p.drawImage(this, 0,
                  0);
                var d = k.toDataURL("image/png");
                (new a).src = d;
                b = b.replace(new RegExp("\\{\\{\\{" + m.ind + "\\}\\}\\}", "g"), d);
                e == f && c(b);
                f++
              };
              d++;
              return h
            })
          }
        }

        function J(a) {
          var b, c, d;
          b = 0 <= a.split(",")[0].indexOf("base64") ? E.atob(a.split(",")[1]) : E.unescape(a.split(",")[1]);
          a = a.split(",")[0].split(":")[1].split(";")[0];
          c = new Uint8Array(b.length);
          for (d = 0; d < b.length; d++)c[d] = b.charCodeAt(d);
          return new E.Blob([c], {type: a})
        }

        var L = d.layers.buttons, R = f.id, U = d.paper, S = t && t.hcLib, T, ha, X, ka, r, w, v, D = S && S.isCanvasElemSupported(),
          e, ja, N = 0, W = {}, ba, Z, da, sa, fa = {};
        L && L.attr("visibility", "hidden");
        w = U.toSVG(C && D && "svg" !== k);
        v = U.toSVG(C && D);
        L && L.attr("visibility", "visible");
        w = w.replace(/(\sd\s*=\s*["'])[M\s\d\.]*(["'])/ig, "$1M 0 0 L 0 0$2");
        w = w.replace(/NS\d+:/gi, "xlink:");
        v = v.replace(/(\sd\s*=\s*["'])[M\s\d\.]*(["'])/ig, "$1M 0 0 L 0 0$2");
        T = {
          charttype: f.chartType(),
          stream: w,
          stream_type: "svg",
          meta_bgColor: h.bgcolor || "",
          meta_bgAlpha: h.bgalpha || "1",
          meta_DOMId: f.id,
          meta_width: U.width,
          meta_height: U.height,
          parameters: ["exportfilename=" +
          u, "exportformat=" + k, "exportaction=" + s, "exportparameters=" + A].join("|")
        };
        B && "download" === s ? (L = I(), "undefined" == typeof E.btoa || "Chrome" != L.name && "Firefox" != L.name ? (t.raiseWarning(this, "14038823867", "run", "Export Feature", "Client side export requirement not fulfiled so exporting through server side"), g()) : m()) : g();
        t.raiseEvent("exportDataReady", T, f)
      }, function () {
        t.raiseEvent("exportCancelled", h, f)
      })
    }, print: function (a) {
      var b = this, c = m({}, a);
      if (b.isPrinting)return !1;
      t.raiseEvent("BeforePrint", c, b.logic.chartInstance,
        void 0, function () {
          var a = b.container, d = b.elements, f = d.printButton, g = d.exportButton, h = [], k = a.parentNode, d = l.body || l.getElementsByTagName("body")[0], m = d.childNodes;
          b.isPrinting = !0;
          qa(m, function (a, b) {
            1 == a.nodeType && (h[b] = a.style.display, a.style.display = "none")
          });
          !1 !== c.hideButtons && (f && "hidden" != f.attrs.visibility && f.attr({visibility: "hidden"}), g && "hidden" != g.attrs.visibility && g.attr({visibility: "hidden"}));
          d.appendChild(a);
          E.print();
          setTimeout(function () {
            f && f.attr({visibility: "visible"});
            g && g.attr({visibility: "visible"});
            k.appendChild(a);
            qa(m, function (a, b) {
              1 == a.nodeType && (a.style.display = h[b])
            });
            b.isPrinting = !1;
            t.raiseEvent("PrintComplete", c, b.logic.chartInstance)
          }, 1E3)
        }, function () {
          t.raiseEvent("PrintCancelled", c, b.logic.chartInstance)
        })
    }, getSymbolPath: function (a, b, c, d, f, g, h) {
      var k = ["M"], l, m, n;
      l = (g.color && Rb("string" === typeof g.color ? g.color : g.color.FCcolor.color) || S).replace(fa, "");
      n = eb(l, 60).replace(fa, xa);
      h ? l = {FCcolor: {color: l, angle: 0, ratio: "0", alpha: "100"}} : (h = eb(l, 40), l = {
        FCcolor: {
          color: l + "," + l + "," + h + "," + l + "," +
          l, ratio: "0,30,30,30,10", angle: 0, alpha: "100,100,100,100,100"
        }
      });
      switch (f) {
        case "column":
        case "dragcolumn":
        case "column3d":
          g = .25 * c;
          f = .5 * g;
          h = .7 * d;
          m = .4 * d;
          k = k.concat([a, b + d, "l", 0, -h, g, 0, 0, h, "z", "m", g + f, 0, "l", 0, -d, g, 0, 0, d, "z", "m", g + f, 0, "l", 0, -m, g, 0, 0, m, "z"]);
          l.FCcolor.angle = 270;
          break;
        case "bar":
        case "bar3d":
          g = .3 * c;
          f = .6 * c;
          h = d / 4;
          m = h / 2;
          k = k.concat([a, b, "L", a + f, b, a + f, b + h, a, b + h, "Z", "M", a, b + h + m, "L", a + c, b + h + m, a + c, b + h + m + h, a, b + 2 * h + m, "Z", "M", a, b + 2 * (h + m), "L", a + g, b + 2 * (h + m), a + g, b + d, a, b + d, "Z"]);
          break;
        case "area":
        case "area3d":
        case "areaspline":
        case "dragarea":
          h =
            .6 * d;
          m = .2 * d;
          d *= .8;
          k = k.concat([a, b + d, "L", a, b + h, a + .3 * c, b + m, a + .6 * c, b + h, a + c, b + m, a + c, b + d, "Z"]);
          l.FCcolor.angle = 270;
          break;
        case "pie":
        case "pie3d":
          g = .5 * c;
          f = .9 * g;
          c = a + g + 1;
          d = b + g - 1;
          a = a + g - 1;
          b = b + g + 1;
          k = k.concat(["M", c, d, "L", c, d - f + 1, "A", f - 1, f - 1, 0, 0, 1, c + f - 1, d, "Z", "M", a, b, "L", a, b - f, "A", f, f, 0, 1, 0, a + f, b, "Z"]);
          l.FCcolor.radialGradient = "1";
          l.FCcolor.ratio = "0,0,0,100,0";
          break;
        case "boxandwhisker2d":
          k = k.concat([a, b, "L", a + c, b, a + c, b + d, a, b + d, "Z"]);
          l = g.color;
          n = "#000000";
          break;
        default:
          k = k.concat([a, b, "L", a + c, b, a + c, b + d, a, b + d,
            "Z"]), l.FCcolor.angle = 270, l.FCcolor.ratio = "0,70,30"
      }
      return {path: k, color: l, strokeWidth: .5, strokeColor: n}
    }
  });
  d.prototype = {
    configure: function () {
      var a = this.axisData, b = this.renderer, c = this.isVertical, d = this.isReverse, f = b.options, g = f.chart, h = g.marginBottom, g = g.marginRight, k = b.canvasTop, l = b.canvasLeft, m = this.min = a.min, m = this.span = (this.max = a.max) - m, l = this.startX = ka(a.startX, l), k = this.startY = ka(a.startY, k), n = this.endX = ka(a.endX, b.canvasRight), a = this.endY = ka(a.endY, b.canvasBottom), m = this.pixelRatio = c ? (a - k) /
      m : (n - l) / m, s = this.relatedObj = {};
      s.marginObj = {top: k, right: g, bottom: h, left: l};
      s.canvasObj = {x: l, y: k, w: n - l, h: a - k, toX: n, toY: a};
      this.startPixel = d ? c ? a : n : c ? k : l;
      this.pixelValueRatio = d ? -m : m;
      this.primaryOffset = this.secondaryOffset = 0;
      this.cache = {lowestVal: 0, highestVal: 0, indexArr: [], hashTable: {}};
      this.elements = this.elements || {};
      this.belowBandGroup && (b.elements.axes = b.elements.axes || {}, b.elements.axes.belowBandGroup = this.belowBandGroup, f && f.chart && f.chart.hasScroll && this.belowBandGroup.attr({"clip-rect": b.elements["clip-canvas"]}));
      this.poi = {}
    }, draw: function () {
      var a = this.axisData, b = a && a.plotLines || [], c = a && a.plotBands || [], d = a && a.showLine, f = a && a.tickLength, g = a && a.tickWidth;
      a && a.title && this.drawAxisName();
      a && a.labels && (this.renderer.addCSSDefinition("." + a.labels.className + " .fusioncharts-label", a.labels.style), this.belowLabelGroup && this.belowLabelGroup.attr("class", a.labels.className), this.topLabelGroup && this.topLabelGroup.attr("class", a.labels.className));
      b && 0 < b.length && this.drawPlotLine();
      c && 0 < c.length && this.drawPlotBands();
      isNaN(f) || 0 === f || isNaN(g) || 0 === g || this.drawTicks();
      d && this.drawLine()
    }, scroll: function () {
    }, setOffset: function (a, b) {
      var c = this.primaryOffset = a, d = this.secondaryOffset = b || this.secondaryOffset, f = this.isVertical, g, h, k, l = [this.topLabelGroup, this.belowLabelGroup, this.topLineGroup, this.belowLineGroup, this.topBandGroup, this.belowBandGroup], m, n;
      m = 0;
      for (n = l.length; m < n; m += 1)if (k = l[m])g = f ? d : c, h = f ? c : d, k.attr({transform: "t" + g + "," + h});
      f || this.drawPlotLine && this.drawPlotLine()
    }, update: function () {
    }, drawTicks: function () {
      var a =
        this.axisData, b = this.renderer.paper, c = this.min, d = this.max, f = this.isVertical, g = this.layerBelowDataset, g = this.tickGroup = this.tickGroup || b.group("axis-ticks", g), h = this.relatedObj.canvasObj, k = a.offset, l = a.opposite, m = a.showAxis, n = a.tickInterval, s = a.tickLength, q = a.tickWidth, a = a.tickColor, u = c;
      if (f && m)for (c = this.getAxisPosition(c), f = this.getAxisPosition(d), h = l ? h.toX + k : h.x - k, b.path(["M", h, c, "L", h, f], g).attr({
        stroke: a,
        "stroke-width": q
      }); db(u) <= d;)k = this.getAxisPosition(u), c = l ? h + s : h - s, b.path(["M", h, k, "L", c,
        k], g).attr({stroke: a, "stroke-width": q}), u += n
    }, getAxisPosition: function (a, b) {
      var c;
      b ? c = (a - this.startPixel) / this.pixelValueRatio + this.min : (a = this.axisData.reversed ? this.min + (this.max - a) : a, c = this.startPixel + (a - this.min) * this.pixelValueRatio);
      return c
    }, drawPlotLine: function () {
      var a = this.renderer, b = a.paper, c = this.isVertical, d = +!c, g = this.lines = this.lines || [], k = this.labels = this.labels || [], l = this.relatedObj.canvasObj, m = this.globalOptions || {}, n = this.elements || {}, s = this.axisData.plotLines || [], q = this.primaryOffset,
        u = c ? this.startY : this.startX, t = c ? this.endY : this.endX, A = parseFloat(a.canvasBorderWidth) || 0, z = Ba(s.length, Ba(g.length, k.length)), C = a.layers.datalabels, B = this.belowLineGroup, E = this.topLineGroup, G = this.belowLabelGroup, K = this.topLabelGroup, Q = !1 !== (a.tooltip || {}).enabled, P = function (b) {
          return function (c) {
            Ka.call(this, a, c, b)
          }
        }, J = m.chart.xDepth || 0, m = m.chart.textDirection, L = [], R = 0, U, T, X, ba, da, Z, aa, r, w, v, D, e, ja, N, W, fa, Wa, Sa, sa, ga, F, M, $, na, pa, va, qa, xa, za, Aa, Ea, ra, Wb, Ra, ua, Da, ya, Ca, Qa, Ta, Ya, fb, ib;
      for (ib = 0; ib < z; ib +=
        1) {
        X = ba = da = null;
        X = g[ib];
        ba = k[ib];
        if (aa = s[ib])if (r = aa.width, w = aa.isVline, v = aa.isTrend, D = aa.isGrid, e = aa.tooltext, ja = aa.value, N = aa.color, W = aa.dashStyle, fa = v ? aa.to : null, Wa = aa._isStackSum, U = 3 < aa.zIndex ? E : B, Sa = aa.label) {
          sa = Sa.style;
          ga = Sa.text;
          F = sa && sa.color;
          M = Sa.offsetScaleIndex || 0;
          $ = Sa.offsetScale;
          if (na = sa && sa.fontSize)pa = na, -1 !== pa.indexOf("px") && (pa = pa.replace("px", ""), pa = parseFloat(pa));
          T = sa && sa.lineHeight;
          na = sa ? {
            fontFamily: sa.fontFamily, fontSize: sa.fontSize, lineHeight: sa.lineHeight, fontWeight: sa.fontWeight,
            fontStyle: sa.fontStyle
          } : null;
          T && (va = T, -1 !== va.indexOf("px") && (va = va.replace("px", ""), va = parseFloat(va)));
          qa = Sa.rotation;
          xa = Sa.x || 0;
          za = Sa.y || 0;
          Aa = Sa.align;
          Ea = Sa.verticalAlign;
          ra = Sa.textAlign;
          Wb = ka(parseInt(Sa.borderWidth, 10), 1);
          T = Wa ? C : 3 <= aa.zIndex ? K : G;
          Sa.backgroundColor && (Sa.labelBgClr = ha({color: Sa.backgroundColor, alpha: 100 * Sa.backgroundOpacity}));
          Sa.borderColor && (Sa.labelBorderClr = ha({color: Sa.borderColor, alpha: "100"}));
          Ra = pa ? .2 * pa : 2;
          ra = "left" === ra ? "start" : "right" === ra ? "end" : "middle"
        }
        Z = Ya = "visible";
        fb = 0 > ka($, ja, 0);
        c ? (Da = this.getAxisPosition(ja), Ca = v ? this.getAxisPosition(fa) || Da : Da, Qa = Da !== Ca ? !0 : !1, Ta = ["M", l.x, Da, "L", l.toX, Ca], w ? a.logic.isBar && (ua = a.yAxis[M], !Wa && !isNaN($) && 0 <= $ && 1 >= $ && ($ = ua.min + (ua.max - ua.min) * $), ya = ua.getAxisPosition(ka($, ja)) + xa + Ra * (fb ? -1 : 1)) : ya = Sa ? ua = this.axisData.isOpposite || "right" === Aa ? l.toX + xa : l.x + xa : ua = this.axisData.isOpposite ? l.toX : l.x) : (ua = this.getAxisPosition(ja) || 0, ya = v ? this.getAxisPosition(fa) || ua : ua, !v && !w && 0 < J && !a.logic.isBar && (ua += J, ya += J, t += J), Qa = ua !== ya ? !0 :
          !1, Ta = ["M" + ua, l.y, "L", ya, l.toY], Ya = ua + q < u || ua + q > t ? "hidden" : Ya, w ? (ua = a.yAxis[M], !Wa && !isNaN($) && 0 <= $ && 1 >= $ && ($ = ua.min + (ua.max - ua.min) * (1 - $)), Da = ua.getAxisPosition(ka($, ja)) + za, Da -= (A + parseFloat(Wb)) * (za && (0 < za ? -1 : 1))) : this.axisData.opposite || "top" === Ea && !D ? (Da = l.y + za, Ca = "bottom") : Da = l.toY + za, Ca = Da);
        Z = c ? Z : ya + q < u || ya + q > t ? "hidden" : Z;
        if (aa && "visible" === Ya && .1 < r)Qa = {
          path: Fa(Ta, r),
          stroke: N,
          "stroke-width": r,
          "shape-rendering": !Qa && 1 <= r ? "crisp" : void 0,
          "stroke-dasharray": W ? W : void 0,
          visibility: Ya
        }, X ? X.attr(Qa) :
          (X = g[ib] = b.path(Qa, U).css(aa.style), n.lines = n.lines || [], n.lines[ib] = X), Q && e && r < h && Ya && (da = b.path({
          stroke: f,
          "stroke-width": h,
          ishot: !0,
          path: Ta,
          fill: f
        }, a.layers.tracker)), da = da || X, Q && e && da.tooltip(e); else if (X || ba)X && X.remove(), X = null, g && (g[ib] = null), n && n.lines && (n.lines[ib] = null);
        Sa && aa && !aa.stepped && "visible" === Z && Sa.text != S && " " != Sa.text ? (Da = v ? "left" === Aa ? Da : Ca : Ca, da = ya - +!w * d * J + d * (xa || 0), Wa ? (Ca = c || qa ? "middle" : "bottom", Da += c ? 0 : pa * (fb ? -.4 : .4), qa && (Da += fb ? 4 : -2, ra = fb ? "end" : "start")) : d && this.axisData.opposite ?
          (Ca = Va, ra = qa ? "start" : "middle") : Ca = Ea, /\n|<br\s*?\/?>/ig.test(ga) && D && (qa ? (Ca = "middle", da -= d * (xa || 0)) : (Ca = d && this.axisData.opposite && !qa ? "middle" : Xa, Da -= va)), Qa = {
          "class": "fusioncharts-label",
          text: ga,
          fill: na ? F || N : null,
          ishot: !0,
          title: Sa && (Sa.originalText || S),
          cursor: Sa.link ? "pointer" : S,
          x: da,
          y: Da,
          "text-anchor": ra,
          "vertical-align": Ca,
          direction: m,
          transform: " ",
          "text-bound": [sa.backgroundColor || Sa.labelBgClr, sa.borderColor || Sa.labelBorderClr, sa.borderThickness || Wb, sa.borderPadding || Ra, sa.borderRadius, sa.borderDash],
          visibility: Z,
          "line-height": sa.lineHeight
        }, ba ? ba.attr(Qa) : (ba = k[ib] = b.text(Qa, T).attr("class", "fusioncharts-label"), X && (X.label = ba), n.labels = n.labels || [], n.labels[ib] = ba, aa.isDataLabel && ba.click(P("dataLabelClick")).hover(P("dataLabelRollOver"), P("dataLabelRollOut"))), na && ba.css(na), aa.isDataLabel && (Z = {
          text: ga,
          index: R,
          link: Sa.link
        }, R += 1, ba.data("eventArgs", Z)), qa && ba.attr("transform", ["r", qa, da, Da]), Wa && ba && L.push(ba)) : ba && (ba.isRotationSet = !1, ba.remove(), k && (k[ib] = null), n && n.labels && (n.labels[ib] =
          null));
        !X && !ba || aa && null === aa.value || (aa && aa.isMinLabel ? this.poi.min = {
          label: ba,
          index: ib,
          line: X
        } : aa && aa.isMaxLabel ? this.poi.max = {
          label: ba,
          index: ib,
          line: X
        } : aa && aa.isZeroPlane && (this.poi.zero = {label: ba, index: ib, line: X}));
        X = ba = null
      }
      ka(a.options.plotOptions.series.animation.duration, 0)
    }, drawPlotBands: function () {
      var a = this.renderer, b = a.paper, c = this.isVertical, d = this.axisData.plotBands || [], f = this.bands = this.bands || [], g = this.bandLabels = this.bandLabels || [], h = this.relatedObj.canvasObj, k = this.primaryOffset,
        l = c ? this.startY : this.startX, m = c ? this.endY : this.endX, n = a.options.chart.hasScroll, s = this.belowBandGroup, q = this.topBandGroup, u = this.belowLabelGroup, t = this.topLabelGroup, A = this.elements || {}, z = a.options.chart.textDirection, a = !1 !== (a.tooltip || {}).enabled, C, B, E, G, K, Q, P, J, L, R, U, T, S, X, aa, r, w, v, D, e, ja, N, W, ba, da, Z, fa, $, F, M, ga, na = Ba(d.length, f.length);
      for (ga = 0; ga < na; ga += 1) {
        M = "visible";
        $ = f[ga];
        F = g[ga];
        if (C = d[ga])if (B = C.tooltext, E = C.to, G = C.from, K = C.value, Q = C.width, P = C.color, Z = 3 < C.zIndex ? q : s, J = C.label) {
          if (L = J.style)(S =
            L.fontSize) && -1 !== S.indexOf("px") && (S = S.replace("px", ""), parseFloat(S)), (S = L.lineHeight) && -1 !== S.indexOf("px") && (S = S.replace("px", ""), parseFloat(S)), S = L.color;
          v = (R = J.borderWidth) ? -1 !== R.indexOf("px") ? R.replace("px", "") : 1 : 1;
          R = J.align;
          U = J.x;
          T = J.y;
          r = J.text;
          w = J.originalText;
          X = J.backgroundColor;
          aa = J.backgroundOpacity;
          X && (J.labelBgClr = ha({color: X, alpha: 100 * aa}));
          if (X = J.borderColor)J.labelBorderClr = ha({color: X, alpha: "100"});
          X = J.textAlign;
          X = "left" === X ? "start" : "right" === X ? "end" : "middle";
          aa = J.verticalAlign;
          fa = 3 < C.zIndex ? t : u
        }
        D = this.getAxisPosition(ka(E, K));
        e = this.getAxisPosition(ka(G, K));
        ja = c ? h.x : e;
        N = c ? D : h.y;
        W = c ? h.w : (this.axisData.reversed ? e - D : D - e) || Q || 1;
        e = c ? e - D || 1 : h.h;
        D = ja + W;
        W = jb(W);
        0 > e && (e = jb(e), N -= e);
        c || (M = n ? "hidden" : ja + k > m || D + k < l ? "hidden" : M);
        J && (ba = c ? "right" === R ? h.toX + U : h.x + U : ja + W / 2, da = c ? N + e / 2 : h.toY + T);
        if (!$ && C && "visible" === M)C = {
          x: ja,
          y: N,
          width: W,
          height: e,
          fill: ha(P),
          "stroke-width": 0
        }, $ ? $.attr(C) : ($ = f[ga] = b.rect(C, Z), A.bands = A.bands || [], A.bands[ga] = $), a && B && $.tooltip(B); else if ($ && (!C || "hidden" ===
          M)) {
          A.bandLabels && (g[ga] = A.bandLabels[ga] = null);
          $.label && $.label.remove();
          f[ga] = A.bands[ga] = null;
          $.remove();
          continue
        }
        $ && J && J.text && (C = {
          "class": "fusioncharts-label",
          text: r,
          title: w || "",
          fill: S,
          "text-bound": [L.backgroundColor || J.labelBgClr, L.borderColor || J.labelBorderClr, L.borderThickness || v, L.borderPadding || 2, L.borderRadius, L.borderDash],
          x: ba,
          y: da,
          "text-anchor": X,
          direction: z,
          "vertical-align": aa,
          "line-height": L.lineHeight
        }, F ? F.attr(C) : (F = g[ga] = $.label = b.text(C, fa).attr("class", "fusioncharts-label"), L &&
        F.css(L), A.bandLabels = A.bandLabels || [], A.bandLabels[ga] = F))
      }
    }, drawAxisName: function () {
      var a = this.axisData, b = a.title || {}, c = b && b.style, d = b && b.className, f = b.align, g = b.centerYAxisName || !1, h = this.renderer.paper, k = this.isVertical, l = this.relatedObj.canvasObj, m = ka(a.offset, 0) + ka(b.margin, 0), n = b.text || "", s = this.name || void 0, a = a.opposite, q = this.layerBelowDataset, q = q.nameGroup = q.nameGroup || h.group("axis-name", q), u = ka(b.rotation, a ? 90 : 270), t = k ? a ? l.toX + m : l.x - m : (l.x + l.toX) / 2, A = {
        fontFamily: c.fontFamily, fontSize: c.fontSize,
        lineHeight: c.lineHeight, fontWeight: c.fontWeight, fontStyle: c.fontStyle
      }, z, g = k ? "low" === f ? l.toY : g ? (l.y + l.toY) / 2 : this.renderer.chartHeight / 2 : l.toY + m;
      n ? (!isNaN(u) && u && k && (z = c.fontSize, z = -1 != z.indexOf("px") ? z.replace("px", "") : z, a ? (t += parseFloat(z), z = 270 === u ? "bottom" : "top") : (t -= parseFloat(z), z = 270 === u ? "top" : "bottom")), this.renderer.addCSSDefinition("." + d, A), d = {
        "class": d,
        x: 0,
        y: 0,
        text: n,
        fill: c.color,
        direction: this.renderer.options.chart.textDirection,
        "text-anchor": "low" === f ? 90 == u ? "end" : "start" : "middle",
        "vertical-align": k ?
          u ? z : "middle" : a ? Va : "top",
        transform: k ? "t" + t + "," + g + "r" + u : "t" + t + "," + g,
        "font-size": c.fontSize
      }, b.originalText && (d.title = b.originalText), s ? s.attr(d) : s = this.name = h.text(d, q), setTimeout(function () {
        s.attr({
          "line-height": c.lineHeight,
          "text-bound": [c.backgroundColor, c.borderColor, c.borderThickness, c.borderPadding, c.borderRadius, c.borderDash]
        })
      }, 0)) : s && s.remove();
      this.elements.name = s
    }, drawLine: function () {
      var a = this.axisData, b = this.renderer.paper, c = this.min, d = this.max, f = this.isVertical, g = a.opposite, h = this.layerBelowDataset,
        h = this.lineGroup = this.lineGroup || b.group("axis-lines", h), k = a.lineColor, l = a.lineThickness, m = a.lineEndExtension || 0, n = a.lineStartExtension || 0, a = this.relatedObj.canvasObj;
      f ? (c = this.getAxisPosition(c) - n, m = this.getAxisPosition(d) + m, d = f = g ? a.toX + l / 2 : a.x - l / 2) : (d = a.x - n, f = a.toX + m, c = m = g ? a.y - l / 2 : a.toY + l / 2);
      b = b.path({path: ["M", d, c, "L", f, m], stroke: k, "stroke-width": l}, h);
      this.elements.axisLine = b
    }, realtimeUpdateX: function (a) {
      if (0 < a) {
        for (var b = this.axisData.plotBands, c = this.min + a, d, f = b.length; f--;)(d = b[f]) && !d.isNumVDIV &&
        (d.value < c || d.from < c || d.to < c ? b.splice(f, 1) : (void 0 !== d.value && (d.value -= a), void 0 !== d.from && (d.from -= a), void 0 !== d.to && (d.to -= a)));
        this.drawPlotLine();
        this.drawPlotBands()
      }
    }, realtimeUpdateY: function (a, b) {
      var c = this.axisData, d = this.min = c.min = a, c = this.span = (this.max = c.max = b) - d, c = this.pixelRatio = this.isVertical ? this.relatedObj.canvasObj.h / c : this.relatedObj.canvasObj.w / c;
      this.pixelValueRatio = this.isReverse ? -c : c;
      this.drawPlotLine();
      this.drawPlotBands()
    }
  };
  d.prototype.constructor = d;
  G("renderer.cartesian",
    {
      drawCanvas: function () {
        var a = this.options, b = a.chart || {}, c = b.plotBackgroundColor, d = this.paper, f = this.elements, g = f.canvas, h = f.canvas3DBase, k = f.canvas3dbaseline, h = f.canvasBorder, l = f.canvasBg, m = this.canvasTop, n = this.canvasLeft, s = this.canvasWidth, u = this.canvasHeight, t = ka(b.plotBorderRadius, 0), l = b.plotBorderWidth, A = .5 * l, z = b.plotBorderColor, C = b.isBar, B = b.is3D, E = b.use3DLighting, G = b.showCanvasBg, J = b.canvasBgDepth, Q = b.showCanvasBase, P = b.canvasBaseColor3D, L = b.canvasBaseDepth, R = b.plotShadow, U = q && 0 === l && R && R.enabled,
          T = b.xDepth || 0, a = b.yDepth || (a[Aa] && a[Aa].isScroll ? m : 0), b = this.layers, S = b.background, X = b.dataset;
        b.tracker = b.tracker || d.group("hot").insertAfter(X);
        b.datalabels = b.datalabels || d.group("datalabels").insertAfter(X);
        b = b.canvas = b.canvas || d.group("canvas").insertAfter(S);
        h || (f.canvasBorder = d.rect({
          x: n - A,
          y: m - A,
          width: s + l,
          height: u + l,
          r: t,
          "stroke-width": l,
          stroke: z,
          "stroke-linejoin": 2 < l ? "round" : "miter"
        }, b).shadow(R));
        f["clip-canvas"] = [Ba(0, n - T), Ba(0, m - a), Ba(1, s + 2 * T), Ba(1, u + a)];
        f["clip-canvas-init"] = [Ba(0, n - T),
          Ba(0, m - a), 1, Ba(1, u + 2 * a)];
        B && (G && (l = C ? f.canvasBg = d.path(["M", n, ",", m, "L", n + 1.2 * J, ",", m - J, ",", n + s - J, ",", m - J, ",", n + s, ",", m, "Z"], b) : f.canvasBg = d.path(["M", n + s, ",", m, "L", n + s + J, ",", m + 1.2 * J, ",", n + s + J, ",", m + u - J, ",", n + s, ",", m + u, "Z"], b), l.attr({
          "stroke-width": 0,
          stroke: "none",
          fill: ha(c)
        })), Q && (h = C ? f.canvas3DBase = d.cubepath(n - T - L - 1, m + a + 1, L, u, T + 1, a + 1, b) : f.canvas3DBase = d.cubepath(n - T - 1, m + u + a + 1, s, L, T + 1, a + 1, b), h.attr({
          stroke: "none",
          "stroke-width": 0,
          fill: [P.replace(fa, xa), !E]
        }), k || (k = f.canvas3dbaseline = d.path(void 0,
          b)), k.attr({
          path: C ? ["M", n, m, "V", u + m] : ["M", n, m + u, "H", s + n],
          stroke: K.tintshade(P.replace(fa, xa), .05).rgba
        })));
        !g && c && (f.canvas = d.rect({
          x: n,
          y: m,
          width: s,
          height: u,
          r: t,
          "stroke-width": 0,
          stroke: "none",
          fill: ha(c)
        }, b).shadow(U))
      }, drawAxes: function () {
      var a = this.logic, b = this.options, c = this.paper, f = this.layers, g = f.dataset, h = f.layerBelowDataset = f.layerBelowDataset || c.group("axisbottom").trackTooltip(!0), k = f.layerAboveDataset = f.layerAboveDataset || c.group("axistop").trackTooltip(!0), c = this.xAxis = [], f = this.yAxis = [];
      h.insertBefore(g);
      k.insertAfter(g);
      if (b.xAxis && b.xAxis.length)for (g = 0, h = b.xAxis.length; g < h; g += 1)c[g] = this.xAxis[g] = new d(b.xAxis[g], this, a.isBar); else c[0] = this.xAxis[0] = new d(b.xAxis, this, a.isBar);
      if (b.yAxis)for (g = 0, h = b.yAxis.length; g < h; g += 1)f[g] = this.yAxis[g] = new d(b.yAxis[g], this, !a.isBar, !a.isBar);
      g = 0;
      for (h = f.length; g < h; g += 1)f[g].axisData && (f[g].axisData.title && (f[g].axisData.title.className = "fusioncharts-yaxis-" + g + "-title"), f[g].axisData.labels && (f[g].axisData.labels.className = "fusioncharts-yaxis-" +
      g + "-gridlabels")), f[g].draw();
      g = 0;
      for (h = c.length; g < h; g += 1)c[g].axisData && (c[g].axisData.title && (c[g].axisData.title.className = "fusioncharts-xaxis-" + g + "-title"), c[g].axisData.labels && (c[g].axisData.labels.className = "fusioncharts-xaxis-" + g + "-gridlabels")), c[g].draw()
    }, drawScroller: function () {
      var a = this, b = a.options, c = a.paper, d = a.layers, f = a.xAxis["0"] || {}, g = f.axisData || {}, h = g.scroll || {}, k = a.canvasTop, l = a.canvasLeft, n = a.canvasWidth, s = a.canvasHeight, q = a.canvasBorderWidth, u = q || (g.showLine ? g.lineThickness :
          0), A = q || g.lineStartExtension, g = q || g.lineEndExtension, q = b.chart.useRoundEdges, z, C, B, E, G, J, Q, P, L, R, T, U, S, X, aa, ba = d.dataset, ha = d.datalabels, da = d.tracker;
      E = d.layerAboveDataset;
      var r, w;
      h.enabled && (r = d.scroll = d.scroll || c.group("scroll").insertAfter(E), E = h.scrollRatio, b = ka(b[Aa].xAxisScrollPos, h.startPercent), G = h.viewPortMax, J = h.viewPortMin, C = h.vxLength, Q = Ca(C), P = h.showButtons, L = h.height, R = h.padding, T = h.color, U = h.flatScrollBars, C = h.windowedCanvasWidth = f.getAxisPosition(C), z = h.fullCanvasWidth = f.getAxisPosition(G -
      J) - C, B = va(b * z), S = a.fusionCharts.jsVars._reflowData, X = {hcJSON: {_FCconf: {xAxisScrollPos: 0}}}, aa = X.hcJSON._FCconf, d.scroller = c.scroller(l - A, k + s + u + R - !!u, n + A + g, L, !0, {
        showButtons: P,
        displayStyleFlat: U,
        scrollRatio: E,
        scrollPosition: b
      }, r).data("fullCanvasWidth", z).data("windowedCanvasWidth", C).attr({
        "scroll-display-style": U,
        fill: T,
        r: q && 2 || 0
      }).scroll(function (b) {
        var c;
        B = -va(b * z);
        ba && ba.transform(["T", B, 0]);
        ha && ha.transform(["T", B, 0]);
        da && da.transform(["T", B, 0]);
        f.setOffset && f.setOffset(B);
        c = {
          position: b, direction: b -
          h.lastPos || 0, vxLength: Q
        };
        aa.xAxisScrollPos = h.lastPos = b;
        m(S, X, !0);
        if (0 !== c.direction)for (w = 0; w < a.datasets.length; w++)a[a.datasets[w].drawPlot + "Scroll"] && a[a.datasets[w].drawPlot + "Scroll"].call(a, a.plots[w], a.datasets[w], c)
      }), function () {
        var b;
        K.eve.on("raphael.scroll.start." + d.scroller.id, function (c) {
          b = c;
          t.raiseEvent("scrollstart", {scrollPosition: c}, a.logic.chartInstance)
        });
        K.eve.on("raphael.scroll.end." + d.scroller.id, function (c) {
          t.raiseEvent("scrollend", {prevScrollPosition: b, scrollPosition: c}, a.logic.chartInstance)
        })
      }());
      return h.enabled
    }, finalizeScrollPlots: function () {
      var a = this, c = a.container, d = a.elements, f = a.layers, g = f.scroller, h = f.dataset, k = f.datalabels, f = f.tracker, l, m = {}, s, q = a.xAxis["0"] || {}, u = (q.axisData || {}).scroll || {}, A = ka(a.options[Aa].xAxisScrollPos, u.startPercent), z = u.fullCanvasWidth;
      u.enabled && (h.attr({"clip-rect": d["clip-canvas"]}), k.attr({"clip-rect": d["clip-canvas"]}), f.attr({"clip-rect": d["clip-canvas"]}), d = function (c) {
        var d = a.elements.canvas, f = l.left, h = l.top, k = c.state, p = T && b.getTouchEvent(c) || n;
        c =
          c.originalEvent;
        f = (c.clientX || c.pageX || p.pageX) - f;
        h = (c.clientY || c.pageY || p.pageY) - h;
        switch (k) {
          case "start":
            s = d.isPointInside(f, h);
            m.ox = s && f || null;
            if (!s)return !1;
            m.prevScrollPosition = g.attrs["scroll-position"];
            t.raiseEvent("scrollstart", {scrollPosition: m.prevScrollPosition}, a.logic.chartInstance);
            break;
          case "end":
            t.raiseEvent("scrollend", {
              prevScrollPosition: m.prevScrollPosition,
              scrollPosition: m.scrollPosition
            }, a.logic.chartInstance);
            s = !1;
            m = {};
            break;
          default:
            if (!s)break;
            d = f - m.ox;
            m.ox = f;
            m.scrollPosition =
              g.attrs["scroll-position"] - d / z;
            g.attr({"scroll-position": m.scrollPosition})
        }
      }, T && (l = C(c), c && (X(c, "pointerdrag", d), P(c, "pointerdrag", d))), 0 < A && (c = -va(A * z), h && h.transform(["T", c, 0]), k && k.transform(["T", c, 0]), f && f.transform(["T", c, 0]), q.setOffset && q.setOffset(c)))
    }, drawPlotColumn: function (a, b, c) {
      var d = this, g = a.data, k = g.length, l = a.items, m = a.graphics || (a.graphics = []), n = d.paper, s = d.smartLabel, q = d.logic, t = d.layers, z = d.options, C = d.elements, B = z.chart, E = !1 !== (z.tooltip || {}).enabled, G, J = d.definition.chart, P =
        z.plotOptions.series, L = P.dataLabels.style, R = d.xAxis[b.xAxis || 0], T = d.yAxis[b.yAxis || 0], Y = d.chartWidth, U = d.chartHeight, S = T.axisData.reversed, X = q.isLog, aa = q.is3D, ba = q.isStacked, da = q.isWaterfall, Z = q.isCandleStick, fa = u(R.axisData.scroll, {}), $ = c || {}, r = fa.enabled, w = ka($.position, z[Aa].xAxisScrollPos, fa.startPercent), v = $.vxLength || Ca(fa.vxLength), D = $.scrollStart || Ba(0, va((k - v) * w) - 1) || 0, e = $.scrollEnd || Ya(k, D + v + 2) || k, ja = B.canvasBorderOpacity = K.color(B.plotBorderColor).opacity, N = d.canvasBorderWidth, W = B.isCanvasBorder =
        0 !== ja && 0 < N, ga, Wa = c !== Da ? 0 : isNaN(+P.animation) && P.animation.duration || 1E3 * P.animation, Sa = b.numColumns || 1, sa = b.columnPosition || 0, na = B.use3DLighting, F = !1 === b.visible ? "hidden" : "visible", M = B.overlapColumns, pa = R.getAxisPosition(0), $a = R.getAxisPosition(1) - pa, ua = J && J.plotspacepercent, qa = ka(J && J.plotpaddingpercent), xa = P.groupPadding, ra = P.maxColWidth, za = (1 - .01 * ua) * $a || Ya($a * (1 - 2 * xa), ra * Sa), Ea = za / 2, Fa = za / Sa, Ta = Ya(Fa - 1, 1 < Sa ? M || qa !== Da ? 0 < qa ? Fa * qa / 100 : 0 : 4 : 0), Wb = sa * Fa - Ea + Ta / 2, Ra = T.max, Va = T.min, bb = 0 < Ra && 0 <= Va, db =
        0 >= Ra && 0 > Va, eb = 0 < Ra && 0 > Va, Qa = db || S && bb ? Ra : X || bb ? Va : 0, Xa = T.yBasePos = T.getAxisPosition(Qa), lb, fb = ka(B.useRoundEdges, 0), ib = t.dataset = t.dataset || n.group("dataset-orphan"), cb = t.datalabels = t.datalabels || n.group("datalabels").insertAfter(ib), kd = t.tracker, qb = d.canvasTop, xb = d.canvasLeft, Qb = d.canvasWidth, Ib = d.canvasBottom, Fc = d.canvasRight, tc, Rb, ld, Gc, cc, dc, Oc, Lb, ac, vb, nb, pc, Db, wc, Mb, hc, Nb, Za, xc, Hc, Ha, tb, bc, qc, rc, Pc, ob, Ob, Sb, sc, mc, uc, Ic, Qc, yc, pb, Lc, Pb, Ma, zc = function (a) {
        Ka.call(this, d, a)
      }, Ac = function (a, b) {
        return function (c) {
          a.attr(b);
          Ka.call(this, d, c, "DataPlotRollOver")
        }
      }, Tb = function (a, b) {
        return function (c) {
          a.attr(b);
          Ka.call(this, d, c, "DataPlotRollOut")
        }
      };
      d.addCSSDefinition(".fusioncharts-datalabels .fusioncharts-label", {
        fontFamily: L.fontFamily,
        fontSize: L.fontSize,
        lineHeight: L.lineHeight,
        fontWeight: L.fontWeight,
        fontStyle: L.fontStyle,
        color: L.color
      });
      cb.attr("class", "fusioncharts-datalabels");
      Wa && (!c && cb.attr({transform: "...t" + Y + "," + U}), d.animationCompleteQueue.push({
        fn: function () {
          cb.attr({transform: "...t" + -Y + "," + -U})
        }, scope: d
      }));
      Fa -= Ta;
      r && D > e - v - 2 && (D = Ba(0, e - v - 2));
      ba && (pb = ib.shadows || (ib.shadows = n.group("shadows", ib).toBack()));
      Sb = ib.column || (ib.column = n.group("columns", ib));
      Z || aa || r || Sb.attrs["clip-rect"] || Sb.attr({"clip-rect": C["clip-canvas"]});
      da && Sb.toBack();
      if (aa)for (cc = B.xDepth || 0, dc = B.yDepth || 0, sc = Sb.negative = Sb.negative || n.group("negative-values", Sb), Ic = Sb.column = Sb.column || n.group("positive-values", Sb), uc = Sb.zeroPlane, !uc && 0 > Va && 0 <= Ra && (uc = Sb.zeroPlane = n.group("zero-plane", Sb).insertBefore(Ic), Rb = B.zeroPlaneColor,
        ld = B.zeroPlaneBorderColor, Gc = B.zeroPlaneShowBorder, C.zeroplane = n.cubepath(xb - cc, Xa + dc, Qb, 1, cc, dc, uc).attr({
        fill: [Rb, !na],
        stroke: ld || "none",
        "stroke-width": Gc ? 1 : 0
      })), (mc = sc.data("categoryplots")) || (sc.data("categoryplots", Array(k)), mc = sc.data("categoryplots")), (Qc = Ic.data("categoryplots")) || (Ic.data("categoryplots", Array(k)), Qc = Ic.data("categoryplots")), vb = 0; vb < k; vb += 1)mc[vb] = mc[vb] || n.group(sc), Qc[vb] = Qc[vb] || n.group(Ic); else yc = Sb;
      s.setStyle({
        fontFamily: L.fontFamily, fontSize: L.fontSize, lineHeight: L.lineHeight,
        fontWeight: L.fontWeight, fontStyle: L.fontStyle
      });
      for (vb = D; vb < e; vb += 1) {
        nb = g[vb];
        Mb = nb.y;
        G = nb.toolText;
        tc = a.index + "_" + vb;
        ob = Ob = null;
        if (null === Mb) {
          if (ac = l[vb])ob = ac.graphic, aa || ob.attr({height: 0})
        } else {
          Oc = !1;
          wc = ka(nb.x, vb);
          pc = nb.link;
          Db = Q(nb.borderWidth) || 0;
          Pc = nb._FCW * $a;
          Nb = R.getAxisPosition(nb._FCX) || R.getAxisPosition(wc) + Wb;
          hc = nb.previousY;
          xc = T.getAxisPosition(hc || Qa);
          Za = T.getAxisPosition(Mb + (hc || 0));
          Ha = jb(Za - xc);
          tb = Pc || Fa;
          Ma = {
            index: vb, link: pc, value: nb.y, displayValue: nb.displayValue, categoryLabel: nb.categoryLabel,
            toolText: nb.toolText, id: a.userID, datasetIndex: a.index, datasetName: a.name, visible: a.visible
          };
          if (aa) {
            0 > Mb && (Za = xc, Oc = !0);
            yc = 0 > Mb ? mc : Qc;
            (ac = l[vb]) || (ac = l[vb] = {
              index: vb,
              value: Mb,
              graphic: n.cubepath(yc[vb]),
              dataLabel: null,
              tracker: null,
              hot: null
            });
            ob = ac.graphic;
            qc = rc = {};
            nb.hoverEffects && (qc = {
              fill: [ha(nb.color), !na],
              stroke: Db && ha(nb.borderColor) || "NONE",
              "stroke-width": Db
            }, bc = nb.rolloverProperties, rc = {
              fill: [ha(bc.color), !na],
              stroke: bc.borderWidth && ha(bc.borderColor) || "NONE",
              "stroke-width": bc.borderWidth
            });
            ob.attr({
              cubepath: [Nb - cc, Wa ? Xa + dc : Za + dc, tb, Wa ? 0 : Ha, cc, dc],
              fill: [ha(nb.color), !na],
              ishot: !E,
              stroke: Db && ha(nb.borderColor) || "NONE",
              "stroke-width": Db,
              visibility: F
            }).shadow(P.shadow && nb.shadow, pb).data("BBox", {height: Ha, width: tb, x: Nb, y: Za});
            Wa && ob.animate({cubepath: [Nb - cc, Za + dc, tb, Ha, cc, dc]}, Wa, "normal", d.getAnimationCompleteFn());
            if (pc || E)!ba && Ha < h && (Za -= (h - Ha) / 2, Ha = h), ac.tracker || (ac.tracker = n.cubepath(kd)), Ob = ac.tracker, Ob.attr({
              cubepath: [Nb - cc, Za + dc, tb, Ha, cc, dc], cursor: pc ? "pointer" : "", stroke: Db &&
              f || "NONE", "stroke-width": Db, fill: f, ishot: !0, visibility: F
            });
            (Ob || ob).data("eventArgs", Ma).data("groupId", tc).click(zc).hover(Ac(ob, rc), Tb(ob, qc)).tooltip(G);
            (Ob || ob)._.cubetop.data("eventArgs", Ma).data("groupId", tc).click(zc).hover(Ac(ob, rc), Tb(ob, qc)).tooltip(G);
            (Ob || ob)._.cubeside.data("eventArgs", Ma).data("groupId", tc).click(zc).hover(Ac(ob, rc), Tb(ob, qc)).tooltip(G);
            ba && Oc && (ob.toBack(), Ob && Ob.toBack())
          } else {
            Lb = !1;
            if (!X && !S && 0 > Mb || !X && S && 0 < Mb)Za = xc, Lb = !0;
            S && !eb && 0 < Mb && (Za = xc - Ha, Lb = !1);
            da && 0 > Mb &&
            ya(hc) && (Za -= Ha, Lb = !0);
            Z || r || (A(Za) <= qb && (Ha -= qb - Za - +W, Za = qb - +W), va(Za + Ha) >= Ib && (Ha -= va(Za + Ha) - Ib + +!!Db + +W, B.xAxisLineVisible && !W && (Ha += 1)), 1 >= Db && (va(Nb) <= xb && (tb += Nb, Nb = xb - Db / 2 + +!!Db - +W, tb -= Nb), va(Nb + tb) >= Fc && (tb = Fc - Nb + Db / 2 - +!!Db + +W)));
            Lc = K.crispBound(Nb, Za, tb, Ha, Db);
            Nb = Lc.x;
            Za = Lc.y;
            tb = Lc.width;
            Ha = Lc.height;
            if (!Z && W && (!ya(hc) || da && hc === Mb && Mb === nb._FCY))if (db && !S)ga = Za - (qb - Db / 2), Ha += ga, Xa = Za -= ga; else if (X || bb || S && db)Ha = Ib - Za + Db / 2, Xa = Za + Ha;
            da && hc && 0 < Db && 0 !== P.connectorOpacity && 1 === P.connectorWidth &&
            P.connectorDashStyle && (--Ha, 0 > Mb && (Za += 1));
            1 > Ha && (Za += 0 > Mb ? 1 : 0 === Mb ? 0 : -(1 - Ha), Ha = 1);
            b._columnWidth = tb;
            if (!(ac = l[vb])) {
              ac = l[vb] = {
                index: vb,
                value: Mb,
                width: tb,
                graphic: null,
                valueBelowPlot: Lb,
                dataLabel: null,
                tracker: null
              };
              lb = 0;
              Wa || (Xa = Za, lb = Ha || 1);
              qc = rc = {};
              nb.hoverEffects && (qc = {
                fill: ha(nb.color),
                stroke: ha(nb.borderColor),
                "stroke-width": Db,
                "stroke-dasharray": nb.dashStyle
              }, bc = nb.rolloverProperties, rc = {
                fill: ha(bc.color),
                stroke: ha(bc.borderColor),
                "stroke-width": bc.borderWidth,
                "stroke-dasharray": bc.dashStyle
              });
              Pb = {
                x: Nb,
                y: Xa,
                width: tb,
                height: lb,
                r: fb,
                ishot: !E,
                fill: ha(nb.color),
                stroke: ha(nb.borderColor),
                "stroke-width": Db,
                "stroke-dasharray": nb.dashStyle,
                "stroke-linejoin": "miter",
                visibility: F
              };
              ob ? ob.attr(Pb) : ob = ac.graphic = n.rect(Pb, yc);
              ob.shadow(P.shadow && nb.shadow, pb).data("BBox", Lc);
              Wa && ob.animate({y: Za, height: Ha || 1}, Wa, "normal", d.getAnimationCompleteFn());
              if (pc || E)!ba && Ha < h && (Za -= (h - Ha) / 2, Ha = h), Pb = {
                x: Nb,
                y: Za,
                width: tb,
                height: Ha,
                r: fb,
                cursor: pc ? "pointer" : "",
                stroke: f,
                "stroke-width": Db,
                fill: f,
                ishot: !0,
                visibility: F
              },
                (Ob = ac.tracker) ? Ob.attr(Pb) : Ob = ac.tracker = n.rect(Pb, kd);
              Ob = ac.tracker;
              (Ob || ob).data("eventArgs", Ma).data("groupId", tc).click(zc).hover(Ac(ob, rc), Tb(ob, qc)).tooltip(G)
            }
          }
          Hc = d.drawPlotColumnLabel(a, b, vb, Nb, Za)
        }
        Hc && m.push(Hc);
        ob && m.push(ob);
        Ob && m.push(Ob);
        d.drawTracker && d.drawTracker.call(d, a, b, vb)
      }
      a.visible = !1 !== b.visible;
      return a
    }, drawPlotColumnScroll: function (a, b, c) {
      var d = a.data.length, f = a.items, g;
      g = c.vxLength;
      var h = Ba(0, va((d - g) * c.position) - 1) || 0, d = Ya(d, h + g + 2) || d;
      h > d - g - 2 && (h = Ba(0, d - g - 2));
      c.scrollEnd =
        d;
      for (g = h; g < d; g++)if (!f[g]) {
        c.scrollStart = g;
        this.drawPlotColumn(a, b, c);
        break
      }
    }, drawPlotColumnLabel: function (a, b, c, d, f, g) {
      var h = this.options, k = this.logic;
      d = h.chart;
      var l = this.paper, m = this.smartLabel, n = this.layers, h = h.plotOptions.series.dataLabels.style, s = 1 === d.rotateValues ? 270 : 0, q = this.canvasHeight, t = this.canvasTop, A = a.data[c];
      a = a.items[c];
      var z = d.valuePadding + 2, C = a.graphic;
      c = a.dataLabel;
      var B = u(a.valueBelowPlot, 0 > A.y), E = k.isStacked, k = k.is3D, G = d.xDepth || 0, K = d.yDepth || 0, J = A.displayValue;
      b = !1 === b.visible ?
        "hidden" : "visible";
      var P = d.placeValuesInside, Q;
      g = g || n.datalabels;
      ya(J) && J !== S && null !== A.y ? (a._state && a._state.labelWidth || (m = m.getOriSize(J), a._state = s ? {
        labelWidth: m.height,
        labelHeight: m.width
      } : {
        labelWidth: m.width,
        labelHeight: m.height
      }), C = C.data("BBox"), n = C.height, m = Q = a._state.labelHeight + z, z = .5 * Q + z, C = C.x + .5 * C.width, Q = B ? t + q - (f + n) : f - t, E ? (f = Ya(t + q - .5 * m, f + .5 * n + (K || 0)), f = Ba(t + .5 * m, f), C -= G) : P ? n >= m ? (f += B ? n - z : z, A._valueBelowPoint = 1, k && (C -= G, f += K)) : Q >= m ? (f += B ? n + z : -z, k && B && (C -= G, f += K)) : (f += B ? n - z : z, A._valueBelowPoint =
        1, k && (C -= G, f += K)) : Q >= m ? (f += B ? n + z : -z, k && (B ? (C -= G, f += K) : C -= G / 2)) : (f += B ? n - z : z, A._valueBelowPoint = 1, k && (C -= G, f += K)), c ? c.attr({
        x: C,
        y: f,
        visibility: b
      }) : c = a.dataLabel = l.text({
        text: J,
        "class": "fusioncharts-label",
        x: C,
        y: f,
        fill: h.color,
        "font-size": h.fontSize,
        direction: d.textDirection,
        visibility: b
      }, g).attr({
        "line-height": h.lineHeight,
        "text-bound": [h.backgroundColor, h.borderColor, h.borderThickness, h.borderPadding, h.borderRadius, h.borderDash]
      }), s && c.attr("transform", "T0,0,R" + s)) : c && c.attr({text: S});
      return c
    }, drawPlotFloatedcolumn: function (a,
                                        b) {
      this.drawPlotColumn.call(this, a, b)
    }, drawPlotColumn3d: function (a, b) {
      this.drawPlotColumn.call(this, a, b)
    }, drawPlotBar: function (a, b) {
      var c = this, d = a.data, g = d.length, k = a.items, l = a.graphics = [], m = c.paper, n = c.logic, s = c.layers, q = c.options, u = c.elements, t = q.chart, z = !1 !== (q.tooltip || {}).enabled, C, B = c.definition.chart, q = q.plotOptions.series, G = q.dataLabels.style, E = {
        fontFamily: G.fontFamily,
        fontSize: G.fontSize,
        lineHeight: G.lineHeight,
        fontWeight: G.fontWeight,
        fontStyle: G.fontStyle
      }, G = c.xAxis[b.xAxis || 0], J = c.yAxis[b.yAxis ||
      0], P = n.is3D, n = n.isStacked, L = t.canvasBorderOpacity = K.color(t.plotBorderColor).opacity, R = c.canvasBorderWidth, L = t.isCanvasBorder = 0 !== L && 0 < R, R = isNaN(+q.animation) && q.animation.duration || 1E3 * q.animation, Y = b.numColumns || 1, T = b.columnPosition || 0, U = t.use3DLighting, S = !1 === b.visible ? "hidden" : "visible", X = t.overlapColumns, aa = G.getAxisPosition(0), aa = G.getAxisPosition(1) - aa, ba = B && B.plotspacepercent, B = ka(B && B.plotpaddingpercent), da = q.groupPadding, Z = q.maxColWidth, ba = (1 - .01 * ba) * aa || Ya(aa * (1 - 2 * da), Z * Y), aa = ba / 2, ba = ba /
        Y, X = Ya(ba - 1, 1 < Y ? X || B !== Da ? 0 < B ? ba * B / 100 : 0 : 4 : 0), Y = ba - X, T = T * ba - aa + X / 2, fa = J.max, r = J.min, X = J.getAxisPosition(0 > fa && 0 > r ? fa : 0 < fa && 0 < r ? r : 0), B = ka(t.useRoundEdges, 0), w = c.canvasTop, aa = c.canvasLeft, v = c.canvasHeight, ba = c.canvasRight, D = c.chartWidth, e = c.chartHeight, ja, N, W, $, ga, na, sa, pa, F, M, da = J.axisData.effectiveZeroPlaneThickness;
      sa = s.dataset = s.dataset || m.group("dataset-orphan");
      var qa = s.datalabels = s.datalabels || m.group("datalabels").insertAfter(sa), s = s.tracker, $a, ua, Ba, xa, za, ra, Z = function (a) {
          Ka.call(this, c, a)
        },
        Fa = function (a, b) {
          return function (d) {
            a.attr(b);
            Ka.call(this, c, d, "DataPlotRollOver")
          }
        }, Ea = function (a, b) {
          return function (d) {
            a.attr(b);
            Ka.call(this, c, d, "DataPlotRollOut")
          }
        }, Aa;
      c.addCSSDefinition(".fusioncharts-datalabels .fusioncharts-label", E);
      qa.attr("class", "fusioncharts-datalabels");
      R && (c.animationCompleteQueue.push({
        fn: function () {
          qa.attr({transform: "...t" + -D + "," + -e})
        }, scope: c
      }), qa.attr({transform: "...t" + D + "," + e}));
      n && (za = sa.shadows || (sa.shadows = m.group("shadows", sa).toBack()));
      pa = sa.column = sa.column ||
      m.group("bars", sa);
      if (P)for (ja = t.xDepth || 0, N = t.yDepth || 0, E = pa.negative = pa.negative || m.group("negative-values", pa), sa = pa.column = pa.column || m.group("positive-values", pa), Ba = pa.zeroPlane, !Ba && 0 > r && 0 <= fa && (Ba = pa.zeroPlane = m.group("zero-plane", pa).insertBefore(sa), M = t.zeroPlaneColor, fa = t.zeroPlaneBorderColor, r = t.zeroPlaneShowBorder, u.zeroplane = m.cubepath(X - ja, w + N, 1, v, ja, N, Ba).attr({
        fill: [M, !U],
        stroke: fa || "none",
        "stroke-width": r ? 1 : 0
      })), (Ba = E.data("categoryplots")) || (E.data("categoryplots", Array(g)), Ba =
        E.data("categoryplots")), (M = sa.data("categoryplots")) || (sa.data("categoryplots", Array(g)), M = sa.data("categoryplots")), u = 0; u < g; u += 1)Ba[u] = Ba[u] || m.group(E), M[u] = M[u] || m.group(sa); else pa.attrs["clip-rect"] || pa.attr({"clip-rect": u["clip-canvas"]}), xa = pa;
      u = 0;
      for (E = g - 1; u < g; u += 1, --E) {
        w = d[u];
        r = w.y;
        $a = v = null;
        if (null === r) {
          if (na = k[u])$a = na.graphic, P || $a.attr({width: 0})
        } else {
          pa = ka(w.x, u);
          sa = w.link;
          C = w.toolText;
          fa = Q(w.borderWidth) || 0;
          pa = G.getAxisPosition(pa) + T;
          na = (W = w.previousY) ? J.getAxisPosition(W) : X;
          F = J.getAxisPosition(r +
          (W || 0));
          W = jb(F - na);
          0 < r && (F = na);
          ua = {
            index: u,
            link: sa,
            value: w.y,
            displayValue: w.displayValue,
            categoryLabel: w.categoryLabel,
            toolText: w.toolText,
            id: a.userID,
            datasetIndex: a.index,
            datasetName: a.name,
            visible: a.visible
          };
          if (P) {
            xa = 0 > r ? Ba : M;
            (na = k[u]) || (na = k[u] = {
              index: u,
              value: r,
              graphic: m.cubepath(xa[E]),
              dataLabel: null,
              tracker: null
            });
            $a = na.graphic;
            ga = $ = {};
            w.hoverEffects && (ga = {
              fill: [ha(w.color), !U],
              stroke: fa && ha(w.borderColor) || "NONE",
              "stroke-width": fa
            }, $ = w.rolloverProperties, $ = {
              fill: [ha($.color), !U], stroke: $.borderWidth &&
              ha($.borderColor) || "NONE", "stroke-width": $.borderWidth
            });
            $a.attr({
              cubepath: [R ? X - ja : F - ja, pa + N, R ? 0 : W, Y, ja, N],
              fill: [ha(w.color), !U],
              ishot: !z,
              stroke: fa && ha(w.borderColor) || "NONE",
              "stroke-width": fa,
              "stroke-dasharray": w.dashStyle,
              cursor: sa ? "pointer" : "",
              visibility: S
            }).shadow(q.shadow && w.shadow, za).data("BBox", {height: Y, width: W, x: F, y: pa});
            R && $a.animate({cubepath: [F - ja, pa + N, W, Y, ja, N]}, R, "normal", c.getAnimationCompleteFn());
            if (sa || z)!n && W < h && (F -= (h - W) / 2, W = h), na.tracker || (na.tracker = m.cubepath(s)), v = na.tracker,
              v.attr({
                cubepath: [F - ja, pa + N, W, Y, ja, N],
                cursor: sa ? "pointer" : "",
                stroke: fa && f || "NONE",
                "stroke-width": fa,
                fill: f,
                ishot: !0
              });
            (v || $a).data("eventArgs", ua).click(Z).hover(Fa($a, $), Ea($a, ga)).tooltip(C);
            (v || $a)._.cubetop.data("eventArgs", ua).click(Z).hover(Fa($a, $), Ea($a, ga));
            (v || $a)._.cubeside.data("eventArgs", ua).click(Z).hover(Fa($a, $), Ea($a, ga));
            if (!n || n && 0 > r)$a.toBack(), v && v.toBack()
          } else {
            A(F) <= aa && (W += F, F = aa + fa / 2 + .2, t.xAxisLineVisible && !L && --F, W -= F);
            va(F + W) >= ba && (W = ba - F - fa / 2 - .2);
            ra = K.crispBound(F, pa,
              W, Y, fa);
            F = ra.x;
            pa = ra.y;
            W = ra.width;
            Aa = ra.height;
            1 >= W && (W = 1, F += 0 > r ? -W : 0 === r ? 0 : 1 < da ? W : 0);
            (na = k[u]) || (na = k[u] = {
              index: u,
              value: r,
              height: Aa,
              graphic: null,
              dataLabel: null,
              tracker: null
            });
            $a = na.graphic;
            ga = $ = {};
            w.hoverEffects && (ga = {
              fill: ha(w.color),
              stroke: ha(w.borderColor),
              "stroke-width": fa,
              "stroke-dasharray": w.dashStyle
            }, $ = w.rolloverProperties, $ = {
              fill: ha($.color),
              stroke: ha($.borderColor),
              "stroke-width": $.borderWidth,
              "stroke-dasharray": $.dashStyle
            });
            r = {
              x: R ? X : F,
              y: pa,
              width: R ? 0 : W || 1,
              height: Aa,
              r: B,
              fill: ha(w.color),
              ishot: !z,
              stroke: ha(w.borderColor),
              "stroke-width": fa,
              "stroke-dasharray": w.dashStyle,
              "stroke-linejoin": "miter",
              cursor: sa ? "pointer" : "",
              visibility: S
            };
            $a ? $a.attr(r) : $a = na.graphic = m.rect(r, xa);
            $a.shadow(q.shadow && w.shadow, za).data("BBox", ra);
            R && $a.animate({x: F, width: W || 1}, R, "normal", c.getAnimationCompleteFn());
            if (sa || z)!n && W < h && (F -= (h - W) / 2, W = h), v = na.tracker, r = {
              x: F,
              y: pa,
              width: W,
              height: Y,
              r: B,
              cursor: sa ? "pointer" : "",
              stroke: f,
              "stroke-width": fa,
              fill: f,
              ishot: !0
            }, v ? v.attr(r) : v = na.tracker = m.rect(r, s), v.data("eventArgs",
              ua);
            (v || $a).data("eventArgs", ua).click(Z).hover(Fa($a, $), Ea($a, ga)).tooltip(C)
          }
          C = c.drawPlotBarLabel(a, b, u, F, pa)
        }
        C && l.push(C);
        $a && l.push($a);
        v && l.push(v);
        c.drawTracker && c.drawTracker.call(c, a, b, u)
      }
      a.visible = !1 !== b.visible;
      return a
    }, drawPlotBarLabel: function (a, b, c, d, f, g) {
      var h = this.options, k = this.logic, l = h.chart, m = this.paper, n = this.layers, s = h.plotOptions.series.dataLabels.style, h = this.canvasLeft, q = this.canvasWidth, u = a.data[c], t = a.items[c];
      a = l.valuePadding + 2;
      var z = t.graphic;
      c = t.dataLabel;
      var A = 0 > u.y,
        C = k.isStacked, k = k.is3D, B = l.xDepth || 0, G = l.yDepth || 0, E = u.displayValue;
      b = !1 === b.visible ? "hidden" : "visible";
      var K = l.placeValuesInside;
      g = g || n.datalabels;
      if (ya(E) && E !== S && null !== u.y) {
        c || (c = t.dataLabel = m.text({
          "class": "fusioncharts-label",
          text: E,
          "font-size": s.fontSize,
          title: u.originalText || "",
          fill: s.color,
          direction: l.textDirection,
          x: 0,
          y: 0,
          "line-height": s.lineHeight
        }, g).attr("text-bound", [s.backgroundColor, s.borderColor, s.borderThickness, s.borderPadding, s.borderRadius, s.borderDash]));
        n = c.getBBox();
        g = z.data("BBox");
        s = g.height;
        m = g.width;
        g = C ? "middle" : A ? K ? "start" : "end" : K ? "end" : "start";
        l = A ? d - h : h + q - (d + m);
        n = n.width;
        n += a;
        s = f + .5 * s;
        u = d + (A ? 0 : m);
        f = A ? d - h : h + q - (d + m);
        if (C)u = Ba(h + .5 * n, u + .5 * (A ? m : -m)), u = Ya(h + q - .5 * n, u), u -= k ? B : 0, s += k ? G : 0; else if (K ? m >= n ? (u += A ? a : -a, k && (s += G, u -= B)) : n < l ? (u += A ? -a : a, g = A ? "end" : "start", k && A && (u -= B)) : (A ? (u = d + m + Ba(n - d - m + h, 0) - a, g = "end", u -= k ? B : 0) : (u = d - Ba(n - (h + q - d), 0) + a, g = "start"), k && (u -= B, s += G)) : f >= n ? (u += A ? -a : a, k && A && (u -= B, s += B)) : (u += A ? a + n : -(a + n), k && (u -= B, s += G)), u > h + q || u < h)u = h + 4, g = "start";
        c.attr({
          x: u, y: s,
          "text-anchor": g, visibility: b
        })
      } else c && c.attr({text: S});
      return c
    }, drawPlotBar3d: function (a, b) {
      this.drawPlotBar.call(this, a, b)
    }, drawPlotLine: function (b, c) {
      var d = this, g = d.paper, h = d.elements, k = d.options, l = k.chart, m = d.logic, n = k.plotOptions.series, s = b.items, q = b.graphics = b.graphics || [], u, t = d.xAxis[c.xAxis || 0], A = d.yAxis[c.yAxis || 0], z = m.multisetRealtime || m.dragExtended, C = m.isWaterfall, B, G, E, J, P, R = 0, Y = !1 !== (k.tooltip || {}).enabled, T, U = isNaN(+n.animation) && n.animation.duration || 1E3 * n.animation, S, X = n.dataLabels.style,
        ba = {
          fontFamily: X.fontFamily,
          fontSize: X.fontSize,
          lineHeight: X.lineHeight,
          fontWeight: X.fontWeight,
          fontStyle: X.fontStyle
        }, aa = l.xDepth || 0, da = l.yDepth || 0, Z = l.series2D3Dshift, fa = c.step, r = c.drawVerticalJoins, w = c.useForwardSteps, v = b.data, D = !1 === c.visible ? "hidden" : "visible", e, ja = v.length, N = t.getAxisPosition(0), W = t.getAxisPosition(1) - N, $ = W * ja, ga = t.axisData.scroll || {}, na = l.hasScroll || !1, pa, va = n.connectNullData, F = d.chartWidth, M = d.chartHeight, ua = function () {
          hc.attr({"clip-rect": null});
          Mb.show();
          wc.show();
          Nb.show();
          Fc.attr({transform: "...t" + -F + "," + -M})
        }, qa, xa, ra, za, Da, Fa, Ea, Aa = null, Ca, ya, Ra = n.connectorWidth = Q(c.lineWidth), Ta = c.color, Va, bb, db = n.connectorDashStyle = c.dashStyle, Qa, Xa, jb, fb, ib, cb, eb, lb, qb, xb = d.layers, Ib = xb.dataset = xb.dataset || g.group("dataset-orphan"), Fc = xb.datalabels = xb.datalabels || g.group("datalabels").insertAfter(Ib), tc = xb.tracker, Qb = h["clip-canvas-init"].slice(0), Rb = h["clip-canvas"].slice(0), Gc = A.axisData.reversed, cc = A.max, dc = A.min, Oc = A.getAxisPosition(0 < cc && 0 < dc ? Gc ? cc : dc : 0 > cc && 0 > dc ? Gc ? dc : cc :
            Gc ? cc : 0) + (Z ? da : 0), Lb = [], ac = l.anchorTrackingRadius, vb = /drag/ig.test(d.logic.rendererId), nb, pc, Db, wc, Mb, hc, Nb, Za, xc, Hc, Ha, tb, bc = [], qc = function (a) {
          Ka.call(this, d, a)
        }, rc = function (a) {
          return function (b) {
            d.hoverPlotAnchor(this, b, "DataPlotRollOver", a, d)
          }
        }, Pc = function (a) {
          return function (b) {
            d.hoverPlotAnchor(this, b, "DataPlotRollOut", a, d)
          }
        }, ob = function (a, e, h, k, l, m, n, r) {
          return function () {
            var s = h.imageUrl, u = h.imageScale, v = h.imageAlpha, w = n.imageHoverAlpha, t = n.imageHoverScale, A = this.height * u * .01, z = this.width * u *
              .01, C = this.width * t * .01;
            eb = {x: a - this.width * u * .005, y: e - this.height * u * .005, width: z, height: A, alpha: v};
            lb = {
              x: a - this.width * t * .005,
              y: e - this.height * t * .005,
              width: C,
              height: this.height * t * .01,
              alpha: w
            };
            w = C > z ? lb : eb;
            vb && (w = {cx: a, cy: e, r: .5 * Ba(A, z)});
            (k.graphic = fb = g.image(s, Nb).attr(eb).css({opacity: .01 * v}).data("alwaysInvisible", !u).data("setRolloverProperties", n).data("setRolloverAttr", lb).data("setRolloutAttr", eb).data("anchorRadius", u).data("anchorHoverRadius", t)) && q.push(fb);
            if (xa || Y || n)ib = k.tracker = (vb ? g.circle(tc) :
              g.rect(tc)).attr(w).attr({
                cursor: xa ? "pointer" : "",
                stroke: f,
                "stroke-width": h.lineWidth,
                fill: f,
                ishot: !0,
                visibility: D
              }).data("eventArgs", l).data("groupId", nb).click(qc).hover(rc(k), Pc(k)).tooltip(m);
            d.drawTracker && d.drawTracker.call(d, b, c, r);
            (Ha = k.dataLabel = d.drawPlotLineLabel(b, c, r, a, e)) && q.push(Ha)
          }
        }, Ob = function (a, e, f, g, h, k, l, m) {
          return function () {
            (Ha = g.dataLabel = d.drawPlotLineLabel(b, c, m, a, e)) && q.push(Ha)
          }
        };
      d.addCSSDefinition(".fusioncharts-datalabels .fusioncharts-label", ba);
      Fc.attr("class", "fusioncharts-datalabels");
      n.connectorOpacity = K.color(Ta).opacity;
      A.yBasePos = Oc;
      C && (B = (G = d.definition.chart) && G.plotspacepercent, E = n.groupPadding, J = n.maxColWidth, P = (1 - .01 * B) * W || Ya(W * (1 - 2 * E), 1 * J), R = P / 2);
      Fc.attr({transform: "...t" + F + "," + M});
      U && d.animationCompleteQueue.push({fn: ua, scope: d});
      Db = Ib.line || (Ib.line = g.group("line-connector", Ib));
      wc = b.lineShadowLayer || (b.lineShadowLayer = g.group("connector-shadow", Db));
      Mb = b.anchorShadowLayer || (b.anchorShadowLayer = g.group("anchor-shadow", Db));
      hc = b.lineLayer || (b.lineLayer = g.group("connector",
        Db));
      Nb = b.anchorLayer || (b.anchorLayer = g.group("anchors", Db));
      Nb.hide();
      wc.hide();
      Mb.hide();
      for (e = 0; e < ja; e += 1) {
        qa = v[e];
        Da = qa.y;
        Fa = qa.previousY || 0;
        T = qa.toolText;
        nb = b.index + "_" + e;
        qb = Ha = fb = ib = null;
        u = s[e] = {
          index: e,
          value: null,
          graphic: null,
          connector: null,
          dataLabel: null,
          shadowGroup: Mb,
          tracker: null
        };
        if (null === Da)bc.length = 0, 0 === va && (Aa = null); else {
          za = ka(qa.x, e);
          xa = qa.link;
          "boxandwhisker" === c.relatedSeries && c.pointStart && (za += c.pointStart);
          ya = A.getAxisPosition(Da + Fa) + (Z ? da : 0);
          Ca = t.getAxisPosition(za) - aa;
          Ca =
            L(Ca, Ra, Ra).position;
          ya = L(ya, Ra, Ra).position;
          if ((Qa = qa.marker) && Qa.enabled)if (Xa = Qa.symbol.split("_"), jb = "spoke" === Xa[0] ? 1 : 0, ra = Qa.radius, Za = Qa.shadow, pc = {
              index: e,
              link: xa,
              value: qa.y,
              displayValue: qa.displayValue,
              categoryLabel: qa.categoryLabel,
              toolText: qa.toolText,
              id: b.userID,
              datasetIndex: b.index,
              datasetName: b.name,
              visible: b.visible
            }, eb = lb = {}, cb = qa.rolloverProperties, Qa.imageUrl)tb = new a, tb.onload = ob(Ca, ya, Qa, u, pc, T, cb, e), tb.onerror = Ob(Ca, ya, Qa, u, pc, T, cb, e), tb.src = Qa.imageUrl; else {
            cb && (eb = {
              polypath: [Xa[1] ||
              2, Ca, ya, ra, Qa.startAngle, jb],
              fill: ha(Qa.fillColor),
              "stroke-width": Qa.lineWidth,
              stroke: ha(Qa.lineColor)
            }, lb = {
              polypath: [cb.sides || 2, Ca, ya, cb.radius, cb.startAngle, cb.dip],
              fill: ha(cb.fillColor),
              "stroke-width": cb.lineWidth,
              stroke: ha(cb.lineColor)
            });
            fb = u.graphic = g.polypath(Xa[1] || 2, Ca, ya, ra, Qa.startAngle, jb, Nb).attr({
              fill: ha(Qa.fillColor),
              ishot: !Y,
              "stroke-width": Qa.lineWidth,
              stroke: ha(Qa.lineColor),
              cursor: xa ? "pointer" : "",
              visibility: ra ? D : "hidden"
            }).data("alwaysInvisible", !ra).data("setRolloverProperties",
              cb).data("setRolloverAttr", lb).data("setRolloutAttr", eb).data("anchorRadius", ra).data("anchorHoverRadius", cb && cb.radius).shadow(Za || !1, Mb);
            if (xa || Y || cb)ra = Ba(ra, cb && cb.radius || 0, ac), ib = u.tracker = g.circle({
              cx: Ca,
              cy: ya,
              r: ra,
              cursor: xa ? "pointer" : "",
              stroke: f,
              "stroke-width": Qa.lineWidth,
              fill: f,
              ishot: !0,
              visibility: D
            }, tc);
            (ib || fb).data("eventArgs", pc).data("groupId", nb).click(qc).hover(rc(u), Pc(u)).tooltip(T);
            d.drawTracker && d.drawTracker.call(d, b, c, e)
          }
          xc = Hc !== [ha(qa.color || Ta), qa.dashStyle || db].join(":");
          if (null !== Aa) {
            if (bc.length && (Lb = Lb.concat(bc), bc.length = 0), (z || C || !Lb.join("")) && Lb.push("M", Ea, Aa), C && Lb.push("m", -R, 0), fa ? w ? (Lb.push("H", Ca), C && Lb.push("h", R), r ? Lb.push("V", ya) : Lb.push("m", 0, ya - Aa)) : (r && Lb.push("V", ya), Lb.push("M", Ea, ya, "H", Ca)) : Lb.push("L", Ca, ya), z || xc)qb = u.connector = g.path(Lb, hc).attr({
              "stroke-dasharray": bb,
              "stroke-width": Ra,
              stroke: Va,
              "stroke-linecap": "round",
              "stroke-linejoin": 2 < Ra ? "round" : "miter",
              visibility: D
            }).shadow(n.shadow && qa.shadow, wc), Lb = []
          } else!z && bc.push("M", Ca, ya);
          Qa && Qa.imageUrl || (Ha = u.dataLabel = d.drawPlotLineLabel(b, c, e, Ca, ya));
          Ea = Ca;
          Aa = ya;
          Va = ha(qa.color || Ta);
          bb = qa.dashStyle || db;
          Hc = [Va, bb].join(":")
        }
        Ha && q.push(Ha);
        fb && q.push(fb);
        qb && q.push(qb);
        ib && q.push(ib)
      }
      !z && Lb.join("") && (qb = g.path(Lb, hc).attr({
        "stroke-dasharray": bb,
        "stroke-width": Ra,
        stroke: Va,
        "stroke-linecap": "round",
        "stroke-linejoin": 2 <= Ra ? "round" : "miter",
        visibility: D
      }).shadow(n.shadow && qa.shadow, wc)) && q.push(qb);
      na && (pa = ga.startPercent, Rb[2] = $ + Qb[0], 1 === pa && (Qb[0] = Rb[2], Rb[0] = 0));
      U ? (S = K.animation({"clip-rect": Rb},
        U, na ? "easeIn" : "normal", d.getAnimationCompleteFn()), hc.attr({"clip-rect": Qb}).animate(C ? S.delay(U) : S)) : (ua && ua(), ua = void 0);
      b.visible = !1 !== c.visible;
      return b
    }, hoverPlotAnchor: function (a, b, c, d, f) {
      var g = d.graphic;
      d = d.dataLabel;
      var h = f.options.chart, k = 1 === h.rotateValues ? 270 : 0, l = g.data("setRolloverProperties"), m = g.data("isRealtime"), n = m && g.attr("polypath"), s = g.data("setRolloverAttr"), q = "image" === g.type, u = g.data("setRolloutAttr"), t = d && (d.data("isBelow") ? 1 : -1) * (q ? .5 * (s.height - u.height) : g.data("anchorHoverRadius") -
        g.data("anchorRadius")), A = "DataPlotRollOver" == c ? s : u, z = {transform: "T0," + ("DataPlotRollOver" === c ? t : 0) + "R" + k}, C = {
        fill: A.fill,
        "stroke-width": A["stroke-width"],
        stroke: A.stroke
      }, A = q ? A : {polypath: A.polypath}, h = h.syncLabelWithAnchor, B = g.data("anchorRadius"), G = g.data("anchorHoverRadius"), s = !(/,0\)$/.test(s.fill) && /,0\)$/.test(u.fill)) && g.data("anchorHoverRadius") - g.data("anchorRadius") && l.animation && 50;
      d && d.data("isMiddle") && (z = {transform: "T," + ("DataPlotRollOver" === c ? t : 0) + ",0R" + k});
      l && (("DataPlotRollOver" ==
      c && 0 !== G || "DataPlotRollOut" == c && 0 !== B) && g.attr({visibility: "visible"}), q ? g.css({opacity: .01 * A.alpha}) : g.attr(C), m && !q && (A.polypath[1] = n[1], A.polypath[2] = n[2]), g.stop(), g.animate(A, s, "easeOut", function () {
        ("DataPlotRollOver" == c && !G || "DataPlotRollOut" == c && !B) && g.attr({visibility: "hidden"})
      }), d && d.stop(), s && h && d && d.animate(z, s, "easeOut"));
      Ka.call(a, f, b, c)
    }, drawPlotArea: function (b, c) {
      var d = this, g = d.paper, h = d.options, k = h.chart, l = d.logic, n = h.plotOptions.series, s = d.elements, q = b.items, u = b.graphics = b.graphics ||
        [], t, A = d.xAxis[c.xAxis || 0], z = d.yAxis[c.yAxis || 0], C = z.axisData.reversed, B = k.xDepth || 0, G = k.yDepth || 0, l = l.isStacked, E = !1 !== (h.tooltip || {}).enabled, K, J, h = n.dataLabels.style, P = {
          fontFamily: h.fontFamily,
          fontSize: h.fontSize,
          lineHeight: h.lineHeight,
          fontWeight: h.fontWeight,
          fontStyle: h.fontStyle,
          color: h.color
        }, h = isNaN(+n.animation) && n.animation.duration || 1E3 * n.animation, Q = k.series2D3Dshift, L = "0" === d.definition.chart.drawfullareaborder, R = b.data, T = !1 === c.visible ? "hidden" : "visible", U = R.length, S = A.getAxisPosition(0),
        X = (A.getAxisPosition(1) - S) * U, ba = A.axisData.scroll || {}, S = k.hasScroll || !1, aa = n.connectNullData, da, Z, r, w, v, D = z.max, e = z.min, ja = z.getAxisPosition(0 < D && 0 > e ? 0 : !C && 0 < D && 0 <= e ? e : D) + (Q ? G : 0), N = d.chartWidth, W = d.chartHeight, C = function () {
          db.attr({"clip-rect": null});
          Qa.show();
          bb.show();
          Fa.attr({transform: "...t" + -N + "," + -W})
        }, fa = null, $, ga, D = c.lineWidth, e = c.dashStyle, na = ha(c.fillColor), pa = ha(c.lineColor), F = 0, M = /drag/ig.test(d.logic.rendererId), qa, ua, va, ra, xa, za, ya = [], Ca = [], Da = null, Ea = [], Aa = d.layers;
      w = Aa.dataset = Aa.dataset ||
      g.group("dataset-orphan");
      var Fa = Aa.datalabels = Aa.datalabels || g.group("datalabels").insertAfter(w), Ta = Aa.tracker, Aa = s["clip-canvas-init"].slice(0), s = s["clip-canvas"].slice(0), k = k.anchorTrackingRadius, Va, bb, Ya, Qa, db, eb, fb, ib, cb, Xa, jb = function (a) {
        Ka.call(this, d, a)
      }, lb = function (a) {
        return function (b) {
          d.hoverPlotAnchor(this, b, "DataPlotRollOver", a, d)
        }
      }, qb = function (a) {
        return function (b) {
          d.hoverPlotAnchor(this, b, "DataPlotRollOut", a, d)
        }
      }, Da = function (a, e, h, k, l, n, r, s) {
        return function () {
          var q = h.imageUrl, v = h.imageScale,
            w = h.imageAlpha, t = r.imageHoverAlpha, A = r.imageHoverScale, z = this.width * v * .01, C = this.width * A * .01;
          xa = {
            x: a - this.width * v * .005,
            y: e - this.height * v * .005,
            width: z,
            height: this.height * v * .01,
            alpha: w
          };
          za = {
            x: a - this.width * A * .005,
            y: e - this.height * A * .005,
            width: C,
            height: this.height * A * .01,
            alpha: t
          };
          t = C > z ? za : xa;
          M && (t = {cx: a, cy: e, r: .5 * Ba(C, z)});
          (k.graphic = ua = g.image(q, Qa).attr(xa).css({opacity: .01 * w}).data("alwaysInvisible", !v).data("setRolloverProperties", r).data("setRolloverAttr", za).data("setRolloutAttr", xa).data("anchorRadius",
            v).data("anchorHoverRadius", A)) && u.push(ua);
          if (Z || E || r)Va = m({
            cursor: Z ? "pointer" : "",
            stroke: f,
            "stroke-width": h.lineWidth,
            fill: f,
            ishot: !0,
            visibility: T
          }, t), va = k.tracker = (M ? g.circle(Va, Ta) : g.rect(Va, Ta)).data("eventArgs", l).click(jb).hover(lb(k), qb(k)).tooltip(n), d.drawTracker && d.drawTracker.call(d, b, c, s);
          (Xa = k.dataLabel = d.drawPlotLineLabel(b, c, s, a, e)) && u.push(Xa)
        }
      }, xb = function (a, e, f, g, h, k, l, m) {
        return function () {
          (Xa = g.dataLabel = d.drawPlotLineLabel(b, c, m, a, e)) && u.push(Xa)
        }
      };
      d.addCSSDefinition(".fusioncharts-datalabels .fusioncharts-label",
        P);
      Fa.attr("class", "fusioncharts-datalabels");
      z.yBasePos = ja;
      Fa.attr({transform: "...t" + N + "," + W});
      h && d.animationCompleteQueue.push({fn: C, scope: d});
      P = w;
      l && (eb = P.shadows || (P.shadows = g.group("shadows", P).toBack()));
      db = P.area = P.area || g.group("area", P);
      P = P.areaConnector || (P.areaConnector = g.group("area-connector", P));
      b.lineShadowLayer || (b.lineShadowLayer = g.group("connector-shadow", P));
      bb = b.anchorShadowLayer || (b.anchorShadowLayer = g.group("anchor-shadow", P));
      Ya = b.lineLayer || (b.lineLayer = g.group("connector",
        P));
      Qa = b.anchorLayer || (b.anchorLayer = g.group("anchors", P));
      Qa.hide();
      bb.hide();
      P = w;
      for (P = 0; P < U; P += 1) {
        da = R[P];
        w = da.y;
        t = ka(da.x, P);
        $ = A.getAxisPosition(t) - B;
        ua = Xa = va = null;
        t = q[P] = {};
        if (null === w)0 === aa && (fa = null, 0 < F && (1 === F ? ya.splice(-8, 8) : (ya = ya.concat(Ca), ya.push("Z")), Ca = [])), t.chart = d, t.index = P, t.value = w; else {
          Z = da.link;
          K = da.toolText;
          J = da.previousY;
          v = (v = z.getAxisPosition(J) || null) || ja;
          ga = z.getAxisPosition(w + (J || 0)) + (Q ? G : 0);
          if ((cb = da.marker) && cb.enabled)if (J = {
              index: P,
              link: Z,
              value: da.y,
              displayValue: da.displayValue,
              categoryLabel: da.categoryLabel,
              toolText: da.toolText,
              id: b.userID,
              datasetIndex: b.index,
              datasetName: b.name,
              visible: b.visible
            }, xa = za = {}, ra = da.rolloverProperties, cb.imageUrl)r = new a, r.onload = Da($, ga, cb, t, J, K, ra, P), r.onerror = xb($, ga, cb, t, J, K, ra, P), r.src = cb.imageUrl; else {
            qa = cb.symbol.split("_");
            r = cb.radius;
            ib = cb.shadow;
            ra && (xa = {
              polypath: [qa[1] || 2, $, ga, r, cb.startAngle, 0],
              fill: ha(cb.fillColor),
              "stroke-width": cb.lineWidth,
              stroke: ha(cb.lineColor)
            }, ra = da.rolloverProperties, za = {
              polypath: [ra.sides || 2, $, ga, ra.radius,
                ra.startAngle, ra.dip], fill: ha(ra.fillColor), "stroke-width": ra.lineWidth, stroke: ha(ra.lineColor)
            });
            ua = t.graphic = g.polypath(qa[1] || 2, $, ga, r, cb.startAngle, 0, Qa).attr({
              fill: ha(cb.fillColor),
              ishot: !E,
              "stroke-width": cb.lineWidth,
              stroke: ha(cb.lineColor),
              cursor: Z ? "pointer" : "",
              visibility: r ? T : "hidden"
            }).data("alwaysInvisible", !r).data("setRolloverProperties", ra).data("setRolloverAttr", za).data("setRolloutAttr", xa).data("anchorRadius", r).data("anchorHoverRadius", ra && ra.radius).shadow(ib || !1, bb);
            if (Z || E || ra)l ||
            (r = Ba(r, ra && ra.radius || 0, k)), va = t.tracker = g.circle({
              cx: $,
              cy: ga,
              r: r,
              cursor: Z ? "pointer" : "",
              stroke: f,
              "stroke-width": cb.lineWidth,
              fill: f,
              ishot: !0,
              visibility: T
            }, Ta);
            (va || ua).data("eventArgs", J).click(jb).hover(lb(t), qb(t)).tooltip(K);
            d.drawTracker && d.drawTracker.call(d, b, c, P)
          }
          null === fa ? (Ea.push("M", $, ",", ga), ya.push("M", $, ",", v), F = 0) : Ea.push("L", $, ",", ga);
          ya.push("L", $, ",", ga);
          Ca.unshift("L", $, ",", v);
          F++;
          fa = ga;
          cb && cb.imageUrl || (Xa = t.dataLabel = d.drawPlotLineLabel(b, c, P, $, ga));
          t.chart = d;
          t.index = P;
          t.value =
            w;
          t.dataLabel = Xa
        }
        Xa && u.push(Xa);
        ua && u.push(ua);
        va && u.push(va)
      }
      0 < F && (1 === F ? ya.splice(-8, 8) : (ya = ya.concat(Ca), ya.push("Z")));
      (Da = b.graphic = g.path(ya, db).attr({
        fill: na,
        "stroke-dasharray": e,
        "stroke-width": L ? 0 : D,
        stroke: pa,
        "stroke-linecap": "round",
        "stroke-linejoin": 2 < D ? "round" : "miter",
        visibility: T
      }).shadow(n.shadow && da.shadow, eb)) && u.push(Da);
      S && (n = ba.startPercent, s[2] = X + Aa[0], 1 === n && (Aa[0] = s[2], s[0] = 0));
      h ? fb = db.attr({"clip-rect": Aa}).animate({"clip-rect": s}, h, S ? "easeIn" : "normal", d.getAnimationCompleteFn()) :
        (C && C(), C = void 0);
      eb && (h ? eb.attr({"clip-rect": Aa}).animateWith(db, fb, {"clip-rect": s}, h, S ? "easeIn" : "normal", function () {
        eb.attr({"clip-rect": null})
      }) : eb.attr({"clip-rect": null}));
      L && (n = b.connector = g.path(Ea, Ya).attr({
        "stroke-dasharray": e,
        "stroke-width": D,
        stroke: pa,
        "stroke-linecap": "round",
        "stroke-linejoin": 2 < D ? "round" : "miter",
        visibility: T
      }), h ? Ya.attr({"clip-rect": Aa}).animateWith(db, fb, {"clip-rect": s}, h, S ? "easeIn" : "normal", function () {
        Ya.attr({"clip-rect": null})
      }) : Ya.attr({"clip-rect": null}), n && u.push(n));
      b.visible = !1 !== c.visible;
      return b
    }, drawPlotScatter: function (a, b) {
      var c = this, d = c.options, g = d.chart, h = d.plotOptions.series, k = c.paper, l = c.elements, m = a.items, n, s = a.graphics = a.graphics || [], q = c.xAxis[b.xAxis || 0], u = c.yAxis[b.yAxis || 0], t = a.data, A = !1 === b.visible ? "hidden" : "visible", d = !1 !== (d.tooltip || {}).enabled, z, C = h.dataLabels.style, B = {
        fontFamily: C.fontFamily,
        fontSize: C.fontSize,
        lineHeight: C.lineHeight,
        fontWeight: C.fontWeight,
        fontStyle: C.fontStyle,
        color: C.color
      }, C = isNaN(+h.animation) && h.animation.duration ||
        1E3 * h.animation, G = c.chartWidth, E = c.chartHeight, K, P, J, Q, L, R, T, U, S, X = b.lineWidth, ba = 0 < X, aa = b.color, r = b.dashStyle, w = h.connectNullData, v = [], D, e, da, N, W, $, Z = c.layers, fa = Z.dataset || (Z.dataset = k.group("dataset-orphan")), ga = Z.datalabels || (Z.datalabels = k.group("datalabels").insertAfter(fa)), Z = Z.tracker, g = g.anchorTrackingRadius, ka, F, M, na = function (a) {
        Ka.call(this, c, a)
      }, pa = function (a) {
        return function (b) {
          c.hoverPlotAnchor(this, b, "DataPlotRollOver", a, c)
        }
      }, qa = function (a) {
        return function (b) {
          c.hoverPlotAnchor(this,
            b, "DataPlotRollOut", a, c)
        }
      };
      c.addCSSDefinition(".fusioncharts-datalabels .fusioncharts-label", B);
      ga.attr("class", "fusioncharts-datalabels");
      C && (c.animationCompleteQueue.push({
        fn: function () {
          ga.attr({transform: "...t" + -G + "," + -E})
        }, scope: c
      }), ga.attr({transform: "...t" + G + "," + E}));
      ka = fa.line || (fa.line = k.group("line-connector", fa));
      a.lineShadowLayer = k.group("connector-shadow", ka);
      fa = a.anchorShadowLayer = k.group("anchor-shadow", ka);
      B = a.lineLayer = k.group("connector", ka);
      ka = a.anchorLayer = k.group("anchors", ka);
      K = 0;
      for (P = t.length; K < P; K += 1) {
        J = t[K];
        D = J.marker;
        U = S = N = z = W = null;
        M = a.index + "_" + K;
        R = J.y;
        L = J.x;
        if (null !== R && null !== L) {
          if (D && D.enabled) {
            Q = J.link;
            z = J.toolText;
            da = D.radius;
            F = D.shadow;
            S = u.getAxisPosition(R);
            U = q.getAxisPosition(L);
            e = {
              index: K,
              link: Q,
              y: J.y,
              x: J.x,
              displayValue: J.displayValue,
              categoryLabel: J.categoryLabel,
              toolText: J.toolText,
              id: a.userID,
              datasetIndex: a.index,
              datasetName: a.name,
              visible: a.visible
            };
            N = D.symbol.split("_");
            n = m[K] = {index: K, x: L, y: R, value: R};
            L = R = {};
            J.hoverEffects && (L = {
              polypath: [N[1] || 2, U,
                S, da, D.startAngle, 0], fill: ha(D.fillColor), "stroke-width": D.lineWidth, stroke: ha(D.lineColor)
            }, $ = J.rolloverProperties, R = {
              polypath: [$.sides || 2, U, S, $.radius, $.startAngle, $.dip],
              fill: ha($.fillColor),
              "stroke-width": $.lineWidth,
              stroke: ha($.lineColor)
            });
            N = n.graphic = k.polypath(N[1] || 2, U, S, da, D.startAngle, 0, ka).attr({
              fill: ha(D.fillColor),
              ishot: !d,
              "stroke-width": D.lineWidth,
              stroke: ha(D.lineColor),
              cursor: Q ? "pointer" : "",
              visibility: da ? A : "hidden"
            }).data("alwaysInvisible", !da).data("setRolloverProperties", $).data("setRolloverAttr",
              R).data("setRolloutAttr", L).data("anchorRadius", da).data("anchorHoverRadius", $ && $.radius).shadow(F || !1, fa);
            if (Q || d || $)da = Ba(da, $ && $.radius || 0, g), W = n.tracker = k.circle({
              cx: U,
              cy: S,
              r: da,
              cursor: Q ? "pointer" : "",
              stroke: f,
              "stroke-width": D.lineWidth,
              fill: f,
              ishot: !0,
              visibility: A
            }, Z);
            (W || N).data("eventArgs", e).data("groupId", M).click(na).hover(pa(n), qa(n)).tooltip(z)
          }
          ba && ((void 0 === T || null === T && 0 === w) && U && S && v.push("M", U, ",", S), U && S && v.push("L", U, ",", S), T = S);
          z = n.dataLabel = c.drawPlotLineLabel(a, b, K, U, S)
        } else ba &&
        0 === w && (T = null), m[K] = {chart: c, index: K, x: L, y: R};
        z && s.push(z);
        N && s.push(N);
        W && s.push(W);
        c.drawTracker && c.drawTracker.call(c, a, b, K)
      }
      v.length && (h = a.graphic = k.path(v, B).attr({
        "stroke-dasharray": r,
        "stroke-width": X,
        stroke: aa,
        "stroke-linecap": "round",
        "stroke-linejoin": 2 < X ? "round" : "miter",
        visibility: A
      }).shadow(h.shadow && J.shadow), B.attr({"clip-rect": l[C ? "clip-canvas-init" : "clip-canvas"]}), C && B.animate({"clip-rect": l["clip-canvas"]}, C, "normal"), s.push(h));
      C && ka.attr({opacity: 0}).animate({opacity: 1}, C, "normal",
        c.getAnimationCompleteFn());
      a.visible = !1 !== b.visible;
      return a
    }, drawPlotLineLabel: function (a, b, c, d, f, g) {
      var h = this.options, k = h.chart, l = this.paper, m = this.layers, n = h.plotOptions.series.dataLabels.style, h = 1 === k.rotateValues ? 270 : 0, s = this.canvasHeight, q = this.canvasTop, u = a.data, t = u[c], A = a.items[c], z = U(t.valuePosition, "auto").toLowerCase();
      a = this.logic.defaultSeriesType;
      var C = A.graphic, B = t.marker, C = B && B.enabled ? C && "image" == C.type && .5 * C.attr("height") || B && B.radius - 3 : 0, C = k.valuePadding + 2 + C;
      b = !1 === b.visible ?
        "hidden" : "visible";
      B = A.dataLabel;
      g = g || m.datalabels;
      switch (z) {
        case "above":
          c = 0;
          break;
        case "below":
          c = 1;
          break;
        default:
          m = u[c - 1] || {}, u = u[c + 1] || {}, c = c ? m.y > t.y ? 1 : (null == m.y && u.y) > t.y ? 1 : 0 : 0
      }
      m = t.displayValue;
      ya(m) && m !== S ? (B ? h && B.attr("transform", ["r", 360 - h]) : B = A.dataLabel = l.text(g).attr({
        "class": "fusioncharts-label",
        text: m,
        fill: n.color,
        "text-bound": [n.backgroundColor, n.borderColor, n.borderThickness, n.borderPadding, n.borderRadius, n.borderDash],
        direction: k.textDirection,
        "font-weight": n.fontWeight,
        "font-style": n.fontStyle,
        "font-family": n.fontFamily,
        "font-size": n.fontSize,
        "line-height": n.lineHeight
      }), B.attr({
        title: t.originalText || "",
        fill: n.color
      }), A._state && A._state.labelWidth || (g = B.getBBox(), A._state = {
        labelWidth: g.width,
        labelHeight: g.height
      }), k = l = h ? A._state.labelWidth : A._state.labelHeight, g = f - q, s = q + s - f, k = k + C + 4, q = .5 * l + C, /bubble/i.test(a) || (c ? s > k ? (f += q, t._valueBelowPoint = 1) : g > k && (f -= q, t._valueBelowPoint = 0) : g > k ? (f -= q, t._valueBelowPoint = 0) : s > k && (f += q, t._valueBelowPoint = 1)), B.attr({
        x: d,
        y: f,
        visibility: b
      }).data("isBelow",
        t._valueBelowPoint), h && B.attr("transform", "T0,0,R" + h)) : B && B.attr({text: S});
      return B
    }, drawLabels: function () {
      for (var a = this.paper, b = this.options, c = b.labels && b.labels.items && b.labels.items, d = c && c.length, f = this.layers.layerAboveDataset, g = this.elements.quadran || (this.elements.quadran = []), h = this.canvasTop, k = this.canvasLeft, b = b.chart.textDirection, l = {
        right: "end",
        left: "start",
        undefined: "start"
      }, m, n, s; d--;)s = c[d], m = s.style, n = {
        fontFamily: m.fontFamily, fontSize: m.fontSize, lineHeight: m.lineHeight, fontWeight: m.fontWeight,
        fontStyle: m.fontStyle, fill: m.color
      }, ya(s.html) && s.html !== S && (g[d] = a.text({text: s.html}, f).css(n).attr({
        x: parseInt(m.left, 10) + k,
        y: parseInt(m.top, 10) + h,
        "text-anchor": l[s.textAlign],
        "vertical-align": s.vAlign,
        direction: b,
        "text-bound": [m.backgroundColor, m.borderColor, m.borderThickness, m.borderPadding, m.borderRadius, m.borderDash]
      }))
    }
    }, G["renderer.root"]);
  G("renderer.piebase", {
      isHovered: !1, getPlotData: function (a, b) {
        var c = this.datasets[0], d = c.data[a], c = c.userData || (c.userData = []), f, g;
        if (c[a])c = c[a]; else {
          c =
            c[a] = {};
          for (g in d)"object" !== typeof(f = d[g]) && "function" !== typeof f && 0 !== g.indexOf("_") && (c[g] = f);
          c.value = c.y;
          c.label = c.name;
          delete c.y;
          delete c.total;
          delete c.doNotSlice;
          delete c.name;
          delete c.centerAngle;
          delete c.showInLegend
        }
        c.sliced = b;
        return c
      }, redrawDataLabels: function (a) {
        var b = a.elements.plots[0];
        a.placeDataLabels(!0, b.items, b);
        return {}
      }, sliceInOtherPies: function (a) {
        var b = this.options.series[0], c = b.plot.items, d = c.length, f = 0, g;
        for (b.enableMultiSlicing = !0; d--;)d !== a && (g = c[d]).sliced && ++f && this.plotGraphicClick.call(g);
        b.enableMultiSlicing = !1;
        return !!f
      }, plotGraphicClick: function (a) {
        var b = this.graphic || this, c = b.plotItem || b.data("plotItem"), d = c.seriesData, f = c.chart, g = f.logic.chartInstance, h = c.index, k = b.data("eventArgs") || {}, l = f.options.series[0].enableMultiSlicing, n = d.data[c.index].doNotSlice, s = c.slicedTranslation, q, u;
        !d.isRotating && Ka.call(b, f, a);
        if (!(d.isRotating || d.singletonCase || n || (b = !l && f.sliceInOtherPies(h), (a = c.sliced) && b)))return b = c.graphic, d = c.connector, l = c.dataLabel, s = "object" === typeof s ? "t" + s : s, n = c.connectorPath,
          q = (a ? -1 : 1) * c.transX, u = (a ? -1 : 1) * c.transY, t.raiseEvent("slicingStart", {
          slicedState: a,
          dataIndex: "index"in k && k.index,
          data: f.getPlotData(h, a)
        }, g), b.animate({transform: a ? "t0,0" : s}, 200, "easeIn", function () {
          t.raiseEvent("slicingEnd", {
            slicedState: c.sliced,
            dataIndex: "index"in k && k.index,
            data: f.getPlotData(h, c.sliced)
          }, g)
        }), l && l.x && l.animate({x: l.x + (a ? 0 : q)}, 200, "easeIn"), n && (n[1] += q, n[2] += u, n[4] += q, n[6] += q, d.animate({path: n}, 200, "easeIn")), a = c.sliced = !a, b = {hcJSON: {series: []}}, b.hcJSON.series[0] = {data: s = []},
          s[h] = {sliced: a}, m(g.jsVars._reflowData, b, !0), a
      }, plotDragStart: function (a, b, c) {
        var d = this.data("plotItem"), f = d.chart, d = d.seriesData, g = f.options.series, h = -f.datasets[0].startAngle * Ta;
        d.isRotating = !1;
        if (g[0].enableRotation) {
          g = f.container;
          f = {left: 0, top: 0};
          if (g.getBoundingClientRect)g = g.getBoundingClientRect(), f.top = g.top + (E.pageYOffset || l.scrollTop || 0) - (l.clientTop || 0), f.left = g.left + (E.pageXOffset || l.scrollLeft || 0) - (l.clientLeft || 0); else for (; g;)f.left += g.offsetLeft || 0, f.top += g.offsetTop || 0, g !== l.body &&
          g !== l.documentElement && (f.left -= g.scrollLeft || 0, f.top -= g.scrollTop || 0), g = g.offsetParent;
          d.chartPosition = f;
          a = k.call(c, a, b, d.pieCenter, d.chartPosition);
          d.dragStartAngle = a;
          d.startingAngleOnDragStart = h
        }
      }, plotDragEnd: function (a) {
        var b = this.data("plotItem"), c = b.chart, d = b.seriesData, f = -c.datasets[0].startAngle * Ta, g = {hcJSON: {series: [{startAngle: f}]}};
        c.disposed || (m(c.logic.chartInstance.jsVars._reflowData, g, !0), c.rotate(d, c.options.series[0]));
        !d.isRotating && c.plotGraphicClick.call(b, a);
        d.isRotating && (setTimeout(function () {
          d.isRotating = !1
        }, 0), t.raiseEvent("RotationEnd", {
          startingAngle: B(f, !0),
          changeInAngle: f - d.startingAngleOnDragStart
        }, c.logic.chartInstance));
        !c.isHovered && c.onPlotHover(this, !1)
      }, plotDragMove: function (a, b, c, d, f) {
        a = this.data("plotItem");
        var g = a.chart, h = a.seriesData, l = g.options.series;
        l[0].enableRotation && !h.singletonCase && (c = k.call(f, c, d, h.pieCenter, h.chartPosition), h.isRotating || (h.dragStartAngle !== c && (h.isRotating = !0), t.raiseEvent("RotationStart", {startingAngle: B(h.startingAngleOnDragStart, !0)}, g.logic.chartInstance)),
          l[0].startAngle += c - h.dragStartAngle, h.dragStartAngle = c, h.moveDuration = 0, c = (new Date).getTime(), !h._lastTime || h._lastTime + h.timerThreshold < c) && (setTimeout(function () {
          g.rotate(h, l[0])
        }, 0), h._lastTime = c)
      }, plotRollOver: function (a) {
        var b = this.plotItem || this.data("plotItem"), c = b.chart, d, f;
        b.seriesData.isRotating || (Ka.call(this, c, a, "DataPlotRollOver"), c.onPlotHover(this, !0));
        c.isHovered = !0;
        (a = b.innerDiameter) && (d = b.centerLabelConfig) && (f = d.label) && c.drawDoughnutCenterLabel(f, b.center[0], b.center[1], a, a,
          d, !1)
      }, plotRollOut: function (a) {
        var b = this.plotItem || this.data("plotItem"), c = b.chart, d = c.options.series[0], f, g;
        b.seriesData.isRotating || (Ka.call(this, c, a, "DataPlotRollOut"), c.onPlotHover(this, !1));
        c.isHovered = !1;
        (a = b.innerDiameter) && (f = d.centerLabelConfig) && ((g = f.label) || !g) && c.drawDoughnutCenterLabel(g, b.center[0], b.center[1], a, a, f, !1)
      }, onPlotHover: function (a, b) {
        var c = a.data("plotItem"), d = c.rolloverProperties, f = b ? d.color : c.color, g = b ? d.borderWidth : c.borderWidth, h = b ? d.borderColor : c.borderColor;
        d && c.graphic.attr({
          fill: ha(f),
          "stroke-width": g, stroke: h
        })
      }, getEventArgs: function (a) {
        a = a || {};
        return {
          datasetName: a.label,
          datasetIndex: a.originalIndex,
          id: a.userID,
          visible: !0,
          label: a.label,
          value: a.value,
          percentValue: a.percentage,
          tooltext: a.toolText,
          link: a.link,
          sliced: a.sliced
        }
      }, legendClick: function (a) {
        var b = a.chart;
        b.elements.plots[0].isRotating = !1;
        b.plotGraphicClick.call(a)
      }, placeDataLabels: function () {
        var a = function (a, b) {
          return a.point.value - b.point.value
        }, b = function (a, b) {
          return a.angle - b.angle
        }, c = ["start", "start", "end", "end"], d =
          [-1, 1, 1, -1], f = [1, 1, -1, -1];
        return function (g, h, k, l) {
          var m = this.options.plotOptions, n = m.pie, s = this.canvasLeft + .5 * this.canvasWidth, q = this.canvasTop + .5 * this.canvasHeight, u = this.smartLabel, t = m.series.dataLabels, A = t.style, z = ka(Ca(parseFloat(A.lineHeight)), 12), z = da(t.placeLabelsInside, 1 === h.length ? !0 : !1), m = t.skipOverlapLabels, C = t.manageLabelOverflow, B = t.connectorPadding, G = t.distance, E = l && l.metrics || [s, q, n.size, n.innerSize || 0], K = E[1], P = E[0];
          l = .5 * E[2];
          var J = [[], [], [], []], L = this.canvasLeft, Q = this.canvasTop,
            n = this.canvasWidth, G = k.labelsRadius || (k.labelsRadius = l + G), q = s = parseInt(A.fontSize, 10), R = q / 2, B = [B, B, -B, -B];
          k = k.labelsMaxInQuadrant || (k.labelsMaxInQuadrant = db(G / q));
          var t = t.isSmartLineSlanted, E = E[3] / 2, T, U, S, X, ba, r, w, v, D, e, aa, N, W, $, Z, fa, ga, ha, F, M;
          g || u.setStyle(A);
          if (1 == h.length && !E && z)X = h[0], (Z = X.dataLabel) && Z.show(), X.slicedTranslation = [L, Q], Z && (Z.attr({
            visibility: na,
            align: "middle",
            transform: ["t", P, K]
          }), Z.x = P); else if (z)M = E + (l - E) / 2, qa(h, function (a) {
            (Z = a.dataLabel) && Z.show();
            Z && (W = a.angle, N = K + M * Ea(W),
              v = P + M * pa(W), Z.x = v, Z._x = v, Z.y = N, a.sliced && (F = a.slicedTranslation, ga = F[0] - L, ha = F[1] - Q, v += ga, N += ha), Z.attr({
              visibility: na,
              align: "middle",
              transform: ["t", v, N]
            }))
          }); else {
            qa(h, function (a) {
              (Z = a.dataLabel) && Z.show();
              Z && (W = a.angle % qb, 0 > W && (W = qb + W), fa = 0 <= W && W < Qb ? 1 : W < ra ? 2 : W < Ib ? 3 : 0, J[fa].push({
                point: a,
                angle: W
              }))
            });
            for (h = g = 4; h--;) {
              if (m && (z = J[h].length - k, 0 < z))for (J[h].sort(a), A = J[h].splice(0, z), z = 0, S = A.length; z < S; z += 1)X = A[z].point, X.dataLabel.attr({visibility: "hidden"}), X.connector && X.connector.attr({visibility: "hidden"});
              J[h].sort(b)
            }
            z = Ba(J[0].length, J[1].length, J[2].length, J[3].length);
            $ = Ba(Ya(z, k) * q, G + q);
            J[1].reverse();
            for (J[3].reverse(); g--;) {
              E = J[g];
              S = E.length;
              m || (q = S > k ? $ / S : s, R = q / 2);
              X = S * q;
              A = $;
              for (h = 0; h < S; h += 1, X -= q)z = jb($ * Ea(E[h].angle)), A < z ? z = A : z < X && (z = X), A = (E[h].oriY = z) - q;
              T = c[g];
              S = $ - (S - 1) * q;
              A = 0;
              for (h = E.length - 1; 0 <= h; --h, S += q)if (X = E[h].point, W = E[h].angle, ba = X.sliced, Z = X.dataLabel, z = jb($ * Ea(W)), z < A ? z = A : z > S && (z = S), A = z + q, e = (z + E[h].oriY) / 2, r = P + f[g] * G * pa(ua.asin(e / $)), e *= d[g], e += K, aa = K + l * Ea(W), w = P + l * pa(W), (2 > g && r < w ||
                1 < g && r > w) && (r = w), v = r + B[g], N = e - R - 2, D = v + B[g], Z.x = D, Z._x = D, C && (U = 1 < g ? D - this.canvasLeft : this.canvasLeft + n - D, u.setStyle(X.style), z = ka(Ca(parseFloat(X.style.lineHeight)), 12) + 2 * Ca(ka(parseFloat(X.style.border), 12), 12), z = u.getSmartText(X.labelText, U, z), Z.attr({
                  text: z.text,
                  title: z.tooltext || ""
                })), Z.y = N, ba && (ga = X.transX, ha = X.transY, v += ga, r += ga, w += ga, aa += ha, D += ga), Z.attr({
                  visibility: na,
                  "text-anchor": T,
                  vAlign: "middle",
                  x: D,
                  y: e
                }), z = X.connector)X.connectorPath = X = ["M", w, aa, "L", t ? r : w, e, v, e], z.attr({
                path: X,
                visibility: na
              })
            }
          }
        }
      }()
    },
    G["renderer.root"])
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-interface", function () {
  var d = this, k = d.hcLib, B = d.renderer.getRenderer("javascript"), t = k.hasModule, E = k.loadModule, b = k.getMetaSentence, K = k.moduleCmdQueue, G = k.executeWaitingCommands, c = k.injectModuleDependency, l = k.moduleDependencies, a = k.getDependentModuleName, q, S;
  q = function (c) {
    var f, g, l, n = {}, q;
    c = b(c);
    for (f in d.core.items)f = d.core.items[f], g = f.chartType(), l = f.options.chartTypeSourcePath + g, (g = f.jsVars) && g.waitingModule && f.__state.rendering &&
    k.needsModule(c.predicate, l) && (g.waitingModuleError = !0, g = a(l).concat(g.userModules), g.length && (g = g[g.length - 1], n[g] = k.moduleCmdQueue[g]));
    for (q in n)G(n[q]);
    d.raiseError(d.core, "11171116151", "run", "HC-interface~renderer.load", "Unable to load required modules and resources: " + c.key)
  };
  S = function (a, b, c) {
    var k = a.args, l = a.options;
    a._chartMessageImageStyle = {
      imageHAlign: k.typeNotSupportedMessageImageHAlign || l.baseChartMessageImageHAlign,
      imageVAlign: k.typeNotSupportedMessageImageVAlign || l.baseChartMessageImageVAlign,
      imageAlpha: k.typeNotSupportedMessageImageAlpha || l.baseChartMessageImageAlpha,
      imageScale: k.typeNotSupportedMessageImageScale || l.baseChartMessageImageScale
    };
    a._chartMessageStyle = {
      color: k.typeNotSupportedMessageColor || l.baseChartMessageColor,
      fontFamily: k.typeNotSupportedMessageFont || l.baseChartMessageFont,
      fontSize: k.typeNotSupportedMessageFontSize || l.baseChartMessageFontSize
    };
    d.hcLib.createChart(a, b, "stub", c, l.typeNotSupportedMessage)
  };
  k.eventList = d.extend(d.legacyEventList, {
    loaded: "FC_Loaded",
    dataloaded: "FC_DataLoaded",
    rendered: "FC_Rendered",
    drawcomplete: "FC_DrawComplete",
    dataxmlinvalid: "FC_DataXMLInvalid",
    nodatatodisplay: "FC_NoDataToDisplay",
    exported: "FC_Exported"
  });
  k.raiseEvent = d.raiseEventWithLegacy;
  l.charts = d.extend(l.charts || {}, {
    column2d: 0,
    column3d: 0,
    bar2d: 0,
    bar3d: 0,
    pie2d: 0,
    pie3d: 0,
    line: 0,
    area2d: 0,
    doughnut2d: 0,
    doughnut3d: 0,
    pareto2d: 0,
    pareto3d: 0,
    mscolumn2d: 0,
    mscolumn3d: 0,
    msline: 0,
    msarea: 0,
    msbar2d: 0,
    msbar3d: 0,
    stackedcolumn2d: 0,
    marimekko: 0,
    stackedcolumn3d: 0,
    stackedarea2d: 0,
    stackedcolumn2dline: 0,
    stackedcolumn3dline: 0,
    stackedbar2d: 0,
    stackedbar3d: 0,
    msstackedcolumn2d: 0,
    mscombi2d: 0,
    mscombi3d: 0,
    mscolumnline3d: 0,
    mscombidy2d: 0,
    mscolumn3dlinedy: 0,
    stackedcolumn3dlinedy: 0,
    msstackedcolumn2dlinedy: 0,
    scatter: 0,
    bubble: 0,
    ssgrid: 0,
    scrollcolumn2d: 0,
    scrollcolumn3d: 0,
    scrollline2d: 0,
    scrollarea2d: 0,
    scrollstackedcolumn2d: 0,
    scrollcombi2d: 0,
    scrollcombidy2d: 0,
    zoomline: 0
  });
  l.powercharts = d.extend(l.powercharts || {}, {
    spline: 0,
    splinearea: 0,
    msspline: 0,
    mssplinearea: 0,
    mssplinedy: 0,
    multiaxisline: 0,
    multilevelpie: 0,
    waterfall2d: 0,
    msstepline: 0,
    inversemsline: 0,
    inversemscolumn2d: 0,
    inversemsarea: 0,
    errorbar2d: 0,
    errorscatter: 0,
    errorline: 0,
    logmsline: 0,
    logmscolumn2d: 0,
    logstackedcolumn2d: 0,
    radar: 0,
    dragnode: 0,
    candlestick: 0,
    selectscatter: 0,
    dragcolumn2d: 0,
    dragline: 0,
    dragarea: 0,
    boxandwhisker2d: 0,
    kagi: 0,
    heatmap: 0
  });
  l.widgets = d.extend(l.widgets || {}, {
    angulargauge: 0,
    bulb: 0,
    cylinder: 0,
    drawingpad: 0,
    funnel: 0,
    hbullet: 0,
    hled: 0,
    hlineargauge: 0,
    vlineargauge: 0,
    pyramid: 0,
    realtimearea: 0,
    realtimecolumn: 0,
    realtimeline: 0,
    realtimelinedy: 0,
    realtimestackedarea: 0,
    realtimestackedcolumn: 0,
    sparkcolumn: 0,
    sparkline: 0,
    sparkwinloss: 0,
    thermometer: 0,
    vbullet: 0,
    gantt: 0,
    vled: 0
  });
  l.maps = d.extend(l.maps || {}, {});
  d.extend(B, {
    render: function (b, f) {
      var g = this.chartType(), l = this.options.chartTypeSourcePath + g, n = this.jsVars, q = this.__state, A = k.chartAPI, m = this.options, E = this.args, z = this.options.showChartLoadingMessage, G, u;
      G = a(l).concat(n.userModules);
      n.isResizing && (n.isResizing = clearTimeout(n.isResizing));
      n.hcObj && n.hcObj.destroy && n.hcObj.destroy();
      if (A[g]) {
        if (A[q.lastRenderedType] && q.lastRenderedType !==
          g)for (u in d.raiseEvent("chartTypeChanged", {
          previousType: q.lastRenderedType,
          newType: g
        }, this), A[q.lastRenderedType].eiMethods)delete this[u];
        q.lastRenderedType = g;
        q.lastRenderedSrc = this.src;
        !n.waitingModuleError && k.raiseEvent("internal.loaded", {
          type: g,
          triggeredModuleLoad: n.drLoadAttempted || n.waitingModule
        }, this, [this.id]);
        delete n.waitingModule;
        delete n.waitingModuleError;
        delete n.drLoadAttempted;
        d.hcLib.createChart(this, b, g, f)
      } else {
        if (g && t(G)) {
          if (n.drLoadAttempted) {
            d.raiseError(this, 11112822001, "run",
              "HC-interface~renderer.render", "Chart runtimes not loaded even when resource is present");
            S(this, b, f);
            return
          }
          c(l) && (G = a(l).concat(n.userModules));
          n.drLoadAttempted = !0
        } else {
          if (!G.length) {
            S(this, b, f);
            return
          }
          if (n.waitingModuleError) {
            S(this, b, f);
            delete n.waitingModule;
            delete n.waitingModuleError;
            return
          }
        }
        (g = K[G[G.length - 1]]) ? (g.push({
          cmd: "render",
          obj: this,
          args: arguments
        }), n.waitingModule || (n = z ? m.PBarLoadingText || m.loadMessage : "", this._chartMessageImageStyle = {
          imageHAlign: E.loadMessageImageHAlign || m.baseChartMessageImageHAlign,
          imageVAlign: E.loadMessageImageVAlign || m.baseChartMessageImageVAlign,
          imageAlpha: E.loadMessageImageAlpha || m.baseChartMessageImageAlpha,
          imageScale: E.loadMessageImageScale || m.baseChartMessageImageScale
        }, this._chartMessageStyle = {
          color: E.loadMessageColor || m.baseChartMessageColor,
          fontFamily: E.loadMessageFont || m.baseChartMessageFont,
          fontSize: E.loadMessageFontSize || m.baseChartMessageFontSize
        }, d.hcLib.createChart(this, b, "stub", void 0, n), B.load.call(this, b, f))) : (d.raiseError(this, 12080515551, "run", "HC-interface~renderer.render",
          "Unregistered module in dependentModule definition."), this._chartMessageImageStyle = {
          imageHAlign: E.renderErrorMessageImageHAlign || m.baseChartMessageImageHAlign,
          imageVAlign: E.renderErrorMessageImageVAlign || m.baseChartMessageImageVAlign,
          imageAlpha: E.renderErrorMessageImageAlpha || m.baseChartMessageImageAlpha,
          imageScale: E.renderErrorMessageImageScale || m.baseChartMessageImageScale
        }, this._chartMessageStyle = {
          color: E.renderErrorMessageColor || m.baseChartMessageColor,
          fontFamily: E.renderErrorMessageFont || m.baseChartMessageFont,
          fontSize: E.renderErrorMessageFontSize || m.baseChartMessageFontSize
        }, d.hcLib.createChart(this, b, "stub", void 0, m.renderErrorMessage))
      }
    }, update: function (a) {
      var b = this.ref, c = this.jsVars, k = c && c.fcObj, k = c.container || k && k.options && k.options.containerElement && k.options.containerElement.childNodes[0];
      c.hcObj && c.hcObj.destroy && c.hcObj.destroy();
      c.isResizing && (c.isResizing = clearTimeout(c.isResizing));
      void 0 === a.error ? (delete c.stallLoad, delete c.loadError, this.isActive() ? this.src !== this.__state.lastRenderedSrc ?
        this.render() : d.hcLib.createChart(this, k) : this.__state.rendering && !c.waitingModule && d.hcLib.createChart(this, k)) : (this.isActive() && "function" === typeof b.showChartMessage && b.showChartMessage("InvalidXMLText"), delete c.loadError)
    }, resize: function (a) {
      var b = this.ref, c, k = this.jsVars;
      b && b.resize && (k.isResizing && (k.isResizing = clearTimeout(k.isResizing)), k.isResizing = setTimeout(function () {
        c = d.normalizeCSSDimension(a.width, a.height, b);
        void 0 !== a.width && (b.style.width = c.width);
        void 0 !== a.height && (b.style.height =
          c.height);
        b.resize();
        delete k.isResizing
      }, 0))
    }, dispose: function () {
      var a, b = this.jsVars;
      b.isResizing && (b.isResizing = clearTimeout(b.isResizing));
      b.instanceAPI && b.instanceAPI.dispose && (b.instanceAPI.dispose(), delete b.instanceAPI);
      if (a = this.ref)d.purgeDOM(a), a.parentNode && a.parentNode.removeChild(a);
      b.container = null;
      k.cleanupWaitingCommands(this)
    }, load: function (b, c) {
      var g = this.jsVars, l = this.chartType(), n = d.hcLib.chartAPI[l], l = a(l).concat(g.userModules), t = l[l.length - 1];
      n || !l || l && 0 === l.length ? (delete g.waitingModule,
      b && S(this, b || this.ref, c)) : g.waitingModule || (g.waitingModule = !0, delete g.waitingModuleError, E(l, function () {
        delete g.waitingModule;
        G(k.moduleCmdQueue[t])
      }, q, this))
    }
  })
}]);
FusionCharts.register("module", ["private", "modules.api.dynamicchartattributes", function () {
  var d = this;
  d.extend(d.core, {
    setChartAttribute: function (k, B) {
      var t, E, b, K;
      if ("string" === typeof k)t = k, k = {}, k[t] = B; else if (null === k || "object" !== typeof k)return;
      K = 0;
      if (b = (t = this.getChartData(d.dataFormats.JSON)) && (t.chart || t.graph || t.map)) {
        for (E in k)K += 1, null === k[E] ? delete b[E.toLowerCase()] : b[E.toLowerCase()] = k[E];
        0 < K && ("undefined" === typeof b.animation && (b.animation = "0"), this.setChartData(t, d.dataFormats.JSON))
      } else d.raiseError(this,
        "2105141421", "run", "#setChartAttribute()", "Could not retrieve attribute list. Is data ready?")
    }, getChartAttribute: function (k) {
      var B = this.getChartData(d.dataFormats.JSON), B = B && (B.chart || B.graph || B.map), t, E;
      if (0 === arguments.length || void 0 === k || void 0 === B)return B;
      if ("string" === typeof k)t = B[k.toString().toLowerCase()]; else if (k instanceof Array)for (t = {}, E = 0; E < k.length; E += 1)t[k[E]] = B[k[E].toString().toLowerCase()]; else d.raiseError(this, "25081429", "param", "~getChartAttribute()", 'Unexpected value of "attribute"');
      return t
    }
  }, !0)
}]);
FusionCharts.register("module", ["private", "api.linkmanager", function () {
  var d = this, k = d.FusionChartsDOMInsertModes, B = {}, t = function (b, k) {
    this.items = {};
    this.root = b;
    this.parent = k;
    k instanceof d.core ? this.level = this.parent.link.level + 1 : (B[b.id] = [{}], this.level = 0)
  }, E = function (b, d) {
    return (b.options.containerElement === d.options.containerElement || b.options.containerElementId === d.options.containerElementId) && b.options.insertMode === k.REPLACE
  };
  d.policies.link = ["link", void 0];
  t.prototype.configuration = function () {
    return B[this.root.id][this.level] ||
      (B[this.root.id][this.level] = {})
  };
  d.extend(d.core, {
    configureLink: function (b, k) {
      var t;
      if (b instanceof Array) {
        for (t = 0; t < b.length; t += 1)"object" !== typeof B[this.link.root.id][t] && (B[this.link.root.id][t] = {}), d.extend(B[this.link.root.id][t], b[t]);
        B[this.link.root.id].splice(b.length)
      } else"object" === typeof b ? ("number" !== typeof k && (k = this.link.level), void 0 === B[this.link.root.id][k] && (B[this.link.root.id][k] = {}), d.extend(B[this.link.root.id][k], b)) : d.raiseError(this, "25081731", "param", "~configureLink()",
        "Unable to update link configuration from set parameters")
    }
  }, !0);
  d.addEventListener("beforeInitialize", function (b) {
    b.sender.link instanceof t ? b.sender.link.parent instanceof d.core && (b.sender.link.parent.link.items[b.sender.id] = b.sender) : b.sender.link = new t(b.sender)
  });
  d.addEventListener("linkedChartInvoked", function (b, k) {
    var B = b.sender, c = B.clone({
      dataSource: k.data,
      dataFormat: k.linkType,
      link: new t(B.link.root, B)
    }, !0), l = k.alias, a;
    l && (!c.typeSource && c.swfUrl && (c.typeSource = c.swfUrl.replace(/(.*?)?[^\/]*\.swf.*?/ig,
      "$1")), c.type = l);
    B.args && 0 !== parseInt(B.args.animate, 10) && delete c.animate;
    d.extend(c, B.link.configuration());
    d.raiseEvent("beforeLinkedItemOpen", {level: B.link.level}, B.link.root, void 0, function () {
      d.core.items[c.id]instanceof d.core && d.core.items[c.id].dispose();
      a = new d.core(c);
      E(a, B) || B.options.overlayButton && B.options.overlayButton.message || ("object" !== typeof B.options.overlayButton && (B.options.overlayButton = {}), B.options.overlayButton.message = "Close");
      a.render();
      d.raiseEvent("linkedItemOpened",
        {level: B.link.level, item: a}, B.link.root)
    })
  });
  d.addEventListener("overlayButtonClick", function (b, k) {
    if ("LinkManager" === k.id) {
      var t = b.sender, c = t.link.level - 1, l = t.link.parent, a = t.link.root;
      d.raiseEvent("beforeLinkedItemClose", {level: c, item: t}, a, t, function () {
        setTimeout(function () {
          d.core.items[t.id] && t.dispose();
          d.raiseEvent("linkedItemClosed", {level: c}, a)
        }, 0);
        l.disposed || l.isActive() || !E(t, l) || l.render()
      })
    }
  });
  d.addEventListener("Loaded", function (b) {
    b = b.sender;
    var k;
    b && void 0 !== b.link && b.link.root !== b &&
    b.link.parent instanceof d.core && (b.ref && "function" === typeof b.ref.drawOverlayButton ? (k = d.extend({
      show: !0,
      id: "LinkManager"
    }, b.link.parent.options.overlayButton), d.extend(k, b.link.parent.link.configuration().overlayButton || {}), b.ref.drawOverlayButton(k)) : d.raiseWarning(b, "04091602", "run", "::LinkManager^Loaded", "Unable to draw overlay button on object. -" + b.id))
  });
  d.addEventListener("beforeDispose", function (b) {
    var k = b.sender;
    k && k.link instanceof t && (k && k.link && k.link.parent instanceof d.core && k.link.parent.link &&
    k.link.parent.link.items && delete k.link.parent.link.items[b.sender.id], delete B[k.id])
  })
}]);
FusionCharts.register("module", ["private", "modules.renderer.js-thememanager", function () {
  var d = this, k, B, t, E = /\s+!important$/, b = /\\!important$/, K = function (a, b) {
    for (var c = b.length, d = -1; c--;)if (a === b[c]) {
      d = c;
      break
    }
    return d
  }, G = function (a, b, c, d, k) {
    var l, m, q, t;
    k ? (d.push(a), k.push(b)) : (d = [a], k = [b]);
    if (b instanceof Array)for (l = 0; l < b.length; l += 1) {
      try {
        m = a[l], q = b[l]
      } catch (B) {
        continue
      }
      if ("object" !== typeof q)c && void 0 === q || (a[l] = q); else {
        if (null === m || "object" !== typeof m)m = a[l] = q instanceof Array ? [] : {};
        t = K(q, k);
        -1 !== t ? m = a[l] = d[t] : G(m, q, c, d, k)
      }
    } else for (l in b) {
      try {
        m = a[l], q = b[l]
      } catch (u) {
        continue
      }
      if (null !== q && "object" === typeof q)if (t = Object.prototype.toString.call(q), "[object Object]" === t) {
        if (null === m || "object" !== typeof m)m = a[l] = {};
        t = K(q, k);
        -1 !== t ? m = a[l] = d[t] : G(m, q, c, d, k)
      } else"[object Array]" === t ? (null !== m && m instanceof Array || (m = a[l] = []), t = K(q, k), -1 !== t ? m = a[l] = d[t] : G(m, q, c, d, k)) : a[l] = q; else a[l] = q
    }
    return a
  }, c = function (a, b, c) {
    if ("object" !== typeof a && "object" !== typeof b)return null;
    if ("object" !== typeof b ||
      null === b)return a;
    "object" !== typeof a && (a = b instanceof Array ? [] : {});
    G(a, b, c);
    return a
  }, l = function (a) {
    var c = {important: !1, str: ""};
    if (!a)return c;
    a = a.toString();
    E.test(a) ? (a = a.replace(E, ""), c.important = !0) : (a = a.replace(b, "!imporant"), c.important = !1);
    c.str = a;
    return c
  }, a = function (b, c) {
    var d, k, l, t, m, B, z = 0, C = 0;
    for (d in b)if (k = b[d], k instanceof Array)for (B = k.length, m = 0; m < B; m += 1) {
      if (t = k[m], "object" === typeof t)if ("category" === d)if ("true" === t.vline) {
        if (l = c.component("vline", z, t))q(t, l), z += 1
      } else {
        if (l = c.component("category",
            C, t, B))q(t, l), C += 1
      } else if (l = c.component(d, m, t, B))q(t, l), a(t, l)
    } else"object" === typeof k && (l = c.component(d, null, k)) && (q(k, l), a(k, l))
  }, q = function (a, b) {
    var c = b.getAll(), d, k;
    for (d in c)k = c[d].toString(), k = l(k), k.important ? a[d.toLowerCase()] = k.str : void 0 === a[d.toLowerCase()] && (a[d.toLowerCase()] = k.str)
  }, S = function (a, b) {
    "geo" === b.defaultSeriesType && C.call(this, a, b)
  }, C = function (a, b) {
    var c = a.sender, k = c.getChartData(d.dataFormats.JSON, !0), l;
    k.error || ((l = k.data.chart.theme) ? t.themify(l, c, c.chartType(), k.data,
      "geo" === b.defaultSeriesType && "geo") : c.jsVars.themeObject && c.jsVars.themeObject.dispose())
  };
  k = function () {
    this.themeStore = {}
  };
  k.prototype = {
    constructor: k, add: function (a) {
      for (var b = 0, c = a.length, d; b < c; b += 1)(d = a[b].name) && (this.themeStore[d] = a[b])
    }, themify: function (a, b, c, k, l) {
      var q = b.jsVars, m = a.split(","), t = [], z = m.length, E, u;
      if (z) {
        for (u = 0; u < z; u += 1) {
          E = this.themeStore;
          var G;
          G = m[u];
          G = G.replace(/^\s\s*/, "");
          for (var K = /\s/, ba = G.length; K.test(G.charAt(--ba)););
          G = G.slice(0, ba + 1);
          (E = E[G]) && t.push(this.evaluateThemeJSON(E.theme,
            b, c, l))
        }
        t.length ? (q.themeObject = new B(t, b, !1, k), this.applyTheme(b), b.addEventListener("chartTypeChanged", C), b.addEventListener("internal.drawstart", S)) : d.raiseWarning(b, "14051100501", "run", "api.themes~themify()", 'The theme "' + a + '" requested has not been registered.')
      }
    }, evaluateThemeJSON: function (a, b, d, k) {
      var l = {}, q = b.jsVars, m = function (a) {
        var b, d;
        for (b in a)d = a[b], l[b] = d instanceof Array ? c(l[b] || [], d) : "object" === typeof d ? c(l[b] || {}, d) : d
      };
      d = d || b.chartType();
      q.themeObject && a !== q.themeObject && (q.themeObject.dispose(),
        delete q.themeObject);
      m(a.base);
      k && a[k] && m(a[k]);
      d && a[d] && m(a[d]);
      return l
    }, applyTheme: function (b) {
      b = b.jsVars.themeObject;
      var c = b.getThemedJSONData().data;
      c && a(c, b)
    }
  };
  B = function (a, b, d, k) {
    this.themeArray = a;
    this.themeComponents = {};
    this.base = {};
    this.chartInstance = b;
    this.isChildInstance = Boolean(d);
    this.themedData = d ? null : c({}, k);
    this.length = a.length;
    b = 0;
    for (d = a.length; b < d; b += 1)this.parse(a[b])
  };
  B.prototype = {
    constructor: B, pushTheme: function (a) {
      a && (this.themeArray.push(a), this.parse(a), this.length += 1)
    },
    popTheme: function () {
    }, parse: function (a) {
      var b = this.themeComponents, d = this.chartInstance, k = this.base, q, t, m;
      for (t in a)if ("string" === typeof a[t] || "number" === typeof a[t])if (k[t]) {
        if (q = l(a[t]), m = l(k[t]), q.important || !m.important)k[t] = a[t]
      } else k[t] = a[t]; else b[t] || (b[t] = []), q = b[t], a[t]instanceof Array ? q.push(c([], a[t])) : "object" === typeof a[t] ? q.push(new B([a[t]], d, !0)) : "function" === typeof a[t] && q.push(a[t])
    }, merge: function (a) {
      var b = this.base, c = a.base, d = this.themeComponents, k = a.themeComponents, q, m, t;
      for (t in c)if (q =
          l(b[t]), m = l(c[t]), !q.important || m.important)b[t] = c[t];
      for (t in k)d[t] = d[t] ? d[t].concat(k[t]) : [].concat(k[t]);
      this.length += a.length
    }, get: function (a) {
      return this.base[a]
    }, getAll: function () {
      return c({}, this.base)
    }, component: function (a, b, c, d) {
      var k = this.themeComponents, l = this.chartInstance, m = new B([], l, !0), q, t, C;
      t = k[a];
      if (!t)return null;
      a = 0;
      for (k = t.length; a < k; a += 1)C = t[a], "function" === typeof C ? (b = b || 0, m.pushTheme(C.call(l, b, c, d))) : C instanceof Array ? (b = b || 0, q = C.length, b %= q, q = C[b], q instanceof B ? m.merge(q) :
        "function" === typeof q ? m.pushTheme(q.call(l, b, c, d)) : m.pushTheme(q)) : C instanceof B ? m.merge(C) : m.pushTheme(C);
      return m
    }, getThemedJSONData: function () {
      return {data: this.themedData}
    }, dispose: function () {
      var a = this.themeComponents, b = this.chartInstance, c, d;
      for (c in a)if (d = a[c].length) {
        for (; d--;)a[c][d].dispose && a[c][d].dispose();
        delete a[c]
      }
      this.isChildInstance || (b.removeEventListener("chartTypeChanged", C), b.removeEventListener("internal.drawstart", S));
      this.dataWithoutTheme = this.isChildInstance = this.themeArray =
        this.base = this.chartInstance = this.themeComponents = null
    }
  };
  t = new k;
  d.registrars.theme = d.registerTheme = function (a) {
    a && ("[object Array]" !== Object.prototype.toString.call(a) && (a = [a]), t.add(a))
  };
  d.addEventListener("beforeDataUpdate", function (a, b) {
    var c = a.sender, k = d.core.transcodeData(b.data, b.format, d.dataFormats.JSON), l = k.chart && k.chart.theme;
    l ? t.themify(l, c, c.args.type, k) : c.jsVars.themeObject && (c.jsVars.themeObject.dispose(), delete c.jsVars.themeObject)
  })
}]);
FusionCharts.register("theme", {
  name: "default", theme: {
    base: {
      chart: {
        labelDisplay: "stagger !important",
        caption: "Theme Caption \\!important",
        canvasBgColor: "#56EF22",
        borderThickness: "5 !important",
        borderColor: "#E60539",
        baseFontColor: "#781129"
      }, categories: [{
        fontColor: "#0F4F40", fontSize: 15, category: function (d) {
          return {showLabel: d % 2 ? 0 : 1}
        }, vline: {color: "#000000", thickness: 2}
      }], dataset: [{
        color: "#8C3146", data: function (d, k) {
          8 == d && (k.value = "");
          return {color: 32E3 > Number(k.value) ? "#8C3146" : "#FF0000", alpha: "100"}
        }
      }],
      trendlines: [{
        line: function (d) {
          return d ? {color: "#ff0000", thickness: 3} : {color: "#ffff00", thickness: 3}
        }
      }]
    },
    pie2d: {chart: {bgColor: "#FF0000"}},
    msline: {chart: {canvasBgColor: "#ff0000"}},
    geo: {chart: {canvasBgColor: "#0000ff"}},
    world: {chart: {canvasBgColor: "#00ff00"}}
  }
});
