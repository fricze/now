// Compiled by ClojureScript 0.0-3211 {}
goog.provide('now.core');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('now.views');
goog.require('now.routes');
goog.require('now.handlers');
goog.require('re_frame.core');
goog.require('now.subs');
now.core.mount_root = (function now$core$mount_root(){
return reagent.core.render.call(null,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [now.views.main_panel], null),document.getElementById("app"));
});
now.core.init = (function now$core$init(){
now.routes.app_routes.call(null);

re_frame.core.dispatch_sync.call(null,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"initialize-db","initialize-db",230998432)], null));

return now.core.mount_root.call(null);
});
goog.exportSymbol('now.core.init', now.core.init);

//# sourceMappingURL=core.js.map?rel=1445440531327