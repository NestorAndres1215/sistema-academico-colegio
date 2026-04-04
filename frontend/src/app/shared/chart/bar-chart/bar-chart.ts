import { AfterViewInit, Component, ElementRef, Input, ViewChild, OnChanges } from '@angular/core';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-bar-chart',
  imports: [],
  templateUrl: './bar-chart.html',
  styleUrl: './bar-chart.css',
})
export class BarChart {

  @Input() labels: string[] = [];
  @Input() data: number[] = [];
  @Input() titulo: string = 'Gráfico de Barras';
  @ViewChild('barCanvas') barCanvas!: ElementRef<HTMLCanvasElement>;
  barChart!: Chart;


  ngAfterViewInit(): void {
    if (this.labels.length > 0 && this.data.length > 0) {
      this.renderChart();
    }
  }

  ngOnChanges(): void {
    if (this.barChart) {
      this.barChart.destroy();
    }
    if (this.labels.length > 0) {
      this.renderChart();
    }
  }

  renderChart() {
    console.log(this.labels)
    this.barChart = new Chart(this.barCanvas.nativeElement, {
      type: 'bar',
      data: {
        labels: this.labels,
        datasets: [
          {
            label: 'Porcentaje por Módulo (%)',
            data: this.data,
            backgroundColor: 'rgba(54, 162, 235, 0.6)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
          }
        ]
      },
      options: {
        responsive: true,
        scales: {
          y: { beginAtZero: true }
        }
      }
    });
  }

}
