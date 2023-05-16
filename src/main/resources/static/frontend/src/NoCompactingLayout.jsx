import React from "react";
import _ from "lodash";
import RGL, { WidthProvider } from "react-grid-layout";
import Box from "@mui/material/Box";
const ReactGridLayout = WidthProvider(RGL);

export default class NoCompactingLayout extends React.PureComponent {
    static defaultProps = {
        className: "layout",
        items: ["ab","cd","ef"],
        cols: 12,
        rowHeight: 30,
        onLayoutChange: function() {},
        // This turns off compaction so you can place items wherever.
        verticalCompact: false
    };

    constructor(props) {
        super(props);

        const layout = this.generateLayout();
        this.state = { layout };
    }

    generateDOM() {

        return _.map(this.props.items, function(i) {
            return (
                <div key={i} style={{backgroundColor:"gray"}}>
                        <span className="text">{i}</span>

                </div>
            );
        });
    }

    generateLayout() {
        const p = this.props;
        const availableHandles = ["s", "w", "e", "n", "sw", "nw", "se", "ne"];
        return _.map(new Array(p.items), function(item, i) {
            const y = _.result(p, "y") || Math.ceil(Math.random() * 4) + 1;
            return {
                x: (i * 2) % 12,
                y: Math.floor(i / 6) * y,
                w: 3,
                h: 3,
                i: i.toString(),
                resizeHandles: _.shuffle(availableHandles).slice(0, _.random(1, availableHandles.length-1))
            };
        });
    }

    onLayoutChange(layout) {
        this.props.onLayoutChange(layout);
    }

    render() {
        return (
            <ReactGridLayout
                isResizeable={true}
                layout={this.state.layout}
                onLayoutChange={this.onLayoutChange}
                {...this.props}
            >
                {this.generateDOM()}
            </ReactGridLayout>
        );
    }
}

