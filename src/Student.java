class Student{
    private StringBuilder name;
    public Student(String name){
        this.name=new StringBuilder(name);
    }

    public String getName() {
        return name.toString();
    }

    public void setName(StringBuilder name) {
        this.name = name;
    }
}