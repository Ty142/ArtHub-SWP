package Arthub.entity;


public class Role {
    private int roleID;
    private String roleName;

    public Role(){}

    public int getRoleId() {
        return roleID;
    }

    public void setRoleId(int roleId) {
        this.roleID = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
