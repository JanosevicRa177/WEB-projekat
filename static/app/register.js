Vue.component("register", {
	data: function () {
		    return {
		      user: {name:null,surname:null,username:null,password:null,gender:null,birthDate:null},
		      loginTitle: "Back to login",
		      birthDateString: null
		    }
	},
	template: ` 
<div style="text-align:center;">
    <h2 style="font-size: 55px;">Registration</h2>
        <table style="margin-left:auto; margin-right:auto;">
        <tr>
            <td align="left"><strong style="font-size: 30px;">Username:</strong></td>
            <td><input type="text" v-model="user.username" style="font-size: 25px;"></input></td>
        </tr>
        <tr>
            <td align="left"><strong style="font-size: 30px;">Password:</strong></td>
                <td><input type="password" v-model="user.password" style="font-size: 25px;"></input></td>
        </tr>
        <tr>
            <td align="left"><strong style="font-size: 30px;">Name:</strong></td>
            <td><input type="text" v-model="user.name" style="font-size: 25px;"></input></td>
        </tr>
        <tr>
        <td align="left"><strong style="font-size: 30px;">Surname:</strong></td>
        <td><input type="text" v-model="user.surname" style="font-size: 25px;"></input></td>
        </tr>
        <tr>
        <td align="left"><strong style="font-size: 30px;">Gender:</strong></td>
            <td><select name="gender" id="gender" v-model="user.gender"  style="font-size: 25px; width: 100%;">
            	<option value="Male">male</option>
            	<option value="Female">female</option>
            	<option value="Alien">alien</option>
            </select></td>
        </tr>
        <tr>
        <td align="left"><strong style="font-size: 30px;">Birth Date:</strong></td>
        <td><input type="date" value="2000-05-15" v-model="user.birthDate" style="font-size: 25px; width: 98.3%;"></input></td>
        </tr>
        <tr style="height:70px">
        	<td colspan="2">
	        	<button v-on:click="alert()" style="font-size: 25px; width: 35%;margin: 0px 10px;"> Submit </button> 
	        	<input type = "submit" v-on:click = "ShowLoginForm" v-bind:value = "this.loginTitle" style="font-size: 25px; width: 35%; margin: 0px 10px;">
        	</td>
        </tr>
    </table>
</div> 
`
	, 
	methods : {
		init : function() {
			this.user = {};
		}, 
		ShowLoginForm : function () {
			router.push(`/`);
		},
		alert : function () {
			alert(this.user.birthDate);
				axios
		          .post('customer/add',this.user)
		          .then(response => {alert(response.data)})
		} 
	},
	mounted () {
        /*axios
          .get('rest/proizvodi/getJustSc')
          .then(response => {
        	  this.sc = response.data;
          });
        axios
        .get('rest/proizvodi/getTotal')
        .then(response => (this.total = response.data));*/
    }
});