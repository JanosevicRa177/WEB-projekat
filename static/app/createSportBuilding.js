Vue.component("createSportBuilding", {
	data: function () {
		    return {
			user: {name:null,surname:null,username:null,password:null,gender:"Male",birthDate:"2000-03-20",userType:"Manager",sportBuilding:"None"},
			  sportBuilding: {wrokTime:'',averageGrade:5,name:null,type:null,image:null,manager:null,location:{longitude:0,latitude:0,address:{city:null,street:null,number:null,zipCode:null}}},
			  allManagers:[],
		      birthDateString: null,
		      cantSubmit: true,
		      nameNotValid: true,
		      regMan:false,
		      cityNotValid:true,
		      streetNotValid:true,
		      numberNotValid:true,
		      zipCodeNotValid:true,
		      cantAllSubmit:true,
		      notSelectedManager:true,
		      urlNotValid:true,
		      usernameNotValid:true,
		      nameNotValid:true,
		      surnameNotValid:true,
		      passwordNotValid:true,
		      open:00,
		      openhalf:00,
		      close:00,
		      closehalf:00
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
	            		<td><input type="text" v-model="sportBuilding.name" style="font-size: 25px;width: 342px;" v-on:change = "validateName" name="name"></input></td>
	        		</tr>
	        		<tr>
	        			<td align="left"><strong style="font-size: 30px;"> Location:</strong></td>
	        			</tr>
	         		<tr>
	        			<td align="right"><strong style="font-size: 30px;"> City:</strong></td>
	        			<td><input type="text"  v-model="sportBuilding.location.address.city" style="font-size: 25px;width: 342px;" name="city" v-on:change = "validateCity"></input></td>
	        		</tr>
	        		<tr>
	        			<td align="right"><strong style="font-size: 30px;">		Street:</strong></td>
	        			<td><input type="text" v-model="sportBuilding.location.address.street" style="font-size: 25px;width: 342px;" name="street" v-on:change = "validateStreet"></input></td>
	        		</tr>
	        		<tr>
	        			<td align="right"><strong style="font-size: 30px;">		Number:</strong></td>
	        			<td><input type="text" v-model="sportBuilding.location.address.number" style="font-size: 25px;width: 342px;"  name="number" v-on:change = "validateNumber"></input></td>
	        		</tr>
	        		<tr>
	        			<td align="right"><strong style="font-size: 30px;">		Zip Code:</strong></td>
	        			<td><input type="text" v-model="sportBuilding.location.address.zipCode" style="font-size: 25px;width: 342px;"  name="zipcode" v-on:change = "validateZipCode"></input></td>
	        		</tr>
	        		<tr>
			        	<td align="left"><strong  style="font-size: 30px;">Type:</strong></td>
			            <td>
				             <select v-model="sportBuilding.type" style="font-size: 25px; width: 350px;">
				            	<option value="Gym">Gym</option>
				            	<option value="Pool">Pool</option>
				            	<option value="sportCenter">SportCenter</option>
				            	<option value="danceStudio">danceStudio</option>
		            		</select>
	            		</td>
			        </tr>
	        		<tr>
						<td align="left"><strong style="font-size: 30px;">URL of logo image:</strong></td>
						<td><input type="text" v-model="sportBuilding.image" style="font-size: 25px;width: 342px;" v-on:change = "urlCheck" name="url"></input></td>
					</tr>
			        <tr>
			        	<td align="left"><strong style="font-size: 30px;">Manager:</strong></td>
			            <td>
				            <select v-if="regMan == false" name="managers" id="managers" v-model="sportBuilding.manager" v-on:change = "selectedManager" style="font-size: 25px; width: 350px;">
				            	<option v-for="(object, index) in this.allManagers">{{object.username}}</option>
				            </select>
				            <input readonly v-if="regMan" type="text" v-model="user.username" style="font-size: 25px;width: 342px;" name="c"></input>
			            </td>
			        </tr>
			        <tr>
			        	<td align="left"><strong  style="font-size: 30px;">Grade:</strong></td>
			            <td>
				             <select v-model="sportBuilding.averageGrade" style="font-size: 25px; width: 350px;">
				            	<option value="5">5</option>
				            	<option value="6">6</option>
				            	<option value="7">7</option>
				            	<option value="8">8</option>
				            	<option value="9">9</option>
				            	<option value="10">10</option>
		            		</select>
	            		</td>
			        </tr>
			        	<tr>
			        	<td align="left"><strong style="font-size: 30px;">Work Time:</strong></td>
			        	<td>
			        	<table>
			        	<tr>
			        	<td><input v-model="open" v-on:change="openclosechanged" type="number" style="font-size: 25px;width:  45px;" min="0"max="23" ></input></td>
			        	<td>:</td>
			        	<td><input v-model="openhalf" v-on:change="openclosechanged" type="number" style="font-size: 25px;width:  45px;" min="0"max="30"step="30" ></input></td>
			        	<td>-</td>
			        	<td><input v-model="close" v-on:change="openclosechanged" type="number" style="font-size: 25px;width:  45px;" min="0"max="23" ></input></td>
			        	<td>:</td>
			        	<td><input v-model="closehalf" v-on:change="openclosechanged" type="number" style="font-size: 25px;width:  45px;" min="0"max="30"step="30" ></input></td>
			        	</tr>
			        	</table>
			        	</td>
			        	</tr>
			        <tr style="height:70px">
			        	<td colspan="2">
				        	<button v-on:click="Submit()" :disabled="cantAllSubmit" style="font-size: 25px; width: 42%;margin: 0px 10px;"> Submit </button> 
			        	</td>
			        </tr>
	    		</table>
	    		 <p style="font-size:20px;" v-show=nameNotValid>You should enter valid name(first letter uppercase without numbers)</p>
    <p align="left" style="font-size:20px;" v-show=cityNotValid>You should enter valid city(first letter uppercase without numbers)</p>
    <p style="font-size:20px;" v-show=streetNotValid>You should enter valid street(first letter uppercase without numbers)</p>
    <p style="font-size:20px;" v-show=numberNotValid>You should enter valid number(no special characters)</p>
    <p style="font-size:20px;" v-show=zipCodeNotValid>You should enter valid zipCode(numbers only)</p>
    <p style="font-size:20px;" v-show=urlNotValid>Please enter url for logo!</p>
    <p style="font-size:20px;" v-show=notSelectedManager>Please select manager!</p>
	    	</td>
			<td>
				<div style="text-align:top;">
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
					        	<button v-on:click="addManager()" :disabled="cantSubmit" style="font-size: 25px; width: 42%;margin: 0px 10px;"> Submit </button> 
				        	</td>
			        	</tr>
	        		</table>
	    		</div> 
	    		    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=usernameNotValid>You should enter username</p>
    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=passwordNotValid>You should enter password</p>
    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=nameNotValid>You should enter valid name(first letter uppercase without numbers)</p>
    <p :style="{visibility: regMan ? 'visible' : 'hidden'}" style="font-size:20px;" v-show=surnameNotValid>You should enter valid surname(first letter uppercase without numbers)</p>
	    	</td>
	    </tr>
    </table>

</div>
`
	, 
	methods : {
		openclosechanged : function() {
			this.sportBuilding.workTime = this.open + ":" + this.openhalf + "-" + this.close + ":" + this.closehalf; 
			alert(this.sportBuilding.workTime);
		},
		checkCanConfirm: function(){
			if(!this.nameNotValid & !this.surnameNotValid & !this.usernameNotValid & !this.passwordNotValid)
			{
				this.cantSubmit = false;
			}
			else this.cantSubmit = true;
		},
		checkCanSubmit: function() {
			if(!this.cityNotValid & !this.streetNotValid & !this.numberNotValid & !this.zipCodeNotValid & !this.urlNotValid & (this.sportBuilding.manager != null))
				this.cantAllSubmit = false;
			else this.cantAllSubmit = true;
		},
		selectedManager : function() {
			this.notSelectedManager = false;
			this.checkCanSubmit();
		},
		bruh : function(data){
			if (data == "Manager registered successfuly!"){
				alert("Manager registered.");
				this.notSelectedManager = false;
				this.cantSubmit = true;
				this.sportBuilding.manager = this.user.username;
				this.checkCanSubmit();
			}
			else alert(data);
		},
		addManager : function () {
				axios
		          .post('manager/add',this.user)
		          .then(response => (this.bruh(response.data)));
		}
		,
		urlCheck :function() {
			let name = document.getElementsByName('url')[0].value;
			name = name + "e";
			if(!(name === "e"))
			{
				this.urlNotValid = false;
			}
			else
			{
				this.urlNotValid = true;
			}
			this.checkCanSubmit();
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
			this.checkCanSubmit();
		},
		validateCity : function() {
			const regex = new RegExp('^[A-Z.-]+(\s*[A-Za-z.-]+)*$');
			let name = document.getElementsByName('city')[0].value;
			name = name + "e";
			if(regex.test(name) & !(name === "e"))
			{
				this.cityNotValid = false;
			}
			else
			{
				this.cityNotValid = true;
			}
			this.checkCanSubmit();
		},
		validateStreet : function() {
			const regex = new RegExp('^[A-Z.-]+(\ *\s*[A-Za-z.-]+)*$');
			let name = document.getElementsByName('street')[0].value;
			name = name + "e";
			if(regex.test(name) & !(name === "e"))
			{
				this.streetNotValid = false;
			}
			else
			{
				this.streetNotValid = true;
			}
			this.checkCanSubmit();
		},
		validateNumber : function() {
			const regex = new RegExp('^(\s*[A-Za-z0-9.-]+)*$');
			let name = document.getElementsByName('number')[0].value;
			name = name + "e";
			if(regex.test(name) & !(name === "e"))
			{
				this.numberNotValid = false;
			}
			else
			{
				this.numberNotValid = true;
			}
			this.checkCanSubmit();
		},
		validateZipCode : function () {
			const regex = new RegExp("^\\d+$");
			let name = document.getElementsByName('zipcode')[0].value;
			name = name + "0";
			if(regex.test(name) & !(name === "0"))
			{
				this.zipCodeNotValid = false;
			}
			else
			{
				this.zipCodeNotValid = true;
			}
			this.checkCanSubmit();
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
		    if(data == null) {
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
		end: function(data) {
			if(data == "success")
			{
				alert("Sport building created");
				router.push('/');
			}
			else alert(data);	
			},
		Submit : function() {
			axios 
				.post('sportBuilding/add',this.sportBuilding)
				.then(response => (this.end(response.data)));
		}
	},
	mounted () {
		//this.user.birthDate = new Date(Date.now()).toISOString().split('T')[0]
		this.sportBuilding.type = "Gym";
		axios
			.get('manager/getAllNoSportBuilding')
			.then(response => (this.init(response.data)));
    }
    });