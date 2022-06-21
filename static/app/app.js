const Register = { template: '<register></register>' }
const Login = { template: '<login></login>' }
const Index = { template: '<index></index>' }
const MyProfile = { template: '<myprofile></myprofile>' }

const router = new VueRouter({
	  mode: 'hash',	
	 routes: [
		{ path: '/login', component: Login },
	    { path: '/register', component: Register},
	    { path: '/', component: Index},
	    { path: '/myprofile', component: MyProfile}
	  ]
});

router.beforeEach((to,from,next) => {
   	if(to.path == '/myprofile')  { 
		axios
			.get('user/getlogged')
			.then(response => (logged = response.data))
			.finally(() => {
                    if(logged == false) {
						alert("No user logged in!");
						return next({path:'/'});
					}
					else {
						return next({path:to});
					}
            });
	}
	else {
		return next({path:to});	
	}
});



var app = new Vue({
	router,
	el: '#sportArena'
});