package view.swing.table;

import view.model.ModelStudent;

public interface EventAction {

    public void delete(ModelStudent student);

    public void update(ModelStudent student);
//    public void view(ModelStudent student);
}
