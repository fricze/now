// Compiled by ClojureScript 0.0-3211 {}
goog.provide('figwheel.connect');
goog.require('cljs.core');
goog.require('figwheel.client.utils');
goog.require('figwheel.client');
goog.require('now.core');
figwheel.client.start.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"websocket-url","websocket-url",-490444938),"ws://localhost:3449/figwheel-ws",new cljs.core.Keyword(null,"on-jsload","on-jsload",-395756602),(function() { 
var G__30325__delegate = function (x){
if(cljs.core.truth_(now.core.mount_root)){
return cljs.core.apply.call(null,now.core.mount_root,x);
} else {
return figwheel.client.utils.log.call(null,new cljs.core.Keyword(null,"debug","debug",-1608172596),"Figwheel: :on-jsload hook 'now.core/mount-root' is missing");
}
};
var G__30325 = function (var_args){
var x = null;
if (arguments.length > 0) {
var G__30326__i = 0, G__30326__a = new Array(arguments.length -  0);
while (G__30326__i < G__30326__a.length) {G__30326__a[G__30326__i] = arguments[G__30326__i + 0]; ++G__30326__i;}
  x = new cljs.core.IndexedSeq(G__30326__a,0);
} 
return G__30325__delegate.call(this,x);};
G__30325.cljs$lang$maxFixedArity = 0;
G__30325.cljs$lang$applyTo = (function (arglist__30327){
var x = cljs.core.seq(arglist__30327);
return G__30325__delegate(x);
});
G__30325.cljs$core$IFn$_invoke$arity$variadic = G__30325__delegate;
return G__30325;
})()
,new cljs.core.Keyword(null,"build-id","build-id",1642831089),"dev"], null));

//# sourceMappingURL=connect.js.map?rel=1445440465735