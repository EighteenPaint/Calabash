package com.hbh.bean;

/**
 * Created by chenbin@megaeyes.com on 2018/3/16 0016.
 */
public class ModelAndView {
    private Model model;
    private String viewName;

    public void addAttribute(String name, Object object){
        if(this.model == null){
            model = new Model();
        }
        model.addAttribute(name,object);
    }

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public ModelAndView() {
    }

    public ModelAndView(Model model,String vierName) {
        this.model = model;
        this.viewName = vierName;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
