const Register = { template: '<register></register>' }
const Login = { template: '<login></login>' }
const Index = { template: '<index></index>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/login', component: Login },
	    { path: '/register', component: Register},
	    { path: '/', component: Index}
	  ]
});

var app = new Vue({
	router,
	el: '#sportArena'
});