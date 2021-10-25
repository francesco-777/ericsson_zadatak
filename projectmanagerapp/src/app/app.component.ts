import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Project } from './project';
import { ProjectService } from './project.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  public projects: Project[];
  public editProject: Project;
  public deleteProject: Project;
  title: any;

  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.getProjects();
  }

  public getProjects(): void {
    this.projectService.getProjects().subscribe(
      (response: Project[]) => {
        this.projects = response;
        console.log(this.projects);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddProject(addForm: NgForm): void {
    document.getElementById('add-project-form').click();
    this.projectService.addProject(addForm.value).subscribe(
      (response: Project) => {
        console.log(response);
        this.getProjects;
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateProject(project: Project): void {
    this.projectService.updateProject(project).subscribe(
      (response: Project) => {
        console.log(response);
        this.getProjects();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteProject(projectId: number): void {
    this.projectService.deleteProject(projectId).subscribe(
      (response: void) => {
        console.log(response);
        this.getProjects();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchProjects(key: string): void {
    console.log(key);
    const results: Project[] = [];
    for (const project of this.projects) {
      if (project.title.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || project.desc.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || project.status.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(project);
      }
    }
    this.projects = results;
    if (results.length === 0 || !key) {
      this.getProjects();
    }
  }

  public onOpenModal(project: Project, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addProjectModal');
    }
    if (mode === 'edit') {
      this.editProject = project;
      button.setAttribute('data-target', '#updateProjectModal');
    }
    if (mode === 'delete') {
      this.deleteProject = project;
      button.setAttribute('data-target', '#deleteProjectModal');
    }
    container.appendChild(button);
    button.click();
  }

}
