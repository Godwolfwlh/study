/*
 * JQuery zTree core v3.5.30
 * http://treejs.cn/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2017-11-11
 */
(function ($) {
    var settings = {}, roots = {}, caches = {},
        //default consts of core
        _consts = {
            className: {
                BUTTON: "button",
                LEVEL: "level",
                ICO_LOADING: "ico_loading",
                SWITCH: "switch",
                NAME: 'node_name'
            },
            event: {
                NODECREATED: "ztree_nodeCreated",
                CLICK: "ztree_click",
                EXPAND: "ztree_expand",
                COLLAPSE: "ztree_collapse",
                ASYNC_SUCCESS: "ztree_async_success",
                ASYNC_ERROR: "ztree_async_error",
                REMOVE: "ztree_remove",
                SELECTED: "ztree_selected",
                UNSELECTED: "ztree_unselected"
            },
            id: {
                A: "_a",
                ICON: "_ico",
                SPAN: "_span",
                SWITCH: "_switch",
                UL: "_ul"
            },
            line: {
                ROOT: "root",
                ROOTS: "roots",
                CENTER: "center",
                BOTTOM: "bottom",
                NOLINE: "noline",
                LINE: "line"
            },
            folder: {
                OPEN: "open",
                CLOSE: "close",
                DOCU: "docu"
            },
            node: {
                CURSELECTED: "curSelectedNode"
            }
        },
        //default setting of core
        _setting = {
            treeId: "",
            treeObj: null,
            view: {
                addDiyDom: null,
                autoCancelSelected: true,
                dblClickExpand: true,
                expandSpeed: "fast",
                fontCss: {},
                nameIsHTML: false,
                selectedMulti: true,
                showIcon: true,
                showLine: true,
                showTitle: true,
                txtSelectedEnable: false
            },
            data: {
                key: {
                    children: "children",
                    name: "name",
                    title: "",
                    url: "url",
                    icon: "icon"
                },
                simpleData: {
                    enable: false,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                },
                keep: {
                    parent: false,
                    leaf: false
                }
            },
            async: {
                enable: false,
                contentType: "application/x-www-form-urlencoded",
                type: "post",
                dataType: "text",
                url: "",
                autoParam: [],
                otherParam: [],
                dataFilter: null
            },
            callback: {
                beforeAsync: null,
                beforeClick: null,
                beforeDblClick: null,
                beforeRightClick: null,
                beforeMouseDown: null,
                beforeMouseUp: null,
                beforeExpand: null,
                beforeCollapse: null,
                beforeRemove: null,

                onAsyncError: null,
                onAsyncSuccess: null,
                onNodeCreated: null,
                onClick: null,
                onDblClick: null,
                onRightClick: null,
                onMouseDown: null,
                onMouseUp: null,
                onExpand: null,
                onCollapse: null,
                onRemove: null
            }
        },
        //default root of core
        //zTree use root to save full data
        _initRoot = function (setting) {
            var r = data.getRoot(setting);
            if (!r) {
                r = {};
                data.setRoot(setting, r);
            }
            r[setting.data.key.children] = [];
            r.expandTriggerFlag = false;
            r.curSelectedList = [];
            r.noSelection = true;
            r.createdNodes = [];
            r.zId = 0;
            r._ver = (new Date()).getTime();
        },
        //default cache of core
        _initCache = function (setting) {
            var c = data.getCache(setting);
            if (!c) {
                c = {};
                data.setCache(setting, c);
            }
            c.nodes = [];
            c.doms = [];
        },
        //default bindEvent of core
        _bindEvent = function (setting) {
            var o = setting.treeObj,
                c = consts.event;
            o.bind(c.NODECREATED, function (event, treeId, node) {
                tools.apply(setting.callback.onNodeCreated, [event, treeId, node]);
            });

            o.bind(c.CLICK, function (event, srcEvent, treeId, node, clickFlag) {
                tools.apply(setting.callback.onClick, [srcEvent, treeId, node, clickFlag]);
            });

            o.bind(c.EXPAND, function (event, treeId, node) {
                tools.apply(setting.callback.onExpand, [event, treeId, node]);
            });

            o.bind(c.COLLAPSE, function (event, treeId, node) {
                tools.apply(setting.callback.onCollapse, [event, treeId, node]);
            });

            o.bind(c.ASYNC_SUCCESS, function (event, treeId, node, msg) {
                tools.apply(setting.callback.onAsyncSuccess, [event, treeId, node, msg]);
            });

            o.bind(c.ASYNC_ERROR, function (event, treeId, node, XMLHttpRequest, textStatus, errorThrown) {
                tools.apply(setting.callback.onAsyncError, [event, treeId, node, XMLHttpRequest, textStatus, errorThrown]);
            });

            o.bind(c.REMOVE, function (event, treeId, treeNode) {
                tools.apply(setting.callback.onRemove, [event, treeId, treeNode]);
            });

            o.bind(c.SELECTED, function (event, treeId, node) {
                tools.apply(setting.callback.onSelected, [treeId, node]);
            });
            o.bind(c.UNSELECTED, function (event, treeId, node) {
                tools.apply(setting.callback.onUnSelected, [treeId, node]);
            });
        },
        _unbindEvent = function (setting) {
            var o = setting.treeObj,
                c = consts.event;
            o.unbind(c.NODECREATED)
                .unbind(c.CLICK)
                .unbind(c.EXPAND)
                .unbind(c.COLLAPSE)
                .unbind(c.ASYNC_SUCCESS)
                .unbind(c.ASYNC_ERROR)
                .unbind(c.REMOVE)
                .unbind(c.SELECTED)
                .unbind(c.UNSELECTED);
        },
        //default event proxy of core
        _eventProxy = function (event) {
            var target = event.target,
                setting = data.getSetting(event.data.treeId),
                tId = "", node = null,
                nodeEventType = "", treeEventType = "",
                nodeEventCallback = null, treeEventCallback = null,
                tmp = null;

            if (tools.eqs(event.type, "mousedown")) {
                treeEventType = "mousedown";
            } else if (tools.eqs(event.type, "mouseup")) {
                treeEventType = "mouseup";
            } else if (tools.eqs(event.type, "contextmenu")) {
                treeEventType = "contextmenu";
            } else if (tools.eqs(event.type, "click")) {
                if (tools.eqs(target.tagName, "span") && target.getAttribute("treeNode" + consts.id.SWITCH) !== null) {
                    tId = tools.getNodeMainDom(target).id;
                    nodeEventType = "switchNode";
                } else {
                    tmp = tools.getMDom(setting, target, [{tagName: "a", attrName: "treeNode" + consts.id.A}]);
                    if (tmp) {
                        tId = tools.getNodeMainDom(tmp).id;
                        nodeEventType = "clickNode";
                    }
                }
            } else if (tools.eqs(event.type, "dblclick")) {
                treeEventType = "dblclick";
                tmp = tools.getMDom(setting, target, [{tagName: "a", attrName: "treeNode" + consts.id.A}]);
                if (tmp) {
                    tId = tools.getNodeMainDom(tmp).id;
                    nodeEventType = "switchNode";
                }
            }
            if (treeEventType.length > 0 && tId.length == 0) {
                tmp = tools.getMDom(setting, target, [{tagName: "a", attrName: "treeNode" + consts.id.A}]);
                if (tmp) {
                    tId = tools.getNodeMainDom(tmp).id;
                }
            }
            // event to node
            if (tId.length > 0) {
                node = data.getNodeCache(setting, tId);
                switch (nodeEventType) {
                    case "switchNode" :
                        if (!node.isParent) {
                            nodeEventType = "";
                        } else if (tools.eqs(event.type, "click")
                            || (tools.eqs(event.type, "dblclick") && tools.apply(setting.view.dblClickExpand, [setting.treeId, node], setting.view.dblClickExpand))) {
                            nodeEventCallback = handler.onSwitchNode;
                        } else {
                            nodeEventType = "";
                        }
                        break;
                    case "clickNode" :
                        nodeEventCallback = handler.onClickNode;
                        break;
                }
            }
            // event to zTree
            switch (treeEventType) {
                case "mousedown" :
                    treeEventCallback = handler.onZTreeMousedown;
                    break;
                case "mouseup" :
                    treeEventCallback = handler.onZTreeMouseup;
                    break;
                case "dblclick" :
                    treeEventCallback = handler.onZTreeDblclick;
                    break;
                case "contextmenu" :
                    treeEventCallback = handler.onZTreeContextmenu;
                    break;
            }
            var proxyResult = {
                stop: false,
                node: node,
                nodeEventType: nodeEventType,
                nodeEventCallback: nodeEventCallback,
                treeEventType: treeEventType,
                treeEventCallback: treeEventCallback
            };
            return proxyResult
        },
        //default init node of core
        _initNode = function (setting, level, n, parentNode, isFirstNode, isLastNode, openFlag) {
            if (!n) return;
            var r = data.getRoot(setting),
                childKey = setting.data.key.children;
            n.level = level;
            n.tId = setting.treeId + "_" + (++r.zId);
            n.parentTId = parentNode ? parentNode.tId : null;
            n.open = (typeof n.open == "string") ? tools.eqs(n.open, "true") : !!n.open;
            if (n[childKey] && n[childKey].length > 0) {
                n.isParent = true;
                n.zAsync = true;
            } else {
                n.isParent = (typeof n.isParent == "string") ? tools.eqs(n.isParent, "true") : !!n.isParent;
                n.open = (n.isParent && !setting.async.enable) ? n.open : false;
                n.zAsync = !n.isParent;
            }
            n.isFirstNode = isFirstNode;
            n.isLastNode = isLastNode;
            n.getParentNode = function () {
                return data.getNodeCache(setting, n.parentTId);
            };
            n.getPreNode = function () {
                return data.getPreNode(setting, n);
            };
            n.getNextNode = function () {
                return data.getNextNode(setting, n);
            };
            n.getIndex = function () {
                return data.getNodeIndex(setting, n);
            };
            n.getPath = function () {
                return data.getNodePath(setting, n);
            };
            n.isAjaxing = false;
            data.fixPIdKeyValue(setting, n);
        },
        _init = {
            bind: [_bindEvent],
            unbind: [_unbindEvent],
            caches: [_initCache],
            nodes: [_initNode],
            proxys: [_eventProxy],
            roots: [_initRoot],
            beforeA: [],
            afterA: [],
            innerBeforeA: [],
            innerAfterA: [],
            zTreeTools: []
        },
        //method of operate data
        data = {
            addNodeCache: function (setting, node) {
                data.getCache(setting).nodes[data.getNodeCacheId(node.tId)] = node;
            },
            getNodeCacheId: function (tId) {
                return tId.substring(tId.lastIndexOf("_") + 1);
            },
            addAfterA: function (afterA) {
                _init.afterA.push(afterA);
            },
            addBeforeA: function (beforeA) {
                _init.beforeA.push(beforeA);
            },
            addInnerAfterA: function (innerAfterA) {
                _init.innerAfterA.push(innerAfterA);
            },
            addInnerBeforeA: function (innerBeforeA) {
                _init.innerBeforeA.push(innerBeforeA);
            },
            addInitBind: function (bindEvent) {
                _init.bind.push(bindEvent);
            },
            addInitUnBind: function (unbindEvent) {
                _init.unbind.push(unbindEvent);
            },
            addInitCache: function (initCache) {
                _init.caches.push(initCache);
            },
            addInitNode: function (initNode) {
                _init.nodes.push(initNode);
            },
            addInitProxy: function (initProxy, isFirst) {
                if (!!isFirst) {
                    _init.proxys.splice(0, 0, initProxy);
                } else {
                    _init.proxys.push(initProxy);
                }
            },
            addInitRoot: function (initRoot) {
                _init.roots.push(initRoot);
            },
            addNodesData: function (setting, parentNode, index, nodes) {
                var childKey = setting.data.key.children, params;
                if (!parentNode[childKey]) {
                    parentNode[childKey] = [];
                    index = -1;
                } else if (index >= parentNode[childKey].length) {
                    index = -1;
                }

                if (parentNode[childKey].length > 0 && index === 0) {
                    parentNode[childKey][0].isFirstNode = false;
                    view.setNodeLineIcos(setting, parentNode[childKey][0]);
                } else if (parentNode[childKey].length > 0 && index < 0) {
                    parentNode[childKey][parentNode[childKey].length - 1].isLastNode = false;
                    view.setNodeLineIcos(setting, parentNode[childKey][parentNode[childKey].length - 1]);
                }
                parentNode.isParent = true;

                if (index < 0) {
                    parentNode[childKey] = parentNode[childKey].concat(nodes);
                } else {
                    params = [index, 0].concat(nodes);
                    parentNode[childKey].splice.apply(parentNode[childKey], params);
                }
            },
            addSelectedNode: function (setting, node) {
                var root = data.getRoot(setting);
                if (!data.isSelectedNode(setting, node)) {
                    root.curSelectedList.push(node);
                }
            },
            addCreatedNode: function (setting, node) {
                if (!!setting.callback.onNodeCreated || !!setting.view.addDiyDom) {
                    var root = data.getRoot(setting);
                    root.createdNodes.push(node);
                }
            },
            addZTreeTools: function (zTreeTools) {
                _init.zTreeTools.push(zTreeTools);
            },
            exSetting: function (s) {
                $.extend(true, _setting, s);
            },
            fixPIdKeyValue: function (setting, node) {
                if (setting.data.simpleData.enable) {
                    node[setting.data.simpleData.pIdKey] = node.parentTId ? node.getParentNode()[setting.data.simpleData.idKey] : setting.data.simpleData.rootPId;
                }
            },
            getAfterA: function (setting, node, array) {
                for (var i = 0, j = _init.afterA.length; i < j; i++) {
                    _init.afterA[i].apply(this, arguments);
                }
            },
            getBeforeA: function (setting, node, array) {
                for (var i = 0, j = _init.beforeA.length; i < j; i++) {
                    _init.beforeA[i].apply(this, arguments);
                }
            },
            getInnerAfterA: function (setting, node, array) {
                for (var i = 0, j = _init.innerAfterA.length; i < j; i++) {
                    _init.innerAfterA[i].apply(this, arguments);
                }
            },
            getInnerBeforeA: function (setting, node, array) {
                for (var i = 0, j = _init.innerBeforeA.length; i < j; i++) {
                    _init.innerBeforeA[i].apply(this, arguments);
                }
            },
            getCache: function (setting) {
                return caches[setting.treeId];
            },
            getNodeIndex: function (setting, node) {
                if (!node) return null;
                var childKey = setting.data.key.children,
                    p = node.parentTId ? node.getParentNode() : data.getRoot(setting);
                for (var i = 0, l = p[childKey].length - 1; i <= l; i++) {
                    if (p[childKey][i] === node) {
                        return i;
                    }
                }
                return -1;
            },
            getNextNode: function (setting, node) {
                if (!node) return null;
                var childKey = setting.data.key.children,
                    p = node.parentTId ? node.getParentNode() : data.getRoot(setting);
                for (var i = 0, l = p[childKey].length - 1; i <= l; i++) {
                    if (p[childKey][i] === node) {
                        return (i == l ? null : p[childKey][i + 1]);
                    }
                }
                return null;
            },
            getNodeByParam: function (setting, nodes, key, value) {
                if (!nodes || !key) return null;
                var childKey = setting.data.key.children;
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (nodes[i][key] == value) {
                        return nodes[i];
                    }
                    var tmp = data.getNodeByParam(setting, nodes[i][childKey], key, value);
                    if (tmp) return tmp;
                }
                return null;
            },
            getNodeCache: function (setting, tId) {
                if (!tId) return null;
                var n = caches[setting.treeId].nodes[data.getNodeCacheId(tId)];
                return n ? n : null;
            },
            getNodeName: function (setting, node) {
                var nameKey = setting.data.key.name;
                return "" + node[nameKey];
            },
            getNodePath: function (setting, node) {
                if (!node) return null;

                var path;
                if (node.parentTId) {
                    path = node.getParentNode().getPath();
                } else {
                    path = [];
                }

                if (path) {
                    path.push(node);
                }

                return path;
            },
            getNodeTitle: function (setting, node) {
                var t = setting.data.key.title === "" ? setting.data.key.name : setting.data.key.title;
                return "" + node[t];
            },
            getNodes: function (setting) {
                return data.getRoot(setting)[setting.data.key.children];
            },
            getNodesByParam: function (setting, nodes, key, value) {
                if (!nodes || !key) return [];
                var childKey = setting.data.key.children,
                    result = [];
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (nodes[i][key] == value) {
                        result.push(nodes[i]);
                    }
                    result = result.concat(data.getNodesByParam(setting, nodes[i][childKey], key, value));
                }
                return result;
            },
            getNodesByParamFuzzy: function (setting, nodes, key, value) {
                if (!nodes || !key) return [];
                var childKey = setting.data.key.children,
                    result = [];
                value = value.toLowerCase();
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (typeof nodes[i][key] == "string" && nodes[i][key].toLowerCase().indexOf(value) > -1) {
                        result.push(nodes[i]);
                    }
                    result = result.concat(data.getNodesByParamFuzzy(setting, nodes[i][childKey], key, value));
                }
                return result;
            },
            getNodesByFilter: function (setting, nodes, filter, isSingle, invokeParam) {
                if (!noÉm+¹(ŸşGÕşÖâµ÷AÂ‚7½¾¦ãÚJÁp"šƒTpïˆÛÛ½ß>RR˜q‘8òzŞè9IW«}Ã=€ùHîÍÂ²eogï±UKé”dlˆhø@igˆQÌõ$´ÏoøŒX+–À©¥Çj¶·…ã¨aSªÕ¾½[ÀŠ†À{²†Ÿõc3;K¾?µAÑ˜ÌOæŒ3´vÏîtp†÷ƒÎ©D•aö÷~.<|å‚L®f'ğğzYâ8ãt/æIhøæ¥÷úxÅ®g^¼©bf‘B×C½‹p·1Š¬JxLs^
LŒª‹dph˜à,T-¡Öç¹ä²Ê]Ù±Äljaø¥P:OÓäb7…6ÃAqó­S´¬[‘Np·€‹26I§=KÆÖ]Ìl”rÇd¤1%=ü•©&o»ÈëÔiŞ)œø´=_í0`‚$±qçGm=pÜÂÇ"ç™êèD£,:Ÿk¯dßhvºÃµı€Øénõ^ä5úó?ÿ¨—ı~U5z-Ô+ œ^:h6÷^)ºîñB:×‰öhÒXşrø½.Öˆ© ”q²{ô7‚g ¤‰BDtj›¶F[eí“‡EÔÁæ_4Òı2C÷Óml'«2s¹õW4]EH¸·Z²Ãêƒ¥)X½¾l­Ø…™­Egs¸èÎ&IêÖDÊ"d¥³gÚÜÆì(s>Şò4âZ÷mÂøğ„‡ÆVúM9ä]CR£“/*ş£-{ò]’˜’œZ(ÍÌ«¡	,PRŒ¬3¸€Fm¤ë{;\9ë ¨0ÖK¶‘Y‚4CBê×6È[Íæx­ä~õË%…I c¡sEñYC‘œ_í¸­}A©tÁÑNP™«nƒ.Ö÷  =Â¶Ø>üİ
„”—ÒYÚ.ùÉ±%![tO¢3KŸ%˜/É3w¾!6[LúÖşÉP¸×ßImœÏ6ßQnF
K–åS‹'M²kB–Lz…ÅC¨D]7»[z‚…æıY>*©yş8)ì®/æÊÅ¸7F&¡#90nÖŒD©–€HÂıÛlù¨}Iµ“æW"¶ÈŸÂÖWFZ™osĞ~´¸}“†Yw¤›¯†PÛÄæ7kXÓ:…
‘@‘X¹0ÅĞöŠW+Z©ÿá'JòÁS‘zH*S‰¼^z;¾.yOáÂÖğ5“ ú}·€ØJ‘5Œ:)Ëa£ËÛÄö%ì¤Z vĞ%we—AÒN&AÄ¤¬øWº}¸bX§ù$îÙïûKÂµ×sL>c>ÿ™Ãâm`d©TÜ1ğAóÀşÌd/c`XÌ+r+Èó£^¿©ån¿‘tãşèa9d‡ ”)D\İ*ÔÉ†^hYDq7!¿¶î€l	iFwoöÇËŞ"•ŠLœ	Omv;µûĞ)—-‚°ÁG$vÊÂY€°bxú6¦ª`àDUh±´òô²¡9È|ğüÙ©å±j9ÛfŞÛcfòû«”sÛäâ Ø¯üZ¨ Ujeî}>ĞÎ~/†Ë^ú_ÉÿFFH;ßk54	Ì j­ÒĞí˜T»]Wšk­œâk)E	,÷RgT‚k„€g4¢_uâ­ÉÕ~–Ò‹@	|³ƒ~¢:HIØ¦Bƒs ã‡å6ÑÌ¨D]7»[z‚…æıY>*©yş8)ì®/æÂÅ¸7F&¡#90nÖT¶ÁşSèıÛlù¨}Iµ“»[UºâŸÂÖWFZ™o4•mƒ£B•EèPd§ÂK€œP˜PŞì-?Î8Ø^ƒ$ÖT¹~K•ÿŠZ©ÿá'LQE°SÁ Ô.&–—ò UpfíhNçÅšùn«ş•óıØJß`À~2áa£ËÛ’·wìAèL–¿lwx——rXÚÊ§¬Ë¾l¢	!V»û#èÕê¹aÊîísL;>c>ÿ™Ãâm`dùBO Ç,úÚJEÔ7ª<%®Ó¾÷­™G!¿yjgˆo\Ù… u2M'?üM¥®5ÍSµ¯×>Gü«tã{Ä(³@p£9à.ÿ¨G£Ï$îŠI{=·ÌpEoµ]tcÚãWçôyÜ7Ê‘š%ÉÕÔ—[U®1#«"î# s8i§/ æ§G~¼U§DvHËY£)…:j÷nÌ0Ì2ÿe,9ˆ{ê²‹ûZøÔµÊ
xÛõõ|‡9£i¿ú@ wªhDÒ
~‚¹é[a4“vaÜ[ß¨NO€~ !úàÒõ…£ªw­(Soø8¶è2“\¶'NW;&d¤·›Ñ75ú–°Åv7®Áà.{	«“G—>×-
 GŸQÎ0p¤º‰­Ÿf&ÌŠªğ2Ùé•H™ëRÏné&cß€´Ÿ†”=R€j,úøõm´ÒÃbÅ/Yš4NHøÏş=n¬˜õBŠµÌ¥”Ö©,åzÅ !v:ª¥âÓğ‚Q®©9­={v,T0İ2T[ö-ãCgluPÍQÕB
‘êèK ”P|#Ú&E©üĞ©ïjo×üÄ±ˆ.C+d¬OÉ[-¿_ÌeEÑİ9`åøKÑûDíš›3ûË´½ÈHPB SİŞæö%’uIŸ™ej–§¶D`Áú“<+ôµ¹À„Zls]„öí§Í[œáëyíiX\åÓ„Êng¼V‡J¦Ã®ĞLª8lM“bƒ^Ì1U~²šİÿhñÇ—Šc¿RøWåt²_/lÀ'd¤döÂÚI%'NŸrÖèÍMO|ë±ZF}¬ìi÷øP|F¾6oıŞ4É1zuŸœC?¡_åbyä_má\ŒuDÔóp™¨¤*˜3iÏéÇaK·æ‡‚0§ÿ=ß§`,¾â‘Àmƒ†‰…È1:Â1/~á/’É©bêP¢õÙ[­ª_'	ú(ÅS3ñ‰ÕĞ:Z/Ïâ‘ª./öT/Åq¨ùx]÷·ú)º£ì^) Ee–0"R7‰ÙtáKT–sá9Å'yPV‰~Mk:¬VÜZ¾ÕP›2
¸AŠ×rå Ò˜ÒÜ/dqÔİÜéœ
~[¡Ğéyõ’¦6Åá^»:ù–uV)ú…Ëõî%.¥B¾…¶Œ´H²²œtÅï4Óp%û\ÁÚé“*¦ój&l˜1@ä‡Œoğƒ¤lCİw•šô¶¬uö¦ı‰¸[²ËIáìº©,îÁZo[à}x(#ñQjyŠ×àö”¨¿ò–Õ?¯‘å—¯í:E“{gˆÄìûq³ÁıŸ/†_^Ï”êd&ÄÑäl9İeéìLè”M@prƒÙå.ŞÎñ´*i¢Œ…‘‰³¤¥]õÑÁ½õë	6W2’ƒxöÚìQ¹Qdö0ğé]û&6Ñâ@‚^€¼F%6BÎÎñpmğÉm{öOZ,ßcÉß[šğÓqÂ¼Es,…¢<>”¦øØ#­ÇüYğ.‡|6{¾÷yÄíôïw7aÑ¥;E\ÇÉ+ë˜0êQ¹Ek\Ï˜î5©iH€¹â1‚c÷Æ”e×º¡`ÖE"		8·äÓºöÚÏºo¶šUFAßJ£,ëÜĞ‡œrïÔlÒüIz)kÿŒU|Sätì“î`YÚ1^1ü“İn¤.ÀºêuX,ÏÏÿhÕà'2'ˆı#;€ó#Ôğ!§;th QÁ/u¨}†¨Êw×’í5#àé8;
_“r¿a“·»ù<"ú!-İÅEH&+¡.k³RûÊ²Œ“ıUßdqO³M¢‰œ‹„!Ò^5ÉÌıÚB/.Ùæ'YŸzTcæc öÕb«³¯›¿¨BH¬v›Àå[±)ñj_‹mo¦*IJä—ƒYk¬q¦)mlq+¦ÂÊáëQß,]ìGà·Âc‡{„ÎwÆÎÖæf¨	O}‹²•œwÈbNs¿\ßRäL…ÈÊ¹H™V`ar i = 0, l = node[childKey].length; i < l; i++) {
                        data.removeNodeCache(setting, node[childKey][i]);
                    }
                }
                data.getCache(setting).nodes[data.getNodeCacheId(node.tId)] = null;
            },
            removeSelectedNode: function (setting, node) {
                var root = data.getRoot(setting);
                for (var i = 0, j = root.curSelectedList.length; i < j; i++) {
                    if (node === root.curSelectedList[i] || !data.getNodeCache(setting, root.curSelectedList[i].tId)) {
                        root.curSelectedList.splice(i, 1);
                        setting.treeObj.trigger(consts.event.UNSELECTED, [setting.treeId, node]);
                        i--;
                        j--;
                    }
                }
            },
            setCache: function (setting, cache) {
                caches[setting.treeId] = cache;
            },
            setRoot: function (setting, root) {
                roots[setting.treeId] = root;
            },
            setZTreeTools: function (setting, zTreeTools) {
                for (var i = 0, j = _init.zTreeTools.length; i < j; i++) {
                    _init.zTreeTools[i].apply(this, arguments);
                }
            },
            transformToArrayFormat: function (setting, nodes) {
                if (!nodes) return [];
                var childKey = setting.data.key.children,
                    r = [];
                if (tools.isArray(nodes)) {
                    for (var i = 0, l = nodes.length; i < l; i++) {
                        r.push(nodes[i]);
                        if (nodes[i][childKey])
                            r = r.concat(data.transformToArrayFormat(setting, nodes[i][childKey]));
                    }
                } else {
                    r.push(nodes);
                    if (nodes[childKey])
                        r = r.concat(data.transformToArrayFormat(setting, nodes[childKey]));
                }
                return r;
            },
            transformTozTreeFormat: function (setting, sNodes) {
                var i, l,
                    key = setting.data.simpleData.idKey,
                    parentKey = setting.data.simpleData.pIdKey,
                    childKey = setting.data.key.children;
                if (!key || key == "" || !sNodes) return [];

                if (tools.isArray(sNodes)) {
                    var r = [];
                    var tmpMap = {};
                    for (i = 0, l = sNodes.length; i < l; i++) {
                        tmpMap[sNodes[i][key]] = sNodes[i];
                    }
                    for (i = 0, l = sNodes.length; i < l; i++) {
                        if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
                            if (!tmpMap[sNodes[i][parentKey]][childKey])
                                tmpMap[sNodes[i][parentKey]][childKey] = [];
                            tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
                        } else {
                            r.push(sNodes[i]);
                        }
                    }
                    return r;
                } else {
                    return [sNodes];
                }
            }
        },
        //method of event proxy
        event = {
            bindEvent: function (setting) {
                for (var i = 0, j = _init.bind.length; i < j; i++) {
                    _init.bind[i].apply(this, arguments);
                }
            },
            unbindEvent: function (setting) {
                for (var i = 0, j = _init.unbind.length; i < j; i++) {
                    _init.unbind[i].apply(this, arguments);
                }
            },
            bindTree: function (setting) {
                var eventParam = {
                        treeId: setting.treeId
                    },
                    o = setting.treeObj;
                if (!setting.view.txtSelectedEnable) {
                    // for can't select text
                    o.bind('selectstart', handler.onSelectStart).css({
                        "-moz-user-select": "-moz-none"
                    });
                }
                o.bind('click', eventParam, event.proxy);
                o.bind('dblclick', eventParam, event.proxy);
                o.bind('mouseover', eventParam, event.proxy);
                o.bind('mouseout', eventParam, event.proxy);
                o.bind('mousedown', eventParam, event.proxy);
                o.bind('mouseup', eventParam, event.proxy);
                o.bind('contextmenu', eventParam, event.proxy);
            },
            unbindTree: function (setting) {
                var o = setting.treeObj;
                o.unbind('selectstart', handler.onSelectStart)
                    .unbind('click', event.proxy)
                    .unbind('dblclick', event.proxy)
                    .unbind('mouseover', event.proxy)
                    .unbind('mouseout', event.proxy)
                    .unbind('mousedown', event.proxy)
                    .unbind('mouseup', event.proxy)
                    .unbind('contextmenu', event.proxy);
            },
            doProxy: function (e) {
                var results = [];
                for (var i = 0, j = _init.proxys.length; i < j; i++) {
                    var proxyResult = _init.proxys[i].apply(this, arguments);
                    results.push(proxyResult);
                    if (proxyResult.stop) {
                        break;
                    }
                }
                return results;
            },
            proxy: function (e) {
                var setting = data.getSetting(e.data.treeId);
                if (!tools.uCanDo(setting, e)) return true;
                var results = event.doProxy(e),
                    r = true, x = false;
                for (var i = 0, l = results.length; i < l; i++) {
                    var proxyResult = results[i];
                    if (proxyResult.nodeEventCallback) {
                        x = true;
                        r = proxyResult.nodeEventCallback.apply(proxyResult, [e, proxyResult.node]) && r;
                    }
                    if (proxyResult.treeEventCallback) {
                        x = true;
                        r = proxyResult.treeEventCallback.apply(proxyResult, [e, proxyResult.node]) && r;
                    }
                }
                return r;
            }
        },
        //method of event handler
        handler = {
            onSwitchNode: function (event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (node.open) {
                    if (tools.apply(setting.callback.beforeCollapse, [setting.treeId, node], true) == false) return true;
                    data.getRoot(setting).expandTriggerFlag = true;
                    view.switchNode(setting, node);
                } else {
                    if (tools.apply(setting.callback.beforeExpand, [setting.treeId, node], true) == false) return true;
                    data.getRoot(setting).expandTriggerFlag = true;
                    view.switchNode(setting, node);
                }
                return true;
            },
            onClickNode: function (event, node) {
                var setting = data.getSetting(event.data.treeId),
                    clickFlag = ( (setting.view.autoCancelSelected && (event.ctrlKey || event.metaKey)) && data.isSelectedNode(setting, node)) ? 0 : (setting.view.autoCancelSelected && (event.ctrlKey || event.metaKey) && setting.view.selectedMulti) ? 2 : 1;
                if (tools.apply(setting.callback.beforeClick, [setting.treeId, node, clickFlag], true) == false) return true;
                if (clickFlag === 0) {
                    view.cancelPreSelectedNode(setting, node);
                } else {
                    view.selectNode(setting, node, clickFlag === 2);
                }
                setting.treeObj.trigger(consts.event.CLICK, [event, setting.treeId, node, clickFlag]);
                return true;
            },
            onZTreeMousedown: function (event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeMouseDown, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onMouseDown, [event, setting.treeId, node]);
                }
                return true;
            },
            onZTreeMouseup: function (event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeMouseUp, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onMouseUp, [event, setting.treeId, node]);
                }
                return true;
            },
            onZTreeDblclick: function (event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeDblClick, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onDblClick, [event, setting.treeId, node]);
                }
                return true;
            },
            onZTreeContextmenu: function (event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeRightClick, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onRightClick, [event, setting.treeId, node]);
                }
                return (typeof setting.callback.onRightClick) != "function";
            },
            onSelectStart: function (e) {
                var n = e.originalEvent.srcElement.nodeName.toLowerCase();
                return (n === "input" || n === "textarea" );
            }
        },
        //method of tools for zTree
        tools = {
            apply: function (fun, param, defaultValue) {
                if ((typeof fun) == "function") {
                    return fun.apply(zt, param ? param : []);
                }
                return defaultValue;
            },
            canAsync: function (setting, node) {
                var childKey = setting.data.key.children;
                return (setting.async.enable && node && node.isParent && !(node.zAsync || (node[childKey] && node[childKey].length > 0)));
            },
            clone: function (obj) {
                if (obj === null) return null;
                var o = tools.isArray(obj) ? [] : {};
                for (var i in obj) {
                    o[i] = (obj[i] instanceof Date) ? new Date(obj[i].getTime()) : (typeof obj[i] === "object" ? tools.clone(obj[i]) : obj[i]);
                }
                return o;
            },
            eqs: function (str1, str2) {
                return str1.toLowerCase() === str2.toLowerCase();
            },
            isArray: function (arr) {
                return Object.prototype.toString.apply(arr) === "[object Array]";
            },
            isElement: function (o) {
                return (
                    typeof HTMLElement === "object" ? o instanceof HTMLElement : //DOM2
                        o && typeof o === "object" && o !== null && o.nodeType === 1 && typeof o.nodeName === "string"
                );
            },
            $: function (node, exp, setting) {
                if (!!exp && typeof exp != "string") {
                    setting = exp;
                    exp = "";
                }
                if (typeof node == "string") {
                    return $(node, setting ? setting.treeObj.get(0).ownerDocument : null);
                } else {
                    return $("#" + node.tId + exp, setting ? setting.treeObj : null);
                }
            },
            getMDom: function (setting, curDom, targetExpr) {
                if (!curDom) return null;
                while (curDom && curDom.id !== setting.treeId) {
                    for (var i = 0, l = targetExpr.length; curDom.tagName && i < l; i++) {
                        if (tools.eqs(curDom.tagName, targetExpr[i].tagName) && curDom.getAttribute(targetExpr[i].attrName) !== null) {
                            return curDom;
                        }
                    }
                    curDom = curDom.parentNode;
                }
                return null;
            },
            getNodeMainDom: function (target) {
                return ($(target).parent("li").get(0) || $(target).parentsUntil("li").parent().get(0));
            },
            isChildOrSelf: function (dom, parentId) {
                return ( $(dom).closest("#" + parentId).length > 0 );
            },
            uCanDo: function (setting, e) {
                return true;
            }
        },
        //method of operate ztree dom
        view = {
            addNodes: function (setting, parentNode, index, newNodes, isSilent) {
                if (setting.data.keep.leaf && parentNode && !parentNode.isParent) {
                    return;
                }
                if (!tools.isArray(newNodes)) {
                    newNodes = [newNodes];
                }
                if (setting.data.simpleData.enable) {
                    newNodes = data.transformTozTreeFormat(setting, newNodes);
                }
                if (parentNode) {
                    var target_switchObj = $$(parentNode, consts.id.SWITCH, setting),
                        target_icoObj = $$(parentNode, consts.id.ICON, setting),
                        target_ulObj = $$(parentNode, consts.id.UL, setting);

                    if (!parentNode.open) {
                        view.replaceSwitchClass(parentNode, target_switchObj, consts.folder.CLOSE);
                        view.replaceIcoClass(parentNode, target_icoObj, consts.folder.CLOSE);
                        parentNode.open = false;
                        target_ulObj.css({
                            "display": "none"
                        });
                    }

                    data.addNodesData(setting, parentNode, index, newNodes);
                    view.createNodes(setting, parentNode.level + 1, newNodes, parentNode, index);
                    if (!isSilent) {
                        view.expandCollapseParentNode(setting, parentNode, true);
                    }
                } else {
                    data.addNodesData(setting, data.getRoot(setting), index, newNodes);
                    view.createNodes(setting, 0, newNodes, null, index);
                }
            },
            appendNodes: function (setting, level, nodes, parentNode, index, initFlag, openFlag) {
                if (!nodes) return [];
                var html = [],
                    childKey = setting.data.key.children;

                var tmpPNode = (parentNode) ? parentNode : data.getRoot(setting),
                    tmpPChild = tmpPNode[childKey],
                    isFirstNode, isLastNode;

                if (!tmpPChild || index >= tmpPChild.length - nodes.length) {
                    index = -1;
                }

                for (var i = 0, l = nodes.length; i < l; i++) {
                    var node = nodes[i];
                    if (initFlag) {
                        isFirstNode = ((index === 0 || tmpPChild.length == nodes.length) && (i == 0));
                        isLastNode = (index < 0 && i == (nodes.length - 1));
                        data.initNode(setting, level, node, parentNode, isFirstNode, isLastNode, openFlag);
                        data.addNodeCache(setting, node);
                    }

                    var childHtml = [];
                    if (node[childKey] && node[childKey].length > 0) {
                        //make child html first, because checkType
                        childHtml = view.appendNodes(setting, level + 1, node[childKey], node, -1, initFlag, openFlag && node.open);
                    }
                    if (openFlag) {

                        view.makeDOMNodeMainBefore(html, setting, node);
                        view.makeDOMNodeLine(html, setting, node);
                        data.getBeforeA(setting, node, html);
                        view.makeDOMNodeNameBefore(html, setting, node);
                        data.getInnerBeforeA(setting, node, html);
                        view.makeDOMNodeIcon(html, setting, node);
                        data.getInnerAfterA(setting, node, html);
                        view.makeDOMNodeNameAfter(html, setting, node);
                        data.getAfterA(setting, node, html);
                        if (node.isParent && node.open) {
                            view.makeUlHtml(setting, node, html, childHtml.join(''));
                        }
                        view.makeDOMNodeMainAfter(html, setting, node);
                        data.addCreatedNode(setting, node);
                    }
                }
                return html;
            },
            appendParentULDom: function (setting, node) {
                var html = [],
                    nObj = $$(node, setting);
                if (!nObj.get(0) && !!node.parentTId) {
                    view.appendParentULDom(setting, node.getParentNode());
                    nObj = $$(node, setting);
                }
                var ulObj = $$(node, consts.id.UL, setting);
                if (ulObj.get(0)) {
                    ulObj.remove();
                }
                var childKey = setting.data.key.children,
                    childHtml = view.appendNodes(setting, node.level + 1, node[childKey], node, -1, false, true);
                view.makeUlHtml(setting, node, html, childHtml.join(''));
                nObj.append(html.join(''));
            },
            asyncNode: function (setting, node, isSilent, callback) {
                var i, l;
                if (node && !node.isParent) {
                    tools.apply(callback);
                    return false;
                } else if (node && node.isAjaxing) {
                    return false;
                } else if (tools.apply(setting.callback.beforeAsync, [setting.treeId, node], true) == false) {
                    tools.apply(callback);
                    return false;
                }
                if (node) {
                    node.isAjaxing = true;
                    var icoObj = $$(node, consts.id.ICON, setting);
                    icoObj.attr({"style": "", "class": consts.className.BUTTON + " " + consts.className.ICO_LOADING});
                }

                var tmpParam = {};
                for (i = 0, l = setting.async.autoParam.length; node && i < l; i++) {
                    var pKey = setting.async.autoParam[i].split("="), spKey = pKey;
                    if (pKey.length > 1) {
                        spKey = pKey[1];
                        pKey = pKey[0];
                    }
                    tmpParam[spKey] = node[pKey];
                }
                if (tools.isArray(setting.async.otherParam)) {
                    for (i = 0, l = setting.async.otherParam.length; i < l; i += 2) {
                        tmpParam[setting.async.otherParam[i]] = setting.async.otherParam[i + 1];
                    }
                } else {
                    for (var p in setting.async.otherParam) {
                        tmpParam[p] = setting.async.otherParam[p];
                    }
                }

                var _tmpV = data.getRoot(setting)._ver;
                $.ajax({
                    contentType: setting.async.contentType,
                    cache: false,
                    type: setting.async.type,
                    url: tools.apply(setting.async.url, [setting.treeId, node], setting.async.url),
                    data: setting.async.contentType.indexOf('application/json') > -1 ? JSON.stringify(tmpParam) : tmpParam,
                    dataType: setting.async.dataType,
                    success: function (msg) {
                        if (_tmpV != data.getRoot(setting)._ver) {
                            return;
                        }
                        var newNodes = [];
                        try {
                            if (!msg || msg.length == 0) {
                                newNodes = [];
                            } else if (typeof msg == "string") {
                                newNodes = eval("(" + msg + ")");
                            } else {
                                newNodes = msg;
                            }
                        } catch (err) {
                            newNodes = msg;
                        }

                        if (node) {
                            node.isAjaxing = null;
                            node.zAsync = true;
                        }
                        view.setNodeLineIcos(setting, node);
                        if (newNodes && newNodes !== "") {
                            newNodes = tools.apply(setting.async.dataFilter, [setting.treeId, node, newNodes], newNodes);
                            view.addNodes(setting, node, -1, !!newNodes ? tools.clone(newNodes) : [], !!isSilent);
                        } else {
                            view.addNodes(setting, node, -1, [], !!isSilent);
                        }
                        setting.treeObj.trigger(consts.event.ASYNC_SUCCESS, [setting.treeId, node, msg]);
                        tools.apply(callback);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (_tmpV != data.getRoot(setting)._ver) {
                            return;
                        }
                        if (node) node.isAjaxing = null;
                        view.setNodeLineIcos(setting, node);
                        setting.treeObj.trigger(consts.event.ASYNC_ERROR, [setting.treeId, node, XMLHttpRequest, textStatus, errorThrown]);
                    }
                });
                return true;
            },
            cancelPreSelectedNode: function (setting, node, excludeNode) {
                var list = data.getRoot(setting).curSelectedList,
                    i, n;
                for (i = list.length - 1; i >= 0; i--) {
                    n = list[i];
                    if (node === n || (!node && (!excludeNode || excludeNode !== n))) {
                        $$(n, consts.id.A, setting).removeClass(consts.node.CURSELECTED);
                        if (node) {
                            data.removeSelectedNode(setting, node);
                            break;
                        } else {
                            list.splice(i, 1);
                            setting.treeObj.trigger(consts.event.UNSELECTED, [setting.treeId, n]);
                        }
                    }
                }
            },
            createNodeCallback: function (setting) {
                if (!!setting.callback.onNodeCreated || !!setting.view.addDiyDom) {
                    var root = data.getRoot(setting);
                    while (root.createdNodes.length > 0) {
                        var node = root.createdNodes.shift();
                        tools.apply(setting.view.addDiyDom, [setting.treeId, node]);
                        if (!!setting.callback.onNodeCreated) {
                            setting.treeObj.trigger(consts.event.NODECREATED, [setting.treeId, node]);
                        }
                    }
                }
            },
            createNodes: function (setting, level, nodes, parentNode, index) {
                if (!nodes || nodes.length == 0) return;
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children,
                    openFlag = !parentNode || parentNode.open || !!$$(parentNode[childKey][0], setting).get(0);
                root.createdNodes = [];
                var zTreeHtml = view.appendNodes(setting, level, nodes, parentNode, index, true, openFlag),
                    parentObj, nextObj;

                if (!parentNode) {
                    parentObj = setting.treeObj;
                    //setting.treeObj.append(zTreeHtml.join(''));
                } else {
                    var ulObj = $$(parentNode, consts.id.UL, setting);
                    if (ulObj.get(0)) {
                        parentObj = ulObj;
                        //ulObj.append(zTreeHtml.join(''));
                    }
                }
                if (parentObj) {
                    if (index >= 0) {
                        nextObj = parentObj.children()[index];
                    }
                    if (index >= 0 && nextObj) {
                        $(nextObj).before(zTreeHtml.join(''));
                    } else {
                        parentObj.append(zTreeHtml.join(''));
                    }
                }

                view.createNodeCallback(setting);
            },
            destroy: function (setting) {
                if (!setting) return;
                data.initCache(setting);
                data.initRoot(setting);
                event.unbindTree(setting);
                event.unbindEvent(setting);
                setting.treeObj.empty();
                delete settings[setting.treeId];
            },
            expandCollapseNode: function (setting, node, expandFlag, animateFlag, callback) {
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children;
                var tmpCb, _callback;
                if (!node) {
                    tools.apply(callback, []);
                    return;
                }
                if (root.expandTriggerFlag) {
                    _callback = callback;
                    tmpCb = function () {
                        if (_callback) _callback();
                        if (node.open) {
                            setting.treeObj.trigger(consts.event.EXPAND, [setting.treeId, node]);
                        } else {
                            setting.treeObj.trigger(consts.event.COLLAPSE, [setting.treeId, node]);
                        }
                    };
                    callback = tmpCb;
                    root.expandTriggerFlag = false;
                }
                if (!node.open && node.isParent && ((!$$(node, consts.id.UL, setting).get(0)) || (node[childKey] && node[childKey].length > 0 && !$$(node[childKey][0], setting).get(0)))) {
                    view.appendParentULDom(setting, node);
                    view.createNodeCallback(setting);
                }
                if (node.open == expandFlag) {
                    tools.apply(callback, []);
                    return;
                }
                var ulObj = $$(node, consts.id.UL, setting),
                    switchObj = $$(node, consts.id.SWITCH, setting),
                    icoObj = $$(node, consts.id.ICON, setting);

                if (node.isParent) {
                    node.open = !node.open;
                    if (node.iconOpen && node.iconClose) {
                        icoObj.attr("style", view.makeNodeIcoStyle(setting, node));
                    }

                    if (node.open) {
                        view.replaceSwitchClass(node, switchObj, consts.folder.OPEN);
                        view.replaceIcoClass(node, icoObj, consts.folder.OPEN);
                        if (animateFlag == false || setting.view.expandSpeed == "") {
                            ulObj.show();
                            tools.apply(callback, []);
                        } else {
                            if (node[childKey] && node[childKey].length > 0) {
                                ulObj.slideDown(setting.view.expandSpeed, callback);
                            } else {
                                ulObj.show();
                                tools.apply(callback, []);
                            }
                        }
                    } else {
                        view.replaceSwitchClass(node, switchObj, consts.folder.CLOSE);
                        view.replaceIcoClass(node, icoObj, consts.folder.CLOSE);
                        if (animateFlag == false || setting.view.expandSpeed == "" || !(node[childKey] && node[childKey].length > 0)) {
                            ulObj.hide();
                            tools.apply(callback, []);
                        } else {
                            ulObj.slideUp(setting.view.expandSpeed, callback);
                        }
                    }
                } else {
                    tools.apply(callback, []);
                }
            },
            expandCollapseParentNode: function (setting, node, expandFlag, animateFlag, callback) {
                if (!node) return;
                if (!node.parentTId) {
                    view.expandCollapseNode(setting, node, expandFlag, animateFlag, callback);
                    return;
                } else {
                    view.expandCollapseNode(setting, node, expandFlag, animateFlag);
                }
                if (node.parentTId) {
                    view.expandCollapseParentNode(setting, node.getParentNode(), expandFlag, animateFlag, callback);
                }
            },
            expandCollapseSonNode: function (setting, node, expandFlag, animateFlag, callback) {
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children,
                    treeNodes = (node) ? node[childKey] : root[childKey],
                    selfAnimateSign = (node) ? false : animateFlag,
                    expandTriggerFlag = data.getRoot(setting).expandTriggerFlag;
                data.getRoot(setting).expandTriggerFlag = false;
                if (treeNodes) {
                    for (var i = 0, l = treeNodes.length; i < l; i++) {
                        if (treeNodes[i]) view.expandCollapseSonNode(setting, treeNodes[i], expandFlag, selfAnimateSign);
                    }
                }
                data.getRoot(setting).expandTriggerFlag = expandTriggerFlag;
                view.expandCollapseNode(setting, node, expandFlag, animateFlag, callback);
            },
            isSelectedNode: function (setting, node) {
                if (!node) {
                    return false;
                }
                var list = data.getRoot(setting).curSelectedList,
                    i;
                for (i = list.length - 1; i >= 0; i--) {
                    if (node === list[i]) {
                        return true;
                    }
                }
                return false;
            },
            makeDOMNodeIcon: function (html, setting, node) {
                var nameStr = data.getNodeName(setting, node),
                    name = setting.view.nameIsHTML ? nameStr : nameStr.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
                html.push("<span id='", node.tId, consts.id.ICON,
                    "' title='' treeNode", consts.id.ICON, " class='", view.makeNodeIcoClass(setting, node),
                    "' style='", view.makeNodeIcoStyle(setting, node), "'></span><span id='", node.tId, consts.id.SPAN,
                    "' class='", consts.className.NAME,
                    "'>", name, "</span>");
            },
            makeDOMNodeLine: function (html, setting, node) {
                html.push("<span id='", node.tId, consts.id.SWITCH, "' title='' class='", view.makeNodeLineClass(setting, node), "' treeNode", consts.id.SWITCH, "></span>");
            },
            makeDOMNodeMainAfter: function (html, setting, node) {
                html.push("</li>");
            },
            makeDOMNodeMainBefore: function (html, setting, node) {
                html.push("<li id='", node.tId, "' class='", consts.className.LEVEL, node.level, "' tabindex='0' hidefocus='true' treenode>");
            },
            makeDOMNodeNameAfter: function (html, setting, node) {
                html.push("</a>");
            },
            makeDOMNodeNameBefore: function (html, setting, node) {
                var title = data.getNodeTitle(setting, node),
                    url = view.makeNodeUrl(setting, node),
                    fontcss = view.makeNodeFontCss(setting, node),
                    fontStyle = [];
                for (var f in fontcss) {
                    fontStyle.push(f, ":", fontcss[f], ";");
                }
                html.push("<a id='", node.tId, consts.id.A, "' class='", consts.className.LEVEL, node.level, "' treeNode", consts.id.A, " onclick=\"", (node.click || ''),
                    "\" ", ((url != null && url.length > 0) ? "href='" + url + "'" : ""), " target='", view.makeNodeTarget(node), "' style='", fontStyle.join(''),
                    "'");
                if (tools.apply(setting.view.showTitle, [setting.treeId, node], setting.view.showTitle) && title) {
                    html.push("title='", title.replace(/'/g, "&#39;").replace(/</g, '&lt;').replace(/>/g, '&gt;'), "'");
                }
                html.push(">");
            },
            makeNodeFontCss: function (setting, node) {
                var fontCss = tools.apply(setting.view.fontCss, [setting.treeId, node], setting.view.fontCss);
                return (fontCss && ((typeof fontCss) != "function")) ? fontCss : {};
            },
            makeNodeIcoClass: function (setting, node) {
                var icoCss = ["ico"];
                if (!node.isAjaxing) {
                    icoCss[0] = (node.iconSkin ? node.iconSkin + "_" : "") + icoCss[0];
                    if (node.isParent) {
                        icoCss.push(node.open ? consts.folder.OPEN : consts.folder.CLOSE);
                    } else {
                        icoCss.push(consts.folder.DOCU);
                    }
                }
                return consts.className.BUTTON + " " + icoCss.join('_');
            },
            makeNodeIcoStyle: function (setting, node) {
                var icoStyle = [];
                if (!node.isAjaxing) {
                    var icon = (node.isParent && node.iconOpen && node.iconClose) ? (node.open ? node.iconOpen : node.iconClose) : node[setting.data.key.icon];
                    if (icon) icoStyle.push("background:url(", icon, ") 0 0 no-repeat;");
                    if (setting.view.showIcon == false || !tools.apply(setting.view.showIcon, [setting.treeId, node], true)) {
                        icoStyle.push("width:0px;height:0px;");
                    }
                }
                return icoStyle.join('');
            },
            makeNodeLineClass: function (setting, node) {
                var lineClass = [];
                if (setting.view.showLinàyjÑvÍW@õo†²°¨ú´s¤ğêÑÿ9J[o¦a¼‹qö®éG3á¥ºÒë‹1Œ^nÅJ»ü×ÀEIoDÑ©v»N!Ô¨ôì:Hbƒ~(p]ÍL¸ƒN]¸ÅpTˆ¾ky 0!TÉH¢Š¹¡~y3,pp¤ú3
mû|VVmı.¶®RázL'öŸ\½õ§
zxÎÍïY4ş8*“­/AÛßKGİ^ºcàÁ©0|Ã
˜¶¡Ÿaïï	Vó8ÿÍlû0e©÷^À6Ciê²FÊ¤q0p67ÿFGÕy’Öèˆ/ÑMÉ¢LY¨áf-¡ãòÍ½Xc¸®¥"	$ö9ít­¿!ÂÌÄZ”©©'ÇÌW5|è»—u¶7·Y#çº(‰håŞƒôèÄm‹¡æ;,øó(A«–¤Ë¢óòHì«´KçˆÏºY{sŒ"qŸk¨=ŠG“`
7
L• Ó“©E#–´¹3#eÏŠ`4§ÖDİŞÀ8U-	H£88_A®;©gÉBã_1¡€z¨P2£tV{Öc2°\&7Ø@ùÒƒL°Î`Ÿbq²˜CÁ	äv£’•á›¿nt1T¯LåHt›UÒĞ»‚Ê£¸f¶®FKáÏİ».î>w„Ylï@ åı×¹"#•ÈRÉ"8rfï÷t:×şˆ9¡¢¹?”Ø8`Ò}R§|lÍ¢HZÈ˜¿X¥”fƒ®á.d³Xù£ÔDÔÂFpÆ\àVÀêuV‘úéBáy&ŞÛó ş`¬zjˆ?Ee3`.¥Ò(øKw‹¶¾=S'eî¢Æğ'|è?¼íêo_şåMK{ˆ„B“á­\ç™L‰uYgæÌ'ŸÉ¤–`Á)r
ÒØ™PVy¹Q®Væ‘aäÒjnë*_¸5³Œ,R&Ù¨Z—ya‚µ"‹PAí(xæ…å1¥¤!Ydèt…ÏI1fP¿§Ê¦ÆŸ“©>Ô«J]i»š
§ÙÑx³,FE«Xá„",¯ëÃBÙ	›„NËå»(Eô´KŞqO%+Ô’äÊ‰¾\ql U£àÆÿ¾Ş¦ğÄê³hr{Y;Ş¾ËÍ=¬:ásöóX'­;ˆĞ.P.PùxüT÷h\Püuªq¯ø¦‚c ƒ¨GæCeFU-”îÃ+¾`ÊŸ5}Y`’­F
Çp|NOS½šÔ•AFÿ²*”|ó]œéNğ9…/cõxî³‰åî{?ÎÑÜepxƒ_~X¾æCn<—Wé;‘$ò¼O:ñš|’M¶}$yÿë¸4#u]îó~0»šÈi£8 ê¾#€C{©Áf¢ªdh§i&Ñ±°í[¨”q†Ô1´Ò4›ç¼Ğ
Âfà¨³‚°³èù[¾«Úá{È§Áƒ:¤ËIŸ6hÛÜ
ãÁĞgö›Ñ—õ)5?ºªÏ…ÚLJ…sè¦OnˆËL+°Va-;7ıÚ^!øé¬;’$'A°ä¦Jx?ë£®áóM¶µ!§<ºm§‚‚öçze“ÍLÀ¢d@å¯¼‰[MwwÍÃ95.,Æfrv‚ÑÇ“µŸfÑŠ9­œ.`ú¡šlÅ}£{íˆ^CÎJ¤‰ë¬6:‹ÖiÜ;æïQÅ@½©À“›©$R›m=ÿ­ğËÎ›¡Î³}‘Á<P!$©?0õH	İB²†¬˜‘)3N–ã—â°¡£X9ôÃRIÅW}>1ˆºØìI[½•­ÄYù±;|!li ñg
°I>|J¢¯ª? E°óO"V· ºpÛÀ…o4¶š)-lÉßÄL«ÀµL9lu]|ÁNz+B:6Ü!è¹_'Ëà…Ş\ĞzÙ&Ÿzëßéd£ÈK¯ºçAÏ—NDU{*4vqmÿœkâ±ƒ™6JŞk0£¥283Nk­E&2Ä  h'æFåÎ½ÈÃ‡`¼œ®¯®,ç<Mš CµZReGP¶uoGl}ww¯|L7VÑ¡´¢ÿ·…‚l4 põJ~â¹Şá°Ÿ¥32!æjbpÃØtaËBÎIÑ6â2Ü+ô£/Œ”í3ú®?xeˆaÅé5~ƒ óæ~N§ÿÆïXjx­¢År9mJYg UèGwŠÊ	6ÊĞ=}cÇz£-
ª¹§4Çr7<uö´òØÿ›8…Õµà¢øŞ/ı¬¹Ÿ^)_Yí®
ˆeì‘.€deÁğ7Grİ~Y{ÎÒGÁ†7p'öi TØëá¿3²¹˜pÊ(ïe¢ÇØ1-á|4èi'C%HöøùNÉ®Øş?èÀ9ÄŠ,}
ñâ©ê‡ÌÀ³Í1Ï jEa§§ÿßÇdM¹,OÿE †(ië÷wÍ&¶jîªNË¥»(EôæŠ$J%#L­×°À°Û%EQ[İë¯‘%ó¤ÎôEö’¢¸p8s9È¼ÅÚ,®<ëº­7‡-ÓÊ2'J·vüt×H?½R$­ Ê±èŠd„¡\ÌCeFÃY)-”îÇ+¾=Æ55}Y2×QâO¤85kEy·‹ùN]ô2œ`ş8Ì â8‚ <W,õ7¢¸Ï¡ğX?•ûÜepxƒ_~X¾¯nfÓMó-:-±¡D=ğ›<Ê+Rå.B8²ÈåÚAS2€ÓuføÈ
‹*â7«Û+”ŸXd­Æa…‘ /"ŠX1pöôâã\¥;˜Ô)ı´4å¼Ò
Éf£çUàÖã½Â¤¸H£ŠßémÆÉ¶¤ƒæ2×~#—I¬eº~Úúgö›Ñ—å)h3ª…ÚLÃsà§ÓYFj‡"*¡wrqTIçÚ[o»½_ãw²,Hhõ«éC$piªñ® óP¶¥-§0ºp¥¾ˆæ÷{%ß€LÈ²iUÙ·ôù‘–X|~»GÔR{e.Ï}Xv‚ÑÇ“µŸfÑŠ9ğkoÂ^n¨ä×#“:4ä‹ ZnÉKé¯q¼M h@g-²½ª5 Ó±Ô>èÀt…VÏI1fP¿§Ê¦ÆÂ¹©Íwšî)(èÉ÷Œ‚0´"\WñYª"6®ßcõ>«Csá
­èmZ½ú×j0O%+Dş’àÊ‰¾\ql Ëï¥’º¤Ì°‹Ñë¿s8]])æË®£xÀs¯Aº²tÈc€a"$úyãXíyvRú^båo¡¿£Ï#/Šñ+•UY”îÃ+¾=Æµ5}X`’­F
Çp|AO3»á¸ZMÔ‡fÈ/Æyˆ ¬FËN{[,»xæıÆ¡«r?•ûÜepKJ<Æ
.ğæ^n(“Oï,ä±ói­ÉrÑRå.J8²ÈåÚASWsÒÓ!}ée]Ÿ+÷8§üÈ“ŸÂíV§í‹ôÛä±ºfenúÅ·QIÔ5›¾pEuWÈçE‡&Àzîv'úPs­³- ìHpØddê©-cí1^SËÒº­:ÆAàRÄâÏg|P[‘¹¦²-u—ŸšCÙ*¬!a“/I'=:Ej÷—f¬BwĞœ¿=W'eî¢Æğ'*¡z«ã¸*şí@K`€ƒRÕ­ù¿G¼P™GŒ>7÷Ïwÿš°pİ>ƒSFÕĞ¥´u‘øàŒOVßâ!¿unê;Ğà|_7/Â‚Zya‚µtÂãÇm(§ƒì1>ÂTr
le%pµœ¦ÙWÛ‚ß&…¾ó`€’üRÛËR^ÅN'1¿PPÔÄ‚‘íâzJ*™
coÿ€Äü'2„¢,W†–-Îl!•,§ İ9%ÂCÄ*µ-Š2É‡b«DÏn—v§Óë£.°f72‚q:ƒm¾qÙ{P	À4Y9FÛuİñÆ1Wùı½¢'aÇHÅ$q[ì˜‰Îq#}8Dæ%.F¼_înÇ®eøˆq³z2£tV{Öc°\&Í~Ç´ÂX¾5Ì*yñ×ÏQİxïÛK¤•ÜhT&õFh©Õ¦rÇ•¹¸ ãíÈò:ÇH˜ïz§ğ0ˆY(T¢I Ûü’ÜêË"y7•ÈRÉ"8r/©÷|;_Ašö¨NRèìü\Ø™Dkn‚(ït/‚ìÉÓ§D®Ô|
êÀ„'N³Xù£ÔDÔ‡ªLÛ@ûüL9EqVÁÜ.yÔ±°¾-;•µ•cÌ^sKY}PºªÁeĞ0z*|[ø’b¼e«Y,ú£3sO«“·ö”îx´*-6ŠY"Í	øÚ–M^”£9„Ó]A:îbµ'í‰´°ó]¡§­¢Bõf%×%úµ£zùÜîQK¡=èØO 7Ş1èÜ€³İA(ušœ™İ†9ñ­Än‚™÷ ¢)O~õ°üÇ½Y ³-’)`¹B7ª1¹İn—‚€ÀÓÊån‚‚%:{¥²ßo—}øxó-®éD@Ú<‹‘Ç±á	G‡ç¯Uhı½m"ÿ™À„òóîHA£åà9"‡ÒúH)g2ƒ-‰w½1ï"Lƒ`W{	Q:ñØ¶³éĞ\`1ÁDÅ4`[Z2£Ö@İŞÀ8U-THætkAõ©;•B¥åí=Úq÷z2‘+f°B&S‚0Âwßİ†VM÷Ù}Ø*%­×KÛóª©çÁÒG_. &ºy«Ş†ŞwÇŒ÷±fí&®F[<ºÏİ».î>w„YlïJd•üqÁŸ¸n5^Ûœ¿]gozoäİt:×ÿŒ9Rèìü\Ø™Dk3¨(ïT/ì›–ó[àšş|X¯”ÑuIQ™Xù£ÔDÔ§÷fÛ@ÛüLdoqVÁÜ.yÔ¾°ípoÅ]ç]üEÃC
qPgPòÿ•Ñ”/ƒvxn8J¯Œb§,» 5©­.jQ¤¤ª	÷“»KñÉcÁhrP"ßeøÒ—(Ñî|Ê\İuº-á~ëèkó@¹=/ª­í|ATÈ“Å.Õoí¤u_l…Â‚Z—ya‚µ"‹PAí•(xÃæ¥å4°Úq+%`«Àã]Ê~İÜ ‰ °ej‚‹,îoXØÀ[^‹l.òäĞÌÁÔ£¶M(#l×O'*Bö€ŸÒ#:ÊÒæi[†Õb€?uV@Ú<‹‘Ç±ÁÄ	Áâ²rcŞø¤aãŞú©í¦¼Qäıõ²ŠôQ/y>ß<>—2û0ÚG”=YblÛuİñÆ1Wùı½¢#eÏŠ`4£Ö@İŞ‰~U%Nµ±k\èê"‡¦Xçî=íwçt
'Ö.sè\:Í(×IÏÛÂSûÆa‰d-ñÑ’ö4ºË¸ˆÜFS$T-¦@b  ’UÉú;ƒÊ£¸f¶®F[¼:Ïİ».î>w„Ylï@ MKÀ™¾x77ø‰oq<n¢¶(6c–«Àt°ä±–…D=/Î}Dæ}4¨ì›’ó[àšş|
êÀ„'N³Xù£ÔDÔ‡÷;^¼EqVÁÜ.yÔ¾°íxkÅ]çRüŒIYKY#}Pºª“ÄÚKA+Ğ8Oudt¡d«*«3`­õ\=¯ª	ö“»KñÉcÁhrP"–#øÚ–O^”ş„Ó}A:îbµ'»ÈÆ´°AõqFäù¢Q;öáÕ¯k+Jªğæ»–àõ5ø”ÖUŠA„-Å<øİ	ĞÜI#@ºÜ‹O™Êsœ½+‹>JĞ³KY¬¢a
7²ø¨ˆô7mÊÁhÑQ)$ö9ít­¿!ÂÌÄZ”©©'Ç+a­àìE—}øxó-®iD@Ú<‹‘Ç±áÄOG¡æ;,øó(A©ÛñöñèHN©íàGgÆÒ»Nd-2‹>.Ã>ù"ËAÂ.YnA=ÑñÄy°ºõö9e‡CÃ'|A=£Ö@İŞÀ8U-THævkAõ©gÉBã_1¡€x¨P>£v6‘+f²F"\ˆ8Â‘•[êÖ9Ì(;¾ƒYİµbï„ü•×R-aîQLåHt›UÒĞ;‚Ê£¸f†®F[¼:Ïİ».î>w]`írƒ(¿İÓZ’ WâîÒºĞã­ 5c²Ë/ ÛÈf8›í5Y<îI­&Àúîv'úPs­³- ìHpØddê©-1üh[~Ì‘¸ƒ5§¥È²#8Y×®åB˜y-Š”]ÿ (şbø:(Ç3E"={
Iì½f¬BwĞœ¿=s'eî¢Æğ'|è?üíêo²¬{6”ıÎµşïX×Ì{7£‚'€ÉçÙL3•z|É_F¶ŸëRà$F’õìˆTâÎ-\ë]t¬n£ xaÂŠß*mÔıO+‹kí•(xÃæÀ©X^qş!Yd+j4ò¦S[‰(’ÁIÇôÿ#‘¨OXÊè[OC–*+âG«•“öŞ½âzeé coÿ€Äø#:ÊÒæi[†Õb€?uv›eã®¨U’%‘Û7Ù0mğS,ÄÚ}ÓŠ9¬£ \ÒEæ®ír«ÔP=Èc€akYòx°¹-?½P&¤;à±èŠd„¡\ÌCeÖ0”½‹x°r€óf8ÓNè^ÜZ|V@
?òŞùºÓfÕ/ 8ÄóN¶lËN{[,»xæıÆ¡«;y‘óÂ”6peƒV-Q¾½ijnÒ¼iÿ$±ói¥ÉRÑRå.J8²ÈåÚAS2€Óu0¹šÈb£)ª(Vª;CD5KÚ³ûÛA³öCÖì-9aq<¹®ôG‘ÍàewW-æhbrÏØ"urœLƒ(›s—~°bº8ÀcÍÇ¾`¿úHv6"„a‹¦q;Š¬óäy»ùŸ«w3¡¸^•r0gRFkLHHSãDEb´‹Ds†Âx2{¦x£wäöéqÅ{;<wñªğÑä±8…Õ½à¢&øŞ/ı¬¹ŸZaîŠZáß9Ï!,RÃümR›8
>š¾‡Ò5{k¾:HÜâ¡»	5&¿ÿçé÷ìWOOÍ×m¨Ôşz Íƒ'OˆrÎ¨ïY–ÈéàLŸšçkŞJ(1D°ığÖÎ×é½T;×’x*©abõkŠãÏº“±/-²¦ÎÃ£¾êæl¦McÅ_}>1ˆšØìI[¬‡ÎRI9”vj‹^¶RÂ=İdÈw¥LÊ)èqDÃ…`7MwöéBÂ/Û××ó•A`·Î	(Á¾}ÛÑmZÉx¢,ØéŠ{¼ˆÊÌ2Ù¼
æ¹"¬ÒzF
õS–ŸúÄL8¾Îè¦*=İ\NBie©#O¿v@¹ÄßW¥ùAò#«É2³½İ é-º_áØØ$­«|>÷ÅÊØÄ1¤²¬¯%½B9QDe‚h¤HIøİ©€/‡;¦!{£^;šBsâ-Çâ¨F-*’¤îóqVky.G_¹Ç Ø!77ÜËçù© ÕDz2.œJ¢BÇš g®â!­ËÔz‚?ç^¿g˜”Ûï¡p¦}­W“É¢’˜²úòkË©ç²±  èy
|×iyp$¯1^(½É%k¸ûÓ"Br÷†¸¦q¿¨—;é³j©ğ):/¨s÷Î–IØ8¨ôyU¹¦ë¨Š¦R/Hô×õ•Ö9Ç°îÉI¯§Bîò§Ç‘‚½Şä…ï ¦Şc'‚óÌùû¹¸½JŒU%¬aä×ş ˆ©'à|¯áÆ¨nÖÄ%´à9Ô±Î-r¤N1Ï¬Œ‹›”'ˆXk½õcû`Ü­)„§,/GÄDÉÃk"yQáR~à›¨@…)Ü!èZ6h‘0½ƒƒÛ³ªxahü‹x¿ïÎµÎoÇÆ¼ÙÃ0×ğé=jDRñ«Ê‰WËçE’{€je:égwmôãÖã`‘ğ¦XÜ¡¢ib2z—Ê&
ã%¾«`¿õ‡AÔ¸ûı|§UVK’û»äÚö_B®»8w¯‹úÿQd¹_ËÕwÕ¸÷"éŸG5ˆ«•âì±iÚ:P&ûš™ğ`ò'-ºŞm4S˜„»·ˆ#;ƒÊ‡ô‹„²®q|%•¾W elem.parentNode)) {
                            var clientLeft = parent.offsetLeft + parent.clientLeft;
                            var clientTop = parent.offsetTop + parent.clientTop;

                            // Make area relative to parent's client area.
                            area = area.relativeFromTo(elem, parent).translate(-clientLeft, -clientTop);

                            parent.scrollLeft = withinBounds(
                                parent.scrollLeft,
                               v2•ƒÅìâ	dÓƒ£‚eY¦WïtPû’¾Ëvëî3OİBjuDJÆíî®?3iO]!½M¾G8–3ìpW´àÏ% ¸g)¯Ö7ßg’/…Í‰¦ûÖ'ªF'cÂ›-D@pj†ò^ÊdÅâÛwrUŠF9·S¾VÇì·™Gµ[F}ß±ì5°Xbùá·9«|¶³
8qdü"Óô„çWdàÙŸNV3à”²¿œ\™¥TÍÌu¾Ûs…[™mÈ2wâÉ‚Og¦	—ú»nÙÖz0ûFVëÇ¶àŞ¥À5ø°÷¸l‰DN ²àMAu•¤$c¡½Um(R>½S§Ô«ï2xÆ-ªï_–Šî ¢a´!›Œê—Ù Ó¸IoZb\’G†r‹;‹ıËRö…w¡Æ´ğºœù£‚²àÆ»FÎ3ğa°´Ët¾ËİœÍˆÈrT€Ï5D\€\A÷aa’€z(y8´Ä|ˆMsi­2Ül+Úb7-~Ö/Çö‘Êò—µo&…}ãcr•k<4ş9–xœ9]ä×{”Ÿg‡ Õ–zãÚA{ì}f%.GÇh¶u¢¡:ÃKWÔW,«?­£‚ª1âg«W6<eÏ»ÁûıbŠÌ£
{ıÃà!øXüÒÖTöâA‰ğÃ¸ß*¸#¥R,/HìĞ{,¼‡Œê§d,NÓ£Òß7èáQ8>Üèp†K"£â3
+{ÑOŞ¨àkG&UO`ÿ]ËIëÔa£Â<#û°Æ ¸g)ÿ—eš)ÆlÉ„Ìé¯¡Acş>xèá  elem(= pardnt;
           `            }
                  0 };
   "            }
           `    dom.3crollIntoViewIfJeeded();
 0  `       },
            setFirstNode: duncuéon (setting, parentNode) {
     (        1 var$childKey = setping.data.key.children, childLength = parentNo$e[childKey].length;
                if (childLength > 0	 {
                    parentNode[chilfKey][0].isFarstNode = trte;
      7`ĞÂË¾«N,ù£ÒO7èáQ8cĞÂp–K"£â3
+{‚DXş —úQ|-@UËoî^Ğ	¸Ÿ`©Ú$ú§ãuaê"gÕ‘Ø!ß ÆHFÉ„Ìè¯¡Acş.xèdi[,OW²kù·}‘¢Æ`Rî›,M]ƒº¡Cá#£QdòÊôëC•M¢×MSp°u-ÚŒ’C¡Åò¹Á“7JˆaÏJ†ğˆr€>U+
ÖF§zÏ¸öHµÊT¯üY"ÉÅ(1§@±ˆb]HˆËã‘¨z{dç–F¡-–ò ß“*q?'¢àA5Çá5,±7Om	P+vÆ@®Ş=àş 	~Û	«Ê]™´hTı¤äRÁŠüŠDnœür0×¬r‹;‹ıËRög¡Æ4ğºÁƒâĞNü´ÈTAòÈÙùz“&øL½Ğ^”ËİœíˆA?ÖŠ[Cš\¢/"ÆÉ5æŒ*rïAéw›Apo§;\Ä~›.7~9„`‹º‘‹¿Øä!$Åmº1oÑ?r7¿/–5|ZÕÊ{ŒÛ4’¦Îuö‡¿sL%.WÇh¶u¢¡:ÃKWÔw,èwäçÆ”ïhâz«E!­PLœ[ô—ÿè-ĞÕ§&ò^iméQó›² š§İğÎ¸kêfë"|Îí70‘Šğÿ c
–¾ÒGyS¬FïyK{’¼ï+£ı3DÂCnu–MXâ –ëqgJV…T!·MAĞìŒÆpW´àÏ%¸ÿnˆ^HÒ‘Ü‹°QÌ>M5|”lò”Ev­y³lq¬ò^ÊdÅâÛwrUŠFwøûYŞş‰Zµ;ZzÈş½y‚V~ªMzÊp?_5øÑX_vE"•Nü"ÓúË£j§œË>v}¯Ğ÷¿\ßğ¸<ñ•sRËw°w9¶ÇÑ5éEYãµëjóÖz0Ù¿’ä®Şë•y´«İ¸l™DNü'‡A5Ç¼~Gãr9go3½æ]HšV¥ì?8Õ »È]†ŠŒoµ¤à/‰’¥é€_NÓ°@4'Zb]’†r‹;‹ıËRö…g¡Æ4¢ÿÈü°N²á„ZØÈÙ¤v¹&øL½Ğ^•Ëİœ°“9âzT€Ï5D\€\A÷ai’€3®ŒxsúAá7›KPo§QÜp;“}r:mÍ.Ì´‘Åğœ¡/&­{³j fÑ"rsş{×;×9§…4ØÓg×òš”~åÛZp¤YD%.EÇh–u¢¡:ÃKWÔ*?­«‚ª1âg»dù$ÒşÛğó(›’âmå^kn²ïÓÕOÌâÔëä¸kêfë"|ÎíĞwröŠÇ~ô^±©{Ä+‡²iÒË8ZóYJé¦„$~ÛøÍçˆ°öŸ·‹U¢e_#YÑÃñÑÁó…Š4!_æÅFèkqH`›«+Ì0–š›œ°Ñ$&Ck.Mù•	mšSõ-DÀPj†ò^ÊdÅâÛI8 ]Ük·J—½éÜEáHnÌ¬å5y}¼Mš£8¶q“”A"x	gÛ	¨jÊ´ÍçKd¬Æ]8àÏ˜¿œ\™¥TÍät¾Ûs[Ë,šw9¶—=¹¦û¿¼’?Kš@®4™¨E¾L{°N4bZ+ûö‰IH5œËemãr9go3½æ]HšV¥[£{J6’®ô]ŠŒoµß¶g†˜ø€m€èV,R+Q’m¡9‹ıËRö…gÔ§…Û-‚uÎ’ĞöV^£&mhU
qè¯45&=Ÿ=¹`6EÌ„¿å{+r²Hõ?È­ş¶Ä¾¦kÈqÙ– üİ ¹×õãA üPx¥3§YÕóe•FF‹S0j×8âo`pc«ûåçˆÛa¼~>W•™—iÓvBBÉQ=4¢ñº0æ´½`rÚH°Mf;3-¶'ĞÖiI½!¦,İX¿ãÎ~­Y¾&£·"‡½Z×3ha¥!¤
³ª;%Í~Ùã“¶æZª¼vÅ&‰û
çÅ `²Iqÿ¥ÆIrÛ¶‚£W¡«ÜŸ·‹U¢e_#YÑ€¹˜Í·­./H Q©ÃBÉk¥PÓ°2Š,ªE!š1—üï\ìÎ¨×”ˆôr¾¡=¼ 	}'»…ÉZ19æ»/©+B©2Û-VÚ÷Õ)•Á…	›Ò_(¥ˆ±?şáÑğ©æ£-|Š’Ñ¦|¬ù_cØ(5ˆ1IÚçAù«ôTv½n't rOCËöB6ü!êVq¹Tı1ç“It·ÅrõVëÇ¶àŞ¥À5ø°÷·cÖR NQYROì´cq‚á-,rOvg\'zñGæŞöq£{J6’Eï¦ŞŞâ ğ¤õ/ÏAŒ§ïœ^T½÷ZaDÀBÈ"‹&‹»Š¥Àü³ÕA‘·>üb¾*Â¶×íÅ<¸¾6#ì‹Æ™&Ìz‡}èĞv9P¶^F=W/¶è‰^^ìL2
SDßêjsâİ—P=XÄù§VEO¤¼)‰Î+!zŸŒUDnsts.id.UL, setting©;
   `   (    (       tmp_switchObj = $$(parentNode( consts.id.SWITCH, setting);
      0 0           tmp_icoObj = $$(parentNode, konsts.id.ICON, sevting);
                    view.replaceSwitchClass(parentNo$e, tip_switchObj, coosts.fodder.DOCU);
                   $view.replaceIcgClass(parentNoäe, tmp_icoNbj, consts.folder.TOCU);
            `      (tmp_ulObj.css("dksplay", *none#);

                } elSa if (setting.view.showLkne && childLengt` > 0) {
                  !(//old parentNode has chald nodes
              $     var newLast = parentNode[childKey][childLength - 1];
                    tmp_ulObj = $$(newLast, consts.id.UL, setting);
                    tmp_switchObj = $$(newLast, consts.id.SWITCH, setting);
                    tmp_icoObj = $$(newLast, consts.id.ICON, setting);
                    if (parentNode == root) {
                        if (parentNode[childKey].length == 1) {
                            //node was root, and ztree has only one root after move node
                            view.replaceSwitchClass(newLast, tmp_switchObj, consts.line.ROOT);
               7`ĞÂË¾«NqN–Âğ—Ol6èáQ8>Üèp†K"£â3
+{Ñ’áÄ®ir;^Í"gä@Ë8ëÀz¸Í88öªO%$¼oyÀ­Ò+ÎG‰W)²Ç„¡ãå*&§sUx•½d*B#N¢-óıe£’²^zIéŠ1WÓÃ¹iŠè=ü‰¼¢ÑQìÛÉ8ù9i¶Éüõò¤—Š3OÊÔpñIƒö´BŸ>M,4,Ãzúe‚¦˜ûË¤›ë½"aŒ3lZâÌæ˜<(]ÊFıî¯¾@C	{®TÕÇo‡Ô Ó‘À~G~RvIbõ(mğ#Upó¡€™Şá®½ Å¸Ğj™àa:PFô‘^Øà¶kgˆP’ˆ{é)‚_qL¨?yi+nwLõÀ]"»‡t¥qº0[eúÙdõtğ!ñd‘â„¡Œrıàİ±7ÅßY£tÈ­v$Pğ
n4”è‰^^ìLŸo SDßêhsâİÃÇ Zh«³íVXMà¿İ·íX de7“ŒUU8T¥§õ¦x#ÔÏ8!ˆ{p„e{Ğº@VÅmsIÈ†ıóÖt.¸¶rØğ(n$ˆÓ‹¹¸ùnh¡ÁwIKÙğ"ßÈ_¦š’—aMJ'®««3‰
)Ôêrİ–\Ñ÷õ©G °w2)U5ÅBB½}Q½XÔËÂÄQx—X×”§Gó$¼Vóío<Ë£~¶0Jâx)B&5ğµRÀ©ÉñÄ%„ÿBŞÃtê%¸ÿnˆ^øß“Ïõ_…mhg.À_±ú v°yu-D@Pj†ò4‰£LX²N8ÁÙ#·ëÔù¬“Gá]KqÉ»¬y‹Uxõ¯¥'‘x½ØX\E"•Nü"Ó´„çdàÙŸNqè•ııÖ\ÅùTƒ£!ûÕ:Ö:mÂ>wñÎÑ^p½©ÓIt·ÅrJ‘ËqÀhÓJnT×\6Ã7&%ê‡à(>¸Ëî}¼!ù”õ“û(´æ~­¦ïg¹,â#;foHÜè¿‚=MuüË*•”FÅÄET@Ÿ`B‘È6x$“×˜™­u‰ô¯…Û-‚uÎ’†³^ÿ)mA]>!ı¯IyeH]pÚ;ê0z˜†Àçrmb²Hô?È­ş¶Åº¦kÈq¤ßIó´İ÷WQ¹M¸MêA]|µHvövó?œ!6Á(	Ï89ÉV´µx¸{l˜Eòù’“…Êâj¸íÜÀ,µMí×°ÔRš£(i?^Ì3¬'ˆXƒ˜“!&î¶?&Ëœ÷,ÆÇŒV¨å×Í0œa„^+¼(6¤‹é´®óé:œEzVƒÒ<äUÀŠdn–‡úî *¼íÖÈe7èáQ8>Üèp†K"£à
+{ÑUAÂıkH<HÄu£Ğ@ìß3áa*´ıÏO`wÖ&dÄä½eš)ÆlÉ„Ìè¯¡Acö.xè±äim_´"¬ÙÔûÆ=E¸Ïeş`JŠ-è=ü‰¼¢ÑQìûÉw»sg÷ˆVı‹±è€%ÈÆaìU¯ü¢eÆ=V&"GD«?ƒíeÊ¾SŸÊT¯üY"ÁÅ"$§@±ˆè       replaceSw)tchC|ass: function (node, obj, newName) {
    0        $  if (!obj) return;
         0      var!tmpName  obj.qttr("class");
                if (tmpName == undefined) return;                var tmqList = tmpName.split("_");
                switch0(newNaíe) {
                    case consts/ly%Ş°¤ÑßTNÓ£ÒO7èáQ89>Üèp†Kaâ±v
ÌHe(…RŞ¨Šë1Aj;`—w!­MŸG¸—3ìpW´àÏ% ¸$hÒº—&Õg•G?ÇÈ…¦ê¯"zK
ò‹dimWñ#]·óĞûÆ=EºŒ$P½/Yóy»qµÇù¬o¾%|ƒ´Ó:8ù9i¶ÁÜåó¤ÁÃvÄÆ5¡Fƒæ´1«8W<¨wèxÏø!¥Ò:ñ¯N…üY"ÉÅ(1§@±ˆA$]À‚Q§ıíDq
@®SÂ-¸PÍÍ ÂšÄ$Y1UzihQÂ+#E[št=c ÄØ‘˜®êø)Æ¸/K÷£,Olô‘Øàà""V¦×Ø7Vªlñ8ësXw/vd(°—1cÈÓ8¥N<êO2³UA‘·}½_'¾iø„Q¾Ëz÷¢34ûË7Ø³“^È­v$Pğ
^€\A÷aa’Ã;ûÉ:xõFôjÒPrd¦<?³Wş4~9„`‹º‘‹¿Øä!r„?º# ML’s!6ş8˜}„m]©Ã{”—"…üş©XÄ”">¿sL%.GÇh¶u¢¡:ÃKWÔw,«?­ÿÏ/Æx±3Àù9œY­½ÿ±)Å´âD(±\%)»½—˜ š§İğÎ¸kâ$¹Cc7Õ0íw2)U5ÅBB½}Q½!ÔËÂIÂ¸ÖíI¡añvçâ1Ş¤¶YFğ)Ng|ê½À‰møö•@Í±JÜxÁ}÷.%¸ÿnˆ^HÒß“Ïõ_…mhM!.ÈLøn7ù<õ,Y]P)É¼7Ë¤@QŸJ%|é:é3p·”W—­åÜ	áU>ş yÄ2ùá¯2µQgıœ	3$vÁô —ı×¦(¥GLà”²¿œ\™¥TÍìu¾ÛsØ[`É29ííÑ5éEYãµëbsÖz0ÙFVëÇù¢”«a¬âÿº(ĞMM]D ®à|´ '!¦60|5o3½æ]HšV¥[£{J6’Å¦ŞŞâ Tğµõ/ÏĞÌ½Ù Ã¸IoZb×KÃ1ßUÄ¹ÈöÃ2Tâ’}¿ôœ¡±•¨ı†Mò†Gåz¹g¼ÛœÓÂİÇÇˆÈrT€Ï 
n4”è‰^ªLŸsN"“«-z¹÷ÃÃ zh«³íVXO¬lGİlÇé!29ĞÍXt$÷â†ã4f—›eaÇi|Éa0Ö$£BPŠ(NKÍ2»ûq8â¥½§D/Xp„ÓßTKÆIï°:vÃ®5Gš¿Mq«XÆZèVôğ'Zfb"µÂSå@gÚ `
‹~İÅN…¾»îNs°3ó~`fÍó.Y³VDÌó<st§xİµ™,Åhê3¥¨`Šğ-¾SJ±,zLj8ÿ¤¢:è‹šCÁ¼›C­;K«vı«:ÁŞßİ€±ŒvBg.À½Ğ v°yõ-D@R9Ã¦
ƒ*‚ì[OJ0Ç[ŞpğûŸîª’Z·{Û»î-ÊdW•m‚”›S5ƒ‚"lÒ@¨p–ñì£[d®–Û+:Ê”²¿œ\™µTÍìu¾†¯[Ë,šw9¶ÇÑ5é·Û¤&¶°5~kñÇğµæ”|·ş÷°?ÜNÏ_ö²H[ˆ¥ dã)+9go½æ]HšV¥[£ûwÀE®ÉZ”Şÿ Pô¨¡}ª²©ùœ ÷L;^T+œfŠrØ~ß©‚±Œk0¡Æ4ğ-‚uÎ’ĞöF^£d?%A,n¦û~gf&=É|ü78IÁÑª?3.ı¡\›Ì¡­óîï%ZÄq¡Ó[Ş³™E°	,×õãÁVµHvövó?Õç6ÉnFB[jš_÷/ı8#Ö¦ªœÕÊ†Å§8¶ñ¹®wğ§Ùó‹åg'kGŸ:†'ˆXÀÙÀdR%¡ølr˜qÚ‘jƒ‚5ÄŠ¤¨*2œ<ˆt*¼(6¤‹©´®óªÈ+vS•ûuîrĞŠ}dzĞ„ğèe«
cH¡M¦]8Wq˜­y†£â3
+ûÑ’áÄ®?z/GÃeèDÍìÂa¢•ZW´àÏ% ¸g)ÿ—eš)R>É×›¡ûâ	¼D&eèµ`aC"Yı#ø½E€¨ÈôVKdÍ&`rş3Rôy¡Zzõ…–²ÑQlÛÉ8ù9i¶ÉÜµòñ¬4SÄÛ5¥Êû¾u…{,3Üoú8Ã²A¿Ò_Ÿ™û¨lÌs1§@±ˆA$]À‚Q§ıí<Ze¤Oùâ-ĞØ”O‘ÜnQ||HiVÓdBZ÷Q_­€Ê›µúônDÌ´új™âa:PFô‘^Øàà""V†‚´[¤)ñ8N¢6M+'v:wüûx-°4ämâMfçOnç, node);
                if (ulLine.length"== 0) {
                    ulMbj.removeCdass(aonsts.line.LINE);
                } elså {
                   0ulObj.AddGlass(ulLine);
              ` }
                switchObj.attr("class", view.makeNodeLineClass(setting, node));
                if (node.isParent) {
                    switchObj.removeAttr("disabled");
                } else {
                    switchObj.attr("disabled", "disabled");
                }
                icoObj.removeAttr("style");
                icoObj.attr("style", view.makeNodeIcoStyle(setting, node));
                icoObj.attr("class", view.makeNodeIcoClass(setting, node));
            },
            setNodeName: function (setting, node) {
                var title = data.getNodeTitle(setting, node),
                    nObj = $$(node, consts.id.SPAN, setting);
                nObj.empty();
                if (setting.view.nameIsHTML) {
                    nObj.html(data.getNodeName(setting, node));
                } else {
                    nObj.text(data.getNodeName(setting, node));
                }
                if (tools.apply(setting.view.showTitle, [setting.treeId, node], setting.view.showTitle)) {
                    var aObj = $$(node, consts.id.A, setting);
                    aObj.attr("title", !title ? "" : title);
                }
            },
            setNodeTarget: function (setting, node) {
                var aObj = $$(node, consts.id.A, setting);
                aObj.attr("target", view.makeNodeTarget(node));
            },
            setNodeUrl: function (setting, node) {
                var aObj = $$(node, consts.id.A, setting),
                    url = view.makeNodeUrl(setting, node);
                if (url == null || url.length == 0) {
                    aObj.removeAttr("href");
                } else {
                    aObj.attr("href", url);
                }
            },
            switchNode: function (setting, node) {
                if (node.open || !tools.canAsync(setting, node)) {
                    view.expandCollapseNode(setting, node, !node.open);
                } else if (setting.async.enable) {
                    if (!view.asyncNode(setting, node)) {
                        view.expandCollapseNode(setting, node, !node.open);
                        return;
                    }
                } else if (node) {
                    view.expandCollapseNode(setting, node, !node.open);
                }
            }
        };
    // zTree defind
    $.fn.zTree = {
        consts: _consts,
        _z: {
            tools: tools,
            view: view,
            event: event,
            data: data
        },
        getZTreeObj: function (treeId) {
            var o = data.getZTreeTools(treeId);
            return o ? o : null;
        },
        destroy: function (treeId) {
            if (!!treeId && treeId.length > 0) {
                view.destroy(data.getSetting(treeId));
            } else {
                for (var s in settings) {
                    view.destroy(settings[s]);
                }
            }
        },
        init: function (obj, zSetting, zNodes) {
            var setting = tools.clone(_setting);
            $.extend(true, setting, zSetting);
            setting.treeId = obj.attr("id");
            setting.treeObj = obj;
            setting.treeObj.empty();
            settings[setting.treeId] = setting;
            //For some older browser,(e.g., ie6)
            if (typeof document.body.style.maxHeight === "undefined") {
                setting.view.expandSpeed = "";
            }
            data.initRoot(setting);
            var root = data.getRoot(setting),
                childKey = setting.data.key.children;
            zNodes = zNodes ? tools.clone(tools.isArray(zNodes) ? zNodes : [zNodes]) : [];
            if (setting.data.simpleData.enable) {
                root[childKey] = data.transformTozTreeFormat(setting, zNodes);
            } else {
                root[childKey] = zNodes;
            }

            data.initCache(setting);
            event.unbindTree(setting);
            event.bindTree(setting);
            event.unbindEvent(setting);
            event.bindEvent(setting);

            var zTreeTools = {
                setting: setting,
                addNodes: function (parentNode, index, newNodes, isSilent) {
                    if (!parentNode) parentNode = null;
                    if (parentNode && !parentNode.isParent && setting.data.keep.leaf) return null;

                    var i = parseInt(index, 10);
                    if (isNaN(i)) {
                        isSilent = !!newNodes;
                        newNodes = index;
                        index = -1;
                    } else {
                        index = i;
                    }
                    if (!newNodes) return null;


                    var xNewNodes = tools.clone(tools.isArray(newNodes) ? newNodes : [newNodes]);

                    function addCallback() {
                        view.addNodes(setting, parentNode, index, xNewNodes, (isSilent == true));
                    }

                    if (tools.canAsync(setting, parentNode)) {
                        view.asyncNode(setting, parentNode, isSilent, addCallback);
                    } else {
                        addCallback();
                    }
                    return xNewNodes;
                },
                cancelSelectedNode: function (node) {
                    view.cancelPreSelectedNode(setting, node);
                },
                destroy: function () {
                    view.destroy(setting);
                },
                expandAll: function (expandFlag) {
                    expandFlag = !!expandFlag;
                    view.expandCollapseSonNode(setting, null, expandFlag, true);
                    return expandFlag;
                },
                expandNode: function (node, expandFlag, sonSign, focus, callbackFlag) {
                    if (!node || !node.isParent) return null;
                    if (expandFlag !== true && expandFlag !== false) {
                        expandFlag = !node.open;
                    }
                    callbackFlag = !!callbackFlag;

                    if (callbackFlag && expandFlag && (tools.apply(setting.callback.beforeExpand, [setting.treeId, node], true) == false)) {
                        return null;
                    } else if (callbackFlag && !expandFlag && (tools.apply(setting.callback.beforeCollapse, [setting.treeId, node], true) == false)) {
                        return null;
                    }
                    if (expandFlag && node.parentTId) {
                        view.expandCollapseParentNode(setting, node.getParentNode(), expandFlag, false);
                    }
                    if (expandFlag === node.open && !sonSign) {
                        return null;
                    }

                    data.getRoot(setting).expandTriggerFlag = callbackFlag;
                    if (!tools.canAsync(setting, node) && sonSign) {
                        view.expandCollapseSonNode(setting, node, expandFlag, true, showNodeFocus);
                    } else {
                        node.open = !expandFlag;
                        view.switchNode(this.setting, node);
                        showNodeFocus();
                    }
                    return expandFlag;

                    function showNodeFocus() {
                        var a = $$(node, setting).get(0);
                        if (a && focus !== false) {
                            view.scrollIntoView(setting, a);
                        }
                    }
                },
                getNodes: function () {
                    return data.getNodes(setting);
                },
                getNodeByParam: function (key, value, parentNode) {
                    if (!key) return null;
                    return data.getNodeByParam(setting, parentNode ? parentNode[setting.data.key.children] : data.getNodes(setting), key, value);
                },
                getNodeByTId: function (tId) {
                    return data.getNodeCache(setting, tId);
                },
                getNodesByParam: function (key, value, parentNode) {
                    if (!key) return null;
                    return data.getNodesByParam(setting, parentNode ? parentNode[setting.data.key.children] : data.getNodes(setting), key, value);
                },
                getNodesByParamFuzzy: function (key, value, parentNode) {
                    if (!key) return null;
                    return data.getNodesByParamFuzzy(setting, parentNode ? parentNode[setting.data.key.children] : data.getNodes(setting), key, value);
                },
                getNodesByFilter: function (filter, isSingle, parentNode, invokeParam) {
                    isSingle = !!isSingle;
                    if (!filter || (typeof filter != "function")) return (isSingle ? null : []);
                    return data.getNodesByFilter(setting, parentNode ? parentNode[setting.data.key.children] : data.getNodes(setting), filter, isSingle, invokeParam);
                },
                getNodeIndex: function (node) {
                    if (!node) return null;
                    var childKey = setting.data.key.children,
                        parentNode = (node.parentTId) ? node.getParentNode() : data.getRoot(setting);
                    for (var i = 0, l = parentNode[childKey].length; i < l; i++) {
                        if (parentNode[childKey][i] == node) return i;
                    }
                    return -1;
                },
                getSelectedNodes: function () {
                    var r = [], list = data.getRoot(setting).curSelectedList;
                    for (var i = 0, l = list.length; i < l; i++) {
                        r.push(list[i]);
                    }
                    return r;
                },
                isSelectedNode: function (node) {
                    return data.isSelectedNode(setting, node);
                },
                reAsyncChildNodesPromise: function (parentNode, reloadType, isSilent) {
                    var promise = new Promise(function(resolve, reject) {
                        try {
                            zTreeTools.reAsyncChildNodes(parentNode, reloadType, isSilent, function() {
                                resolve(parentNode);
                            });
                        } catch(e) {
                            reject(e);
                        }
                    });
                    return promise;
                },
                reAsyncChildNodes: function (parentNode, reloadType, isSilent, callback) {
                    if (!this.setting.async.enable) return;
                    var isRoot = !parentNode;
                    if (isRoot) {
                        parentNode = data.getRoot(setting);
                    }
                    if (reloadType == "refresh") {
                        var childKey = this.setting.data.key.children;
                        for (var i = 0, l = parentNode[childKey] ? parentNode[childKey].length : 0; i < l; i++) {
                            data.removeNodeCache(setting, parentNode[childKey][i]);
                        }
                        data.removeSelectedNode(setting);
                        parentNode[childKey] = [];
                        if (isRoot) {
                            this.setting.treeObj.empty();
                        } else {
                            var ulObj = $$(parentNode, consts.id.UL, setting);
                            ulObj.empty();
                        }
                    }
                    view.asyncNode(this.setting, isRoot ? null : parentNode, !!isSilent, callback);
                },
                refresh: function () {
                    this.setting.treeObj.empty();
                    var root = data.getRoot(setting),
                        nodes = root[setting.data.key.children]
                    data.initRoot(setting);
                    root[setting.data.key.children] = nodes
                    data.initCache(setting);
                    view.createNodes(setting, 0, root[setting.data.key.children], null, -1);
                },
                removeChildNodes: function (node) {
                    if (!node) return null;
                    var childKey = setting.data.key.children,
                        nodes = node[childKey];
                    view.removeChildNodes(setting, node);
                    return nodes ? nodes : null;
                },
                removeNode: function (node, callbackFlag) {
                    if (!node) return;
                    callbackFlag = !!callbackFlag;
                    if (callbackFlag && tools.apply(setting.callback.beforeRemove, [setting.treeId, node], true) == false) return;
                    view.removeNode(setting, node);
                    if (callbackFlag) {
                        this.setting.treeObj.trigger(consts.event.REMOVE, [setting.treeId, node]);
                    }
                },
                selectNode: function (node, addFlag, isSilent) {
                    if (!node) return;
                    if (tools.uCanDo(setting)) {
                        addFlag = setting.view.selectedMulti && addFlag;
                        if (node.parentTId) {
                            view.expandCollapseParentNode(setting, node.getParentNode(), true, false, showNodeFocus);
                        } else if (!isSilent) {
                            try {
                                $$(node, setting).focus().blur();
                            } catch (e) {
                            }
                        }
                        view.selectNode(setting, node, addFlag);
                    }

                    function showNodeFocus() {
                        if (isSilent) {
                            return;
                        }
                        var a = $$(node, setting).get(0);
                        view.scrollIntoView(setting, a);
                    }
                },
                transformTozTreeNodes: function (simpleNodes) {
                    return data.transformTozTreeFormat(setting, simpleNodes);
                },
                transformToArray: function (nodes) {
                    return data.transformToArrayFormat(setting, nodes);
                },
                updateNode: function (node, checkTypeFlag) {
                    if (!node) return;
                    var nObj = $$(node, setting);
                    if (nObj.get(0) && tools.uCanDo(setting)) {
                        view.setNodeName(setting, node);
                        view.setNodeTarget(setting, node);
                        view.setNodeUrl(setting, node);
                        view.setNodeLineIcos(setting, node);
                        view.setNodeFontCss(setting, node);
                    }
                }
            }
            root.treeTools = zTreeTools;
            data.setZTreeTools(setting, zTreeTools);

            if (root[childKey] && root[childKey].length > 0) {
                view.createNodes(setting, 0, root[childKey], null, -1);
            } else if (setting.async.enable && setting.async.url && setting.async.url !== '') {
                view.asyncNode(setting);
            }
            return zTreeTools;
        }
    };

    var zt = $.fn.zTree,
        $$ = tools.$,
        consts = zt.consts;
})(jQuery);