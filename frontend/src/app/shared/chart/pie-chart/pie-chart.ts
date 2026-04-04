import { Component, Input, ViewChild, ElementRef, AfterViewInit, OnChanges } from '@angular/core';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-pie-chart',
  imports: [],
  templateUrl: './pie-chart.html',
  styleUrl: './pie-chart.css',
})
export class PieChart {
 @Input() titulo: string = 'Grafico de Porcentaje';
  @Input() labels: string[] = [];
  @Input() data: number[] = [];

  @ViewChild('pieCanvas') pieCanvas!: ElementRef;

  chart!: Chart;

  ngAfterViewInit() {
    this.renderChart();
  }

  ngOnChanges() {
    if (this.chart) {
      this.chart.destroy();
      this.renderChart();
    }
  }

  renderChart() {

    if (!this.pieCanvas) return;

    this.chart = new Chart(this.pieCanvas.nativeElement, {
      type: 'pie',
      data: {
        labels: this.labels,
        datasets: [
          {
            data: this.data, // ← NÚMEROS
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          tooltip: {
            callbacks: {
              label: (context: any) => {
                const label = context.label || '';
                const value = context.raw; 
                return ` ${value}%`; 
              }
            }
          }
        }
      }
    });
  }
}
