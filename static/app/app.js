const Register = { template: '<register></register>' }
const Login = { template: '<login></login>' }
const Index = { template: '<index></index>' }
const MyProfile = { template: '<myprofile></myprofile>' }
const RegisterCoMan = { template: '<registerCoachManager></registerCoachManager>' }
const AdminShowRegisterUsers = { template: '<adminShowRegisterUsers></adminShowRegisterUsers>' }
const BarShow = { template: '<showbar></showbar>' }
const ShowSportBuilding = { template: '<showBuilding></showBuilding>' }
const CreateSportBuilding = { template: '<createSportBuilding></createSportBuilding>'}
const CreateSportBuildingContent = { template: '<createContent></createContent>'}
const ChangeSportBuildingContent = { template: '<changeContent></changeContent>'}
const ShowSportBuildingContentByManager = { template: '<showSportBuildingContentByManager></showSportBuildingContentByManager>'}

const router = new VueRouter({
	  mode: 'hash',	
	 routes: [
		{ path: '/login', 
		components: {
			default: Login,
			Bar: BarShow
			}
		},
		{ path: '/createSportBuilding',
	    components: {
			default: CreateSportBuilding,
			Bar: BarShow
		    }
	    },
		{ path: '/createContent',
	    components: {
			default: CreateSportBuildingContent,
			Bar: BarShow
		    }
	    },
		{ path: '/changeContent',
	    components: {
			default: ChangeSportBuildingContent,
			Bar: BarShow
		    }
	    },
		{ path: '/manager/showContents',
	    components: {
			default: ShowSportBuildingContentByManager,
			Bar: BarShow
		    }
	    },
		{ path: '/ShowSportBuilding',
	    components: {
			default: ShowSportBuilding,
			Bar: BarShow
		    }
	    },
	    { path: '/register',
	    components: {
			default: Register,
			Bar: BarShow
		    }
	    },
	   { path: '/register',
	    components: {
			default: Register,
			Bar: BarShow
		    }
	    },
	    { path: '/', 
	    components: {
			default: Index,
			Bar: BarShow
			}
		},
		{ path: '/logged', 
	    components: {
			default: Index,
			Bar: BarShow
			}
		},
	    { path: '/myprofile', 
	    components: {
			default: MyProfile,
			Bar: BarShow
			}
		},
	    { path: '/registerCoachManager',
	    components: {
			default: RegisterCoMan,
			Bar: BarShow
			}
		},
	    { path: '/adminShowRegisterUsers', 
	    components: {
			default: AdminShowRegisterUsers,
			Bar: BarShow
			}
		},
	  ]
});

router.beforeEach((to,from,next) => {
	if(from.path == '/changeContent'){
		axios
		.delete('/workout/invalidateChange')
	}
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
	} else if(to.path == '/adminShowRegisterUsers' || to.path == '/registerCoachManager' || to.path == '/createSportBuilding'){
				axios
			.get('user/userType')
			.then(response => (Type = response.data))
			.finally(() => {
                    if(Type == "Admin") {
						return next({path:to});
					}
					else {
						alert("You have no rights to be here! >:(");
						return next({path:'/'});
					}
            });
	}
        else if(to.path == '/createContent'){
				axios
			.get('user/userType')
			.then(response => (Type = response.data))
			.finally(() => {
                    if(Type == "Manager") {
						return next({path:to});
					}
					else {
						alert("You have no rights to be here! >:(");
						return next({path:'/'});
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