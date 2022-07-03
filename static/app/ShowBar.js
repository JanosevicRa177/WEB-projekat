Vue.component("showbar", {
	data: function () {
		    return {
				loggedin : null,
				isAdmin:false,
				isCoach:false,
				isCustomer:false,
				isManager:false,
				log: null,
				regprof: null,
		    }
	},
	template: ` 
<div style="text-align:center;width:calc(100% + 80px);margin-left:-50px;margin-top:-35px " class="bar">
	<table style="width:calc(100% - 22px);margin-left:22px;">
		<tr>
			<td style="text-align:left">
				<span class="topnav">
				  <button v-on:click="ShowHome"> Home </button>
				  <button v-on:click="LoginLogoffFunction" >{{log}}</button>
				  <button v-on:click="ShowRegisterFormOrProfile"> {{regprof}} </button>
				</span>
			</td>
			<td>
				  <span v-if="isAdmin" class="topnavbar">
				  <button v-on:click="adminUserShowAndRegister">Show Registered users</button>
				  <button v-on:click="createSportBuilding">Register building</button>
				  <button v-on:click="registerCoachManager">Register managers / coaches </button>
				  <button>Admin</button>
				  <button>Admin</button>
				  </span>
				  <span v-if="isManager" class="topnavbar">
				  <button v-on:click="createContent">Add new content</button>
				  <button>Change sport object content</button>
				  <button>Show all trainings</button>
				  <button>Check customer tranining</button>
				  </span>
				  <span v-if="isCustomer" class="topnavbar">
				  <button>Show my trainings</button>
				  <button>Customer</button>
				  <button>Customer</button>
				  <button>Customer</button>
				  </span>
				  <span v-if="isCoach" class="topnavbar">
				  <button>Show my trainings</button>
				  <button>Coach</button>
				  <button>Coach</button>
				  <button>Coach</button>
				  </span>
			</td>
		</tr>
	</table>
</div> 	
`
	, 
	methods : {
		init : function() {
		},
		initialiseUserType : function (data) {
			if(data == "Admin") this.isAdmin = true;
			else if(data == "Manager") this.isManager = true;
			else if(data == "Coach") this.isCoach = true;
			else if(data == "Customer") this.isCustomer = true;
		},
		createSportBuilding : function () {
			router.push('/createSportBuilding');
		},
		createContent : function () {
		      axios
		      .get('manager/checkSportBuilding')
		      .then(response => (this.CheckManagerValidity(response.data)));
		},
		CheckManagerValidity : function (data) {
			if(data == "True") 
			{
				router.push('/createContent');
			}
			else alert("You have no sport building signed for you!");
		},
		logchange : function(data) {
			this.loggedin = data;
			if(this.loggedin)  { 
				this.log = "Log off";
				this.regprof = "My profile";
				axios
					.get('user/getUsername')
					.then(response => this.usernameText = "Welcome " + response.data + "!");
			}
			else {
				this.log = "Login";
				this.regprof = "Register";
			}
		},
		adminUserShowAndRegister : function() {
			router.push(`/adminShowRegisterUsers`);
		},
		registerCoachManager : function() {
			router.push('/registerCoachManager');
		},
		ShowHome : function() {
			router.push(`/`);
		},
		LoginLogoffFunction : function(){
			if(this.loggedin) {
				if (!confirm('Are you sure you wanna log off?'));				
				else {
					this.loggedin = false;
					axios
						.get('user/logoff');
					this.log = "Login";
					this.userType = "none";
					this.regprof = "Register";
					this.isAdmin = false;
					this.isManager = false;
					this.isCoach = false;
					this.isCustomer = false;
					router.push(`/`);
					this.componentKey += 1;

				}
			}
			else{
			 	router.push(`/login`);
			}
		},
		ShowRegisterFormOrProfile : function () {
			if(this.loggedin == false)
				router.push(`/register`);
			else router.push(`/myprofile`);
		},
		regCoach : function() {
			router.push('/registerCoachManager');
		}
	},
	mounted () {
		axios
			.get('user/getlogged')
			.then(response => (this.logchange(response.data)));
		axios
			.get('user/userType')
			.then(response => (this.initialiseUserType(response.data)));
    }
});