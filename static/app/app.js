const Register = { template: '<register></register>' }
const Login = { template: '<login></login>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		{ path: '/', component: Login },
	    { path: '/register', component: Register}
	  ]
});

var app = new Vue({
	router,
	el: '#sportArena'
});