Vue.component("register", {
	data: function () {
		    return {
		      user: {name:null,surname:null,username:null,password:null,gender:null},
		      loginTitle: "Back to login"
		    }
	},
	template: ` 
<div>
        <h4>Registracija na sajt:</h4>
        <table>
        <tr>
            <td>Username:</td>
            <td><input type="text" v-model="user.username"></input></td>
        </tr>
        <tr>
            <td>Password:</td>
                <td><input type="password" v-model="user.password"></input></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" v-model="user.name"></input></td>
        </tr>
        <tr>
        <td>Surname:</td>
        <td><input type="text" v-model="user.surname"></input></td>
        </tr>
        <tr>
        <td>Gender:</td>
            <td><select name="gender" id="gender" v-model="user.gender">
            	<option value="Male">male</option>
            	<option value="Female">female</option>
            	<option value="Alien">alien</option>
            </select></td>
        </tr>
        <tr>
        <td>Birth Date:</td>
        <td><input type="date" value="2000-05-15"></input></td>
        </tr>
        <tr>
        	<td><button v-on:click="alert()"> Submit </button></td>
        	<td><input type = "submit" v-on:click = "ShowLoginForm" v-bind:value = "this.loginTitle"></td>
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
			//this.user.birthDate = new Date(this.user.birthDate).toLocaleDateString('fr-CA', { year: 'numeric', month: '2-digit', day: '2-digit' })
			//alert(this.user.birthDate);
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