Vue.component("login", {
	data: function () {
		    return {
				registerTitle: "Register",
				username:null,
				password:null,
				userType:null
		    }
	},
	template: ` 
<div style="text-align:center;">
	<h1 style="font-size: 50px;">WELCOME TO SPORT ARENA</h1>
    <h2 style="font-size: 35px;">Login:</h2>
    <table style="margin-left:auto; margin-right:auto;">
        <tr>
            <td colspan="2"><input type="text" placeholder="Username" style="font-size: 25px;" v-model="username"></input></td>
        </tr>
        <tr>
            <td colspan="2"><input type="password" placeholder="Password" style="font-size: 25px;" v-model="password"></input></td>
        </tr>
        <tr>
            <td colspan="2"><select v-model="userType">
            	<option value="Customer">Customer</option>
            	<option value="Manager">Manager</option>
            	<option value="Coach">Coach</option>
            	<option value="Coach">Coach</option>
            </select></td>
        </tr>
        <tr style="height:70px">
        	<td><button v-on:click="alert" style="font-size: 25px;"> Submit </button></td>
        	<td><input type = "submit" v-on:click = "ShowRegisterForm" v-bind:value = "this.registerTitle" style="font-size: 25px;"></td>
        </tr>
    </table>
</div> 
`
	, 
	methods : {
		init : function() {
		}, 
		ShowRegisterForm : function () {
			router.push(`/register`);
		},
		alert : function () {
			alert(this.username);
			alert(this.password);
		}
	},
	mounted () {
    }
});