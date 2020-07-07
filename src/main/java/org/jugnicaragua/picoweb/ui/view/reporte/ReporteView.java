package org.jugnicaragua.picoweb.ui.view.reporte;

import org.jugnicaragua.picoweb.backend.servicio.PropietarioServicio;
import org.jugnicaragua.picoweb.ui.view.list.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Map;

@Route(value = "reporte", layout = MainLayout.class)
@PageTitle("Reporte | Pico Web")
public class ReporteView extends VerticalLayout {

    private PropietarioServicio propietarioServicio;

    public ReporteView(PropietarioServicio propietarioServicio) {
        this.propietarioServicio=propietarioServicio;
        addClassName("reporte-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getPropietarioStats(), getPropietarioChart());
    }

    private Component getPropietarioStats() {
        Span stats = new Span(propietarioServicio.count() + " clientes");
        stats.addClassName("cliente-stats");
        return stats;
    }

    private Chart getPropietarioChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> companies = propietarioServicio.getStats();
        companies.forEach((company, employees) ->
                dataSeries.add(new DataSeriesItem(company, employees)));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}
