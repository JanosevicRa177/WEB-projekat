Vue.component("register", {
	data: function () {
		    return {
		      user: {name:null,surname:null,username:null,password:null,gender:null,birthDate:"2000-03-20"},
		      birthDateString: null,
		      cantSubmit: true,
		      nameNotValid: true,
		      surnameNotValid: true,
		      usernameNotValid: true,
		      passwordNotValid: true,
		    }
	},
	template: ` 
<div style="text-align:center;">
    <h2 style="font-size: 55px;">Registration</h2>
        <table style="margin-left:auto; margin-right:auto;">
        <tr>
            <td align="left"><strong style="font-size: 30px;">Username:</strong></td>
            <td><input type="text" v-model="user.username" style="font-size: 25px;width: 342px;" v-on:change = "validateUsername" name="username"></input></td>
        </tr>
        <tr>
            <td align="left"><strong style="font-size: 30px;" >Password:</strong> </td>
                <td>
                <input type="password" v-model="user.password" style="font-size: 25px;width: 342px;" v-on:change = "validatePassword" name="password"></input> 
                </td>
        </tr>
        <tr>
            <td align="left"><strong style="font-size: 30px;">Name:</strong></td>
            <td><input type="text" v-model="user.name" style="font-size: 25px;width: 342px;" v-on:change = "validateName" name="name"></input></td>
        </tr>
        <tr>
        <td align="left"><strong style="font-size: 30px;">Surname:</strong></td>
        <td><input type="text" v-model="user.surname" style="font-size: 25px;width: 342px;" v-on:change = "validateSurname" name="surname"></input></td>
        </tr>
        <tr>
        <td align="left"><strong style="font-size: 30px;">Gender:</strong></td>
            <td><select name="gender" id="gender" v-model="user.gender" style="font-size: 25px; width: 350px;">
            	<option value="Male">male</option>
            	<option value="Female">female</option>
            	<option value="Alien">alien</option>
            </select></td>
        </tr>
        <tr>
        <td align="left"><strong style="font-size: 30px;">Birth Date:</strong></td>
        <td><input type="date" v-model="user.birthDate" style="font-size: 25px; width: 345px;"></input></td>
        </tr>
        <tr style="height:70px">
        	<td colspan="2">
	        	<button v-on:click="addCustomer()" :disabled="cantSubmit" style="font-size: 25px; width: 42%;margin: 0px 10px;"> Submit </button> 
	        	<button v-on:click="ShowLoginForm()"style="font-size: 25px; width: 42%; margin: 0px 10px;">Back main page</button>
        	</td>
        </tr>
    </table>
    <div style="text-align:left;">
	    <p style="font-size:20px;" v-show=usernameNotValid>You should enter username</p>
	    <p style="font-size:20px;" v-show=passwordNotValid>You should enter password</p>
	    <p style="font-size:20px;" v-show=nameNotValid>You should valid enter name(first letter uppercase without numbers)</p>
	    <p style="font-size:20px;" v-show=surnameNotValid>You valid should enter surname(first letter uppercase without numbers)</p>
    </div>
</div> 
`
	, 
	methods : {
		checkCanConfirm: function(){
			if(!this.nameNotValid & !this.surnameNotValid & !this.usernameNotValid & !this.passwordNotValid)
			{
				this.cantSubmit = false;
			}
			else this.cantSubmit = true;
		},
		validateName: function(){
			const regex = new RegExp('^[A-Z.-]+(\s*[A-Za-z.-]+)*$');
			let name = document.getElementsByName('name')[0].value;
			name = name + "e";
			if(regex.test(name) & !(name === "e"))
			{
				this.nameNotValid = false;
			}
			else
			{
				this.nameNotValid = true;
			}
			this.checkCanConfirm();
		},
		validateSurname: function(){
			const regex = new RegExp('^[A-Z.-]+(\s*[A-Za-z.-]+)*$');
			let surname = document.getElementsByName('surname')[0].value;
			surname = surname + "e";
			if(regex.test(surname) & !(surname === "e"))
			{
				this.surnameNotValid = false;
			}
			else
			{
				this.surnameNotValid = true;
			}
			this.checkCanConfirm();
		},
		validateUsername: function(){
			let username = document.getElementsByName('username')[0].value;
			username = username + "e";
			if(!(username === "e"))
			{
				this.usernameNotValid = false;
			}
			else
			{
				this.usernameNotValid = true;
			}
			this.checkCanConfirm();
		},
		validatePassword: function(){
			let password = document.getElementsByName('password')[0].value;
			password = password + "e";
			if(!(password === "e"))
			{
				this.passwordNotValid = false;
			}
			else
			{
				this.passwordNotValid = true;
			}
			this.checkCanConfirm();
		},
		init : function() {
			this.user = {};
		}, 
		ShowLoginForm : function () {
			router.push(`/`);
		},
		loginFinal : function(data){
			if (data == "success"){
				alert("You are now registered, please login.");
			}
			else alert(data);
		},
		addCustomer : function () {
				axios
		          .post('customer/add',this.user)
		          .then(response => (this.loginFinal(response.data)));
		    
		}
	},
	mounted () {
		//this.user.birthDate = new Date(Date.now()).toISOString().split('T')[0]
		this.user.gender = "Male";
    }
});