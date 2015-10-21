// Compiled by ClojureScript 0.0-3211 {}
goog.provide('now.routes');
goog.require('cljs.core');
goog.require('re_frame.core');
goog.require('goog.history.EventType');
goog.require('goog.events');
goog.require('secretary.core');
goog.require('goog.History');
now.routes.hook_browser_navigation_BANG_ = (function now$routes$hook_browser_navigation_BANG_(){
var G__23854 = (new goog.History());
goog.events.listen(G__23854,goog.history.EventType.NAVIGATE,((function (G__23854){
return (function (event){
return secretary.core.dispatch_BANG_.call(null,event.token);
});})(G__23854))
);

G__23854.setEnabled(true);

return G__23854;
});
now.routes.app_routes = (function now$routes$app_routes(){
secretary.core.set_config_BANG_.call(null,new cljs.core.Keyword(null,"prefix","prefix",-265908465),"#");

var action__23763__auto___23863 = (function (params__23764__auto__){
if(cljs.core.map_QMARK_.call(null,params__23764__auto__)){
var map__23859 = params__23764__auto__;
var map__23859__$1 = ((cljs.core.seq_QMARK_.call(null,map__23859))?cljs.core.apply.call(null,cljs.core.hash_map,map__23859):map__23859);
return re_frame.core.dispatch.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-panel","set-active-panel",-965871124),new cljs.core.Keyword(null,"home-panel","home-panel",1226198754)], null));
} else {
if(cljs.core.vector_QMARK_.call(null,params__23764__auto__)){
var vec__23860 = params__23764__auto__;
return re_frame.core.dispatch.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-panel","set-active-panel",-965871124),new cljs.core.Keyword(null,"home-panel","home-panel",1226198754)], null));
} else {
return null;
}
}
});
secretary.core.add_route_BANG_.call(null,"/",action__23763__auto___23863);


var action__23763__auto___23864 = (function (params__23764__auto__){
if(cljs.core.map_QMARK_.call(null,params__23764__auto__)){
var map__23861 = params__23764__auto__;
var map__23861__$1 = ((cljs.core.seq_QMARK_.call(null,map__23861))?cljs.core.apply.call(null,cljs.core.hash_map,map__23861):map__23861);
return re_frame.core.dispatch.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-panel","set-active-panel",-965871124),new cljs.core.Keyword(null,"about-panel","about-panel",334628515)], null));
} else {
if(cljs.core.vector_QMARK_.call(null,params__23764__auto__)){
var vec__23862 = params__23764__auto__;
return re_frame.core.dispatch.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"set-active-panel","set-active-panel",-965871124),new cljs.core.Keyword(null,"about-panel","about-panel",334628515)], null));
} else {
return null;
}
}
});
secretary.core.add_route_BANG_.call(null,"/about",action__23763__auto___23864);


return now.routes.hook_browser_navigation_BANG_.call(null);
});

//# sourceMappingURL=routes.js.map?rel=1445440459620