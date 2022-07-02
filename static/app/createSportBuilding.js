Vue.component("createSportBuilding", {
	data: function () {
		    return {
			user: {name:null,surname:null,username:null,password:null,gender:null,birthDate:"2000-03-20"},
			  sportBuilding: {name:null,type:null,logo:null,manager:null},
			  allManagers:[],
		      birthDateString: null,
		      cantSubmit: true,
		      nameNotValid: true,
		      regMan:false
		    }
	},
	template: ` 
<div style="text-align:center;">
    <h2 style="font-size: 55px;">Registration of Sport Building</h2>
    <table style="margin-left:auto; margin-right:auto;">
	    <tr>
	    	<td>
	        	<table style="margin-left:auto; margin-right:auto;">
	            	<tr>
	            		<td align="left"><strong style="font-size: 30px;">Name:</strong></td>
	            		<td><input type="text" v-model="sportBuilding.name" style="font-size: 25px;width: 342px;" v-on:change = "validateName" name="username"></input></td>
	        		</tr>
	         		<tr>
	        			<td align="left"><strong style="font-size: 30px;">Location? xd:</strong></td>
	        			<td><input type="text" style="font-size: 25px;width: 342px;" placeholder="nisam znao kako ovo da odradim pa sam ostavio za kasnije xd"  name="surname"></input></td>
	        		</tr>
	        		<tr>
			        	<td align="left"><strong style="font-size: 30px;">Type:</strong></td>
			            <td>
				             <select style="font-size: 25px; width: 350px;">
				            	<option value="Gym">Gym</option>
				            	<option value="Pool">Pool</option>
				            	<option value="sportCenter">SportCenter</option>
				            	<option value="danceStudio">danceStudio</option>
		            		</select>
	            		</td>
			        </tr>
	        		<tr>
						<td align="left"><strong style="font-size: 30px;">URL of logo image:</strong></td>
						<td><input type="text" v-model="sportBuilding.logo" style="font-size: 25px;width: 342px;" name="surname"></input></td>
					</tr>
			        <tr>
			        	<td align="left"><strong style="font-size: 30px;">Manager:</strong></td>
			            <td>
				            <select name="sportB" id="sportB" v-model="sportBuilding.manager" style="font-size: 25px; width: 350px;">
				            	<option v-for="(object, index) in this.allManagers">{{object.username}}</option>
				            </select>
			            </td>
			        </tr>
			        <tr style="height:70px">
			        	<td colspan="2">
				        	<button v-on:click="addCustomer()" :disabled="cantSubmit" style="font-size: 25px; width: 42%;margin: 0px 10px;"> Submit </button> 
				        	<button v-on:click="ShowLoginForm()"style="font-size: 25px; width: 42%; margin: 0px 10px;">Back main page</button>
			        	</td>
			        </tr>
	    		</table>
	    	</td>
			<td>
				<div style="text-align:center;">
	        		<table style="margin-left:auto; margin-right:auto;" v-if="regMan">
			            <tr>
			                <td colspan = "2"> There is no avalible manager, register new one </td>
			            </tr>
				        <tr>
				            <td align="left"><strong style="font-size: 30px;">Username:</strong></td>
				            <td><input type="text" v-bind:disabled="regMan==false" v-model="user.username" style="font-size: 25px;width: 342px;" v-on:change = "validateUsername" name="username"></input></td>
				        </tr>
				        <tr>
				            <td align="left"><strong style="font-size: 30px;" >Password:</strong> </td>
				                <td>
				                	<input v-bind:disabled="regMan==false"  type="password" v-model="user.password" style="font-size: 25px;width: 342px;" v-on:change = "validatePassword" name="password"></input> 
				                </td>
				        </tr>
				        <tr>
				            <td align="left"><strong style="font-size: 30px;">Name:</strong></td>
				            <td><input v-bind:disabled="regMan==false"  type="text" v-model="user.name" style="font-size: 25px;width: 342px;" v-on:change = "validateManName" name="manname"></input></td>
				        </tr>
				        <tr>
					        <td align="left"><strong style="font-size: 30px;">Surname:</strong></td>
					        <td><input v-bind:disabled="regMan==false"  type="text" v-model="user.surname" style="font-size: 25px;width: 342px;" v-on:change = "validateSurname" name="surname"></input></td>
				        </tr>
				    	<tr>
				    	<td align="left"><strong style="font-size: 30px;">Gender:</strong></td>
				            <td>
					            <select v-bind:disabled="regMan==false"  name="gender" id="gender" v-model="user.gender" style="font-size: 25px; width: 350px;">
					            	<option value="Male">male</option>
					            	<option value="Female">female</option>
					            	<option value="Alien">alien</option>
					            </select>
				            </td>
				        </tr>
	        			<tr>
					        <td align="left"><strong style="font-size: 30px;">Birth Date:</strong></td>
					        <td><input v-bind:disabled="regMan==false" type="date" v-model="user.birthDate" style="font-size: 25px; width: 345px;"></input></td>
	        			</tr>
			        	<tr style="height:70px">
				        	<td colspan="2">
					        	<button v-on:click="addCustomer()" :disabled="cantSubmit" style="font-size: 25px; width: 42%;margin: 0px 10px;"> Submit </button> 
				        	</td>
			        	</tr>
	        		</table>
	    		</div> 
	    	</td>
	    </tr>
    </table>
    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=usernameNotValid>You should enter username</p>
    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=passwordNotValid>You should enter password</p>
    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=nameNotValid>You should enter valid name(first letter uppercase without numbers)</p>
    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=surnameNotValid>You should enter valid surname(first letter uppercase without numbers)</p>
	<div style="text-align:left;">
	<p style="font-size:20px;" v-show=nameNotValid>You should enter valid name(first letter uppercase without numbers)</p>
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
		ShowLoginForm : function () {
			router.push(`/`);
		},
		loginFinal : function(data){
			if (data == "success"){
				alert("You are now registered, please login.");
			}
			else alert(data);
		},
		init : function (data) {
		this.allManagers = data;
		    if(this.allManagers.length == 0) {
			this.regMan = true;
		}
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
		validateManName: function(){
			const regex = new RegExp('^[A-Z.-]+(\s*[A-Za-z.-]+)*$');
			let name = document.getElementsByName('manname')[0].value;
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
	},
	mounted () {
		//this.user.birthDate = new Date(Date.now()).toISOString().split('T')[0]
		this.sportBuilding.type = "Gym";
		axios
			.get('manager/getAll')
			.then(response => (this.init(response.data)));
    }
    });