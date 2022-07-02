Vue.component("registerCoachManager", {
	data: function () {
		    return {
			  user: {name:null,surname:null,username:null,password:null,gender:null,birthDate:"2000-03-20",userType:null,sportBuilding:"None"},
		      backTitle: "Back main page",
		      birthDateString: null,
		      test:false,
		      allsportBuildings: null,
		      cantSubmit: true,
		      nameNotValid: true,
		      surnameNotValid: true,
		      usernameNotValid: true,
		      passwordNotValid: true,
		      isManager:false
		    }
	},
	template: ` 
<div style="text-align:center;">
    <h2 style="font-size: 55px;">Registration of coaches and managers</h2>
        <table style="margin-left:auto; margin-right:auto;">
        <tr>
        <td align="left" style="width: 250px"><strong style="font-size: 30px;">Role:</strong></td>
            <td><select name="usertype" id="utype" v-model="user.userType" v-on:click="CheckType" style="font-size: 25px; width: 350px;">
            	<option value="Coach">coach</option>
            	<option value="Manager">manager</option>
            </select></td>
        </tr>
        <tr v-if="isManager">
        <td align="left"><strong style="font-size: 30px;">Sport Building:</strong></td>
            <td><select name="sportB" id="sportB" v-model="user.sportBuilding" style="font-size: 25px; width: 350px;">
           		<option value="None">None</option>
            	<option v-for="(object, index) in this.allsportBuildings">{{object.name}}</option>
            </select></td>
        </tr>
        <div class = vspace style="height: 10px;" > </div>
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
	        	<button v-on:click="back()"style="font-size: 25px; width: 42%; margin: 0px 10px;">Back main page</button>
        	</td>
        </tr>
    </table>
    <div style="text-align:left;">
	    <p style="font-size:20px;" v-show=usernameNotValid>You should enter username</p>
	    <p style="font-size:20px;" v-show=passwordNotValid>You should enter password</p>
	    <p style="font-size:20px;" v-show=nameNotValid>You should valid enter name(first letter uppercase without numbers)</p>
	    <p style="font-size:20px;" v-show=surnameNotValid>You valid should enter surname(first letter uppercase without numbers)</p>
    </div>
    </table>
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
		CheckType: function(){
			if(this.user.userType == "Manager")
				this.isManager = true;
			else {
				this.isManager = false;
				this.user.sportBuilding = "None";
			}
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
		back : function () {
			router.push(`/`);
		},
		loginFinal : function(data){
			if (data == "success"){
				alert("You are now registered, please login.");
				if(this.user.sportBuilding != "None"){
					this.allsportBuildings = this.allsportBuildings.filter(data1 => data1.name != this.user.sportBuilding);
					this.user.sportBuilding = "None";
				}
				//router.push(`/login`);
			}
			else alert(data);
		},
		addCustomer : function () {
			if(this.user.userType == "Coach")
			{
				axios
		          .post('coach/add',this.user)
		          .then(response => (this.loginFinal(response.data)));
		    }
		    else axios
		          .post('manager/add',this.user)
		          .then(response => (this.loginFinal(response.data)));
		}
	},
	mounted () {
		axios
			.get('sportBuilding/getAllNoManager')
			.then(response => (this.allsportBuildings = response.data));
		this.user.gender = "Male";
		this.user.userType = "Coach";
    }
});