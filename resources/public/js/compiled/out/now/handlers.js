// Compiled by ClojureScript 0.0-3211 {}
goog.provide('now.handlers');
goog.require('cljs.core');
goog.require('now.db');
goog.require('re_frame.core');
re_frame.core.register_handler.call(null,new cljs.core.Keyword(null,"initialize-db","initialize-db",230998432),(function (_,___$1){
return now.db.default_db;
}));
re_frame.core.register_handler.call(null,new cljs.core.Keyword(null,"set-active-panel","set-active-panel",-965871124),(function (db,p__30402){
var vec__30403 = p__30402;
var _ = cljs.core.nth.call(null,vec__30403,(0),null);
var active_panel = cljs.core.nth.call(null,vec__30403,(1),null);
return cljs.core.assoc.call(null,db,new cljs.core.Keyword(null,"active-panel","active-panel",-1802545994),active_panel);
}));

//# sourceMappingURL=handlers.js.map?rel=1445442016780