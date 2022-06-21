Vue.component("myprofile", {
	data: function () {
		    return {
				log:false,
				user:null,
				mode:''
		    }
	},
	template: ` 
    <div style="text-align:center;">
    <h2 style="font-size: 55px;">My Profile</h2>
    <table style="margin-left:auto; margin-right:auto;">
        <tr>
            <td>Username:<td>
                <td><input v-model="user.username" disabled/></td>
        </tr>
        <tr>
            <td>Password:<td>
                <td><input v-model="user.password"  v-bind:disabled="mode=='achange'"/></td>
        </tr>
        <tr>
            <td>Name:<td>
                <td><input v-model="user.name"  v-bind:disabled="mode=='achange'"/></td>
        </tr>
        <tr>
            <td>Surname:<td>
                <td><input v-model="user.surname"  v-bind:disabled="mode=='achange'"/></td>
        </tr>
        <tr>
            <td>Gender:<td>
                <td>
                <select name="gender" id="gender" v-model="user.gender"  v-bind:disabled="mode=='achange'">
                    <option value="Male">male</option>
                    <option value="Female">female</option>
                    <option value="Alien">alien</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Birth Date:<td>
                <td><input type="date" v-model="user.birthDate"  v-bind:disabled="mode=='achange'"/></td>
        </tr>
 
    </table>
    <div class="vspace"></div>
    <button v-on:click="save()" v-bind:disabled="mode=='achange'">Save</button>
    <div class="hspace"></div>
    <button v-on:click="change()">Change Profile</button>
</div> 
`
	, 
	methods : {
		change : function () {
			this.mode = "change";
		},
		save : function() {
			if (!confirm('Are you sure you wanna save changes?'));			
				else {
					axios
						.post('user/changeUser', this.user);
					this.mode = "achange";
				}
			
		}
	},
	mounted () {
		this.mode = "achange";
		axios
			.get('user/getLoggedUser')
			.then(response => (this.user = response.data));
    }
});