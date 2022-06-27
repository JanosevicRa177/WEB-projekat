Vue.component("myprofile", {
	data: function () {
		    return {
				log:false,
				user:null,
				mode:'',
				cantSubmit: false,
		      	nameNotValid: false,
		     	surnameNotValid: false,
		      	usernameNotValid: false,
		      	passwordNotValid: false,
		      	currentPassword:null
		    }
	},
	template: ` 
    <div style="text-align:center;">
    <h2 style="font-size: 55px;">My Profile</h2>
    <table style="margin-left:auto; margin-right:auto;text-align:left;">
        <tr>
            <td>Username:<td>
                <td><input style="width:392px;" v-model="user.username" disabled/></td>
        </tr>
        <tr>
            <td>New password:<td>
                <td><input style="width:392px;" type="password" placeholder="New password" v-model="user.password" v-on:change = "validatePassword" name="password"  v-bind:disabled="mode=='achange'"/></td>
        </tr>
        <tr>
            <td text-align:right>Name:<td>
                <td><input style="width:392px;" v-model="user.name" name="name" v-on:change="validateName" v-bind:disabled="mode=='achange'"/></td>
        </tr>
        <tr>
            <td>Surname:<td>
                <td><input style="width:392px;" v-model="user.surname" name="surname" v-on:change = "validateSurname" v-bind:disabled="mode=='achange'"/></td>
        </tr>
        <tr>
            <td>Gender:<td>
                <td>
                <select style="width:400px;" name="gender" id="gender" v-model="user.gender"  v-bind:disabled="mode=='achange'">
                    <option value="Male">male</option>
                    <option value="Female">female</option>
                    <option value="Alien">alien</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Birth Date:<td>
                <td><input style="width:395px;" type="date" v-model="user.birthDate"  v-bind:disabled="mode=='achange'"/></td>
        </tr>
        
        <tr>
        <div style="width:auto;height:25px"/>
        </tr>
 
 		 <tr>
            <td>Current password:<td>
                <td><input style="width:392px;" type="password" v-model="currentPassword" name="Cpassword"  v-bind:disabled="mode!='achange'"/></td>
        </tr>
    </table>
    <div class="vspace"></div>
    <button v-on:click="save()"  style="width: 250px;font-size:35px;" v-bind:disabled="mode=='achange'  || cantSubmit">Save</button>
    <div class="hspace"></div>
    <button v-on:click="change()" style="width: 250px;font-size:35px;">Change Profile</button>
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
		checkpass: function(bool) {
			if(bool == true) this.mode = "change";
			else toast("Enter your current password!");
		},
		change : function () {
			axios
				.get('user/checkPassword', { params: {currentPassword: this.currentPassword} })
				.then(response => (this.checkpass(response.data)));
		},
		save : function() {
			if (!confirm('Are you sure you wanna save changes?'));			
				else {
					axios
						.put('user/changeUser', this.user);
					this.mode = "achange";
				}
			},
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
		}
	},
	mounted () {
		this.mode = "achange";
		axios
			.get('user/getLoggedUser')
			.then(response => (this.user = response.data,this.user.password = ''));
    }
});