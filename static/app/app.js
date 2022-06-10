//const Register = { template: '<register></register>' }
//const ShoppingCart = { template: '<shopping-cart></shopping-cart>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Register},
	    { path: '/sc', component: ShoppingCart }
	  ]
});

var app = new Vue({
	router,
	el: '#webShop'
});