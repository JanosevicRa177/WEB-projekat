Vue.component("login", {
	data: function () {
		    return {
				backTitle: "Back main page",
				username:'',
				password:'',
				logbool:false,
				userType:"Customer"
		    }
	},
	template: ` 
<div style="text-align:center;">
    <h2 style="font-size: 55px;">Login</h2>
    <table style="margin-left:auto; margin-right:auto;">
        <tr>
            <td colspan="2"><input type="text" placeholder="Username" style="font-size: 30px; width: 100%;" v-model="username"></input></td>
        </tr>
        <tr>
            <td colspan="2"><input type="password" placeholder="Password" style="font-size: 30px; width: 100%;" v-model="password"></input></td>
        </tr>
        <tr style="height:70px">
        	<td><button v-on:click="login()" style="font-size: 30px;width: 240px;margin:5px"> Submit </button></td>
        	<td><input type = "submit" v-on:click = "ShowRegisterForm" v-bind:value = "this.backTitle" style="font-size: 30px;width: 240px;margin:5px"></td>
        </tr>
    </table>
</div> 
`
	, 
	methods : {
		init : function() {
		}, 
		ShowRegisterForm : function () {
			router.push(`/`);
		},
		checklogin : function(data){
		        if(data == "wrong") {toast ("Wrong username and/or password, try again!");}
		        else if(data == "logged") {router.push(`/`); alert("You are now logged in!");}
		        else alert("User " + data + " already logged in!" );
		},
		login : function () {
			axios
				.post('user/login',{"username":''+ this.username,"password":'' + this.password})
		        .then(response => (this.checklogin(response.data)));
		        
		}

	},
	mounted () {
    }
});